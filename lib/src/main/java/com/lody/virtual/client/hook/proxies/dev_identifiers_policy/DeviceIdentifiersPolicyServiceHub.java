package com.lody.virtual.client.hook.proxies.dev_identifiers_policy;

import android.annotation.TargetApi;
import android.content.AttributionSource;
import android.os.Build;
import android.os.Bundle;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;

import java.lang.reflect.Method;

import mirror.android.os.IDeviceIdentifiersPolicyService;

@TargetApi(29)
public class DeviceIdentifiersPolicyServiceHub extends BinderInvocationProxy {

    public DeviceIdentifiersPolicyServiceHub() {
        super(IDeviceIdentifiersPolicyService.Stub.asInterface, "device_identifiers");
    }

    @Override
    protected void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new SanitizeDeviceIdentifierMethodProxy("getSerial"));
        addMethodProxy(new SanitizeDeviceIdentifierMethodProxy("getSerialForPackage"));
    }

    private static final class SanitizeDeviceIdentifierMethodProxy extends ReplaceCallingPkgMethodProxy {

        private SanitizeDeviceIdentifierMethodProxy(String name) {
            super(name);
        }

        @Override
        public boolean beforeCall(Object who, Method method, Object... args) {
            sanitizeAttributionSourceArgs(args);
            return super.beforeCall(who, method, args);
        }
    }

    private static void sanitizeAttributionSourceArgs(Object[] args) {
        if (args == null || args.length == 0 || Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            return;
        }
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (arg == null) {
                continue;
            }
            if (isAttributionSource(arg)) {
                args[i] = createSafeAttributionSource();
                continue;
            }
            if (arg instanceof Bundle) {
                args[i] = sanitizeBundle((Bundle) arg);
            }
        }
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
}
