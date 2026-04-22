package com.lody.virtual.client.hook.proxies.appops;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.AttributionSource;
import android.os.Build;
import android.os.Bundle;

import com.lody.virtual.GmsSupport;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.Keep;
import com.lody.virtual.client.hook.utils.MethodParameterUtils;

import java.lang.reflect.Method;

/**
 * @author Lody
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
@Keep
public class MethodProxies {

    private static void replaceUidAndPackage(Object[] args, int pkgIndex) {
        int resolvedPkgIndex = resolvePackageIndex(args, pkgIndex);
        if (resolvedPkgIndex < 0) {
            return;
        }
        args[resolvedPkgIndex] = VirtualCore.get().getHostPkg();
        int uidIndex = findUidIndexBefore(args, resolvedPkgIndex);
        if (uidIndex >= 0) {
            args[uidIndex] = VirtualCore.get().myUid();
        }
    }

    private static int resolvePackageIndex(Object[] args, int preferredIndex) {
        if (args == null || args.length == 0) {
            return -1;
        }
        if (preferredIndex >= 0 && preferredIndex < args.length && args[preferredIndex] instanceof String) {
            return preferredIndex;
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String && VirtualCore.get().isAppInstalled((String) args[i])) {
                return i;
            }
        }
        return -1;
    }

    private static int findUidIndexBefore(Object[] args, int pkgIndex) {
        for (int i = pkgIndex - 1; i >= 0; i--) {
            if (args[i] instanceof Integer) {
                return i;
            }
        }
        return -1;
    }

    private static Object[] sanitizeAttributionSourceArgs(Object[] args) {
        if (args == null || args.length == 0 || Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            return args;
        }
        Object[] sanitizedArgs = args.clone();
        for (int i = 0; i < sanitizedArgs.length; i++) {
            Object arg = sanitizedArgs[i];
            if (arg == null) {
                continue;
            }
            if (isAttributionSource(arg)) {
                sanitizedArgs[i] = createSafeAttributionSource();
                continue;
            }
            if (arg instanceof Bundle) {
                sanitizedArgs[i] = sanitizeBundle((Bundle) arg);
            }
        }
        return sanitizedArgs;
    }

    private static Bundle sanitizeBundle(Bundle source) {
        Bundle sanitized = new Bundle(source);
        for (String key : sanitized.keySet()) {
            Object value = sanitized.get(key);
            if (value == null) {
                continue;
            }
            if (isAttributionSource(value)) {
                sanitized.putParcelable(key, createSafeAttributionSource());
                continue;
            }
            if (value instanceof Bundle) {
                sanitized.putBundle(key, sanitizeBundle((Bundle) value));
            }
        }
        return sanitized;
    }

    private static AttributionSource createSafeAttributionSource() {
        int uid = VirtualCore.get().myUid();
        String packageName = VirtualCore.get().getHostPkg();
        try {
            return new AttributionSource.Builder(uid)
                    .setPackageName(packageName)
                    .build();
        } catch (Throwable e) {
            return null;
        }
    }

    private static boolean isAttributionSource(Object arg) {
        String className = arg.getClass().getName();
        return "android.content.AttributionSource".equals(className)
                || className.contains("AttributionSource");
    }

    public static Object checkAudioOperation(Object who, Method method, Object[] args) throws Throwable {
        replaceUidAndPackage(args, 3);
        args = sanitizeAttributionSourceArgs(args);
        return method.invoke(who, args);
    }

    public static Object checkOperation(Object who, Method method, Object[] args) throws Throwable {
        replaceUidAndPackage(args, 2);
        args = sanitizeAttributionSourceArgs(args);
        return method.invoke(who, args);
    }

    public static Object checkPackage(Object who, Method method, Object[] args) throws Throwable {
        String pkg = (String) args[1];
        if (GmsSupport.isGoogleAppOrService(pkg)) {
            return AppOpsManager.MODE_ALLOWED;
        }
        replaceUidAndPackage(args, 1);
        args = sanitizeAttributionSourceArgs(args);
        return method.invoke(who, args);
    }


    public static Object getOpsForPackage(Object who, Method method, Object[] args) throws Throwable {
        replaceUidAndPackage(args, 1);
        args = sanitizeAttributionSourceArgs(args);
        return method.invoke(who, args);
    }

    public static Object getPackagesForOps(Object who, Method method, Object[] args) throws Throwable {
        args = sanitizeAttributionSourceArgs(args);
        return method.invoke(who, args);
    }

    public static Object noteOperation(Object who, Method method, Object[] args) throws Throwable {
        replaceUidAndPackage(args, 2);
        args = sanitizeAttributionSourceArgs(args);
        return method.invoke(who, args);
    }


    public static Object noteProxyOperation(Object who, Method method, Object[] args) throws Throwable {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            int pkgIndex = resolvePackageIndex(args, -1);
            int uidIndex = findUidIndexBefore(args, pkgIndex);
            MethodParameterUtils.replaceAllAppPkgWithHost(args);
            if (uidIndex >= 0) {
                args[uidIndex] = VirtualCore.get().myUid();
            }
            args = sanitizeAttributionSourceArgs(args);
            return method.invoke(who, args);
        }
        return AppOpsManager.MODE_ALLOWED;
    }

    public static Object resetAllModes(Object who, Method method, Object[] args) throws Throwable {
        // force userId to 0
        args[0] = 0;
        args[1] = VirtualCore.get().getHostPkg();
        args = sanitizeAttributionSourceArgs(args);
        return method.invoke(who, args);
    }

    public static Object startOperation(Object who, Method method, Object[] args) throws Throwable {
        replaceUidAndPackage(args, 3);
        args = sanitizeAttributionSourceArgs(args);
        return method.invoke(who, args);
    }

    public static Object finishOperation(Object who, Method method, Object[] args) throws Throwable {
        replaceUidAndPackage(args, 3);
        args = sanitizeAttributionSourceArgs(args);
        return method.invoke(who, args);
    }

    //Android Q
    public static Object checkOperationRaw(Object who, Method method, Object[] args) throws Throwable {
        replaceUidAndPackage(args, 2);
        args = sanitizeAttributionSourceArgs(args);
        return method.invoke(who, args);
    }
}
