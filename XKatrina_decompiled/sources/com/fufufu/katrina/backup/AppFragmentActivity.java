package com.fufufu.katrina.backup;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.elevation.SurfaceColors;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.protocol.HTTP;
/* loaded from: classes5.dex */
public class AppFragmentActivity extends Fragment {
    private AlertDialog BOT;
    private AlertDialog EDITNOTE;
    private AlertDialog FILTER;
    private AlertDialog INFOBACKUP;
    private AlertDialog PROSESRESTOR;
    private AlertDialog RAPP;
    private AlertDialog RESTORE;
    private AlertDialog SHOWFOLDER;
    private AlertDialog UAPP;
    private AlertDialog UNIVERSAL;
    private AlertDialog UPDATE;
    private AlertDialog WAPP;
    private FloatingActionButton _fab;
    private AutoCompleteTextView auto_app;
    private AutoCompleteTextView auto_backup;
    private Button btn_download;
    private AlertDialog customDialog3;
    private MaterialCardView cv_favorite;
    private MaterialCardView cv_search_type;
    private FloatingActionButton fab2;
    private FloatingActionButton fabeternal;
    private ImageView im_app_ritual;
    private ImageView im_empty;
    private ImageView im_favorite;
    private ImageView im_search_type;
    private LinearLayout ln_02;
    private LinearLayout ln_03;
    private LinearLayout ln_04;
    private LinearLayout ln_06;
    private LinearLayout ln_07;
    private LinearLayout ln_backup;
    private LinearLayout ln_backup_empty;
    private LinearLayout ln_base;
    private LinearLayout ln_progressbar;
    private LinearLayout ln_recycle_view;
    private LinearLayout ln_ritual;
    private LinearLayout ln_ritual_top;
    private LinearLayout ln_search_bar;
    private LinearLayout ln_search_view;
    private LottieAnimationView lottie1;
    private ListView lv_app;
    private ListView lv_backup;
    private ListView lv_fav;
    private MaterialCardView materialcardview1;
    private Chip mchip_info;
    private Chip mchip_loc;
    private Chip mchip_slot;
    private Chip mchip_title;
    private Chip mchip_total;
    private MaterialCardView mcv_ritual;
    private MyAPPLIST myAPPLIST;
    private MyBackgroundAction myBackgroundAction;
    private MyREADBACKUP myREADBACKUP;
    private MyREADFOLDER myREADFOLDER;
    private MyRITUALREINSTALL myRITUALREINSTALL;
    private MyRITUALTIMEPICK myRITUALTIMEPICK;
    private MyRITUALWIPEDATA myRITUALWIPEDATA;
    private MyRITUALWIPEGMS myRITUALWIPEGMS;
    private MySTARTSORTIR mySTARTSORTIR;
    private ProgressBar pbar_ritual;
    private SharedPreferences prefall;
    private SharedPreferences preffav;
    private SharedPreferences preflast;
    private SharedPreferences prefrelease;
    private SharedPreferences prefui;
    private SharedPreferences prefuser;
    private ProgressBar progressBar;
    private Runnable runnableOnRestore;
    private Runnable runnableREINSTALLAPP;
    private Runnable runnableUNINSTALLAPP;
    private Runnable runnableWIPEAPP;
    private Runnable runnablefolderone;
    private Runnable runnablefoldertri;
    private Runnable runnablefoldertwo;
    private MaterialSwitch switch_gms;
    private MaterialSwitch switch_reinstall;
    private MaterialSwitch switch_timepick;
    private MaterialSwitch switch_wipe;
    private TextInputLayout til_app;
    private TextInputLayout til_backup;
    private TextView tv_name_ritual;
    private TextView tv_ritual_result;
    private TextView tv_status;
    private TextView tv_versi_ritual;
    private Vibrator vibrate;
    private ScrollView vscr_ritual;
    private HashMap<String, Object> m_app = new HashMap<>();
    private double n_pos = 0.0d;
    private HashMap<String, Object> m_search_backup = new HashMap<>();
    private HashMap<String, Object> m_fav = new HashMap<>();
    private boolean b_fav = false;
    private String s_fav_app = "";
    private String s_backup_app = "";
    private String s_backup_loc = "";
    private HashMap<String, Object> m_sort_result = new HashMap<>();
    private String s_fname = "";
    private double n1 = 0.0d;
    private double n2 = 0.0d;
    private double n3 = 0.0d;
    private HashMap<String, Object> m_json_app = new HashMap<>();
    private String s_filePath = "";
    private HashMap<String, Object> m_search_app = new HashMap<>();
    private String s_path_json = "";
    private HashMap<String, Object> m_path_json = new HashMap<>();
    private String s_folder_picker = "";
    private boolean b_folder_scan = false;
    private String s_note = "";
    private double n_position = 0.0d;
    private double n_backup_number = 0.0d;
    private String s_command = "";
    private boolean b_command = false;
    private String s_commandResult = "";
    private String s_scbase = "";
    private String s_exe1 = "";
    private String s_exe2 = "";
    private String s_exe3 = "";
    private String s_universal_progress = "";
    private double n_position_app = 0.0d;
    private String s_sdk = "";
    private String s_restore_loc = "";
    private String s_restore_ssaid = "";
    private String s_restore_prop = "";
    private String s_restore_sdk = "";
    private boolean b_ssaid = false;
    private boolean b_prop = false;
    private double n_restore_position = 0.0d;
    private String s_null = "";
    private String s_top_appname = "";
    private boolean b_update_force = false;
    private String s_url = "";
    private String s_commandBase = "";
    private boolean b_ritual = false;
    private String s_ritual_app = "";
    private String s_package_ritual = "";
    private String s_commandBaseRestore = "";
    private boolean b_rebackup = false;
    private String s_usia_backup = "";
    private String s_file_size = "";
    private String s_date_backup = "";
    private String s_backupLocation = "";
    private HashMap<String, Object> m_export = new HashMap<>();
    private boolean b_custom = false;
    private double n_restore_pos_app = 0.0d;
    private String s_extra = "";
    private HashMap<String, Object> m_extra = new HashMap<>();
    private String s_progressfile = "";
    private String s_totalfile = "";
    private String s_filename = "";
    private String s_exitCode = "";
    private String s_totalsize = "";
    private String s_namefile = "";
    private boolean b_restore = false;
    private String s_filterbackup = "";
    private String s_cek_folder = "";
    private String s_cek_sortir = "";
    private String s_backup_number = "";
    private double n_filter = 0.0d;
    private double n_shortfilter = 0.0d;
    private String s_string = "";
    private String s_string_folder = "";
    private String s_shortslot = "";
    private String s_markcolor = "";
    private ArrayList<HashMap<String, Object>> lm_all_app = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_search_backup = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_fav_app = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_backup_data = new ArrayList<>();
    private ArrayList<String> ls_sort_1 = new ArrayList<>();
    private ArrayList<String> ls_sort_2 = new ArrayList<>();
    private ArrayList<String> ls_sort_3 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_search_app = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_folder_picker = new ArrayList<>();
    private ArrayList<String> ls_folder_picker = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_release = new ArrayList<>();
    private ArrayList<String> ls_allapps = new ArrayList<>();
    private ArrayList<String> ls_eternal = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_eternal = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_picked = new ArrayList<>();
    private ArrayList<String> ls_backupbot = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_eternal_app = new ArrayList<>();
    private Calendar c = Calendar.getInstance();
    private ObjectAnimator oa1 = new ObjectAnimator();
    private ObjectAnimator oa2 = new ObjectAnimator();
    private Intent intentcustom = new Intent();
    private Handler folderone = new Handler();
    private Handler foldertwo = new Handler();
    private Handler foldertri = new Handler();
    private Handler UNINSTALLAPP = new Handler();
    private Handler REINSTALLAPP = new Handler();
    private Handler WIPEAPP = new Handler();
    private Handler OnRestore = new Handler();

    public void _EXTRA() {
    }

