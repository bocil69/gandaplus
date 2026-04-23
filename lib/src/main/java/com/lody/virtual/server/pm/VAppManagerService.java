package com.lody.virtual.server.pm;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.text.TextUtils;

import com.lody.virtual.GmsSupport;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.SpecialComponentList;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.client.stub.InstallerSetting;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.helper.DexOptimizer;
import com.lody.virtual.helper.collection.IntArray;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.compat.NativeLibraryHelperCompat;
import com.lody.virtual.helper.utils.ArrayUtils;
import com.lody.virtual.helper.utils.FileUtils;
import com.lody.virtual.helper.utils.Singleton;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.os.VEnvironment;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.os.VUserInfo;
import com.lody.virtual.os.VUserManager;
import com.lody.virtual.remote.InstallOptions;
import com.lody.virtual.remote.InstallResult;
import com.lody.virtual.remote.InstalledAppInfo;
import com.lody.virtual.server.accounts.VAccountManagerService;
import com.lody.virtual.server.am.AttributeCache;
import com.lody.virtual.server.am.BroadcastSystem;
import com.lody.virtual.server.am.UidSystem;
import com.lody.virtual.server.am.VActivityManagerService;
import com.lody.virtual.server.bit64.V64BitHelper;
import com.lody.virtual.server.interfaces.IAppManager;
import com.lody.virtual.server.interfaces.IPackageObserver;
import com.lody.virtual.server.job.VJobSchedulerService;
import com.lody.virtual.server.notification.VNotificationManagerService;
import com.lody.virtual.server.pm.parser.PackageParserEx;
import com.lody.virtual.server.pm.parser.VPackage;
import com.xdja.zs.VServiceKeepAliveManager;
import com.xdja.zs.VServiceKeepAliveService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.lody.virtual.remote.InstalledAppInfo.MODE_APP_COPY_APK;
import static com.lody.virtual.remote.InstalledAppInfo.MODE_APP_USE_OUTSIDE_APK;


/**
 * @author Lody
 */
public class VAppManagerService extends IAppManager.Stub {

    private static final String TAG = VAppManagerService.class.getSimpleName();
    private static final String BASE_APK_NAME = "base.apk";
    private static final String INSTALL_STAGE_DIR = "va-install";
    private static final Singleton<VAppManagerService> sService = new Singleton<VAppManagerService>() {
        @Override
        protected VAppManagerService create() {
            return new VAppManagerService();
        }
    };
    private final UidSystem mUidSystem = new UidSystem();
    private final PackagePersistenceLayer mPersistenceLayer = new PackagePersistenceLayer(this);
    private final Set<String> mVisibleOutsidePackages = new HashSet<>();
    private boolean mBooting;
    private RemoteCallbackList<IPackageObserver> mRemoteCallbackList = new RemoteCallbackList<>();

    private static final class InstallSource {
        final File parseTarget;
        final File baseApk;
        final List<File> splitApks;
        final File cleanupDir;

        InstallSource(File parseTarget, File baseApk, List<File> splitApks, File cleanupDir) {
            this.parseTarget = parseTarget;
            this.baseApk = baseApk;
            this.splitApks = splitApks;
            this.cleanupDir = cleanupDir;
        }

        List<File> getAllApks() {
            ArrayList<File> apks = new ArrayList<>(1 + splitApks.size());
            apks.add(baseApk);
            apks.addAll(splitApks);
            return apks;
        }
    }

    /*
        《A》
        该广播接收器监听外部应用安装/卸载，内部随之做相应的动作（外面的卸载，内部也卸载；外部安装，内部随之更新）。
        需要屏蔽掉。
     */
    private BroadcastReceiver appEventReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (mBooting) {
                return;
            }
            PendingResult result = goAsync();
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            Uri data = intent.getData();
            if (data == null) {
                return;
            }
            String pkg = data.getSchemeSpecificPart();
            if (pkg == null) {
                return;
            }
            PackageSetting ps = PackageCacheManager.getSetting(pkg);
            if (ps == null || ps.appMode != InstalledAppInfo.MODE_APP_USE_OUTSIDE_APK) {
                return;
            }
            VActivityManagerService.get().killAppByPkg(pkg, VUserHandle.USER_ALL);
            if (action.equals(Intent.ACTION_PACKAGE_REPLACED)) {
                ApplicationInfo outInfo = null;
                try {
                    outInfo = VirtualCore.getPM().getApplicationInfo(pkg, 0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                if (outInfo == null) {
                    return;
                }
                InstallOptions options = InstallOptions.makeOptions(true, false, InstallOptions.UpdateStrategy.FORCE_UPDATE);
                InstallResult res = installPackageImpl(outInfo.publicSourceDir, options);
                VLog.e(TAG, "Update package %s %s", res.packageName, res.isSuccess ? "success" : "failed");
            } else if (action.equals(Intent.ACTION_PACKAGE_REMOVED)) {
                if (intent.getBooleanExtra(Intent.EXTRA_DATA_REMOVED, false)) {
                    VLog.e(TAG, "Removing package %s...", ps.packageName);
                    uninstallPackageFully(ps, true);
                }
            }
            result.finish();
        }
    };


    public static VAppManagerService get() {
        return sService.get();
    }

    public static void systemReady() {
        VEnvironment.systemReady();
        if (!BuildCompat.isPie()) {
            get().extractRequiredFrameworks();
        }
        get().startup();
    }

    private void startup() {
        mVisibleOutsidePackages.add("com.android.providers.downloads");
        mVisibleOutsidePackages.addAll(com.lody.virtual.GmsSupport.GMS_PACKAGES);
        mUidSystem.initUidList();

        //见《A》
        /*IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addDataScheme("package");
        VirtualCore.get().getContext().registerReceiver(appEventReciever, filter);*/
    }

    public boolean isBooting() {
        return mBooting;
    }

    private void persistPackageState(String reason, String packageName) {
        mPersistenceLayer.save();
        if (!mPersistenceLayer.verifyIntegrity()) {
            VLog.w(TAG, "Persistence integrity check failed after %s for %s, retrying save.",
                    reason, packageName);
            mPersistenceLayer.save();
        }
    }

    private void extractRequiredFrameworks() {
        for (String framework : StubManifest.REQUIRED_FRAMEWORK) {
            File zipFile = VEnvironment.getFrameworkFile32(framework);
            File odexFile = VEnvironment.getOptimizedFrameworkFile32(framework);
            if (!odexFile.exists()) {
                OatHelper.extractFrameworkFor32Bit(framework, zipFile, odexFile);
            }
        }
    }

