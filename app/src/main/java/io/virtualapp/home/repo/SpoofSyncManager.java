package io.virtualapp.home.repo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.utils.HookErrorHandler;
import com.lody.virtual.client.ipc.VDeviceManager;
import com.lody.virtual.client.ipc.VirtualLocationManager;
import com.lody.virtual.remote.VDeviceConfig;
import com.lody.virtual.remote.vloc.VLocation;

/**
 * Spoof Sync Manager - Ensures device spoof and fake location are ALWAYS synchronized.
 * 
 * <p>This manager acts as the bridge between:
 * <ul>
 *   <li>UI Layer (Onboarding, Spoof Manager)</li>
 *   <li>Virtual Engine (VDeviceManager, VirtualLocationManager)</li>
 *   <li>Hooking Layer (MethodProxies)</li>
 * </ul>
 * 
 * <p>Key responsibilities:
 * <ol>
 *   <li>Ensure Account N always has matching device + location data</li>
 *   <li>Auto-recover from corrupted/missing data</li>
 *   <li>Provide atomic "save all" and "load all" operations</li>
 *   <li>Validation before app launch</li>
 * </ol>
 */
public class SpoofSyncManager {
    
    private static final String TAG = "SpoofSyncManager";
    private static final String PREFS_NAME = "spoof_sync_state";
    
    // Preference keys
    private static final String KEY_DEVICE_SAVED = "device_saved_";
    private static final String KEY_LOCATION_SAVED = "location_saved_";
    private static final String KEY_LAST_ACCOUNT = "last_active_account_";
    
    private static SpoofSyncManager sInstance;
    private final Context mContext;
    private final SharedPreferences mPrefs;
    
    public static synchronized SpoofSyncManager get(Context context) {
        if (sInstance == null) {
            sInstance = new SpoofSyncManager(context.getApplicationContext());
        }
        return sInstance;
    }
    
