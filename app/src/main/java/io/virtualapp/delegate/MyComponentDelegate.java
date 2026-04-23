package io.virtualapp.delegate;

import android.app.Application;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.ContentProvider;
import android.util.Log;

import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.AppCallback;
import com.lody.virtual.client.core.VirtualCore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class MyComponentDelegate implements AppCallback {

    private static final String TAG = "GandaPlus.RootHide";
    private static final String[] SENSITIVE_FILE_PATHS = {
            "/proc/self/maps",
            "/proc/cpuinfo",
            "/sys/devices/system/cpu/"
    };
    private static final String[] HOST_REFLECTION_CLASSES = {
            "android.app.ActivityThread"
    };

    /**
     * Paths that indicate root on the HOST that must never be visible inside VA.
     */
    private static final String[] ROOT_PATHS = {
        "/su", "/sbin/su", "/system/bin/su", "/system/xbin/su",
        "/system/xbin/busybox", "/system/bin/busybox",
        "/data/local/su", "/data/local/xbin/su", "/data/local/bin/su",
        "/system/sd/xbin/su", "/system/bin/.ext/.su",
        "/system/usr/we-need-root/su-backup",
        "/sbin/.magisk", "/data/adb/magisk",
        "/cache/.disable_magisk", "/sbin/.core/mirror",
        "/data/adb/ksu", "/data/adb/ksud",
        "/system/lib/libsupol.so", "/system/lib64/libsupol.so",
    };

    private static boolean sRootHideInjected = false;

    static boolean markRootHideInjectedIfNeeded() {
        if (sRootHideInjected) {
            return false;
        }
        sRootHideInjected = true;
        return true;
    }

    static void resetRootHideInjectedForTests() {
        sRootHideInjected = false;
    }

    @Override
    public void beforeStartApplication(String packageName, String processName, Context context) {
        if (context == null) return;

        if ("travel.eskimo.esim".equals(packageName)) {
            ensureEskimoFacebookSdkInitialized(context, context.getClassLoader(), packageName, "beforeStartApplication");
        }

        injectNetworkHooks(packageName, processName, context);
        
        if (markRootHideInjectedIfNeeded()) {
            injectRootHide();
            injectLeakGuards();
        }
    }

    /**
     * Hook File.exists() and File.canExecute() so that any root-related path
     * always returns false inside the virtual process.
     */
    void injectNetworkHooks(String packageName, String processName, Context context) {
        io.virtualapp.hook.network.NetworkSpoofManager.inject(packageName, processName, context);
    }

    void injectRootHide() {
        try {
            XposedHelpers.findAndHookMethod(File.class, "exists", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    File f = (File) param.thisObject;
                    if (f != null) {
                        String path = f.getAbsolutePath();
                        if (path != null && isRootPath(path)) {
                            param.setResult(false);
                        }
                    }
                }
            });
        } catch (Throwable e) {
            Log.w(TAG, "File.exists hook failed: " + e.getMessage());
        }

        try {
            XposedHelpers.findAndHookMethod(File.class, "canExecute", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    File f = (File) param.thisObject;
                    if (f != null) {
                        String path = f.getAbsolutePath();
                        if (path != null && isRootPath(path)) {
                            param.setResult(false);
                        }
                    }
                }
            });
        } catch (Throwable e) {
            Log.w(TAG, "File.canExecute hook failed: " + e.getMessage());
        }

        // Hook Runtime.exec to block su execution
        try {
            XposedHelpers.findAndHookMethod(Runtime.class, "exec",
                    String[].class, String[].class, File.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) {
                    String[] cmds = (String[]) param.args[0];
                    if (cmds != null) {
                        for (String cmd : cmds) {
                            if (cmd != null && (cmd.endsWith("/su") || cmd.equals("su"))) {
                                param.setThrowable(new SecurityException("su blocked by GandaPlus"));
                                return;
                            }
                        }
                    }
                }
            });
        } catch (Throwable e) {
            Log.w(TAG, "Runtime.exec hook failed: " + e.getMessage());
        }

        // Block Build.TAGS leaking "test-keys" (used as root indicator)
        try {
            XposedHelpers.setStaticObjectField(android.os.Build.class, "TAGS", "release-keys");
        } catch (Throwable e) {
            Log.w(TAG, "Build.TAGS spoof failed: " + e.getMessage());
        }
    }

    void injectLeakGuards() {
        hookJavaFileOpen();
        hookReflectionLeak();
    }

    private void hookJavaFileOpen() {
        try {
            XposedHelpers.findAndHookConstructor(FileInputStream.class, String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    String path = (String) param.args[0];
                    if (shouldBlockPath(path)) {
                        param.setThrowable(new FileNotFoundException("blocked by virtual guard"));
                    }
                }
            });
        } catch (Throwable e) {
            Log.w(TAG, "FileInputStream(String) hook failed: " + e.getMessage());
        }

        try {
            XposedHelpers.findAndHookConstructor(FileInputStream.class, File.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    File file = (File) param.args[0];
                    if (file != null && shouldBlockPath(file.getAbsolutePath())) {
                        param.setThrowable(new FileNotFoundException("blocked by virtual guard"));
                    }
                }
            });
        } catch (Throwable e) {
            Log.w(TAG, "FileInputStream(File) hook failed: " + e.getMessage());
        }

        try {
            XposedHelpers.findAndHookConstructor(RandomAccessFile.class, String.class, String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    String path = (String) param.args[0];
                    if (shouldBlockPath(path)) {
                        param.setThrowable(new FileNotFoundException("blocked by virtual guard"));
                    }
                }
            });
        } catch (Throwable e) {
            Log.w(TAG, "RandomAccessFile(String) hook failed: " + e.getMessage());
        }

        try {
            XposedHelpers.findAndHookConstructor(RandomAccessFile.class, File.class, String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    File file = (File) param.args[0];
                    if (file != null && shouldBlockPath(file.getAbsolutePath())) {
                        param.setThrowable(new FileNotFoundException("blocked by virtual guard"));
                    }
                }
            });
        } catch (Throwable e) {
            Log.w(TAG, "RandomAccessFile(File) hook failed: " + e.getMessage());
        }
    }

    private void hookReflectionLeak() {
        try {
            XposedHelpers.findAndHookMethod(Class.class, "forName", String.class, boolean.class, ClassLoader.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    String className = (String) param.args[0];
                    if (shouldBlockClassLookup(className)) {
                        param.setThrowable(new ClassNotFoundException(className));
                    }
                }
            });
        } catch (Throwable e) {
            Log.w(TAG, "Class.forName hook failed: " + e.getMessage());
        }

        try {
            XposedHelpers.findAndHookMethod(Class.class, "getDeclaredMethod", String.class, Class[].class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Class<?> targetClass = (Class<?>) param.thisObject;
                    String methodName = (String) param.args[0];
                    if (shouldBlockDeclaredMethod(targetClass, methodName)) {
                        param.setThrowable(new NoSuchMethodException(methodName));
                    }
                }
            });
        } catch (Throwable e) {
            Log.w(TAG, "getDeclaredMethod hook failed: " + e.getMessage());
        }

    }

    private boolean shouldBlockPath(String path) {
        if (path == null || !isProtectedTargetProcess()) {
            return false;
        }
        if (isRootPath(path)) {
            return true;
        }
        for (String sensitivePath : SENSITIVE_FILE_PATHS) {
            if (path.equals(sensitivePath) || path.startsWith(sensitivePath)) {
                return true;
            }
        }
        String hostPkg = VirtualCore.get().getHostPkg();
        String hostDataPrefix = "/data/data/" + hostPkg;
        String hostUserPrefix = "/data/user/0/" + hostPkg;
        String hostVirtualDataPrefix = hostDataPrefix + "/virtual/";
        String hostVirtualUserPrefix = hostUserPrefix + "/virtual/";
        if (path.startsWith(hostVirtualDataPrefix) || path.startsWith(hostVirtualUserPrefix)) {
            return false;
        }
        return path.startsWith(hostDataPrefix + "/")
                || path.equals(hostDataPrefix)
                || path.startsWith(hostUserPrefix + "/")
                || path.equals(hostUserPrefix);
    }

    private boolean shouldBlockClassLookup(String className) {
        if (!isProtectedTargetProcess() || className == null) {
            return false;
        }
        if (isVirtualRuntimeReflectionCall()) {
            return false;
        }
        for (String blocked : HOST_REFLECTION_CLASSES) {
            if (blocked.equals(className)) {
                return true;
            }
        }
        return false;
    }

    private boolean shouldBlockDeclaredMethod(Class<?> targetClass, String methodName) {
        if (!isProtectedTargetProcess() || targetClass == null || methodName == null) {
            return false;
        }
        if (isVirtualRuntimeReflectionCall()) {
            return false;
        }
        String className = targetClass.getName();
        if ("android.app.ActivityThread".equals(className)) {
            return "currentActivityThread".equals(methodName)
                    || "currentApplication".equals(methodName)
                    || "getSystemContext".equals(methodName);
        }
        return false;
    }

    private boolean isVirtualRuntimeReflectionCall() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            String className = element.getClassName();
            if (className == null) {
                continue;
            }
            if (className.startsWith("mirror.")
                    || className.startsWith("com.lody.virtual.")
                    || className.startsWith("io.virtualapp.delegate.")) {
                return true;
            }
        }
        return false;
    }

    private boolean isProtectedTargetProcess() {
        String pkg = VClient.get().getCurrentPackage();
        if (pkg == null) {
            return false;
        }
        return pkg.equals("com.grabtaxi.passenger")
                || pkg.equals("com.grab.driver")
                || pkg.equals("ovo.id")
                || pkg.equals("travel.eskimo.esim");
    }

    private boolean isRootPath(String path) {
        if (path == null) return false;
        for (String r : ROOT_PATHS) {
            if (path.equals(r) || path.startsWith(r + "/")) return true;
        }
        return false;
    }

    @Override
    public void beforeApplicationCreate(String packageName, String processName, Application application) {
        if (application == null || !"travel.eskimo.esim".equals(packageName)) {
            return;
        }
        ensureEskimoFacebookSdkInitialized(application, application.getClassLoader(), packageName, "beforeApplicationCreate");
    }

    @Override
    public void afterApplicationCreate(String packageName, String processName, Application application) {
    }

    private void ensureEskimoFacebookSdkInitialized(Context context, ClassLoader classLoader,
                                                    String packageName, String stage) {
        try {
            if (context == null || classLoader == null) {
                return;
            }
            triggerEskimoFacebookInitProvider(context, classLoader, packageName);
            Class<?> facebookSdkClass = Class.forName("com.facebook.FacebookSdk", false, classLoader);
            if (isFacebookSdkInitialized(facebookSdkClass)) {
                Log.i(TAG, "Eskimo FacebookSdk already initialized during " + stage);
                return;
            }

            invokeBooleanSetterIfPresent(facebookSdkClass, "setAutoInitEnabled", true);
            invokeBooleanSetterIfPresent(facebookSdkClass, "setAutoLogAppEventsEnabled", true);
            invokeContextInitializerIfPresent(facebookSdkClass, context);
            invokeNoArgIfPresent(facebookSdkClass, "fullyInitialize");

            if (isFacebookSdkInitialized(facebookSdkClass)) {
                Log.i(TAG, "Eskimo FacebookSdk initialized via delegate hook during " + stage);
            } else {
                Log.w(TAG, "Eskimo FacebookSdk init hook ran during " + stage + " but SDK still reports uninitialized");
            }
        } catch (ClassNotFoundException e) {
            Log.w(TAG, "Eskimo FacebookSdk class not found in target app during " + stage);
        } catch (Throwable e) {
            Log.w(TAG, "Eskimo FacebookSdk init hook failed during " + stage + ": " + e.getMessage());
        }
    }

    private void triggerEskimoFacebookInitProvider(Context context, ClassLoader classLoader, String packageName) {
        if (context == null || classLoader == null) {
            return;
        }
        try {
            Class<?> providerClass = Class.forName("com.facebook.internal.FacebookInitProvider", false, classLoader);
            Object provider = providerClass.getDeclaredConstructor().newInstance();
            if (!(provider instanceof ContentProvider)) {
                Log.w(TAG, "Eskimo FacebookInitProvider class is not a ContentProvider");
                return;
            }
            ProviderInfo info = new ProviderInfo();
            info.authority = packageName + ".FacebookInitProvider";
            info.packageName = packageName;
            info.exported = false;
            ((ContentProvider) provider).attachInfo(context, info);
            Log.i(TAG, "Eskimo FacebookInitProvider bootstrapped in-process");
        } catch (Throwable e) {
            Log.w(TAG, "Eskimo FacebookInitProvider bootstrap failed: " + e.getMessage());
        }
    }

    private boolean isFacebookSdkInitialized(Class<?> facebookSdkClass) {
        try {
            Method isInitialized = facebookSdkClass.getMethod("isInitialized");
            Object result = isInitialized.invoke(null);
            return result instanceof Boolean && (Boolean) result;
        } catch (Throwable ignored) {
            return false;
        }
    }

    private void invokeBooleanSetterIfPresent(Class<?> targetClass, String methodName, boolean value) {
        try {
            Method method = targetClass.getMethod(methodName, boolean.class);
            method.invoke(null, value);
        } catch (Throwable ignored) {
        }
    }

    private void invokeContextInitializerIfPresent(Class<?> facebookSdkClass, Context context) {
        try {
            Method method = facebookSdkClass.getMethod("sdkInitialize", Context.class);
            method.invoke(null, context);
        } catch (NoSuchMethodException e) {
            try {
                Method method = facebookSdkClass.getMethod("sdkInitialize", Context.class, int.class);
                method.invoke(null, context, 0);
            } catch (Throwable ignored) {
            }
        } catch (Throwable ignored) {
        }
    }

    private void invokeNoArgIfPresent(Class<?> targetClass, String methodName) {
        try {
            Method method = targetClass.getMethod(methodName);
            method.invoke(null);
        } catch (Throwable ignored) {
        }
    }
}
