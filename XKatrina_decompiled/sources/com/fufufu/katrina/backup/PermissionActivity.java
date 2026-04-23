package com.fufufu.katrina.backup;

import android.accessibilityservice.AccessibilityService;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import io.noties.markwon.Markwon;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
/* loaded from: classes5.dex */
public class PermissionActivity extends AppCompatActivity {
    private static final int ACCESSIBILITY_REQUEST_CODE = 100;
    private static final int OVERLAY_PERMISSION_REQUEST_CODE = 1000;
    private AlertDialog AGREE;
    private OnCompleteListener<AuthResult> _katrina_user_create_user_listener;
    private OnCompleteListener<Void> _katrina_user_reset_password_listener;
    private OnCompleteListener<AuthResult> _katrina_user_sign_in_listener;
    private ValueAnimator anim;
    private ValueAnimator anim1;
    private ObjectAnimator anim2;
    private ObjectAnimator anim3;
    private ValueAnimator anim4;
    private ValueAnimator anim5;
    private Button btn_access;
    private Button btn_notif;
    private Button btn_overlay;
    private FirebaseAuth katrina_user;
    private OnCompleteListener<Void> katrina_user_deleteUserListener;
    private OnCompleteListener<Void> katrina_user_emailVerificationSentListener;
    private OnCompleteListener<AuthResult> katrina_user_googleSignInListener;
    private OnCompleteListener<AuthResult> katrina_user_phoneAuthListener;
    private OnCompleteListener<Void> katrina_user_updateEmailListener;
    private OnCompleteListener<Void> katrina_user_updatePasswordListener;
    private OnCompleteListener<Void> katrina_user_updateProfileListener;
    private LinearLayout ln_base_island;
    private LinearLayout ln_image1;
    private LinearLayout ln_image2;
    private LinearLayout ln_image_bg;
    private LinearLayout ln_island_img;
    private LinearLayout ln_permission_access;
    private LinearLayout ln_permission_notif;
    private LinearLayout ln_permission_overlay;
    private SharedPreferences pref;
    private TextView tv_01;
    private TextView tv_02;
    private TextView tv_03;
    private TextView tv_katrina;
    private String s_channel_id = "";
    private Intent i_auth = new Intent();
    int width_value = 0;
    int margin_value = 0;
    int new_width_value = 0;
    int new_margin_value = 0;

