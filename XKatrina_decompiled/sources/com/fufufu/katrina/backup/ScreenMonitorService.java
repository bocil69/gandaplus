package com.fufufu.katrina.backup;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
/* loaded from: classes5.dex */
public class ScreenMonitorService extends Service {
    private SharedPreferences prefrandom;
    private BroadcastReceiver screenOnReceiver = new BroadcastReceiver() { // from class: com.fufufu.katrina.backup.ScreenMonitorService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!"android.intent.action.USER_PRESENT".equals(intent.getAction()) || "false".equals(ScreenMonitorService.this.prefrandom.getString("random_wall", ""))) {
                return;
            }
            Intent intent2 = new Intent(context, WallActivity.class);
            intent2.putExtra("setwall", "setwall");
            intent2.addFlags(268435456);
            context.startActivity(intent2);
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.prefrandom = getSharedPreferences("random_preferences", 0);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        registerReceiver(this.screenOnReceiver, intentFilter);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.screenOnReceiver);
    }
}
