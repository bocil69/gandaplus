package com.lody.virtual.client.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import com.lody.virtual.BuildConfig;
import com.lody.virtual.R;
import com.lody.virtual.client.NativeEngine;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.env.Constants;
import com.lody.virtual.client.env.SpecialComponentList;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.client.fixer.ContextFixer;
import com.lody.virtual.client.hook.delegate.TaskDescriptionDelegate;
import com.lody.virtual.client.ipc.LocalProxyUtils;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.client.ipc.VPackageManager;
import com.lody.virtual.client.stub.HiddenForeNotification;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.helper.compat.ActivityManagerCompat;
import com.lody.virtual.helper.compat.BundleCompat;
import com.lody.virtual.helper.compat.ReceiverCompat;
import com.lody.virtual.helper.utils.BitmapUtils;
import com.lody.virtual.helper.utils.FileUtils;
import com.lody.virtual.helper.utils.IInterfaceUtils;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.remote.BroadcastIntentData;
import com.lody.virtual.remote.InstallOptions;
import com.lody.virtual.remote.InstallResult;
import com.lody.virtual.remote.InstalledAppInfo;
import com.lody.virtual.server.bit64.V64BitHelper;
import com.lody.virtual.server.interfaces.IAppManager;
import com.lody.virtual.server.interfaces.IPackageObserver;
import com.lody.virtual.server.pm.PrivilegeAppOptimizer;
import com.xdja.activitycounter.ActivityCounterManager;
import com.xdja.call.CallLogObserver;
import com.xdja.call.PhoneCallService;
import com.xdja.mms.SmsObserver;
import com.xdja.utils.PackagePermissionManager;
import com.xdja.zs.IAppPermissionCallback;
import com.xdja.zs.IControllerServiceCallback;
import com.xdja.zs.INotificationCallback;
import com.xdja.zs.IToastCallback;
import com.xdja.zs.IUiCallback;
import com.xdja.zs.IVSCallback;
import com.xdja.zs.IVSKeyCallback;
import com.xdja.zs.exceptionRecorder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mirror.android.app.ActivityThread;

/**
 * @author Lody
 * @version 3.5
 */
public final class VirtualCore {

    public static final int GET_HIDDEN_APP = 0x00000001;
    private static final String TAG = VirtualCore.class.getSimpleName();
    @SuppressLint("StaticFieldLeak")
    private static VirtualCore gCore = new VirtualCore();
    private final int myUid = Process.myUid();
    /**
     * Client Package Manager
     */
    private PackageManager unHookPackageManager;
    /**
     * Host package name
     */
    private String hostPkgName;
    /**
     * ActivityThread instance
     */
    private Object mainThread;
    private Context context;
    /**
     * Main ProcessName
     */
    private String mainProcessName;
    /**
     * Real Process Name
     */
    private String processName;
    private ProcessType processType;
    private boolean isHostPulginApp;
    private IAppManager mService;
    private boolean isStartUp;
    private PackageInfo mHostPkgInfo;
    private ConditionVariable mInitLock;
    private AppCallback mAppCallback;
    private TaskDescriptionDelegate mTaskDescriptionDelegate;
    private SettingConfig mConfig;
    private AppRequestListener mAppRequestListener;

    private VirtualCore() {
    }

    public static SettingConfig getConfig() {
        return get().mConfig;
    }

    public static VirtualCore get() {
        return gCore;
    }

    public static PackageManager getPM() {
        return get().getPackageManager();
    }

    public static Object mainThread() {
        if (BuildConfig.DEBUG) {
            if (get().isMainProcess()) {
                throw new RuntimeException("get ActivityThread on Main Process.");
            }
        }
        return get().mainThread;
    }

