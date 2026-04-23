package com.fufufu.katrina.backup;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.FirebaseApp;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/* loaded from: classes5.dex */
public class RitualResultActivity extends AppCompatActivity {
    private LinearLayout ln_base;
    private LinearLayout ln_cleaner;
    private LinearLayout ln_prop;
    private LinearLayout ln_reboot;
    private LinearLayout ln_ssaid;
    private LinearLayout ln_wipe;
    private Runnable runnableModpes;
    private Runnable runnableReboot;
    private Runnable runnableStartReboot;
    private TextView tv_01;
    private TextView tv_02;
    private TextView tv_03;
    private TextView tv_04;
    private TextView tv_05;
    private TextView tv_cleaner;
    private TextView tv_prop;
    private TextView tv_reboot;
    private TextView tv_ssaid;
    private TextView tv_wipe;
    private ScrollView vscleaner;
    private ScrollView vsprop;
    private ScrollView vsssaid;
    private ScrollView vswipe;
    private String s_universal = "";
    private String s_extra = "";
    private boolean b_prop = false;
    private boolean b_cleaner = false;
    private boolean b_wipe = false;
    private boolean b_ssaid = false;
    private String s_command = "";
    private String s_commandResult = "";
    private String s_exitCode = "";
    private boolean b_command = false;
    private String s_commandBase = "";
    private boolean b_modpes = false;
    private String s_commandModpes = "";
    private Intent i = new Intent();
    private ObjectAnimator oacleaner = new ObjectAnimator();
    private ObjectAnimator oaprop = new ObjectAnimator();
    private ObjectAnimator oawipe = new ObjectAnimator();
    private ObjectAnimator oassaid = new ObjectAnimator();
    private Handler Reboot = new Handler();
    private Handler Modpes = new Handler();
    private Handler StartReboot = new Handler();

