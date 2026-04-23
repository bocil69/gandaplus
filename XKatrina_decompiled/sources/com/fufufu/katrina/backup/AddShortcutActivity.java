package com.fufufu.katrina.backup;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.carousel.MaskableFrameLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/* loaded from: classes5.dex */
public class AddShortcutActivity extends AppCompatActivity {
    private OnCompleteListener<AuthResult> _katrina_user_create_user_listener;
    private OnCompleteListener<Void> _katrina_user_reset_password_listener;
    private OnCompleteListener<AuthResult> _katrina_user_sign_in_listener;
    private FragmentshortcutFragmentAdapter fragmentshortcut;
    private FrameLayout frame_base;
    private FirebaseAuth katrina_user;
    private OnCompleteListener<Void> katrina_user_deleteUserListener;
    private OnCompleteListener<Void> katrina_user_emailVerificationSentListener;
    private OnCompleteListener<AuthResult> katrina_user_googleSignInListener;
    private OnCompleteListener<AuthResult> katrina_user_phoneAuthListener;
    private OnCompleteListener<Void> katrina_user_updateEmailListener;
    private OnCompleteListener<Void> katrina_user_updatePasswordListener;
    private OnCompleteListener<Void> katrina_user_updateProfileListener;
    private LinearLayout ln_base;
    private RecyclerView rv_corousel_banner;
    private TextView tv_title_main;
    private HashMap<String, Object> m_fragment = new HashMap<>();
    private String s_extra = "";
    private ArrayList<HashMap<String, Object>> lm_fragment = new ArrayList<>();

