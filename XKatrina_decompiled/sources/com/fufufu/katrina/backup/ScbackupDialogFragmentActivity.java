package com.fufufu.katrina.backup;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.DialogFragment;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
/* loaded from: classes5.dex */
public class ScbackupDialogFragmentActivity extends DialogFragment {
    private String advertisingId;
    private MaterialButton btn_add_rules;
    private Button btn_batal;
    private Button btn_close;
    private Button btn_oke;
    private LinearLayout ln_backup_add;
    private LinearLayout ln_base;
    private LinearLayout ln_bottom;
    private ChipGroup ln_chip_group;
    private LinearLayout ln_dialog;
    private LinearLayout ln_lottie;
    private LinearLayout ln_proses;
    private LinearLayout ln_proses_content;
    private LinearLayout ln_top_backup;
    private LottieAnimationView lottie1;
    private Chip m_custom;
    private Chip m_normal;
    private MaterialSwitch msc_prop;
    private MaterialSwitch msc_ssaid;
    private MaterialCardView mvc_base;
    private MyBackgroundAction myBackgroundAction;
    private SharedPreferences prefui;
    private Runnable runnableOnBackup;
    private TextInputLayout til_1;
    private TextInputLayout til_2;
    private TextInputLayout til_3;
    private AutoCompleteTextView tv_app;
    private TextView tv_desc;
    private AutoCompleteTextView tv_location;
    private AutoCompleteTextView tv_number;
    private TextView tv_proses_backup;
    private TextView tv_proses_title;
    private TextView tv_rebackup_title;
    private Vibrator vibrate;
    public final int REQ_CD_FP = 101;
    private boolean b_rebackup = false;
    private String s_cek_folder = "";
    private String s_backup_number = "";
    private String s_cat_eternal = "";
    private String s_prop_json = "";
    private boolean b_command = false;
    private HashMap<String, Object> mProp = new HashMap<>();
    private boolean b_custom = false;
    private HashMap<String, Object> m_export = new HashMap<>();
    private String s_scbase = "";
    private String s_exe1 = "";
    private String s_exe2 = "";
    private String s_exe3 = "";
    private String s_backup_name = "";
    private String s_json_loc = "";
    private String s_command = "";
    private String s_commandResult = "";
    private String s_extra = "";
    private String s_commandBase = "";
    private String userAgent = "";
    private ArrayList<HashMap<String, Object>> lm_picked = new ArrayList<>();
    private Intent fp = new Intent("android.intent.action.GET_CONTENT");
    private Intent intentcustom = new Intent();
    private Calendar c = Calendar.getInstance();
    private Intent intentFinish = new Intent();
    private Handler OnBackup = new Handler();

