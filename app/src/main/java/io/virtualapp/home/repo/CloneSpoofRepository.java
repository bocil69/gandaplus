package io.virtualapp.home.repo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;

import com.lody.virtual.client.ipc.VDeviceManager;
import com.lody.virtual.remote.VDeviceConfig;

import java.util.Random;
import java.util.UUID;
import java.util.Map;

/**
 * Persistent per-clone spoof state store.
 *
 * <p>Key scheme:
 * <pre>
 *   onboarded_{pkg}_{userId}   → boolean  (has this clone been onboarded?)
 *   spoof_{pkg}_{userId}_*     → String   (individual spoof fields)
 * </pre>
 *
 * <p>Usage:
 * <pre>
 *   CloneSpoofRepository repo = CloneSpoofRepository.get(context);
 *   if (!repo.isOnboarded(pkg, userId)) {
 *       // show onboarding, then:
 *       repo.saveSpoof(pkg, userId, config);
 *       repo.markOnboarded(pkg, userId);
 *   } else {
 *       repo.applySpoof(pkg, userId);   // restores saved spoof
 *   }
 * </pre>
 */
public class CloneSpoofRepository {

    private static final String PREF_NAME = "ganda_clone_spoof";

    // SharedPref key helpers
    private static String keyOnboarded(String pkg, int userId) {
        return "onboarded_" + pkg + "_" + userId;
    }
    private static String keySpoofField(String pkg, int userId, String field) {
        return "spoof_" + pkg + "_" + userId + "_" + field;
    }

    // ── Singleton ────────────────────────────────────────────────────
    private static CloneSpoofRepository sInstance;

    public static synchronized CloneSpoofRepository get(Context context) {
        if (sInstance == null) sInstance = new CloneSpoofRepository(context.getApplicationContext());
        return sInstance;
    }

    private final SharedPreferences mPrefs;

    private CloneSpoofRepository(Context context) {
        mPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // ── Onboarding state ─────────────────────────────────────────────

    /** Returns true if this clone (pkg + userId) has already been onboarded. */
    public boolean isOnboarded(String pkg, int userId) {
        return mPrefs.getBoolean(keyOnboarded(pkg, userId), false);
    }

    /** Mark this clone as onboarded. Call after user taps "Done" on onboarding. */
    public void markOnboarded(String pkg, int userId) {
        mPrefs.edit().putBoolean(keyOnboarded(pkg, userId), true).apply();
    }

    /** Clear onboarding flag (for "reset spoof" use-case). */
    public void clearOnboarded(String pkg, int userId) {
        mPrefs.edit().remove(keyOnboarded(pkg, userId)).apply();
    }

    public void clearCloneState(String pkg, int userId) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.remove(keyOnboarded(pkg, userId));
        String prefix = "spoof_" + pkg + "_" + userId + "_";
        for (Map.Entry<String, ?> entry : mPrefs.getAll().entrySet()) {
            String key = entry.getKey();
            if (key != null && key.startsWith(prefix)) {
                editor.remove(key);
            }
        }
        editor.apply();
    }

    // ── Spoof data persistence ────────────────────────────────────────