    @Override
    public void scanApps() {
        if (mBooting) {
            return;
        }
        synchronized (this) {
            mBooting = true;
            try {
                /*
                    这里将安装过的应用全部加载起来。
                 */
                mPersistenceLayer.read();
                if (mPersistenceLayer.changed) {
                    mPersistenceLayer.changed = false;
                    persistPackageState("persistence migration", "<all-packages>");
                    VLog.w(TAG, "Package PersistenceLayer updated.");
                }

                /*
                    预安装，目的不明。
                 */
                for (String preInstallPkg : SpecialComponentList.getPreInstallPackages()) {
                    if (!isAppInstalled(preInstallPkg)) {
                        try {
                            ApplicationInfo outInfo = VirtualCore.get().getUnHookPackageManager().getApplicationInfo(preInstallPkg, 0);
                            InstallOptions options = InstallOptions.makeOptions(true, false, InstallOptions.UpdateStrategy.FORCE_UPDATE);
                            installPackageImpl(outInfo.publicSourceDir, options);
                        } catch (PackageManager.NameNotFoundException e) {
                            // ignore
                        }
                    }
                }

                /*
                    这个不明白 ***
                 */
                PrivilegeAppOptimizer.get().performOptimizeAllApps();
                //适配旧版本va，或者系统升级
                if (isAppInstalled(InstallerSetting.PROVIDER_TELEPHONY_PKG)) {
                    supportTelephony(0);
                }
            } finally {
                mBooting = false;
            }
        }
    }

    private void cleanUpResidualFiles(PackageSetting ps) {
        VLog.e(TAG, "cleanup residual files for : %s", ps.packageName);
        uninstallPackageFully(ps, false);
    }


    public void onUserCreated(VUserInfo userInfo) {
        VEnvironment.getUserDataDirectory(userInfo.id).mkdirs();
    }


    synchronized boolean loadPackage(PackageSetting setting) {
        if (!loadPackageInnerLocked(setting)) {
            if (setting.appMode == InstalledAppInfo.MODE_APP_USE_OUTSIDE_APK) {
                VLog.w(TAG, "Preserving outside-apk package after load failure: %s", setting.packageName);
            } else {
                cleanUpResidualFiles(setting);
            }
            return false;
        }
        return true;
    }