    public void _onStartDownload() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d001e, viewGroup, false);
        initialize(bundle, inflate);
        FirebaseApp.initializeApp(getContext());
        initializeLogic();
        return inflate;
    }

    private void initialize(Bundle bundle, View view) {
        this._fab = (FloatingActionButton) view.findViewById(R.id.admsoloraya_res_0x7f0a000f);
        this.ln_base = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0270);
        this.ln_progressbar = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02ca);
        this.ln_recycle_view = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02d2);
        this.ln_04 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a025d);
        this.ln_ritual = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02d6);
        this.ln_backup = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a026c);
        this.cv_favorite = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a014c);
        this.lv_app = (ListView) view.findViewById(R.id.admsoloraya_res_0x7f0a030c);
        this.lv_fav = (ListView) view.findViewById(R.id.admsoloraya_res_0x7f0a0310);
        this.im_favorite = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0216);
        this.ln_ritual_top = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02d7);
        this.switch_wipe = (MaterialSwitch) view.findViewById(R.id.admsoloraya_res_0x7f0a0474);
        this.switch_reinstall = (MaterialSwitch) view.findViewById(R.id.admsoloraya_res_0x7f0a0470);
        this.switch_gms = (MaterialSwitch) view.findViewById(R.id.admsoloraya_res_0x7f0a046e);
        this.switch_timepick = (MaterialSwitch) view.findViewById(R.id.admsoloraya_res_0x7f0a0472);
        this.tv_status = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0558);
        this.pbar_ritual = (ProgressBar) view.findViewById(R.id.admsoloraya_res_0x7f0a03f2);
        this.vscr_ritual = (ScrollView) view.findViewById(R.id.admsoloraya_res_0x7f0a05c2);
        this.ln_06 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a025f);
        this.mcv_ritual = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0382);
        this.ln_07 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0260);
        this.im_app_ritual = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0208);
        this.tv_name_ritual = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a052c);
        this.tv_versi_ritual = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a05a9);
        this.tv_ritual_result = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0547);
        this.ln_search_bar = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02d9);
        this.ln_03 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0259);
        this.ln_02 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0254);
        this.lv_backup = (ListView) view.findViewById(R.id.admsoloraya_res_0x7f0a030e);
        this.ln_backup_empty = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a026f);
        this.materialcardview1 = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0354);
        this.ln_search_view = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02da);
        this.til_app = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a049e);
        this.til_backup = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a049f);
        this.cv_search_type = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a014e);
        this.auto_app = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a005d);
        this.auto_backup = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0060);
        this.im_search_type = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0220);
        this.mchip_title = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a036f);
        this.mchip_info = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0361);
        this.mchip_slot = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a036d);
        this.mchip_loc = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0364);
        this.mchip_total = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0370);
        this.im_empty = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0212);
        this.lottie1 = (LottieAnimationView) view.findViewById(R.id.admsoloraya_res_0x7f0a0308);
        this.fab2 = (FloatingActionButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0199);
        this.fabeternal = (FloatingActionButton) view.findViewById(R.id.admsoloraya_res_0x7f0a019c);
        this.prefui = getContext().getSharedPreferences("preferences_ui", 0);
        this.preffav = getContext().getSharedPreferences("preferences_fav", 0);
        this.preflast = getContext().getSharedPreferences("preferences_last", 0);
        this.prefall = getContext().getSharedPreferences("all_app_preferences", 0);
        this.prefrelease = getContext().getSharedPreferences("release_preference", 0);
        this.vibrate = (Vibrator) getContext().getSystemService("vibrator");
        this.prefuser = getContext().getSharedPreferences("user_preferences", 0);
        this.cv_favorite.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._setFavoriteApp();
            }
        });
        this.switch_wipe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_wipe_app", "true").commit();
                } else {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_wipe_app", "false").commit();
                }
            }
        });
        this.switch_reinstall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_reinstall_app", "true").commit();
                } else {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_reinstall_app", "false").commit();
                }
            }
        });
        this.switch_gms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_wipe_gms", "true").commit();
                } else {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_wipe_gms", "false").commit();
                }
            }
        });
        this.switch_timepick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_clean_timepick", "true").commit();
                } else {
                    AppFragmentActivity.this.prefui.edit().putString("ritual_clean_timepick", "false").commit();
                }
            }
        });
        this.im_app_ritual.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity._actionOpenPackage(appFragmentActivity.prefui.getString("backup_app_package", ""));
            }
        });
        this.cv_search_type.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._setSearchType();
            }
        });
        this.auto_app.addTextChangedListener(new TextWatcher() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.8
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                AppFragmentActivity.this._onSearchApp(charSequence.toString());
            }
        });
        this.auto_backup.addTextChangedListener(new TextWatcher() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.9
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                AppFragmentActivity.this._onSearchBackup(charSequence.toString());
            }
        });
        this.mchip_title.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity._actionOpenPackage(appFragmentActivity.prefui.getString("backup_app_package", ""));
            }
        });
        this.mchip_info.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity._actionInfoPackage(appFragmentActivity.prefui.getString("backup_app_package", ""));
            }
        });
        this.mchip_slot.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.12
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view2) {
                AppFragmentActivity.this._startSorterBackup();
                return true;
            }
        });
        this.mchip_slot.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SketchwareUtil.showMessage(AppFragmentActivity.this.getContext().getApplicationContext(), "Klik lama untuk mengurutkan ulang nomer slot backup dan mengisi slot kosong.");
            }
        });
        this.mchip_loc.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._showFolderPicker();
            }
        });
        this.mchip_total.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._showDialogFilter();
            }
        });
        this.fab2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (AppFragmentActivity.this.b_ritual) {
                    AppFragmentActivity.this.b_rebackup = false;
                    AppFragmentActivity.this._onAppRitual();
                    return;
                }
                AppFragmentActivity.this.b_rebackup = false;
                AppFragmentActivity.this._onCreateBackup();
            }
        });
        this.fabeternal.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (AppFragmentActivity.this.getActivity() instanceof KatrinaActivity) {
                    ((KatrinaActivity) AppFragmentActivity.this.getActivity())._fragmentEternal();
                }
            }
        });
        this._fab.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._showDialogRitual();
            }
        });
        this.oa1.addListener(new Animator.AnimatorListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.19
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
                AppFragmentActivity.this.oa2.cancel();
                AppFragmentActivity.this.oa2.setTarget(AppFragmentActivity.this.cv_favorite);
                AppFragmentActivity.this.oa2.setPropertyName("alpha");
                AppFragmentActivity.this.oa2.setFloatValues(0.0f, 1.0f);
                AppFragmentActivity.this.oa2.setDuration(300L);
                AppFragmentActivity.this.oa2.start();
            }
        });
        this.oa2.addListener(new Animator.AnimatorListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.20
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
                AppFragmentActivity.this.cv_favorite.setVisibility(0);
            }
        });
    }

    private void initializeLogic() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) { // from class: com.fufufu.katrina.backup.AppFragmentActivity.21
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
            }
        });
        _setFirstUI();
        _getAppList();
        _setFromPreferences();
        new Handler().postDelayed(new Runnable() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.22
            @Override // java.lang.Runnable
            public void run() {
                if (AppFragmentActivity.this.prefrelease.getString("release", "").equals("")) {
                    return;
                }
                AppFragmentActivity.this.lm_release = (ArrayList) new Gson().fromJson(AppFragmentActivity.this.prefrelease.getString("release", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.22.1
                }.getType());
                AppFragmentActivity.this._showDialogUpdate();
            }
        }, 3000L);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        _setAppPosition();
        _setBackupPosition();
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
            LayoutInflater layoutInflater = AppFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d0028, (ViewGroup) null);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a01f1);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04bd)).setText(Uri.parse(((HashMap) AppFragmentActivity.this.lm_folder_picker.get(i)).get("folder").toString()).getLastPathSegment());
            if (FileUtil.isDirectory(((HashMap) AppFragmentActivity.this.lm_folder_picker.get(i)).get("folder").toString())) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f080105);
            }
            if (FileUtil.isFile(((HashMap) AppFragmentActivity.this.lm_folder_picker.get(i)).get("folder").toString())) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f080103);
            }
            return view;
        }
    }

    public void updateTextRitual() {
        this.vscr_ritual.post(new Runnable() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.23
            @Override // java.lang.Runnable
            public void run() {
                AppFragmentActivity.this.vscr_ritual.fullScroll(130);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public boolean jsonIsValid(String str) {
        new HashMap();
        new ArrayList();
        try {
            try {
                HashMap hashMap = (HashMap) new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.24
                }.getType());
                return true;
            } catch (Exception unused) {
                ArrayList arrayList = (ArrayList) new Gson().fromJson(str, new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.25
                }.getType());
                return true;
            }
        } catch (Exception unused2) {
            return false;
        }
    }

    public void _getAppList() {
        _showLoadingMain("Memuat Aplikasi...");
        this.ls_allapps = ApkUtils.getInstalledAppPackages(requireContext());
        this.ls_eternal = new ArrayList<>(GetEternal.findAppsByActivity(requireContext(), "com.fufufu.katrina.eternal.MainActivity"));
        MyAPPLIST myAPPLIST = this.myAPPLIST;
        if (myAPPLIST != null && myAPPLIST.isRunning) {
            this.myAPPLIST.cancelAPPLISTTask();
        }
        MyAPPLIST myAPPLIST2 = new MyAPPLIST(requireContext());
        this.myAPPLIST = myAPPLIST2;
        myAPPLIST2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyAPPLIST extends AsyncTask<Void, Void, Void> {
        private WeakReference<Context> contextReference;
        private boolean isRunning = false;

        public MyAPPLIST(Context context) {
            this.contextReference = new WeakReference<>(context);
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            if (this.contextReference.get() != null) {
                AppFragmentActivity.this.ln_base.setVisibility(8);
                AppFragmentActivity.this.ln_progressbar.setVisibility(0);
                AppFragmentActivity.this.lm_all_app.clear();
                if (AppFragmentActivity.this.preffav.getString("favorite_app_list", "").equals("")) {
                    return;
                }
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity.s_fav_app = appFragmentActivity.preffav.getString("favorite_app_list", "");
                AppFragmentActivity.this.lm_fav_app = (ArrayList) new Gson().fromJson(AppFragmentActivity.this.s_fav_app, new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.MyAPPLIST.1
                }.getType());
                SketchwareUtil.sortListMap(AppFragmentActivity.this.lm_fav_app, "appsort", false, true);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            String str;
            String str2;
            String str3;
            while (!isCancelled()) {
                Context context = this.contextReference.get();
                if (context != null) {
                    PackageManager packageManager = context.getPackageManager();
                    int size = AppFragmentActivity.this.ls_allapps.size();
                    int i = 0;
                    while (i < size) {
                        int i2 = i + 10;
                        int min = Math.min(i2, size);
                        while (i < min) {
                            String str4 = (String) AppFragmentActivity.this.ls_allapps.get(i);
                            try {
                                str2 = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(str4, 0));
                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                                str2 = str4;
                            }
                            try {
                                str3 = packageManager.getPackageInfo(str4, 0).versionName;
                            } catch (PackageManager.NameNotFoundException e2) {
                                e2.printStackTrace();
                                str3 = "";
                            }
                            AppFragmentActivity.this.m_app = new HashMap();
                            AppFragmentActivity.this.m_app.put("appname", str2);
                            AppFragmentActivity.this.m_app.put("appsort", str2.toLowerCase());
                            AppFragmentActivity.this.m_app.put("apppackage", str4);
                            AppFragmentActivity.this.m_app.put("appversion", str3);
                            AppFragmentActivity.this.lm_all_app.add(AppFragmentActivity.this.m_app);
                            i++;
                        }
                        i = i2;
                    }
                    PackageManager packageManager2 = context.getPackageManager();
                    int size2 = AppFragmentActivity.this.ls_eternal.size();
                    int i3 = 0;
                    while (i3 < size2) {
                        int i4 = i3 + 10;
                        int min2 = Math.min(i4, size2);
                        while (i3 < min2) {
                            String str5 = (String) AppFragmentActivity.this.ls_eternal.get(i3);
                            try {
                                str = (String) packageManager2.getApplicationLabel(packageManager2.getApplicationInfo(str5, 0));
                            } catch (PackageManager.NameNotFoundException e3) {
                                e3.printStackTrace();
                                str = str5;
                            }
                            AppFragmentActivity.this.m_app = new HashMap();
                            AppFragmentActivity.this.m_app.put("appname", str);
                            AppFragmentActivity.this.m_app.put("apppackage", str5);
                            AppFragmentActivity.this.lm_eternal.add(AppFragmentActivity.this.m_app);
                            i3++;
                        }
                        i3 = i4;
                    }
                    return null;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r4) {
            this.isRunning = false;
            if (this.contextReference.get() != null) {
                SketchwareUtil.sortListMap(AppFragmentActivity.this.lm_all_app, "appsort", false, true);
                AppFragmentActivity.this.prefall.edit().putString("all_app_list", new Gson().toJson(AppFragmentActivity.this.lm_all_app)).commit();
                AppFragmentActivity.this.prefall.edit().putString("all_app_eternal", new Gson().toJson(AppFragmentActivity.this.lm_eternal)).commit();
                AppFragmentActivity.this.ln_base.setVisibility(0);
                AppFragmentActivity.this.ln_progressbar.setVisibility(8);
            }
        }

        public void cancelAPPLISTTask() {
            cancel(true);
        }
    }

    public void _setFirstUI() {
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
            this.s_commandBaseRestore = replace61;
            this.s_commandBase = replace61.replace("futhispackage", getContext().getApplicationContext().getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.auto_app.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.26
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 6) {
                    AppFragmentActivity.this.auto_app.clearFocus();
                    return false;
                }
                return false;
            }
        });
        this.auto_backup.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.27
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 6) {
                    AppFragmentActivity.this.auto_backup.clearFocus();
                    return false;
                }
                return false;
            }
        });
        this.lv_app.setVerticalScrollBarEnabled(false);
        this.lv_fav.setVerticalScrollBarEnabled(false);
        this.vscr_ritual.setVerticalScrollBarEnabled(false);
        this.lv_backup.setVerticalScrollBarEnabled(false);
        this._fab.setImageResource(R.drawable.admsoloraya_res_0x7f0800ff);
        this.fab2.setImageResource(R.drawable.admsoloraya_res_0x7f0800fe);
        this.fabeternal.setImageResource(R.drawable.admsoloraya_res_0x7f0800fb);
        this.ln_backup_empty.setVisibility(8);
        this.ln_ritual.setVisibility(8);
        this.fabeternal.setVisibility(8);
        this._fab.setVisibility(8);
        this.fab2.setVisibility(8);
        this.lv_app.setVisibility(8);
        this.lv_fav.setVisibility(8);
        this.im_empty.setImageResource(R.drawable.admsoloraya_res_0x7f080177);
        Typeface createFromAsset = Typeface.createFromAsset(getContext().getAssets(), "fonts/sans.ttf");
        this.mchip_title.setTypeface(createFromAsset);
        this.mchip_info.setTypeface(createFromAsset);
        this.mchip_loc.setTypeface(createFromAsset);
        this.mchip_total.setTypeface(createFromAsset);
        this.mchip_slot.setTypeface(createFromAsset);
    }

    public void _setFromPreferences() {
        if (this.prefui.getString("backup_show_favorite", "").equals("")) {
            this.prefui.edit().putString("backup_show_favorite", "false").commit();
        }
        if (this.prefui.getString("backup_show_favorite", "").equals("true")) {
            this.cv_favorite.setCardBackgroundColor(getResources().getColor(R.color.admsoloraya_res_0x7f060191));
            this.lv_fav.setVisibility(0);
            this.lv_app.setVisibility(8);
        } else {
            this.cv_favorite.setCardBackgroundColor(getResources().getColor(R.color.admsoloraya_res_0x7f0600b0));
            this.lv_fav.setVisibility(8);
            this.lv_app.setVisibility(0);
        }
        this.lv_fav.setAdapter((ListAdapter) new Lv_favAdapter(this.lm_fav_app));
        this.lv_app.setAdapter((ListAdapter) new Lv_appAdapter(this.lm_all_app));
        _setFavoriteAppVisibility();
        if (this.prefui.getString("backup_search_type", "").equals("backup")) {
            this.im_search_type.setImageResource(R.drawable.admsoloraya_res_0x7f08013f);
            this.til_app.setVisibility(8);
            this.til_backup.setVisibility(0);
        } else {
            this.prefui.edit().putString("backup_search_type", "application").commit();
            this.im_search_type.setImageResource(R.drawable.admsoloraya_res_0x7f08013e);
            this.til_app.setVisibility(0);
            this.til_backup.setVisibility(8);
        }
        if (this.prefui.getString("backup_sdcard_location", "").equals("")) {
            this.prefui.edit().putString("backup_sdcard_location", "/storage/emulated/0/XKatrina").commit();
            this.mchip_loc.setText(this.prefui.getString("backup_sdcard_location", "").replace("/storage/emulated/0", ""));
        } else {
            this.mchip_loc.setText(this.prefui.getString("backup_sdcard_location", "").replace("/storage/emulated/0", ""));
        }
        if (!FileUtil.isExistFile("/storage/emulated/0/XKatrina")) {
            FileUtil.makeDir("/storage/emulated/0/XKatrina");
        }
        if (this.prefui.getString("backup_app_package", "").equals("")) {
            _hideLoadingMain();
            return;
        }
        this.tv_name_ritual.setText(this.prefui.getString("backup_app_name", ""));
        try {
            String string = this.prefui.getString("backup_app_package", "");
            if (!string.isEmpty()) {
                PackageManager packageManager = getActivity().getPackageManager();
                this.tv_versi_ritual.setText(packageManager.getPackageInfo(string, 0).versionName);
                this.im_app_ritual.setImageDrawable(packageManager.getApplicationIcon(packageManager.getApplicationInfo(string, 0)));
            } else {
                this.tv_versi_ritual.setText("Unknown");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            this.tv_versi_ritual.setText("Unknown");
        }
        _onReadBackup();
        _setBackupTopChip();
    }

    public void _setFavoriteApp() {
        if (this.prefui.getString("backup_show_favorite", "").equals("true")) {
            this.prefui.edit().putString("backup_show_favorite", "false").commit();
            this.cv_favorite.setCardBackgroundColor(getResources().getColor(R.color.admsoloraya_res_0x7f0600b0));
            Animation loadAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.admsoloraya_res_0x7f010024);
            this.cv_favorite.startAnimation(loadAnimation);
            this.cv_favorite.setVisibility(4);
            this.lv_fav.startAnimation(loadAnimation);
            this.lv_fav.setVisibility(8);
            Animation loadAnimation2 = AnimationUtils.loadAnimation(requireContext(), R.anim.admsoloraya_res_0x7f010023);
            this.lv_app.startAnimation(loadAnimation2);
            this.lv_app.setVisibility(0);
            this.cv_favorite.startAnimation(loadAnimation2);
            this.cv_favorite.setVisibility(0);
            _setFavoriteAppVisibility();
            return;
        }
        this.prefui.edit().putString("backup_show_favorite", "true").commit();
        this.cv_favorite.setCardBackgroundColor(getResources().getColor(R.color.admsoloraya_res_0x7f060191));
        Animation loadAnimation3 = AnimationUtils.loadAnimation(requireContext(), R.anim.admsoloraya_res_0x7f010024);
        this.cv_favorite.startAnimation(loadAnimation3);
        this.cv_favorite.setVisibility(4);
        this.lv_app.startAnimation(loadAnimation3);
        this.lv_app.setVisibility(8);
        Animation loadAnimation4 = AnimationUtils.loadAnimation(requireContext(), R.anim.admsoloraya_res_0x7f010023);
        this.lv_fav.startAnimation(loadAnimation4);
        this.lv_fav.setVisibility(0);
        this.cv_favorite.startAnimation(loadAnimation4);
        this.cv_favorite.setVisibility(0);
        _setFavoriteAppVisibility();
    }

    public void _setFavoriteAppVisibility() {
        if (this.lv_fav.getVisibility() == 0) {
            this.im_favorite.setImageResource(R.drawable.admsoloraya_res_0x7f0800f5);
        } else {
            this.im_favorite.setImageResource(R.drawable.admsoloraya_res_0x7f08011a);
        }
    }

    public void _setBackupTopChip() {
        if (this.prefui.getString("backup_app_name", "").equals("")) {
            this.mchip_title.setText("Belum dipilih");
            this.mchip_title.setChipIcon(ContextCompat.getDrawable(requireContext(), R.drawable.admsoloraya_res_0x7f0800dd));
        } else {
            String string = this.prefui.getString("backup_app_name", "");
            this.s_top_appname = string;
            if (string.length() > 10) {
                this.s_top_appname = this.s_top_appname.substring(0, 10);
            }
            this.mchip_title.setText(this.s_top_appname);
            try {
                this.mchip_title.setChipIcon(getActivity().getPackageManager().getApplicationIcon(this.prefui.getString("backup_app_package", "")));
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        this.mchip_loc.setText(this.prefui.getString("backup_sdcard_location", "").replace("/storage/emulated/0", ""));
        _setAppPosition();
    }

    public void _setSearchType() {
        this.auto_app.setText("");
        this.auto_backup.setText("");
        if (this.prefui.getString("backup_search_type", "").equals("backup")) {
            this.prefui.edit().putString("backup_search_type", "application").commit();
            this.im_search_type.setImageResource(R.drawable.admsoloraya_res_0x7f08013e);
            this.til_backup.setVisibility(8);
            this.til_app.setVisibility(0);
            this.auto_backup.setEnabled(false);
            this.auto_app.setEnabled(true);
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Type pencarian aplikasi");
        } else {
            this.prefui.edit().putString("backup_search_type", "backup").commit();
            this.im_search_type.setImageResource(R.drawable.admsoloraya_res_0x7f08013f);
            this.til_backup.setVisibility(0);
            this.til_app.setVisibility(8);
            this.auto_app.setEnabled(false);
            this.auto_backup.setEnabled(true);
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Type pencarian backup");
        }
        if (this.prefui.getString("backup_show_favorite", "").equals("true")) {
            this.im_favorite.setImageResource(R.drawable.admsoloraya_res_0x7f0800f5);
        } else {
            this.im_favorite.setImageResource(R.drawable.admsoloraya_res_0x7f08011a);
        }
    }

    public void _setInvalidateBackup() {
        this.lv_backup.invalidateViews();
    }

    public void _showPopupFav(final double d, final String str, final String str2, final String str3, View view) {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0031, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0591)).setText(str2.concat(" (".concat(str3.concat(")"))));
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a056f)).setText(str);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a029c);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a029d);
        LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a029e);
        LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a029f);
        LinearLayout linearLayout5 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02a0);
        LinearLayout linearLayout6 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02a1);
        LinearLayout linearLayout7 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02a2);
        LinearLayout linearLayout8 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02a3);
        TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04bf);
        TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c0);
        TextView textView3 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c1);
        TextView textView4 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c2);
        TextView textView5 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c3);
        TextView textView6 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c4);
        TextView textView7 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c5);
        TextView textView8 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c6);
        MaterialCardView materialCardView = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0371);
        MaterialCardView materialCardView2 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0378);
        MaterialCardView materialCardView3 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0379);
        MaterialCardView materialCardView4 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037a);
        MaterialCardView materialCardView5 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037b);
        MaterialCardView materialCardView6 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037c);
        MaterialCardView materialCardView7 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037d);
        MaterialCardView materialCardView8 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037e);
        ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f1)).setImageResource(R.drawable.admsoloraya_res_0x7f0800f5);
        ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f8)).setImageResource(R.drawable.admsoloraya_res_0x7f0800fd);
        ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f9)).setImageResource(R.drawable.admsoloraya_res_0x7f080146);
        ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fa)).setImageResource(R.drawable.admsoloraya_res_0x7f080134);
        ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fb)).setImageResource(R.drawable.admsoloraya_res_0x7f08016f);
        ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fc)).setImageResource(R.drawable.admsoloraya_res_0x7f08013a);
        ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fd)).setImageResource(R.drawable.admsoloraya_res_0x7f080171);
        ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fe)).setImageResource(R.drawable.admsoloraya_res_0x7f080108);
        try {
            if ((requireContext().getPackageManager().getApplicationInfo(str, 0).flags & 1) != 0) {
                linearLayout5.setAlpha(0.4f);
                linearLayout6.setAlpha(0.4f);
                linearLayout5.setEnabled(false);
                linearLayout6.setEnabled(false);
            } else {
                linearLayout5.setAlpha(1.0f);
                linearLayout6.setAlpha(1.0f);
                linearLayout5.setEnabled(true);
                linearLayout6.setEnabled(true);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        popupWindow.setAnimationStyle(16973826);
        popupWindow.showAsDropDown(view, 120, -230);
        materialCardView.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (!AppFragmentActivity.this.auto_app.getText().toString().equals("")) {
                    AppFragmentActivity.this.n_pos = 0.0d;
                    int i = 0;
                    while (true) {
                        if (i >= AppFragmentActivity.this.lm_fav_app.size()) {
                            break;
                        } else if (((HashMap) AppFragmentActivity.this.lm_search_app.get((int) d)).get("apppackage").toString().equals(((HashMap) AppFragmentActivity.this.lm_fav_app.get((int) AppFragmentActivity.this.n_pos)).get("apppackage").toString())) {
                            AppFragmentActivity.this.lm_fav_app.remove((int) AppFragmentActivity.this.n_pos);
                            AppFragmentActivity.this.lm_search_app.remove((int) d);
                            AppFragmentActivity.this.preffav.edit().putString("favorite_app_list", new Gson().toJson(AppFragmentActivity.this.lm_fav_app)).commit();
                            AppFragmentActivity.this.lv_fav.invalidateViews();
                            break;
                        } else {
                            AppFragmentActivity.this.n_pos += 1.0d;
                            i++;
                        }
                    }
                    popupWindow.dismiss();
                    return;
                }
                AppFragmentActivity.this.lm_fav_app.remove((int) d);
                AppFragmentActivity.this.preffav.edit().putString("favorite_app_list", new Gson().toJson(AppFragmentActivity.this.lm_fav_app)).commit();
                AppFragmentActivity.this.lv_fav.invalidateViews();
                popupWindow.dismiss();
            }
        });
        materialCardView2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionSavePackage(str, str2, str3);
                popupWindow.dismiss();
            }
        });
        materialCardView3.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.30
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionSharePackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView4.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.31
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionOpenPackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView5.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.32
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this.n_position_app = d;
                AppFragmentActivity.this._actionUninstallPackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView6.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.33
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionReinstallPackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView7.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.34
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionWipePackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView8.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.35
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionInfoPackage(str);
                popupWindow.dismiss();
            }
        });
    }

    public void _showPopupApp(final double d, final String str, final String str2, final String str3, View view) {
        ImageView imageView;
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0031, (ViewGroup) null);
        final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0591)).setText(str2.concat(" (".concat(str3.concat(")"))));
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a056f)).setText(str);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f1);
        ImageView imageView3 = (ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f8);
        ImageView imageView4 = (ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f9);
        ImageView imageView5 = (ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fa);
        ImageView imageView6 = (ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fb);
        ImageView imageView7 = (ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fc);
        ImageView imageView8 = (ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fd);
        ImageView imageView9 = (ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fe);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a029c);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a029d);
        LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a029e);
        LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a029f);
        LinearLayout linearLayout5 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02a0);
        LinearLayout linearLayout6 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02a1);
        LinearLayout linearLayout7 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02a2);
        LinearLayout linearLayout8 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02a3);
        TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04bf);
        TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c0);
        TextView textView3 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c1);
        TextView textView4 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c2);
        TextView textView5 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c3);
        TextView textView6 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c4);
        TextView textView7 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c5);
        TextView textView8 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c6);
        MaterialCardView materialCardView = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0371);
        MaterialCardView materialCardView2 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0378);
        MaterialCardView materialCardView3 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0379);
        MaterialCardView materialCardView4 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037a);
        MaterialCardView materialCardView5 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037b);
        MaterialCardView materialCardView6 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037c);
        MaterialCardView materialCardView7 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037d);
        MaterialCardView materialCardView8 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037e);
        if (this.lm_fav_app.isEmpty()) {
            imageView2.setImageResource(R.drawable.admsoloraya_res_0x7f08011a);
            this.b_fav = false;
            textView.setText("Pin");
            imageView = imageView3;
        } else {
            imageView = imageView3;
            this.n_pos = 0.0d;
            int i = 0;
            while (true) {
                if (i >= this.lm_fav_app.size()) {
                    break;
                }
                TextView textView9 = textView;
                if (this.lm_fav_app.get((int) this.n_pos).get("apppackage").toString().equals(str)) {
                    imageView2.setImageResource(R.drawable.admsoloraya_res_0x7f0800f5);
                    textView9.setText("Unpin");
                    this.b_fav = true;
                    break;
                }
                imageView2.setImageResource(R.drawable.admsoloraya_res_0x7f08011a);
                textView9.setText("Pin");
                this.b_fav = false;
                this.n_pos += 1.0d;
                i++;
                textView = textView9;
            }
        }
        imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0800fd);
        imageView4.setImageResource(R.drawable.admsoloraya_res_0x7f080146);
        imageView5.setImageResource(R.drawable.admsoloraya_res_0x7f080134);
        imageView6.setImageResource(R.drawable.admsoloraya_res_0x7f08016f);
        imageView7.setImageResource(R.drawable.admsoloraya_res_0x7f08013a);
        imageView8.setImageResource(R.drawable.admsoloraya_res_0x7f080171);
        imageView9.setImageResource(R.drawable.admsoloraya_res_0x7f080108);
        try {
            if ((requireContext().getPackageManager().getApplicationInfo(str, 0).flags & 1) != 0) {
                linearLayout5.setAlpha(0.4f);
                linearLayout6.setAlpha(0.4f);
                linearLayout5.setEnabled(false);
                linearLayout6.setEnabled(false);
            } else {
                linearLayout5.setAlpha(1.0f);
                linearLayout6.setAlpha(1.0f);
                linearLayout5.setEnabled(true);
                linearLayout6.setEnabled(true);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        popupWindow.setAnimationStyle(16973826);
        popupWindow.showAsDropDown(view, 120, -170);
        materialCardView.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.36
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                int i2 = 0;
                if (AppFragmentActivity.this.b_fav) {
                    AppFragmentActivity.this.n_pos = 0.0d;
                    while (true) {
                        if (i2 >= AppFragmentActivity.this.lm_fav_app.size()) {
                            break;
                        } else if (((HashMap) AppFragmentActivity.this.lm_fav_app.get((int) AppFragmentActivity.this.n_pos)).get("apppackage").toString().equals(str)) {
                            AppFragmentActivity.this.lm_fav_app.remove((int) AppFragmentActivity.this.n_pos);
                            AppFragmentActivity.this.preffav.edit().putString("favorite_app_list", new Gson().toJson(AppFragmentActivity.this.lm_fav_app)).commit();
                            ListView listView = AppFragmentActivity.this.lv_fav;
                            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                            listView.setAdapter((ListAdapter) new Lv_favAdapter(appFragmentActivity.lm_fav_app));
                            break;
                        } else {
                            AppFragmentActivity.this.n_pos += 1.0d;
                            i2++;
                        }
                    }
                } else {
                    AppFragmentActivity.this.m_fav = new HashMap();
                    AppFragmentActivity.this.m_fav.put("appname", str2);
                    AppFragmentActivity.this.m_fav.put("apppackage", str);
                    AppFragmentActivity.this.m_fav.put("appversion", str3);
                    AppFragmentActivity.this.m_fav.put("appsort", str2.toLowerCase());
                    AppFragmentActivity.this.lm_fav_app.add(AppFragmentActivity.this.m_fav);
                    SketchwareUtil.sortListMap(AppFragmentActivity.this.lm_fav_app, "appsort", false, true);
                    AppFragmentActivity.this.preffav.edit().putString("favorite_app_list", new Gson().toJson(AppFragmentActivity.this.lm_fav_app)).commit();
                    ListView listView2 = AppFragmentActivity.this.lv_fav;
                    AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
                    listView2.setAdapter((ListAdapter) new Lv_favAdapter(appFragmentActivity2.lm_fav_app));
                }
                popupWindow.dismiss();
            }
        });
        materialCardView2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.37
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionSavePackage(str, str2, str3);
                popupWindow.dismiss();
            }
        });
        materialCardView3.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.38
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionSharePackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView4.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.39
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionOpenPackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView5.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.40
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this.n_position_app = d;
                AppFragmentActivity.this._actionUninstallPackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView6.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.41
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionReinstallPackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView7.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.42
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionWipePackage(str);
                popupWindow.dismiss();
            }
        });
        materialCardView8.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.43
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                AppFragmentActivity.this._actionInfoPackage(str);
                popupWindow.dismiss();
            }
        });
    }

    public void _showPopupBackup(final double d, final ArrayList<HashMap<String, Object>> arrayList, View view) {
        int i = (int) d;
        this.s_path_json = FileUtil.readFile(arrayList.get(i).get("appjson").toString());
        this.m_path_json = (HashMap) new Gson().fromJson(this.s_path_json, new TypeToken<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.44
        }.getType());
        if (arrayList.get(i).get("appmark").toString().equals("false")) {
            View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0032, (ViewGroup) null);
            final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, true);
            ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a058e)).setText("Tandai");
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f1)).setVisibility(4);
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f8)).setVisibility(4);
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f9)).setVisibility(4);
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fa)).setVisibility(4);
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fb)).setVisibility(4);
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fc)).setVisibility(4);
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fd)).setVisibility(4);
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01fe)).setVisibility(4);
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01ff)).setVisibility(4);
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f2)).setVisibility(4);
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f3)).setVisibility(4);
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f4)).setVisibility(4);
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f5)).setVisibility(4);
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f6)).setVisibility(4);
            ((ImageView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a01f7)).setVisibility(4);
            MaterialCardView materialCardView = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0371);
            MaterialCardView materialCardView2 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0378);
            MaterialCardView materialCardView3 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0379);
            MaterialCardView materialCardView4 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037a);
            MaterialCardView materialCardView5 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037b);
            MaterialCardView materialCardView6 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037c);
            MaterialCardView materialCardView7 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037d);
            MaterialCardView materialCardView8 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037e);
            MaterialCardView materialCardView9 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a037f);
            MaterialCardView materialCardView10 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0372);
            MaterialCardView materialCardView11 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0373);
            MaterialCardView materialCardView12 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0374);
            MaterialCardView materialCardView13 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0375);
            MaterialCardView materialCardView14 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0376);
            MaterialCardView materialCardView15 = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0377);
            popupWindow.setAnimationStyle(16973826);
            popupWindow.showAsDropDown(view, 0, -160);
            materialCardView.setCardBackgroundColor(Color.parseColor("#FFE8DFF5"));
            materialCardView2.setCardBackgroundColor(Color.parseColor("#FFFCE1E4"));
            materialCardView3.setCardBackgroundColor(Color.parseColor("#FFFCF4DD"));
            materialCardView4.setCardBackgroundColor(Color.parseColor("#FFDDEDEA"));
            materialCardView5.setCardBackgroundColor(Color.parseColor("#FFDAEAF6"));
            materialCardView6.setCardBackgroundColor(Color.parseColor("#FFC8B2EB"));
            materialCardView7.setCardBackgroundColor(Color.parseColor("#FFFBADAB"));
            materialCardView8.setCardBackgroundColor(Color.parseColor("#FFFADF7E"));
            materialCardView9.setCardBackgroundColor(Color.parseColor("#FFBBD9C1"));
            materialCardView10.setCardBackgroundColor(Color.parseColor("#FF80B7FF"));
            materialCardView11.setCardBackgroundColor(Color.parseColor("#FF9866E8"));
            materialCardView12.setCardBackgroundColor(Color.parseColor("#FFF9635F"));
            materialCardView13.setCardBackgroundColor(Color.parseColor("#FFFAD241"));
            materialCardView14.setCardBackgroundColor(Color.parseColor("#FF5CD574"));
            materialCardView15.setCardBackgroundColor(Color.parseColor("#FF4294FF"));
            materialCardView.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.45
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFE8DFF5");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFE8DFF5");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.46
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFFCE1E4");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFFCE1E4");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView3.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.47
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFFCF4DD");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFFCF4DD");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView4.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.48
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFDDEDEA");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFDDEDEA");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView5.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.49
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFDAEAF6");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFDAEAF6");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView6.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.50
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFC8B2EB");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFC8B2EB");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView7.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.51
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFFBADAB");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFFBADAB");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView8.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.52
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFFADF7E");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFFADF7E");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView9.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.53
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFBBD9C1");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFBBD9C1");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView10.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.54
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FF80B7FF");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FF80B7FF");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView11.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.55
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FF9866E8");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FF9866E8");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView12.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.56
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFF9635F");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFF9635F");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView13.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.57
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FFFAD241");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FFFAD241");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView14.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.58
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FF5CD574");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FF5CD574");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            materialCardView15.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.59
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.m_path_json.put("MARK", "true");
                    AppFragmentActivity.this.m_path_json.put("COLOR", "#FF4294FF");
                    ((HashMap) arrayList.get((int) d)).put("appmark", "true");
                    ((HashMap) arrayList.get((int) d)).put("appcolor", "#FF4294FF");
                    AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                    Gson create = new GsonBuilder().setPrettyPrinting().create();
                    FileUtil.writeFile(((HashMap) arrayList.get((int) d)).get("appjson").toString(), create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class)));
                    AppFragmentActivity.this._setInvalidateBackup();
                    popupWindow.dismiss();
                }
            });
            return;
        }
        this.m_path_json.put("MARK", "false");
        arrayList.get(i).put("appmark", "false");
        this.s_path_json = new Gson().toJson(this.m_path_json);
        Gson create = new GsonBuilder().setPrettyPrinting().create();
        FileUtil.writeFile(arrayList.get(i).get("appjson").toString(), create.toJson(create.fromJson(this.s_path_json, (Class<Object>) Object.class)));
        _setInvalidateBackup();
    }

    public void _showInfoBackup() {
        showINFOBACKUP();
    }

    private void showINFOBACKUP() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0022, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(true);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a024a);
        Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0079);
        TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04bd);
        TableLayout tableLayout = new TableLayout(getActivity());
        ((ScrollView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a05be)).setVerticalScrollBarEnabled(false);
        tableLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        for (Map.Entry entry : ((Map) new Gson().fromJson(this.s_path_json, (Class<Object>) TreeMap.class)).entrySet()) {
            TableRow tableRow = new TableRow(getActivity());
            tableRow.setLayoutParams(new TableRow.LayoutParams(-1, -2));
            TextView textView2 = new TextView(getActivity());
            textView2.setText((String) entry.getKey());
            textView2.setPadding(0, 10, 0, 10);
            textView2.setLayoutParams(new TableRow.LayoutParams(-2, -2));
            textView2.setTextSize(12.0f);
            tableRow.addView(textView2);
            TextView textView3 = new TextView(getActivity());
            textView3.setText("  : ");
            textView3.setPadding(0, 10, 0, 10);
            textView3.setLayoutParams(new TableRow.LayoutParams(-2, -2));
            textView3.setTextSize(12.0f);
            tableRow.addView(textView3);
            TextView textView4 = new TextView(getActivity());
            textView4.setText((String) entry.getValue());
            textView4.setPadding(0, 10, 0, 10);
            textView4.setLayoutParams(new TableRow.LayoutParams(-2, -2));
            textView4.setTextSize(12.0f);
            tableRow.addView(textView4);
            tableLayout.addView(tableRow);
        }
        linearLayout.addView(tableLayout);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.60
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.INFOBACKUP.dismiss();
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.INFOBACKUP = create;
        create.show();
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
        Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0083);
        Button button2 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0086);
        Button button3 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0297);
        final LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02b6);
        this.b_folder_scan = false;
        this.s_folder_picker = FileUtil.getExternalStorageDir();
        linearLayout.setVisibility(8);
        linearLayout2.setVisibility(0);
        _onTaskFolderPicker();
        Runnable runnable = new Runnable() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.61
            @Override // java.lang.Runnable
            public void run() {
                if (AppFragmentActivity.this.b_folder_scan) {
                    AppFragmentActivity.this.b_folder_scan = false;
                    listView.setDivider(null);
                    listView.setDividerHeight(0);
                    ListView listView2 = listView;
                    AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                    listView2.setAdapter((ListAdapter) new lv_folder_pickerAdapter(appFragmentActivity.lm_folder_picker));
                    ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
                    listView.setVerticalScrollBarEnabled(false);
                    textView.setText(AppFragmentActivity.this.s_folder_picker);
                    linearLayout.setVisibility(0);
                    linearLayout2.setVisibility(8);
                    AppFragmentActivity.this.folderone.removeCallbacks(AppFragmentActivity.this.runnablefolderone);
                    return;
                }
                AppFragmentActivity.this.folderone.postDelayed(AppFragmentActivity.this.runnablefolderone, 100L);
            }
        };
        this.runnablefolderone = runnable;
        this.folderone.postDelayed(runnable, 0L);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.62
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (FileUtil.isDirectory((String) AppFragmentActivity.this.ls_folder_picker.get(i))) {
                    File file = new File((String) AppFragmentActivity.this.ls_folder_picker.get(i));
                    if (file.exists() && file.isDirectory()) {
                        File[] listFiles = file.listFiles();
                        if (listFiles != null && listFiles.length > 0) {
                            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                            appFragmentActivity.s_folder_picker = (String) appFragmentActivity.ls_folder_picker.get(i);
                            linearLayout.setVisibility(8);
                            linearLayout2.setVisibility(0);
                            AppFragmentActivity.this._onTaskFolderPicker();
                            AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
                            final ListView listView2 = listView;
                            final TextView textView2 = textView;
                            final LinearLayout linearLayout3 = linearLayout;
                            final LinearLayout linearLayout4 = linearLayout2;
                            appFragmentActivity2.runnablefoldertwo = new Runnable() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.62.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (AppFragmentActivity.this.b_folder_scan) {
                                        AppFragmentActivity.this.b_folder_scan = false;
                                        listView2.setDivider(null);
                                        listView2.setDividerHeight(0);
                                        listView2.setAdapter((ListAdapter) new lv_folder_pickerAdapter(AppFragmentActivity.this.lm_folder_picker));
                                        ((BaseAdapter) listView2.getAdapter()).notifyDataSetChanged();
                                        listView2.setVerticalScrollBarEnabled(false);
                                        textView2.setText(AppFragmentActivity.this.s_folder_picker);
                                        linearLayout3.setVisibility(0);
                                        linearLayout4.setVisibility(8);
                                        AppFragmentActivity.this.foldertwo.removeCallbacks(AppFragmentActivity.this.runnablefoldertwo);
                                        return;
                                    }
                                    AppFragmentActivity.this.foldertwo.postDelayed(AppFragmentActivity.this.runnablefoldertwo, 100L);
                                }
                            };
                            AppFragmentActivity.this.foldertwo.postDelayed(AppFragmentActivity.this.runnablefoldertwo, 0L);
                            return;
                        }
                        SketchwareUtil.showMessage(AppFragmentActivity.this.getContext().getApplicationContext(), "Folder ini kosong");
                        return;
                    }
                    return;
                }
                SketchwareUtil.showMessage(AppFragmentActivity.this.getContext().getApplicationContext(), "Hanya bisa memilih folder");
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.63
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AppFragmentActivity.this.s_folder_picker.equals("/storage/emulated/0")) {
                    SketchwareUtil.showMessage(AppFragmentActivity.this.getContext().getApplicationContext(), "Kamu sudah sampe di awal");
                    return;
                }
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity.s_folder_picker = appFragmentActivity.s_folder_picker.substring(0, (AppFragmentActivity.this.s_folder_picker.length() - Uri.parse(AppFragmentActivity.this.s_folder_picker).getLastPathSegment().length()) - 1);
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(0);
                AppFragmentActivity.this._onTaskFolderPicker();
                AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
                final ListView listView2 = listView;
                final TextView textView2 = textView;
                final LinearLayout linearLayout3 = linearLayout;
                final LinearLayout linearLayout4 = linearLayout2;
                appFragmentActivity2.runnablefoldertri = new Runnable() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.63.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (AppFragmentActivity.this.b_folder_scan) {
                            AppFragmentActivity.this.b_folder_scan = false;
                            listView2.setDivider(null);
                            listView2.setDividerHeight(0);
                            listView2.setAdapter((ListAdapter) new lv_folder_pickerAdapter(AppFragmentActivity.this.lm_folder_picker));
                            ((BaseAdapter) listView2.getAdapter()).notifyDataSetChanged();
                            listView2.setVerticalScrollBarEnabled(false);
                            textView2.setText(AppFragmentActivity.this.s_folder_picker);
                            linearLayout3.setVisibility(0);
                            linearLayout4.setVisibility(8);
                            AppFragmentActivity.this.foldertri.removeCallbacks(AppFragmentActivity.this.runnablefoldertri);
                            return;
                        }
                        AppFragmentActivity.this.foldertri.postDelayed(AppFragmentActivity.this.runnablefoldertri, 100L);
                    }
                };
                AppFragmentActivity.this.foldertri.postDelayed(AppFragmentActivity.this.runnablefoldertri, 0L);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.64
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.SHOWFOLDER.dismiss();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.65
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.prefui.edit().putString("backup_sdcard_location", AppFragmentActivity.this.s_folder_picker).commit();
                AppFragmentActivity.this.SHOWFOLDER.dismiss();
                AppFragmentActivity.this._onReadBackup();
                AppFragmentActivity.this._setBackupTopChip();
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.SHOWFOLDER = create;
        create.show();
    }

    public void _showEditNote() {
        showEDITNOTE();
    }

    private void showEDITNOTE() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0024, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(true);
        if (this.auto_backup.getText().toString().equals("")) {
            this.s_note = this.lm_backup_data.get((int) this.n_position).get("appnote").toString();
            this.s_path_json = FileUtil.readFile(this.lm_backup_data.get((int) this.n_position).get("appjson").toString());
        } else {
            this.s_note = this.lm_search_backup.get((int) this.n_position).get("appnote").toString();
            this.s_path_json = FileUtil.readFile(this.lm_search_backup.get((int) this.n_position).get("appjson").toString());
        }
        this.m_path_json = (HashMap) new Gson().fromJson(this.s_path_json, new TypeToken<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.66
        }.getType());
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a005c);
        TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04bd);
        TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a049a);
        autoCompleteTextView.setFocusable(true);
        autoCompleteTextView.setFocusableInTouchMode(true);
        autoCompleteTextView.setText(this.s_note);
        autoCompleteTextView.setHint("Catatan");
        ((Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0086)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.67
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.EDITNOTE.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cc)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.68
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AppFragmentActivity.this.auto_backup.getText().toString().equals("")) {
                    AppFragmentActivity.this.m_path_json.put("NOTE", autoCompleteTextView.getText().toString());
                    ((HashMap) AppFragmentActivity.this.lm_backup_data.get((int) AppFragmentActivity.this.n_position)).put("appnote", autoCompleteTextView.getText().toString());
                } else {
                    AppFragmentActivity.this.m_path_json.put("NOTE", autoCompleteTextView.getText().toString());
                    ((HashMap) AppFragmentActivity.this.lm_search_backup.get((int) AppFragmentActivity.this.n_position)).put("appnote", autoCompleteTextView.getText().toString());
                    Iterator it = AppFragmentActivity.this.lm_backup_data.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        HashMap hashMap = (HashMap) it.next();
                        Object obj = hashMap.get("appfile");
                        if (obj != null && obj.toString().equals(((HashMap) AppFragmentActivity.this.lm_search_backup.get((int) AppFragmentActivity.this.n_position)).get("appfile").toString())) {
                            hashMap.put("appnote", autoCompleteTextView.getText().toString());
                            break;
                        }
                    }
                }
                AppFragmentActivity.this.s_path_json = new Gson().toJson(AppFragmentActivity.this.m_path_json);
                Gson create = new GsonBuilder().setPrettyPrinting().create();
                String json = create.toJson(create.fromJson(AppFragmentActivity.this.s_path_json, (Class<Object>) Object.class));
                if (AppFragmentActivity.this.auto_backup.getText().toString().equals("")) {
                    FileUtil.writeFile(((HashMap) AppFragmentActivity.this.lm_backup_data.get((int) AppFragmentActivity.this.n_position)).get("appjson").toString(), json);
                } else {
                    FileUtil.writeFile(((HashMap) AppFragmentActivity.this.lm_search_backup.get((int) AppFragmentActivity.this.n_position)).get("appjson").toString(), json);
                }
                AppFragmentActivity.this._setInvalidateBackup();
                AppFragmentActivity.this.EDITNOTE.dismiss();
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.EDITNOTE = create;
        create.show();
    }

    public void _showDialogRitual() {
        this.tv_ritual_result.setText("");
        this.tv_status.setText("");
        this.tv_status.setVisibility(4);
        this.pbar_ritual.setVisibility(4);
        if (this.ln_ritual.getVisibility() == 8) {
            this.b_ritual = true;
            if (this.prefui.getString("ritual_wipe_app", "").equals("true")) {
                this.switch_wipe.setChecked(true);
            } else {
                this.switch_wipe.setChecked(false);
            }
            if (this.prefui.getString("ritual_reinstall_app", "").equals("true")) {
                this.switch_reinstall.setChecked(true);
            } else {
                this.switch_reinstall.setChecked(false);
            }
            if (this.prefui.getString("ritual_wipe_gms", "").equals("true")) {
                this.switch_gms.setChecked(true);
            } else {
                this.switch_gms.setChecked(false);
            }
            if (this.prefui.getString("ritual_clean_timepick", "").equals("true")) {
                this.switch_timepick.setChecked(true);
            } else {
                this.switch_timepick.setChecked(false);
            }
            this._fab.setImageResource(R.drawable.admsoloraya_res_0x7f080100);
            this.fab2.setImageResource(R.drawable.admsoloraya_res_0x7f080102);
            this.ln_backup.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.admsoloraya_res_0x7f010024));
            this.ln_backup.setVisibility(8);
            this.ln_ritual.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.admsoloraya_res_0x7f010023));
            this.ln_ritual.setVisibility(0);
            return;
        }
        this.b_ritual = false;
        this._fab.setImageResource(R.drawable.admsoloraya_res_0x7f0800ff);
        this.fab2.setImageResource(R.drawable.admsoloraya_res_0x7f0800fe);
        this.ln_ritual.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.admsoloraya_res_0x7f010024));
        this.ln_ritual.setVisibility(8);
        this.ln_backup.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.admsoloraya_res_0x7f010023));
        this.ln_backup.setVisibility(0);
    }

    public void _showUniversalProgress() {
        showUNIVERSAL();
    }

    private void showUNIVERSAL() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d002c, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a059f)).setText(this.s_universal_progress);
        AlertDialog create = materialAlertDialogBuilder.create();
        this.UNIVERSAL = create;
        create.show();
    }

    public void _hideUniversalProgress() {
        AlertDialog alertDialog = this.UNIVERSAL;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.UNIVERSAL.dismiss();
    }

    public void _actionUninstallPackage(String str) {
        this.s_universal_progress = str;
        showUAPP();
    }

    private void showUAPP() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d002e, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0571);
        TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0528);
        Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0086);
        Button button2 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a027b);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02c9);
        textView.setText("UNINSTALL");
        textView2.setText("Uninstall aplikasi \n".concat(this.s_universal_progress));
        linearLayout2.setVisibility(8);
        linearLayout.setVisibility(0);
        button.setVisibility(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.69
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.UAPP.dismiss();
            }
        });
        button2.setOnClickListener(new AnonymousClass70(linearLayout2, linearLayout, textView2, textView, button, button2));
        AlertDialog create = materialAlertDialogBuilder.create();
        this.UAPP = create;
        create.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fufufu.katrina.backup.AppFragmentActivity$70  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass70 implements View.OnClickListener {
        private final /* synthetic */ Button val$btn_cancel;
        private final /* synthetic */ Button val$btn_oke;
        private final /* synthetic */ LinearLayout val$ln_button;
        private final /* synthetic */ LinearLayout val$ln_progress;
        private final /* synthetic */ TextView val$tv_message;
        private final /* synthetic */ TextView val$tv_title;

        AnonymousClass70(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, Button button, Button button2) {
            this.val$ln_progress = linearLayout;
            this.val$ln_button = linearLayout2;
            this.val$tv_message = textView;
            this.val$tv_title = textView2;
            this.val$btn_cancel = button;
            this.val$btn_oke = button2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.val$ln_progress.setVisibility(0);
            this.val$ln_button.setVisibility(8);
            this.val$tv_message.setText("Menguninstall aplikasi \n".concat(AppFragmentActivity.this.s_universal_progress));
            AppFragmentActivity.this.s_command = "app_package=\"fupackagename\"\n\nif pm list packages | grep -q $app_package; then\nif pm uninstall $app_package > /dev/null 2>&1; then\necho -n \"Sukses menguninstal aplikasi\"\nelse\necho -n \"Gagal menguninstal aplikasi\"\nfi\nelse\necho -n \"Aplikasi tidak ditemukan\"\nfi";
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_command = appFragmentActivity.s_command.replace("fupackagename", AppFragmentActivity.this.s_universal_progress);
            AppFragmentActivity.this.b_command = false;
            AppFragmentActivity.this.s_commandResult = "";
            AppFragmentActivity.this.s_exitCode = "";
            AppFragmentActivity.this._OnBackgroundAction();
            AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
            final TextView textView = this.val$tv_title;
            final TextView textView2 = this.val$tv_message;
            final LinearLayout linearLayout = this.val$ln_button;
            final Button button = this.val$btn_cancel;
            final LinearLayout linearLayout2 = this.val$ln_progress;
            final Button button2 = this.val$btn_oke;
            appFragmentActivity2.runnableUNINSTALLAPP = new Runnable() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.70.1
                @Override // java.lang.Runnable
                public void run() {
                    if (AppFragmentActivity.this.b_command) {
                        AppFragmentActivity.this.UNINSTALLAPP.removeCallbacks(AppFragmentActivity.this.runnableUNINSTALLAPP);
                        AppFragmentActivity.this.b_command = false;
                        if (AppFragmentActivity.this.s_commandResult.contains("Sukses")) {
                            AppFragmentActivity.this.n_pos = 0.0d;
                            int i = 0;
                            while (true) {
                                if (i >= AppFragmentActivity.this.lm_all_app.size()) {
                                    break;
                                } else if (((HashMap) AppFragmentActivity.this.lm_all_app.get((int) AppFragmentActivity.this.n_pos)).get("apppackage").toString().equals(AppFragmentActivity.this.s_universal_progress)) {
                                    AppFragmentActivity.this.lm_all_app.remove((int) AppFragmentActivity.this.n_pos);
                                    break;
                                } else {
                                    AppFragmentActivity.this.n_pos += 1.0d;
                                    i++;
                                }
                            }
                            AppFragmentActivity.this.n_pos = 0.0d;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= AppFragmentActivity.this.lm_fav_app.size()) {
                                    break;
                                } else if (((HashMap) AppFragmentActivity.this.lm_fav_app.get((int) AppFragmentActivity.this.n_pos)).get("apppackage").toString().equals(AppFragmentActivity.this.s_universal_progress)) {
                                    AppFragmentActivity.this.lm_fav_app.remove((int) AppFragmentActivity.this.n_pos);
                                    break;
                                } else {
                                    AppFragmentActivity.this.n_pos += 1.0d;
                                    i2++;
                                }
                            }
                            AppFragmentActivity.this.n_pos = 0.0d;
                            int i3 = 0;
                            while (true) {
                                if (i3 >= AppFragmentActivity.this.lm_search_app.size()) {
                                    break;
                                } else if (((HashMap) AppFragmentActivity.this.lm_search_app.get((int) AppFragmentActivity.this.n_pos)).get("apppackage").toString().equals(AppFragmentActivity.this.s_universal_progress)) {
                                    AppFragmentActivity.this.lm_search_app.remove((int) AppFragmentActivity.this.n_pos);
                                    break;
                                } else {
                                    AppFragmentActivity.this.n_pos += 1.0d;
                                    i3++;
                                }
                            }
                            if (AppFragmentActivity.this.prefui.getString("backup_app_package", "").equals(AppFragmentActivity.this.s_universal_progress)) {
                                AppFragmentActivity.this.prefui.edit().putString("backup_app_name", "").commit();
                                AppFragmentActivity.this.prefui.edit().putString("backup_app_package", "").commit();
                                AppFragmentActivity.this._onReadBackup();
                                AppFragmentActivity.this._setBackupTopChip();
                            }
                            AppFragmentActivity.this.preffav.edit().putString("favorite_app_list", new Gson().toJson(AppFragmentActivity.this.lm_fav_app)).commit();
                            AppFragmentActivity.this.lv_app.invalidateViews();
                            textView.setText("UNINSTALL SUKSES");
                            textView2.setText(AppFragmentActivity.this.s_commandResult.concat("\n".concat(AppFragmentActivity.this.s_universal_progress)));
                        } else {
                            textView.setText("UNINSTALL GAGAL");
                            textView2.setText("Error tidak diketahui");
                        }
                        linearLayout.setVisibility(0);
                        button.setVisibility(8);
                        linearLayout2.setVisibility(8);
                        button2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.70.1.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                AppFragmentActivity.this.UAPP.dismiss();
                            }
                        });
                        return;
                    }
                    AppFragmentActivity.this.UNINSTALLAPP.postDelayed(AppFragmentActivity.this.runnableUNINSTALLAPP, 100L);
                }
            };
            AppFragmentActivity.this.UNINSTALLAPP.postDelayed(AppFragmentActivity.this.runnableUNINSTALLAPP, 0L);
        }
    }

    public void _actionReinstallPackage(String str) {
        this.s_universal_progress = str;
        showRAPP();
    }

    private void showRAPP() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d002e, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0571);
        TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0528);
        Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0086);
        Button button2 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a027b);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02c9);
        textView.setText("REINSTALL");
        textView2.setText("Reinstall aplikasi \n".concat(this.s_universal_progress));
        linearLayout2.setVisibility(8);
        linearLayout.setVisibility(0);
        button.setVisibility(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.71
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.RAPP.dismiss();
            }
        });
        button2.setOnClickListener(new AnonymousClass72(linearLayout2, linearLayout, textView2, textView, button, button2));
        AlertDialog create = materialAlertDialogBuilder.create();
        this.RAPP = create;
        create.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fufufu.katrina.backup.AppFragmentActivity$72  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass72 implements View.OnClickListener {
        private final /* synthetic */ Button val$btn_cancel;
        private final /* synthetic */ Button val$btn_oke;
        private final /* synthetic */ LinearLayout val$ln_button;
        private final /* synthetic */ LinearLayout val$ln_progress;
        private final /* synthetic */ TextView val$tv_message;
        private final /* synthetic */ TextView val$tv_title;

        AnonymousClass72(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, Button button, Button button2) {
            this.val$ln_progress = linearLayout;
            this.val$ln_button = linearLayout2;
            this.val$tv_message = textView;
            this.val$tv_title = textView2;
            this.val$btn_cancel = button;
            this.val$btn_oke = button2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.val$ln_progress.setVisibility(0);
            this.val$ln_button.setVisibility(8);
            this.val$tv_message.setText("Menguninstall dan menginstall aplikasi \n".concat(AppFragmentActivity.this.s_universal_progress));
            AppFragmentActivity.this.s_command = "app_package=\"fupackagename\"\nbase_path=$(pm path $app_package)\nfull_path=${base_path#*:}\ntmp_path=\"/data/local/tmp/\"\n\nmkdir -p $tmp_path > /dev/null 2>&1;\ncp \"$full_path\" \"$tmp_path\" > /dev/null 2>&1;\n\nif [ -f \"$tmp_path/${full_path##*/}\" ] > /dev/null 2>&1; then\n\nif pm uninstall $app_package > /dev/null 2>&1; then\n\nif pm install -t -i com.android.vending -r \"$tmp_path/base.apk\" > /dev/null 2>&1; then\necho -n \"Sukses menginstal ulang aplikasi\"\nelse\necho -n \"Gagal menginstal aplikasi \"\nfi\n\nelse\necho -n \"Gagal menguninstall aplikasi\"\nfi\n\nelse\necho -n \"Gagal menguninstall aplikasi\"\nfi";
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_command = appFragmentActivity.s_command.replace("fupackagename", AppFragmentActivity.this.s_universal_progress);
            AppFragmentActivity.this.b_command = false;
            AppFragmentActivity.this.s_commandResult = "";
            AppFragmentActivity.this.s_exitCode = "";
            AppFragmentActivity.this._OnBackgroundAction();
            AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
            final TextView textView = this.val$tv_title;
            final TextView textView2 = this.val$tv_message;
            final LinearLayout linearLayout = this.val$ln_button;
            final Button button = this.val$btn_cancel;
            final LinearLayout linearLayout2 = this.val$ln_progress;
            final Button button2 = this.val$btn_oke;
            appFragmentActivity2.runnableREINSTALLAPP = new Runnable() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.72.1
                @Override // java.lang.Runnable
                public void run() {
                    if (AppFragmentActivity.this.b_command) {
                        AppFragmentActivity.this.REINSTALLAPP.removeCallbacks(AppFragmentActivity.this.runnableREINSTALLAPP);
                        AppFragmentActivity.this.b_command = false;
                        if (AppFragmentActivity.this.s_commandResult.contains("Sukses")) {
                            textView.setText("REINSTALL SUKSES");
                            textView2.setText(AppFragmentActivity.this.s_commandResult.concat("\n".concat(AppFragmentActivity.this.s_universal_progress)));
                        } else {
                            textView.setText("REINSTALL GAGAL");
                            textView2.setText("Error tidak diketahui");
                        }
                        linearLayout.setVisibility(0);
                        button.setVisibility(8);
                        linearLayout2.setVisibility(8);
                        button2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.72.1.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                AppFragmentActivity.this.RAPP.dismiss();
                            }
                        });
                        return;
                    }
                    AppFragmentActivity.this.REINSTALLAPP.postDelayed(AppFragmentActivity.this.runnableREINSTALLAPP, 100L);
                }
            };
            AppFragmentActivity.this.REINSTALLAPP.postDelayed(AppFragmentActivity.this.runnableREINSTALLAPP, 0L);
        }
    }

    public void _actionWipePackage(String str) {
        this.s_universal_progress = str;
        showWAPP();
    }

    private void showWAPP() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d002e, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0571);
        TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0528);
        Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0086);
        Button button2 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a027b);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02c9);
        textView.setText("WIPE DATA");
        textView2.setText("Wipe data aplikasi \n".concat(this.s_universal_progress));
        linearLayout2.setVisibility(8);
        linearLayout.setVisibility(0);
        button.setVisibility(0);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.73
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.WAPP.dismiss();
            }
        });
        button2.setOnClickListener(new AnonymousClass74(linearLayout2, linearLayout, textView2, textView, button, button2));
        AlertDialog create = materialAlertDialogBuilder.create();
        this.WAPP = create;
        create.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.fufufu.katrina.backup.AppFragmentActivity$74  reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass74 implements View.OnClickListener {
        private final /* synthetic */ Button val$btn_cancel;
        private final /* synthetic */ Button val$btn_oke;
        private final /* synthetic */ LinearLayout val$ln_button;
        private final /* synthetic */ LinearLayout val$ln_progress;
        private final /* synthetic */ TextView val$tv_message;
        private final /* synthetic */ TextView val$tv_title;

        AnonymousClass74(LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2, Button button, Button button2) {
            this.val$ln_progress = linearLayout;
            this.val$ln_button = linearLayout2;
            this.val$tv_message = textView;
            this.val$tv_title = textView2;
            this.val$btn_cancel = button;
            this.val$btn_oke = button2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.val$ln_progress.setVisibility(0);
            this.val$ln_button.setVisibility(8);
            this.val$tv_message.setText("Menghapus data dan cache aplikasi \n".concat(AppFragmentActivity.this.s_universal_progress));
            AppFragmentActivity.this.s_command = "app_package=\"fupackagename\"\nandroid_data=\"/storage/emulated/0/Android/data\"\nam force-stop $app_package > /dev/null 2>&1\n\nif pm clear $app_package > /dev/null 2>&1; then\nrm -rf $android_data/$app_package > /dev/null 2>&1;\necho -n \"Sukses menghapus data dan cache aplikasi\"\nelse\necho -n \"Gagal membersihkan data dan cache aplikasi\"\nfi";
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_command = appFragmentActivity.s_command.replace("fupackagename", AppFragmentActivity.this.s_universal_progress);
            AppFragmentActivity.this.b_command = false;
            AppFragmentActivity.this.s_commandResult = "";
            AppFragmentActivity.this.s_exitCode = "";
            AppFragmentActivity.this._OnBackgroundAction();
            AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
            final TextView textView = this.val$tv_title;
            final TextView textView2 = this.val$tv_message;
            final LinearLayout linearLayout = this.val$ln_button;
            final Button button = this.val$btn_cancel;
            final LinearLayout linearLayout2 = this.val$ln_progress;
            final Button button2 = this.val$btn_oke;
            appFragmentActivity2.runnableWIPEAPP = new Runnable() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.74.1
                @Override // java.lang.Runnable
                public void run() {
                    if (AppFragmentActivity.this.b_command) {
                        AppFragmentActivity.this.WIPEAPP.removeCallbacks(AppFragmentActivity.this.runnableWIPEAPP);
                        AppFragmentActivity.this.b_command = false;
                        if (AppFragmentActivity.this.s_commandResult.contains("Sukses")) {
                            textView.setText("WIPE DATA SUKSES");
                            textView2.setText(AppFragmentActivity.this.s_commandResult.concat("\n".concat(AppFragmentActivity.this.s_universal_progress)));
                        } else {
                            textView.setText("WIPE DATA GAGAL");
                            textView2.setText("Error tidak diketahui");
                        }
                        linearLayout.setVisibility(0);
                        button.setVisibility(8);
                        linearLayout2.setVisibility(8);
                        button2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.74.1.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                AppFragmentActivity.this.WAPP.dismiss();
                            }
                        });
                        return;
                    }
                    AppFragmentActivity.this.WIPEAPP.postDelayed(AppFragmentActivity.this.runnableWIPEAPP, 100L);
                }
            };
            AppFragmentActivity.this.WIPEAPP.postDelayed(AppFragmentActivity.this.runnableWIPEAPP, 0L);
        }
    }

    public void _actionOpenPackage(String str) {
        OpenAppDialogFragmentActivity openAppDialogFragmentActivity = new OpenAppDialogFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("package", str);
        openAppDialogFragmentActivity.setArguments(bundle);
        openAppDialogFragmentActivity.show(getActivity().getSupportFragmentManager(), "OpenAppDialogFragmentActivity12");
    }

    public void _actionSharePackage(String str) {
        String str2;
        try {
            str2 = getActivity().getPackageManager().getPackageInfo(str, 1).applicationInfo.publicSourceDir;
        } catch (Exception unused) {
            str2 = "";
        }
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("*/*");
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str2)));
        startActivity(Intent.createChooser(intent, "Share Via"));
    }

    public void _actionSavePackage(final String str, final String str2, final String str3) {
        this.s_universal_progress = "Menyimpan Aplikasi";
        _showUniversalProgress();
        new Handler().postDelayed(new Runnable() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.75
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String str4 = AppFragmentActivity.this.requireActivity().getPackageManager().getApplicationInfo(str, 0).sourceDir;
                    File file = new File(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()) + File.separator + "XKatrina" + File.separator + str2);
                    file.mkdirs();
                    File file2 = new File(file, String.valueOf(str2) + "_" + str3 + ".apk");
                    FileChannel channel = new FileInputStream(str4).getChannel();
                    FileChannel channel2 = new FileOutputStream(file2).getChannel();
                    channel2.transferFrom(channel, 0L, channel.size());
                    channel.close();
                    channel2.close();
                    AppFragmentActivity.this._hideUniversalProgress();
                    Toast.makeText(AppFragmentActivity.this.getContext(), "Aplikasi telah disimpan di folder XKatrina", 0).show();
                } catch (PackageManager.NameNotFoundException unused) {
                    AppFragmentActivity.this._hideUniversalProgress();
                    Toast.makeText(AppFragmentActivity.this.getContext(), "Aplikasi tidak ditemukan", 0).show();
                } catch (IOException e) {
                    AppFragmentActivity.this._hideUniversalProgress();
                    Toast.makeText(AppFragmentActivity.this.getContext(), "Gagal menyimpan aplikasi", 0).show();
                    e.printStackTrace();
                }
            }
        }, 1000L);
    }

    public void _actionInfoPackage(String str) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + str));
        startActivity(intent);
    }

    public void _onReadBackup() {
        MyREADBACKUP myREADBACKUP = this.myREADBACKUP;
        if (myREADBACKUP != null && myREADBACKUP.isRunning) {
            this.myREADBACKUP.cancelREADBACKUPTask();
        }
        MyREADBACKUP myREADBACKUP2 = new MyREADBACKUP();
        this.myREADBACKUP = myREADBACKUP2;
        myREADBACKUP2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyREADBACKUP extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyREADBACKUP() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            AppFragmentActivity.this._showLoadingMain("Memuat file backup...");
            AppFragmentActivity.this._fab.setVisibility(8);
            AppFragmentActivity.this.fab2.setVisibility(8);
            AppFragmentActivity.this.fabeternal.setVisibility(8);
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_backup_app = appFragmentActivity.prefui.getString("backup_app_package", "");
            AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
            appFragmentActivity2.s_backup_loc = appFragmentActivity2.prefui.getString("backup_sdcard_location", "");
            AppFragmentActivity appFragmentActivity3 = AppFragmentActivity.this;
            appFragmentActivity3.s_backupLocation = appFragmentActivity3.s_backup_loc.concat("/".concat(AppFragmentActivity.this.s_backup_app));
            AppFragmentActivity.this.ls_sort_1.clear();
            AppFragmentActivity.this.ls_sort_2.clear();
            AppFragmentActivity.this.ls_sort_3.clear();
            AppFragmentActivity.this.lm_backup_data.clear();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            FileUtil.listDir(AppFragmentActivity.this.s_backupLocation, AppFragmentActivity.this.ls_sort_1);
            Iterator it = AppFragmentActivity.this.ls_sort_1.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (FileUtil.isDirectory(str)) {
                    AppFragmentActivity.this.ls_sort_2.add(str);
                }
            }
            Iterator it2 = AppFragmentActivity.this.ls_sort_2.iterator();
            while (it2.hasNext()) {
                String str2 = (String) it2.next();
                AppFragmentActivity.this.ls_sort_3.clear();
                FileUtil.listDir(str2, AppFragmentActivity.this.ls_sort_3);
                AppFragmentActivity.this.s_fname = str2;
                Iterator it3 = AppFragmentActivity.this.ls_sort_3.iterator();
                while (it3.hasNext()) {
                    String str3 = (String) it3.next();
                    if (str3.endsWith("tar.gz") && FileUtil.isExistFile(str3.replace("tar.gz", "json"))) {
                        AppFragmentActivity.this.s_filePath = str3;
                        AppFragmentActivity.this.s_file_size = String.format("%.2f MB", Double.valueOf((new File(AppFragmentActivity.this.s_filePath).length() / 1024) / 1024.0d));
                        String readFile = FileUtil.readFile(AppFragmentActivity.this.s_filePath.replace("tar.gz", "json"));
                        AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                        appFragmentActivity.s_date_backup = Uri.parse(appFragmentActivity.s_filePath).getLastPathSegment();
                        if (!AppFragmentActivity.this.jsonIsValid(readFile)) {
                            readFile = "{}";
                        }
                        AppFragmentActivity.this.m_json_app = (HashMap) new Gson().fromJson(readFile, new TypeToken<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.MyREADBACKUP.1
                        }.getType());
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("UNIX")) {
                            AppFragmentActivity.this.s_usia_backup = "?? hari yang lalu";
                        } else {
                            try {
                                long parseLong = Long.parseLong(AppFragmentActivity.this.m_json_app.get("UNIX").toString());
                                AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
                                appFragmentActivity2.s_usia_backup = String.valueOf(((System.currentTimeMillis() / 1000) - parseLong) / 86400) + " hari yang lalu";
                            } catch (NumberFormatException unused) {
                                AppFragmentActivity.this.s_usia_backup = "?? hari yang lalu";
                            }
                        }
                        AppFragmentActivity.this.m_sort_result = new HashMap();
                        AppFragmentActivity.this.m_sort_result.put("appfolder", AppFragmentActivity.this.s_fname);
                        AppFragmentActivity.this.m_sort_result.put("appsize", AppFragmentActivity.this.s_file_size);
                        AppFragmentActivity.this.m_sort_result.put("appbuild", AppFragmentActivity.this.s_usia_backup);
                        AppFragmentActivity.this.m_sort_result.put("appname", AppFragmentActivity.this.s_filePath);
                        AppFragmentActivity.this.m_sort_result.put("appfile", AppFragmentActivity.this.s_filePath);
                        AppFragmentActivity.this.m_sort_result.put("appjson", AppFragmentActivity.this.s_filePath.replace("tar.gz", "json"));
                        if (AppFragmentActivity.this.m_json_app != null && AppFragmentActivity.this.m_json_app.containsKey("DATE")) {
                            AppFragmentActivity.this.m_sort_result.put("appdate", AppFragmentActivity.this.m_json_app.get("DATE").toString());
                        } else {
                            Matcher matcher = Pattern.compile("-(\\d+-\\d+)\\.").matcher(AppFragmentActivity.this.s_date_backup);
                            if (matcher.find()) {
                                AppFragmentActivity.this.s_date_backup = matcher.group(1);
                            } else {
                                AppFragmentActivity.this.s_date_backup = "Tanggal null";
                            }
                            AppFragmentActivity.this.m_sort_result.put("appdate", AppFragmentActivity.this.s_date_backup);
                        }
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("MARK")) {
                            AppFragmentActivity.this.m_sort_result.put("appmark", "false");
                        } else {
                            AppFragmentActivity.this.m_sort_result.put("appmark", AppFragmentActivity.this.m_json_app.get("MARK").toString());
                        }
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("COLOR")) {
                            AppFragmentActivity.this.m_sort_result.put("appcolor", "#FFFFFFFF");
                        } else {
                            AppFragmentActivity.this.m_sort_result.put("appcolor", AppFragmentActivity.this.m_json_app.get("COLOR").toString());
                        }
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("NOTE")) {
                            AppFragmentActivity.this.m_sort_result.put("appnote", "");
                        } else {
                            AppFragmentActivity.this.m_sort_result.put("appnote", AppFragmentActivity.this.m_json_app.get("NOTE").toString());
                        }
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("SDK")) {
                            AppFragmentActivity.this.m_sort_result.put("appsdk", "");
                        } else {
                            AppFragmentActivity.this.m_sort_result.put("appsdk", AppFragmentActivity.this.m_json_app.get("SDK").toString());
                        }
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("UNIX")) {
                            AppFragmentActivity.this.m_sort_result.put("appunix", "");
                        } else {
                            AppFragmentActivity.this.m_sort_result.put("appunix", AppFragmentActivity.this.m_json_app.get("UNIX").toString());
                        }
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("settings_ssaid")) {
                            AppFragmentActivity.this.m_sort_result.put("settings_ssaid", "false");
                        } else {
                            AppFragmentActivity.this.m_sort_result.put("settings_ssaid", AppFragmentActivity.this.m_json_app.get("settings_ssaid").toString());
                        }
                        if (AppFragmentActivity.this.m_json_app == null || !AppFragmentActivity.this.m_json_app.containsKey("system.prop")) {
                            AppFragmentActivity.this.m_sort_result.put("system.prop", "false");
                        } else {
                            AppFragmentActivity.this.m_sort_result.put("system.prop", AppFragmentActivity.this.m_json_app.get("system.prop").toString());
                        }
                        AppFragmentActivity.this.lm_backup_data.add(AppFragmentActivity.this.m_sort_result);
                    }
                }
            }
            AppFragmentActivity.deleteEmptyFolders(AppFragmentActivity.this.s_backupLocation);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r6) {
            this.isRunning = false;
            SketchwareUtil.sortListMap(AppFragmentActivity.this.lm_backup_data, "appfolder", false, true);
            if (AppFragmentActivity.this.lm_backup_data.size() > 0) {
                AppFragmentActivity.this.mchip_total.setText(String.valueOf(AppFragmentActivity.this.lm_backup_data.size()).concat(" Backup"));
                AppFragmentActivity.this.lv_backup.setVisibility(0);
                AppFragmentActivity.this.ln_backup_empty.setVisibility(8);
                ListView listView = AppFragmentActivity.this.lv_backup;
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                listView.setAdapter((ListAdapter) new Lv_backupAdapter(appFragmentActivity.lm_backup_data));
                ((BaseAdapter) AppFragmentActivity.this.lv_backup.getAdapter()).notifyDataSetChanged();
                AppFragmentActivity.this._setBackupPosition();
            } else {
                AppFragmentActivity.this.mchip_total.setText(String.valueOf(AppFragmentActivity.this.lm_backup_data.size()).concat(" Backup"));
                AppFragmentActivity.this.lv_backup.setVisibility(8);
                AppFragmentActivity.this.ln_backup_empty.setVisibility(0);
            }
            AppFragmentActivity.this._fab.setVisibility(0);
            AppFragmentActivity.this.fab2.setVisibility(0);
            AppFragmentActivity.this._showFabEternal();
            AppFragmentActivity.this._hideLoadingMain();
            AppFragmentActivity.this._setAppPosition();
        }

        public void cancelREADBACKUPTask() {
            cancel(true);
        }
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
            AppFragmentActivity.this.lm_folder_picker.clear();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            FileUtil.listDir(AppFragmentActivity.this.s_folder_picker, AppFragmentActivity.this.ls_folder_picker);
            Collections.sort(AppFragmentActivity.this.ls_folder_picker, new Comparator<String>() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.MyREADFOLDER.1FileComparator
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
            AppFragmentActivity.this.n_pos = 0.0d;
            for (int i = 0; i < AppFragmentActivity.this.ls_folder_picker.size(); i++) {
                HashMap hashMap = new HashMap();
                hashMap.put("folder", AppFragmentActivity.this.ls_folder_picker.get((int) AppFragmentActivity.this.n_pos));
                AppFragmentActivity.this.lm_folder_picker.add(hashMap);
                AppFragmentActivity.this.n_pos += 1.0d;
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            this.isRunning = false;
            AppFragmentActivity.this.b_folder_scan = true;
        }

        public void cancelREADFOLDERTask() {
            cancel(true);
        }
    }

    public void _onSearchApp(String str) {
        this.lm_search_app.clear();
        this.n_pos = 0.0d;
        int i = 0;
        if (this.prefui.getString("backup_show_favorite", "").equals("true")) {
            while (i < this.lm_fav_app.size()) {
                if (this.lm_fav_app.get((int) this.n_pos).get("appname").toString().toLowerCase().contains(str.toLowerCase())) {
                    HashMap<String, Object> hashMap = this.lm_fav_app.get((int) this.n_pos);
                    this.m_search_app = hashMap;
                    this.lm_search_app.add(hashMap);
                }
                this.n_pos += 1.0d;
                i++;
            }
            this.lv_fav.setAdapter((ListAdapter) new Lv_favAdapter(this.lm_search_app));
            ((BaseAdapter) this.lv_fav.getAdapter()).notifyDataSetChanged();
            return;
        }
        while (i < this.lm_all_app.size()) {
            if (this.lm_all_app.get((int) this.n_pos).get("appname").toString().toLowerCase().contains(str.toLowerCase())) {
                HashMap<String, Object> hashMap2 = this.lm_all_app.get((int) this.n_pos);
                this.m_search_app = hashMap2;
                this.lm_search_app.add(hashMap2);
            }
            this.n_pos += 1.0d;
            i++;
        }
        this.lv_app.setAdapter((ListAdapter) new Lv_appAdapter(this.lm_search_app));
        ((BaseAdapter) this.lv_app.getAdapter()).notifyDataSetChanged();
    }

    public void _onDeleteFileBackup(double d, final String str, String str2, String str3) {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0023, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04d6);
        TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04d9);
        final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02c9);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a027b);
        final LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02a7);
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04bd)).setText(str);
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04c8)).setText(str2);
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04d0)).setText(str3);
        linearLayout.setVisibility(8);
        ((Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0086)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.76
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.customDialog3.dismiss();
            }
        });
        ((Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cc)).setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.77
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i = 0;
                linearLayout.setVisibility(0);
                linearLayout3.setVisibility(8);
                FileUtil.deleteFile(str);
                AppFragmentActivity.this.n_pos = 0.0d;
                int i2 = 0;
                while (true) {
                    if (i2 >= AppFragmentActivity.this.lm_backup_data.size()) {
                        break;
                    } else if (((HashMap) AppFragmentActivity.this.lm_backup_data.get((int) AppFragmentActivity.this.n_pos)).get("appfolder").toString().equals(str)) {
                        AppFragmentActivity.this.lm_backup_data.remove((int) AppFragmentActivity.this.n_pos);
                        break;
                    } else {
                        AppFragmentActivity.this.n_pos += 1.0d;
                        i2++;
                    }
                }
                AppFragmentActivity.this.n_pos = 0.0d;
                while (true) {
                    if (i >= AppFragmentActivity.this.lm_search_backup.size()) {
                        break;
                    } else if (((HashMap) AppFragmentActivity.this.lm_search_backup.get((int) AppFragmentActivity.this.n_pos)).get("appfolder").toString().equals(str)) {
                        AppFragmentActivity.this.lm_search_backup.remove((int) AppFragmentActivity.this.n_pos);
                        break;
                    } else {
                        AppFragmentActivity.this.n_pos += 1.0d;
                        i++;
                    }
                }
                AppFragmentActivity.this.lv_backup.invalidateViews();
                AppFragmentActivity.this.mchip_total.setText(String.valueOf(AppFragmentActivity.this.lm_backup_data.size()).concat(" Backup"));
                AppFragmentActivity.this._onVibrate();
                AppFragmentActivity.this.customDialog3.dismiss();
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.customDialog3 = create;
        create.show();
    }

    public void _showDialogRestore(String str, String str2, String str3, String str4) {
        this.s_restore_loc = str;
        this.s_restore_ssaid = str2;
        this.s_restore_prop = str3;
        this.s_restore_sdk = str4;
        showRESTORE();
    }

    private void showRESTORE() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0029, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(true);
        TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0503);
        TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0502);
        MaterialSwitch materialSwitch = (MaterialSwitch) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0471);
        MaterialSwitch materialSwitch2 = (MaterialSwitch) inflate.findViewById(R.id.admsoloraya_res_0x7f0a046f);
        Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        Button button2 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0086);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0254);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0259);
        this.b_command = false;
        this.b_ssaid = false;
        this.b_prop = false;
        ((LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02c9)).setVisibility(8);
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0571)).setText(this.prefui.getString("backup_app_name", ""));
        ((TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0528)).setText(Uri.parse(this.s_restore_loc).getLastPathSegment());
        this.s_sdk = String.valueOf(Build.VERSION.SDK_INT);
        if (this.s_restore_prop.equals("true")) {
            textView2.setText("Diijinkan :");
            materialSwitch2.setEnabled(true);
            materialSwitch2.setAlpha(1.0f);
        } else {
            textView2.setText("Tidak diijinkan :");
            materialSwitch2.setEnabled(false);
            materialSwitch2.setAlpha(0.4f);
        }
        if (this.s_restore_ssaid.equals("true")) {
            textView.setText("Diijinkan :");
            materialSwitch.setEnabled(true);
            materialSwitch.setAlpha(1.0f);
        } else {
            textView.setText("Tidak diijinkan :");
            materialSwitch.setEnabled(false);
            materialSwitch.setAlpha(0.4f);
        }
        if (!this.s_sdk.equals(this.s_restore_sdk)) {
            textView.setText("Tidak diijinkan :");
            materialSwitch.setEnabled(false);
            textView2.setText("Tidak diijinkan :");
            materialSwitch2.setEnabled(false);
            materialSwitch.setAlpha(0.4f);
            materialSwitch2.setAlpha(0.4f);
        }
        materialSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.78
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AppFragmentActivity.this.b_ssaid = true;
                } else {
                    AppFragmentActivity.this.b_ssaid = false;
                }
            }
        });
        materialSwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.79
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AppFragmentActivity.this.b_prop = true;
                } else {
                    AppFragmentActivity.this.b_prop = false;
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.80
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.RESTORE.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.81
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity.s_scbase = appFragmentActivity.s_commandBaseRestore.concat("\nonrestore");
                AppFragmentActivity.this.s_exe1 = "tar -zxf $thisfile -C / > /dev/null 2>&1\nif [[ $? -eq 0 ]]; then\n  echo \"Sukses restore $thisapp\"\nelse\n  echo \"Gagal restore $thisapp\"\nfi";
                AppFragmentActivity.this.s_exe2 = "tar -zxf $thispath/fileprop.tar.gz -C / > /dev/null 2>&1\nif [[ $? -eq 0 ]]; then\n  echo \"Sukses restore system.prop\"\nelse\n  echo  \"Gagal restore system.prop\"\nfi";
                AppFragmentActivity.this.s_exe3 = "tar -zxf $thispath/filessaid.tar.gz -C / > /dev/null 2>&1\nif [[ $? -eq 0 ]]; then\n  echo \"Sukses restore settings_ssaid\"\nelse\n  echo \"Gagal restore settings_ssaid\"\nfi";
                AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
                appFragmentActivity2.s_scbase = appFragmentActivity2.s_scbase.replace("futhisapp", AppFragmentActivity.this.prefui.getString("backup_app_name", ""));
                AppFragmentActivity appFragmentActivity3 = AppFragmentActivity.this;
                appFragmentActivity3.s_scbase = appFragmentActivity3.s_scbase.replace("futhispackage", AppFragmentActivity.this.prefui.getString("backup_app_package", ""));
                AppFragmentActivity appFragmentActivity4 = AppFragmentActivity.this;
                appFragmentActivity4.s_scbase = appFragmentActivity4.s_scbase.replace("futhisfile", AppFragmentActivity.this.s_restore_loc);
                AppFragmentActivity appFragmentActivity5 = AppFragmentActivity.this;
                appFragmentActivity5.s_scbase = appFragmentActivity5.s_scbase.replace("#exe1", AppFragmentActivity.this.s_exe1);
                AppFragmentActivity.this.s_commandResult = "";
                AppFragmentActivity.this.s_exitCode = "";
                if (AppFragmentActivity.this.b_prop) {
                    AppFragmentActivity appFragmentActivity6 = AppFragmentActivity.this;
                    appFragmentActivity6.s_scbase = appFragmentActivity6.s_scbase.replace("#exe2", AppFragmentActivity.this.s_exe2);
                }
                if (AppFragmentActivity.this.b_ssaid) {
                    AppFragmentActivity appFragmentActivity7 = AppFragmentActivity.this;
                    appFragmentActivity7.s_scbase = appFragmentActivity7.s_scbase.replace("#exe3", AppFragmentActivity.this.s_exe3);
                }
                AppFragmentActivity appFragmentActivity8 = AppFragmentActivity.this;
                appFragmentActivity8.s_command = appFragmentActivity8.s_scbase;
                AppFragmentActivity.this._onRestoreBackup();
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.RESTORE = create;
        create.show();
    }

    public void _onRestoreBackup() {
        AlertDialog alertDialog = this.RESTORE;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.RESTORE.dismiss();
        }
        showPROSESRESTOR();
    }

    private void showPROSESRESTOR() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d002a, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        final TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a053e);
        final TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a053d);
        final Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        final Button button2 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cd);
        final LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02b9);
        textView.setText("Proses");
        textView2.setText("Restore backup\n".concat(this.prefui.getString("backup_app_name", "")));
        linearLayout.setVisibility(0);
        button.setVisibility(8);
        button2.setVisibility(8);
        this.b_restore = false;
        _OnBackgroundAction();
        Runnable runnable = new Runnable() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.82
            @Override // java.lang.Runnable
            public void run() {
                if (AppFragmentActivity.this.b_restore) {
                    AppFragmentActivity.this.b_restore = false;
                    AppFragmentActivity.this.OnRestore.removeCallbacks(AppFragmentActivity.this.runnableOnRestore);
                    textView.setText("Selesai");
                    textView2.setText(AppFragmentActivity.this.s_commandResult);
                    linearLayout.setVisibility(8);
                    button.setVisibility(0);
                    button2.setVisibility(0);
                    AppFragmentActivity.this._onVibrate();
                    AppFragmentActivity.this.preflast.edit().putString(AppFragmentActivity.this.prefui.getString("backup_app_package", ""), AppFragmentActivity.this.s_restore_loc).commit();
                }
                AppFragmentActivity.this.OnRestore.postDelayed(AppFragmentActivity.this.runnableOnRestore, 100L);
            }
        };
        this.runnableOnRestore = runnable;
        this.OnRestore.postDelayed(runnable, 0L);
        if (this.b_ssaid || this.b_prop) {
            button.setText("Reboot");
            button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.83
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AppFragmentActivity.this.s_command = "am start -a android.intent.action.REBOOT";
                    AppFragmentActivity.this.b_command = false;
                    Shell.Result exec = Shell.cmd(AppFragmentActivity.this.s_command).exec();
                    List<String> out = exec.getOut();
                    exec.getCode();
                    AppFragmentActivity.this.b_command = exec.isSuccess();
                    AppFragmentActivity.this.s_commandResult = AppFragmentActivity$83$$ExternalSyntheticBackport0.m("\n", out);
                }
            });
        } else {
            button.setText("Tutup");
            button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.84
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AppFragmentActivity.this.PROSESRESTOR.dismiss();
                }
            });
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.85
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                    appFragmentActivity._actionOpenPackage(appFragmentActivity.prefui.getString("backup_app_package", ""));
                    AppFragmentActivity.this.PROSESRESTOR.dismiss();
                }
            });
        }
        AlertDialog create = materialAlertDialogBuilder.create();
        this.PROSESRESTOR = create;
        create.show();
    }

    public void _setBackupPosition() {
        String string = this.prefui.getString("backup_app_package", "");
        this.s_universal_progress = string;
        if (this.preflast.getString(string, "").equals("")) {
            return;
        }
        this.n_restore_position = 0.0d;
        for (int i = 0; i < this.lm_backup_data.size(); i++) {
            if (this.lm_backup_data.get((int) this.n_restore_position).get("appfile").toString().equals(this.preflast.getString(this.s_universal_progress, ""))) {
                this.lv_backup.setSelection((int) this.n_restore_position);
            }
            this.n_restore_position += 1.0d;
        }
        _setAppPosition();
    }

    public void _onSearchBackup(String str) {
        this.lm_search_backup.clear();
        this.n_pos = 0.0d;
        for (int i = 0; i < this.lm_backup_data.size(); i++) {
            if (this.lm_backup_data.get((int) this.n_pos).get("appnote").toString().toLowerCase().contains(str.toLowerCase())) {
                HashMap<String, Object> hashMap = this.lm_backup_data.get((int) this.n_pos);
                this.m_search_backup = hashMap;
                this.lm_search_backup.add(hashMap);
            } else if (this.lm_backup_data.get((int) this.n_pos).get("appfolder").toString().toLowerCase().contains(str.toLowerCase())) {
                HashMap<String, Object> hashMap2 = this.lm_backup_data.get((int) this.n_pos);
                this.m_search_backup = hashMap2;
                this.lm_search_backup.add(hashMap2);
            }
            this.n_pos += 1.0d;
        }
        if (this.lm_search_backup.size() > 0) {
            this.lv_backup.setVisibility(0);
            this.ln_backup_empty.setVisibility(8);
            this.lv_backup.setAdapter((ListAdapter) new Lv_backupAdapter(this.lm_search_backup));
            ((BaseAdapter) this.lv_backup.getAdapter()).notifyDataSetChanged();
            return;
        }
        this.lv_backup.setVisibility(8);
        this.ln_backup_empty.setVisibility(0);
    }

    public void _setSearchNull() {
        this.tv_name_ritual.setText(this.prefui.getString("backup_app_name", ""));
        try {
            String string = this.prefui.getString("backup_app_package", "");
            if (!string.isEmpty()) {
                PackageManager packageManager = getActivity().getPackageManager();
                this.tv_versi_ritual.setText(packageManager.getPackageInfo(string, 0).versionName);
                this.im_app_ritual.setImageDrawable(packageManager.getApplicationIcon(packageManager.getApplicationInfo(string, 0)));
            } else {
                this.tv_versi_ritual.setText("Unknown");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            this.tv_versi_ritual.setText("Unknown");
        }
        this.ln_backup.setVisibility(0);
        this.ln_ritual.setVisibility(8);
        this.auto_backup.setText("");
        this.auto_backup.clearFocus();
    }

    public void _showDialogUpdate() {
        showUPDATE();
    }

    private void showUPDATE() {
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
        this.s_filename = "/data/user/0/".concat(getContext().getApplicationContext().getPackageName().concat("/xkatrina.apk"));
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.86
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AppFragmentActivity.this.b_update_force) {
                    return;
                }
                AppFragmentActivity.this.UPDATE.dismiss();
            }
        });
        this.btn_download.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.87
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity.s_url = ((HashMap) appFragmentActivity.lm_release.get(0)).get("release_url").toString();
                new DownloadTask(AppFragmentActivity.this, null).execute(AppFragmentActivity.this.s_url);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.88
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Context context = AppFragmentActivity.this.getContext();
                AppFragmentActivity.this.getContext().getApplicationContext();
                ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", textView3.getText().toString()));
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.89
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.UPDATE.dismiss();
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.UPDATE = create;
        create.show();
    }

    /* loaded from: classes5.dex */
    private class DownloadTask extends AsyncTask<String, Integer, Boolean> {
        private static final int BUFFER_SIZE = 1024;

        private DownloadTask() {
        }

        /* synthetic */ DownloadTask(AppFragmentActivity appFragmentActivity, DownloadTask downloadTask) {
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
                com.fufufu.katrina.backup.AppFragmentActivity r6 = com.fufufu.katrina.backup.AppFragmentActivity.this     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> Lb0
                java.lang.String r6 = com.fufufu.katrina.backup.AppFragmentActivity.access$52(r6)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> Lb0
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
            throw new UnsupportedOperationException("Method not decompiled: com.fufufu.katrina.backup.AppFragmentActivity.DownloadTask.doInBackground(java.lang.String[]):java.lang.Boolean");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate((Object[]) numArr);
            AppFragmentActivity.this.progressBar.setProgress(numArr[0].intValue());
            Button button = AppFragmentActivity.this.btn_download;
            button.setText(numArr[0] + "%");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            super.onPostExecute((DownloadTask) bool);
            if (bool.booleanValue()) {
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity.s_command = "pm install ".concat(appFragmentActivity.s_filename);
                AppFragmentActivity.this.s_commandResult = "";
                AppFragmentActivity.this.s_exitCode = "";
                AppFragmentActivity.this.b_command = false;
                AppFragmentActivity.this.b_command = false;
                Shell.Result exec = Shell.cmd(AppFragmentActivity.this.s_command).exec();
                List<String> out = exec.getOut();
                exec.getCode();
                AppFragmentActivity.this.b_command = exec.isSuccess();
                AppFragmentActivity.this.s_commandResult = AppFragmentActivity$DownloadTask$$ExternalSyntheticBackport0.m("\n", out);
                return;
            }
            AppFragmentActivity.this.fushowToast("Download failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fushowToast(String str) {
        Toast.makeText(requireContext(), str, 0).show();
    }

    public void _onAppRitual() {
        this.s_package_ritual = this.prefui.getString("backup_app_package", "");
        this.tv_status.setText("\nProses...");
        this.tv_status.setVisibility(0);
        this.pbar_ritual.setVisibility(0);
        this.s_commandResult = "";
        _cekWipeData();
    }

    public void _cekWipeData() {
        if (this.prefui.getString("ritual_wipe_app", "").equals("true")) {
            _onRitualWipeData();
        } else {
            _cekReinstallApp();
        }
    }

    public void _cekReinstallApp() {
        if (this.prefui.getString("ritual_reinstall_app", "").equals("true")) {
            _onRitualReinstallApp();
        } else {
            _cekWipeGms();
        }
    }

    public void _cekWipeGms() {
        if (this.prefui.getString("ritual_wipe_gms", "").equals("true")) {
            _onRitualWipeGms();
        } else {
            _cekCleanTimepick();
        }
    }

    public void _cekCleanTimepick() {
        if (this.prefui.getString("ritual_clean_timepick", "").equals("true")) {
            _onRitualTimepick();
            return;
        }
        this.pbar_ritual.setVisibility(4);
        this.tv_status.setText("Selesai");
    }

    public void _onVibrate() {
        this.vibrate.vibrate(100L);
    }

    public void _sendFileToYourBot(ArrayList<String> arrayList) {
        new TelegramFileSender().execute(this.prefuser.getString("token_bot", ""), this.prefuser.getString("chat_id", ""), arrayList);
    }

    /* loaded from: classes5.dex */
    public class TelegramFileSender extends AsyncTask<Object, Integer, Void> {
        public TelegramFileSender() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            ProgressBar progressBar = (ProgressBar) AppFragmentActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a03e9);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            ((LinearLayout) AppFragmentActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a02db)).setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Object... objArr) {
            String str = (String) objArr[0];
            String str2 = (String) objArr[1];
            ArrayList arrayList = (ArrayList) objArr[2];
            try {
                int size = arrayList.size();
                AppFragmentActivity.this.s_totalfile = String.valueOf(size);
                int i = 0;
                while (i < size) {
                    String str3 = (String) arrayList.get(i);
                    long totalFileSize = getTotalFileSize(str3);
                    AppFragmentActivity.this.s_namefile = str3;
                    int i2 = i + 1;
                    AppFragmentActivity.this.s_progressfile = String.valueOf(i2);
                    AppFragmentActivity.this.s_totalsize = String.format("%.2f", Double.valueOf(totalFileSize / 1048576.0d));
                    publishProgress(0);
                    sendFileToTelegram(str, str2, str3, totalFileSize);
                    publishProgress(Integer.valueOf((i2 * 100) / size));
                    i = i2;
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        private void sendFileToTelegram(String str, String str2, String str3, long j) throws IOException {
            String.valueOf(j);
            String format = String.format("https://api.telegram.org/bot%s/sendDocument?chat_id=%s", str, str2);
            File file = new File(str3);
            String hexString = Long.toHexString(System.currentTimeMillis());
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(format).openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty(HTTP.CONTENT_TYPE, "multipart/form-data; boundary=" + hexString);
            try {
                OutputStream outputStream = httpURLConnection.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(file);
                outputStream.write(("--" + hexString + "\r\n").getBytes());
                outputStream.write(("Content-Disposition: form-data; name=\"document\"; filename=\"" + file.getName() + "\"\r\n").getBytes());
                outputStream.write("Content-Type: application/octet-stream\r\n\r\n".getBytes());
                byte[] bArr = new byte[8192];
                long j2 = 0;
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(bArr, 0, read);
                    j2 += read;
                    publishProgress(Integer.valueOf((int) ((100 * j2) / j)));
                }
                outputStream.write(("\r\n--" + hexString + "--\r\n").getBytes());
                fileInputStream.close();
                if (outputStream != null) {
                    outputStream.close();
                }
                int responseCode = httpURLConnection.getResponseCode();
                PrintStream printStream = System.out;
                printStream.println("Response Code: " + responseCode);
            } finally {
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
            int intValue = numArr[0].intValue();
            TextView textView = (TextView) AppFragmentActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a052d);
            if (textView != null) {
                textView.setText(Uri.parse(AppFragmentActivity.this.s_namefile).getLastPathSegment());
            }
            TextView textView2 = (TextView) AppFragmentActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a0539);
            if (textView2 != null) {
                textView2.setText(String.valueOf(AppFragmentActivity.this.s_progressfile) + "/" + AppFragmentActivity.this.s_totalfile);
            }
            TextView textView3 = (TextView) AppFragmentActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a059b);
            if (textView3 != null) {
                textView3.setText(String.valueOf(AppFragmentActivity.this.s_totalsize) + "MB");
            }
            ProgressBar progressBar = (ProgressBar) AppFragmentActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a03e9);
            if (progressBar != null) {
                progressBar.setProgress(intValue);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r3) {
            TextView textView = (TextView) AppFragmentActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a0537);
            if (textView != null) {
                textView.setText(String.valueOf(AppFragmentActivity.this.s_totalfile) + " File Terkirim");
            }
            Button button = (Button) AppFragmentActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
            if (button != null) {
                button.setText("SELESAI");
            }
            ((LinearLayout) AppFragmentActivity.this.BOT.findViewById(R.id.admsoloraya_res_0x7f0a02db)).setVisibility(8);
            AppFragmentActivity.this.ls_backupbot.clear();
        }

        private long getTotalFileSize(String str) {
            return new File(str).length();
        }
    }

    public void _openDialogBot() {
        showBOT();
    }

    private void showBOT() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0033, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(false);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02db);
        Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0084);
        final Button button2 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a052d);
        TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0539);
        TextView textView3 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a059b);
        TextView textView4 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0537);
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.admsoloraya_res_0x7f0a03e9);
        linearLayout.setBackgroundColor(Color.parseColor("#20000000"));
        linearLayout.setVisibility(8);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.ls_backupbot.iterator();
        while (it.hasNext()) {
            String next = it.next();
            int lastIndexOf = next.lastIndexOf(47);
            if (lastIndexOf != -1) {
                next = next.substring(lastIndexOf + 1);
            }
            arrayList.add(next);
        }
        textView4.setText(AppFragmentActivity$$ExternalSyntheticBackport0.m("\n", arrayList));
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.90
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppFragmentActivity.this.BOT.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.91
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (button2.getText().toString().equals("KIRIM")) {
                    if (!AppFragmentActivity.this.prefuser.getString("token_bot", "").equals("") && !AppFragmentActivity.this.prefuser.getString("chat_id", "").equals("")) {
                        if (AppFragmentActivity.this.ls_backupbot.size() != 0) {
                            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                            appFragmentActivity._sendFileToYourBot(appFragmentActivity.ls_backupbot);
                            return;
                        }
                        SketchwareUtil.showMessage(AppFragmentActivity.this.getContext().getApplicationContext(), "Tidak ada file untuk dikirim");
                        return;
                    }
                    SketchwareUtil.showMessage(AppFragmentActivity.this.getContext().getApplicationContext(), "Bot belum disetting");
                    return;
                }
                AppFragmentActivity.this.BOT.dismiss();
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.BOT = create;
        create.show();
    }

    public void _showFabEternal() {
        ArrayList<HashMap<String, Object>> arrayList = (ArrayList) new Gson().fromJson(this.prefall.getString("all_app_eternal", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.92
        }.getType());
        this.lm_eternal_app = arrayList;
        if (arrayList.size() == 0) {
            this.fabeternal.setVisibility(8);
            return;
        }
        Iterator<HashMap<String, Object>> it = this.lm_eternal_app.iterator();
        while (it.hasNext()) {
            Object obj = it.next().get("apppackage");
            if (obj != null && obj.toString().equals(this.prefui.getString("backup_app_package", ""))) {
                this.fabeternal.setVisibility(0);
                return;
            }
            this.fabeternal.setVisibility(8);
        }
    }

    public void _onRitualWipeData() {
        MyRITUALWIPEDATA myRITUALWIPEDATA = this.myRITUALWIPEDATA;
        if (myRITUALWIPEDATA != null && myRITUALWIPEDATA.isRunning) {
            this.myRITUALWIPEDATA.cancelRITUALWIPEDATATask();
        }
        MyRITUALWIPEDATA myRITUALWIPEDATA2 = new MyRITUALWIPEDATA();
        this.myRITUALWIPEDATA = myRITUALWIPEDATA2;
        myRITUALWIPEDATA2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyRITUALWIPEDATA extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyRITUALWIPEDATA() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            AppFragmentActivity.this.tv_status.setText("Wipe Data App\nProses...");
            AppFragmentActivity.this.updateTextRitual();
            AppFragmentActivity.this.s_ritual_app = "app_package=\"fupackagename\"\nandroid_data=\"/storage/emulated/0/Android/data\"\nam force-stop $app_package > /dev/null 2>&1\necho \"Sukses force stop aplikasi $app_package\"\nif pm clear $app_package > /dev/null 2>&1; then\necho \"Sukses menghapus data dan cache aplikasi\"\nrm -rf $android_data/$app_package > /dev/null 2>&1;\necho \"Sukses menghapus $android_data/$app_package\"\nelse\necho \"Gagal menghapus $android_data/$app_package\"\nfi";
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_ritual_app = appFragmentActivity.s_ritual_app.replace("fupackagename", AppFragmentActivity.this.s_package_ritual);
            AppFragmentActivity.this.b_command = false;
            AppFragmentActivity.this.s_exitCode = "";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            AppFragmentActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(AppFragmentActivity.this.s_ritual_app).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            AppFragmentActivity.this.b_command = exec.isSuccess();
            AppFragmentActivity.this.s_commandResult = AppFragmentActivity$MyRITUALWIPEDATA$$ExternalSyntheticBackport0.m("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            AppFragmentActivity.this._cekReinstallApp();
        }

        public void cancelRITUALWIPEDATATask() {
            cancel(true);
        }
    }

    public void _onRitualReinstallApp() {
        MyRITUALREINSTALL myRITUALREINSTALL = this.myRITUALREINSTALL;
        if (myRITUALREINSTALL != null && myRITUALREINSTALL.isRunning) {
            this.myRITUALREINSTALL.cancelRITUALREINSTALLTask();
        }
        MyRITUALREINSTALL myRITUALREINSTALL2 = new MyRITUALREINSTALL();
        this.myRITUALREINSTALL = myRITUALREINSTALL2;
        myRITUALREINSTALL2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyRITUALREINSTALL extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyRITUALREINSTALL() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            AppFragmentActivity.this.tv_status.setText("Install Ulang App\nProses...");
            AppFragmentActivity.this.tv_ritual_result.setText(AppFragmentActivity.this.s_commandResult);
            AppFragmentActivity.this.updateTextRitual();
            AppFragmentActivity.this.s_ritual_app = "app_package=\"fupackagename\"\nbase_path=$(pm path $app_package)\nfull_path=${base_path#*:}\ntmp_path=\"/data/local/tmp/\"\n\nmkdir -p $tmp_path > /dev/null 2>&1;\necho \"Persiapan proses reinstall aplikasi\"\ncp \"$full_path\" \"$tmp_path\" > /dev/null 2>&1;\n\nif [ -f \"$tmp_path/${full_path##*/}\" ] > /dev/null 2>&1; then\n\nif pm uninstall $app_package > /dev/null 2>&1; then\necho \"Sukses menguninstal aplikasi\"\nif pm install -r \"$tmp_path/base.apk\" > /dev/null 2>&1; then\necho \"Sukses menginstal ulang aplikasi\"\nelse\necho \"Gagal menginstal aplikasi \"\nfi\n\nelse\necho \"Gagal menguninstall aplikasi\"\nfi\n\nelse\necho \"Gagal menguninstall aplikasi\"\nfi";
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_ritual_app = appFragmentActivity.s_ritual_app.replace("fupackagename", AppFragmentActivity.this.s_package_ritual);
            AppFragmentActivity.this.b_command = false;
            AppFragmentActivity.this.s_exitCode = "";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            AppFragmentActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(AppFragmentActivity.this.s_ritual_app).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            AppFragmentActivity.this.b_command = exec.isSuccess();
            AppFragmentActivity.this.s_commandResult = AppFragmentActivity$MyRITUALREINSTALL$$ExternalSyntheticBackport0.m("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            AppFragmentActivity.this._cekWipeGms();
        }

        public void cancelRITUALREINSTALLTask() {
            cancel(true);
        }
    }

    public void _onRitualWipeGms() {
        MyRITUALWIPEGMS myRITUALWIPEGMS = this.myRITUALWIPEGMS;
        if (myRITUALWIPEGMS != null && myRITUALWIPEGMS.isRunning) {
            this.myRITUALWIPEGMS.cancelRITUALWIPEGMSTask();
        }
        MyRITUALWIPEGMS myRITUALWIPEGMS2 = new MyRITUALWIPEGMS();
        this.myRITUALWIPEGMS = myRITUALWIPEGMS2;
        myRITUALWIPEGMS2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyRITUALWIPEGMS extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyRITUALWIPEGMS() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            AppFragmentActivity.this.tv_status.setText("Wipe Data GMS\nProses...");
            AppFragmentActivity.this.tv_ritual_result.setText(AppFragmentActivity.this.s_commandResult);
            AppFragmentActivity.this.updateTextRitual();
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_ritual_app = appFragmentActivity.s_commandBase.concat("\nritualgms");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            AppFragmentActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(AppFragmentActivity.this.s_ritual_app).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            AppFragmentActivity.this.b_command = exec.isSuccess();
            AppFragmentActivity.this.s_commandResult = AppFragmentActivity$MyRITUALWIPEGMS$$ExternalSyntheticBackport0.m("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            AppFragmentActivity.this._cekCleanTimepick();
        }

        public void cancelRITUALWIPEGMSTask() {
            cancel(true);
        }
    }

    public void _onRitualTimepick() {
        MyRITUALTIMEPICK myRITUALTIMEPICK = this.myRITUALTIMEPICK;
        if (myRITUALTIMEPICK != null && myRITUALTIMEPICK.isRunning) {
            this.myRITUALTIMEPICK.cancelRITUALTIMEPICKTask();
        }
        MyRITUALTIMEPICK myRITUALTIMEPICK2 = new MyRITUALTIMEPICK();
        this.myRITUALTIMEPICK = myRITUALTIMEPICK2;
        myRITUALTIMEPICK2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyRITUALTIMEPICK extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyRITUALTIMEPICK() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            AppFragmentActivity.this.tv_status.setText("Cleaning\nProses... (wait)");
            AppFragmentActivity.this.tv_ritual_result.setText(AppFragmentActivity.this.s_commandResult);
            AppFragmentActivity.this.s_commandResult = "";
            AppFragmentActivity.this.b_command = false;
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_ritual_app = appFragmentActivity.s_commandBase.concat("\nritualcln");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            AppFragmentActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(AppFragmentActivity.this.s_ritual_app).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            AppFragmentActivity.this.b_command = exec.isSuccess();
            AppFragmentActivity.this.s_commandResult = AppFragmentActivity$MyRITUALTIMEPICK$$ExternalSyntheticBackport0.m("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            this.isRunning = false;
            AppFragmentActivity.this.pbar_ritual.setVisibility(4);
            AppFragmentActivity.this.tv_status.setText("\nSelesai");
            AppFragmentActivity.this.tv_ritual_result.setText(AppFragmentActivity.this.s_commandResult);
            AppFragmentActivity.this.updateTextRitual();
        }

        public void cancelRITUALTIMEPICKTask() {
            cancel(true);
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
            AppFragmentActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(AppFragmentActivity.this.s_command).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            AppFragmentActivity.this.b_command = exec.isSuccess();
            AppFragmentActivity.this.s_commandResult = AppFragmentActivity$MyBackgroundAction$$ExternalSyntheticBackport0.m("\n", out);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            this.isRunning = false;
            AppFragmentActivity.this.b_command = true;
            AppFragmentActivity.this.b_restore = true;
        }

        public void cancelBackgroundActionTask() {
            cancel(true);
        }
    }

    public void _setAppPosition() {
        if (this.prefui.getString("backup_app_package", "").equals("")) {
            return;
        }
        int i = 0;
        if (this.lv_app.getVisibility() == 0) {
            this.n_restore_pos_app = 0.0d;
            while (i < this.lm_all_app.size()) {
                if (this.lm_all_app.get((int) this.n_restore_pos_app).get("apppackage").toString().equals(this.prefui.getString("backup_app_package", ""))) {
                    this.lv_app.setSelection((int) this.n_restore_pos_app);
                    return;
                } else {
                    this.n_restore_pos_app += 1.0d;
                    i++;
                }
            }
        } else if (this.lv_fav.getVisibility() == 0) {
            this.n_restore_pos_app = 0.0d;
            while (i < this.lm_fav_app.size()) {
                if (this.lm_fav_app.get((int) this.n_restore_pos_app).get("apppackage").toString().equals(this.prefui.getString("backup_app_package", ""))) {
                    this.lv_fav.setSelection((int) this.n_restore_pos_app);
                    return;
                } else {
                    this.n_restore_pos_app += 1.0d;
                    i++;
                }
            }
        }
    }

    public void _onCreateBackup() {
        if (this.b_rebackup) {
            BackupDialogFragmentActivity backupDialogFragmentActivity = new BackupDialogFragmentActivity();
            Bundle bundle = new Bundle();
            bundle.putString("extrakey", this.s_extra);
            backupDialogFragmentActivity.setArguments(bundle);
            backupDialogFragmentActivity.show(getActivity().getSupportFragmentManager(), "BackupDialogFragmentActivity1");
            return;
        }
        BackupDialogFragmentActivity backupDialogFragmentActivity2 = new BackupDialogFragmentActivity();
        Bundle bundle2 = new Bundle();
        bundle2.putString("extrakey", "");
        backupDialogFragmentActivity2.setArguments(bundle2);
        backupDialogFragmentActivity2.show(getActivity().getSupportFragmentManager(), "BackupDialogFragmentActivity1");
    }

    public void _showLoadingMain(String str) {
        if (getActivity() instanceof KatrinaActivity) {
            ((KatrinaActivity) getActivity())._showLoadingMain(str);
        }
    }

    public void _hideLoadingMain() {
        if (getActivity() instanceof KatrinaActivity) {
            ((KatrinaActivity) getActivity())._hideLoadingMain();
        }
    }

    public void _startSorterBackup() {
        if (getActivity() instanceof KatrinaActivity) {
            ((KatrinaActivity) getActivity())._startFiltering();
        }
    }

    public void _showDialogFilter() {
        showFILTER();
    }

    private void showFILTER() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0025, (ViewGroup) null);
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(requireContext());
        materialAlertDialogBuilder.setView(inflate);
        materialAlertDialogBuilder.setCancelable(true);
        Button button = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        Button button2 = (Button) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0086);
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.admsoloraya_res_0x7f0a010d);
        final CheckBox checkBox2 = (CheckBox) inflate.findViewById(R.id.admsoloraya_res_0x7f0a010c);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a005b);
        TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0499);
        autoCompleteTextView.setFocusable(true);
        autoCompleteTextView.setFocusableInTouchMode(true);
        autoCompleteTextView.setText(this.prefui.getString("short_filter", ""));
        autoCompleteTextView.setHint("Cari catatan");
        if (this.prefui.getString("short_action", "").equals("move")) {
            checkBox.setChecked(true);
            checkBox2.setChecked(false);
        } else if (this.prefui.getString("short_action", "").equals("delete")) {
            checkBox2.setChecked(true);
            checkBox.setChecked(false);
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.93
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z) {
                    AppFragmentActivity.this.prefui.edit().putString("short_action", "").commit();
                    return;
                }
                checkBox2.setChecked(false);
                AppFragmentActivity.this.prefui.edit().putString("short_action", "move").commit();
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.94
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z) {
                    AppFragmentActivity.this.prefui.edit().putString("short_action", "").commit();
                    return;
                }
                checkBox.setChecked(false);
                AppFragmentActivity.this.prefui.edit().putString("short_action", "delete").commit();
            }
        });
        autoCompleteTextView.addTextChangedListener(new TextWatcher() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.95
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                AppFragmentActivity.this.s_filterbackup = charSequence.toString();
                AppFragmentActivity.this.prefui.edit().putString("short_filter", AppFragmentActivity.this.s_filterbackup).commit();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.96
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AppFragmentActivity.this.FILTER == null || !AppFragmentActivity.this.FILTER.isShowing()) {
                    return;
                }
                AppFragmentActivity.this.FILTER.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.97
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AppFragmentActivity.this.prefui.getString("short_filter", "").equals("") || AppFragmentActivity.this.prefui.getString("short_action", "").equals("")) {
                    SketchwareUtil.showMessage(AppFragmentActivity.this.getContext().getApplicationContext(), "Data tidak lengkap");
                    return;
                }
                AppFragmentActivity.this._onStartFilter();
                if (AppFragmentActivity.this.FILTER == null || !AppFragmentActivity.this.FILTER.isShowing()) {
                    return;
                }
                AppFragmentActivity.this.FILTER.dismiss();
            }
        });
        AlertDialog create = materialAlertDialogBuilder.create();
        this.FILTER = create;
        create.show();
    }

    public void _onStartFilter() {
        MySTARTSORTIR mySTARTSORTIR = this.mySTARTSORTIR;
        if (mySTARTSORTIR != null && mySTARTSORTIR.isRunning) {
            this.mySTARTSORTIR.cancelSTARTSORTIRTask();
        }
        MySTARTSORTIR mySTARTSORTIR2 = new MySTARTSORTIR();
        this.mySTARTSORTIR = mySTARTSORTIR2;
        mySTARTSORTIR2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MySTARTSORTIR extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MySTARTSORTIR() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            AppFragmentActivity.this._showLoadingMain("Melakukan pencarian dan sortir");
            AppFragmentActivity.this.s_cek_sortir = FileUtil.getExternalStorageDir().concat("/".concat("XKatrinaSortir"));
            AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
            appFragmentActivity.s_cek_folder = appFragmentActivity.s_cek_sortir.concat("/".concat(AppFragmentActivity.this.prefui.getString("backup_app_package", "")));
            if (!FileUtil.isExistFile(AppFragmentActivity.this.s_cek_sortir)) {
                FileUtil.makeDir(AppFragmentActivity.this.s_cek_sortir);
            }
            if (!FileUtil.isExistFile(AppFragmentActivity.this.s_cek_folder)) {
                FileUtil.makeDir(AppFragmentActivity.this.s_cek_folder);
            }
            AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
            appFragmentActivity2.s_filterbackup = appFragmentActivity2.prefui.getString("short_filter", "");
            AppFragmentActivity.this.n_shortfilter = 0.0d;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            int parseInt;
            if (isCancelled()) {
                return null;
            }
            for (int i = 0; i < AppFragmentActivity.this.lm_backup_data.size(); i++) {
                AppFragmentActivity appFragmentActivity = AppFragmentActivity.this;
                appFragmentActivity.s_string = ((HashMap) appFragmentActivity.lm_backup_data.get((int) AppFragmentActivity.this.n_shortfilter)).get("appnote").toString();
                if (!AppFragmentActivity.this.prefui.getString("short_action", "").equals("move")) {
                    if (AppFragmentActivity.this.s_string.contains(AppFragmentActivity.this.s_filterbackup)) {
                        AppFragmentActivity appFragmentActivity2 = AppFragmentActivity.this;
                        appFragmentActivity2.s_string_folder = ((HashMap) appFragmentActivity2.lm_backup_data.get((int) AppFragmentActivity.this.n_shortfilter)).get("appfolder").toString();
                        FileUtil.deleteFile(AppFragmentActivity.this.s_string_folder);
                    }
                } else {
                    if (!FileUtil.isExistFile(AppFragmentActivity.this.s_cek_folder)) {
                        AppFragmentActivity.this.s_backup_number = "001";
                    } else {
                        String[] list = new File(AppFragmentActivity.this.s_cek_folder).list();
                        Arrays.sort(list);
                        int i2 = 0;
                        for (String str : list) {
                            if (AppFragmentActivity.this.isInteger(str) && (parseInt = Integer.parseInt(str)) > i2) {
                                i2 = parseInt;
                            }
                        }
                        AppFragmentActivity.this.s_backup_number = String.format("%03d", Integer.valueOf(i2 + 1));
                    }
                    if (AppFragmentActivity.this.s_string.contains(AppFragmentActivity.this.s_filterbackup)) {
                        AppFragmentActivity appFragmentActivity3 = AppFragmentActivity.this;
                        appFragmentActivity3.s_shortslot = appFragmentActivity3.s_cek_folder.concat("/".concat(AppFragmentActivity.this.s_backup_number));
                        FileUtil.makeDir(AppFragmentActivity.this.s_shortslot);
                        AppFragmentActivity appFragmentActivity4 = AppFragmentActivity.this;
                        appFragmentActivity4.s_string_folder = ((HashMap) appFragmentActivity4.lm_backup_data.get((int) AppFragmentActivity.this.n_shortfilter)).get("appfolder").toString();
                        File file = new File(AppFragmentActivity.this.s_string_folder);
                        File file2 = new File(AppFragmentActivity.this.s_shortslot);
                        if (file.isDirectory()) {
                            if (!file2.exists()) {
                                file2.mkdirs();
                            }
                            File[] listFiles = file.listFiles();
                            if (listFiles != null) {
                                for (File file3 : listFiles) {
                                    file3.renameTo(new File(file2, file3.getName()));
                                }
                            }
                            file.delete();
                            System.out.println("Folder berhasil dipindahkan.");
                        } else {
                            System.out.println("Sumber direktori tidak ditemukan atau bukan direktori.");
                        }
                    }
                }
                AppFragmentActivity.this.n_shortfilter += 1.0d;
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            this.isRunning = false;
            AppFragmentActivity.this._hideLoadingMain();
            AppFragmentActivity.this._onReadBackup();
        }

        public void cancelSTARTSORTIRTask() {
            cancel(true);
        }
    }

    /* loaded from: classes5.dex */
    public class Lv_appAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_appAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = AppFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d002f, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            MaterialCardView materialCardView = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0354);
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04e9);
            final ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a011d);
            try {
                imageView.setImageDrawable(AppFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(this._data.get(i).get("apppackage").toString()));
                textView.setText(this._data.get(i).get("appname").toString());
            } catch (PackageManager.NameNotFoundException unused) {
                textView.setText(this._data.get(i).get("appname").toString());
            }
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.Lv_appAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.prefui.edit().putString("backup_app_name", Lv_appAdapter.this._data.get(i).get("appname").toString()).commit();
                    AppFragmentActivity.this.prefui.edit().putString("backup_app_package", Lv_appAdapter.this._data.get(i).get("apppackage").toString()).commit();
                    AppFragmentActivity.this._setSearchNull();
                    AppFragmentActivity.this._onReadBackup();
                    AppFragmentActivity.this._setBackupTopChip();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.Lv_appAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this._showPopupApp(i, Lv_appAdapter.this._data.get(i).get("apppackage").toString(), Lv_appAdapter.this._data.get(i).get("appname").toString(), Lv_appAdapter.this._data.get(i).get("appversion").toString(), imageView);
                }
            });
            return view;
        }
    }

    /* loaded from: classes5.dex */
    public class Lv_favAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_favAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = AppFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d002f, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            MaterialCardView materialCardView = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0354);
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04e9);
            final ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a011d);
            try {
                imageView.setImageDrawable(AppFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(this._data.get(i).get("apppackage").toString()));
                textView.setText(this._data.get(i).get("appname").toString());
            } catch (PackageManager.NameNotFoundException unused) {
                textView.setText(this._data.get(i).get("appname").toString());
            }
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.Lv_favAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this.prefui.edit().putString("backup_app_name", Lv_favAdapter.this._data.get(i).get("appname").toString()).commit();
                    AppFragmentActivity.this.prefui.edit().putString("backup_app_package", Lv_favAdapter.this._data.get(i).get("apppackage").toString()).commit();
                    AppFragmentActivity.this._setSearchNull();
                    AppFragmentActivity.this._onReadBackup();
                    AppFragmentActivity.this._setBackupTopChip();
                }
            });
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.Lv_favAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AppFragmentActivity.this._showPopupFav(i, Lv_favAdapter.this._data.get(i).get("apppackage").toString(), Lv_favAdapter.this._data.get(i).get("appname").toString(), Lv_favAdapter.this._data.get(i).get("appversion").toString(), imageView);
                }
            });
            return view;
        }
    }

    /* loaded from: classes5.dex */
    public class Lv_backupAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_backupAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            MaterialButton materialButton;
            View inflate = view == null ? AppFragmentActivity.this.getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0030, (ViewGroup) null) : view;
            MaterialCardView materialCardView = (MaterialCardView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a014a);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02e3);
            LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a029a);
            TextView textView = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0500);
            LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0254);
            LinearLayout linearLayout5 = (LinearLayout) inflate.findViewById(R.id.admsoloraya_res_0x7f0a02c0);
            TextView textView2 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a052e);
            MaterialButton materialButton2 = (MaterialButton) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00bd);
            final MaterialButton materialButton3 = (MaterialButton) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00fc);
            MaterialButton materialButton4 = (MaterialButton) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00bf);
            MaterialButton materialButton5 = (MaterialButton) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00e5);
            MaterialButton materialButton6 = (MaterialButton) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00e9);
            TextView textView3 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a04f6);
            TextView textView4 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0556);
            TextView textView5 = (TextView) inflate.findViewById(R.id.admsoloraya_res_0x7f0a0532);
            MaterialButton materialButton7 = (MaterialButton) inflate.findViewById(R.id.admsoloraya_res_0x7f0a00f9);
            View view2 = inflate;
            textView2.setText(Uri.parse(this._data.get(i).get("appfolder").toString()).getLastPathSegment());
            textView3.setText(this._data.get(i).get("appbuild").toString());
            textView.setText(this._data.get(i).get("appdate").toString());
            textView4.setText(this._data.get(i).get("appsize").toString());
            if (this._data.get(i).get("appnote").toString().equals("")) {
                textView5.setText("Buat catatan disini");
            } else {
                textView5.setText(this._data.get(i).get("appnote").toString());
            }
            if (this._data.get(i).get("appmark").toString().equals("true")) {
                AppFragmentActivity.this.s_markcolor = this._data.get(i).get("appcolor").toString();
                materialButton3.setIcon(AppFragmentActivity.this.getResources().getDrawable(R.drawable.admsoloraya_res_0x7f080127));
                materialCardView.setCardBackgroundColor(Color.parseColor(AppFragmentActivity.this.s_markcolor));
                if (AppFragmentActivity.this.s_markcolor.equals("#FF9866E8") || AppFragmentActivity.this.s_markcolor.equals("#FFF9635F") || AppFragmentActivity.this.s_markcolor.equals("#FFF9635F") || AppFragmentActivity.this.s_markcolor.equals("#FF5CD574") || AppFragmentActivity.this.s_markcolor.equals("#FF4294FF")) {
                    materialButton = materialButton6;
                    materialButton2.setIconTintResource(R.color.admsoloraya_res_0x7f0600b2);
                    materialButton3.setIconTintResource(R.color.admsoloraya_res_0x7f0600b2);
                    materialButton4.setIconTintResource(R.color.admsoloraya_res_0x7f0600b2);
                    materialButton5.setIconTintResource(R.color.admsoloraya_res_0x7f0600b2);
                    materialButton7.setIconTintResource(R.color.admsoloraya_res_0x7f0600b2);
                    textView2.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f0600b2));
                    textView.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f0600b2));
                    textView3.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f0600b2));
                    textView5.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f0600b2));
                    textView4.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f0600b2));
                    materialButton.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f0600b2));
                } else {
                    materialButton2.setIconTintResource(R.color.admsoloraya_res_0x7f060040);
                    materialButton3.setIconTintResource(R.color.admsoloraya_res_0x7f060040);
                    materialButton4.setIconTintResource(R.color.admsoloraya_res_0x7f060040);
                    materialButton5.setIconTintResource(R.color.admsoloraya_res_0x7f060040);
                    materialButton7.setIconTintResource(R.color.admsoloraya_res_0x7f060040);
                    textView2.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f060040));
                    textView.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f060040));
                    textView3.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f060040));
                    textView5.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f060040));
                    textView4.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f060040));
                    materialButton = materialButton6;
                    materialButton.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f060040));
                }
            } else {
                materialButton = materialButton6;
                materialButton3.setIcon(AppFragmentActivity.this.getResources().getDrawable(R.drawable.admsoloraya_res_0x7f080128));
                materialCardView.setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(AppFragmentActivity.this.requireContext()));
                materialButton2.setIconTintResource(R.color.admsoloraya_res_0x7f060040);
                materialButton3.setIconTintResource(R.color.admsoloraya_res_0x7f060040);
                materialButton4.setIconTintResource(R.color.admsoloraya_res_0x7f060040);
                materialButton5.setIconTintResource(R.color.admsoloraya_res_0x7f060040);
                materialButton7.setIconTintResource(R.color.admsoloraya_res_0x7f060040);
                textView2.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f060040));
                textView.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f060040));
                textView3.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f060040));
                textView5.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f060040));
                textView4.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f060040));
                materialButton.setTextColor(AppFragmentActivity.this.getResources().getColor(R.color.admsoloraya_res_0x7f060040));
            }
            if (i == this._data.size() - 1) {
                linearLayout.setVisibility(0);
            } else {
                linearLayout.setVisibility(8);
            }
            materialButton3.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.Lv_backupAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AppFragmentActivity.this._showPopupBackup(i, Lv_backupAdapter.this._data, materialButton3);
                }
            });
            materialButton2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.Lv_backupAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AppFragmentActivity.this._onDeleteFileBackup(i, Lv_backupAdapter.this._data.get(i).get("appfolder").toString(), Lv_backupAdapter.this._data.get(i).get("appdate").toString(), Lv_backupAdapter.this._data.get(i).get("appsize").toString());
                }
            });
            materialButton4.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.Lv_backupAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AppFragmentActivity.this.s_path_json = FileUtil.readFile(Lv_backupAdapter.this._data.get(i).get("appjson").toString());
                    AppFragmentActivity.this._showInfoBackup();
                }
            });
            textView5.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.Lv_backupAdapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AppFragmentActivity.this.n_position = i;
                    AppFragmentActivity.this._showEditNote();
                }
            });
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.Lv_backupAdapter.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AppFragmentActivity.this._showDialogRestore(Lv_backupAdapter.this._data.get(i).get("appfile").toString(), Lv_backupAdapter.this._data.get(i).get("settings_ssaid").toString(), Lv_backupAdapter.this._data.get(i).get("system.prop").toString(), Lv_backupAdapter.this._data.get(i).get("appsdk").toString());
                }
            });
            materialButton5.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.Lv_backupAdapter.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AppFragmentActivity.this.b_rebackup = true;
                    AppFragmentActivity.this.m_extra = new HashMap();
                    AppFragmentActivity.this.m_extra.put("appfolder", Lv_backupAdapter.this._data.get(i).get("appfolder").toString());
                    AppFragmentActivity.this.m_extra.put("appfile", Lv_backupAdapter.this._data.get(i).get("appfile").toString());
                    AppFragmentActivity.this.s_extra = new Gson().toJson(AppFragmentActivity.this.m_extra);
                    AppFragmentActivity.this._onCreateBackup();
                }
            });
            materialButton7.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AppFragmentActivity.Lv_backupAdapter.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                    AppFragmentActivity.this.ls_backupbot.clear();
                    AppFragmentActivity.this.ls_backupbot.add(Lv_backupAdapter.this._data.get(i).get("appfile").toString());
                    AppFragmentActivity.this.ls_backupbot.add(Lv_backupAdapter.this._data.get(i).get("appjson").toString());
                    AppFragmentActivity.this._openDialogBot();
                }
            });
            return view2;
        }
    }
}
