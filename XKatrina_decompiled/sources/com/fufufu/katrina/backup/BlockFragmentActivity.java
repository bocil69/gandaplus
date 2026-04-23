package com.fufufu.katrina.backup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.elevation.SurfaceColors;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes5.dex */
public class BlockFragmentActivity extends Fragment {
    private MaterialButton btn_allapp;
    private MaterialButton btn_reset;
    private MaterialButton btn_save;
    private FrameLayout fr_icon;
    private ImageView im_shape;
    private LinearLayout ln_bottom;
    private LinearLayout ln_button_top;
    private LinearLayout ln_listActivity_base;
    private LinearLayout ln_top;
    private LinearLayout ln_view;
    private ListView lv_listActivity;
    private ListView lv_listReceiver;
    private ListView lv_listService;
    private MyGETACT myGETACT;
    private ProgressBar pbar_listActivity;
    private SharedPreferences prefapp;
    private RecyclerView rv_all_app;
    private TabLayout tab_listActivity;
    private TextView tv_blocker_title;
    private String s_packageName = "";
    private String s_activityStatus = "";
    private String s_activityName = "";
    private double n = 0.0d;
    private boolean allapp = false;
    private String s_firewall_rules = "";
    private String s_firewall_activity = "";
    private String s_firewall_service = "";
    private String s_firewall_receiver = "";
    private String s_new_rules = "";
    private String s_type = "";
    private String s_new_activity = "";
    private String s_new_service = "";
    private String s_new_receiver = "";
    private String s_ifw_path = "";
    private String s_command = "";
    private String s_target_package = "";
    private String s_tmp = "";
    private String s_commandResult = "";
    private boolean b_command = false;
    private String s_exitCode = "";
    private String xml = "";
    private String s_command_xml = "";
    private String s_get_disable = "";
    private String s_launch_result = "";
    private String s_launch_app = "";
    private String s_appName = "";
    private ArrayList<HashMap<String, Object>> lm_activityData = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_serviceData = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_receiverData = new ArrayList<>();
    private ArrayList<String> ls_activityList = new ArrayList<>();
    private ArrayList<String> ls_serviceList = new ArrayList<>();
    private ArrayList<String> ls_receiverList = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lm_allapp = new ArrayList<>();
    private ArrayList<String> ls_allactivity = new ArrayList<>();