    private boolean loadPackageInnerLocked(PackageSetting ps) {
        boolean modeUseOutsideApk = ps.appMode == InstalledAppInfo.MODE_APP_USE_OUTSIDE_APK;
        if (modeUseOutsideApk) {
            if (!VirtualCore.get().isOutsideInstalled(ps.packageName)) {
                return false;
            }
        }
        VPackage pkg = null;
        try {
            pkg = PackageParserEx.readPackageCache(ps.packageName);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if (pkg == null || pkg.packageName == null) {
            //重新解析apk
            String apkPath = ps.getApkPath(ps.isRunPluginProcess());
            File apkFile = TextUtils.isEmpty(apkPath)
                    ? null
                    : new File(apkPath);
            if (apkFile == null || !apkFile.exists()) {
                VLog.e(TAG, "parse failed 1:not found apk %s path=%s", ps.packageName, apkPath);
                return false;
            }
            try {
                //reparse apk (use the package directory when split APKs are present)
                pkg = PackageParserEx.parsePackage(resolvePackageParseTarget(apkFile));
                VLog.e(TAG, "reload parsePackage ok %s", ps.packageName);
            } catch (Throwable e2) {
                VLog.e(TAG, "parsePackage %s\n%s", ps.packageName, VLog.getStackTraceString(e2));
                return false;
            }
            //save pkg
            if (pkg == null || pkg.packageName == null) {
                //parse failed.
                return false;
            }
            //save cache
            PackageParserEx.savePackageCache(pkg);
        }
        PackageCacheManager.put(pkg, ps);
        if (modeUseOutsideApk) {
            try {
                PackageInfo outInfo = VirtualCore.get().getUnHookPackageManager().getPackageInfo(ps.packageName, 0);
                if (pkg.mVersionCode != outInfo.versionCode) {
                    VLog.d(TAG, "app (" + ps.packageName + ") has changed version, update it.");
                    InstallOptions options = InstallOptions.makeOptions(true, false, InstallOptions.UpdateStrategy.FORCE_UPDATE);
                    installPackageImpl(outInfo.applicationInfo.publicSourceDir, options, true);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            VEnvironment.chmodPackageDictionary(new File(ps.getApkPath(ps.isRunPluginProcess())));
        }

        BroadcastSystem.get().startApp(pkg);

        return true;
    }

    @Override
    public boolean isOutsidePackageVisible(String pkg) {
        return pkg != null && mVisibleOutsidePackages.contains(pkg);
    }

    @Override
    public int getUidForSharedUser(String sharedUserName) {
        if (sharedUserName == null) {
            return -1;
        }
        return mUidSystem.getUid(sharedUserName);
    }

    @Override
    public void addVisibleOutsidePackage(String pkg) {
        if (pkg != null) {
            mVisibleOutsidePackages.add(pkg);
        }
    }

    @Override
    public void removeVisibleOutsidePackage(String pkg) {
        if (pkg != null) {
            mVisibleOutsidePackages.remove(pkg);
        }
    }

    @Override
    public void installPackage(String path, InstallOptions options, ResultReceiver receiver) {
        VLog.i(TAG, "installPackage called from IPC, path: %s", path);
        InstallResult res;
        synchronized (this) {
            res = installPackageImpl(path, options);
        }
        sendInstallResult(receiver, res);
    }

    @Override
    public void installSplitPackage(List<String> apkPaths, InstallOptions options, ResultReceiver receiver) {
        VLog.i(TAG, "installSplitPackage called from IPC, apkCount=%d", apkPaths == null ? 0 : apkPaths.size());
        InstallResult res;
        synchronized (this) {
            res = installPackage(apkPaths, options);
        }
        sendInstallResult(receiver, res);
    }

    @Override
    public void requestCopyPackage64(String packageName) {
        /**
         * Lock VAMS avoid two process invoke this method Simultaneously.
         */
        synchronized (VActivityManagerService.get()) {
            PackageSetting ps = PackageCacheManager.getSetting(packageName);
            if (ps != null && ps.appMode == MODE_APP_USE_OUTSIDE_APK) {
                V64BitHelper.copyPackage64(ps.getApkPath(false), packageName);
            }
        }
    }

    public InstallResult installPackage(String path, InstallOptions options) {
        synchronized (this) {
            return installPackageImpl(path, options);
        }
    }

    /**
     * Install from a list of APK paths where the first element is the base APK
     * and any subsequent elements are split APKs (ABI splits, density splits, etc.).
     *
     * <p>Native libraries are extracted from split APKs and copied into
     * {@link VEnvironment#getAppLibDirectory(String)} so the
     * virtual app can load them without {@link UnsatisfiedLinkError}.
     *
     * <p>Backward-compatible: a single-element list behaves identically to
     * {@link #installPackage(String, InstallOptions)}.
     */
    public synchronized InstallResult installPackage(List<String> apkPaths, InstallOptions options) {
        if (apkPaths == null || apkPaths.isEmpty()) {
            return InstallResult.makeFailure("apkPaths is null or empty");
        }
        InstallSource installSource = null;
        try {
            installSource = createInstallSource(apkPaths);
            return installPackageImpl(installSource, options, false);
        } finally {
            cleanupInstallSource(installSource);
        }
    }

    private InstallResult installPackageImpl(String path, InstallOptions options){
        return installPackageImpl(path, options, false);
    }

    private InstallResult installPackageImpl(String path, InstallOptions options, boolean loadingApp) {
        InstallSource installSource = null;
        try {
            installSource = createInstallSource(path);
            return installPackageImpl(installSource, options, loadingApp);
        } finally {
            cleanupInstallSource(installSource);
        }
    }

    private InstallResult installPackageImpl(InstallSource installSource, InstallOptions options, boolean loadingApp) {
        long installTime = System.currentTimeMillis();
        if (installSource == null || installSource.baseApk == null) {
            return InstallResult.makeFailure("Package File is not exist.");
        }
        File packageFile = installSource.baseApk;
        if (!packageFile.exists() || !packageFile.isFile()) {
            VLog.e(TAG, "Install failed: Package File does not exist: %s", packageFile.getPath());
            return InstallResult.makeFailure("Package File is not exist.");
        }
        VPackage pkg = null;
        VLog.i(TAG, "Stage: Parsing package %s", installSource.parseTarget.getPath());
        try {
            com.lody.virtual.helper.compat.HiddenApiCompat.ensureExemptions();
            pkg = PackageParserEx.parsePackage(installSource.parseTarget);
        } catch (Throwable e) {
            VLog.e(TAG, "Install failed: Unable to parse package: %s", e.getMessage());
            e.printStackTrace();
        }
        if (pkg == null || pkg.packageName == null) {
            VLog.e(TAG, "Stage: Parsing package failed (null)");
            return InstallResult.makeFailure("Unable to parse the package.");
        }
        VLog.i(TAG, "Stage: Package parsed successfully: %s", pkg.packageName);
        if(!loadingApp) {
            BroadcastSystem.get().stopApp(pkg.packageName);
        }
        AttributeCache.instance().removePackage(pkg.packageName);
        VActivityManagerService.get().killAppByPkg(pkg.packageName, -1);
        InstallResult res = new InstallResult();
        res.packageName = pkg.packageName;
        // PackageCache holds all packages, try to check if we need to update.
        VPackage existOne = PackageCacheManager.get(pkg.packageName);
        PackageSetting existSetting = existOne != null ? (PackageSetting) existOne.mExtras : null;
        if (existOne != null) {
            if (options.updateStrategy == InstallOptions.UpdateStrategy.IGNORE_NEW_VERSION) {
                res.isUpdate = true;
                return res;
            }
            if (!isAllowedUpdate(existOne, pkg, options.updateStrategy)) {
                res.error = "Not allowed to update the package.";
                return res;
            }
            res.isUpdate = true;
            VServiceKeepAliveService.get().scheduleUpdateKeepAliveList(res.packageName, VServiceKeepAliveManager.ACTION_TEMP_DEL);
            VActivityManagerService.get().killAppByPkg(res.packageName, VUserHandle.USER_ALL);
        }
        boolean useSourceLocationApk = options.useSourceLocationApk;
        if (existOne != null) {
            PackageCacheManager.remove(pkg.packageName);
        }
        PackageSetting ps;
        if (existSetting != null) {
            ps = existSetting;
        } else {
            ps = new PackageSetting();
        }
        boolean support64bit = false, support32bit = false;
        boolean checkSupportAbi = true;
        if (!GmsSupport.GMS_PKG.equals(pkg.packageName) && GmsSupport.isGoogleAppOrService(pkg.packageName)) {
            PackageSetting gmsPs = PackageCacheManager.getSetting(GmsSupport.GMS_PKG);
            if (gmsPs != null) {
                ps.flag = gmsPs.flag;
                support32bit = isPackageSupport32Bit(ps);
                support64bit = isPackageSupport64Bit(ps);
                checkSupportAbi = false;
            }
        }
        List<File> apkFiles = installSource.getAllApks();
        List<Map<String, List<String>>> soMaps = new ArrayList<>(apkFiles.size());

        if (checkSupportAbi) {
            VLog.i(TAG, "Stage: Checking Support ABI for %s", installSource.parseTarget.getPath());
            Set<String> abiList = new HashSet<>();
            for (File apk : apkFiles) {
                Map<String, List<String>> soMap = NativeLibraryHelperCompat.getSoMapForApk(apk.getPath());
                soMaps.add(soMap);
                if (soMap != null) {
                    abiList.addAll(soMap.keySet());
                }
            }
            if (abiList.isEmpty()) {
                support32bit = true;
                support64bit = true;
            } else {
                if (NativeLibraryHelperCompat.contain64bitAbi(abiList)) {
                    support64bit = true;
                }
                if (NativeLibraryHelperCompat.contain32bitAbi(abiList)) {
                    support32bit = true;
                }
            }
            if (support32bit) {
                if (support64bit) {
                    ps.flag = PackageSetting.FLAG_RUN_BOTH_32BIT_64BIT;
                } else {
                    ps.flag = PackageSetting.FLAG_RUN_32BIT;
                }
            } else {
                ps.flag = PackageSetting.FLAG_RUN_64BIT;
            }
        } else {
            for (int i = 0; i < apkFiles.size(); i++) {
                soMaps.add(null);
            }
        }
        if ((VirtualRuntime.is64bit() && ps.flag == PackageSetting.FLAG_RUN_32BIT)
                || (!VirtualRuntime.is64bit() && ps.flag == PackageSetting.FLAG_RUN_64BIT)) {
            if (!VirtualCore.get().is64BitEngineInstalled() || !V64BitHelper.has64BitEngineStartPermission()) {
                return InstallResult.makeFailure("64bit engine not installed.");
            }
        }
        VLog.i(TAG, "Stage: Copying native binaries for %s", pkg.packageName);
        File nativeLibDir = VEnvironment.getAppLibDirectory(pkg.packageName);
        for (int i = 0; i < apkFiles.size(); i++) {
            NativeLibraryHelperCompat.copyNativeBinaries(apkFiles.get(i), nativeLibDir, soMaps.get(i));
        }

        List<File> installedSplitApks = installSource.splitApks;
        if (!useSourceLocationApk) {
            VLog.i(TAG, "Stage: Copying APK and creating symlinks for %s", pkg.packageName);
            //new apk
            File privatePackageFile = VEnvironment.getPackageResourcePathNext(pkg.packageName);
            //base.apk
            File baseApkFile = VEnvironment.getPublicResourcePath(pkg.packageName);
            try {
                //delete old odex
                FileUtils.deleteDir(VEnvironment.getOdexFile(pkg.packageName));
                deleteInstalledApkArtifacts(privatePackageFile.getParentFile());
                //copy apk -> new apk
                FileUtils.copyFile(packageFile, privatePackageFile);
                VLog.d(TAG, "copyFile:%s->%s", packageFile.getPath(), privatePackageFile.getPath());
            } catch (Exception e) {
                privatePackageFile.delete();
                return InstallResult.makeFailure("Unable to copy the package file.");
            }
            try {
                String realPath = privatePackageFile.getPath();
                String linkPath = baseApkFile.getPath();
                if (!TextUtils.equals(realPath, linkPath)) {
                    //delete base.apk
                    FileUtils.deleteDir(baseApkFile);
                    if (Build.VERSION.SDK_INT >= 29) {
                        // Hard links and symlinks are very unreliable on Android 10+ due to strict SELinux and IO rules
                        FileUtils.copyFile(privatePackageFile, baseApkFile);
                        VLog.d(TAG, "copyFile (SDK >= 29):%s->%s", realPath, linkPath);
                    } else {
                        try {
                            //new apk->base.apk
                            FileUtils.createSymlink(realPath, linkPath);
                            VLog.d(TAG, "createSymlink:%s->%s", realPath, linkPath);
                        } catch (Exception e) {
                            //copy new apk->base.apk
                            FileUtils.copyFile(privatePackageFile, baseApkFile);
                            VLog.d(TAG, "createSymlink failed, fallback to copyFile:%s->%s", realPath, linkPath);
                        }
                    }
                } else {
                    VLog.d(TAG, "use base don't need link %s", realPath);
                }
            } catch (Exception e) {
                baseApkFile.delete();
                return InstallResult.makeFailure("Unable to copy the package file.");
            }
            ArrayList<File> copiedSplitApks = new ArrayList<>(installSource.splitApks.size());
            try {
                for (File splitApk : installSource.splitApks) {
                    File installedSplitApk = new File(privatePackageFile.getParentFile(), splitApk.getName());
                    FileUtils.copyFile(splitApk, installedSplitApk);
                    VEnvironment.chmodPackageDictionary(installedSplitApk);
                    copiedSplitApks.add(installedSplitApk);
                }
            } catch (Exception e) {
                VLog.e(TAG, "Copy split APK failed for %s: %s", pkg.packageName, e.getMessage());
                return InstallResult.makeFailure("Unable to copy split package files.");
            }
            packageFile = privatePackageFile;
            VEnvironment.chmodPackageDictionary(packageFile);
            installedSplitApks = copiedSplitApks;
            rewriteInstalledApplicationInfo(pkg.applicationInfo, packageFile, installedSplitApks);
        }

        if (support64bit && !useSourceLocationApk) {
            VLog.i(TAG, "Stage: Copying 64bit package for %s", pkg.packageName);
            deleteInstalledApkArtifacts(VEnvironment.getDataAppPackageDirectory64(pkg.packageName));
            if (!V64BitHelper.copyPackage64(packageFile.getPath(), pkg.packageName)) {
                VLog.w(TAG, "Stage: Failed to copy package to 64bit engine for %s — "
                        + "app will run in 32-bit mode only.", pkg.packageName);
                // Downgrade to 32-bit only so the app can still run
                if (ps.flag == PackageSetting.FLAG_RUN_64BIT) {
                    ps.flag = PackageSetting.FLAG_RUN_32BIT;
                } else if (ps.flag == PackageSetting.FLAG_RUN_BOTH_32BIT_64BIT) {
                    ps.flag = PackageSetting.FLAG_RUN_32BIT;
                }
            } else {
                copySplitApksTo64BitDirectory(pkg.packageName, installedSplitApks, soMaps);
            }
        }

        ps.appMode = useSourceLocationApk ? MODE_APP_USE_OUTSIDE_APK : MODE_APP_COPY_APK;
        ps.packageName = pkg.packageName;
        ps.appId = VUserHandle.getAppId(mUidSystem.getOrCreateUid(pkg));
        if (res.isUpdate) {
            ps.lastUpdateTime = installTime;
        } else {
            ps.firstInstallTime = installTime;
            ps.lastUpdateTime = installTime;
            for (int userId : VUserManagerService.get().getUserIds()) {
                boolean installed = userId == 0;
                ps.setUserState(userId, false/*launched*/, false/*hidden*/, installed);
            }
        }
        if (!TextUtils.isEmpty(options.installerPackageName)) {
            ps.installerPackageName = options.installerPackageName;
        } else if (!res.isUpdate) {
            ps.installerPackageName = null;
        }
        if (!TextUtils.isEmpty(options.installSourcePackageName)) {
            ps.installSourcePackageName = options.installSourcePackageName;
        } else if (!res.isUpdate) {
            ps.installSourcePackageName = ps.installerPackageName;
        }
        VLog.i(TAG, "Stage: Saving package cache for %s", pkg.packageName);
        // Extract and persist original signatures for the signature-preservation hook
        try {
            android.content.pm.PackageInfo archiveInfo = VirtualCore.get().getUnHookPackageManager()
                    .getPackageArchiveInfo(installSource.baseApk.getPath(), PackageManager.GET_SIGNATURES);
            if (archiveInfo != null && archiveInfo.signatures != null
                    && archiveInfo.signatures.length > 0) {
                ps.originalSignatures = archiveInfo.signatures;
                VLog.d(TAG, "Captured %d original signature(s) for %s",
                        archiveInfo.signatures.length, pkg.packageName);
            }
        } catch (Throwable e) {
            VLog.w(TAG, "Failed to extract signatures for %s (non-fatal): %s",
                    pkg.packageName, e.getMessage());
        }
        PackageParserEx.savePackageCache(pkg);
        PackageCacheManager.put(pkg, ps);
        persistPackageState("installPackageImpl", ps.packageName);
        if (support32bit && !useSourceLocationApk) {
            VLog.i(TAG, "Stage: Optimizing DEX for %s", ps.packageName);
            try {
                DexOptimizer.optimizeDex(packageFile.getPath(), VEnvironment.getOdexFile(ps.packageName).getPath());
            } catch (Throwable e) {
                // DEX optimization failure is non-fatal - app will still work (just slower)
                // This can happen with certain ART hook implementations
                VLog.w(TAG, "DEX optimization failed for %s (non-fatal): %s", ps.packageName, e.getMessage());
            }
        }
        if (options.notify) {
            notifyAppInstalled(ps, -1);
            if(res.isUpdate){
                notifyAppUpdate(ps, -1);
            }
        }
        //版本差异适配
        if (InstallerSetting.PROVIDER_TELEPHONY_PKG.equals(pkg.packageName)) {
            supportTelephony(0);
        }
        if(!loadingApp) {
            try {
                BroadcastSystem.get().startApp(pkg);
            } catch (Throwable e) {
                VLog.w(TAG, "startApp failed for %s (non-fatal): %s", pkg.packageName, e.getMessage());
            }
        }
        res.isSuccess = true;
        VLog.i(TAG, "Install completed successfully: %s (update=%s)", res.packageName, res.isUpdate);
        VServiceKeepAliveService.get().scheduleUpdateKeepAliveList(res.packageName, VServiceKeepAliveManager.ACTION_TEMP_ADD);
        if(!res.isUpdate) {
            VirtualCore.getConfig().onFirstInstall(ps.packageName, false);
        }
        return res;
    }

    private void sendInstallResult(ResultReceiver receiver, InstallResult res) {
        if (res == null) {
            VLog.e(TAG, "installPackageImpl returned null!");
            res = InstallResult.makeFailure("Internal error: installPackageImpl returned null");
        }
        VLog.i(TAG, "installPackage sending result: isSuccess=%s, error=%s", res.isSuccess, res.error);
        if (receiver != null) {
            Bundle data = new Bundle();
            data.putParcelable("result", res);
            receiver.send(0, data);
        }
    }

    private InstallSource createInstallSource(String path) {
        if (path == null) {
            VLog.e(TAG, "Install failed: path = NULL");
            return null;
        }
        File packageFile = new File(path);
        if (!packageFile.exists() || !packageFile.isFile()) {
            return null;
        }
        List<File> splitApks = collectSiblingSplitApks(packageFile);
        File parseTarget = splitApks.isEmpty() ? packageFile : packageFile.getParentFile();
        return new InstallSource(parseTarget, packageFile, splitApks, null);
    }

    private InstallSource createInstallSource(List<String> apkPaths) {
        if (apkPaths == null || apkPaths.isEmpty()) {
            return null;
        }
        if (apkPaths.size() == 1) {
            return createInstallSource(apkPaths.get(0));
        }
        ArrayList<File> inputApks = new ArrayList<>(apkPaths.size());
        for (String apkPath : apkPaths) {
            if (TextUtils.isEmpty(apkPath)) {
                continue;
            }
            File apkFile = new File(apkPath);
            if (apkFile.exists() && apkFile.isFile()) {
                inputApks.add(apkFile);
            }
        }
        if (inputApks.isEmpty()) {
            return null;
        }
        File stageRoot = new File(VirtualCore.get().getContext().getCacheDir(), INSTALL_STAGE_DIR);
        if (!stageRoot.exists()) {
            stageRoot.mkdirs();
        }
        File stageDir = new File(stageRoot, String.valueOf(System.nanoTime()));
        if (!stageDir.mkdirs()) {
            return null;
        }
        try {
            File stagedBase = new File(stageDir, BASE_APK_NAME);
            FileUtils.copyFile(inputApks.get(0), stagedBase);
            ArrayList<File> stagedSplits = new ArrayList<>(Math.max(0, inputApks.size() - 1));
            Set<String> usedNames = new HashSet<>();
            usedNames.add(BASE_APK_NAME.toLowerCase());
            for (int i = 1; i < inputApks.size(); i++) {
                File sourceApk = inputApks.get(i);
                String stagedName = buildUniqueSplitName(sourceApk.getName(), i, usedNames);
                File stagedSplit = new File(stageDir, stagedName);
                FileUtils.copyFile(sourceApk, stagedSplit);
                stagedSplits.add(stagedSplit);
            }
            return new InstallSource(stageDir, stagedBase, stagedSplits, stageDir);
        } catch (Exception e) {
            VLog.e(TAG, "Failed to stage split APKs: %s", e.getMessage());
            FileUtils.deleteDir(stageDir);
            return null;
        }
    }

    private void cleanupInstallSource(InstallSource installSource) {
        if (installSource != null && installSource.cleanupDir != null) {
            FileUtils.deleteDir(installSource.cleanupDir);
        }
    }

    private File resolvePackageParseTarget(File packageFile) {
        if (packageFile == null || !packageFile.exists() || !packageFile.isFile()) {
            return packageFile;
        }
        List<File> splitApks = collectSiblingSplitApks(packageFile);
        return splitApks.isEmpty() ? packageFile : packageFile.getParentFile();
    }

    private List<File> collectSiblingSplitApks(File baseApk) {
        if (baseApk == null || !baseApk.exists() || !baseApk.isFile()) {
            return Collections.emptyList();
        }
        String baseName = baseApk.getName().toLowerCase();
        if (!baseName.startsWith("base")) {
            return Collections.emptyList();
        }
        File parent = baseApk.getParentFile();
        if (parent == null || !parent.isDirectory()) {
            return Collections.emptyList();
        }
        File[] siblings = parent.listFiles();
        if (siblings == null || siblings.length == 0) {
            return Collections.emptyList();
        }
        ArrayList<File> splitApks = new ArrayList<>();
        for (File sibling : siblings) {
            if (sibling == null || !sibling.isFile()) {
                continue;
            }
            String siblingName = sibling.getName().toLowerCase();
            if (!siblingName.endsWith(".apk") || sibling.equals(baseApk)) {
                continue;
            }
            if (BASE_APK_NAME.equals(siblingName) || siblingName.startsWith("base-")) {
                continue;
            }
            splitApks.add(sibling);
        }
        return splitApks;
    }

    private String buildUniqueSplitName(String originalName, int index, Set<String> usedNames) {
        String safeName = TextUtils.isEmpty(originalName) ? "split_" + index + ".apk" : originalName;
        safeName = safeName.replace(File.separatorChar, '_');
        if (!safeName.toLowerCase().endsWith(".apk")) {
            safeName = safeName + ".apk";
        }
        String candidate = safeName;
        int suffix = 1;
        while (usedNames.contains(candidate.toLowerCase())) {
            String prefix = safeName.substring(0, safeName.length() - 4);
            candidate = prefix + "_" + suffix + ".apk";
            suffix++;
        }
        usedNames.add(candidate.toLowerCase());
        return candidate;
    }

    private void deleteInstalledApkArtifacts(File packageDir) {
        if (packageDir == null || !packageDir.exists() || !packageDir.isDirectory()) {
            return;
        }
        File[] children = packageDir.listFiles();
        if (children == null) {
            return;
        }
        for (File child : children) {
            if (child != null && child.isFile() && child.getName().toLowerCase().endsWith(".apk")) {
                FileUtils.deleteDir(child);
            }
        }
    }

    private void rewriteInstalledApplicationInfo(ApplicationInfo applicationInfo, File baseApk, List<File> splitApks) {
        if (applicationInfo == null || baseApk == null) {
            return;
        }
        applicationInfo.sourceDir = baseApk.getPath();
        applicationInfo.publicSourceDir = baseApk.getPath();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (splitApks == null || splitApks.isEmpty()) {
                applicationInfo.splitSourceDirs = null;
                applicationInfo.splitPublicSourceDirs = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    applicationInfo.splitNames = null;
                }
                return;
            }
            String[] splitPaths = new String[splitApks.size()];
            for (int i = 0; i < splitApks.size(); i++) {
                splitPaths[i] = splitApks.get(i).getPath();
            }
            applicationInfo.splitSourceDirs = splitPaths;
            applicationInfo.splitPublicSourceDirs = splitPaths.clone();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                    && (applicationInfo.splitNames == null || applicationInfo.splitNames.length != splitPaths.length)) {
                applicationInfo.splitNames = buildSplitNames(splitApks);
            }
        }
    }

