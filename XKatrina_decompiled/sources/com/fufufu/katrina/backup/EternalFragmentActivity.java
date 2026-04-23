package com.fufufu.katrina.backup;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fufufu.katrina.backup.RequestNetwork;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.elevation.SurfaceColors;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes5.dex */
public class EternalFragmentActivity extends Fragment {
    private AlertDialog COMPLETE;
    private AlertDialog ETERNAL;
    private AlertDialog ETERNALREAD;
    private RequestNetwork.RequestListener _get_branch_all_request_listener;
    private RequestNetwork.RequestListener _get_branch_child1_request_listener;
    private RequestNetwork.RequestListener _get_branch_child2_request_listener;
    private AutoCompleteTextView auto_input_fp;
    private AutoCompleteTextView auto_input_model;
    private MaterialButton btn_apply;
    private Button btn_back;
    private MaterialButton btn_collapse_input;
    private Button btn_dump;
    private Button btn_dumpall;
    private MaterialButton btn_get_dump;
    private Button btn_prop;
    private AutoCompleteTextView et_input_dump;
    private ExtendedFloatingActionButton extendedfab_eternal;
    private ExtendedFloatingActionButton extendedfab_inject;
    private ExtendedFloatingActionButton extendedfab_mode;
    private RequestNetwork get_branch_all;
    private RequestNetwork get_branch_child1;
    private RequestNetwork get_branch_child2;
    private MaterialButton im_eternal;
    private ImageView im_icon_app;
    private LinearLayout ln_01;
    private LinearLayout ln_02;
    private LinearLayout ln_03;
    private LinearLayout ln_04;
    private LinearLayout ln_05;
    private LinearLayout ln_06;
    private LinearLayout ln_07;
    private LinearLayout ln_08;
    private LinearLayout ln_base_top;
    private LinearLayout ln_input_dump;
    private ExpandableLayout ln_input_fp;
    private LinearLayout ln_input_prop;
    private LinearLayout ln_left;
    private LinearLayout ln_title_input;
    private ListView lv_1;
    private ListView lv_branch_all;
    private MaterialCardView materialcardview1;
    private MaterialCardView materialcardview2;
    private MaterialCardView mcv_icon_app;
    private FloatingActionButton mfab_app;
    private MyPROPDUMP myPROPDUMP;
    private ProgressBar pbar_eternal;
    private ProgressBar pbar_prop;
    private SharedPreferences pref;
    private SharedPreferences pref_eid;
    private SharedPreferences prefall;
    private SharedPreferences prefeternal;
    private SharedPreferences prefui;
    private RecyclerView rv_1;
    private TextInputLayout til_dump;
    private TextInputLayout til_input_fp;
    private TextInputLayout til_input_model;
    private TextView tv_eternal_subtitle;
    private TextView tv_eternal_title;
    private TextView tv_input_fingerprint;
    private TextView tv_prop_type;
    private TextView tv_response;
    private TextView tv_title;
    private HashMap<String, Object> mProp = new HashMap<>();
    private String s_click_brand = "";
    private String s_rv = "";
    private boolean eternal = false;
    private String s_update_prop = "";
    private String s_new_prop = "";
    private String s_command = "";
    private String s_commandResult = "";
    private String s_exitCode = "";
    private String s_prop_result = "";
    private String s_source = "";
    private String s_target = "";
    private String s_eternal_title = "";
    private String s_eternal_message = "";
    private boolean b_command = false;
    private String s_feed_dump = "";
    private String s_add_prop = "";
    private String s_custom_prop = "";
    private String s_dump_model = "";
    private String s_rv2 = "";
    private HashMap<String, Object> m_input = new HashMap<>();
    private String s_parsemodel = "";
    private String s_parsebrand = "";
    private String s_parseproduct = "";
    private String s_parsedevice = "";
    private String s_parserelease = "";
    private String s_parsebuildid = "";
    private String s_parseincremental = "";
    private String s_json_old = "";
    private String s_data_old = "";
    private String s_data_new = "";
    private String s_json_result = "";
    private String s_input_json = "";
    private String s_input_prop = "";
    private String s_add_prop_base = "";
    private String s_input_model = "";
    private String s_dump_all = "";
    private String s_raw_model = "";
    private String s_dump_raw = "";
    private String s_dump_head = "";
    private String s_prop_incremental = "";
    private String s_prop_release = "";
    private String s_prop_buildid = "";
    private String s_prop_name = "";
    private String s_prop_description = "";
    private String s_prop_display = "";
    private String s_response_result = "";
    private HashMap<String, Object> m_prop = new HashMap<>();
    private double n = 0.0d;
    private String s_prop_match = "";
    private HashMap<String, Object> m_branch_all = new HashMap<>();
    private String s_dump_prop2 = "";
    private String s_dump_prop1 = "";
    private String s_url_prop2 = "";
    private String s_url_prop1 = "";
    private String s_prop_branch_all = "";
    private String s_loc = "";
    private String s_commandBase = "";
    private String s_fufufu_dump_online = "";
    private ArrayList<HashMap<String, Object>> lm_prop = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_brand = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_model = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_eternal_app = new ArrayList<>();
    private ArrayList<String> ls_feed_dump = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_json_asset = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_input = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_old_prop = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_final = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_dump_prop = new ArrayList<>();
    private ArrayList<String> ls_dump_prop = new ArrayList<>();
    private ArrayList<String> ls_branch_all = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_branch_all = new ArrayList<>();
    private ObjectAnimator oa1 = new ObjectAnimator();

    public void _EXTRA() {
    }

    public void _EXTRA2() {
    }

