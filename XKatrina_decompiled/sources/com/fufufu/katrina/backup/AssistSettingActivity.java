package com.fufufu.katrina.backup;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.elevation.SurfaceColors;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
/* loaded from: classes5.dex */
public class AssistSettingActivity extends AppCompatActivity {
    private Button btn_oke;
    private MaterialButton btn_save;
    private MaterialButton btn_save_note;
    private MaterialButton btn_save_toolbar;
    private EditText et_match;
    private EditText et_note;
    private EditText et_toolbar;
    private ImageView im_01;
    private ImageView im_02;
    private ImageView im_03;
    private ImageView im_04;
    private ImageView im_05;
    private ImageView im_06;
    private ImageView im_07;
    private ImageView im_08;
    private ImageView im_09;
    private ImageView im_10;
    private ImageView im_11;
    private ImageView im_12;
    private ImageView im_13;
    private ImageView im_14;
    private ImageView im_15;
    private ImageView im_result;
    private LinearLayout ln_01;
    private LinearLayout ln_02;
    private LinearLayout ln_03;
    private LinearLayout ln_04;
    private LinearLayout ln_1;
    private LinearLayout ln_3;
    private LinearLayout ln_5;
    private LinearLayout ln_6;
    private LinearLayout ln_intent;
    private LinearLayout ln_setting;
    private MaterialCardView materialcardview1;
    private MaterialCardView materialcardview2;
    private MaterialCardView materialcardview3;
    private MaterialCardView mcv1;
    private MaterialCardView mcv10;
    private MaterialCardView mcv11;
    private MaterialCardView mcv12;
    private MaterialCardView mcv13;
    private MaterialCardView mcv14;
    private MaterialCardView mcv15;
    private MaterialCardView mcv2;
    private MaterialCardView mcv3;
    private MaterialCardView mcv4;
    private MaterialCardView mcv5;
    private MaterialCardView mcv6;
    private MaterialCardView mcv7;
    private MaterialCardView mcv8;
    private MaterialCardView mcv9;
    private SharedPreferences pref_assist;
    private SharedPreferences preflast;
    private SharedPreferences prefui;
    private TextView tv_1;
    private TextView tv_result;
    private TextView tv_set1;
    private TextView tv_set2;
    private ScrollView vscr_1;
    private String s_key = "";
    private String s_package = "";
    private String s_json_backup = "";
    private String s_json_path = "";
    private HashMap<String, Object> m_json = new HashMap<>();
    private String s_new_note = "";
    private String s_result = "";
    private String s_command = "";
    private boolean b_command = false;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d0020);
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
        this.ln_intent = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02ac);
        this.ln_setting = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02e1);
        this.im_result = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a021f);
        this.tv_result = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a0545);
        this.btn_oke = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        this.vscr_1 = (ScrollView) findViewById(R.id.admsoloraya_res_0x7f0a05be);
        this.ln_6 = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0269);
        this.materialcardview1 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a0354);
        this.materialcardview2 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a0355);
        this.materialcardview3 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a0356);
        this.ln_02 = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0254);
        this.ln_1 = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0263);
        this.tv_1 = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04de);
        this.et_toolbar = (EditText) findViewById(R.id.admsoloraya_res_0x7f0a0190);
        this.btn_save_toolbar = (MaterialButton) findViewById(R.id.admsoloraya_res_0x7f0a00f3);
        this.ln_3 = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0265);
        this.tv_set1 = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a0552);
        this.et_match = (EditText) findViewById(R.id.admsoloraya_res_0x7f0a018a);
        this.btn_save = (MaterialButton) findViewById(R.id.admsoloraya_res_0x7f0a00ea);
        this.ln_5 = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0268);
        this.tv_set2 = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a0553);
        this.et_note = (EditText) findViewById(R.id.admsoloraya_res_0x7f0a018d);
        this.btn_save_note = (MaterialButton) findViewById(R.id.admsoloraya_res_0x7f0a00f0);
        this.ln_01 = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a024a);
        this.ln_03 = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0259);
        this.ln_04 = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a025d);
        this.mcv1 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a0371);
        this.mcv2 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a0378);
        this.mcv3 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a0379);
        this.mcv4 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a037a);
        this.mcv5 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a037b);
        this.im_01 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a01f1);
        this.im_02 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a01f8);
        this.im_03 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a01f9);
        this.im_04 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a01fa);
        this.im_05 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a01fb);
        this.mcv6 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a037c);
        this.mcv7 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a037d);
        this.mcv8 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a037e);
        this.mcv9 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a037f);
        this.mcv10 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a0372);
        this.im_06 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a01fc);
        this.im_07 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a01fd);
        this.im_08 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a01fe);
        this.im_09 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a01ff);
        this.im_10 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0201);
        this.mcv11 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a0373);
        this.mcv12 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a0374);
        this.mcv13 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a0375);
        this.mcv14 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a0376);
        this.mcv15 = (MaterialCardView) findViewById(R.id.admsoloraya_res_0x7f0a0377);
        this.im_11 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0202);
        this.im_12 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0203);
        this.im_13 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0204);
        this.im_14 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0205);
        this.im_15 = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0206);
        this.pref_assist = getSharedPreferences("assist_preferences", 0);
        this.preflast = getSharedPreferences("preferences_last", 0);
        this.prefui = getSharedPreferences("preferences_ui", 0);
        this.btn_oke.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this.finish();
            }
        });
        this.btn_save_toolbar.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity assistSettingActivity = AssistSettingActivity.this;
                assistSettingActivity._settingFindMatch("toolbar", assistSettingActivity.et_toolbar.getText().toString());
            }
        });
        this.btn_save.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity assistSettingActivity = AssistSettingActivity.this;
                assistSettingActivity._settingFindMatch("assist_key", assistSettingActivity.et_match.getText().toString());
            }
        });
        this.btn_save_note.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity assistSettingActivity = AssistSettingActivity.this;
                assistSettingActivity._settingFindMatch("update_note", assistSettingActivity.et_note.getText().toString());
            }
        });
        this.mcv1.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FFE8DFF5");
            }
        });
        this.mcv2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FFFCE1E4");
            }
        });
        this.mcv3.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FFFCF4DD");
            }
        });
        this.mcv4.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FFDDEDEA");
            }
        });
        this.mcv5.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FFDAEAF6");
            }
        });
        this.mcv6.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FFC8B2EB");
            }
        });
        this.mcv7.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FFFBADAB");
            }
        });
        this.mcv8.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FFFADF7E");
            }
        });
        this.mcv9.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FFBBD9C1");
            }
        });
        this.mcv10.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FF80B7FF");
            }
        });
        this.mcv11.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FF9866E8");
            }
        });
        this.mcv12.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FFF9635F");
            }
        });
        this.mcv13.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FFFAD241");
            }
        });
        this.mcv14.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FF5CD574");
            }
        });
        this.mcv15.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistSettingActivity.this._setMcvColor("#FF4294FF");
            }
        });
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
        _setFirstUI();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        finishAndRemoveTask();
    }

    public void _setFirstUI() {
        this.im_01.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        this.im_02.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        this.im_03.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        this.im_04.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        this.im_05.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        this.im_06.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        this.im_07.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        this.im_08.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        this.im_09.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        this.im_10.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        this.im_11.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        this.im_12.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        this.im_13.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        this.im_14.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        this.im_15.setImageResource(R.drawable.admsoloraya_res_0x7f080130);
        _setImageVisibility();
        this.materialcardview1.setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(this));
        this.materialcardview2.setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(this));
        this.materialcardview3.setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(this));
        this.mcv1.setCardBackgroundColor(Color.parseColor("#FFE8DFF5"));
        this.mcv2.setCardBackgroundColor(Color.parseColor("#FFFCE1E4"));
        this.mcv3.setCardBackgroundColor(Color.parseColor("#FFFCF4DD"));
        this.mcv4.setCardBackgroundColor(Color.parseColor("#FFDDEDEA"));
        this.mcv5.setCardBackgroundColor(Color.parseColor("#FFDAEAF6"));
        this.mcv6.setCardBackgroundColor(Color.parseColor("#FFC8B2EB"));
        this.mcv7.setCardBackgroundColor(Color.parseColor("#FFFBADAB"));
        this.mcv8.setCardBackgroundColor(Color.parseColor("#FFFADF7E"));
        this.mcv9.setCardBackgroundColor(Color.parseColor("#FFBBD9C1"));
        this.mcv10.setCardBackgroundColor(Color.parseColor("#FF80B7FF"));
        this.mcv11.setCardBackgroundColor(Color.parseColor("#FF9866E8"));
        this.mcv12.setCardBackgroundColor(Color.parseColor("#FFF9635F"));
        this.mcv13.setCardBackgroundColor(Color.parseColor("#FFFAD241"));
        this.mcv14.setCardBackgroundColor(Color.parseColor("#FF5CD574"));
        this.mcv15.setCardBackgroundColor(Color.parseColor("#FF4294FF"));
        if (getIntent().hasExtra("STATUS_EXTRA")) {
            this.ln_intent.setVisibility(0);
            this.ln_setting.setVisibility(8);
            this.btn_oke.setVisibility(8);
            String stringExtra = getIntent().getStringExtra("STATUS_EXTRA");
            this.s_key = stringExtra;
            if (stringExtra.equals("true")) {
                _onStringFound();
                return;
            } else if (this.s_key.equals("not_match")) {
                _onStringNotMatch();
                return;
            } else {
                _onStringNotFound();
                return;
            }
        }
        String string = this.pref_assist.getString("assist_key", "");
        this.s_key = string;
        this.et_match.setText(string);
        String string2 = this.pref_assist.getString("toolbar", "");
        this.s_key = string2;
        this.et_toolbar.setText(string2);
        String string3 = this.pref_assist.getString("update_note", "");
        this.s_key = string3;
        this.et_note.setText(string3);
        this.ln_intent.setVisibility(8);
        this.ln_setting.setVisibility(0);
    }

    public void _settingFindMatch(String str, String str2) {
        this.pref_assist.edit().putString(str, str2).commit();
        SketchwareUtil.showMessage(getApplicationContext(), "Berhasil disimpan");
    }

    public void _onStringFound() {
        String string = this.prefui.getString("backup_app_package", "");
        this.s_package = string;
        String string2 = this.preflast.getString(string, "");
        this.s_json_backup = string2;
        String replace = string2.replace(".tar.gz", ".json");
        this.s_json_backup = replace;
        if (FileUtil.isExistFile(replace)) {
            this.s_json_path = FileUtil.readFile(this.s_json_backup);
            HashMap<String, Object> hashMap = (HashMap) new Gson().fromJson(this.s_json_path, new TypeToken<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.AssistSettingActivity.20
            }.getType());
            this.m_json = hashMap;
            this.s_new_note = hashMap.get("NOTE").toString();
            String concat = this.pref_assist.getString("update_note", "").concat("\n".concat(this.s_new_note));
            this.s_new_note = concat;
            this.m_json.put("NOTE", concat);
            this.m_json.put("MARK", "true");
            this.m_json.put("COLOR", this.pref_assist.getString(TypedValues.Custom.S_COLOR, ""));
            this.s_new_note = new Gson().toJson(this.m_json);
            String json = new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement) ((JsonObject) new Gson().fromJson(this.s_new_note, (Class<Object>) JsonObject.class)));
            this.s_new_note = json;
            FileUtil.writeFile(this.s_json_backup, json);
            this.s_result = "Catatan Backup berhasil diganti";
        } else {
            this.s_result = "Tidak dilakukan perubahan apapun";
        }
        _onResult(this.s_result);
    }

    public void _onStringNotFound() {
        this.s_result = "Tidak ditemukan, Tidak dilakukan perubahan apapun";
        _onResult("Tidak ditemukan, Tidak dilakukan perubahan apapun");
    }

    public void _onStringNotMatch() {
        this.s_result = "Halaman tidak cocok, Tidak dilakukan perubahan apapun. Refufu dihentikan.";
        this.s_command = "am force-stop com.google.android.apps.googleassistantx";
        this.b_command = false;
        Shell.Result exec = Shell.cmd("am force-stop com.google.android.apps.googleassistantx").exec();
        List<String> out = exec.getOut();
        exec.getCode();
        this.b_command = exec.isSuccess();
        this.s_command = AssistSettingActivity$$ExternalSyntheticBackport0.m("\n", out);
        _onResult(this.s_result);
    }

    public void _onResult(String str) {
        this.tv_result.setText(str);
        this.btn_oke.setVisibility(0);
    }

    public void _setMcvColor(String str) {
        this.pref_assist.edit().putString(TypedValues.Custom.S_COLOR, str).commit();
        _setImageVisibility();
    }

    public void _setImageVisibility() {
        this.im_01.setVisibility(4);
        this.im_02.setVisibility(4);
        this.im_03.setVisibility(4);
        this.im_04.setVisibility(4);
        this.im_05.setVisibility(4);
        this.im_06.setVisibility(4);
        this.im_07.setVisibility(4);
        this.im_08.setVisibility(4);
        this.im_09.setVisibility(4);
        this.im_10.setVisibility(4);
        this.im_11.setVisibility(4);
        this.im_12.setVisibility(4);
        this.im_13.setVisibility(4);
        this.im_14.setVisibility(4);
        this.im_15.setVisibility(4);
        if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FFE8DFF5")) {
            this.im_01.setVisibility(0);
        } else if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FFFCE1E4")) {
            this.im_02.setVisibility(0);
        } else if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FFFCF4DD")) {
            this.im_03.setVisibility(0);
        } else if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FFDDEDEA")) {
            this.im_04.setVisibility(0);
        } else if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FFDAEAF6")) {
            this.im_05.setVisibility(0);
        } else if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FFC8B2EB")) {
            this.im_06.setVisibility(0);
        } else if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FFFBADAB")) {
            this.im_07.setVisibility(0);
        } else if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FFFADF7E")) {
            this.im_08.setVisibility(0);
        } else if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FFBBD9C1")) {
            this.im_09.setVisibility(0);
        } else if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FF80B7FF")) {
            this.im_10.setVisibility(0);
        } else if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FF9866E8")) {
            this.im_11.setVisibility(0);
        } else if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FFF9635F")) {
            this.im_12.setVisibility(0);
        } else if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FFFAD241")) {
            this.im_13.setVisibility(0);
        } else if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FF5CD574")) {
            this.im_14.setVisibility(0);
        } else if (this.pref_assist.getString(TypedValues.Custom.S_COLOR, "").equals("#FF4294FF")) {
            this.im_15.setVisibility(0);
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
