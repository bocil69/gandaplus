package com.fufufu.katrina.backup;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.budiyev.android.codescanner.BarcodeUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
/* loaded from: classes5.dex */
public class KatrinaIslandService extends AccessibilityService {
    public static final String ACTION_SHOW_TEXT = "com.fufufu.katrina.backup.action.SHOW_TEXT";
    public static final String EXTRA_TEXT = "com.fufufu.katrina.backup.extra.TEXT";
    private static final int REQUEST_WINDOW_HANDLE = 1;
    private ConnectivityReceiver connectivityReceiver;
    private String filePath;
    private View floatingIsland;
    private View floatingListview;
    private View floatingNotif;
    private View floatingView;
    private FrameLayout fr_base;
    private FrameLayout fr_base_island;
    private LinearLayout im_divider;
    private LinearLayout ln_content;
    private LinearLayout ln_content_expand;
    private LinearLayout ln_content_listview;
    private LinearLayout ln_content_notif;
    private LinearLayout ln_divider;
    private LinearLayout ln_dot;
    private LinearLayout ln_left;
    private LinearLayout ln_listview;
    private LinearLayout ln_right;
    private LinearLayout ln_shape;
    private LinearLayout ln_top_expand;
    private ListView lv_sh;
    private String mHandle;
    private double n_total_address;
    private double n_total_name;
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;
    private SharedPreferences prefip;
    private SharedPreferences prefrandom;
    private String s_front_address;
    private String s_insert;
    private String s_result_address;
    private String s_result_email;
    private String s_result_name;
    private String s_result_password;
    private int targetHeight;
    private int targetWidth;
    private TextView tv_01;
    private TextView tv_message;
    private Vibrator vibrate;
    private WindowManager windowManager;
    int width_value = 0;
    int margin_value = 0;
    int posX = 0;
    int posY = 0;
    CountDownTimer countDownTimer = null;
    String s_notif = "";
    private double n = 0.0d;
    private HashMap<String, Object> m_sh = new HashMap<>();
    private ArrayList<HashMap<String, Object>> lm_sh = new ArrayList<>();
    private ArrayList<String> ls_sh = new ArrayList<>();
    private ArrayList<String> ls_ip = new ArrayList<>();
    private Calendar getnow = Calendar.getInstance();
    private List<String> ls_random_name = new ArrayList();
    private List<String> ls_random_address = new ArrayList();
    private String mode = "Tidak Terhubung";

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.vibrate = (Vibrator) getSystemService("vibrator");
        this.connectivityReceiver = new ConnectivityReceiver(this, null);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.connectivityReceiver, intentFilter);
        SharedPreferences sharedPreferences = getSharedPreferences("preferences_xy", 0);
        this.posX = sharedPreferences.getInt("posX", 0);
        this.posY = sharedPreferences.getInt("posY", 0);
    }

    @Override // android.accessibilityservice.AccessibilityService
    protected void onServiceConnected() {
        SharedPreferences sharedPreferences = getSharedPreferences("floating_island_pref", 0);
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.1
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences2, String str) {
                if (str.equals("width_shape") || str.equals("margin_shape")) {
                    KatrinaIslandService.this.width_value = sharedPreferences2.getInt("width_shape", 80);
                    KatrinaIslandService.this.margin_value = sharedPreferences2.getInt("margin_shape", 0);
                    KatrinaIslandService katrinaIslandService = KatrinaIslandService.this;
                    katrinaIslandService.updateFloatingPopupSize(katrinaIslandService.width_value, KatrinaIslandService.this.margin_value);
                }
            }
        };
        this.preferenceChangeListener = onSharedPreferenceChangeListener;
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
        accessibilityServiceInfo.eventTypes = 32;
        accessibilityServiceInfo.feedbackType = 16;
        setServiceInfo(accessibilityServiceInfo);
        startService(new Intent(this, ScreenMonitorService.class));
        showFloatingPopup();
        showFloatingExpand();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        View view = this.floatingView;
        if (view != null) {
            this.windowManager.removeView(view);
        }
        getSharedPreferences("floating_island_pref", 0).unregisterOnSharedPreferenceChangeListener(this.preferenceChangeListener);
        unregisterReceiver(this.connectivityReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFloatingPopupSize(int i, int i2) {
        closeFloatingIsland();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.fr_base.getLayoutParams();
        layoutParams.width = -2;
        layoutParams.height = i;
        layoutParams.gravity = 17;
        this.fr_base.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(i, i);
        layoutParams2.gravity = 17;
        this.ln_shape.setLayoutParams(layoutParams2);
        WindowManager.LayoutParams windowManagerParams = windowManagerParams();
        windowManagerParams.gravity = 49;
        windowManagerParams.y = i2;
        WindowManager windowManager = (WindowManager) getSystemService("window");
        this.windowManager = windowManager;
        windowManager.updateViewLayout(this.floatingView, windowManagerParams);
    }

    private WindowManager.LayoutParams windowManagerParams() {
        if (Build.VERSION.SDK_INT >= 26) {
            return new WindowManager.LayoutParams(-2, -2, 2038, 776, -3);
        }
        return new WindowManager.LayoutParams(-2, -2, 2002, 776, -3);
    }

    public void checkPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("floating_island_pref", 0);
        this.width_value = sharedPreferences.getInt("width_shape", 80);
        this.margin_value = sharedPreferences.getInt("margin_shape", 90);
    }

    public void rippleView(View view) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor("#FF000000"));
        gradientDrawable.setCornerRadius((float) 100.0d);
        view.setBackground(new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{Color.parseColor("#4DFFFFFF")}), gradientDrawable, null));
    }

    public void rippleViewDiv(View view) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor("#20FFFFFF"));
        gradientDrawable.setCornerRadius((float) 100.0d);
        view.setBackground(new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{Color.parseColor("#4DFFFFFF")}), gradientDrawable, null));
    }

    public void rippleViewChip(View view) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor("#20FFFFFF"));
        gradientDrawable.setCornerRadius((float) 100.0d);
        view.setBackground(new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{Color.parseColor("#4DFFFFFF")}), gradientDrawable, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getRandomName() {
        this.prefrandom = getSharedPreferences("random_preferences", 0);
        if (this.ls_random_name.size() == 0) {
            try {
                this.ls_random_name = new ArrayList(Arrays.asList(SketchwareUtil.copyFromInputStream(getAssets().open("b.json")).split(",")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.s_result_name = "";
        try {
            this.n_total_name = Double.parseDouble(this.prefrandom.getString("s_total_name", ""));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            this.n_total_name = 2.0d;
        }
        for (int i = 0; i < ((int) this.n_total_name); i++) {
            String str = this.s_result_name;
            List<String> list = this.ls_random_name;
            this.s_result_name = str.concat(" ".concat(list.get(SketchwareUtil.getRandom(0, list.size()))));
        }
        this.s_result_name = this.s_result_name.substring(1);
        if (!this.prefrandom.getString("s_code_name", "").equals("")) {
            if (this.prefrandom.getString("s_location_name_code", "").equals("d")) {
                this.s_result_name = this.s_result_name.substring(this.s_result_name.indexOf(" ") + 1);
                this.s_result_name = this.prefrandom.getString("s_code_name", "").concat(" ".concat(this.s_result_name));
            } else {
                String substring = this.s_result_name.substring(0, this.s_result_name.lastIndexOf(" "));
                this.s_result_name = substring;
                this.s_result_name = substring.concat(" ".concat(this.prefrandom.getString("s_code_name", "")));
            }
        }
        String str2 = this.s_result_name;
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied Text", str2));
        this.s_notif = str2;
        showFloatingNotif();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getRandomEmail() {
        this.prefrandom = getSharedPreferences("random_preferences", 0);
        if (this.ls_random_name.size() == 0) {
            try {
                this.ls_random_name = new ArrayList(Arrays.asList(SketchwareUtil.copyFromInputStream(getAssets().open("b.json")).split(",")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<String> list = this.ls_random_name;
        String str = list.get(SketchwareUtil.getRandom(0, list.size()));
        this.s_result_email = str;
        String concat = str.concat(String.valueOf(SketchwareUtil.getRandom(99, 9999)));
        this.s_result_email = concat;
        String concat2 = concat.concat(this.prefrandom.getString("s_code_email", ""));
        this.s_result_email = concat2;
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied Text", concat2));
        this.s_notif = concat2;
        showFloatingNotif();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getRandomPassword() {
        SharedPreferences sharedPreferences = getSharedPreferences("random_preferences", 0);
        this.prefrandom = sharedPreferences;
        String string = sharedPreferences.getString("s_code_password", "");
        this.s_result_password = string;
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied Text", string));
        this.s_notif = string;
        showFloatingNotif();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getRandomTelepon() {
        String concat;
        this.prefrandom = getSharedPreferences("random_preferences", 0);
        Random random = new Random();
        String str = "";
        for (int i = 0; i < 12; i++) {
            int nextInt = random.nextInt(10);
            str = String.valueOf(str) + String.valueOf(nextInt);
        }
        if (this.prefrandom.getString("s_code_telepon", "").equals("")) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("0852");
            arrayList.add("0853");
            arrayList.add("0811");
            arrayList.add("0812");
            arrayList.add("0813");
            arrayList.add("0821");
            arrayList.add("0822");
            arrayList.add("0851");
            arrayList.add("0814");
            arrayList.add("0815");
            arrayList.add("0816");
            arrayList.add("0855");
            arrayList.add("0856");
            arrayList.add("0857");
            arrayList.add("0858");
            arrayList.add("0896");
            arrayList.add("0895");
            arrayList.add("0897");
            arrayList.add("0898");
            arrayList.add("0899");
            arrayList.add("0817");
            arrayList.add("0818");
            arrayList.add("0819");
            arrayList.add("0859");
            arrayList.add("0877");
            arrayList.add("0878");
            arrayList.add("0813");
            arrayList.add("0832");
            arrayList.add("0833");
            arrayList.add("0838");
            arrayList.add("0881");
            arrayList.add("0882");
            arrayList.add("0883");
            arrayList.add("0884");
            arrayList.add("0885");
            arrayList.add("0886");
            arrayList.add("0887");
            arrayList.add("0888");
            arrayList.add("0889");
            Collections.shuffle(arrayList);
            concat = ((String) arrayList.get(new Random().nextInt(arrayList.size()))).concat(str);
        } else {
            concat = this.prefrandom.getString("s_code_telepon", "").concat(str);
        }
        String substring = concat.substring(0, 12);
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied Text", substring));
        this.s_notif = substring;
        showFloatingNotif();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getRandomAddress() {
        this.prefrandom = getSharedPreferences("random_preferences", 0);
        if (this.ls_random_address.size() == 0) {
            try {
                this.ls_random_address = new ArrayList(Arrays.asList(SketchwareUtil.copyFromInputStream(getAssets().open("a.json")).split(",")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.s_front_address = new String[]{"Jalan", "Jln", "Jl", "Perumahan", "Perum", "Griya", "Toko", "Warung", "Rumah", "Gang", "Gg", "RT", "RW"}[new Random().nextInt(13)];
        this.s_result_address = "";
        try {
            this.n_total_address = Double.parseDouble(this.prefrandom.getString("s_total_address", ""));
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            this.n_total_address = 4.0d;
        }
        for (int i = 0; i < ((int) this.n_total_address); i++) {
            String str = this.s_result_address;
            List<String> list = this.ls_random_address;
            this.s_result_address = str.concat(" ".concat(list.get(SketchwareUtil.getRandom(0, list.size()))));
        }
        if (this.prefrandom.getString("s_code_address", "").equals("")) {
            this.s_result_address = this.s_front_address.concat(this.s_result_address);
        } else if (this.prefrandom.getString("s_location_address_code", "").equals("d")) {
            this.s_result_address = this.s_front_address.concat(" ".concat(this.prefrandom.getString("s_code_address", "").concat(this.s_result_address)));
        } else if (this.prefrandom.getString("s_location_address_code", "").equals("t")) {
            this.s_insert = " ".concat(this.prefrandom.getString("s_code_address", "").concat(" "));
            int length = this.s_result_address.length() / 2;
            this.s_result_address = this.s_front_address.concat(String.valueOf(this.s_result_address.substring(0, length)) + this.s_insert + this.s_result_address.substring(length));
        } else {
            this.s_result_address = this.s_front_address.concat(this.s_result_address.concat(" ".concat(this.prefrandom.getString("s_code_address", ""))));
        }
        String str2 = this.s_result_address;
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied Text", str2));
        this.s_notif = str2;
        showFloatingNotif();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeFloatingNotif() {
        View view = this.floatingNotif;
        if (view == null || !view.isShown()) {
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.tv_message, "alpha", 1.0f, 0.0f);
        ofFloat.setDuration(200L);
        ofFloat.start();
        ValueAnimator ofInt = ValueAnimator.ofInt(this.ln_content_notif.getMeasuredWidth(), 0);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = KatrinaIslandService.this.ln_content_notif.getLayoutParams();
                layoutParams.width = intValue;
                KatrinaIslandService.this.ln_content_notif.setLayoutParams(layoutParams);
            }
        });
        ofInt.setDuration(500L);
        ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (KatrinaIslandService.this.floatingNotif == null || !KatrinaIslandService.this.floatingNotif.isShown()) {
                    return;
                }
                KatrinaIslandService.this.windowManager.removeView(KatrinaIslandService.this.floatingNotif);
            }
        });
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.start();
        ValueAnimator ofInt2 = ValueAnimator.ofInt(this.ln_content_notif.getMeasuredHeight(), 0);
        ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = KatrinaIslandService.this.ln_content_notif.getLayoutParams();
                layoutParams.height = intValue;
                KatrinaIslandService.this.ln_content_notif.setLayoutParams(layoutParams);
            }
        });
        ofInt2.setDuration(400L);
        ofInt2.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt2.start();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null || intent.getAction() == null || !intent.getAction().equals(ACTION_SHOW_TEXT)) {
            return 2;
        }
        this.s_notif = intent.getStringExtra(EXTRA_TEXT);
        View view = this.floatingNotif;
        if (view != null && view.isShown()) {
            this.windowManager.removeView(this.floatingNotif);
        }
        showFloatingNotif();
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.fufufu.katrina.backup.KatrinaIslandService$5] */
    public void startCountdownTimer() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.countDownTimer = new CountDownTimer(3000L, 1000L) { // from class: com.fufufu.katrina.backup.KatrinaIslandService.5
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (KatrinaIslandService.this.floatingNotif.isAttachedToWindow()) {
                    KatrinaIslandService.this.closeFloatingNotif();
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFloatingNotif() {
        CountDownTimer countDownTimer = this.countDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        View view = this.floatingNotif;
        if (view != null && view.isShown()) {
            this.windowManager.removeView(this.floatingNotif);
        }
        View inflate = LayoutInflater.from(this).inflate(R.layout.admsoloraya_res_0x7f0d00a7, (ViewGroup) null);
        this.floatingNotif = inflate;
        FrameLayout frameLayout = (FrameLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01b2);
        this.ln_content_notif = (LinearLayout) this.floatingNotif.findViewById(R.id.admsoloraya_res_0x7f0a028b);
        this.tv_message = (TextView) this.floatingNotif.findViewById(R.id.admsoloraya_res_0x7f0a0528);
        checkPreferences();
        this.tv_message.setText(this.s_notif);
        if (this.s_notif.contains("NEW")) {
            this.tv_message.setTextColor(-16711936);
        } else if (this.s_notif.contains("OLD")) {
            this.tv_message.setTextColor(SupportMenu.CATEGORY_MASK);
        } else {
            this.tv_message.setTextColor(-1);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) frameLayout.getLayoutParams();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.gravity = 17;
        frameLayout.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        this.ln_content_notif.setLayoutParams(layoutParams2);
        this.tv_message.setAlpha(0.0f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.ln_content_notif, "alpha", 0.0f, 0.1f);
        ofFloat.setDuration(400L);
        ofFloat.start();
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                int height = KatrinaIslandService.this.ln_content_notif.getHeight();
                int width = KatrinaIslandService.this.ln_content_notif.getWidth();
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(KatrinaIslandService.this.ln_content_notif, "alpha", 0.1f, 1.0f);
                ofFloat2.setDuration(300L);
                ofFloat2.start();
                ValueAnimator ofInt = ValueAnimator.ofInt(30, width);
                ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.6.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                        ViewGroup.LayoutParams layoutParams3 = KatrinaIslandService.this.ln_content_notif.getLayoutParams();
                        layoutParams3.width = intValue;
                        KatrinaIslandService.this.ln_content_notif.setLayoutParams(layoutParams3);
                    }
                });
                ofInt.setDuration(400L);
                ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.6.2
                    /* JADX WARN: Type inference failed for: r0v0, types: [com.fufufu.katrina.backup.KatrinaIslandService$6$2$1] */
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator2) {
                        KatrinaIslandService.this.ln_content_notif.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.6.2.1
                            public GradientDrawable getIns(int i, int i2, int i3, int i4) {
                                setCornerRadius(i);
                                setStroke(i2, i3);
                                setColor(i4);
                                return this;
                            }
                        }.getIns(100, 0, 0, ViewCompat.MEASURED_STATE_MASK));
                    }

                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator2) {
                        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(KatrinaIslandService.this.tv_message, "alpha", 0.0f, 1.0f);
                        ofFloat3.setDuration(200L);
                        ofFloat3.start();
                        KatrinaIslandService.this.startCountdownTimer();
                    }
                });
                ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
                ofInt.start();
                ValueAnimator ofInt2 = ValueAnimator.ofInt(30, height);
                ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.6.3
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                        ViewGroup.LayoutParams layoutParams3 = KatrinaIslandService.this.ln_content_notif.getLayoutParams();
                        layoutParams3.height = intValue;
                        KatrinaIslandService.this.ln_content_notif.setLayoutParams(layoutParams3);
                    }
                });
                ofInt2.setDuration(600L);
                ofInt2.setInterpolator(new AccelerateDecelerateInterpolator());
                ofInt2.start();
            }
        });
        WindowManager.LayoutParams windowManagerParams = windowManagerParams();
        windowManagerParams.gravity = 49;
        int i = this.margin_value;
        int i2 = this.width_value;
        windowManagerParams.y = i + i2 + i2;
        WindowManager windowManager = (WindowManager) getSystemService("window");
        this.windowManager = windowManager;
        windowManager.addView(this.floatingNotif, windowManagerParams);
    }

    /* JADX WARN: Type inference failed for: r1v10, types: [com.fufufu.katrina.backup.KatrinaIslandService$8] */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.fufufu.katrina.backup.KatrinaIslandService$7] */
    private void showFloatingPopup() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.admsoloraya_res_0x7f0d00a8, (ViewGroup) null);
        this.floatingView = inflate;
        this.fr_base = (FrameLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01ae);
        this.ln_shape = (LinearLayout) this.floatingView.findViewById(R.id.admsoloraya_res_0x7f0a02e4);
        this.ln_dot = (LinearLayout) this.floatingView.findViewById(R.id.admsoloraya_res_0x7f0a0292);
        checkPreferences();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.fr_base.getLayoutParams();
        layoutParams.width = -2;
        layoutParams.height = this.width_value;
        layoutParams.gravity = 17;
        this.fr_base.setLayoutParams(layoutParams);
        int i = this.width_value;
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(i, i);
        layoutParams2.gravity = 17;
        this.ln_shape.setLayoutParams(layoutParams2);
        this.ln_shape.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.7
            public GradientDrawable getIns(int i2, int i3, int i4, int i5) {
                setCornerRadius(i2);
                setStroke(i3, i4);
                setColor(i5);
                return this;
            }
        }.getIns(BarcodeUtils.ROTATION_180, 0, 0, ViewCompat.MEASURED_STATE_MASK));
        this.ln_dot.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.8
            public GradientDrawable getIns(int i2, int i3, int i4, int i5) {
                setCornerRadius(i2);
                setStroke(i3, i4);
                setColor(i5);
                return this;
            }
        }.getIns(BarcodeUtils.ROTATION_180, 0, 0, -2236963));
        this.ln_shape.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (KatrinaIslandService.this.floatingIsland != null && KatrinaIslandService.this.floatingIsland.isShown()) {
                    KatrinaIslandService.this.windowManager.removeView(KatrinaIslandService.this.floatingIsland);
                    return;
                }
                KatrinaIslandService.this.showFloatingIsland();
                KatrinaIslandService.this.vibrateDevice();
            }
        });
        WindowManager.LayoutParams windowManagerParams = windowManagerParams();
        windowManagerParams.gravity = 49;
        windowManagerParams.y = this.margin_value;
        WindowManager windowManager = (WindowManager) getSystemService("window");
        this.windowManager = windowManager;
        windowManager.addView(this.floatingView, windowManagerParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeFloatingIsland() {
        View view = this.floatingIsland;
        if (view == null || !view.isShown()) {
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.ln_right, "alpha", 1.0f, 0.0f);
        ofFloat.setDuration(200L);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.ln_left, "alpha", 1.0f, 0.0f);
        ofFloat2.setDuration(200L);
        ofFloat.start();
        ofFloat2.start();
        ValueAnimator ofInt = ValueAnimator.ofInt(this.ln_content.getMeasuredWidth(), this.width_value);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.10
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = KatrinaIslandService.this.ln_content.getLayoutParams();
                layoutParams.width = intValue;
                KatrinaIslandService.this.ln_content.setLayoutParams(layoutParams);
            }
        });
        ofInt.setDuration(600L);
        ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.11
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (KatrinaIslandService.this.floatingIsland == null || !KatrinaIslandService.this.floatingIsland.isShown()) {
                    return;
                }
                KatrinaIslandService.this.windowManager.removeView(KatrinaIslandService.this.floatingIsland);
            }
        });
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.start();
        ValueAnimator ofInt2 = ValueAnimator.ofInt(this.ln_content.getMeasuredHeight(), this.width_value);
        ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.12
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = KatrinaIslandService.this.ln_content.getLayoutParams();
                layoutParams.height = intValue;
                KatrinaIslandService.this.ln_content.setLayoutParams(layoutParams);
            }
        });
        ofInt2.setDuration(400L);
        ofInt2.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt2.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r12v15, types: [com.fufufu.katrina.backup.KatrinaIslandService$14] */
    /* JADX WARN: Type inference failed for: r14v5, types: [com.fufufu.katrina.backup.KatrinaIslandService$13] */
    public void showFloatingIsland() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.admsoloraya_res_0x7f0d00a4, (ViewGroup) null);
        this.floatingIsland = inflate;
        this.fr_base_island = (FrameLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01b0);
        this.ln_divider = (LinearLayout) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a0291);
        this.im_divider = (LinearLayout) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a020f);
        this.ln_content = (LinearLayout) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a0288);
        this.ln_right = (LinearLayout) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a02d5);
        this.ln_left = (LinearLayout) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a02b1);
        ImageView imageView = (ImageView) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a01e7);
        ImageView imageView2 = (ImageView) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a01e9);
        ImageView imageView3 = (ImageView) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a01ea);
        ImageView imageView4 = (ImageView) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a01eb);
        ImageView imageView5 = (ImageView) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a01ec);
        ImageView imageView6 = (ImageView) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a01ed);
        ImageView imageView7 = (ImageView) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a01ee);
        ImageView imageView8 = (ImageView) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a01ef);
        ImageView imageView9 = (ImageView) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a01f0);
        ImageView imageView10 = (ImageView) this.floatingIsland.findViewById(R.id.admsoloraya_res_0x7f0a01e8);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
        this.targetWidth = (int) (displayMetrics.widthPixels * 0.95d);
        checkPreferences();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.fr_base_island.getLayoutParams();
        layoutParams.width = this.targetWidth;
        layoutParams.height = (int) (this.width_value * 1.9d);
        layoutParams.gravity = 17;
        this.fr_base_island.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.ln_divider.getLayoutParams();
        layoutParams2.width = this.width_value;
        layoutParams2.height = this.width_value;
        layoutParams2.gravity = 17;
        this.ln_divider.setLayoutParams(layoutParams2);
        int i = this.width_value;
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(i, i);
        layoutParams3.gravity = 49;
        this.ln_content.setLayoutParams(layoutParams3);
        this.ln_content.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.13
            public GradientDrawable getIns(int i2, int i3, int i4, int i5) {
                setCornerRadius(i2);
                setStroke(i3, i4);
                setColor(i5);
                return this;
            }
        }.getIns(BarcodeUtils.ROTATION_180, 0, 0, ViewCompat.MEASURED_STATE_MASK));
        this.im_divider.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.14
            public GradientDrawable getIns(int i2, int i3, int i4, int i5) {
                setCornerRadius(i2);
                setStroke(i3, i4);
                setColor(i5);
                return this;
            }
        }.getIns(BarcodeUtils.ROTATION_180, 0, 0, -2236963));
        this.ln_left.setAlpha(0.0f);
        this.ln_right.setAlpha(0.0f);
        this.ln_divider.setEnabled(true);
        imageView.setImageResource(R.drawable.admsoloraya_res_0x7f08010a);
        imageView2.setImageResource(R.drawable.admsoloraya_res_0x7f080111);
        imageView3.setImageResource(R.drawable.admsoloraya_res_0x7f08010d);
        imageView4.setImageResource(R.drawable.admsoloraya_res_0x7f080112);
        imageView5.setImageResource(R.drawable.admsoloraya_res_0x7f08010b);
        imageView6.setImageResource(R.drawable.admsoloraya_res_0x7f08010c);
        imageView7.setImageResource(R.drawable.admsoloraya_res_0x7f080114);
        imageView8.setImageResource(R.drawable.admsoloraya_res_0x7f08010e);
        imageView9.setImageResource(R.drawable.admsoloraya_res_0x7f080115);
        imageView10.setImageResource(R.drawable.admsoloraya_res_0x7f080113);
        rippleViewDiv(this.ln_divider);
        rippleView(imageView);
        rippleView(imageView2);
        rippleView(imageView3);
        rippleView(imageView4);
        rippleView(imageView5);
        rippleView(imageView6);
        rippleView(imageView7);
        rippleView(imageView8);
        rippleView(imageView9);
        rippleView(imageView10);
        ValueAnimator ofInt = ValueAnimator.ofInt(this.ln_content.getMeasuredWidth(), this.targetWidth);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.15
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams4 = KatrinaIslandService.this.ln_content.getLayoutParams();
                layoutParams4.width = intValue;
                KatrinaIslandService.this.ln_content.setLayoutParams(layoutParams4);
            }
        });
        ofInt.setDuration(400L);
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.start();
        ValueAnimator ofInt2 = ValueAnimator.ofInt(this.ln_content.getMeasuredHeight(), (int) (this.width_value * 1.9d));
        ofInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.16
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams4 = KatrinaIslandService.this.ln_content.getLayoutParams();
                layoutParams4.height = intValue;
                KatrinaIslandService.this.ln_content.setLayoutParams(layoutParams4);
            }
        });
        ofInt2.setDuration(500L);
        ofInt2.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt2.addListener(new AnimatorListenerAdapter() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.17
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(KatrinaIslandService.this.ln_right, "alpha", 0.0f, 1.0f);
                ofFloat.setDuration(200L);
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(KatrinaIslandService.this.ln_left, "alpha", 0.0f, 1.0f);
                ofFloat2.setDuration(200L);
                ofFloat.start();
                ofFloat2.start();
            }
        });
        ofInt2.start();
        this.ln_divider.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KatrinaIslandService.this.ln_divider.setEnabled(false);
                KatrinaIslandService.this.closeFloatingIsland();
                KatrinaIslandService.this.vibrateDevice();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KatrinaIslandService.this.getRandomAddress();
                KatrinaIslandService.this.vibrateDevice();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KatrinaIslandService.this.getRandomName();
                KatrinaIslandService.this.vibrateDevice();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KatrinaIslandService.this.getRandomEmail();
                KatrinaIslandService.this.vibrateDevice();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KatrinaIslandService.this.getRandomPassword();
                KatrinaIslandService.this.vibrateDevice();
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(KatrinaIslandService.this, ShortcutExecutorActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("shortcut_command", "backup");
                intent.putExtra("shortcut_desc", "backup");
                KatrinaIslandService.this.startActivity(intent);
                KatrinaIslandService.this.vibrateDevice();
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClassName("com.fufufu.katrina.backup", "com.fufufu.katrina.backup.DialogActivity");
                intent.addFlags(268435456);
                KatrinaIslandService.this.startActivity(intent);
                KatrinaIslandService.this.vibrateDevice();
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KatrinaIslandService.this.ln_divider.performClick();
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KatrinaIslandService.this.showFloatingExpand();
                KatrinaIslandService.this.ln_divider.performClick();
            }
        });
        imageView9.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KatrinaIslandService.this.getRandomTelepon();
                KatrinaIslandService.this.vibrateDevice();
            }
        });
        imageView10.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(KatrinaIslandService.this, ShortcutExecutorActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("shortcut_command", "restore");
                intent.putExtra("shortcut_desc", "restore");
                KatrinaIslandService.this.startActivity(intent);
                KatrinaIslandService.this.vibrateDevice();
            }
        });
        imageView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.29
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                Intent intent = new Intent(KatrinaIslandService.this, KatrinaActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("setelan", "true");
                KatrinaIslandService.this.startActivity(intent);
                KatrinaIslandService.this.vibrateDevice();
                return true;
            }
        });
        imageView2.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.30
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                Intent intent = new Intent(KatrinaIslandService.this, KatrinaActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("setelan", "true");
                KatrinaIslandService.this.startActivity(intent);
                KatrinaIslandService.this.vibrateDevice();
                return true;
            }
        });
        imageView3.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.31
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                Intent intent = new Intent(KatrinaIslandService.this, KatrinaActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("setelan", "true");
                KatrinaIslandService.this.startActivity(intent);
                KatrinaIslandService.this.vibrateDevice();
                return true;
            }
        });
        imageView4.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.32
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                Intent intent = new Intent(KatrinaIslandService.this, KatrinaActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("setelan", "true");
                KatrinaIslandService.this.startActivity(intent);
                KatrinaIslandService.this.vibrateDevice();
                return true;
            }
        });
        imageView9.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.33
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                Intent intent = new Intent(KatrinaIslandService.this, KatrinaActivity.class);
                intent.addFlags(268435456);
                intent.putExtra("setelan", "true");
                KatrinaIslandService.this.startActivity(intent);
                KatrinaIslandService.this.vibrateDevice();
                return true;
            }
        });
        imageView5.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.34
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                Intent intent = new Intent(KatrinaIslandService.this, KatrinaActivity.class);
                intent.addFlags(268435456);
                KatrinaIslandService.this.startActivity(intent);
                KatrinaIslandService.this.vibrateDevice();
                return true;
            }
        });
        imageView6.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.35
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                Intent intent = new Intent(KatrinaIslandService.this, KatrinaActivity.class);
                intent.addFlags(268435456);
                KatrinaIslandService.this.startActivity(intent);
                KatrinaIslandService.this.vibrateDevice();
                return true;
            }
        });
        imageView7.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.36
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                Intent intent = new Intent(KatrinaIslandService.this, KatrinaActivity.class);
                intent.addFlags(268435456);
                KatrinaIslandService.this.startActivity(intent);
                KatrinaIslandService.this.vibrateDevice();
                return true;
            }
        });
        imageView8.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.37
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                Intent intent = new Intent(KatrinaIslandService.this, KatrinaActivity.class);
                intent.addFlags(268435456);
                KatrinaIslandService.this.startActivity(intent);
                KatrinaIslandService.this.vibrateDevice();
                return true;
            }
        });
        imageView10.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.38
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                Intent intent = new Intent(KatrinaIslandService.this, KatrinaActivity.class);
                intent.addFlags(268435456);
                KatrinaIslandService.this.startActivity(intent);
                KatrinaIslandService.this.vibrateDevice();
                return true;
            }
        });
        WindowManager.LayoutParams windowManagerParams = windowManagerParams();
        windowManagerParams.gravity = 49;
        windowManagerParams.y = this.margin_value;
        ((WindowManager) getSystemService("window")).addView(this.floatingIsland, windowManagerParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFloatingExpand() {
        startService(new Intent(this, FloatingExpandService.class));
    }

    /* loaded from: classes5.dex */
    private class ConnectivityReceiver extends BroadcastReceiver {
        private ConnectivityReceiver() {
        }

        /* synthetic */ ConnectivityReceiver(KatrinaIslandService katrinaIslandService, ConnectivityReceiver connectivityReceiver) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager;
            String action = intent.getAction();
            if (action == null || !action.equals("android.net.conn.CONNECTIVITY_CHANGE") || (connectivityManager = (ConnectivityManager) KatrinaIslandService.this.getSystemService("connectivity")) == null) {
                return;
            }
            if (!(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) || KatrinaIslandService.this.isAirplaneModeOn(context)) {
                return;
            }
            KatrinaIslandService katrinaIslandService = KatrinaIslandService.this;
            katrinaIslandService.prefip = katrinaIslandService.getSharedPreferences("preferences_ip", 0);
            String ipAddress = getIpAddress(context);
            KatrinaIslandService.this.getnow = Calendar.getInstance();
            String format = new SimpleDateFormat("ddMMMyyyy").format(KatrinaIslandService.this.getnow.getTime());
            String string = KatrinaIslandService.this.prefip.getString("ip_today", "");
            KatrinaIslandService.this.ls_ip.clear();
            if (!string.equals("") && string.contains(format)) {
                KatrinaIslandService.this.ls_ip = (ArrayList) new Gson().fromJson(string, new TypeToken<ArrayList<String>>() { // from class: com.fufufu.katrina.backup.KatrinaIslandService.ConnectivityReceiver.1
                }.getType());
                if (KatrinaIslandService.this.ls_ip.contains(ipAddress)) {
                    KatrinaIslandService katrinaIslandService2 = KatrinaIslandService.this;
                    katrinaIslandService2.mode = "OLD : " + ipAddress;
                } else {
                    KatrinaIslandService katrinaIslandService3 = KatrinaIslandService.this;
                    katrinaIslandService3.mode = "NEW : " + ipAddress;
                    KatrinaIslandService.this.ls_ip.add(ipAddress);
                    KatrinaIslandService.this.prefip.edit().putString("ip_today", new Gson().toJson(KatrinaIslandService.this.ls_ip)).apply();
                }
            } else {
                KatrinaIslandService katrinaIslandService4 = KatrinaIslandService.this;
                katrinaIslandService4.mode = "NEW : " + ipAddress;
                KatrinaIslandService.this.ls_ip.add(format);
                KatrinaIslandService.this.ls_ip.add(ipAddress);
                KatrinaIslandService.this.prefip.edit().putString("ip_today", new Gson().toJson(KatrinaIslandService.this.ls_ip)).apply();
            }
            KatrinaIslandService katrinaIslandService5 = KatrinaIslandService.this;
            katrinaIslandService5.s_notif = katrinaIslandService5.mode;
            KatrinaIslandService.this.showFloatingNotif();
        }

        private String getIpAddress(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            return connectivityManager.getActiveNetworkInfo() != null ? getIpAddress(connectivityManager.getActiveNetworkInfo().getType()) : "N/A";
        }

        private String getIpAddress(int i) {
            try {
                for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                    for (InetAddress inetAddress : Collections.list(networkInterface.getInetAddresses())) {
                        if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && !inetAddress.isMulticastAddress()) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
                return "N/A";
            } catch (SocketException e) {
                e.printStackTrace();
                return "N/A";
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0;
    }

    public String getMode() {
        return this.mode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void vibrateDevice() {
        this.vibrate.vibrate(30L);
    }
}