    public void _EXTRARANDOM() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d0055, viewGroup, false);
        initialize(bundle, inflate);
        FirebaseApp.initializeApp(getContext());
        initializeLogic();
        return inflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.ln_04 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a025d);
        this.ln_base_top = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0275);
        this.ln_01 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
        this.ln_03 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0259);
        this.mcv_icon_app = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0381);
        this.ln_05 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a025e);
        this.im_eternal = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0214);
        this.pbar_eternal = (ProgressBar) view.findViewById(R.id.admsoloraya_res_0x7f0a03ec);
        this.im_icon_app = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0219);
        this.tv_eternal_title = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a050d);
        this.tv_eternal_subtitle = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a050c);
        this.ln_title_input = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02eb);
        this.ln_input_fp = (ExpandableLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02aa);
        this.ln_06 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a025f);
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
        this.ln_07 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0260);
        this.ln_08 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0261);
        this.btn_apply = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0082);
        this.til_input_model = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a04a3);
        this.til_input_fp = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a04a2);
        this.auto_input_model = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0064);
        this.auto_input_fp = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0063);
        this.ln_left = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02b1);
        this.lv_1 = (ListView) view.findViewById(R.id.admsoloraya_res_0x7f0a030b);
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
        this.mfab_app = (FloatingActionButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0385);
        this.extendedfab_eternal = (ExtendedFloatingActionButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0195);
        this.extendedfab_mode = (ExtendedFloatingActionButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0197);
        this.extendedfab_inject = (ExtendedFloatingActionButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0196);
        this.prefall = getContext().getSharedPreferences("all_app_preferences", 0);
        this.prefeternal = getContext().getSharedPreferences("eternal_preferences", 0);
        this.get_branch_all = new RequestNetwork((Activity) getContext());
        this.get_branch_child1 = new RequestNetwork((Activity) getContext());
        this.get_branch_child2 = new RequestNetwork((Activity) getContext());
        this.pref = getContext().getSharedPreferences("eternal", 0);
        this.pref_eid = getContext().getSharedPreferences("eternal_id", 0);
        this.prefui = getContext().getSharedPreferences("preferences_ui", 0);
        this.im_eternal.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._readEternalFile();
            }
        });
        this.btn_prop.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._setPropType("Termux Prop");
            }
        });
        this.btn_dump.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._setPropType("Android Dump");
            }
        });
        this.btn_dumpall.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._setPropType("Online Dump");
            }
        });
        this.btn_get_dump.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onGetDumpOnline();
            }
        });
        this.btn_collapse_input.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onCollapseFingerInput("clickbutton");
            }
        });
        this.btn_apply.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onConvertProp();
            }
        });
        this.btn_back.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onBackButton();
            }
        });
        this.mfab_app.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._showDialogEternalPicker();
            }
        });
        this.extendedfab_eternal.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onFabEternal();
            }
        });
        this.extendedfab_mode.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onFabMode();
            }
        });
        this.extendedfab_inject.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onInjectEternal();
            }
        });
        this._get_branch_all_request_listener = new RequestNetwork.RequestListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.13
            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> hashMap) {
                if (!str2.contains("Sign in · GitLab")) {
                    EternalFragmentActivity.this.ls_branch_all.clear();
                    EternalFragmentActivity.this.lm_branch_all.clear();
                    EternalFragmentActivity.this.s_response_result = EternalFragmentActivity.removeLinesList(str2);
                    EternalFragmentActivity eternalFragmentActivity = EternalFragmentActivity.this;
                    eternalFragmentActivity.s_response_result = eternalFragmentActivity.s_response_result.replaceAll(".*data-branch-name=\"", "");
                    EternalFragmentActivity eternalFragmentActivity2 = EternalFragmentActivity.this;
                    eternalFragmentActivity2.s_response_result = eternalFragmentActivity2.s_response_result.replaceAll(".*data-default-branch=\"", "");
                    EternalFragmentActivity eternalFragmentActivity3 = EternalFragmentActivity.this;
                    eternalFragmentActivity3.s_response_result = eternalFragmentActivity3.s_response_result.replaceAll("\".*", "");
                    EternalFragmentActivity.this.ls_branch_all = new ArrayList(Arrays.asList(EternalFragmentActivity.this.s_response_result.split("\n")));
                    EternalFragmentActivity.this.n = 0.0d;
                    for (int i = 0; i < EternalFragmentActivity.this.ls_branch_all.size(); i++) {
                        EternalFragmentActivity eternalFragmentActivity4 = EternalFragmentActivity.this;
                        eternalFragmentActivity4.s_prop_branch_all = (String) eternalFragmentActivity4.ls_branch_all.get((int) EternalFragmentActivity.this.n);
                        String[] split = EternalFragmentActivity.this.s_prop_branch_all.split("-", 6);
                        String str3 = split[0];
                        String str4 = split[1];
                        String str5 = split[2];
                        String str6 = split[3];
                        String str7 = split[4];
                        String str8 = split[5];
                        EternalFragmentActivity.this.m_branch_all = new HashMap();
                        EternalFragmentActivity.this.m_branch_all.put("device_url1", EternalFragmentActivity.this.s_dump_head.concat(EternalFragmentActivity.this.s_raw_model.concat(EternalFragmentActivity.this.s_prop_branch_all.concat(EternalFragmentActivity.this.s_dump_prop2))));
                        EternalFragmentActivity.this.m_branch_all.put("device_url2", EternalFragmentActivity.this.s_dump_head.concat(EternalFragmentActivity.this.s_raw_model.concat(EternalFragmentActivity.this.s_prop_branch_all.concat(EternalFragmentActivity.this.s_dump_prop1))));
                        EternalFragmentActivity.this.m_branch_all.put("device", str3);
                        EternalFragmentActivity.this.m_branch_all.put("release", str5);
                        EternalFragmentActivity.this.m_branch_all.put("buildid", str6);
                        EternalFragmentActivity.this.m_branch_all.put("incremental", str7);
                        EternalFragmentActivity.this.lm_branch_all.add(EternalFragmentActivity.this.m_branch_all);
                        EternalFragmentActivity.this.n += 1.0d;
                    }
                    SketchwareUtil.sortListMap(EternalFragmentActivity.this.lm_branch_all, "release", false, true);
                    ListView listView = EternalFragmentActivity.this.lv_branch_all;
                    EternalFragmentActivity eternalFragmentActivity5 = EternalFragmentActivity.this;
                    listView.setAdapter((ListAdapter) new Lv_branch_allAdapter(eternalFragmentActivity5.lm_branch_all));
                    EternalFragmentActivity.this._onLoadingOnline("clickafterget");
                    return;
                }
                EternalFragmentActivity.this._onLoadingOnline("clicknotfound");
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
                EternalFragmentActivity.this._onResponseError("Tidak ada koneksi internet");
            }
        };
        this._get_branch_child1_request_listener = new RequestNetwork.RequestListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.14
            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> hashMap) {
                if (!str2.contains("Not Found")) {
                    EternalFragmentActivity.this.s_response_result = str2;
                    EternalFragmentActivity.this._getPropResult();
                    return;
                }
                EternalFragmentActivity.this.get_branch_child2.startRequestNetwork("GET", EternalFragmentActivity.this.s_url_prop2, "a", EternalFragmentActivity.this._get_branch_child2_request_listener);
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
                EternalFragmentActivity.this._onResponseError(str2);
            }
        };
        this._get_branch_child2_request_listener = new RequestNetwork.RequestListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.15
            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onResponse(String str, String str2, HashMap<String, Object> hashMap) {
                if (!str2.contains("Not Found")) {
                    EternalFragmentActivity.this.s_response_result = str2;
                    EternalFragmentActivity.this._getPropResult();
                    return;
                }
                EternalFragmentActivity.this.tv_response.setVisibility(0);
                EternalFragmentActivity.this.tv_response.setText("fufufu tidak dapat menemukan prop");
            }

            @Override // com.fufufu.katrina.backup.RequestNetwork.RequestListener
            public void onErrorResponse(String str, String str2) {
                EternalFragmentActivity.this._onResponseError(str2);
            }
        };
    }

    private void initializeLogic() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.16
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                if (EternalFragmentActivity.this.getActivity() instanceof KatrinaActivity) {
                    ((KatrinaActivity) EternalFragmentActivity.this.getActivity())._fragmentApp();
                }
            }
        });
        _setFirstUI();
        _setFabAppIcon();
        _checkEternalFile();
        _onLoadBrand();
        _createRandomProp();
    }

    public static String acak(int i) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    protected static String acakString(String str, int i) {
        Random random = new Random();
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = str.charAt(random.nextInt(str.length()));
        }
        return new String(cArr);
    }

    public static String RandomBOARD() {
        return new String[]{acak(4), acak(5), acak(6), acak(7)}[new Random().nextInt(4)];
    }

    public static String RandomBOOTLOADER() {
        return new String[]{acak(6), acak(7), acak(8), acak(9)}[new Random().nextInt(4)];
    }

    public static String RandomBRAND() {
        return new String[]{acak(5), acak(6), acak(7), acak(8)}[new Random().nextInt(4)];
    }

    public static String RandomBUILDID() {
        return new String[]{acak(9), acak(10), acak(11), acak(12)}[new Random().nextInt(4)];
    }

    public static String RandomDEVICE() {
        return new String[]{acak(5), acak(6), acak(7), acak(8)}[new Random().nextInt(4)];
    }

    public static String RandomFLAVOR() {
        return new String[]{acak(7), acak(8), acak(9), acak(10)}[new Random().nextInt(4)];
    }

    public static String RandomHOST() {
        return new String[]{acak(8), acak(9), acak(10), acak(11)}[new Random().nextInt(4)];
    }

    public static String RandomINCREMENTAL() {
        return new String[]{acak(7), acak(8), acak(9), acak(10)}[new Random().nextInt(4)];
    }

    public static String RandomMODEL() {
        return new String[]{acak(7), acak(8), acak(6), acak(5)}[new Random().nextInt(4)];
    }

    public static String RandomPRODUCT() {
        return new String[]{acak(8), acak(5), acak(6), acak(7)}[new Random().nextInt(4)];
    }

    public static String RandomRELEASE() {
        return new String[]{"9", "10", "11", "12", "13", "14"}[new Random().nextInt(6)];
    }

    public static String RandomUSER() {
        return new String[]{acak(10), acak(7), acak(8), acak(9)}[new Random().nextInt(4)];
    }

    public static String RandomMANUFACTURER() {
        return new String[]{acak(10), acak(7), acak(8), acak(9)}[new Random().nextInt(4)];
    }

    public static String RandomSERIAL() {
        return new String[]{acak(7), acak(8), acak(9), acak(10)}[new Random().nextInt(4)];
    }

    public static String RandomSERIAL2() {
        return new String[]{acak(8), acak(9), acak(10), acak(11)}[new Random().nextInt(4)];
    }

    public static String RandomBLUETHOOTNAME() {
        return new String[]{acak(8), acak(9), acak(10), acak(11)}[new Random().nextInt(4)];
    }

    public static String RandomDEVICENAME() {
        return new String[]{acak(8), acak(9), acak(10), acak(11)}[new Random().nextInt(4)];
    }

    public static String RandomHARDWARE() {
        return new String[]{acak(6), acak(4), acak(5)}[new Random().nextInt(3)];
    }

    public static String RandomANDROIDID() {
        return acakString("0123456789abcdef", 16);
    }

    public static String RandomTIME() {
        String str = new String[]{"15", "16"}[new Random().nextInt(2)];
        return String.valueOf(str) + (String.valueOf(acakString("0123456789", 8)) + "000");
    }

    public static String RandomIMEI() {
        String str = new String[]{"35", "86"}[new Random().nextInt(2)];
        String acakString = acakString("0123456789", 13);
        return String.valueOf(str) + acakString;
    }

    public static String RandomIKLANID() {
        return UUID.randomUUID().toString();
    }

    public void _createRandomProp() {
        this.lm_prop.clear();
        HashMap<String, Object> hashMap = new HashMap<>();
        this.mProp = hashMap;
        hashMap.put("IDIKLAN", RandomIKLANID());
        this.mProp.put("IMEI", RandomIMEI());
        this.mProp.put("MODEL", RandomMODEL());
        this.mProp.put("BOARD", RandomBOARD());
        this.mProp.put("TIME", RandomTIME());
        this.mProp.put("ANDROIDID", RandomANDROIDID());
        this.mProp.put("BLUETHOOTNAME", RandomBLUETHOOTNAME());
        this.mProp.put("DEVICENAME", RandomDEVICENAME());
        this.mProp.put("SERIAL", RandomSERIAL());
        this.mProp.put("SERIAL2", RandomSERIAL2());
        this.mProp.put("MANUFACTURER", RandomMANUFACTURER());
        this.mProp.put("USER", RandomUSER());
        this.mProp.put("RELEASE", RandomRELEASE());
        this.mProp.put("DEVICE", RandomDEVICE());
        this.mProp.put("BUILDID", RandomBUILDID());
        this.mProp.put("HARDWARE", RandomHARDWARE());
        this.mProp.put("BOOT", RandomBOOTLOADER());
        this.mProp.put("BRAND", RandomBRAND());
        this.mProp.put("PRODUCT", RandomPRODUCT());
        this.mProp.put("HOST", RandomHOST());
        this.mProp.put("INCREMENTAL", RandomINCREMENTAL());
        this.mProp.put("SDK", Build.VERSION.SDK);
        HashMap<String, Object> hashMap2 = this.mProp;
        hashMap2.put("NAME", hashMap2.get("PRODUCT").toString());
        HashMap<String, Object> hashMap3 = this.mProp;
        hashMap3.put("DISPLAY", hashMap3.get("BUILDID").toString().concat(".".concat(this.mProp.get("INCREMENTAL").toString())));
        HashMap<String, Object> hashMap4 = this.mProp;
        hashMap4.put("RADIOVERSION", hashMap4.get("INCREMENTAL").toString().concat(",".concat(this.mProp.get("INCREMENTAL").toString())));
        this.mProp.put("HTTPAGENT", "Dalvik/2.1.0 (Linux; U; Android " + this.mProp.get("RELEASE") + "; " + this.mProp.get("MODEL") + " Build/" + this.mProp.get("BUILDID") + ")");
        this.mProp.put("USERAGENT", "Mozilla/5.0 (Linux; Android " + this.mProp.get("RELEASE") + "; " + this.mProp.get("MODEL") + " Build/" + this.mProp.get("BUILDID") + "; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/109.0.5414.118 Mobile Safari/537.36");
        this.mProp.put("FINGERPRINT", this.mProp.get("BRAND") + "/" + this.mProp.get("PRODUCT") + "/" + this.mProp.get("DEVICE") + ":" + this.mProp.get("RELEASE") + "/" + this.mProp.get("BUILDID") + "/" + this.mProp.get("INCREMENTAL") + ":user/release-keys");
        StringBuilder sb = new StringBuilder();
        sb.append(this.mProp.get("PRODUCT"));
        sb.append("-user ");
        sb.append(this.mProp.get("RELEASE"));
        sb.append(" ");
        sb.append(this.mProp.get("BUILDID"));
        sb.append(this.mProp.get("INCREMENTAL"));
        sb.append(" release-keys");
        this.mProp.put("DESCRIPTION", sb.toString());
        this.lm_prop.add(this.mProp);
        this.lv_1.setAdapter((ListAdapter) new Lv_1Adapter(this.lm_prop));
        ((BaseAdapter) this.lv_1.getAdapter()).notifyDataSetChanged();
    }

    public void _setFirstUI() {
        this.ln_input_fp.setExpansion(false);
        this.ln_input_fp.setDuration(350);
        this.ln_input_fp.setOrientation(1);
        this.lv_branch_all.setVerticalScrollBarEnabled(false);
        this.eternal = true;
        this.extendedfab_eternal.setText("PROP");
        this.extendedfab_mode.setText("ACAK");
        this.ln_left.setVisibility(8);
        this.pbar_eternal.setVisibility(8);
        this.ln_base_top.setVisibility(8);
        this.lv_branch_all.setVisibility(8);
        this.tv_response.setVisibility(8);
        this.pbar_prop.setVisibility(8);
        this.ln_input_dump.setVisibility(8);
        this.ln_input_prop.setVisibility(0);
        this.auto_input_model.setSingleLine(true);
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
        this.et_input_dump.setSingleLine(true);
        this.et_input_dump.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.17
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 2) {
                    EternalFragmentActivity.this._onGetDumpOnline();
                    return true;
                }
                return false;
            }
        });
    }

    public void _onLoadBrand() {
        this.tv_prop_type.setText("Termux Prop");
        this.lm_json_brand.clear();
        try {
            this.lm_json_brand = (ArrayList) new Gson().fromJson(SketchwareUtil.copyFromInputStream(getContext().getAssets().open("prop.json")), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.18
            }.getType());
            this.rv_1.setAdapter(new Rv_1Adapter(this.lm_json_brand));
            this.rv_1.setLayoutManager(new LinearLayoutManager(getContext()));
            this.btn_back.setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void _onLoadModel() {
        this.lm_json_model.clear();
        this.lm_json_model = (ArrayList) new Gson().fromJson(this.s_click_brand, new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.19
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
        view.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this.s_click_brand = ((HashMap) arrayList.get((int) d)).get("MEREK").toString();
                EternalFragmentActivity.this.s_rv = ((HashMap) arrayList.get((int) d)).get("MEREK").toString();
                EternalFragmentActivity.this.s_click_brand = new Gson().toJson(((HashMap) arrayList.get((int) d)).get("DATA"));
                EternalFragmentActivity.this._onLoadModel();
            }
        });
    }

    public void _onAdvanceBindModel(View view, TextView textView, TextView textView2, final double d, final ArrayList<HashMap<String, Object>> arrayList) {
        int i = (int) d;
        textView.setText(arrayList.get(i).get("DEVICENAME").toString());
        textView2.setText("OS : ".concat(arrayList.get(i).get("RELEASE").toString()));
        view.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EternalFragmentActivity.this._onCreateJsonProp(d, arrayList);
            }
        });
    }

    public void _onBackButton() {
        if (this.btn_back.getText().toString().equals("BACK TO BRAND")) {
            this.rv_1.setAdapter(new Rv_1Adapter(this.lm_json_brand));
            this.rv_1.setLayoutManager(new LinearLayoutManager(getContext()));
            this.tv_title.setText("BRAND");
            this.btn_back.setVisibility(8);
            this.lm_prop.clear();
            ((BaseAdapter) this.lv_1.getAdapter()).notifyDataSetChanged();
        } else if (this.btn_back.getText().toString().equals("BACK TO MODEL")) {
            this.rv_1.setAdapter(new Rv_1Adapter(this.lm_json_model));
            this.rv_1.setLayoutManager(new LinearLayoutManager(getContext()));
            this.btn_back.setText("BACK TO BRAND");
            this.tv_title.setText(this.s_rv);
            this.rv_1.setVisibility(0);
            this.btn_back.setVisibility(0);
        }
    }

    public void _onCreateJsonProp(double d, ArrayList<HashMap<String, Object>> arrayList) {
        this.lm_prop.clear();
        HashMap<String, Object> hashMap = new HashMap<>();
        this.mProp = hashMap;
        hashMap.put("IDIKLAN", RandomIKLANID());
        this.mProp.put("IMEI", RandomIMEI());
        int i = (int) d;
        this.mProp.put("MODEL", arrayList.get(i).get("MODEL").toString());
        this.mProp.put("BOARD", RandomBOARD());
        this.mProp.put("TIME", RandomTIME());
        this.mProp.put("ANDROIDID", RandomANDROIDID());
        this.mProp.put("BLUETHOOTNAME", RandomBLUETHOOTNAME());
        this.mProp.put("DEVICENAME", RandomDEVICENAME());
        this.mProp.put("SERIAL", RandomSERIAL());
        this.mProp.put("SERIAL2", RandomSERIAL2());
        this.mProp.put("MANUFACTURER", arrayList.get(i).get("MANUFACTURER").toString());
        this.mProp.put("USER", RandomUSER());
        this.mProp.put("RELEASE", arrayList.get(i).get("RELEASE").toString());
        this.mProp.put("DEVICE", arrayList.get(i).get("DEVICE").toString());
        this.mProp.put("BUILDID", arrayList.get(i).get("BUILDID").toString());
        this.mProp.put("HARDWARE", RandomHARDWARE());
        this.mProp.put("BOOT", arrayList.get(i).get("INCREMENTAL").toString());
        this.mProp.put("BRAND", arrayList.get(i).get("BRAND").toString());
        this.mProp.put("PRODUCT", arrayList.get(i).get("PRODUCT").toString());
        this.mProp.put("HOST", RandomHOST());
        this.mProp.put("INCREMENTAL", arrayList.get(i).get("INCREMENTAL").toString());
        this.mProp.put("SDK", Build.VERSION.SDK);
        this.mProp.put("NAME", arrayList.get(i).get("PRODUCT").toString());
        HashMap<String, Object> hashMap2 = this.mProp;
        hashMap2.put("DISPLAY", hashMap2.get("BUILDID").toString().concat(".".concat(this.mProp.get("INCREMENTAL").toString())));
        HashMap<String, Object> hashMap3 = this.mProp;
        hashMap3.put("RADIOVERSION", hashMap3.get("INCREMENTAL").toString().concat(",".concat(this.mProp.get("INCREMENTAL").toString())));
        this.mProp.put("HTTPAGENT", "Dalvik/2.1.0 (Linux; U; Android " + this.mProp.get("RELEASE") + "; " + this.mProp.get("MODEL") + " Build/" + this.mProp.get("BUILDID") + ")");
        this.mProp.put("USERAGENT", "Mozilla/5.0 (Linux; Android " + this.mProp.get("RELEASE") + "; " + this.mProp.get("MODEL") + " Build/" + this.mProp.get("BUILDID") + "; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/109.0.5414.118 Mobile Safari/537.36");
        this.mProp.put("FINGERPRINT", this.mProp.get("BRAND") + "/" + this.mProp.get("PRODUCT") + "/" + this.mProp.get("DEVICE") + ":" + this.mProp.get("RELEASE") + "/" + this.mProp.get("BUILDID") + "/" + this.mProp.get("INCREMENTAL") + ":user/release-keys");
        StringBuilder sb = new StringBuilder();
        sb.append(this.mProp.get("PRODUCT"));
        sb.append("-user ");
        sb.append(this.mProp.get("RELEASE"));
        sb.append(" ");
        sb.append(this.mProp.get("BUILDID"));
        sb.append(this.mProp.get("INCREMENTAL"));
        sb.append(" release-keys");
        this.mProp.put("DESCRIPTION", sb.toString());
        this.lm_prop.add(this.mProp);
        this.lv_1.setAdapter((ListAdapter) new Lv_1Adapter(this.lm_prop));
    }

    public void _onFabEternal() {
        if (this.eternal) {
            this.eternal = false;
            this.extendedfab_eternal.setText("AUTO");
            this.extendedfab_mode.setText("UPDATE");
            this.ln_base_top.setVisibility(0);
            this.ln_left.setVisibility(0);
            this.oa1.cancel();
            this.oa1.setTarget(this.ln_left);
            this.oa1.setPropertyName("translationX");
            this.oa1.setFloatValues(-500.0f, 0.0f);
            this.oa1.setDuration(300L);
            this.oa1.start();
            _setPropType("Termux Prop");
            return;
        }
        this.eternal = true;
        this.extendedfab_eternal.setText("PROP");
        this.extendedfab_mode.setText("ACAK");
        this.ln_base_top.setVisibility(8);
        this.ln_left.setVisibility(8);
        _createRandomProp();
    }

    public void _onFabMode() {
        if (this.eternal) {
            _createRandomProp();
        } else if (this.lm_prop.size() == 0) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Harap setting prop");
        } else {
            _onUpdateProp();
        }
    }

    public void _onUpdateProp() {
        String replace = new Gson().toJson(this.lm_prop).replace(this.lm_prop.get(0).get("INCREMENTAL").toString(), RandomINCREMENTAL());
        this.s_update_prop = replace;
        this.s_new_prop = replace.replace(this.lm_prop.get(0).get("HOST").toString(), RandomHOST());
        this.lm_prop.clear();
        this.lm_prop = (ArrayList) new Gson().fromJson(this.s_new_prop, new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.22
        }.getType());
        this.lv_1.setAdapter((ListAdapter) new Lv_1Adapter(this.lm_prop));
        ((BaseAdapter) this.lv_1.getAdapter()).notifyDataSetChanged();
    }

    /* loaded from: classes5.dex */
    public class lv_app_pickerAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public lv_app_pickerAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = EternalFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d0053, (ViewGroup) null);
            }
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a050b);
            ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0215);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0295);
            try {
                imageView.setImageDrawable(EternalFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(((HashMap) EternalFragmentActivity.this.lm_eternal_app.get(i)).get("apppackage").toString()));
            } catch (PackageManager.NameNotFoundException unused) {
            }
            textView.setText(((HashMap) EternalFragmentActivity.this.lm_eternal_app.get(i)).get("appname").toString());
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.lv_app_pickerAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    EternalFragmentActivity.this.prefeternal.edit().putString("choose_app_eternal", ((HashMap) EternalFragmentActivity.this.lm_eternal_app.get(i)).get("appname").toString()).commit();
                    EternalFragmentActivity.this.prefeternal.edit().putString("choose_package_eternal", ((HashMap) EternalFragmentActivity.this.lm_eternal_app.get(i)).get("apppackage").toString()).commit();
                    EternalFragmentActivity.this._setFabAppIcon();
                    EternalFragmentActivity.this._checkEternalFile();
                    if (EternalFragmentActivity.this.ETERNAL == null || !EternalFragmentActivity.this.ETERNAL.isShowing()) {
                        return;
                    }
                    EternalFragmentActivity.this.ETERNAL.dismiss();
                }
            });
            return view;
        }
    }

    public void _showDialogEternalPicker() {
        showETERNAL();
    }

    private void showETERNAL() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0052, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04fb);
        TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a058b);
        ListView listView = (ListView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a030d);
        ArrayList<HashMap<String, Object>> arrayList = (ArrayList) new Gson().fromJson(this.prefall.getString("all_app_eternal", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.23
        }.getType());
        this.lm_eternal_app = arrayList;
        if (arrayList.size() == 0) {
            textView2.setText("Tidak ada aplikasi yang di patch oleh fufufu");
            listView.setVisibility(8);
        } else {
            textView2.setText("Daftar Eternal");
            listView.setVisibility(0);
            listView.setDivider(null);
            listView.setDividerHeight(0);
            listView.setAdapter((ListAdapter) new lv_app_pickerAdapter(this.lm_eternal_app));
            ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
            listView.setVerticalScrollBarEnabled(false);
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EternalFragmentActivity.this.ETERNAL.dismiss();
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.ETERNAL = create;
        create.show();
    }

    public void _setFabAppIcon() {
        this.mcv_icon_app.setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(requireContext()));
        if (this.prefeternal.getString("choose_app_eternal", "").equals("")) {
            this.im_icon_app.setImageResource(R.drawable.admsoloraya_res_0x7f0800dd);
            return;
        }
        try {
            this.im_icon_app.setImageDrawable(getActivity().getPackageManager().getApplicationIcon(this.prefeternal.getString("choose_package_eternal", "")));
        } catch (PackageManager.NameNotFoundException unused) {
        }
        this.tv_eternal_title.setText(this.prefeternal.getString("choose_app_eternal", ""));
        this.tv_eternal_subtitle.setText(this.prefeternal.getString("choose_package_eternal", ""));
    }

    public void _onInjectEternal() {
        if (this.lm_prop.size() == 0) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Harap pilih prop");
        } else if (this.prefeternal.getString("choose_package_eternal", "").equals("")) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Harap pilih aplikasi");
        } else {
            this.extendedfab_inject.setText("LOADING");
            this.s_prop_result = new GsonBuilder().setPrettyPrinting().create().toJson(this.lm_prop);
            try {
                JSONArray jSONArray = new JSONArray(this.s_prop_result);
                SharedPreferences.Editor edit = this.pref_eid.edit();
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        edit.putString(next, jSONObject.getString(next));
                    }
                }
                edit.commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.s_source = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/eternal_id"));
            this.s_target = "/data/user/0/".concat(this.prefeternal.getString("choose_package_eternal", "").concat("/eternal_id"));
            this.s_loc = "/data/user/0/".concat(this.prefeternal.getString("choose_package_eternal", ""));
            FileUtil.writeFile(this.s_source, this.s_prop_result);
            String str = this.s_commandBase;
            this.s_command = str;
            String replace = str.replace("s_loc", this.s_loc);
            this.s_command = replace;
            String replace2 = replace.replace("s_source", this.s_source);
            this.s_command = replace2;
            String replace3 = replace2.replace("s_target", this.s_target);
            this.s_command = replace3;
            String concat = replace3.concat("\neternal");
            this.s_command = concat;
            this.b_command = false;
            Shell.Result exec = Shell.cmd(concat).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            this.b_command = exec.isSuccess();
            this.s_commandResult = EternalFragmentActivity$$ExternalSyntheticBackport0.m("\n", out);
            this.extendedfab_inject.setText("INJECT");
            _checkEternalFile();
            _showDialogComplete();
        }
    }

    public void _showDialogComplete() {
        showCOMPLETE();
    }

    private void showCOMPLETE() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0054, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04fe);
        TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0545);
        Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0079);
        Button button2 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a007a);
        if (this.s_commandResult.equals("\n") || this.s_commandResult.equals("")) {
            textView.setText("Berhasil");
            textView2.setText(this.s_prop_result);
        } else {
            textView.setText("Gagal");
            textView2.setText("// Gagal memproses");
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EternalFragmentActivity.this.COMPLETE.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EternalFragmentActivity eternalFragmentActivity = EternalFragmentActivity.this;
                eternalFragmentActivity._actionOpenPackage(eternalFragmentActivity.prefui.getString("backup_app_package", ""));
                EternalFragmentActivity.this.COMPLETE.dismiss();
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.COMPLETE = create;
        create.show();
    }

    public void _checkEternalFile() {
        if (this.prefeternal.getString("choose_package_eternal", "").equals("")) {
            return;
        }
        this.s_target = "/data/user/0/".concat(this.prefeternal.getString("choose_package_eternal", "").concat("/eternal_id"));
        if (executeCommand(new String[]{"su", "-c", "ls " + this.s_target})) {
            this.im_eternal.setAlpha(1.0f);
        } else {
            this.im_eternal.setAlpha(0.3f);
        }
    }

    private boolean executeCommand(String[] strArr) {
        try {
            return Runtime.getRuntime().exec(strArr).waitFor() == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void _readEternalFile() {
        if (this.prefeternal.getString("choose_package_eternal", "").equals("")) {
            return;
        }
        this.pbar_eternal.setVisibility(0);
        this.im_eternal.setVisibility(8);
        this.s_target = "/data/user/0/".concat(this.prefeternal.getString("choose_package_eternal", "").concat("/eternal_id"));
        if (executeCommand(new String[]{"su", "-c", "ls " + this.s_target})) {
            this.s_eternal_title = "Is Eternal";
            this.b_command = false;
            Shell.Result exec = Shell.cmd("cat ".concat(this.s_target)).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            this.b_command = exec.isSuccess();
            String m = EternalFragmentActivity$$ExternalSyntheticBackport0.m("\n", out);
            this.s_commandResult = m;
            this.s_eternal_message = m;
            this.pbar_eternal.setVisibility(8);
            this.im_eternal.setVisibility(0);
            _showDialogReadEternal();
            return;
        }
        this.s_eternal_title = "Not Eternal";
        this.s_eternal_message = "Eternal belum di inject";
        this.pbar_eternal.setVisibility(8);
        this.im_eternal.setVisibility(0);
        _showDialogReadEternal();
    }

    public void _showDialogReadEternal() {
        showETERNALREAD();
    }

    private void showETERNALREAD() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0054, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04fe)).setText(this.s_eternal_title);
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0545)).setText(this.s_eternal_message);
        ((Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0079)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EternalFragmentActivity.this.ETERNALREAD.dismiss();
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.ETERNALREAD = create;
        create.show();
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

    public void _setPropType(String str) {
        this.lm_prop.clear();
        ((BaseAdapter) this.lv_1.getAdapter()).notifyDataSetChanged();
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
        MyPROPDUMP myPROPDUMP = this.myPROPDUMP;
        if (myPROPDUMP != null && myPROPDUMP.isRunning) {
            this.myPROPDUMP.cancelPROPDUMPTask();
        }
        MyPROPDUMP myPROPDUMP2 = new MyPROPDUMP();
        this.myPROPDUMP = myPROPDUMP2;
        myPROPDUMP2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyPROPDUMP extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyPROPDUMP() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            EternalFragmentActivity.this.rv_1.setVisibility(0);
            EternalFragmentActivity.this.pbar_prop.setVisibility(0);
            EternalFragmentActivity.this.btn_prop.setEnabled(false);
            EternalFragmentActivity.this.btn_dump.setEnabled(false);
            EternalFragmentActivity.this.btn_dumpall.setEnabled(false);
            EternalFragmentActivity.this.tv_title.setText("BRAND");
            EternalFragmentActivity.this.lm_json_brand.clear();
            if (FileUtil.isExistFile(EternalFragmentActivity.this.s_add_prop)) {
                EternalFragmentActivity eternalFragmentActivity = EternalFragmentActivity.this;
                eternalFragmentActivity.s_custom_prop = FileUtil.readFile(eternalFragmentActivity.s_add_prop);
                EternalFragmentActivity eternalFragmentActivity2 = EternalFragmentActivity.this;
                if (eternalFragmentActivity2.jsonIsValid(eternalFragmentActivity2.s_custom_prop)) {
                    EternalFragmentActivity.this.lm_json_brand = (ArrayList) new Gson().fromJson(EternalFragmentActivity.this.s_custom_prop, new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.MyPROPDUMP.1
                    }.getType());
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            try {
                InputStream open = EternalFragmentActivity.this.getContext().getAssets().open("dump.json");
                EternalFragmentActivity.this.lm_json_asset = (ArrayList) new Gson().fromJson(SketchwareUtil.copyFromInputStream(open), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.MyPROPDUMP.2
                }.getType());
                EternalFragmentActivity.this.lm_json_brand.addAll(EternalFragmentActivity.this.lm_json_asset);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r4) {
            this.isRunning = false;
            RecyclerView recyclerView = EternalFragmentActivity.this.rv_1;
            EternalFragmentActivity eternalFragmentActivity = EternalFragmentActivity.this;
            recyclerView.setAdapter(new Rv_1Adapter(eternalFragmentActivity.lm_json_brand));
            EternalFragmentActivity.this.rv_1.setLayoutManager(new LinearLayoutManager(EternalFragmentActivity.this.getContext()));
            EternalFragmentActivity.this.btn_back.setVisibility(8);
            EternalFragmentActivity.this.btn_prop.setEnabled(true);
            EternalFragmentActivity.this.btn_dump.setEnabled(true);
            EternalFragmentActivity.this.btn_dumpall.setEnabled(true);
            EternalFragmentActivity.this.pbar_prop.setVisibility(8);
        }

        public void cancelPROPDUMPTask() {
            cancel(true);
        }
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
            this.btn_back.setVisibility(8);
            this.tv_response.setVisibility(8);
            this.pbar_prop.setVisibility(8);
            this.lv_branch_all.setVisibility(0);
        } else if (str.equals("clicknotfound")) {
            _onResponseError("Prop tidak ditemukan");
        }
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
            return;
        }
        this.til_input_fp.setError("Fingerprint tidak sesuai format");
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
        if (!this.lm_dump_prop.get(0).containsKey("IDIKLAN")) {
            this.lm_dump_prop.get(0).put("IDIKLAN", RandomIKLANID());
        }
        if (!this.lm_dump_prop.get(0).containsKey("RADIOVERSION")) {
            this.lm_dump_prop.get(0).put("RADIOVERSION", this.lm_dump_prop.get(0).get("INCREMENTAL").toString().concat(",".concat(this.lm_dump_prop.get(0).get("INCREMENTAL").toString())));
        }
        if (!this.lm_dump_prop.get(0).containsKey("BLUETHOOTNAME")) {
            this.lm_dump_prop.get(0).put("BLUETHOOTNAME", RandomBLUETHOOTNAME());
        }
        if (!this.lm_dump_prop.get(0).containsKey("SERIAL")) {
            this.lm_dump_prop.get(0).put("SERIAL", RandomSERIAL());
        }
        if (!this.lm_dump_prop.get(0).containsKey("DEVICENAME")) {
            this.lm_dump_prop.get(0).put("DEVICENAME", RandomDEVICENAME());
        }
        if (!this.lm_dump_prop.get(0).containsKey("ANDROIDID")) {
            this.lm_dump_prop.get(0).put("ANDROIDID", RandomANDROIDID());
        }
        if (!this.lm_dump_prop.get(0).containsKey("IMEI")) {
            this.lm_dump_prop.get(0).put("IMEI", RandomIMEI());
        }
        if (!this.lm_dump_prop.get(0).containsKey("TIME")) {
            this.lm_dump_prop.get(0).put("TIME", this.lm_dump_prop.get(0).get("UTC").toString());
        }
        if (!this.lm_dump_prop.get(0).containsKey("SERIAL2")) {
            this.lm_dump_prop.get(0).put("SERIAL2", RandomSERIAL2());
        }
        if (!this.lm_dump_prop.get(0).containsKey("USERAGENT")) {
            this.lm_dump_prop.get(0).put("USERAGENT", "Mozilla/5.0 (Linux; Android " + this.mProp.get("RELEASE") + "; " + this.mProp.get("MODEL") + " Build/" + this.mProp.get("BUILDID") + "; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/109.0.5414.118 Mobile Safari/537.36");
        }
        if (!this.lm_dump_prop.get(0).containsKey("HTTPAGENT")) {
            this.lm_dump_prop.get(0).put("HTTPAGENT", "Dalvik/2.1.0 (Linux; U; Android " + this.mProp.get("RELEASE") + "; " + this.mProp.get("MODEL") + " Build/" + this.mProp.get("BUILDID") + ")");
        }
        this.lm_prop.clear();
        this.lm_prop.addAll(this.lm_dump_prop);
        this.s_rv2 = Uri.parse(this.s_dump_model).getLastPathSegment();
        this.lv_1.setAdapter((ListAdapter) new Lv_1Adapter(this.lm_prop));
        ((BaseAdapter) this.lv_1.getAdapter()).notifyDataSetChanged();
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
            } else if (this.ls_dump_prop.get((int) this.n).contains("release=")) {
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

    public boolean jsonIsValid(String str) {
        new HashMap();
        new ArrayList();
        try {
            try {
                HashMap hashMap = (HashMap) new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.28
                }.getType());
                return true;
            } catch (Exception unused) {
                ArrayList arrayList = (ArrayList) new Gson().fromJson(str, new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.29
                }.getType());
                return true;
            }
        } catch (Exception unused2) {
            return false;
        }
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

    public void _actionOpenPackage(String str) {
        OpenAppDialogFragmentActivity openAppDialogFragmentActivity = new OpenAppDialogFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("package", str);
        openAppDialogFragmentActivity.setArguments(bundle);
        openAppDialogFragmentActivity.show(getActivity().getSupportFragmentManager(), "OpenAppDialogFragmentActivity12");
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = view == null ? EternalFragmentActivity.this.getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0056, (ViewGroup) null) : view;
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a05be);
            LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04bd);
            TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c8);
            TextView textView3 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04d0);
            TextView textView4 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c5);
            TextView textView5 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04d6);
            TextView textView6 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04d9);
            TextView textView7 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04da);
            TextView textView8 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04db);
            TextView textView9 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c4);
            TextView textView10 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c3);
            TextView textView11 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c0);
            TextView textView12 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c1);
            TextView textView13 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c2);
            TextView textView14 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a050e);
            TextView textView15 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04cd);
            TextView textView16 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0501);
            TextView textView17 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04be);
            TextView textView18 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04ef);
            TextView textView19 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04bf);
            TextView textView20 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0512);
            TextView textView21 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04dd);
            TextView textView22 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0513);
            TextView textView23 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04dc);
            TextView textView24 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a05a6);
            TextView textView25 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04cb);
            TextView textView26 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0570);
            TextView textView27 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c7);
            TextView textView28 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0515);
            TextView textView29 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04ce);
            TextView textView30 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0516);
            TextView textView31 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04cf);
            TextView textView32 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04e5);
            TextView textView33 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04d1);
            TextView textView34 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04ee);
            TextView textView35 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04d2);
            TextView textView36 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0505);
            TextView textView37 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04d3);
            TextView textView38 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0550);
            TextView textView39 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04d5);
            TextView textView40 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0551);
            TextView textView41 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04d4);
            TextView textView42 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0540);
            TextView textView43 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04d7);
            TextView textView44 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0514);
            TextView textView45 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04d8);
            View view2 = inflate;
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0504)).setText(this._data.get(i).get("DEVICE").toString());
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a052a)).setText(this._data.get(i).get("MODEL").toString());
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0538)).setText(this._data.get(i).get("PRODUCT").toString());
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a052b)).setText(this._data.get(i).get("NAME").toString());
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0521)).setText(this._data.get(i).get("MANUFACTURER").toString());
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04f5)).setText(this._data.get(i).get("BRAND").toString());
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04f0)).setText(this._data.get(i).get("BOOT").toString());
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04f6)).setText(this._data.get(i).get("BUILDID").toString());
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0543)).setText(this._data.get(i).get("RELEASE").toString());
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0517)).setText(this._data.get(i).get("INCREMENTAL").toString());
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a054f)).setText(this._data.get(i).get("SDK").toString());
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0508)).setText(this._data.get(i).get("DISPLAY").toString());
            textView14.setText(this._data.get(i).get("FINGERPRINT").toString());
            textView16.setText(this._data.get(i).get("DESCRIPTION").toString());
            textView18.setText(this._data.get(i).get("BOARD").toString());
            textView20.setText(this._data.get(i).get("HARDWARE").toString());
            textView22.setText(this._data.get(i).get("HOST").toString());
            textView24.setText(this._data.get(i).get("USER").toString());
            textView28.setText(this._data.get(i).get("IDIKLAN").toString());
            textView26.setText(this._data.get(i).get("TIME").toString());
            textView30.setText(this._data.get(i).get("IMEI").toString());
            textView32.setText(this._data.get(i).get("ANDROIDID").toString());
            textView34.setText(this._data.get(i).get("BLUETHOOTNAME").toString());
            textView36.setText(this._data.get(i).get("DEVICENAME").toString());
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a05a7)).setText(this._data.get(i).get("USERAGENT").toString());
            textView40.setText(this._data.get(i).get("SERIAL2").toString());
            textView38.setText(this._data.get(i).get("SERIAL").toString());
            textView42.setText(this._data.get(i).get("RADIOVERSION").toString());
            textView44.setText(this._data.get(i).get("HTTPAGENT").toString());
            return view2;
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
            LayoutInflater layoutInflater = EternalFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d005e, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04f2)).setText(this._data.get(i).get("device").toString());
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04f4)).setText("OS    : ".concat(this._data.get(i).get("release").toString()));
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04f1)).setText("Build : ".concat(this._data.get(i).get("buildid").toString()));
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04f3)).setText("Incre : ".concat(this._data.get(i).get("incremental").toString()));
            ((MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a014b)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.EternalFragmentActivity.Lv_branch_allAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    EternalFragmentActivity.this._onLoadingOnline("clickchild");
                    EternalFragmentActivity.this.s_url_prop1 = Lv_branch_allAdapter.this._data.get(i).get("device_url1").toString();
                    EternalFragmentActivity.this.s_url_prop2 = Lv_branch_allAdapter.this._data.get(i).get("device_url2").toString();
                    EternalFragmentActivity.this.get_branch_child1.startRequestNetwork("GET", EternalFragmentActivity.this.s_url_prop1, "a", EternalFragmentActivity.this._get_branch_child1_request_listener);
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
            View inflate = EternalFragmentActivity.this.getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d00ac, (ViewGroup) null);
            inflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            View view = viewHolder.itemView;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04bd);
            TextView textView2 = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04c8);
            ((MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0380)).setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(EternalFragmentActivity.this.requireContext()));
            if (this._data.get(i).containsKey("MEREK")) {
                EternalFragmentActivity.this._onAdvanceBindBrand(linearLayout, textView, textView2, i, this._data);
            } else {
                EternalFragmentActivity.this._onAdvanceBindModel(linearLayout, textView, textView2, i, this._data);
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
}
