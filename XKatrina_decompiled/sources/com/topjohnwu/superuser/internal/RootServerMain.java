package com.topjohnwu.superuser.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
/* loaded from: classes2.dex */
class RootServerMain extends ContextWrapper implements Callable<Object[]> {
    static final String CMDLINE_START_DAEMON = "daemon";
    static final String CMDLINE_START_SERVICE = "start";
    static final String CMDLINE_STOP_SERVICE = "stop";
    private static final Method attachBaseContext;
    private static final Method getService;
    private final boolean isDaemon;
    private final int uid;

    static {
        try {
            getService = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", String.class);
            Method declaredMethod = ContextWrapper.class.getDeclaredMethod("attachBaseContext", Context.class);
            attachBaseContext = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressLint({"PrivateApi"})
    static Context getSystemContext() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            return (Context) cls.getMethod("getSystemContext", new Class[0]).invoke(cls.getMethod("systemMain", new Class[0]).invoke(null, new Object[0]), new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getServiceName(String str) {
        return "libsu-" + str;
    }

    public static void main(String[] strArr) {
        System.out.close();
        System.err.close();
        if (strArr.length < 3) {
            System.exit(1);
        }
        Looper.prepareMainLooper();
        try {
            new RootServerMain(strArr);
        } catch (Exception e) {
            Log.e("IPC", "Error in IPCMain", e);
            System.exit(1);
        }
        Looper.loop();
        System.exit(1);
    }

    @Override // java.util.concurrent.Callable
    public Object[] call() {
        return new Object[]{Integer.valueOf(this.uid), Boolean.valueOf(this.isDaemon)};
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0084, code lost:
        if (r15 != false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008f, code lost:
        if (r15 == false) goto L19;
     */
    @android.annotation.SuppressLint({"DiscouragedPrivateApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public RootServerMain(java.lang.String[] r15) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.topjohnwu.superuser.internal.RootServerMain.<init>(java.lang.String[]):void");
    }

    /* loaded from: classes2.dex */
    static class ResourcesWrapper extends Resources {
        @SuppressLint({"DiscouragedPrivateApi"})
        public ResourcesWrapper(Resources resources) throws ReflectiveOperationException {
            super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
            Method declaredMethod = Resources.class.getDeclaredMethod("getImpl", new Class[0]);
            declaredMethod.setAccessible(true);
            Method declaredMethod2 = Resources.class.getDeclaredMethod("setImpl", declaredMethod.getReturnType());
            declaredMethod2.setAccessible(true);
            declaredMethod2.invoke(this, declaredMethod.invoke(resources, new Object[0]));
        }

        @Override // android.content.res.Resources
        public boolean getBoolean(int i) {
            try {
                return super.getBoolean(i);
            } catch (Resources.NotFoundException unused) {
                return false;
            }
        }
    }
}
