package com.fufufu.katrina.backup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
/* loaded from: classes5.dex */
public class ScwipemassFragmentActivity extends Fragment {
    private FloatingActionButton _fab;
    private AutoCompleteTextView auto_shortcut;
    private GridView grid_open_app;
    private LinearLayout ln_wipemass_base;
    private ListView lv_listwipe;
    private MaterialCardView mcv_base;
    private SharedPreferences prefall;
    private SharedPreferences prefwipemass;
    private TextInputLayout til_shortcut;
    private String s_wipemass = "";
    private HashMap<String, Object> m_add = new HashMap<>();
    private double n_remove = 0.0d;
    private boolean b_exist = false;
    private String s_list = "";
    private String s_text = "";
    private String s_name = "";
    private String s_desc = "";
    private String s_icon = "";
    private HashMap<String, Object> m_prefwipemass = new HashMap<>();
    private String s_chartmp = "";
    private boolean b_validname = false;
    private String s_prefwipe = "";
    private double n_pref = 0.0d;
    private String s_app_package = "";
    private String s_app_name = "";
    private boolean b_editor = false;
    private ArrayList<HashMap<String, Object>> lm_allapp = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_listwipe = new ArrayList<>();
    private ArrayList<String> ls_wipemass = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_prefwipemass = new ArrayList<>();

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d00b9, viewGroup, false);
        initialize(bundle, inflate);
        FirebaseApp.initializeApp(getContext());
        initializeLogic();
        return inflate;
    }

    private void initialize(Bundle bundle, View view) {
        this._fab = (FloatingActionButton) view.findViewById(R.id.admsoloraya_res_0x7f0a000f);
        this.til_shortcut = (TextInputLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a04a7);
        this.ln_wipemass_base = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02fb);
        this.auto_shortcut = (AutoCompleteTextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0068);
        this.grid_open_app = (GridView) view.findViewById(R.id.admsoloraya_res_0x7f0a01c3);
        this.mcv_base = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0380);
        this.lv_listwipe = (ListView) view.findViewById(R.id.admsoloraya_res_0x7f0a0317);
        this.prefwipemass = getContext().getSharedPreferences("wipemass_preferences", 0);
        this.prefall = getContext().getSharedPreferences("all_app_preferences", 0);
        this.auto_shortcut.addTextChangedListener(new TextWatcher() { // from class: com.fufufu.katrina.backup.ScwipemassFragmentActivity.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String charSequence2 = charSequence.toString();
                if (ScwipemassFragmentActivity.this.lm_prefwipemass.size() == 0) {
                    ScwipemassFragmentActivity.this.b_validname = true;
                    return;
                }
                ScwipemassFragmentActivity.this.b_validname = false;
                ScwipemassFragmentActivity.this.s_chartmp = charSequence2.replaceAll(" ", "_").replaceAll("[^a-zA-Z0-9_]", "");
                ScwipemassFragmentActivity scwipemassFragmentActivity = ScwipemassFragmentActivity.this;
                scwipemassFragmentActivity.s_chartmp = scwipemassFragmentActivity.s_chartmp.toLowerCase();
                ScwipemassFragmentActivity.this.n_remove = 0.0d;
                for (int i4 = 0; i4 < ScwipemassFragmentActivity.this.lm_prefwipemass.size(); i4++) {
                    if (((HashMap) ScwipemassFragmentActivity.this.lm_prefwipemass.get((int) ScwipemassFragmentActivity.this.n_remove)).get("key_app").toString().equals(ScwipemassFragmentActivity.this.s_chartmp)) {
                        ScwipemassFragmentActivity.this.til_shortcut.setErrorEnabled(true);
                        ScwipemassFragmentActivity.this.til_shortcut.setError("Gunakan nama lain");
                        ScwipemassFragmentActivity.this.b_validname = false;
                        return;
                    }
                    ScwipemassFragmentActivity.this.til_shortcut.setErrorEnabled(false);
                    ScwipemassFragmentActivity.this.b_validname = true;
                    ScwipemassFragmentActivity.this.n_remove += 1.0d;
                }
            }
        });
        this._fab.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScwipemassFragmentActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (ScwipemassFragmentActivity.this.b_editor) {
                    ScwipemassFragmentActivity.this._saveNewWipePref();
                } else {
                    ScwipemassFragmentActivity.this._createListShortcut();
                }
            }
        });
    }

    private void initializeLogic() {
        _setFirstUI();
    }

    public void _setFirstUI() {
        this.lm_allapp.clear();
        this.lm_listwipe.clear();
        this.lm_allapp = (ArrayList) new Gson().fromJson(this.prefall.getString("all_app_list", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.ScwipemassFragmentActivity.3
        }.getType());
        this.grid_open_app.setAdapter((ListAdapter) new Grid_open_appAdapter(this.lm_allapp));
        this.lv_listwipe.setAdapter((ListAdapter) new Lv_listwipeAdapter(this.lm_listwipe));
        this.auto_shortcut.setInputType(1);
        this.lm_prefwipemass.clear();
        Map<String, ?> all = this.prefwipemass.getAll();
        for (String str : all.keySet()) {
            HashMap<String, Object> hashMap = new HashMap<>();
            this.m_prefwipemass = hashMap;
            hashMap.put("key_app", str);
            this.m_prefwipemass.put("value_app", all.get(str).toString());
            this.lm_prefwipemass.add(this.m_prefwipemass);
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("wipemass_editor");
            this.b_editor = true;
            String string2 = this.prefwipemass.getString(string, "");
            this.s_prefwipe = string2;
            String replace = string2.replace("am force-stop", "");
            this.s_prefwipe = replace;
            String replace2 = replace.replace("pm clear", "");
            this.s_prefwipe = replace2;
            String replace3 = replace2.replace(" ", "");
            this.s_prefwipe = replace3;
            this.s_prefwipe = replace3.replace(";", "\n");
            ArrayList arrayList = new ArrayList(Arrays.asList(this.s_prefwipe.split("\n")));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (((String) it.next()).trim().isEmpty()) {
                    it.remove();
                }
            }
            ArrayList arrayList2 = new ArrayList(new LinkedHashSet(arrayList));
            this.n_pref = 0.0d;
            for (int i = 0; i < arrayList2.size(); i++) {
                this.s_app_package = (String) arrayList2.get((int) this.n_pref);
                HashMap<String, Object> hashMap2 = new HashMap<>();
                this.m_add = hashMap2;
                hashMap2.put("apppackage", this.s_app_package);
                PackageManager packageManager = getActivity().getPackageManager();
                try {
                    this.s_app_name = packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.s_app_package, 0)).toString();
                } catch (PackageManager.NameNotFoundException unused) {
                    this.s_app_name = "null";
                }
                this.m_add.put("appname", this.s_app_name);
                this.lm_listwipe.add(this.m_add);
                this.n_pref += 1.0d;
            }
            this.auto_shortcut.setText(string);
            this.til_shortcut.setErrorEnabled(false);
            this.lv_listwipe.setAdapter((ListAdapter) new Lv_listwipeAdapter(this.lm_listwipe));
            this._fab.setImageResource(R.drawable.admsoloraya_res_0x7f0800fe);
            this.auto_shortcut.setFocusable(false);
            this.auto_shortcut.setFocusableInTouchMode(false);
            return;
        }
        this.b_editor = false;
    }

    public void _addListWipe(String str, String str2) {
        int i = 0;
        this.b_exist = false;
        while (true) {
            if (i < this.lm_listwipe.size()) {
                HashMap<String, Object> hashMap = this.lm_listwipe.get(i);
                if ((hashMap.get("apppackage") instanceof String) && ((String) hashMap.get("apppackage")).equals(str2)) {
                    this.b_exist = true;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        if (!this.b_exist) {
            HashMap<String, Object> hashMap2 = new HashMap<>();
            this.m_add = hashMap2;
            hashMap2.put("appname", str);
            this.m_add.put("apppackage", str2);
            this.lm_listwipe.add(this.m_add);
            this.lv_listwipe.invalidateViews();
            return;
        }
        SketchwareUtil.showMessage(getContext().getApplicationContext(), "Aplikasi sudah ada didalam list");
    }

    public void _removeListWipe(double d) {
        this.lm_listwipe.remove((int) d);
        this.lv_listwipe.invalidateViews();
    }

    public void _createListShortcut() {
        String str;
        if (this.b_validname) {
            if (this.auto_shortcut.getText().toString().length() < 1 || this.lm_listwipe.size() < 1) {
                SketchwareUtil.showMessage(getContext().getApplicationContext(), "Mohon lengkapi data");
                return;
            }
            StringBuilder sb = new StringBuilder();
            Iterator<HashMap<String, Object>> it = this.lm_listwipe.iterator();
            while (it.hasNext()) {
                Object obj = it.next().get("apppackage");
                if (obj instanceof String) {
                    sb.append((String) obj);
                    sb.append(", ");
                }
            }
            String sb2 = sb.toString();
            this.s_list = sb2;
            if (sb2.endsWith(", ")) {
                this.s_list = this.s_list.substring(0, str.length() - 2);
            }
            this.ls_wipemass = new ArrayList<>(Arrays.asList(this.s_list.split(", ")));
            StringBuilder sb3 = new StringBuilder();
            Iterator<String> it2 = this.ls_wipemass.iterator();
            while (it2.hasNext()) {
                String next = it2.next();
                String str2 = "am force-stop " + next + "; ";
                sb3.append(str2);
                sb3.append("pm clear " + next + "; ");
            }
            this.s_list = sb3.toString().trim();
            String obj2 = this.auto_shortcut.getText().toString();
            this.s_name = obj2;
            String replaceAll = obj2.replaceAll(" ", "_").replaceAll("[^a-zA-Z0-9_]", "");
            this.s_text = replaceAll;
            this.s_text = replaceAll.toLowerCase();
            this.s_desc = "wipemass";
            this.s_icon = "wipe_mass";
            this.prefwipemass.edit().putString(this.s_text, this.s_list).commit();
            _createShortcut(this.s_text, this.s_name, this.s_desc, this.s_icon);
            return;
        }
        SketchwareUtil.showMessage(getContext().getApplicationContext(), "Mohon ubah data");
    }

    public void _createShortcut(String str, String str2, String str3, String str4) {
        Intent intent = new Intent(requireContext(), ShortcutExecutorActivity.class);
        intent.setAction("android.intent.action.MAIN");
        intent.putExtra("shortcut_command", str);
        intent.putExtra("shortcut_desc", str3);
        Intent intent2 = new Intent();
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        intent2.putExtra("android.intent.extra.shortcut.NAME", str2);
        intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(requireContext(), R.drawable.admsoloraya_res_0x7f080156));
        intent2.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        requireContext().sendBroadcast(intent2);
        getActivity().setResult(-1, intent2);
        getActivity().finish();
    }

    public void _saveNewWipePref() {
        String str;
        if (this.auto_shortcut.getText().toString().length() < 1 || this.lm_listwipe.size() < 1) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), "Mohon lengkapi data");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<HashMap<String, Object>> it = this.lm_listwipe.iterator();
        while (it.hasNext()) {
            Object obj = it.next().get("apppackage");
            if (obj instanceof String) {
                sb.append((String) obj);
                sb.append(", ");
            }
        }
        String sb2 = sb.toString();
        this.s_list = sb2;
        if (sb2.endsWith(", ")) {
            this.s_list = this.s_list.substring(0, str.length() - 2);
        }
        this.ls_wipemass = new ArrayList<>(Arrays.asList(this.s_list.split(", ")));
        StringBuilder sb3 = new StringBuilder();
        Iterator<String> it2 = this.ls_wipemass.iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            String str2 = "am force-stop " + next + "; ";
            sb3.append(str2);
            sb3.append("pm clear " + next + "; ");
        }
        this.s_list = sb3.toString().trim();
        String obj2 = this.auto_shortcut.getText().toString();
        this.s_name = obj2;
        String replaceAll = obj2.replaceAll(" ", "_").replaceAll("[^a-zA-Z0-9_]", "");
        this.s_text = replaceAll;
        this.s_text = replaceAll.toLowerCase();
        this.s_desc = "wipemass";
        this.s_icon = "wipe_mass";
        this.prefwipemass.edit().putString(this.s_text, this.s_list).commit();
        getActivity().finish();
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
            LayoutInflater layoutInflater = ScwipemassFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d002f, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            MaterialCardView materialCardView = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0354);
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04e9);
            try {
                ((ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a011d)).setImageDrawable(ScwipemassFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(this._data.get(i).get("apppackage").toString()));
                textView.setText(this._data.get(i).get("appname").toString());
            } catch (PackageManager.NameNotFoundException unused) {
                textView.setText(this._data.get(i).get("appname").toString());
            }
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScwipemassFragmentActivity.Grid_open_appAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScwipemassFragmentActivity.this._addListWipe(Grid_open_appAdapter.this._data.get(i).get("appname").toString(), Grid_open_appAdapter.this._data.get(i).get("apppackage").toString());
                }
            });
            return view;
        }
    }

    /* loaded from: classes5.dex */
    public class Lv_listwipeAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_listwipeAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = ScwipemassFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d002f, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            MaterialCardView materialCardView = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0354);
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04e9);
            try {
                ((ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a011d)).setImageDrawable(ScwipemassFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(this._data.get(i).get("apppackage").toString()));
                textView.setText(this._data.get(i).get("appname").toString());
            } catch (PackageManager.NameNotFoundException unused) {
                textView.setText(this._data.get(i).get("appname").toString());
            }
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.ScwipemassFragmentActivity.Lv_listwipeAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ScwipemassFragmentActivity.this.n_remove = i;
                    ScwipemassFragmentActivity.this._removeListWipe(ScwipemassFragmentActivity.this.n_remove);
                }
            });
            return view;
        }
    }
}