    public void gotoBackHome() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        home.putExtra("pid", Process.myPid());
        home.putExtra("process", get().getProcessName());
        home.putExtra("userId", VUserHandle.myUserId());
        try {
            getContext().startActivity(home);
        } catch (Throwable ignore) {
        }
    }

    public ConditionVariable getInitLock() {
        return mInitLock;
    }

    public int myUid() {
        return myUid;
    }

    public int myUserId() {
        return VUserHandle.getUserId(myUid);
    }

    public AppCallback getAppCallback() {
        return mAppCallback == null ? AppCallback.EMPTY : mAppCallback;
    }

    /**
     * @see #setAppCallback
     * @deprecated
     */
    public void setComponentDelegate(AppCallback delegate) {
        setAppCallback(delegate);
    }

    public void setAppCallback(AppCallback delegate) {
        this.mAppCallback = delegate;
    }

    public void setCrashHandler(CrashHandler handler) {
        VClient.get().setCrashHandler(handler);
    }

    public TaskDescriptionDelegate getTaskDescriptionDelegate() {
        return mTaskDescriptionDelegate;
    }

    public void setTaskDescriptionDelegate(TaskDescriptionDelegate mTaskDescriptionDelegate) {
        this.mTaskDescriptionDelegate = mTaskDescriptionDelegate;
    }

    public int[] getGids() {
        return mHostPkgInfo.gids;
    }

    public ApplicationInfo getHostApplicationInfo() {
        return mHostPkgInfo.applicationInfo;
    }

    public Context getContext() {
        return context;
    }

    public PackageManager getPackageManager() {
        return context.getPackageManager();
    }

    public boolean isSystemApp() {
        ApplicationInfo applicationInfo = getContext().getApplicationInfo();
        return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }

    public String getHostPkg() {
        return hostPkgName;
    }

    public int getTargetSdkVersion() {
        return context.getApplicationInfo().targetSdkVersion;
    }

    public PackageManager getUnHookPackageManager() {
        return unHookPackageManager;
    }

    public boolean checkSelfPermission(String permission, boolean is64Bit) {
        if (is64Bit) {
            return PackageManager.PERMISSION_GRANTED == unHookPackageManager.checkPermission(permission, StubManifest.PACKAGE_NAME_64BIT);
        } else {
            return PackageManager.PERMISSION_GRANTED == unHookPackageManager.checkPermission(permission, StubManifest.PACKAGE_NAME);
        }
    }

    public void waitStartup() {
        if (mInitLock != null) {
            mInitLock.block();
        }
    }

    public int getUidForSharedUser(String sharedUserName) {
        try {
            IAppManager service = getService();
            if (service == null) {
                waitForEngine();
                service = getService();
            }
            if (service == null) {
                Log.w(TAG, "getUidForSharedUser: app manager unavailable for " + sharedUserName);
                return -1;
            }
            return service.getUidForSharedUser(sharedUserName);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    private final BroadcastReceiver mDownloadCompleteReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            VLog.w("DownloadManager", "receive download completed brodcast: " + intent);
            intent.setExtrasClassLoader(BroadcastIntentData.class.getClassLoader());
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
                VActivityManager.get().handleDownloadCompleteIntent(intent);
            }
        }
    };

    public void startup(Context context, SettingConfig config) throws Throwable {
        if (!isStartUp) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new IllegalStateException("VirtualCore.startup() must called in main thread.");
            }
            mInitLock = new ConditionVariable();
            mConfig = config;
            String packageName = config.getHostPackageName();
            String packageName64 = config.getPluginEnginePackageName();
            Constants.ACTION_SHORTCUT = packageName + Constants.ACTION_SHORTCUT;
            Constants.ACTION_BADGER_CHANGE = packageName + Constants.ACTION_BADGER_CHANGE;

            StubManifest.PACKAGE_NAME = packageName;
            StubManifest.STUB_CP_AUTHORITY = packageName + ".virtual_stub_";
            StubManifest.PROXY_CP_AUTHORITY = packageName + ".provider_proxy";
            StubManifest.PROXY_CP_AUTHORITY_OUTSIDE =  packageName + ".provider_outside";

            if (packageName64 == null) {
                packageName64 = "NO_64BIT";
            }
            StubManifest.PACKAGE_NAME_64BIT = packageName64;
            StubManifest.STUB_CP_AUTHORITY_64BIT = packageName64 + ".virtual_stub_64bit_";
            StubManifest.PROXY_CP_AUTHORITY_64BIT = packageName64 + ".provider_proxy_64bit";

            this.context = context;
            unHookPackageManager = context.getPackageManager();
            mHostPkgInfo = unHookPackageManager.getPackageInfo(packageName, PackageManager.GET_GIDS);

            NativeEngine.bypassHiddenAPIEnforcementPolicyIfNeeded();
            //////////////////////////////
            // Now we can use hidden API//
            //////////////////////////////

            detectProcessType();
            if (isMainProcess()) {
                try {
                    CallLogObserver.observe();
                } catch (Throwable e) {
                    VLog.w(TAG, "CallLogObserver init failed (non-fatal): %s", e.getMessage());
                }
                try {
                    SmsObserver.observe();
                } catch (Throwable e) {
                    VLog.w(TAG, "SmsObserver init failed (non-fatal): %s", e.getMessage());
                }
            }
            if (isServerProcess() || isVAppProcess()) {
                mainThread = ActivityThread.currentActivityThread.call();
            }
            if (isPluginEngine()) {
                VLog.e(TAG, "===========  64Bit Engine(%s) ===========", processType.name());
                if (isVAppProcess()) {
                    getService().asBinder().linkToDeath(new IBinder.DeathRecipient() {
                        @Override
                        public void binderDied() {
                            VLog.e(TAG, "32Bit Engine was dead, kill app process.");
                            Process.killProcess(Process.myPid());
                        }
                    }, 0);
                }
            }
            if (isServerProcess() || is64bitHelperProcess()) {
                VLog.w("DownloadManager", "Listening DownloadManager action  in process: " + processType);
                IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
                try {
                    ReceiverCompat.registerReceiver(context, mDownloadCompleteReceiver, filter, true);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            InvocationStubManager invocationStubManager = InvocationStubManager.getInstance();
            invocationStubManager.init();
            invocationStubManager.injectAll();
            ContextFixer.fixContext(context);
            isStartUp = true;

            //xdja 屏蔽‘不兼容api’对话框    (进一步确保）    暂时屏蔽，观察效果。
            /*try {
                Reflect.on("android.app.ActivityThread").call("currentActivityThread").set("mHiddenApiWarningShown", true);
            } catch (Exception e)
            {
                e.printStackTrace();
            }*/

            mInitLock.open();
        }
    }

    public void waitForEngine() {
        ServiceManagerNative.ensureServerStarted();
    }

    //Add by xdja
    public void preLaunchApp() {
        try {
            if (shouldLaunchApp("com.xdja.dialer")) {
                VirtualCore.get().context.startService(new Intent(VirtualCore.get().context, PhoneCallService.class));
            }
        } catch (Throwable e) {
            Log.w(TAG, "preLaunchApp failed (non-fatal): " + e.getMessage());
        }
    }

    //Add by xdja
    public void sendBootCompleteBC(final String packageName, String action, boolean update) {
        if (shouldLaunchApp(packageName)) {
            PrivilegeAppOptimizer.get().addPrivilegeApp(packageName);
            PrivilegeAppOptimizer.get().performOptimizeAllApps();
            PrivilegeAppOptimizer.get().removePrivilegeApp(packageName);
        }
    }

    //Add by xdja
    public boolean shouldLaunchApp(String pkgName) {
        return (isAppInstalled(pkgName) && !isAppRunning(pkgName, myUserId(), false));
    }

    public boolean isEngineLaunched() {
        if (isPluginEngine()) {
            return true;
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String engineProcessName = getEngineProcessName();
        for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
            if (info.processName.endsWith(engineProcessName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isIORelocateWork() {
        try {
            return getService().isIORelocateWork();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<ActivityManager.RunningAppProcessInfo> getRunningAppProcessesEx() {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = new ArrayList<>(am.getRunningAppProcesses());
        List<ActivityManager.RunningAppProcessInfo> list64 = V64BitHelper.getRunningAppProcess64();
        if (list64 != null) {
            list.addAll(list64);
        }
        return list;
    }

    public List<ActivityManager.RunningTaskInfo> getRunningTasksEx(int maxNum) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = new ArrayList<>(am.getRunningTasks(maxNum));
        List<ActivityManager.RunningTaskInfo> list64 = V64BitHelper.getRunningTasks64(maxNum);
        if (list64 != null) {
            list.addAll(list64);
        }
        return list;
    }


    public List<ActivityManager.RecentTaskInfo> getRecentTasksEx(int maxNum, int flags) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RecentTaskInfo> list = new ArrayList<>(am.getRecentTasks(maxNum, flags));
        List<ActivityManager.RecentTaskInfo> list64 = V64BitHelper.getRecentTasks64(maxNum, flags);
        if (list64 != null) {
            list.addAll(list64);
        }
        return list;
    }

    public List<ActivityManager.RecentTaskInfo> getAppTasksEx() {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> list = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            //TODO V64BitHelper.getgetAppTasks64
            list = new ArrayList<>(am.getAppTasks());
            List<ActivityManager.RecentTaskInfo> recentTaskInfoList = new ArrayList<>();
            for (ActivityManager.AppTask task : list) {
                try {
                    ActivityManager.RecentTaskInfo info = task.getTaskInfo();
                    if (info == null) {
                        continue;
                    }
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
                        //過濾其他App
                        String pkg = ActivityManagerCompat.getPackageName(info);
                        if (!(TextUtils.equals(VirtualCore.getConfig().getHostPackageName(), pkg)
                                || TextUtils.equals(VirtualCore.getConfig().getPluginEnginePackageName(), pkg)
                        )) {
                            continue;
                        }
                    }
                    recentTaskInfoList.add(info);
                }catch (Exception e){
                    continue;
                }
            }
            return recentTaskInfoList;
        } else {
            return getRecentTasksEx(128, ActivityManager.RECENT_IGNORE_UNAVAILABLE | ActivityManager.RECENT_WITH_EXCLUDED);
        }
    }

    public void requestCopyPackage64(String packageName) {
        try {
            getService().requestCopyPackage64(packageName);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public String getEngineProcessName() {
        return context.getString(R.string.engine_process_name);
    }

    public void registerActivityLifecycleCallbacks(Application app){
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }
            @Override
            public void onActivityStarted(Activity activity) {
                ActivityCounterManager.get().activityCountAdd(activity.getPackageName(),activity.getClass().getName(),android.os.Process.myPid());
            }
            @Override
            public void onActivityResumed(Activity activity) {}
            @Override
            public void onActivityPaused(Activity activity) {}
            @Override
            public void onActivityStopped(Activity activity) {
                ActivityCounterManager.get().activityCountReduce(activity.getPackageName(),activity.getClass().getName(),android.os.Process.myPid());
            }
            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) { }
            @Override
            public void onActivityDestroyed(Activity activity) { }
        });
    }

    public void initialize(VirtualInitializer initializer) {
        if (initializer == null) {
            throw new IllegalStateException("Initializer = NULL");
        }
        Log.d("Vxlib", "version: " + BuildConfig.commit);
        switch (processType) {
            case Main:
                initializer.onMainProcess();
                //启动时清零主进程计数器，有可能在进程崩溃时导致计数器残留计数
                try {
                    ActivityCounterManager.get().cleanPackage(VirtualCore.get().getHostPkg());
                } catch (Throwable e) {
                    Log.w(TAG, "Failed to clean package counter (non-fatal): " + e.getMessage());
                }

                //设置异常记录
                Thread.setDefaultUncaughtExceptionHandler(new exceptionRecorder.defaulUncaughtExceptionHandler());

                break;
            case VAppClient:
                initializer.onVirtualProcess();
                break;
            case Server:
                initializer.onServerProcess();

                //设置异常记录
                Thread.setDefaultUncaughtExceptionHandler(new exceptionRecorder.defaulUncaughtExceptionHandler());

                break;
            case CHILD:
                initializer.onChildProcess();

                //设置异常记录
                Thread.setDefaultUncaughtExceptionHandler(new exceptionRecorder.defaulUncaughtExceptionHandler());
                
                break;
        }
    }

    private static String getProcessName(Context context) {
        int pid = Process.myPid();
        String processName = null;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
            if (info.pid == pid) {
                processName = info.processName;
                break;
            }
        }
        if (processName == null) {
            throw new RuntimeException("processName = null");
        }
        return processName;
    }

    private void detectProcessType() {
        // Host package name
        hostPkgName = context.getApplicationInfo().packageName;
        // Main process name
        mainProcessName = context.getApplicationInfo().processName;
        // Current process name
        processName = getProcessName(context);
        isHostPulginApp = StubManifest.isHostPluginPackageName(hostPkgName);
        if (processName.equals(mainProcessName)) {
            processType = ProcessType.Main;
        } else if (processName.endsWith(Constants.SERVER_PROCESS_NAME)) {
            processType = ProcessType.Server;
        } else if (processName.endsWith(Constants.HELPER_PROCESS_NAME)) {
            processType = ProcessType.Helper;
        } else if (VActivityManager.get().isAppProcess(processName)) {
            processType = ProcessType.VAppClient;
        } else {
            processType = ProcessType.CHILD;
        }
    }

    public boolean isPluginEngine() {
        return isHostPulginApp;
    }

    private IAppManager getService() {
        if (!IInterfaceUtils.isAlive(mService)) {
            synchronized (this) {
                Object remote = getStubInterface();
                if (remote == null) {
                    Log.e(TAG, "getService: getStubInterface() returned null - service not available");
                    return null;
                }
                mService = LocalProxyUtils.genProxy(IAppManager.class, remote);
                if (mService == null) {
                    Log.e(TAG, "getService: genProxy() returned null");
                } else {
                    Log.d(TAG, "getService: service connected successfully");
                }
            }
        }
        return mService;
    }

    private Object getStubInterface() {
        android.os.IBinder binder = ServiceManagerNative.getService(ServiceManagerNative.APP);
        if (binder == null) {
            Log.e(TAG, "getStubInterface: ServiceManagerNative.getService(APP) returned null");
            return null;
        }
        return IAppManager.Stub.asInterface(binder);
    }

    /**
     * @return If the current process is used to VA.
     */
    public boolean isVAppProcess() {
        return ProcessType.VAppClient == processType;
    }

    public boolean is64bitHelperProcess() {
        return ProcessType.Helper == processType;
    }

    /**
     * @return If the current process is the main.
     */
    public boolean isMainProcess() {
        return ProcessType.Main == processType;
    }

    /**
     * @return If the current process is the child.
     */
    public boolean isChildProcess() {
        return ProcessType.CHILD == processType;
    }

    /**
     * @return If the current process is the server.
     */
    public boolean isServerProcess() {
        return ProcessType.Server == processType;
    }

    /**
     * @return the <em>actual</em> process name
     */
    public String getProcessName() {
        return processName;
    }

    /**
     * @return the <em>Main</em> process name
     */
    public String getMainProcessName() {
        return mainProcessName;
    }

    /**
     * Optimize the Dalvik-Cache for the specified package.
     *
     * @param pkg package name
     * @throws IOException
     */
    @Deprecated
    public void preOpt(String pkg) throws IOException {

    }

    /**
     * Check if the specified app running in foreground / background?
     *
     * @param packageName package name
     * @param userId      user id
     * @return if the specified app running in foreground / background.
     */
    public boolean isAppRunning(String packageName, int userId, boolean foreground) {
        return VActivityManager.get().isAppRunning(packageName, userId, foreground);
    }

    public InstallResult installPackageSync(String apkPath, InstallOptions options) {
        final ConditionVariable lock = new ConditionVariable();
        final InstallResult[] out = new InstallResult[1];
        installPackage(apkPath, options, new InstallCallback() {
            @Override
            public void onFinish(InstallResult result) {
                out[0] = result;
                lock.open();
            }
        });
        lock.block();
        //Add by xdja
        preLaunchApp();
        // Handle null result (should not happen, but safety check)
        if (out[0] == null) {
            Log.e(TAG, "installPackageSync: result is null, creating failure result");
            InstallResult failure = new InstallResult();
            failure.isSuccess = false;
            failure.error = "Install failed - no result from server";
            return failure;
        }
        return out[0];
    }

    /**
     * Install from a list of APK paths where the first element is the base APK
     * and subsequent elements are split APKs (ABI / density).
     *
     * <p>Native libraries from ABI split APKs are extracted and placed in the virtual
     * app's native library directory automatically.
     *
     * <p>A single-element list is equivalent to
     * {@link #installPackageSync(String, InstallOptions)}.
     */
    public InstallResult installPackageSync(List<String> apkPaths, InstallOptions options) {
        if (apkPaths == null || apkPaths.isEmpty()) {
            return buildInstallFailure("apkPaths is null or empty");
        }
        if (apkPaths.size() == 1) {
            return installPackageSync(apkPaths.get(0), options);
        }

        final ConditionVariable lock = new ConditionVariable();
        final InstallResult[] out = new InstallResult[1];
        installPackage(apkPaths, options, new InstallCallback() {
            @Override
            public void onFinish(InstallResult result) {
                out[0] = result;
                lock.open();
            }
        });
        lock.block();
        preLaunchApp();
        if (out[0] == null) {
            Log.e(TAG, "installPackageSync(List): result is null, creating failure result");
            return buildInstallFailure("Install failed - no result from server");
        }
        return out[0];
    }

    @Deprecated
    public InstallResult installPackage(String apkPath, InstallOptions options) {
        return installPackageSync(apkPath, options);
    }


    public interface InstallCallback {
        void onFinish(InstallResult result);
    }

    private InstallResult buildInstallFailure(String error) {
        InstallResult res = new InstallResult();
        res.isSuccess = false;
        res.error = error;
        return res;
    }

    private ResultReceiver createInstallResultReceiver(final InstallCallback callback) {
        return new ResultReceiver(null) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                Log.i(TAG, "Stage: VirtualCore.installPackage result received, code: " + resultCode);
                InstallResult res = null;
                if (resultData != null) {
                    resultData.setClassLoader(InstallResult.class.getClassLoader());
                    res = resultData.getParcelable("result");
                }
                if (callback != null) {
                    if (res == null) {
                        Log.e(TAG, "Stage: installPackage: result from service is null");
                        res = buildInstallFailure("Server returned null result");
                    } else {
                        Log.i(TAG, "Stage: installPackage completed result: isSuccess=" + res.isSuccess + ", error=" + res.error);
                    }
                    callback.onFinish(res);
                }
            }
        };
    }

    public void installPackage(String apkPath, InstallOptions options, final InstallCallback callback) {
        Log.i(TAG, "Stage: VirtualCore.installPackage called for: " + apkPath);
        if (apkPath == null) {
            Log.e(TAG, "installPackage: apkPath is null");
            if (callback != null) {
                callback.onFinish(buildInstallFailure("APK path is null"));
            }
            return;
        }
        ResultReceiver receiver = createInstallResultReceiver(callback);
        try {
            IAppManager service = getService();
            if (service == null) {
                Log.e(TAG, "Stage: installPackage: getService() returned null");
                if (callback != null) {
                    callback.onFinish(buildInstallFailure("AppManager service not available"));
                }
                return;
            }
            Log.i(TAG, "Stage: Calling service.installPackage for " + apkPath);
            service.installPackage(apkPath, options, receiver);
        } catch (RemoteException e) {
            Log.e(TAG, "installPackage RemoteException: " + e.getMessage());
            if (callback != null) {
                callback.onFinish(buildInstallFailure("RemoteException: " + e.getMessage()));
            }
        } catch (Throwable e) {
            Log.e(TAG, "installPackage Throwable: " + e.getMessage());
            if (callback != null) {
                callback.onFinish(buildInstallFailure("Exception: " + e.getMessage()));
            }
        }
    }

    public void installPackage(List<String> apkPaths, InstallOptions options, final InstallCallback callback) {
        Log.i(TAG, "Stage: VirtualCore.installPackage(List) called for: " + apkPaths);
        if (apkPaths == null || apkPaths.isEmpty()) {
            if (callback != null) {
                callback.onFinish(buildInstallFailure("apkPaths is null or empty"));
            }
            return;
        }
        if (apkPaths.size() == 1) {
            installPackage(apkPaths.get(0), options, callback);
            return;
        }
        ResultReceiver receiver = createInstallResultReceiver(callback);
        try {
            IAppManager service = getService();
            if (service == null) {
                Log.e(TAG, "Stage: installPackage(List): getService() returned null");
                if (callback != null) {
                    callback.onFinish(buildInstallFailure("AppManager service not available"));
                }
                return;
            }
            Log.i(TAG, "Stage: Calling service.installSplitPackage for " + apkPaths.size() + " APKs");
            service.installSplitPackage(apkPaths, options, receiver);
        } catch (RemoteException e) {
            Log.e(TAG, "installPackage(List) RemoteException: " + e.getMessage());
            if (callback != null) {
                callback.onFinish(buildInstallFailure("RemoteException: " + e.getMessage()));
            }
        } catch (Throwable e) {
            Log.e(TAG, "installPackage(List) Throwable: " + e.getMessage());
            if (callback != null) {
                callback.onFinish(buildInstallFailure("Exception: " + e.getMessage()));
            }
        }
    }

    public InstallResult installPackageFromAsset(String asset, InstallOptions options) {
        InputStream inputStream = null;
        try {
            inputStream = getContext().getAssets().open(asset);
            return installPackageFromStream(inputStream, options);
        } catch (Throwable e) {
            InstallResult res = new InstallResult();
            res.error = e.getMessage();
            return res;
        } finally {
            FileUtils.closeQuietly(inputStream);
        }
    }

    public InstallResult installPackageFromStream(InputStream inputStream, InstallOptions options) {
        try {
            File dir = getContext().getCacheDir();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File apkFile = new File(dir, "tmp_" + System.currentTimeMillis() + ".apk");
            FileUtils.writeToFile(inputStream, apkFile);
            InstallResult res = installPackageSync(apkFile.getAbsolutePath(), options);
            apkFile.delete();
            return res;
        } catch (Throwable e) {
            InstallResult res = new InstallResult();
            res.error = e.getMessage();
            return res;
        }
    }

    public void addVisibleOutsidePackage(String pkg) {
        try {
            getService().addVisibleOutsidePackage(pkg);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void removeVisibleOutsidePackage(String pkg) {
        try {
            getService().removeVisibleOutsidePackage(pkg);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public boolean isOutsidePackageVisible(String pkg) {
        try {
            return getService().isOutsidePackageVisible(pkg);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public boolean isAppInstalled(String pkg) {
        try {
            return getService().isAppInstalled(pkg);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public boolean isPackageLaunchable(String packageName) {
        InstalledAppInfo info = getInstalledAppInfo(packageName, 0);
        if (info == null) {
            return false;
        }
        int launchUserId = resolveLaunchUserId(info.getInstalledUsers());
        return getLaunchIntent(packageName, launchUserId) != null;
    }

    public Intent getLaunchIntent(String packageName, int userId) {
        VPackageManager pm = VPackageManager.get();
        Intent intentToResolve = new Intent(Intent.ACTION_MAIN);
        intentToResolve.addCategory(Intent.CATEGORY_INFO);
        intentToResolve.setPackage(packageName);
        List<ResolveInfo> ris = pm.queryIntentActivities(intentToResolve, intentToResolve.resolveType(context), 0, userId);

        // Otherwise, try to find a main launcher activity.
        if (ris == null || ris.size() <= 0) {
            // reuse the intent instance
            intentToResolve.removeCategory(Intent.CATEGORY_INFO);
            intentToResolve.addCategory(Intent.CATEGORY_LAUNCHER);
            intentToResolve.setPackage(packageName);
            ris = pm.queryIntentActivities(intentToResolve, intentToResolve.resolveType(context), 0, userId);
        }
        if (ris == null || ris.size() <= 0) {
            Intent fallbackIntent = buildFallbackLaunchIntent(packageName, userId);
            if (fallbackIntent != null) {
                return fallbackIntent;
            }
            return null;
        }
        Intent intent = new Intent(intentToResolve);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName(ris.get(0).activityInfo.packageName,
                ris.get(0).activityInfo.name);
        return intent;
    }

    private int resolveLaunchUserId(int[] installedUsers) {
        if (installedUsers == null || installedUsers.length == 0) {
            return 0;
        }
        for (int userId : installedUsers) {
            if (userId == 0) {
                return 0;
            }
        }
        return installedUsers[0];
    }

    private Intent buildFallbackLaunchIntent(String packageName, int userId) {
        InstalledAppInfo installedAppInfo = getInstalledAppInfo(packageName, 0);
        if (installedAppInfo == null) {
            return null;
        }
        PackageInfo packageInfo = VPackageManager.get().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES, userId);
        if (packageInfo != null && packageInfo.activities != null && packageInfo.activities.length > 0) {
            ActivityInfo fallbackActivity = selectFallbackLaunchActivity(packageInfo.activities);
            Intent packageInfoIntent = buildExplicitLaunchIntent(fallbackActivity);
            if (packageInfoIntent != null && resolveActivityInfo(packageInfoIntent, userId) != null) {
                VLog.w("VirtualCore", "Fallback launch activity from PackageInfo for %s user=%d activity=%s", packageName, userId, fallbackActivity.name);
                return packageInfoIntent;
            }
            if (fallbackActivity != null) {
                VLog.w("VirtualCore", "PackageInfo fallback activity not resolvable for %s user=%d activity=%s", packageName, userId, fallbackActivity.name);
            }
        } else {
            VLog.w("VirtualCore", "PackageInfo fallback has no activities for %s user=%d", packageName, userId);
        }

        Intent hostLaunchIntent = buildResolvableHostLaunchIntent(packageName, userId);
        if (hostLaunchIntent != null) {
            return hostLaunchIntent;
        }

        try {
            PackageInfo hostPackageInfo = getUnHookPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            if (hostPackageInfo != null && hostPackageInfo.activities != null && hostPackageInfo.activities.length > 0) {
                Intent hostActivitiesIntent = buildResolvableHostActivityIntent(hostPackageInfo.activities, userId);
                if (hostActivitiesIntent != null) {
                    VLog.w("VirtualCore", "Fallback launch activity from host PackageInfo for %s user=%d component=%s", packageName, userId, hostActivitiesIntent.getComponent());
                    return hostActivitiesIntent;
                }
                VLog.w("VirtualCore", "Host PackageInfo activities are not resolvable in virtual PM for %s user=%d", packageName, userId);
            }
        } catch (PackageManager.NameNotFoundException e) {
            VLog.w("VirtualCore", "Host PackageInfo lookup failed for %s user=%d", packageName, userId);
        }

        return null;
    }

    private Intent buildResolvableHostLaunchIntent(String packageName, int userId) {
        Intent hostLaunchIntent = getUnHookPackageManager().getLaunchIntentForPackage(packageName);
        if (hostLaunchIntent != null && hostLaunchIntent.getComponent() != null) {
            Intent explicitIntent = new Intent(hostLaunchIntent);
            explicitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            explicitIntent.setAction(Intent.ACTION_MAIN);
            explicitIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            explicitIntent.setPackage(null);
            explicitIntent.setComponent(hostLaunchIntent.getComponent());
            if (resolveActivityInfo(explicitIntent, userId) != null) {
                VLog.w("VirtualCore", "Fallback launch intent from host package manager for %s user=%d component=%s", packageName, userId, explicitIntent.getComponent());
                return explicitIntent;
            }
        }

        if (hostLaunchIntent != null && hostLaunchIntent.getComponent() != null) {
            VLog.w("VirtualCore", "Host launch component not resolvable in virtual PM for %s user=%d component=%s", packageName, userId, hostLaunchIntent.getComponent());
        }
        return null;
    }

    private Intent buildResolvableHostActivityIntent(ActivityInfo[] hostActivities, int userId) {
        ActivityInfo preferredHostActivity = selectFallbackLaunchActivity(hostActivities);
        Intent preferredIntent = buildExplicitLaunchIntent(preferredHostActivity);
        if (preferredIntent != null && resolveActivityInfo(preferredIntent, userId) != null) {
            return preferredIntent;
        }

        Intent bestIntent = null;
        int bestScore = Integer.MIN_VALUE;
        for (ActivityInfo hostActivity : hostActivities) {
            Intent candidateIntent = buildExplicitLaunchIntent(hostActivity);
            if (candidateIntent == null || resolveActivityInfo(candidateIntent, userId) == null) {
                continue;
            }
            int candidateScore = getFallbackLaunchActivityScore(hostActivity);
            if (bestIntent == null || candidateScore > bestScore) {
                bestIntent = candidateIntent;
                bestScore = candidateScore;
            }
        }
        return bestIntent;
    }

    private Intent buildExplicitLaunchIntent(ActivityInfo activityInfo) {
        if (activityInfo == null || TextUtils.isEmpty(activityInfo.packageName) || TextUtils.isEmpty(activityInfo.name)) {
            return null;
        }
        Intent explicitIntent = new Intent(Intent.ACTION_MAIN);
        explicitIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        explicitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        explicitIntent.setClassName(activityInfo.packageName, activityInfo.name);
        return explicitIntent;
    }

    private ActivityInfo selectFallbackLaunchActivity(ActivityInfo[] activities) {
        ActivityInfo firstEnabled = null;
        ActivityInfo firstExportedEnabled = null;
        ActivityInfo preferred = null;
        int preferredScore = Integer.MIN_VALUE;
        for (ActivityInfo activityInfo : activities) {
            if (activityInfo == null || !activityInfo.enabled || TextUtils.isEmpty(activityInfo.name)) {
                continue;
            }
            if (firstEnabled == null) {
                firstEnabled = activityInfo;
            }
            if (activityInfo.exported && firstExportedEnabled == null) {
                firstExportedEnabled = activityInfo;
            }
            int candidateScore = getFallbackLaunchActivityScore(activityInfo);
            if (preferred == null || candidateScore > preferredScore) {
                preferred = activityInfo;
                preferredScore = candidateScore;
            }
        }
        if (preferred != null) {
            return preferred;
        }
        if (firstExportedEnabled != null) {
            return firstExportedEnabled;
        }
        return firstEnabled;
    }

    private int getFallbackLaunchActivityScore(ActivityInfo activityInfo) {
        if (activityInfo == null || !activityInfo.enabled || TextUtils.isEmpty(activityInfo.name)) {
            return Integer.MIN_VALUE;
        }
        int score = activityInfo.exported ? 100 : 10;
        String lowerName = activityInfo.name.toLowerCase();
        if (containsAnyKeyword(lowerName, "launcher")) {
            score += 1000;
        }
        if (containsAnyKeyword(lowerName, "starting")) {
            score += 650;
        }
        if (containsAnyKeyword(lowerName, "start")) {
            score += 450;
        }
        if (containsAnyKeyword(lowerName, "splash")) {
            score += 350;
        }
        if (containsAnyKeyword(lowerName, "main")) {
            score += 300;
        }
        if (containsAnyKeyword(lowerName, "home")) {
            score += 250;
        }
        if (containsAnyKeyword(lowerName, "entry", "landing", "welcome")) {
            score += 150;
        }
        if (containsAnyKeyword(lowerName, "deeplink", "deep_link", "deep.link")) {
            score -= 1000;
        }
        if (containsAnyKeyword(lowerName, "router", "route", "redirect")) {
            score -= 700;
        }
        if (containsAnyKeyword(lowerName, "central")) {
            score -= 500;
        }
        if (containsAnyKeyword(lowerName, "intent", "action")) {
            score -= 400;
        }
        if (containsAnyKeyword(lowerName, "proxy", "bridge", "alias", "dispatcher")) {
            score -= 250;
        }
        return score;
    }

    private boolean containsAnyKeyword(String value, String... keywords) {
        if (TextUtils.isEmpty(value) || keywords == null) {
            return false;
        }
        for (String keyword : keywords) {
            if (!TextUtils.isEmpty(keyword) && value.contains(keyword)) {
                return true;
            }
        }
        return false;
    }


    public boolean createShortcut(int userId, String packageName, OnEmitShortcutListener listener) {
        return createShortcut(userId, packageName, null, listener);
    }

    public boolean createShortcut(int userId, String packageName, Intent splash, OnEmitShortcutListener listener) {
        InstalledAppInfo setting = getInstalledAppInfo(packageName, 0);
        if (setting == null) {
            return false;
        }
        ApplicationInfo appInfo = setting.getApplicationInfo(userId);
        PackageManager pm = context.getPackageManager();
        String name;
        Bitmap icon;
        try {
            CharSequence sequence = appInfo.loadLabel(pm);
            name = sequence.toString();
            icon = BitmapUtils.drawableToBitmap(appInfo.loadIcon(pm));
        } catch (Throwable e) {
            return false;
        }
        if (listener != null) {
            String newName = listener.getName(name);
            if (newName != null) {
                name = newName;
            }
            Bitmap newIcon = listener.getIcon(icon);
            if (newIcon != null) {
                icon = newIcon;
            }
        }
        Intent targetIntent = getLaunchIntent(packageName, userId);
        if (targetIntent == null) {
            return false;
        }
        Intent shortcutIntent = wrapperShortcutIntent(targetIntent, splash, packageName, userId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ShortcutInfo likeShortcut;
            likeShortcut = new ShortcutInfo.Builder(getContext(), packageName + "@" + userId)
                    .setLongLabel(name)
                    .setShortLabel(name)
                    .setIcon(Icon.createWithBitmap(icon))
                    .setIntent(shortcutIntent)
                    .build();
            ShortcutManager shortcutManager = getContext().getSystemService(ShortcutManager.class);
            if (shortcutManager != null) {
                try {
                    shortcutManager.requestPinShortcut(likeShortcut,
                            PendingIntent.getActivity(getContext(), packageName.hashCode() + userId, shortcutIntent,
                                    android.os.Build.VERSION.SDK_INT >= 31
                                            ? PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
                                            : PendingIntent.FLAG_UPDATE_CURRENT).getIntentSender());
                } catch (Throwable e) {
                    return false;
                }
            }
        } else {
            Intent addIntent = new Intent();
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, BitmapUtils.warrperIcon(icon, 256, 256));
            addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            try {
                context.sendBroadcast(addIntent);
            } catch (Throwable e) {
                return false;
            }
        }
        return true;
    }

    public boolean removeShortcut(int userId, String packageName, Intent splash, OnEmitShortcutListener listener) {
        InstalledAppInfo setting = getInstalledAppInfo(packageName, 0);
        if (setting == null) {
            return false;
        }
        ApplicationInfo appInfo = setting.getApplicationInfo(userId);
        PackageManager pm = context.getPackageManager();
        String name;
        try {
            CharSequence sequence = appInfo.loadLabel(pm);
            name = sequence.toString();
        } catch (Throwable e) {
            return false;
        }
        if (listener != null) {
            String newName = listener.getName(name);
            if (newName != null) {
                name = newName;
            }
        }
        Intent targetIntent = getLaunchIntent(packageName, userId);
        if (targetIntent == null) {
            return false;
        }
        Intent shortcutIntent = wrapperShortcutIntent(targetIntent, splash, packageName, userId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        } else {
            Intent addIntent = new Intent();
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
            addIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
            context.sendBroadcast(addIntent);
        }
        return true;
    }

    /**
     * @param intent target activity
     * @param splash loading activity
     * @param userId userId
     */
    public Intent wrapperShortcutIntent(Intent intent, Intent splash, String packageName, int userId) {
        Intent shortcutIntent = new Intent();
        shortcutIntent.addCategory(Intent.CATEGORY_DEFAULT);
        shortcutIntent.setAction(Constants.ACTION_SHORTCUT);
        shortcutIntent.setPackage(getHostPkg());
        if (splash != null) {
            shortcutIntent.putExtra("_VA_|_splash_", splash.toUri(0));
        }
        shortcutIntent.putExtra("_VA_|_pkg_", packageName);
        shortcutIntent.putExtra("_VA_|_uri_", intent.toUri(0));
        shortcutIntent.putExtra("_VA_|_user_id_", userId);
        return shortcutIntent;
    }

    public abstract static class UiCallback extends IUiCallback.Stub {

    }

    public void setUiCallback(Intent intent, IUiCallback callback) {
        if (callback != null) {
            Bundle bundle = new Bundle();
            BundleCompat.putBinder(bundle, "_VA_|_ui_callback_", callback.asBinder());
            intent.putExtra("_VA_|_sender_", bundle);
        }
    }

    public abstract static class VSCallback extends IVSCallback.Stub {
    }

    public abstract static class AppPermissionCallback extends IAppPermissionCallback.Stub {
    }
    public abstract static class NotificationCallback extends INotificationCallback.Stub {
    }

    public abstract static class ControllerServiceCallback extends IControllerServiceCallback.Stub{

    }

    public abstract static class VSKeyCallback extends IVSKeyCallback.Stub {

    }

    public abstract static class ToastCallback extends IToastCallback.Stub {

    }

    public InstalledAppInfo getInstalledAppInfo(String pkg, int flags) {
        try {
            return getService().getInstalledAppInfo(pkg, flags);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public int getInstalledAppCount() {
        try {
            return getService().getInstalledAppCount();
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public boolean isStartup() {
        return isStartUp;
    }

    public boolean uninstallPackageAsUser(String pkgName, int userId) {
        try {
            if(PackagePermissionManager.getProtectUninstallList().contains(pkgName))
                return false;
            return getService().uninstallPackageAsUser(pkgName, userId);
        } catch (RemoteException e) {
            // Ignore
        }
        return false;
    }

    public boolean uninstallPackage(String pkgName) {
        try {
            if(PackagePermissionManager.getProtectUninstallList().contains(pkgName))
                return false;
            return getService().uninstallPackage(pkgName);
        } catch (RemoteException e) {
            // Ignore
        }
        return false;
    }

    public Resources getResources(String pkg) throws Resources.NotFoundException {
        InstalledAppInfo installedAppInfo = getInstalledAppInfo(pkg, 0);
        if (installedAppInfo != null) {
            AssetManager assets = mirror.android.content.res.AssetManager.ctor.newInstance();
            mirror.android.content.res.AssetManager.addAssetPath.call(assets, installedAppInfo.getApkPath());
            Resources hostRes = context.getResources();
            return new Resources(assets, hostRes.getDisplayMetrics(), hostRes.getConfiguration());
        }
        throw new Resources.NotFoundException(pkg);
    }

    public synchronized ActivityInfo resolveActivityInfo(Intent intent, int userId) {
        if (intent == null) {
            return null;
        }
        android.util.Log.e(TAG, "resolveActivityInfo: intent=" + intent + ", user=" + userId);
        if (SpecialComponentList.shouldBlockIntent(intent)) {
            android.util.Log.e(TAG, "resolveActivityInfo: intent blocked");
            return null;
        }
        ActivityInfo activityInfo = null;
        if (intent.getComponent() == null) {
            ResolveInfo resolveInfo = VPackageManager.get().resolveIntent(intent, intent.getType(), 0, userId);
            if (resolveInfo != null && resolveInfo.activityInfo != null) {
                activityInfo = resolveInfo.activityInfo;
                android.util.Log.e(TAG, "resolveActivityInfo: resolved to " + activityInfo.name + " in " + activityInfo.packageName);
                intent.setClassName(activityInfo.packageName, activityInfo.name);
            } else {
                android.util.Log.e(TAG, "resolveActivityInfo: internal resolution failed, trying host PM fallback");
                activityInfo = resolveActivityInfoViaHostIntent(intent, userId);
            }
        } else {
            android.util.Log.e(TAG, "resolveActivityInfo: explicit component=" + intent.getComponent());
            activityInfo = resolveActivityInfo(intent.getComponent(), userId);
            if (activityInfo == null) {
                android.util.Log.e(TAG, "resolveActivityInfo: component resolution failed, trying alias");
                activityInfo = resolveAliasActivityInfo(intent, userId);
            }
        }
        android.util.Log.e(TAG, "resolveActivityInfo result: " + (activityInfo != null ? activityInfo.name : "null"));
        return activityInfo;
    }

    private ActivityInfo resolveActivityInfoViaHostIntent(Intent intent, int userId) {
        if (intent == null) {
            return null;
        }
        android.util.Log.e(TAG, "resolveActivityInfoViaHostIntent: intent=" + intent);
        try {
            ResolveInfo hostResolveInfo = getUnHookPackageManager().resolveActivity(intent,
                    PackageManager.MATCH_DEFAULT_ONLY | PackageManager.GET_META_DATA);
            if (hostResolveInfo == null || hostResolveInfo.activityInfo == null) {
                android.util.Log.e(TAG, "resolveActivityInfoViaHostIntent: host PM resolve failed");
                return null;
            }
            String resolvedPackage = hostResolveInfo.activityInfo.packageName;
            android.util.Log.e(TAG, "resolveActivityInfoViaHostIntent: host PM resolved package=" + resolvedPackage);
            InstalledAppInfo installedAppInfo = getInstalledAppInfo(resolvedPackage, 0);
            if (installedAppInfo == null) {
                android.util.Log.e(TAG, "resolveActivityInfoViaHostIntent: resolved package not installed in VA");
                return null;
            }
            ComponentName resolvedComponent = new ComponentName(resolvedPackage,
                    hostResolveInfo.activityInfo.name);
            ActivityInfo activityInfo = resolveActivityInfo(resolvedComponent, userId);
            if (activityInfo != null) {
                intent.setComponent(resolvedComponent);
                intent.setPackage(activityInfo.packageName);
                android.util.Log.e(TAG, "Resolved activity via host PM deep-link fallback intent=" + intent + " component=" + resolvedComponent + " user=" + userId);
            } else {
                android.util.Log.e(TAG, "resolveActivityInfoViaHostIntent: final resolveActivityInfo failed for component");
            }
            return activityInfo;
        } catch (Throwable e) {
            android.util.Log.e(TAG, "Host PM fallback failed for intent=" + intent + " user=" + userId + " error=" + e);
            return null;
        }
    }


    public ActivityInfo resolveActivityInfo(ComponentName componentName, int userId) {
        ActivityInfo activityInfo = VPackageManager.get().getActivityInfo(componentName, 0, userId);
        if (activityInfo != null) {
            return activityInfo;
        }
        return resolveAliasActivityInfo(componentName, userId);
    }

    private ActivityInfo resolveAliasActivityInfo(Intent intent, int userId) {
        if (intent == null || intent.getComponent() == null) {
            return null;
        }
        ComponentName aliasComponent = intent.getComponent();
        ActivityInfo activityInfo = resolveAliasActivityInfo(aliasComponent, userId);
        if (activityInfo != null) {
            ComponentName targetComponent = new ComponentName(activityInfo.packageName, activityInfo.name);
            intent.setPackage(null);
            intent.setComponent(targetComponent);
        }
        return activityInfo;
    }

    private ActivityInfo resolveAliasActivityInfo(ComponentName aliasComponent, int userId) {
        try {
            ActivityInfo hostActivityInfo = getUnHookPackageManager().getActivityInfo(aliasComponent, PackageManager.GET_META_DATA);
            if (hostActivityInfo == null || TextUtils.isEmpty(hostActivityInfo.targetActivity)) {
                return null;
            }

            String targetActivityName = hostActivityInfo.targetActivity;
            if (targetActivityName.charAt(0) == '.') {
                targetActivityName = aliasComponent.getPackageName() + targetActivityName;
            }

            ComponentName targetComponent = new ComponentName(aliasComponent.getPackageName(), targetActivityName);
            ActivityInfo targetActivityInfo = VPackageManager.get().getActivityInfo(targetComponent, 0, userId);
            if (targetActivityInfo != null) {
                VLog.w("VirtualCore", "Resolved launcher alias %s -> %s for user=%d", aliasComponent, targetComponent, userId);
                return targetActivityInfo;
            }

            VLog.w("VirtualCore", "Alias target not resolvable in virtual PM alias=%s target=%s user=%d", aliasComponent, targetComponent, userId);
        } catch (PackageManager.NameNotFoundException ignored) {
            // Not a host-visible alias component.
        }
        return null;
    }

    public ServiceInfo resolveServiceInfo(Intent intent, int userId) {
        if (SpecialComponentList.shouldBlockIntent(intent)) {
            return null;
        }
        if (intent == null) {
            return null;
        }
        ServiceInfo serviceInfo = null;
        ComponentName component = intent.getComponent();
        if (component != null) {
            serviceInfo = VPackageManager.get().getServiceInfo(component, 0, userId);
            if (serviceInfo != null) {
                VLog.w("VirtualCore", "Resolved explicit service component %s for user=%d", component, userId);
                return serviceInfo;
            }
        }
        ResolveInfo resolveInfo = VPackageManager.get().resolveService(intent, intent.getType(), 0, userId);
        if (resolveInfo != null) {
            serviceInfo = resolveInfo.serviceInfo;
            if (serviceInfo != null && intent.getComponent() == null) {
                intent.setClassName(serviceInfo.packageName, serviceInfo.name);
            }
        }
        return serviceInfo;
    }

    public void killApp(String pkg, int userId) {
        VActivityManager.get().killAppByPkg(pkg, userId);
    }

    public void killAllApps() {
        VActivityManager.get().killAllApps();
    }

    public List<InstalledAppInfo> getInstalledApps(int flags) {
        try {
            IAppManager service = getService();
            if (service == null) {
                waitForEngine();
                service = getService();
            }
            if (service == null) {
                Log.w(TAG, "getInstalledApps: app manager unavailable, returning empty list");
                return Collections.emptyList();
            }
            return service.getInstalledApps(flags);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public List<InstalledAppInfo> getInstalledAppsAsUser(int userId, int flags) {
        try {
            IAppManager service = getService();
            if (service == null) {
                waitForEngine();
                service = getService();
            }
            if (service == null) {
                Log.w(TAG, "getInstalledAppsAsUser: app manager unavailable for user " + userId + ", returning empty list");
                return Collections.emptyList();
            }
            return service.getInstalledAppsAsUser(userId, flags);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public void scanApps() {
        try {
            IAppManager service = getService();
            if (service == null) {
                waitForEngine();
                service = getService();
            }
            if (service == null) {
                Log.w(TAG, "scanApps: app manager unavailable, skipping scan");
                return;
            }
            service.scanApps();
        } catch (RemoteException e) {
            // Ignore
        }
    }

    public AppRequestListener getAppRequestListener() {
        return mAppRequestListener;
    }

    public void setAppRequestListener(final AppRequestListener listener) {
        this.mAppRequestListener = listener;
    }

    public boolean isPackageLaunched(int userId, String packageName) {
        try {
            return getService().isPackageLaunched(userId, packageName);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public void setPackageHidden(int userId, String packageName, boolean hidden) {
        try {
            getService().setPackageHidden(userId, packageName, hidden);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean installPackageAsUser(int userId, String packageName) {
        try {
            return getService().installPackageAsUser(userId, packageName);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public boolean isAppInstalledAsUser(int userId, String packageName) {
        try {
            return getService().isAppInstalledAsUser(userId, packageName);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public int[] getPackageInstalledUsers(String packageName) {
        try {
            return getService().getPackageInstalledUsers(packageName);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public abstract static class PackageObserver extends IPackageObserver.Stub {
    }

    public void registerObserver(IPackageObserver observer) {
        try {
            getService().registerObserver(observer);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void unregisterObserver(IPackageObserver observer) {
        try {
            getService().unregisterObserver(observer);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public boolean isOutsideInstalled(String packageName) {
        if (packageName == null) {
            return false;
        }
        try {
            return unHookPackageManager.getApplicationInfo(packageName, 0) != null;
        } catch (PackageManager.NameNotFoundException e) {
            // Ignore
        }
        return false;
    }

    public boolean is64BitEngineInstalled() {
        return isOutsideInstalled(StubManifest.PACKAGE_NAME_64BIT);
    }

    public boolean isRun64BitProcess(String packageName) {
        try {
            return getService().isRun64BitProcess(packageName);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public boolean cleanPackageData(String pkg, int userId) {
        try {
            return getService().cleanPackageData(pkg, userId);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public void startForeground(){
        if(VirtualCore.getConfig().isHideForegroundNotification()){
            Intent foregroundIntent = new Intent(context, HiddenForeNotification.class);
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                        && VirtualCore.get().getTargetSdkVersion() >= Build.VERSION_CODES.O) {
                    context.startForegroundService(foregroundIntent);
                } else {
                    context.startService(foregroundIntent);
                }
            } catch (SecurityException | IllegalStateException e) {
                VLog.w(TAG, "Unable to start HiddenForeNotification, continuing without hidden foreground service: %s", e.getMessage());
            } catch (RuntimeException e) {
                VLog.w(TAG, "HiddenForeNotification startup failed, continuing without hidden foreground service", e);
            }
        }
    }

    public void stopForeground() {
        if (VirtualCore.getConfig().isHideForegroundNotification()) {
            try {
                context.stopService(new Intent(context, HiddenForeNotification.class));
            } catch (RuntimeException e) {
                VLog.w(TAG, "HiddenForeNotification stop failed: %s", e.getMessage());
            }
        }
    }

    public abstract static class Receiver extends BroadcastReceiver{
        @Override
        public final void onReceive(Context context, Intent intent) {
            Bundle extraData = intent.getExtras();
            BroadcastIntentData intentData = null;
            if (extraData != null) {
                extraData.setClassLoader(BroadcastIntentData.class.getClassLoader());
                intentData = extraData.getParcelable("_VA_|_data_");
            }
            int userId;
            if (intentData != null) {
                //内部广播，或者服务进程广播
                intent = intentData.intent;
                userId = intentData.userId;
            } else {
                //系统广播
                userId = VUserHandle.USER_ALL;
                SpecialComponentList.unprotectIntent(intent);
            }
            onReceive(context, intent, userId);
        }

        public abstract void onReceive(Context context, Intent intent, int userId);
    }

    /**
     *
     * @param context 直接用这个context进行unregisterReceiver
     * @param receiver intent的内容会自动处理，和原生一样
     * @param filter 自动处理
     */
    public void registerReceiver(Context context, Receiver receiver, IntentFilter filter){
        SpecialComponentList.protectIntentFilter(filter, true);
        ReceiverCompat.registerReceiver(context, receiver, filter, true);
    }

    /**
     * Process type
     */
    private enum ProcessType {
        /**
         * Server process
         */
        Server,
        /**
         * Virtual app process
         */
        VAppClient,
        /**
         * Main process
         */
        Main,
        /**
         * Helper process
         */
        Helper,
        /**
         * Child process
         */
        CHILD
    }

    public interface AppRequestListener {
        void onRequestInstall(String path);

        void onRequestUninstall(String pkg);
    }

    public interface OnEmitShortcutListener {
        Bitmap getIcon(Bitmap originIcon);

        String getName(String originName);
    }

    public static abstract class VirtualInitializer {
        public void onMainProcess() {
        }

        public void onVirtualProcess() {
        }

        public void onServerProcess() {
        }

        public void onChildProcess() {
        }

    }
}
