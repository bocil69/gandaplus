package com.fufufu.katrina.backup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes5.dex */
public class ScopenFragmentActivity extends Fragment {
    private FloatingActionButton _fab;
    private LinearLayout color_0;
    private LinearLayout color_1;
    private LinearLayout color_10;
    private LinearLayout color_11;
    private LinearLayout color_12;
    private LinearLayout color_13;
    private LinearLayout color_14;
    private LinearLayout color_2;
    private LinearLayout color_3;
    private LinearLayout color_4;
    private LinearLayout color_5;
    private LinearLayout color_6;
    private LinearLayout color_7;
    private LinearLayout color_8;
    private LinearLayout color_9;
    private FrameLayout fr_icon;
    private GridView grid_open_app;
    private HorizontalScrollView hscr_1;
    private ImageView im_icon;
    private ImageView im_more;
    private ImageView im_shape;
    private LinearLayout ln_color;
    private LinearLayout ln_icon;
    private SharedPreferences prefall;
    private TextView tv_app_name;
    private TextView tv_app_package;
    private String s_text = "";
    private String s_name = "";
    private String s_desc = "";
    private String s_icon = "";
    private boolean b_grid = false;
    private HashMap<String, Object> m_shape = new HashMap<>();
    private String s_tint = "";
    private String s_package = "";
    private double n_pos = 0.0d;
    private ArrayList<HashMap<String, Object>> lm_allapp = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_shape = new ArrayList<>();

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d00b5, viewGroup, false);
        initialize(bundle, inflate);
        FirebaseApp.initializeApp(getContext());
        initializeLogic();
        return inflate;
    }

    private void initialize(Bundle bundle, View view) {
        this._fab = (FloatingActionButton) view.findViewById(R.id.admsoloraya_res_0x7f0a000f);
        this.ln_icon = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a029b);
        this.tv_app_name = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04e7);
        this.grid_open_app = (GridView) view.findViewById(R.id.admsoloraya_res_0x7f0a01c3);
        this.tv_app_package = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04e8);
        this.hscr_1 = (HorizontalScrollView) view.findViewById(R.id.admsoloraya_res_0x7f0a01d5);
        this.fr_icon = (FrameLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a01b4);
        this.im_more = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a021c);
        this.im_shape = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0222);
        this.im_icon = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0218);
        this.ln_color = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0287);
        this.color_0 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0124);
        this.color_1 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0125);
        this.color_2 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a012b);
        this.color_3 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a012c);
        this.color_4 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a012d);
        this.color_5 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a012e);
        this.color_6 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a012f);
        this.color_7 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0130);
        this.color_8 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0131);
        this.color_9 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0132);
        this.color_10 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0126);
        this.color_11 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0127);
        this.color_12 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0128);
        this.color_13 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0129);
        this.color_14 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a012a);
        this.prefall = getContext().getSharedPreferences("all_app_preferences", 0);
        this.im_more.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (ScopenFragmentActivity.this.b_grid) {
                    ScopenFragmentActivity.this.b_grid = false;
                } else {
                    ScopenFragmentActivity.this.b_grid = true;
                }
                ScopenFragmentActivity.this._setGridData();
            }
        });
        this.color_1.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FFAECAFD";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this.color_0.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FFFFFFFF";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this.color_2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FF8BCDEF";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this.color_3.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FF8ECCD7";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this.color_4.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FFC6F1CB";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this.color_5.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FFFFECB7";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this.color_6.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FFF1BC99";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this.color_7.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FFFFB2B2";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this.color_8.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FFFEC5E9";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this.color_9.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FFB7BDFB";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this.color_10.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FFD1B7FB";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this.color_11.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FFE4D7A9";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this.color_12.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FFD7D7D7";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this.color_13.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FFD3B684";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this.color_14.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ScopenFragmentActivity.this.s_tint = "#FFFAF0E0";
                ScopenFragmentActivity.this._refreshGridView();
            }
        });
        this._fab.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (ScopenFragmentActivity.this.tv_app_name.getText().toString().equals("App Name")) {
                    SketchwareUtil.showMessage(ScopenFragmentActivity.this.getContext().getApplicationContext(), "Harap pilih aplikasi dahulu");
                    return;
                }
                ScopenFragmentActivity scopenFragmentActivity = ScopenFragmentActivity.this;
                scopenFragmentActivity._createShortcut(scopenFragmentActivity.s_text, ScopenFragmentActivity.this.s_name, ScopenFragmentActivity.this.s_desc, ScopenFragmentActivity.this.s_icon);
            }
        });
    }

    private void initializeLogic() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.18
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
            }
        });
        this.b_grid = false;
        _setFirstUI();
    }

    /* JADX WARN: Type inference failed for: r1v11, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$21] */
    /* JADX WARN: Type inference failed for: r1v13, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$22] */
    /* JADX WARN: Type inference failed for: r1v15, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$23] */
    /* JADX WARN: Type inference failed for: r1v17, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$24] */
    /* JADX WARN: Type inference failed for: r1v19, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$25] */
    /* JADX WARN: Type inference failed for: r1v21, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$26] */
    /* JADX WARN: Type inference failed for: r1v23, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$27] */
    /* JADX WARN: Type inference failed for: r1v25, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$28] */
    /* JADX WARN: Type inference failed for: r1v27, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$29] */
    /* JADX WARN: Type inference failed for: r1v29, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$30] */
    /* JADX WARN: Type inference failed for: r1v31, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$31] */
    /* JADX WARN: Type inference failed for: r1v33, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$32] */
    /* JADX WARN: Type inference failed for: r1v35, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$33] */
    /* JADX WARN: Type inference failed for: r1v9, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$20] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.fufufu.katrina.backup.ScopenFragmentActivity$19] */
    public void _setFirstUI() {
        this.s_tint = "#FFFFFFFF";
        this.s_package = getContext().getApplicationContext().getPackageName();
        this.hscr_1.setVisibility(8);
        this.im_icon.setScaleX(0.7f);
        this.im_icon.setScaleY(0.7f);
        this.im_shape.setImageResource(R.drawable.admsoloraya_res_0x7f0801cd);
        _setMoreImage(this.s_package);
        this.color_0.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.19
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFFFFFF")));
        this.color_1.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.20
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFAECAFD")));
        this.color_2.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.21
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FF8BCDEF")));
        this.color_3.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.22
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FF8ECCD7")));
        this.color_4.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.23
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFC6F1CB")));
        this.color_5.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.24
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFFECB7")));
        this.color_6.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.25
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFF1BC99")));
        this.color_7.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.26
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFFB2B2")));
        this.color_8.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.27
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFEC5E9")));
        this.color_9.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.28
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFB7BDFB")));
        this.color_10.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.29
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFD1B7FB")));
        this.color_11.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.30
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFE4D7A9")));
        this.color_12.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.31
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFD7D7D7")));
        this.color_13.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.32
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFD3B684")));
        this.color_14.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.33
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFAF0E0")));
        this.lm_allapp.clear();
        this.lm_shape.clear();
        this.lm_allapp = (ArrayList) new Gson().fromJson(this.prefall.getString("all_app_list", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.34
        }.getType());
        this.grid_open_app.setAdapter((ListAdapter) new Grid_open_appAdapter(this.lm_allapp));
        HashMap<String, Object> hashMap = new HashMap<>();
        this.m_shape = hashMap;
        hashMap.put("shape", "0");
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> hashMap2 = new HashMap<>();
        this.m_shape = hashMap2;
        hashMap2.put("shape", "1");
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> hashMap3 = new HashMap<>();
        this.m_shape = hashMap3;
        hashMap3.put("shape", ExifInterface.GPS_MEASUREMENT_2D);
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> hashMap4 = new HashMap<>();
        this.m_shape = hashMap4;
        hashMap4.put("shape", ExifInterface.GPS_MEASUREMENT_3D);
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> hashMap5 = new HashMap<>();
        this.m_shape = hashMap5;
        hashMap5.put("shape", "4");
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> hashMap6 = new HashMap<>();
        this.m_shape = hashMap6;
        hashMap6.put("shape", "5");
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> hashMap7 = new HashMap<>();
        this.m_shape = hashMap7;
        hashMap7.put("shape", "6");
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> hashMap8 = new HashMap<>();
        this.m_shape = hashMap8;
        hashMap8.put("shape", "7");
        this.lm_shape.add(this.m_shape);
        HashMap<String, Object> hashMap9 = new HashMap<>();
        this.m_shape = hashMap9;
        hashMap9.put("shape", "8");
        this.lm_shape.add(this.m_shape);
    }

    public void _setTextOpenApp(String str, String str2) {
        this.s_package = str2;
        _setMoreImage(str2);
        this.s_text = str2;
        this.s_name = str;
        this.s_desc = "open_app";
        this.s_icon = "openapp";
        this.tv_app_name.setText(str);
        this.tv_app_package.setText(str2);
    }

    public void _createShortcut(String str, String str2, String str3, String str4) {
        Intent intent = new Intent(requireContext(), ShortcutExecutorActivity.class);
        intent.setAction("android.intent.action.MAIN");
        intent.putExtra("shortcut_command", str);
        intent.putExtra("shortcut_desc", str3);
        Intent intent2 = new Intent();
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        intent2.putExtra("android.intent.extra.shortcut.NAME", str2);
        try {
            this.fr_icon.setDrawingCacheEnabled(true);
            this.fr_icon.buildDrawingCache(true);
            Bitmap createBitmap = Bitmap.createBitmap(this.fr_icon.getDrawingCache());
            this.fr_icon.setDrawingCacheEnabled(false);
            intent2.putExtra("android.intent.extra.shortcut.ICON", createBitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
            intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(getContext().getApplicationContext(), R.drawable.admsoloraya_res_0x7f080077));
        }
        intent2.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        requireContext().sendBroadcast(intent2);
        getActivity().setResult(-1, intent2);
        getActivity().finish();
    }

    public void _setMoreImage(String str) {
        try {
            this.im_icon.setImageDrawable(getActivity().getPackageManager().getApplicationIcon(str));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            this.im_icon.setImageResource(R.drawable.admsoloraya_res_0x7f080077);
        }
        this.im_shape.setColorFilter(Color.parseColor(this.s_tint), PorterDuff.Mode.MULTIPLY);
    }

    public void _setGridData() {
        if (this.b_grid) {
            this.grid_open_app.setAdapter((ListAdapter) new Grid_open_appAdapter(this.lm_shape));
            this.hscr_1.setVisibility(0);
            return;
        }
        this.grid_open_app.setAdapter((ListAdapter) new Grid_open_appAdapter(this.lm_allapp));
        this.hscr_1.setVisibility(8);
    }

    public void _refreshGridView() {
        this.im_shape.setColorFilter(Color.parseColor(this.s_tint), PorterDuff.Mode.MULTIPLY);
        Grid_open_appAdapter grid_open_appAdapter = new Grid_open_appAdapter(this.lm_shape);
        this.grid_open_app.setAdapter((ListAdapter) grid_open_appAdapter);
        grid_open_appAdapter.notifyDataSetChanged();
    }

    public void _setNewShape() {
        double d = this.n_pos;
        if (d == 0.0d) {
            this.im_shape.setImageResource(R.drawable.admsoloraya_res_0x7f0801cc);
        } else if (d == 1.0d) {
            this.im_shape.setImageResource(R.drawable.admsoloraya_res_0x7f0801cd);
        } else if (d == 2.0d) {
            this.im_shape.setImageResource(R.drawable.admsoloraya_res_0x7f0801ce);
        } else if (d == 3.0d) {
            this.im_shape.setImageResource(R.drawable.admsoloraya_res_0x7f0801cf);
        } else if (d == 4.0d) {
            this.im_shape.setImageResource(R.drawable.admsoloraya_res_0x7f0801d0);
        } else if (d == 5.0d) {
            this.im_shape.setImageResource(R.drawable.admsoloraya_res_0x7f0801d1);
        } else if (d == 6.0d) {
            this.im_shape.setImageResource(R.drawable.admsoloraya_res_0x7f0801d2);
        } else if (d == 7.0d) {
            this.im_shape.setImageResource(R.drawable.admsoloraya_res_0x7f0801d3);
        } else if (d == 8.0d) {
            this.im_shape.setImageResource(R.drawable.admsoloraya_res_0x7f0801d4);
        } else {
            this.im_shape.setImageResource(R.drawable.admsoloraya_res_0x7f0801cd);
        }
        this.im_shape.setColorFilter(Color.parseColor(this.s_tint), PorterDuff.Mode.MULTIPLY);
    }

    /* loaded from: classes5.dex */
    public class Grid_open_appAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Grid_open_appAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = ScopenFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d00bf, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a029b);
            FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a01b4);
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a052b);
            ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0222);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0218);
            if (ScopenFragmentActivity.this.b_grid) {
                textView.setVisibility(8);
                imageView.setVisibility(0);
                imageView2.setScaleX(0.7f);
                imageView2.setScaleY(0.7f);
                try {
                    imageView2.setImageDrawable(ScopenFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(ScopenFragmentActivity.this.s_package));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    imageView2.setImageResource(R.drawable.admsoloraya_res_0x7f080077);
                }
                if (i == 0) {
                    imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0801cc);
                } else if (i == 1) {
                    imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0801cd);
                } else if (i == 2) {
                    imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0801ce);
                } else if (i == 3) {
                    imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0801cf);
                } else if (i == 4) {
                    imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0801d0);
                } else if (i == 5) {
                    imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0801d1);
                } else if (i == 6) {
                    imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0801d2);
                } else if (i == 7) {
                    imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0801d3);
                } else if (i == 8) {
                    imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0801d4);
                } else {
                    imageView.setImageResource(R.drawable.admsoloraya_res_0x7f0801cd);
                }
                imageView.setColorFilter(Color.parseColor(ScopenFragmentActivity.this.s_tint), PorterDuff.Mode.MULTIPLY);
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.Grid_open_appAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        ScopenFragmentActivity.this.n_pos = i;
                        ScopenFragmentActivity.this._setNewShape();
                    }
                });
            } else {
                textView.setVisibility(0);
                imageView.setVisibility(8);
                imageView2.setScaleX(1.0f);
                imageView2.setScaleY(1.0f);
                try {
                    imageView2.setImageDrawable(ScopenFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(this._data.get(i).get("apppackage").toString()));
                    textView.setText(this._data.get(i).get("appname").toString());
                } catch (PackageManager.NameNotFoundException unused) {
                    textView.setText(this._data.get(i).get("appname").toString());
                }
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScopenFragmentActivity.Grid_open_appAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        ScopenFragmentActivity.this._setTextOpenApp(Grid_open_appAdapter.this._data.get(i).get("appname").toString(), Grid_open_appAdapter.this._data.get(i).get("apppackage").toString());
                    }
                });
            }
            return view;
        }
    }
}
