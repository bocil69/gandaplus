package io.virtualapp.home;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.utils.HookErrorHandler;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.client.ipc.VDeviceManager;
import com.lody.virtual.client.ipc.VPackageManager;
import com.lody.virtual.client.ipc.VirtualLocationManager;
import com.lody.virtual.helper.compat.PermissionCompat;
import com.lody.virtual.open.MultiAppHelper;
import com.lody.virtual.remote.InstallResult;
import com.lody.virtual.remote.InstalledAppInfo;
import com.lody.virtual.remote.VDeviceConfig;
import com.lody.virtual.remote.vloc.VLocation;
import com.lody.virtual.server.bit64.V64BitHelper;

import io.virtualapp.R;
import io.virtualapp.VCommends;
import io.virtualapp.abs.ui.VUiKit;
import io.virtualapp.home.models.AppData;
import io.virtualapp.home.models.AppInfoLite;
import io.virtualapp.home.models.MultiplePackageAppData;
import io.virtualapp.home.models.PackageAppData;
import io.virtualapp.home.repo.AppRepository;
import io.virtualapp.home.repo.CloneSpoofRepository;
import io.virtualapp.home.repo.PackageAppDataStorage;
import io.virtualapp.home.repo.SpoofSyncManager;
import jonathanfinerty.once.Once;

import static io.virtualapp.VCommends.REQUEST_PERMISSION;

/**
 * Home presenter — handles launch routing with per-clone onboarding gate.
 * ENHANCED with comprehensive error handling and validation.
 *
 * <p><b>Launch logic:</b>
 * <ol>
 *   <li>Validate all required data (spoof + location)</li>
 *   <li>If clone has never been onboarded → show {@link CloneOnboardingActivity}</li>
 *   <li>If clone is already onboarded → apply saved spoof & location, then launch app</li>
 *   <li>Any error → show detailed error dialog with copy-to-clipboard</li>
 * </ol>
 */
class HomePresenterImpl implements HomeContract.HomePresenter {

    private static final String TAG = "HomePresenter";

    private HomeContract.HomeView mView;
    private Activity mActivity;
    private AppRepository mRepo;

    HomePresenterImpl(HomeContract.HomeView view) {
        mView = view;
        mActivity = view.getActivity();
        mRepo = new AppRepository(mActivity);
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        dataChanged();
        if (!Once.beenDone(VCommends.TAG_SHOW_ADD_APP_GUIDE)) {
            mView.showGuide();
            Once.markDone(VCommends.TAG_SHOW_ADD_APP_GUIDE);
        }
    }

    @Override
    public String getLabel(String packageName) {
        return mRepo.getLabel(packageName);
    }

    @Override
    public boolean check64bitEnginePermission() {
        if (VirtualCore.get().is64BitEngineInstalled()) {
            if (!V64BitHelper.has64BitEngineStartPermission()) {
                mView.showPermissionDialog();
                return true;
            }
        }
        return false;
    }

    // ── launchApp (public) ─────────────────────────────────────────────

