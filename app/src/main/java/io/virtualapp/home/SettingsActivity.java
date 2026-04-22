package io.virtualapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.lody.virtual.GmsSupport;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.client.stub.ChooseTypeAndAccountActivity;
import com.lody.virtual.os.VUserInfo;
import com.lody.virtual.os.VUserManager;

import java.util.ArrayList;
import java.util.List;

import io.virtualapp.R;

import io.virtualapp.home.location.LocationSettingsActivity;

/**
 * MD3 Settings screen for GANDA PLUS
 */
public class SettingsActivity extends AppCompatActivity {

    private static final int COLOR_PRIMARY = 0xFF4FC3F7;
    private static final int COLOR_ERROR   = 0xFFFFB4AB;
    private static final int COLOR_NEUTRAL = 0xFFC3C7CF;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Settings");
        }

        bindListeners();

        TextView versionText = findViewById(R.id.settings_version_text);
        try {
            String vn = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            versionText.setText("GANDA PLUS v" + vn);
        } catch (Exception ignored) {
            versionText.setText("GANDA PLUS");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /** Applies primary-color tint to POSITIVE button, neutral tint to NEGATIVE button. */
    private void styleDialog(AlertDialog dialog, int positiveColor) {
        dialog.setOnShowListener(d -> {
            if (dialog.getButton(AlertDialog.BUTTON_POSITIVE) != null)
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(positiveColor);
            if (dialog.getButton(AlertDialog.BUTTON_NEGATIVE) != null)
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(COLOR_NEUTRAL);
        });
    }

    private void bindListeners() {

        // Multi Accounts
        ((LinearLayout) findViewById(R.id.settings_accounts)).setOnClickListener(v -> {
            List<VUserInfo> users = VUserManager.get().getUsers();
            List<String> names = new ArrayList<>(users.size());
            for (VUserInfo info : users) names.add(info.name);
            new AlertDialog.Builder(this)
                    .setTitle("Virtual Accounts")
                    .setItems(names.toArray(new CharSequence[0]), (dialog, which) -> {
                        Intent intent = new Intent(this, ChooseTypeAndAccountActivity.class);
                        intent.putExtra(ChooseTypeAndAccountActivity.KEY_USER_ID, users.get(which).id);
                        startActivity(intent);
                    })
                    .show();
        });

        // Virtual Location
        ((LinearLayout) findViewById(R.id.settings_location)).setOnClickListener(v -> {
            if (VirtualCore.get().getInstalledApps(0).isEmpty()) {
                Toast.makeText(this, "Add an app first to use virtual location", Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(new Intent(this, LocationSettingsActivity.class));
        });

        // Kill All
        ((LinearLayout) findViewById(R.id.settings_kill_all)).setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Kill All Apps")
                    .setMessage("Stop all running virtual apps and free memory?")
                    .setPositiveButton("Kill All", (d, w) -> {
                        VActivityManager.get().killAllApps();
                        Toast.makeText(this, "Memory released ✓", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            styleDialog(dialog, COLOR_ERROR);
            dialog.show();
        });

        // Google Play Services
        ((LinearLayout) findViewById(R.id.settings_gms)).setOnClickListener(v -> showGmsDialog());

        // Exit
        ((LinearLayout) findViewById(R.id.settings_exit)).setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Exit GANDA PLUS")
                    .setMessage("Stop all virtual processes and exit?")
                    .setPositiveButton("Exit", (d, w) -> {
                        VirtualCore.get().killAllApps();
                        VirtualCore.get().stopForeground();
                        Process.killProcess(VActivityManager.get().getSystemPid());
                        Process.killProcess(Process.myPid());
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            styleDialog(dialog, COLOR_ERROR);
            dialog.show();
        });

        // About
        ((LinearLayout) findViewById(R.id.settings_about)).setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("About GANDA PLUS")
                    .setMessage("GANDA PLUS\nAndroid 14 Compatible Virtual Engine\n\n"
                            + "Run multiple accounts of any app without root.\n\n"
                            + "Features:\n• Multi-clone per app\n• Device & IMEI spoofing\n"
                            + "• Virtual GPS location\n• Root-free operation")
                    .setPositiveButton("OK", null)
                    .create();
            styleDialog(dialog, COLOR_PRIMARY);
            dialog.show();
        });
    }

    private void showGmsDialog() {
        if (!GmsSupport.isOutsideGoogleFrameworkExist()) {
            AlertDialog d = new AlertDialog.Builder(this)
                    .setTitle("Google Play Services")
                    .setMessage("Google Play Services framework is not available on this device.")
                    .setPositiveButton("OK", null).create();
            styleDialog(d, COLOR_PRIMARY);
            d.show();
            return;
        }
        if (GmsSupport.isInstalledGoogleService()) {
            AlertDialog d = new AlertDialog.Builder(this)
                    .setTitle("Google Play Services")
                    .setMessage("Google Play Services is already installed in the virtual environment.")
                    .setPositiveButton("OK", null).create();
            styleDialog(d, COLOR_PRIMARY);
            d.show();
            return;
        }
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Install Google Play Services")
                .setMessage("Install GMS into the virtual environment? This enables Google sign-in inside cloned apps.")
                .setPositiveButton("Install", (d, w) -> {
                    Toast.makeText(this, "Installing GMS…", Toast.LENGTH_SHORT).show();
                    new Thread(() -> GmsSupport.installGApps(0)).start();
                })
                .setNegativeButton("Cancel", null)
                .setCancelable(false)
                .create();
        styleDialog(dialog, COLOR_PRIMARY);
        dialog.show();
    }
}
