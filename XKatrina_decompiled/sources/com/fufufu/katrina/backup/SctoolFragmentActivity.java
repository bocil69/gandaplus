package com.fufufu.katrina.backup;

import android.content.Intent;
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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes5.dex */
public class SctoolFragmentActivity extends Fragment {
    private FloatingActionButton _fab;
    private ChipGroup chip_group;
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
    private GridView grid_open_tool;
    private HorizontalScrollView hscr_1;
    private ImageView im_icon;
    private ImageView im_more;
    private ImageView im_shape;
    private LinearLayout ln_base_tool;
    private LinearLayout ln_color;
    private LinearLayout ln_icon;
    private Chip mchip_fakegps;
    private Chip mchip_fastreboot;
    private Chip mchip_gms;
    private Chip mchip_katrina;
    private Chip mchip_kill;
    private Chip mchip_modpes;
    private Chip mchip_otp;
    private Chip mchip_prop_system;
    private Chip mchip_refufu;
    private Chip mchip_reseto;
    private Chip mchip_ritual;
    private Chip mchip_timepick;
    private TextView tv_shortcut_tool;
    private String s_text = "";
    private String s_name = "";
    private String s_desc = "";
    private String s_icon = "";
    private HashMap<String, Object> m_shape = new HashMap<>();
    private String s_tint = "";
    private double n_pos = 0.0d;
    private boolean b_gettask = false;
    private ArrayList<HashMap<String, Object>> lm_shape = new ArrayList<>();

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d00b7, viewGroup, false);
        initialize(bundle, inflate);
        FirebaseApp.initializeApp(getContext());
        initializeLogic();
        return inflate;
    }

    private void initialize(Bundle bundle, View view) {
        this._fab = (FloatingActionButton) view.findViewById(R.id.admsoloraya_res_0x7f0a000f);
        this.ln_base_tool = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0274);
        this.ln_icon = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a029b);
        this.tv_shortcut_tool = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0555);
        this.chip_group = (ChipGroup) view.findViewById(R.id.admsoloraya_res_0x7f0a0118);
        this.grid_open_tool = (GridView) view.findViewById(R.id.admsoloraya_res_0x7f0a01c4);
        this.hscr_1 = (HorizontalScrollView) view.findViewById(R.id.admsoloraya_res_0x7f0a01d5);
        this.fr_icon = (FrameLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a01b4);
        this.im_more = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a021c);
        this.im_shape = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0222);
        this.im_icon = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0218);
        this.mchip_timepick = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a036e);
        this.mchip_kill = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0363);
        this.mchip_fastreboot = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a035f);
        this.mchip_modpes = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0365);
        this.mchip_reseto = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a036b);
        this.mchip_otp = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0368);
        this.mchip_prop_system = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0369);
        this.mchip_fakegps = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a035e);
        this.mchip_refufu = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a036a);
        this.mchip_gms = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0360);
        this.mchip_ritual = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a036c);
        this.mchip_katrina = (Chip) view.findViewById(R.id.admsoloraya_res_0x7f0a0362);
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
        this.im_more.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SctoolFragmentActivity.this.chip_group.getVisibility() == 0) {
                    SctoolFragmentActivity.this.chip_group.setVisibility(8);
                    SctoolFragmentActivity.this.grid_open_tool.setVisibility(0);
                    SctoolFragmentActivity.this.hscr_1.setVisibility(0);
                    return;
                }
                SctoolFragmentActivity.this.chip_group.setVisibility(0);
                SctoolFragmentActivity.this.grid_open_tool.setVisibility(8);
                SctoolFragmentActivity.this.hscr_1.setVisibility(8);
            }
        });
        this.color_1.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FFAECAFD";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this.color_0.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FFFFFFFF";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this.color_2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FF8BCDEF";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this.color_3.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FF8ECCD7";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this.color_4.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FFC6F1CB";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this.color_5.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FFFFECB7";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this.color_6.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FFF1BC99";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this.color_7.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FFFFB2B2";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this.color_8.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FFFEC5E9";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this.color_9.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FFB7BDFB";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this.color_10.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FFD1B7FB";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this.color_11.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FFE4D7A9";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this.color_12.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FFD7D7D7";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this.color_13.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FFD3B684";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this.color_14.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_tint = "#FFFAF0E0";
                SctoolFragmentActivity.this._refreshGridView();
            }
        });
        this._fab.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (SctoolFragmentActivity.this.b_gettask) {
                    SctoolFragmentActivity sctoolFragmentActivity = SctoolFragmentActivity.this;
                    sctoolFragmentActivity._createShortcut(sctoolFragmentActivity.s_text, SctoolFragmentActivity.this.s_name, SctoolFragmentActivity.this.s_desc, SctoolFragmentActivity.this.s_icon);
                    return;
                }
                SketchwareUtil.showMessage(SctoolFragmentActivity.this.getContext().getApplicationContext(), "Harap pilih tools");
            }
        });
        this.mchip_timepick.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_text = "timepick";
                SctoolFragmentActivity.this.s_name = "Timepick";
                SctoolFragmentActivity.this.s_desc = "timepick";
                SctoolFragmentActivity.this.s_icon = "timepick";
                SctoolFragmentActivity.this._setImageSample();
            }
        });
        this.mchip_kill.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_text = "killall";
                SctoolFragmentActivity.this.s_name = "Kill All";
                SctoolFragmentActivity.this.s_desc = "killall";
                SctoolFragmentActivity.this.s_icon = "killall";
                SctoolFragmentActivity.this._setImageSample();
            }
        });
        this.mchip_fastreboot.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_text = "fastreboot";
                SctoolFragmentActivity.this.s_name = "Fast Reboot";
                SctoolFragmentActivity.this.s_desc = "fastreboot";
                SctoolFragmentActivity.this.s_icon = "fastreboot";
                SctoolFragmentActivity.this._setImageSample();
            }
        });
        this.mchip_modpes.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_text = "modpes";
                SctoolFragmentActivity.this.s_name = "Modpes";
                SctoolFragmentActivity.this.s_desc = "modpes";
                SctoolFragmentActivity.this.s_icon = "modpes";
                SctoolFragmentActivity.this._setImageSample();
            }
        });
        this.mchip_reseto.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_text = "reseto";
                SctoolFragmentActivity.this.s_name = "Reset 0";
                SctoolFragmentActivity.this.s_desc = "reseto";
                SctoolFragmentActivity.this.s_icon = "reseto";
                SctoolFragmentActivity.this._setImageSample();
            }
        });
        this.mchip_otp.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_text = "otp";
                SctoolFragmentActivity.this.s_name = "OTP";
                SctoolFragmentActivity.this.s_desc = "otp";
                SctoolFragmentActivity.this.s_icon = "otp";
                SctoolFragmentActivity.this._setImageSample();
            }
        });
        this.mchip_prop_system.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_text = "editprop";
                SctoolFragmentActivity.this.s_name = "Edit Prop";
                SctoolFragmentActivity.this.s_desc = "editprop";
                SctoolFragmentActivity.this.s_icon = "editprop";
                SctoolFragmentActivity.this._setImageSample();
            }
        });
        this.mchip_fakegps.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.25
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_text = "fakegps";
                SctoolFragmentActivity.this.s_name = "Fake GPS";
                SctoolFragmentActivity.this.s_desc = "fakegps";
                SctoolFragmentActivity.this.s_icon = "fakegps";
                SctoolFragmentActivity.this._setImageSample();
            }
        });
        this.mchip_refufu.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_text = "refufu";
                SctoolFragmentActivity.this.s_name = "Refufu";
                SctoolFragmentActivity.this.s_desc = "refufu";
                SctoolFragmentActivity.this.s_icon = "refufu";
                SctoolFragmentActivity.this._setImageSample();
            }
        });
        this.mchip_gms.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_text = "wipegms";
                SctoolFragmentActivity.this.s_name = "Wipe GMS";
                SctoolFragmentActivity.this.s_desc = "wipegms";
                SctoolFragmentActivity.this.s_icon = "wipegms";
                SctoolFragmentActivity.this._setImageSample();
            }
        });
        this.mchip_ritual.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_text = "ritual";
                SctoolFragmentActivity.this.s_name = "Ritual Editor";
                SctoolFragmentActivity.this.s_desc = "ritual";
                SctoolFragmentActivity.this.s_icon = "ritual";
                SctoolFragmentActivity.this._setImageSample();
            }
        });
        this.mchip_katrina.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SctoolFragmentActivity.this.s_text = "katrina";
                SctoolFragmentActivity.this.s_name = "XKatrina";
                SctoolFragmentActivity.this.s_desc = "katrina";
                SctoolFragmentActivity.this.s_icon = "katrina";
                SctoolFragmentActivity.this._setImageSample();
            }
        });
    }

    private void initializeLogic() {
        this.b_gettask = false;
        _setFirstUI();
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

    public void _setImageSample() {
        this.tv_shortcut_tool.setText(this.s_name);
        if (this.s_name.equals("Timepick")) {
            this.im_icon.setImageResource(R.drawable.admsoloraya_res_0x7f080168);
        } else if (this.s_name.equals("Kill All")) {
            this.im_icon.setImageResource(R.drawable.admsoloraya_res_0x7f080161);
        } else if (this.s_name.equals("Fast Reboot")) {
            this.im_icon.setImageResource(R.drawable.admsoloraya_res_0x7f080160);
        } else if (this.s_name.equals("Modpes")) {
            this.im_icon.setImageResource(R.drawable.admsoloraya_res_0x7f080162);
        }
        if (this.s_name.equals("Reset 0")) {
            this.im_icon.setImageResource(R.drawable.admsoloraya_res_0x7f080166);
        } else if (this.s_name.equals("OTP")) {
            this.im_icon.setImageResource(R.drawable.admsoloraya_res_0x7f080163);
        } else if (this.s_name.equals("Edit Prop")) {
            this.im_icon.setImageResource(R.drawable.admsoloraya_res_0x7f080164);
        } else if (this.s_name.equals("Fake GPS")) {
            this.im_icon.setImageResource(R.drawable.admsoloraya_res_0x7f08015f);
        } else if (this.s_name.equals("Refufu")) {
            this.im_icon.setImageResource(R.drawable.admsoloraya_res_0x7f080165);
        } else if (this.s_name.equals("Wipe GMS")) {
            this.im_icon.setImageResource(R.drawable.admsoloraya_res_0x7f080169);
        } else if (this.s_name.equals("XKatrina")) {
            this.im_icon.setImageResource(R.drawable.admsoloraya_res_0x7f08016a);
        } else if (this.s_name.equals("Ritual Editor")) {
            this.im_icon.setImageResource(R.drawable.admsoloraya_res_0x7f080167);
        }
        this.b_gettask = true;
    }

    /* JADX WARN: Type inference failed for: r2v10, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$33] */
    /* JADX WARN: Type inference failed for: r2v12, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$34] */
    /* JADX WARN: Type inference failed for: r2v14, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$35] */
    /* JADX WARN: Type inference failed for: r2v16, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$36] */
    /* JADX WARN: Type inference failed for: r2v18, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$37] */
    /* JADX WARN: Type inference failed for: r2v20, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$38] */
    /* JADX WARN: Type inference failed for: r2v22, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$39] */
    /* JADX WARN: Type inference failed for: r2v24, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$40] */
    /* JADX WARN: Type inference failed for: r2v26, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$41] */
    /* JADX WARN: Type inference failed for: r2v28, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$42] */
    /* JADX WARN: Type inference failed for: r2v30, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$43] */
    /* JADX WARN: Type inference failed for: r2v32, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$44] */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$30] */
    /* JADX WARN: Type inference failed for: r2v6, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$31] */
    /* JADX WARN: Type inference failed for: r2v8, types: [com.fufufu.katrina.backup.SctoolFragmentActivity$32] */
    public void _setFirstUI() {
        this.s_tint = "#FFFAF0E0";
        this.im_shape.setImageResource(R.drawable.admsoloraya_res_0x7f0801cd);
        this.im_icon.setImageResource(R.drawable.admsoloraya_res_0x7f080077);
        this.hscr_1.setVisibility(8);
        this.grid_open_tool.setVisibility(8);
        this.im_icon.setScaleX(0.7f);
        this.im_icon.setScaleY(0.7f);
        this.color_0.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.30
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFFFFFF")));
        this.color_1.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.31
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFAECAFD")));
        this.color_2.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.32
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FF8BCDEF")));
        this.color_3.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.33
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FF8ECCD7")));
        this.color_4.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.34
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFC6F1CB")));
        this.color_5.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.35
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFFECB7")));
        this.color_6.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.36
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFF1BC99")));
        this.color_7.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.37
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFFB2B2")));
        this.color_8.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.38
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFEC5E9")));
        this.color_9.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.39
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFB7BDFB")));
        this.color_10.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.40
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFD1B7FB")));
        this.color_11.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.41
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFE4D7A9")));
        this.color_12.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.42
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFD7D7D7")));
        this.color_13.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.43
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFD3B684")));
        this.color_14.setBackground(new GradientDrawable() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.44
            public GradientDrawable getIns(int i, int i2) {
                setCornerRadius(i);
                setColor(i2);
                return this;
            }
        }.getIns(10, Color.parseColor("#FFFAF0E0")));
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
        this.grid_open_tool.setAdapter((ListAdapter) new Grid_open_toolAdapter(this.lm_shape));
    }

    public void _refreshGridView() {
        this.im_shape.setColorFilter(Color.parseColor(this.s_tint), PorterDuff.Mode.MULTIPLY);
        Grid_open_toolAdapter grid_open_toolAdapter = new Grid_open_toolAdapter(this.lm_shape);
        this.grid_open_tool.setAdapter((ListAdapter) grid_open_toolAdapter);
        grid_open_toolAdapter.notifyDataSetChanged();
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
    public class Grid_open_toolAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Grid_open_toolAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = SctoolFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d00bf, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a029b);
            FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a01b4);
            ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0222);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0218);
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a052b)).setVisibility(8);
            imageView2.setVisibility(4);
            imageView2.setScaleX(0.7f);
            imageView2.setScaleY(0.7f);
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
            imageView.setColorFilter(Color.parseColor(SctoolFragmentActivity.this.s_tint), PorterDuff.Mode.MULTIPLY);
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.SctoolFragmentActivity.Grid_open_toolAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SctoolFragmentActivity.this.n_pos = i;
                    SctoolFragmentActivity.this._setNewShape();
                }
            });
            return view;
        }
    }
}
