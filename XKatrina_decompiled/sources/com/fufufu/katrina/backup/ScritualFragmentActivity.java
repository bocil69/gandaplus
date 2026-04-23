package com.fufufu.katrina.backup;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.fufufu.katrina.backup.RequestNetwork;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.elevation.SurfaceColors;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
/* loaded from: classes5.dex */
public class ScritualFragmentActivity extends Fragment {
    private AlertDialog EDITOR;
    private AlertDialog LOADING;
    private AlertDialog UPDATE;
    private FloatingActionButton _fab;
    private RequestNetwork.RequestListener _get_branch_all_request_listener;
    private RequestNetwork.RequestListener _get_branch_child1_request_listener;
    private RequestNetwork.RequestListener _get_branch_child2_request_listener;
    private AutoCompleteTextView auto_input_fp;
    private AutoCompleteTextView auto_input_model;
    private MaterialButton btn_apply;
    private Button btn_back;
    private MaterialButton btn_collapse_input;
    private Button btn_download;
    private Button btn_dump;
    private Button btn_dumpall;
    private MaterialButton btn_get_dump;
    private Button btn_prop;
    private MaterialCardView cv_3;
    private AutoCompleteTextView et_input_dump;
    private FloatingActionButton fab_editor;
    private ExtendedFloatingActionButton fab_random;
    private RequestNetwork get_branch_all;
    private RequestNetwork get_branch_child1;
    private RequestNetwork get_branch_child2;
    private KatrinaCEKMODULE katrinaCEKMODULE;
    private KatrinaCLEANERWIPE katrinaCLEANERWIPE;
    private KatrinaPROP katrinaPROP;
    private KatrinaPROPDUMP katrinaPROPDUMP;
    private KatrinaREADEXISTPROP katrinaREADEXISTPROP;
    private KatrinaREPLACEPROP katrinaREPLACEPROP;
    private ChipGroup ln_01;
    private ChipGroup ln_012;
    private LinearLayout ln_02;
    private LinearLayout ln_03;
    private LinearLayout ln_04;
    private LinearLayout ln_05;
    private ChipGroup ln_06;
    private ChipGroup ln_08;
    private LinearLayout ln_6;
    private LinearLayout ln_7;
    private LinearLayout ln_base;
    private LinearLayout ln_base_top;
    private LinearLayout ln_bottom;
    private FrameLayout ln_editor;
    private LinearLayout ln_input_dump;
    private ExpandableLayout ln_input_fp;
    private LinearLayout ln_input_prop;
    private LinearLayout ln_left;
    private LinearLayout ln_module_install;
    private LinearLayout ln_not_active;
    private LinearLayout ln_right;
    private LinearLayout ln_set_convert;
    private LinearLayout ln_setting_editor;
    private LinearLayout ln_title_input;
    private LottieAnimationView lottie1;
    private ListView lv_branch_all;
    private Chip m_dalvic;
    private Chip m_gms;
    private Chip m_nol;
    private Chip m_norestart;
    private Chip m_reboot;
    private Chip m_ssaid;
    private Chip m_timepick;
    private MaterialCardView materialcardview1;
    private MaterialCardView materialcardview2;
    private Chip mchip_modpes_end;
    private Chip mchip_modpes_start;
    private NestedScrollView nestscroll_1;
    private ProgressBar pbar_prop;
    private SharedPreferences pref;
    private SharedPreferences prefos;
    private SharedPreferences prefprop;
    private SharedPreferences prefrelease;
    private ProgressBar progressBar;
    private RecyclerView rv_1;
    private RecyclerView rv_2;
    private RecyclerView rv_3;
    private RecyclerView rv_4;
    private MaterialSwitch switch_editor;
    private TextInputLayout til_dump;
    private TextInputLayout til_input_fp;
    private TextInputLayout til_input_model;
    private TextView tv_02;
    private TextView tv_03;
    private TextView tv_05;
    private TextView tv_input_fingerprint;
    private TextView tv_not_active;
    private TextView tv_note;
    private TextView tv_prop_type;
    private TextView tv_response;
    private TextView tv_title;
    private String s_click_brand = "";
    private String s_click_model = "";
    private HashMap<String, Object> m_prop = new HashMap<>();
    private String s_json_prop = "";
    private String s_prop_final = "";
    private HashMap<String, Object> m_editor = new HashMap<>();
    private String s_command = "";
    private String s_commandResult = "";
    private String s_exitCode = "";
    private boolean b_command = false;
    private String s_propvalue = "";
    private String s_prop_device = "";
    private String s_prop_model = "";
    private String s_prop_product = "";
    private String s_prop_manufacturer = "";
    private String s_prop_brand = "";
    private String s_prop_boot = "";
    private String s_prop_buildid = "";
    private String s_prop_release = "";
    private String s_prop_incremental = "";
    private String s_prop_display = "";
    private String s_prop_board = "";
    private String s_prop_hardware = "";
    private String s_prop_host = "";
    private String s_prop_user = "";
    private String s_prop_fingerprint = "";
    private String s_systemprop = "";
    private HashMap<String, Object> m_random = new HashMap<>();
    private HashMap<String, Object> m_ori = new HashMap<>();
    private String s_defaultprop = "";
    private String s_prop_flavor = "";
    private String s_prop_dateutc = "";
    private String s_prop_description = "";
    private String s_prop_sdk = "";
    private String s_prop_date = "";
    private String s_prop_name = "";
    private String s_universal = "";
    private double n = 0.0d;
    private String s_rv = "";
    private String s_rv2 = "";
    private String extracleaner = "";
    private String extraprop = "";
    private String extrassaid = "";
    private String extrareset = "";
    private String extrareboot = "";
    private String extradalvic = "";
    private String extranorestart = "";
    private String extrawipe = "";
    private HashMap<String, Object> m_input = new HashMap<>();
    private String s_parsemodel = "";
    private String s_parsedevice = "";
    private String s_parserelease = "";
    private String s_parsebrand = "";
    private String s_parseproduct = "";
    private String s_parsebuildid = "";
    private String s_parseincremental = "";
    private String s_input_prop = "";
    private String s_add_prop = "";
    private String s_add_prop_base = "";
    private String s_input_json = "";
    private String s_json_old = "";
    private String s_data_old = "";
    private String s_json_result = "";
    private String s_data_new = "";
    private String s_custom_prop = "";
    private String s_response_result = "";
    private String s_prop_match = "";
    private String s_dump_head = "";
    private String s_dump_prop1 = "";
    private String s_dump_prop2 = "";
    private String s_dump_all = "";
    private String s_dump_raw = "";
    private String s_url_prop1 = "";
    private String s_url_prop2 = "";
    private String s_input_model = "";
    private String s_raw_model = "";
    private String s_prop_branch_all = "";
    private HashMap<String, Object> m_branch_all = new HashMap<>();
    private String s_url_prop = "";
    private String s_dump_model = "";
    private String s_feed_dump = "";
    private boolean b_update_force = false;
    private String s_filename = "";
    private String s_url = "";
    private String s_component = "";
    private String s_commandBase = "";
    private double n_os = 0.0d;
    private String s_os_plus = "";
    private String extramodpes = "";
    private String s_commandModpes = "";
    private HashMap<String, Object> m_modpes = new HashMap<>();
    private String s_json_pref = "";
    private double editor_pos = 0.0d;
    private String s_editor = "";
    private String s_dialog_editor = "";
    private String s_result_editor = "";
    private String s_title_editor = "";
    private String s_card_random = "";
    private String s_random_desc = "";
    private String s_random_release = "";
    private double n_random_editor = 0.0d;
    private String s_random_pref = "";
    private String s_random_fing = "";
    private String s_prop_title = "";
    private String s_prop_value = "";
    private String s_prop_new = "";
    private String s_commandGetProp = "";
    private boolean b_commandModule = false;
    private String s_commandModule = "";
    private String s_resultModule = "";
    private String s_post_fs_data = "";
    private String s_sensitiveprop = "";
    private String s_printprop = "";
    private HashMap<String, Object> m_systemprop = new HashMap<>();
    private String s_getkatrinaprop = "";
    private String s_fufufu_dump_online = "";
    private String s_remove_old = "";
    private ArrayList<HashMap<String, Object>> lm_json_model = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_brand = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_prop = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_editor = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_random = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_ori = new ArrayList<>();
    private ArrayList<String> ls_prop = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_input = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_old_prop = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_final = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_asset = new ArrayList<>();
    private ArrayList<String> ls_dump_prop = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_dump_prop = new ArrayList<>();
    private ArrayList<String> ls_branch_all = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_branch_all = new ArrayList<>();
    private ArrayList<String> ls_feed_dump = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_release = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_editor = new ArrayList<>();
    private ObjectAnimator oa1 = new ObjectAnimator();
    private ObjectAnimator oa2 = new ObjectAnimator();
    private ObjectAnimator oa3 = new ObjectAnimator();
    private ObjectAnimator oa4 = new ObjectAnimator();
    private Intent i = new Intent();

    public void _EXTRA() {
    }

