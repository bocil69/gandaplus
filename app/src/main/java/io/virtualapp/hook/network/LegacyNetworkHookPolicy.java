package io.virtualapp.hook.network;

import android.content.Context;
import android.content.SharedPreferences;

import io.virtualapp.BuildConfig;

public final class LegacyNetworkHookPolicy {

    private static final String PREF_NAME = "legacy_network_hook_policy";
    private static final String KEY_ENABLED = "legacy_network_hook_enabled";

    private static LegacyNetworkHookPolicy sInstance;

    public static synchronized LegacyNetworkHookPolicy get(Context context) {
        if (sInstance == null) {
            sInstance = new LegacyNetworkHookPolicy(context.getApplicationContext());
        }
        return sInstance;
    }

    public static boolean evaluate(Boolean storedToggle, String buildType) {
        if (isReleaseBuild(buildType)) {
            return false;
        }
        return Boolean.TRUE.equals(storedToggle);
    }

    static boolean isReleaseBuild(String buildType) {
        return "release".equalsIgnoreCase(buildType);
    }

    private final SharedPreferences preferences;

    private LegacyNetworkHookPolicy(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean isEnabled() {
        return evaluate(readStoredToggle(), BuildConfig.BUILD_TYPE);
    }

    public Boolean readStoredToggle() {
        if (!preferences.contains(KEY_ENABLED)) {
            return null;
        }
        return preferences.getBoolean(KEY_ENABLED, false);
    }

    public void setStoredToggle(boolean enabled) {
        preferences.edit().putBoolean(KEY_ENABLED, enabled).apply();
    }

    public void clearStoredToggle() {
        preferences.edit().remove(KEY_ENABLED).apply();
    }
}
