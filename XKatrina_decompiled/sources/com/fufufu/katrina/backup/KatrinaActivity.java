package com.fufufu.katrina.backup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.elevation.SurfaceColors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.termfu.app.TermuxActivity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
/* loaded from: classes5.dex */
public class KatrinaActivity extends AppCompatActivity {
    private ChildEventListener _app_data_child_listener;
    private OnCompleteListener<AuthResult> _katrina_auth_create_user_listener;
    private OnCompleteListener<Void> _katrina_auth_reset_password_listener;
    private OnCompleteListener<AuthResult> _katrina_auth_sign_in_listener;
    private BottomNavigationView bottom_nav;
    private MaterialButton btn_oke;
    private FrameLayout frame;
    private FrameLayout framebase;
    private FuFragmentAdapter fu;
    private FirebaseAuth katrina_auth;
    private OnCompleteListener<Void> katrina_auth_deleteUserListener;
    private OnCompleteListener<Void> katrina_auth_emailVerificationSentListener;
    private OnCompleteListener<AuthResult> katrina_auth_googleSignInListener;
    private OnCompleteListener<AuthResult> katrina_auth_phoneAuthListener;
    private OnCompleteListener<Void> katrina_auth_updateEmailListener;
    private OnCompleteListener<Void> katrina_auth_updatePasswordListener;
    private OnCompleteListener<Void> katrina_auth_updateProfileListener;
    private LinearLayout ln_1;
    private LinearLayout ln_2;
    private LinearLayout ln_loadingmain;
    private MyFILTER myFILTER;
    private MyRENAME myRENAME;
    private ProgressBar pbar_loadingmain;
    private SharedPreferences pref;
    private SharedPreferences.OnSharedPreferenceChangeListener prefappListener;
    private SharedPreferences prefui;
    private SharedPreferences prefuser;
    private TextView tv_subtitle_loadingmain;
    private TextView tv_title_loadingmain;
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    int nilaiTertinggi = 0;
    int highestFolderNumber = 0;
    private String s_pos = "";
    private String s_version_code = "";
    private HashMap<String, Object> m_account = new HashMap<>();
    private HashMap<String, Object> m_getnetwork = new HashMap<>();
    private HashMap<String, Object> m_banned = new HashMap<>();
    private boolean b_username = false;
    private boolean b_date = false;
    private String s_preferenceloc = "";
    private String s_result = "";
    private String s_url_splash = "";
    private String s_files_path = "";
    private String s_fufufush_path = "";
    private String s_sc_path = "";
    private String s_home_path = "";
    private String s_sh_path = "";
    private ArrayList<HashMap<String, Object>> lm_app_data = new ArrayList<>();
    private ArrayList<String> ls_check = new ArrayList<>();
    private DatabaseReference app_data = this._firebase.getReference("app_data");
    private Calendar cal = Calendar.getInstance();