    private String[] buildSplitNames(List<File> splitApks) {
        String[] splitNames = new String[splitApks.size()];
        for (int i = 0; i < splitApks.size(); i++) {
            String fileName = splitApks.get(i).getName();
            splitNames[i] = fileName.endsWith(".apk") ? fileName.substring(0, fileName.length() - 4) : fileName;
        }
        return splitNames;
    }

    private void copySplitApksTo64BitDirectory(String packageName, List<File> splitApks, List<Map<String, List<String>>> soMaps) {
        if (splitApks == null || splitApks.isEmpty()) {
            return;
        }
        File targetDir = VEnvironment.getDataAppPackageDirectory64(packageName);
        File libDir = VEnvironment.getAppLibDirectory64(packageName);
        for (int i = 0; i < splitApks.size(); i++) {
            File splitApk = splitApks.get(i);
            File targetFile = new File(targetDir, splitApk.getName());
            try {
                FileUtils.copyFile(splitApk, targetFile);
                VEnvironment.chmodPackageDictionary(targetFile);
                if (soMaps != null && soMaps.size() > i + 1) {
                    NativeLibraryHelperCompat.copyNativeBinaries(splitApk, libDir, soMaps.get(i + 1));
                }
            } catch (Exception e) {
                VLog.w(TAG, "Failed to mirror split %s into 64bit package directory: %s",
                        splitApk.getName(), e.getMessage());
            }
        }
    }

