package com.lody.virtual.helper;

/**
 * Native bridge for Anti-Detection hooks.
 */
public class AntiDetect {

    private static boolean sInitialized = false;

    /**
     * Initializes the native hooks for environment hiding.
     * This includes hooking open/openat to filter /proc/self/maps.
     */
    public static synchronized void init() {
        if (sInitialized) {
            return;
        }
        try {
            // Ensure shadowhook is initialized before hooking
            // The caller (NativeEngine) should have already done this, but we're just providing the API.
            initProcMapsHook();
            sInitialized = true;
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static native void initProcMapsHook();
}
