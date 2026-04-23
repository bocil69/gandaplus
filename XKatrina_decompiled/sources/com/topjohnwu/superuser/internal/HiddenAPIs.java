package com.topjohnwu.superuser.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.IBinder;
import java.lang.reflect.Method;
@SuppressLint({"PrivateApi,DiscouragedPrivateApi,SoonBlockedPrivateApi"})
/* loaded from: classes2.dex */
class HiddenAPIs {
    public static final int FLAG_RECEIVER_FROM_SHELL;
    private static Method addService;
    private static Method attachBaseContext;
    private static Method setAppName;

    HiddenAPIs() {
    }

    static {
        FLAG_RECEIVER_FROM_SHELL = Build.VERSION.SDK_INT >= 26 ? 4194304 : 0;
        try {
            Class<?> cls = Class.forName("android.os.ServiceManager");
            if (Build.VERSION.SDK_INT >= 28) {
                try {
                    addService = cls.getDeclaredMethod("addService", String.class, IBinder.class, Boolean.TYPE, Integer.TYPE);
                } catch (NoSuchMethodException unused) {
                }
            }
            if (addService == null) {
                addService = cls.getDeclaredMethod("addService", String.class, IBinder.class);
            }
            Method declaredMethod = ContextWrapper.class.getDeclaredMethod("attachBaseContext", Context.class);
            attachBaseContext = declaredMethod;
            declaredMethod.setAccessible(true);
            setAppName = Class.forName("android.ddm.DdmHandleAppName").getDeclaredMethod("setAppName", String.class, Integer.TYPE);
        } catch (ReflectiveOperationException e) {
            Utils.err("IPC", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setAppName(String str) {
        try {
            setAppName.invoke(null, str, 0);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addService(String str, IBinder iBinder) {
        try {
            if (addService.getParameterTypes().length == 4) {
                addService.invoke(null, str, iBinder, false, 0);
            } else {
                addService.invoke(null, str, iBinder);
            }
        } catch (ReflectiveOperationException e) {
            Utils.err("IPC", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void attachBaseContext(Object obj, Context context) {
        if (obj instanceof ContextWrapper) {
            try {
                attachBaseContext.invoke(obj, context);
            } catch (ReflectiveOperationException unused) {
            }
        }
    }
}
