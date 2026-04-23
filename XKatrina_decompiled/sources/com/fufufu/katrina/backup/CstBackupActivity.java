package com.fufufu.katrina.backup;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseApp;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import org.apache.http.cookie.ClientCookie;
/* loaded from: classes5.dex */
public class CstBackupActivity extends AppCompatActivity {
    private MaterialButton btn_cancel;
    private MaterialButton btn_simpan;
    private ImageView im_app;
    private LinearLayout ln_1;
    private LinearLayout ln_content;
    private LinearLayout ln_left;
    private LinearLayout ln_path;
    private LinearLayout ln_right;
    private LinearLayout ln_space;
    private ListView lv_list_file;
    private TextView tv_custom_backup;
    private TextView tv_list_empty;
    private TextView tv_main_path;
    private ScrollView vscr_1;
    private String s_command = "";
    private HashMap<String, Object> m_main = new HashMap<>();
    private String s_back_path = "";
    private String s_package_name = "";
    private String s_picked_file = "";
    private String s_custom_result = "";
    private String s_ssaid_prop = "";
    private ArrayList<HashMap<String, Object>> lm_result = new ArrayList<>();
    private ArrayList<String> ls_picked_file = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d003a);
        initialize(bundle);
        FirebaseApp.initializeApp(this);
        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == -1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1000);
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
        this.ln_path = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02c3);
        this.ln_content = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0288);
        this.im_app = (ImageView) findViewById(R.id.admsoloraya_res_0x7f0a0207);
        this.tv_main_path = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a0520);
        this.ln_left = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02b1);
        this.ln_space = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02e5);
        this.ln_right = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a02d5);
        this.lv_list_file = (ListView) findViewById(R.id.admsoloraya_res_0x7f0a0316);
        this.tv_list_empty = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a051c);
        this.vscr_1 = (ScrollView) findViewById(R.id.admsoloraya_res_0x7f0a05be);
        this.ln_1 = (LinearLayout) findViewById(R.id.admsoloraya_res_0x7f0a0263);
        this.tv_custom_backup = (TextView) findViewById(R.id.admsoloraya_res_0x7f0a04ff);
        this.btn_cancel = (MaterialButton) findViewById(R.id.admsoloraya_res_0x7f0a0086);
        this.btn_simpan = (MaterialButton) findViewById(R.id.admsoloraya_res_0x7f0a00fb);
        this.ln_path.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.CstBackupActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CstBackupActivity.this._onBackPath();
            }
        });
        this.btn_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.CstBackupActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CstBackupActivity.this.finish();
            }
        });
        this.btn_simpan.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.CstBackupActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CstBackupActivity.this._onSaveConfig();
            }
        });
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
        if (getIntent().hasExtra("package")) {
            String stringExtra = getIntent().getStringExtra("package");
            this.s_package_name = stringExtra;
            this.s_ssaid_prop = "\n/data/user/0/sssss/filessaid.tar.gz\n/data/user/0/sssss/fileprop.tar.gz";
            this.s_ssaid_prop = "\n/data/user/0/sssss/filessaid.tar.gz\n/data/user/0/sssss/fileprop.tar.gz".replace("sssss", stringExtra);
            try {
                this.im_app.setImageDrawable(getPackageManager().getApplicationIcon(this.s_package_name));
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                this.im_app.setImageResource(R.drawable.admsoloraya_res_0x7f080077);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(1, -1);
            layoutParams.setMargins(0, 0, 0, 0);
            this.ln_space.setLayoutParams(layoutParams);
            this.lv_list_file.setVerticalScrollBarEnabled(false);
            this.ln_right.setVerticalScrollBarEnabled(false);
            _getRootFileList(this.s_package_name);
            return;
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        _onBackPath();
    }

    public void _getRootFileList(String str) {
        this.s_command = "stat -c '%n %F' /data/user/0/replacemefufufu/* | sort -f | awk '$2 ~ /directory/ {print \"1 \" $0} $2 !~ /directory/ {print \"2 \" $0}' | sort -f";
        String replace = "stat -c '%n %F' /data/user/0/replacemefufufu/* | sort -f | awk '$2 ~ /directory/ {print \"1 \" $0} $2 !~ /directory/ {print \"2 \" $0}' | sort -f".replace("replacemefufufu", str);
        this.s_command = replace;
        ArrayList<HashMap<String, Object>> executeShellCommand = FileReader.executeShellCommand(replace);
        if (executeShellCommand.size() == 0) {
            this.tv_list_empty.setVisibility(0);
        } else {
            if (this.ls_picked_file.size() != 0) {
                Iterator<HashMap<String, Object>> it = executeShellCommand.iterator();
                while (it.hasNext()) {
                    HashMap<String, Object> next = it.next();
                    String obj = next.get(ClientCookie.PATH_ATTR).toString();
                    Iterator<String> it2 = this.ls_picked_file.iterator();
                    while (it2.hasNext()) {
                        if (it2.next().equals(obj)) {
                            next.put("pick", "true");
                        }
                    }
                }
            }
            this.tv_list_empty.setVisibility(8);
        }
        String replace2 = "/".concat(str).replace("//", "/");
        HashMap<String, Object> hashMap = new HashMap<>();
        this.m_main = hashMap;
        hashMap.put(ClientCookie.PATH_ATTR, "...");
        this.m_main.put("type", "M");
        this.m_main.put("pick", "false");
        executeShellCommand.add(0, this.m_main);
        this.lv_list_file.setAdapter((ListAdapter) new Lv_list_fileAdapter(executeShellCommand));
        this.tv_main_path.setText(replace2);
    }

    public void _onBackPath() {
        String charSequence = this.tv_main_path.getText().toString();
        this.s_back_path = charSequence;
        if (charSequence.equals("")) {
            return;
        }
        String str = this.s_back_path;
        this.s_back_path = str.replace("/".concat(Uri.parse(str).getLastPathSegment()), "");
        if (this.tv_main_path.getText().toString().equals("/".concat(this.s_package_name))) {
            return;
        }
        _getRootFileList(this.s_back_path);
    }

    public void _onPickedFile(double d, ArrayList<HashMap<String, Object>> arrayList, String str, String str2) {
        int i = (int) d;
        arrayList.get(i).put("pick", "true");
        String obj = arrayList.get(i).get(ClientCookie.PATH_ATTR).toString();
        this.ls_picked_file.add(obj);
        Iterator<String> it = this.ls_picked_file.iterator();
        while (it.hasNext()) {
            if (it.next().matches(String.valueOf(obj) + "/.*")) {
                it.remove();
            }
        }
        if (this.ls_picked_file.size() != 0) {
            Collections.sort(this.ls_picked_file);
            this.s_picked_file = CstBackupActivity$$ExternalSyntheticBackport0.m("\n\n", this.ls_picked_file);
        } else {
            this.s_picked_file = "Empty";
        }
        this.lv_list_file.invalidateViews();
        this.tv_custom_backup.setText(this.s_picked_file);
    }

    public void _onDeletePick(double d, ArrayList<HashMap<String, Object>> arrayList, String str, String str2) {
        arrayList.get((int) d).put("pick", "false");
        if (this.ls_picked_file.size() != 0) {
            Iterator<String> it = this.ls_picked_file.iterator();
            while (it.hasNext()) {
                if (it.next().equals(str2)) {
                    it.remove();
                }
            }
        }
        if (this.ls_picked_file.size() != 0) {
            Collections.sort(this.ls_picked_file);
            this.s_picked_file = CstBackupActivity$$ExternalSyntheticBackport0.m("\n\n", this.ls_picked_file);
        } else {
            this.s_picked_file = "Empty";
        }
        this.lv_list_file.invalidateViews();
        this.tv_custom_backup.setText(this.s_picked_file);
    }

    public void _onSaveConfig() {
        String replace = this.tv_custom_backup.getText().toString().replace("\n\n", "\n");
        this.s_custom_result = replace;
        this.s_custom_result = replace.concat(this.s_ssaid_prop);
        try {
            File filesDir = getFilesDir();
            FileWriter fileWriter = new FileWriter(new File(filesDir, String.valueOf(this.s_package_name) + ".fu"));
            fileWriter.write(this.s_custom_result);
            fileWriter.close();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            SketchwareUtil.showMessage(getApplicationContext(), "Gagal menyimpan configurasi");
        }
    }

    public void _onListClick(double d, ArrayList<HashMap<String, Object>> arrayList) {
        int i = (int) d;
        if (arrayList.get(i).get(ClientCookie.PATH_ATTR).toString().replaceAll(".*\\/", "").equals("lib")) {
            SketchwareUtil.showMessage(getApplicationContext(), "Tidak bisa dibuka : Tidak perlu dibackup/dibuka");
        } else if (arrayList.get(i).get("type").toString().equals("D")) {
            if (arrayList.get(i).get("pick").toString().equals("true")) {
                SketchwareUtil.showMessage(getApplicationContext(), "Tidak bisa dibuka : Semua isi folder ini sudah ada didalam list backup");
                return;
            }
            final String replace = arrayList.get(i).get(ClientCookie.PATH_ATTR).toString().replace("/data/user/0/", "");
            new Handler().postDelayed(new Runnable() { // from class: com.fufufu.katrina.backup.CstBackupActivity.4
                @Override // java.lang.Runnable
                public void run() {
                    CstBackupActivity.this._getRootFileList(replace);
                }
            }, 200L);
        } else if (arrayList.get(i).get("type").toString().equals("M")) {
            new Handler().postDelayed(new Runnable() { // from class: com.fufufu.katrina.backup.CstBackupActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    CstBackupActivity.this._onBackPath();
                }
            }, 200L);
        } else {
            SketchwareUtil.showMessage(getApplicationContext(), "Tidak bisa dibuka : Bukan folder");
        }
    }

    /* loaded from: classes5.dex */
    public class Lv_list_fileAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_list_fileAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = CstBackupActivity.this.getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d003b, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02f4);
            ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0224);
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02e8);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a021d);
            ImageView imageView3 = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a020d);
            TextView textView = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a059c);
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a0535)).setText(this._data.get(i).get(ClientCookie.PATH_ATTR).toString().replaceAll(".*\\/", ""));
            if (this._data.get(i).get("type").toString().equals("M")) {
                imageView3.setVisibility(8);
                imageView2.setVisibility(8);
                textView.setVisibility(8);
            } else if (this._data.get(i).get(ClientCookie.PATH_ATTR).toString().replaceAll(".*\\/", "").equals("lib")) {
                imageView3.setVisibility(8);
                imageView2.setVisibility(8);
                textView.setVisibility(0);
            } else if (this._data.get(i).get("pick").toString().equals("false")) {
                imageView3.setVisibility(8);
                imageView2.setVisibility(0);
                textView.setVisibility(0);
            } else {
                imageView3.setVisibility(0);
                imageView2.setVisibility(8);
                textView.setVisibility(0);
            }
            if (this._data.get(i).get("type").toString().equals("D")) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f08016b);
                textView.setText("directory");
            } else if (this._data.get(i).get("type").toString().equals("F")) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f08016d);
                textView.setText("regular file");
            } else if (this._data.get(i).get("type").toString().equals("L")) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f08016e);
                textView.setText("symbolic link");
            } else if (this._data.get(i).get("type").toString().equals(ExifInterface.LONGITUDE_EAST)) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f08016c);
                textView.setText("regular empty file");
            } else if (this._data.get(i).get("type").toString().equals("M")) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f08016b);
                textView.setText("main");
            } else {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f08016d);
                textView.setText("?");
            }
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.CstBackupActivity.Lv_list_fileAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    CstBackupActivity.this._onListClick(i, Lv_list_fileAdapter.this._data);
                }
            });
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.CstBackupActivity.Lv_list_fileAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    CstBackupActivity.this._onPickedFile(i, Lv_list_fileAdapter.this._data, Lv_list_fileAdapter.this._data.get(i).get("pick").toString(), Lv_list_fileAdapter.this._data.get(i).get(ClientCookie.PATH_ATTR).toString());
                }
            });
            imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.CstBackupActivity.Lv_list_fileAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    CstBackupActivity.this._onDeletePick(i, Lv_list_fileAdapter.this._data, Lv_list_fileAdapter.this._data.get(i).get("pick").toString(), Lv_list_fileAdapter.this._data.get(i).get(ClientCookie.PATH_ATTR).toString());
                }
            });
            return view;
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
