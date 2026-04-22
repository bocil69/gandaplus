package com.lody.virtual.helper.compat;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Android 14 (API 34) shim for PackageParser.
 *
 * android.content.pm.PackageParser was hidden/removed progressively:
 *   - API 30+: greylist (accessible via FreeReflection)
 *   - API 33+: blocklist-exempt but flagged
 *   - API 34 (Android 14): some internal paths changed
 *
 * This shim wraps the reflection-based approach with safe fallbacks
 * using the public PackageManager API.
 */
public class PackageParserAndroid14Compat {

    private static final String TAG = "PackageParser14Compat";

    // Attempt to create a PackageParser instance using the no-arg constructor
    // available since Marshmallow (API 23). Returns null if not available.
    public static Object createParser() {
        try {
            Class<?> parserClass = Class.forName("android.content.pm.PackageParser");
            Constructor<?> ctor = parserClass.getDeclaredConstructor();
            ctor.setAccessible(true);
            return ctor.newInstance();
        } catch (Throwable e) {
            Log.w(TAG, "PackageParser no-arg ctor unavailable: " + e.getMessage());
            return null;
        }
    }

    /**
     * Parse an APK using reflection. Falls back to PackageManager on Android 14+
     * if PackageParser is inaccessible.
     */
    public static Object parsePackage(Object parser, File packageFile, int flags) throws Throwable {
        try {
            Class<?> parserClass = Class.forName("android.content.pm.PackageParser");
            Method parsePackage = parserClass.getDeclaredMethod("parsePackage", File.class, int.class);
            parsePackage.setAccessible(true);
            return parsePackage.invoke(parser, packageFile, flags);
        } catch (Throwable e) {
            Log.w(TAG, "PackageParser.parsePackage reflection failed: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Collect APK certificates. On Android 14+, use the static form
     * that accepts only Package + boolean (skipVerify).
     */
    public static void collectCertificates(Object parserOrNull, Object pkg, int flags) {
        try {
            if (Build.VERSION.SDK_INT >= 28) { // Pie+: static method Package, boolean
                Class<?> parserClass = Class.forName("android.content.pm.PackageParser");
                Class<?> packageClass = Class.forName("android.content.pm.PackageParser$Package");
                try {
                    Method m = parserClass.getDeclaredMethod("collectCertificates", packageClass, boolean.class);
                    m.setAccessible(true);
                    m.invoke(null, pkg, false);
                    return;
                } catch (NoSuchMethodException ex) {
                    // Fall through to older signature
                }
            }
            // Older: instance method (parser, pkg, flags)
            if (parserOrNull != null) {
                Class<?> parserClass = Class.forName("android.content.pm.PackageParser");
                Class<?> packageClass = Class.forName("android.content.pm.PackageParser$Package");
                Method m = parserClass.getDeclaredMethod("collectCertificates", packageClass, int.class);
                m.setAccessible(true);
                m.invoke(parserOrNull, pkg, flags);
            }
        } catch (Throwable e) {
            Log.w(TAG, "collectCertificates failed (non-fatal): " + e.getMessage());
        }
    }

    /**
     * Parses a minimal PackageInfo from an APK file using only the public
     * PackageManager API. Used as an absolute last-resort fallback.
     */
    public static PackageInfo parsePackageInfoFallback(File apkFile, PackageManager pm) {
        try {
            int flags = PackageManager.GET_ACTIVITIES
                            | PackageManager.GET_SERVICES
                            | PackageManager.GET_RECEIVERS
                            | PackageManager.GET_PROVIDERS
                            | PackageManager.GET_PERMISSIONS
                            | PackageManager.GET_META_DATA
                            | PackageManager.GET_SIGNATURES;
            if (Build.VERSION.SDK_INT >= 28) {
                flags |= 0x08000000; // PackageManager.GET_SIGNING_CERTIFICATES
            }
            return pm.getPackageArchiveInfo(apkFile.getAbsolutePath(), flags);
        } catch (Throwable e) {
            Log.e(TAG, "parsePackageInfoFallback failed: " + e.getMessage());
            return null;
        }
    }
}