    private void supportTelephony(int userId) {
        if (Build.VERSION.SDK_INT >= 29) {
            setDefaultComponent(new ComponentName(InstallerSetting.PROVIDER_TELEPHONY_PKG, "com.android.providers.telephony.RcsProvider"), userId);
        } else {
            disableComponent(new ComponentName(InstallerSetting.PROVIDER_TELEPHONY_PKG, "com.android.providers.telephony.RcsProvider"), userId);
        }
        if (Build.VERSION.SDK_INT < 28) {
            disableComponent(new ComponentName(InstallerSetting.PROVIDER_TELEPHONY_PKG, "com.android.providers.telephony.CarrierIdProvider"), userId);
            disableComponent(new ComponentName(InstallerSetting.PROVIDER_TELEPHONY_PKG, "com.android.providers.telephony.CarrierProvider"), userId);
        } else {
            setDefaultComponent(new ComponentName(InstallerSetting.PROVIDER_TELEPHONY_PKG, "com.android.providers.telephony.CarrierIdProvider"), userId);
            setDefaultComponent(new ComponentName(InstallerSetting.PROVIDER_TELEPHONY_PKG, "com.android.providers.telephony.CarrierProvider"), userId);
        }
        if (Build.VERSION.SDK_INT < 26) {
            disableComponent(new ComponentName(InstallerSetting.PROVIDER_TELEPHONY_PKG, "com.android.providers.telephony.ServiceStateProvider"), userId);
        } else {
            setDefaultComponent(new ComponentName(InstallerSetting.PROVIDER_TELEPHONY_PKG, "com.android.providers.telephony.ServiceStateProvider"), userId);
        }
    }

