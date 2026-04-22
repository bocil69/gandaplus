package io.virtualapp.home;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lody.virtual.GmsSupport;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.Constants;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.client.stub.ChooseTypeAndAccountActivity;
import com.lody.virtual.client.stub.InstallerSetting;
import com.lody.virtual.helper.utils.BitmapUtils;
import com.lody.virtual.helper.utils.FileUtils;
import com.lody.virtual.oem.OemPermissionHelper;
import com.lody.virtual.os.VUserInfo;
import com.lody.virtual.os.VUserManager;
import com.xdja.monitor.MediaObserver;
import com.xdja.safekeyservice.jarv2.SecuritySDKManager;
import com.xdja.safekeyservice.jarv2.bean.IVerifyPinResult;
import com.xdja.zs.VAppPermissionManager;
import com.xdja.zs.VSafekeyManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import io.virtualapp.R;
import io.virtualapp.VCommends;
import io.virtualapp.abs.nestedadapter.SmartRecyclerAdapter;
import io.virtualapp.abs.ui.VActivity;
import io.virtualapp.abs.ui.VUiKit;
import io.virtualapp.home.adapters.LaunchpadAdapter;
import io.virtualapp.home.models.AddAppButton;
import io.virtualapp.home.models.AppData;
import io.virtualapp.home.models.AppInfoLite;
import io.virtualapp.home.models.EmptyAppData;
import io.virtualapp.home.models.MultiplePackageAppData;
import io.virtualapp.home.models.PackageAppData;
import io.virtualapp.home.repo.AppListBackupManager;
import io.virtualapp.diagnostic.StorageDiagnosticActivity;

import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG;
import static androidx.recyclerview.widget.ItemTouchHelper.DOWN;
import static androidx.recyclerview.widget.ItemTouchHelper.LEFT;
import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;
import static androidx.recyclerview.widget.ItemTouchHelper.UP;

/**
 * @author Lody
 */
public class HomeActivity extends VActivity implements HomeContract.HomeView {

    private static final String TAG = "HomeActivity";
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int MENU_DIAGNOSTIC = 1001;
    private boolean mRestoredFromBackup = false;
    private HomeContract.HomePresenter mPresenter;
    private View mLoadingView;
    private RecyclerView mLauncherView;
    private ExtendedFloatingActionButton mFabAdd;
    private ExtendedFloatingActionButton mFabLocation;
    private View mBottomArea;
    private TextView mEnterSettingTextView;
    private View mDeleteAppBox;
    private TextView mDeleteAppTextView;
    private TextView mAppCountText;
    private LaunchpadAdapter mLaunchpadAdapter;
    private Handler mUiHandler;
    private final Paint mSwipePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint mSwipeTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public static void goHome(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        
        // Check and restore from backup if needed
        checkAndRestoreFromBackup();
        
        setContentView(R.layout.activity_home);
        mUiHandler = new Handler(Looper.getMainLooper());
        bindViews();
        initLaunchpad();
        new HomePresenterImpl(this);
        initMagic();
        mPresenter.check64bitEnginePermission();
        mPresenter.start();
        
        // Log storage info untuk debugging
        logStorageInfo();

        VirtualCore.get().registerReceiver(this, receiver, new IntentFilter("com.xdja.dialer.removecall"));
        IntentFilter filter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        filter.addDataScheme("package");
        VirtualCore.get().registerReceiver(this, receiver, filter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkPermission()) {
                MediaObserver.observe(this);
            } else {
                requestPermission();
            }
        } else {
            MediaObserver.observe(this);
        }

