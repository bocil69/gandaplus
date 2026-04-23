package com.fufufu.katrina.backup;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.Key;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes5.dex */
public class WallActivity extends AppCompatActivity {
    private Button btn_gg;
    private Button btn_tt;
    private Button btn_ww;
    private FrameLayout fr_wall;
    private ImageView im_back;
    private ImageView im_mask;
    private ImageView im_wall;
    private LinearLayout ln_base;
    private LinearLayout ln_code;
    private FrameLayout ln_frame;
    private LinearLayout ln_mode;
    private LinearLayout ln_top;
    private LinearLayout ln_vertical;
    private LinearLayout ln_wall;
    private SharedPreferences prefwall;
    private TextView tv_code;
    private TextView tv_vertical;
    private double n = 0.0d;
    private String s_interstelar = "";
    private String c1 = "";
    private String c2 = "";
    private HashMap<String, Object> m_color = new HashMap<>();
    private String drawableName = "";
    private ArrayList<String> ls_interstelar = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_color = new ArrayList<>();
    private Calendar c = Calendar.getInstance();
    private Intent intent = new Intent();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d00cb);
        initialize(bundle);
        FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {
        this.ln_base = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0270);
        this.ln_frame = (FrameLayout) findViewById(R.id.admsoloraya_res_0x7f0a0298);
        this.ln_top = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02ed);
        this.ln_mode = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02bc);
        this.ln_wall = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02f5);
        this.fr_wall = (FrameLayout) findViewById(R.id.admsoloraya_res_0x7f0a01b5);
        this.im_back = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a020a);
        this.im_mask = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a021b);
        this.im_wall = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0226);
        this.ln_vertical = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02f3);
        this.ln_code = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0285);
        this.tv_vertical = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a05aa);
        this.tv_code = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04fd);
        this.btn_gg = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00bc);
        this.btn_tt = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00ff);
        this.btn_ww = (Button) findViewById(R.id.admsoloraya_res_0x7f0a0104);
        this.prefwall = getSharedPreferences("preferences_wall", 0);
        this.btn_gg.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.WallActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WallActivity.this.prefwall.edit().putString("mode", "gg").commit();
                WallActivity.this._setFirstUI();
            }
        });
        this.btn_tt.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.WallActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WallActivity.this.prefwall.edit().putString("mode", "tt").commit();
                WallActivity.this._setFirstUI();
            }
        });
        this.btn_ww.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.WallActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WallActivity.this.prefwall.edit().putString("mode", "ww").commit();
                WallActivity.this._setFirstUI();
            }
        });
    }

    private void initializeLogic() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(Color.parseColor("#FF000000"));
        }
        _setFirstUI();
    }

    public void _setHighLighter(TextView textView) {
        String str = this.c1;
        TextView textView2 = new TextView(this);
        TextView textView3 = new TextView(this);
        TextView textView4 = new TextView(this);
        TextView textView5 = new TextView(this);
        TextView textView6 = new TextView(this);
        TextView textView7 = new TextView(this);
        TextView textView8 = new TextView(this);
        TextView textView9 = new TextView(this);
        TextView textView10 = new TextView(this);
        TextView textView11 = new TextView(this);
        TextView textView12 = new TextView(this);
        textView2.setText("\\b(out|print|println|valueOf|toString|concat|equals|for|while|switch|getText");
        textView3.setText("|println|printf|print|out|parseInt|round|sqrt|charAt|compareTo|compareToIgnoreCase|concat|contains|contentEquals|equals|length|toLowerCase|trim|toUpperCase|toString|valueOf|substring|startsWith|split|replace|replaceAll|lastIndexOf|size)\\b");
        textView4.setText("\\b(public|private|protected|void|switch|case|class|import|package|extends|Activity|TextView|EditText|LinearLayout|CharSequence|String|int|onCreate|ArrayList|float|if|else|static|Intent|Button|SharedPreferences");
        textView5.setText("|abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|extends|final|finally|float|for|goto|if|implements|import|instanceof|interface|long|native|new|package|private|protected|");
        textView6.setText("public|return|short|static|strictfp|super|switch|synchronized|this|throw|throws|transient|try|void|volatile|while|when|true|false|null)\\b");
        textView7.setText("\\b([0-9]+)\\b");
        textView8.setText("(\\w+)(\\()+");
        textView9.setText("\\@\\s*(\\w+)");
        textView10.setText("\"(.*?)\"|'(.*?)'");
        textView11.setText("/\\*(?:.|[\\n\\r])*?\\*/|//.*");
        textView12.setText("\\b(Uzuakoli|Amoji|Bright|Ndudirim|Ezinwanne|Lightworker|Isuochi|Abia|Ngodo)\\b");
        textView.addTextChangedListener(new TextWatcher(textView2, textView3, str, textView4, textView5, textView6, textView7, textView8, textView10, textView11, textView9, textView12) { // from class: com.fufufu.katrina.backup.WallActivity.4
            ColorScheme keywords1;
            ColorScheme keywords2;
            ColorScheme keywords3;
            ColorScheme keywords4;
            ColorScheme keywords5;
            ColorScheme keywords6;
            ColorScheme keywords7;
            ColorScheme keywords8;
            final ColorScheme[] schemes;

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            {
                this.keywords1 = new ColorScheme(Pattern.compile(textView2.getText().toString().concat(textView3.getText().toString())), Color.parseColor(str));
                this.keywords2 = new ColorScheme(Pattern.compile(textView4.getText().toString().concat(textView5.getText().toString().concat(textView6.getText().toString()))), Color.parseColor("#9fe481"));
                this.keywords3 = new ColorScheme(Pattern.compile(textView7.getText().toString()), Color.parseColor("#f6e785"));
                this.keywords4 = new ColorScheme(Pattern.compile(textView8.getText().toString()), Color.parseColor(str));
                this.keywords5 = new ColorScheme(Pattern.compile(textView10.getText().toString()), Color.parseColor("#ff1744"));
                this.keywords6 = new ColorScheme(Pattern.compile(textView11.getText().toString()), Color.parseColor("#6a6a6a"));
                this.keywords7 = new ColorScheme(Pattern.compile(textView9.getText().toString()), Color.parseColor("#f6e785"));
                ColorScheme colorScheme = new ColorScheme(Pattern.compile(textView12.getText().toString()), Color.parseColor("#ff5722"));
                this.keywords8 = colorScheme;
                this.schemes = new ColorScheme[]{this.keywords1, this.keywords2, this.keywords3, this.keywords4, this.keywords5, this.keywords6, this.keywords7, colorScheme};
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                ColorScheme[] colorSchemeArr;
                removeSpans(editable, ForegroundColorSpan.class);
                for (ColorScheme colorScheme : this.schemes) {
                    Matcher matcher = colorScheme.pattern.matcher(editable);
                    while (matcher.find()) {
                        if (colorScheme == this.keywords4) {
                            editable.setSpan(new ForegroundColorSpan(colorScheme.color), matcher.start(), matcher.end() - 1, 33);
                        } else {
                            editable.setSpan(new ForegroundColorSpan(colorScheme.color), matcher.start(), matcher.end(), 33);
                        }
                    }
                }
            }

            void removeSpans(Editable editable, Class cls) {
                for (CharacterStyle characterStyle : (CharacterStyle[]) editable.getSpans(0, editable.length(), cls)) {
                    editable.removeSpan(characterStyle);
                }
            }

            /* renamed from: com.fufufu.katrina.backup.WallActivity$4$ColorScheme */
            /* loaded from: classes5.dex */
            class ColorScheme {
                final int color;
                final Pattern pattern;

                ColorScheme(Pattern pattern, int i) {
                    this.pattern = pattern;
                    this.color = i;
                }
            }
        });
    }

    public void _setWallpaper() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02f5);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Bitmap createBitmap = Bitmap.createBitmap(displayMetrics.widthPixels, displayMetrics.heightPixels, Bitmap.Config.ARGB_8888);
        this.ln_wall.draw(new Canvas(createBitmap));
        try {
            WallpaperManager.getInstance(getApplicationContext()).setBitmap(createBitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish();
    }

    private boolean is24HourFormat() {
        return DateFormat.is24HourFormat(this);
    }

    private int getRandomDrawableWW() {
        this.drawableName = "ww" + (new Random().nextInt(36) + 1);
        return getResources().getIdentifier(this.drawableName, "drawable", getPackageName());
    }

    private int getRandomDrawableGG() {
        return getResources().getIdentifier("gg" + (new Random().nextInt(50) + 1), "drawable", getPackageName());
    }

    public void _setFirstUI() {
        int i;
        int i2;
        HashMap<String, Object> hashMap = new HashMap<>();
        this.m_color = hashMap;
        hashMap.put("c1", "#FFBE7D7C");
        this.m_color.put("c2", "#FFDB9796");
        this.m_color.put("c3", "#FFF8B2B0");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap2 = new HashMap<>();
        this.m_color = hashMap2;
        hashMap2.put("c1", "#FF97C1A9");
        this.m_color.put("c2", "#FFB2DDC4");
        this.m_color.put("c3", "#FFCEFAE0");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap3 = new HashMap<>();
        this.m_color = hashMap3;
        hashMap3.put("c1", "#FFBFB6A3");
        this.m_color.put("c2", "#FFDBD2BE");
        this.m_color.put("c3", "#FFF7EEDA");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap4 = new HashMap<>();
        this.m_color = hashMap4;
        hashMap4.put("c1", "#FFA6C4FF");
        this.m_color.put("c2", "#FFC0D5FF");
        this.m_color.put("c3", "#FFD9E6FF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap5 = new HashMap<>();
        this.m_color = hashMap5;
        hashMap5.put("c1", "#FF8ED2AA");
        this.m_color.put("c2", "#FFA9EEC5");
        this.m_color.put("c3", "#FFC5FFE1");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap6 = new HashMap<>();
        this.m_color = hashMap6;
        hashMap6.put("c1", "#FFE2B6E2");
        this.m_color.put("c2", "#FFFFD2FF");
        this.m_color.put("c3", "#FFFFEEFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap7 = new HashMap<>();
        this.m_color = hashMap7;
        hashMap7.put("c1", "#FFBCC2EA");
        this.m_color.put("c2", "#FFD8DEFF");
        this.m_color.put("c3", "#FFF5FAFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap8 = new HashMap<>();
        this.m_color = hashMap8;
        hashMap8.put("c1", "#FFCEC8A1");
        this.m_color.put("c2", "#FFEAE4BC");
        this.m_color.put("c3", "#FFFFFFD8");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap9 = new HashMap<>();
        this.m_color = hashMap9;
        hashMap9.put("c1", "#FFB99B84");
        this.m_color.put("c2", "#FFD5B69E");
        this.m_color.put("c3", "#FFF2D1B9");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap10 = new HashMap<>();
        this.m_color = hashMap10;
        hashMap10.put("c1", "#FFF8863D");
        this.m_color.put("c2", "#FFFFA157");
        this.m_color.put("c3", "#FFFFBC71");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap11 = new HashMap<>();
        this.m_color = hashMap11;
        hashMap11.put("c1", "#FF4DC1AA");
        this.m_color.put("c2", "#FF6BDDC5");
        this.m_color.put("c3", "#FF89FAE1");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap12 = new HashMap<>();
        this.m_color = hashMap12;
        hashMap12.put("c1", "#FF76B3F4");
        this.m_color.put("c2", "#FF93CEFF");
        this.m_color.put("c3", "#FFB0EBFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap13 = new HashMap<>();
        this.m_color = hashMap13;
        hashMap13.put("c1", "#FFEF755D");
        this.m_color.put("c2", "#FFFF9076");
        this.m_color.put("c3", "#FFFFAB90");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap14 = new HashMap<>();
        this.m_color = hashMap14;
        hashMap14.put("c1", "#FFFF6666");
        this.m_color.put("c2", "#FFFF827F");
        this.m_color.put("c3", "#FFFF9E99");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap15 = new HashMap<>();
        this.m_color = hashMap15;
        hashMap15.put("c1", "#FFA2997C");
        this.m_color.put("c2", "#FFBDB496");
        this.m_color.put("c3", "#FFD9CFB1");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap16 = new HashMap<>();
        this.m_color = hashMap16;
        hashMap16.put("c1", "#FFA0BBA2");
        this.m_color.put("c2", "#FFBBD7BD");
        this.m_color.put("c3", "#FFD7F3D9");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap17 = new HashMap<>();
        this.m_color = hashMap17;
        hashMap17.put("c1", "#FF9AFF85");
        this.m_color.put("c2", "#FFB7FFA0");
        this.m_color.put("c3", "#FFD5FFBC");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap18 = new HashMap<>();
        this.m_color = hashMap18;
        hashMap18.put("c1", "#FFFF72C8");
        this.m_color.put("c2", "#FFFF8EE4");
        this.m_color.put("c3", "#FFFFABFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap19 = new HashMap<>();
        this.m_color = hashMap19;
        hashMap19.put("c1", "#FFE156AD");
        this.m_color.put("c2", "#FFFF72C8");
        this.m_color.put("c3", "#FFFF8FE5");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap20 = new HashMap<>();
        this.m_color = hashMap20;
        hashMap20.put("c1", "#FFC989FC");
        this.m_color.put("c2", "#FFE7A4FF");
        this.m_color.put("c3", "#FFFFC0FF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap21 = new HashMap<>();
        this.m_color = hashMap21;
        hashMap21.put("c1", "#FFB785DF");
        this.m_color.put("c2", "#FFD3A0FC");
        this.m_color.put("c3", "#FFF0BBFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap22 = new HashMap<>();
        this.m_color = hashMap22;
        hashMap22.put("c1", "#FFB2BCA8");
        this.m_color.put("c2", "#FFCED8C3");
        this.m_color.put("c3", "#FFEAF4DF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap23 = new HashMap<>();
        this.m_color = hashMap23;
        hashMap23.put("c1", "#FF9B99E0");
        this.m_color.put("c2", "#FFB7B4FC");
        this.m_color.put("c3", "#FFD3CFFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap24 = new HashMap<>();
        this.m_color = hashMap24;
        hashMap24.put("c1", "#FFD092B7");
        this.m_color.put("c2", "#FFD0A6C0");
        this.m_color.put("c3", "#FFD0BBC8");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap25 = new HashMap<>();
        this.m_color = hashMap25;
        hashMap25.put("c1", "#FF64B8FF");
        this.m_color.put("c2", "#FF84D3FF");
        this.m_color.put("c3", "#FFA3EFFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap26 = new HashMap<>();
        this.m_color = hashMap26;
        hashMap26.put("c1", "#FFB3E3FF");
        this.m_color.put("c2", "#FFCFFFFF");
        this.m_color.put("c3", "#FFECFFFF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap27 = new HashMap<>();
        this.m_color = hashMap27;
        hashMap27.put("c1", "#FFADACAB");
        this.m_color.put("c2", "#FFBEB1AB");
        this.m_color.put("c3", "#FFCFB7AB");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap28 = new HashMap<>();
        this.m_color = hashMap28;
        hashMap28.put("c1", "#FFD5A87F");
        this.m_color.put("c2", "#FFF3C399");
        this.m_color.put("c3", "#FFFFDFB4");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap29 = new HashMap<>();
        this.m_color = hashMap29;
        hashMap29.put("c1", "#FFFFB7E1");
        this.m_color.put("c2", "#FFFFD3FE");
        this.m_color.put("c3", "#FFFFF0FF");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap30 = new HashMap<>();
        this.m_color = hashMap30;
        hashMap30.put("c1", "#ffcbcd69");
        this.m_color.put("c2", "#ffeced87");
        this.m_color.put("c3", "#ffeced87");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap31 = new HashMap<>();
        this.m_color = hashMap31;
        hashMap31.put("c1", "#ffa9844d");
        this.m_color.put("c2", "#ffc3a269");
        this.m_color.put("c3", "#ffd8c087");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap32 = new HashMap<>();
        this.m_color = hashMap32;
        hashMap32.put("c1", "#ffaf965f");
        this.m_color.put("c2", "#ffcfb47b");
        this.m_color.put("c3", "#ffe4cb9a");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap33 = new HashMap<>();
        this.m_color = hashMap33;
        hashMap33.put("c1", "#ffa39761");
        this.m_color.put("c2", "#ffc3b67e");
        this.m_color.put("c3", "#ffe3d59c");
        this.lm_color.add(this.m_color);
        HashMap<String, Object> hashMap34 = new HashMap<>();
        this.m_color = hashMap34;
        hashMap34.put("c1", "#ffc94139");
        this.m_color.put("c2", "#ffed5c53");
        this.m_color.put("c3", "#ffff7c70");
        this.lm_color.add(this.m_color);
        Collections.shuffle(this.lm_color);
        HashMap<String, Object> hashMap35 = this.lm_color.get(0);
        double random = SketchwareUtil.getRandom(1, 3);
        this.n = random;
        if (1.0d == random) {
            this.c1 = hashMap35.get("c1").toString();
            this.c2 = hashMap35.get("c1").toString();
        } else if (2.0d == random) {
            this.c1 = hashMap35.get("c2").toString();
            this.c2 = hashMap35.get("c1").toString();
        } else {
            this.c1 = hashMap35.get("c3").toString();
            this.c2 = hashMap35.get("c1").toString();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.ln_wall.setLayoutParams(new LinearLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels));
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.tv_vertical, Key.ROTATION, 0.0f, 90.0f);
        ofFloat.setDuration(10L);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.tv_vertical, "translationX", 0.0f, (-i) / 2.7f);
        ofFloat2.setDuration(10L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.start();
        this.tv_vertical.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/line.ttf"), 0);
        this.tv_vertical.setTextSize(40.0f);
        this.tv_code.setText("");
        this.im_back.setImageResource(R.drawable.admsoloraya_res_0x7f0801e8);
        if (this.prefwall.getString("mode", "").equals("gg")) {
            this.btn_gg.setAlpha(0.5f);
            this.btn_ww.setAlpha(1.0f);
            this.btn_tt.setAlpha(1.0f);
            this.ln_code.setVisibility(4);
            this.ln_vertical.setVisibility(0);
            this.s_interstelar = "ACCRETION DISK\nANDROMEDA GALAXY\nAPHELION\nASTEROID\nASTROBIOLOGY\nASTROLABE\nASTROMETRY\nASTRONOMICAL UNIT\nASTROPARTICLE PHYSICS\nAURORA AUSTRALIS\nAURORA BOREALIS\nBLACK HOLE\nBLUESHIFT\nBROWN DWARF\nCELESTIAL BODY\nCELESTIAL COORDINATES\nCELESTIAL EQUATOR\nCELESTIAL MECHANICS\nCELESTIAL SPHERE\nCHANDRASEKHAR LIMIT\nCOMET\nCOMOVING DISTANCE\nCONSTELLATION\nCORONA\nCORONAL MASS EJECTION\nCOSMIC DUST\nCOSMIC DUST GRAIN\nCOSMIC INFLATION\nCOSMIC MICROWAVE\nCOSMIC RAY\nCOSMIC STRINGS\nCOSMIC WEB\nCOSMOGONY\nCOSMOLOGICAL CONSTANT\nCOSMOLOGY\nDARK ENERGY\nDARK MATTER\nDARK NEBULA\nDARK SKY RESERVE\nDWARF PLANET\nECCENTRICITY\nELECTROMAGNETIC SPECTRUM\nELLIPTICAL GALAXY\nELLIPTICAL ORBIT\nEVENT HORIZON\nEVENT HORIZON TELESCOPE\nEXOPLANET\nGALACTIC BULGE\nGALACTIC CLUSTER\nGALACTIC HALO\nGALAXY\nGAMMA-RAY BURST\nGAS GIANT\nGEOCENTRIC MODEL\nGEOSYNCHRONOUS ORBIT\nGIANT IMPACT HYPOTHESIS\nGIANT MOLECULAR CLOUD\nGRAVITATIONAL CONSTANT\nGRAVITATIONAL LENS\nGRAVITATIONAL WAVE\nGREAT RED SPOT\nHAWKING RADIATION\nHELIOCENTRIC MODEL\nHELIOCHROMOLOGY\nHELIOSEISMOLOGY\nHELIOSPHERE\nHELIOSYNCHRONOUS ORBIT\nHERTZSPRUNG-RUSSELL DIAGRAM\nHUBBLE SPACE TELESCOPE\nINTERFEROMETRY\nINTERPLANETARY DUST\nINTERPLANETARY MAGNETIC\nINTERPLANETARY MEDIUM\nINTERSTELLAR MEDIUM\nINTERSTELLAR REDDENING\nKUIPER BELT\nLAGRANGE POINT\nLIGHT POLLUTION\nLIGHT-YEAR\nLOCAL GROUP\nLUMINOSITY\nLUMINOSITY CLASS\nLUNAR ECLIPSE\nLYMAN-ALPHA FOREST\nMAGNETAR\nMAGNETOHYDRODYNAMICS\nMAGNETOPAUSE\nMAGNETOSPHERE\nMICROGRAVITY\nMILKY WAY\nNEBULA\nOORT CLOUD\nORBIT\nPARALLAX\nPARSEC\nPENUMBRAL ECLIPSE\nPERIHELION\nPERSEID METEOR SHOWER\nPHOTOSPHERE\nPLANETARY DIFFERENTIATION\nPLANETARY NEBULA\nPLANETARY PROTECTION\nPLANETARY RING\nPLANETARY SYSTEM\nPOLARIMETER\nPROGRADE MOTION\nPROTOPLANETARY DISK\nPULSAR\nQUASAR\nRADIO GALAXY\nRED GIANT\nREDSHIFT\nRETROGRADE MOTION\nROCHE LIMIT\nROCHE LOBE\nSOLAR CYCLE\nSOLAR ECLIPSE\nSOLAR FLARE\nSOLAR SYSTEM\nSOLAR WIND\nSPECTROSCOPY\nSPIRAL GALAXY\nSTELLAR EVOLUTION\nSTELLAR NURSERY\nSTELLAR PARALLAX\nSUPERMASSIVE BLACK HOLE\nSUPERNOVA\nSYNCHROTRON RADIATION\nTELESCOPE\nTERRESTRIAL PLANET\nTIDAL FORCE\nTIDAL LOCKING\nTRANS-NEPTUNIAN OBJECT\nTRANSITS OF VENUS\nTYCHO SUPERNOVA REMNANT\nVARIABLE STAR\nWHITE DWARF\nX-RAY BINARY\nZENITH\nZODIACAL LIGHT\nBACKGROUND RADIATION\nPLANETARY MOTION";
            this.ls_interstelar = new ArrayList<>(Arrays.asList(this.s_interstelar.split("\n")));
            this.s_interstelar = this.ls_interstelar.get(new Random().nextInt(this.ls_interstelar.size()));
            this.im_wall.setImageResource(getRandomDrawableGG());
            this.im_back.setColorFilter(Color.parseColor("#FF000000"), PorterDuff.Mode.MULTIPLY);
            this.im_wall.setColorFilter(Color.parseColor(this.c1), PorterDuff.Mode.MULTIPLY);
            this.tv_vertical.setText(this.s_interstelar);
            this.tv_vertical.setTranslationY(0.0f);
        } else if (this.prefwall.getString("mode", "").equals("tt")) {
            this.btn_tt.setAlpha(0.5f);
            this.btn_gg.setAlpha(1.0f);
            this.btn_ww.setAlpha(1.0f);
            _setHighLighter(this.tv_code);
            this.ln_code.setVisibility(0);
            this.ln_vertical.setVisibility(4);
            this.s_interstelar = "xxxxxx\n\n// life motto\n\nwhile (me.Alive) {\n\tme.WakeUp();\n\tme.Eat();\n\tme.DoCode();\n\tme.Sleep();\n}\nxxxxxx\n\n// life motto\n\nif (sad() = true) {\n\tsad().stop();\n\tbeAwesome();\n}\nxxxxxx\n\n// life motto\n\nwhile (true) {\n\tbitches.fuck() ;\n\tmoney.get();\n}\nxxxxxx\n\n// life motto\n\nif (me != success) {\n\tpoverty.get() ;\n} else {\n\tlife.great();\n\tmoney.get();\n\tlive.myDream();\n}\nxxxxxx\n\n// life motto\n\nLife myLife = new Life();\nmyLife.startLife();\nwhile(!myLife.makeSuccess()) {\n\tmyLife.tryAgain();\n\tif (myLife.death()) {\n\t\tbreak;\n\t}\n}\nxxxxxx\n\n// time is running\n\nt = 0\nwhile True:\nprint(\" Nothing lasts forever. \")\nt += 1\nxxxxxx\n\n// inspiration\n\n#include <inspiration.h>\nart me(her) {\n\tif(sheSmiles == true) {\n\t\treturn poetry;\n\t}\n}\nxxxxxx\n\n// bug\n\nwhile (fixingBugs()) {\n\tcode();\n\tdeepBreath();\n\tkeepCalm();\n}\nxxxxxx\n\n// i am tired\n\nif (tired) {\n\ttakeNap();\n\tcode();\n\trepeat();\n}\nxxxxxx\n\n// i am hungry\n\nif (hungry) {\n\torderPizza();\n\tcode();\n\tenjoySlice();\n}\nxxxxxx\n\n// i am on fire\n\nif (feeling Productive()) {\n\tcode();\n\tpatSelfOnBack();\n\tplanWorldDomination();\n}\nxxxxxx\n\n// ngelu\n\nwhile (brainstorming()) {\n\tcode();\n\ttakeCups0fCoffee();\n\twaitForIdeas();\n}\nxxxxxx\n\n// mumet\n\nwhile (ndasMumet()) {\n\ttry {\n\t\tududDulu();\n\t\tngopiDulu();\n\t\tnungguIde();\n\t}\n}\nxxxxxx\n\n// jangan sedih\n\nwhile (sedih = true) {\n\ttry {\n\t\tsedih.stop();\n\t\tsenyum.start();\n\t\tberbahagialah();\n\t}\n}\nxxxxxx\n\n// ngantuk\n\nwhile (levelKopi == 0) {\n\ttry {\n\t\tambilCangkir();\n\t\tbikinLagi();\n\t\tsruput.start();\n\t}\n}\nxxxxxx\n\n// kepising\n\nwhile (mules2 == 0) {\n\ttry {\n\t\tngising();\n\t\tngising();\n\t\tngising();\n\t\tndang_ngising();\n\t}\n}\nxxxxxx\n\n// it's up to you\n\nif (hardwork) == true) {\n\tsuccess();\n} else { \n\tstruggle();\n}\nxxxxxx\n\n// algorithm of success\n\n#include <life.h>\nwhile (!success) {\n\ttryAgain();\n\tif (success) {\n\t\timprove();\n\t}\n}\nxxxxxx\n\n// life motto\n\nwhile (noSuccess) {\n\ttryAgain();\n\tif (dead) {\n\t\tbreak;\n\t}\n}\nxxxxxx\n\n// about coffee\n\nCoffee coffee = new Coffee();\nif (coffee.Empty) {\n\tcoffee.Refill();\n} else {\n\tcoffee.Drink();\n}\nxxxxxx\n\npublic class algorithmOfSuccess() {\n\t// algorithm of success\n\tpublic static void main(String[] args) {\n\t\twhile (!success) {\n\t\t\ttryAgain();\n\t\t\tif (dead)\n\t\t\tbreak;\n\t\t}\n\t}\n}\npublic static void tryAgain() {\n\tsuccess = confidence && hardwork;\n}\nxxxxxx\n\n// happiness is\n\nwhen (your.code()) {\n\truns.without();\n\terrors();\n}\nxxxxxx\n\n// bahagia adalah\n\nwhen (rejeki.kamu()) {\n\tmengalir.dengan();\n\tlancar();\n}\nxxxxxx\n\n// life motto\n\nif (saldo.equals, \"nol\") {\n    sad = false;\n    kerja.lagi();\n\tbissmillah();\n}\nxxxxxx\n\n// life motto\n\nif (feeling.equals, \"positive\") {\n    be = yourSelf;\n}\nxxxxxx\n\nalways:\ntry {\n\tyour best and;\n\tdo {\n\t\twhat you need to do;\n\t} while (you still have the time);\n\tfor (opportunity; comes; only once) {\n\t\tso grab the chance;\n\t}\n}\nif (you fail)\nthrow \"all your worries\";\ncatch (yourself) {\n\teverytime you fall;\n\tgoto always;\n}\n";
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(this.s_interstelar.split("xxxxxx")));
            this.ls_interstelar = arrayList;
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                if (it.next().isEmpty()) {
                    it.remove();
                }
            }
            this.s_interstelar = this.ls_interstelar.get(new Random().nextInt(this.ls_interstelar.size()));
            getRandomDrawableGG();
            this.im_wall.setImageResource(R.drawable.admsoloraya_res_0x7f0801e8);
            this.im_back.setColorFilter(Color.parseColor(this.c2), PorterDuff.Mode.MULTIPLY);
            this.im_wall.setColorFilter(Color.parseColor("#dd000000"), PorterDuff.Mode.MULTIPLY);
            this.tv_code.setText(this.s_interstelar);
        } else {
            this.btn_ww.setAlpha(0.5f);
            this.btn_gg.setAlpha(1.0f);
            this.btn_tt.setAlpha(1.0f);
            this.ln_code.setVisibility(4);
            this.ln_vertical.setVisibility(0);
            this.im_wall.setImageResource(getRandomDrawableWW());
            this.im_back.setColorFilter(Color.parseColor(this.c1), PorterDuff.Mode.MULTIPLY);
            this.im_wall.setColorFilter(Color.parseColor(this.c1), PorterDuff.Mode.MULTIPLY);
            if (this.drawableName.equals("ww1")) {
                this.tv_vertical.setText("Province Aceh");
            }
            if (this.drawableName.equals("ww2")) {
                this.tv_vertical.setText("Province Bali");
            }
            if (this.drawableName.equals("ww3")) {
                this.tv_vertical.setText("Province Banten");
            }
            if (this.drawableName.equals("ww4")) {
                this.tv_vertical.setText("Province Gorontalo");
            }
            if (this.drawableName.equals("ww5")) {
                this.tv_vertical.setText("Province Jakarta");
            }
            if (this.drawableName.equals("ww6")) {
                this.tv_vertical.setText("Province Jambi");
            }
            if (this.drawableName.equals("ww7")) {
                this.tv_vertical.setText("Jawa Barat");
            }
            if (this.drawableName.equals("ww8")) {
                this.tv_vertical.setText("Jawa Tengah");
            }
            if (this.drawableName.equals("ww9")) {
                this.tv_vertical.setText("Jawa Timur");
            }
            if (this.drawableName.equals("ww10")) {
                this.tv_vertical.setText("Kalimantan Selatan");
            }
            if (this.drawableName.equals("ww11")) {
                this.tv_vertical.setText("Kalimantan Tengah");
            }
            if (this.drawableName.equals("ww12")) {
                this.tv_vertical.setText("Kalimantan Timur");
            }
            if (this.drawableName.equals("ww13")) {
                this.tv_vertical.setText("Bangka Belitung");
            }
            if (this.drawableName.equals("ww14")) {
                this.tv_vertical.setText("Province Lampung");
            }
            if (this.drawableName.equals("ww15")) {
                this.tv_vertical.setText("Maluku Utara");
            }
            if (this.drawableName.equals("ww16")) {
                this.tv_vertical.setText("Province Maluku");
            }
            if (this.drawableName.equals("ww17")) {
                this.tv_vertical.setText("Nusa Tenggara Barat");
            }
            if (this.drawableName.equals("ww18")) {
                this.tv_vertical.setText("Nusa Tenggara Timur");
            }
            if (this.drawableName.equals("ww19")) {
                this.tv_vertical.setText("Papua Barat");
            }
            if (this.drawableName.equals("ww20")) {
                this.tv_vertical.setText("Province Riau");
            }
            if (this.drawableName.equals("ww21")) {
                this.tv_vertical.setText("Sulawesi Barat");
            }
            if (this.drawableName.equals("ww22")) {
                this.tv_vertical.setText("Sulawesi Selatan");
            }
            if (this.drawableName.equals("ww23")) {
                this.tv_vertical.setText("Sulawesi Tengah");
            }
            if (this.drawableName.equals("ww24")) {
                this.tv_vertical.setText("Sulawesi Tenggara");
            }
            if (this.drawableName.equals("ww25")) {
                this.tv_vertical.setText("Sulawesi Utara");
            }
            if (this.drawableName.equals("ww26")) {
                this.tv_vertical.setText("Sumatera Barat");
            }
            if (this.drawableName.equals("ww27")) {
                this.tv_vertical.setText("Sumatera Selatan");
            }
            if (this.drawableName.equals("ww28")) {
                this.tv_vertical.setText("Province Yogyakarta");
            }
            if (this.drawableName.equals("ww29")) {
                this.tv_vertical.setText("Kalimantan Utara");
            }
            if (this.drawableName.equals("ww30")) {
                this.tv_vertical.setText("Pulau Kalimantan");
            }
            if (this.drawableName.equals("ww31")) {
                this.tv_vertical.setText("Province Bengkulu");
            }
            if (this.drawableName.equals("ww32")) {
                this.tv_vertical.setText("Western Indonesia");
            }
            if (this.drawableName.equals("ww33")) {
                this.tv_vertical.setText("Middle Indonesia");
            }
            if (this.drawableName.equals("ww34")) {
                this.tv_vertical.setText("Eastern Indonesia");
            }
            if (this.drawableName.equals("ww35")) {
                this.tv_vertical.setText("Republic Of Indonesia");
            }
            if (this.drawableName.equals("ww36")) {
                this.tv_vertical.setText("Republic Of Indonesia");
            }
        }
        Calendar calendar = Calendar.getInstance();
        if (is24HourFormat()) {
            i2 = calendar.get(11);
        } else {
            int i3 = calendar.get(10);
            i2 = calendar.get(9) == 1 ? i3 + 12 : i3;
        }
        if (i2 >= 4 && i2 < 9) {
            this.im_mask.setImageResource(R.drawable.admsoloraya_res_0x7f0801eb);
        } else if (i2 >= 9 && i2 < 16) {
            this.im_mask.setImageResource(R.drawable.admsoloraya_res_0x7f0801ec);
        } else if (i2 >= 16 && i2 < 18) {
            this.im_mask.setImageResource(R.drawable.admsoloraya_res_0x7f0801ea);
        } else {
            this.im_mask.setImageResource(R.drawable.admsoloraya_res_0x7f0801e9);
        }
        if (getIntent().hasExtra("setwall")) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            startActivity(intent);
            this.ln_mode.setVisibility(8);
            new Handler().postDelayed(new Runnable() { // from class: com.fufufu.katrina.backup.WallActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    WallActivity.this._setWallpaper();
                }
            }, 200L);
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