    public void _onStartDownload() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d00ab, viewGroup, false);
        initialize(bundle, inflate);
        FirebaseApp.initializeApp(getContext());
        initializeLogic();
        return inflate;
    }

    private void initialize(Bundle bundle, View view) {
        this._fab = (FloatingActionButton) view.findViewById(R.id.admsoloraya_res_0x7f0a000f);
        this.lottie1 = (LottieAnimationView) view.findViewById(R.id.admsoloraya_res_0x7f0a0308);
        this.ln_setting_editor = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02e2);
        this.ln_not_active = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02bf);
        this.ln_editor = (FrameLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0294);
        this.ln_bottom = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0277);
        this.switch_editor = (MaterialSwitch) view.findViewById(R.id.admsoloraya_res_0x7f0a046d);
        this.ln_module_install = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02be);
        this.cv_3 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0145);
        this.tv_note = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0532);
        this.ln_set_convert = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02dc);
        this.tv_not_active = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0530);
        this.nestscroll_1 = (NestedScrollView) view.findViewById(R.id.admsoloraya_res_0x7f0a03cc);
        this.ln_6 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0269);
        this.ln_7 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a026a);
        this.rv_4 = (RecyclerView) view.findViewById(R.id.admsoloraya_res_0x7f0a040f);
        this.tv_05 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04d9);
        this.ln_01 = (ChipGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
        this.tv_02 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c8);
        this.ln_06 = (ChipGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a025f);
        this.tv_03 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04d0);
        this.ln_08 = (ChipGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a0261);
        this.mchip_modpes_start = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0367);
        this.mchip_modpes_end = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0366);
        this.m_timepick = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0338);
        this.m_gms = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a032d);
        this.ln_012 = (ChipGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a024c);
        this.m_ssaid = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0337);
        this.m_nol = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0332);
        this.m_reboot = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0336);
        this.m_dalvic = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0328);
        this.m_norestart = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0333);
        this.fab_random = (ExtendedFloatingActionButton) view.findViewById(R.id.admsoloraya_res_0x7f0a019b);
        this.fab_editor = (FloatingActionButton) view.findViewById(R.id.admsoloraya_res_0x7f0a019a);
        this.ln_base_top = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0275);
        this.ln_base = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0270);
        this.ln_title_input = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02eb);
        this.ln_input_fp = (ExpandableLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02aa);
        this.ln_05 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a025e);
        this.ln_input_dump = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02a9);
        this.ln_input_prop = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02ab);
        this.btn_prop = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00d0);
        this.btn_dump = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a008f);
        this.btn_dumpall = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a0090);
        this.til_dump = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a04a0);
        this.btn_get_dump = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00ba);
        this.et_input_dump = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0188);
        this.tv_input_fingerprint = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0518);
        this.btn_collapse_input = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a008a);
        this.ln_04 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a025d);
        this.ln_03 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0259);
        this.btn_apply = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0082);
        this.til_input_model = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a04a3);
        this.til_input_fp = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a04a2);
        this.auto_input_model = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0064);
        this.auto_input_fp = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0063);
        this.ln_left = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02b1);
        this.ln_right = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02d5);
        this.ln_02 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0254);
        this.btn_back = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a0083);
        this.tv_response = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0544);
        this.pbar_prop = (ProgressBar) view.findViewById(R.id.admsoloraya_res_0x7f0a03f1);
        this.tv_prop_type = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a053c);
        this.materialcardview1 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0354);
        this.materialcardview2 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0355);
        this.tv_title = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0571);
        this.lv_branch_all = (ListView) view.findViewById(R.id.admsoloraya_res_0x7f0a030f);
        this.rv_1 = (RecyclerView) view.findViewById(R.id.admsoloraya_res_0x7f0a040c);
        this.rv_2 = (RecyclerView) view.findViewById(R.id.admsoloraya_res_0x7f0a040d);
        this.rv_3 = (RecyclerView) view.findViewById(R.id.admsoloraya_res_0x7f0a040e);
        this.pref = getContext().getSharedPreferences("preferences_editor", 0);
        this.prefprop = getContext().getSharedPreferences("input_fp_preferences", 0);
        this.get_branch_all = new RequestNetwork((Activity) getContext());
        this.get_branch_child1 = new RequestNetwork((Activity) getContext());
        this.get_branch_child2 = new RequestNetwork((Activity) getContext());
        this.prefrelease = getContext().getSharedPreferences("release_preference", 0);
        this.prefos = getContext().getSharedPreferences("os_preferences", 0);
        this.switch_editor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScritualFragmentActivity.this.pref.edit().putString("show_fragment", "show_editor").commit();
                } else {
                    ScritualFragmentActivity.this.pref.edit().putString("show_fragment", "hide_editor").commit();
                }
                ScritualFragmentActivity.this._onCheckSwitchSetting();
            }
        });
        this.mchip_modpes_start.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_modpes_start", "true").commit();
                } else {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_modpes_start", "false").commit();
                }
            }
        });
        this.mchip_modpes_end.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_modpes_end", "true").commit();
                } else {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_modpes_end", "false").commit();
                }
            }
        });
        this.m_timepick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_timepick", "true").commit();
                } else {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_timepick", "false").commit();
                }
            }
        });
        this.m_gms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_gms", "true").commit();
                } else {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_gms", "false").commit();
                }
            }
        });
        this.m_ssaid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_ssaid", "true").commit();
                } else {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_ssaid", "false").commit();
                }
            }
        });
        this.m_nol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.7
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_nol", "true").commit();
                } else {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_nol", "false").commit();
                }
            }
        });
        this.m_reboot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.8
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_reboot", "true").commit();
                } else {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_reboot", "false").commit();
                }
            }
        });
        this.m_dalvic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.9
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_dalvic", "true").commit();
                } else {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_dalvic", "false").commit();
                }
            }
        });
        this.m_norestart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.10
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_norestart", "true").commit();
                } else {
                    ScritualFragmentActivity.this.pref.edit().putString("chip_editor_norestart", "false").commit();
                }
            }
        });
        this.fab_random.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScritualFragmentActivity.this._onRandomAllEditor();
            }
        });
        this.fab_editor.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScritualFragmentActivity.this._onStartRitualEditor();
            }
        });
        this.btn_prop.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScritualFragmentActivity.this._setPropType("Termux Prop");
            }
        });
        this.btn_dump.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScritualFragmentActivity.this._setPropType("Android Dump");
            }
        });
        this.btn_dumpall.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScritualFragmentActivity.this._setPropType("Online Dump");
            }
        });
        this.btn_get_dump.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScritualFragmentActivity.this._onGetDumpOnline();
            }
        });
        this.btn_collapse_input.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScritualFragmentActivity.this._onCollapseFingerInput("clickbutton");
            }
        });
        this.btn_apply.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScritualFragmentActivity.this._onConvertProp();
            }
        });
        this.auto_input_fp.addTextChangedListener(new TextWatcher() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.19
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                charSequence.toString();
                ScritualFragmentActivity.this.til_input_fp.setError(null);
            }
        });
        this.btn_back.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScritualFragmentActivity.this._onBackButton();
            }
        });
        this._fab.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScritualFragmentActivity.this._onGetAllProp();
            }
        });
        this.oa1.addListener(new Animator.AnimatorListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.22
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                ScritualFragmentActivity.this.rv_3.setVisibility(0);
                ScritualFragmentActivity.this._fab.setVisibility(0);
            }
        });
        this.oa3.addListener(new Animator.AnimatorListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.23
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ScritualFragmentActivity.this.rv_3.setVisibility(8);
                ScritualFragmentActivity.this._fab.setVisibility(8);
            }
        });
        this._get_branch_all_request_listener = new RequestNetwork.RequestListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.24
            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> hashMap) {
                if (!str2.contains("Sign in · GitLab")) {
                    ScritualFragmentActivity.this.ls_branch_all.clear();
                    ScritualFragmentActivity.this.lm_branch_all.clear();
                    ScritualFragmentActivity.this.s_response_result = ScritualFragmentActivity.removeLinesList(str2);
                    ScritualFragmentActivity scritualFragmentActivity = ScritualFragmentActivity.this;
                    scritualFragmentActivity.s_response_result = scritualFragmentActivity.s_response_result.replaceAll(".*data-branch-name=\"", "");
                    ScritualFragmentActivity scritualFragmentActivity2 = ScritualFragmentActivity.this;
                    scritualFragmentActivity2.s_response_result = scritualFragmentActivity2.s_response_result.replaceAll(".*data-default-branch=\"", "");
                    ScritualFragmentActivity scritualFragmentActivity3 = ScritualFragmentActivity.this;
                    scritualFragmentActivity3.s_response_result = scritualFragmentActivity3.s_response_result.replaceAll("\".*", "");
                    ScritualFragmentActivity.this.ls_branch_all = new ArrayList(Arrays.asList(ScritualFragmentActivity.this.s_response_result.split("\n")));
                    ScritualFragmentActivity.this.n = 0.0d;
                    for (int i = 0; i < ScritualFragmentActivity.this.ls_branch_all.size(); i++) {
                        ScritualFragmentActivity scritualFragmentActivity4 = ScritualFragmentActivity.this;
                        scritualFragmentActivity4.s_prop_branch_all = (String) scritualFragmentActivity4.ls_branch_all.get((int) ScritualFragmentActivity.this.n);
                        String[] split = ScritualFragmentActivity.this.s_prop_branch_all.split("-", 6);
                        String str3 = split[0];
                        String str4 = split[1];
                        String str5 = split[2];
                        String str6 = split[3];
                        String str7 = split[4];
                        String str8 = split[5];
                        ScritualFragmentActivity.this.m_branch_all = new HashMap();
                        ScritualFragmentActivity.this.m_branch_all.put("device_url1", ScritualFragmentActivity.this.s_dump_head.concat(ScritualFragmentActivity.this.s_raw_model.concat(ScritualFragmentActivity.this.s_prop_branch_all.concat(ScritualFragmentActivity.this.s_dump_prop2))));
                        ScritualFragmentActivity.this.m_branch_all.put("device_url2", ScritualFragmentActivity.this.s_dump_head.concat(ScritualFragmentActivity.this.s_raw_model.concat(ScritualFragmentActivity.this.s_prop_branch_all.concat(ScritualFragmentActivity.this.s_dump_prop1))));
                        ScritualFragmentActivity.this.m_branch_all.put("device", str3);
                        ScritualFragmentActivity.this.m_branch_all.put("release", str5);
                        ScritualFragmentActivity.this.m_branch_all.put("buildid", str6);
                        ScritualFragmentActivity.this.m_branch_all.put("incremental", str7);
                        ScritualFragmentActivity.this.lm_branch_all.add(ScritualFragmentActivity.this.m_branch_all);
                        ScritualFragmentActivity.this.n += 1.0d;
                    }
                    SketchwareUtil.sortListMap(ScritualFragmentActivity.this.lm_branch_all, "release", false, true);
                    ListView listView = ScritualFragmentActivity.this.lv_branch_all;
                    ScritualFragmentActivity scritualFragmentActivity5 = ScritualFragmentActivity.this;
                    listView.setAdapter((ListAdapter) new Lv_branch_allAdapter(scritualFragmentActivity5.lm_branch_all));
                    ScritualFragmentActivity.this._onLoadingOnline("clickafterget");
                    return;
                }
                ScritualFragmentActivity.this._onLoadingOnline("clicknotfound");
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
                ScritualFragmentActivity.this._onResponseError("Tidak ada koneksi internet");
            }
        };
        this._get_branch_child1_request_listener = new RequestNetwork.RequestListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.25
            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> hashMap) {
                if (!str2.contains("Not Found")) {
                    ScritualFragmentActivity.this.s_response_result = str2;
                    ScritualFragmentActivity.this._getPropResult();
                    return;
                }
                ScritualFragmentActivity.this.get_branch_child2.startRequestNetwork("GET", ScritualFragmentActivity.this.s_url_prop2, "a", ScritualFragmentActivity.this._get_branch_child2_request_listener);
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
                ScritualFragmentActivity.this._onResponseError(str2);
            }
        };
        this._get_branch_child2_request_listener = new RequestNetwork.RequestListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.26
            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> hashMap) {
                if (!str2.contains("Not Found")) {
                    ScritualFragmentActivity.this.s_response_result = str2;
                    ScritualFragmentActivity.this._getPropResult();
                    return;
                }
                ScritualFragmentActivity.this.tv_response.setVisibility(0);
                ScritualFragmentActivity.this.tv_response.setText("fufufu tidak dapat menemukan prop");
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
                ScritualFragmentActivity.this._onResponseError(str2);
            }
        };
    }

    private void initializeLogic() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.27
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
            }
        });
        this.ln_not_active.setVisibility(8);
        this.ln_setting_editor.setVisibility(8);
        this.ln_editor.setVisibility(8);
        this.ln_bottom.setVisibility(8);
        _onCheckModule();
        _setFirstUI();
        new Handler().postDelayed(new Runnable() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.28
            @Override // java.lang.Runnable
            public void run() {
                if (ScritualFragmentActivity.this.prefrelease.getString("release", "").equals("")) {
                    return;
                }
                ScritualFragmentActivity.this.lm_release = (ArrayList) new Gson().fromJson(ScritualFragmentActivity.this.prefrelease.getString("release", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.28.1
                }.getType());
                ScritualFragmentActivity.this._showDialogUpdate();
            }
        }, 3000L);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
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

    private static String removeLines(String str) {
        String[] split;
        StringBuilder sb = new StringBuilder();
        for (String str2 : str.split("\n")) {
            if (((!str2.contains(".release=") && !str2.contains(".model=") && !str2.contains(".hardware=") && !str2.contains(".date=") && !str2.contains(".date.utc=") && !str2.contains(".fingerprint=") && !str2.contains(".build.id=") && !str2.contains(".incremental=") && !str2.contains(".bootloader=") && !str2.contains(".description=") && !str2.contains(".display.id=") && !str2.contains(".flavor=") && !str2.contains(".host=") && !str2.contains(".product=") && !str2.contains(".user=") && !str2.contains(".board=") && !str2.contains(".brand=") && !str2.contains(".device=") && !str2.contains(".manufacturer=") && !str2.contains(".name=")) || str2.contains(".vendor") || str2.contains("bt.name")) ? false : true) {
                sb.append(str2);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String removeLinesList(String str) {
        String[] split;
        StringBuilder sb = new StringBuilder();
        for (String str2 : str.split("\n")) {
            if (str2.contains("data-default-branch=") || str2.contains("data-branch-name=")) {
                sb.append(str2);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private static String generateRandomDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        calendar.setTimeZone(TimeZone.getTimeZone("KST"));
        calendar.set(getRandomNumberInRange(2000, 2023), getRandomNumberInRange(0, 11), getRandomNumberInRange(1, 28), getRandomNumberInRange(0, 23), getRandomNumberInRange(0, 59), getRandomNumberInRange(0, 59));
        return simpleDateFormat.format(calendar.getTime());
    }

    public boolean jsonIsValid(String str) {
        new HashMap();
        new ArrayList();
        try {
            try {
                HashMap hashMap = (HashMap) new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.29
                }.getType());
                return true;
            } catch (Exception unused) {
                ArrayList arrayList = (ArrayList) new Gson().fromJson(str, new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.30
                }.getType());
                return true;
            }
        } catch (Exception unused2) {
            return false;
        }
    }

    public void _setFirstUI() {
        this.m_timepick.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.m_gms.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.m_ssaid.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.m_nol.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.m_reboot.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.m_dalvic.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.m_norestart.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.mchip_modpes_start.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this.mchip_modpes_end.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf"), 0);
        this._fab.setImageResource(R.drawable.admsoloraya_res_0x7f080102);
        this.fab_editor.setImageResource(R.drawable.admsoloraya_res_0x7f080102);
        this.ln_input_fp.setExpansion(false);
        this.ln_input_fp.setDuration(350);
        this.ln_input_fp.setOrientation(1);
        this.btn_collapse_input.setRotation(0.0f);
        this.btn_back.setVisibility(8);
        this.rv_2.setVisibility(8);
        this.rv_3.setVisibility(8);
        this._fab.setVisibility(8);
        this.pbar_prop.setVisibility(8);
        this.tv_response.setVisibility(8);
        this.ln_input_prop.setVisibility(0);
        this.ln_input_dump.setVisibility(8);
        this.lv_branch_all.setVisibility(8);
        this.tv_title.setText("BRAND");
        this.auto_input_model.setSingleLine(true);
        if (this.prefos.getString("OSPLUS", "").equals("")) {
            this.prefos.edit().putString("OSPLUS", "15").commit();
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
            String replace61 = replace60.replace("ⅱ", "");
            this.s_commandBase = replace61;
            this.s_commandBase = replace61.replace("futhispackage", getContext().getApplicationContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.s_systemprop = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/system.prop"));
        this.s_sensitiveprop = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/post-fs-data.sh"));
        this.s_defaultprop = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/default.prop"));
        File filesDir = getContext().getFilesDir();
        this.s_add_prop = filesDir.getPath() + "/custom_fp.json";
        this.s_add_prop_base = "[{\"MEREK\":\"KATRINA\", \"DATA\": []}]";
        this.s_dump_head = "https://dumps.tadiphone.dev/dumps/";
        this.s_dump_prop2 = "/system/system/build.prop?ref_type=heads";
        this.s_dump_prop1 = "/system/build.prop?ref_type=heads";
        this.s_dump_all = "/-/branches/all";
        this.s_dump_raw = "/-/raw/";
        this.s_fufufu_dump_online = FileUtil.getExternalStorageDir().concat("/fufufu_dump_online.txt");
        if (!FileUtil.isExistFile(this.s_add_prop)) {
            FileUtil.writeFile(this.s_add_prop, this.s_add_prop_base);
        }
        this.et_input_dump.setSingleLine(true);
        this.et_input_dump.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.31
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 2) {
                    ScritualFragmentActivity.this._onGetDumpOnline();
                    return true;
                }
                return false;
            }
        });
    }

    public void _onLoadBrand() {
        KatrinaPROP katrinaPROP = this.katrinaPROP;
        if (katrinaPROP == null || katrinaPROP.getStatus() == AsyncTask.Status.FINISHED) {
            KatrinaPROP katrinaPROP2 = new KatrinaPROP();
            this.katrinaPROP = katrinaPROP2;
            katrinaPROP2.execute(new Void[0]);
        } else if (this.katrinaPROP.getStatus() == AsyncTask.Status.PENDING) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Proses KatrinaPROP masih pending");
        }
    }

    /* loaded from: classes5.dex */
    public class KatrinaPROP extends AsyncTask<Void, Void, Void> {
        public KatrinaPROP() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            ScritualFragmentActivity.this.rv_1.setVisibility(0);
            ScritualFragmentActivity.this.rv_2.setVisibility(8);
            ScritualFragmentActivity.this.rv_3.setVisibility(8);
            ScritualFragmentActivity.this._fab.setVisibility(8);
            ScritualFragmentActivity.this.pbar_prop.setVisibility(0);
            ScritualFragmentActivity.this.btn_prop.setEnabled(false);
            ScritualFragmentActivity.this.btn_dump.setEnabled(false);
            ScritualFragmentActivity.this.btn_dumpall.setEnabled(false);
            ScritualFragmentActivity.this.tv_title.setText("BRAND");
            ScritualFragmentActivity.this.lm_json_brand.clear();
            if (FileUtil.isExistFile(ScritualFragmentActivity.this.s_add_prop)) {
                ScritualFragmentActivity scritualFragmentActivity = ScritualFragmentActivity.this;
                scritualFragmentActivity.s_custom_prop = FileUtil.readFile(scritualFragmentActivity.s_add_prop);
                ScritualFragmentActivity scritualFragmentActivity2 = ScritualFragmentActivity.this;
                if (scritualFragmentActivity2.jsonIsValid(scritualFragmentActivity2.s_custom_prop)) {
                    ScritualFragmentActivity.this.lm_json_brand = (ArrayList) new Gson().fromJson(ScritualFragmentActivity.this.s_custom_prop, new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.KatrinaPROP.1
                    }.getType());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            while (!isCancelled()) {
                try {
                    InputStream open = ScritualFragmentActivity.this.getContext().getAssets().open("prop.json");
                    ScritualFragmentActivity.this.lm_json_asset = (ArrayList) new Gson().fromJson(SketchwareUtil.copyFromInputStream(open), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.KatrinaPROP.2
                    }.getType());
                    ScritualFragmentActivity.this.lm_json_brand.addAll(ScritualFragmentActivity.this.lm_json_asset);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!isCancelled()) {
                    return null;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r4) {
            RecyclerView recyclerView = ScritualFragmentActivity.this.rv_1;
            ScritualFragmentActivity scritualFragmentActivity = ScritualFragmentActivity.this;
            recyclerView.setAdapter(new Rv_1Adapter(scritualFragmentActivity.lm_json_brand));
            ScritualFragmentActivity.this.rv_1.setLayoutManager(new LinearLayoutManager(ScritualFragmentActivity.this.getContext()));
            ScritualFragmentActivity.this.btn_back.setVisibility(8);
            ScritualFragmentActivity.this.btn_prop.setEnabled(true);
            ScritualFragmentActivity.this.btn_dump.setEnabled(true);
            ScritualFragmentActivity.this.btn_dumpall.setEnabled(true);
            ScritualFragmentActivity.this.pbar_prop.setVisibility(8);
        }

        public void cancelKatrinaPROPTask() {
            cancel(true);
        }
    }

    public void _onLoadModel() {
        this.lm_json_model.clear();
        this.lm_json_model = (ArrayList) new Gson().fromJson(this.s_click_brand, new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.32
        }.getType());
        this.rv_1.setAdapter(new Rv_1Adapter(this.lm_json_model));
        this.rv_1.setLayoutManager(new LinearLayoutManager(getContext()));
        this.btn_back.setText("BACK TO BRAND");
        this.tv_title.setText(this.s_rv);
        this.btn_back.setVisibility(0);
    }

    public void _onAdvanceBindBrand(View view, TextView textView, TextView textView2, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        textView.setText(arrayList.get((int) d).get("MEREK").toString());
        textView2.setVisibility(8);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.33
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScritualFragmentActivity.this.s_click_brand = ((HashMap) arrayList.get((int) d)).get("MEREK").toString();
                ScritualFragmentActivity.this.s_rv = ((HashMap) arrayList.get((int) d)).get("MEREK").toString();
                ScritualFragmentActivity.this.s_click_brand = new Gson().toJson(((HashMap) arrayList.get((int) d)).get("DATA"));
                ScritualFragmentActivity.this._onLoadModel();
            }
        });
    }

    public void _onAdvanceBindModel(View view, TextView textView, TextView textView2, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        int i = (int) d;
        textView.setText(arrayList.get(i).get("DEVICENAME").toString());
        textView2.setText("OS : ".concat(arrayList.get(i).get("RELEASE").toString()));
        view.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.34
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScritualFragmentActivity.this.s_rv2 = ((HashMap) arrayList.get((int) d)).get("DEVICENAME").toString();
                ScritualFragmentActivity.this._onCreateJsonProp(d, arrayList);
                ScritualFragmentActivity.this._onCreateJsonOri(d, arrayList);
                ScritualFragmentActivity.this._onCreateJsonRandom(d, arrayList);
                ScritualFragmentActivity.this._onModelLoadProp();
            }
        });
    }

    public void _onBackButton() {
        if (this.lm_json_prop.size() > 0 && this.lm_json_prop.get(0).containsKey("ONLINE")) {
            if (this.btn_back.getText().toString().equals("BACK TO MODEL")) {
                this.lv_branch_all.setVisibility(0);
                this.rv_2.setVisibility(8);
                this.btn_back.setVisibility(8);
            }
        } else if (this.btn_back.getText().toString().equals("BACK TO BRAND")) {
            this.rv_1.setAdapter(new Rv_1Adapter(this.lm_json_brand));
            this.rv_1.setLayoutManager(new LinearLayoutManager(getContext()));
            this.tv_title.setText("BRAND");
            this.btn_back.setVisibility(8);
        } else if (this.btn_back.getText().toString().equals("BACK TO MODEL")) {
            this.rv_1.setAdapter(new Rv_1Adapter(this.lm_json_model));
            this.rv_1.setLayoutManager(new LinearLayoutManager(getContext()));
            this.btn_back.setText("BACK TO BRAND");
            this.tv_title.setText(this.s_rv);
            this.rv_1.setVisibility(0);
            this.btn_back.setVisibility(0);
            this.rv_2.setVisibility(8);
        }
        this.oa3.cancel();
        this.oa3.setTarget(this.rv_3);
        this.oa3.setPropertyName("alpha");
        this.oa3.setFloatValues(1.0f, 0.0f);
        this.oa3.setDuration(300L);
        this.oa3.start();
        this.oa4.cancel();
        this.oa4.setTarget(this._fab);
        this.oa4.setPropertyName("alpha");
        this.oa4.setFloatValues(1.0f, 0.0f);
        this.oa4.setDuration(300L);
        this.oa4.start();
    }

    public void _onModelLoadProp() {
        this.btn_back.setText("BACK TO MODEL");
        this.tv_title.setText(this.s_rv2);
        this.rv_1.setVisibility(8);
        this.rv_2.setVisibility(0);
        _onModelLoadEditor();
    }

    public void _onModelLoadEditor() {
        if (this.pref.getString("preferences_editor", "").equals("")) {
            _createJsonEditor();
        } else {
            this.lm_json_editor.clear();
            String string = this.pref.getString("preferences_editor", "");
            this.s_json_pref = string;
            if (string.equals("[]")) {
                _createJsonEditor();
            } else if (jsonIsValid(this.s_json_pref)) {
                ArrayList<HashMap<String, Object>> arrayList = (ArrayList) new Gson().fromJson(this.s_json_pref, new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.35
                }.getType());
                this.lm_json_editor = arrayList;
                String[] strArr = {"DEVICE", "MODEL", "PRODUCT", "MANUFACTURER", "BRAND", "BOOT", "BUILDID", "RELEASE", "INCREMENTAL", "DISPLAY", "FINGERPRINT", "DESCRIPTION", "NAME", "TIMEPICK", "WIPEGMS", "SSAID", "RESET0", "REBOOT", "DALVIC", "NORESTART", "OS9", "OS10", "OS11", "OS12", "OS12L", "OS13", "OS14", "OSPLUS", "MODPESSTART", "MODPESEND"};
                Iterator<HashMap<String, Object>> it = arrayList.iterator();
                while (it.hasNext()) {
                    HashMap<String, Object> next = it.next();
                    int i = 0;
                    if (next.isEmpty()) {
                        while (i < 30) {
                            next.put(strArr[i], "false");
                            i++;
                        }
                    } else {
                        while (i < 30) {
                            String str = strArr[i];
                            if (!next.containsKey(str)) {
                                next.put(str, "false");
                            }
                            i++;
                        }
                    }
                }
            } else {
                _createJsonEditor();
            }
            _onModelFromEditor();
        }
        this.rv_2.setAdapter(new Rv_2Adapter(this.lm_json_prop));
        this.rv_2.setLayoutManager(new LinearLayoutManager(getContext()));
        this.rv_3.setAdapter(new Rv_3Adapter(this.lm_json_editor));
        this.rv_3.setLayoutManager(new LinearLayoutManager(getContext()));
        this.oa1.cancel();
        this.oa1.setTarget(this.rv_3);
        this.oa1.setPropertyName("alpha");
        this.oa1.setFloatValues(0.0f, 1.0f);
        this.oa1.setDuration(1000L);
        this.oa1.start();
        this.oa2.cancel();
        this.oa2.setTarget(this._fab);
        this.oa2.setPropertyName("alpha");
        this.oa2.setFloatValues(0.0f, 1.0f);
        this.oa2.setDuration(1000L);
        this.oa2.start();
    }

    public void _onModelFromEditor() {
        if (this.lm_json_editor.get(0).get("DEVICE").toString().equals("true")) {
            this.lm_json_prop.get(0).put("DEVICE", this.lm_json_random.get(0).get("DEVICE").toString());
        }
        if (this.lm_json_editor.get(0).get("MODEL").toString().equals("true")) {
            this.lm_json_prop.get(0).put("MODEL", this.lm_json_random.get(0).get("MODEL").toString());
        }
        if (this.lm_json_editor.get(0).get("PRODUCT").toString().equals("true")) {
            this.lm_json_prop.get(0).put("PRODUCT", this.lm_json_random.get(0).get("PRODUCT").toString());
        }
        if (this.lm_json_editor.get(0).get("MANUFACTURER").toString().equals("true")) {
            this.lm_json_prop.get(0).put("MANUFACTURER", this.lm_json_random.get(0).get("MANUFACTURER").toString());
        }
        if (this.lm_json_editor.get(0).get("BRAND").toString().equals("true")) {
            this.lm_json_prop.get(0).put("BRAND", this.lm_json_random.get(0).get("BRAND").toString());
        }
        if (this.lm_json_editor.get(0).get("BOOT").toString().equals("true")) {
            this.lm_json_prop.get(0).put("BOOT", this.lm_json_random.get(0).get("BOOT").toString());
        }
        if (this.lm_json_editor.get(0).get("BUILDID").toString().equals("true")) {
            this.lm_json_prop.get(0).put("BUILDID", this.lm_json_random.get(0).get("BUILDID").toString());
        }
        if (this.lm_json_editor.get(0).get("INCREMENTAL").toString().equals("true")) {
            this.lm_json_prop.get(0).put("INCREMENTAL", this.lm_json_random.get(0).get("INCREMENTAL").toString());
        }
        if (this.lm_json_editor.get(0).get("DISPLAY").toString().equals("true")) {
            this.lm_json_prop.get(0).put("DISPLAY", this.lm_json_random.get(0).get("DISPLAY").toString());
        }
        if (this.lm_json_editor.get(0).get("FINGERPRINT").toString().equals("true")) {
            _onGetValueRelease();
            this.s_universal = this.lm_json_random.get(0).get("FINGERPRINT").toString().replace("FURELEASEFU", this.s_prop_release);
            this.lm_json_prop.get(0).put("FINGERPRINT", this.s_universal);
        }
        if (this.lm_json_editor.get(0).get("DESCRIPTION").toString().equals("true")) {
            _onGetValueRelease();
            this.s_universal = this.lm_json_random.get(0).get("DESCRIPTION").toString().replace("FURELEASEFU", this.s_prop_release);
            this.lm_json_prop.get(0).put("DESCRIPTION", this.s_universal);
        }
        if (this.lm_json_editor.get(0).get("NAME").toString().equals("true")) {
            this.lm_json_prop.get(0).put("NAME", this.lm_json_random.get(0).get("NAME").toString());
        }
        if (this.lm_json_editor.get(0).get("OS9").toString().equals("9")) {
            this.lm_json_prop.get(0).put("RELEASE", "9");
        }
        if (this.lm_json_editor.get(0).get("OS10").toString().equals("10")) {
            this.lm_json_prop.get(0).put("RELEASE", "10");
        }
        if (this.lm_json_editor.get(0).get("OS11").toString().equals("11")) {
            this.lm_json_prop.get(0).put("RELEASE", "11");
        }
        if (this.lm_json_editor.get(0).get("OS12").toString().equals("12")) {
            this.lm_json_prop.get(0).put("RELEASE", "12");
        }
        if (this.lm_json_editor.get(0).get("OS12L").toString().equals("12.1")) {
            this.lm_json_prop.get(0).put("RELEASE", "12.1");
        }
        if (this.lm_json_editor.get(0).get("OS13").toString().equals("13")) {
            this.lm_json_prop.get(0).put("RELEASE", "13");
        }
        if (this.lm_json_editor.get(0).get("OS14").toString().equals("14")) {
            this.lm_json_prop.get(0).put("RELEASE", "14");
        }
        if (this.lm_json_editor.get(0).get("OSPLUS").toString().equals("false")) {
            return;
        }
        this.lm_json_prop.get(0).put("RELEASE", this.prefos.getString("OSPLUS", ""));
    }

    public void _onCreateJsonProp(double d, ArrayList<HashMap<String, Object>> arrayList) {
        this.lm_json_prop.clear();
        this.m_prop.clear();
        this.m_prop = new HashMap<>();
        int i = (int) d;
        String obj = arrayList.get(i).get("DEVICE").toString();
        this.s_prop_device = obj;
        this.m_prop.put("DEVICE", obj);
        String obj2 = arrayList.get(i).get("MODEL").toString();
        this.s_prop_model = obj2;
        this.m_prop.put("MODEL", obj2);
        String obj3 = arrayList.get(i).get("PRODUCT").toString();
        this.s_prop_product = obj3;
        this.m_prop.put("PRODUCT", obj3);
        String str = this.s_prop_product;
        this.s_prop_name = str;
        this.m_prop.put("NAME", str);
        String obj4 = arrayList.get(i).get("MANUFACTURER").toString();
        this.s_prop_manufacturer = obj4;
        this.m_prop.put("MANUFACTURER", obj4);
        String obj5 = arrayList.get(i).get("BRAND").toString();
        this.s_prop_brand = obj5;
        this.m_prop.put("BRAND", obj5);
        String obj6 = arrayList.get(i).get("INCREMENTAL").toString();
        this.s_prop_boot = obj6;
        this.m_prop.put("BOOT", obj6);
        String obj7 = arrayList.get(i).get("BUILDID").toString();
        this.s_prop_buildid = obj7;
        this.m_prop.put("BUILDID", obj7);
        String obj8 = arrayList.get(i).get("RELEASE").toString();
        this.s_prop_release = obj8;
        this.m_prop.put("RELEASE", obj8);
        String obj9 = arrayList.get(i).get("INCREMENTAL").toString();
        this.s_prop_incremental = obj9;
        this.m_prop.put("INCREMENTAL", obj9);
        String concat = this.s_prop_buildid.concat(".".concat(this.s_prop_incremental));
        this.s_prop_display = concat;
        this.m_prop.put("DISPLAY", concat);
        String concat2 = this.s_prop_brand.concat("/".concat(this.s_prop_product.concat("/".concat(this.s_prop_device.concat(":".concat(this.s_prop_release.concat("/".concat(this.s_prop_buildid.concat("/".concat(this.s_prop_incremental.concat(":user/release-keys")))))))))));
        this.s_prop_fingerprint = concat2;
        this.m_prop.put("FINGERPRINT", concat2);
        String concat3 = this.s_prop_name.concat("-user ".concat(this.s_prop_release.concat(" ".concat(this.s_prop_buildid.concat(this.s_prop_incremental.concat(" release-keys"))))));
        this.s_prop_description = concat3;
        this.m_prop.put("DESCRIPTION", concat3);
        String str2 = Build.VERSION.SDK;
        this.s_prop_sdk = str2;
        this.m_prop.put("SDK", str2);
        String generateRandomString = generateRandomString();
        this.s_prop_board = generateRandomString;
        this.m_prop.put("BOARD", generateRandomString);
        String generateRandomString2 = generateRandomString();
        this.s_prop_hardware = generateRandomString2;
        this.m_prop.put("HARDWARE", generateRandomString2);
        String generateRandomString3 = generateRandomString();
        this.s_prop_host = generateRandomString3;
        this.m_prop.put("HOST", generateRandomString3);
        String generateRandomString4 = generateRandomString();
        this.s_prop_user = generateRandomString4;
        this.m_prop.put("USER", generateRandomString4);
        String generateRandomString5 = generateRandomString();
        this.s_prop_flavor = generateRandomString5;
        this.m_prop.put("FLAVOR", generateRandomString5);
        long randomUnixTime = getRandomUnixTime(1623239806L, new Date().getTime() / 1000);
        Date date = new Date(1000 * randomUnixTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        this.s_prop_date = simpleDateFormat.format(date);
        this.s_prop_dateutc = String.valueOf(randomUnixTime);
        String replaceAll = this.s_prop_date.replaceAll("GMT.* ", "KST ");
        this.s_prop_date = replaceAll;
        this.m_prop.put("DATE", replaceAll);
        this.m_prop.put("UTC", this.s_prop_dateutc);
        this.lm_json_prop.add(this.m_prop);
    }

    public void _onCreateJsonOri(double d, ArrayList<HashMap<String, Object>> arrayList) {
        this.lm_json_ori.clear();
        this.m_ori.clear();
        HashMap<String, Object> hashMap = new HashMap<>();
        this.m_ori = hashMap;
        hashMap.put("DEVICE", this.s_prop_device);
        this.m_ori.put("MODEL", this.s_prop_model);
        this.m_ori.put("PRODUCT", this.s_prop_product);
        this.m_ori.put("NAME", this.s_prop_name);
        this.m_ori.put("MANUFACTURER", this.s_prop_manufacturer);
        this.m_ori.put("BRAND", this.s_prop_brand);
        this.m_ori.put("BOOT", this.s_prop_boot);
        this.m_ori.put("BUILDID", this.s_prop_buildid);
        this.m_ori.put("RELEASE", this.s_prop_release);
        this.m_ori.put("INCREMENTAL", this.s_prop_incremental);
        this.m_ori.put("DISPLAY", this.s_prop_display);
        this.m_ori.put("FINGERPRINT", this.s_prop_fingerprint);
        this.m_ori.put("DESCRIPTION", this.s_prop_description);
        this.m_ori.put("SDK", this.s_prop_sdk);
        this.m_ori.put("BOARD", this.s_prop_board);
        this.m_ori.put("HARDWARE", this.s_prop_hardware);
        this.m_ori.put("HOST", this.s_prop_host);
        this.m_ori.put("USER", this.s_prop_user);
        this.m_ori.put("DATE", this.s_prop_date);
        this.m_ori.put("UTC", this.s_prop_dateutc);
        this.m_ori.put("FLAVOR", this.s_prop_flavor);
        this.lm_json_ori.add(this.m_ori);
    }

    public void _onCreateJsonRandom(double d, ArrayList<HashMap<String, Object>> arrayList) {
        this.lm_json_random.clear();
        this.m_random.clear();
        this.m_random = new HashMap<>();
        String generateRandomString = generateRandomString();
        this.s_prop_device = generateRandomString;
        this.m_random.put("DEVICE", generateRandomString);
        String generateRandomString2 = generateRandomString();
        this.s_prop_model = generateRandomString2;
        this.m_random.put("MODEL", generateRandomString2);
        String generateRandomString3 = generateRandomString();
        this.s_prop_product = generateRandomString3;
        this.m_random.put("PRODUCT", generateRandomString3);
        String str = this.s_prop_product;
        this.s_prop_name = str;
        this.m_random.put("NAME", str);
        String generateRandomString4 = generateRandomString();
        this.s_prop_manufacturer = generateRandomString4;
        this.m_random.put("MANUFACTURER", generateRandomString4);
        String generateRandomString5 = generateRandomString();
        this.s_prop_brand = generateRandomString5;
        this.m_random.put("BRAND", generateRandomString5);
        String generateRandomString6 = generateRandomString();
        this.s_prop_boot = generateRandomString6;
        this.m_random.put("BOOT", generateRandomString6);
        String generateRandomString7 = generateRandomString();
        this.s_prop_buildid = generateRandomString7;
        this.m_random.put("BUILDID", generateRandomString7);
        String generateRandomString8 = generateRandomString();
        this.s_prop_incremental = generateRandomString8;
        this.m_random.put("INCREMENTAL", generateRandomString8);
        this.m_random.put("DISPLAY", this.s_prop_buildid.concat(".".concat(this.s_prop_incremental)));
        this.m_random.put("FINGERPRINT", this.s_prop_brand.concat("/".concat(this.s_prop_product.concat("/".concat(this.s_prop_device.concat(":".concat("FURELEASEFU".concat("/".concat(this.s_prop_buildid.concat("/".concat(this.s_prop_incremental.concat(":user/release-keys"))))))))))));
        String concat = this.s_prop_name.concat("-user FURELEASEFU ".concat(this.s_prop_buildid.concat(this.s_prop_incremental.concat(" release-keys"))));
        this.s_prop_description = concat;
        this.m_random.put("DESCRIPTION", concat);
        String generateRandomString9 = generateRandomString();
        this.s_prop_board = generateRandomString9;
        this.m_random.put("BOARD", generateRandomString9);
        String generateRandomString10 = generateRandomString();
        this.s_prop_hardware = generateRandomString10;
        this.m_random.put("HARDWARE", generateRandomString10);
        String generateRandomString11 = generateRandomString();
        this.s_prop_host = generateRandomString11;
        this.m_random.put("HOST", generateRandomString11);
        String generateRandomString12 = generateRandomString();
        this.s_prop_user = generateRandomString12;
        this.m_random.put("USER", generateRandomString12);
        this.m_random.put("DATE", this.s_prop_date);
        this.m_random.put("UTC", this.s_prop_dateutc);
        String generateRandomString13 = generateRandomString();
        this.s_prop_flavor = generateRandomString13;
        this.m_random.put("FLAVOR", generateRandomString13);
        this.lm_json_random.add(this.m_random);
    }

    public void _onChipProp(CheckBox checkBox, final String str, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.36
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ((HashMap) arrayList.get((int) d)).put(str, "true");
                    if (str.equals("FINGERPRINT")) {
                        ScritualFragmentActivity.this._onGetValueRelease();
                        ScritualFragmentActivity scritualFragmentActivity = ScritualFragmentActivity.this;
                        scritualFragmentActivity.s_universal = ((HashMap) scritualFragmentActivity.lm_json_random.get((int) d)).get(str).toString().replace("FURELEASEFU", ScritualFragmentActivity.this.s_prop_release);
                        ((HashMap) ScritualFragmentActivity.this.lm_json_prop.get((int) d)).put(str, ScritualFragmentActivity.this.s_universal);
                    } else if (!str.equals("DESCRIPTION")) {
                        ((HashMap) ScritualFragmentActivity.this.lm_json_prop.get((int) d)).put(str, ((HashMap) ScritualFragmentActivity.this.lm_json_random.get((int) d)).get(str).toString());
                    } else {
                        ScritualFragmentActivity.this._onGetValueRelease();
                        ScritualFragmentActivity scritualFragmentActivity2 = ScritualFragmentActivity.this;
                        scritualFragmentActivity2.s_universal = ((HashMap) scritualFragmentActivity2.lm_json_random.get((int) d)).get(str).toString().replace("FURELEASEFU", ScritualFragmentActivity.this.s_prop_release);
                        ((HashMap) ScritualFragmentActivity.this.lm_json_prop.get((int) d)).put(str, ScritualFragmentActivity.this.s_universal);
                    }
                    ((Rv_2Adapter) ScritualFragmentActivity.this.rv_2.getAdapter()).notifyDataSetChanged();
                    return;
                }
                ((HashMap) arrayList.get((int) d)).put(str, "false");
                ((HashMap) ScritualFragmentActivity.this.lm_json_prop.get((int) d)).put(str, ((HashMap) ScritualFragmentActivity.this.lm_json_ori.get((int) d)).get(str).toString());
                ((Rv_2Adapter) ScritualFragmentActivity.this.rv_2.getAdapter()).notifyDataSetChanged();
            }
        });
    }

    public void _onChipRelease(CheckBox checkBox, final String str, final ArrayList<HashMap<String, Object>> arrayList, final double d, final String str2) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.37
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ((HashMap) arrayList.get((int) d)).put("OS9", "false");
                    ((HashMap) arrayList.get((int) d)).put("OS10", "false");
                    ((HashMap) arrayList.get((int) d)).put("OS11", "false");
                    ((HashMap) arrayList.get((int) d)).put("OS12", "false");
                    ((HashMap) arrayList.get((int) d)).put("OS12L", "false");
                    ((HashMap) arrayList.get((int) d)).put("OS13", "false");
                    ((HashMap) arrayList.get((int) d)).put("OS14", "false");
                    ((HashMap) arrayList.get((int) d)).put("OSPLUS", "false");
                    ((HashMap) arrayList.get((int) d)).put(str, str2);
                    ((HashMap) ScritualFragmentActivity.this.lm_json_prop.get((int) d)).put("RELEASE", str2);
                    if (((HashMap) ScritualFragmentActivity.this.lm_json_editor.get(0)).get("FINGERPRINT").toString().equals("true")) {
                        ScritualFragmentActivity scritualFragmentActivity = ScritualFragmentActivity.this;
                        scritualFragmentActivity.s_universal = ((HashMap) scritualFragmentActivity.lm_json_random.get(0)).get("FINGERPRINT").toString().replace("FURELEASEFU", str2);
                        ((HashMap) ScritualFragmentActivity.this.lm_json_prop.get(0)).put("FINGERPRINT", ScritualFragmentActivity.this.s_universal);
                    }
                    if (((HashMap) ScritualFragmentActivity.this.lm_json_editor.get(0)).get("DESCRIPTION").toString().equals("true")) {
                        ScritualFragmentActivity scritualFragmentActivity2 = ScritualFragmentActivity.this;
                        scritualFragmentActivity2.s_universal = ((HashMap) scritualFragmentActivity2.lm_json_random.get(0)).get("DESCRIPTION").toString().replace("FURELEASEFU", str2);
                        ((HashMap) ScritualFragmentActivity.this.lm_json_prop.get(0)).put("DESCRIPTION", ScritualFragmentActivity.this.s_universal);
                    }
                    ((Rv_2Adapter) ScritualFragmentActivity.this.rv_2.getAdapter()).notifyDataSetChanged();
                    return;
                }
                ((HashMap) arrayList.get((int) d)).put(str, "false");
                ((HashMap) ScritualFragmentActivity.this.lm_json_prop.get((int) d)).put("RELEASE", ((HashMap) ScritualFragmentActivity.this.lm_json_ori.get((int) d)).get("RELEASE").toString());
                ((Rv_2Adapter) ScritualFragmentActivity.this.rv_2.getAdapter()).notifyDataSetChanged();
            }
        });
    }

    public void _onChipClean(CheckBox checkBox, final String str, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.38
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ((HashMap) arrayList.get((int) d)).put("SSAID", "false");
                    ((HashMap) arrayList.get((int) d)).put("RESET0", "false");
                    ((HashMap) arrayList.get((int) d)).put(str, "true");
                    return;
                }
                ((HashMap) arrayList.get((int) d)).put(str, "false");
            }
        });
    }

    public void _onChipReboot(CheckBox checkBox, final String str, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.39
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ((HashMap) arrayList.get((int) d)).put("REBOOT", "false");
                    ((HashMap) arrayList.get((int) d)).put("DALVIC", "false");
                    ((HashMap) arrayList.get((int) d)).put("NORESTART", "false");
                    ((HashMap) arrayList.get((int) d)).put(str, "true");
                    return;
                }
                ((HashMap) arrayList.get((int) d)).put(str, "false");
            }
        });
    }

    public void _onGetAllProp() {
        this.pref.edit().putString("preferences_editor", new Gson().toJson(this.lm_json_editor)).commit();
        this.b_command = false;
        _showProgressDialog();
        if (this.lm_json_editor.get(0).get("MODPESSTART").toString().equals("true")) {
            this.s_commandModpes = "settings put global airplane_mode_on 1\nam broadcast -a android.intent.action.AIRPLANE_MODE";
            this.b_command = false;
            Shell.Result exec = Shell.cmd("settings put global airplane_mode_on 1\nam broadcast -a android.intent.action.AIRPLANE_MODE").exec();
            List<String> out = exec.getOut();
            exec.getCode();
            this.b_command = exec.isSuccess();
            this.s_commandResult = ScritualFragmentActivity$$ExternalSyntheticBackport0.m("\n", out);
            _onGetAllProp2();
            return;
        }
        _onGetAllProp2();
    }

    public void _onGetAllProp2() {
        String str = this.s_commandBase;
        this.s_command = str;
        String concat = str.concat("\nogetprop");
        this.s_command = concat;
        this.b_command = false;
        Shell.Result exec = Shell.cmd(concat).exec();
        List<String> out = exec.getOut();
        exec.getCode();
        this.b_command = exec.isSuccess();
        this.s_commandResult = ScritualFragmentActivity$$ExternalSyntheticBackport0.m("\n", out);
        _onGetValueProp();
    }

    public void _onGetValueProp() {
        this.s_prop_device = this.lm_json_prop.get(0).get("DEVICE").toString();
        this.s_prop_model = this.lm_json_prop.get(0).get("MODEL").toString();
        this.s_prop_product = this.lm_json_prop.get(0).get("PRODUCT").toString();
        this.s_prop_name = this.lm_json_prop.get(0).get("NAME").toString();
        this.s_prop_manufacturer = this.lm_json_prop.get(0).get("MANUFACTURER").toString();
        this.s_prop_brand = this.lm_json_prop.get(0).get("BRAND").toString();
        this.s_prop_boot = this.lm_json_prop.get(0).get("BOOT").toString();
        this.s_prop_buildid = this.lm_json_prop.get(0).get("BUILDID").toString();
        this.s_prop_fingerprint = this.lm_json_prop.get(0).get("FINGERPRINT").toString();
        this.s_prop_description = this.lm_json_prop.get(0).get("DESCRIPTION").toString();
        this.s_prop_incremental = this.lm_json_prop.get(0).get("INCREMENTAL").toString();
        this.s_prop_display = this.lm_json_prop.get(0).get("DISPLAY").toString();
        this.s_prop_board = this.lm_json_prop.get(0).get("BOARD").toString();
        this.s_prop_hardware = this.lm_json_prop.get(0).get("HARDWARE").toString();
        this.s_prop_host = this.lm_json_prop.get(0).get("HOST").toString();
        this.s_prop_user = this.lm_json_prop.get(0).get("USER").toString();
        this.s_prop_flavor = this.lm_json_prop.get(0).get("FLAVOR").toString();
        this.s_prop_date = this.lm_json_prop.get(0).get("DATE").toString();
        this.s_prop_dateutc = this.lm_json_prop.get(0).get("UTC").toString();
        if (this.lm_json_editor.get(0).get("OS9").toString().equals("9")) {
            this.s_prop_release = "9";
        } else if (this.lm_json_editor.get(0).get("OS10").toString().equals("10")) {
            this.s_prop_release = "10";
        } else if (this.lm_json_editor.get(0).get("OS11").toString().equals("11")) {
            this.s_prop_release = "11";
        } else if (this.lm_json_editor.get(0).get("OS12").toString().equals("12")) {
            this.s_prop_release = "12";
        } else if (this.lm_json_editor.get(0).get("OS12L").toString().equals("12.1")) {
            this.s_prop_release = "12.1";
        } else if (this.lm_json_editor.get(0).get("OS13").toString().equals("13")) {
            this.s_prop_release = "13";
        } else if (this.lm_json_editor.get(0).get("OS14").toString().equals("14")) {
            this.s_prop_release = "14";
        } else if (!this.lm_json_editor.get(0).get("OSPLUS").toString().equals("false")) {
            this.s_prop_release = this.prefos.getString("OSPLUS", "");
        } else {
            this.s_prop_release = this.lm_json_prop.get(0).get("RELEASE").toString();
        }
        _onReplaceProp();
    }

    public void _onReplaceProp() {
        KatrinaREPLACEPROP katrinaREPLACEPROP = this.katrinaREPLACEPROP;
        if (katrinaREPLACEPROP == null || katrinaREPLACEPROP.getStatus() == AsyncTask.Status.FINISHED) {
            KatrinaREPLACEPROP katrinaREPLACEPROP2 = new KatrinaREPLACEPROP();
            this.katrinaREPLACEPROP = katrinaREPLACEPROP2;
            katrinaREPLACEPROP2.execute(new Void[0]);
        } else if (this.katrinaREPLACEPROP.getStatus() == AsyncTask.Status.PENDING) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Proses KatrinaREPLACEPROP masih pending");
        }
    }

    /* loaded from: classes5.dex */
    public class KatrinaREPLACEPROP extends AsyncTask<Void, Void, Void> {
        public KatrinaREPLACEPROP() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            ScritualFragmentActivity.this.m_systemprop = new HashMap();
            ScritualFragmentActivity.this.m_systemprop.put("BOARD", ScritualFragmentActivity.this.s_prop_board);
            ScritualFragmentActivity.this.m_systemprop.put("BUILDID", ScritualFragmentActivity.this.s_prop_buildid);
            ScritualFragmentActivity.this.m_systemprop.put("DEVICE", ScritualFragmentActivity.this.s_prop_device);
            ScritualFragmentActivity.this.m_systemprop.put("MODEL", ScritualFragmentActivity.this.s_prop_model);
            ScritualFragmentActivity.this.m_systemprop.put("MANUFACTURER", ScritualFragmentActivity.this.s_prop_manufacturer);
            ScritualFragmentActivity.this.m_systemprop.put("RELEASE", ScritualFragmentActivity.this.s_prop_release);
            ScritualFragmentActivity.this.m_systemprop.put("USER", ScritualFragmentActivity.this.s_prop_user);
            ScritualFragmentActivity.this.m_systemprop.put("BRAND", ScritualFragmentActivity.this.s_prop_brand);
            ScritualFragmentActivity.this.m_systemprop.put("DISPLAYID", ScritualFragmentActivity.this.s_prop_display);
            ScritualFragmentActivity.this.m_systemprop.put("FINGERPRINT", ScritualFragmentActivity.this.s_prop_fingerprint);
            ScritualFragmentActivity.this.m_systemprop.put("DESCRIPTION", ScritualFragmentActivity.this.s_prop_description);
            ScritualFragmentActivity.this.m_systemprop.put("PRODUCT", ScritualFragmentActivity.this.s_prop_product);
            ScritualFragmentActivity.this.m_systemprop.put("NAME", ScritualFragmentActivity.this.s_prop_name);
            ScritualFragmentActivity.this.m_systemprop.put("INCREMENTAL", ScritualFragmentActivity.this.s_prop_incremental);
            ScritualFragmentActivity.this.m_systemprop.put("HOST", ScritualFragmentActivity.this.s_prop_host);
            ScritualFragmentActivity.this.m_systemprop.put("BOOTLOADER", ScritualFragmentActivity.this.s_prop_boot);
            ScritualFragmentActivity.this.m_systemprop.put("HARDWARE", ScritualFragmentActivity.this.s_prop_hardware);
            ScritualFragmentActivity.this.m_systemprop.put("DATE", ScritualFragmentActivity.this.s_prop_date);
            ScritualFragmentActivity.this.m_systemprop.put("UTC", ScritualFragmentActivity.this.s_prop_dateutc);
            ScritualFragmentActivity.this.b_command = false;
            ScritualFragmentActivity scritualFragmentActivity = ScritualFragmentActivity.this;
            scritualFragmentActivity.s_propvalue = FileUtil.readFile(scritualFragmentActivity.s_defaultprop);
            ScritualFragmentActivity scritualFragmentActivity2 = ScritualFragmentActivity.this;
            scritualFragmentActivity2.s_propvalue = scritualFragmentActivity2.s_propvalue.replace("ro.boot.hardware=", "#");
            ScritualFragmentActivity scritualFragmentActivity3 = ScritualFragmentActivity.this;
            scritualFragmentActivity3.s_propvalue = scritualFragmentActivity3.s_propvalue.replace("ro.build.flavor=", "#");
            ScritualFragmentActivity scritualFragmentActivity4 = ScritualFragmentActivity.this;
            scritualFragmentActivity4.s_propvalue = scritualFragmentActivity4.s_propvalue.replace(".display.id=", ".displayid=");
            ScritualFragmentActivity scritualFragmentActivity5 = ScritualFragmentActivity.this;
            scritualFragmentActivity5.s_propvalue = scritualFragmentActivity5.s_propvalue.replace(".board=", ".board ".concat(ScritualFragmentActivity.this.s_prop_board));
            ScritualFragmentActivity scritualFragmentActivity6 = ScritualFragmentActivity.this;
            scritualFragmentActivity6.s_propvalue = scritualFragmentActivity6.s_propvalue.replace(".id=", ".id ".concat(ScritualFragmentActivity.this.s_prop_buildid));
            ScritualFragmentActivity scritualFragmentActivity7 = ScritualFragmentActivity.this;
            scritualFragmentActivity7.s_propvalue = scritualFragmentActivity7.s_propvalue.replace(".device=", ".device ".concat(ScritualFragmentActivity.this.s_prop_device));
            ScritualFragmentActivity scritualFragmentActivity8 = ScritualFragmentActivity.this;
            scritualFragmentActivity8.s_propvalue = scritualFragmentActivity8.s_propvalue.replace(".model=", ".model ".concat(ScritualFragmentActivity.this.s_prop_model));
            ScritualFragmentActivity scritualFragmentActivity9 = ScritualFragmentActivity.this;
            scritualFragmentActivity9.s_propvalue = scritualFragmentActivity9.s_propvalue.replace(".manufacturer=", ".manufacturer ".concat(ScritualFragmentActivity.this.s_prop_manufacturer));
            ScritualFragmentActivity scritualFragmentActivity10 = ScritualFragmentActivity.this;
            scritualFragmentActivity10.s_propvalue = scritualFragmentActivity10.s_propvalue.replace(".release=", ".release ".concat(ScritualFragmentActivity.this.s_prop_release));
            ScritualFragmentActivity scritualFragmentActivity11 = ScritualFragmentActivity.this;
            scritualFragmentActivity11.s_propvalue = scritualFragmentActivity11.s_propvalue.replace(".user=", ".user ".concat(ScritualFragmentActivity.this.s_prop_user));
            ScritualFragmentActivity scritualFragmentActivity12 = ScritualFragmentActivity.this;
            scritualFragmentActivity12.s_propvalue = scritualFragmentActivity12.s_propvalue.replace(".brand=", ".brand ".concat(ScritualFragmentActivity.this.s_prop_brand));
            ScritualFragmentActivity scritualFragmentActivity13 = ScritualFragmentActivity.this;
            scritualFragmentActivity13.s_propvalue = scritualFragmentActivity13.s_propvalue.replace(".displayid=", ".displayid ".concat(ScritualFragmentActivity.this.s_prop_display));
            ScritualFragmentActivity scritualFragmentActivity14 = ScritualFragmentActivity.this;
            scritualFragmentActivity14.s_propvalue = scritualFragmentActivity14.s_propvalue.replace(".fingerprint=", ".fingerprint ".concat(ScritualFragmentActivity.this.s_prop_fingerprint));
            ScritualFragmentActivity scritualFragmentActivity15 = ScritualFragmentActivity.this;
            scritualFragmentActivity15.s_propvalue = scritualFragmentActivity15.s_propvalue.replace(".description=", ".description ".concat(ScritualFragmentActivity.this.s_prop_description));
            ScritualFragmentActivity scritualFragmentActivity16 = ScritualFragmentActivity.this;
            scritualFragmentActivity16.s_propvalue = scritualFragmentActivity16.s_propvalue.replace(".product=", ".product ".concat(ScritualFragmentActivity.this.s_prop_product));
            ScritualFragmentActivity scritualFragmentActivity17 = ScritualFragmentActivity.this;
            scritualFragmentActivity17.s_propvalue = scritualFragmentActivity17.s_propvalue.replace(".name=", ".name ".concat(ScritualFragmentActivity.this.s_prop_name));
            ScritualFragmentActivity scritualFragmentActivity18 = ScritualFragmentActivity.this;
            scritualFragmentActivity18.s_propvalue = scritualFragmentActivity18.s_propvalue.replace(".incremental=", ".incremental ".concat(ScritualFragmentActivity.this.s_prop_incremental));
            ScritualFragmentActivity scritualFragmentActivity19 = ScritualFragmentActivity.this;
            scritualFragmentActivity19.s_propvalue = scritualFragmentActivity19.s_propvalue.replace(".host=", ".host ".concat(ScritualFragmentActivity.this.s_prop_host));
            ScritualFragmentActivity scritualFragmentActivity20 = ScritualFragmentActivity.this;
            scritualFragmentActivity20.s_propvalue = scritualFragmentActivity20.s_propvalue.replace(".bootloader=", ".bootloader ".concat(ScritualFragmentActivity.this.s_prop_boot));
            ScritualFragmentActivity scritualFragmentActivity21 = ScritualFragmentActivity.this;
            scritualFragmentActivity21.s_propvalue = scritualFragmentActivity21.s_propvalue.replace(".hardware=", ".hardware ".concat(ScritualFragmentActivity.this.s_prop_hardware));
            ScritualFragmentActivity scritualFragmentActivity22 = ScritualFragmentActivity.this;
            scritualFragmentActivity22.s_propvalue = scritualFragmentActivity22.s_propvalue.replace(".date=", ".date ".concat(ScritualFragmentActivity.this.s_prop_date));
            ScritualFragmentActivity scritualFragmentActivity23 = ScritualFragmentActivity.this;
            scritualFragmentActivity23.s_propvalue = scritualFragmentActivity23.s_propvalue.replace(".date.utc=", ".date.utc ".concat(ScritualFragmentActivity.this.s_prop_dateutc));
            ScritualFragmentActivity scritualFragmentActivity24 = ScritualFragmentActivity.this;
            scritualFragmentActivity24.s_propvalue = scritualFragmentActivity24.s_propvalue.replace(".displayid=", ".display.id ");
            ScritualFragmentActivity.this.s_printprop = new Gson().toJson(ScritualFragmentActivity.this.m_systemprop);
            ScritualFragmentActivity.this.s_printprop = new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement) ((JsonObject) new Gson().fromJson(ScritualFragmentActivity.this.s_printprop, (Class<Object>) JsonObject.class)));
            FileUtil.writeFile(ScritualFragmentActivity.this.s_systemprop, ScritualFragmentActivity.this.s_printprop);
            String[] split = ScritualFragmentActivity.this.s_propvalue.split("\n");
            StringBuilder sb = new StringBuilder();
            for (String str : split) {
                if (str.startsWith("ro.")) {
                    str = str.replaceFirst("ro\\.", "check_resetprop ro.");
                }
                sb.append(str);
                sb.append("\n");
            }
            ScritualFragmentActivity.this.s_propvalue = sb.toString().trim();
            try {
                ScritualFragmentActivity.this.s_post_fs_data = SketchwareUtil.copyFromInputStream(ScritualFragmentActivity.this.getContext().getAssets().open("main2.dex"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            ScritualFragmentActivity scritualFragmentActivity25 = ScritualFragmentActivity.this;
            scritualFragmentActivity25.s_post_fs_data = scritualFragmentActivity25.s_post_fs_data.replace("#XKatrina", "#XKatrina\n".concat(ScritualFragmentActivity.this.s_propvalue));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            while (!isCancelled()) {
                FileUtil.writeFile(ScritualFragmentActivity.this.s_sensitiveprop, ScritualFragmentActivity.this.s_post_fs_data);
                ScritualFragmentActivity scritualFragmentActivity = ScritualFragmentActivity.this;
                scritualFragmentActivity.s_command = "\ncp ".concat(scritualFragmentActivity.s_sensitiveprop.concat(" /data/adb/modules/xkatrina_snstv_prps/post-fs-data.sh\nchmod 0644 /data/adb/modules/xkatrina_snstv_prps/post-fs-data.sh"));
                ScritualFragmentActivity scritualFragmentActivity2 = ScritualFragmentActivity.this;
                scritualFragmentActivity2.s_command = scritualFragmentActivity2.s_command.concat("\ncp ".concat(ScritualFragmentActivity.this.s_systemprop.concat(" /data/adb/modules/xkatrina_snstv_prps/fu.dex\nchmod 0644 /data/adb/modules/xkatrina_snstv_prps/fu.dex")));
                ScritualFragmentActivity.this.b_command = false;
                Shell.Result exec = Shell.cmd(ScritualFragmentActivity.this.s_command).exec();
                List<String> out = exec.getOut();
                exec.getCode();
                ScritualFragmentActivity.this.b_command = exec.isSuccess();
                ScritualFragmentActivity.this.s_commandResult = ScritualFragmentActivity$KatrinaREPLACEPROP$$ExternalSyntheticBackport0.m("\n", out);
                if (!isCancelled()) {
                    return null;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            if (ScritualFragmentActivity.this.b_command) {
                ScritualFragmentActivity.this._onCleanerWipe();
            }
        }

        public void cancelKatrinaREPLACEPROPTask() {
            cancel(true);
        }
    }

    public void _showProgressDialog() {
        showLOADING();
    }

    private void showLOADING() {
        if (getActivity() != null) {
            View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d002c, (ViewGroup) null);
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
            materialAlertDialogBuilder.setView(inflate);
            materialAlertDialogBuilder.setCancelable(false);
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a059f)).setText("Mohon menunggu...");
            AlertDialog create = materialAlertDialogBuilder.create();
            this.LOADING = create;
            create.show();
        }
    }

    public void _hideProgressDialog() {
        AlertDialog alertDialog = this.LOADING;
        if (alertDialog == null || !alertDialog.isShowing() || getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        this.LOADING.dismiss();
    }

    public void _onGetValueRelease() {
        if (this.lm_json_editor.get(0).get("OS9").toString().equals("9")) {
            this.s_prop_release = "9";
        } else if (this.lm_json_editor.get(0).get("OS10").toString().equals("10")) {
            this.s_prop_release = "10";
        } else if (this.lm_json_editor.get(0).get("OS11").toString().equals("11")) {
            this.s_prop_release = "11";
        } else if (this.lm_json_editor.get(0).get("OS12").toString().equals("12")) {
            this.s_prop_release = "12";
        } else if (this.lm_json_editor.get(0).get("OS12L").toString().equals("12.1")) {
            this.s_prop_release = "12.1";
        } else if (this.lm_json_editor.get(0).get("OS13").toString().equals("13")) {
            this.s_prop_release = "13";
        } else if (this.lm_json_editor.get(0).get("OS14").toString().equals("14")) {
            this.s_prop_release = "14";
        } else {
            this.s_prop_release = this.lm_json_prop.get(0).get("RELEASE").toString();
        }
    }

    public void _onCleanerWipe() {
        this.b_command = false;
        this.extraprop = "true";
        this.s_command = this.s_commandBase;
        this.s_commandResult = "";
        this.s_exitCode = "";
        if (this.lm_json_editor.get(0).get("TIMEPICK").toString().equals("true")) {
            this.s_command = this.s_command.concat("\nocleaner");
            this.extracleaner = "true";
        } else {
            this.extracleaner = "false";
        }
        if (this.lm_json_editor.get(0).get("WIPEGMS").toString().equals("true")) {
            this.s_command = this.s_command.concat("\nowipegms");
            this.extrawipe = "true";
        } else {
            this.extrawipe = "false";
        }
        if (this.lm_json_editor.get(0).get("SSAID").toString().equals("true")) {
            this.s_command = this.s_command.concat("\nooossaid");
            this.extrassaid = "true";
        } else {
            this.extrassaid = "false";
        }
        if (this.lm_json_editor.get(0).get("RESET0").toString().equals("true")) {
            this.s_command = this.s_command.concat("\nooooonol");
            this.extrareset = "true";
        } else {
            this.extrareset = "false";
        }
        if (this.lm_json_editor.get(0).get("REBOOT").toString().equals("true")) {
            this.extrareboot = "true";
        } else {
            this.extrareboot = "false";
        }
        if (this.lm_json_editor.get(0).get("DALVIC").toString().equals("true")) {
            this.extradalvic = "true";
        } else {
            this.extradalvic = "false";
        }
        if (this.lm_json_editor.get(0).get("NORESTART").toString().equals("true")) {
            this.extranorestart = "true";
        } else {
            this.extranorestart = "false";
        }
        if (this.lm_json_editor.get(0).get("MODPESEND").toString().equals("true")) {
            this.extramodpes = "true";
        } else {
            this.extramodpes = "false";
        }
        KatrinaCLEANERWIPE katrinaCLEANERWIPE = this.katrinaCLEANERWIPE;
        if (katrinaCLEANERWIPE == null || katrinaCLEANERWIPE.getStatus() == AsyncTask.Status.FINISHED) {
            KatrinaCLEANERWIPE katrinaCLEANERWIPE2 = new KatrinaCLEANERWIPE();
            this.katrinaCLEANERWIPE = katrinaCLEANERWIPE2;
            katrinaCLEANERWIPE2.execute(new Void[0]);
        } else if (this.katrinaCLEANERWIPE.getStatus() == AsyncTask.Status.PENDING) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Proses KatrinaCLEANERWIPE masih pending");
        }
    }

    /* loaded from: classes5.dex */
    public class KatrinaCLEANERWIPE extends AsyncTask<Void, Void, Void> {
        @Override // android.os.AsyncTask
        protected void onPreExecute() {
        }

        public KatrinaCLEANERWIPE() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            while (!isCancelled()) {
                ScritualFragmentActivity.this.b_command = false;
                Shell.Result exec = Shell.cmd(ScritualFragmentActivity.this.s_command).exec();
                List<String> out = exec.getOut();
                exec.getCode();
                ScritualFragmentActivity.this.b_command = exec.isSuccess();
                ScritualFragmentActivity.this.s_commandResult = ScritualFragmentActivity$KatrinaCLEANERWIPE$$ExternalSyntheticBackport0.m("\n", out);
                if (!isCancelled()) {
                    return null;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            ScritualFragmentActivity.this._goToResult();
        }

        public void cancelKatrinaCLEANERWIPETask() {
            cancel(true);
        }
    }

    public void _onChipWipe(CheckBox checkBox, final String str, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.40
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ((HashMap) arrayList.get((int) d)).put(str, "true");
                } else {
                    ((HashMap) arrayList.get((int) d)).put(str, "false");
                }
            }
        });
    }

    public void _goToResult() {
        _hideProgressDialog();
        this.i.setClass(getContext().getApplicationContext(), RitualResultActivity.class);
        this.i.putExtra("PROP", this.extraprop);
        this.i.putExtra("RESET0", this.extrareset);
        this.i.putExtra("WIPEGMS", this.extrawipe);
        this.i.putExtra("SSAID", this.extrassaid);
        this.i.putExtra("CLEANER", this.extracleaner);
        this.i.putExtra("REBOOT", this.extrareboot);
        this.i.putExtra("DALVIC", this.extradalvic);
        this.i.putExtra("NORESTART", this.extranorestart);
        this.i.putExtra("MODPES", this.extramodpes);
        startActivity(this.i);
    }

    public void _onConvertProp() {
        this.s_input_prop = this.auto_input_fp.getText().toString();
        Matcher matcher = Pattern.compile("([^/]+)/(.*?)/(.*?):(.*?)/(.*?)/(.*?):(.*?)/(.*?)").matcher(this.s_input_prop);
        if (matcher.find()) {
            this.s_parsebrand = matcher.group(1);
            this.s_parsedevice = matcher.group(2);
            this.s_parseproduct = matcher.group(3);
            this.s_parserelease = matcher.group(4);
            this.s_parsebuildid = matcher.group(5);
            this.s_parseincremental = matcher.group(6);
            matcher.group(7);
            if (this.auto_input_model.getText().toString().equals("")) {
                this.s_parsemodel = this.s_parsedevice;
            } else {
                this.s_parsemodel = this.auto_input_model.getText().toString();
            }
            this.auto_input_fp.clearFocus();
            this.auto_input_model.clearFocus();
            KeyboardUtils.toggleKeyboardVisibility(requireContext(), this.auto_input_fp);
            KeyboardUtils.toggleKeyboardVisibility(requireContext(), this.auto_input_model);
            this.s_rv2 = this.s_parsemodel;
            this.lm_input.clear();
            this.m_input.clear();
            HashMap<String, Object> hashMap = new HashMap<>();
            this.m_input = hashMap;
            hashMap.put("DEVICENAME", this.s_parsemodel);
            this.m_input.put("MANUFACTURER", this.s_parsebrand);
            this.m_input.put("MODEL", this.s_parsemodel);
            this.m_input.put("BRAND", this.s_parsebrand);
            this.m_input.put("PRODUCT", this.s_parseproduct);
            this.m_input.put("DEVICE", this.s_parsedevice);
            this.m_input.put("RELEASE", this.s_parserelease);
            this.m_input.put("BUILDID", this.s_parsebuildid);
            this.m_input.put("INCREMENTAL", this.s_parseincremental);
            this.lm_input.add(this.m_input);
            this.s_json_old = FileUtil.readFile(this.s_add_prop);
            String json = new Gson().toJson(this.lm_input);
            this.s_data_new = json;
            try {
                JSONArray jSONArray = new JSONArray(this.s_json_old);
                JSONArray jSONArray2 = new JSONArray(json);
                JSONArray jSONArray3 = jSONArray.getJSONObject(0).getJSONArray("DATA");
                for (int i = 0; i < jSONArray2.length(); i++) {
                    jSONArray3.put(jSONArray2.getJSONObject(i));
                }
                this.s_input_json = jSONArray.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String json2 = new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(this.s_input_json));
            this.s_input_json = json2;
            FileUtil.writeFile(this.s_add_prop, json2);
            _onCreateJsonProp(0.0d, this.lm_input);
            _onCreateJsonOri(0.0d, this.lm_input);
            _onCreateJsonRandom(0.0d, this.lm_input);
            _onModelLoadProp();
            return;
        }
        this.til_input_fp.setError("Fingerprint tidak sesuai format");
    }

    public void _setPropType(String str) {
        KeyboardUtils.toggleKeyboardVisibility(requireContext(), this.et_input_dump);
        KeyboardUtils.toggleKeyboardVisibility(requireContext(), this.auto_input_fp);
        KeyboardUtils.toggleKeyboardVisibility(requireContext(), this.auto_input_model);
        this.tv_prop_type.setText(str);
        this.lv_branch_all.setVisibility(8);
        this.ln_input_dump.setVisibility(8);
        this.ln_input_prop.setVisibility(0);
        this.tv_response.setVisibility(8);
        this.rv_1.setVisibility(0);
        this.ln_input_fp.setVisibility(8);
        this.et_input_dump.clearFocus();
        this.auto_input_model.clearFocus();
        this.auto_input_fp.clearFocus();
        if (this.tv_prop_type.getText().toString().equals("Android Dump")) {
            _onLoadDump();
        } else if (this.tv_prop_type.getText().toString().contains("Online Dump")) {
            this.ls_feed_dump.clear();
            if (FileUtil.isExistFile(this.s_fufufu_dump_online)) {
                String readFile = FileUtil.readFile(this.s_fufufu_dump_online);
                this.s_feed_dump = readFile;
                ArrayList arrayList = new ArrayList(Arrays.asList(readFile.split("\n")));
                this.et_input_dump.dismissDropDown();
                this.et_input_dump.setThreshold(1);
                this.et_input_dump.setAdapter(new CustomAdapter(getActivity().getBaseContext(), R.layout.admsoloraya_res_0x7f0d0051, arrayList));
                this.et_input_dump.showDropDown();
            } else {
                this.s_feed_dump = "• Data untuk VIP\n• Input manual";
                this.ls_feed_dump = new ArrayList<>(Arrays.asList(this.s_feed_dump.split("---")));
                this.et_input_dump.setAdapter(new CustomAdapter(getActivity().getBaseContext(), R.layout.admsoloraya_res_0x7f0d0051, this.ls_feed_dump));
                this.et_input_dump.showDropDown();
            }
            _onLoadingOnline("clickmenu");
        } else {
            _onLoadBrand();
        }
        _onCollapseFingerInput("clickmenu");
    }

    public void _onLoadDump() {
        KatrinaPROPDUMP katrinaPROPDUMP = this.katrinaPROPDUMP;
        if (katrinaPROPDUMP == null || katrinaPROPDUMP.getStatus() == AsyncTask.Status.FINISHED) {
            KatrinaPROPDUMP katrinaPROPDUMP2 = new KatrinaPROPDUMP();
            this.katrinaPROPDUMP = katrinaPROPDUMP2;
            katrinaPROPDUMP2.execute(new Void[0]);
        } else if (this.katrinaPROPDUMP.getStatus() == AsyncTask.Status.PENDING) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Proses KatrinaPROPDUMP masih pending");
        }
    }

    /* loaded from: classes5.dex */
    public class KatrinaPROPDUMP extends AsyncTask<Void, Void, Void> {
        public KatrinaPROPDUMP() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            ScritualFragmentActivity.this.rv_1.setVisibility(0);
            ScritualFragmentActivity.this.rv_2.setVisibility(8);
            ScritualFragmentActivity.this.rv_3.setVisibility(8);
            ScritualFragmentActivity.this._fab.setVisibility(8);
            ScritualFragmentActivity.this.pbar_prop.setVisibility(0);
            ScritualFragmentActivity.this.btn_prop.setEnabled(false);
            ScritualFragmentActivity.this.btn_dump.setEnabled(false);
            ScritualFragmentActivity.this.btn_dumpall.setEnabled(false);
            ScritualFragmentActivity.this.tv_title.setText("BRAND");
            ScritualFragmentActivity.this.lm_json_brand.clear();
            if (FileUtil.isExistFile(ScritualFragmentActivity.this.s_add_prop)) {
                ScritualFragmentActivity scritualFragmentActivity = ScritualFragmentActivity.this;
                scritualFragmentActivity.s_custom_prop = FileUtil.readFile(scritualFragmentActivity.s_add_prop);
                ScritualFragmentActivity scritualFragmentActivity2 = ScritualFragmentActivity.this;
                if (scritualFragmentActivity2.jsonIsValid(scritualFragmentActivity2.s_custom_prop)) {
                    ScritualFragmentActivity.this.lm_json_brand = (ArrayList) new Gson().fromJson(ScritualFragmentActivity.this.s_custom_prop, new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.KatrinaPROPDUMP.1
                    }.getType());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            while (!isCancelled()) {
                try {
                    InputStream open = ScritualFragmentActivity.this.getContext().getAssets().open("dump.json");
                    ScritualFragmentActivity.this.lm_json_asset = (ArrayList) new Gson().fromJson(SketchwareUtil.copyFromInputStream(open), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.KatrinaPROPDUMP.2
                    }.getType());
                    ScritualFragmentActivity.this.lm_json_brand.addAll(ScritualFragmentActivity.this.lm_json_asset);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!isCancelled()) {
                    return null;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r4) {
            RecyclerView recyclerView = ScritualFragmentActivity.this.rv_1;
            ScritualFragmentActivity scritualFragmentActivity = ScritualFragmentActivity.this;
            recyclerView.setAdapter(new Rv_1Adapter(scritualFragmentActivity.lm_json_brand));
            ScritualFragmentActivity.this.rv_1.setLayoutManager(new LinearLayoutManager(ScritualFragmentActivity.this.getContext()));
            ScritualFragmentActivity.this.btn_back.setVisibility(8);
            ScritualFragmentActivity.this.btn_prop.setEnabled(true);
            ScritualFragmentActivity.this.btn_dump.setEnabled(true);
            ScritualFragmentActivity.this.btn_dumpall.setEnabled(true);
            ScritualFragmentActivity.this.pbar_prop.setVisibility(8);
        }

        public void cancelKatrinaPROPDUMPTask() {
            cancel(true);
        }
    }

    public void _getPropResult() {
        this.lm_dump_prop.clear();
        this.ls_dump_prop.clear();
        this.s_response_result = removeLines(this.s_response_result);
        this.ls_dump_prop = new ArrayList<>(Arrays.asList(this.s_response_result.split("\n")));
        HashMap<String, Object> hashMap = new HashMap<>();
        this.m_prop = hashMap;
        hashMap.put("ONLINE", "true");
        this.lm_dump_prop.add(this.m_prop);
        this.n = 0.0d;
        for (int i = 0; i < this.ls_dump_prop.size(); i++) {
            if (this.ls_dump_prop.get((int) this.n).contains("manufacturer=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("MANUFACTURER", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("model=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("MODEL", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("version.release=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("RELEASE", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("hardware=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("HARDWARE", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("date=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("DATE", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("date.utc=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("UTC", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("fingerprint=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("FINGERPRINT", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("build.id=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("BUILDID", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("incremental=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("INCREMENTAL", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("bootloader=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("BOOT", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("description=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("DESCRIPTION", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("display.id=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("DISPLAY", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("flavor=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("FLAVOR", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("product=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("PRODUCT", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("host=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("HOST", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("user=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("USER", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("board=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("BOARD", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("brand=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("BRAND", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("device=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("DEVICE", this.s_prop_match);
            } else if (this.ls_dump_prop.get((int) this.n).contains("name=")) {
                this.s_prop_match = this.ls_dump_prop.get((int) this.n).replaceAll(".*=", "");
                this.lm_dump_prop.get(0).put("NAME", this.s_prop_match);
            }
            this.n += 1.0d;
        }
        _onLoadingOnline("clickafterchild");
        _onConvertOnline();
    }

    public void _onResponseError(String str) {
        this.tv_response.setVisibility(0);
        this.tv_response.setText(str);
        this.pbar_prop.setVisibility(8);
    }

    public void _onLoadingOnline(String str) {
        if (str.equals("clickmenu")) {
            this.tv_title.setText(Uri.parse(this.s_dump_model).getLastPathSegment());
            this.rv_1.setVisibility(8);
            this.rv_2.setVisibility(8);
            this.rv_3.setVisibility(8);
            this._fab.setVisibility(8);
            this.btn_back.setVisibility(8);
            this.lv_branch_all.setVisibility(8);
            this.tv_response.setVisibility(8);
            this.pbar_prop.setVisibility(8);
            this.ln_input_prop.setVisibility(8);
            this.ln_input_dump.setVisibility(0);
        } else if (str.equals("clickget")) {
            this.et_input_dump.clearFocus();
            this.tv_title.setText(Uri.parse(this.s_dump_model).getLastPathSegment());
            this.pbar_prop.setVisibility(0);
            this.tv_response.setVisibility(8);
            this.btn_back.setVisibility(8);
        } else if (str.equals("clickafterget")) {
            this.pbar_prop.setVisibility(8);
            this.btn_back.setVisibility(8);
            this.tv_response.setVisibility(8);
            this.lv_branch_all.setVisibility(0);
        } else if (str.equals("clickchild")) {
            this.pbar_prop.setVisibility(0);
            this.btn_back.setVisibility(8);
            this.tv_response.setVisibility(8);
            this.lv_branch_all.setVisibility(8);
        } else if (str.equals("clickafterchild")) {
            this.btn_back.setVisibility(0);
            this.tv_response.setVisibility(8);
            this.pbar_prop.setVisibility(8);
        } else if (str.equals("clicknotfound")) {
            _onResponseError("Prop tidak ditemukan");
        }
    }

    public void _onCollapseFingerInput(String str) {
        if (str.equals("clickbutton")) {
            if (this.ln_input_fp.isExpanded()) {
                this.ln_input_fp.collapse();
                this.btn_collapse_input.setRotation(0.0f);
                return;
            }
            this.ln_input_fp.expand();
            this.btn_collapse_input.setRotation(180.0f);
        } else if (this.ln_input_fp.isExpanded()) {
            this.ln_input_fp.collapse();
            this.btn_collapse_input.setRotation(0.0f);
        }
    }

    public void _onGetDumpOnline() {
        if (this.et_input_dump.getText().toString().contains("/")) {
            KeyboardUtils.toggleKeyboardVisibility(requireContext(), this.et_input_dump);
            String obj = this.et_input_dump.getText().toString();
            this.s_dump_model = obj;
            this.s_input_model = obj.toLowerCase().concat(this.s_dump_all);
            this.s_raw_model = this.s_dump_model.concat(this.s_dump_raw);
            _onLoadingOnline("clickget");
            this.get_branch_all.startRequestNetwork("GET", this.s_dump_head.concat(this.s_input_model), "a", this._get_branch_all_request_listener);
            return;
        }
        SketchwareUtil.showMessage(getContext().getApplicationContext(), "Input tidak valid");
    }

    public void _onConvertOnline() {
        if (!this.lm_dump_prop.get(0).containsKey("MANUFACTURER")) {
            this.lm_dump_prop.get(0).put("MANUFACTURER", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("MODEL")) {
            this.lm_dump_prop.get(0).put("MODEL", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("RELEASE")) {
            this.lm_dump_prop.get(0).put("RELEASE", Build.VERSION.RELEASE);
        }
        if (!this.lm_dump_prop.get(0).containsKey("DATE")) {
            this.lm_dump_prop.get(0).put("DATE", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("UTC")) {
            this.lm_dump_prop.get(0).put("UTC", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("FINGERPRINT")) {
            this.lm_dump_prop.get(0).put("FINGERPRINT", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("BUILDID")) {
            this.lm_dump_prop.get(0).put("BUILDID", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("INCREMENTAL")) {
            this.lm_dump_prop.get(0).put("INCREMENTAL", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("HOST")) {
            this.lm_dump_prop.get(0).put("HOST", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("USER")) {
            this.lm_dump_prop.get(0).put("USER", Build.VERSION.RELEASE);
        }
        if (!this.lm_dump_prop.get(0).containsKey("BRAND")) {
            this.lm_dump_prop.get(0).put("BRAND", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("DEVICE")) {
            this.lm_dump_prop.get(0).put("DEVICE", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("PRODUCT")) {
            this.lm_dump_prop.get(0).put("PRODUCT", this.lm_dump_prop.get(0).get("DEVICE").toString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("NAME")) {
            this.lm_dump_prop.get(0).put("NAME", this.lm_dump_prop.get(0).get("PRODUCT").toString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("SDK")) {
            this.lm_dump_prop.get(0).put("SDK", Build.VERSION.SDK);
        }
        if (!this.lm_dump_prop.get(0).containsKey("HARDWARE")) {
            this.lm_dump_prop.get(0).put("HARDWARE", this.lm_dump_prop.get(0).get("INCREMENTAL").toString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("BOOT")) {
            this.lm_dump_prop.get(0).put("BOOT", this.lm_dump_prop.get(0).get("INCREMENTAL").toString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("FLAVOR")) {
            this.lm_dump_prop.get(0).put("FLAVOR", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("BOARD")) {
            this.lm_dump_prop.get(0).put("BOARD", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("DESCRIPTION")) {
            this.s_prop_incremental = this.lm_dump_prop.get(0).get("INCREMENTAL").toString();
            this.s_prop_release = this.lm_dump_prop.get(0).get("RELEASE").toString();
            this.s_prop_buildid = this.lm_dump_prop.get(0).get("BUILDID").toString();
            String obj = this.lm_dump_prop.get(0).get("NAME").toString();
            this.s_prop_name = obj;
            this.s_prop_description = obj.concat("-user ".concat(this.s_prop_release.concat(" ".concat(this.s_prop_buildid.concat(this.s_prop_incremental.concat(" release-keys"))))));
            this.lm_dump_prop.get(0).put("DESCRIPTION", generateRandomString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("DISPLAY")) {
            this.s_prop_incremental = this.lm_dump_prop.get(0).get("INCREMENTAL").toString();
            String obj2 = this.lm_dump_prop.get(0).get("BUILDID").toString();
            this.s_prop_buildid = obj2;
            this.s_prop_display = obj2.concat(".".concat(this.s_prop_incremental));
            this.lm_dump_prop.get(0).put("DISPLAY", generateRandomString());
        }
        this.lm_json_ori.clear();
        this.lm_json_prop.clear();
        this.lm_json_prop.addAll(this.lm_dump_prop);
        this.s_rv2 = Uri.parse(this.s_dump_model).getLastPathSegment();
        _onCreateJsonOri(0.0d, this.lm_dump_prop);
        _onCreateJsonRandom(0.0d, this.lm_dump_prop);
        _onModelLoadProp();
    }

    public void _showDialogUpdate() {
        showUPDATE();
    }

    private void showUPDATE() {
        if (getActivity() != null) {
            View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d004e, (ViewGroup) null);
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
            materialAlertDialogBuilder.setView(inflate);
            materialAlertDialogBuilder.setCancelable(false);
            TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a05a5);
            TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a05a3);
            final TextView textView3 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a051a);
            TextView textView4 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04bd);
            Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0088);
            Button button2 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a020c);
            this.btn_download = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a008e);
            ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.admsoloraya_res_0x7f0a03e8);
            this.progressBar = progressBar;
            progressBar.setVisibility(8);
            textView.setText(textView.getText().toString().concat(this.lm_release.get(0).get("release_version").toString()));
            textView2.setText(textView2.getText().toString().concat(this.lm_release.get(0).get("release_date").toString()));
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a05a4)).setText(this.lm_release.get(0).get("release_log").toString());
            textView3.setText(this.lm_release.get(0).get("release_url").toString());
            if (this.lm_release.get(0).get("release_force").toString().equals("true")) {
                this.b_update_force = true;
                button.setVisibility(8);
            } else {
                this.b_update_force = false;
                button.setVisibility(0);
                button.setText("Nanti");
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.41
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ScritualFragmentActivity.this.b_update_force || ScritualFragmentActivity.this.UPDATE == null || !ScritualFragmentActivity.this.UPDATE.isShowing()) {
                        return;
                    }
                    ScritualFragmentActivity.this.UPDATE.dismiss();
                }
            });
            this.btn_download.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.42
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ScritualFragmentActivity scritualFragmentActivity = ScritualFragmentActivity.this;
                    scritualFragmentActivity.s_filename = "/data/user/0/".concat(scritualFragmentActivity.getContext().getApplicationContext().getPackageName().concat("/xkatrina.apk"));
                    ScritualFragmentActivity scritualFragmentActivity2 = ScritualFragmentActivity.this;
                    scritualFragmentActivity2.s_url = ((HashMap) scritualFragmentActivity2.lm_release.get(0)).get("release_url").toString();
                    new DownloadTask(ScritualFragmentActivity.this, null).execute(ScritualFragmentActivity.this.s_url);
                }
            });
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.43
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Context context = ScritualFragmentActivity.this.getContext();
                    ScritualFragmentActivity.this.getContext().getApplicationContext();
                    ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", textView3.getText().toString()));
                }
            });
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.44
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ScritualFragmentActivity.this.UPDATE == null || !ScritualFragmentActivity.this.UPDATE.isShowing()) {
                        return;
                    }
                    ScritualFragmentActivity.this.UPDATE.dismiss();
                }
            });
            AlertDialog create = materialAlertDialogBuilder.create();
            this.UPDATE = create;
            create.show();
        }
    }

    /* loaded from: classes5.dex */
    private class DownloadTask extends AsyncTask<String, Integer, Boolean> {
        private static final int BUFFER_SIZE = 1024;

        private DownloadTask() {
        }

        /* synthetic */ DownloadTask(ScritualFragmentActivity scritualFragmentActivity, DownloadTask downloadTask) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:57:0x00aa  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x00bf  */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Boolean doInBackground(java.lang.String... r15) {
            /*
                r14 = this;
                r0 = 0
                java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
                r15 = r15[r0]
                r2 = 0
                java.net.URL r3 = new java.net.URL     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> Lae
                r3.<init>(r15)     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> Lae
                java.net.URLConnection r15 = r3.openConnection()     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> Lae
                java.net.HttpURLConnection r15 = (java.net.HttpURLConnection) r15     // Catch: java.lang.Throwable -> L98 java.lang.Exception -> Lae
                r15.connect()     // Catch: java.lang.Throwable -> L93 java.lang.Exception -> L96
                int r3 = r15.getResponseCode()     // Catch: java.lang.Throwable -> L93 java.lang.Exception -> L96
                r4 = 200(0xc8, float:2.8E-43)
                if (r3 == r4) goto L24
                if (r15 == 0) goto L23
                r15.disconnect()
            L23:
                return r1
            L24:
                int r3 = r15.getContentLength()     // Catch: java.lang.Throwable -> L93 java.lang.Exception -> L96
                java.io.InputStream r4 = r15.getInputStream()     // Catch: java.lang.Throwable -> L93 java.lang.Exception -> L96
                java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> Lb0
                com.fufufu.katrina.backup.ScritualFragmentActivity r6 = com.fufufu.katrina.backup.ScritualFragmentActivity.this     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> Lb0
                java.lang.String r6 = com.fufufu.katrina.backup.ScritualFragmentActivity.access$52(r6)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> Lb0
                r5.<init>(r6)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> Lb0
                r2 = 1024(0x400, float:1.435E-42)
                byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
                r6 = 0
            L3d:
                int r8 = r4.read(r2)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
                r9 = -1
                r10 = 1
                if (r8 != r9) goto L59
                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r10)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
                r5.close()     // Catch: java.io.IOException -> L52
                if (r4 == 0) goto L53
                r4.close()     // Catch: java.io.IOException -> L52
                goto L53
            L52:
            L53:
                if (r15 == 0) goto L58
                r15.disconnect()
            L58:
                return r0
            L59:
                boolean r9 = r14.isCancelled()     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
                if (r9 == 0) goto L72
                r4.close()     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
                r5.close()     // Catch: java.io.IOException -> L6b
                if (r4 == 0) goto L6c
                r4.close()     // Catch: java.io.IOException -> L6b
                goto L6c
            L6b:
            L6c:
                if (r15 == 0) goto L71
                r15.disconnect()
            L71:
                return r1
            L72:
                long r11 = (long) r8
                long r6 = r6 + r11
                if (r3 <= 0) goto L88
                java.lang.Integer[] r9 = new java.lang.Integer[r10]     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
                r10 = 100
                long r10 = r10 * r6
                long r12 = (long) r3     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
                long r10 = r10 / r12
                int r11 = (int) r10     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
                java.lang.Integer r10 = java.lang.Integer.valueOf(r11)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
                r9[r0] = r10     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
                r14.publishProgress(r9)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
            L88:
                r5.write(r2, r0, r8)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8f
                goto L3d
            L8c:
                r0 = move-exception
                r2 = r5
                goto L9b
            L8f:
                r2 = r5
                goto Lb0
            L91:
                r0 = move-exception
                goto L9b
            L93:
                r0 = move-exception
                r4 = r2
                goto L9b
            L96:
                r4 = r2
                goto Lb0
            L98:
                r0 = move-exception
                r15 = r2
                r4 = r15
            L9b:
                if (r2 == 0) goto La3
                r2.close()     // Catch: java.io.IOException -> La1
                goto La3
            La1:
                goto La8
            La3:
                if (r4 == 0) goto La8
                r4.close()     // Catch: java.io.IOException -> La1
            La8:
                if (r15 == 0) goto Lad
                r15.disconnect()
            Lad:
                throw r0
            Lae:
                r15 = r2
                r4 = r15
            Lb0:
                if (r2 == 0) goto Lb8
                r2.close()     // Catch: java.io.IOException -> Lb6
                goto Lb8
            Lb6:
                goto Lbd
            Lb8:
                if (r4 == 0) goto Lbd
                r4.close()     // Catch: java.io.IOException -> Lb6
            Lbd:
                if (r15 == 0) goto Lc2
                r15.disconnect()
            Lc2:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fufufu.katrina.backup.ScritualFragmentActivity.DownloadTask.doInBackground(java.lang.String[]):java.lang.Boolean");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate((Object[]) numArr);
            ScritualFragmentActivity.this.progressBar.setProgress(numArr[0].intValue());
            Button button = ScritualFragmentActivity.this.btn_download;
            button.setText(numArr[0] + "%");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            super.onPostExecute((DownloadTask) bool);
            if (bool.booleanValue()) {
                ScritualFragmentActivity scritualFragmentActivity = ScritualFragmentActivity.this;
                scritualFragmentActivity.s_command = "pm install ".concat(scritualFragmentActivity.s_filename);
                ScritualFragmentActivity.this.s_commandResult = "";
                ScritualFragmentActivity.this.s_exitCode = "";
                ScritualFragmentActivity.this.b_command = false;
                ScritualFragmentActivity.this.b_command = false;
                Shell.Result exec = Shell.cmd(ScritualFragmentActivity.this.s_command).exec();
                List<String> out = exec.getOut();
                exec.getCode();
                ScritualFragmentActivity.this.b_command = exec.isSuccess();
                ScritualFragmentActivity.this.s_commandResult = ScritualFragmentActivity$DownloadTask$$ExternalSyntheticBackport0.m("\n", out);
                return;
            }
            ScritualFragmentActivity.this.fushowToast("Download failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fushowToast(String str) {
        Toast.makeText(requireContext(), str, 0).show();
    }

    public void _onChangeOsPlus(String str, CheckBox checkBox) {
        if (str.equals("down")) {
            this.n_os = Double.parseDouble(this.prefos.getString("OSPLUS", "")) - 1.0d;
            this.prefos.edit().putString("OSPLUS", String.valueOf((long) this.n_os)).commit();
        } else {
            this.n_os = Double.parseDouble(this.prefos.getString("OSPLUS", "")) + 1.0d;
            this.prefos.edit().putString("OSPLUS", String.valueOf((long) this.n_os)).commit();
        }
        ((Rv_3Adapter) this.rv_3.getAdapter()).notifyDataSetChanged();
        this.s_os_plus = this.prefos.getString("OSPLUS", "");
        if (checkBox.isChecked()) {
            this.lm_json_prop.get(0).put("RELEASE", this.s_os_plus);
            if (this.lm_json_editor.get(0).get("FINGERPRINT").toString().equals("true")) {
                this.s_universal = this.lm_json_random.get(0).get("FINGERPRINT").toString().replace("FURELEASEFU", this.s_os_plus);
                this.lm_json_prop.get(0).put("FINGERPRINT", this.s_universal);
            }
            if (this.lm_json_editor.get(0).get("DESCRIPTION").toString().equals("true")) {
                this.s_universal = this.lm_json_random.get(0).get("DESCRIPTION").toString().replace("FURELEASEFU", this.s_os_plus);
                this.lm_json_prop.get(0).put("DESCRIPTION", this.s_universal);
            }
            ((Rv_2Adapter) this.rv_2.getAdapter()).notifyDataSetChanged();
        }
    }

    public void _createJsonEditor() {
        this.lm_json_editor.clear();
        this.m_editor.clear();
        HashMap<String, Object> hashMap = new HashMap<>();
        this.m_editor = hashMap;
        hashMap.put("DEVICE", "false");
        this.m_editor.put("MODEL", "false");
        this.m_editor.put("PRODUCT", "false");
        this.m_editor.put("MANUFACTURER", "false");
        this.m_editor.put("BRAND", "false");
        this.m_editor.put("BOOT", "false");
        this.m_editor.put("BUILDID", "false");
        this.m_editor.put("RELEASE", "false");
        this.m_editor.put("INCREMENTAL", "false");
        this.m_editor.put("DISPLAY", "false");
        this.m_editor.put("FINGERPRINT", "false");
        this.m_editor.put("DESCRIPTION", "false");
        this.m_editor.put("NAME", "false");
        this.m_editor.put("TIMEPICK", "false");
        this.m_editor.put("WIPEGMS", "false");
        this.m_editor.put("SSAID", "false");
        this.m_editor.put("RESET0", "false");
        this.m_editor.put("REBOOT", "false");
        this.m_editor.put("DALVIC", "false");
        this.m_editor.put("NORESTART", "false");
        this.m_editor.put("OS9", "false");
        this.m_editor.put("OS10", "false");
        this.m_editor.put("OS11", "false");
        this.m_editor.put("OS12", "false");
        this.m_editor.put("OS12L", "false");
        this.m_editor.put("OS13", "false");
        this.m_editor.put("OS14", "false");
        this.m_editor.put("OSPLUS", "false");
        this.m_editor.put("MODPESSTART", "false");
        this.m_editor.put("MODPESEND", "false");
        this.lm_json_editor.add(this.m_editor);
    }

    public void _onChipModePes(CheckBox checkBox, final String str, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.45
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ((HashMap) arrayList.get((int) d)).put(str, "true");
                } else {
                    ((HashMap) arrayList.get((int) d)).put(str, "false");
                }
            }
        });
    }

    public void _onCheckSwitchSetting() {
        Animation loadAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.admsoloraya_res_0x7f010023);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(requireContext(), R.anim.admsoloraya_res_0x7f010024);
        if (this.pref.getString("show_fragment", "").equals("show_editor")) {
            this.switch_editor.setText("Hide Editor");
            this.switch_editor.setChecked(true);
            this.ln_bottom.startAnimation(loadAnimation2);
            this.ln_bottom.setVisibility(8);
            this.ln_editor.startAnimation(loadAnimation);
            this.ln_editor.setVisibility(0);
            if (this._fab.getVisibility() == 0) {
                this._fab.setVisibility(8);
            }
            _setFirstUIEditor();
            return;
        }
        this.switch_editor.setText("Show Editor");
        this.switch_editor.setChecked(false);
        this.ln_editor.startAnimation(loadAnimation2);
        this.ln_editor.setVisibility(8);
        this.ln_bottom.startAnimation(loadAnimation);
        this.ln_bottom.setVisibility(0);
        _onLoadBrand();
    }

    public void _setFirstUIEditor() {
        if (this.pref.getString("chip_editor_modpes_start", "").equals("true")) {
            this.mchip_modpes_start.setChecked(true);
        }
        if (this.pref.getString("chip_editor_modpes_end", "").equals("true")) {
            this.mchip_modpes_end.setChecked(true);
        }
        if (this.pref.getString("chip_editor_timepick", "").equals("true")) {
            this.m_timepick.setChecked(true);
        }
        if (this.pref.getString("chip_editor_gms", "").equals("true")) {
            this.m_gms.setChecked(true);
        }
        if (this.pref.getString("chip_editor_ssaid", "").equals("true")) {
            this.m_ssaid.setChecked(true);
        }
        if (this.pref.getString("chip_editor_nol", "").equals("true")) {
            this.m_nol.setChecked(true);
        }
        if (this.pref.getString("chip_editor_reboot", "").equals("true")) {
            this.m_reboot.setChecked(true);
        }
        if (this.pref.getString("chip_editor_dalvic", "").equals("true")) {
            this.m_dalvic.setChecked(true);
        }
        if (this.pref.getString("chip_editor_norestart", "").equals("true")) {
            this.m_norestart.setChecked(true);
        }
        KatrinaREADEXISTPROP katrinaREADEXISTPROP = this.katrinaREADEXISTPROP;
        if (katrinaREADEXISTPROP == null || katrinaREADEXISTPROP.getStatus() == AsyncTask.Status.FINISHED) {
            KatrinaREADEXISTPROP katrinaREADEXISTPROP2 = new KatrinaREADEXISTPROP();
            this.katrinaREADEXISTPROP = katrinaREADEXISTPROP2;
            katrinaREADEXISTPROP2.execute(new Void[0]);
        } else if (this.katrinaREADEXISTPROP.getStatus() == AsyncTask.Status.PENDING) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Proses KatrinaREADEXISTPROP masih pending");
        }
    }

    /* loaded from: classes5.dex */
    public class KatrinaREADEXISTPROP extends AsyncTask<Void, Void, Void> {
        public KatrinaREADEXISTPROP() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            ScritualFragmentActivity.this.lm_editor.clear();
            ScritualFragmentActivity.this.m_systemprop.clear();
            ScritualFragmentActivity scritualFragmentActivity = ScritualFragmentActivity.this;
            scritualFragmentActivity.s_commandGetProp = scritualFragmentActivity.s_commandBase.concat("\ngetkatrinaprop");
            ScritualFragmentActivity.this.rv_4.setVisibility(8);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            while (!isCancelled()) {
                ScritualFragmentActivity.this.b_command = false;
                Shell.Result exec = Shell.cmd(ScritualFragmentActivity.this.s_commandGetProp).exec();
                List<String> out = exec.getOut();
                exec.getCode();
                ScritualFragmentActivity.this.b_command = exec.isSuccess();
                ScritualFragmentActivity.this.s_getkatrinaprop = ScritualFragmentActivity$KatrinaREADEXISTPROP$$ExternalSyntheticBackport0.m("\n", out);
                if (!isCancelled()) {
                    return null;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r4) {
            if (ScritualFragmentActivity.this.s_getkatrinaprop.equals("")) {
                return;
            }
            ScritualFragmentActivity scritualFragmentActivity = ScritualFragmentActivity.this;
            if (!scritualFragmentActivity.jsonIsValid(scritualFragmentActivity.s_getkatrinaprop)) {
                ScritualFragmentActivity.this.rv_4.setVisibility(8);
                return;
            }
            ScritualFragmentActivity.this.m_systemprop = (HashMap) new Gson().fromJson(ScritualFragmentActivity.this.s_getkatrinaprop, new TypeToken<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.KatrinaREADEXISTPROP.1
            }.getType());
            ScritualFragmentActivity.this.lm_editor.add(ScritualFragmentActivity.this.m_systemprop);
            RecyclerView recyclerView = ScritualFragmentActivity.this.rv_4;
            ScritualFragmentActivity scritualFragmentActivity2 = ScritualFragmentActivity.this;
            recyclerView.setAdapter(new Rv_4Adapter(scritualFragmentActivity2.lm_editor));
            ScritualFragmentActivity.this.rv_4.setLayoutManager(new LinearLayoutManager(ScritualFragmentActivity.this.getContext()));
            ScritualFragmentActivity.this.rv_4.setVisibility(0);
        }

        public void cancelKatrinaREADEXISTPROPTask() {
            cancel(true);
        }
    }

    public void _onCheckModule() {
        KatrinaCEKMODULE katrinaCEKMODULE = this.katrinaCEKMODULE;
        if (katrinaCEKMODULE == null || katrinaCEKMODULE.getStatus() == AsyncTask.Status.FINISHED) {
            KatrinaCEKMODULE katrinaCEKMODULE2 = new KatrinaCEKMODULE();
            this.katrinaCEKMODULE = katrinaCEKMODULE2;
            katrinaCEKMODULE2.execute(new Void[0]);
        } else if (this.katrinaCEKMODULE.getStatus() == AsyncTask.Status.PENDING) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Proses KatrinaCEKMODULE masih pending");
        }
    }

    /* loaded from: classes5.dex */
    public class KatrinaCEKMODULE extends AsyncTask<Void, Void, Void> {
        public KatrinaCEKMODULE() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            ScritualFragmentActivity.this.s_commandModule = "if [ -d \"/data/adb/modules/xkatrina_snstv_prps\" ]; then\n    ls /data/adb/modules/xkatrina_snstv_prps\nelse\n    echo \"Direktori tidak ditemukan\"\nfi";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            while (!isCancelled()) {
                ScritualFragmentActivity.this.b_commandModule = false;
                Shell.Result exec = Shell.cmd(ScritualFragmentActivity.this.s_commandModule).exec();
                List<String> out = exec.getOut();
                exec.getCode();
                ScritualFragmentActivity.this.b_commandModule = exec.isSuccess();
                ScritualFragmentActivity.this.s_resultModule = ScritualFragmentActivity$KatrinaCEKMODULE$$ExternalSyntheticBackport0.m("\n", out);
                if (!isCancelled()) {
                    return null;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            ScritualFragmentActivity.this._onModuleInstalled();
        }

        public void cancelKatrinaCEKMODULETask() {
            cancel(true);
        }
    }

    public void _onModuleInstalled() {
        if (getActivity() == null || !this.b_commandModule) {
            return;
        }
        if (this.s_resultModule.contains("resetprop")) {
            this.lottie1.setVisibility(8);
            this.ln_setting_editor.setVisibility(0);
            _onCheckSwitchSetting();
            return;
        }
        this.ln_not_active.setVisibility(0);
        this.ln_setting_editor.setVisibility(8);
        this.ln_editor.setVisibility(8);
        this.ln_bottom.setVisibility(8);
        this._fab.setVisibility(8);
        if (this.s_resultModule.contains("disable")) {
            this.tv_not_active.setText("Module Disable");
            this.tv_note.setText("Aktifkan module XKatrina pada magisk module");
            return;
        }
        this.s_remove_old = "rm -rf /data/adb/modules/XKatrina";
        this.b_command = false;
        Shell.Result exec = Shell.cmd("rm -rf /data/adb/modules/XKatrina").exec();
        List<String> out = exec.getOut();
        exec.getCode();
        this.b_command = exec.isSuccess();
        this.s_remove_old = ScritualFragmentActivity$$ExternalSyntheticBackport0.m("\n", out);
        this.tv_not_active.setText("Module tidak aktif");
        this.tv_note.setText("Silahkan install secara manual module yang ada di /sdcard/xkatrina.zip melalui magisk.\nMatikan module pengubah merek lain di magisk jika ada agar tidak bentrok dengan XKatrina.");
        try {
            InputStream open = getContext().getAssets().open("main.dex");
            FileOutputStream fileOutputStream = new FileOutputStream("/storage/emulated/0/xkatrina.zip");
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    open.close();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void _onCardFavButton(String str) {
        if (this.pref.getString(str, "").equals("true")) {
            this.pref.edit().putString(str, "false").commit();
        } else {
            this.pref.edit().putString(str, "true").commit();
        }
        ((Rv_4Adapter) this.rv_4.getAdapter()).notifyDataSetChanged();
    }

    public void _onRandomAllEditor() {
        if (this.lm_editor.size() == 0) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Tidak ada data untuk di acak");
            return;
        }
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
        if (this.pref.getString("release", "").equals("true")) {
            this.lm_editor.get(0).put("RELEASE", this.s_random_release);
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
            this.s_random_fing = replace5.replace("fufufu6", generateRandomString());
            this.lm_editor.get(0).put("FINGERPRINT", this.s_random_fing);
        }
        if (this.pref.getString("description", "").equals("true")) {
            String replace6 = this.s_random_desc.replace("fufufuwww", generateRandomString());
            this.s_random_desc = replace6;
            String replace7 = replace6.replace("fufufuxxx", this.s_random_release);
            this.s_random_desc = replace7;
            String replace8 = replace7.replace("fufufuyyy", generateRandomString());
            this.s_random_desc = replace8;
            this.s_random_desc = replace8.replace("fufufuzzz", generateRandomString());
            this.lm_editor.get(0).put("DESCRIPTION", this.s_random_desc);
        }
        if (this.pref.getString("displayid", "").equals("true")) {
            this.lm_editor.get(0).put("DISPLAYID", generateRandomString().concat(".".concat(generateRandomString())));
        }
        if (this.pref.getString("date", "").equals("true")) {
            this.lm_editor.get(0).put("DATE", generateRandomDate());
        }
        if (this.pref.getString("utc", "").equals("true")) {
            this.lm_editor.get(0).put("UTC", String.valueOf(SketchwareUtil.getRandom(1558918000, 1658918000)));
        }
        if (this.pref.getString("board", "").equals("true")) {
            this.lm_editor.get(0).put("BOARD", generateRandomString());
        }
        if (this.pref.getString("build.id", "").equals("true")) {
            this.lm_editor.get(0).put("BUILDID", generateRandomString());
        }
        if (this.pref.getString("device", "").equals("true")) {
            this.lm_editor.get(0).put("DEVICE", generateRandomString());
        }
        if (this.pref.getString("host", "").equals("true")) {
            this.lm_editor.get(0).put("HOST", generateRandomString());
        }
        if (this.pref.getString("model", "").equals("true")) {
            this.lm_editor.get(0).put("MODEL", generateRandomString());
        }
        if (this.pref.getString("manufacturer", "").equals("true")) {
            this.lm_editor.get(0).put("MANUFACTURER", generateRandomString());
        }
        if (this.pref.getString("user", "").equals("true")) {
            this.lm_editor.get(0).put("USER", generateRandomString());
        }
        if (this.pref.getString("incremental", "").equals("true")) {
            this.lm_editor.get(0).put("INCREMENTAL", generateRandomString());
        }
        if (this.pref.getString("brand", "").equals("true")) {
            this.lm_editor.get(0).put("BRAND", generateRandomString());
        }
        if (this.pref.getString("name", "").equals("true")) {
            this.lm_editor.get(0).put("NAME", generateRandomString());
        }
        if (this.pref.getString("hardware", "").equals("true")) {
            this.lm_editor.get(0).put("HARDWARE", generateRandomString());
        }
        if (this.pref.getString("product", "").equals("true")) {
            this.lm_editor.get(0).put("PRODUCT", generateRandomString());
        }
        if (this.pref.getString("bootloader", "").equals("true")) {
            this.lm_editor.get(0).put("BOOTLOADER", generateRandomString());
        }
        ((Rv_4Adapter) this.rv_4.getAdapter()).notifyDataSetChanged();
    }

    public void _onStartRitualEditor() {
        _showProgressDialog();
        this.lm_json_editor.clear();
        this.m_editor = new HashMap<>();
        if (this.pref.getString("chip_editor_modpes_end", "").equals("true")) {
            this.m_editor.put("MODPESEND", "true");
        } else {
            this.m_editor.put("MODPESEND", "false");
        }
        if (this.pref.getString("chip_editor_timepick", "").equals("true")) {
            this.m_editor.put("TIMEPICK", "true");
        } else {
            this.m_editor.put("TIMEPICK", "false");
        }
        if (this.pref.getString("chip_editor_gms", "").equals("true")) {
            this.m_editor.put("WIPEGMS", "true");
        } else {
            this.m_editor.put("WIPEGMS", "false");
        }
        if (this.pref.getString("chip_editor_ssaid", "").equals("true")) {
            this.m_editor.put("SSAID", "true");
        } else {
            this.m_editor.put("SSAID", "false");
        }
        if (this.pref.getString("chip_editor_nol", "").equals("true")) {
            this.m_editor.put("RESET0", "true");
        } else {
            this.m_editor.put("RESET0", "false");
        }
        if (this.pref.getString("chip_editor_reboot", "").equals("true")) {
            this.m_editor.put("REBOOT", "true");
        } else {
            this.m_editor.put("REBOOT", "false");
        }
        if (this.pref.getString("chip_editor_dalvic", "").equals("true")) {
            this.m_editor.put("DALVIC", "true");
        } else {
            this.m_editor.put("DALVIC", "false");
        }
        if (this.pref.getString("chip_editor_norestart", "").equals("true")) {
            this.m_editor.put("NORESTART", "true");
        } else {
            this.m_editor.put("NORESTART", "false");
        }
        this.lm_json_editor.add(this.m_editor);
        if (this.pref.getString("chip_editor_modpes_start", "").equals("true")) {
            this.s_commandModpes = "settings put global airplane_mode_on 1\nam broadcast -a android.intent.action.AIRPLANE_MODE";
            this.b_command = false;
            Shell.Result exec = Shell.cmd("settings put global airplane_mode_on 1\nam broadcast -a android.intent.action.AIRPLANE_MODE").exec();
            List<String> out = exec.getOut();
            exec.getCode();
            this.b_command = exec.isSuccess();
            this.s_commandResult = ScritualFragmentActivity$$ExternalSyntheticBackport0.m("\n", out);
        }
        if (this.lm_editor.size() == 0) {
            _onCleanerWipe();
        } else {
            _onEditExistProp();
        }
    }

    public void _onEditExistProp() {
        this.s_prop_device = this.lm_editor.get(0).get("DEVICE").toString();
        this.s_prop_model = this.lm_editor.get(0).get("MODEL").toString();
        this.s_prop_product = this.lm_editor.get(0).get("PRODUCT").toString();
        this.s_prop_name = this.lm_editor.get(0).get("NAME").toString();
        this.s_prop_manufacturer = this.lm_editor.get(0).get("MANUFACTURER").toString();
        this.s_prop_brand = this.lm_editor.get(0).get("BRAND").toString();
        this.s_prop_boot = this.lm_editor.get(0).get("BOOTLOADER").toString();
        this.s_prop_buildid = this.lm_editor.get(0).get("BUILDID").toString();
        this.s_prop_fingerprint = this.lm_editor.get(0).get("FINGERPRINT").toString();
        this.s_prop_description = this.lm_editor.get(0).get("DESCRIPTION").toString();
        this.s_prop_incremental = this.lm_editor.get(0).get("INCREMENTAL").toString();
        this.s_prop_display = this.lm_editor.get(0).get("DISPLAYID").toString();
        this.s_prop_board = this.lm_editor.get(0).get("BOARD").toString();
        this.s_prop_hardware = this.lm_editor.get(0).get("HARDWARE").toString();
        this.s_prop_host = this.lm_editor.get(0).get("HOST").toString();
        this.s_prop_user = this.lm_editor.get(0).get("USER").toString();
        this.s_prop_date = this.lm_editor.get(0).get("DATE").toString();
        this.s_prop_dateutc = this.lm_editor.get(0).get("UTC").toString();
        this.s_prop_release = this.lm_editor.get(0).get("RELEASE").toString();
        _onReplaceProp();
    }

    public void _onCardEditButton(double d, String str, ArrayList<HashMap<String, Object>> arrayList) {
        this.editor_pos = d;
        this.s_editor = str;
        this.s_title_editor = str;
        this.s_dialog_editor = arrayList.get((int) d).get(str).toString();
        showEDITOR();
    }

    private void showEDITOR() {
        if (getActivity() != null) {
            View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0024, (ViewGroup) null);
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
            materialAlertDialogBuilder.setView(inflate);
            materialAlertDialogBuilder.setCancelable(true);
            final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a005c);
            TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a049a);
            autoCompleteTextView.setFocusable(true);
            autoCompleteTextView.setFocusableInTouchMode(true);
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04bd)).setText(this.s_title_editor);
            autoCompleteTextView.setHint(this.s_dialog_editor);
            autoCompleteTextView.setText(this.s_dialog_editor);
            autoCompleteTextView.setSingleLine(true);
            ((Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0086)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.46
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ScritualFragmentActivity.this.EDITOR == null || !ScritualFragmentActivity.this.EDITOR.isShowing()) {
                        return;
                    }
                    ScritualFragmentActivity.this.EDITOR.dismiss();
                }
            });
            ((Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cc)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.47
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ScritualFragmentActivity.this.s_result_editor = autoCompleteTextView.getText().toString();
                    ((HashMap) ScritualFragmentActivity.this.lm_editor.get((int) ScritualFragmentActivity.this.editor_pos)).put(ScritualFragmentActivity.this.s_editor, ScritualFragmentActivity.this.s_result_editor);
                    ((Rv_4Adapter) ScritualFragmentActivity.this.rv_4.getAdapter()).notifyDataSetChanged();
                    if (ScritualFragmentActivity.this.EDITOR == null || !ScritualFragmentActivity.this.EDITOR.isShowing()) {
                        return;
                    }
                    ScritualFragmentActivity.this.EDITOR.dismiss();
                }
            });
            AlertDialog create = materialAlertDialogBuilder.create();
            this.EDITOR = create;
            create.show();
        }
    }

    public void _onCardRandomButton(double d, String str, ArrayList<HashMap<String, Object>> arrayList) {
        this.s_random_desc = "fufufuwww-user fufufuxxx fufufuyyy.fufufuzzz release-keys";
        this.s_random_fing = "fufufu1/fufufu2/fufufu3:fufufu4/fufufu5/fufufu6:user/release-keys";
        this.s_card_random = str;
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
        if (str.equals("RELEASE")) {
            arrayList.get((int) d).put(str, this.s_random_release);
        } else if (str.equals("FINGERPRINT")) {
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
            this.s_random_fing = replace5.replace("fufufu6", generateRandomString());
            arrayList.get((int) d).put(str, this.s_random_fing);
        } else if (str.equals("DESCRIPTION")) {
            String replace6 = this.s_random_desc.replace("fufufuwww", generateRandomString());
            this.s_random_desc = replace6;
            String replace7 = replace6.replace("fufufuxxx", this.s_random_release);
            this.s_random_desc = replace7;
            String replace8 = replace7.replace("fufufuyyy", generateRandomString());
            this.s_random_desc = replace8;
            this.s_random_desc = replace8.replace("fufufuzzz", generateRandomString());
            arrayList.get((int) d).put(str, this.s_random_desc);
        } else if (str.equals("DISPLAYID")) {
            arrayList.get((int) d).put(str, generateRandomString().concat(".".concat(generateRandomString())));
        } else if (str.equals("DATE")) {
            arrayList.get((int) d).put(str, generateRandomDate().replace("WIB", "UTC"));
        } else if (str.equals("UTC")) {
            arrayList.get((int) d).put(str, String.valueOf(SketchwareUtil.getRandom(1558918000, 1658918000)));
        } else {
            arrayList.get((int) d).put(str, generateRandomString());
        }
        ((Rv_4Adapter) this.rv_4.getAdapter()).notifyDataSetChanged();
    }

    /* loaded from: classes5.dex */
    public class Rv_4Adapter extends RecyclerView.Adapter<ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_4Adapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = ScritualFragmentActivity.this.getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0097, (ViewGroup) null);
            inflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            MaterialButton materialButton;
            MaterialButton materialButton2;
            MaterialButton materialButton3;
            MaterialButton materialButton4;
            MaterialButton materialButton5;
            MaterialButton materialButton6;
            MaterialButton materialButton7;
            MaterialButton materialButton8;
            View view = viewHolder.itemView;
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0572);
            TextView textView2 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a055a);
            MaterialButton materialButton9 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00a7);
            MaterialButton materialButton10 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0092);
            MaterialButton materialButton11 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00d2);
            TextView textView3 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0573);
            TextView textView4 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a055b);
            MaterialButton materialButton12 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00a8);
            MaterialButton materialButton13 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0093);
            MaterialButton materialButton14 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00d3);
            TextView textView5 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0574);
            TextView textView6 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a055c);
            MaterialButton materialButton15 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00a9);
            MaterialButton materialButton16 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0094);
            MaterialButton materialButton17 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00d4);
            TextView textView7 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0575);
            TextView textView8 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a055d);
            MaterialButton materialButton18 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00aa);
            MaterialButton materialButton19 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0095);
            MaterialButton materialButton20 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00d5);
            TextView textView9 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0576);
            TextView textView10 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a055e);
            MaterialButton materialButton21 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00ab);
            MaterialButton materialButton22 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0096);
            MaterialButton materialButton23 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00d6);
            TextView textView11 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0577);
            TextView textView12 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a055f);
            MaterialButton materialButton24 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00ac);
            MaterialButton materialButton25 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0097);
            MaterialButton materialButton26 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00d7);
            TextView textView13 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0578);
            TextView textView14 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0560);
            MaterialButton materialButton27 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00ad);
            MaterialButton materialButton28 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0098);
            MaterialButton materialButton29 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00d8);
            TextView textView15 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0579);
            TextView textView16 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0561);
            MaterialButton materialButton30 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00ae);
            MaterialButton materialButton31 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0099);
            MaterialButton materialButton32 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00d9);
            TextView textView17 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a057a);
            TextView textView18 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0562);
            MaterialButton materialButton33 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00af);
            MaterialButton materialButton34 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a009a);
            MaterialButton materialButton35 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00da);
            TextView textView19 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a057b);
            TextView textView20 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0563);
            MaterialButton materialButton36 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00b0);
            MaterialButton materialButton37 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a009b);
            MaterialButton materialButton38 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00db);
            TextView textView21 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a057d);
            TextView textView22 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0565);
            MaterialButton materialButton39 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00b1);
            MaterialButton materialButton40 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a009c);
            MaterialButton materialButton41 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00dc);
            TextView textView23 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a057e);
            TextView textView24 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0566);
            MaterialButton materialButton42 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00b2);
            MaterialButton materialButton43 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a009d);
            MaterialButton materialButton44 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00dd);
            TextView textView25 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a057f);
            TextView textView26 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0567);
            MaterialButton materialButton45 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00b3);
            MaterialButton materialButton46 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a009e);
            MaterialButton materialButton47 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00de);
            TextView textView27 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0580);
            TextView textView28 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0568);
            MaterialButton materialButton48 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00b4);
            MaterialButton materialButton49 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a009f);
            MaterialButton materialButton50 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00df);
            TextView textView29 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0581);
            TextView textView30 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0569);
            MaterialButton materialButton51 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00b5);
            MaterialButton materialButton52 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00a0);
            MaterialButton materialButton53 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00e0);
            TextView textView31 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0582);
            TextView textView32 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a056a);
            MaterialButton materialButton54 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00b6);
            MaterialButton materialButton55 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00a1);
            MaterialButton materialButton56 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00e1);
            TextView textView33 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0583);
            TextView textView34 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a056b);
            MaterialButton materialButton57 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00b7);
            MaterialButton materialButton58 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00a2);
            MaterialButton materialButton59 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00e2);
            TextView textView35 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0584);
            TextView textView36 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a056c);
            MaterialButton materialButton60 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00b8);
            MaterialButton materialButton61 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00a3);
            MaterialButton materialButton62 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00e3);
            TextView textView37 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0585);
            TextView textView38 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a056d);
            MaterialButton materialButton63 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00b9);
            MaterialButton materialButton64 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00a4);
            MaterialButton materialButton65 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00e4);
            if (ScritualFragmentActivity.this.pref.getString("board", "").equals("true")) {
                materialButton9.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton9.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton10.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton11.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("build.id", "").equals("true")) {
                materialButton12.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton12.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton13.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton14.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("displayid", "").equals("true")) {
                materialButton15.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton15.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton16.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton17.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("device", "").equals("true")) {
                materialButton18.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton18.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton19.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton20.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("host", "").equals("true")) {
                materialButton = materialButton21;
                materialButton.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton = materialButton21;
                materialButton.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton22.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton23.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("model", "").equals("true")) {
                materialButton2 = materialButton24;
                materialButton2.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton2 = materialButton24;
                materialButton2.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton25.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton26.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("release", "").equals("true")) {
                materialButton3 = materialButton27;
                materialButton3.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton3 = materialButton27;
                materialButton3.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton28.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton29.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("manufacturer", "").equals("true")) {
                materialButton4 = materialButton30;
                materialButton4.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton4 = materialButton30;
                materialButton4.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton31.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton32.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("user", "").equals("true")) {
                materialButton5 = materialButton33;
                materialButton5.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton5 = materialButton33;
                materialButton5.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton34.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton35.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("brand", "").equals("true")) {
                materialButton6 = materialButton36;
                materialButton6.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton6 = materialButton36;
                materialButton6.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton37.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton38.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("name", "").equals("true")) {
                materialButton7 = materialButton39;
                materialButton7.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton7 = materialButton39;
                materialButton7.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton40.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton41.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("fingerprint", "").equals("true")) {
                materialButton42.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton42.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton43.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton44.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("description", "").equals("true")) {
                materialButton45.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton45.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton46.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton47.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("hardware", "").equals("true")) {
                materialButton48.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton48.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton49.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton50.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("product", "").equals("true")) {
                materialButton51.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton51.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton52.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton53.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("bootloader", "").equals("true")) {
                materialButton54.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton54.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton55.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton56.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("incremental", "").equals("true")) {
                materialButton57.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton57.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton58.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton59.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("date", "").equals("true")) {
                materialButton60.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton60.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton61.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton62.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            if (ScritualFragmentActivity.this.pref.getString("utc", "").equals("true")) {
                materialButton8 = materialButton63;
                materialButton8.setIconResource(R.drawable.admsoloraya_res_0x7f0800e7);
            } else {
                materialButton8 = materialButton63;
                materialButton8.setIconResource(R.drawable.admsoloraya_res_0x7f0800e6);
            }
            materialButton64.setIconResource(R.drawable.admsoloraya_res_0x7f0800e5);
            materialButton65.setIconResource(R.drawable.admsoloraya_res_0x7f0800e8);
            textView.setText("BOARD");
            textView2.setText(this._data.get(i).get("BOARD").toString());
            textView3.setText("BUILDID");
            textView4.setText(this._data.get(i).get("BUILDID").toString());
            textView5.setText("DISPLAYID");
            textView6.setText(this._data.get(i).get("DISPLAYID").toString());
            textView7.setText("DEVICE");
            textView8.setText(this._data.get(i).get("DEVICE").toString());
            textView9.setText("HOST");
            textView10.setText(this._data.get(i).get("HOST").toString());
            textView11.setText("MODEL");
            textView12.setText(this._data.get(i).get("MODEL").toString());
            textView13.setText("RELEASE");
            textView14.setText(this._data.get(i).get("RELEASE").toString());
            textView15.setText("MANUFACTURER");
            textView16.setText(this._data.get(i).get("MANUFACTURER").toString());
            textView17.setText("USER");
            textView18.setText(this._data.get(i).get("USER").toString());
            textView19.setText("BRAND");
            textView20.setText(this._data.get(i).get("BRAND").toString());
            textView21.setText("NAME");
            textView22.setText(this._data.get(i).get("NAME").toString());
            textView23.setText("FINGERPRINT");
            textView24.setText(this._data.get(i).get("FINGERPRINT").toString());
            textView25.setText("DESCRIPTION");
            textView26.setText(this._data.get(i).get("DESCRIPTION").toString());
            textView27.setText("HARDWARE");
            textView28.setText(this._data.get(i).get("HARDWARE").toString());
            textView29.setText("PRODUCT");
            textView30.setText(this._data.get(i).get("PRODUCT").toString());
            textView31.setText("BOOTLOADER");
            textView32.setText(this._data.get(i).get("BOOTLOADER").toString());
            textView33.setText("INCREMENTAL");
            textView34.setText(this._data.get(i).get("INCREMENTAL").toString());
            textView35.setText("DATE");
            textView36.setText(this._data.get(i).get("DATE").toString());
            textView37.setText("UTC");
            textView38.setText(this._data.get(i).get("UTC").toString());
            materialButton9.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("board");
                }
            });
            materialButton12.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("build.id");
                }
            });
            materialButton15.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("displayid");
                }
            });
            materialButton18.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("device");
                }
            });
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("host");
                }
            });
            materialButton2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("model");
                }
            });
            materialButton3.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("release");
                }
            });
            materialButton4.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("manufacturer");
                }
            });
            materialButton5.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("user");
                }
            });
            materialButton6.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("brand");
                }
            });
            materialButton7.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("name");
                }
            });
            materialButton42.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.12
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("fingerprint");
                }
            });
            materialButton45.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("description");
                }
            });
            materialButton48.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.14
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("hardware");
                }
            });
            materialButton51.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.15
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("product");
                }
            });
            materialButton54.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.16
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("bootloader");
                }
            });
            materialButton57.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.17
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("incremental");
                }
            });
            materialButton60.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.18
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("date");
                }
            });
            materialButton8.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.19
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardFavButton("utc");
                }
            });
            materialButton10.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.20
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "BOARD", Rv_4Adapter.this._data);
                }
            });
            materialButton13.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.21
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "BUILDID", Rv_4Adapter.this._data);
                }
            });
            materialButton16.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.22
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "DISPLAYID", Rv_4Adapter.this._data);
                }
            });
            materialButton19.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.23
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "DEVICE", Rv_4Adapter.this._data);
                }
            });
            materialButton22.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.24
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "HOST", Rv_4Adapter.this._data);
                }
            });
            materialButton25.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.25
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "MODEL", Rv_4Adapter.this._data);
                }
            });
            materialButton28.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.26
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "RELEASE", Rv_4Adapter.this._data);
                }
            });
            materialButton31.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.27
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "MANUFACTURER", Rv_4Adapter.this._data);
                }
            });
            materialButton34.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.28
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "USER", Rv_4Adapter.this._data);
                }
            });
            materialButton37.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.29
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "BRAND", Rv_4Adapter.this._data);
                }
            });
            materialButton40.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.30
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "NAME", Rv_4Adapter.this._data);
                }
            });
            materialButton43.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.31
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "FINGERPRINT", Rv_4Adapter.this._data);
                }
            });
            materialButton46.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.32
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "DESCRIPTION", Rv_4Adapter.this._data);
                }
            });
            materialButton49.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.33
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "HARDWARE", Rv_4Adapter.this._data);
                }
            });
            materialButton52.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.34
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "PRODUCT", Rv_4Adapter.this._data);
                }
            });
            materialButton55.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.35
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "BOOTLOADER", Rv_4Adapter.this._data);
                }
            });
            materialButton58.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.36
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "INCREMENTAL", Rv_4Adapter.this._data);
                }
            });
            materialButton61.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.37
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "DATE", Rv_4Adapter.this._data);
                }
            });
            materialButton64.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.38
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardEditButton(i, "UTC", Rv_4Adapter.this._data);
                }
            });
            materialButton11.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.39
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "BOARD", Rv_4Adapter.this._data);
                }
            });
            materialButton14.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.40
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "BUILDID", Rv_4Adapter.this._data);
                }
            });
            materialButton17.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.41
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "DISPLAYID", Rv_4Adapter.this._data);
                }
            });
            materialButton20.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.42
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "DEVICE", Rv_4Adapter.this._data);
                }
            });
            materialButton23.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.43
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "HOST", Rv_4Adapter.this._data);
                }
            });
            materialButton26.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.44
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "MODEL", Rv_4Adapter.this._data);
                }
            });
            materialButton29.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.45
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "RELEASE", Rv_4Adapter.this._data);
                }
            });
            materialButton32.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.46
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "MANUFACTURER", Rv_4Adapter.this._data);
                }
            });
            materialButton35.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.47
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "USER", Rv_4Adapter.this._data);
                }
            });
            materialButton38.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.48
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "BRAND", Rv_4Adapter.this._data);
                }
            });
            materialButton41.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.49
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "NAME", Rv_4Adapter.this._data);
                }
            });
            materialButton44.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.50
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "FINGERPRINT", Rv_4Adapter.this._data);
                }
            });
            materialButton47.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.51
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "DESCRIPTION", Rv_4Adapter.this._data);
                }
            });
            materialButton50.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.52
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "HARDWARE", Rv_4Adapter.this._data);
                }
            });
            materialButton53.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.53
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "PRODUCT", Rv_4Adapter.this._data);
                }
            });
            materialButton56.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.54
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "BOOTLOADER", Rv_4Adapter.this._data);
                }
            });
            materialButton59.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.55
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "INCREMENTAL", Rv_4Adapter.this._data);
                }
            });
            materialButton62.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.56
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "DATE", Rv_4Adapter.this._data);
                }
            });
            materialButton65.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_4Adapter.57
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onCardRandomButton(i, "UTC", Rv_4Adapter.this._data);
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
    public class Lv_branch_allAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_branch_allAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = ScritualFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d005e, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04f2)).setText(this._data.get(i).get("device").toString());
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04f4)).setText("OS    : ".concat(this._data.get(i).get("release").toString()));
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04f1)).setText("Build : ".concat(this._data.get(i).get("buildid").toString()));
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04f3)).setText("Incre : ".concat(this._data.get(i).get("incremental").toString()));
            ((MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a014b)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Lv_branch_allAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onLoadingOnline("clickchild");
                    ScritualFragmentActivity.this.s_url_prop1 = Lv_branch_allAdapter.this._data.get(i).get("device_url1").toString();
                    ScritualFragmentActivity.this.s_url_prop2 = Lv_branch_allAdapter.this._data.get(i).get("device_url2").toString();
                    ScritualFragmentActivity.this.get_branch_child1.startRequestNetwork("GET", ScritualFragmentActivity.this.s_url_prop1, "a", ScritualFragmentActivity.this._get_branch_child1_request_listener);
                }
            });
            return view;
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
            View inflate = ScritualFragmentActivity.this.getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d00ac, (ViewGroup) null);
            inflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            View view = viewHolder.itemView;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04bd);
            TextView textView2 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c8);
            ((MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0380)).setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(ScritualFragmentActivity.this.requireContext()));
            if (this._data.get(i).containsKey("MEREK")) {
                ScritualFragmentActivity.this._onAdvanceBindBrand(linearLayout, textView, textView2, i, this._data);
            } else {
                ScritualFragmentActivity.this._onAdvanceBindModel(linearLayout, textView, textView2, i, this._data);
            }
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
    public class Rv_2Adapter extends RecyclerView.Adapter<ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_2Adapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = ScritualFragmentActivity.this.getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d00ad, (ViewGroup) null);
            inflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            View view = viewHolder.itemView;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0259);
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04eb);
            LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0254);
            TextView textView2 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04bd);
            TextView textView3 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0504);
            TextView textView4 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c8);
            TextView textView5 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a052a);
            TextView textView6 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04d0);
            TextView textView7 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0538);
            TextView textView8 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c5);
            TextView textView9 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a052b);
            TextView textView10 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04d6);
            TextView textView11 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0521);
            TextView textView12 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04d9);
            TextView textView13 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04f5);
            TextView textView14 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04da);
            TextView textView15 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04f0);
            TextView textView16 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04db);
            TextView textView17 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04f6);
            TextView textView18 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c4);
            TextView textView19 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0543);
            TextView textView20 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c3);
            TextView textView21 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0517);
            TextView textView22 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c0);
            TextView textView23 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a054f);
            TextView textView24 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c1);
            TextView textView25 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0508);
            TextView textView26 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c2);
            TextView textView27 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a050e);
            TextView textView28 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04cd);
            TextView textView29 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0501);
            TextView textView30 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04be);
            TextView textView31 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04ef);
            TextView textView32 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04bf);
            TextView textView33 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0512);
            TextView textView34 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04dd);
            TextView textView35 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0513);
            TextView textView36 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04dc);
            TextView textView37 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a05a6);
            TextView textView38 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c7);
            TextView textView39 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a050f);
            TextView textView40 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c9);
            TextView textView41 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0500);
            TextView textView42 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04cb);
            TextView textView43 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a05a8);
            ((MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0380)).setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(ScritualFragmentActivity.this.requireContext()));
            if (this._data.get(i).containsKey("ONLINE")) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
            }
            textView3.setText(this._data.get(i).get("DEVICE").toString());
            textView5.setText(this._data.get(i).get("MODEL").toString());
            textView7.setText(this._data.get(i).get("PRODUCT").toString());
            textView9.setText(this._data.get(i).get("NAME").toString());
            textView11.setText(this._data.get(i).get("MANUFACTURER").toString());
            textView13.setText(this._data.get(i).get("BRAND").toString());
            textView15.setText(this._data.get(i).get("BOOT").toString());
            textView17.setText(this._data.get(i).get("BUILDID").toString());
            textView19.setText(this._data.get(i).get("RELEASE").toString());
            textView21.setText(this._data.get(i).get("INCREMENTAL").toString());
            textView23.setText(this._data.get(i).get("SDK").toString());
            textView25.setText(this._data.get(i).get("DISPLAY").toString());
            textView27.setText(this._data.get(i).get("FINGERPRINT").toString());
            textView29.setText(this._data.get(i).get("DESCRIPTION").toString());
            textView31.setText(this._data.get(i).get("BOARD").toString());
            textView33.setText(this._data.get(i).get("HARDWARE").toString());
            textView35.setText(this._data.get(i).get("HOST").toString());
            textView37.setText(this._data.get(i).get("USER").toString());
            textView39.setText(this._data.get(i).get("FLAVOR").toString());
            textView41.setText(this._data.get(i).get("DATE").toString());
            textView43.setText(this._data.get(i).get("UTC").toString());
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
    public class Rv_3Adapter extends RecyclerView.Adapter<ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_3Adapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = ScritualFragmentActivity.this.getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d00ae, (ViewGroup) null);
            inflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Chip chip;
            Chip chip2;
            Chip chip3;
            Chip chip4;
            Chip chip5;
            Chip chip6;
            Chip chip7;
            Chip chip8;
            Chip chip9;
            Chip chip10;
            Chip chip11;
            Chip chip12;
            Chip chip13;
            Chip chip14;
            Chip chip15;
            Chip chip16;
            Chip chip17;
            Chip chip18;
            Chip chip19;
            Chip chip20;
            View view = viewHolder.itemView;
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04d9);
            ChipGroup chipGroup = (ChipGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            TextView textView2 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04bd);
            ChipGroup chipGroup2 = (ChipGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a025d);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02d3);
            ChipGroup chipGroup3 = (ChipGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a024b);
            TextView textView3 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c8);
            ChipGroup chipGroup4 = (ChipGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a025f);
            TextView textView4 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04d0);
            ChipGroup chipGroup5 = (ChipGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a0261);
            Chip chip21 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0367);
            Chip chip22 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0366);
            Chip chip23 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a032a);
            Chip chip24 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0335);
            Chip chip25 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0331);
            Chip chip26 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0330);
            Chip chip27 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0325);
            Chip chip28 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a032f);
            Chip chip29 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0326);
            Chip chip30 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a032e);
            Chip chip31 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a032b);
            Chip chip32 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a032c);
            Chip chip33 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0329);
            Chip chip34 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0324);
            TextView textView5 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04d6);
            MaterialButton materialButton = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a008d);
            MaterialButton materialButton2 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0103);
            Chip chip35 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0323);
            Chip chip36 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a031c);
            Chip chip37 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a031d);
            Chip chip38 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a031e);
            Chip chip39 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a031f);
            Chip chip40 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0320);
            Chip chip41 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0321);
            Chip chip42 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0322);
            Chip chip43 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0338);
            Chip chip44 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a032d);
            ChipGroup chipGroup6 = (ChipGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a024c);
            Chip chip45 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0337);
            Chip chip46 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0332);
            Chip chip47 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0336);
            Chip chip48 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0328);
            Chip chip49 = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0333);
            chip23.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip24.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip25.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip26.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip27.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip28.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip29.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip30.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip31.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip32.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip33.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip34.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip35.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip36.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip37.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip38.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip39.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip40.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip41.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip42.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip43.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip44.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip45.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip46.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip47.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip48.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip49.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip21.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip22.setTypeface(Typeface.createFromAsset(ScritualFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 0);
            chip42.setText("  OS ".concat(ScritualFragmentActivity.this.prefos.getString("OSPLUS", "").concat("  ")));
            if (this._data.get(i).get("DEVICE").toString().equals("true")) {
                chip23.setChecked(true);
            } else {
                chip23.setChecked(false);
            }
            if (this._data.get(i).get("MODEL").toString().equals("true")) {
                chip26.setChecked(true);
            } else {
                chip26.setChecked(false);
            }
            if (this._data.get(i).get("PRODUCT").toString().equals("true")) {
                chip24.setChecked(true);
            } else {
                chip24.setChecked(false);
            }
            if (this._data.get(i).get("MANUFACTURER").toString().equals("true")) {
                chip28.setChecked(true);
            } else {
                chip28.setChecked(false);
            }
            if (this._data.get(i).get("BRAND").toString().equals("true")) {
                chip27.setChecked(true);
            } else {
                chip27.setChecked(false);
            }
            if (this._data.get(i).get("BOOT").toString().equals("true")) {
                chip34.setChecked(true);
            } else {
                chip34.setChecked(false);
            }
            if (this._data.get(i).get("BUILDID").toString().equals("true")) {
                chip29.setChecked(true);
            } else {
                chip29.setChecked(false);
            }
            if (this._data.get(i).get("INCREMENTAL").toString().equals("true")) {
                chip30.setChecked(true);
            } else {
                chip30.setChecked(false);
            }
            if (this._data.get(i).get("DISPLAY").toString().equals("true")) {
                chip31.setChecked(true);
            } else {
                chip31.setChecked(false);
            }
            if (this._data.get(i).get("FINGERPRINT").toString().equals("true")) {
                chip32.setChecked(true);
            } else {
                chip32.setChecked(false);
            }
            if (this._data.get(i).get("DESCRIPTION").toString().equals("true")) {
                chip = chip33;
                chip.setChecked(true);
            } else {
                chip = chip33;
                chip.setChecked(false);
            }
            Chip chip50 = chip;
            if (this._data.get(i).get("NAME").toString().equals("true")) {
                chip2 = chip25;
                chip2.setChecked(true);
            } else {
                chip2 = chip25;
                chip2.setChecked(false);
            }
            if (this._data.get(i).get("OS9").toString().equals("false")) {
                chip3 = chip30;
                chip4 = chip35;
                chip4.setChecked(false);
            } else {
                chip3 = chip30;
                chip4 = chip35;
                chip4.setChecked(true);
            }
            Chip chip51 = chip4;
            if (this._data.get(i).get("OS10").toString().equals("false")) {
                chip5 = chip36;
                chip5.setChecked(false);
            } else {
                chip5 = chip36;
                chip5.setChecked(true);
            }
            Chip chip52 = chip5;
            if (this._data.get(i).get("OS11").toString().equals("false")) {
                chip6 = chip37;
                chip6.setChecked(false);
            } else {
                chip6 = chip37;
                chip6.setChecked(true);
            }
            Chip chip53 = chip6;
            if (this._data.get(i).get("OS12").toString().equals("false")) {
                chip7 = chip38;
                chip7.setChecked(false);
            } else {
                chip7 = chip38;
                chip7.setChecked(true);
            }
            Chip chip54 = chip7;
            if (this._data.get(i).get("OS12L").toString().equals("false")) {
                chip8 = chip39;
                chip8.setChecked(false);
            } else {
                chip8 = chip39;
                chip8.setChecked(true);
            }
            Chip chip55 = chip8;
            if (this._data.get(i).get("OS13").toString().equals("false")) {
                chip9 = chip40;
                chip9.setChecked(false);
            } else {
                chip9 = chip40;
                chip9.setChecked(true);
            }
            Chip chip56 = chip9;
            if (this._data.get(i).get("OS14").toString().equals("false")) {
                chip10 = chip41;
                chip10.setChecked(false);
            } else {
                chip10 = chip41;
                chip10.setChecked(true);
            }
            if (this._data.get(i).get("OSPLUS").toString().equals("false")) {
                chip11 = chip42;
                chip11.setChecked(false);
            } else {
                chip11 = chip42;
                chip11.setChecked(true);
            }
            Chip chip57 = chip10;
            if (this._data.get(i).get("TIMEPICK").toString().equals("true")) {
                chip12 = chip43;
                chip12.setChecked(true);
            } else {
                chip12 = chip43;
                chip12.setChecked(false);
            }
            Chip chip58 = chip12;
            if (this._data.get(i).get("WIPEGMS").toString().equals("true")) {
                chip13 = chip44;
                chip13.setChecked(true);
            } else {
                chip13 = chip44;
                chip13.setChecked(false);
            }
            Chip chip59 = chip13;
            if (this._data.get(i).get("RESET0").toString().equals("true")) {
                chip14 = chip46;
                chip14.setChecked(true);
            } else {
                chip14 = chip46;
                chip14.setChecked(false);
            }
            Chip chip60 = chip14;
            if (this._data.get(i).get("SSAID").toString().equals("true")) {
                chip15 = chip45;
                chip15.setChecked(true);
            } else {
                chip15 = chip45;
                chip15.setChecked(false);
            }
            Chip chip61 = chip15;
            if (this._data.get(i).get("REBOOT").toString().equals("true")) {
                chip16 = chip47;
                chip16.setChecked(true);
            } else {
                chip16 = chip47;
                chip16.setChecked(false);
            }
            Chip chip62 = chip16;
            if (this._data.get(i).get("DALVIC").toString().equals("true")) {
                chip17 = chip48;
                chip17.setChecked(true);
            } else {
                chip17 = chip48;
                chip17.setChecked(false);
            }
            Chip chip63 = chip17;
            if (this._data.get(i).get("NORESTART").toString().equals("true")) {
                chip18 = chip49;
                chip18.setChecked(true);
            } else {
                chip18 = chip49;
                chip18.setChecked(false);
            }
            Chip chip64 = chip18;
            if (this._data.get(i).get("MODPESSTART").toString().equals("true")) {
                chip19 = chip21;
                chip19.setChecked(true);
            } else {
                chip19 = chip21;
                chip19.setChecked(false);
            }
            Chip chip65 = chip19;
            if (this._data.get(i).get("MODPESEND").toString().equals("true")) {
                chip20 = chip22;
                chip20.setChecked(true);
            } else {
                chip20 = chip22;
                chip20.setChecked(false);
            }
            double d = i;
            ScritualFragmentActivity.this._onChipProp(chip23, "DEVICE", d, this._data);
            ScritualFragmentActivity.this._onChipProp(chip26, "MODEL", d, this._data);
            ScritualFragmentActivity.this._onChipProp(chip24, "PRODUCT", d, this._data);
            ScritualFragmentActivity.this._onChipProp(chip2, "NAME", d, this._data);
            ScritualFragmentActivity.this._onChipProp(chip28, "MANUFACTURER", d, this._data);
            ScritualFragmentActivity.this._onChipProp(chip27, "BRAND", d, this._data);
            ScritualFragmentActivity.this._onChipProp(chip34, "BOOT", d, this._data);
            ScritualFragmentActivity.this._onChipProp(chip29, "BUILDID", d, this._data);
            ScritualFragmentActivity.this._onChipProp(chip3, "INCREMENTAL", d, this._data);
            ScritualFragmentActivity.this._onChipProp(chip31, "DISPLAY", d, this._data);
            ScritualFragmentActivity.this._onChipProp(chip32, "FINGERPRINT", d, this._data);
            ScritualFragmentActivity.this._onChipProp(chip50, "DESCRIPTION", d, this._data);
            ScritualFragmentActivity.this._onChipRelease(chip51, "OS9", this._data, d, "9");
            ScritualFragmentActivity.this._onChipRelease(chip52, "OS10", this._data, d, "10");
            ScritualFragmentActivity.this._onChipRelease(chip53, "OS11", this._data, d, "11");
            ScritualFragmentActivity.this._onChipRelease(chip54, "OS12", this._data, d, "12");
            ScritualFragmentActivity.this._onChipRelease(chip55, "OS12L", this._data, d, "12.1");
            ScritualFragmentActivity.this._onChipRelease(chip56, "OS13", this._data, d, "13");
            ScritualFragmentActivity.this._onChipRelease(chip57, "OS14", this._data, d, "14");
            ScritualFragmentActivity scritualFragmentActivity = ScritualFragmentActivity.this;
            scritualFragmentActivity._onChipRelease(chip11, "OSPLUS", this._data, d, scritualFragmentActivity.prefos.getString("OSPLUS", ""));
            ScritualFragmentActivity.this._onChipClean(chip61, "SSAID", d, this._data);
            ScritualFragmentActivity.this._onChipClean(chip60, "RESET0", d, this._data);
            ScritualFragmentActivity.this._onChipReboot(chip62, "REBOOT", d, this._data);
            ScritualFragmentActivity.this._onChipReboot(chip63, "DALVIC", d, this._data);
            ScritualFragmentActivity.this._onChipReboot(chip64, "NORESTART", d, this._data);
            ScritualFragmentActivity.this._onChipWipe(chip59, "WIPEGMS", d, this._data);
            ScritualFragmentActivity.this._onChipWipe(chip58, "TIMEPICK", d, this._data);
            final Chip chip66 = chip11;
            ScritualFragmentActivity.this._onChipModePes(chip65, "MODPESSTART", d, this._data);
            ScritualFragmentActivity.this._onChipModePes(chip20, "MODPESEND", d, this._data);
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_3Adapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onChangeOsPlus("down", chip66);
                }
            });
            materialButton2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScritualFragmentActivity.Rv_3Adapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScritualFragmentActivity.this._onChangeOsPlus("up", chip66);
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