    public void _EXTRA() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d005a);
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
        this.frame = (FrameLayout) findViewById(R.id.admsoloraya_res_0x7f0a01b8);
        this.ln_loadingmain = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02b7);
        this.bottom_nav = (BottomNavigationView) findViewById(R.id.admsoloraya_res_0x7f0a0074);
        this.framebase = (FrameLayout) findViewById(R.id.admsoloraya_res_0x7f0a01ba);
        this.pbar_loadingmain = (ProgressBar) findViewById(R.id.admsoloraya_res_0x7f0a03ef);
        this.ln_2 = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0264);
        this.ln_1 = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0263);
        this.btn_oke = (MaterialButton) findViewById(R.id.admsoloraya_res_0x7f0a00cc);
        this.tv_title_loadingmain = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a058c);
        this.tv_subtitle_loadingmain = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a056e);
        this.fu = new FuFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
        this.pref = getSharedPreferences("release_preference", 0);
        this.prefuser = getSharedPreferences("user_preferences", 0);
        this.prefui = getSharedPreferences("preferences_ui", 0);
        this.katrina_auth = FirebaseAuth.getInstance();
        this.bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { // from class: com.fufufu.katrina.backup.KatrinaActivity.1
            @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == 0 && !KatrinaActivity.this.s_pos.equals("0")) {
                    KatrinaActivity.this.s_pos = "0";
                    KatrinaActivity.this._fragmentApp();
                }
                if (itemId == 1 && !KatrinaActivity.this.s_pos.equals("1")) {
                    KatrinaActivity.this.s_pos = "1";
                    KatrinaActivity.this._fragmentRitual();
                }
                if (itemId == 2 && !KatrinaActivity.this.s_pos.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                    KatrinaActivity.this.s_pos = ExifInterface.GPS_MEASUREMENT_2D;
                    KatrinaActivity.this._fragmentBlocker();
                }
                if (itemId == 3 && !KatrinaActivity.this.s_pos.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    KatrinaActivity.this.s_pos = ExifInterface.GPS_MEASUREMENT_3D;
                    KatrinaActivity.this._fragmentSetelan();
                }
                if (itemId == 4 && !KatrinaActivity.this.s_pos.equals("4")) {
                    KatrinaActivity.this.s_pos = "4";
                    KatrinaActivity.this._fragmentDonate();
                }
                return true;
            }
        });
        this.btn_oke.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.KatrinaActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KatrinaActivity.this._hideLoadingMain();
                KatrinaActivity.this._fragmentApp();
            }
        });
        ChildEventListener childEventListener = new ChildEventListener() { // from class: com.fufufu.katrina.backup.KatrinaActivity.3
            @Override // com.google.firebase.database.ChildEventListener
            public void onChildMoved(DataSnapshot dataSnapshot, String str) {
            }

            @Override // com.google.firebase.database.ChildEventListener
            public void onChildAdded(DataSnapshot dataSnapshot, String str) {
                GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.KatrinaActivity.3.1
                };
                String key = dataSnapshot.getKey();
                HashMap hashMap = (HashMap) dataSnapshot.getValue(genericTypeIndicator);
                if (!key.equals("app_data_katrina")) {
                    KatrinaActivity.this.pref.edit().putString("current", KatrinaActivity.this.s_version_code).commit();
                    KatrinaActivity.this.pref.edit().putString("release", "").commit();
                } else {
                    try {
                        int i = KatrinaActivity.this.getPackageManager().getPackageInfo(KatrinaActivity.this.getPackageName(), 0).versionCode;
                        KatrinaActivity.this.s_version_code = String.valueOf(i);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (KatrinaActivity.this.s_version_code.equals(hashMap.get("release_version").toString())) {
                        KatrinaActivity.this.pref.edit().putString("current", KatrinaActivity.this.s_version_code).commit();
                        KatrinaActivity.this.pref.edit().putString("release", "").commit();
                    } else {
                        KatrinaActivity.this.lm_app_data.clear();
                        KatrinaActivity.this.app_data.addListenerForSingleValueEvent(new ValueEventListener() { // from class: com.fufufu.katrina.backup.KatrinaActivity.3.2
                            @Override // com.google.firebase.database.ValueEventListener
                            public void onCancelled(DatabaseError databaseError) {
                            }

                            @Override // com.google.firebase.database.ValueEventListener
                            public void onDataChange(DataSnapshot dataSnapshot2) {
                                KatrinaActivity.this.lm_app_data = new ArrayList();
                                try {
                                    GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator2 = new GenericTypeIndicator<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.KatrinaActivity.3.2.1
                                    };
                                    for (DataSnapshot dataSnapshot3 : dataSnapshot2.getChildren()) {
                                        KatrinaActivity.this.lm_app_data.add((HashMap) dataSnapshot3.getValue(genericTypeIndicator2));
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                                KatrinaActivity.this.pref.edit().putString("current", KatrinaActivity.this.s_version_code).commit();
                                KatrinaActivity.this.pref.edit().putString("release", new Gson().toJson(KatrinaActivity.this.lm_app_data)).commit();
                            }
                        });
                    }
                    KatrinaActivity.this.s_url_splash = hashMap.get("splash_url").toString();
                    if (KatrinaActivity.this.s_url_splash.equals("")) {
                        KatrinaActivity.this.pref.edit().putString("splash", "").commit();
                        FileUtil.deleteFile("/data/user/0/".concat(KatrinaActivity.this.getApplicationContext().getPackageName().concat("/splash.jpg")));
                    } else if (!KatrinaActivity.this.s_url_splash.equals(KatrinaActivity.this.pref.getString("splash", ""))) {
                        KatrinaActivity.this.pref.edit().putString("splash", KatrinaActivity.this.s_url_splash).commit();
                        KatrinaActivity.this._onDownloadSplash();
                    }
                }
                KatrinaActivity.this.app_data.removeEventListener(KatrinaActivity.this._app_data_child_listener);
            }

            @Override // com.google.firebase.database.ChildEventListener
            public void onChildChanged(DataSnapshot dataSnapshot, String str) {
                GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.KatrinaActivity.3.3
                };
                dataSnapshot.getKey();
                HashMap hashMap = (HashMap) dataSnapshot.getValue(genericTypeIndicator);
            }

            @Override // com.google.firebase.database.ChildEventListener
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() { // from class: com.fufufu.katrina.backup.KatrinaActivity.3.4
                };
                dataSnapshot.getKey();
                HashMap hashMap = (HashMap) dataSnapshot.getValue(genericTypeIndicator);
            }

            @Override // com.google.firebase.database.ChildEventListener
            public void onCancelled(DatabaseError databaseError) {
                databaseError.getCode();
                databaseError.getMessage();
            }
        };
        this._app_data_child_listener = childEventListener;
        this.app_data.addChildEventListener(childEventListener);
        this.katrina_auth_updateEmailListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.KatrinaActivity.4
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_auth_updatePasswordListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.KatrinaActivity.5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_auth_emailVerificationSentListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.KatrinaActivity.6
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_auth_deleteUserListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.KatrinaActivity.7
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_auth_phoneAuthListener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.KatrinaActivity.8
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_auth_updateProfileListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.KatrinaActivity.9
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_auth_googleSignInListener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.KatrinaActivity.10
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this._katrina_auth_create_user_listener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.KatrinaActivity.11
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this._katrina_auth_sign_in_listener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.KatrinaActivity.12
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this._katrina_auth_reset_password_listener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.KatrinaActivity.13
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
            }
        };
    }

    private void initializeLogic() {
        _dinamycShortcut();
        _setFirstUI();
        this.app_data.addChildEventListener(this._app_data_child_listener);
        if (getIntent().hasExtra("setelan")) {
            _fragmentSetelan();
            return;
        }
        _EXTRASH();
        _fragmentApp();
    }

    /* loaded from: classes5.dex */
    public class FuFragmentAdapter extends FragmentStatePagerAdapter {
        Context context;
        int tabCount;

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int i) {
            return null;
        }

        public FuFragmentAdapter(Context context, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.context = context;
        }

        public void setTabCount(int i) {
            this.tabCount = i;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.tabCount;
        }

        @Override // androidx.fragment.app.FragmentStatePagerAdapter
        public Fragment getItem(int i) {
            if (i == 0) {
                return new AppFragmentActivity();
            }
            if (i == 1) {
                return new RitualFragmentActivity();
            }
            if (i == 2) {
                return new BlockFragmentActivity();
            }
            if (i == 3) {
                return new SetelanFragmentActivity();
            }
            if (i == 4) {
                return new DonateFragmentActivity();
            }
            return null;
        }
    }

    public void _fragmentApp() {
        this.s_pos = "0";
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.admsoloraya_res_0x7f010023, R.anim.admsoloraya_res_0x7f010024).replace(R.id.admsoloraya_res_0x7f0a01ba, new AppFragmentActivity()).commit();
    }

    public void _fragmentRitual() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.admsoloraya_res_0x7f010023, R.anim.admsoloraya_res_0x7f010024).replace(R.id.admsoloraya_res_0x7f0a01ba, new RitualFragmentActivity()).commit();
    }

    public void _fragmentSetelan() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.admsoloraya_res_0x7f010023, R.anim.admsoloraya_res_0x7f010024).replace(R.id.admsoloraya_res_0x7f0a01ba, new SetelanFragmentActivity()).commit();
    }

    public void _fragmentEternal() {
        this.s_pos = "ETERNAL";
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.admsoloraya_res_0x7f010023, R.anim.admsoloraya_res_0x7f010024).replace(R.id.admsoloraya_res_0x7f0a01ba, new EternalFragmentActivity()).commit();
    }

    public void _fragmentBlocker() {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.admsoloraya_res_0x7f010023, R.anim.admsoloraya_res_0x7f010024).replace(R.id.admsoloraya_res_0x7f0a01ba, new BlockFragmentActivity()).commit();
    }

    public void _fragmentDonate() {
        String string = this.prefuser.getString("emanresu", "");
        String string2 = this.prefuser.getString("liamresu", "");
        DonateFragmentActivity donateFragmentActivity = new DonateFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("donate", "true");
        bundle.putString("donate_name", string);
        bundle.putString("donate_mail", string2);
        donateFragmentActivity.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.admsoloraya_res_0x7f010023, R.anim.admsoloraya_res_0x7f010024).replace(R.id.admsoloraya_res_0x7f0a01ba, donateFragmentActivity).commit();
    }

    public void _setFirstUI() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(0);
        }
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        getWindow().getDecorView().setSystemUiVisibility(8208);
        this.ln_loadingmain.setBackgroundColor(SurfaceColors.SURFACE_2.getColor(this));
        this.ln_loadingmain.setVisibility(8);
        this.bottom_nav.getMenu().add(0, 0, 0, "App").setIcon(R.drawable.admsoloraya_res_0x7f0800dc);
        this.bottom_nav.getMenu().add(0, 1, 0, "Ritual").setIcon(R.drawable.admsoloraya_res_0x7f08013d);
        this.bottom_nav.getMenu().add(0, 2, 0, "Activity").setIcon(R.drawable.admsoloraya_res_0x7f0800e4);
        this.bottom_nav.getMenu().add(0, 3, 0, "Setelan").setIcon(R.drawable.admsoloraya_res_0x7f080145);
        this.bottom_nav.getMenu().add(0, 4, 0, "Coffee").setIcon(R.drawable.admsoloraya_res_0x7f0800ef);
        String format = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        if (format.equals("25") || format.equals("26") || format.equals("27") || format.equals("28") || format.equals("29") || format.equals("30") || format.equals("31")) {
            BadgeDrawable orCreateBadge = this.bottom_nav.getOrCreateBadge(4);
            orCreateBadge.setVisible(true);
            orCreateBadge.setNumber(1);
        }
        if ("a d m s o l o r a y a" == 0) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        if (!this.prefui.getString("agreement", "").equals("1")) {
            finish();
        }
        this.s_pos = "0";
    }

    public void _showLoadingMain(String str) {
        this.ln_loadingmain.setVisibility(0);
        this.frame.setVisibility(4);
        this.bottom_nav.setVisibility(8);
        this.btn_oke.setVisibility(8);
        this.tv_title_loadingmain.setText("Loading");
        this.tv_subtitle_loadingmain.setText(str);
    }

    public void _hideLoadingMain() {
        this.ln_loadingmain.setVisibility(8);
        this.frame.setVisibility(0);
        this.bottom_nav.setVisibility(0);
    }

    public void _startFiltering() {
        this.tv_title_loadingmain.setText("Pelacakan slot backup kosong");
        this.tv_subtitle_loadingmain.setText("...");
        this.s_preferenceloc = this.prefui.getString("backup_sdcard_location", "").concat("/".concat(this.prefui.getString("backup_app_package", "").concat("/")));
        MyFILTER myFILTER = this.myFILTER;
        if (myFILTER != null && myFILTER.isRunning) {
            this.myFILTER.cancelFILTERTask();
        }
        MyFILTER myFILTER2 = new MyFILTER();
        this.myFILTER = myFILTER2;
        myFILTER2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyFILTER extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyFILTER() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            KatrinaActivity.this.ln_loadingmain.setVisibility(0);
            KatrinaActivity.this.frame.setVisibility(4);
            KatrinaActivity.this.bottom_nav.setVisibility(8);
            KatrinaActivity.this.btn_oke.setVisibility(8);
            File[] listFiles = new File(KatrinaActivity.this.s_preferenceloc).listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        try {
                            int parseInt = Integer.parseInt(file.getName());
                            KatrinaActivity katrinaActivity = KatrinaActivity.this;
                            katrinaActivity.nilaiTertinggi = Math.max(katrinaActivity.nilaiTertinggi, parseInt);
                        } catch (NumberFormatException unused) {
                        }
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            for (int i = 1; i <= KatrinaActivity.this.nilaiTertinggi; i++) {
                String format = String.format("%03d", Integer.valueOf(i));
                String str = String.valueOf(KatrinaActivity.this.s_preferenceloc) + format;
                File file = new File(str);
                if (file.exists() && file.isDirectory()) {
                    System.out.println("Folder ditemukan: " + format);
                    KatrinaActivity.this.ls_check.clear();
                    FileUtil.listDir(str, KatrinaActivity.this.ls_check);
                    Iterator it = KatrinaActivity.this.ls_check.iterator();
                    boolean z = false;
                    boolean z2 = false;
                    while (it.hasNext()) {
                        String str2 = (String) it.next();
                        if (str2.endsWith(".tar.gz")) {
                            z = true;
                        }
                        if (str2.endsWith(".json")) {
                            z2 = true;
                        }
                    }
                    if (!z) {
                        File file2 = new File(str);
                        if (file2.exists() && file2.isDirectory()) {
                            if (file2.renameTo(new File(str.concat("NOTAR")))) {
                                System.out.println("Folder renamed successfully.");
                            } else {
                                System.out.println("Failed to rename the folder.");
                            }
                        }
                    }
                    if (!z2) {
                        File file3 = new File(str);
                        if (file3.exists() && file3.isDirectory()) {
                            if (file3.renameTo(new File(str.concat("NOJSON")))) {
                                System.out.println("Folder renamed successfully.");
                            } else {
                                System.out.println("Failed to rename the folder.");
                            }
                        }
                    }
                } else {
                    System.out.println("Folder tidak ditemukan: " + format);
                }
                KatrinaActivity.this.s_result = format;
                publishProgress(new Void[0]);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Void... voidArr) {
            super.onProgressUpdate((Object[]) voidArr);
            KatrinaActivity.this.tv_subtitle_loadingmain.setText(KatrinaActivity.this.s_result);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            this.isRunning = false;
            KatrinaActivity.this.tv_title_loadingmain.setText("Mengurutkan ulang slot backup");
            KatrinaActivity.this.tv_subtitle_loadingmain.setText("...");
            KatrinaActivity.this._startRenaming();
        }

        public void cancelFILTERTask() {
            cancel(true);
        }
    }

    public void _startRenaming() {
        MyRENAME myRENAME = this.myRENAME;
        if (myRENAME != null && myRENAME.isRunning) {
            this.myRENAME.cancelRENAMETask();
        }
        MyRENAME myRENAME2 = new MyRENAME();
        this.myRENAME = myRENAME2;
        myRENAME2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyRENAME extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyRENAME() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            KatrinaActivity katrinaActivity = KatrinaActivity.this;
            katrinaActivity.highestFolderNumber = KatrinaActivity.findHighestFolderNumber(katrinaActivity.s_preferenceloc);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            if (KatrinaActivity.this.highestFolderNumber != -1) {
                int i = 1;
                for (int i2 = 1; i2 <= KatrinaActivity.this.highestFolderNumber; i2++) {
                    String format = String.format("%03d", Integer.valueOf(i2));
                    KatrinaActivity.this.s_result = format;
                    File file = new File(String.valueOf(KatrinaActivity.this.s_preferenceloc) + format);
                    if (file.exists()) {
                        String format2 = String.format("%03d", Integer.valueOf(i));
                        if (!new File(String.valueOf(KatrinaActivity.this.s_preferenceloc) + format2).exists()) {
                            if (file.renameTo(new File(String.valueOf(KatrinaActivity.this.s_preferenceloc) + format2))) {
                                PrintStream printStream = System.out;
                                printStream.println("Folder " + format + " renamed to " + format2);
                            } else {
                                PrintStream printStream2 = System.out;
                                printStream2.println("Failed to rename folder " + format);
                            }
                        }
                        i++;
                        publishProgress(new Void[0]);
                    }
                }
                return null;
            }
            System.out.println("No folders found in the directory.");
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Void... voidArr) {
            super.onProgressUpdate((Object[]) voidArr);
            KatrinaActivity.this.tv_subtitle_loadingmain.setText(KatrinaActivity.this.s_result);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r3) {
            this.isRunning = false;
            KatrinaActivity.this.tv_title_loadingmain.setText("Selesai");
            KatrinaActivity.this.btn_oke.setVisibility(0);
        }

        public void cancelRENAMETask() {
            cancel(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int findHighestFolderNumber(String str) {
        File[] listFiles = new File(str).listFiles();
        int i = -1;
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    try {
                        i = Math.max(i, Integer.parseInt(file.getName()));
                    } catch (NumberFormatException unused) {
                    }
                }
            }
        }
        return i;
    }

    public void _onDownloadSplash() {
        this.s_url_splash = this.pref.getString("splash", "");
        new DownloadImageTask(this, null).execute(this.s_url_splash);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class DownloadImageTask extends AsyncTask<String, Void, Void> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
        }

        private DownloadImageTask() {
        }

        /* synthetic */ DownloadImageTask(KatrinaActivity katrinaActivity, DownloadImageTask downloadImageTask) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(String... strArr) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0]).openConnection();
                httpURLConnection.setRequestMethod("GET");
                InputStream inputStream = httpURLConnection.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream("/data/user/0/" + KatrinaActivity.this.getPackageName() + "/splash.jpg");
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        inputStream.close();
                        fileOutputStream.close();
                        return null;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void _EXTRASH() {
        this.s_files_path = "/data/data/".concat(getApplicationContext().getPackageName().concat("/files"));
        this.s_fufufush_path = FileUtil.getExternalStorageDir().concat("/fufufush/");
        String concat = this.s_files_path.concat("/home");
        this.s_home_path = concat;
        this.s_sc_path = concat.concat("/.shortcuts/");
        this.s_sh_path = this.s_home_path.concat("/.sh/");
        if (!FileUtil.isExistFile(this.s_files_path)) {
            FileUtil.makeDir(this.s_files_path);
        }
        if (!FileUtil.isExistFile(this.s_home_path)) {
            FileUtil.makeDir(this.s_home_path);
        }
        if (!FileUtil.isExistFile(this.s_sc_path)) {
            FileUtil.makeDir(this.s_sc_path);
        }
        if (!FileUtil.isExistFile(this.s_sh_path)) {
            FileUtil.makeDir(this.s_sh_path);
        }
        if (!FileUtil.isExistFile(this.s_fufufush_path)) {
            FileUtil.makeDir(this.s_fufufush_path);
        }
        copyFilesWithExtension(this.s_fufufush_path, this.s_sh_path, ".sh");
        getAllSHFilesAndWriteToNewDirectory(this.s_sh_path, this.s_sc_path);
        setPermissions(this.s_sc_path);
        setPermissions(this.s_sh_path);
    }

    public static void copyFilesWithExtension(String str, String str2, final String str3) {
        try {
            final Path path = Paths.get(str, new String[0]);
            final Path path2 = Paths.get(str2, new String[0]);
            Files.createDirectories(path2, new FileAttribute[0]);
            Files.walk(path, new FileVisitOption[0]).filter(new Predicate() { // from class: com.fufufu.katrina.backup.KatrinaActivity$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean isRegularFile;
                    isRegularFile = Files.isRegularFile((Path) obj, new LinkOption[0]);
                    return isRegularFile;
                }
            }).filter(new Predicate() { // from class: com.fufufu.katrina.backup.KatrinaActivity$$ExternalSyntheticLambda1
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean endsWith;
                    endsWith = ((Path) obj).toString().endsWith(str3);
                    return endsWith;
                }
            }).forEach(new Consumer() { // from class: com.fufufu.katrina.backup.KatrinaActivity$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    KatrinaActivity.lambda$2(path2, path, (Path) obj);
                }
            });
            System.out.println("Files copied successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$2(Path path, Path path2, Path path3) {
        try {
            Files.copy(path3, path.resolve(path2.relativize(path3)), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getAllSHFilesAndWriteToNewDirectory(String str, String str2) {
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile() && file.getName().endsWith(".sh")) {
                    writeToNewDirectory(file.getName(), file.getAbsolutePath(), str2);
                }
            }
            return;
        }
        System.out.println("Directory is empty or does not exist.");
    }

    public static void writeToNewDirectory(String str, String str2, String str3) {
        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(str3) + str);
            fileWriter.write("su -c " + str2);
            fileWriter.close();
            PrintStream printStream = System.out;
            printStream.println("File " + str + " has been created with command in " + str3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setPermissions(String str) {
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isFile()) {
                    try {
                        HashSet hashSet = new HashSet();
                        hashSet.add(PosixFilePermission.OWNER_READ);
                        hashSet.add(PosixFilePermission.OWNER_WRITE);
                        hashSet.add(PosixFilePermission.OWNER_EXECUTE);
                        hashSet.add(PosixFilePermission.GROUP_READ);
                        hashSet.add(PosixFilePermission.GROUP_EXECUTE);
                        hashSet.add(PosixFilePermission.OTHERS_READ);
                        hashSet.add(PosixFilePermission.OTHERS_EXECUTE);
                        Files.setPosixFilePermissions(file.toPath(), hashSet);
                        System.out.println("Permissions set for: " + file.getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return;
        }
        System.out.println("Directory is empty or does not exist.");
    }

    public void _dinamycShortcut() {
        ShortcutManager shortcutManager = Build.VERSION.SDK_INT >= 25 ? (ShortcutManager) getSystemService(ShortcutManager.class) : null;
        if (Build.VERSION.SDK_INT < 26) {
            Toast.makeText(getApplicationContext(), "Pinned shortcuts are not supported!", 0).show();
        } else if (shortcutManager != null) {
            Intent intent = new Intent(this, ShortcutExecutorActivity.class);
            intent.setAction("android.intent.action.MAIN");
            intent.putExtra("shortcut_command", "reboot");
            intent.putExtra("shortcut_desc", "dynamic");
            ShortcutInfo build = new ShortcutInfo.Builder(this, "1").setShortLabel("Reboot").setLongLabel("Reboot").setRank(0).setIntent(intent).setIcon(Icon.createWithResource(this, (int) R.drawable.admsoloraya_res_0x7f08009f)).build();
            Intent intent2 = new Intent(this, ShortcutExecutorActivity.class);
            intent2.setAction("android.intent.action.MAIN");
            intent2.putExtra("shortcut_command", "ssaid");
            intent2.putExtra("shortcut_desc", "dynamic");
            ShortcutInfo build2 = new ShortcutInfo.Builder(this, ExifInterface.GPS_MEASUREMENT_2D).setShortLabel("Reset SSAID").setLongLabel("Reset SSAID").setRank(1).setIntent(intent2).setIcon(Icon.createWithResource(this, (int) R.drawable.admsoloraya_res_0x7f0800a1)).build();
            Intent intent3 = new Intent(this, ShortcutExecutorActivity.class);
            intent3.setAction("android.intent.action.MAIN");
            intent3.putExtra("shortcut_command", "recovery");
            intent3.putExtra("shortcut_desc", "dynamic");
            ShortcutInfo build3 = new ShortcutInfo.Builder(this, ExifInterface.GPS_MEASUREMENT_3D).setShortLabel("Recovery").setLongLabel("Recovery").setRank(2).setIntent(intent3).setIcon(Icon.createWithResource(this, (int) R.drawable.admsoloraya_res_0x7f0800a0)).build();
            Intent intent4 = new Intent(this, TermuxActivity.class);
            intent4.setAction("android.intent.action.MAIN");
            shortcutManager.setDynamicShortcuts(Arrays.asList(build, build2, build3, new ShortcutInfo.Builder(this, "4").setShortLabel("XTermod").setLongLabel("XTermod").setRank(3).setIntent(intent4).setIcon(Icon.createWithResource(this, (int) R.drawable.admsoloraya_res_0x7f08015e)).build()));
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
