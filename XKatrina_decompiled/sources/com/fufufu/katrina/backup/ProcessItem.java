package com.fufufu.katrina.backup;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import com.stericson.RootShell.RootShell;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;
/* loaded from: classes5.dex */
public class ProcessItem<T> implements Comparable<ProcessItem<T>>, Parcelable {
    private static final String PREFS_NAME = "ProcessItem";
    private Context context;
    public Drawable icon;
    public String applicationName = "";
    public Boolean check = true;
    public Boolean isSystemApp = false;
    public String packageName = "";
    public Boolean saveChanges = false;
    public int versionCode = 0;
    public String versionName = "";

    /* loaded from: classes5.dex */
    public enum ApplicationTypes {
        AllApps,
        OnlyUserApps,
        OnlySystemApps;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static ApplicationTypes[] valuesCustom() {
            ApplicationTypes[] valuesCustom = values();
            int length = valuesCustom.length;
            ApplicationTypes[] applicationTypesArr = new ApplicationTypes[length];
            System.arraycopy(valuesCustom, 0, applicationTypesArr, 0, length);
            return applicationTypesArr;
        }
    }

    /* loaded from: classes5.dex */
    public enum FilterTypes {
        AllApps,
        OnlyCheckedApps,
        OnlyUncheckedApps;

        /* renamed from: values  reason: to resolve conflict with enum method */
        public static FilterTypes[] valuesCustom() {
            FilterTypes[] valuesCustom = values();
            int length = valuesCustom.length;
            FilterTypes[] filterTypesArr = new FilterTypes[length];
            System.arraycopy(valuesCustom, 0, filterTypesArr, 0, length);
            return filterTypesArr;
        }
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((ProcessItem) ((ProcessItem) obj));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public ProcessItem(Context context) {
        this.context = context;
    }

    public int compareTo(ProcessItem<T> processItem) {
        return this.applicationName.compareToIgnoreCase(processItem.applicationName);
    }

    public static ArrayList<ProcessItem> getRunningApps(Context context, ApplicationTypes applicationTypes) {
        ArrayList<ProcessItem> arrayList = new ArrayList<>();
        ArrayList<ProcessItem> closableApps = getClosableApps(context, applicationTypes);
        ArrayList<String> runningPackages = getRunningPackages(context);
        for (int i = 0; i < closableApps.size(); i++) {
            ProcessItem processItem = closableApps.get(i);
            if (runningPackages.contains(processItem.packageName)) {
                arrayList.add(processItem);
            }
        }
        return arrayList;
    }

    public static ArrayList<ProcessItem> getClosableApps(Context context, ApplicationTypes applicationTypes) {
        ArrayList<ProcessItem> arrayList = new ArrayList<>();
        ArrayList<ProcessItem> installedApps = getInstalledApps(context, applicationTypes, FilterTypes.AllApps);
        ArrayList<String> whiteList = getWhiteList(context);
        ArrayList<String> blackList = getBlackList(context);
        for (int i = 0; i < installedApps.size(); i++) {
            ProcessItem processItem = installedApps.get(i);
            if ((processItem.isSystemApp.booleanValue() && blackList.contains(processItem.packageName)) || (!processItem.isSystemApp.booleanValue() && !whiteList.contains(processItem.packageName))) {
                arrayList.add(processItem);
            }
        }
        return arrayList;
    }

