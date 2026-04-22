package com.lody.virtual.client.hook.proxies.vibrator;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.StaticMethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import mirror.android.os.IVibratorManagerService;

/**
 * Android 12+ vibrator manager service proxy.
 */
public class VibratorManagerStub extends BinderInvocationProxy {

    private static final String SERVICE_NAME = "vibrator_manager";

    public VibratorManagerStub() {
        super(IVibratorManagerService.Stub.asInterface, SERVICE_NAME);
    }

    @Override
    protected void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new VibratorManagerMethodProxy("vibrate"));
        addMethodProxy(new VibratorManagerMethodProxy("setAlwaysOnEffect"));
    }

    private static final class VibratorManagerMethodProxy extends StaticMethodProxy {

        private VibratorManagerMethodProxy(String name) {
            super(name);
        }

        @Override
        public boolean beforeCall(Object who, Method method, Object... args) {
            rewriteUidAndPackage(args);
            return super.beforeCall(who, method, args);
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            try {
                return super.call(who, method, args);
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof SecurityException) {
                    Class<?> returnType = method.getReturnType();
                    if (returnType == Boolean.TYPE) {
                        return false;
                    }
                    if (returnType == Integer.TYPE) {
                        return 0;
                    }
                    return null;
                }
                throw cause;
            }
        }

        private void rewriteUidAndPackage(Object[] args) {
            if (args == null || args.length == 0) {
                return;
            }
            int pkgIndex = findPackageIndex(args);
            if (pkgIndex >= 0) {
                args[pkgIndex] = VirtualCore.get().getHostPkg();
                int uidIndex = findUidIndexBefore(args, pkgIndex);
                if (uidIndex >= 0) {
                    args[uidIndex] = getRealUid();
                }
                return;
            }
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Integer) {
                    args[i] = getRealUid();
                    return;
                }
            }
        }

        private int findPackageIndex(Object[] args) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof String && VirtualCore.get().isAppInstalled((String) args[i])) {
                    return i;
                }
            }
            return -1;
        }

        private int findUidIndexBefore(Object[] args, int pkgIndex) {
            for (int i = pkgIndex - 1; i >= 0; i--) {
                if (args[i] instanceof Integer) {
                    return i;
                }
            }
            return -1;
        }
    }
}
