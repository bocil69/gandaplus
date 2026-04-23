package com.fufufu.katrina.backup;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
/* loaded from: classes5.dex */
public class ShortcutExecutorActivity extends AppCompatActivity {
    private AlertDialog UNIVERSAL;
    private OnCompleteListener<AuthResult> _katrina_user_create_user_listener;
    private OnCompleteListener<Void> _katrina_user_reset_password_listener;
    private OnCompleteListener<AuthResult> _katrina_user_sign_in_listener;
    public ActivityManager amanager;
    private FirebaseAuth katrina_user;
    private OnCompleteListener<Void> katrina_user_deleteUserListener;
    private OnCompleteListener<Void> katrina_user_emailVerificationSentListener;
    private OnCompleteListener<AuthResult> katrina_user_googleSignInListener;
    private OnCompleteListener<AuthResult> katrina_user_phoneAuthListener;
    private OnCompleteListener<Void> katrina_user_updateEmailListener;
    private OnCompleteListener<Void> katrina_user_updatePasswordListener;
    private OnCompleteListener<Void> katrina_user_updateProfileListener;
    private LinearLayout ln_base_shortcut;
    private MyMODPESOFF myMODPESOFF;
    private MyMODPESON myMODPESON;
    private MyRESETO myRESETO;
    private MyTIMEPICKTASK myTIMEPICKTASK;
    private MyWIPEGMS myWIPEGMS;
    private MyWIPEMASS myWIPEMASS;
    private MyWIPEROOT myWIPEROOT;
    private NestedScrollView nestscroll;
    private ProgressBar pbar_command;
    private SharedPreferences prefwipemass;
    private Runnable runnableCommand;
    private TextView tv_cleaning_subtitle;
    private TextView tv_cleaning_title;
    private TextView tv_command_result;
    private Vibrator vibrator;
    private String s_url = "";
    private String s_wipe = "";
    private String s_command = "";
    private String s_commandResult = "";
    private String s_exitCode = "";
    private boolean b_command = false;
    private String s_universal_progress = "";
    private String s_commandBase = "";
    private String s_timepick = "";
    private String s_open = "";
    private HashMap<String, Object> m_prefwipemass = new HashMap<>();
    private String s_extra = "";
    private boolean b_dialog = false;
    private ArrayList<HashMap<String, Object>> lm_prefwipemass = new ArrayList<>();
    private Intent iShortcut = new Intent();
    private Intent intent_ritual = new Intent();
    private Handler Command = new Handler();