    @Override
    public void launchApp(AppData data) {
        final int userId = data.getUserId();
        final String packageName = data.getPackageName();
        
        // Validate basic parameters
        if (userId == -1 || packageName == null) {
            HookErrorHandler.handleError(
                HookErrorHandler.ErrorType.SPOOF_DATA_MISSING,
                "launchApp: Invalid parameters (userId=" + userId + ", pkg=" + packageName + ")",
                null
            );
            return;
        }

        try {
            CloneSpoofRepository spoofRepo = CloneSpoofRepository.get(mActivity);

            // ── GATE: onboarding check ────────────────────────────────
            if (!spoofRepo.isOnboarded(packageName, userId)) {
                // First-ever launch → show onboarding, do NOT launch app yet
                Intent intent = new Intent(mActivity, CloneOnboardingActivity.class);
                intent.putExtra(CloneOnboardingActivity.EXTRA_PKG,   packageName);
                intent.putExtra(CloneOnboardingActivity.EXTRA_USER,  userId);
                intent.putExtra(CloneOnboardingActivity.EXTRA_LABEL, data.getName());
                mActivity.startActivityForResult(intent, CloneOnboardingActivity.REQUEST_CODE);
                return;
            }

            // ── Already onboarded → validate and apply data ───────────
            if (!validateAndApplySpoof(packageName, userId)) {
                return; // Error already shown by validateAndApplySpoof
            }

            // ── Permission check (Android M+) ─────────────────────────
            boolean runAppNow = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                InstalledAppInfo info = VirtualCore.get().getInstalledAppInfo(packageName, userId);
                if (info == null) {
                    HookErrorHandler.handleError(
                        HookErrorHandler.ErrorType.SPOOF_DATA_MISSING,
                        "launchApp: InstalledAppInfo not found for " + packageName,
                        null
                    );
                    return;
                }
                
                ApplicationInfo applicationInfo = info.getApplicationInfo(userId);
                boolean is64bit = VirtualCore.get().isRun64BitProcess(info.packageName);
                if (is64bit && check64bitEnginePermission()) return;
                if (PermissionCompat.isCheckPermissionRequired(applicationInfo.targetSdkVersion)) {
                    String[] permissions = VPackageManager.get().getDangrousPermissions(info.packageName);
                    if (!PermissionCompat.checkPermissions(permissions, is64bit)) {
                        runAppNow = false;
                        PermissionRequestActivity.requestPermission(
                                mActivity, permissions, data.getName(), userId, packageName, REQUEST_PERMISSION);
                    }
                }
            }
            
            if (runAppNow) {
                data.isFirstOpen = false;
                launchAppInternal(userId, packageName);
            }

        } catch (Throwable e) {
            HookErrorHandler.handleError(
                HookErrorHandler.ErrorType.ACTIVITY_LAUNCH_FAILED,
                "launchApp: Unexpected error launching " + packageName,
                e
            );
        }
    }

    /**
     * Validate spoof and location data, apply if valid.
     * Returns true if all data is valid and applied successfully.
     */
    private boolean validateAndApplySpoof(String packageName, int userId) {
        try {
            SpoofSyncManager syncManager = SpoofSyncManager.get(mActivity);
            if (!syncManager.loadCompleteProfile(packageName, userId)) {
                SpoofSyncManager.ValidationResult validation = syncManager.validateBeforeLaunch(packageName, userId);
                String details = validation.hasErrors()
                        ? validation.getErrorMessage()
                        : "• Failed to load slot profile for this clone";
                HookErrorHandler.handleError(
                    HookErrorHandler.ErrorType.SPOOF_DATA_MISSING,
                    "Validation Failed for " + packageName + " (slot " + userId + "):\n\n" + details,
                    null
                );
                return false;
            }

            Log.d(TAG, "Loaded clone slot profile for " + packageName + " (userId=" + userId + ")");
            return true;

        } catch (Exception e) {
            HookErrorHandler.handleError(
                HookErrorHandler.ErrorType.SPOOF_DATA_MISSING,
                "validateAndApplySpoof: Exception validating " + packageName,
                e
            );
            return false;
        }
    }

    /**
     * Called by {@link HomeActivity#onActivityResult} after the onboarding screen
     * returns RESULT_OK. The spoof has already been saved; just launch the app.
     */
    void onOnboardingComplete(String packageName, int userId) {
        // Validate before launching
        if (!validateAndApplySpoof(packageName, userId)) {
            return; // Error shown, don't launch
        }
        
        // Reload the saved slot profile and launch immediately.
        SpoofSyncManager.get(mActivity).loadCompleteProfile(packageName, userId);
        launchAppInternal(userId, packageName);
    }

    // ── Internal launch ───────────────────────────────────────────────

    private void launchAppInternal(int userId, String packageName) {
        try {
            if (VirtualCore.get().isRun64BitProcess(packageName)) {
                if (!VirtualCore.get().is64BitEngineInstalled()) {
                    Toast.makeText(mActivity, "Please install 64-bit engine.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!V64BitHelper.has64BitEngineStartPermission()) {
                    Toast.makeText(mActivity, "No permission to start 64-bit engine.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            
            long lastTime = VActivityManager.get().getLastBackHomeTime();
            long elapsed  = System.currentTimeMillis() - lastTime;
            if (lastTime > 0 && elapsed <= 6000 && "com.xdja.HDSafeEMailClient".equals(packageName)) {
                VUiKit.postDelayed(Math.max(2000, elapsed), () ->
                        VActivityManager.get().launchApp(userId, packageName));
            } else {
                VActivityManager.get().launchApp(userId, packageName);
            }
            
            Log.d(TAG, "App launched: " + packageName + " (userId=" + userId + ")");
            
        } catch (Exception e) {
            HookErrorHandler.handleError(
                HookErrorHandler.ErrorType.ACTIVITY_LAUNCH_FAILED,
                "launchAppInternal: Failed to launch " + packageName,
                e
            );
        }
    }

    // ── Data ──────────────────────────────────────────────────────────

    @Override
    public void dataChanged() {
        mView.showLoading();
        mRepo.getVirtualApps().done(mView::loadFinish).fail(mView::loadError);
    }

    @Override
    public void addApp(AppInfoLite info) {
        class AddResult {
            private PackageAppData appData;
            private int userId;
        }
        AddResult addResult = new AddResult();
        
        // MD3 styled loading dialog
        AlertDialog loadingDialog = createMD3LoadingDialog(mActivity.getString(R.string.tip_add_apps));
        loadingDialog.show();
        
        VUiKit.defer().when(() -> {
            InstalledAppInfo installedAppInfo = VirtualCore.get().getInstalledAppInfo(info.packageName, 0);
            if (installedAppInfo != null) {
                addResult.userId = MultiAppHelper.installExistedPackage(installedAppInfo);
                if (addResult.userId > 0) {
                    SpoofSyncManager.get(mActivity).createNewAccount(info.packageName, addResult.userId);
                }
            } else {
                InstallResult res = mRepo.addVirtualApp(info);
                if (!res.isSuccess) throw new IllegalStateException();
            }
        }).then((res) -> {
            addResult.appData = PackageAppDataStorage.get().acquire(info.packageName);
        }).fail((e) -> {
            loadingDialog.dismiss();
        }).done(res -> {
            AppData appData;
            if (addResult.userId == 0) {
                PackageAppData d = addResult.appData;
                d.isLoading = true;
                mView.addAppToLauncher(d);
                handleLoadingApp(d);
                appData = d;
            } else {
                MultiplePackageAppData d = new MultiplePackageAppData(addResult.appData, addResult.userId);
                d.isLoading = true;
                mView.addAppToLauncher(d);
                handleLoadingApp(d);
                appData = d;
            }
            loadingDialog.dismiss();
        });
    }
    
    /**
     * Install an app from a local archive file (.apk, .xapk, .apks, .apkm).
     * Extracts split APKs and native libraries automatically.
     */
    public void addAppFromStorage(java.io.File archiveFile) {
        if (archiveFile == null || !archiveFile.exists()) {
            Toast.makeText(mActivity, "Selected archive is missing.", Toast.LENGTH_LONG).show();
            return;
        }
        final PackageAppData[] addedAppData = new PackageAppData[1];
        AlertDialog loadingDialog = createMD3LoadingDialog("Preparing APK set...");
        loadingDialog.show();

        VUiKit.defer().when(() -> {
            // 1. Extract splits
            java.util.List<java.io.File> extracted = com.lody.virtual.helper.SplitApkExtractor.extractSplitApks(archiveFile, mActivity);
            if (extracted.isEmpty()) {
                throw new RuntimeException("No valid APKs found in " + archiveFile.getName());
            }

            // 2. Convert to list of paths
            java.util.List<String> paths = new java.util.ArrayList<>();
            for (java.io.File f : extracted) {
                paths.add(f.getAbsolutePath());
            }

            // 3. Install via new multi-APK API
            com.lody.virtual.remote.InstallOptions options = com.lody.virtual.remote.InstallOptions
                    .makeOptions(false, false, com.lody.virtual.remote.InstallOptions.UpdateStrategy.COMPARE_VERSION)
                    .setInstallerInfo("com.android.vending", "com.android.vending");
            InstallResult res = VirtualCore.get().installPackageSync(paths, options);
            if (!res.isSuccess) {
                throw new RuntimeException("Install failed: " + res.error);
            }
            addedAppData[0] = PackageAppDataStorage.get().acquire(res.packageName);
        }).fail((e) -> {
            loadingDialog.dismiss();
            Throwable t = (Throwable) e;
            Toast.makeText(mActivity, t.getMessage(), Toast.LENGTH_LONG).show();
        }).done((res) -> {
            PackageAppData appData = addedAppData[0];
            if (appData != null) {
                appData.isLoading = true;
                mView.addAppToLauncher(appData);
                handleLoadingApp(appData);
            }
            loadingDialog.dismiss();
        });
    }
    
    /**
     * Create MD3 styled loading dialog with AMOLED dark theme and blue accent
     */
    private AlertDialog createMD3LoadingDialog(String message) {
        View dialogView = LayoutInflater.from(mActivity).inflate(R.layout.dialog_md3_loading, null);
        android.widget.TextView tvMessage = dialogView.findViewById(R.id.loading_message);
        if (tvMessage != null) {
            tvMessage.setText(message);
        }
        
        AlertDialog dialog = new AlertDialog.Builder(mActivity)
                .setView(dialogView)
                .setCancelable(false)
                .create();
        
        // Transparent background for CardView effect
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
        
        return dialog;
    }

    private void handleLoadingApp(AppData data) {
        VUiKit.defer().when(() -> {
            long start = System.currentTimeMillis();
            long elapsed = System.currentTimeMillis() - start;
            if (elapsed < 1500L) {
                try { Thread.sleep(1500L - elapsed); } catch (InterruptedException ignored) {}
            }
        }).done((res) -> {
            if (data instanceof PackageAppData) {
                ((PackageAppData) data).isLoading  = false;
                ((PackageAppData) data).isFirstOpen = true;
            } else if (data instanceof MultiplePackageAppData) {
                ((MultiplePackageAppData) data).isLoading  = false;
                ((MultiplePackageAppData) data).isFirstOpen = true;
            }
            mView.refreshLauncherItem(data);
        });
    }

    @Override
    public void deleteApp(AppData data) {
        mView.removeAppToLauncher(data);
        AlertDialog loadingDialog = createMD3LoadingDialog(mActivity.getString(R.string.tip_delete) + " " + data.getName());
        loadingDialog.show();
        VUiKit.defer().when(() ->
                mRepo.removeVirtualApp(data.getPackageName(), data.getUserId())
        ).fail(e -> loadingDialog.dismiss()).done(rs -> loadingDialog.dismiss());
    }

    @Override
    public void clearCloneData(AppData data) {
        AlertDialog loadingDialog = createMD3LoadingDialog(mActivity.getString(R.string.clear_clone_data_progress, data.getName()));
        loadingDialog.show();
        VUiKit.defer().when(() ->
                mRepo.clearVirtualAppData(data.getPackageName(), data.getUserId())
        ).fail(e -> {
            loadingDialog.dismiss();
            Toast.makeText(mActivity, R.string.clear_clone_data_failed, Toast.LENGTH_SHORT).show();
        }).done(rs -> {
            loadingDialog.dismiss();
            Toast.makeText(mActivity, rs ? R.string.clear_clone_data_success : R.string.clear_clone_data_failed, Toast.LENGTH_SHORT).show();
            mView.refreshLauncherItem(data);
        });
    }

    @Override
    public void enterAppSetting(AppData data) {
        AppSettingActivity.enterAppSetting(mActivity, data.getPackageName(), data.getUserId());
    }

    @Override
    public int getAppCount() {
        return VirtualCore.get().getInstalledApps(0).size();
    }
}