    private void disableComponent(ComponentName componentName, int userId){
        VPackageManagerService.get().setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP, userId);
    }

    private void setDefaultComponent(ComponentName componentName, int userId){
        VPackageManagerService.get().setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT, PackageManager.DONT_KILL_APP, userId);
    }

    @Override
    public synchronized boolean installPackageAsUser(int userId, String packageName) {
        if (VUserManagerService.get().exists(userId)) {
            PackageSetting ps = PackageCacheManager.getSetting(packageName);
            if (ps != null) {
                if (!ps.isInstalled(userId)) {
                    ps.setInstalled(userId, true);
                    mkdirsForUser(packageName, userId);
                    notifyAppInstalled(ps, userId);
                    persistPackageState("installPackageAsUser", packageName);
                    //版本差异适配
                    if (InstallerSetting.PROVIDER_TELEPHONY_PKG.equals(packageName)) {
                        supportTelephony(userId);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAllowedUpdate(VPackage existOne, VPackage newOne, InstallOptions.UpdateStrategy strategy) {
        switch (strategy) {
            case FORCE_UPDATE:
                return true;
            case COMPARE_VERSION:
                return existOne.mVersionCode <= newOne.mVersionCode;
            case TERMINATE_IF_EXIST:
                return false;
        }
        return true;
    }


    @Override
    public synchronized boolean uninstallPackage(String packageName) {
        PackageSetting ps = PackageCacheManager.getSetting(packageName);
        if (ps != null) {
            uninstallPackageFully(ps, true);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean uninstallPackageAsUser(String packageName, int userId) {
        if (!VUserManagerService.get().exists(userId)) {
            return false;
        }
        PackageSetting ps = PackageCacheManager.getSetting(packageName);
        if (ps != null) {
            int[] userIds = getPackageInstalledUsers(packageName);
            if (!ArrayUtils.contains(userIds, userId)) {
                return false;
            }
            if (userIds.length == 1) {
                uninstallPackageFully(ps, true);
            } else {
                VServiceKeepAliveService.get().scheduleUpdateKeepAliveList(packageName, VServiceKeepAliveManager.ACTION_DEL);
                // Just hidden it
                VNotificationManagerService.get().cancelAllNotification(packageName, userId);
                VJobSchedulerService.get().cancelAll(ps.appId, userId);
                VActivityManagerService.get().killAppByPkg(packageName, userId);
                ps.setInstalled(userId, false);
                persistPackageState("uninstallPackageAsUser", packageName);
                deletePackageDataAsUser(userId, ps, false);
                notifyAppUninstalled(ps, userId);
            }
            return true;
        }
        return false;
    }

    private boolean isPackageSupport32Bit(PackageSetting ps) {
        return ps.flag == PackageSetting.FLAG_RUN_32BIT
                || ps.flag == PackageSetting.FLAG_RUN_BOTH_32BIT_64BIT;
    }

    private boolean isPackageSupport64Bit(PackageSetting ps) {
        return ps.flag == PackageSetting.FLAG_RUN_64BIT
                || ps.flag == PackageSetting.FLAG_RUN_BOTH_32BIT_64BIT;
    }

    private void deletePackageDataAsUser(int userId, PackageSetting ps, boolean linkLib) {
        if (isPackageSupport32Bit(ps)) {
            String libPath = VEnvironment.getAppLibDirectory(ps.packageName).getAbsolutePath();
            if (userId == -1) {
                List<VUserInfo> userInfos = VUserManager.get().getUsers();
                if (userInfos != null) {
                    for (VUserInfo info : userInfos) {
                        FileUtils.deleteDir(VEnvironment.getDataUserPackageDirectory(info.id, ps.packageName));
                        if(linkLib) {
                            File userLibDir = VEnvironment.getUserAppLibDirectory(userId, ps.packageName);
                            if (!userLibDir.exists()) {
                                try {
                                    FileUtils.createSymlink(libPath, userLibDir.getPath());
                                    VLog.d(TAG, "createSymlink %s@%d's lib", ps.packageName, userId);
                                } catch (Exception e) {
                                    //ignore
                                }
                            }
                        }
                        // add by lml@xdja.com
                        {
                            FileUtils.deleteDir(VEnvironment.getExternalStorageAppDataDir(info.id, ps.packageName));
                        }
                    }
                }
            } else {
                FileUtils.deleteDir(VEnvironment.getDataUserPackageDirectory(userId, ps.packageName));
                if(linkLib) {
                    File userLibDir = VEnvironment.getUserAppLibDirectory(userId, ps.packageName);
                    if (!userLibDir.exists()) {
                        try {
                            FileUtils.createSymlink(libPath, userLibDir.getPath());
                            VLog.d(TAG, "createSymlink %s@%d's lib", ps.packageName, userId);
                        } catch (Exception e) {
                            //ignore
                        }
                    }
                }
                // add by lml@xdja.com
                {
                    FileUtils.deleteDir(VEnvironment.getExternalStorageAppDataDir(userId, ps.packageName));
                }
            }
        }
        if (isPackageSupport64Bit(ps)) {
            V64BitHelper.cleanPackageData64(userId, ps.packageName);
        }
        VNotificationManagerService.get().cancelAllNotification(ps.packageName, userId);
        if(userId == 0 || userId == -1) {
            VirtualCore.getConfig().onFirstInstall(ps.packageName, true);
        }
    }

    public boolean cleanPackageData(String pkg, int userId) {
        PackageSetting ps = PackageCacheManager.getSetting(pkg);
        if (ps == null) {
            return false;
        }
        VActivityManagerService.get().killAppByPkg(pkg, userId);
        deletePackageDataAsUser(userId, ps, true);
        return true;
    }

    private void uninstallPackageFully(PackageSetting ps, boolean notify) {
        String packageName = ps.packageName;
        try {
            AttributeCache.instance().removePackage(packageName);
            BroadcastSystem.get().stopApp(packageName);
            VServiceKeepAliveService.get().scheduleUpdateKeepAliveList(packageName, VServiceKeepAliveManager.ACTION_DEL);
            VJobSchedulerService.get().cancelAll(ps.appId, VUserHandle.USER_ALL);
            VNotificationManagerService.get().cancelAllNotification(packageName, VUserHandle.USER_ALL);
            VActivityManagerService.get().killAppByPkg(packageName, VUserHandle.USER_ALL);
            if (isPackageSupport32Bit(ps)) {
                FileUtils.deleteDir(VEnvironment.getPackageResourcePath(packageName));
                FileUtils.deleteDir(VEnvironment.getPublicResourcePath(packageName));
                FileUtils.deleteDir(VEnvironment.getDataAppPackageDirectory(packageName));
                VEnvironment.getOdexFile(packageName).delete();
                for (int id : VUserManagerService.get().getUserIds()) {
                    deletePackageDataAsUser(id, ps, false);
                }
            }
            if (isPackageSupport64Bit(ps)) {
                V64BitHelper.uninstallPackage64(-1, packageName);
            }
            PackageCacheManager.remove(packageName);
            mPersistenceLayer.save();
            File cacheFile = VEnvironment.getPackageCacheFile(packageName);
            cacheFile.delete();
            File signatureFile = VEnvironment.getSignatureFile(packageName);
            signatureFile.delete();
            FileUtils.deleteDir(VEnvironment.getDataAppPackageDirectory(packageName));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (notify) {
                notifyAppUninstalled(ps, -1);
            }
        }
    }

    @Override
    public int[] getPackageInstalledUsers(String packageName) {
        PackageSetting ps = PackageCacheManager.getSetting(packageName);
        if (ps != null) {
            IntArray installedUsers = new IntArray(5);
            int[] userIds = VUserManagerService.get().getUserIds();
            for (int userId : userIds) {
                if (ps.readUserState(userId).installed) {
                    installedUsers.add(userId);
                }
            }
            return installedUsers.getAll();
        }
        return new int[0];
    }

    @Override
    public List<InstalledAppInfo> getInstalledApps(int flags) {
        List<InstalledAppInfo> infoList = new ArrayList<>(getInstalledAppCount());
        for (VPackage p : PackageCacheManager.PACKAGE_CACHE.values()) {
            PackageSetting setting = (PackageSetting) p.mExtras;
            infoList.add(setting.getAppInfo());
        }
        return infoList;
    }

    @Override
    public List<InstalledAppInfo> getInstalledAppsAsUser(int userId, int flags) {
        List<InstalledAppInfo> infoList = new ArrayList<>(getInstalledAppCount());
        for (VPackage p : PackageCacheManager.PACKAGE_CACHE.values()) {
            PackageSetting setting = (PackageSetting) p.mExtras;
            boolean visible = setting.isInstalled(userId);
            if ((flags & VirtualCore.GET_HIDDEN_APP) == 0 && setting.isHidden(userId)) {
                visible = false;
            }
            if (visible) {
                infoList.add(setting.getAppInfo());
            }
        }
        return infoList;
    }

    @Override
    public int getInstalledAppCount() {
        return PackageCacheManager.PACKAGE_CACHE.size();
    }

    @Override
    public boolean isAppInstalled(String packageName) {
        return packageName != null && PackageCacheManager.PACKAGE_CACHE.containsKey(packageName);
    }

    @Override
    public boolean isAppInstalledAsUser(int userId, String packageName) {
        if (packageName == null || !VUserManagerService.get().exists(userId)) {
            return false;
        }
        PackageSetting setting = PackageCacheManager.getSetting(packageName);
        if (setting == null) {
            return false;
        }
        return setting.isInstalled(userId);
    }

    private void notifyAppInstalled(PackageSetting setting, int userId) {
        final String pkg = setting.packageName;
        int N = mRemoteCallbackList.beginBroadcast();
        while (N-- > 0) {
            try {
                if (userId == -1) {
                    mRemoteCallbackList.getBroadcastItem(N).onPackageInstalled(pkg);
                    mRemoteCallbackList.getBroadcastItem(N).onPackageInstalledAsUser(0, pkg);

                } else {
                    mRemoteCallbackList.getBroadcastItem(N).onPackageInstalledAsUser(userId, pkg);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if(userId == -1){
            userId = VUserHandle.USER_OWNER;
        }
        sendInstalledBroadcast(pkg, new VUserHandle(userId));
        mRemoteCallbackList.finishBroadcast();
        VAccountManagerService.get().refreshAuthenticatorCache(null);
    }

    private void notifyAppUpdate(PackageSetting setting, int userId) {
        final String pkg = setting.packageName;
        int N = mRemoteCallbackList.beginBroadcast();
        while (N-- > 0) {
            try {
                if (userId == -1) {
                    mRemoteCallbackList.getBroadcastItem(N).onPackageUpdate(pkg);
                    mRemoteCallbackList.getBroadcastItem(N).onPackageUpdateAsUser(0, pkg);

                } else {
                    mRemoteCallbackList.getBroadcastItem(N).onPackageUpdateAsUser(userId, pkg);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if(userId == -1){
            userId = VUserHandle.USER_OWNER;
        }
        sendUpdateBroadcast(pkg, new VUserHandle(userId));
        mRemoteCallbackList.finishBroadcast();
        VAccountManagerService.get().refreshAuthenticatorCache(null);
    }

    private void notifyAppUninstalled(PackageSetting setting, int userId) {
        final String pkg = setting.packageName;
        int N = mRemoteCallbackList.beginBroadcast();
        while (N-- > 0) {
            try {
                if (userId == -1) {
                    mRemoteCallbackList.getBroadcastItem(N).onPackageUninstalled(pkg);
                    mRemoteCallbackList.getBroadcastItem(N).onPackageUninstalledAsUser(0, pkg);
                } else {
                    mRemoteCallbackList.getBroadcastItem(N).onPackageUninstalledAsUser(userId, pkg);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if(userId == -1){
            userId = VUserHandle.USER_OWNER;
        }
        sendUninstalledBroadcast(pkg, new VUserHandle(userId));
        mRemoteCallbackList.finishBroadcast();
        VAccountManagerService.get().refreshAuthenticatorCache(null);
    }


    public void sendInstalledBroadcast(String packageName, VUserHandle user) {
        Intent intent = new Intent(Intent.ACTION_PACKAGE_ADDED);
        intent.setData(Uri.parse("package:" + packageName));
        VActivityManagerService.get().sendBroadcastAsUser(intent, user);
    }

    public void sendUninstalledBroadcast(String packageName, VUserHandle user) {
        Intent intent = new Intent(Intent.ACTION_PACKAGE_REMOVED);
        intent.setData(Uri.parse("package:" + packageName));
        VActivityManagerService.get().sendBroadcastAsUser(intent, user);
    }

    public void sendUpdateBroadcast(String packageName, VUserHandle user) {
        Intent intent = new Intent(Intent.ACTION_PACKAGE_REPLACED);
        intent.setData(Uri.parse("package:" + packageName));
        VActivityManagerService.get().sendBroadcastAsUser(intent, user);
    }

    @Override
    public void registerObserver(IPackageObserver observer) {
        try {
            mRemoteCallbackList.register(observer);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unregisterObserver(IPackageObserver observer) {
        try {
            mRemoteCallbackList.unregister(observer);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public InstalledAppInfo getInstalledAppInfo(String packageName, int flags) {
        synchronized (PackageCacheManager.class) {
            if (packageName != null) {
                PackageSetting setting = PackageCacheManager.getSetting(packageName);
                if (setting != null) {
                    return setting.getAppInfo();
                }
            }
            return null;
        }
    }

    @Override
    public boolean isRun64BitProcess(String packageName) {
        PackageSetting ps = PackageCacheManager.getSetting(packageName);
        return ps != null && ps.isRunPluginProcess();
    }

    @Override
    public synchronized boolean isIORelocateWork() {
        return true;
    }

    public boolean isPackageLaunched(int userId, String packageName) {
        PackageSetting ps = PackageCacheManager.getSetting(packageName);
        return ps != null && ps.isLaunched(userId);
    }

    public void setPackageHidden(int userId, String packageName, boolean hidden) {
        PackageSetting ps = PackageCacheManager.getSetting(packageName);
        if (ps != null && VUserManagerService.get().exists(userId)) {
            ps.setHidden(userId, hidden);
            mPersistenceLayer.save();
        }
    }

    public int getAppId(String packageName) {
        PackageSetting setting = PackageCacheManager.getSetting(packageName);
        return setting != null ? setting.appId : -1;
    }

    void restoreFactoryState() {
        VLog.w(TAG, "Warning: Restore the factory state...");
        VEnvironment.getDalvikCacheDirectory().delete();
        VEnvironment.getUserSystemDirectory().delete();
        VEnvironment.getUserDeSystemDirectory().delete();
        VEnvironment.getDataAppDirectory().delete();
    }

    public void savePersistenceData() {
        mPersistenceLayer.save();
    }

    public boolean is64BitUid(int uid) throws PackageManager.NameNotFoundException {
        int appId = VUserHandle.getAppId(uid);
        synchronized (PackageCacheManager.PACKAGE_CACHE) {
            for (VPackage p : PackageCacheManager.PACKAGE_CACHE.values()) {
                PackageSetting ps = (PackageSetting) p.mExtras;
                if (ps.appId == appId) {
                    return ps.isRunPluginProcess();
                }
            }
        }
        throw new PackageManager.NameNotFoundException();
    }

    private void mkdirsForUser(String packageName, int userId){
        File dir = VEnvironment.getDataUserPackageDirectory(userId, packageName);
        File files = new File(dir, "files");
        File cache = new File(dir, "cache");
        if(!files.exists()){
            files.mkdirs();
        }
        if(!cache.exists()){
            cache.mkdirs();
        }
    }
}
