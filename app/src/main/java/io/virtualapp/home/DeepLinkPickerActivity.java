package io.virtualapp.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.helper.compat.ActivityManagerCompat;
import com.lody.virtual.remote.InstalledAppInfo;

import io.virtualapp.R;

public class DeepLinkPickerActivity extends Activity {

    public static final String EXTRA_TARGET_PACKAGE = "extra.TARGET_PACKAGE";
    public static final String EXTRA_ORIGINAL_INTENT = "extra.ORIGINAL_INTENT";

    private String mTargetPackage;
    private Intent mOriginalIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTargetPackage = getIntent().getStringExtra(EXTRA_TARGET_PACKAGE);
        mOriginalIntent = getIntent().getParcelableExtra(EXTRA_ORIGINAL_INTENT);

        if (TextUtils.isEmpty(mTargetPackage) || mOriginalIntent == null) {
            finish();
            return;
        }

        InstalledAppInfo appInfo = VirtualCore.get().getInstalledAppInfo(mTargetPackage, 0);
        if (appInfo == null) {
            finish();
            return;
        }

        int[] installedUsers = appInfo.getInstalledUsers();
        if (installedUsers == null || installedUsers.length == 0) {
            finish();
            return;
        }

        if (installedUsers.length == 1) {
            launchForUser(installedUsers[0]);
            return;
        }

        showCloneChooser(installedUsers);
    }

    private void showCloneChooser(int[] installedUsers) {
        String[] labels = new String[installedUsers.length];
        for (int i = 0; i < installedUsers.length; i++) {
            labels[i] = buildCloneLabel(installedUsers[i]);
        }

        new AlertDialog.Builder(this, R.style.VAAlertTheme)
                .setTitle(R.string.pick_clone_for_link)
                .setItems(labels, (dialog, which) -> launchForUser(installedUsers[which]))
                .setOnCancelListener(dialog -> finish())
                .show();
    }

    private String buildCloneLabel(int userId) {
        SharedPreferences prefs = getSharedPreferences("clone_names", MODE_PRIVATE);
        String customName = prefs.getString(mTargetPackage + ":" + userId, null);
        if (!TextUtils.isEmpty(customName)) {
            return customName;
        }
        if (userId == 0) {
            return getString(R.string.primary_clone_label);
        }
        return getString(R.string.clone_number_label, userId + 1);
    }

    private void launchForUser(int userId) {
        Intent launchIntent = new Intent(mOriginalIntent);
        launchIntent.setComponent(null);
        launchIntent.setPackage(mTargetPackage);
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        int result = VActivityManager.get().startActivity(launchIntent, userId);
        if (result != ActivityManagerCompat.START_SUCCESS && result != ActivityManagerCompat.START_TASK_TO_FRONT) {
            Toast.makeText(this, R.string.deeplink_open_failed, Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
