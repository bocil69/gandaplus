package com.fufufu.katrina.backup;

import android.app.TabActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TabHost;
import com.fufufu.katrina.backup.ProcessItem;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes5.dex */
public class AppListAll extends TabActivity {
    ListView mListViewSystem;
    ListView mListViewUser;

    @Override // android.app.ActivityGroup, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.admsoloraya_res_0x7f0d001f);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(0);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.setNavigationBarColor(0);
        }
        getWindow().getDecorView().setSystemUiVisibility(8208);
        if (Statics.PACKAGE_NAME == null) {
            Statics.PACKAGE_NAME = getPackageName();
        }
        this.mListViewUser = (ListView) findViewById(R.id.admsoloraya_res_0x7f0a0248);
        this.mListViewSystem = (ListView) findViewById(R.id.admsoloraya_res_0x7f0a0247);
        TabHost tabHost = (TabHost) findViewById(16908306);
        TabHost.TabSpec newTabSpec = tabHost.newTabSpec("tabUser");
        newTabSpec.setContent(R.id.admsoloraya_res_0x7f0a0475);
        newTabSpec.setIndicator(getString(R.string.admsoloraya_res_0x7f1200c9));
        tabHost.addTab(newTabSpec);
        TabHost.TabSpec newTabSpec2 = tabHost.newTabSpec("tabSystem");
        newTabSpec2.setContent(R.id.admsoloraya_res_0x7f0a0476);
        newTabSpec2.setIndicator(getString(R.string.admsoloraya_res_0x7f1200c8));
        tabHost.addTab(newTabSpec2);
        Spinner spinner = (Spinner) findViewById(R.id.admsoloraya_res_0x7f0a0457);
        spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(this, 17367049, getResources().getStringArray(R.array.admsoloraya)));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.fufufu.katrina.backup.AppListAll.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ProcessItem.FilterTypes filterTypes = ProcessItem.FilterTypes.AllApps;
                if (i == 1) {
                    filterTypes = ProcessItem.FilterTypes.OnlyCheckedApps;
                } else if (i == 2) {
                    filterTypes = ProcessItem.FilterTypes.OnlyUncheckedApps;
                }
                AppListAll.this.GetUserApps(filterTypes);
            }
        });
        Spinner spinner2 = (Spinner) findViewById(R.id.admsoloraya_res_0x7f0a0456);
        spinner2.setAdapter((SpinnerAdapter) new ArrayAdapter(this, 17367049, getResources().getStringArray(R.array.admsoloraya)));
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.fufufu.katrina.backup.AppListAll.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                ProcessItem.FilterTypes filterTypes = ProcessItem.FilterTypes.AllApps;
                if (i == 1) {
                    filterTypes = ProcessItem.FilterTypes.OnlyCheckedApps;
                } else if (i == 2) {
                    filterTypes = ProcessItem.FilterTypes.OnlyUncheckedApps;
                }
                AppListAll.this.GetSystemApps(filterTypes);
            }
        });
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        finish();
    }

    public void GetUserApps(ProcessItem.FilterTypes filterTypes) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(ProcessItem.getInstalledApps(this, ProcessItem.ApplicationTypes.OnlyUserApps, filterTypes));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((ProcessItem) it.next()).saveChanges = true;
        }
        this.mListViewUser.setAdapter((ListAdapter) new ProcessItemArrayAdapter(this, arrayList));
    }

    public void GetSystemApps(ProcessItem.FilterTypes filterTypes) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(ProcessItem.getInstalledApps(this, ProcessItem.ApplicationTypes.OnlySystemApps, filterTypes));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((ProcessItem) it.next()).saveChanges = true;
        }
        this.mListViewSystem.setAdapter((ListAdapter) new ProcessItemArrayAdapter(this, arrayList));
    }
}
