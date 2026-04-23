package com.fufufu.katrina.backup;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import io.noties.markwon.Markwon;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
/* loaded from: classes5.dex */
public class SetelanFragmentActivity extends Fragment {
    private AlertDialog CONVERTDIALOG;
    private AlertDialog SHOWFOLDER;
    private FloatingActionButton _fab;
    private TextInputEditText auto_uname;
    private Button btn_address_belakang;
    private Button btn_address_depan;
    private Button btn_address_tengah;
    private MaterialButton btn_choose_folder;
    private MaterialButton btn_convert;
    private Button btn_margin;
    private Button btn_margin_down;
    private Button btn_margin_up;
    private Button btn_nama_dua;
    private Button btn_nama_tiga;
    private Button btn_save_address;
    private MaterialButton btn_save_bot;
    private Button btn_save_email;
    private Button btn_save_name;
    private Button btn_save_password;
    private Button btn_save_telepon;
    private Button btn_scale;
    private Button btn_scale_down;
    private Button btn_scale_up;
    private MaterialButton btn_uname;
    private MaterialCardView cv_1;
    private MaterialCardView cv_2;
    private MaterialCardView cv_3;
    private MaterialCardView cv_4;
    private MaterialCardView cv_5;
    private MaterialCardView cv_7;
    private MaterialCardView cv_9;
    private EditText et_chat_id;
    private EditText et_code_address;
    private EditText et_code_email;
    private EditText et_code_name;
    private EditText et_code_password;
    private EditText et_code_telepon;
    private EditText et_token_bot;
    private EditText et_total_address;
    private MaterialButtonToggleGroup group_address;
    private MaterialButtonToggleGroup group_margin;
    private MaterialButtonToggleGroup group_name;
    private MaterialButtonToggleGroup group_scale;
    private ImageView im_edit;
    private LinearLayout ln_012;
    private LinearLayout ln_013;
    private LinearLayout ln_014;
    private LinearLayout ln_015;
    private LinearLayout ln_016;
    private LinearLayout ln_017;
    private LinearLayout ln_018;
    private LinearLayout ln_019;
    private LinearLayout ln_020;
    private LinearLayout ln_026;
    private LinearLayout ln_028;
    private LinearLayout ln_029;
    private LinearLayout ln_03;
    private LinearLayout ln_030;
    private LinearLayout ln_031;
    private LinearLayout ln_032;
    private LinearLayout ln_04;
    private LinearLayout ln_05;
    private LinearLayout ln_06;
    private LinearLayout ln_07;
    private LinearLayout ln_08;
    private LinearLayout ln_09;
    private LinearLayout ln_33;
    private LinearLayout ln_36;
    private LinearLayout ln_base_island;
    private ExpandableLayout ln_bot_contain;
    private ExpandableLayout ln_changelog_contain;
    private ExpandableLayout ln_convert_contain;
    private LinearLayout ln_edit_uname;
    private ExpandableLayout ln_island_contain;
    private ExpandableLayout ln_random_contain;
    private LinearLayout ln_set_convert;
    private ScrollView ln_sett;
    private LinearLayout ln_sett_contain;
    private LinearLayout ln_sett_island;
    private LinearLayout ln_sett_random;
    private MyCONVERT myCONVERT;
    private MyREADFOLDER myREADFOLDER;
    private SharedPreferences pref;
    private SharedPreferences prefrandom;
    private SharedPreferences prefuser;
    private Runnable runnablefolderone;
    private Runnable runnablefoldertri;
    private Runnable runnablefoldertwo;
    private Switch switch_wall;
    private TextInputLayout til_uname;
    private TextView tv_010;
    private TextView tv_013;
    private TextView tv_015;
    private TextView tv_018;
    private TextView tv_022;
    private TextView tv_023;
    private TextView tv_024;
    private TextView tv_04;
    private TextView tv_05;
    private TextView tv_06;
    private TextView tv_09;
    private TextView tv_27;
    private TextView tv_about;
    private TextView tv_loc_code_name;
    private TextView tv_margin;
    private TextView tv_sampel_address;
    private TextView tv_sampel_email;
    private TextView tv_sampel_name;
    private TextView tv_sampel_password;
    private TextView tv_sampel_telepon;
    private TextView tv_scale;
    private TextView tv_title_address;
    private TextView tv_title_email;
    private TextView tv_title_name;
    private TextView tv_title_password;
    private TextView tv_title_set_about;
    private TextView tv_title_set_bot;
    private TextView tv_title_set_convert;
    private TextView tv_title_set_kill;
    private TextView tv_title_set_wallpaper;
    private TextView tv_title_sett_island;
    private TextView tv_title_sett_random;
    private TextView tv_title_telepon;
    private TextView tv_umail;
    private TextView tv_uname;
    private String s_location_address_code = "";
    private String s_code_address = "";
    private String s_total_address = "";
    private String s_location_name_code = "";
    private String s_code_name = "";
    private String s_total_name = "";
    private String s_code_email = "";
    private String s_front_address = "";
    private String s_result_address = "";
    private double n_total_address = 0.0d;
    private String s_insert = "";
    private String s_result_name = "";
    private double n_total_name = 0.0d;
    private String s_result_email = "";
    private String s_folder_picker = "";
    private double n_pos = 0.0d;
    private boolean b_folder_scan = false;
    private String s_targetpath = "";
    private String s_package = "";
    private double n = 0.0d;
    private double n_folder = 0.0d;
    private String s_loop_tar = "";
    private String s_loop_prop = "";
    private String s_loop_tar_name = "";
    private String s_loop_prop_name = "";
    private String s_folder = "";
    private String s_finaltarget = "";
    private String s_file_properties = "";
    private double n_properties = 0.0d;
    private String s_file_note = "";
    private String s_file_date = "";
    private String s_file_unix = "";
    private HashMap<String, Object> m_json = new HashMap<>();
    private String s_json = "";
    private String s_sort = "";
    private HashMap<String, Object> m_unix = new HashMap<>();
    private String s_path = "";
    private String s_ext = "";
    private String s_code_password = "";
    private HashMap<String, Object> m_request = new HashMap<>();
    private ArrayList<String> ls_random_address = new ArrayList<>();
    private ArrayList<String> ls_random_name = new ArrayList<>();
    private ArrayList<String> ls_folder_picker = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_folder_picker = new ArrayList<>();
    private ArrayList<String> ls_convert_backup = new ArrayList<>();
    private ArrayList<String> ls_file_properties = new ArrayList<>();
    private ArrayList<String> ls_unix_backup = new ArrayList<>();
    private ArrayList<String> ls_all_file_backup = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_unix_backup = new ArrayList<>();
    private Calendar c = Calendar.getInstance();
    private Intent i_kill = new Intent();
    int width_value = 0;
    int margin_value = 0;
    int new_width_value = 0;
    int new_margin_value = 0;
    private Handler folderone = new Handler();
    private Handler foldertwo = new Handler();
    private Handler foldertri = new Handler();