    public void _EXTRA() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d00a1);
        initialize(bundle);
        FirebaseApp.initializeApp(this);
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == -1 || ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == -1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1000);
        } else {
            initializeLogic();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1000) {
            initializeLogic();
        }
    }

    private void initialize(Bundle bundle) {
        this.ln_base_island = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0272);
        this.ln_image_bg = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02a6);
        this.ln_permission_notif = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02c6);
        this.ln_permission_overlay = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02c7);
        this.ln_permission_access = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02c5);
        this.ln_image1 = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02a4);
        this.ln_image2 = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02a5);
        this.ln_island_img = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02ae);
        this.tv_katrina = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a0519);
        this.tv_03 = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04d0);
        this.btn_notif = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00cb);
        this.tv_01 = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04bd);
        this.btn_overlay = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00ce);
        this.tv_02 = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04c8);
        this.btn_access = (Button) findViewById(R.id.admsoloraya_res_0x7f0a007b);
        this.katrina_user = FirebaseAuth.getInstance();
        this.pref = getSharedPreferences("preferences_ui", 0);
        this.btn_notif.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PermissionActivity.this.startActivity(new Intent("android.settings.APP_NOTIFICATION_SETTINGS").putExtra("android.provider.extra.APP_PACKAGE", PermissionActivity.this.getPackageName()));
            }
        });
        this.btn_overlay.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PermissionActivity.this.checkOverlayPermission();
            }
        });
        this.btn_access.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PermissionActivity.this.initializeAccessibilityService();
            }
        });
        this.katrina_user_updateEmailListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.PermissionActivity.4
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_updatePasswordListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.PermissionActivity.5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_emailVerificationSentListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.PermissionActivity.6
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_deleteUserListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.PermissionActivity.7
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_phoneAuthListener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.PermissionActivity.8
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_updateProfileListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.PermissionActivity.9
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_googleSignInListener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.PermissionActivity.10
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this._katrina_user_create_user_listener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.PermissionActivity.11
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this._katrina_user_sign_in_listener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.PermissionActivity.12
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this._katrina_user_reset_password_listener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.PermissionActivity.13
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
        this.s_channel_id = "notif_channel";
        _createNotificationChannel();
        _firstSetUI();
        if (this.pref.getString("agreement", "").equals("")) {
            _showAgreement();
        } else {
            _checkPermission();
        }
        _playKatrinaAnimation();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        _checkPermission();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkOverlayPermission() {
        if (Build.VERSION.SDK_INT < 23 || Settings.canDrawOverlays(this)) {
            return;
        }
        startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())), 1000);
    }

    private boolean checkDrawOverlay() {
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(this);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeAccessibilityService() {
        if (isAccessibilityServiceEnabled(getApplicationContext(), KatrinaIslandService.class)) {
            return;
        }
        startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
    }

    private boolean isAccessibilityServiceEnabled(Context context, Class<? extends AccessibilityService> cls) {
        ComponentName componentName = new ComponentName(context, cls);
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
        TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
        if (string != null) {
            simpleStringSplitter.setString(string);
            while (simpleStringSplitter.hasNext()) {
                ComponentName unflattenFromString = ComponentName.unflattenFromString(simpleStringSplitter.next());
                if (unflattenFromString != null && unflattenFromString.equals(componentName)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean checkAccessibilityServiceEnabled() {
        ComponentName componentName = new ComponentName(this, KatrinaIslandService.class);
        String string = Settings.Secure.getString(getContentResolver(), "enabled_accessibility_services");
        return string != null && string.contains(componentName.flattenToString());
    }

    private void requestAccessibilityService() {
        startActivityForResult(new Intent("android.settings.ACCESSIBILITY_SETTINGS"), 100);
    }

    private void startFloatingService() {
        startService(new Intent(this, KatrinaIslandService.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100) {
            boolean isAccessibilityServiceEnabled = isAccessibilityServiceEnabled(getApplicationContext(), KatrinaIslandService.class);
            boolean checkDrawOverlay = checkDrawOverlay();
            if (isAccessibilityServiceEnabled && checkDrawOverlay) {
                startFloatingService();
            }
        }
    }

    public void _firstSetUI() {
        this.tv_katrina.setAlpha(0.0f);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadii(new float[]{60.0f, 60.0f, 60.0f, 60.0f, 0.0f, 0.0f, 0.0f, 0.0f});
        gradientDrawable.setStroke(0, ViewCompat.MEASURED_STATE_MASK);
        gradientDrawable.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.ln_image1.setBackground(gradientDrawable);
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setShape(0);
        gradientDrawable2.setCornerRadii(new float[]{60.0f, 60.0f, 60.0f, 60.0f, 0.0f, 0.0f, 0.0f, 0.0f});
        gradientDrawable2.setColor(-1);
        this.ln_image2.setBackground(gradientDrawable2);
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setShape(0);
        gradientDrawable3.setCornerRadius(100.0f);
        gradientDrawable3.setStroke(0, ViewCompat.MEASURED_STATE_MASK);
        gradientDrawable3.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.ln_island_img.setBackground(gradientDrawable3);
    }

    public void _playKatrinaAnimation() {
        this.tv_katrina.setText("I am Katrina Island </>");
        ValueAnimator ofInt = ValueAnimator.ofInt(this.ln_island_img.getMeasuredWidth(), 600);
        this.anim = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.14
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.width = intValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim.setDuration(800L);
        this.anim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim.start();
        ValueAnimator ofInt2 = ValueAnimator.ofInt(this.ln_island_img.getMeasuredHeight(), 125);
        this.anim1 = ofInt2;
        ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.15
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.height = intValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim1.setDuration(1000L);
        this.anim1.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim1.addListener(new AnonymousClass16());
        this.anim1.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fufufu.katrina.backup.PermissionActivity$16  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass16 extends AnimatorListenerAdapter {
        AnonymousClass16() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            PermissionActivity permissionActivity = PermissionActivity.this;
            permissionActivity.anim2 = ObjectAnimator.ofFloat(permissionActivity.tv_katrina, "alpha", 0.0f, 1.0f);
            PermissionActivity.this.anim2.setDuration(500L);
            PermissionActivity.this.anim2.addListener(new AnonymousClass1());
            PermissionActivity.this.anim2.start();
        }

        /* renamed from: com.fufufu.katrina.backup.PermissionActivity$16$1  reason: invalid class name */
        /* loaded from: classes5.dex */
        class AnonymousClass1 extends AnimatorListenerAdapter {
            AnonymousClass1() {
            }

            /* renamed from: com.fufufu.katrina.backup.PermissionActivity$16$1$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes5.dex */
            class RunnableC00121 implements Runnable {
                RunnableC00121() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    PermissionActivity.this.anim3 = ObjectAnimator.ofFloat(PermissionActivity.this.tv_katrina, "alpha", 1.0f, 0.0f);
                    PermissionActivity.this.anim3.setDuration(500L);
                    PermissionActivity.this.anim3.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.16.1.1.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            PermissionActivity.this.anim4 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredWidth(), 50);
                            PermissionActivity.this.anim4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.16.1.1.1.1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.width = intValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim4.setDuration(800L);
                            PermissionActivity.this.anim4.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim4.start();
                            PermissionActivity.this.anim5 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredHeight(), 50);
                            PermissionActivity.this.anim5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.16.1.1.1.2
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.height = intValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim5.setDuration(1000L);
                            PermissionActivity.this.anim5.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim5.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.16.1.1.1.3
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    PermissionActivity.this._playKatrinaAnimation1();
                                }
                            });
                            PermissionActivity.this.anim5.start();
                        }
                    });
                    PermissionActivity.this.anim3.start();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                new Handler().postDelayed(new RunnableC00121(), 2000L);
            }
        }
    }

    public void _playKatrinaAnimation1() {
        this.tv_katrina.setText("Kamu bisa akses menu penting dari sini");
        ValueAnimator ofInt = ValueAnimator.ofInt(this.ln_island_img.getMeasuredWidth(), 600);
        this.anim = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.17
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.width = intValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim.setDuration(800L);
        this.anim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim.start();
        ValueAnimator ofInt2 = ValueAnimator.ofInt(this.ln_island_img.getMeasuredHeight(), 125);
        this.anim1 = ofInt2;
        ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.18
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.height = intValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim1.setDuration(1000L);
        this.anim1.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim1.addListener(new AnonymousClass19());
        this.anim1.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fufufu.katrina.backup.PermissionActivity$19  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass19 extends AnimatorListenerAdapter {
        AnonymousClass19() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            PermissionActivity permissionActivity = PermissionActivity.this;
            permissionActivity.anim2 = ObjectAnimator.ofFloat(permissionActivity.tv_katrina, "alpha", 0.0f, 1.0f);
            PermissionActivity.this.anim2.setDuration(500L);
            PermissionActivity.this.anim2.addListener(new AnonymousClass1());
            PermissionActivity.this.anim2.start();
        }

        /* renamed from: com.fufufu.katrina.backup.PermissionActivity$19$1  reason: invalid class name */
        /* loaded from: classes5.dex */
        class AnonymousClass1 extends AnimatorListenerAdapter {
            AnonymousClass1() {
            }

            /* renamed from: com.fufufu.katrina.backup.PermissionActivity$19$1$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes5.dex */
            class RunnableC00151 implements Runnable {
                RunnableC00151() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    PermissionActivity.this.anim3 = ObjectAnimator.ofFloat(PermissionActivity.this.tv_katrina, "alpha", 1.0f, 0.0f);
                    PermissionActivity.this.anim3.setDuration(500L);
                    PermissionActivity.this.anim3.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.19.1.1.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            PermissionActivity.this.anim4 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredWidth(), 50);
                            PermissionActivity.this.anim4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.19.1.1.1.1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.width = intValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim4.setDuration(800L);
                            PermissionActivity.this.anim4.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim4.start();
                            PermissionActivity.this.anim5 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredHeight(), 50);
                            PermissionActivity.this.anim5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.19.1.1.1.2
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.height = intValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim5.setDuration(1000L);
                            PermissionActivity.this.anim5.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim5.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.19.1.1.1.3
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    PermissionActivity.this._playKatrinaAnimation2();
                                }
                            });
                            PermissionActivity.this.anim5.start();
                        }
                    });
                    PermissionActivity.this.anim3.start();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                new Handler().postDelayed(new RunnableC00151(), 2000L);
            }
        }
    }

    public void _playKatrinaAnimation2() {
        this.tv_katrina.setText("Semua script sh juga bisa dijalankan dari sini");
        ValueAnimator ofInt = ValueAnimator.ofInt(this.ln_island_img.getMeasuredWidth(), 600);
        this.anim = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.20
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.width = intValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim.setDuration(800L);
        this.anim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim.start();
        ValueAnimator ofInt2 = ValueAnimator.ofInt(this.ln_island_img.getMeasuredHeight(), 125);
        this.anim1 = ofInt2;
        ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.21
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.height = intValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim1.setDuration(1000L);
        this.anim1.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim1.addListener(new AnonymousClass22());
        this.anim1.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fufufu.katrina.backup.PermissionActivity$22  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass22 extends AnimatorListenerAdapter {
        AnonymousClass22() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            PermissionActivity permissionActivity = PermissionActivity.this;
            permissionActivity.anim2 = ObjectAnimator.ofFloat(permissionActivity.tv_katrina, "alpha", 0.0f, 1.0f);
            PermissionActivity.this.anim2.setDuration(500L);
            PermissionActivity.this.anim2.addListener(new AnonymousClass1());
            PermissionActivity.this.anim2.start();
        }

        /* renamed from: com.fufufu.katrina.backup.PermissionActivity$22$1  reason: invalid class name */
        /* loaded from: classes5.dex */
        class AnonymousClass1 extends AnimatorListenerAdapter {
            AnonymousClass1() {
            }

            /* renamed from: com.fufufu.katrina.backup.PermissionActivity$22$1$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes5.dex */
            class RunnableC00181 implements Runnable {
                RunnableC00181() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    PermissionActivity.this.anim3 = ObjectAnimator.ofFloat(PermissionActivity.this.tv_katrina, "alpha", 1.0f, 0.0f);
                    PermissionActivity.this.anim3.setDuration(500L);
                    PermissionActivity.this.anim3.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.22.1.1.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            PermissionActivity.this.anim4 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredWidth(), 50);
                            PermissionActivity.this.anim4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.22.1.1.1.1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.width = intValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim4.setDuration(800L);
                            PermissionActivity.this.anim4.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim4.start();
                            PermissionActivity.this.anim5 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredHeight(), 50);
                            PermissionActivity.this.anim5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.22.1.1.1.2
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.height = intValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim5.setDuration(1000L);
                            PermissionActivity.this.anim5.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim5.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.22.1.1.1.3
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    PermissionActivity.this._playKatrinaAnimation3();
                                }
                            });
                            PermissionActivity.this.anim5.start();
                        }
                    });
                    PermissionActivity.this.anim3.start();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                new Handler().postDelayed(new RunnableC00181(), 2000L);
            }
        }
    }

    public void _playKatrinaAnimation3() {
        this.tv_katrina.setText("Ritual juga ada disini :)");
        ValueAnimator ofInt = ValueAnimator.ofInt(this.ln_island_img.getMeasuredWidth(), 600);
        this.anim = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.23
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.width = intValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim.setDuration(800L);
        this.anim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim.start();
        ValueAnimator ofInt2 = ValueAnimator.ofInt(this.ln_island_img.getMeasuredHeight(), 125);
        this.anim1 = ofInt2;
        ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.24
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.height = intValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim1.setDuration(1000L);
        this.anim1.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim1.addListener(new AnonymousClass25());
        this.anim1.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fufufu.katrina.backup.PermissionActivity$25  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass25 extends AnimatorListenerAdapter {
        AnonymousClass25() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            PermissionActivity permissionActivity = PermissionActivity.this;
            permissionActivity.anim2 = ObjectAnimator.ofFloat(permissionActivity.tv_katrina, "alpha", 0.0f, 1.0f);
            PermissionActivity.this.anim2.setDuration(500L);
            PermissionActivity.this.anim2.addListener(new AnonymousClass1());
            PermissionActivity.this.anim2.start();
        }

        /* renamed from: com.fufufu.katrina.backup.PermissionActivity$25$1  reason: invalid class name */
        /* loaded from: classes5.dex */
        class AnonymousClass1 extends AnimatorListenerAdapter {
            AnonymousClass1() {
            }

            /* renamed from: com.fufufu.katrina.backup.PermissionActivity$25$1$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes5.dex */
            class RunnableC00211 implements Runnable {
                RunnableC00211() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    PermissionActivity.this.anim3 = ObjectAnimator.ofFloat(PermissionActivity.this.tv_katrina, "alpha", 1.0f, 0.0f);
                    PermissionActivity.this.anim3.setDuration(500L);
                    PermissionActivity.this.anim3.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.25.1.1.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            PermissionActivity.this.anim4 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredWidth(), 50);
                            PermissionActivity.this.anim4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.25.1.1.1.1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.width = intValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim4.setDuration(800L);
                            PermissionActivity.this.anim4.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim4.start();
                            PermissionActivity.this.anim5 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredHeight(), 50);
                            PermissionActivity.this.anim5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.25.1.1.1.2
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.height = intValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim5.setDuration(1000L);
                            PermissionActivity.this.anim5.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim5.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.25.1.1.1.3
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    PermissionActivity.this._playKatrinaAnimation4();
                                }
                            });
                            PermissionActivity.this.anim5.start();
                        }
                    });
                    PermissionActivity.this.anim3.start();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                new Handler().postDelayed(new RunnableC00211(), 2000L);
            }
        }
    }

    public void _playKatrinaAnimation4() {
        this.tv_katrina.setText("Generate alamat juga ada");
        ValueAnimator ofInt = ValueAnimator.ofInt(this.ln_island_img.getMeasuredWidth(), 600);
        this.anim = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.26
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.width = intValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim.setDuration(800L);
        this.anim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim.start();
        ValueAnimator ofInt2 = ValueAnimator.ofInt(this.ln_island_img.getMeasuredHeight(), 125);
        this.anim1 = ofInt2;
        ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.27
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                layoutParams.height = intValue;
                PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
            }
        });
        this.anim1.setDuration(1000L);
        this.anim1.setInterpolator(new AccelerateDecelerateInterpolator());
        this.anim1.addListener(new AnonymousClass28());
        this.anim1.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fufufu.katrina.backup.PermissionActivity$28  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass28 extends AnimatorListenerAdapter {
        AnonymousClass28() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            PermissionActivity permissionActivity = PermissionActivity.this;
            permissionActivity.anim2 = ObjectAnimator.ofFloat(permissionActivity.tv_katrina, "alpha", 0.0f, 1.0f);
            PermissionActivity.this.anim2.setDuration(500L);
            PermissionActivity.this.anim2.addListener(new AnonymousClass1());
            PermissionActivity.this.anim2.start();
        }

        /* renamed from: com.fufufu.katrina.backup.PermissionActivity$28$1  reason: invalid class name */
        /* loaded from: classes5.dex */
        class AnonymousClass1 extends AnimatorListenerAdapter {
            AnonymousClass1() {
            }

            /* renamed from: com.fufufu.katrina.backup.PermissionActivity$28$1$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes5.dex */
            class RunnableC00241 implements Runnable {
                RunnableC00241() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    PermissionActivity.this.anim3 = ObjectAnimator.ofFloat(PermissionActivity.this.tv_katrina, "alpha", 1.0f, 0.0f);
                    PermissionActivity.this.anim3.setDuration(500L);
                    PermissionActivity.this.anim3.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.28.1.1.1
                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            PermissionActivity.this.anim4 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredWidth(), 50);
                            PermissionActivity.this.anim4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.28.1.1.1.1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.width = intValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim4.setDuration(800L);
                            PermissionActivity.this.anim4.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim4.start();
                            PermissionActivity.this.anim5 = ValueAnimator.ofInt(PermissionActivity.this.ln_island_img.getMeasuredHeight(), 50);
                            PermissionActivity.this.anim5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.28.1.1.1.2
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                                    ViewGroup.LayoutParams layoutParams = PermissionActivity.this.ln_island_img.getLayoutParams();
                                    layoutParams.height = intValue;
                                    PermissionActivity.this.ln_island_img.setLayoutParams(layoutParams);
                                }
                            });
                            PermissionActivity.this.anim5.setDuration(1000L);
                            PermissionActivity.this.anim5.setInterpolator(new AccelerateDecelerateInterpolator());
                            PermissionActivity.this.anim5.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.PermissionActivity.28.1.1.1.3
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    PermissionActivity.this._playKatrinaAnimation();
                                }
                            });
                            PermissionActivity.this.anim5.start();
                        }
                    });
                    PermissionActivity.this.anim3.start();
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                new Handler().postDelayed(new RunnableC00241(), 2000L);
            }
        }
    }

    public void _createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(this.s_channel_id, "Notification Channel", 3);
            notificationChannel.setDescription("Channel for displaying notifications");
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }

    public void _checkPermission() {
        NotificationManager notificationManager;
        boolean isAccessibilityServiceEnabled = isAccessibilityServiceEnabled(getApplicationContext(), KatrinaIslandService.class);
        if (Build.VERSION.SDK_INT >= 23 && Settings.canDrawOverlays(this)) {
            this.btn_overlay.setText("DIIJINKAN");
        } else {
            this.btn_overlay.setText("BERIKAN IJIN");
        }
        if (isAccessibilityServiceEnabled) {
            this.btn_access.setText("DIIJINKAN");
        } else {
            this.btn_access.setText("BERIKAN IJIN");
        }
        if (Build.VERSION.SDK_INT >= 26 && (notificationManager = (NotificationManager) getSystemService(NotificationManager.class)) != null) {
            if (!notificationManager.areNotificationsEnabled()) {
                this.btn_notif.setText("BERIKAN IJIN");
            } else {
                this.btn_notif.setText("DIIJINKAN");
            }
        }
        if (this.pref.getString("agreement", "").equals("1") && this.btn_notif.getText().toString().equals("DIIJINKAN") && this.btn_overlay.getText().toString().equals("DIIJINKAN") && this.btn_access.getText().toString().equals("DIIJINKAN")) {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                if (FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {
                    startActivity(new Intent(getApplicationContext(), KatrinaActivity.class));
                    finish();
                    return;
                }
                this.i_auth.setClass(getApplicationContext(), LoginActivity.class);
                startActivity(this.i_auth);
                finish();
                return;
            }
            this.i_auth.setClass(getApplicationContext(), LoginActivity.class);
            startActivity(this.i_auth);
            finish();
        }
    }

    public void _showAgreement() {
        showAGREE();
    }

    private void showAGREE() {
        View inflate = getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d001d, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        final EditText editText = (EditText) inflate.findViewById(R.id.admsoloraya_res_0x7f0a017e);
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        Markwon.builder(this).build().setMarkdown((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04e2), loadMarkdownFromAssets("agreement.md"));
        ((Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0080)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.PermissionActivity.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (editText.getText().toString().equals("setuju")) {
                    PermissionActivity.this.pref.edit().putString("agreement", "1").commit();
                    PermissionActivity.this._checkPermission();
                    PermissionActivity.this.AGREE.dismiss();
                    return;
                }
                SketchwareUtil.showMessage(PermissionActivity.this.getApplicationContext(), "Anda belum diperbolehkan menggunakan aplikasi ini");
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.AGREE = create;
        create.show();
    }

    private String loadMarkdownFromAssets(String str) {
        try {
            InputStream open = getAssets().open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
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
