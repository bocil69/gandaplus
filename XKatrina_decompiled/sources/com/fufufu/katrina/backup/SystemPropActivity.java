package com.fufufu.katrina.backup;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
/* loaded from: classes5.dex */
public class SystemPropActivity extends AppCompatActivity {
    private AlertDialog EDITOR;
    private Button btn_apply;
    private Button btn_exit;
    private Button btn_hard_random;
    private Button btn_random;
    private Button btn_reboot;
    private LinearLayout ln_base;
    private LinearLayout ln_bottom;
    private LinearLayout ln_reboot;
    private LinearLayout ln_title_top;
    private ListView lv_1;
    private MyAPPLYPROP myAPPLYPROP;
    private MyCONVERT myCONVERT;
    private ProgressBar pbar_title;
    private SharedPreferences pref;
    private RecyclerView rv_1;
    private TextView tv_prop_title;
    private TextView tv_title;
    private ScrollView vscr_base;
    private String s_commandResult = "";
    private boolean b_command = false;
    private String s_command = "";
    private String s_fileprop_list = "";
    private double n = 0.0d;
    private double n2 = 0.0d;
    private HashMap<String, Object> m_loop = new HashMap<>();
    private String s_editor = "";
    private String s_key_prop = "";
    private double n3 = 0.0d;
    private String s_listprop_json = "";
    private String s_random_desc = "";
    private String s_random_fing = "";
    private String s_new_prop = "";
    private String s_random_release = "";
    private double editor_pos = 0.0d;
    private String s_title_editor = "";
    private String s_dialog_editor = "";
    private String s_result_editor = "";
    private String s_card_random = "";
    private String s_commandBase = "";
    private String s_getrandom = "";
    private String s_getbrand = "";
    private String s_getdevice = "";
    private String s_getmodel = "";
    private String s_getproduct = "";
    private ArrayList<String> ls_file = new ArrayList<>();
    private ArrayList<String> ls_loop = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_loop = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_editor = new ArrayList<>();
    private ArrayList<String> ls_replace = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_replace = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_getrandom = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_getbrand = new ArrayList<>();