    public void _EXTRA() {
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d0034, viewGroup, false);
        initialize(bundle, inflate);
        FirebaseApp.initializeApp(getContext());
        initializeLogic();
        return inflate;
    }

    private void initialize(Bundle bundle, View view) {
        this.ln_listActivity_base = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02b3);
        this.ln_top = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02ed);
        this.ln_button_top = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a027e);
        this.rv_all_app = (RecyclerView) view.findViewById(R.id.admsoloraya_res_0x7f0a0410);
        this.tab_listActivity = (TabLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0478);
        this.pbar_listActivity = (ProgressBar) view.findViewById(R.id.admsoloraya_res_0x7f0a03ed);
        this.ln_bottom = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0277);
        this.tv_blocker_title = (TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04ed);
        this.btn_allapp = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a0081);
        this.fr_icon = (FrameLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a01b4);
        this.ln_view = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02f4);
        this.btn_reset = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00e8);
        this.btn_save = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00ea);
        this.im_shape = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0222);
        this.lv_listActivity = (ListView) view.findViewById(R.id.admsoloraya_res_0x7f0a0313);
        this.lv_listService = (ListView) view.findViewById(R.id.admsoloraya_res_0x7f0a0315);
        this.lv_listReceiver = (ListView) view.findViewById(R.id.admsoloraya_res_0x7f0a0314);
        this.prefapp = getContext().getSharedPreferences("all_app_preferences", 0);
        this.tab_listActivity.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.1
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    BlockFragmentActivity.this.lv_listActivity.setVisibility(0);
                    BlockFragmentActivity.this.lv_listService.setVisibility(8);
                    BlockFragmentActivity.this.lv_listReceiver.setVisibility(8);
                } else if (position == 1) {
                    BlockFragmentActivity.this.lv_listActivity.setVisibility(8);
                    BlockFragmentActivity.this.lv_listService.setVisibility(0);
                    BlockFragmentActivity.this.lv_listReceiver.setVisibility(8);
                } else if (position == 2) {
                    BlockFragmentActivity.this.lv_listActivity.setVisibility(8);
                    BlockFragmentActivity.this.lv_listService.setVisibility(8);
                    BlockFragmentActivity.this.lv_listReceiver.setVisibility(0);
                }
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getPosition();
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
                tab.getPosition();
            }
        });
        this.btn_allapp.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                BlockFragmentActivity.this._loadActivity();
            }
        });
        this.btn_reset.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                BlockFragmentActivity.this._onResetIFW();
            }
        });
        this.btn_save.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                BlockFragmentActivity.this._startBlockerAction();
            }
        });
    }

    private void initializeLogic() {
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.5
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                if (BlockFragmentActivity.this.rv_all_app.getVisibility() == 8) {
                    BlockFragmentActivity.this.btn_allapp.setVisibility(0);
                    BlockFragmentActivity.this.rv_all_app.setVisibility(0);
                    BlockFragmentActivity.this.tab_listActivity.setVisibility(8);
                    BlockFragmentActivity.this.pbar_listActivity.setVisibility(8);
                    BlockFragmentActivity.this.ln_bottom.setVisibility(8);
                    BlockFragmentActivity.this.ln_button_top.setVisibility(8);
                }
            }
        });
        _setFirstUI();
    }

    public void _getActivityList(String str) {
        MyGETACT myGETACT = this.myGETACT;
        if (myGETACT != null && myGETACT.isRunning) {
            this.myGETACT.cancelGETACTTask();
        }
        MyGETACT myGETACT2 = new MyGETACT();
        this.myGETACT = myGETACT2;
        myGETACT2.execute(new Void[0]);
    }

    /* loaded from: classes5.dex */
    public class MyGETACT extends AsyncTask<Void, Void, Void> {
        private boolean isRunning = false;

        public MyGETACT() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.isRunning = true;
            BlockFragmentActivity.this.ls_allactivity.clear();
            BlockFragmentActivity.this.ls_activityList.clear();
            BlockFragmentActivity.this.lm_activityData.clear();
            BlockFragmentActivity.this.ls_receiverList.clear();
            BlockFragmentActivity.this.lm_receiverData.clear();
            BlockFragmentActivity.this.ls_serviceList.clear();
            BlockFragmentActivity.this.lm_serviceData.clear();
            BlockFragmentActivity.this.btn_allapp.setVisibility(8);
            BlockFragmentActivity.this.rv_all_app.setVisibility(8);
            BlockFragmentActivity.this.pbar_listActivity.setVisibility(0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            BlockFragmentActivity.this.b_command = false;
            Shell.Result exec = Shell.cmd(BlockFragmentActivity.this.s_command_xml).exec();
            List<String> out = exec.getOut();
            exec.getCode();
            BlockFragmentActivity.this.b_command = exec.isSuccess();
            BlockFragmentActivity.this.s_commandResult = BlockFragmentActivity$MyGETACT$$ExternalSyntheticBackport0.m("\n", out);
            if (out != null && !out.isEmpty()) {
                BlockFragmentActivity.this.ls_allactivity.addAll(out);
            }
            try {
                PackageInfo packageInfo = BlockFragmentActivity.this.getContext().getApplicationContext().getPackageManager().getPackageInfo(BlockFragmentActivity.this.s_packageName, 1);
                if (packageInfo.activities != null && packageInfo.activities.length > 0) {
                    for (ActivityInfo activityInfo : packageInfo.activities) {
                        BlockFragmentActivity.this.ls_activityList.add(activityInfo.name);
                    }
                } else {
                    System.out.println("No activities found in the specified package.");
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            Collections.sort(BlockFragmentActivity.this.ls_activityList);
            int i = 0;
            for (int i2 = 0; i2 < BlockFragmentActivity.this.ls_activityList.size(); i2++) {
                BlockFragmentActivity blockFragmentActivity = BlockFragmentActivity.this;
                blockFragmentActivity.s_activityName = (String) blockFragmentActivity.ls_activityList.get(i);
                if (BlockFragmentActivity.this.ls_allactivity.contains(BlockFragmentActivity.this.s_activityName)) {
                    BlockFragmentActivity.this.s_activityStatus = "disable";
                } else {
                    BlockFragmentActivity.this.s_activityStatus = "enable";
                }
                HashMap hashMap = new HashMap();
                hashMap.put("packageapp", BlockFragmentActivity.this.s_packageName);
                hashMap.put("type", "activity");
                hashMap.put("name", BlockFragmentActivity.this.s_activityName);
                hashMap.put(NotificationCompat.CATEGORY_STATUS, BlockFragmentActivity.this.s_activityStatus);
                BlockFragmentActivity.this.lm_activityData.add(hashMap);
                i++;
            }
            SketchwareUtil.sortListMap(BlockFragmentActivity.this.lm_activityData, "name", false, true);
            try {
                PackageInfo packageInfo2 = BlockFragmentActivity.this.getContext().getApplicationContext().getPackageManager().getPackageInfo(BlockFragmentActivity.this.s_packageName, 4);
                if (packageInfo2.services != null && packageInfo2.services.length > 0) {
                    for (ServiceInfo serviceInfo : packageInfo2.services) {
                        BlockFragmentActivity.this.ls_serviceList.add(serviceInfo.name);
                    }
                } else {
                    System.out.println("No service found in the specified package.");
                }
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
            Collections.sort(BlockFragmentActivity.this.ls_serviceList);
            int i3 = 0;
            for (int i4 = 0; i4 < BlockFragmentActivity.this.ls_serviceList.size(); i4++) {
                BlockFragmentActivity blockFragmentActivity2 = BlockFragmentActivity.this;
                blockFragmentActivity2.s_activityName = (String) blockFragmentActivity2.ls_serviceList.get(i3);
                if (BlockFragmentActivity.this.ls_allactivity.contains(BlockFragmentActivity.this.s_activityName)) {
                    BlockFragmentActivity.this.s_activityStatus = "disable";
                } else {
                    BlockFragmentActivity.this.s_activityStatus = "enable";
                }
                HashMap hashMap2 = new HashMap();
                hashMap2.put("packageapp", BlockFragmentActivity.this.s_packageName);
                hashMap2.put("type", NotificationCompat.CATEGORY_SERVICE);
                hashMap2.put("name", BlockFragmentActivity.this.s_activityName);
                hashMap2.put(NotificationCompat.CATEGORY_STATUS, BlockFragmentActivity.this.s_activityStatus);
                BlockFragmentActivity.this.lm_serviceData.add(hashMap2);
                i3++;
            }
            SketchwareUtil.sortListMap(BlockFragmentActivity.this.lm_serviceData, "name", false, true);
            try {
                PackageInfo packageInfo3 = BlockFragmentActivity.this.getContext().getApplicationContext().getPackageManager().getPackageInfo(BlockFragmentActivity.this.s_packageName, 2);
                if (packageInfo3.receivers != null && packageInfo3.receivers.length > 0) {
                    for (ActivityInfo activityInfo2 : packageInfo3.receivers) {
                        BlockFragmentActivity.this.ls_receiverList.add(activityInfo2.name);
                    }
                } else {
                    System.out.println("No activities found in the specified package.");
                }
            } catch (PackageManager.NameNotFoundException e3) {
                e3.printStackTrace();
            }
            Collections.sort(BlockFragmentActivity.this.ls_receiverList);
            int i5 = 0;
            for (int i6 = 0; i6 < BlockFragmentActivity.this.ls_receiverList.size(); i6++) {
                BlockFragmentActivity blockFragmentActivity3 = BlockFragmentActivity.this;
                blockFragmentActivity3.s_activityName = (String) blockFragmentActivity3.ls_receiverList.get(i5);
                if (BlockFragmentActivity.this.ls_allactivity.contains(BlockFragmentActivity.this.s_activityName)) {
                    BlockFragmentActivity.this.s_activityStatus = "disable";
                } else {
                    BlockFragmentActivity.this.s_activityStatus = "enable";
                }
                HashMap hashMap3 = new HashMap();
                hashMap3.put("packageapp", BlockFragmentActivity.this.s_packageName);
                hashMap3.put("type", "receiver");
                hashMap3.put("name", BlockFragmentActivity.this.s_activityName);
                hashMap3.put(NotificationCompat.CATEGORY_STATUS, BlockFragmentActivity.this.s_activityStatus);
                BlockFragmentActivity.this.lm_receiverData.add(hashMap3);
                i5++;
            }
            SketchwareUtil.sortListMap(BlockFragmentActivity.this.lm_receiverData, "name", false, true);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r5) {
            this.isRunning = false;
            BlockFragmentActivity.this.tab_listActivity.getTabAt(0).select();
            ListView listView = BlockFragmentActivity.this.lv_listActivity;
            BlockFragmentActivity blockFragmentActivity = BlockFragmentActivity.this;
            listView.setAdapter((ListAdapter) new Lv_listActivityAdapter(blockFragmentActivity.lm_activityData));
            ((BaseAdapter) BlockFragmentActivity.this.lv_listActivity.getAdapter()).notifyDataSetChanged();
            ListView listView2 = BlockFragmentActivity.this.lv_listService;
            BlockFragmentActivity blockFragmentActivity2 = BlockFragmentActivity.this;
            listView2.setAdapter((ListAdapter) new Lv_listServiceAdapter(blockFragmentActivity2.lm_serviceData));
            ((BaseAdapter) BlockFragmentActivity.this.lv_listService.getAdapter()).notifyDataSetChanged();
            ListView listView3 = BlockFragmentActivity.this.lv_listReceiver;
            BlockFragmentActivity blockFragmentActivity3 = BlockFragmentActivity.this;
            listView3.setAdapter((ListAdapter) new Lv_listReceiverAdapter(blockFragmentActivity3.lm_receiverData));
            ((BaseAdapter) BlockFragmentActivity.this.lv_listReceiver.getAdapter()).notifyDataSetChanged();
            BlockFragmentActivity.this.tab_listActivity.setVisibility(0);
            BlockFragmentActivity.this.ln_button_top.setVisibility(0);
            BlockFragmentActivity.this.ln_bottom.setVisibility(0);
            BlockFragmentActivity.this.lv_listActivity.setVisibility(0);
            BlockFragmentActivity.this.rv_all_app.setVisibility(8);
            BlockFragmentActivity.this.pbar_listActivity.setVisibility(8);
            BlockFragmentActivity.this.lv_listService.setVisibility(8);
            BlockFragmentActivity.this.lv_listReceiver.setVisibility(8);
        }

        public void cancelGETACTTask() {
            cancel(true);
        }
    }

    private static String extractComponentFilters(String str, String str2) {
        Matcher matcher = Pattern.compile(str2, 32).matcher(str);
        return matcher.find() ? matcher.group(1).trim() : "";
    }

    public static boolean isPackageHasActivity(String str, PackageManager packageManager) {
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str);
        return (launchIntentForPackage == null || launchIntentForPackage.getComponent() == null) ? false : true;
    }

    public void _setFirstUI() {
        this.allapp = false;
        IfwUtils.getSecureDir();
        IfwUtils.isEncrypted();
        IfwUtils.getIfw();
        this.s_ifw_path = IfwUtils.getIfw();
        this.pbar_listActivity.setVisibility(8);
        this.tab_listActivity.setVisibility(8);
        this.ln_button_top.setVisibility(8);
        this.ln_bottom.setVisibility(8);
        TabLayout tabLayout = this.tab_listActivity;
        tabLayout.addTab(tabLayout.newTab().setText("Activity"));
        TabLayout tabLayout2 = this.tab_listActivity;
        tabLayout2.addTab(tabLayout2.newTab().setText("Service"));
        TabLayout tabLayout3 = this.tab_listActivity;
        tabLayout3.addTab(tabLayout3.newTab().setText("Receiver"));
        ArrayList<HashMap<String, Object>> arrayList = (ArrayList) new Gson().fromJson(this.prefapp.getString("all_app_list", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.6
        }.getType());
        this.lm_allapp = arrayList;
        Iterator<HashMap<String, Object>> it = arrayList.iterator();
        while (it.hasNext()) {
            if (!isPackageHasActivity(it.next().get("apppackage").toString(), requireContext().getPackageManager())) {
                it.remove();
            }
        }
        this.rv_all_app.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        this.rv_all_app.setAdapter(new Rv_all_appAdapter(this.lm_allapp));
        this.rv_all_app.setHasFixedSize(true);
    }

    public void _loadActivity() {
        this.lm_allapp = (ArrayList) new Gson().fromJson(this.prefapp.getString("all_app_list", ""), new TypeToken<ArrayList<HashMap<String, Object>>>() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.7
        }.getType());
        if (!this.allapp) {
            this.allapp = true;
            this.btn_allapp.setText("Sembunyikan");
            this.rv_all_app.setLayoutManager(new GridLayoutManager(requireContext(), 4));
            this.rv_all_app.setAdapter(new Rv_all_appAdapter(this.lm_allapp));
            this.rv_all_app.setHasFixedSize(true);
            this.rv_all_app.getAdapter().notifyDataSetChanged();
            return;
        }
        this.allapp = false;
        this.btn_allapp.setText("Lihat Semua");
        Iterator<HashMap<String, Object>> it = this.lm_allapp.iterator();
        while (it.hasNext()) {
            if (!isPackageHasActivity(it.next().get("apppackage").toString(), requireContext().getPackageManager())) {
                it.remove();
            }
        }
        this.rv_all_app.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        this.rv_all_app.setAdapter(new Rv_all_appAdapter(this.lm_allapp));
        this.rv_all_app.setHasFixedSize(true);
        this.rv_all_app.getAdapter().notifyDataSetChanged();
    }

    public void _setIntentFirewall() {
        String str;
        if (this.s_new_rules.equals("<rules>\n</rules>")) {
            _onResetIFW();
            return;
        }
        this.s_command = "echo \"" + this.s_new_rules + "\" > " + this.s_ifw_path + this.s_target_package;
        String str2 = String.valueOf(str) + " && chmod 644 " + this.s_ifw_path + this.s_target_package;
        this.s_command = str2;
        this.b_command = false;
        Shell.Result exec = Shell.cmd(str2).exec();
        List<String> out = exec.getOut();
        exec.getCode();
        this.b_command = exec.isSuccess();
        this.s_commandResult = BlockFragmentActivity$$ExternalSyntheticBackport0.m("\n", out);
        SketchwareUtil.showMessage(getContext().getApplicationContext(), "IFW diterapkan");
        _getActivityList(this.s_packageName);
    }

    public void _setNewData(double d, ArrayList<HashMap<String, Object>> arrayList, String str, String str2) {
        if (str2.equals("0")) {
            this.lm_activityData.get((int) d).put(NotificationCompat.CATEGORY_STATUS, str);
            this.lv_listActivity.invalidateViews();
        } else if (str2.equals("1")) {
            this.lm_serviceData.get((int) d).put(NotificationCompat.CATEGORY_STATUS, str);
            this.lv_listService.invalidateViews();
        } else if (str2.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
            this.lm_receiverData.get((int) d).put(NotificationCompat.CATEGORY_STATUS, str);
            this.lv_listReceiver.invalidateViews();
        }
    }

    public void _startBlockerAction() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.lm_activityData.size(); i++) {
            HashMap<String, Object> hashMap = this.lm_activityData.get(i);
            if ("disable".equals(hashMap.get(NotificationCompat.CATEGORY_STATUS).toString())) {
                String obj = hashMap.get("packageapp").toString();
                String obj2 = hashMap.get("name").toString();
                sb.append("<component-filter name=\"");
                sb.append(obj);
                sb.append("/");
                sb.append(obj2);
                sb.append("\"/>");
            }
        }
        this.s_new_activity = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        for (int i2 = 0; i2 < this.lm_serviceData.size(); i2++) {
            HashMap<String, Object> hashMap2 = this.lm_serviceData.get(i2);
            if ("disable".equals(hashMap2.get(NotificationCompat.CATEGORY_STATUS).toString())) {
                String obj3 = hashMap2.get("packageapp").toString();
                String obj4 = hashMap2.get("name").toString();
                sb2.append("<component-filter name=\"");
                sb2.append(obj3);
                sb2.append("/");
                sb2.append(obj4);
                sb2.append("\"/>");
            }
        }
        this.s_new_service = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        for (int i3 = 0; i3 < this.lm_receiverData.size(); i3++) {
            HashMap<String, Object> hashMap3 = this.lm_receiverData.get(i3);
            if ("disable".equals(hashMap3.get(NotificationCompat.CATEGORY_STATUS).toString())) {
                String obj5 = hashMap3.get("packageapp").toString();
                String obj6 = hashMap3.get("name").toString();
                sb3.append("<component-filter name=\"");
                sb3.append(obj5);
                sb3.append("/");
                sb3.append(obj6);
                sb3.append("\"/>");
            }
        }
        this.s_new_receiver = sb3.toString();
        if (this.s_new_activity.equals("")) {
            this.s_firewall_activity = "";
        } else {
            this.s_firewall_activity = String.format("\n<activity block=\"true\" log=\"true\">%s</activity>", this.s_new_activity);
        }
        if (this.s_new_service.equals("")) {
            this.s_firewall_service = "";
        } else {
            this.s_firewall_service = String.format("\n<service block=\"true\" log=\"true\">\n%s\n</service>", this.s_new_service);
        }
        if (this.s_new_receiver.equals("")) {
            this.s_firewall_receiver = "";
        } else {
            this.s_firewall_receiver = String.format("\n<broadcast block=\"true\" log=\"true\">\n%s\n</broadcast>", this.s_new_receiver);
        }
        String format = String.format("<rules>\n%s\n</rules>", String.valueOf(this.s_firewall_activity) + this.s_firewall_service + this.s_firewall_receiver);
        this.s_new_rules = format;
        String replaceAll = format.replaceAll("(?m)^[ \t]*\r?\n", "");
        this.s_new_rules = replaceAll;
        String replace = replaceAll.replace("><", ">\n<");
        this.s_new_rules = replace;
        String replace2 = replace.replace("\"", "\\\"");
        this.s_new_rules = replace2;
        this.s_new_rules = replace2.replace("$", "\\$");
        _setIntentFirewall();
    }

    public void _onResetIFW() {
        String str = "if [ -e " + this.s_ifw_path + this.s_target_package + " ]; then rm " + this.s_ifw_path + this.s_target_package + " && echo IFW berhasil direset || echo IFW gagal direset; else echo IFW tidak ada; fi";
        this.s_command = str;
        this.b_command = false;
        Shell.Result exec = Shell.cmd(str).exec();
        List<String> out = exec.getOut();
        exec.getCode();
        this.b_command = exec.isSuccess();
        this.s_commandResult = BlockFragmentActivity$$ExternalSyntheticBackport0.m("\n", out);
        if (this.b_command) {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), this.s_commandResult);
        } else {
            SketchwareUtil.showMessage(getContext().getApplicationContext(), this.s_commandResult);
        }
        _getActivityList(this.s_packageName);
    }

    public void _onRunActivity(String str, String str2) {
        String concat = "am start -n ".concat(str.concat("/".concat(str2)));
        this.s_launch_app = concat;
        this.b_command = false;
        Shell.Result exec = Shell.cmd(concat).exec();
        List<String> out = exec.getOut();
        exec.getCode();
        this.b_command = exec.isSuccess();
        this.s_launch_result = BlockFragmentActivity$$ExternalSyntheticBackport0.m("\n", out);
        SketchwareUtil.showMessage(getContext().getApplicationContext(), this.s_launch_result);
    }

    public void _createShortcut(String str, String str2, String str3, String str4) {
        try {
            this.fr_icon.setDrawingCacheEnabled(true);
            this.fr_icon.buildDrawingCache(true);
            Bitmap createBitmap = Bitmap.createBitmap(this.fr_icon.getDrawingCache());
            this.fr_icon.setDrawingCacheEnabled(false);
            String uuid = UUID.randomUUID().toString();
            if (ShortcutManagerCompat.isRequestPinShortcutSupported(requireActivity())) {
                Intent intent = new Intent(requireActivity(), ShortcutExecutorActivity.class);
                intent.setAction("android.intent.action.MAIN");
                intent.putExtra("shortcut_command", str);
                intent.putExtra("shortcut_desc", str3);
                ShortcutManagerCompat.requestPinShortcut(requireActivity(), new ShortcutInfoCompat.Builder(requireActivity(), uuid).setIntent(intent).setShortLabel(str2).setIcon(IconCompat.createWithBitmap(createBitmap)).build(), null);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /* loaded from: classes5.dex */
    public class Rv_all_appAdapter extends RecyclerView.Adapter<ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;

        public Rv_all_appAdapter(ArrayList<HashMap<String, Object>> arrayList) {
            this._data = arrayList;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = BlockFragmentActivity.this.getActivity().getLayoutInflater().inflate(R.layout.admsoloraya_res_0x7f0d002f, (ViewGroup) null);
            inflate.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new ViewHolder(inflate);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            View view = viewHolder.itemView;
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a024a);
            MaterialCardView materialCardView = (MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0354);
            ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a011d);
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a04e9)).setText(this._data.get(i).get("appname").toString());
            try {
                imageView.setImageDrawable(BlockFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(this._data.get(i).get("apppackage").toString()));
            } catch (PackageManager.NameNotFoundException unused) {
                imageView.setImageResource(R.drawable.admsoloraya_res_0x7f080099);
            }
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.Rv_all_appAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    try {
                        BlockFragmentActivity.this.im_shape.setImageDrawable(BlockFragmentActivity.this.getActivity().getPackageManager().getApplicationIcon(Rv_all_appAdapter.this._data.get(i).get("apppackage").toString()));
                    } catch (PackageManager.NameNotFoundException unused2) {
                        BlockFragmentActivity.this.im_shape.setImageResource(R.drawable.admsoloraya_res_0x7f080099);
                    }
                    BlockFragmentActivity.this.s_appName = Rv_all_appAdapter.this._data.get(i).get("appname").toString();
                    BlockFragmentActivity.this.s_packageName = Rv_all_appAdapter.this._data.get(i).get("apppackage").toString();
                    BlockFragmentActivity.this.s_target_package = BlockFragmentActivity.this.s_packageName.concat(".xml");
                    BlockFragmentActivity.this.s_command_xml = "grep \"component-filter\" ".concat(BlockFragmentActivity.this.s_ifw_path.concat(BlockFragmentActivity.this.s_target_package.concat(" | sed 's/\\\"\\/>//;s/.*\\///'")));
                    BlockFragmentActivity.this._getActivityList(BlockFragmentActivity.this.s_packageName);
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
    public class Lv_listActivityAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_listActivityAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = BlockFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d0035, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0263);
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02b3);
            LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a0264);
            ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a020e);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0213);
            MaterialButton materialButton = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00c0);
            MaterialButton materialButton2 = (MaterialButton) view.findViewById(R.id.admsoloraya_res_0x7f0a00fa);
            ((MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0354)).setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(BlockFragmentActivity.this.requireContext()));
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a051b)).setText(this._data.get(i).get("name").toString());
            if (this._data.get(i).get(NotificationCompat.CATEGORY_STATUS).toString().contains("disable")) {
                imageView.setVisibility(0);
                imageView2.setVisibility(8);
            } else {
                imageView.setVisibility(8);
                imageView2.setVisibility(0);
            }
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.Lv_listActivityAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    BlockFragmentActivity.this._setNewData(i, BlockFragmentActivity.this.lm_activityData, "enable", "0");
                }
            });
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.Lv_listActivityAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    BlockFragmentActivity.this._setNewData(i, BlockFragmentActivity.this.lm_activityData, "disable", "0");
                }
            });
            materialButton.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.Lv_listActivityAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    BlockFragmentActivity.this._onRunActivity(Lv_listActivityAdapter.this._data.get(i).get("packageapp").toString(), Lv_listActivityAdapter.this._data.get(i).get("name").toString());
                }
            });
            materialButton2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.Lv_listActivityAdapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    BlockFragmentActivity.this._createShortcut(Lv_listActivityAdapter.this._data.get(i).get("packageapp").toString().concat("/".concat(Lv_listActivityAdapter.this._data.get(i).get("name").toString())), BlockFragmentActivity.this.s_appName, "launch_act", BlockFragmentActivity.this.s_packageName);
                }
            });
            return view;
        }
    }

    /* loaded from: classes5.dex */
    public class Lv_listServiceAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_listServiceAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = BlockFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d0036, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02b3);
            ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a020e);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0213);
            ((MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0354)).setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(BlockFragmentActivity.this.requireContext()));
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a051b)).setText(this._data.get(i).get("name").toString());
            if (this._data.get(i).get(NotificationCompat.CATEGORY_STATUS).toString().contains("disable")) {
                imageView.setVisibility(0);
                imageView2.setVisibility(8);
            } else {
                imageView.setVisibility(8);
                imageView2.setVisibility(0);
            }
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.Lv_listServiceAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    BlockFragmentActivity.this._setNewData(i, BlockFragmentActivity.this.lm_serviceData, "enable", "1");
                }
            });
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.Lv_listServiceAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    BlockFragmentActivity.this._setNewData(i, BlockFragmentActivity.this.lm_serviceData, "disable", "1");
                }
            });
            return view;
        }
    }

    /* loaded from: classes5.dex */
    public class Lv_listReceiverAdapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public Lv_listReceiverAdapter(ArrayList<HashMap<String, Object>> arrayList) {
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
            LayoutInflater layoutInflater = BlockFragmentActivity.this.getActivity().getLayoutInflater();
            if (view == null) {
                view = layoutInflater.inflate(R.layout.admsoloraya_res_0x7f0d0036, (ViewGroup) null);
            }
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.admsoloraya_res_0x7f0a02b3);
            ImageView imageView = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a020e);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.admsoloraya_res_0x7f0a0213);
            ((MaterialCardView) view.findViewById(R.id.admsoloraya_res_0x7f0a0354)).setCardBackgroundColor(SurfaceColors.SURFACE_2.getColor(BlockFragmentActivity.this.requireContext()));
            ((TextView) view.findViewById(R.id.admsoloraya_res_0x7f0a051b)).setText(this._data.get(i).get("name").toString());
            if (this._data.get(i).get(NotificationCompat.CATEGORY_STATUS).toString().contains("disable")) {
                imageView.setVisibility(0);
                imageView2.setVisibility(8);
            } else {
                imageView.setVisibility(8);
                imageView2.setVisibility(0);
            }
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.Lv_listReceiverAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    BlockFragmentActivity.this._setNewData(i, BlockFragmentActivity.this.lm_receiverData, "enable", ExifInterface.GPS_MEASUREMENT_2D);
                }
            });
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.fufufu.katrina.backup.BlockFragmentActivity.Lv_listReceiverAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    BlockFragmentActivity.this._setNewData(i, BlockFragmentActivity.this.lm_receiverData, "disable", ExifInterface.GPS_MEASUREMENT_2D);
                }
            });
            return view;
        }
    }
}
