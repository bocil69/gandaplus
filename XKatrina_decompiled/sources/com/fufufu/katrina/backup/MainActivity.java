package com.fufufu.katrina.backup;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.FirebaseApp;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/* loaded from: classes5.dex */
public class MainActivity extends AppCompatActivity {
    private static final int DURATION = 1000;
    private static final int IMAGE_COUNT = 12;
    private Button btn_getprop;
    private MaterialCardView cv_pbar;
    private ImageView im_01;
    private LinearLayout ln_offline;
    private LinearLayout ln_online;
    private ImageView logo01;
    private ImageView logo02;
    private ImageView logo03;
    private ImageView logo04;
    private ImageView logo05;
    private ImageView logo06;
    private ImageView logo07;
    private ImageView logo08;
    private ImageView logo09;
    private ImageView logo10;
    private ImageView logo11;
    private ImageView logo12;
    private ProgressBar pbar_loading;
    private SharedPreferences pref;
    private SharedPreferences prefuser;
    private SharedPreferences prefversion;
    private Runnable runnableIMAGEANIM;
    private TextView tv_app;
    private TextView tv_not_granted;
    private String s_channel_id = "";
    private String s_splash_loc = "";
    private String s_command = "";
    private String s_commandResult = "";
    private String s_exitCode = "";
    private boolean b_command = false;
    private String s_katrina_day = "";
    private String s_katrina_day2 = "";
    private Handler IMAGEANIM = new Handler();