    public static ArrayList<ProcessItem> getInstalledApps(Context context, ApplicationTypes applicationTypes, FilterTypes filterTypes) {
        ArrayList<ProcessItem> arrayList = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        ArrayList<String> whiteList = getWhiteList(context);
        ArrayList<String> blackList = getBlackList(context);
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            boolean z = true;
            Boolean valueOf = Boolean.valueOf((packageInfo.applicationInfo.flags & 1) == 1);
            if ((!applicationTypes.equals(ApplicationTypes.OnlyUserApps) || !valueOf.booleanValue()) && ((!applicationTypes.equals(ApplicationTypes.OnlySystemApps) || valueOf.booleanValue()) && !packageInfo.packageName.equals(Statics.PACKAGE_NAME))) {
                ProcessItem processItem = new ProcessItem(context);
                processItem.applicationName = packageInfo.applicationInfo.loadLabel(packageManager).toString();
                processItem.packageName = packageInfo.packageName;
                processItem.versionName = packageInfo.versionName;
                processItem.versionCode = packageInfo.versionCode;
                processItem.icon = packageInfo.applicationInfo.loadIcon(packageManager);
                processItem.isSystemApp = valueOf;
                if ((!valueOf.booleanValue() || !blackList.contains(processItem.packageName)) && (processItem.isSystemApp.booleanValue() || whiteList.contains(processItem.packageName))) {
                    z = false;
                }
                processItem.check = Boolean.valueOf(z);
                if (filterTypes == FilterTypes.AllApps || ((filterTypes == FilterTypes.OnlyCheckedApps && processItem.check.booleanValue()) || (filterTypes == FilterTypes.OnlyUncheckedApps && !processItem.check.booleanValue()))) {
                    arrayList.add(processItem);
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static ArrayList<String> getRunningPackages(Context context) {
        final ArrayList<String> arrayList = new ArrayList<>();
        try {
            RootShell.getShell(true);
            try {
                try {
                    RootTools.getShell(true).add(new Command(0, new String[]{"ps"}) { // from class: com.fufufu.katrina.backup.ProcessItem.1
                        @Override // com.stericson.RootShell.execution.Command
                        public void commandOutput(int i, String str) {
                            arrayList.add(str.substring(str.lastIndexOf(" ") + 1));
                        }
                    });
                } catch (RootDeniedException e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (TimeoutException e3) {
                e3.printStackTrace();
            }
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    private static ArrayList<String> getWhiteList(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Cursor select = new DBAdapter(context).select("whiteList", new String[]{"packageName"}, null, null, null, null, null, null);
            if (select != null) {
                if (select.moveToFirst()) {
                    do {
                        arrayList.add(select.getString(0));
                    } while (select.moveToNext());
                    select.close();
                } else {
                    select.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private static ArrayList<String> getBlackList(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            Cursor select = new DBAdapter(context).select("blackList", new String[]{"packageName"}, null, null, null, null, null, null);
            if (select != null) {
                if (select.moveToFirst()) {
                    do {
                        arrayList.add(select.getString(0));
                    } while (select.moveToNext());
                    select.close();
                } else {
                    select.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public boolean isExists(Context context) {
        Cursor select = new DBAdapter(context).select(this.isSystemApp.booleanValue() ? "blackList" : "whiteList", new String[]{"packageName"}, "packageName=?", new String[]{this.packageName}, null, null, null, null);
        if (select != null) {
            boolean z = select.getCount() > 0;
            select.close();
            return z;
        }
        return false;
    }

    private void insertToDb() {
        DBAdapter dBAdapter = new DBAdapter(this.context);
        ContentValues contentValues = new ContentValues();
        contentValues.put("packageName", this.packageName);
        dBAdapter.insert(this.isSystemApp.booleanValue() ? "blackList" : "whiteList", contentValues);
    }

    private void deleteFromDb() {
        DBAdapter dBAdapter = new DBAdapter(this.context);
        ContentValues contentValues = new ContentValues();
        contentValues.put("packageName", this.packageName);
        dBAdapter.delete(this.isSystemApp.booleanValue() ? "blackList" : "whiteList", contentValues);
    }

    public void save() {
        try {
            if (this.isSystemApp.booleanValue()) {
                if (this.check.booleanValue()) {
                    if (!isExists(this.context)) {
                        insertToDb();
                    }
                } else if (isExists(this.context)) {
                    deleteFromDb();
                }
            } else if (this.check.booleanValue()) {
                if (isExists(this.context)) {
                    deleteFromDb();
                }
            } else if (!isExists(this.context)) {
                insertToDb();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