    /** Persist all spoof fields from a VDeviceConfig into SharedPreferences. */
    public void saveSpoof(String pkg, int userId, VDeviceConfig config) {
        SharedPreferences.Editor ed = mPrefs.edit();
        ed.putString(keySpoofField(pkg, userId, "BRAND"),       config.getProp("BRAND"));
        ed.putString(keySpoofField(pkg, userId, "MODEL"),       config.getProp("MODEL"));
        ed.putString(keySpoofField(pkg, userId, "PRODUCT"),     config.getProp("PRODUCT"));
        ed.putString(keySpoofField(pkg, userId, "DEVICE"),      config.getProp("DEVICE"));
        ed.putString(keySpoofField(pkg, userId, "BOARD"),       config.getProp("BOARD"));
        ed.putString(keySpoofField(pkg, userId, "DISPLAY"),     config.getProp("DISPLAY"));
        ed.putString(keySpoofField(pkg, userId, "ID"),          config.getProp("ID"));
        ed.putString(keySpoofField(pkg, userId, "SERIAL"),      config.getProp("SERIAL"));
        ed.putString(keySpoofField(pkg, userId, "MANUFACTURER"),config.getProp("MANUFACTURER"));
        ed.putString(keySpoofField(pkg, userId, "FINGERPRINT"), config.getProp("FINGERPRINT"));
        ed.putString(keySpoofField(pkg, userId, "ro.build.version.release"), config.getProp("ro.build.version.release"));
        ed.putString(keySpoofField(pkg, userId, "ro.build.id"), config.getProp("ro.build.id"));
        ed.putString(keySpoofField(pkg, userId, "ro.build.display.id"), config.getProp("ro.build.display.id"));
        ed.putString(keySpoofField(pkg, userId, "ro.build.fingerprint"), config.getProp("ro.build.fingerprint"));
        ed.putString(keySpoofField(pkg, userId, "ro.product.model"), config.getProp("ro.product.model"));
        ed.putString(keySpoofField(pkg, userId, "ro.product.manufacturer"), config.getProp("ro.product.manufacturer"));
        ed.putString(keySpoofField(pkg, userId, "androidId"),   config.androidId);
        ed.putString(keySpoofField(pkg, userId, "imei"),        config.deviceId);
        ed.putString(keySpoofField(pkg, userId, "imsi"),        config.subscriberId);
        ed.putString(keySpoofField(pkg, userId, "iccId"),       config.iccId);
        ed.putString(keySpoofField(pkg, userId, "subscriberId"), config.subscriberId);
        ed.putString(keySpoofField(pkg, userId, "simSerial"), config.simSerial);
        ed.putString(keySpoofField(pkg, userId, "networkOperator"), config.networkOperator);
        ed.putString(keySpoofField(pkg, userId, "operatorName"), config.operatorName);
        ed.putString(keySpoofField(pkg, userId, "countryIso"), config.countryIso);
        ed.putString(keySpoofField(pkg, userId, "mac"),         config.wifiMac);
        ed.putString(keySpoofField(pkg, userId, "serial"),      config.serial);
        ed.apply();
    }

