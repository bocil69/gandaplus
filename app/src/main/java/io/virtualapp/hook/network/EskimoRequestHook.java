package io.virtualapp.hook.network;

import android.util.Log;

import com.lody.virtual.remote.VDeviceConfig;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class EskimoRequestHook {
    private static final String TAG = "EskimoRequestHook";

    public static void hook(ClassLoader classLoader, VDeviceConfig config) {
        try {
            Class<?> builderClass = XposedHelpers.findClassIfExists("okhttp3.Request$Builder", classLoader);
            if (builderClass != null) {
                XposedHelpers.findAndHookMethod(builderClass, "header", String.class, String.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        String name = (String) param.args[0];
                        if ("User-Agent".equalsIgnoreCase(name)) {
                            // Spoof user-agent to reflect the spoofed device info
                            String spoofedUserAgent = "Eskimo Android Dalvik/2.1.0 (Linux; U; Android " +
                                    config.getProp("ro.build.version.release") + "; " +
                                    config.getProp("ro.product.model") + " Build/" +
                                    config.getProp("ro.build.display.id") + ")";
                            param.args[1] = spoofedUserAgent;
                        }
                    }
                });

                XposedHelpers.findAndHookMethod(builderClass, "addHeader", String.class, String.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        String name = (String) param.args[0];
                        if ("User-Agent".equalsIgnoreCase(name)) {
                            String spoofedUserAgent = "Eskimo Android Dalvik/2.1.0 (Linux; U; Android " +
                                    config.getProp("ro.build.version.release") + "; " +
                                    config.getProp("ro.product.model") + " Build/" +
                                    config.getProp("ro.build.display.id") + ")";
                            param.args[1] = spoofedUserAgent;
                        }
                    }
                });
            }

            Log.i(TAG, "EskimoRequestHook injected successfully.");
        } catch (Throwable t) {
            Log.e(TAG, "Error injecting EskimoRequestHook", t);
        }
    }
}
