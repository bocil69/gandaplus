package com.fufufu.katrina.backup;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import com.fufufu.katrina.backup.ProcessItem;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/* loaded from: classes5.dex */
public class Statics {
    private static final int NTHREDS = 111;
    public static String PACKAGE_NAME;
    public static int completedProcessCount;
    private static SharedPreferences prefkill;

    /* loaded from: classes5.dex */
    public static class ACTION {
        public static final String ForceStopItems = "com.fufufu.katrina.backup.ForceStopItems";
        public static final String ForceStopItems_AppType = "com.fufufu.katrina.backup.ForceStopItems.AppType";
        public static final String ForceStopItems_ProcessItems = "com.fufufu.katrina.backup.ForceStopItems.Items";
        public static final String GetAllRunningApps = "com.fufufu.katrina.backup.GetAllRunningApps";
        public static final String GetAllRunningApps_ShowToast = "com.fufufu.katrina.backup.GetAllRunningApps.ShowToast";
        public static final String HideBusyIndicator = "com.fufufu.katrina.backup.HideBusyIndicator";
        public static final String ShowBusyIndicator = "com.fufufu.katrina.backup.ShowBusyIndicator";
        public static final String ShowToast = "com.fufufu.katrina.backup.ShowToast";
        public static final String ShowToast_Message = "com.fufufu.katrina.backup.ShowToast.Message";
    }

    static int access$008() {
        int i = completedProcessCount;
        completedProcessCount = i + 1;
        return i;
    }

    public static void LogD(String str) {
        Log.d("ForceStopIt", "*** " + str + " ***");
    }

    public static boolean serviceIsRunning(Context context, Class<? extends Service> cls) {
        return PendingIntent.getService(context, 0, new Intent(context, cls), 536870912) != null;
    }

    public static boolean ForceStopPackage(String str) {
        try {
            RunAsRoot(new String[]{"sleep 0.2\nam force-stop " + str});
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void RunAsRoot(String[] strArr) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(Runtime.getRuntime().exec("su").getOutputStream());
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            dataOutputStream.writeBytes(String.valueOf(strArr[i]) + "\n");
        }
        dataOutputStream.writeBytes("exit\n");
        dataOutputStream.flush();
    }

    public static void ForceStopItems(Context context, String str, Boolean bool, Boolean bool2) {
        ArrayList<ProcessItem> closableApps = ProcessItem.getClosableApps(context, ProcessItem.ApplicationTypes.AllApps);
        ArrayList arrayList = new ArrayList();
        Iterator<ProcessItem> it = closableApps.iterator();
        while (it.hasNext()) {
            ProcessItem next = it.next();
            if (str.contains("{" + next.packageName + "}")) {
                arrayList.add(next);
            }
        }
        ForceStopItems(context, arrayList, bool, bool2);
    }

    public static void ForceStopItems(Context context, ProcessItem.ApplicationTypes applicationTypes, Boolean bool, Boolean bool2) {
        ForceStopItems(context, ProcessItem.getClosableApps(context, applicationTypes), bool, bool2);
    }

    private static void ForceStopItems(final Context context, final ArrayList<ProcessItem> arrayList, Boolean bool, final Boolean bool2) {
        final ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final Handler handler = new Handler();
        final ProgressDialog progressDialog = new ProgressDialog(context);
        prefkill = context.getSharedPreferences("katrina_kill_status", 0);
        progressDialog.setTitle(context.getString(R.string.admsoloraya_res_0x7f1200bb));
        progressDialog.setMessage(context.getString(R.string.admsoloraya_res_0x7f1200ba));
        progressDialog.setProgressStyle(1);
        progressDialog.setProgress(0);
        progressDialog.setMax(arrayList.size());
        progressDialog.setCancelable(true);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.fufufu.katrina.backup.Statics.1
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                newFixedThreadPool.shutdownNow();
            }
        });
        progressDialog.show();
        completedProcessCount = 0;
        for (final int i = 0; i < arrayList.size(); i++) {
            newFixedThreadPool.execute(new Runnable() { // from class: com.fufufu.katrina.backup.Statics.2
                @Override // java.lang.Runnable
                public void run() {
                    ProcessItem processItem = (ProcessItem) arrayList.get(i);
                    Statics.LogD(String.format("Stopping %s/%s -> %s", Integer.valueOf(i + 1), Integer.valueOf(arrayList.size()), processItem.packageName));
                    Statics.ForceStopPackage(processItem.packageName);
                    Statics.access$008();
                    Handler handler2 = handler;
                    final ProgressDialog progressDialog2 = progressDialog;
                    handler2.post(new Runnable() { // from class: com.fufufu.katrina.backup.Statics.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            progressDialog2.setProgress(Statics.completedProcessCount);
                        }
                    });
                }
            });
        }
        new Thread(new Runnable() { // from class: com.fufufu.katrina.backup.Statics.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    newFixedThreadPool.shutdown();
                    try {
                        newFixedThreadPool.awaitTermination(5L, TimeUnit.MINUTES);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Statics.LogD("Finished all threads");
                    Thread.sleep(2000L);
                    Statics.prefkill.edit().putString("kill_status", "finish").commit();
                    progressDialog.dismiss();
                    if (bool2.booleanValue()) {
                        Statics.SendBroadcast_GetAllRunningApps(context, true);
                    }
                } catch (Exception unused) {
                }
            }
        }).start();
    }

    public static void SendBroadcast_GetAllRunningApps(Context context, Boolean bool) {
        Intent intent = new Intent();
        intent.setAction(ACTION.GetAllRunningApps);
        intent.putExtra(ACTION.GetAllRunningApps_ShowToast, bool);
        context.sendBroadcast(intent);
    }
}