    public void _EXTRA() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d001c);
        initialize(bundle);
        FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {
        this.ln_base = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0270);
        this.rv_corousel_banner = (RecyclerView) findViewById(R.id.admsoloraya_res_0x7f0a0411);
        this.tv_title_main = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a058d);
        this.frame_base = (FrameLayout) findViewById(R.id.admsoloraya_res_0x7f0a01b9);
        this.fragmentshortcut = new FragmentshortcutFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
        this.katrina_user = FirebaseAuth.getInstance();
        this.katrina_user_updateEmailListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.AddShortcutActivity.1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_updatePasswordListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.AddShortcutActivity.2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_emailVerificationSentListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.AddShortcutActivity.3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_deleteUserListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.AddShortcutActivity.4
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_phoneAuthListener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.AddShortcutActivity.5
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_updateProfileListener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.AddShortcutActivity.6
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.katrina_user_googleSignInListener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.AddShortcutActivity.7
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this._katrina_user_create_user_listener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.AddShortcutActivity.8
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this._katrina_user_sign_in_listener = new OnCompleteListener<AuthResult>() { // from class: com.fufufu.katrina.backup.AddShortcutActivity.9
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this._katrina_user_reset_password_listener = new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.AddShortcutActivity.10
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                task.isSuccessful();
            }
        };
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

    /* loaded from: classes5.dex */
    public class FragmentshortcutFragmentAdapter extends FragmentStatePagerAdapter {
        Context context;
        int tabCount;

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int i) {
            return null;
        }

        public FragmentshortcutFragmentAdapter(Context context, FragmentManager fragmentManager) {
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
                return new SctoolFragmentActivity();
            }
            if (i == 1) {
                return new ScopenFragmentActivity();
            }
            if (i == 2) {
                return new ScwipeFragmentActivity();
            }
            if (i == 3) {
                return new ScwipemassFragmentActivity();
            }
            if (i == 4) {
                return new SclinkFragmentActivity();
            }
            return null;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        finish();
    }

    public void _setFirstUI() {
        if ("a d m s o l o r a y a" != 0) {
            if (getIntent().hasExtra("wipemass_editor")) {
                HashMap<String, Object> hashMap = new HashMap<>();
                this.m_fragment = hashMap;
                hashMap.put("fragment", "wipemass");
                this.m_fragment.put("title", "Shortcut\nMulti Wipe");
                this.lm_fragment.add(this.m_fragment);
                _onFragmentWipeMassEditor(getIntent().getStringExtra("wipemass_editor"));
                _setBannerCarousel("show");
                return;
            } else if (getIntent().hasExtra("ritual_editor")) {
                _onFragmentRitual(getIntent().getStringExtra("ritual_editor"));
                _setBannerCarousel("hide");
                return;
            } else {
                HashMap<String, Object> hashMap2 = new HashMap<>();
                this.m_fragment = hashMap2;
                hashMap2.put("fragment", "tools");
                this.m_fragment.put("title", "Shortcut\nTools");
                this.lm_fragment.add(this.m_fragment);
                HashMap<String, Object> hashMap3 = new HashMap<>();
                this.m_fragment = hashMap3;
                hashMap3.put("fragment", "openapp");
                this.m_fragment.put("title", "Shortcut\nOpen App");
                this.lm_fragment.add(this.m_fragment);
                HashMap<String, Object> hashMap4 = new HashMap<>();
                this.m_fragment = hashMap4;
                hashMap4.put("fragment", "wipeapp");
                this.m_fragment.put("title", "Shortcut\nWipe App");
                this.lm_fragment.add(this.m_fragment);
                HashMap<String, Object> hashMap5 = new HashMap<>();
                this.m_fragment = hashMap5;
                hashMap5.put("fragment", "wipemass");
                this.m_fragment.put("title", "Shortcut\nMulti Wipe");
                this.lm_fragment.add(this.m_fragment);
                HashMap<String, Object> hashMap6 = new HashMap<>();
                this.m_fragment = hashMap6;
                hashMap6.put("fragment", "openlink");
                this.m_fragment.put("title", "Shortcut\nLink");
                this.lm_fragment.add(this.m_fragment);
                _onFragmentTool();
                _setBannerCarousel("show");
                return;
            }
        }
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void _selectFragment(String str) {
        if (str.equals("tools")) {
            this.tv_title_main.setText("Shortcut Tools");
            _onFragmentTool();
        } else if (str.equals("openapp")) {
            this.tv_title_main.setText("Shortcut Open App");
            _onFragmentOpenApp();
        } else if (str.equals("wipeapp")) {
            this.tv_title_main.setText("Shortcut Wipe App");
            _onFragmentWipe();
        } else if (str.equals("wipemass")) {
            this.tv_title_main.setText("Shortcut Wipe Mass");
            _onFragmentWipeMass();
        } else if (str.equals("openlink")) {
            this.tv_title_main.setText("Shortcut Link");
            _onFragmentLink();
        } else {
            this.tv_title_main.setText("Shortcut");
            _onFragmentTool();
        }
    }

    public void _onFragmentTool() {
        getSupportFragmentManager().beginTransaction().replace(R.id.admsoloraya_res_0x7f0a01b9, new SctoolFragmentActivity()).commit();
    }

    public void _onFragmentOpenApp() {
        getSupportFragmentManager().beginTransaction().replace(R.id.admsoloraya_res_0x7f0a01b9, new ScopenFragmentActivity()).commit();
    }

    public void _onFragmentWipe() {
        getSupportFragmentManager().beginTransaction().replace(R.id.admsoloraya_res_0x7f0a01b9, new ScwipeFragmentActivity()).commit();
    }

    public void _onFragmentWipeMass() {
        getSupportFragmentManager().beginTransaction().replace(R.id.admsoloraya_res_0x7f0a01b9, new ScwipemassFragmentActivity()).commit();
    }

    public void _onFragmentLink() {
        getSupportFragmentManager().beginTransaction().replace(R.id.admsoloraya_res_0x7f0a01b9, new SclinkFragmentActivity()).commit();
    }

    public void _createShortcut(String str, String str2, String str3, String str4) {
        if (str4.equals("timepick")) {
            Intent intent = new Intent(getApplicationContext(), ShortcutExecutorActivity.class);
            intent.setAction("android.intent.action.MAIN");
            intent.putExtra("shortcut_command", str);
            intent.putExtra("shortcut_desc", str3);
            Intent intent2 = new Intent();
            intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
            intent2.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.admsoloraya_res_0x7f080153));
            intent2.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent2);
            setResult(-1, intent2);
            finish();
        } else if (str4.equals("killall")) {
            Intent intent3 = new Intent(getApplicationContext(), ShortcutExecutorActivity.class);
            intent3.setAction("android.intent.action.MAIN");
            intent3.putExtra("shortcut_command", str);
            intent3.putExtra("shortcut_desc", str3);
            Intent intent4 = new Intent();
            intent4.putExtra("android.intent.extra.shortcut.INTENT", intent3);
            intent4.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent4.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.admsoloraya_res_0x7f08014c));
            intent4.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent4);
            setResult(-1, intent4);
            finish();
        } else if (str4.equals("reseto")) {
            Intent intent5 = new Intent(getApplicationContext(), ShortcutExecutorActivity.class);
            intent5.setAction("android.intent.action.MAIN");
            intent5.putExtra("shortcut_command", str);
            intent5.putExtra("shortcut_desc", str3);
            Intent intent6 = new Intent();
            intent6.putExtra("android.intent.extra.shortcut.INTENT", intent5);
            intent6.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent6.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.admsoloraya_res_0x7f080152));
            intent6.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent6);
            setResult(-1, intent6);
            finish();
        } else if (str4.equals("modpes")) {
            Intent intent7 = new Intent(getApplicationContext(), ShortcutExecutorActivity.class);
            intent7.setAction("android.intent.action.MAIN");
            intent7.putExtra("shortcut_command", str);
            intent7.putExtra("shortcut_desc", str3);
            Intent intent8 = new Intent();
            intent8.putExtra("android.intent.extra.shortcut.INTENT", intent7);
            intent8.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent8.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.admsoloraya_res_0x7f08014e));
            intent8.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent8);
            setResult(-1, intent8);
            finish();
        } else if (str4.equals("refufu")) {
            Intent intent9 = new Intent(getApplicationContext(), ShortcutExecutorActivity.class);
            intent9.setAction("android.intent.action.MAIN");
            intent9.putExtra("shortcut_command", str);
            intent9.putExtra("shortcut_desc", str3);
            Intent intent10 = new Intent();
            intent10.putExtra("android.intent.extra.shortcut.INTENT", intent9);
            intent10.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent10.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.admsoloraya_res_0x7f080151));
            intent10.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent10);
            setResult(-1, intent10);
            finish();
        } else if (str4.equals("editprop")) {
            Intent intent11 = new Intent(getApplicationContext(), ShortcutExecutorActivity.class);
            intent11.setAction("android.intent.action.MAIN");
            intent11.putExtra("shortcut_command", str);
            intent11.putExtra("shortcut_desc", str3);
            Intent intent12 = new Intent();
            intent12.putExtra("android.intent.extra.shortcut.INTENT", intent11);
            intent12.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent12.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.admsoloraya_res_0x7f080148));
            intent12.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent12);
            setResult(-1, intent12);
            finish();
        } else if (str4.equals("fakegps")) {
            Intent intent13 = new Intent(getApplicationContext(), ShortcutExecutorActivity.class);
            intent13.setAction("android.intent.action.MAIN");
            intent13.putExtra("shortcut_command", str);
            intent13.putExtra("shortcut_desc", str3);
            Intent intent14 = new Intent();
            intent14.putExtra("android.intent.extra.shortcut.INTENT", intent13);
            intent14.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent14.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.admsoloraya_res_0x7f080149));
            intent14.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent14);
            setResult(-1, intent14);
            finish();
        } else if (str4.equals("otp")) {
            Intent intent15 = new Intent(getApplicationContext(), ShortcutExecutorActivity.class);
            intent15.setAction("android.intent.action.MAIN");
            intent15.putExtra("shortcut_command", str);
            intent15.putExtra("shortcut_desc", str3);
            Intent intent16 = new Intent();
            intent16.putExtra("android.intent.extra.shortcut.INTENT", intent15);
            intent16.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent16.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.admsoloraya_res_0x7f08014f));
            intent16.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent16);
            setResult(-1, intent16);
            finish();
        } else if (str4.equals("fastreboot")) {
            Intent intent17 = new Intent(getApplicationContext(), ShortcutExecutorActivity.class);
            intent17.setAction("android.intent.action.MAIN");
            intent17.putExtra("shortcut_command", str);
            intent17.putExtra("shortcut_desc", str3);
            Intent intent18 = new Intent();
            intent18.putExtra("android.intent.extra.shortcut.INTENT", intent17);
            intent18.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent18.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.admsoloraya_res_0x7f08014a));
            intent18.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent18);
            setResult(-1, intent18);
            finish();
        } else if (str4.equals("wipegms")) {
            Intent intent19 = new Intent(getApplicationContext(), ShortcutExecutorActivity.class);
            intent19.setAction("android.intent.action.MAIN");
            intent19.putExtra("shortcut_command", str);
            intent19.putExtra("shortcut_desc", str3);
            Intent intent20 = new Intent();
            intent20.putExtra("android.intent.extra.shortcut.INTENT", intent19);
            intent20.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent20.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.admsoloraya_res_0x7f080155));
            intent20.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent20);
            setResult(-1, intent20);
            finish();
        } else if (str4.equals("katrina")) {
            Intent intent21 = new Intent(getApplicationContext(), ShortcutExecutorActivity.class);
            intent21.setAction("android.intent.action.MAIN");
            intent21.putExtra("shortcut_command", str);
            intent21.putExtra("shortcut_desc", str3);
            Intent intent22 = new Intent();
            intent22.putExtra("android.intent.extra.shortcut.INTENT", intent21);
            intent22.putExtra("android.intent.extra.shortcut.NAME", str2);
            intent22.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.admsoloraya_res_0x7f08014b));
            intent22.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            sendBroadcast(intent22);
            setResult(-1, intent22);
            finish();
        } else {
            new Intent().putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.admsoloraya_res_0x7f080077));
        }
    }

    public void _onFragmentWipeMassEditor(String str) {
        this.s_extra = str;
        ScwipemassFragmentActivity scwipemassFragmentActivity = new ScwipemassFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("wipemass_editor", this.s_extra);
        scwipemassFragmentActivity.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.admsoloraya_res_0x7f0a01b9, scwipemassFragmentActivity).commit();
    }

    public void _onFragmentRitual(String str) {
        this.s_extra = str;
        ScritualFragmentActivity scritualFragmentActivity = new ScritualFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("wipemass_editor", this.s_extra);
        scritualFragmentActivity.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.admsoloraya_res_0x7f0a01b9, scritualFragmentActivity).commit();
    }

    public void _setBannerCarousel(String str) {
        if (str.equals("show")) {
            this.rv_corousel_banner.setVisibility(0);
            this.rv_corousel_banner.setAdapter(new Rv_corousel_bannerAdapter(this.lm_fragment));
            this.tv_title_main.setText("Shortcut Tools");
            return;
        }
        this.rv_corousel_banner.setVisibility(8);
        this.tv_title_main.setText("Shortcut Ritual");
    }

    /* loaded from: classes5.dex */
    public class Rv_corousel_bannerAdapter extends RecyclerView.Adapter<ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_corousel_bannerAdapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = AddShortcutActivity.this.getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d0038, (ViewGroup) null);
            inflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            View view = viewHolder.itemView;
            MaskableFrameLayout maskableFrameLayout = (MaskableFrameLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a010a);
            ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0109);
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0588);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins((int) AddShortcutActivity.this.getDip(4), (int) AddShortcutActivity.this.getDip(0), (int) AddShortcutActivity.this.getDip(4), (int) AddShortcutActivity.this.getDip(0));
            maskableFrameLayout.setLayoutParams(layoutParams);
            if (this._data.get(i).get("fragment").toString().equals("tools")) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0800a4);
            } else if (this._data.get(i).get("fragment").toString().equals("openapp")) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0800a3);
            } else if (this._data.get(i).get("fragment").toString().equals("wipeapp")) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0800a5);
            } else if (this._data.get(i).get("fragment").toString().equals("wipemass")) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0800a6);
            } else if (this._data.get(i).get("fragment").toString().equals("openlink")) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0800a2);
            } else {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0800a4);
            }
            textView.setText(this._data.get(i).get("title").toString());
            maskableFrameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.AddShortcutActivity.Rv_corousel_bannerAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AddShortcutActivity.this._selectFragment(Rv_corousel_bannerAdapter.this._data.get(i).get("fragment").toString());
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