    public void _EXTRA() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d00bd, viewGroup, false);
        initialize(bundle, inflate);
        FirebaseApp.initializeApp(getContext());
        initializeLogic();
        return inflate;
    }

    private void initialize(Bundle bundle, View view) {
        this._fab = (FloatingActionButton) view.findViewById(R.id.admsoloraya_res_0x7f0a000f);
        this.ln_base_island = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0272);
        this.ln_018 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0252);
        this.ln_sett = (ScrollView) view.findViewById(R.id.admsoloraya_res_0x7f0a02dd);
        this.ln_019 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0253);
        this.ln_020 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0255);
        this.tv_umail = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a059d);
        this.tv_uname = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a059e);
        ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0211);
        this.im_edit = imageView;
        imageView.setVisibility(8);
        this.ln_sett_contain = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02de);
        this.ln_edit_uname = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0293);
        this.cv_1 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0143);
        this.cv_2 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0144);
        this.cv_3 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0145);
        this.cv_4 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0146);
        this.cv_7 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0148);
        this.cv_9 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0149);
        this.cv_5 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0147);
        this.til_uname = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a04a8);
        this.btn_uname = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0101);
        this.auto_uname = (TextInputEditText) view.findViewById(R.id.admsoloraya_res_0x7f0a0069);
        this.ln_sett_island = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02df);
        this.tv_title_sett_island = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0597);
        this.ln_island_contain = (ExpandableLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02ad);
        this.ln_08 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0261);
        this.tv_scale = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a054d);
        this.group_scale = (MaterialButtonToggleGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a01cb);
        this.tv_margin = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0522);
        this.group_margin = (MaterialButtonToggleGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a01c9);
        this.btn_scale_down = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00f5);
        this.btn_scale = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00f4);
        this.btn_scale_up = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00f6);
        this.btn_margin_down = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00c4);
        this.btn_margin = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00c3);
        this.btn_margin_up = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00c5);
        this.ln_sett_random = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02e0);
        this.tv_title_sett_random = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0598);
        this.ln_random_contain = (ExpandableLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02d0);
        this.ln_07 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0260);
        this.tv_title_address = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0586);
        this.tv_sampel_address = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0548);
        this.ln_03 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0259);
        this.ln_04 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a025d);
        this.tv_06 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04da);
        this.group_address = (MaterialButtonToggleGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a01c6);
        this.btn_save_address = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00eb);
        this.tv_title_name = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a058f);
        this.tv_sampel_name = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a054a);
        this.ln_06 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a025f);
        this.ln_05 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a025e);
        this.group_name = (MaterialButtonToggleGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a01ca);
        this.btn_save_name = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00ef);
        this.tv_title_email = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a058a);
        this.tv_sampel_email = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0549);
        this.ln_09 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0262);
        this.btn_save_email = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00ee);
        this.tv_title_password = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0590);
        this.tv_sampel_password = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a054b);
        this.ln_015 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024f);
        this.btn_save_password = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00f1);
        this.tv_title_telepon = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0599);
        this.tv_sampel_telepon = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a054c);
        this.ln_33 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0266);
        this.btn_save_telepon = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00f2);
        this.tv_04 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04d6);
        this.et_total_address = (EditText) view.findViewById(R.id.admsoloraya_res_0x7f0a0191);
        this.tv_05 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04d9);
        this.et_code_address = (EditText) view.findViewById(R.id.admsoloraya_res_0x7f0a0181);
        this.btn_address_depan = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a007e);
        this.btn_address_tengah = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a007f);
        this.btn_address_belakang = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a007d);
        this.tv_010 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04be);
        this.tv_loc_code_name = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a051e);
        this.tv_09 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04dd);
        this.et_code_name = (EditText) view.findViewById(R.id.admsoloraya_res_0x7f0a0183);
        this.btn_nama_dua = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00c8);
        this.btn_nama_tiga = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00c9);
        this.tv_013 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c1);
        this.et_code_email = (EditText) view.findViewById(R.id.admsoloraya_res_0x7f0a0182);
        this.tv_018 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c6);
        this.et_code_password = (EditText) view.findViewById(R.id.admsoloraya_res_0x7f0a0184);
        this.tv_27 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04e0);
        this.et_code_telepon = (EditText) view.findViewById(R.id.admsoloraya_res_0x7f0a0185);
        this.ln_set_convert = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02dc);
        this.tv_title_set_convert = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0594);
        this.ln_convert_contain = (ExpandableLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a028c);
        this.ln_012 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024c);
        this.tv_015 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c3);
        this.ln_013 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024d);
        this.ln_014 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024e);
        this.btn_choose_folder = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0087);
        this.btn_convert = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a008b);
        this.ln_016 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0250);
        this.tv_title_set_kill = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0595);
        this.ln_026 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0256);
        this.tv_title_set_bot = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0593);
        this.ln_bot_contain = (ExpandableLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0276);
        this.ln_028 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0257);
        this.tv_022 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04ca);
        this.ln_029 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0258);
        this.ln_030 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a025a);
        this.ln_031 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a025b);
        this.ln_032 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a025c);
        this.tv_023 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04cb);
        this.et_token_bot = (EditText) view.findViewById(R.id.admsoloraya_res_0x7f0a018f);
        this.tv_024 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04cc);
        this.et_chat_id = (EditText) view.findViewById(R.id.admsoloraya_res_0x7f0a0180);
        this.btn_save_bot = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00ec);
        this.ln_36 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0267);
        this.tv_title_set_wallpaper = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0596);
        this.switch_wall = (Switch) view.findViewById(R.id.admsoloraya_res_0x7f0a0473);
        this.ln_017 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0251);
        this.tv_title_set_about = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0592);
        this.ln_changelog_contain = (ExpandableLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0281);
        this.tv_about = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04e1);
        this.pref = getContext().getSharedPreferences("floating_island_pref", 0);
        this.prefrandom = getContext().getSharedPreferences("random_preferences", 0);
        this.prefuser = getContext().getSharedPreferences("user_preferences", 0);
        this.im_edit.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.ln_edit_uname.getVisibility() == 0) {
                    SetelanFragmentActivity.this.ln_edit_uname.setVisibility(8);
                } else {
                    SetelanFragmentActivity.this.ln_edit_uname.setVisibility(0);
                }
                SetelanFragmentActivity.this.auto_uname.setText(SetelanFragmentActivity.this.prefuser.getString("emanresu", ""));
            }
        });
        this.btn_uname.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this._onEditUname();
            }
        });
        this.auto_uname.addTextChangedListener(new TextWatcher() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.3
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                charSequence.toString();
                SetelanFragmentActivity.this.til_uname.setErrorEnabled(false);
            }
        });
        this.tv_title_sett_island.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.ln_island_contain.isExpanded()) {
                    SetelanFragmentActivity.this.ln_island_contain.collapse();
                } else {
                    SetelanFragmentActivity.this.ln_island_contain.expand();
                }
            }
        });
        this.btn_scale_down.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity.width_value = setelanFragmentActivity.pref.getInt("width_shape", 80);
                SetelanFragmentActivity setelanFragmentActivity2 = SetelanFragmentActivity.this;
                setelanFragmentActivity2.new_width_value = setelanFragmentActivity2.width_value - 1;
                SharedPreferences.Editor edit = SetelanFragmentActivity.this.pref.edit();
                edit.putInt("width_shape", SetelanFragmentActivity.this.new_width_value);
                edit.apply();
                SetelanFragmentActivity setelanFragmentActivity3 = SetelanFragmentActivity.this;
                setelanFragmentActivity3.width_value = setelanFragmentActivity3.pref.getInt("width_shape", 80);
                SetelanFragmentActivity.this.btn_scale.setText(String.valueOf(SetelanFragmentActivity.this.width_value));
            }
        });
        this.btn_scale_up.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity.width_value = setelanFragmentActivity.pref.getInt("width_shape", 80);
                SetelanFragmentActivity setelanFragmentActivity2 = SetelanFragmentActivity.this;
                setelanFragmentActivity2.new_width_value = setelanFragmentActivity2.width_value + 1;
                SharedPreferences.Editor edit = SetelanFragmentActivity.this.pref.edit();
                edit.putInt("width_shape", SetelanFragmentActivity.this.new_width_value);
                edit.apply();
                SetelanFragmentActivity setelanFragmentActivity3 = SetelanFragmentActivity.this;
                setelanFragmentActivity3.width_value = setelanFragmentActivity3.pref.getInt("width_shape", 80);
                SetelanFragmentActivity.this.btn_scale.setText(String.valueOf(SetelanFragmentActivity.this.width_value));
            }
        });
        this.btn_margin_down.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity.margin_value = setelanFragmentActivity.pref.getInt("margin_shape", 0);
                SetelanFragmentActivity setelanFragmentActivity2 = SetelanFragmentActivity.this;
                setelanFragmentActivity2.new_margin_value = setelanFragmentActivity2.margin_value - 1;
                SharedPreferences.Editor edit = SetelanFragmentActivity.this.pref.edit();
                edit.putInt("margin_shape", SetelanFragmentActivity.this.new_margin_value);
                edit.apply();
                SetelanFragmentActivity setelanFragmentActivity3 = SetelanFragmentActivity.this;
                setelanFragmentActivity3.margin_value = setelanFragmentActivity3.pref.getInt("margin_shape", 0);
                SetelanFragmentActivity.this.btn_margin.setText(String.valueOf(SetelanFragmentActivity.this.margin_value));
            }
        });
        this.btn_margin_up.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity.margin_value = setelanFragmentActivity.pref.getInt("margin_shape", 0);
                SetelanFragmentActivity setelanFragmentActivity2 = SetelanFragmentActivity.this;
                setelanFragmentActivity2.new_margin_value = setelanFragmentActivity2.margin_value + 1;
                SharedPreferences.Editor edit = SetelanFragmentActivity.this.pref.edit();
                edit.putInt("margin_shape", SetelanFragmentActivity.this.new_margin_value);
                edit.apply();
                SetelanFragmentActivity setelanFragmentActivity3 = SetelanFragmentActivity.this;
                setelanFragmentActivity3.margin_value = setelanFragmentActivity3.pref.getInt("margin_shape", 80);
                SetelanFragmentActivity.this.btn_margin.setText(String.valueOf(SetelanFragmentActivity.this.margin_value));
            }
        });
        this.tv_title_sett_random.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.ln_random_contain.isExpanded()) {
                    SetelanFragmentActivity.this.ln_random_contain.collapse();
                } else {
                    SetelanFragmentActivity.this.ln_random_contain.expand();
                }
            }
        });
        this.btn_save_address.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this._previewSaveAddress();
            }
        });
        this.btn_save_name.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this._previewSaveName();
            }
        });
        this.btn_save_email.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this._previewSaveEmail();
            }
        });
        this.btn_save_password.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this._previewSavePassword();
            }
        });
        this.btn_save_telepon.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this._previewSaveTelepon();
            }
        });
        this.et_total_address.addTextChangedListener(new TextWatcher() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.15
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SetelanFragmentActivity.this.s_total_address = charSequence.toString();
            }
        });
        this.et_code_address.addTextChangedListener(new TextWatcher() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.16
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SetelanFragmentActivity.this.s_code_address = charSequence.toString();
            }
        });
        this.btn_address_depan.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this.s_location_address_code = "d";
            }
        });
        this.btn_address_tengah.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this.s_location_address_code = "t";
            }
        });
        this.btn_address_belakang.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this.s_location_address_code = "b";
            }
        });
        this.tv_loc_code_name.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.tv_loc_code_name.getText().toString().equals("Depan")) {
                    SetelanFragmentActivity.this.s_location_name_code = "b";
                    SetelanFragmentActivity.this.tv_loc_code_name.setText("Belakang");
                    return;
                }
                SetelanFragmentActivity.this.s_location_name_code = "d";
                SetelanFragmentActivity.this.tv_loc_code_name.setText("Depan");
            }
        });
        this.et_code_name.addTextChangedListener(new TextWatcher() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.21
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SetelanFragmentActivity.this.s_code_name = charSequence.toString();
            }
        });
        this.btn_nama_dua.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this.s_total_name = ExifInterface.GPS_MEASUREMENT_2D;
            }
        });
        this.btn_nama_tiga.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this.s_total_name = ExifInterface.GPS_MEASUREMENT_3D;
            }
        });
        this.et_code_email.addTextChangedListener(new TextWatcher() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.24
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SetelanFragmentActivity.this.s_code_email = charSequence.toString();
            }
        });
        this.tv_title_set_convert.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.ln_convert_contain.isExpanded()) {
                    SetelanFragmentActivity.this.ln_convert_contain.collapse();
                } else {
                    SetelanFragmentActivity.this.ln_convert_contain.expand();
                }
            }
        });
        this.btn_choose_folder.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this._showFolderPicker();
            }
        });
        this.btn_convert.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.btn_choose_folder.getText().toString().equals("PILIH FOLDER")) {
                    SketchwareUtil.showMessage(SetelanFragmentActivity.this.getContext().getApplicationContext(), "Harap pilih folder");
                } else {
                    SetelanFragmentActivity.this._showDialogConvert();
                }
            }
        });
        this.tv_title_set_kill.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this.i_kill.setClass(SetelanFragmentActivity.this.getContext().getApplicationContext(), AppListAll.class);
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity.startActivity(setelanFragmentActivity.i_kill);
            }
        });
        this.tv_title_set_bot.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.ln_bot_contain.isExpanded()) {
                    SetelanFragmentActivity.this.ln_bot_contain.collapse();
                } else {
                    SetelanFragmentActivity.this.ln_bot_contain.expand();
                }
            }
        });
        this.btn_save_bot.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.30
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity._saveBotSetting(setelanFragmentActivity.et_token_bot.getText().toString(), SetelanFragmentActivity.this.et_chat_id.getText().toString());
            }
        });
        this.tv_title_set_wallpaper.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.31
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this.startActivity(new Intent(SetelanFragmentActivity.this.getContext().getApplicationContext(), WallActivity.class));
            }
        });
        this.switch_wall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.32
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SetelanFragmentActivity.this.prefrandom.edit().putString("random_wall", "true").commit();
                } else {
                    SetelanFragmentActivity.this.prefrandom.edit().putString("random_wall", "false").commit();
                }
            }
        });
        this.tv_title_set_about.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.33
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SetelanFragmentActivity.this.ln_changelog_contain.isExpanded()) {
                    SetelanFragmentActivity.this.ln_changelog_contain.collapse();
                } else {
                    SetelanFragmentActivity.this.ln_changelog_contain.expand();
                }
            }
        });
        this._fab.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.34
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SetelanFragmentActivity.this._openTelegramChanel();
            }
        });
    }

    private void initializeLogic() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.35
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                SetelanFragmentActivity.this.ln_island_contain.setExpansion(false);
                SetelanFragmentActivity.this.ln_island_contain.setDuration(350);
                SetelanFragmentActivity.this.ln_island_contain.setOrientation(1);
                SetelanFragmentActivity.this.ln_random_contain.setExpansion(false);
                SetelanFragmentActivity.this.ln_random_contain.setDuration(750);
                SetelanFragmentActivity.this.ln_random_contain.setOrientation(1);
                SetelanFragmentActivity.this.ln_convert_contain.setExpansion(false);
                SetelanFragmentActivity.this.ln_convert_contain.setDuration(350);
                SetelanFragmentActivity.this.ln_convert_contain.setOrientation(1);
                SetelanFragmentActivity.this.ln_changelog_contain.setExpansion(false);
                SetelanFragmentActivity.this.ln_changelog_contain.setDuration(350);
                SetelanFragmentActivity.this.ln_changelog_contain.setOrientation(1);
                SetelanFragmentActivity.this.ln_bot_contain.setExpansion(false);
                SetelanFragmentActivity.this.ln_bot_contain.setDuration(350);
                SetelanFragmentActivity.this.ln_bot_contain.setOrientation(1);
            }
        });
        _firstSetUI();
        this.group_margin.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.36
            @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
            public void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z) {
                if (z) {
                    materialButtonToggleGroup.clearChecked();
                }
            }
        });
        this.group_scale.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.37
            @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
            public void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i, boolean z) {
                if (z) {
                    materialButtonToggleGroup.clearChecked();
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    public void _firstSetUI() {
        String packageName = requireActivity().getPackageName();
        new File("/data/user/0/" + packageName + "/app_HOME/").mkdirs();
        int i = this.pref.getInt("width_shape", 80);
        this.width_value = i;
        this.btn_scale.setText(String.valueOf(i));
        int i2 = this.pref.getInt("margin_shape", 90);
        this.margin_value = i2;
        this.btn_margin.setText(String.valueOf(i2));
        this.group_address.setSingleSelection(true);
        this.group_name.setSingleSelection(true);
        this.ln_sett.setVerticalScrollBarEnabled(false);
        this.ln_island_contain.setVisibility(8);
        this.ln_random_contain.setVisibility(8);
        this.ln_edit_uname.setVisibility(8);
        TextView textView = this.tv_uname;
        this.prefuser.getString("emanresu", "");
        textView.setText("𝗮𝗱𝗺𝘀𝗼𝗹𝗼𝗿𝗮𝘆𝗮");
        TextView textView2 = this.tv_umail;
        this.prefuser.getString("liamresu", "");
        textView2.setText("Tambah tua doang, kaga tambah kaya adalah saya 😀");
        this._fab.setImageResource(R.drawable.admsoloraya_res_0x7f080158);
        if (this.prefrandom.getString("s_location_address_code", "").equals("")) {
            this.s_location_address_code = "b";
            this.prefrandom.edit().putString("s_location_address_code", this.s_location_address_code).commit();
            this.group_address.check(this.btn_address_belakang.getId());
        } else {
            String string = this.prefrandom.getString("s_location_address_code", "");
            this.s_location_address_code = string;
            if (string.equals("d")) {
                this.group_address.check(this.btn_address_depan.getId());
            } else if (this.s_location_address_code.equals("t")) {
                this.group_address.check(this.btn_address_tengah.getId());
            } else {
                this.group_address.check(this.btn_address_belakang.getId());
            }
        }
        if (this.prefrandom.getString("s_code_address", "").equals("")) {
            this.s_code_address = "";
            this.prefrandom.edit().putString("s_code_address", this.s_code_address).commit();
        } else {
            this.s_code_address = this.prefrandom.getString("s_code_address", "");
        }
        this.et_code_address.setText(this.s_code_address);
        if (this.prefrandom.getString("s_total_address", "").equals("0") || this.prefrandom.getString("s_total_address", "").equals("")) {
            this.s_total_address = "4";
            this.prefrandom.edit().putString("s_total_address", this.s_total_address).commit();
        } else {
            this.s_total_address = this.prefrandom.getString("s_total_address", "");
        }
        this.et_total_address.setText(this.s_total_address);
        if (this.prefrandom.getString("s_location_name_code", "").equals("")) {
            this.s_location_name_code = "d";
            this.prefrandom.edit().putString("s_location_name_code", this.s_location_name_code).commit();
            this.tv_loc_code_name.setText("Depan");
        } else {
            String string2 = this.prefrandom.getString("s_location_name_code", "");
            this.s_location_name_code = string2;
            if (string2.equals("d")) {
                this.tv_loc_code_name.setText("Depan");
            } else {
                this.tv_loc_code_name.setText("Belakang");
            }
        }
        if (this.prefrandom.getString("s_code_name", "").equals("")) {
            this.s_code_name = "";
            this.prefrandom.edit().putString("s_code_name", this.s_code_name).commit();
        } else {
            this.s_code_name = this.prefrandom.getString("s_code_name", "");
        }
        this.et_code_name.setText(this.s_code_name);
        if (this.prefrandom.getString("s_total_name", "").equals("")) {
            this.s_total_name = ExifInterface.GPS_MEASUREMENT_2D;
            this.prefrandom.edit().putString("s_total_name", this.s_total_name).commit();
            this.group_name.check(this.btn_nama_dua.getId());
        } else {
            String string3 = this.prefrandom.getString("s_total_name", "");
            this.s_total_name = string3;
            if (string3.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                this.group_name.check(this.btn_nama_dua.getId());
            } else {
                this.group_name.check(this.btn_nama_tiga.getId());
            }
        }
        if (this.prefrandom.getString("s_code_email", "").equals("")) {
            this.s_code_email = "";
            this.prefrandom.edit().putString("s_code_email", this.s_code_email).commit();
        } else {
            this.s_code_email = this.prefrandom.getString("s_code_email", "");
        }
        this.et_code_email.setText(this.s_code_email);
        if (this.prefrandom.getString("s_code_password", "").equals("")) {
            this.s_code_password = "Password belum disetting";
            this.prefrandom.edit().putString("s_code_password", this.s_code_password).commit();
        } else {
            this.s_code_password = this.prefrandom.getString("s_code_password", "");
        }
        this.tv_sampel_password.setText(this.s_code_password);
        this.et_code_password.setText("");
        if (this.prefuser.getString("token_bot", "").equals("")) {
            this.et_token_bot.setText("");
        } else {
            this.et_token_bot.setText(this.prefuser.getString("token_bot", ""));
            if (this.prefuser.getString("chat_id", "").equals("")) {
                this.et_chat_id.setText("");
            } else {
                this.et_chat_id.setText(this.prefuser.getString("chat_id", ""));
            }
        }
        this.ln_island_contain.setExpansion(false);
        this.ln_island_contain.setDuration(350);
        this.ln_island_contain.setOrientation(1);
        this.ln_random_contain.setExpansion(false);
        this.ln_random_contain.setDuration(750);
        this.ln_random_contain.setOrientation(1);
        this.ln_convert_contain.setExpansion(false);
        this.ln_convert_contain.setDuration(350);
        this.ln_convert_contain.setOrientation(1);
        this.ln_changelog_contain.setExpansion(false);
        this.ln_changelog_contain.setDuration(350);
        this.ln_changelog_contain.setOrientation(1);
        this.ln_bot_contain.setExpansion(false);
        this.ln_bot_contain.setDuration(350);
        this.ln_bot_contain.setOrientation(1);
        Markwon.builder(requireContext()).build().setMarkdown(this.tv_about, loadMarkdownFromAssets("xkatrina.md"));
        if (this.prefrandom.getString("random_wall", "").equals("false")) {
            this.switch_wall.setChecked(false);
        } else {
            this.switch_wall.setChecked(true);
        }
    }

    public void _previewSaveAddress() {
        this.prefrandom.edit().putString("s_location_address_code", this.s_location_address_code).commit();
        this.prefrandom.edit().putString("s_code_address", this.s_code_address).commit();
        if (this.s_total_address.equals("0")) {
            this.prefrandom.edit().putString("s_total_address", "4").commit();
        } else {
            this.prefrandom.edit().putString("s_total_address", this.s_total_address).commit();
        }
        if (this.ls_random_address.size() == 0) {
            try {
                this.ls_random_address = new ArrayList<>(Arrays.asList(SketchwareUtil.copyFromInputStream(getContext().getAssets().open("a.json")).split(",")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.s_front_address = new String[]{"Jalan", "Jln", "Jl", "Perumahan", "Perum", "Griya", "Toko", "Warung", "Rumah", "Gang", "Gg", "RT", "RW"}[new Random().nextInt(13)];
        this.s_result_address = "";
        this.n_total_address = Double.parseDouble(this.prefrandom.getString("s_total_address", ""));
        for (int i = 0; i < ((int) this.n_total_address); i++) {
            String str = this.s_result_address;
            ArrayList<String> arrayList = this.ls_random_address;
            this.s_result_address = str.concat(" ".concat(arrayList.get(SketchwareUtil.getRandom(0, arrayList.size()))));
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
        this.tv_sampel_address.setText(this.s_result_address);
    }

    public void _previewSaveName() {
        this.prefrandom.edit().putString("s_location_name_code", this.s_location_name_code).commit();
        this.prefrandom.edit().putString("s_code_name", this.s_code_name).commit();
        this.prefrandom.edit().putString("s_total_name", this.s_total_name).commit();
        if (this.ls_random_name.size() == 0) {
            try {
                this.ls_random_name = new ArrayList<>(Arrays.asList(SketchwareUtil.copyFromInputStream(getContext().getAssets().open("b.json")).split(",")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.s_result_name = "";
        this.n_total_name = Double.parseDouble(this.prefrandom.getString("s_total_name", ""));
        for (int i = 0; i < ((int) this.n_total_name); i++) {
            String str = this.s_result_name;
            ArrayList<String> arrayList = this.ls_random_name;
            this.s_result_name = str.concat(" ".concat(arrayList.get(SketchwareUtil.getRandom(0, arrayList.size()))));
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
        this.tv_sampel_name.setText(this.s_result_name);
    }

    public void _previewSaveEmail() {
        this.prefrandom.edit().putString("s_code_email", this.s_code_email).commit();
        if (this.ls_random_name.size() == 0) {
            try {
                this.ls_random_name = new ArrayList<>(Arrays.asList(SketchwareUtil.copyFromInputStream(getContext().getAssets().open("b.json")).split(",")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayList<String> arrayList = this.ls_random_name;
        String str = arrayList.get(SketchwareUtil.getRandom(0, arrayList.size()));
        this.s_result_email = str;
        String concat = str.concat(String.valueOf(SketchwareUtil.getRandom(99, 9999)));
        this.s_result_email = concat;
        String concat2 = concat.concat(this.prefrandom.getString("s_code_email", ""));
        this.s_result_email = concat2;
        this.tv_sampel_email.setText(concat2);
    }

    /* loaded from: classes5.dex */
    public class lv_folder_pickerAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public lv_folder_pickerAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = SetelanFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d0028, (ViewGroup) null);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a01f1);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04bd)).setText(Uri.parse(((HashMap) SetelanFragmentActivity.this.lm_folder_picker.get(i)).get("folder").toString()).getLastPathSegment());
            if (FileUtil.isDirectory(((HashMap) SetelanFragmentActivity.this.lm_folder_picker.get(i)).get("folder").toString())) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f080105);
            }
            if (FileUtil.isFile(((HashMap) SetelanFragmentActivity.this.lm_folder_picker.get(i)).get("folder").toString())) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f080103);
            }
            return view;
        }
    }

    /* loaded from: classes5.dex */
    public class lv_unix_backupAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public lv_unix_backupAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = SetelanFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d0060, (ViewGroup) null);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0225);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02f0);
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a05a1)).setText(((HashMap) SetelanFragmentActivity.this.lm_unix_backup.get(i)).get("apppackage").toString());
            try {
                imageView.setImageDrawable(SetelanFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(this._data.get(i).get("apppackage").toString()));
            } catch (PackageManager.NameNotFoundException unused) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0800dd);
            }
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.lv_unix_backupAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SetelanFragmentActivity.this.c = Calendar.getInstance();
                    SetelanFragmentActivity.this.s_targetpath = "/storage/emulated/0/XKatrina".concat(new SimpleDateFormat("HHmmss").format(SetelanFragmentActivity.this.c.getTime()).concat("/"));
                    SetelanFragmentActivity.this.s_package = ((HashMap) SetelanFragmentActivity.this.lm_unix_backup.get(i)).get("apppackage").toString();
                    SetelanFragmentActivity.this._onConvertProses();
                    SetelanFragmentActivity.this.CONVERTDIALOG.dismiss();
                }
            });
            return view;
        }
    }

    private void setAppFont(ViewGroup viewGroup, Typeface typeface) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                setAppFont((ViewGroup) childAt, typeface);
            } else if (childAt instanceof TextView) {
                ((TextView) childAt).setTypeface(typeface);
            }
        }
    }

    private String loadMarkdownFromAssets(String str) {
        try {
            InputStream open = getContext().getAssets().open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void _onTaskFolderPicker() {
        MyREADFOLDER myREADFOLDER = this.myREADFOLDER;
        if (myREADFOLDER != null && myREADFOLDER.isRunning) {
            this.myREADFOLDER.cancelREADFOLDERTask();
        }
        MyREADFOLDER myREADFOLDER2 = new MyREADFOLDER();
        this.myREADFOLDER = myREADFOLDER2;
        myREADFOLDER2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyREADFOLDER extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyREADFOLDER() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            SetelanFragmentActivity.this.lm_folder_picker.clear();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            FileUtil.listDir(SetelanFragmentActivity.this.s_folder_picker, SetelanFragmentActivity.this.ls_folder_picker);
            Collections.sort(SetelanFragmentActivity.this.ls_folder_picker, new Comparator<String>() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.MyREADFOLDER.1FileComparator
                @Override // java.util.Comparator
                public int compare(String str, String str2) {
                    if (str == str2) {
                        return 0;
                    }
                    if (FileUtil.isDirectory(str) && FileUtil.isFile(str2)) {
                        return -1;
                    }
                    if (FileUtil.isFile(str) && FileUtil.isDirectory(str2)) {
                        return 1;
                    }
                    return str.compareToIgnoreCase(str2);
                }
            });
            SetelanFragmentActivity.this.n_pos = 0.0d;
            for (int i = 0; i < SetelanFragmentActivity.this.ls_folder_picker.size(); i++) {
                HashMap hashMap = new HashMap();
                hashMap.put("folder", SetelanFragmentActivity.this.ls_folder_picker.get((int) SetelanFragmentActivity.this.n_pos));
                SetelanFragmentActivity.this.lm_folder_picker.add(hashMap);
                SetelanFragmentActivity.this.n_pos += 1.0d;
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            this.isRunning = false;
            SetelanFragmentActivity.this.b_folder_scan = true;
        }

        public void cancelREADFOLDERTask() {
            cancel(true);
        }
    }

    public void _showFolderPicker() {
        showSHOWFOLDER();
    }

    private void showSHOWFOLDER() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0026, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        final ListView listView = (ListView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0311);
        final TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04bd);
        Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0086);
        Button button2 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        Button button3 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0083);
        final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0297);
        final LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02b6);
        this.b_folder_scan = false;
        this.s_folder_picker = FileUtil.getExternalStorageDir();
        linearLayout.setVisibility(8);
        linearLayout2.setVisibility(0);
        _onTaskFolderPicker();
        Runnable runnable = new Runnable() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.38
            @Override // java.lang.Runnable
            public void run() {
                if (SetelanFragmentActivity.this.b_folder_scan) {
                    SetelanFragmentActivity.this.folderone.removeCallbacks(SetelanFragmentActivity.this.runnablefolderone);
                    SetelanFragmentActivity.this.b_folder_scan = false;
                    listView.setDivider(null);
                    listView.setDividerHeight(0);
                    ListView listView2 = listView;
                    SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                    listView2.setAdapter((ListAdapter) new lv_folder_pickerAdapter(setelanFragmentActivity.lm_folder_picker));
                    ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                    listView.setVerticalScrollBarEnabled(false);
                    textView.setText(SetelanFragmentActivity.this.s_folder_picker);
                    linearLayout.setVisibility(0);
                    linearLayout2.setVisibility(8);
                    return;
                }
                SetelanFragmentActivity.this.folderone.postDelayed(SetelanFragmentActivity.this.runnablefolderone, 100L);
            }
        };
        this.runnablefolderone = runnable;
        this.folderone.postDelayed(runnable, 0L);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.39
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (FileUtil.isDirectory((String) SetelanFragmentActivity.this.ls_folder_picker.get(i))) {
                    File file = new File((String) SetelanFragmentActivity.this.ls_folder_picker.get(i));
                    if (file.exists() && file.isDirectory()) {
                        File[] listFiles = file.listFiles();
                        if (listFiles != null && listFiles.length > 0) {
                            SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                            setelanFragmentActivity.s_folder_picker = (String) setelanFragmentActivity.ls_folder_picker.get(i);
                            linearLayout.setVisibility(8);
                            linearLayout2.setVisibility(0);
                            SetelanFragmentActivity.this._onTaskFolderPicker();
                            SetelanFragmentActivity setelanFragmentActivity2 = SetelanFragmentActivity.this;
                            final ListView listView2 = listView;
                            final TextView textView2 = textView;
                            final LinearLayout linearLayout3 = linearLayout;
                            final LinearLayout linearLayout4 = linearLayout2;
                            setelanFragmentActivity2.runnablefoldertwo = new Runnable() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.39.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (SetelanFragmentActivity.this.b_folder_scan) {
                                        SetelanFragmentActivity.this.foldertwo.removeCallbacks(SetelanFragmentActivity.this.runnablefoldertwo);
                                        SetelanFragmentActivity.this.b_folder_scan = false;
                                        listView2.setDivider(null);
                                        listView2.setDividerHeight(0);
                                        listView2.setAdapter((ListAdapter) new lv_folder_pickerAdapter(SetelanFragmentActivity.this.lm_folder_picker));
                                        ((BaseAdapter) listView2.getAdapter()).notifyDataSetChanged();
                                        listView2.setVerticalScrollBarEnabled(false);
                                        textView2.setText(SetelanFragmentActivity.this.s_folder_picker);
                                        linearLayout3.setVisibility(0);
                                        linearLayout4.setVisibility(8);
                                        return;
                                    }
                                    SetelanFragmentActivity.this.foldertwo.postDelayed(SetelanFragmentActivity.this.runnablefoldertwo, 100L);
                                }
                            };
                            SetelanFragmentActivity.this.foldertwo.postDelayed(SetelanFragmentActivity.this.runnablefoldertwo, 0L);
                            return;
                        }
                        SketchwareUtil.showMessage(SetelanFragmentActivity.this.getContext().getApplicationContext(), "Folder ini kosong");
                        return;
                    }
                    return;
                }
                SketchwareUtil.showMessage(SetelanFragmentActivity.this.getContext().getApplicationContext(), "Hanya bisa memilih folder");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.40
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SetelanFragmentActivity.this.s_folder_picker.equals("/storage/emulated/0")) {
                    SketchwareUtil.showMessage(SetelanFragmentActivity.this.getContext().getApplicationContext(), "Kamu sudah sampe di awal");
                    return;
                }
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity.s_folder_picker = setelanFragmentActivity.s_folder_picker.substring(0, (SetelanFragmentActivity.this.s_folder_picker.length() - Uri.parse(SetelanFragmentActivity.this.s_folder_picker).getLastPathSegment().length()) - 1);
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(0);
                SetelanFragmentActivity.this._onTaskFolderPicker();
                SetelanFragmentActivity setelanFragmentActivity2 = SetelanFragmentActivity.this;
                final ListView listView2 = listView;
                final TextView textView2 = textView;
                final LinearLayout linearLayout3 = linearLayout;
                final LinearLayout linearLayout4 = linearLayout2;
                setelanFragmentActivity2.runnablefoldertri = new Runnable() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.40.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (SetelanFragmentActivity.this.b_folder_scan) {
                            SetelanFragmentActivity.this.foldertri.removeCallbacks(SetelanFragmentActivity.this.runnablefoldertri);
                            SetelanFragmentActivity.this.b_folder_scan = false;
                            listView2.setDivider(null);
                            listView2.setDividerHeight(0);
                            listView2.setAdapter((ListAdapter) new lv_folder_pickerAdapter(SetelanFragmentActivity.this.lm_folder_picker));
                            ((BaseAdapter) listView2.getAdapter()).notifyDataSetChanged();
                            listView2.setVerticalScrollBarEnabled(false);
                            textView2.setText(SetelanFragmentActivity.this.s_folder_picker);
                            linearLayout3.setVisibility(0);
                            linearLayout4.setVisibility(8);
                            return;
                        }
                        SetelanFragmentActivity.this.foldertri.postDelayed(SetelanFragmentActivity.this.runnablefoldertri, 100L);
                    }
                };
                SetelanFragmentActivity.this.foldertri.postDelayed(SetelanFragmentActivity.this.runnablefoldertri, 0L);
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.41
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SetelanFragmentActivity.this.SHOWFOLDER.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.42
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SetelanFragmentActivity.this.btn_choose_folder.setText(SetelanFragmentActivity.this.s_folder_picker);
                SetelanFragmentActivity.this.SHOWFOLDER.dismiss();
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.SHOWFOLDER = create;
        create.show();
    }

    public void _onConvertSearch(String str, String str2) {
        File[] listFiles;
        File file = new File(str);
        if (!file.exists() || (listFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : listFiles) {
            if (file2.isFile() && file2.getName().endsWith(str2)) {
                this.ls_all_file_backup.add(file2.getAbsolutePath());
            } else if (file2.isDirectory()) {
                _onConvertSearch(file2.getAbsolutePath(), str2);
            }
        }
    }

    public void _onConvertProses() {
        MyCONVERT myCONVERT = this.myCONVERT;
        if (myCONVERT != null && myCONVERT.isRunning) {
            this.myCONVERT.cancelCONVERTTask();
        }
        MyCONVERT myCONVERT2 = new MyCONVERT();
        this.myCONVERT = myCONVERT2;
        myCONVERT2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyCONVERT extends AsyncTask<Void, Integer, Void> {
        Button btn_close;
        AlertDialog dialog;
        private boolean isRunning = false;
        LinearLayout ln_pbar;
        ProgressBar pbar_unix;
        TextView tv_message;
        TextView tv_size;
        TextView tv_total;

        public MyCONVERT() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(SetelanFragmentActivity.this.requireContext());
            View inflate = SetelanFragmentActivity.this.getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0037, (ViewGroup) null);
            this.pbar_unix = (ProgressBar) inflate.findViewById(R.id.admsoloraya_res_0x7f0a03f4);
            this.tv_size = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0556);
            this.tv_total = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a059a);
            this.tv_message = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0528);
            this.btn_close = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0088);
            this.ln_pbar = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02c4);
            materialAlertDialogBuilder.setView(inflate);
            materialAlertDialogBuilder.setCancelable(false);
            AlertDialog create = materialAlertDialogBuilder.create();
            this.dialog = create;
            create.show();
            this.tv_message.setText("Sedang mengkonversi...");
            this.btn_close.setVisibility(8);
            if (!FileUtil.isExistFile(SetelanFragmentActivity.this.s_targetpath)) {
                FileUtil.makeDir(SetelanFragmentActivity.this.s_targetpath);
            }
            if (FileUtil.isExistFile(SetelanFragmentActivity.this.s_targetpath.concat(SetelanFragmentActivity.this.s_package))) {
                return;
            }
            FileUtil.makeDir(SetelanFragmentActivity.this.s_targetpath.concat(SetelanFragmentActivity.this.s_package));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            SetelanFragmentActivity.this.ls_convert_backup.clear();
            Iterator it = SetelanFragmentActivity.this.ls_all_file_backup.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (str.contains(SetelanFragmentActivity.this.s_package)) {
                    SetelanFragmentActivity.this.ls_convert_backup.add(str);
                }
            }
            Collections.sort(SetelanFragmentActivity.this.ls_convert_backup);
            SetelanFragmentActivity.this.n = 0.0d;
            SetelanFragmentActivity.this.n_folder = 10001.0d;
            for (int i = 0; i < SetelanFragmentActivity.this.ls_convert_backup.size(); i++) {
                SetelanFragmentActivity setelanFragmentActivity = SetelanFragmentActivity.this;
                setelanFragmentActivity.s_loop_tar = (String) setelanFragmentActivity.ls_convert_backup.get((int) SetelanFragmentActivity.this.n);
                SetelanFragmentActivity setelanFragmentActivity2 = SetelanFragmentActivity.this;
                setelanFragmentActivity2.s_loop_prop = setelanFragmentActivity2.s_loop_tar.replace(".tar.gz", ".properties");
                SetelanFragmentActivity setelanFragmentActivity3 = SetelanFragmentActivity.this;
                setelanFragmentActivity3.s_loop_tar_name = Uri.parse(setelanFragmentActivity3.s_loop_tar).getLastPathSegment();
                SetelanFragmentActivity setelanFragmentActivity4 = SetelanFragmentActivity.this;
                setelanFragmentActivity4.s_loop_prop_name = setelanFragmentActivity4.s_loop_tar_name.replace(".tar.gz", ".json");
                SetelanFragmentActivity setelanFragmentActivity5 = SetelanFragmentActivity.this;
                setelanFragmentActivity5.s_folder = String.valueOf((long) setelanFragmentActivity5.n_folder);
                SetelanFragmentActivity setelanFragmentActivity6 = SetelanFragmentActivity.this;
                setelanFragmentActivity6.s_folder = setelanFragmentActivity6.s_folder.substring(2);
                SetelanFragmentActivity setelanFragmentActivity7 = SetelanFragmentActivity.this;
                setelanFragmentActivity7.s_finaltarget = setelanFragmentActivity7.s_targetpath.concat(SetelanFragmentActivity.this.s_package.concat("/".concat(SetelanFragmentActivity.this.s_folder.concat("/"))));
                FileUtil.makeDir(SetelanFragmentActivity.this.s_finaltarget);
                FileUtil.copyFile(SetelanFragmentActivity.this.s_loop_tar, SetelanFragmentActivity.this.s_finaltarget.concat(SetelanFragmentActivity.this.s_loop_tar_name));
                if (FileUtil.isExistFile(SetelanFragmentActivity.this.s_loop_prop)) {
                    SetelanFragmentActivity.this.ls_file_properties.clear();
                    SetelanFragmentActivity setelanFragmentActivity8 = SetelanFragmentActivity.this;
                    setelanFragmentActivity8.s_file_properties = FileUtil.readFile(setelanFragmentActivity8.s_loop_prop);
                    SetelanFragmentActivity.this.ls_file_properties = new ArrayList(Arrays.asList(SetelanFragmentActivity.this.s_file_properties.split("\n")));
                    SetelanFragmentActivity.this.n_properties = 0.0d;
                    for (int i2 = 0; i2 < SetelanFragmentActivity.this.ls_file_properties.size(); i2++) {
                        if (((String) SetelanFragmentActivity.this.ls_file_properties.get((int) SetelanFragmentActivity.this.n_properties)).contains("personal_note")) {
                            SetelanFragmentActivity setelanFragmentActivity9 = SetelanFragmentActivity.this;
                            setelanFragmentActivity9.s_file_note = (String) setelanFragmentActivity9.ls_file_properties.get((int) SetelanFragmentActivity.this.n_properties);
                            SetelanFragmentActivity setelanFragmentActivity10 = SetelanFragmentActivity.this;
                            setelanFragmentActivity10.s_file_note = setelanFragmentActivity10.s_file_note.replace("personal_note=", "");
                        }
                        if (((String) SetelanFragmentActivity.this.ls_file_properties.get((int) SetelanFragmentActivity.this.n_properties)).contains("GMT+")) {
                            SetelanFragmentActivity setelanFragmentActivity11 = SetelanFragmentActivity.this;
                            setelanFragmentActivity11.s_file_date = (String) setelanFragmentActivity11.ls_file_properties.get((int) SetelanFragmentActivity.this.n_properties);
                            SetelanFragmentActivity setelanFragmentActivity12 = SetelanFragmentActivity.this;
                            setelanFragmentActivity12.s_file_date = setelanFragmentActivity12.s_file_date.replace("#", "");
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEEE dd MMMM yyyy HH:mm:ss", new Locale("id", "ID"));
                            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
                            try {
                                SetelanFragmentActivity.this.s_file_date = simpleDateFormat2.format(simpleDateFormat.parse(SetelanFragmentActivity.this.s_file_date));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                SetelanFragmentActivity.this.s_file_unix = String.valueOf(new SimpleDateFormat("EEEE dd MMMM yyyy HH:mm:ss", new Locale("id", "ID")).parse(SetelanFragmentActivity.this.s_file_date).getTime() / 1000);
                            } catch (ParseException e2) {
                                e2.printStackTrace();
                            }
                        }
                        SetelanFragmentActivity.this.n_properties += 1.0d;
                    }
                    SetelanFragmentActivity.this.m_json.clear();
                    SetelanFragmentActivity.this.m_json = new HashMap();
                    SetelanFragmentActivity.this.m_json.put("NOTE", SetelanFragmentActivity.this.s_file_note);
                    SetelanFragmentActivity.this.m_json.put("DATE", SetelanFragmentActivity.this.s_file_date);
                    SetelanFragmentActivity.this.m_json.put("UNIX", SetelanFragmentActivity.this.s_file_unix);
                    SetelanFragmentActivity.this.m_json.put("system.prop", "false");
                    SetelanFragmentActivity.this.m_json.put("settings_ssaid", "false");
                    SetelanFragmentActivity.this.m_json.put("DEVICE", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("MODEL", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("PRODUCT", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("MANUFACTURER", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("BRAND", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("SDK", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("BOARD", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("BOOT", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("DISPLAY", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("FINGERPRINT", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("HARDWARE", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("BUILDID", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("HOST", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("USER", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("RELEASE", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("INCREMENTAL", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("USERAGENT", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("HTTPAGENT", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("RADIOVERSION", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("TIME", "Hasil Convert");
                    SetelanFragmentActivity.this.m_json.put("MARK", "false");
                    SetelanFragmentActivity.this.m_json.put("COLOR", "#FFFFFFFF");
                    SetelanFragmentActivity.this.m_json.put("ANDROIDID", "NOT ETERNAL");
                    SetelanFragmentActivity.this.m_json.put("BLUETHOOTNAME", "NOT ETERNAL");
                    SetelanFragmentActivity.this.m_json.put("DEVICENAME", "NOT ETERNAL");
                    SetelanFragmentActivity.this.m_json.put("IMEI", "NOT ETERNAL");
                    SetelanFragmentActivity.this.m_json.put("SERIAL", "NOT ETERNAL");
                    SetelanFragmentActivity.this.m_json.put("SERIAL2", "NOT ETERNAL");
                    SetelanFragmentActivity.this.s_json = new Gson().toJson(SetelanFragmentActivity.this.m_json);
                    FileUtil.writeFile(SetelanFragmentActivity.this.s_finaltarget.concat(SetelanFragmentActivity.this.s_loop_prop_name), SetelanFragmentActivity.this.s_json);
                }
                SetelanFragmentActivity.this.n += 1.0d;
                SetelanFragmentActivity.this.n_folder += 1.0d;
                if (SetelanFragmentActivity.this.n > 0.0d) {
                    publishProgress(Integer.valueOf((int) SetelanFragmentActivity.this.n));
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate((Object[]) numArr);
            this.pbar_unix.setMax(SetelanFragmentActivity.this.ls_convert_backup.size());
            this.pbar_unix.setProgress((int) SetelanFragmentActivity.this.n);
            this.tv_size.setText(String.valueOf((long) SetelanFragmentActivity.this.n));
            this.tv_total.setText(String.valueOf(SetelanFragmentActivity.this.ls_convert_backup.size()));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r4) {
            this.isRunning = false;
            this.btn_close.setVisibility(0);
            this.ln_pbar.setVisibility(8);
            this.tv_message.setText("Selesai mengkonversi ".concat(String.valueOf(SetelanFragmentActivity.this.ls_convert_backup.size()).concat("file backup\nDisimpan di ".concat(Uri.parse(SetelanFragmentActivity.this.s_targetpath).getLastPathSegment()))));
            this.btn_close.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.MyCONVERT.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MyCONVERT.this.dialog.dismiss();
                }
            });
        }

        public void cancelCONVERTTask() {
            cancel(true);
        }
    }

    public void _showDialogConvert() {
        showCONVERTDIALOG();
    }

    private void showCONVERTDIALOG() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d005f, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        ListView listView = (ListView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0319);
        Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0102);
        String charSequence = this.btn_choose_folder.getText().toString();
        this.s_path = charSequence;
        this.s_ext = ".tar.gz";
        _onConvertSearch(charSequence, ".tar.gz");
        Collections.sort(this.ls_unix_backup);
        this.n = 0.0d;
        for (int i = 0; i < this.ls_all_file_backup.size(); i++) {
            String lastPathSegment = Uri.parse(this.ls_all_file_backup.get((int) this.n)).getLastPathSegment();
            this.s_sort = lastPathSegment;
            String substring = lastPathSegment.substring(0, lastPathSegment.indexOf("-"));
            this.s_sort = substring;
            this.ls_unix_backup.add(substring);
            this.n += 1.0d;
        }
        HashSet hashSet = new HashSet(this.ls_unix_backup);
        this.ls_unix_backup.clear();
        ArrayList arrayList = new ArrayList(hashSet);
        this.lm_unix_backup.clear();
        this.n = 0.0d;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            this.m_unix = hashMap;
            hashMap.put("apppackage", arrayList.get((int) this.n));
            this.lm_unix_backup.add(this.m_unix);
            this.n += 1.0d;
        }
        listView.setDivider(null);
        listView.setDividerHeight(0);
        listView.setAdapter((ListAdapter) new lv_unix_backupAdapter(this.lm_unix_backup));
        ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
        listView.setVerticalScrollBarEnabled(false);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SetelanFragmentActivity.43
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SetelanFragmentActivity.this.CONVERTDIALOG.dismiss();
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.CONVERTDIALOG = create;
        create.show();
    }

    public void _previewSavePassword() {
        this.prefrandom.edit().putString("s_code_password", this.et_code_password.getText().toString()).commit();
        this.tv_sampel_password.setText(this.prefrandom.getString("s_code_password", ""));
        this.et_code_password.setText(this.prefrandom.getString("s_code_password", ""));
    }

    public void _onEditUname() {
        if (this.auto_uname.getText().toString().length() < 3) {
            this.til_uname.setErrorEnabled(true);
            this.til_uname.setError("Minimal 3 karakter");
            return;
        }
        this.prefuser.edit().putString("emanresu", this.auto_uname.getText().toString()).commit();
        this.auto_uname.clearFocus();
        this.ln_edit_uname.setVisibility(8);
        this.tv_uname.setText(this.prefuser.getString("emanresu", ""));
    }

    public void _saveBotSetting(String str, String str2) {
        if (str.length() >= 5 && str2.length() >= 5) {
            this.prefuser.edit().putString("token_bot", str).commit();
            this.prefuser.edit().putString("chat_id", str2).commit();
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Berhasil disimpan");
            return;
        }
        SketchwareUtil.showMessage(getContext().getApplicationContext(), "Ada yang salah");
    }

    public void _previewSaveTelepon() {
        String concat;
        this.prefrandom.edit().putString("s_code_telepon", this.et_code_telepon.getText().toString()).commit();
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
        this.tv_sampel_telepon.setText(concat.substring(0, 12));
        this.et_code_telepon.setText(this.prefrandom.getString("s_code_telepon", ""));
    }

    public void _openTelegramChanel() {
        openTelegramChannel(requireContext(), "https://t.me/fufufuxkatrina");
    }

    public static void openTelegramChannel(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Tidak ada aplikasi yang tersedia untuk membuka tautan. Tautan disalin ke clipboard.", 0).show();
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
            ClipData newPlainText = ClipData.newPlainText("Tautan Telegram", str);
            if (clipboardManager != null) {
                clipboardManager.setPrimaryClip(newPlainText);
                Toast.makeText(context, "Tautan telah disalin ke clipboard.", 0).show();
                return;
            }
            Toast.makeText(context, "Gagal menyalin tautan ke clipboard.", 0).show();
        }
    }
}
