package com.lody.virtual.server.pm.parser;

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;

import com.lody.virtual.GmsSupport;
import com.lody.virtual.client.core.SettingConfig;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.client.fixer.ComponentFixer;
import com.lody.virtual.helper.collection.ArrayMap;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.compat.NativeLibraryHelperCompat;
import com.lody.virtual.helper.compat.PackageParserCompat;
import com.lody.virtual.helper.utils.FileUtils;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.os.VEnvironment;
import com.lody.virtual.remote.InstalledAppInfo;
import com.lody.virtual.server.pm.PackageCacheManager;
import com.lody.virtual.server.pm.PackageSetting;
import com.lody.virtual.server.pm.PackageUserState;
import com.xdja.zs.InstallerSettingManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import mirror.android.content.pm.ApplicationInfoL;
import mirror.android.content.pm.ApplicationInfoN;

/**
 * @author Lody
 */

public class PackageParserEx {

    private static final String TAG = PackageParserEx.class.getSimpleName();
    private static final int PACKAGE_CACHE_VERSION = 6;

    private static final ArrayMap<String, String[]> sSharedLibCache = new ArrayMap<>();

    public static VPackage parsePackage(File packageFile) throws Throwable {
        com.lody.virtual.helper.compat.HiddenApiCompat.ensureExemptions();
        File fallbackTarget = resolvePackageArchiveFallbackTarget(packageFile);
        if (Build.VERSION.SDK_INT >= 34 && fallbackTarget != null) {
            try {
                PackageInfo pi = com.lody.virtual.helper.compat.PackageParserAndroid14Compat.parsePackageInfoFallback(fallbackTarget, VirtualCore.get().getContext().getPackageManager());
                if (pi != null && pi.packageName != null) {
                    VPackage pkg = buildPackageFromPackageInfo(pi, packageFile, fallbackTarget);
                    if (pkg != null) {
                        return pkg;
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        PackageParser parser;
        PackageParser.Package p;
        try {
            parser = PackageParserCompat.createParser(packageFile);
            if (BuildCompat.isQ()) {
                parser.setCallback(new PackageParser.CallbackImpl(VirtualCore.getPM()));
            }
            p = PackageParserCompat.parsePackage(parser, packageFile, 0);
        } catch (Throwable e) {
            VLog.w(TAG, "PackageParserCompat failed: " + e.getMessage() + ", trying fallback...");
            // Android 14+ may block PackageParser entirely; use fallback.
            if (Build.VERSION.SDK_INT >= 28 && fallbackTarget != null) {
                android.content.pm.PackageInfo pi =
                        com.lody.virtual.helper.compat.PackageParserAndroid14Compat
                                .parsePackageInfoFallback(fallbackTarget, VirtualCore.get().getUnHookPackageManager());
                if (pi == null) {
                    throw new RuntimeException("PackageParser fallback failed for: " + packageFile.getAbsolutePath(), e);
                }
                VLog.i(TAG, "Using PackageInfo fallback for: " + pi.packageName);
                // Build VPackage from PackageInfo
                return buildPackageFromPackageInfo(pi, packageFile, fallbackTarget);
            }
            throw e;
        }
        if (p.requestedPermissions.contains("android.permission.FAKE_PACKAGE_SIGNATURE")
                && p.mAppMetaData != null
                && p.mAppMetaData.containsKey("fake-signature")) {
            String sig = p.mAppMetaData.getString("fake-signature");
            buildSignature(p, new Signature[]{new Signature(sig)});
            VLog.d(TAG, "Using fake-signature feature on : " + p.packageName);
        } else {
            try {
                int flag = 0;
                if (BuildCompat.isPie()) {
                    flag |= PackageParser.PARSE_IS_SYSTEM_DIR;
                } else {
                    flag |= PackageParser.PARSE_IS_SYSTEM;
                }
                PackageParserCompat.collectCertificates(parser, p, flag);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return buildPackageCache(p);
    }

    private static boolean isSplitClusterTarget(File packageFile) {
        return packageFile != null && packageFile.isDirectory();
    }

    private static File resolvePackageArchiveFallbackTarget(File packageFile) {
        if (packageFile == null) {
            return null;
        }
        if (packageFile.isFile()) {
            return packageFile;
        }
        if (!packageFile.isDirectory()) {
            return null;
        }
        File baseApk = new File(packageFile, "base.apk");
        return baseApk.isFile() ? baseApk : null;
    }

    public static boolean hasComponentMetadata(VPackage pkg) {
        return pkg != null
                && ((pkg.activities != null && !pkg.activities.isEmpty())
                || (pkg.services != null && !pkg.services.isEmpty())
                || (pkg.receivers != null && !pkg.receivers.isEmpty())
                || (pkg.providers != null && !pkg.providers.isEmpty()));
    }

    public static Signature[] extractSignatures(PackageInfo pi) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && pi.signingInfo != null) {
            Signature[] signers = pi.signingInfo.getApkContentsSigners();
            if (signers != null && signers.length > 0) {
                return signers;
            }
        }
        return pi.signatures;
    }

    /**
     * Build VPackage from PackageInfo (fallback for Android 14+ where PackageParser is blocked)
     */
    private static VPackage buildPackageFromPackageInfo(PackageInfo pi, File packageFile, File archiveSource) {
        pi = enrichPackageInfoForFallback(pi);
        VPackage pkg = new VPackage();
        pkg.packageName = pi.packageName;
        pkg.mVersionCode = (int) pi.getLongVersionCode(); // cast to int
        pkg.mVersionName = pi.versionName;
        pkg.mSharedUserId = pi.sharedUserId;
        pkg.mSharedUserLabel = pi.sharedUserLabel;
        
        // Initialize lists
        pkg.activities = new ArrayList<>();
        pkg.services = new ArrayList<>();
        pkg.receivers = new ArrayList<>();
        pkg.providers = new ArrayList<>();
        pkg.instrumentation = new ArrayList<>();
        pkg.permissions = new ArrayList<>();
        pkg.permissionGroups = new ArrayList<>();
        pkg.requestedPermissions = new ArrayList<>();
        
        // ApplicationInfo
        if (pi.applicationInfo != null) {
            pkg.applicationInfo = pi.applicationInfo;
            pkg.mAppMetaData = pi.applicationInfo.metaData != null
                    ? new android.os.Bundle(pi.applicationInfo.metaData)
                    : null;
            // Fix paths
            String archivePath = archiveSource != null ? archiveSource.getAbsolutePath() : packageFile.getAbsolutePath();
            pkg.applicationInfo.sourceDir = archivePath;
            pkg.applicationInfo.publicSourceDir = archivePath;
            if (pkg.applicationInfo.nativeLibraryDir == null) {
                pkg.applicationInfo.nativeLibraryDir = new File(packageFile.getParentFile(), "lib").getAbsolutePath();
            }
            applySplitMetadataForCluster(pkg.applicationInfo, packageFile, archiveSource);
        }
        
        // Requested permissions
        if (pi.requestedPermissions != null) {
            for (String perm : pi.requestedPermissions) {
                pkg.requestedPermissions.add(perm);
            }
        }
        
        // Activities - create minimal ActivityComponent using reflection
        if (pi.activities != null) {
            for (ActivityInfo ai : pi.activities) {
                try {
                    VPackage.ActivityComponent activity = createActivityComponent(ai);
                    if (activity != null) {
                        pkg.activities.add(activity);
                    }
                } catch (Exception e) {
                    VLog.w(TAG, "Failed to create ActivityComponent for " + ai.name + ": " + e.getMessage());
                }
            }
        }
        
        // Services - create minimal ServiceComponent using reflection
        if (pi.services != null) {
            for (ServiceInfo si : pi.services) {
                try {
                    VPackage.ServiceComponent service = createServiceComponent(si);
                    if (service != null) {
                        pkg.services.add(service);
                    }
                } catch (Exception e) {
                    VLog.w(TAG, "Failed to create ServiceComponent for " + si.name + ": " + e.getMessage());
                }
            }
        }
        
        // Providers - create minimal ProviderComponent using reflection
        if (pi.providers != null) {
            for (ProviderInfo pri : pi.providers) {
                try {
                    VPackage.ProviderComponent provider = createProviderComponent(pri);
                    if (provider != null) {
                        pkg.providers.add(provider);
                    }
                } catch (Exception e) {
                    VLog.w(TAG, "Failed to create ProviderComponent for " + pri.name + ": " + e.getMessage());
                }
            }
        }
        
        // Signatures
        pkg.mSignatures = extractSignatures(pi);
        
        VLog.i(TAG, "Built VPackage from PackageInfo: " + pkg.packageName + 
                " (activities=" + pkg.activities.size() + 
                ", services=" + pkg.services.size() + 
                ", providers=" + pkg.providers.size() + ")");

        addOwner(pkg);
        return pkg;
    }

    private static void applySplitMetadataForCluster(ApplicationInfo applicationInfo, File packageFile, File archiveSource) {
        if (applicationInfo == null || packageFile == null || !packageFile.isDirectory()) {
            return;
        }
        List<File> splitApks = new ArrayList<>();
        File[] files = packageFile.listFiles();
        if (files == null) {
            return;
        }
        String baseName = archiveSource != null ? archiveSource.getName() : "base.apk";
        for (File file : files) {
            if (file == null || !file.isFile() || !file.getName().endsWith(".apk")) {
                continue;
            }
            if (file.getName().equals(baseName)) {
                continue;
            }
            splitApks.add(file);
        }
        if (splitApks.isEmpty()) {
            applicationInfo.splitNames = null;
            applicationInfo.splitSourceDirs = null;
            applicationInfo.splitPublicSourceDirs = null;
            return;
        }
        Collections.sort(splitApks, (left, right) -> left.getName().compareTo(right.getName()));
        String[] splitPaths = new String[splitApks.size()];
        String[] splitNames = new String[splitApks.size()];
        for (int i = 0; i < splitApks.size(); i++) {
            File splitApk = splitApks.get(i);
            splitPaths[i] = splitApk.getAbsolutePath();
            String fileName = splitApk.getName();
            splitNames[i] = fileName.endsWith(".apk") ? fileName.substring(0, fileName.length() - 4) : fileName;
        }
        applicationInfo.splitNames = splitNames;
        applicationInfo.splitSourceDirs = splitPaths;
        applicationInfo.splitPublicSourceDirs = splitPaths;
    }

    private static PackageInfo enrichPackageInfoForFallback(PackageInfo archiveInfo) {
        if (archiveInfo == null || TextUtils.isEmpty(archiveInfo.packageName)) {
            return archiveInfo;
        }
        boolean missingActivities = archiveInfo.activities == null || archiveInfo.activities.length == 0;
        boolean missingServices = archiveInfo.services == null || archiveInfo.services.length == 0;
        boolean missingReceivers = archiveInfo.receivers == null || archiveInfo.receivers.length == 0;
        boolean missingProviders = archiveInfo.providers == null || archiveInfo.providers.length == 0;
        if (!missingActivities && !missingServices && !missingReceivers && !missingProviders) {
            return archiveInfo;
        }

        try {
            int flags = PackageManager.GET_ACTIVITIES
                    | PackageManager.GET_SERVICES
                    | PackageManager.GET_RECEIVERS
                    | PackageManager.GET_PROVIDERS
                    | PackageManager.GET_META_DATA;
            PackageInfo installedInfo = VirtualCore.get().getUnHookPackageManager()
                    .getPackageInfo(archiveInfo.packageName, flags);
            if (installedInfo == null) {
                return archiveInfo;
            }

            if (archiveInfo.applicationInfo != null && installedInfo.applicationInfo != null
                    && archiveInfo.applicationInfo.metaData == null && installedInfo.applicationInfo.metaData != null) {
                archiveInfo.applicationInfo.metaData = new android.os.Bundle(installedInfo.applicationInfo.metaData);
                VLog.w(TAG, "Fallback parser borrowed application metadata for %s",
                        archiveInfo.packageName);
            }

            if (missingActivities && installedInfo.activities != null && installedInfo.activities.length > 0) {
                archiveInfo.activities = installedInfo.activities;
                VLog.w(TAG, "Fallback parser borrowed %d host activities for %s",
                        installedInfo.activities.length, archiveInfo.packageName);
            }
            if (missingServices && installedInfo.services != null && installedInfo.services.length > 0) {
                archiveInfo.services = installedInfo.services;
                VLog.w(TAG, "Fallback parser borrowed %d host services for %s",
                        installedInfo.services.length, archiveInfo.packageName);
            }
            if (missingReceivers && installedInfo.receivers != null && installedInfo.receivers.length > 0) {
                archiveInfo.receivers = installedInfo.receivers;
                VLog.w(TAG, "Fallback parser borrowed %d host receivers for %s",
                        installedInfo.receivers.length, archiveInfo.packageName);
            }
            if (missingProviders && installedInfo.providers != null && installedInfo.providers.length > 0) {
                archiveInfo.providers = installedInfo.providers;
                VLog.w(TAG, "Fallback parser borrowed %d host providers for %s",
                        installedInfo.providers.length, archiveInfo.packageName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            VLog.w(TAG, "Fallback parser could not enrich host package info for %s", archiveInfo.packageName);
        }
        return archiveInfo;
    }

    // Helper methods to create Component instances using reflection (for Android 14+ fallback)
    private static VPackage.ActivityComponent createActivityComponent(ActivityInfo ai) {
        try {
            // Use reflection to access protected constructor
            java.lang.reflect.Constructor<VPackage.ActivityComponent> ctor = 
                VPackage.ActivityComponent.class.getDeclaredConstructor();
            ctor.setAccessible(true);
            VPackage.ActivityComponent activity = ctor.newInstance();
            activity.info = ai;
            activity.className = ai.name;
            activity.metaData = ai.metaData;
            activity.intents = new ArrayList<>();
            return activity;
        } catch (Exception e) {
            VLog.w(TAG, "Reflection failed for ActivityComponent: " + e.getMessage());
            return null;
        }
    }

    private static VPackage.ServiceComponent createServiceComponent(ServiceInfo si) {
        try {
            java.lang.reflect.Constructor<VPackage.ServiceComponent> ctor = 
                VPackage.ServiceComponent.class.getDeclaredConstructor();
            ctor.setAccessible(true);
            VPackage.ServiceComponent service = ctor.newInstance();
            service.info = si;
            service.className = si.name;
            service.metaData = si.metaData;
            service.intents = new ArrayList<>();
            return service;
        } catch (Exception e) {
            VLog.w(TAG, "Reflection failed for ServiceComponent: " + e.getMessage());
            return null;
        }
    }

    private static VPackage.ProviderComponent createProviderComponent(ProviderInfo pi) {
        try {
            java.lang.reflect.Constructor<VPackage.ProviderComponent> ctor = 
                VPackage.ProviderComponent.class.getDeclaredConstructor();
            ctor.setAccessible(true);
            VPackage.ProviderComponent provider = ctor.newInstance();
            provider.info = pi;
            provider.className = pi.name;
            provider.metaData = pi.metaData;
            provider.intents = new ArrayList<>();
            return provider;
        } catch (Exception e) {
            VLog.w(TAG, "Reflection failed for ProviderComponent: " + e.getMessage());
            return null;
        }
    }

    private static void buildSignature(PackageParser.Package p, Signature[] signatures) {
        if (BuildCompat.isQ()) {
            Object signingDetails = mirror.android.content.pm.PackageParser.Package.mSigningDetails.get(p);
            mirror.android.content.pm.PackageParser.SigningDetails.pastSigningCertificates.set(signingDetails, signatures);
            mirror.android.content.pm.PackageParser.SigningDetails.signatures.set(signingDetails, signatures);
        } else {
            p.mSignatures = signatures;
        }
    }

    public static VPackage readPackageCache(String packageName) {
        Parcel p = Parcel.obtain();
        try {
            File cacheFile = VEnvironment.getPackageCacheFile(packageName);
            FileInputStream is = new FileInputStream(cacheFile);
            byte[] bytes = FileUtils.toByteArray(is);
            is.close();
            p.unmarshall(bytes, 0, bytes.length);
            p.setDataPosition(0);
            int cacheVersion = p.readInt();
            if (cacheVersion != PACKAGE_CACHE_VERSION) {
                VLog.w(TAG, "Invalid package cache version for %s: %d != %d",
                        packageName, cacheVersion, PACKAGE_CACHE_VERSION);
                throw new IllegalStateException("Invalid version.");
            }
            VPackage pkg = new VPackage(p);
            addOwner(pkg);
            return pkg;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            p.recycle();
        }
        return null;
    }

    public static void readSignature(VPackage pkg) {
        File signatureFile = VEnvironment.getSignatureFile(pkg.packageName);
        if (!signatureFile.exists()) {
            return;
        }
        Parcel p = Parcel.obtain();
        try {
            FileInputStream fis = new FileInputStream(signatureFile);
            byte[] bytes = FileUtils.toByteArray(fis);
            fis.close();
            p.unmarshall(bytes, 0, bytes.length);
            p.setDataPosition(0);
            pkg.mSignatures = p.createTypedArray(Signature.CREATOR);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            p.recycle();
        }
    }

    public static void savePackageCache(VPackage pkg) {
        final String packageName = pkg.packageName;
        File cacheFile = VEnvironment.getPackageCacheFile(packageName);
        if (cacheFile.exists()) {
            cacheFile.delete();
        }
        File signatureFile = VEnvironment.getSignatureFile(packageName);
        if (signatureFile.exists()) {
            signatureFile.delete();
        }
        Parcel p = Parcel.obtain();

        try {
            p.writeInt(PACKAGE_CACHE_VERSION);
            pkg.writeToParcel(p, 0);
            FileOutputStream fos = new FileOutputStream(cacheFile);
            fos.write(p.marshall());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            p.recycle();
        }
        Signature[] signatures = pkg.mSignatures;
        if (signatures != null) {
            if (signatureFile.exists() && !signatureFile.delete()) {
                VLog.w(TAG, "Unable to delete the signatures of " + packageName);
            }
            p = Parcel.obtain();
            try {
                p.writeTypedArray(signatures, 0);
                FileUtils.writeParcelToFile(p, signatureFile);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                p.recycle();
            }
        }
    }

    private static VPackage buildPackageCache(PackageParser.Package p) {
        VPackage cache = new VPackage();
        cache.activities = new ArrayList<>(p.activities.size());
        cache.services = new ArrayList<>(p.services.size());
        cache.receivers = new ArrayList<>(p.receivers.size());
        cache.providers = new ArrayList<>(p.providers.size());
        cache.instrumentation = new ArrayList<>(p.instrumentation.size());
        cache.permissions = new ArrayList<>(p.permissions.size());
        cache.permissionGroups = new ArrayList<>(p.permissionGroups.size());

        for (PackageParser.Activity activity : p.activities) {
            cache.activities.add(new VPackage.ActivityComponent(activity));
        }
        for (PackageParser.Service service : p.services) {
            cache.services.add(new VPackage.ServiceComponent(service));
        }
        for (PackageParser.Activity receiver : p.receivers) {
            cache.receivers.add(new VPackage.ActivityComponent(receiver));
        }
        for (PackageParser.Provider provider : p.providers) {
            cache.providers.add(new VPackage.ProviderComponent(provider));
        }
        for (PackageParser.Instrumentation instrumentation : p.instrumentation) {
            cache.instrumentation.add(new VPackage.InstrumentationComponent(instrumentation));
        }
        for (PackageParser.Permission permission : p.permissions) {
            cache.permissions.add(new VPackage.PermissionComponent(permission));
        }
        for (PackageParser.PermissionGroup permissionGroup : p.permissionGroups) {
            cache.permissionGroups.add(new VPackage.PermissionGroupComponent(permissionGroup));
        }
        cache.requestedPermissions = new ArrayList<>(p.requestedPermissions.size());
        cache.requestedPermissions.addAll(p.requestedPermissions);
        if (mirror.android.content.pm.PackageParser.Package.protectedBroadcasts != null) {
            List<String> protectedBroadcasts = mirror.android.content.pm.PackageParser.Package.protectedBroadcasts.get(p);
            if (protectedBroadcasts != null) {
                cache.protectedBroadcasts = new ArrayList<>(protectedBroadcasts);
                cache.protectedBroadcasts.addAll(protectedBroadcasts);
            }
        }
        cache.applicationInfo = p.applicationInfo;
        cache.mSignatures = getSignature(p);
        cache.mAppMetaData = p.mAppMetaData;
        cache.packageName = p.packageName;
        cache.mPreferredOrder = p.mPreferredOrder;
        cache.mVersionName = p.mVersionName;
        cache.mSharedUserId = p.mSharedUserId;
        cache.mSharedUserLabel = p.mSharedUserLabel;
        cache.usesLibraries = p.usesLibraries;
        cache.mVersionCode = p.mVersionCode;
        cache.configPreferences = p.configPreferences;
        cache.reqFeatures = p.reqFeatures;
        cache.usesOptionalLibraries = p.usesOptionalLibraries;
        addOwner(cache);
        return cache;
    }

    private static Signature[] getSignature(PackageParser.Package p) {
        if (BuildCompat.isPie()) {
            return p.mSigningDetails.signatures;
        } else {
            return p.mSignatures;
        }
    }

    public static void initApplicationInfoBase(PackageSetting ps, VPackage p) {
        ApplicationInfo ai = p.applicationInfo;
        if (TextUtils.isEmpty(ai.processName)) {
            ai.processName = ai.packageName;
        }
        ai.enabled = true;
        ai.uid = ps.appId;
        ai.name = ComponentFixer.fixComponentClassName(ps.packageName, ai.name);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ApplicationInfoL.scanSourceDir.set(ai, ai.dataDir);
            ApplicationInfoL.scanPublicSourceDir.set(ai, ai.dataDir);
            String hostPrimaryCpuAbi = ApplicationInfoL.primaryCpuAbi.get(VirtualCore.get().getContext().getApplicationInfo());
            ApplicationInfoL.primaryCpuAbi.set(ai, hostPrimaryCpuAbi);
        }
        String[] sharedLibraryFiles = sSharedLibCache.get(ps.packageName);
        if (sharedLibraryFiles == null) {
            List<String> sharedLibraryFileList = new LinkedList<>();
            if (ps.appMode == InstalledAppInfo.MODE_APP_USE_OUTSIDE_APK) {
                PackageManager hostPM = VirtualCore.get().getUnHookPackageManager();
                try {
                    ApplicationInfo hostInfo = hostPM.getApplicationInfo(ps.packageName, PackageManager.GET_SHARED_LIBRARY_FILES);
                    if (hostInfo.sharedLibraryFiles != null) {
                        Collections.addAll(sharedLibraryFileList, hostInfo.sharedLibraryFiles);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    // ignore
                }
            }
            if (Build.VERSION.SDK_INT >= 28 && (ai.targetSdkVersion < 28 || needFixApache(p.usesLibraries, p.usesOptionalLibraries))) {
                String APACHE_LEGACY_JAR = "/system/framework/org.apache.http.legacy.boot.jar";
                String APACHE_LEGACY_JAR_Q = "/system/framework/org.apache.http.legacy.jar";
                if (!sharedLibraryFileList.contains(APACHE_LEGACY_JAR) && !sharedLibraryFileList.contains(APACHE_LEGACY_JAR_Q)) {
                    if (BuildCompat.isQ()) {
                        if (!FileUtils.isExist(APACHE_LEGACY_JAR_Q)) {
                            sharedLibraryFileList.add(APACHE_LEGACY_JAR);
                        } else {
                            sharedLibraryFileList.add(APACHE_LEGACY_JAR_Q);
                        }
                    } else {
                        sharedLibraryFileList.add(APACHE_LEGACY_JAR);
                    }
                }
            }
            sharedLibraryFiles = sharedLibraryFileList.toArray(new String[0]);
            sSharedLibCache.put(ps.packageName, sharedLibraryFiles);
        }
        ai.sharedLibraryFiles = sharedLibraryFiles;
    }

    private static boolean needFixApache(List<String> usesLibraries, List<String> usesOptionalLibraries){
        if(usesLibraries != null){
            if(usesLibraries.contains("org.apache.http.legacy")){
                return true;
            }
        }
        if(usesOptionalLibraries != null){
            if(usesOptionalLibraries.contains("org.apache.http.legacy")){
                return true;
            }
        }
        return false;
    }

    private static boolean initApplicationAsUser(ApplicationInfo ai, int userId) {
        PackageSetting ps = PackageCacheManager.getSetting(ai.packageName);
        if (ps == null) {
            return false;
        }
        boolean is64bit = ps.isRunPluginProcess();
        String apkPath = ps.getApkPath(is64bit);
        if(apkPath == null){
            return false;
        }
        ai.publicSourceDir = apkPath;
        ai.sourceDir = apkPath;
        if (ps.appMode != InstalledAppInfo.MODE_APP_USE_OUTSIDE_APK && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String[] splitSourceDirs = ai.splitSourceDirs != null ? ai.splitSourceDirs : ai.splitPublicSourceDirs;
            if (splitSourceDirs != null && splitSourceDirs.length > 0) {
                File packageDir = is64bit
                        ? VEnvironment.getDataAppPackageDirectory64(ai.packageName)
                        : VEnvironment.getDataAppPackageDirectory(ai.packageName);
                String[] rewrittenSplitSourceDirs = new String[splitSourceDirs.length];
                String[] rewrittenSplitPublicSourceDirs = new String[splitSourceDirs.length];
                for (int i = 0; i < splitSourceDirs.length; i++) {
                    String splitPath = splitSourceDirs[i];
                    String splitFileName = splitPath == null ? null : new File(splitPath).getName();
                    if (TextUtils.isEmpty(splitFileName)) {
                        rewrittenSplitSourceDirs[i] = splitPath;
                        rewrittenSplitPublicSourceDirs[i] = splitPath;
                    } else {
                        String rewrittenPath = new File(packageDir, splitFileName).getPath();
                        rewrittenSplitSourceDirs[i] = rewrittenPath;
                        rewrittenSplitPublicSourceDirs[i] = rewrittenPath;
                    }
                }
                ai.splitSourceDirs = rewrittenSplitSourceDirs;
                ai.splitPublicSourceDirs = rewrittenSplitPublicSourceDirs;
            }
        }
        SettingConfig config = VirtualCore.getConfig();
        SettingConfig.AppLibConfig libConfig = config.getAppLibConfig(ai.packageName);
        if (is64bit) {
            ai.nativeLibraryDir = VEnvironment.getAppLibDirectory64(ai.packageName).getPath();
        } else {
            ai.nativeLibraryDir = VEnvironment.getAppLibDirectory(ai.packageName).getPath();
        }
        if (ps.appMode == InstalledAppInfo.MODE_APP_USE_OUTSIDE_APK) {
            ApplicationInfo outside = null;
            try {
                outside = VirtualCore.get().getUnHookPackageManager().getApplicationInfo(ai.packageName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                // ignore
            }
            if (libConfig == SettingConfig.AppLibConfig.UseRealLib && outside == null) {
                libConfig = SettingConfig.AppLibConfig.UseOwnLib;
            }
            if (GmsSupport.isGoogleAppOrService(ai.packageName)) {
                libConfig = SettingConfig.AppLibConfig.UseOwnLib;
            }
            if (outside != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ai.splitNames = outside.splitNames;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ai.splitPublicSourceDirs = outside.splitPublicSourceDirs;
                    ai.splitSourceDirs = outside.splitSourceDirs;
                }
                if (libConfig == SettingConfig.AppLibConfig.UseRealLib) {
                    String outsideNativeLib = chooseOutsideNativeLib(outside, VirtualRuntime.is64bit());
                    if (outsideNativeLib != null) {
                        ai.nativeLibraryDir = outsideNativeLib;
                    }
                }
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (is64bit) {
                if (Build.SUPPORTED_64_BIT_ABIS.length > 0) {
                    ApplicationInfoL.primaryCpuAbi.set(ai, Build.SUPPORTED_64_BIT_ABIS[0]);
                }
                if (ps.flag == PackageSetting.FLAG_RUN_BOTH_32BIT_64BIT) {
                    ApplicationInfoL.secondaryCpuAbi.set(ai, Build.SUPPORTED_32_BIT_ABIS[0]);
                }
            } else {
                ApplicationInfoL.primaryCpuAbi.set(ai, Build.SUPPORTED_32_BIT_ABIS[0]);
                if (ps.flag == PackageSetting.FLAG_RUN_BOTH_32BIT_64BIT) {
                    if (Build.SUPPORTED_64_BIT_ABIS.length > 0) {
                        ApplicationInfoL.secondaryCpuAbi.set(ai, Build.SUPPORTED_64_BIT_ABIS[0]);
                    }
                }
            }
        }

        if (is64bit) {
            ai.dataDir = VEnvironment.getDataUserPackageDirectory64(userId, ai.packageName).getPath();
        } else {
            ai.dataDir = VEnvironment.getDataUserPackageDirectory(userId, ai.packageName).getPath();
        }
        String scanSourceDir = new File(apkPath).getParent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ApplicationInfoL.scanSourceDir.set(ai, scanSourceDir);
            ApplicationInfoL.scanPublicSourceDir.set(ai, scanSourceDir);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String deDataDir;
            if (is64bit) {
                deDataDir = VEnvironment.getDeDataUserPackageDirectory64(userId, ai.packageName).getPath();
            } else {
                deDataDir = VEnvironment.getDeDataUserPackageDirectory(userId, ai.packageName).getPath();
            }
            if (ApplicationInfoN.deviceEncryptedDataDir != null) {
                ApplicationInfoN.deviceEncryptedDataDir.set(ai, deDataDir);
            }
            if (ApplicationInfoN.credentialEncryptedDataDir != null) {
                ApplicationInfoN.credentialEncryptedDataDir.set(ai, ai.dataDir);
            }
            if (ApplicationInfoN.deviceProtectedDataDir != null) {
                ApplicationInfoN.deviceProtectedDataDir.set(ai, deDataDir);
            }
            if (ApplicationInfoN.credentialProtectedDataDir != null) {
                ApplicationInfoN.credentialProtectedDataDir.set(ai, ai.dataDir);
            }
        }
        if (config.isEnableIORedirect()) {
            if (config.isUseRealDataDir(ai.packageName)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    ai.dataDir = "/data/user/" + userId + "/" + ai.packageName;
                } else {
                    ai.dataDir = "/data/data/" + ai.packageName;
                }
            }
            if (config.isUseRealLibDir(ai.packageName)) {
                ai.nativeLibraryDir = "/data/data/" + ai.packageName + "/lib/";
            }
        }
        return true;
    }

    private static String chooseOutsideNativeLib(ApplicationInfo ai, boolean is64bit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                String primaryCpuAbi = ApplicationInfoL.primaryCpuAbi.get(ai);
                String secondaryCpuAbi = ApplicationInfoL.secondaryCpuAbi.get(ai);
                if (primaryCpuAbi == null) {
                    return null;
                }
                boolean matchPrimary = is64bit
                        ? NativeLibraryHelperCompat.is64bitAbi(primaryCpuAbi)
                        : NativeLibraryHelperCompat.is32bitAbi(primaryCpuAbi);
                if (matchPrimary) {
                    return ai.nativeLibraryDir;
                } else {
                    if (secondaryCpuAbi != null) {
                        return ApplicationInfoL.secondaryNativeLibraryDir.get(ai);
                    }
                    return null;
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return ai.nativeLibraryDir;
    }

    private static void addOwner(VPackage p) {
        for (VPackage.ActivityComponent activity : p.activities) {
            activity.owner = p;
            for (VPackage.ActivityIntentInfo info : activity.intents) {
                info.activity = activity;
            }
        }
        for (VPackage.ServiceComponent service : p.services) {
            service.owner = p;
            for (VPackage.ServiceIntentInfo info : service.intents) {
                info.service = service;
            }
        }
        for (VPackage.ActivityComponent receiver : p.receivers) {
            receiver.owner = p;
            for (VPackage.ActivityIntentInfo info : receiver.intents) {
                info.activity = receiver;
            }
        }
        for (VPackage.ProviderComponent provider : p.providers) {
            provider.owner = p;
            for (VPackage.ProviderIntentInfo info : provider.intents) {
                info.provider = provider;
            }
        }
        for (VPackage.InstrumentationComponent instrumentation : p.instrumentation) {
            instrumentation.owner = p;
        }
        for (VPackage.PermissionComponent permission : p.permissions) {
            permission.owner = p;
        }
        for (VPackage.PermissionGroupComponent group : p.permissionGroups) {
            group.owner = p;
        }
        int flags = ApplicationInfo.FLAG_HAS_CODE;
        if (GmsSupport.isGoogleService(p.packageName)) {
            flags |= ApplicationInfo.FLAG_PERSISTENT;
        }
        p.applicationInfo.flags |= flags;
    }

    private static boolean isAppPermissionEnable(String pkg, String perName) {
        if (!VirtualCore.get().checkSelfPermission(perName, false)) {
            return false;
        }
        return com.xdja.zs.VAppPermissionManagerService.get().getAppPermissionEnable(pkg, perName);
    }

    public static PackageInfo generatePackageInfo(VPackage p, int flags, long firstInstallTime, long lastUpdateTime, PackageUserState state, int userId) {
        if (!checkUseInstalledOrHidden(state, flags)) {
            return null;
        }
        if (p.mSignatures == null) {
            readSignature(p);
        }
        PackageInfo pi = new PackageInfo();
        pi.packageName = p.packageName;
        pi.versionCode = p.mVersionCode;
        pi.sharedUserLabel = p.mSharedUserLabel;
        pi.versionName = p.mVersionName;
        pi.sharedUserId = p.mSharedUserId;
        pi.applicationInfo = generateApplicationInfo(p, flags, state, userId);
        if(pi.applicationInfo == null){
            return null;
        }
        pi.firstInstallTime = firstInstallTime;
        pi.lastUpdateTime = lastUpdateTime;
        if (p.requestedPermissions != null && !p.requestedPermissions.isEmpty()) {
            String[] requestedPermissions = new String[p.requestedPermissions.size()];
            p.requestedPermissions.toArray(requestedPermissions);
            pi.requestedPermissions = requestedPermissions;
        }
        if ((flags & PackageManager.GET_GIDS) != 0) {
            pi.gids = PackageParserCompat.GIDS;
        }
        if ((flags & PackageManager.GET_CONFIGURATIONS) != 0) {
            int N = p.configPreferences != null ? p.configPreferences.size() : 0;
            if (N > 0) {
                pi.configPreferences = new ConfigurationInfo[N];
                p.configPreferences.toArray(pi.configPreferences);
            }
            N = p.reqFeatures != null ? p.reqFeatures.size() : 0;
            if (N > 0) {
                pi.reqFeatures = new FeatureInfo[N];
                p.reqFeatures.toArray(pi.reqFeatures);
            }
        }
        if ((flags & PackageManager.GET_ACTIVITIES) != 0) {
            final int N = p.activities.size();
            if (N > 0) {
                int num = 0;
                final ActivityInfo[] res = new ActivityInfo[N];
                for (int i = 0; i < N; i++) {
                    final VPackage.ActivityComponent a = p.activities.get(i);
                    res[num++] = generateActivityInfo(a, flags, state, userId);
                }
                pi.activities = res;
            }
        }
        if ((flags & PackageManager.GET_RECEIVERS) != 0) {
            final int N = p.receivers.size();
            if (N > 0) {
                int num = 0;
                final ActivityInfo[] res = new ActivityInfo[N];
                for (int i = 0; i < N; i++) {
                    final VPackage.ActivityComponent a = p.receivers.get(i);
                    res[num++] = generateActivityInfo(a, flags, state, userId);
                }
                pi.receivers = res;
            }
        }
        if ((flags & PackageManager.GET_SERVICES) != 0) {
            final int N = p.services.size();
            if (N > 0) {
                int num = 0;
                final ServiceInfo[] res = new ServiceInfo[N];
                for (int i = 0; i < N; i++) {
                    final VPackage.ServiceComponent s = p.services.get(i);
                    res[num++] = generateServiceInfo(s, flags, state, userId);
                }
                pi.services = res;
            }
        }
        if ((flags & PackageManager.GET_PROVIDERS) != 0) {
            final int N = p.providers.size();
            if (N > 0) {
                int num = 0;
                final ProviderInfo[] res = new ProviderInfo[N];
                for (int i = 0; i < N; i++) {
                    final VPackage.ProviderComponent pr = p.providers.get(i);
                    res[num++] = generateProviderInfo(pr, flags, state, userId);
                }
                pi.providers = res;
            }
        }
        if ((flags & PackageManager.GET_INSTRUMENTATION) != 0) {
            int N = p.instrumentation.size();
            if (N > 0) {
                pi.instrumentation = new InstrumentationInfo[N];
                for (int i = 0; i < N; i++) {
                    pi.instrumentation[i] = generateInstrumentationInfo(
                            p.instrumentation.get(i), flags);
                }
            }
        }
        if ((flags & PackageManager.GET_PERMISSIONS) != 0) {
            int N = p.permissions.size();
            if (N > 0) {
                pi.permissions = new PermissionInfo[N];
                for (int i = 0; i < N; i++) {
                    pi.permissions[i] = generatePermissionInfo(p.permissions.get(i), flags);
                }
            }
            N = p.requestedPermissions == null ? 0 : p.requestedPermissions.size();
            if (N > 0) {
                pi.requestedPermissions = new String[N];
                pi.requestedPermissionsFlags = new int[N];
                for (int i = 0; i < N; i++) {
                    final String perm = p.requestedPermissions.get(i);
                    pi.requestedPermissions[i] = perm;
                    pi.requestedPermissionsFlags[i] = isAppPermissionEnable(pi.packageName, perm) ? PackageInfo.REQUESTED_PERMISSION_GRANTED : 0;
                }
            }
        }
        if ((flags & PackageManager.GET_SIGNATURES) != 0 || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && (flags & 0x08000000) != 0)) {
            if (p.mSignatures != null) {
                int N = p.mSignatures.length;
                pi.signatures = new Signature[N];
                System.arraycopy(p.mSignatures, 0, pi.signatures, 0, N);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    try {
                        pi.signingInfo = new android.content.pm.SigningInfo();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    PackageInfo outInfo = VirtualCore.get().getUnHookPackageManager().getPackageInfo(p.packageName, PackageManager.GET_SIGNATURES);
                    pi.signatures = outInfo.signatures;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return pi;
    }

    public static ApplicationInfo generateApplicationInfo(VPackage p, int flags,
                                                          PackageUserState state, int userId) {
        if (p == null) return null;
        if (!checkUseInstalledOrHidden(state, flags)) {
            return null;
        }
        // Make shallow copy so we can store the metadata/libraries safely
        ApplicationInfo ai = new ApplicationInfo(p.applicationInfo);

        //xdja mdm能否卸载盒内应用需要判断FLAG_SYSTEM标志，龙剑邮箱添加该标志so加载失败，黑龙江项目中无mdm
        if(InstallerSettingManager.get().isSystemApp(p.packageName) && (!p.packageName.equals("com.xdja.HDSafeEMailClient"))){
            ai.flags = ai.flags | ApplicationInfo.FLAG_SYSTEM;
        }

        if ((flags & PackageManager.GET_META_DATA) != 0) {
            ai.metaData = p.mAppMetaData != null ? new android.os.Bundle(p.mAppMetaData) : null;
            if (ai.metaData == null) {
                Bundle hostMetaData = resolveHostAppMetaData(p);
                if (hostMetaData != null) {
                    ai.metaData = hostMetaData;
                    p.mAppMetaData = new android.os.Bundle(hostMetaData);
                    VLog.w(TAG, "Generated ApplicationInfo borrowed host metadata for %s",
                            p.packageName);
                }
            }
        }
        if(!initApplicationAsUser(ai, userId)){
            return null;
        }
        if(VirtualCore.getConfig().isForceVmSafeMode(ai.packageName)) {
            ai.flags |= ApplicationInfo.FLAG_VM_SAFE_MODE;
        }
        return ai;
    }

    private static Bundle resolveHostAppMetaData(VPackage p) {
        if (p == null || TextUtils.isEmpty(p.packageName)) {
            return null;
        }
        if (!(p.mExtras instanceof PackageSetting)) {
            return null;
        }
        PackageSetting ps = (PackageSetting) p.mExtras;
        if (ps.appMode != InstalledAppInfo.MODE_APP_USE_OUTSIDE_APK) {
            return null;
        }
        try {
            ApplicationInfo hostAi = VirtualCore.get().getUnHookPackageManager()
                    .getApplicationInfo(p.packageName, PackageManager.GET_META_DATA);
            if (hostAi != null && hostAi.metaData != null) {
                return new android.os.Bundle(hostAi.metaData);
            }
        } catch (Throwable e) {
            VLog.w(TAG, "Unable to resolve host app metadata for %s: %s",
                    p.packageName, e.getMessage());
        }
        return null;
    }


    public static ActivityInfo generateActivityInfo(VPackage.ActivityComponent a, int flags,
                                                    PackageUserState state, int userId) {
        if (a == null) return null;
        if (!checkUseInstalledOrHidden(state, flags)) {
            return null;
        }
        // Make shallow copies so we can store the metadata safely
        ActivityInfo ai = new ActivityInfo(a.info);
        if ((flags & PackageManager.GET_META_DATA) != 0
                && (a.metaData != null)) {
            ai.metaData = a.metaData;
        } else if ((flags & PackageManager.GET_META_DATA) != 0) {
            Bundle hostMetaData = resolveHostActivityMetaData(a.owner, a.className);
            if (hostMetaData != null) {
                ai.metaData = hostMetaData;
                a.metaData = new Bundle(hostMetaData);
                VLog.w(TAG, "Generated ActivityInfo borrowed host metadata for %s/%s",
                        a.owner.packageName, a.className);
            }
        }
        ai.applicationInfo = generateApplicationInfo(a.owner, flags, state, userId);
        if(ai.applicationInfo == null){
            return null;
        }
        return ai;
    }

    public static ServiceInfo generateServiceInfo(VPackage.ServiceComponent s, int flags,
                                                  PackageUserState state, int userId) {
        if (s == null) return null;
        if (!checkUseInstalledOrHidden(state, flags)) {
            return null;
        }
        ServiceInfo si = new ServiceInfo(s.info);
        // Make shallow copies so we can store the metadata safely
        if ((flags & PackageManager.GET_META_DATA) != 0 && s.metaData != null) {
            si.metaData = s.metaData;
        } else if ((flags & PackageManager.GET_META_DATA) != 0) {
            Bundle hostMetaData = resolveHostServiceMetaData(s.owner, s.className);
            if (hostMetaData != null) {
                si.metaData = hostMetaData;
                s.metaData = new Bundle(hostMetaData);
                VLog.w(TAG, "Generated ServiceInfo borrowed host metadata for %s/%s",
                        s.owner.packageName, s.className);
            }
        }
        si.applicationInfo = generateApplicationInfo(s.owner, flags, state, userId);
        if(si.applicationInfo == null){
            return null;
        }
        return si;
    }

    public static ProviderInfo generateProviderInfo(VPackage.ProviderComponent p, int flags,
                                                    PackageUserState state, int userId) {
        if (p == null) return null;
        if (!checkUseInstalledOrHidden(state, flags)) {
            return null;
        }
        // Make shallow copies so we can store the metadata safely
        ProviderInfo pi = new ProviderInfo(p.info);
        if ((flags & PackageManager.GET_META_DATA) != 0
                && (p.metaData != null)) {
            pi.metaData = p.metaData;
        } else if ((flags & PackageManager.GET_META_DATA) != 0) {
            Bundle hostMetaData = resolveHostProviderMetaData(p.owner, p.className);
            if (hostMetaData != null) {
                pi.metaData = hostMetaData;
                p.metaData = new Bundle(hostMetaData);
                VLog.w(TAG, "Generated ProviderInfo borrowed host metadata for %s/%s",
                        p.owner.packageName, p.className);
            }
        }

        if ((flags & PackageManager.GET_URI_PERMISSION_PATTERNS) == 0) {
            pi.uriPermissionPatterns = null;
        }
        pi.applicationInfo = generateApplicationInfo(p.owner, flags, state, userId);
        if(pi.applicationInfo == null){
            return null;
        }
        return pi;
    }

    public static InstrumentationInfo generateInstrumentationInfo(
            VPackage.InstrumentationComponent i, int flags) {
        if (i == null) return null;
        if ((flags & PackageManager.GET_META_DATA) == 0) {
            return i.info;
        }
        InstrumentationInfo ii = new InstrumentationInfo(i.info);
        ii.metaData = i.metaData;
        return ii;
    }

    public static PermissionInfo generatePermissionInfo(
            VPackage.PermissionComponent p, int flags) {
        if (p == null) return null;
        if ((flags & PackageManager.GET_META_DATA) == 0) {
            return p.info;
        }
        PermissionInfo pi = new PermissionInfo(p.info);
        pi.metaData = p.metaData;
        return pi;
    }

    public static PermissionGroupInfo generatePermissionGroupInfo(
            VPackage.PermissionGroupComponent pg, int flags) {
        if (pg == null) return null;
        if ((flags & PackageManager.GET_META_DATA) == 0) {
            return pg.info;
        }
        PermissionGroupInfo pgi = new PermissionGroupInfo(pg.info);
        pgi.metaData = pg.metaData;
        return pgi;
    }

    private static boolean checkUseInstalledOrHidden(PackageUserState state, int flags) {
        //noinspection deprecation
        return (state.installed && !state.hidden)
                || (flags & PackageManager.GET_UNINSTALLED_PACKAGES) != 0;
    }

    private static Bundle resolveHostActivityMetaData(VPackage owner, String className) {
        try {
            ActivityInfo info = resolveHostActivityInfo(owner, className);
            return info != null && info.metaData != null ? new Bundle(info.metaData) : null;
        } catch (Throwable e) {
            VLog.w(TAG, "Unable to resolve host activity metadata for %s/%s: %s",
                    owner != null ? owner.packageName : "null", className, e.getMessage());
            return null;
        }
    }

    private static Bundle resolveHostServiceMetaData(VPackage owner, String className) {
        try {
            ServiceInfo info = resolveHostServiceInfo(owner, className);
            return info != null && info.metaData != null ? new Bundle(info.metaData) : null;
        } catch (Throwable e) {
            VLog.w(TAG, "Unable to resolve host service metadata for %s/%s: %s",
                    owner != null ? owner.packageName : "null", className, e.getMessage());
            return null;
        }
    }

    private static Bundle resolveHostProviderMetaData(VPackage owner, String className) {
        try {
            ProviderInfo info = resolveHostProviderInfo(owner, className);
            return info != null && info.metaData != null ? new Bundle(info.metaData) : null;
        } catch (Throwable e) {
            VLog.w(TAG, "Unable to resolve host provider metadata for %s/%s: %s",
                    owner != null ? owner.packageName : "null", className, e.getMessage());
            return null;
        }
    }

    private static ActivityInfo resolveHostActivityInfo(VPackage owner, String className) throws PackageManager.NameNotFoundException {
        if (!shouldResolveHostComponent(owner, className)) {
            return null;
        }
        return VirtualCore.get().getUnHookPackageManager().getActivityInfo(
                new ComponentName(owner.packageName, className), PackageManager.GET_META_DATA);
    }

    private static ServiceInfo resolveHostServiceInfo(VPackage owner, String className) throws PackageManager.NameNotFoundException {
        if (!shouldResolveHostComponent(owner, className)) {
            return null;
        }
        return VirtualCore.get().getUnHookPackageManager().getServiceInfo(
                new ComponentName(owner.packageName, className), PackageManager.GET_META_DATA);
    }

    private static ProviderInfo resolveHostProviderInfo(VPackage owner, String className) throws PackageManager.NameNotFoundException {
        if (!shouldResolveHostComponent(owner, className)) {
            return null;
        }
        return VirtualCore.get().getUnHookPackageManager().getProviderInfo(
                new ComponentName(owner.packageName, className), PackageManager.GET_META_DATA);
    }

    private static boolean shouldResolveHostComponent(VPackage owner, String className) {
        if (owner == null || TextUtils.isEmpty(owner.packageName) || TextUtils.isEmpty(className)) {
            return false;
        }
        if (!(owner.mExtras instanceof PackageSetting)) {
            return false;
        }
        PackageSetting ps = (PackageSetting) owner.mExtras;
        return ps.appMode == InstalledAppInfo.MODE_APP_USE_OUTSIDE_APK;
    }

}
