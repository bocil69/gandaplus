package com.fufufu.katrina.backup;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.elevation.SurfaceColors;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes5.dex */
public class ScrestoreDialogFragmentActivity extends DialogFragment {
    private AutoCompleteTextView auto_editor;
    private Button btn_batal;
    private Button btn_batal_editor;
    private MaterialButton btn_next;
    private Button btn_oke;
    private Button btn_open;
    private MaterialButton btn_prev;
    private Button btn_restore;
    private Button btn_save_editor;
    private MaterialCardView cv_backup;
    private HorizontalScrollView hscr_1;
    private ImageView im_icon;
    private LinearLayout ln_01;
    private LinearLayout ln_backup;
    private LinearLayout ln_backup_content;
    private LinearLayout ln_base;
    private LinearLayout ln_base_top;
    private LinearLayout ln_button;
    private LinearLayout ln_button_bottom;
    private LinearLayout ln_button_editor;
    private LinearLayout ln_content;
    private LinearLayout ln_editor;
    private LinearLayout ln_lottie;
    private LinearLayout ln_mark;
    private LinearLayout ln_proses;
    private LinearLayout ln_proses_content;
    private LinearLayout ln_switch;
    private LinearLayout ln_top;
    private LottieAnimationView lottie1;
    private MaterialCardView mvc_00;
    private MaterialCardView mvc_01;
    private MaterialCardView mvc_02;
    private MaterialCardView mvc_03;
    private MaterialCardView mvc_04;
    private MaterialCardView mvc_05;
    private MaterialCardView mvc_06;
    private MaterialCardView mvc_07;
    private MaterialCardView mvc_08;
    private MaterialCardView mvc_09;
    private MaterialCardView mvc_10;
    private MaterialCardView mvc_base;
    private MaterialCardView mvc_rv;
    private MyBackgroundAction myBackgroundAction;
    private SharedPreferences preflast;
    private SharedPreferences prefui;
    private Runnable runnableOnRestore;
    private RecyclerView rv_1;
    private MaterialSwitch switch_prop;
    private MaterialSwitch switch_ssaid;
    private TextInputLayout til_1;
    private TextView tv_date;
    private TextView tv_desc_prop;
    private TextView tv_desc_ssaid;
    private TextView tv_folder;
    private TextView tv_note;
    private TextView tv_package;
    private TextView tv_proses_backup;
    private TextView tv_proses_title;
    private TextView tv_title;
    private Vibrator vibrate;
    private String s_backup_app = "";
    private String s_backup_loc = "";
    private String s_backupLocation = "";
    private HashMap<String, Object> m_filebackup = new HashMap<>();
    private String s_json_backup = "";
    private HashMap<String, Object> m_json_backup = new HashMap<>();
    private double n_restore_position = 0.0d;
    private String s_commandResult = "";
    private boolean b_command = false;
    private String s_command = "";
    private String s_restore_loc = "";
    private String s_restore_ssaid = "";
    private String s_restore_prop = "";
    private String s_restore_sdk = "";
    private String s_sdk = "";
    private boolean b_ssaid = false;
    private boolean b_prop = false;
    private double n_loop = 0.0d;
    private String s_commandBase = "";
    private String s_scbase = "";
    private String s_exe1 = "";
    private String s_exe2 = "";
    private String s_exe3 = "";
    private String s_exitCode = "";
    private String s_note = "";
    private ArrayList<String> ls_filebackup = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_filebackup = new ArrayList<>();
    private ArrayList<String> ls_sorter = new ArrayList<>();
    private Intent intentFinish = new Intent();
    private Handler OnRestore = new Handler();

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d00b6, viewGroup, false);
        initialize(bundle, inflate);
        FirebaseApp.initializeApp(getContext());
        initializeLogic();
        return inflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.mvc_base = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03bd);
        this.ln_base = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0270);
        this.ln_base_top = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0275);
        this.ln_proses = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02cc);
        this.ln_top = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02ed);
        this.mvc_rv = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03bf);
        this.ln_content = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0288);
        this.ln_editor = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0294);
        this.ln_switch = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02e7);
        this.ln_button = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a027b);
        this.im_icon = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0218);
        this.ln_01 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
        this.tv_title = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0571);
        this.tv_package = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0534);
        this.rv_1 = (RecyclerView) view.findViewById(R.id.admsoloraya_res_0x7f0a040c);
        this.btn_prev = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00cf);
        this.ln_backup = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a026c);
        this.btn_next = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00ca);
        this.cv_backup = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a014a);
        this.ln_backup_content = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a026e);
        this.tv_folder = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0510);
        this.tv_date = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0500);
        this.tv_note = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0532);
        this.til_1 = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a049a);
        this.hscr_1 = (HorizontalScrollView) view.findViewById(R.id.admsoloraya_res_0x7f0a01d5);
        this.ln_button_editor = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a027d);
        this.auto_editor = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0061);
        this.ln_mark = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02bb);
        this.mvc_00 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03aa);
        this.mvc_01 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03ab);
        this.mvc_02 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03ac);
        this.mvc_03 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03ad);
        this.mvc_04 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03ae);
        this.mvc_05 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03af);
        this.mvc_06 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03b0);
        this.mvc_07 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03b1);
        this.mvc_08 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03b2);
        this.mvc_09 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03b3);
        this.mvc_10 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03b4);
        this.btn_batal_editor = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a0085);
        this.btn_save_editor = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00ed);
        this.tv_desc_ssaid = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0503);
        this.switch_ssaid = (MaterialSwitch) view.findViewById(R.id.admsoloraya_res_0x7f0a0471);
        this.tv_desc_prop = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0502);
        this.switch_prop = (MaterialSwitch) view.findViewById(R.id.admsoloraya_res_0x7f0a046f);
        this.btn_batal = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a0084);
        this.btn_restore = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00e9);
        this.tv_proses_title = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a053e);
        this.ln_proses_content = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02cd);
        this.ln_button_bottom = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a027c);
        this.ln_lottie = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02b9);
        this.tv_proses_backup = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a053d);
        this.lottie1 = (LottieAnimationView) view.findViewById(R.id.admsoloraya_res_0x7f0a0308);
        this.btn_oke = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        this.btn_open = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00cd);
        this.prefui = getContext().getSharedPreferences("preferences_ui", 0);
        this.preflast = getContext().getSharedPreferences("preferences_last", 0);
        this.vibrate = (Vibrator) getContext().getSystemService("vibrator");
        this.btn_prev.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity.this._onPrev();
            }
        });
        this.btn_next.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity.this._onNext();
            }
        });
        this.cv_backup.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity.this._showEditor();
                ScrestoreDialogFragmentActivity.this.ln_switch.setVisibility(8);
                ScrestoreDialogFragmentActivity.this.ln_button.setVisibility(8);
                ScrestoreDialogFragmentActivity.this.ln_editor.setVisibility(0);
            }
        });
        this.btn_batal_editor.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity.this._hideEditor();
            }
        });
        this.btn_save_editor.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity.this._saveEditor();
            }
        });
        this.switch_ssaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScrestoreDialogFragmentActivity.this.b_ssaid = true;
                } else {
                    ScrestoreDialogFragmentActivity.this.b_ssaid = false;
                }
            }
        });
        this.switch_prop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScrestoreDialogFragmentActivity.this.b_prop = true;
                } else {
                    ScrestoreDialogFragmentActivity.this.b_prop = false;
                }
            }
        });
        this.btn_batal.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity.this._finishDialog();
            }
        });
        this.btn_restore.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity.this._onRestore();
            }
        });
        this.btn_oke.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (ScrestoreDialogFragmentActivity.this.btn_oke.getText().toString().equals("Reboot")) {
                    ScrestoreDialogFragmentActivity.this.s_command = "am start -a android.intent.action.REBOOT";
                    ScrestoreDialogFragmentActivity.this.b_command = false;
                    Shell.Result exec = Shell.cmd(ScrestoreDialogFragmentActivity.this.s_command).exec();
                    List<String> out = exec.getOut();
                    exec.getCode();
                    ScrestoreDialogFragmentActivity.this.b_command = exec.isSuccess();
                    ScrestoreDialogFragmentActivity.this.s_commandResult = ScrestoreDialogFragmentActivity$10$$ExternalSyntheticBackport0.m("\n", out);
                    return;
                }
                ScrestoreDialogFragmentActivity.this._finishDialog();
            }
        });
        this.btn_open.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScrestoreDialogFragmentActivity screstoreDialogFragmentActivity = ScrestoreDialogFragmentActivity.this;
                screstoreDialogFragmentActivity._openApp(screstoreDialogFragmentActivity.prefui.getString("backup_app_package", ""));
            }
        });
    }

    private void initializeLogic() {
        _setFirstUI();
        _setBackupPosition();
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
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        _finishDialog();
    }

    public void _setFirstUI() {
        this.ln_base_top.setBackgroundColor(Color.parseColor("#80ffffff"));
        this.rv_1.setBackgroundColor(Color.parseColor("#80ffffff"));
        this.ln_proses.setVisibility(8);
        this.ln_editor.setVisibility(8);
        try {
            this.im_icon.setImageDrawable(getActivity().getPackageManager().getApplicationIcon(this.prefui.getString("backup_app_package", "")));
        } catch (PackageManager.NameNotFoundException unused) {
        }
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
        this.tv_package.setText(this.prefui.getString("backup_app_package", ""));
        this.btn_prev.setIcon(getResources().getDrawable(R.drawable.admsoloraya_res_0x7f080141));
        this.btn_next.setIcon(getResources().getDrawable(R.drawable.admsoloraya_res_0x7f080140));
        this.s_backup_app = this.prefui.getString("backup_app_package", "");
        String string = this.prefui.getString("backup_sdcard_location", "");
        this.s_backup_loc = string;
        String concat = string.concat("/".concat(this.s_backup_app));
        this.s_backupLocation = concat;
        FileUtil.listDir(concat, this.ls_filebackup);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.ls_filebackup.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            File file = new File(next);
            if (file.isFile()) {
                this.ls_filebackup.remove(next);
            } else if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File file2 : listFiles) {
                        if (file2.isFile() && file2.getName().endsWith(".tar.gz")) {
                            break;
                        }
                    }
                }
                z = false;
                if (!z) {
                    arrayList.add(next);
                }
            }
        }
        this.ls_filebackup.removeAll(arrayList);
        Iterator<String> it2 = this.ls_filebackup.iterator();
        while (it2.hasNext()) {
            String next2 = it2.next();
            HashMap<String, Object> hashMap = new HashMap<>();
            this.m_filebackup = hashMap;
            hashMap.put("file", next2);
            FileUtil.listDir(next2, this.ls_sorter);
            Iterator<String> it3 = this.ls_sorter.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                String next3 = it3.next();
                if (next3.endsWith(".tar.gz")) {
                    this.m_filebackup.put("backup", next3);
                    break;
                }
            }
            this.lm_filebackup.add(this.m_filebackup);
        }
        SketchwareUtil.sortListMap(this.lm_filebackup, "file", false, true);
        this.rv_1.setAdapter(new Rv_1Adapter(this.lm_filebackup));
        this.rv_1.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        if (this.lm_filebackup.size() == 0) {
            this.mvc_rv.setVisibility(8);
            this.ln_content.setVisibility(8);
            this.btn_restore.setVisibility(8);
            this.ln_switch.setVisibility(8);
            return;
        }
        this.mvc_rv.setVisibility(0);
        this.ln_content.setVisibility(0);
        this.btn_restore.setVisibility(0);
        this.ln_switch.setVisibility(0);
    }

    public void _finishDialog() {
        this.intentFinish.setClass(getContext().getApplicationContext(), ShortcutExecutorActivity.class);
        this.intentFinish.putExtra("shortcut_command", "finish");
        this.intentFinish.putExtra("shortcut_desc", "finish");
        startActivity(this.intentFinish);
        getActivity().finish();
    }

    public void _onChooseFile(String str, String str2) {
        _hideEditor();
        this.s_restore_loc = str2;
        String replace = str2.replace(".tar.gz", ".json");
        this.s_json_backup = replace;
        if (FileUtil.isExistFile(replace)) {
            this.s_json_backup = FileUtil.readFile(this.s_json_backup);
            this.m_json_backup = (HashMap) new Gson().fromJson(this.s_json_backup, new TypeToken<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.12
            }.getType());
            this.tv_folder.setText(str);
            this.ln_backup.setVisibility(0);
            this.ln_switch.setVisibility(0);
            this.btn_restore.setVisibility(0);
            this.ln_button.setVisibility(0);
            _setDialogUI();
            return;
        }
        this.ln_backup.setVisibility(4);
        this.ln_switch.setVisibility(4);
        this.ln_button.setVisibility(0);
        this.btn_restore.setVisibility(8);
    }

    public void _setBackupPosition() {
        String string = this.preflast.getString(this.s_backup_app, "");
        this.s_restore_loc = string;
        if (!string.equals("")) {
            this.n_restore_position = 0.0d;
            for (int i = 0; i < this.lm_filebackup.size(); i++) {
                if (this.lm_filebackup.get((int) this.n_restore_position).get("backup").toString().equals(this.preflast.getString(this.s_backup_app, ""))) {
                    ((LinearLayoutManager) this.rv_1.getLayoutManager()).scrollToPositionWithOffset((int) this.n_restore_position, 0);
                    String replace = this.lm_filebackup.get((int) this.n_restore_position).get("backup").toString().replace(".tar.gz", ".json");
                    this.s_json_backup = replace;
                    if (FileUtil.isExistFile(replace)) {
                        this.s_json_backup = FileUtil.readFile(this.s_json_backup);
                        this.m_json_backup = (HashMap) new Gson().fromJson(this.s_json_backup, new TypeToken<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.13
                        }.getType());
                        this.tv_folder.setText(Uri.parse(this.lm_filebackup.get((int) this.n_restore_position).get("file").toString()).getLastPathSegment());
                        this.ln_backup.setVisibility(0);
                        this.ln_switch.setVisibility(0);
                        this.btn_restore.setVisibility(0);
                        _setDialogUI();
                        return;
                    }
                    this.ln_backup.setVisibility(4);
                    this.ln_switch.setVisibility(4);
                    this.btn_restore.setVisibility(8);
                }
                this.n_restore_position += 1.0d;
            }
        } else if (this.lm_filebackup.size() != 0) {
            this.s_restore_loc = this.lm_filebackup.get(0).get("backup").toString();
            String replace2 = this.lm_filebackup.get(0).get("backup").toString().replace(".tar.gz", ".json");
            this.s_json_backup = replace2;
            if (FileUtil.isExistFile(replace2)) {
                this.s_json_backup = FileUtil.readFile(this.s_json_backup);
                this.m_json_backup = (HashMap) new Gson().fromJson(this.s_json_backup, new TypeToken<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.14
                }.getType());
                this.tv_folder.setText(Uri.parse(this.lm_filebackup.get(0).get("file").toString()).getLastPathSegment());
                this.ln_backup.setVisibility(0);
                this.ln_switch.setVisibility(0);
                this.btn_restore.setVisibility(0);
                _setDialogUI();
                return;
            }
            this.ln_backup.setVisibility(4);
            this.ln_switch.setVisibility(8);
            this.btn_restore.setVisibility(8);
        }
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
            ScrestoreDialogFragmentActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(ScrestoreDialogFragmentActivity.this.s_command).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            ScrestoreDialogFragmentActivity.this.b_command = exec.isSuccess();
            ScrestoreDialogFragmentActivity.this.s_commandResult = ScrestoreDialogFragmentActivity$MyBackgroundAction$$ExternalSyntheticBackport0.m("\n", out);
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

    public void _setDialogUI() {
        this.b_ssaid = false;
        this.b_prop = false;
        this.b_command = false;
        if (this.m_json_backup.containsKey("NOTE")) {
            String obj = this.m_json_backup.get("NOTE").toString();
            this.s_note = obj;
            this.tv_note.setText(obj);
        } else {
            this.s_note = "";
            this.tv_note.setText("Catatan null");
        }
        if (this.m_json_backup.containsKey("DATE")) {
            this.tv_date.setText(this.m_json_backup.get("DATE").toString());
        } else {
            this.tv_date.setText("Date null");
        }
        if (this.m_json_backup.containsKey("settings_ssaid")) {
            this.s_restore_ssaid = this.m_json_backup.get("settings_ssaid").toString();
        } else {
            this.s_restore_ssaid = "false";
        }
        if (this.m_json_backup.containsKey("system.prop")) {
            this.s_restore_prop = this.m_json_backup.get("system.prop").toString();
        } else {
            this.s_restore_prop = "false";
        }
        if (this.m_json_backup.containsKey("SDK")) {
            this.s_restore_sdk = this.m_json_backup.get("SDK").toString();
        } else {
            this.s_restore_sdk = String.valueOf(Build.VERSION.SDK_INT);
        }
        if (this.m_json_backup.containsKey("MARK")) {
            if (this.m_json_backup.get("MARK").toString().equals("true")) {
                if (this.m_json_backup.containsKey("COLOR")) {
                    this.cv_backup.setCardBackgroundColor(Color.parseColor(this.m_json_backup.get("COLOR").toString()));
                }
            } else {
                this.cv_backup.setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(requireContext()));
            }
        }
        this.s_sdk = String.valueOf(Build.VERSION.SDK_INT);
        if (this.s_restore_prop.equals("true")) {
            this.tv_desc_prop.setText("Diijinkan :");
            this.switch_prop.setEnabled(true);
            this.switch_prop.setAlpha(1.0f);
        } else {
            this.tv_desc_prop.setText("Tidak diijinkan :");
            this.switch_prop.setEnabled(false);
            this.switch_prop.setAlpha(0.4f);
        }
        if (this.s_restore_ssaid.equals("true")) {
            this.tv_desc_ssaid.setText("Diijinkan :");
            this.switch_ssaid.setEnabled(true);
            this.switch_ssaid.setAlpha(1.0f);
        } else {
            this.tv_desc_ssaid.setText("Tidak diijinkan :");
            this.switch_ssaid.setEnabled(false);
            this.switch_ssaid.setAlpha(0.4f);
        }
        if (this.s_sdk.equals(this.s_restore_sdk)) {
            return;
        }
        this.tv_desc_ssaid.setText("Tidak diijinkan :");
        this.switch_ssaid.setEnabled(false);
        this.tv_desc_prop.setText("Tidak diijinkan :");
        this.switch_prop.setEnabled(false);
        this.switch_ssaid.setAlpha(0.4f);
        this.switch_prop.setAlpha(0.4f);
    }

    public void _onPrev() {
        this.n_loop = 0.0d;
        for (int i = 0; i < this.lm_filebackup.size(); i++) {
            if (this.lm_filebackup.get((int) this.n_loop).get("backup").toString().equals(this.s_restore_loc)) {
                try {
                    ((LinearLayoutManager) this.rv_1.getLayoutManager()).scrollToPositionWithOffset(((int) this.n_loop) - 1, 0);
                    _onChooseFile(Uri.parse(this.lm_filebackup.get(((int) this.n_loop) - 1).get("file").toString()).getLastPathSegment(), this.lm_filebackup.get(((int) this.n_loop) - 1).get("backup").toString());
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    SketchwareUtil.showMessage(getContext().getApplicationContext(), "Kamu sudah diurutan pertama");
                }
            }
            this.n_loop += 1.0d;
        }
    }

    public void _onNext() {
        this.n_loop = 0.0d;
        for (int i = 0; i < this.lm_filebackup.size(); i++) {
            if (this.lm_filebackup.get((int) this.n_loop).get("backup").toString().equals(this.s_restore_loc)) {
                try {
                    ((LinearLayoutManager) this.rv_1.getLayoutManager()).scrollToPositionWithOffset(((int) this.n_loop) + 1, 0);
                    _onChooseFile(Uri.parse(this.lm_filebackup.get(((int) this.n_loop) + 1).get("file").toString()).getLastPathSegment(), this.lm_filebackup.get(((int) this.n_loop) + 1).get("backup").toString());
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    SketchwareUtil.showMessage(getContext().getApplicationContext(), "Kamu sudah diurutan terakhir");
                }
            }
            this.n_loop += 1.0d;
        }
    }

    public void _onRestore() {
        this.ln_base_top.setVisibility(8);
        this.ln_proses.setVisibility(0);
        String concat = this.s_commandBase.concat("\nonrestore");
        this.s_scbase = concat;
        this.s_exe1 = "tar -zxf $thisfile -C / > /dev/null 2>&1\nif [[ $? -eq 0 ]]; then\n  echo \"Sukses restore $thisapp\"\nelse\n  echo \"Gagal restore $thisapp\"\nfi";
        this.s_exe2 = "tar -zxf $thispath/fileprop.tar.gz -C / > /dev/null 2>&1\nif [[ $? -eq 0 ]]; then\n  echo \"Sukses restore system.prop\"\nelse\n  echo  \"Gagal restore system.prop\"\nfi";
        this.s_exe3 = "tar -zxf $thispath/filessaid.tar.gz -C / > /dev/null 2>&1\nif [[ $? -eq 0 ]]; then\n  echo \"Sukses restore settings_ssaid\"\nelse\n  echo \"Gagal restore settings_ssaid\"\nfi";
        String replace = concat.replace("futhisapp", this.prefui.getString("backup_app_name", ""));
        this.s_scbase = replace;
        String replace2 = replace.replace("futhispackage", this.prefui.getString("backup_app_package", ""));
        this.s_scbase = replace2;
        String replace3 = replace2.replace("futhisfile", this.s_restore_loc);
        this.s_scbase = replace3;
        String replace4 = replace3.replace("#exe1", this.s_exe1);
        this.s_scbase = replace4;
        this.s_commandResult = "";
        this.s_exitCode = "";
        if (this.b_prop) {
            this.s_scbase = replace4.replace("#exe2", this.s_exe2);
        }
        if (this.b_ssaid) {
            this.s_scbase = this.s_scbase.replace("#exe3", this.s_exe3);
        }
        this.s_command = this.s_scbase;
        this.tv_proses_title.setText("Proses");
        this.tv_proses_backup.setText("Restore backup\n".concat(this.prefui.getString("backup_app_name", "")));
        this.ln_lottie.setVisibility(0);
        this.btn_oke.setVisibility(8);
        this.btn_open.setVisibility(8);
        _OnBackgroundAction();
        Runnable runnable = new Runnable() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.15
            @Override // java.lang.Runnable
            public void run() {
                if (ScrestoreDialogFragmentActivity.this.b_command) {
                    ScrestoreDialogFragmentActivity.this.OnRestore.removeCallbacks(ScrestoreDialogFragmentActivity.this.runnableOnRestore);
                    ScrestoreDialogFragmentActivity.this.b_command = false;
                    ScrestoreDialogFragmentActivity.this.tv_proses_title.setText("Selesai");
                    ScrestoreDialogFragmentActivity.this.tv_proses_backup.setText(ScrestoreDialogFragmentActivity.this.s_commandResult);
                    ScrestoreDialogFragmentActivity.this.ln_lottie.setVisibility(8);
                    ScrestoreDialogFragmentActivity.this.btn_oke.setVisibility(0);
                    ScrestoreDialogFragmentActivity.this.btn_open.setVisibility(0);
                    ScrestoreDialogFragmentActivity.this.preflast.edit().putString(ScrestoreDialogFragmentActivity.this.prefui.getString("backup_app_package", ""), ScrestoreDialogFragmentActivity.this.s_restore_loc).commit();
                    ScrestoreDialogFragmentActivity.this._onVibrate();
                    return;
                }
                ScrestoreDialogFragmentActivity.this.OnRestore.postDelayed(ScrestoreDialogFragmentActivity.this.runnableOnRestore, 100L);
            }
        };
        this.runnableOnRestore = runnable;
        this.OnRestore.postDelayed(runnable, 0L);
        if (this.b_ssaid || this.b_prop) {
            this.btn_oke.setText("Reboot");
            return;
        }
        this.btn_open.setVisibility(8);
        this.btn_oke.setText("Tutup");
    }

    public void _openApp(String str) {
        Intent launchIntentForPackage = getActivity().getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            startActivity(launchIntentForPackage);
            dismiss();
            return;
        }
        SketchwareUtil.showMessage(getContext().getApplicationContext(), "Aplikasi tidak ditemukan");
    }

    public void _onVibrate() {
        this.vibrate.vibrate(100L);
    }

    public void _showEditor() {
        this.ln_switch.setVisibility(8);
        this.ln_button.setVisibility(8);
        this.ln_editor.setVisibility(0);
        this.auto_editor.setText(this.s_note);
        this.mvc_01.setCardBackgroundColor(Color.parseColor("#FFE8DFF5"));
        this.mvc_02.setCardBackgroundColor(Color.parseColor("#FFFCE1E4"));
        this.mvc_03.setCardBackgroundColor(Color.parseColor("#FFFCF4DD"));
        this.mvc_04.setCardBackgroundColor(Color.parseColor("#FFDDEDEA"));
        this.mvc_05.setCardBackgroundColor(Color.parseColor("#FFDAEAF6"));
        this.mvc_06.setCardBackgroundColor(Color.parseColor("#FFC8B2EB"));
        this.mvc_07.setCardBackgroundColor(Color.parseColor("#FFFBADAB"));
        this.mvc_08.setCardBackgroundColor(Color.parseColor("#FFFADF7E"));
        this.mvc_09.setCardBackgroundColor(Color.parseColor("#FFBBD9C1"));
        this.mvc_10.setCardBackgroundColor(Color.parseColor("#FF80B7FF"));
        this.mvc_00.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "false");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFFFFFFF");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_01.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFE8DFF5");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_02.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFFCE1E4");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_03.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFFCF4DD");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_04.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFDDEDEA");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_05.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFDAEAF6");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_06.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFC8B2EB");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_07.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFFBADAB");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_08.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFFADF7E");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_09.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FFBBD9C1");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
        this.mvc_10.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("MARK", "true");
                ScrestoreDialogFragmentActivity.this.m_json_backup.put("COLOR", "#FF80B7FF");
                ScrestoreDialogFragmentActivity.this.cv_backup.setCardBackgroundColor(Color.parseColor(ScrestoreDialogFragmentActivity.this.m_json_backup.get("COLOR").toString()));
            }
        });
    }

    public void _saveEditor() {
        this.m_json_backup.put("NOTE", this.auto_editor.getText().toString());
        this.tv_note.setText(this.auto_editor.getText().toString());
        FileUtil.writeFile(this.s_restore_loc.replace(".tar.gz", ".json"), new Gson().toJson(this.m_json_backup));
        this.ln_switch.setVisibility(0);
        this.ln_button.setVisibility(0);
        this.ln_editor.setVisibility(8);
    }

    public void _hideEditor() {
        this.ln_switch.setVisibility(0);
        this.ln_button.setVisibility(0);
        this.ln_editor.setVisibility(8);
    }

    /* loaded from: classes5.dex */
    public class Rv_1Adapter extends RecyclerView.Adapter<ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_1Adapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = ScrestoreDialogFragmentActivity.this.getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d00c0, (ViewGroup) null);
            inflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            View view = viewHolder.itemView;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0263);
            view.setLayoutParams(new RecyclerView.LayoutParams(-2, -2));
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0533)).setText(Uri.parse(this._data.get(i).get("file").toString()).getLastPathSegment());
            ((MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03bd)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScrestoreDialogFragmentActivity.Rv_1Adapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScrestoreDialogFragmentActivity.this._onChooseFile(Uri.parse(Rv_1Adapter.this._data.get(i).get("file").toString()).getLastPathSegment(), Rv_1Adapter.this._data.get(i).get("backup").toString());
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
}