        IntentFilter alarmFilter = new IntentFilter("com.android.deskclock.ALARM_ALERT");
        VirtualCore.get().registerReceiver(this, alarmReceiver, alarmFilter);
        int ret = VSafekeyManager.get().initSafekeyCard();
        if (ret == -1) {
            if (VSafekeyManager.get().getService() != null) {
                try {
                    SecuritySDKManager.getInstance().startVerifyPinActivity(this, new IVerifyPinResult() {
                        @Override
                        public void onResult(int i, String s) {
                            Log.d(TAG, "safekey result: " + i);
                        }
                    });
                } catch (ActivityNotFoundException e) {
                    Log.w(TAG, "Safekey verification activity unavailable, skipping init flow", e);
                }
            } else {
                Log.w(TAG, "Safekey service unavailable, skipping verify flow");
            }
        }
        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_BADGER_CHANGE);
        intentFilter.addAction(Constants.ACTION_WALLPAPER_CHANGED);
        VirtualCore.get().registerReceiver(this, mReceiver, intentFilter);
        VirtualCore.get().startForeground();
        VAppPermissionManager.get().setThirdAppInstallationEnable(true);
        
        // Backup app list setelah startup
        AppListBackupManager.get(this).backupAppList();
    }

    private void checkAndRestoreFromBackup() {
        try {
            Log.d(TAG, "Checking for backup restore...");
            AppListBackupManager backupManager = AppListBackupManager.get(this);
            
            List<com.lody.virtual.remote.InstalledAppInfo> apps = 
                VirtualCore.get().getInstalledApps(0);
            
            if (apps.isEmpty()) {
                Log.w(TAG, "No apps in primary storage, checking backup...");
                boolean restored = backupManager.restoreIfNeeded();
                
                if (restored) {
                    mRestoredFromBackup = true;
                    Log.d(TAG, "Restore attempt completed");
                }
            } else {
                Log.d(TAG, "Primary storage has " + apps.size() + " apps");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error checking backup", e);
        }
    }
    
    private void logStorageInfo() {
        try {
            String dataDir = getApplicationInfo().dataDir;
            File virtualDir = new File(dataDir, "virtual");
            File packageList = new File(virtualDir, "package-list");
            
            Log.d(TAG, "═══════════════════════════════════════════");
            Log.d(TAG, "STORAGE INFO:");
            Log.d(TAG, "  Data Dir: " + dataDir);
            Log.d(TAG, "  Virtual Dir: " + virtualDir + " (exists=" + virtualDir.exists() + ")");
            Log.d(TAG, "  Package List: " + packageList + " (exists=" + packageList.exists() + ")");
            Log.d(TAG, "  Android SDK: " + Build.VERSION.SDK_INT);
            Log.d(TAG, "═══════════════════════════════════════════");
        } catch (Exception e) {
            Log.e(TAG, "Error logging storage info", e);
        }
    }

    private VirtualCore.Receiver mReceiver = new VirtualCore.Receiver() {
        @Override
        public void onReceive(Context context, Intent intent, int userId) {
            if (Constants.ACTION_WALLPAPER_CHANGED.equals(intent.getAction())) {
                String path = intent.getStringExtra(Intent.EXTRA_STREAM);
                if (!FileUtils.isExist(path)) return;
                File file = new File(path);
                File[] files = file.getParentFile().listFiles();
                if (files != null) {
                    for (File f : files) {
                        if (f.isFile() && !TextUtils.equals(f.getPath(), file.getPath())) {
                            f.delete();
                        }
                    }
                }
                mLauncherView.setBackground(new BitmapDrawable(getResources(), BitmapUtils.getBitmapByFile(path)));
            }
        }
    };


    private VirtualCore.Receiver alarmReceiver = new VirtualCore.Receiver() {
        @SuppressLint("DefaultLocale")
        @Override
        public void onReceive(Context context, Intent intent, int userId) {
            if (!"com.android.deskclock.ALARM_ALERT".equals(intent.getAction())) return;
            if (intent.hasExtra("showUI")) {
                Intent activity = intent.getParcelableExtra("showUI");
                activity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                VActivityManager.get().startActivity(activity, 0);
                return;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            new AlertDialog.Builder(context, androidx.appcompat.R.style.Theme_AppCompat_Dialog_Alert)
                    .setMessage(String.format("Alarm: %02d:%02d",
                            calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)))
                    .setNegativeButton("Snooze", (dlg, id) ->
                            VActivityManager.get().sendBroadcast(
                                    new Intent("com.android.deskclock.ALARM_SNOOZE")
                                            .setPackage(InstallerSetting.CLOCK_PKG), 0))
                    .setPositiveButton("Dismiss", (dlg, id) ->
                            VActivityManager.get().sendBroadcast(
                                    new Intent("com.android.deskclock.ALARM_DISMISS")
                                            .setPackage(InstallerSetting.CLOCK_PKG), 0))
                    .show();
        }
    };

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(HomeActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                    MediaObserver.observe(this);
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

    private VirtualCore.Receiver receiver = new VirtualCore.Receiver() {
        @Override
        public void onReceive(Context context, Intent intent, int userId) {
            // package-change events handled by presenter
        }
    };

    /* Start changed by XDJA */
    private void initMagic() {
        try {
            String filePath = getApplicationContext().getFilesDir().getAbsolutePath();
            String fileName = filePath + "/magic.mgc";
            File f = new File(fileName);
            if (!f.exists()) {
                new File(filePath).mkdirs();
                new Thread().sleep(500);
                copyBigDataToSD(fileName);
            }
        } catch (IOException e) {
            Log.e("initMagic", "copy magicto sdcard is failed!");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void copyBigDataToSD(String strOutFileName) throws IOException {
        InputStream myInput;
        OutputStream myOutput = new FileOutputStream(strOutFileName);
        myInput = this.getAssets().open("magic.mgc");
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while (length > 0) {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();
    }
    /* End Changed by XDJA*/


    @Override
    protected void onResume() {
        super.onResume();
        hideLoadingModal();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // Final backup before destroy
        AppListBackupManager.get(this).backupAppList();
        
        unregisterReceiver(alarmReceiver);
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    private void bindViews() {
        mLoadingView = findViewById(R.id.pb_loading_app);
        mLauncherView = findViewById(R.id.home_launcher);
        mFabAdd = findViewById(R.id.fab_add_app);
        mFabLocation = findViewById(R.id.fab_location);
        mBottomArea = findViewById(R.id.bottom_area);
        mEnterSettingTextView = findViewById(R.id.enter_app_setting_text);
        mDeleteAppBox = findViewById(R.id.delete_app_area);
        mDeleteAppTextView = findViewById(R.id.delete_app_text);
        mAppCountText = findViewById(R.id.home_app_count);
        if (mBottomArea != null) {
            mBottomArea.setVisibility(View.GONE);
        }
        mFabAdd.setOnClickListener(v -> onAddAppButtonClick());
        mFabLocation.setOnClickListener(v -> {
            Intent intent = new Intent(this, io.virtualapp.home.location.LocationSettingsActivity.class);
            startActivity(intent);
        });
    }

    private void initLaunchpad() {
        mLauncherView.setHasFixedSize(false);
        // Linear list layout for MD3 card rows
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mLauncherView.setLayoutManager(layoutManager);
        mLaunchpadAdapter = new LaunchpadAdapter(this);
        SmartRecyclerAdapter wrap = new SmartRecyclerAdapter(mLaunchpadAdapter);
        View footer = new View(this);
        footer.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, VUiKit.dpToPx(this, 80)));
        wrap.setFooterView(footer);
        mLauncherView.setAdapter(wrap);
        ItemTouchHelper touchHelper = new ItemTouchHelper(new LauncherTouchCallback());
        touchHelper.attachToRecyclerView(mLauncherView);
        mLaunchpadAdapter.setAppClickListener((pos, data) -> {
            if (!data.isLoading()) {
                if (data instanceof AddAppButton) {
                    onAddAppButtonClick();
                    return;
                }
                showLoadingModal();
                mLaunchpadAdapter.notifyItemChanged(pos);
                mPresenter.launchApp(data);
            }
        });
    }

    private void onAddAppButtonClick() {
        ListAppActivity.gotoListApp(this);
    }

    private void deleteApp(int position) {
        AppData data = mLaunchpadAdapter.getList().get(position);
        View content = LayoutInflater.from(this).inflate(R.layout.dialog_md3_delete_clone, null, false);
        TextView messageView = content.findViewById(R.id.dialog_message);
        View cancelButton = content.findViewById(R.id.btn_cancel);
        View confirmButton = content.findViewById(R.id.btn_confirm);

        if (messageView != null) {
            messageView.setText(getString(R.string.delete_clone_dialog_message, data.getName()));
        }

        AlertDialog dialog = new AlertDialog.Builder(this, R.style.VACustomTheme)
                .setView(content)
                .create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        if (cancelButton != null) {
            cancelButton.setOnClickListener(v -> dialog.dismiss());
        }
        if (confirmButton != null) {
            confirmButton.setOnClickListener(v -> {
                dialog.dismiss();
                mPresenter.deleteApp(data);
            });
        }

        dialog.show();
    }

    private void showCloneOptionsSheet(AppData data, int position) {
        View content = LayoutInflater.from(this).inflate(R.layout.dialog_md3_clone_options, null, false);
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(content);

        TextView titleView = content.findViewById(R.id.dialog_title);
        View editButton = content.findViewById(R.id.btn_edit_spoof);
        View clearDataButton = content.findViewById(R.id.btn_clear_data);

        if (titleView != null) {
            CharSequence cloneName = data.getName();
            titleView.setText(TextUtils.isEmpty(cloneName)
                    ? getString(R.string.app_name)
                    : getString(R.string.manage_clone_title, cloneName));
        }

        if (clearDataButton != null) {
            clearDataButton.setVisibility(View.VISIBLE);
            clearDataButton.setOnClickListener(v -> {
                dialog.dismiss();
                mPresenter.clearCloneData(data);
            });
        }

        if (editButton != null) {
            editButton.setOnClickListener(v -> {
                dialog.dismiss();
                launchCloneEditor(data);
            });
        }
        dialog.show();
    }

    private void launchCloneEditor(AppData data) {
        Intent intent = new Intent(HomeActivity.this, CloneOnboardingActivity.class);
        intent.putExtra(CloneOnboardingActivity.EXTRA_PKG, data.getPackageName());
        intent.putExtra(CloneOnboardingActivity.EXTRA_USER, data.getUserId());
        intent.putExtra(CloneOnboardingActivity.EXTRA_LABEL, data.getName());
        intent.putExtra("is_edit", true);
        startActivityForResult(intent, CloneOnboardingActivity.REQUEST_CODE);
    }

    private void enterAppSetting(int position) {
        AppData model = mLaunchpadAdapter.getList().get(position);
        if (model instanceof PackageAppData || model instanceof MultiplePackageAppData) {
            mPresenter.enterAppSetting(model);
        }
    }

    @Override
    public void setPresenter(HomeContract.HomePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showPermissionDialog() {
        Intent intent = OemPermissionHelper.getPermissionActivityIntent(this);
        androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Notice")
                .setMessage("You must to grant permission to allowed launch 64bit Engine.")
                .setCancelable(false)
                .setNegativeButton("GO", (dialog13, which) -> {
                    try {
                        startActivity(intent);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }).create();
        
        // Apply MD3 styling
        dialog.setOnShowListener(d -> {
            if (dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE) != null) {
                dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(0xFF4FC3F7);
            }
        });
        
        dialog.show();
    }

    @Override
    public void showBottomAction() {
        if (mBottomArea != null) {
            mBottomArea.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideBottomAction() {
        if (mBottomArea != null) {
            mBottomArea.clearAnimation();
            mBottomArea.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoading() {
        if (mLoadingView != null) mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if (mLoadingView != null) mLoadingView.setVisibility(View.GONE);
    }
    
    public void showLoadingModal() {
        View modal = findViewById(R.id.modal_loading_overlay);
        if (modal != null) {
            modal.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoadingModal() {
        View modal = findViewById(R.id.modal_loading_overlay);
        if (modal != null) {
            modal.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadFinish(List<AppData> list) {
        mLaunchpadAdapter.setList(list);
        hideLoading();
        int count = list.size();
        if (mAppCountText != null) {
            mAppCountText.setText(count > 0 ? count + (count == 1 ? " app cloned" : " apps cloned") : "");
        }
        
        // Backup app list setelah successful load
        AppListBackupManager.get(this).backupAppList();
        
        // Show notification jika restore terjadi tapi list masih kosong
        if (mRestoredFromBackup && list.isEmpty()) {
            Toast.makeText(this, 
                "Apps were restored from backup but may need to be re-added", 
                Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(0, MENU_DIAGNOSTIC, 0, "Storage Diagnostics")
            .setShowAsAction(android.view.MenuItem.SHOW_AS_ACTION_NEVER);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == MENU_DIAGNOSTIC) {
            Intent intent = new Intent(this, StorageDiagnosticActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadError(Throwable err) {
        err.printStackTrace();
        hideLoading();
    }

    @Override
    public void showGuide() {

    }

    @Override
    public void addAppToLauncher(AppData model) {
        List<AppData> dataList = mLaunchpadAdapter.getList();
        boolean replaced = false;
        for (int i = 0; i < dataList.size(); i++) {
            AppData data = dataList.get(i);
            if (data instanceof EmptyAppData) {
                mLaunchpadAdapter.replace(i, model);
                replaced = true;
                break;
            }
        }
        if (!replaced) {
            mLaunchpadAdapter.add(model);
            mLauncherView.smoothScrollToPosition(mLaunchpadAdapter.getItemCount() - 1);
        }
    }


    @Override
    public void removeAppToLauncher(AppData model) {
        mLaunchpadAdapter.remove(model);
    }

    @Override
    public void refreshLauncherItem(AppData model) {
        mLaunchpadAdapter.refresh(model);
    }

    @Override
    public void askInstallGms() {
        if (!GmsSupport.isOutsideGoogleFrameworkExist()) {
            return;
        }
        if (GmsSupport.isInstalledGoogleService()) {
            return;
        }
        androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle(R.string.tip)
                .setMessage(R.string.text_install_gms)
                .setPositiveButton(android.R.string.ok, (dialog12, which) -> {
                    defer().when(() -> {
                        GmsSupport.installGApps(0);
                    }).done((res) -> {
                        mPresenter.dataChanged();
                    });
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setCancelable(false)
                .create();
        
        // Apply MD3 styling
        dialog.setOnShowListener(d -> {
            if (dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE) != null) {
                dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE).setTextColor(0xFF4FC3F7);
            }
            if (dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE) != null) {
                dialog.getButton(androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(0xFFC3C7CF);
            }
        });
        
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VCommends.REQUEST_SELECT_APP) {
            if (resultCode == RESULT_OK && data != null) {
                List<AppInfoLite> appList = data.getParcelableArrayListExtra(VCommends.EXTRA_APP_INFO_LIST);
                if (appList != null) {
                    for (AppInfoLite info : appList) {
                        mPresenter.addApp(info);
                    }
                }
                // Backup setelah add app
                AppListBackupManager.get(this).backupAppList();
            }
        } else if (requestCode == VCommends.REQUEST_PERMISSION) {
            if (resultCode == RESULT_OK) {
                String packageName = data.getStringExtra("pkg");
                int userId = data.getIntExtra("user_id", -1);
                VActivityManager.get().launchApp(userId, packageName);
            }
        } else if (requestCode == CloneOnboardingActivity.REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                String packageName = data.getStringExtra(CloneOnboardingActivity.EXTRA_PKG);
                int userId = data.getIntExtra(CloneOnboardingActivity.EXTRA_USER, 0);
                boolean isEdit = data.getBooleanExtra("is_edit", false);
                
                if (mPresenter instanceof HomePresenterImpl) {
                    if (isEdit) {
                        // Just edited spoof/name, apply spoof and refresh UI to show new name
                        io.virtualapp.home.repo.CloneSpoofRepository.get(this).applySpoof(packageName, userId);
                        mPresenter.dataChanged();
                    } else {
                        // First onboarding, apply and launch
                        ((HomePresenterImpl) mPresenter).onOnboardingComplete(packageName, userId);
                    }
                }
            }
        }
    }

    private class LauncherTouchCallback extends ItemTouchHelper.SimpleCallback {

        int[] location = new int[2];
        boolean upAtDeleteAppArea;
        boolean upAtEnterSettingArea;
        RecyclerView.ViewHolder dragHolder;

        LauncherTouchCallback() {
            // Drag: UP/DOWN only. Swipe: LEFT/RIGHT for clone removal options.
            super(UP | DOWN, LEFT | RIGHT);
        }

        @Override
        public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int viewSize, int viewSizeOutOfBounds, int totalSize, long msSinceStartScroll) {
            return 0;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            try {
                AppData data = mLaunchpadAdapter.getList().get(viewHolder.getAdapterPosition());
                if (!data.canReorder()) {
                    return makeMovementFlags(0, 0);
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
            return super.getMovementFlags(recyclerView, viewHolder);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int pos = viewHolder.getAdapterPosition();
            int targetPos = target.getAdapterPosition();
            mLaunchpadAdapter.moveItem(pos, targetPos);
            return true;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (viewHolder instanceof LaunchpadAdapter.ViewHolder) {
                if (actionState == ACTION_STATE_DRAG) {
                    if (dragHolder != viewHolder) {
                        dragHolder = viewHolder;
                        viewHolder.itemView.setScaleX(1.2f);
                        viewHolder.itemView.setScaleY(1.2f);
                    }
                }
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current, RecyclerView.ViewHolder target) {
            if (upAtEnterSettingArea || upAtDeleteAppArea) {
                return false;
            }
            try {
                AppData data = mLaunchpadAdapter.getList().get(target.getAdapterPosition());
                return data.canReorder();
            } catch (IndexOutOfBoundsException e) {
                //ignore
            }
            return false;
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof LaunchpadAdapter.ViewHolder) {
                viewHolder.itemView.setScaleX(1f);
                viewHolder.itemView.setScaleY(1f);
            }
            super.clearView(recyclerView, viewHolder);
            if (dragHolder == viewHolder) {
                dragHolder = null;
            }
        }


        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            if (position < 0 || mLaunchpadAdapter.getList() == null) return;

            mLaunchpadAdapter.notifyItemChanged(position);

            AppData data = mLaunchpadAdapter.getList().get(position);
            if (!(data instanceof PackageAppData) && !(data instanceof MultiplePackageAppData)) return;

            if (direction == RIGHT) {
                showCloneOptionsSheet(data, position);
                return;
            }

            deleteApp(position);
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && Math.abs(dX) > 10) {
                drawSwipeIndicator(c, viewHolder, dX);
            }
            if (isCurrentlyActive && Math.abs(dX) > 10) {
                float alpha = Math.max(0.4f, 1f - Math.abs(dX) / (viewHolder.itemView.getWidth() * 0.6f));
                viewHolder.itemView.setAlpha(alpha);
            } else {
                viewHolder.itemView.setAlpha(1f);
            }
        }

        private void drawSwipeIndicator(Canvas c, RecyclerView.ViewHolder viewHolder, float dX) {
            View itemView = viewHolder.itemView;
            boolean edit = dX > 0;
            mSwipePaint.setColor(edit ? 0xFF2196F3 : 0xFFE53935);
            if (edit) {
                c.drawRect(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + dX, itemView.getBottom(), mSwipePaint);
            } else {
                c.drawRect(itemView.getRight() + dX, itemView.getTop(), itemView.getRight(), itemView.getBottom(), mSwipePaint);
            }

            Drawable icon = ContextCompat.getDrawable(HomeActivity.this,
                    edit ? android.R.drawable.ic_menu_manage : android.R.drawable.ic_menu_delete);
            int iconMargin = VUiKit.dpToPx(HomeActivity.this, 20);
            int iconSize = VUiKit.dpToPx(HomeActivity.this, 24);
            int iconTop = itemView.getTop() + (itemView.getHeight() - iconSize) / 2;
            int iconLeft = edit ? itemView.getLeft() + iconMargin : itemView.getRight() - iconMargin - iconSize;
            if (icon != null) {
                icon.setBounds(iconLeft, iconTop, iconLeft + iconSize, iconTop + iconSize);
                icon.draw(c);
            }

            mSwipeTextPaint.setColor(Color.WHITE);
            mSwipeTextPaint.setTextSize(VUiKit.dpToPx(HomeActivity.this, 14));
            mSwipeTextPaint.setFakeBoldText(true);
            String label = edit ? "MANAGE" : "DELETE";
            Rect bounds = new Rect();
            mSwipeTextPaint.getTextBounds(label, 0, label.length(), bounds);
            float textY = itemView.getTop() + (itemView.getHeight() + bounds.height()) / 2f;
            float textX = edit
                    ? iconLeft + iconSize + VUiKit.dpToPx(HomeActivity.this, 12)
                    : iconLeft - bounds.width() - VUiKit.dpToPx(HomeActivity.this, 12);
            c.drawText(label, textX, textY, mSwipeTextPaint);
        }
    }
}