    public void _ROOT() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d0067);
        initialize(bundle);
        FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {
        this.ln_offline = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02c1);
        this.ln_online = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02c2);
        this.logo01 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a02fc);
        this.logo02 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a02fd);
        this.logo03 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a02fe);
        this.logo04 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a02ff);
        this.logo05 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0300);
        this.logo06 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0301);
        this.logo07 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0302);
        this.logo08 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0303);
        this.logo09 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0304);
        this.logo10 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0305);
        this.logo11 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0306);
        this.logo12 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0307);
        this.tv_app = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04e6);
        this.cv_pbar = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a014d);
        this.tv_not_granted = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a0531);
        this.btn_getprop = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00bb);
        this.pbar_loading = (ProgressBar) findViewById(R.id.admsoloraya_res_0x7f0a03ee);
        this.im_01 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a01f1);
        this.pref = getSharedPreferences("preferences_ui", 0);
        this.prefuser = getSharedPreferences("user_preferences", 0);
        this.prefversion = getSharedPreferences("release_preference", 0);
        this.btn_getprop.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.MainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), ScannerActivity.class));
            }
        });
    }

    private void initializeLogic() {
        if (Build.VERSION.SDK_INT >= 30) {
            getWindow().setDecorFitsSystemWindows(false);
            getWindow().setStatusBarColor(0);
            getWindow().setNavigationBarColor(0);
        } else if (Build.VERSION.SDK_INT >= 19) {
            getWindow().setFlags(512, 512);
        }
        this.s_channel_id = "notif_channel";
        _createNotificationChannel();
        _requestNotificationPermission();
        _setFirstUI();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndRequestRootAccess() {
        if (Shell.getShell().isRoot()) {
            try {
                this.b_command = false;
                Shell.Result exec = Shell.cmd("pm grant com.fufufu.katrina.backup android.permission.SYSTEM_ALERT_WINDOW\nappops set com.fufufu.katrina.backup SYSTEM_ALERT_WINDOW allow\nsettings put secure enabled_accessibility_services com.fufufu.katrina.backup/.KatrinaIslandService").exec();
                List<String> out = exec.getOut();
                exec.getCode();
                this.b_command = exec.isSuccess();
                this.s_commandResult = MainActivity$$ExternalSyntheticBackport0.m("\n", out);
            } catch (Exception e) {
                e.printStackTrace();
            }
            startActivity(new Intent(getApplicationContext(), PermissionActivity.class));
            finish();
            return;
        }
        Boolean isAppGrantedRoot = Shell.isAppGrantedRoot();
        if (isAppGrantedRoot == null || !isAppGrantedRoot.booleanValue()) {
            Shell.getShell(new Shell.GetShellCallback() { // from class: com.fufufu.katrina.backup.MainActivity.2
                @Override // com.topjohnwu.superuser.Shell.GetShellCallback
                public void onShell(@NonNull Shell shell) {
                    if (shell.isRoot()) {
                        try {
                            MainActivity.this.b_command = false;
                            Shell.Result exec2 = Shell.cmd("pm grant com.fufufu.katrina.backup android.permission.SYSTEM_ALERT_WINDOW\nappops set com.fufufu.katrina.backup SYSTEM_ALERT_WINDOW allow\nsettings put secure enabled_accessibility_services com.fufufu.katrina.backup/.KatrinaIslandService").exec();
                            List<String> out2 = exec2.getOut();
                            exec2.getCode();
                            MainActivity.this.b_command = exec2.isSuccess();
                            MainActivity.this.s_commandResult = MainActivity$2$$ExternalSyntheticBackport0.m("\n", out2);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), PermissionActivity.class));
                        MainActivity.this.finish();
                        return;
                    }
                    MainActivity.this.ln_offline.setVisibility(0);
                    MainActivity.this.ln_online.setVisibility(8);
                    MainActivity.this.tv_not_granted.setText("Harap ijinkan akses root pada magisk\nLalu paksa henti XKatrina dan buka kembali aplikasi");
                    MainActivity.this.btn_getprop.setVisibility(0);
                }
            });
            return;
        }
        try {
            this.b_command = false;
            Shell.Result exec2 = Shell.cmd("pm grant com.fufufu.katrina.backup android.permission.SYSTEM_ALERT_WINDOW\nappops set com.fufufu.katrina.backup SYSTEM_ALERT_WINDOW allow\nsettings put secure enabled_accessibility_services com.fufufu.katrina.backup/.KatrinaIslandService").exec();
            List<String> out2 = exec2.getOut();
            exec2.getCode();
            this.b_command = exec2.isSuccess();
            this.s_commandResult = MainActivity$$ExternalSyntheticBackport0.m("\n", out2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        startActivity(new Intent(getApplicationContext(), PermissionActivity.class));
        finish();
    }

    public void _setFirstUI() {
        this.btn_getprop.setVisibility(8);
        try {
            this.prefversion.edit().putString("current", String.valueOf(getPackageManager().getPackageInfo(getPackageName(), 0).versionCode)).commit();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String concat = "/data/user/0/".concat(getApplicationContext().getPackageName().concat("/splash.jpg"));
        this.s_splash_loc = concat;
        if (FileUtil.isExistFile(concat)) {
            this.ln_online.setVisibility(0);
            this.ln_offline.setVisibility(8);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            this.im_01.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(this.s_splash_loc, displayMetrics.widthPixels, displayMetrics.heightPixels));
            new Handler().postDelayed(new Runnable() { // from class: com.fufufu.katrina.backup.MainActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    MainActivity.this.checkAndRequestRootAccess();
                }
            }, 2000L);
            return;
        }
        this.ln_online.setVisibility(8);
        this.ln_offline.setVisibility(0);
        this.prefuser.getString("emanresu", "");
        "𝐁𝐲𝐩𝐚𝐬𝐬𝐞𝐝𝐝𝐝 𝐛𝐲 𝐚𝐝𝐦𝐬𝐨𝐥𝐨𝐫𝐚𝐲𝐚".equals("");
        if (1 != 0) {
            this.tv_app.setText("𝐁𝐲𝐩𝐚𝐬𝐬𝐞𝐝𝐝𝐝 𝐛𝐲 𝐚𝐝𝐦𝐬𝐨𝐥𝐨𝐫𝐚𝐲𝐚");
        } else {
            TextView textView = this.tv_app;
            this.prefuser.getString("emanresu", "");
            textView.setText("𝐁𝐲𝐩𝐚𝐬𝐬𝐞𝐝𝐝𝐝 𝐛𝐲 𝐚𝐝𝐦𝐬𝐨𝐥𝐨𝐫𝐚𝐲𝐚");
        }
        this.pbar_loading.setProgress(0);
        Runnable runnable = new Runnable() { // from class: com.fufufu.katrina.backup.MainActivity.4
            @Override // java.lang.Runnable
            public void run() {
                MainActivity.this.pbar_loading.setProgress(MainActivity.this.pbar_loading.getProgress() + 10);
                if (MainActivity.this.pbar_loading.getProgress() == 100) {
                    MainActivity.this.IMAGEANIM.removeCallbacks(MainActivity.this.runnableIMAGEANIM);
                } else {
                    MainActivity.this.IMAGEANIM.postDelayed(MainActivity.this.runnableIMAGEANIM, 250L);
                }
            }
        };
        this.runnableIMAGEANIM = runnable;
        this.IMAGEANIM.postDelayed(runnable, 0L);
        ImageView[] imageViewArr = {(ImageView) findViewById(R.id.admsoloraya_res_0x7f0a02fc), (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a02fe), (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0300), (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0302), (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0304), (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0306), (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a02fd), (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a02ff), (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0301), (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0304), (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0305), (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0307)};
        ObjectAnimator[] objectAnimatorArr = new ObjectAnimator[6];
        for (int i = 0; i < 6; i++) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageViewArr[i], View.TRANSLATION_X, -500.0f, 0.0f);
            objectAnimatorArr[i] = ofFloat;
            ofFloat.setDuration((i * 150) + 1000);
            objectAnimatorArr[i].setInterpolator(new BounceInterpolator());
        }
        ObjectAnimator[] objectAnimatorArr2 = new ObjectAnimator[6];
        for (int i2 = 6; i2 < 12; i2++) {
            int i3 = i2 - 6;
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageViewArr[i2], View.TRANSLATION_X, 500.0f, 0.0f);
            objectAnimatorArr2[i3] = ofFloat2;
            ofFloat2.setDuration((i2 * 150) + 1000);
            objectAnimatorArr2[i3].setInterpolator(new BounceInterpolator());
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorArr);
        animatorSet.playTogether(objectAnimatorArr2);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.MainActivity.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                MainActivity.this.checkAndRequestRootAccess();
            }
        });
        this.pbar_loading.setMax(100);
    }

    public void _createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(this.s_channel_id, "Notification Channel", 3);
            notificationChannel.setDescription("Channel for displaying notifications");
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }

    public void _requestNotificationPermission() {
        NotificationManager notificationManager;
        if (Build.VERSION.SDK_INT < 26 || (notificationManager = (NotificationManager) getSystemService(NotificationManager.class)) == null) {
            return;
        }
        notificationManager.areNotificationsEnabled();
    }

    public void _sendToFloat(String str) {
        Intent intent = new Intent(this, KatrinaIslandService.class);
        intent.setAction(KatrinaIslandService.ACTION_SHOW_TEXT);
        intent.putExtra(KatrinaIslandService.EXTRA_TEXT, str);
        startService(intent);
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