    /**
     * Restore saved spoof fields into the VDeviceManager for this clone.
     * Call this when a previously-onboarded clone is launched.
     *
     * @return true if spoof data existed and was applied
     */
    public boolean applySpoof(String pkg, int userId) {
        if (!isOnboarded(pkg, userId)) return false;

        VDeviceConfig config = VDeviceManager.get().getDeviceConfig(userId);
        config.enable = true;

        setPropIfSaved(config, pkg, userId, "BRAND");
        setPropIfSaved(config, pkg, userId, "MODEL");
        setPropIfSaved(config, pkg, userId, "PRODUCT");
        setPropIfSaved(config, pkg, userId, "DEVICE");
        setPropIfSaved(config, pkg, userId, "BOARD");
        setPropIfSaved(config, pkg, userId, "DISPLAY");
        setPropIfSaved(config, pkg, userId, "ID");
        setPropIfSaved(config, pkg, userId, "SERIAL");
        setPropIfSaved(config, pkg, userId, "MANUFACTURER");
        setPropIfSaved(config, pkg, userId, "FINGERPRINT");
        setPropIfSaved(config, pkg, userId, "ro.build.version.release");
        setPropIfSaved(config, pkg, userId, "ro.build.id");
        setPropIfSaved(config, pkg, userId, "ro.build.display.id");
        setPropIfSaved(config, pkg, userId, "ro.build.fingerprint");
        setPropIfSaved(config, pkg, userId, "ro.product.model");
        setPropIfSaved(config, pkg, userId, "ro.product.manufacturer");

        String androidId = mPrefs.getString(keySpoofField(pkg, userId, "androidId"), null);
        String imei      = mPrefs.getString(keySpoofField(pkg, userId, "imei"), null);
        String imsi      = mPrefs.getString(keySpoofField(pkg, userId, "imsi"), null);
        String iccId     = mPrefs.getString(keySpoofField(pkg, userId, "iccId"), null);
        String subscriberId = mPrefs.getString(keySpoofField(pkg, userId, "subscriberId"), null);
        String simSerial = mPrefs.getString(keySpoofField(pkg, userId, "simSerial"), null);
        String networkOperator = mPrefs.getString(keySpoofField(pkg, userId, "networkOperator"), null);
        String operatorName = mPrefs.getString(keySpoofField(pkg, userId, "operatorName"), null);
        String countryIso = mPrefs.getString(keySpoofField(pkg, userId, "countryIso"), null);
        String mac       = mPrefs.getString(keySpoofField(pkg, userId, "mac"), null);
        String serial    = mPrefs.getString(keySpoofField(pkg, userId, "serial"), null);

        if (!TextUtils.isEmpty(androidId)) config.androidId = androidId;
        if (!TextUtils.isEmpty(imei))      config.deviceId  = imei;
        if (!TextUtils.isEmpty(iccId))     config.iccId     = iccId;
        if (!TextUtils.isEmpty(imsi))      config.subscriberId = imsi;
        if (!TextUtils.isEmpty(subscriberId)) config.subscriberId = subscriberId;
        if (!TextUtils.isEmpty(simSerial)) config.simSerial = simSerial;
        if (!TextUtils.isEmpty(networkOperator)) config.networkOperator = networkOperator;
        if (!TextUtils.isEmpty(operatorName)) config.operatorName = operatorName;
        if (!TextUtils.isEmpty(countryIso)) config.countryIso = countryIso;
        if (TextUtils.isEmpty(config.simSerial) && !TextUtils.isEmpty(config.iccId)) {
            config.simSerial = config.iccId;
        }
        if (TextUtils.isEmpty(config.iccId) && !TextUtils.isEmpty(config.simSerial)) {
            config.iccId = config.simSerial;
        }
        if (TextUtils.isEmpty(config.networkOperator) && !TextUtils.isEmpty(config.subscriberId)
                && config.subscriberId.length() >= 5) {
            config.networkOperator = config.subscriberId.substring(0, Math.min(6, config.subscriberId.length()));
        }
        if (!TextUtils.isEmpty(mac))       config.wifiMac   = mac;
        if (!TextUtils.isEmpty(serial))    config.serial    = serial;

        VDeviceManager.get().updateDeviceConfig(userId, config);
        return true;
    }

    private void setPropIfSaved(VDeviceConfig config, String pkg, int userId, String prop) {
        String val = mPrefs.getString(keySpoofField(pkg, userId, prop), null);
        if (!TextUtils.isEmpty(val)) config.setProp(prop, val);
    }

    // ── Random spoof generator ────────────────────────────────────────

    /**
     * Generate a fully randomised VDeviceConfig for a clone.
     * Does NOT persist — call saveSpoof() after the user confirms.
     */
    public static VDeviceConfig generateRandomSpoof(int userId) {
        // Device/operator randomization is centralized in VDeviceConfig.random().
        VDeviceConfig config = VDeviceConfig.random();
        config.enable = true;
        
        // Ensure VDeviceManager has the generated config
        VDeviceManager.get().updateDeviceConfig(userId, config);
        
        return config;
    }

    // ── Helpers ───────────────────────────────────────────────────────

    private static String randomSerial(Random rng) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) sb.append(chars.charAt(rng.nextInt(chars.length())));
        return sb.toString();
    }

    private static String randomImei(Random rng) {
        // Generate 14 digits then compute Luhn check digit
        long n = 10000000000000L + (long)(rng.nextDouble() * 89999999999999L);
        String base = String.valueOf(n);
        return base + luhnCheckDigit(base);
    }

    private static int luhnCheckDigit(String number) {
        int sum = 0;
        boolean alt = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int d = number.charAt(i) - '0';
            if (alt) { d *= 2; if (d > 9) d -= 9; }
            sum += d;
            alt = !alt;
        }
        return (10 - (sum % 10)) % 10;
    }

    private static String randomNumeric(Random rng, int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) sb.append(rng.nextInt(10));
        return sb.toString();
    }

    private static String randomMac(Random rng) {
        String[] hex = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        StringBuilder mac = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (i > 0) mac.append(":");
            mac.append(hex[rng.nextInt(16)]).append(hex[rng.nextInt(16)]);
        }
        return mac.toString();
    }
}
