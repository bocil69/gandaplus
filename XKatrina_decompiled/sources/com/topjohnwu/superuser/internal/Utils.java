package com.topjohnwu.superuser.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Process;
import android.util.ArraySet;
import android.util.Log;
import androidx.annotation.RestrictTo;
import com.topjohnwu.superuser.Shell;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public final class Utils {
    private static final String TAG = "LIBSU";
    @SuppressLint({"StaticFieldLeak"})
    public static Context context = null;
    private static int currentRootState = -1;
    private static Class<?> synchronizedCollectionClass;

    public static void log(Object obj) {
        log(TAG, obj);
    }

    public static void log(String str, Object obj) {
        if (vLog()) {
            Log.d(str, obj.toString());
        }
    }

    public static void ex(Throwable th) {
        if (vLog()) {
            Log.d(TAG, "", th);
        }
    }

    public static void err(Throwable th) {
        err(TAG, th);
    }

    public static void err(String str, Throwable th) {
        Log.d(str, "", th);
    }

    public static boolean vLog() {
        return Shell.enableVerboseLogging;
    }

    public static void setContext(Context context2) {
        Context contextImpl = getContextImpl(context2);
        Context applicationContext = contextImpl.getApplicationContext();
        if (applicationContext != null) {
            contextImpl = applicationContext;
        }
        context = getContextImpl(contextImpl);
    }

    @SuppressLint({"PrivateApi"})
    public static Context getContext() {
        if (context == null) {
            try {
                context = getContextImpl((Context) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, new Object[0]));
            } catch (Exception e) {
                err(e);
            }
        }
        return context;
    }

    public static Context getDeContext() {
        Context context2 = getContext();
        return Build.VERSION.SDK_INT >= 24 ? context2.createDeviceProtectedStorageContext() : context2;
    }

    public static Context getContextImpl(Context context2) {
        while (context2 instanceof ContextWrapper) {
            context2 = ((ContextWrapper) context2).getBaseContext();
        }
        return context2;
    }

    public static boolean hasStartupAgents(Context context2) {
        if (Build.VERSION.SDK_INT < 30) {
            return false;
        }
        return new File(context2.getCodeCacheDir(), "startup_agents").isDirectory();
    }

    public static boolean isSynchronized(Collection<?> collection) {
        if (synchronizedCollectionClass == null) {
            synchronizedCollectionClass = Collections.synchronizedCollection(NOPList.getInstance()).getClass();
        }
        return synchronizedCollectionClass.isInstance(collection);
    }

    public static long pump(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[65536];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += read;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> Set<E> newArraySet() {
        if (Build.VERSION.SDK_INT >= 23) {
            return new ArraySet();
        }
        return new HashSet();
    }

    public static synchronized Boolean isAppGrantedRoot() {
        synchronized (Utils.class) {
            int i = currentRootState;
            if (i < 0) {
                if (Process.myUid() == 0) {
                    currentRootState = 2;
                    return true;
                } else {
                    for (String str : System.getenv("PATH").split(":")) {
                        if (new File(str, "su").canExecute()) {
                            currentRootState = 1;
                            return null;
                        }
                    }
                    currentRootState = 0;
                    return false;
                }
            }
            if (i != 0) {
                return i != 2 ? null : true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void setConfirmedRootState(boolean z) {
        synchronized (Utils.class) {
            currentRootState = z ? 2 : 0;
        }
    }

    public static boolean isRootImpossible() {
        return Utils$$ExternalSyntheticBackport0.m(isAppGrantedRoot(), Boolean.FALSE);
    }

    public static boolean isMainShellRoot() {
        return MainShell.get().isRoot();
    }

    @SuppressLint({"DiscouragedPrivateApi"})
    public static boolean isProcess64Bit() {
        if (Build.VERSION.SDK_INT >= 23) {
            return Process.is64Bit();
        }
        if (Build.VERSION.SDK_INT < 21) {
            return false;
        }
        try {
            Class<?> cls = Class.forName("dalvik.system.VMRuntime");
            Method declaredMethod = cls.getDeclaredMethod("getRuntime", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            Method declaredMethod2 = cls.getDeclaredMethod("is64Bit", new Class[0]);
            declaredMethod2.setAccessible(true);
            return ((Boolean) declaredMethod2.invoke(invoke, new Object[0])).booleanValue();
        } catch (ReflectiveOperationException e) {
            err(e);
            return false;
        }
    }
}
