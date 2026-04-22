package io.virtualapp.hook.network;

import android.util.Log;

import com.lody.virtual.remote.VDeviceConfig;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class GrabRequestHook {
    private static final String TAG = "GrabRequestHook";

    public static void hook(ClassLoader classLoader, VDeviceConfig config) {
        try {
            // Hook okhttp3.Request.Builder.header and addHeader to intercept and replace x-grab-* headers
            Class<?> builderClass = XposedHelpers.findClassIfExists("okhttp3.Request$Builder", classLoader);
            if (builderClass != null) {
                XposedHelpers.findAndHookMethod(builderClass, "header", String.class, String.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        String name = (String) param.args[0];
                        if ("x-grab-device-model".equalsIgnoreCase(name) || "x-mts-device-model".equalsIgnoreCase(name)) {
                            param.args[1] = config.getProp("ro.product.model");
                        } else if ("x-grab-device-id".equalsIgnoreCase(name) || "x-mts-device-id".equalsIgnoreCase(name)) {
                            param.args[1] = config.deviceId;
                        } else if ("x-grab-imei".equalsIgnoreCase(name) || "x-mts-imei".equalsIgnoreCase(name)) {
                            param.args[1] = config.deviceId; // Typically IMEI is same as deviceId here
                        }
                    }
                });

                XposedHelpers.findAndHookMethod(builderClass, "addHeader", String.class, String.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        String name = (String) param.args[0];
                        if ("x-grab-device-model".equalsIgnoreCase(name) || "x-mts-device-model".equalsIgnoreCase(name)) {
                            param.args[1] = config.getProp("ro.product.model");
                        } else if ("x-grab-device-id".equalsIgnoreCase(name) || "x-mts-device-id".equalsIgnoreCase(name)) {
                            param.args[1] = config.deviceId;
                        } else if ("x-grab-imei".equalsIgnoreCase(name) || "x-mts-imei".equalsIgnoreCase(name)) {
                            param.args[1] = config.deviceId;
                        }
                    }
                });
            }

            // Hook JSONObject.put to replace JSON payload strings
            Class<?> jsonObjectClass = XposedHelpers.findClassIfExists("org.json.JSONObject", classLoader);
            if (jsonObjectClass != null) {
                XposedHelpers.findAndHookMethod(jsonObjectClass, "put", String.class, Object.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        String key = (String) param.args[0];
                        if (key != null) {
                            if (key.equalsIgnoreCase("deviceModel")) {
                                param.args[1] = config.getProp("ro.product.model");
                            } else if (key.equalsIgnoreCase("deviceManufacturer")) {
                                param.args[1] = config.getProp("ro.product.manufacturer");
                            } else if (key.equalsIgnoreCase("deviceID") || key.equalsIgnoreCase("deviceId")) {
                                param.args[1] = config.deviceId;
                            } else if (key.equalsIgnoreCase("adrIMEI")) {
                                param.args[1] = config.deviceId;
                            } else if (key.equalsIgnoreCase("adrSERIAL")) {
                                param.args[1] = config.serial;
                            }
                        }
                    }
                });
            }

            Log.i(TAG, "GrabRequestHook injected successfully.");
        } catch (Throwable t) {
            Log.e(TAG, "Error injecting GrabRequestHook", t);
        }
    }
}