    public void _EXTRA() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d00af);
        initialize(bundle);
        FirebaseApp.initializeApp(this);
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == -1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1000);
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
        this.ln_base = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0270);
        this.ln_prop = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02cb);
        this.ln_cleaner = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0284);
        this.ln_wipe = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02f7);
        this.ln_ssaid = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02e6);
        this.ln_reboot = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02d1);
        this.tv_01 = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04bd);
        this.vsprop = (ScrollView) findViewById(R.id.admsoloraya_res_0x7f0a05c3);
        this.tv_prop = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a053a);
        this.tv_02 = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04c8);
        this.vscleaner = (ScrollView) findViewById(R.id.admsoloraya_res_0x7f0a05bd);
        this.tv_cleaner = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04f8);
        this.tv_03 = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04d0);
        this.vswipe = (ScrollView) findViewById(R.id.admsoloraya_res_0x7f0a05c5);
        this.tv_wipe = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a05ac);
        this.tv_04 = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04d6);
        this.vsssaid = (ScrollView) findViewById(R.id.admsoloraya_res_0x7f0a05c4);
        this.tv_ssaid = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a0557);
        this.tv_05 = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04d9);
        this.tv_reboot = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a0542);
        this.oacleaner.addListener(new AnonymousClass1());
        this.oaprop.addListener(new AnonymousClass2());
        this.oawipe.addListener(new AnonymousClass3());
        this.oassaid.addListener(new AnonymousClass4());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fufufu.katrina.backup.RitualResultActivity$1  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        AnonymousClass1() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            RitualResultActivity ritualResultActivity = RitualResultActivity.this;
            ritualResultActivity.s_universal = FileUtil.readFile("/data/data/".concat(ritualResultActivity.getApplicationContext().getPackageName().concat("/cleaner.xml")));
            if (RitualResultActivity.this.s_universal.isEmpty()) {
                RitualResultActivity.this.s_universal = "cant read result";
            }
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.1.1
                int lineIndex = 0;
                String[] lines;

                {
                    this.lines = RitualResultActivity.this.s_universal.split("\\n");
                }

                @Override // java.lang.Runnable
                public void run() {
                    TextView textView = RitualResultActivity.this.tv_cleaner;
                    textView.append(String.valueOf(this.lines[this.lineIndex]) + "\n");
                    RitualResultActivity.this.runOnUiThread(new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.1.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RitualResultActivity.this.updateTextCleaner();
                        }
                    });
                    int i = this.lineIndex + 1;
                    this.lineIndex = i;
                    if (i >= this.lines.length) {
                        RitualResultActivity.this.tv_02.setBackgroundColor(-11417769);
                        RitualResultActivity.this.b_cleaner = true;
                        return;
                    }
                    handler.postDelayed(this, 10L);
                }
            }, 10L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fufufu.katrina.backup.RitualResultActivity$2  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass2 implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        AnonymousClass2() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!FileUtil.isExistFile("/data/data/".concat(RitualResultActivity.this.getApplicationContext().getPackageName().concat("/system.prop")))) {
                RitualResultActivity.this.s_universal = "";
            } else {
                RitualResultActivity ritualResultActivity = RitualResultActivity.this;
                ritualResultActivity.s_universal = FileUtil.readFile("/data/data/".concat(ritualResultActivity.getApplicationContext().getPackageName().concat("/system.prop")));
            }
            if (RitualResultActivity.this.s_universal.isEmpty()) {
                RitualResultActivity.this.s_universal = "cant read result";
            }
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.2.1
                int lineIndex = 0;
                String[] lines;

                {
                    this.lines = RitualResultActivity.this.s_universal.split("\\n");
                }

                @Override // java.lang.Runnable
                public void run() {
                    TextView textView = RitualResultActivity.this.tv_prop;
                    textView.append(String.valueOf(this.lines[this.lineIndex]) + "\n");
                    RitualResultActivity.this.runOnUiThread(new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.2.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RitualResultActivity.this.updateTextProp();
                        }
                    });
                    int i = this.lineIndex + 1;
                    this.lineIndex = i;
                    if (i >= this.lines.length) {
                        RitualResultActivity.this.tv_01.setBackgroundColor(-11417769);
                        RitualResultActivity.this.b_prop = true;
                        return;
                    }
                    handler.postDelayed(this, 30L);
                }
            }, 30L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fufufu.katrina.backup.RitualResultActivity$3  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass3 implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        AnonymousClass3() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            RitualResultActivity ritualResultActivity = RitualResultActivity.this;
            ritualResultActivity.s_universal = FileUtil.readFile("/data/data/".concat(ritualResultActivity.getApplicationContext().getPackageName().concat("/wipegms.xml")));
            if (RitualResultActivity.this.s_universal.isEmpty()) {
                RitualResultActivity.this.s_universal = "cant read result";
            }
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.3.1
                int lineIndex = 0;
                String[] lines;

                {
                    this.lines = RitualResultActivity.this.s_universal.split("\\n");
                }

                @Override // java.lang.Runnable
                public void run() {
                    TextView textView = RitualResultActivity.this.tv_wipe;
                    textView.append(String.valueOf(this.lines[this.lineIndex]) + "\n");
                    RitualResultActivity.this.runOnUiThread(new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.3.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RitualResultActivity.this.updateTextWipe();
                        }
                    });
                    int i = this.lineIndex + 1;
                    this.lineIndex = i;
                    if (i >= this.lines.length) {
                        RitualResultActivity.this.tv_03.setBackgroundColor(-11417769);
                        RitualResultActivity.this.b_wipe = true;
                        return;
                    }
                    handler.postDelayed(this, 50L);
                }
            }, 50L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fufufu.katrina.backup.RitualResultActivity$4  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass4 implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        AnonymousClass4() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            RitualResultActivity ritualResultActivity = RitualResultActivity.this;
            ritualResultActivity.s_universal = FileUtil.readFile("/data/data/".concat(ritualResultActivity.getApplicationContext().getPackageName().concat("/reseto.xml")));
            if (RitualResultActivity.this.s_universal.isEmpty()) {
                RitualResultActivity.this.s_universal = "cant read result";
            }
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.4.1
                int lineIndex = 0;
                String[] lines;

                {
                    this.lines = RitualResultActivity.this.s_universal.split("\\n");
                }

                @Override // java.lang.Runnable
                public void run() {
                    TextView textView = RitualResultActivity.this.tv_ssaid;
                    textView.append(String.valueOf(this.lines[this.lineIndex]) + "\n");
                    RitualResultActivity.this.runOnUiThread(new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.4.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RitualResultActivity.this.updateTextSsaid();
                        }
                    });
                    int i = this.lineIndex + 1;
                    this.lineIndex = i;
                    if (i >= this.lines.length) {
                        RitualResultActivity.this.tv_04.setBackgroundColor(-11417769);
                        RitualResultActivity.this.b_ssaid = true;
                        return;
                    }
                    handler.postDelayed(this, 50L);
                }
            }, 50L);
        }
    }

    private void initializeLogic() {
        _firstSetUI();
    }

    public void _firstSetUI() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(ViewCompat.MEASURED_STATE_MASK);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.setNavigationBarColor(ViewCompat.MEASURED_STATE_MASK);
        }
        this.ln_reboot.setVisibility(8);
        this.tv_prop.setText("");
        this.tv_cleaner.setText("");
        this.tv_wipe.setText("");
        this.tv_ssaid.setText("");
        this.tv_reboot.setText("");
        this.tv_01.setBackgroundColor(-151225);
        this.tv_02.setBackgroundColor(-151225);
        this.tv_03.setBackgroundColor(-151225);
        this.tv_04.setBackgroundColor(-151225);
        this.vsprop.setVerticalScrollBarEnabled(false);
        this.vscleaner.setVerticalScrollBarEnabled(false);
        this.vswipe.setVerticalScrollBarEnabled(false);
        this.vsssaid.setVerticalScrollBarEnabled(false);
        this.b_prop = false;
        this.b_cleaner = false;
        this.b_wipe = false;
        this.b_ssaid = false;
        this.b_modpes = false;
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
            _getExtraRitual();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTextProp() {
        this.vsprop.post(new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.5
            @Override // java.lang.Runnable
            public void run() {
                RitualResultActivity.this.vsprop.fullScroll(130);
            }
        });
    }

    public void updateTextCleaner() {
        this.vscleaner.post(new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.6
            @Override // java.lang.Runnable
            public void run() {
                RitualResultActivity.this.vscleaner.fullScroll(130);
            }
        });
    }

    public void updateTextWipe() {
        this.vswipe.post(new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.7
            @Override // java.lang.Runnable
            public void run() {
                RitualResultActivity.this.vswipe.fullScroll(130);
            }
        });
    }

    public void updateTextSsaid() {
        this.vsssaid.post(new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.8
            @Override // java.lang.Runnable
            public void run() {
                RitualResultActivity.this.vsssaid.fullScroll(130);
            }
        });
    }

    public void _onExtraProp() {
        String stringExtra = getIntent().getStringExtra("PROP");
        this.s_extra = stringExtra;
        if (stringExtra.equals("true")) {
            this.oaprop.cancel();
            this.oaprop.setTarget(this.ln_prop);
            this.oaprop.setPropertyName("alpha");
            this.oaprop.setFloatValues(0.0f, 1.0f);
            this.oaprop.setDuration(500L);
            this.oaprop.start();
            return;
        }
        this.ln_prop.setVisibility(8);
    }

    public void _onExtraClean() {
        String stringExtra = getIntent().getStringExtra("CLEANER");
        this.s_extra = stringExtra;
        if (stringExtra.equals("true")) {
            this.oacleaner.cancel();
            this.oacleaner.setTarget(this.ln_cleaner);
            this.oacleaner.setPropertyName("alpha");
            this.oacleaner.setFloatValues(0.0f, 1.0f);
            this.oacleaner.setDuration(500L);
            this.oacleaner.start();
            return;
        }
        this.ln_cleaner.setVisibility(8);
        this.b_cleaner = true;
    }

    public void _onExtraSsaid() {
        String stringExtra = getIntent().getStringExtra("SSAID");
        this.s_extra = stringExtra;
        if (stringExtra.equals("true")) {
            this.oassaid.cancel();
            this.oassaid.setTarget(this.ln_ssaid);
            this.oassaid.setPropertyName("alpha");
            this.oassaid.setFloatValues(0.0f, 1.0f);
            this.oassaid.setDuration(500L);
            this.oassaid.start();
            return;
        }
        String stringExtra2 = getIntent().getStringExtra("RESET0");
        this.s_extra = stringExtra2;
        if (stringExtra2.equals("true")) {
            this.oassaid.cancel();
            this.oassaid.setTarget(this.ln_ssaid);
            this.oassaid.setPropertyName("alpha");
            this.oassaid.setFloatValues(0.0f, 1.0f);
            this.oassaid.setDuration(500L);
            this.oassaid.start();
            return;
        }
        this.ln_ssaid.setVisibility(8);
        this.b_ssaid = true;
    }

    public void _onExtraReboot() {
        Runnable runnable = new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.9
            @Override // java.lang.Runnable
            public void run() {
                if (!RitualResultActivity.this.b_prop || !RitualResultActivity.this.b_cleaner || !RitualResultActivity.this.b_ssaid || !RitualResultActivity.this.b_wipe || !RitualResultActivity.this.b_modpes) {
                    RitualResultActivity.this.Reboot.postDelayed(RitualResultActivity.this.runnableReboot, 500L);
                    return;
                }
                RitualResultActivity.this.Reboot.removeCallbacks(RitualResultActivity.this.runnableReboot);
                if (!RitualResultActivity.this.getIntent().getStringExtra("NORESTART").equals("true")) {
                    RitualResultActivity.this.ln_prop.setVisibility(4);
                    RitualResultActivity.this.ln_cleaner.setVisibility(4);
                    RitualResultActivity.this.ln_wipe.setVisibility(4);
                    RitualResultActivity.this.ln_ssaid.setVisibility(4);
                    RitualResultActivity.this.ln_reboot.setVisibility(0);
                    RitualResultActivity.this._onStartReboot();
                    return;
                }
                RitualResultActivity.this.finish();
            }
        };
        this.runnableReboot = runnable;
        this.Reboot.postDelayed(runnable, 0L);
    }

    public void _onStartReboot() {
        this.s_command = this.s_commandBase;
        this.s_commandResult = "";
        this.s_exitCode = "";
        String stringExtra = getIntent().getStringExtra("REBOOT");
        this.s_extra = stringExtra;
        if (stringExtra.equals("true")) {
            this.tv_05.setText("Reboot");
            this.tv_reboot.setText("Restart phone...");
            this.s_command = this.s_command.concat("\ndelfile\n\nooreboot");
            new Handler().postDelayed(new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.10
                @Override // java.lang.Runnable
                public void run() {
                    RitualResultActivity.this.b_command = false;
                    Shell.Result exec = Shell.cmd(RitualResultActivity.this.s_command).exec();
                    List<String> out = exec.getOut();
                    exec.getCode();
                    RitualResultActivity.this.b_command = exec.isSuccess();
                    RitualResultActivity.this.s_commandResult = RitualResultActivity$10$$ExternalSyntheticBackport0.m("\n", out);
                }
            }, 2000L);
            return;
        }
        String stringExtra2 = getIntent().getStringExtra("DALVIC");
        this.s_extra = stringExtra2;
        if (stringExtra2.equals("true")) {
            this.tv_05.setText("Reboot Recovery");
            this.tv_reboot.setText("Wipe dalvic dan cache...");
            this.s_command = this.s_command.concat("\ndelfile\n\noodalvic");
            new Handler().postDelayed(new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.11
                @Override // java.lang.Runnable
                public void run() {
                    RitualResultActivity.this.b_command = false;
                    Shell.Result exec = Shell.cmd(RitualResultActivity.this.s_command).exec();
                    List<String> out = exec.getOut();
                    exec.getCode();
                    RitualResultActivity.this.b_command = exec.isSuccess();
                    RitualResultActivity.this.s_commandResult = RitualResultActivity$11$$ExternalSyntheticBackport0.m("\n", out);
                }
            }, 2000L);
            return;
        }
        finish();
    }

    public void _onExtraGms() {
        String stringExtra = getIntent().getStringExtra("WIPEGMS");
        this.s_extra = stringExtra;
        if (stringExtra.equals("true")) {
            this.oawipe.cancel();
            this.oawipe.setTarget(this.ln_wipe);
            this.oawipe.setPropertyName("alpha");
            this.oawipe.setFloatValues(0.0f, 1.0f);
            this.oawipe.setDuration(500L);
            this.oawipe.start();
            return;
        }
        this.ln_wipe.setVisibility(8);
        this.b_wipe = true;
    }

    public void _getExtraRitual() {
        _onExtraProp();
        _onExtraClean();
        _onExtraGms();
        _onExtraSsaid();
        _onExtraModpes();
        _onExtraReboot();
    }

    public void _onExtraModpes() {
        Runnable runnable = new Runnable() { // from class: com.fufufu.katrina.backup.RitualResultActivity.12
            @Override // java.lang.Runnable
            public void run() {
                if (!RitualResultActivity.this.b_prop || !RitualResultActivity.this.b_cleaner || !RitualResultActivity.this.b_ssaid || !RitualResultActivity.this.b_wipe) {
                    RitualResultActivity.this.Modpes.postDelayed(RitualResultActivity.this.runnableModpes, 500L);
                    return;
                }
                RitualResultActivity.this.Modpes.removeCallbacks(RitualResultActivity.this.runnableModpes);
                if (RitualResultActivity.this.getIntent().getStringExtra("MODPES").equals("false")) {
                    RitualResultActivity.this.b_modpes = true;
                    return;
                }
                RitualResultActivity.this.ln_prop.setVisibility(4);
                RitualResultActivity.this.ln_cleaner.setVisibility(4);
                RitualResultActivity.this.ln_wipe.setVisibility(4);
                RitualResultActivity.this.ln_ssaid.setVisibility(4);
                RitualResultActivity.this.ln_reboot.setVisibility(0);
                RitualResultActivity.this.tv_05.setText("Mode Pesawat");
                RitualResultActivity.this.tv_reboot.setText("Mematikan mode pesawat..");
                RitualResultActivity.this._onStartModpes();
            }
        };
        this.runnableModpes = runnable;
        this.Modpes.postDelayed(runnable, 0L);
    }

    public void _onStartModpes() {
        this.s_commandModpes = "settings put global airplane_mode_on 0\nam broadcast -a android.intent.action.AIRPLANE_MODE";
        this.b_command = false;
        Shell.Result exec = Shell.cmd("settings put global airplane_mode_on 0\nam broadcast -a android.intent.action.AIRPLANE_MODE").exec();
        List<String> out = exec.getOut();
        exec.getCode();
        this.b_command = exec.isSuccess();
        this.s_commandResult = RitualResultActivity$$ExternalSyntheticBackport0.m("\n", out);
        this.b_modpes = true;
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