    public void _EXTRA() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d00b3, viewGroup, false);
        initialize(bundle, inflate);
        FirebaseApp.initializeApp(getContext());
        initializeLogic();
        return inflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.mvc_base = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03bd);
        this.ln_base = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0270);
        this.ln_dialog = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0290);
        this.ln_proses = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02cc);
        this.tv_rebackup_title = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0541);
        this.ln_top_backup = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02ee);
        this.til_1 = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a049a);
        this.til_2 = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a049b);
        this.til_3 = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a049c);
        this.ln_backup_add = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a026d);
        this.ln_bottom = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0277);
        this.ln_chip_group = (ChipGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a0283);
        this.btn_add_rules = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a007c);
        this.m_normal = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0334);
        this.m_custom = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0327);
        this.tv_number = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0533);
        this.tv_location = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a051f);
        this.tv_app = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04e6);
        this.tv_desc = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0501);
        this.msc_ssaid = (MaterialSwitch) view.findViewById(R.id.admsoloraya_res_0x7f0a0390);
        this.msc_prop = (MaterialSwitch) view.findViewById(R.id.admsoloraya_res_0x7f0a038f);
        this.btn_batal = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a0084);
        this.btn_oke = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        this.tv_proses_title = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a053e);
        this.ln_proses_content = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02cd);
        this.btn_close = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a0088);
        this.ln_lottie = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02b9);
        this.tv_proses_backup = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a053d);
        this.lottie1 = (LottieAnimationView) view.findViewById(R.id.admsoloraya_res_0x7f0a0308);
        this.fp.setType("*/*");
        this.fp.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        this.prefui = getContext().getSharedPreferences("preferences_ui", 0);
        this.vibrate = (Vibrator) getContext().getSystemService("vibrator");
        this.btn_add_rules.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScbackupDialogFragmentActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScbackupDialogFragmentActivity.this._onAddRules();
            }
        });
        this.msc_ssaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScbackupDialogFragmentActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScbackupDialogFragmentActivity.this.prefui.edit().putString("backup_plus_settings_ssaid", "true").commit();
                } else {
                    ScbackupDialogFragmentActivity.this.prefui.edit().putString("backup_plus_settings_ssaid", "false").commit();
                }
            }
        });
        this.msc_prop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScbackupDialogFragmentActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScbackupDialogFragmentActivity.this.prefui.edit().putString("backup_plus_system_prop", "true").commit();
                } else {
                    ScbackupDialogFragmentActivity.this.prefui.edit().putString("backup_plus_system_prop", "false").commit();
                }
            }
        });
        this.btn_batal.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScbackupDialogFragmentActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScbackupDialogFragmentActivity.this._finishDialog();
            }
        });
        this.btn_oke.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScbackupDialogFragmentActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScbackupDialogFragmentActivity.this._onCreateBackup();
            }
        });
        this.btn_close.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScbackupDialogFragmentActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScbackupDialogFragmentActivity.this._finishDialog();
            }
        });
    }

    private void initializeLogic() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.s_extra = arguments.getString("extrakey", "");
            _setFirstUI();
            return;
        }
        _finishDialog();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1) {
            ArrayList arrayList = new ArrayList();
            if (intent != null) {
                if (intent.getClipData() != null) {
                    for (int i3 = 0; i3 < intent.getClipData().getItemCount(); i3++) {
                        arrayList.add(FileUtil.convertUriToFilePath(getContext().getApplicationContext(), intent.getClipData().getItemAt(i3).getUri()));
                    }
                } else {
                    arrayList.add(FileUtil.convertUriToFilePath(getContext().getApplicationContext(), intent.getData()));
                }
            }
            try {
                ArrayList<HashMap<String, Object>> arrayList2 = (ArrayList) new Gson().fromJson(FileUtil.readFile((String) arrayList.get(0)), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.ScbackupDialogFragmentActivity.7
                }.getType());
                this.lm_picked = arrayList2;
                String obj = arrayList2.get(0).get("title").toString();
                FileUtil.writeFile("/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/files/".concat(obj.concat(".fu")))), this.lm_picked.get(0).get("config").toString());
                SketchwareUtil.showMessage(getContext().getApplicationContext(), "Berhasil menyimpan config");
            } catch (Exception e) {
                e.printStackTrace();
                SketchwareUtil.showMessage(getContext().getApplicationContext(), "Gagal menyimpan config");
            }
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null && (window = dialog.getWindow()) != null) {
            dialog.getWindow().setLayout(-1, -2);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        getDialog().setCancelable(false);
        _onCustomBackup();
    }

    public void _onPrepareProsesBackup() {
        this.c = Calendar.getInstance();
        String concat = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/files/".concat(this.prefui.getString("backup_app_package", "").concat(".fu"))));
        String concat2 = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/files/".concat("custom_backup".concat(".fu"))));
        this.s_scbase = this.s_commandBase.concat("\nonbackup");
        this.s_exe1 = "if tar -czf $filedata/filessaid.tar.gz $filessaid > /dev/null 2>&1; then\necho \"Backup settings_ssaid sukses\"\nelse\necho \"Backup settings_ssaid gagal\nkode : $?\"\nfi";
        this.s_exe2 = "if tar -czf $filedata/fileprop.tar.gz $fileprop > /dev/null 2>&1; then\necho \"Backup system.prop sukses\"\nelse\necho \"Backup system.prop gagal\nkode : $?\"\nfi";
        if (this.b_custom) {
            this.s_exe3 = "fufufufilelist=\"getconfig\"\nfufufuresultlist=\"resultconfig\"\n> \"$fufufuresultlist\"\n\nfiles=($(<\"$fufufufilelist\"))\n\nfor file in \"${files[@]}\"; do\n    if [ -e \"$file\" ]; then\n        echo \"$file\" >> \"$fufufuresultlist\"\n    fi\ndone\n\ntar -czf $thisbackuploc/$thisbackupname -T \"$fufufuresultlist\" > /dev/null 2>&1\n\n# Uji arsip untuk memeriksa apakah rusak\ntar -tf \"$thisbackuploc/$thisbackupname\" >/dev/null 2>&1\nif [ $? -eq 0 ]; then\necho \"Backup data $thisapp sukses\"\nelse\nrm -rf $thisbackuploc/$thisbackupname > /dev/null 2>&1\necho \"Backup data $thisapp gagal\nkode : $?\"\nfi\n> \"$fufufuresultlist\"";
            String replace = "fufufufilelist=\"getconfig\"\nfufufuresultlist=\"resultconfig\"\n> \"$fufufuresultlist\"\n\nfiles=($(<\"$fufufufilelist\"))\n\nfor file in \"${files[@]}\"; do\n    if [ -e \"$file\" ]; then\n        echo \"$file\" >> \"$fufufuresultlist\"\n    fi\ndone\n\ntar -czf $thisbackuploc/$thisbackupname -T \"$fufufuresultlist\" > /dev/null 2>&1\n\n# Uji arsip untuk memeriksa apakah rusak\ntar -tf \"$thisbackuploc/$thisbackupname\" >/dev/null 2>&1\nif [ $? -eq 0 ]; then\necho \"Backup data $thisapp sukses\"\nelse\nrm -rf $thisbackuploc/$thisbackupname > /dev/null 2>&1\necho \"Backup data $thisapp gagal\nkode : $?\"\nfi\n> \"$fufufuresultlist\"".replace("getconfig", concat);
            this.s_exe3 = replace;
            this.s_exe3 = replace.replace("resultconfig", concat2);
        } else {
            this.s_exe3 = "tar -czf $thisbackuploc/$thisbackupname --exclude=\"$filedata/lib\" --exclude=\"$filedata/cache\" $filedata > /dev/null 2>&1\n\n# Uji arsip untuk memeriksa apakah rusak\ntar -tf \"$thisbackuploc/$thisbackupname\" >/dev/null 2>&1\nif [ $? -eq 0 ]; then\necho \"Backup data $thisapp sukses\"\nelse\nrm -rf $thisbackuploc/$thisbackupname > /dev/null 2>&1\necho \"Backup data $thisapp gagal\nkode : $?\"\nfi";
        }
        this.s_backup_name = this.prefui.getString("backup_app_package", "").concat("-".concat(new SimpleDateFormat("yyyyMMdd-HHmmss").format(this.c.getTime())));
        String replace2 = this.s_scbase.replace("futhisapp", this.prefui.getString("backup_app_name", ""));
        this.s_scbase = replace2;
        String replace3 = replace2.replace("futhispackage", this.prefui.getString("backup_app_package", ""));
        this.s_scbase = replace3;
        String replace4 = replace3.replace("futhisbackupname", this.s_backup_name);
        this.s_scbase = replace4;
        String replace5 = replace4.replace("futhisbackuppath", this.prefui.getString("backup_sdcard_location", "").concat("/".concat(this.prefui.getString("backup_app_package", ""))));
        this.s_scbase = replace5;
        this.s_scbase = replace5.replace("futhisbackuploc", this.prefui.getString("backup_sdcard_location", "").concat("/".concat(this.prefui.getString("backup_app_package", "").concat("/".concat(this.s_backup_number)))));
        this.s_json_loc = this.prefui.getString("backup_sdcard_location", "").concat("/".concat(this.prefui.getString("backup_app_package", "").concat("/".concat(this.s_backup_number.concat("/".concat(this.s_backup_name.concat(".json")))))));
        if (this.prefui.getString("backup_plus_settings_ssaid", "").equals("true")) {
            this.s_scbase = this.s_scbase.replace("#exe1", this.s_exe1);
            this.mProp.put("settings_ssaid", "true");
        } else {
            this.mProp.put("settings_ssaid", "false");
        }
        if (this.prefui.getString("backup_plus_system_prop", "").equals("true")) {
            this.s_scbase = this.s_scbase.replace("#exe2", this.s_exe2);
            this.mProp.put("system.prop", "true");
        } else {
            this.mProp.put("system.prop", "false");
        }
        String replace6 = this.s_scbase.replace("#exe3", this.s_exe3);
        this.s_scbase = replace6;
        this.s_command = replace6;
        this.mProp.put("NOTE", "");
        this.mProp.put("IDIKLAN", this.prefui.getString("advertisingid", ""));
        this.mProp.put("DATE", new SimpleDateFormat("EEEE dd MMMM yyyy HH:mm:ss", new Locale("id", "ID")).format(this.c.getTime()));
        this.mProp.put("MARK", "false");
        this.mProp.put("COLOR", "#FFFFFFFF");
        this.mProp.put("UNIX", String.valueOf(this.c.getTime().getTime() / 1000));
        _onFinalProsesBackup();
    }

    public void _onFinalProsesBackup() {
        this.tv_proses_title.setText("Proses");
        this.tv_proses_backup.setText("Membuat backup\n".concat(this.prefui.getString("backup_app_name", "")));
        this.ln_lottie.setVisibility(0);
        this.btn_close.setVisibility(8);
        _OnBackgroundAction();
        Runnable runnable = new Runnable() { // from class: com.fufufu.katrina.backup.ScbackupDialogFragmentActivity.8
            @Override // java.lang.Runnable
            public void run() {
                if (ScbackupDialogFragmentActivity.this.b_command) {
                    ScbackupDialogFragmentActivity.this.OnBackup.removeCallbacks(ScbackupDialogFragmentActivity.this.runnableOnBackup);
                    if (!ScbackupDialogFragmentActivity.this.s_commandResult.contains("sukses")) {
                        String replace = ScbackupDialogFragmentActivity.this.s_json_loc.replace(".json", ".tar.gz");
                        String str = ScbackupDialogFragmentActivity.this.s_json_loc;
                        String concat = ScbackupDialogFragmentActivity.this.prefui.getString("backup_sdcard_location", "").concat("/".concat(ScbackupDialogFragmentActivity.this.prefui.getString("backup_app_package", "").concat("/")));
                        ScbackupDialogFragmentActivity.this.tv_proses_title.setText("Gagal");
                        ScbackupDialogFragmentActivity.this.tv_proses_backup.setText(ScbackupDialogFragmentActivity.this.s_commandResult);
                        File file = new File(replace);
                        File file2 = new File(str);
                        if (!file.exists() || !file2.exists()) {
                            if (file.exists()) {
                                file.delete();
                            }
                            if (file2.exists()) {
                                file2.delete();
                            }
                            ScbackupDialogFragmentActivity.deleteEmptyFolders(concat);
                        }
                    } else {
                        ScbackupDialogFragmentActivity.this.tv_proses_title.setText("Selesai");
                        ScbackupDialogFragmentActivity.this.tv_proses_backup.setText(ScbackupDialogFragmentActivity.this.s_commandResult);
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.setPrettyPrinting();
                        Gson create = gsonBuilder.create();
                        JsonParser jsonParser = new JsonParser();
                        ScbackupDialogFragmentActivity.this.s_prop_json = new Gson().toJson(ScbackupDialogFragmentActivity.this.mProp);
                        ScbackupDialogFragmentActivity scbackupDialogFragmentActivity = ScbackupDialogFragmentActivity.this;
                        scbackupDialogFragmentActivity.s_prop_json = create.toJson(jsonParser.parse(scbackupDialogFragmentActivity.s_prop_json));
                        FileUtil.writeFile(ScbackupDialogFragmentActivity.this.s_json_loc, ScbackupDialogFragmentActivity.this.s_prop_json);
                    }
                    ScbackupDialogFragmentActivity.this.b_command = false;
                    ScbackupDialogFragmentActivity.this.ln_lottie.setVisibility(8);
                    ScbackupDialogFragmentActivity.this.btn_close.setVisibility(0);
                    ScbackupDialogFragmentActivity.this._onVibrate();
                    return;
                }
                ScbackupDialogFragmentActivity.this.OnBackup.postDelayed(ScbackupDialogFragmentActivity.this.runnableOnBackup, 100L);
            }
        };
        this.runnableOnBackup = runnable;
        this.OnBackup.postDelayed(runnable, 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void deleteEmptyFolders(String str) {
        File[] listFiles;
        File file = new File(str);
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory() && file2.listFiles().length == 0) {
                    file2.delete();
                }
            }
        }
    }

    public void _onCreateBackup() {
        if (this.m_custom.isChecked()) {
            this.b_custom = true;
        } else {
            this.b_custom = false;
        }
        this.ln_dialog.setVisibility(8);
        this.ln_proses.setVisibility(0);
        getAdvertisingId();
    }

    public void _onAddRules() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d00a6, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        final String concat = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/files/".concat(this.prefui.getString("backup_app_package", "").concat(".fu"))));
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0524)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScbackupDialogFragmentActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScbackupDialogFragmentActivity.this.intentcustom.setClass(ScbackupDialogFragmentActivity.this.getContext().getApplicationContext(), CstBackupActivity.class);
                ScbackupDialogFragmentActivity.this.intentcustom.putExtra("package", ScbackupDialogFragmentActivity.this.prefui.getString("backup_app_package", ""));
                ScbackupDialogFragmentActivity scbackupDialogFragmentActivity = ScbackupDialogFragmentActivity.this;
                scbackupDialogFragmentActivity.startActivity(scbackupDialogFragmentActivity.intentcustom);
                popupWindow.dismiss();
            }
        });
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0525)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScbackupDialogFragmentActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FileUtil.isExistFile(concat)) {
                    FileUtil.deleteFile(concat);
                    ScbackupDialogFragmentActivity.this.m_normal.setVisibility(4);
                    ScbackupDialogFragmentActivity.this.m_custom.setVisibility(4);
                    popupWindow.dismiss();
                    return;
                }
                SketchwareUtil.showMessage(ScbackupDialogFragmentActivity.this.getContext().getApplicationContext(), "Tidak ada file");
            }
        });
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0526)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScbackupDialogFragmentActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScbackupDialogFragmentActivity scbackupDialogFragmentActivity = ScbackupDialogFragmentActivity.this;
                scbackupDialogFragmentActivity.startActivityForResult(scbackupDialogFragmentActivity.fp, 101);
                popupWindow.dismiss();
            }
        });
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0527)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScbackupDialogFragmentActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FileUtil.isExistFile(concat)) {
                    ScbackupDialogFragmentActivity.this.lm_picked.clear();
                    ScbackupDialogFragmentActivity.this.m_export = new HashMap();
                    ScbackupDialogFragmentActivity.this.m_export.put("title", ScbackupDialogFragmentActivity.this.prefui.getString("backup_app_package", ""));
                    ScbackupDialogFragmentActivity.this.m_export.put("config", FileUtil.readFile(concat));
                    ScbackupDialogFragmentActivity.this.lm_picked.add(ScbackupDialogFragmentActivity.this.m_export);
                    String concat2 = FileUtil.getExternalStorageDir().concat("/csb_".concat(ScbackupDialogFragmentActivity.this.prefui.getString("backup_app_package", "").concat(".fu")));
                    FileUtil.writeFile(concat2, new Gson().toJson(ScbackupDialogFragmentActivity.this.lm_picked));
                    SketchwareUtil.showMessage(ScbackupDialogFragmentActivity.this.getContext().getApplicationContext(), "Berhasil disimpan di ".concat(concat2));
                    popupWindow.dismiss();
                    return;
                }
                SketchwareUtil.showMessage(ScbackupDialogFragmentActivity.this.getContext().getApplicationContext(), "Tidak ada file");
            }
        });
        popupWindow.setAnimationStyle(16973826);
        popupWindow.showAsDropDown(this.btn_add_rules, -100, 0);
    }

    public void _setFirstUI() {
        int parseInt;
        this.ln_proses.setVisibility(8);
        try {
            String copyFromInputStream = SketchwareUtil.copyFromInputStream(getContext().getAssets().open("prop.sh"));
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
            this.s_commandBase = replace60.replace("ⅱ", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            WebSettings settings = new WebView(getActivity()).getSettings();
            if (settings != null) {
                this.userAgent = settings.getUserAgentString();
            } else {
                this.userAgent = "User Agent null";
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            this.userAgent = "User Agent null";
        }
        this.mProp.clear();
        HashMap<String, Object> hashMap = new HashMap<>();
        this.mProp = hashMap;
        hashMap.put("DEVICE", Build.DEVICE);
        this.mProp.put("MODEL", Build.MODEL);
        this.mProp.put("PRODUCT", Build.PRODUCT);
        this.mProp.put("MANUFACTURER", Build.MANUFACTURER);
        this.mProp.put("BRAND", Build.BRAND);
        this.mProp.put("SDK", String.valueOf(Build.VERSION.SDK_INT));
        this.mProp.put("BOARD", Build.BOARD);
        this.mProp.put("BOOT", Build.BOOTLOADER);
        this.mProp.put("DISPLAY", Build.DISPLAY);
        this.mProp.put("FINGERPRINT", Build.FINGERPRINT);
        this.mProp.put("HARDWARE", Build.HARDWARE);
        this.mProp.put("BUILDID", Build.ID);
        this.mProp.put("HOST", Build.HOST);
        this.mProp.put("USER", Build.USER);
        this.mProp.put("RELEASE", Build.VERSION.RELEASE);
        this.mProp.put("INCREMENTAL", Build.VERSION.INCREMENTAL);
        this.mProp.put("USERAGENT", this.userAgent);
        this.mProp.put("HTTPAGENT", System.getProperty("http.agent"));
        this.mProp.put("RADIOVERSION", Build.getRadioVersion());
        this.mProp.put("TIME", Long.toString(Build.TIME));
        this.mProp.put("ANDROIDID", "NOT ETERNAL");
        this.mProp.put("BLUETHOOTNAME", "NOT ETERNAL");
        this.mProp.put("DEVICENAME", "NOT ETERNAL");
        this.mProp.put("IMEI", "NOT ETERNAL");
        this.mProp.put("SERIAL", "NOT ETERNAL");
        this.mProp.put("SERIAL2", "NOT ETERNAL");
        String concat = this.prefui.getString("backup_sdcard_location", "").concat("/".concat(this.prefui.getString("backup_app_package", "")));
        this.s_cek_folder = concat;
        if (FileUtil.isExistFile(concat)) {
            String[] list = new File(this.s_cek_folder).list();
            Arrays.sort(list);
            int i = 0;
            for (String str : list) {
                if (isInteger(str) && (parseInt = Integer.parseInt(str)) > i) {
                    i = parseInt;
                }
            }
            this.s_backup_number = String.format("%03d", Integer.valueOf(i + 1));
        } else {
            this.s_backup_number = "001";
        }
        String concat2 = "cat /data/user/0/".concat(this.prefui.getString("backup_app_package", "").concat("/eternal_id"));
        this.s_cat_eternal = concat2;
        this.b_command = false;
        Shell.Result exec = Shell.cmd(concat2).exec();
        List<String> out = exec.getOut();
        exec.getCode();
        this.b_command = exec.isSuccess();
        String m = ScbackupDialogFragmentActivity$$ExternalSyntheticBackport0.m("\n", out);
        this.s_prop_json = m;
        if (!m.contains("MANUFACTURER")) {
            this.s_prop_json = new Gson().toJson(this.mProp);
        } else {
            String replace61 = this.s_prop_json.replace("[", "");
            this.s_prop_json = replace61;
            this.s_prop_json = replace61.replace("]", "");
            this.mProp = (HashMap) new Gson().fromJson(this.s_prop_json, new TypeToken<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.ScbackupDialogFragmentActivity.13
            }.getType());
        }
        this.m_normal.setChecked(true);
        this.tv_number.setText(this.s_backup_number);
        this.tv_location.setText(this.prefui.getString("backup_sdcard_location", "").replace("/storage/emulated/0", ""));
        this.tv_app.setText(this.prefui.getString("backup_app_package", ""));
        if (this.prefui.getString("backup_plus_settings_ssaid", "").equals("")) {
            this.prefui.edit().putString("backup_plus_settings_ssaid", "false").commit();
        }
        if (this.prefui.getString("backup_plus_system_prop", "").equals("")) {
            this.prefui.edit().putString("backup_plus_system_prop", "false").commit();
        }
        if (this.prefui.getString("backup_plus_settings_ssaid", "").equals("false")) {
            this.msc_ssaid.setChecked(false);
        } else {
            this.msc_ssaid.setChecked(true);
        }
        if (this.prefui.getString("backup_plus_system_prop", "").equals("false")) {
            this.msc_prop.setChecked(false);
        } else {
            this.msc_prop.setChecked(true);
        }
        _onCustomBackup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class GetAdvertisingIdTask extends AsyncTask<Void, Void, String> {
        private GetAdvertisingIdTask() {
        }

        /* synthetic */ GetAdvertisingIdTask(ScbackupDialogFragmentActivity scbackupDialogFragmentActivity, GetAdvertisingIdTask getAdvertisingIdTask) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String doInBackground(Void... voidArr) {
            try {
                return AdvertisingIdClient.getAdvertisingIdInfo(ScbackupDialogFragmentActivity.this.getContext()).getId();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
                return null;
            } catch (GooglePlayServicesRepairableException e2) {
                e2.printStackTrace();
                return null;
            } catch (IOException e3) {
                e3.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            if (str != null) {
                ScbackupDialogFragmentActivity.this.prefui.edit().putString("advertisingid", str).commit();
            } else {
                ScbackupDialogFragmentActivity.this.prefui.edit().putString("advertisingid", "").commit();
            }
            ScbackupDialogFragmentActivity.this._onPrepareProsesBackup();
        }
    }

    private void getAdvertisingId() {
        new GetAdvertisingIdTask(this, null).execute(new Void[0]);
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public void _finishDialog() {
        this.intentFinish.setClass(getContext().getApplicationContext(), ShortcutExecutorActivity.class);
        this.intentFinish.putExtra("shortcut_command", "finish");
        this.intentFinish.putExtra("shortcut_desc", "finish");
        startActivity(this.intentFinish);
        getActivity().finish();
    }

    public void _OnBackgroundAction() {
        MyBackgroundAction myBackgroundAction = this.myBackgroundAction;
        if (myBackgroundAction != null && myBackgroundAction.isRunning) {
            this.myBackgroundAction.cancelBackgroundActionTask();
        }
        MyBackgroundAction myBackgroundAction2 = new MyBackgroundAction();
        this.myBackgroundAction = myBackgroundAction2;
        myBackgroundAction2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyBackgroundAction extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyBackgroundAction() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            ScbackupDialogFragmentActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(ScbackupDialogFragmentActivity.this.s_command).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            ScbackupDialogFragmentActivity.this.b_command = exec.isSuccess();
            ScbackupDialogFragmentActivity.this.s_commandResult = ScbackupDialogFragmentActivity$MyBackgroundAction$$ExternalSyntheticBackport0.m("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
        }

        public void cancelBackgroundActionTask() {
            cancel(true);
        }
    }

    public void _onVibrate() {
        this.vibrate.vibrate(100L);
    }

    public void _onCustomBackup() {
        if (FileUtil.isExistFile("/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/files/".concat(this.prefui.getString("backup_app_package", "").concat(".fu")))))) {
            this.m_custom.setVisibility(0);
            this.m_normal.setVisibility(0);
            return;
        }
        this.m_custom.setVisibility(4);
        this.m_normal.setVisibility(4);
    }
}