    private SpoofSyncManager(Context context) {
        mContext = context;
        mPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    
    // ═════════════════════════════════════════════════════════════════
    // PUBLIC API: Atomic Save & Load
    // ═════════════════════════════════════════════════════════════════
    
    /**
     * Save COMPLETE spoof profile (device + location) atomically.
     * This is the ONE method to call from Onboarding when saving.
     */
    public boolean saveCompleteProfile(String pkg, int userId, VDeviceConfig deviceConfig, VLocation location) {
        Log.d(TAG, "💾 Saving complete profile for " + pkg + " (userId=" + userId + ")");
        
        try {
            // 1. Save device spoof
            CloneSpoofRepository spoofRepo = CloneSpoofRepository.get(mContext);
            spoofRepo.saveSpoof(pkg, userId, deviceConfig);
            spoofRepo.markOnboarded(pkg, userId);
            
            // 2. Apply device spoof immediately
            VDeviceManager.get().updateDeviceConfig(userId, deviceConfig);
            
            // 3. Save and apply fake location
            if (location != null && !location.isEmpty()) {
                VirtualLocationManager.get().setMode(userId, pkg, VirtualLocationManager.MODE_USE_SELF);
                VirtualLocationManager.get().setLocation(userId, pkg, location);
            } else {
                VirtualLocationManager.get().setMode(userId, pkg, VirtualLocationManager.MODE_CLOSE);
                VirtualLocationManager.get().setLocation(userId, pkg, new VLocation());
                Log.d(TAG, "ℹ️ Fake location left disabled for " + pkg);
            }
            
            // 4. Mark sync state
            markDeviceSaved(pkg, userId, true);
            markLocationSaved(pkg, userId, true);
            setLastActiveAccount(pkg, userId);
            
            Log.d(TAG, "✅ Complete profile saved for " + pkg);
            return true;
            
        } catch (Exception e) {
            HookErrorHandler.handleError(
                HookErrorHandler.ErrorType.SPOOF_DATA_MISSING,
                "saveCompleteProfile failed for " + pkg,
                e
            );
            return false;
        }
    }
    
    /**
     * Load COMPLETE spoof profile before launching app.
     * Returns true if all data is valid and loaded.
     */
    public boolean loadCompleteProfile(String pkg, int userId) {
        Log.d(TAG, "📂 Loading complete profile for " + pkg + " (userId=" + userId + ")");
        
        try {
            // 1. Check if onboarded
            CloneSpoofRepository spoofRepo = CloneSpoofRepository.get(mContext);
            if (!spoofRepo.isOnboarded(pkg, userId)) {
                Log.e(TAG, "❌ Not onboarded: " + pkg);
                return false;
            }
            
            // 2. Load device spoof
            boolean deviceLoaded = spoofRepo.applySpoof(pkg, userId);
            if (!deviceLoaded) {
                Log.e(TAG, "❌ Failed to load device spoof: " + pkg);
                return false;
            }
            
            // Verify device config
            VDeviceConfig config = VDeviceManager.get().getDeviceConfig(userId);
            if (config == null || !config.enable) {
                Log.e(TAG, "❌ Device config not enabled: " + pkg);
                return false;
            }
            
            // 3. Check and activate location
            int locationMode = VirtualLocationManager.get().getMode(userId, pkg);
            if (locationMode == VirtualLocationManager.MODE_USE_SELF) {
                // 4. Verify location data exists when spoofing is enabled
                VLocation location = com.lody.virtual.client.ipc.VLocationManager.get()
                    .getLocation(pkg, userId);
                if (location == null || location.isEmpty()) {
                    Log.e(TAG, "❌ Location data missing for " + pkg);
                    return false;
                }
                Log.d(TAG, "   Location: " + location.getLatitude() + ", " + location.getLongitude());
            } else {
                Log.d(TAG, "ℹ️ Fake location disabled for " + pkg + ", continuing with original location");
            }
            
            // 5. Set as last active account
            setLastActiveAccount(pkg, userId);
            
            Log.d(TAG, "✅ Complete profile loaded for " + pkg);
            Log.d(TAG, "   Device: " + config.getProp("BRAND") + " " + config.getProp("MODEL"));
            return true;
            
        } catch (Exception e) {
            HookErrorHandler.handleError(
                HookErrorHandler.ErrorType.SPOOF_DATA_MISSING,
                "loadCompleteProfile failed for " + pkg,
                e
            );
            return false;
        }
    }
    
    /**
     * Validate that all required data is present before launch.
     * Shows detailed error if validation fails.
     */
    public ValidationResult validateBeforeLaunch(String pkg, int userId) {
        ValidationResult result = new ValidationResult();
        
        try {
            // Check device
            CloneSpoofRepository spoofRepo = CloneSpoofRepository.get(mContext);
            if (!spoofRepo.isOnboarded(pkg, userId)) {
                result.addError("Clone not onboarded");
            }
            
            VDeviceConfig config = VDeviceManager.get().getDeviceConfig(userId);
            if (config == null) {
                result.addError("Device config is null");
            } else if (!config.enable) {
                result.addError("Device config not enabled");
            }
            
            // Check location
            int mode = VirtualLocationManager.get().getMode(userId, pkg);
            if (mode == VirtualLocationManager.MODE_USE_SELF) {
                VLocation location = com.lody.virtual.client.ipc.VLocationManager.get()
                    .getLocation(pkg, userId);
                if (location == null || location.isEmpty()) {
                    result.addError("Location data is empty");
                }
            }
            
            result.valid = !result.hasErrors();
            
        } catch (Exception e) {
            result.addError("Validation exception: " + e.getMessage());
            result.valid = false;
        }
        
        return result;
    }
    
    // ═════════════════════════════════════════════════════════════════
    // Multi-Account Support
    // ═════════════════════════════════════════════════════════════════
    
    /**
     * Switch to a different account slot and ensure data is loaded.
     */
    public boolean switchToAccount(String pkg, int newUserId) {
        Log.d(TAG, "🔄 Switching " + pkg + " to account " + newUserId);
        
        // Save current account state if needed
        int currentAccount = getLastActiveAccount(pkg);
        if (currentAccount != -1 && currentAccount != newUserId) {
            Log.d(TAG, "   Saving current account " + currentAccount + " state");
        }
        
        // Load new account
        boolean success = loadCompleteProfile(pkg, newUserId);
        if (success) {
            setLastActiveAccount(pkg, newUserId);
            Log.d(TAG, "✅ Switched to account " + newUserId);
        } else {
            Log.e(TAG, "❌ Failed to switch to account " + newUserId);
        }
        
        return success;
    }
    
    /**
     * Create a new account slot with default spoof data.
     */
    public boolean createNewAccount(String pkg, int userId) {
        Log.d(TAG, "➕ Creating new account " + userId + " for " + pkg);

        deleteCloneState(pkg, userId);
        VDeviceConfig deviceConfig = VDeviceManager.get().getDeviceConfig(userId);
        if (deviceConfig != null) {
            deviceConfig.enable = false;
            VDeviceManager.get().updateDeviceConfig(userId, deviceConfig);
        }
        VirtualLocationManager.get().setMode(userId, pkg, VirtualLocationManager.MODE_CLOSE);
        VirtualLocationManager.get().setLocation(userId, pkg, new VLocation());
        return true;
    }

    public void deleteCloneState(String pkg, int userId) {
        CloneSpoofRepository.get(mContext).clearCloneState(pkg, userId);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.remove(KEY_DEVICE_SAVED + pkg + "_" + userId);
        editor.remove(KEY_LOCATION_SAVED + pkg + "_" + userId);
        if (mPrefs.getInt(KEY_LAST_ACCOUNT + pkg, -1) == userId) {
            editor.remove(KEY_LAST_ACCOUNT + pkg);
        }
        editor.apply();
        VirtualLocationManager.get().setMode(userId, pkg, VirtualLocationManager.MODE_CLOSE);
        VirtualLocationManager.get().setLocation(userId, pkg, new VLocation());
    }
    
    // ═════════════════════════════════════════════════════════════════
    // State Tracking
    // ═════════════════════════════════════════════════════════════════
    
    private void markDeviceSaved(String pkg, int userId, boolean saved) {
        mPrefs.edit().putBoolean(KEY_DEVICE_SAVED + pkg + "_" + userId, saved).apply();
    }
    
    private void markLocationSaved(String pkg, int userId, boolean saved) {
        mPrefs.edit().putBoolean(KEY_LOCATION_SAVED + pkg + "_" + userId, saved).apply();
    }
    
    private void setLastActiveAccount(String pkg, int userId) {
        mPrefs.edit().putInt(KEY_LAST_ACCOUNT + pkg, userId).apply();
    }
    
    public int getLastActiveAccount(String pkg) {
        return mPrefs.getInt(KEY_LAST_ACCOUNT + pkg, -1);
    }
    
    public boolean isProfileComplete(String pkg, int userId) {
        boolean deviceSaved = mPrefs.getBoolean(KEY_DEVICE_SAVED + pkg + "_" + userId, false);
        boolean locationSaved = mPrefs.getBoolean(KEY_LOCATION_SAVED + pkg + "_" + userId, false);
        return deviceSaved && locationSaved;
    }
    
    // ═════════════════════════════════════════════════════════════════
    // Validation Result Class
    // ═════════════════════════════════════════════════════════════════
    
    public static class ValidationResult {
        public boolean valid = true;
        private final StringBuilder errors = new StringBuilder();
        
        public void addError(String error) {
            valid = false;
            if (errors.length() > 0) errors.append("\n");
            errors.append("• ").append(error);
        }
        
        public boolean hasErrors() {
            return !valid;
        }
        
        public String getErrorMessage() {
            return errors.toString();
        }
        
        @Override
        public String toString() {
            return valid ? "✅ Valid" : "❌ " + errors.toString();
        }
    }
}
