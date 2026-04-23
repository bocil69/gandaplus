package com.fufufu.katrina.backup;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes5.dex */
public class OpenAppDialogFragmentActivity extends DialogFragment {
    private Button btn_batal;
    private Button btn_save;
    private EditText et_input;
    private EditText et_title;
    private ImageView im_appicon;
    private LinearLayout ln_app;
    private LinearLayout ln_base;
    private LinearLayout ln_button;
    private LinearLayout ln_desc;
    private LinearLayout ln_input;
    private MaterialCardView mvc_base;
    private NestedScrollView nestscroll_1;
    private SharedPreferences preflink;
    private RecyclerView rv_1;
    private TextView tv_appname;
    private TextView tv_apppackage;
    private TextView tv_dialog_link;
    private TextView tv_dialog_title;
    private String s_extra = "";
    private HashMap<String, Object> m_add = new HashMap<>();
    private String s_url = "";
    private double n = 0.0d;
    private ArrayList<HashMap<String, Object>> lm_link = new ArrayList<>();
    private Intent iShortcut = new Intent();

    public void _EXTRA() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d009e, viewGroup, false);
        initialize(bundle, inflate);
        FirebaseApp.initializeApp(getContext());
        initializeLogic();
        return inflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.mvc_base = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a03bd);
        this.ln_base = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0270);
        this.tv_dialog_title = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0507);
        this.ln_app = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a026b);
        this.tv_dialog_link = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0506);
        this.nestscroll_1 = (NestedScrollView) view.findViewById(R.id.admsoloraya_res_0x7f0a03cc);
        this.ln_input = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02a8);
        this.ln_button = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a027b);
        this.im_appicon = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0209);
        this.ln_desc = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a028f);
        this.tv_appname = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04e9);
        this.tv_apppackage = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04ea);
        this.rv_1 = (RecyclerView) view.findViewById(R.id.admsoloraya_res_0x7f0a040c);
        this.et_title = (EditText) view.findViewById(R.id.admsoloraya_res_0x7f0a018e);
        this.et_input = (EditText) view.findViewById(R.id.admsoloraya_res_0x7f0a0187);
        this.btn_batal = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a0084);
        this.btn_save = (Button) view.findViewById(R.id.admsoloraya_res_0x7f0a00ea);
        this.preflink = getContext().getSharedPreferences("openapp_preferences", 0);
        this.ln_app.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.OpenAppDialogFragmentActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                OpenAppDialogFragmentActivity openAppDialogFragmentActivity = OpenAppDialogFragmentActivity.this;
                openAppDialogFragmentActivity._openApp(openAppDialogFragmentActivity.s_extra);
            }
        });
        this.btn_batal.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.OpenAppDialogFragmentActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                OpenAppDialogFragmentActivity.this._cancelAdd();
            }
        });
        this.btn_save.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.OpenAppDialogFragmentActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                OpenAppDialogFragmentActivity.this._addLink();
            }
        });
    }

    private void initializeLogic() {
        this.rv_1.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.s_extra = arguments.getString("package", "");
            _cancelAdd();
            try {
                this.im_appicon.setImageDrawable(getActivity().getPackageManager().getApplicationIcon(this.s_extra));
                PackageManager packageManager = getActivity().getPackageManager();
                this.tv_appname.setText(packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.s_extra, 0)).toString());
            } catch (PackageManager.NameNotFoundException unused) {
            }
            this.tv_apppackage.setText(this.s_extra);
            if (this.preflink.getString(this.s_extra, "").equals("")) {
                return;
            }
            this.lm_link = (ArrayList) new Gson().fromJson(this.preflink.getString(this.s_extra, ""), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.OpenAppDialogFragmentActivity.4
            }.getType());
            this.rv_1.setAdapter(new Rv_1Adapter(this.lm_link));
            this.rv_1.setHasFixedSize(true);
            return;
        }
        getActivity().finish();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        dialog.getWindow().setLayout(-1, -2);
        window.setBackgroundDrawable(new ColorDrawable(0));
    }

    public void _addLink() {
        if (this.nestscroll_1.getVisibility() == 0) {
            this.nestscroll_1.setVisibility(8);
            this.ln_input.setVisibility(0);
            this.btn_batal.setVisibility(0);
            this.btn_save.setText("Tambahkan");
        } else if (!this.et_title.getText().toString().equals("") && !this.et_input.getText().toString().equals("")) {
            String obj = this.et_input.getText().toString();
            if (!obj.startsWith("http://") && !obj.startsWith("https://")) {
                obj = "https://" + obj;
            }
            HashMap<String, Object> hashMap = new HashMap<>();
            this.m_add = hashMap;
            hashMap.put("link_title", this.et_title.getText().toString());
            this.m_add.put("link_content", obj);
            this.m_add.put("link_deep", this.et_input.getText().toString());
            this.lm_link.add(this.m_add);
            this.rv_1.setAdapter(new Rv_1Adapter(this.lm_link));
            this.rv_1.getAdapter().notifyDataSetChanged();
            this.preflink.edit().putString(this.s_extra, new Gson().toJson(this.lm_link)).commit();
            _cancelAdd();
        } else {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Harap lengkapi data");
        }
    }

    public void _cancelAdd() {
        this.nestscroll_1.setVisibility(0);
        this.ln_input.setVisibility(8);
        this.btn_batal.setVisibility(8);
        this.btn_save.setText("Tambah Link");
        this.et_title.setText("");
        this.et_input.setText("");
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

    public void _openAppLink(String str, String str2) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
            intent.setFlags(268435456);
            startActivity(intent);
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
            this.s_url = str;
            if (!TextUtils.isEmpty(str) && !this.s_url.startsWith("http://") && !this.s_url.startsWith("https://")) {
                this.s_url = "http://" + this.s_url;
            }
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(this.s_url));
            intent2.addFlags(268435456);
            if (requireContext().getPackageManager().queryIntentActivities(intent2, 0).size() > 0) {
                startActivity(intent2);
                dismiss();
                return;
            }
            Toast.makeText(requireContext(), "Tidak ada Aplikasi untuk membuka link", 0).show();
        }
    }

    public void _deleteListURL(double d, String str, ArrayList<HashMap<String, Object>> arrayList) {
        arrayList.remove((int) d);
        this.rv_1.setAdapter(new Rv_1Adapter(this.lm_link));
        this.rv_1.getAdapter().notifyDataSetChanged();
        this.preflink.edit().putString(this.s_extra, new Gson().toJson(this.lm_link)).commit();
        SketchwareUtil.showMessage(getContext().getApplicationContext(), "Link dihapus");
    }

    /* loaded from: classes5.dex */
    public class Rv_1Adapter extends RecyclerView.Adapter<ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_1Adapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = OpenAppDialogFragmentActivity.this.getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d002f, (ViewGroup) null);
            inflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            View view = viewHolder.itemView;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            MaterialCardView materialCardView = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0354);
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04e9);
            ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a011d);
            textView.setTypeface(Typeface.createFromAsset(OpenAppDialogFragmentActivity.this.getContext().getAssets(), "fonts/sans.ttf"), 1);
            try {
                imageView.setImageDrawable(OpenAppDialogFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(OpenAppDialogFragmentActivity.this.s_extra));
            } catch (PackageManager.NameNotFoundException unused) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f080077);
            }
            textView.setText(this._data.get(i).get("link_title").toString());
            materialCardView.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.OpenAppDialogFragmentActivity.Rv_1Adapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    OpenAppDialogFragmentActivity.this._openAppLink(Rv_1Adapter.this._data.get(i).get("link_content").toString(), Rv_1Adapter.this._data.get(i).get("link_deep").toString());
                }
            });
            materialCardView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.fufufu.katrina.backup.OpenAppDialogFragmentActivity.Rv_1Adapter.2
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view2) {
                    OpenAppDialogFragmentActivity.this._deleteListURL(i, "link_tilte", Rv_1Adapter.this._data);
                    return true;
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