    public void _EXTRA() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d00c2);
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
        this.ln_base = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0270);
        this.ln_reboot = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02d1);
        this.vscr_base = (ScrollView) findViewById(R.id.admsoloraya_res_0x7f0a05bf);
        this.rv_1 = (RecyclerView) findViewById(R.id.admsoloraya_res_0x7f0a040c);
        this.lv_1 = (ListView) findViewById(R.id.admsoloraya_res_0x7f0a030b);
        this.ln_bottom = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0277);
        this.ln_title_top = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02ec);
        this.tv_title = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a0571);
        this.tv_prop_title = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a053b);
        this.pbar_title = (ProgressBar) findViewById(R.id.admsoloraya_res_0x7f0a03f3);
        this.btn_hard_random = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00be);
        this.btn_random = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00d1);
        this.btn_apply = (Button) findViewById(R.id.admsoloraya_res_0x7f0a0082);
        this.btn_exit = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00a5);
        this.btn_reboot = (Button) findViewById(R.id.admsoloraya_res_0x7f0a00e6);
        this.pref = getSharedPreferences("bprop_preferences", 0);
        this.btn_hard_random.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SystemPropActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SystemPropActivity.this._randomAllPropPref();
            }
        });
        this.btn_random.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SystemPropActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SystemPropActivity.this._randomFromProp();
            }
        });
        this.btn_apply.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SystemPropActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SystemPropActivity.this._onApplyProp();
            }
        });
        this.btn_exit.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SystemPropActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SystemPropActivity.this.finish();
            }
        });
        this.btn_reboot.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SystemPropActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SystemPropActivity systemPropActivity = SystemPropActivity.this;
                systemPropActivity.s_command = systemPropActivity.s_commandBase.concat("\nooreboot");
                SystemPropActivity.this.b_command = false;
                Shell.Result exec = Shell.cmd(SystemPropActivity.this.s_command).exec();
                List<String> out = exec.getOut();
                exec.getCode();
                SystemPropActivity.this.b_command = exec.isSuccess();
                SystemPropActivity.this.s_commandResult = SystemPropActivity$5$$ExternalSyntheticBackport0.m("\n", out);
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

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.ln_reboot.getVisibility() == 0) {
            _setFirstUI();
        } else if (this.rv_1.getVisibility() == 8) {
            this.lv_1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.admsoloraya_res_0x7f010024));
            this.lv_1.setVisibility(8);
            Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.admsoloraya_res_0x7f010023);
            this.rv_1.startAnimation(loadAnimation);
            this.ln_bottom.startAnimation(loadAnimation);
            this.rv_1.setVisibility(0);
            this.ln_bottom.setVisibility(0);
        } else {
            finish();
        }
    }

    public void _openFileProp(String str) {
        this.lm_editor.clear();
        this.lm_editor = (ArrayList) new Gson().fromJson(FileUtil.readFile(str), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.SystemPropActivity.6
        }.getType());
        this.lv_1.setAdapter((ListAdapter) new Lv_1Adapter(this.lm_editor));
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.admsoloraya_res_0x7f010024);
        this.rv_1.startAnimation(loadAnimation);
        this.ln_bottom.startAnimation(loadAnimation);
        this.rv_1.setVisibility(8);
        this.ln_bottom.setVisibility(8);
        this.lv_1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.admsoloraya_res_0x7f010023));
        this.lv_1.setVisibility(0);
    }

    public void _onCardFavButton(String str) {
        if (this.pref.getString(str, "").equals("true")) {
            this.pref.edit().putString(str, "false").commit();
        } else {
            this.pref.edit().putString(str, "true").commit();
        }
        this.lv_1.invalidateViews();
    }

    public void _replaceAllProp(String str, String str2) {
        this.ls_replace.clear();
        FileUtil.listDir(this.s_fileprop_list, this.ls_replace);
        Iterator<String> it = this.ls_replace.iterator();
        while (it.hasNext()) {
            if (!it.next().endsWith(".json")) {
                it.remove();
            }
        }
        this.n3 = 0.0d;
        for (int i = 0; i < this.ls_replace.size(); i++) {
            this.lm_replace.clear();
            this.s_listprop_json = FileUtil.readFile(this.ls_replace.get((int) this.n3));
            this.lm_replace = (ArrayList) new Gson().fromJson(this.s_listprop_json, new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.SystemPropActivity.7
            }.getType());
            String replace = str.replace("=.*", "=");
            Iterator<HashMap<String, Object>> it2 = this.lm_replace.iterator();
            while (it2.hasNext()) {
                HashMap<String, Object> next = it2.next();
                for (Map.Entry<String, Object> entry : next.entrySet()) {
                    if (entry.getKey().equals("prop") && (entry.getValue() instanceof String)) {
                        String str3 = (String) entry.getValue();
                        if (str3.contains(replace)) {
                            next.put("prop", str3.replaceAll(str, str2));
                        }
                    }
                }
            }
            FileUtil.writeFile(this.ls_replace.get((int) this.n3), new Gson().toJson(this.lm_replace));
            this.n3 += 1.0d;
        }
        if (this.lv_1.getVisibility() == 8) {
            this.rv_1.setVisibility(0);
            this.ln_bottom.setVisibility(0);
        }
        AlertDialog alertDialog = this.EDITOR;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.EDITOR.dismiss();
    }

    public void _randomAllPropPref() {
        this.tv_prop_title.setText("Mengacak...");
        this.rv_1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.admsoloraya_res_0x7f010024));
        this.rv_1.setVisibility(8);
        this.ln_bottom.setVisibility(8);
        this.s_random_desc = "fufufuwww-user fufufuxxx fufufuyyy.fufufuzzz release-keys";
        this.s_random_fing = "fufufu1/fufufu2/fufufu3:fufufu4/fufufu5/fufufu6:user/release-keys";
        ArrayList arrayList = new ArrayList();
        arrayList.add("8");
        arrayList.add("9");
        arrayList.add("10");
        arrayList.add("11");
        arrayList.add("12");
        arrayList.add("12.1");
        arrayList.add("13");
        arrayList.add("14");
        Collections.shuffle(arrayList);
        this.s_random_release = (String) arrayList.get(new Random().nextInt(arrayList.size()));
        if (this.pref.getString("incremental", "").equals("true")) {
            this.s_key_prop = "incremental=.*";
            String concat = "incremental=".concat(generateRandomString());
            this.s_new_prop = concat;
            _replaceAllProp(this.s_key_prop, concat);
        }
        if (this.pref.getString("user", "").equals("true")) {
            this.s_key_prop = "user=.*";
            String concat2 = "user=".concat(generateRandomString());
            this.s_new_prop = concat2;
            _replaceAllProp(this.s_key_prop, concat2);
        }
        if (this.pref.getString("product", "").equals("true")) {
            this.s_key_prop = "product=.*";
            String concat3 = "product=".concat(generateRandomString());
            this.s_new_prop = concat3;
            _replaceAllProp(this.s_key_prop, concat3);
        }
        if (this.pref.getString("host", "").equals("true")) {
            this.s_key_prop = "host=.*";
            String concat4 = "host=".concat(generateRandomString());
            this.s_new_prop = concat4;
            _replaceAllProp(this.s_key_prop, concat4);
        }
        if (this.pref.getString("bootloader", "").equals("true")) {
            this.s_key_prop = "bootloader=.*";
            String concat5 = "bootloader=".concat(generateRandomString());
            this.s_new_prop = concat5;
            _replaceAllProp(this.s_key_prop, concat5);
        }
        if (this.pref.getString("model", "").equals("true")) {
            this.s_key_prop = "model=.*";
            String concat6 = "model=".concat(generateRandomString());
            this.s_new_prop = concat6;
            _replaceAllProp(this.s_key_prop, concat6);
        }
        if (this.pref.getString("hardware", "").equals("true")) {
            this.s_key_prop = "hardware=.*";
            String concat7 = "hardware=".concat(generateRandomString());
            this.s_new_prop = concat7;
            _replaceAllProp(this.s_key_prop, concat7);
        }
        if (this.pref.getString("board", "").equals("true")) {
            this.s_key_prop = "board=.*";
            String concat8 = "board=".concat(generateRandomString());
            this.s_new_prop = concat8;
            _replaceAllProp(this.s_key_prop, concat8);
        }
        if (this.pref.getString("device", "").equals("true")) {
            this.s_key_prop = "device=.*";
            String concat9 = "device=".concat(generateRandomString());
            this.s_new_prop = concat9;
            _replaceAllProp(this.s_key_prop, concat9);
        }
        if (this.pref.getString("name", "").equals("true")) {
            this.s_key_prop = "name=.*";
            String concat10 = "name=".concat(generateRandomString());
            this.s_new_prop = concat10;
            _replaceAllProp(this.s_key_prop, concat10);
        }
        if (this.pref.getString("manufacturer", "").equals("true")) {
            this.s_key_prop = "manufacturer=.*";
            String concat11 = "manufacturer=".concat(generateRandomString());
            this.s_new_prop = concat11;
            _replaceAllProp(this.s_key_prop, concat11);
        }
        if (this.pref.getString("brand", "").equals("true")) {
            this.s_key_prop = "brand=.*";
            String concat12 = "brand=".concat(generateRandomString());
            this.s_new_prop = concat12;
            _replaceAllProp(this.s_key_prop, concat12);
        }
        if (this.pref.getString("utc", "").equals("true")) {
            this.s_key_prop = "utc=.*";
            String concat13 = "utc=".concat(String.valueOf(SketchwareUtil.getRandom(1558918000, 1658918000)));
            this.s_new_prop = concat13;
            _replaceAllProp(this.s_key_prop, concat13);
        }
        if (this.pref.getString("release", "").equals("true")) {
            this.s_key_prop = "release=.*";
            String concat14 = "release=".concat(this.s_random_release);
            this.s_new_prop = concat14;
            _replaceAllProp(this.s_key_prop, concat14);
        }
        if (this.pref.getString("build.id", "").equals("true")) {
            this.s_key_prop = "build.id=.*";
            String concat15 = "build.id=".concat(generateRandomString());
            this.s_new_prop = concat15;
            _replaceAllProp(this.s_key_prop, concat15);
        }
        if (this.pref.getString("display.id", "").equals("true")) {
            this.s_key_prop = "display.id=.*";
            String concat16 = "display.id=".concat(generateRandomString().concat(".".concat(generateRandomString())));
            this.s_new_prop = concat16;
            _replaceAllProp(this.s_key_prop, concat16);
        }
        if (this.pref.getString("date", "").equals("true")) {
            this.s_key_prop = "date=.*";
            String concat17 = "date=".concat(generateRandomDate().replace("WIB", "UTC"));
            this.s_new_prop = concat17;
            _replaceAllProp(this.s_key_prop, concat17);
        }
        if (this.pref.getString("fingerprint", "").equals("true")) {
            String replace = this.s_random_fing.replace("fufufu1", generateRandomString());
            this.s_random_fing = replace;
            String replace2 = replace.replace("fufufu2", generateRandomString());
            this.s_random_fing = replace2;
            String replace3 = replace2.replace("fufufu3", generateRandomString());
            this.s_random_fing = replace3;
            String replace4 = replace3.replace("fufufu4", this.s_random_release);
            this.s_random_fing = replace4;
            String replace5 = replace4.replace("fufufu5", generateRandomString());
            this.s_random_fing = replace5;
            String replace6 = replace5.replace("fufufu6", generateRandomString());
            this.s_random_fing = replace6;
            this.s_key_prop = "fingerprint=.*";
            String concat18 = "fingerprint=".concat(replace6);
            this.s_new_prop = concat18;
            _replaceAllProp(this.s_key_prop, concat18);
        }
        if (this.pref.getString("description", "").equals("true")) {
            String replace7 = this.s_random_desc.replace("fufufuwww", generateRandomString());
            this.s_random_desc = replace7;
            String replace8 = replace7.replace("fufufuxxx", this.s_random_release);
            this.s_random_desc = replace8;
            String replace9 = replace8.replace("fufufuyyy", generateRandomString());
            this.s_random_desc = replace9;
            String replace10 = replace9.replace("fufufuzzz", generateRandomString());
            this.s_random_desc = replace10;
            this.s_key_prop = "description=.*";
            String concat19 = "description=".concat(replace10);
            this.s_new_prop = concat19;
            _replaceAllProp(this.s_key_prop, concat19);
        }
        this.tv_prop_title.setText("Mengedit 1 file prop akan otomatis mengubah nilai dari file prop lainnya");
        this.rv_1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.admsoloraya_res_0x7f010023));
        this.rv_1.setVisibility(0);
        this.ln_bottom.setVisibility(0);
        SketchwareUtil.showMessage(getApplicationContext(), "Berhasil diacak (Hard Random)");
    }

    public void _onCardEditButton(double d, ArrayList<HashMap<String, Object>> arrayList) {
        this.editor_pos = d;
        String obj = arrayList.get((int) d).get("prop").toString();
        this.s_editor = obj;
        this.s_title_editor = obj.replaceAll("=.*", "");
        this.s_dialog_editor = this.s_editor.replaceAll(".*=", "");
        if (this.s_editor.contains("build.id=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*build.id=", "build.id=");
        } else if (this.s_editor.contains("display.id=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*display.id=", "display.id=");
        } else if (this.s_editor.contains("date=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*date=", "date=");
        } else if (this.s_editor.contains("fingerprint=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*fingerprint=", "fingerprint=");
        } else if (this.s_editor.contains("incremental=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*incremental=", "incremental=");
        } else if (this.s_editor.contains("user=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*user=", "user=");
        } else if (this.s_editor.contains("product=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*product=", "product=");
        } else if (this.s_editor.contains("host=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*host=", "host=");
        } else if (this.s_editor.contains("description=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*description=", "description=");
        } else if (this.s_editor.contains("bootloader=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*bootloader=", "bootloader=");
        } else if (this.s_editor.contains("model=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*model=", "model=");
        } else if (this.s_editor.contains("hardware=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*hardware=", "hardware=");
        } else if (this.s_editor.contains("board=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*board=", "board=");
        } else if (this.s_editor.contains("device=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*device=", "device=");
        } else if (this.s_editor.contains("name=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*name=", "name=");
        } else if (this.s_editor.contains("manufacturer=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*manufacturer=", "manufacturer=");
        } else if (this.s_editor.contains("brand=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*brand=", "brand=");
        } else if (this.s_editor.contains("utc=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*utc=", "utc=");
        } else if (this.s_editor.contains("release=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*release=", "release=");
        } else {
            this.s_key_prop = "nothingfind";
        }
        showEDITOR();
    }

    private void showEDITOR() {
        View inflate = getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d005c, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(this);
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(true);
        final TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04bd);
        Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a005c);
        final TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a049a);
        final ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.admsoloraya_res_0x7f0a03eb);
        autoCompleteTextView.setFocusable(true);
        autoCompleteTextView.setFocusableInTouchMode(true);
        textInputLayout.setVisibility(0);
        progressBar.setVisibility(8);
        textView.setText(this.s_title_editor);
        autoCompleteTextView.setHint(this.s_dialog_editor);
        autoCompleteTextView.setText(this.s_dialog_editor);
        autoCompleteTextView.setSingleLine(true);
        ((Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0086)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SystemPropActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SystemPropActivity.this.EDITOR.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SystemPropActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                textInputLayout.setVisibility(8);
                progressBar.setVisibility(0);
                textView.setText("Cek all system.prop");
                SystemPropActivity systemPropActivity = SystemPropActivity.this;
                systemPropActivity.s_result_editor = systemPropActivity.s_title_editor.concat("=".concat(autoCompleteTextView.getText().toString()));
                ((HashMap) SystemPropActivity.this.lm_editor.get((int) SystemPropActivity.this.editor_pos)).put("prop", SystemPropActivity.this.s_result_editor);
                SystemPropActivity.this.lv_1.invalidateViews();
                SystemPropActivity systemPropActivity2 = SystemPropActivity.this;
                systemPropActivity2.s_new_prop = systemPropActivity2.s_key_prop.replaceAll("=.*", "=".concat(autoCompleteTextView.getText().toString()));
                SystemPropActivity systemPropActivity3 = SystemPropActivity.this;
                systemPropActivity3._replaceAllProp(systemPropActivity3.s_key_prop, SystemPropActivity.this.s_new_prop);
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.EDITOR = create;
        create.show();
    }

    public void _onCardRandomButton(double d, ArrayList<HashMap<String, Object>> arrayList) {
        this.s_random_desc = "fufufuwww-user fufufuxxx fufufuyyy.fufufuzzz release-keys";
        this.s_random_fing = "fufufu1/fufufu2/fufufu3:fufufu4/fufufu5/fufufu6:user/release-keys";
        int i = (int) d;
        this.s_card_random = arrayList.get(i).get("prop").toString().replaceAll("=.*", "=");
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("8");
        arrayList2.add("9");
        arrayList2.add("10");
        arrayList2.add("11");
        arrayList2.add("12");
        arrayList2.add("12.1");
        arrayList2.add("13");
        arrayList2.add("14");
        Collections.shuffle(arrayList2);
        this.s_random_release = (String) arrayList2.get(new Random().nextInt(arrayList2.size()));
        String obj = arrayList.get(i).get("prop").toString();
        this.s_editor = obj;
        if (obj.contains("build.id=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*build.id=", "build.id=");
        } else if (this.s_editor.contains("display.id=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*display.id=", "display.id=");
        } else if (this.s_editor.contains("date=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*date=", "date=");
        } else if (this.s_editor.contains("fingerprint=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*fingerprint=", "fingerprint=");
        } else if (this.s_editor.contains("incremental=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*incremental=", "incremental=");
        } else if (this.s_editor.contains("user=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*user=", "user=");
        } else if (this.s_editor.contains("product=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*product=", "product=");
        } else if (this.s_editor.contains("host=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*host=", "host=");
        } else if (this.s_editor.contains("description=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*description=", "description=");
        } else if (this.s_editor.contains("bootloader=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*bootloader=", "bootloader=");
        } else if (this.s_editor.contains("model=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*model=", "model=");
        } else if (this.s_editor.contains("hardware=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*hardware=", "hardware=");
        } else if (this.s_editor.contains("board=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*board=", "board=");
        } else if (this.s_editor.contains("device=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*device=", "device=");
        } else if (this.s_editor.contains("name=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*name=", "name=");
        } else if (this.s_editor.contains("manufacturer=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*manufacturer=", "manufacturer=");
        } else if (this.s_editor.contains("brand=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*brand=", "brand=");
        } else if (this.s_editor.contains("utc=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*utc=", "utc=");
        } else if (this.s_editor.contains("release=")) {
            this.s_key_prop = this.s_editor.replaceAll(".*release=", "release=");
        } else {
            this.s_key_prop = "nothingfind";
        }
        if (arrayList.get(i).get("prop").toString().contains("release=")) {
            String str = this.s_random_release;
            arrayList.get(i).put("prop", this.s_card_random.concat(str));
            this.s_new_prop = this.s_key_prop.replaceAll("=.*", "=".concat(str));
        } else if (arrayList.get(i).get("prop").toString().contains("fingerprint=")) {
            String replace = this.s_random_fing.replace("fufufu1", generateRandomString());
            this.s_random_fing = replace;
            String replace2 = replace.replace("fufufu2", generateRandomString());
            this.s_random_fing = replace2;
            String replace3 = replace2.replace("fufufu3", generateRandomString());
            this.s_random_fing = replace3;
            String replace4 = replace3.replace("fufufu4", this.s_random_release);
            this.s_random_fing = replace4;
            String replace5 = replace4.replace("fufufu5", generateRandomString());
            this.s_random_fing = replace5;
            String replace6 = replace5.replace("fufufu6", generateRandomString());
            this.s_random_fing = replace6;
            arrayList.get(i).put("prop", this.s_card_random.concat(replace6));
            this.s_new_prop = this.s_key_prop.replaceAll("=.*", "=".concat(replace6));
        } else if (arrayList.get(i).get("prop").toString().contains("description=")) {
            String replace7 = this.s_random_desc.replace("fufufuwww", generateRandomString());
            this.s_random_desc = replace7;
            String replace8 = replace7.replace("fufufuxxx", this.s_random_release);
            this.s_random_desc = replace8;
            String replace9 = replace8.replace("fufufuyyy", generateRandomString());
            this.s_random_desc = replace9;
            String replace10 = replace9.replace("fufufuzzz", generateRandomString());
            this.s_random_desc = replace10;
            arrayList.get(i).put("prop", this.s_card_random.concat(replace10));
            this.s_new_prop = this.s_key_prop.replaceAll("=.*", "=".concat(replace10));
        } else if (arrayList.get(i).get("prop").toString().contains("display.id=")) {
            String concat = generateRandomString().concat(".".concat(generateRandomString()));
            arrayList.get(i).put("prop", this.s_card_random.concat(concat));
            this.s_new_prop = this.s_key_prop.replaceAll("=.*", "=".concat(concat));
        } else if (arrayList.get(i).get("prop").toString().contains("date=")) {
            String replace11 = generateRandomDate().replace("WIB", "UTC");
            arrayList.get(i).put("prop", this.s_card_random.concat(replace11));
            this.s_new_prop = this.s_key_prop.replaceAll("=.*", "=".concat(replace11));
        } else if (arrayList.get(i).get("prop").toString().contains("utc=")) {
            String valueOf = String.valueOf(SketchwareUtil.getRandom(1558918000, 1658918000));
            arrayList.get(i).put("prop", this.s_card_random.concat(valueOf));
            this.s_new_prop = this.s_key_prop.replaceAll("=.*", "=".concat(valueOf));
        } else {
            String generateRandomString = generateRandomString();
            arrayList.get(i).put("prop", this.s_card_random.concat(generateRandomString));
            this.s_new_prop = this.s_key_prop.replaceAll("=.*", "=".concat(generateRandomString));
        }
        _replaceAllProp(this.s_key_prop, this.s_new_prop);
        this.lv_1.invalidateViews();
    }

    public static String generateRandomString() {
        int randomNumberInRange = getRandomNumberInRange(4, 9);
        StringBuilder sb = new StringBuilder(randomNumberInRange);
        Random random = new Random();
        for (int i = 0; i < randomNumberInRange; i++) {
            sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    public static int getRandomNumberInRange(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    private static long getRandomUnixTime(long j, long j2) {
        return j + ((long) (Math.random() * (j2 - j)));
    }

    private static String generateRandomDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        calendar.setTimeZone(TimeZone.getTimeZone("KST"));
        calendar.set(getRandomNumberInRange(2000, 2023), getRandomNumberInRange(0, 11), getRandomNumberInRange(1, 28), getRandomNumberInRange(0, 23), getRandomNumberInRange(0, 59), getRandomNumberInRange(0, 59));
        return simpleDateFormat.format(calendar.getTime());
    }

    public void _onApplyProp() {
        MyAPPLYPROP myAPPLYPROP = this.myAPPLYPROP;
        if (myAPPLYPROP != null && myAPPLYPROP.isRunning) {
            this.myAPPLYPROP.cancelAPPLYPROPTask();
        }
        MyAPPLYPROP myAPPLYPROP2 = new MyAPPLYPROP();
        this.myAPPLYPROP = myAPPLYPROP2;
        myAPPLYPROP2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyAPPLYPROP extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyAPPLYPROP() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            SystemPropActivity.this.tv_prop_title.setText("Menerapkan pergantian prop");
            SystemPropActivity.this.pbar_title.setVisibility(0);
            SystemPropActivity.this.rv_1.setVisibility(8);
            SystemPropActivity.this.lv_1.setVisibility(8);
            SystemPropActivity.this.ln_bottom.setVisibility(8);
            SystemPropActivity.this.ls_replace.clear();
            FileUtil.listDir(SystemPropActivity.this.s_fileprop_list, SystemPropActivity.this.ls_replace);
            SystemPropActivity systemPropActivity = SystemPropActivity.this;
            systemPropActivity.s_command = systemPropActivity.s_commandBase.concat("\neditsystemprop");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            Iterator it = SystemPropActivity.this.ls_replace.iterator();
            while (it.hasNext()) {
                if (!((String) it.next()).endsWith(".json")) {
                    it.remove();
                }
            }
            SystemPropActivity.this.n3 = 0.0d;
            for (int i = 0; i < SystemPropActivity.this.ls_replace.size(); i++) {
                SystemPropActivity.this.lm_replace.clear();
                String replace = ((String) SystemPropActivity.this.ls_replace.get((int) SystemPropActivity.this.n3)).replace("prop.json", "prop");
                SystemPropActivity systemPropActivity = SystemPropActivity.this;
                systemPropActivity.s_listprop_json = FileUtil.readFile((String) systemPropActivity.ls_replace.get((int) SystemPropActivity.this.n3));
                SystemPropActivity.this.lm_replace = (ArrayList) new Gson().fromJson(SystemPropActivity.this.s_listprop_json, new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.SystemPropActivity.MyAPPLYPROP.1
                }.getType());
                StringBuilder sb = new StringBuilder();
                Iterator it2 = SystemPropActivity.this.lm_replace.iterator();
                while (it2.hasNext()) {
                    Object obj = ((HashMap) it2.next()).get("prop");
                    if (obj != null) {
                        sb.append(obj);
                        sb.append("\n");
                    }
                }
                FileUtil.writeFile(replace, sb.toString());
                SystemPropActivity.this.n3 += 1.0d;
            }
            SystemPropActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(SystemPropActivity.this.s_command).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            SystemPropActivity.this.b_command = exec.isSuccess();
            SystemPropActivity.this.s_commandResult = SystemPropActivity$MyAPPLYPROP$$ExternalSyntheticBackport0.m("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r3) {
            this.isRunning = false;
            SystemPropActivity.this.tv_prop_title.setTextSize(10.0f);
            SystemPropActivity.this.tv_prop_title.setGravity(51);
            SystemPropActivity.this.tv_prop_title.setText(SystemPropActivity.this.s_commandResult);
            SystemPropActivity.this.ln_reboot.setVisibility(0);
            SystemPropActivity.this.pbar_title.setVisibility(8);
        }

        public void cancelAPPLYPROPTask() {
            cancel(true);
        }
    }

    public void _setFirstUI() {
        this.lv_1.setVerticalScrollBarEnabled(false);
        this.rv_1.setLayoutManager(new GridLayoutManager(this, 2));
        MyCONVERT myCONVERT = this.myCONVERT;
        if (myCONVERT != null && myCONVERT.isRunning) {
            this.myCONVERT.cancelCONVERTTask();
        }
        MyCONVERT myCONVERT2 = new MyCONVERT();
        this.myCONVERT = myCONVERT2;
        myCONVERT2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyCONVERT extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyCONVERT() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            SystemPropActivity.this.pbar_title.setVisibility(0);
            SystemPropActivity systemPropActivity = SystemPropActivity.this;
            systemPropActivity.s_fileprop_list = "/data/user/0/".concat(systemPropActivity.getApplicationContext().getPackageName().concat("/systemprop"));
            SystemPropActivity.this.rv_1.setVisibility(8);
            SystemPropActivity.this.lv_1.setVisibility(8);
            SystemPropActivity.this.ln_bottom.setVisibility(8);
            SystemPropActivity.this.ln_reboot.setVisibility(8);
            if (FileUtil.isExistFile(SystemPropActivity.this.s_fileprop_list)) {
                FileUtil.deleteFile(SystemPropActivity.this.s_fileprop_list);
            }
            FileUtil.makeDir(SystemPropActivity.this.s_fileprop_list);
            try {
                InputStream open = SystemPropActivity.this.getAssets().open("prop.sh");
                SystemPropActivity.this.s_commandBase = SketchwareUtil.copyFromInputStream(open);
                SystemPropActivity systemPropActivity2 = SystemPropActivity.this;
                systemPropActivity2.s_commandBase = systemPropActivity2.s_commandBase.replace("Ĩ", "a");
                SystemPropActivity systemPropActivity3 = SystemPropActivity.this;
                systemPropActivity3.s_commandBase = systemPropActivity3.s_commandBase.replace("ĩ", "b");
                SystemPropActivity systemPropActivity4 = SystemPropActivity.this;
                systemPropActivity4.s_commandBase = systemPropActivity4.s_commandBase.replace("Ī", "c");
                SystemPropActivity systemPropActivity5 = SystemPropActivity.this;
                systemPropActivity5.s_commandBase = systemPropActivity5.s_commandBase.replace("ī", "d");
                SystemPropActivity systemPropActivity6 = SystemPropActivity.this;
                systemPropActivity6.s_commandBase = systemPropActivity6.s_commandBase.replace("Ĭ", "e");
                SystemPropActivity systemPropActivity7 = SystemPropActivity.this;
                systemPropActivity7.s_commandBase = systemPropActivity7.s_commandBase.replace("ĭ", "f");
                SystemPropActivity systemPropActivity8 = SystemPropActivity.this;
                systemPropActivity8.s_commandBase = systemPropActivity8.s_commandBase.replace("Į", "g");
                SystemPropActivity systemPropActivity9 = SystemPropActivity.this;
                systemPropActivity9.s_commandBase = systemPropActivity9.s_commandBase.replace("į", "h");
                SystemPropActivity systemPropActivity10 = SystemPropActivity.this;
                systemPropActivity10.s_commandBase = systemPropActivity10.s_commandBase.replace("ĺ", "i");
                SystemPropActivity systemPropActivity11 = SystemPropActivity.this;
                systemPropActivity11.s_commandBase = systemPropActivity11.s_commandBase.replace("ļ", "j");
                SystemPropActivity systemPropActivity12 = SystemPropActivity.this;
                systemPropActivity12.s_commandBase = systemPropActivity12.s_commandBase.replace("ľ", "k");
                SystemPropActivity systemPropActivity13 = SystemPropActivity.this;
                systemPropActivity13.s_commandBase = systemPropActivity13.s_commandBase.replace("ŀ", "l");
                SystemPropActivity systemPropActivity14 = SystemPropActivity.this;
                systemPropActivity14.s_commandBase = systemPropActivity14.s_commandBase.replace("Ǐ", "m");
                SystemPropActivity systemPropActivity15 = SystemPropActivity.this;
                systemPropActivity15.s_commandBase = systemPropActivity15.s_commandBase.replace("ǐ", "n");
                SystemPropActivity systemPropActivity16 = SystemPropActivity.this;
                systemPropActivity16.s_commandBase = systemPropActivity16.s_commandBase.replace("Ȉ", "o");
                SystemPropActivity systemPropActivity17 = SystemPropActivity.this;
                systemPropActivity17.s_commandBase = systemPropActivity17.s_commandBase.replace("ȉ", "p");
                SystemPropActivity systemPropActivity18 = SystemPropActivity.this;
                systemPropActivity18.s_commandBase = systemPropActivity18.s_commandBase.replace("Ȋ", "q");
                SystemPropActivity systemPropActivity19 = SystemPropActivity.this;
                systemPropActivity19.s_commandBase = systemPropActivity19.s_commandBase.replace("ȋ", "r");
                SystemPropActivity systemPropActivity20 = SystemPropActivity.this;
                systemPropActivity20.s_commandBase = systemPropActivity20.s_commandBase.replace("ɭ", "s");
                SystemPropActivity systemPropActivity21 = SystemPropActivity.this;
                systemPropActivity21.s_commandBase = systemPropActivity21.s_commandBase.replace("ΐ", "t");
                SystemPropActivity systemPropActivity22 = SystemPropActivity.this;
                systemPropActivity22.s_commandBase = systemPropActivity22.s_commandBase.replace("ᴉ", "u");
                SystemPropActivity systemPropActivity23 = SystemPropActivity.this;
                systemPropActivity23.s_commandBase = systemPropActivity23.s_commandBase.replace("Ḭ", "v");
                SystemPropActivity systemPropActivity24 = SystemPropActivity.this;
                systemPropActivity24.s_commandBase = systemPropActivity24.s_commandBase.replace("ḭ", "w");
                SystemPropActivity systemPropActivity25 = SystemPropActivity.this;
                systemPropActivity25.s_commandBase = systemPropActivity25.s_commandBase.replace("Ḯ", "x");
                SystemPropActivity systemPropActivity26 = SystemPropActivity.this;
                systemPropActivity26.s_commandBase = systemPropActivity26.s_commandBase.replace("ḯ", "y");
                SystemPropActivity systemPropActivity27 = SystemPropActivity.this;
                systemPropActivity27.s_commandBase = systemPropActivity27.s_commandBase.replace("ḷ", "z");
                SystemPropActivity systemPropActivity28 = SystemPropActivity.this;
                systemPropActivity28.s_commandBase = systemPropActivity28.s_commandBase.replace("ḹ", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
                SystemPropActivity systemPropActivity29 = SystemPropActivity.this;
                systemPropActivity29.s_commandBase = systemPropActivity29.s_commandBase.replace("ḻ", "B");
                SystemPropActivity systemPropActivity30 = SystemPropActivity.this;
                systemPropActivity30.s_commandBase = systemPropActivity30.s_commandBase.replace("ḽ", "C");
                SystemPropActivity systemPropActivity31 = SystemPropActivity.this;
                systemPropActivity31.s_commandBase = systemPropActivity31.s_commandBase.replace("Ỉ", "D");
                SystemPropActivity systemPropActivity32 = SystemPropActivity.this;
                systemPropActivity32.s_commandBase = systemPropActivity32.s_commandBase.replace("ỉ", ExifInterface.LONGITUDE_EAST);
                SystemPropActivity systemPropActivity33 = SystemPropActivity.this;
                systemPropActivity33.s_commandBase = systemPropActivity33.s_commandBase.replace("Ị", "F");
                SystemPropActivity systemPropActivity34 = SystemPropActivity.this;
                systemPropActivity34.s_commandBase = systemPropActivity34.s_commandBase.replace("ị", "G");
                SystemPropActivity systemPropActivity35 = SystemPropActivity.this;
                systemPropActivity35.s_commandBase = systemPropActivity35.s_commandBase.replace("ἰ", "H");
                SystemPropActivity systemPropActivity36 = SystemPropActivity.this;
                systemPropActivity36.s_commandBase = systemPropActivity36.s_commandBase.replace("ἱ", "I");
                SystemPropActivity systemPropActivity37 = SystemPropActivity.this;
                systemPropActivity37.s_commandBase = systemPropActivity37.s_commandBase.replace("ἲ", "J");
                SystemPropActivity systemPropActivity38 = SystemPropActivity.this;
                systemPropActivity38.s_commandBase = systemPropActivity38.s_commandBase.replace("ἳ", "K");
                SystemPropActivity systemPropActivity39 = SystemPropActivity.this;
                systemPropActivity39.s_commandBase = systemPropActivity39.s_commandBase.replace("ἴ", "L");
                SystemPropActivity systemPropActivity40 = SystemPropActivity.this;
                systemPropActivity40.s_commandBase = systemPropActivity40.s_commandBase.replace("ἵ", "M");
                SystemPropActivity systemPropActivity41 = SystemPropActivity.this;
                systemPropActivity41.s_commandBase = systemPropActivity41.s_commandBase.replace("ἶ", "N");
                SystemPropActivity systemPropActivity42 = SystemPropActivity.this;
                systemPropActivity42.s_commandBase = systemPropActivity42.s_commandBase.replace("ἷ", "O");
                SystemPropActivity systemPropActivity43 = SystemPropActivity.this;
                systemPropActivity43.s_commandBase = systemPropActivity43.s_commandBase.replace("Ἱ", "P");
                SystemPropActivity systemPropActivity44 = SystemPropActivity.this;
                systemPropActivity44.s_commandBase = systemPropActivity44.s_commandBase.replace("Ἲ", "Q");
                SystemPropActivity systemPropActivity45 = SystemPropActivity.this;
                systemPropActivity45.s_commandBase = systemPropActivity45.s_commandBase.replace("Ἳ", "R");
                SystemPropActivity systemPropActivity46 = SystemPropActivity.this;
                systemPropActivity46.s_commandBase = systemPropActivity46.s_commandBase.replace("Ἴ", ExifInterface.LATITUDE_SOUTH);
                SystemPropActivity systemPropActivity47 = SystemPropActivity.this;
                systemPropActivity47.s_commandBase = systemPropActivity47.s_commandBase.replace("Ἵ", ExifInterface.GPS_DIRECTION_TRUE);
                SystemPropActivity systemPropActivity48 = SystemPropActivity.this;
                systemPropActivity48.s_commandBase = systemPropActivity48.s_commandBase.replace("Ἶ", "U");
                SystemPropActivity systemPropActivity49 = SystemPropActivity.this;
                systemPropActivity49.s_commandBase = systemPropActivity49.s_commandBase.replace("Ἷ", ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
                SystemPropActivity systemPropActivity50 = SystemPropActivity.this;
                systemPropActivity50.s_commandBase = systemPropActivity50.s_commandBase.replace("ὶ", ExifInterface.LONGITUDE_WEST);
                SystemPropActivity systemPropActivity51 = SystemPropActivity.this;
                systemPropActivity51.s_commandBase = systemPropActivity51.s_commandBase.replace("ί", "X");
                SystemPropActivity systemPropActivity52 = SystemPropActivity.this;
                systemPropActivity52.s_commandBase = systemPropActivity52.s_commandBase.replace("ῐ", "Y");
                SystemPropActivity systemPropActivity53 = SystemPropActivity.this;
                systemPropActivity53.s_commandBase = systemPropActivity53.s_commandBase.replace("ῑ", "Z");
                SystemPropActivity systemPropActivity54 = SystemPropActivity.this;
                systemPropActivity54.s_commandBase = systemPropActivity54.s_commandBase.replace("ῒ", "_");
                SystemPropActivity systemPropActivity55 = SystemPropActivity.this;
                systemPropActivity55.s_commandBase = systemPropActivity55.s_commandBase.replace("ΐ", "=");
                SystemPropActivity systemPropActivity56 = SystemPropActivity.this;
                systemPropActivity56.s_commandBase = systemPropActivity56.s_commandBase.replace("ῖ", "#");
                SystemPropActivity systemPropActivity57 = SystemPropActivity.this;
                systemPropActivity57.s_commandBase = systemPropActivity57.s_commandBase.replace("ῗ", "-");
                SystemPropActivity systemPropActivity58 = SystemPropActivity.this;
                systemPropActivity58.s_commandBase = systemPropActivity58.s_commandBase.replace("Ῐ", "/");
                SystemPropActivity systemPropActivity59 = SystemPropActivity.this;
                systemPropActivity59.s_commandBase = systemPropActivity59.s_commandBase.replace("Ῑ", "+");
                SystemPropActivity systemPropActivity60 = SystemPropActivity.this;
                systemPropActivity60.s_commandBase = systemPropActivity60.s_commandBase.replace("Ὶ", "&");
                SystemPropActivity systemPropActivity61 = SystemPropActivity.this;
                systemPropActivity61.s_commandBase = systemPropActivity61.s_commandBase.replace("Ί", ".");
                SystemPropActivity systemPropActivity62 = SystemPropActivity.this;
                systemPropActivity62.s_commandBase = systemPropActivity62.s_commandBase.replace("ⅱ", "");
                SystemPropActivity systemPropActivity63 = SystemPropActivity.this;
                systemPropActivity63.s_commandBase = systemPropActivity63.s_commandBase.replace("futhispackage", SystemPropActivity.this.getApplicationContext().getPackageName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            SystemPropActivity.this.tv_prop_title.setText("Scanning build.prop file");
            SystemPropActivity.this.rv_1.setVisibility(8);
            SystemPropActivity.this.lv_1.setVisibility(8);
            SystemPropActivity.this.ln_bottom.setVisibility(8);
            SystemPropActivity systemPropActivity64 = SystemPropActivity.this;
            systemPropActivity64.s_command = systemPropActivity64.s_commandBase.concat("\ngetsystemprop\ngetdataprop");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            SystemPropActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(SystemPropActivity.this.s_command).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            SystemPropActivity.this.b_command = exec.isSuccess();
            SystemPropActivity.this.s_commandResult = SystemPropActivity$MyCONVERT$$ExternalSyntheticBackport0.m("\n", out);
            FileUtil.listDir(SystemPropActivity.this.s_fileprop_list, SystemPropActivity.this.ls_file);
            Iterator it = SystemPropActivity.this.ls_file.iterator();
            while (it.hasNext()) {
                if (!((String) it.next()).endsWith(".prop")) {
                    it.remove();
                }
            }
            SystemPropActivity.this.n = 0.0d;
            for (int i = 0; i < SystemPropActivity.this.ls_file.size(); i++) {
                String concat = "cat ".concat((String) SystemPropActivity.this.ls_file.get((int) SystemPropActivity.this.n));
                SystemPropActivity.this.b_command = false;
                Shell.Result exec2 = Shell.cmd(concat).exec();
                List<String> out2 = exec2.getOut();
                exec2.getCode();
                SystemPropActivity.this.b_command = exec2.isSuccess();
                SystemPropActivity.this.s_commandResult = SystemPropActivity$MyCONVERT$$ExternalSyntheticBackport0.m("\n", out2);
                SystemPropActivity.this.lm_loop.clear();
                SystemPropActivity.this.n2 = 0.0d;
                for (int i2 = 0; i2 < out2.size(); i2++) {
                    SystemPropActivity.this.m_loop = new HashMap();
                    SystemPropActivity.this.m_loop.put("prop", out2.get((int) SystemPropActivity.this.n2));
                    SystemPropActivity systemPropActivity = SystemPropActivity.this;
                    systemPropActivity.s_editor = out2.get((int) systemPropActivity.n2);
                    if (!SystemPropActivity.this.s_editor.contains("build.id=")) {
                        if (!SystemPropActivity.this.s_editor.contains("display.id=")) {
                            if (!SystemPropActivity.this.s_editor.contains("date=")) {
                                if (!SystemPropActivity.this.s_editor.contains("fingerprint=")) {
                                    if (!SystemPropActivity.this.s_editor.contains("incremental=")) {
                                        if (!SystemPropActivity.this.s_editor.contains("user=")) {
                                            if (!SystemPropActivity.this.s_editor.contains("product=")) {
                                                if (!SystemPropActivity.this.s_editor.contains("host=")) {
                                                    if (!SystemPropActivity.this.s_editor.contains("description=")) {
                                                        if (!SystemPropActivity.this.s_editor.contains("bootloader=")) {
                                                            if (!SystemPropActivity.this.s_editor.contains("model=")) {
                                                                if (!SystemPropActivity.this.s_editor.contains("hardware=")) {
                                                                    if (!SystemPropActivity.this.s_editor.contains("board=")) {
                                                                        if (!SystemPropActivity.this.s_editor.contains("device=")) {
                                                                            if (!SystemPropActivity.this.s_editor.contains("name=")) {
                                                                                if (!SystemPropActivity.this.s_editor.contains("manufacturer=")) {
                                                                                    if (!SystemPropActivity.this.s_editor.contains("brand=")) {
                                                                                        if (!SystemPropActivity.this.s_editor.contains("utc=")) {
                                                                                            if (!SystemPropActivity.this.s_editor.contains("release=")) {
                                                                                                SystemPropActivity.this.s_key_prop = "nothingfind";
                                                                                            } else {
                                                                                                SystemPropActivity systemPropActivity2 = SystemPropActivity.this;
                                                                                                systemPropActivity2.s_key_prop = systemPropActivity2.s_editor.replaceAll(".*release=", "release=");
                                                                                            }
                                                                                        } else {
                                                                                            SystemPropActivity systemPropActivity3 = SystemPropActivity.this;
                                                                                            systemPropActivity3.s_key_prop = systemPropActivity3.s_editor.replaceAll(".*utc=", "utc=");
                                                                                        }
                                                                                    } else {
                                                                                        SystemPropActivity systemPropActivity4 = SystemPropActivity.this;
                                                                                        systemPropActivity4.s_key_prop = systemPropActivity4.s_editor.replaceAll(".*brand=", "brand=");
                                                                                    }
                                                                                } else {
                                                                                    SystemPropActivity systemPropActivity5 = SystemPropActivity.this;
                                                                                    systemPropActivity5.s_key_prop = systemPropActivity5.s_editor.replaceAll(".*manufacturer=", "manufacturer=");
                                                                                }
                                                                            } else {
                                                                                SystemPropActivity systemPropActivity6 = SystemPropActivity.this;
                                                                                systemPropActivity6.s_key_prop = systemPropActivity6.s_editor.replaceAll(".*name=", "name=");
                                                                            }
                                                                        } else {
                                                                            SystemPropActivity systemPropActivity7 = SystemPropActivity.this;
                                                                            systemPropActivity7.s_key_prop = systemPropActivity7.s_editor.replaceAll(".*device=", "device=");
                                                                        }
                                                                    } else {
                                                                        SystemPropActivity systemPropActivity8 = SystemPropActivity.this;
                                                                        systemPropActivity8.s_key_prop = systemPropActivity8.s_editor.replaceAll(".*board=", "board=");
                                                                    }
                                                                } else {
                                                                    SystemPropActivity systemPropActivity9 = SystemPropActivity.this;
                                                                    systemPropActivity9.s_key_prop = systemPropActivity9.s_editor.replaceAll(".*hardware=", "hardware=");
                                                                }
                                                            } else {
                                                                SystemPropActivity systemPropActivity10 = SystemPropActivity.this;
                                                                systemPropActivity10.s_key_prop = systemPropActivity10.s_editor.replaceAll(".*model=", "model=");
                                                            }
                                                        } else {
                                                            SystemPropActivity systemPropActivity11 = SystemPropActivity.this;
                                                            systemPropActivity11.s_key_prop = systemPropActivity11.s_editor.replaceAll(".*bootloader=", "bootloader=");
                                                        }
                                                    } else {
                                                        SystemPropActivity systemPropActivity12 = SystemPropActivity.this;
                                                        systemPropActivity12.s_key_prop = systemPropActivity12.s_editor.replaceAll(".*description=", "description=");
                                                    }
                                                } else {
                                                    SystemPropActivity systemPropActivity13 = SystemPropActivity.this;
                                                    systemPropActivity13.s_key_prop = systemPropActivity13.s_editor.replaceAll(".*host=", "host=");
                                                }
                                            } else {
                                                SystemPropActivity systemPropActivity14 = SystemPropActivity.this;
                                                systemPropActivity14.s_key_prop = systemPropActivity14.s_editor.replaceAll(".*product=", "product=");
                                            }
                                        } else {
                                            SystemPropActivity systemPropActivity15 = SystemPropActivity.this;
                                            systemPropActivity15.s_key_prop = systemPropActivity15.s_editor.replaceAll(".*user=", "user=");
                                        }
                                    } else {
                                        SystemPropActivity systemPropActivity16 = SystemPropActivity.this;
                                        systemPropActivity16.s_key_prop = systemPropActivity16.s_editor.replaceAll(".*incremental=", "incremental=");
                                    }
                                } else {
                                    SystemPropActivity systemPropActivity17 = SystemPropActivity.this;
                                    systemPropActivity17.s_key_prop = systemPropActivity17.s_editor.replaceAll(".*fingerprint=", "fingerprint=");
                                }
                            } else {
                                SystemPropActivity systemPropActivity18 = SystemPropActivity.this;
                                systemPropActivity18.s_key_prop = systemPropActivity18.s_editor.replaceAll(".*date=", "date=");
                            }
                        } else {
                            SystemPropActivity systemPropActivity19 = SystemPropActivity.this;
                            systemPropActivity19.s_key_prop = systemPropActivity19.s_editor.replaceAll(".*display.id=", "display.id=");
                        }
                    } else {
                        SystemPropActivity systemPropActivity20 = SystemPropActivity.this;
                        systemPropActivity20.s_key_prop = systemPropActivity20.s_editor.replaceAll(".*build.id=", "build.id=");
                    }
                    SystemPropActivity systemPropActivity21 = SystemPropActivity.this;
                    systemPropActivity21.s_key_prop = systemPropActivity21.s_key_prop.replaceAll("=.*", "=");
                    SystemPropActivity.this.m_loop.put("propkey", SystemPropActivity.this.s_key_prop);
                    SystemPropActivity.this.lm_loop.add(SystemPropActivity.this.m_loop);
                    SystemPropActivity.this.n2 += 1.0d;
                }
                FileUtil.writeFile(((String) SystemPropActivity.this.ls_file.get((int) SystemPropActivity.this.n)).concat(".json"), new Gson().toJson(SystemPropActivity.this.lm_loop).replaceAll("\\\\u003d", "="));
                FileUtil.deleteFile((String) SystemPropActivity.this.ls_file.get((int) SystemPropActivity.this.n));
                SystemPropActivity.this.n += 1.0d;
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r7) {
            this.isRunning = false;
            SystemPropActivity.this.ls_file.clear();
            SystemPropActivity.this.lm_loop.clear();
            FileUtil.listDir(SystemPropActivity.this.s_fileprop_list, SystemPropActivity.this.ls_file);
            Iterator it = SystemPropActivity.this.ls_file.iterator();
            while (it.hasNext()) {
                if (!((String) it.next()).endsWith(".prop.json")) {
                    it.remove();
                }
            }
            SystemPropActivity.this.n = 0.0d;
            for (int i = 0; i < SystemPropActivity.this.ls_file.size(); i++) {
                SystemPropActivity.this.m_loop = new HashMap();
                SystemPropActivity.this.m_loop.put("prop", SystemPropActivity.this.ls_file.get((int) SystemPropActivity.this.n));
                SystemPropActivity.this.lm_loop.add(SystemPropActivity.this.m_loop);
                SystemPropActivity.this.n += 1.0d;
            }
            SketchwareUtil.sortListMap(SystemPropActivity.this.lm_loop, "prop", false, true);
            RecyclerView recyclerView = SystemPropActivity.this.rv_1;
            SystemPropActivity systemPropActivity = SystemPropActivity.this;
            recyclerView.setAdapter(new Rv_1Adapter(systemPropActivity.lm_loop));
            SystemPropActivity.this.rv_1.setHasFixedSize(true);
            SystemPropActivity.this.rv_1.setVisibility(0);
            SystemPropActivity.this.ln_bottom.setVisibility(0);
            SystemPropActivity.this.tv_prop_title.setText("Mengedit 1 file prop akan otomatis mengubah nilai dari file prop lainnya");
            SystemPropActivity.this.pbar_title.setVisibility(8);
        }

        public void cancelCONVERTTask() {
            cancel(true);
        }
    }

    public void _randomAllProp() {
        this.s_random_desc = "fufufuwww-user fufufuxxx fufufuyyy.fufufuzzz release-keys";
        this.s_random_fing = "fufufu1/fufufu2/fufufu3:fufufu4/fufufu5/fufufu6:user/release-keys";
        ArrayList arrayList = new ArrayList();
        arrayList.add("8");
        arrayList.add("9");
        arrayList.add("10");
        arrayList.add("11");
        arrayList.add("12");
        arrayList.add("12.1");
        arrayList.add("13");
        arrayList.add("14");
        Collections.shuffle(arrayList);
        this.s_random_release = (String) arrayList.get(new Random().nextInt(arrayList.size()));
        if (this.pref.getString("incremental", "").equals("true")) {
            this.s_key_prop = "incremental=.*";
            String concat = "incremental=".concat(generateRandomString());
            this.s_new_prop = concat;
            _replaceAllProp(this.s_key_prop, concat);
        }
        if (this.pref.getString("user", "").equals("true")) {
            this.s_key_prop = "user=.*";
            String concat2 = "user=".concat(generateRandomString());
            this.s_new_prop = concat2;
            _replaceAllProp(this.s_key_prop, concat2);
        }
        if (this.pref.getString("product", "").equals("true")) {
            this.s_key_prop = "product=.*";
            String concat3 = "product=".concat(this.s_getproduct);
            this.s_new_prop = concat3;
            _replaceAllProp(this.s_key_prop, concat3);
        }
        if (this.pref.getString("host", "").equals("true")) {
            this.s_key_prop = "host=.*";
            String concat4 = "host=".concat(generateRandomString());
            this.s_new_prop = concat4;
            _replaceAllProp(this.s_key_prop, concat4);
        }
        if (this.pref.getString("bootloader", "").equals("true")) {
            this.s_key_prop = "bootloader=.*";
            String concat5 = "bootloader=".concat(generateRandomString());
            this.s_new_prop = concat5;
            _replaceAllProp(this.s_key_prop, concat5);
        }
        if (this.pref.getString("model", "").equals("true")) {
            this.s_key_prop = "model=.*";
            String concat6 = "model=".concat(this.s_getmodel);
            this.s_new_prop = concat6;
            _replaceAllProp(this.s_key_prop, concat6);
        }
        if (this.pref.getString("hardware", "").equals("true")) {
            this.s_key_prop = "hardware=.*";
            String concat7 = "hardware=".concat(generateRandomString());
            this.s_new_prop = concat7;
            _replaceAllProp(this.s_key_prop, concat7);
        }
        if (this.pref.getString("board", "").equals("true")) {
            this.s_key_prop = "board=.*";
            String concat8 = "board=".concat(generateRandomString());
            this.s_new_prop = concat8;
            _replaceAllProp(this.s_key_prop, concat8);
        }
        if (this.pref.getString("device", "").equals("true")) {
            this.s_key_prop = "device=.*";
            String concat9 = "device=".concat(this.s_getdevice);
            this.s_new_prop = concat9;
            _replaceAllProp(this.s_key_prop, concat9);
        }
        if (this.pref.getString("name", "").equals("true")) {
            this.s_key_prop = "name=.*";
            String concat10 = "name=".concat(this.s_getproduct);
            this.s_new_prop = concat10;
            _replaceAllProp(this.s_key_prop, concat10);
        }
        if (this.pref.getString("manufacturer", "").equals("true")) {
            this.s_key_prop = "manufacturer=.*";
            String concat11 = "manufacturer=".concat(this.s_getbrand);
            this.s_new_prop = concat11;
            _replaceAllProp(this.s_key_prop, concat11);
        }
        if (this.pref.getString("brand", "").equals("true")) {
            this.s_key_prop = "brand=.*";
            String concat12 = "brand=".concat(this.s_getbrand);
            this.s_new_prop = concat12;
            _replaceAllProp(this.s_key_prop, concat12);
        }
        if (this.pref.getString("utc", "").equals("true")) {
            this.s_key_prop = "utc=.*";
            String concat13 = "utc=".concat(String.valueOf(SketchwareUtil.getRandom(1558918000, 1658918000)));
            this.s_new_prop = concat13;
            _replaceAllProp(this.s_key_prop, concat13);
        }
        if (this.pref.getString("release", "").equals("true")) {
            this.s_key_prop = "release=.*";
            String concat14 = "release=".concat(this.s_random_release);
            this.s_new_prop = concat14;
            _replaceAllProp(this.s_key_prop, concat14);
        }
        if (this.pref.getString("build.id", "").equals("true")) {
            this.s_key_prop = "build.id=.*";
            String concat15 = "build.id=".concat(generateRandomString());
            this.s_new_prop = concat15;
            _replaceAllProp(this.s_key_prop, concat15);
        }
        if (this.pref.getString("display.id", "").equals("true")) {
            this.s_key_prop = "display.id=.*";
            String concat16 = "display.id=".concat(generateRandomString().concat(".".concat(generateRandomString())));
            this.s_new_prop = concat16;
            _replaceAllProp(this.s_key_prop, concat16);
        }
        if (this.pref.getString("date", "").equals("true")) {
            this.s_key_prop = "date=.*";
            String concat17 = "date=".concat(generateRandomDate().replace("WIB", "UTC"));
            this.s_new_prop = concat17;
            _replaceAllProp(this.s_key_prop, concat17);
        }
        if (this.pref.getString("fingerprint", "").equals("true")) {
            String replace = this.s_random_fing.replace("fufufu1", this.s_getbrand);
            this.s_random_fing = replace;
            String replace2 = replace.replace("fufufu2", this.s_getproduct);
            this.s_random_fing = replace2;
            String replace3 = replace2.replace("fufufu3", this.s_getdevice);
            this.s_random_fing = replace3;
            String replace4 = replace3.replace("fufufu4", this.s_random_release);
            this.s_random_fing = replace4;
            String replace5 = replace4.replace("fufufu5", generateRandomString());
            this.s_random_fing = replace5;
            String replace6 = replace5.replace("fufufu6", generateRandomString());
            this.s_random_fing = replace6;
            this.s_key_prop = "fingerprint=.*";
            String concat18 = "fingerprint=".concat(replace6);
            this.s_new_prop = concat18;
            _replaceAllProp(this.s_key_prop, concat18);
        }
        if (this.pref.getString("description", "").equals("true")) {
            String replace7 = this.s_random_desc.replace("fufufuwww", generateRandomString());
            this.s_random_desc = replace7;
            String replace8 = replace7.replace("fufufuxxx", this.s_random_release);
            this.s_random_desc = replace8;
            String replace9 = replace8.replace("fufufuyyy", generateRandomString());
            this.s_random_desc = replace9;
            String replace10 = replace9.replace("fufufuzzz", generateRandomString());
            this.s_random_desc = replace10;
            this.s_key_prop = "description=.*";
            String concat19 = "description=".concat(replace10);
            this.s_new_prop = concat19;
            _replaceAllProp(this.s_key_prop, concat19);
        }
        this.tv_prop_title.setText("Mengedit 1 file prop akan otomatis mengubah nilai dari file prop lainnya");
        this.rv_1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.admsoloraya_res_0x7f010023));
        this.rv_1.setVisibility(0);
        this.ln_bottom.setVisibility(0);
        SketchwareUtil.showMessage(getApplicationContext(), "Berhasil diacak (Prop Random)");
    }

    public void _randomFromProp() {
        this.tv_prop_title.setText("Mengacak...");
        this.rv_1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.admsoloraya_res_0x7f010024));
        this.rv_1.setVisibility(8);
        this.ln_bottom.setVisibility(8);
        this.s_getrandom = "";
        this.s_getbrand = "";
        this.s_getdevice = "";
        this.s_getmodel = "";
        this.s_getproduct = "";
        try {
            this.lm_getrandom = (ArrayList) new Gson().fromJson(SketchwareUtil.copyFromInputStream(getAssets().open("prop.json")), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.SystemPropActivity.10
            }.getType());
            while (true) {
                if (!this.s_getbrand.equals("") && !this.s_getdevice.equals("") && !this.s_getmodel.equals("") && !this.s_getproduct.equals("")) {
                    break;
                } else if (!this.lm_getrandom.isEmpty()) {
                    this.s_getrandom = new Gson().toJson(this.lm_getrandom.get(new Random().nextInt(this.lm_getrandom.size())).get("DATA"));
                    ArrayList<HashMap<String, Object>> arrayList = (ArrayList) new Gson().fromJson(this.s_getrandom, new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.SystemPropActivity.11
                    }.getType());
                    this.lm_getbrand = arrayList;
                    if (!arrayList.isEmpty()) {
                        int nextInt = new Random().nextInt(this.lm_getbrand.size());
                        if (this.s_getbrand.equals("")) {
                            this.s_getbrand = this.lm_getbrand.get(nextInt).get("BRAND").toString();
                        } else if (this.s_getdevice.equals("")) {
                            this.s_getdevice = this.lm_getbrand.get(nextInt).get("DEVICE").toString();
                        } else if (this.s_getmodel.equals("")) {
                            this.s_getmodel = this.lm_getbrand.get(nextInt).get("MODEL").toString();
                        } else if (!this.s_getproduct.equals("")) {
                            break;
                        } else {
                            this.s_getproduct = this.lm_getbrand.get(nextInt).get("PRODUCT").toString();
                        }
                    } else {
                        continue;
                    }
                } else {
                    this.s_getbrand = generateRandomString();
                    this.s_getdevice = generateRandomString();
                    this.s_getmodel = generateRandomString();
                    this.s_getproduct = generateRandomString();
                }
            }
            _randomAllProp();
        } catch (Exception e) {
            e.printStackTrace();
            SketchwareUtil.showMessage(getApplicationContext(), "Terjadi kesalahan");
        }
    }

    /* loaded from: classes5.dex */
    public class Rv_1Adapter extends RecyclerView.Adapter<ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_1Adapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = SystemPropActivity.this.getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d005d, (ViewGroup) null);
            inflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            View view = viewHolder.itemView;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a027a);
            String replace = this._data.get(i).get("prop").toString().replaceAll(".*/", "").replace("fufufu", "/").replace(".json", "");
            ((ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a020b)).setImageResource(R.drawable.admsoloraya_res_0x7f080103);
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04f7)).setText(replace);
            ((MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03be)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SystemPropActivity.Rv_1Adapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SystemPropActivity.this._openFileProp(Rv_1Adapter.this._data.get(i).get("prop").toString());
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this._data.size();
        }

        /* loaded from: classes5.dex */
        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View view) {
                super(view);
            }
        }
    }

    /* loaded from: classes5.dex */
    public class Lv_1Adapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_1Adapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this._data.size();
        }

        @Override // android.widget.Adapter
        public HashMap<String, Object> getItem(int i) {
            return this._data.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(final int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = SystemPropActivity.this.getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d005b, (ViewGroup) null);
            }
            MaterialCardView materialCardView = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03a9);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a057c);
            TextView textView2 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0564);
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0254);
            MaterialButton materialButton = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00a6);
            MaterialButton materialButton2 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0091);
            MaterialButton materialButton3 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00d1);
            if (SystemPropActivity.this.pref.getString(this._data.get(i).get("propkey").toString().replaceAll("=.*", ""), "").equals("true")) {
                materialButton.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton2.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton3.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            textView.setText(this._data.get(i).get("prop").toString().replaceAll("=.*", ""));
            textView2.setText(this._data.get(i).get("prop").toString().replaceAll(".*=", ""));
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SystemPropActivity.Lv_1Adapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SystemPropActivity.this._onCardFavButton(Lv_1Adapter.this._data.get(i).get("propkey").toString().replaceAll("=.*", ""));
                }
            });
            materialButton2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SystemPropActivity.Lv_1Adapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SystemPropActivity.this._onCardEditButton(i, Lv_1Adapter.this._data);
                }
            });
            materialButton3.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SystemPropActivity.Lv_1Adapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SystemPropActivity.this._onCardRandomButton(i, Lv_1Adapter.this._data);
                }
            });
            return view;
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
