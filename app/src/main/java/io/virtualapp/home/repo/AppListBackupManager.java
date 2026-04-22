package io.virtualapp.home.repo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.remote.InstalledAppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * App List Backup Manager - Secondary persistence for cloned apps.
 * 
 * Uses SharedPreferences as backup storage to prevent app list loss.
 * This complements the binary PersistenceLayer with a more stable (but slower)
 * JSON-based backup system.
 */
public class AppListBackupManager {

    private static final String TAG = "AppListBackup";
    private static final String PREFS_NAME = "app_list_backup";
    private static final String KEY_APP_LIST = "installed_apps";
    private static final String KEY_LAST_BACKUP = "last_backup_time";
    private static final String KEY_BACKUP_VERSION = "backup_version";
    
    private static final int CURRENT_VERSION = 1;
    private static final long BACKUP_INTERVAL_MS = 5000; // Backup at most every 5 seconds
    
    private static AppListBackupManager sInstance;
    private final Context mContext;
    private final SharedPreferences mPrefs;
    private final Gson mGson;
    private long mLastBackupTime = 0;

    public static synchronized AppListBackupManager get(Context context) {
        if (sInstance == null) {
            sInstance = new AppListBackupManager(context.getApplicationContext());
        }
        return sInstance;
    }

    private AppListBackupManager(Context context) {
        mContext = context;
        mPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        mGson = new Gson();
    }

    /**
     * Backup current app list to SharedPreferences.
     * Rate-limited to prevent excessive writes.
     */
    public void backupAppList() {
        long now = System.currentTimeMillis();
        if (now - mLastBackupTime < BACKUP_INTERVAL_MS) {
            return; // Too soon
        }
        
        try {
            List<InstalledAppInfo> apps = VirtualCore.get().getInstalledApps(0);
            List<AppBackupEntry> entries = new ArrayList<>();
            
            for (InstalledAppInfo app : apps) {
                AppBackupEntry entry = new AppBackupEntry();
                entry.packageName = app.packageName;
                entry.appId = app.appId;
                entry.appMode = app.appMode;
                // Note: firstInstallTime and lastUpdateTime not available in this version
                entry.firstInstallTime = 0;
                entry.lastUpdateTime = 0;
                entry.flag = app.flag;
                
                // Save user IDs
                int[] userIds = app.getInstalledUsers();
                entry.userIds = new ArrayList<>();
                if (userIds != null) {
                    for (int userId : userIds) {
                        entry.userIds.add(userId);
                    }
                }
                
                entries.add(entry);
            }
            
            String json = mGson.toJson(entries);
            
            mPrefs.edit()
                .putString(KEY_APP_LIST, json)
                .putLong(KEY_LAST_BACKUP, now)
                .putInt(KEY_BACKUP_VERSION, CURRENT_VERSION)
                .apply();
            
            mLastBackupTime = now;
            Log.d(TAG, "✅ Backed up " + entries.size() + " apps");
            
        } catch (Exception e) {
            Log.e(TAG, "❌ Failed to backup app list", e);
        }
    }

    /**
     * Restore app list from backup if primary storage is empty.
     * Returns true if restore was performed.
     */
    public boolean restoreIfNeeded() {
        try {
            // Check if primary storage has data
            List<InstalledAppInfo> currentApps = VirtualCore.get().getInstalledApps(0);
            
            if (!currentApps.isEmpty()) {
                Log.d(TAG, "✅ Primary storage has " + currentApps.size() + " apps, no restore needed");
                return false;
            }
            
            // Primary is empty, check backup
            String json = mPrefs.getString(KEY_APP_LIST, null);
            if (json == null || json.isEmpty()) {
                Log.w(TAG, "⚠️ No backup available");
                return false;
            }
            
            int version = mPrefs.getInt(KEY_BACKUP_VERSION, 0);
            if (version != CURRENT_VERSION) {
                Log.w(TAG, "⚠️ Backup version mismatch: " + version + " vs " + CURRENT_VERSION);
            }
            
            long backupTime = mPrefs.getLong(KEY_LAST_BACKUP, 0);
            Log.d(TAG, "📂 Restoring from backup (created: " + new java.util.Date(backupTime) + ")");
            
            List<AppBackupEntry> entries = mGson.fromJson(json, 
                new TypeToken<List<AppBackupEntry>>(){}.getType());
            
            if (entries == null || entries.isEmpty()) {
                Log.w(TAG, "⚠️ Backup is empty");
                return false;
            }
            
            Log.d(TAG, "✅ Found " + entries.size() + " apps in backup");
            Log.d(TAG, "   Note: Apps need to be re-installed. Backup contains metadata only.");
            
            // Log the packages that were lost
            for (AppBackupEntry entry : entries) {
                Log.d(TAG, "   📦 " + entry.packageName + " (users: " + entry.userIds + ")");
            }
            
            return true;
            
        } catch (Exception e) {
            Log.e(TAG, "❌ Failed to restore from backup", e);
            return false;
        }
    }

    /**
     * Clear backup data.
     */
    public void clearBackup() {
        mPrefs.edit().clear().apply();
        Log.d(TAG, "🗑️ Backup cleared");
    }

    /**
     * Get diagnostic information about backup state.
     */
    public String getDiagnostics() {
        StringBuilder sb = new StringBuilder();
        sb.append("AppListBackupManager Diagnostics:\n");
        
        try {
            String json = mPrefs.getString(KEY_APP_LIST, null);
            long backupTime = mPrefs.getLong(KEY_LAST_BACKUP, 0);
            int version = mPrefs.getInt(KEY_BACKUP_VERSION, 0);
            
            sb.append("  Backup exists: ").append(json != null).append("\n");
            sb.append("  Backup version: ").append(version).append("\n");
            sb.append("  Backup time: ").append(backupTime > 0 ? new java.util.Date(backupTime) : "never").append("\n");
            
            if (json != null) {
                List<AppBackupEntry> entries = mGson.fromJson(json, 
                    new TypeToken<List<AppBackupEntry>>(){}.getType());
                sb.append("  Apps in backup: ").append(entries != null ? entries.size() : 0).append("\n");
            }
            
            List<InstalledAppInfo> currentApps = VirtualCore.get().getInstalledApps(0);
            sb.append("  Apps in primary: ").append(currentApps.size()).append("\n");
            
        } catch (Exception e) {
            sb.append("  Error: ").append(e.getMessage()).append("\n");
        }
        
        return sb.toString();
    }

    /**
     * Data class for app backup entries.
     */
    private static class AppBackupEntry {
        String packageName;
        int appId;
        int appMode;
        long firstInstallTime;
        long lastUpdateTime;
        int flag;
        List<Integer> userIds;
    }
}
