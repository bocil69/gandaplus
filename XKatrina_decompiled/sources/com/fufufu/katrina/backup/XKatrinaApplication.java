package com.fufufu.katrina.backup;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.color.DynamicColors;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import java.lang.Thread;
import java.text.Normalizer;
/* loaded from: classes5.dex */
public class XKatrinaApplication extends Application {
    private static Context mApplicationContext;
    private String s_channel_id = "notif_channel";
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    public static Context getContext() {
        return mApplicationContext;
    }

    @Override // android.app.Application
    public void onCreate() {
        _getDeviceFCMToken();
        mApplicationContext = getApplicationContext();
        this.uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.fufufu.katrina.backup.XKatrinaApplication.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                Intent intent = new Intent(XKatrinaApplication.this.getApplicationContext(), MaterialDebugActivity.class);
                intent.setFlags(32768);
                intent.putExtra("mode", "crash");
                intent.putExtra("error", Log.getStackTraceString(th));
                intent.putExtra("title", "XKatrina Crash");
                ((AlarmManager) XKatrinaApplication.this.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(2, 1000L, PendingIntent.getActivity(XKatrinaApplication.this.getApplicationContext(), 11111, intent, 1073741824));
                Process.killProcess(Process.myPid());
                System.exit(1);
                XKatrinaApplication.this.uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        });
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
    }

    public void _getDeviceFCMToken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() { // from class: com.fufufu.katrina.backup.XKatrinaApplication.2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                task.isSuccessful();
            }
        });
        if ("all".matches("[a-zA-Z0-9-_.~%]{1,900}")) {
            FirebaseMessaging.getInstance().subscribeToTopic(Normalizer.normalize("all", Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")).addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.fufufu.katrina.backup.XKatrinaApplication.3
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(@NonNull Task<Void> task) {
                    task.isSuccessful();
                }
            });
        }
    }
}
