package io.virtualapp.delegate;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Minimal PackageManager wrapper using dynamic proxy to avoid
 * having to implement every abstract method.
 * Delegates all calls to base, overrides only getPackageName-related methods.
 */
public class PackageManagerWrapper {

    private PackageManagerWrapper() {}

    /**
     * Create a proxy PackageManager that spoofs package name queries.
     * Uses java.lang.reflect.Proxy to avoid compile-time dependency on hidden APIs.
     */
    public static PackageManager createSpoofed(PackageManager base, final String hostPkg, final String spoofPkg) {
        return (PackageManager) java.lang.reflect.Proxy.newProxyInstance(
                PackageManager.class.getClassLoader(),
                new Class<?>[]{ PackageManager.class },
                new java.lang.reflect.InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) throws Throwable {
                        String methodName = method.getName();

                        // Spoof: getNameForUid -> return spoofed package for host uid
                        if ("getNameForUid".equals(methodName) && args != null && args.length == 1) {
                            Object result = method.invoke(base, args);
                            if (result instanceof String && hostPkg.equals(result)) {
                                return spoofPkg;
                            }
                            return result;
                        }

                        // Spoof: getPackagesForUid -> replace host pkg with spoofed
                        if ("getPackagesForUid".equals(methodName) && args != null && args.length == 1) {
                            String[] result = (String[]) method.invoke(base, args);
                            if (result != null) {
                                for (int i = 0; i < result.length; i++) {
                                    if (hostPkg.equals(result[i])) {
                                        result[i] = spoofPkg;
                                    }
                                }
                            }
                            return result;
                        }

                        // Spoof: getInstallerPackageName -> return Play Store
                        if ("getInstallerPackageName".equals(methodName) && args != null && args.length == 1) {
                            if (spoofPkg.equals(args[0])) {
                                return "com.android.vending";
                            }
                            return method.invoke(base, args);
                        }

                        // Default: delegate to base
                        return method.invoke(base, args);
                    }
                }
        );
    }
}