    public void _EXTRA() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d00be);
        initialize(bundle);
        FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {
        this.ln_base_shortcut = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0273);
        this.tv_cleaning_title = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04fa);
        this.tv_cleaning_subtitle = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04f9);
        this.pbar_command = (ProgressBar) findViewById(R.id.admsoloraya_res_0x7f0a03ea);
        this.nestscroll = (NestedScrollView) findViewById(R.id.admsoloraya_res_0x7f0a03cb);
        this.tv_command_result = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04fe);
        this.katrina_user = FirebaseAuth.getInstance();
        this.vibrator = (Vibrator) getSystemService("vibrator");
        this.prefwipemass = getSharedPreferences("wipemass_preferences", 0);
        this.katrina_user_updateEmailListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_updatePasswordListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_emailVerificationSentListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_deleteUserListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.4
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_phoneAuthListener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_updateProfileListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.6
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_googleSignInListener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.7
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this._katrina_user_create_user_listener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.8
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this._katrina_user_sign_in_listener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.9
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this._katrina_user_reset_password_listener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.10
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
            }
        };
    }

    private void initializeLogic() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(0);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.setNavigationBarColor(0);
        }
        getWindow().getDecorView().setSystemUiVisibility(8208);
        this.b_dialog = false;
        _setFirstUI();
        _getExtra();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        _hideUniversalProgress();
    }

    public void _getExtra() {
        this.tv_command_result.setText("");
        if ("a d m s o l o r a y a" != 0) {
            if (getIntent().hasExtra("shortcut_command")) {
                if (getIntent().getStringExtra("shortcut_desc").equals("openlink")) {
                    _intentOpenLink();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("wipe_manual")) {
                    _intentWipeManual();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("wipe_root")) {
                    _intentWipeRoot();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("open_app")) {
                    _intentOpenApp();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("wipegms")) {
                    _intentWipeGms();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("timepick")) {
                    _intentTimepick();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("fastreboot")) {
                    this.amanager = (ActivityManager) getSystemService("activity");
                    _intentFastReboot();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("reseto")) {
                    _intentReseto();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("killall")) {
                    this.iShortcut.setClass(getApplicationContext(), KillActivity.class);
                    startActivity(this.iShortcut);
                    finish();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("modpes")) {
                    _intentModePesawat();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("otp")) {
                    this.iShortcut.setClass(getApplicationContext(), OtpActivity.class);
                    startActivity(this.iShortcut);
                    finish();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("katrina")) {
                    this.iShortcut.setClass(getApplicationContext(), MainActivity.class);
                    startActivity(this.iShortcut);
                    finish();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("refufu")) {
                    try {
                        startActivity(new Intent().setClassName("com.google.android.apps.googleassistantx", "com.google.android.apps.googleassistantx.MainActivityPro"));
                        finish();
                        return;
                    } catch (ActivityNotFoundException unused) {
                        SketchwareUtil.showMessage(getApplicationContext(), "Aplikasi tidak ditemukan");
                        finish();
                        return;
                    }
                } else if (getIntent().getStringExtra("shortcut_desc").equals("fakegps")) {
                    SketchwareUtil.showMessage(getApplicationContext(), "Belum tersedia");
                    finish();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("editprop")) {
                    this.iShortcut.setClass(getApplicationContext(), SystemPropActivity.class);
                    startActivity(this.iShortcut);
                    finish();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("wipemass")) {
                    _intentWipeMass();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("wipemass_dialog")) {
                    _intentWipeMassDialog();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("ritual")) {
                    _intentRitualFragment();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("backup")) {
                    _intentBackup();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("restore")) {
                    _intentRestore();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("finish")) {
                    finish();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("launch_act")) {
                    _intentLaunchActivity();
                    return;
                } else if (getIntent().getStringExtra("shortcut_desc").equals("dynamic")) {
                    _intentDynamic();
                    return;
                } else {
                    finish();
                    return;
                }
            }
            return;
        }
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void _intentOpenLink() {
        String stringExtra = getIntent().getStringExtra("shortcut_command");
        this.s_url = stringExtra;
        if (!TextUtils.isEmpty(stringExtra) && !this.s_url.startsWith("http://") && !this.s_url.startsWith("https://")) {
            this.s_url = "http://" + this.s_url;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.s_url));
        this.iShortcut = intent;
        intent.addFlags(268435456);
        if (getPackageManager().queryIntentActivities(this.iShortcut, 0).size() > 0) {
            startActivity(this.iShortcut);
        } else {
            Toast.makeText(this, "Tidak ada browser untuk membuka link", 0).show();
        }
        finish();
    }

    public void updateTextRitual() {
        this.nestscroll.post(new Runnable() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.11
            @Override // java.lang.Runnable
            public void run() {
                ShortcutExecutorActivity.this.nestscroll.fullScroll(130);
            }
        });
    }

    public void _finish() {
        finish();
    }

    public void _intentWipeManual() {
        this.s_wipe = getIntent().getStringExtra("shortcut_command");
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        this.iShortcut = intent;
        intent.setData(Uri.parse("package:" + this.s_wipe));
        this.iShortcut.addFlags(268435456);
        startActivity(this.iShortcut);
        finish();
    }

    public void _intentWipeRoot() {
        MyWIPEROOT myWIPEROOT = this.myWIPEROOT;
        if (myWIPEROOT != null && myWIPEROOT.isRunning) {
            this.myWIPEROOT.cancelWIPEROOTTask();
        }
        MyWIPEROOT myWIPEROOT2 = new MyWIPEROOT();
        this.myWIPEROOT = myWIPEROOT2;
        myWIPEROOT2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyWIPEROOT extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyWIPEROOT() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_wipe = shortcutExecutorActivity.getIntent().getStringExtra("shortcut_command");
            ShortcutExecutorActivity shortcutExecutorActivity2 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity2.s_universal_progress = "Wipe data aplikasi \n".concat(shortcutExecutorActivity2.s_wipe);
            ShortcutExecutorActivity.this._showUniversalProgress();
            ShortcutExecutorActivity.this.s_command = "app_package=\"fupackagename\"\nandroid_data=\"/storage/emulated/0/Android/data\"\nam force-stop $app_package > /dev/null 2>&1\n\nif pm clear $app_package > /dev/null 2>&1; then\nrm -rf $android_data/$app_package > /dev/null 2>&1;\necho -n \"Sukses menghapus data dan cache aplikasi\"\nelse\necho -n \"Gagal membersihkan data dan cache aplikasi\"\nfi";
            ShortcutExecutorActivity shortcutExecutorActivity3 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity3.s_command = shortcutExecutorActivity3.s_command.replace("fupackagename", ShortcutExecutorActivity.this.s_wipe);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_command;
            ShortcutExecutorActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(ShortcutExecutorActivity.this.s_command).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            ShortcutExecutorActivity.this.b_command = exec.isSuccess();
            ShortcutExecutorActivity.this.s_commandResult = ShortcutExecutorActivity$MyWIPEROOT$$ExternalSyntheticBackport0.m("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            ShortcutExecutorActivity.this._onFinishDialog();
        }

        public void cancelWIPEROOTTask() {
            cancel(true);
        }
    }

    public void _showUniversalProgress() {
        showUNIVERSAL();
    }

    private void showUNIVERSAL() {
        View inflate = getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d002c, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a059f);
        textView.setTextSize(10.0f);
        textView.setText(this.s_universal_progress);
        AlertDialog create = materialAlertDialogBuilder.create();
        this.UNIVERSAL = create;
        create.show();
    }

    public void _hideUniversalProgress() {
        AlertDialog alertDialog = this.UNIVERSAL;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.UNIVERSAL.dismiss();
    }

    public void _intentFastReboot() {
        this.vibrator.vibrate(100L);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Restarting apps...");
        progressDialog.show();
        final List<String> apparray = getApparray(this);
        final StringBuilder sb = new StringBuilder();
        final List<ApplicationInfo> applist = getApplist(this);
        final List<ActivityManager.RunningServiceInfo> runningServices = this.amanager.getRunningServices(2000);
        new Thread(new Runnable() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.12
            @Override // java.lang.Runnable
            public void run() {
                for (ApplicationInfo applicationInfo : applist) {
                    if (!apparray.contains(applicationInfo.packageName)) {
                        ShortcutExecutorActivity.this.amanager.killBackgroundProcesses(applicationInfo.processName);
                        StringBuilder sb2 = sb;
                        sb2.append("• ");
                        sb2.append(applicationInfo.loadLabel(ShortcutExecutorActivity.this.getPackageManager()).toString());
                        sb2.append("\n");
                    }
                }
                for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                    if (!apparray.contains(runningServiceInfo.process)) {
                        try {
                            PackageInfo packageInfo = ShortcutExecutorActivity.this.getPackageManager().getPackageInfo(runningServiceInfo.process, 0);
                            ShortcutExecutorActivity.this.amanager.killBackgroundProcesses(runningServiceInfo.process);
                            StringBuilder sb3 = sb;
                            sb3.append("• ");
                            sb3.append(packageInfo.applicationInfo.loadLabel(ShortcutExecutorActivity.this.getPackageManager()).toString());
                            sb3.append("\n");
                        } catch (PackageManager.NameNotFoundException unused) {
                        }
                    }
                }
                ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
                final ProgressDialog progressDialog2 = progressDialog;
                final StringBuilder sb4 = sb;
                shortcutExecutorActivity.runOnUiThread(new Runnable() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.12.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (progressDialog2.isShowing()) {
                            progressDialog2.cancel();
                        }
                        ShortcutExecutorActivity.this.resultDialog(true, sb4.toString());
                    }
                });
            }
        }).start();
    }

    public static List<ApplicationInfo> getApplist(Context context) {
        return context.getPackageManager().getInstalledApplications(128);
    }

    public static List<String> getApparray(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(context.getPackageName());
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        if (!queryIntentActivities.isEmpty()) {
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                arrayList.add(resolveInfo.activityInfo.packageName);
            }
        }
        return arrayList;
    }

    public void resultDialog(boolean z, String str) {
        new MaterialAlertDialogBuilder(this).setMessage((CharSequence) str).setPositiveButton((CharSequence) "Oke", new DialogInterface.OnClickListener() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ShortcutExecutorActivity.this.finish();
            }
        }).setNegativeButton((CharSequence) "Ulangi", new DialogInterface.OnClickListener() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.14
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ShortcutExecutorActivity.this._intentFastReboot();
                dialogInterface.dismiss();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.15
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                ShortcutExecutorActivity.this.finish();
            }
        }).setTitle((CharSequence) (z ? "Restart App:" : null)).show();
    }

    public void _intentModePesawat() {
        displaySignalStatus(this);
    }

    private boolean isAirplaneModeOn(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0;
    }

    private void displaySignalStatus(Context context) {
        if (isAirplaneModeOn(context)) {
            _onModpesOn();
        } else {
            _onModpesOff();
        }
    }

    public void _intentTimepick() {
        MyTIMEPICKTASK myTIMEPICKTASK = this.myTIMEPICKTASK;
        if (myTIMEPICKTASK != null && myTIMEPICKTASK.isRunning) {
            this.myTIMEPICKTASK.cancelTIMEPICKTASKTask();
        }
        MyTIMEPICKTASK myTIMEPICKTASK2 = new MyTIMEPICKTASK();
        this.myTIMEPICKTASK = myTIMEPICKTASK2;
        myTIMEPICKTASK2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyTIMEPICKTASK extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyTIMEPICKTASK() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            ShortcutExecutorActivity.this.ln_base_shortcut.setVisibility(0);
            ShortcutExecutorActivity.this.nestscroll.setVisibility(8);
            ShortcutExecutorActivity.this.tv_cleaning_title.setTextColor(-565927);
            ShortcutExecutorActivity.this.tv_cleaning_subtitle.setTextColor(-565927);
            ShortcutExecutorActivity.this.tv_cleaning_title.setText("Cleaning\nProses...");
            ShortcutExecutorActivity.this.tv_cleaning_subtitle.setText("Memetakan file dan cache");
            ShortcutExecutorActivity.this.s_commandResult = "";
            ShortcutExecutorActivity.this.b_command = false;
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_commandBase.concat("\nritualcln");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_command;
            ShortcutExecutorActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(ShortcutExecutorActivity.this.s_command).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            ShortcutExecutorActivity.this.b_command = exec.isSuccess();
            ShortcutExecutorActivity.this.s_commandResult = ShortcutExecutorActivity$MyTIMEPICKTASK$$ExternalSyntheticBackport0.m("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r3) {
            this.isRunning = false;
            if (ShortcutExecutorActivity.this.b_command) {
                ShortcutExecutorActivity.this.nestscroll.setVisibility(0);
                ShortcutExecutorActivity.this.pbar_command.setVisibility(8);
                ShortcutExecutorActivity.this.tv_cleaning_title.setTextColor(-11417769);
                ShortcutExecutorActivity.this.tv_cleaning_subtitle.setTextColor(-11417769);
                ShortcutExecutorActivity.this.tv_cleaning_title.setText("Selesai");
                ShortcutExecutorActivity.this.tv_cleaning_subtitle.setText("Berhasil membersihkan /data/*/* && /storage/*/*");
                ShortcutExecutorActivity.this.tv_command_result.setText(ShortcutExecutorActivity.this.s_commandResult);
                ShortcutExecutorActivity.this.updateTextRitual();
                ShortcutExecutorActivity.this.vibrator.vibrate(100L);
            }
        }

        public void cancelTIMEPICKTASKTask() {
            cancel(true);
        }
    }

    public void _intentWipeGms() {
        MyWIPEGMS myWIPEGMS = this.myWIPEGMS;
        if (myWIPEGMS != null && myWIPEGMS.isRunning) {
            this.myWIPEGMS.cancelWIPEGMSTask();
        }
        MyWIPEGMS myWIPEGMS2 = new MyWIPEGMS();
        this.myWIPEGMS = myWIPEGMS2;
        myWIPEGMS2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyWIPEGMS extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyWIPEGMS() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            ShortcutExecutorActivity.this.s_wipe = "All GMS";
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_universal_progress = "Wipe data aplikasi \n".concat(shortcutExecutorActivity.s_wipe);
            ShortcutExecutorActivity.this._showUniversalProgress();
            ShortcutExecutorActivity shortcutExecutorActivity2 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity2.s_command = shortcutExecutorActivity2.s_commandBase;
            ShortcutExecutorActivity shortcutExecutorActivity3 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity3.s_command = shortcutExecutorActivity3.s_command.concat("\nowipegms");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_command;
            ShortcutExecutorActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(ShortcutExecutorActivity.this.s_command).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            ShortcutExecutorActivity.this.b_command = exec.isSuccess();
            ShortcutExecutorActivity.this.s_commandResult = ShortcutExecutorActivity$MyWIPEGMS$$ExternalSyntheticBackport0.m("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            ShortcutExecutorActivity.this._onFinishDialog();
        }

        public void cancelWIPEGMSTask() {
            cancel(true);
        }
    }

    public void _intentReseto() {
        MyRESETO myRESETO = this.myRESETO;
        if (myRESETO != null && myRESETO.isRunning) {
            this.myRESETO.cancelRESETOTask();
        }
        MyRESETO myRESETO2 = new MyRESETO();
        this.myRESETO = myRESETO2;
        myRESETO2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyRESETO extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyRESETO() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            ShortcutExecutorActivity.this.s_wipe = "dan reboot perangkat";
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_universal_progress = "Reset 0 ".concat(shortcutExecutorActivity.s_wipe);
            ShortcutExecutorActivity.this._showUniversalProgress();
            ShortcutExecutorActivity shortcutExecutorActivity2 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity2.s_command = shortcutExecutorActivity2.s_commandBase;
            ShortcutExecutorActivity shortcutExecutorActivity3 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity3.s_command = shortcutExecutorActivity3.s_command.replace("fupackagename", ShortcutExecutorActivity.this.s_wipe);
            ShortcutExecutorActivity shortcutExecutorActivity4 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity4.s_command = shortcutExecutorActivity4.s_command.concat("\nooooonol\nooreboot");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_command;
            ShortcutExecutorActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(ShortcutExecutorActivity.this.s_command).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            ShortcutExecutorActivity.this.b_command = exec.isSuccess();
            ShortcutExecutorActivity.this.s_commandResult = ShortcutExecutorActivity$MyRESETO$$ExternalSyntheticBackport0.m("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            ShortcutExecutorActivity.this._onFinish();
        }

        public void cancelRESETOTask() {
            cancel(true);
        }
    }

    public void _setFirstUI() {
        this.ln_base_shortcut.setVisibility(8);
        try {
            String copyFromInputStream = SketchwareUtil.copyFromInputStream(getAssets().open("prop.sh"));
            this.s_commandBase = copyFromInputStream;
            String replace = copyFromInputStream.replace("Ĩ", "a");
            this.s_commandBase = replace;
            String replace2 = replace.replace("ĩ", "b");
            this.s_commandBase = replace2;
            String replace3 = replace2.replace("Ī", "c");
            this.s_commandBase = replace3;
            String replace4 = replace3.replace("ī", "d");
            this.s_commandBase = replace4;
            String replace5 = replace4.replace("Ĭ", "e");
            this.s_commandBase = replace5;
            String replace6 = replace5.replace("ĭ", "f");
            this.s_commandBase = replace6;
            String replace7 = replace6.replace("Į", "g");
            this.s_commandBase = replace7;
            String replace8 = replace7.replace("į", "h");
            this.s_commandBase = replace8;
            String replace9 = replace8.replace("ĺ", "i");
            this.s_commandBase = replace9;
            String replace10 = replace9.replace("ļ", "j");
            this.s_commandBase = replace10;
            String replace11 = replace10.replace("ľ", "k");
            this.s_commandBase = replace11;
            String replace12 = replace11.replace("ŀ", "l");
            this.s_commandBase = replace12;
            String replace13 = replace12.replace("Ǐ", "m");
            this.s_commandBase = replace13;
            String replace14 = replace13.replace("ǐ", "n");
            this.s_commandBase = replace14;
            String replace15 = replace14.replace("Ȉ", "o");
            this.s_commandBase = replace15;
            String replace16 = replace15.replace("ȉ", "p");
            this.s_commandBase = replace16;
            String replace17 = replace16.replace("Ȋ", "q");
            this.s_commandBase = replace17;
            String replace18 = replace17.replace("ȋ", "r");
            this.s_commandBase = replace18;
            String replace19 = replace18.replace("ɭ", "s");
            this.s_commandBase = replace19;
            String replace20 = replace19.replace("ΐ", "t");
            this.s_commandBase = replace20;
            String replace21 = replace20.replace("ᴉ", "u");
            this.s_commandBase = replace21;
            String replace22 = replace21.replace("Ḭ", "v");
            this.s_commandBase = replace22;
            String replace23 = replace22.replace("ḭ", "w");
            this.s_commandBase = replace23;
            String replace24 = replace23.replace("Ḯ", "x");
            this.s_commandBase = replace24;
            String replace25 = replace24.replace("ḯ", "y");
            this.s_commandBase = replace25;
            String replace26 = replace25.replace("ḷ", "z");
            this.s_commandBase = replace26;
            String replace27 = replace26.replace("ḹ", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
            this.s_commandBase = replace27;
            String replace28 = replace27.replace("ḻ", "B");
            this.s_commandBase = replace28;
            String replace29 = replace28.replace("ḽ", "C");
            this.s_commandBase = replace29;
            String replace30 = replace29.replace("Ỉ", "D");
            this.s_commandBase = replace30;
            String replace31 = replace30.replace("ỉ", ExifInterface.LONGITUDE_EAST);
            this.s_commandBase = replace31;
            String replace32 = replace31.replace("Ị", "F");
            this.s_commandBase = replace32;
            String replace33 = replace32.replace("ị", "G");
            this.s_commandBase = replace33;
            String replace34 = replace33.replace("ἰ", "H");
            this.s_commandBase = replace34;
            String replace35 = replace34.replace("ἱ", "I");
            this.s_commandBase = replace35;
            String replace36 = replace35.replace("ἲ", "J");
            this.s_commandBase = replace36;
            String replace37 = replace36.replace("ἳ", "K");
            this.s_commandBase = replace37;
            String replace38 = replace37.replace("ἴ", "L");
            this.s_commandBase = replace38;
            String replace39 = replace38.replace("ἵ", "M");
            this.s_commandBase = replace39;
            String replace40 = replace39.replace("ἶ", "N");
            this.s_commandBase = replace40;
            String replace41 = replace40.replace("ἷ", "O");
            this.s_commandBase = replace41;
            String replace42 = replace41.replace("Ἱ", "P");
            this.s_commandBase = replace42;
            String replace43 = replace42.replace("Ἲ", "Q");
            this.s_commandBase = replace43;
            String replace44 = replace43.replace("Ἳ", "R");
            this.s_commandBase = replace44;
            String replace45 = replace44.replace("Ἴ", ExifInterface.LATITUDE_SOUTH);
            this.s_commandBase = replace45;
            String replace46 = replace45.replace("Ἵ", ExifInterface.GPS_DIRECTION_TRUE);
            this.s_commandBase = replace46;
            String replace47 = replace46.replace("Ἶ", "U");
            this.s_commandBase = replace47;
            String replace48 = replace47.replace("Ἷ", ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
            this.s_commandBase = replace48;
            String replace49 = replace48.replace("ὶ", ExifInterface.LONGITUDE_WEST);
            this.s_commandBase = replace49;
            String replace50 = replace49.replace("ί", "X");
            this.s_commandBase = replace50;
            String replace51 = replace50.replace("ῐ", "Y");
            this.s_commandBase = replace51;
            String replace52 = replace51.replace("ῑ", "Z");
            this.s_commandBase = replace52;
            String replace53 = replace52.replace("ῒ", "_");
            this.s_commandBase = replace53;
            String replace54 = replace53.replace("ΐ", "=");
            this.s_commandBase = replace54;
            String replace55 = replace54.replace("ῖ", "#");
            this.s_commandBase = replace55;
            String replace56 = replace55.replace("ῗ", "-");
            this.s_commandBase = replace56;
            String replace57 = replace56.replace("Ῐ", "/");
            this.s_commandBase = replace57;
            String replace58 = replace57.replace("Ῑ", "+");
            this.s_commandBase = replace58;
            String replace59 = replace58.replace("Ὶ", "&");
            this.s_commandBase = replace59;
            String replace60 = replace59.replace("Ί", ".");
            this.s_commandBase = replace60;
            String replace61 = replace60.replace("ⅱ", "");
            this.s_commandBase = replace61;
            this.s_commandBase = replace61.replace("futhispackage", getApplicationContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.lm_prefwipemass.clear();
        Map<String, ?> all = this.prefwipemass.getAll();
        for (String str : all.keySet()) {
            HashMap<String, Object> hashMap = new HashMap<>();
            this.m_prefwipemass = hashMap;
            hashMap.put("key_app", str);
            this.m_prefwipemass.put("value_app", all.get(str).toString());
            this.lm_prefwipemass.add(this.m_prefwipemass);
        }
    }

    public void _intentOpenApp() {
        String stringExtra = getIntent().getStringExtra("shortcut_command");
        this.s_open = stringExtra;
        if (stringExtra != null) {
            try {
                Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(this.s_open);
                if (launchIntentForPackage != null) {
                    startActivity(launchIntentForPackage);
                } else {
                    Toast.makeText(this, "Aplikasi tidak ditemukan", 0).show();
                }
            } catch (Exception unused) {
                Toast.makeText(this, "Terjadi kesalahan saat mencoba membuka aplikasi", 0).show();
            }
        } else {
            Toast.makeText(this, "Package name tidak ditemukan", 0).show();
        }
        finish();
    }

    public void _intentWipeMass() {
        MyWIPEMASS myWIPEMASS = this.myWIPEMASS;
        if (myWIPEMASS != null && myWIPEMASS.isRunning) {
            this.myWIPEMASS.cancelWIPEMASSTask();
        }
        MyWIPEMASS myWIPEMASS2 = new MyWIPEMASS();
        this.myWIPEMASS = myWIPEMASS2;
        myWIPEMASS2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyWIPEMASS extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyWIPEMASS() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.prefwipemass.getString(ShortcutExecutorActivity.this.getIntent().getStringExtra("shortcut_command"), "");
            ShortcutExecutorActivity shortcutExecutorActivity2 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity2.s_universal_progress = shortcutExecutorActivity2.s_command.replace(" am force-stop ", "");
            ShortcutExecutorActivity shortcutExecutorActivity3 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity3.s_universal_progress = shortcutExecutorActivity3.s_universal_progress.replace(" pm clear ", "");
            ShortcutExecutorActivity shortcutExecutorActivity4 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity4.s_universal_progress = shortcutExecutorActivity4.s_universal_progress.replace(" ", "");
            ShortcutExecutorActivity shortcutExecutorActivity5 = ShortcutExecutorActivity.this;
            shortcutExecutorActivity5.s_universal_progress = shortcutExecutorActivity5.s_universal_progress.replace(";", "\n");
            ShortcutExecutorActivity.this._showUniversalProgress();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_command;
            ShortcutExecutorActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(ShortcutExecutorActivity.this.s_command).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            ShortcutExecutorActivity.this.b_command = exec.isSuccess();
            ShortcutExecutorActivity.this.s_commandResult = ShortcutExecutorActivity$MyWIPEMASS$$ExternalSyntheticBackport0.m("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            ShortcutExecutorActivity.this._onFinishDialog();
        }

        public void cancelWIPEMASSTask() {
            cancel(true);
        }
    }

    public void _intentWipeMassDialog() {
        this.s_extra = new Gson().toJson(this.lm_prefwipemass);
        WipemassDialogFragmentActivity wipemassDialogFragmentActivity = new WipemassDialogFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("extrakey", this.s_extra);
        wipemassDialogFragmentActivity.setArguments(bundle);
        wipemassDialogFragmentActivity.show(getSupportFragmentManager(), "WipemassDialogFragmentActivity1");
    }

    public void _intentRitualFragment() {
        this.intent_ritual.setClass(getApplicationContext(), AddShortcutActivity.class);
        this.intent_ritual.putExtra("ritual_editor", "ritual_editor");
        startActivity(this.intent_ritual);
        finish();
    }

    public void _onFinish() {
        while (this.b_command) {
            this.b_command = false;
            this.vibrator.vibrate(100L);
            finish();
        }
    }

    public void _onFinishDialog() {
        Runnable runnable = new Runnable() { // from class: com.fufufu.katrina.backup.ShortcutExecutorActivity.16
            @Override // java.lang.Runnable
            public void run() {
                if (ShortcutExecutorActivity.this.b_command) {
                    ShortcutExecutorActivity.this.Command.removeCallbacks(ShortcutExecutorActivity.this.runnableCommand);
                    ShortcutExecutorActivity.this.b_command = false;
                    ShortcutExecutorActivity.this.vibrator.vibrate(100L);
                    ShortcutExecutorActivity.this._hideUniversalProgress();
                    ShortcutExecutorActivity.this.finish();
                    return;
                }
                ShortcutExecutorActivity.this.Command.postDelayed(ShortcutExecutorActivity.this.runnableCommand, 100L);
            }
        };
        this.runnableCommand = runnable;
        this.Command.postDelayed(runnable, 0L);
    }

    public void _onModpesOn() {
        MyMODPESON myMODPESON = this.myMODPESON;
        if (myMODPESON != null && myMODPESON.isRunning) {
            this.myMODPESON.cancelMODPESONTask();
        }
        MyMODPESON myMODPESON2 = new MyMODPESON();
        this.myMODPESON = myMODPESON2;
        myMODPESON2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyMODPESON extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyMODPESON() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            ShortcutExecutorActivity.this.s_command = "settings put global airplane_mode_on 0\nam broadcast -a android.intent.action.AIRPLANE_MODE";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_command;
            ShortcutExecutorActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(ShortcutExecutorActivity.this.s_command).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            ShortcutExecutorActivity.this.b_command = exec.isSuccess();
            ShortcutExecutorActivity.this.s_commandResult = ShortcutExecutorActivity$MyMODPESON$$ExternalSyntheticBackport0.m("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            ShortcutExecutorActivity.this._onFinish();
        }

        public void cancelMODPESONTask() {
            cancel(true);
        }
    }

    public void _onModpesOff() {
        MyMODPESOFF myMODPESOFF = this.myMODPESOFF;
        if (myMODPESOFF != null && myMODPESOFF.isRunning) {
            this.myMODPESOFF.cancelMODPESOFFTask();
        }
        MyMODPESOFF myMODPESOFF2 = new MyMODPESOFF();
        this.myMODPESOFF = myMODPESOFF2;
        myMODPESOFF2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyMODPESOFF extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyMODPESOFF() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            ShortcutExecutorActivity.this.s_command = "settings put global airplane_mode_on 1\nam broadcast -a android.intent.action.AIRPLANE_MODE";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ShortcutExecutorActivity shortcutExecutorActivity = ShortcutExecutorActivity.this;
            shortcutExecutorActivity.s_command = shortcutExecutorActivity.s_command;
            ShortcutExecutorActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(ShortcutExecutorActivity.this.s_command).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            ShortcutExecutorActivity.this.b_command = exec.isSuccess();
            ShortcutExecutorActivity.this.s_commandResult = ShortcutExecutorActivity$MyMODPESOFF$$ExternalSyntheticBackport0.m("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            ShortcutExecutorActivity.this._onFinish();
        }

        public void cancelMODPESOFFTask() {
            cancel(true);
        }
    }

    public void _intentBackup() {
        ScbackupDialogFragmentActivity scbackupDialogFragmentActivity = new ScbackupDialogFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("extrakey", "");
        scbackupDialogFragmentActivity.setArguments(bundle);
        scbackupDialogFragmentActivity.show(getSupportFragmentManager(), "ScbackupDialogFragmentActivity1");
    }

    public void _intentRestore() {
        ScrestoreDialogFragmentActivity screstoreDialogFragmentActivity = new ScrestoreDialogFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("extrakey", "");
        screstoreDialogFragmentActivity.setArguments(bundle);
        screstoreDialogFragmentActivity.show(getSupportFragmentManager(), "ScrestoreDialogFragmentActivity1");
    }

    public void _intentLaunchActivity() {
        String concat = "su -c am start ".concat(getIntent().getStringExtra("shortcut_command"));
        this.s_command = concat;
        this.b_command = false;
        Shell.Result exec = Shell.cmd(concat).exec();
        List<String> out = exec.getOut();
        exec.getCode();
        this.b_command = exec.isSuccess();
        this.s_commandResult = ShortcutExecutorActivity$$ExternalSyntheticBackport0.m("\n", out);
        finish();
    }

    public void _intentDynamic() {
        if (getIntent().getStringExtra("shortcut_command").equals("reboot")) {
            this.s_command = "am start -a android.intent.action.REBOOT";
        } else if (getIntent().getStringExtra("shortcut_command").equals("ssaid")) {
            this.s_command = "rm -rf /data/system/users/0/settings_ssaid.xml\nrm -rf /data/system/users/0/settings_ssaid.xml.fallback";
        } else if (getIntent().getStringExtra("shortcut_command").equals("recovery")) {
            this.s_command = "reboot recovery";
        } else {
            finish();
        }
        String str = this.s_command;
        this.s_command = str;
        this.b_command = false;
        Shell.Result exec = Shell.cmd(str).exec();
        List<String> out = exec.getOut();
        exec.getCode();
        this.b_command = exec.isSuccess();
        this.s_commandResult = ShortcutExecutorActivity$$ExternalSyntheticBackport0.m("\n", out);
    }

    @Deprecated
    public void showMessage(String str) {
        Toast.makeText(getApplicationContext(), str, 0).show();
    }

    @Deprecated
    public int getLocationX(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr[0];
    }

    @Deprecated
    public int getLocationY(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr[1];
    }

    @Deprecated
    public int getRandom(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView listView) {
        ArrayList<Double> arrayList = new ArrayList<>();
        SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
        for (int i = 0; i < checkedItemPositions.size(); i++) {
            if (checkedItemPositions.valueAt(i)) {
                arrayList.add(Double.valueOf(checkedItemPositions.keyAt(i)));
            }
        }
        return arrayList;
    }

    @Deprecated
    public float getDip(int i) {
        return TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels() {
        return getResources().getDisplayMetrics().heightPixels;
    }
}
