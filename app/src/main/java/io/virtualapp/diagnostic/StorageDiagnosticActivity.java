package io.virtualapp.diagnostic;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.os.VEnvironment;
import com.lody.virtual.remote.InstalledAppInfo;
import com.lody.virtual.server.pm.VAppManagerService;

import java.io.File;
import java.util.List;

import io.virtualapp.R;
import io.virtualapp.home.repo.AppListBackupManager;

/**
 * Storage Diagnostic Activity
 * 
 * Shows detailed information about:
 * - Storage paths and permissions
 * - Installed apps in VirtualCore
 * - Persistence layer status
 * - Backup status
 */
public class StorageDiagnosticActivity extends Activity {

    private TextView tvDiagnostics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_diagnostic);

        tvDiagnostics = findViewById(R.id.tv_diagnostics);
        tvDiagnostics.setMovementMethod(new ScrollingMovementMethod());

        Button btnRefresh = findViewById(R.id.btn_refresh);
        Button btnCopy = findViewById(R.id.btn_copy);
        Button btnBackup = findViewById(R.id.btn_backup);
        Button btnRestore = findViewById(R.id.btn_restore);
        Button btnClearBackup = findViewById(R.id.btn_clear_backup);

        btnRefresh.setOnClickListener(v -> runDiagnostics());
        
        btnCopy.setOnClickListener(v -> {
            String text = tvDiagnostics.getText().toString();
            android.content.ClipboardManager clipboard = 
                (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Diagnostics", text);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
        });

        btnBackup.setOnClickListener(v -> {
            AppListBackupManager.get(this).backupAppList();
            Toast.makeText(this, "Backup created", Toast.LENGTH_SHORT).show();
            runDiagnostics();
        });

        btnRestore.setOnClickListener(v -> {
            boolean restored = AppListBackupManager.get(this).restoreIfNeeded();
            Toast.makeText(this,
                    restored ? "Unexpected restore path triggered" : "Metadata checked. Real clone restore depends on engine package state.",
                    Toast.LENGTH_SHORT).show();
            runDiagnostics();
        });

        btnClearBackup.setOnClickListener(v -> {
            AppListBackupManager.get(this).clearBackup();
            Toast.makeText(this, "Backup cleared", Toast.LENGTH_SHORT).show();
            runDiagnostics();
        });

        runDiagnostics();
    }

    private void runDiagnostics() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("╔══════════════════════════════════════════════════════════════╗\n");
        sb.append("║         GANDA PLUS STORAGE DIAGNOSTICS                       ║\n");
        sb.append("╚══════════════════════════════════════════════════════════════╝\n\n");

        // Device Info
        sb.append("📱 DEVICE INFO\n");
        sb.append("──────────────────────────────────────────────────────────────\n");
        sb.append("Android Version: ").append(Build.VERSION.RELEASE).append(" (API ").append(Build.VERSION.SDK_INT).append(")\n");
        sb.append("Device: ").append(Build.MANUFACTURER).append(" ").append(Build.MODEL).append("\n");
        sb.append("Package: ").append(getPackageName()).append("\n");
        sb.append("\n");

        // Storage Info
        sb.append("💾 STORAGE INFO\n");
        sb.append("──────────────────────────────────────────────────────────────\n");
        try {
            String dataDir = getApplicationInfo().dataDir;
            File dataDirFile = new File(dataDir);
            sb.append("Data Directory: ").append(dataDir).append("\n");
            sb.append("  Exists: ").append(dataDirFile.exists()).append("\n");
            sb.append("  Can Read: ").append(dataDirFile.canRead()).append("\n");
            sb.append("  Can Write: ").append(dataDirFile.canWrite()).append("\n");
            
            StatFs stat = new StatFs(dataDir);
            long blockSize = stat.getBlockSizeLong();
            long availableBlocks = stat.getAvailableBlocksLong();
            long totalBlocks = stat.getBlockCountLong();
            
            long availableMB = (availableBlocks * blockSize) / (1024 * 1024);
            long totalMB = (totalBlocks * blockSize) / (1024 * 1024);
            
            sb.append("  Storage: ").append(availableMB).append("MB / ").append(totalMB).append("MB available\n");
            
        } catch (Exception e) {
            sb.append("  Error: ").append(e.getMessage()).append("\n");
        }
        sb.append("\n");

        // Virtual Environment
        sb.append("🔧 VIRTUAL ENVIRONMENT\n");
        sb.append("──────────────────────────────────────────────────────────────\n");
        try {
            File dataDir = VEnvironment.getDataDirectory();
            sb.append("VEnvironment DATA_DIRECTORY: ").append(dataDir.getAbsolutePath()).append("\n");
            sb.append("  Exists: ").append(dataDir.exists()).append("\n");
            sb.append("  Can Read: ").append(dataDir.canRead()).append("\n");
            sb.append("  Can Write: ").append(dataDir.canWrite()).append("\n");
            
            File dataDir64 = VEnvironment.getDataDirectory64();
            sb.append("\nVEnvironment DATA_DIRECTORY64: ").append(dataDir64.getAbsolutePath()).append("\n");
            sb.append("  Exists: ").append(dataDir64.exists()).append("\n");
            sb.append("  Can Read: ").append(dataDir64.canRead()).append("\n");
            sb.append("  Can Write: ").append(dataDir64.canWrite()).append("\n");
            
        } catch (Exception e) {
            sb.append("  Error: ").append(e.getMessage()).append("\n");
        }
        sb.append("\n");

        // Installed Apps
        sb.append("📦 INSTALLED APPS\n");
        sb.append("──────────────────────────────────────────────────────────────\n");
        try {
            List<InstalledAppInfo> apps = VirtualCore.get().getInstalledApps(0);
            sb.append("Count: ").append(apps.size()).append("\n\n");
            
            for (InstalledAppInfo app : apps) {
                sb.append("Package: ").append(app.packageName).append("\n");
                sb.append("  App ID: ").append(app.appId).append("\n");
                
                int[] userIds = app.getInstalledUsers();
                sb.append("  Users: ");
                if (userIds != null) {
                    for (int userId : userIds) {
                        sb.append(userId).append(" ");
                    }
                }
                sb.append("\n");
                
                // Check app directory
                File appDir = VEnvironment.getDataAppPackageDirectory(app.packageName);
                sb.append("  App Dir: ").append(appDir.getAbsolutePath()).append("\n");
                sb.append("    Exists: ").append(appDir.exists()).append("\n");
                
                sb.append("\n");
            }
        } catch (Exception e) {
            sb.append("Error: ").append(e.getMessage()).append("\n");
            e.printStackTrace();
        }
        sb.append("\n");

        // Backup Status
        sb.append("💾 BACKUP STATUS\n");
        sb.append("──────────────────────────────────────────────────────────────\n");
        sb.append(AppListBackupManager.get(this).getDiagnostics());
        sb.append("\n");

        // Recommendations
        sb.append("💡 RECOMMENDATIONS\n");
        sb.append("──────────────────────────────────────────────────────────────\n");
        
        boolean hasIssues = false;
        
        try {
            File dataDir = VEnvironment.getDataDirectory();
            if (dataDir == null || !dataDir.exists() || !dataDir.canWrite()) {
                sb.append("❌ VEnvironment DATA_DIRECTORY is not writable!\n");
                sb.append("   → Check Android version compatibility\n");
                sb.append("   → May need storage permissions\n");
                hasIssues = true;
            }
            
            List<InstalledAppInfo> apps = VirtualCore.get().getInstalledApps(0);
            if (apps.isEmpty()) {
                sb.append("⚠️ No installed apps found\n");
                sb.append("   → This is normal for first run\n");
                sb.append("   → Or data was lost - check backup\n");
            }
            
        } catch (Exception e) {
            sb.append("❌ Error checking: ").append(e.getMessage()).append("\n");
        }
        
        if (!hasIssues) {
            sb.append("✅ No critical issues detected\n");
        }

        tvDiagnostics.setText(sb.toString());
    }
}
