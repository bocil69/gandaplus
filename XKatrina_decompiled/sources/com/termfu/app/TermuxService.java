package com.termfu.app;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import com.fufufu.katrina.backup.R;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.termux.terminal.EmulatorDebug;
import com.termux.terminal.TerminalSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public final class TermuxService extends Service implements TerminalSession.SessionChangedCallback {
    public static final String ACTION_EXECUTE = "com.termfu.service_execute";
    private static final String ACTION_LOCK_WAKE = "com.termfu.service_wake_lock";
    private static final String ACTION_STOP_SERVICE = "com.termfu.service_stop";
    private static final String ACTION_UNLOCK_WAKE = "com.termfu.service_wake_unlock";
    public static final String EXTRA_ARGUMENTS = "com.termfu.execute.arguments";
    public static final String EXTRA_CURRENT_WORKING_DIRECTORY = "com.termfu.execute.cwd";
    public static final String EXTRA_EXECUTE_IN_BACKGROUND = "com.termfu.execute.background";
    @SuppressLint({"SdCardPath"})
    public static final String FILES_PATH = "/data/data/com.fufufu.katrina.backup/files";
    public static final String HOME_PATH = "/data/data/com.fufufu.katrina.backup/files/home";
    private static final String NOTIFICATION_CHANNEL_ID = "termfu_notification_channel";
    private static final int NOTIFICATION_ID = 1337;
    public static final String PREFIX_PATH = "/data/data/com.fufufu.katrina.backup";
    public static final String SC_PATH = "/data/data/com.fufufu.katrina.backup/files/home/.shortcuts";
    public static final String SH_PATH = "/data/data/com.fufufu.katrina.backup/files/home/.sh";
    TerminalSession.SessionChangedCallback mSessionChangeCallback;
    private PowerManager.WakeLock mWakeLock;
    private WifiManager.WifiLock mWifiLock;
    private final IBinder mBinder = new LocalBinder();
    private final Handler mHandler = new Handler();
    final List<TerminalSession> mTerminalSessions = new ArrayList();
    final List<BackgroundJob> mBackgroundTasks = new ArrayList();
    boolean mWantsToStop = false;

    /* loaded from: classes6.dex */
    class LocalBinder extends Binder {
        public final TermuxService service;

        LocalBinder() {
            this.service = TermuxService.this;
        }
    }

    @Override // android.app.Service
    @SuppressLint({"Wakelock"})
    public int onStartCommand(Intent intent, int i, int i2) {
        String action = intent.getAction();
        if (ACTION_STOP_SERVICE.equals(action)) {
            this.mWantsToStop = true;
            for (int i3 = 0; i3 < this.mTerminalSessions.size(); i3++) {
                this.mTerminalSessions.get(i3).finishIfRunning();
            }
            stopSelf();
            return 2;
        } else if (ACTION_LOCK_WAKE.equals(action)) {
            if (this.mWakeLock == null) {
                PowerManager powerManager = (PowerManager) getSystemService("power");
                PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(1, "termfu:service-wakelock");
                this.mWakeLock = newWakeLock;
                newWakeLock.acquire();
                WifiManager.WifiLock createWifiLock = ((WifiManager) getApplicationContext().getSystemService("wifi")).createWifiLock(3, EmulatorDebug.LOG_TAG);
                this.mWifiLock = createWifiLock;
                createWifiLock.acquire();
                String packageName = getPackageName();
                if (!powerManager.isIgnoringBatteryOptimizations(packageName)) {
                    Intent intent2 = new Intent();
                    intent2.setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
                    intent2.setData(Uri.parse("package:" + packageName));
                    intent2.setFlags(268435456);
                    try {
                        startActivity(intent2);
                    } catch (ActivityNotFoundException e) {
                        Log.e(EmulatorDebug.LOG_TAG, "Failed to call ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", e);
                    }
                }
                updateNotification();
                return 2;
            }
            return 2;
        } else if (ACTION_UNLOCK_WAKE.equals(action)) {
            PowerManager.WakeLock wakeLock = this.mWakeLock;
            if (wakeLock != null) {
                wakeLock.release();
                this.mWakeLock = null;
                this.mWifiLock.release();
                this.mWifiLock = null;
                updateNotification();
                return 2;
            }
            return 2;
        } else if (!"com.termfu.service_execute".equals(action)) {
            if (action != null) {
                Log.e(EmulatorDebug.LOG_TAG, "Unknown TermuxService action: '" + action + "'");
                return 2;
            }
            return 2;
        } else {
            Uri data = intent.getData();
            String path = data == null ? null : data.getPath();
            String[] stringArrayExtra = data != null ? intent.getStringArrayExtra(EXTRA_ARGUMENTS) : null;
            String stringExtra = intent.getStringExtra(EXTRA_CURRENT_WORKING_DIRECTORY);
            if (intent.getBooleanExtra(EXTRA_EXECUTE_IN_BACKGROUND, false)) {
                this.mBackgroundTasks.add(new BackgroundJob(stringExtra, path, stringArrayExtra, this, (PendingIntent) intent.getParcelableExtra(BaseGmsClient.KEY_PENDING_INTENT)));
                updateNotification();
                return 2;
            }
            TerminalSession createTermSession = createTermSession(path, stringArrayExtra, stringExtra, intent.getBooleanExtra(TermuxActivity.TERMUX_FAILSAFE_SESSION_ACTION, true));
            if (path != null) {
                int lastIndexOf = path.lastIndexOf(47);
                if (lastIndexOf != -1) {
                    path = path.substring(lastIndexOf + 1);
                }
                createTermSession.mSessionName = path.replace('-', ' ');
            }
            TermuxPreferences.storeCurrentSession(this, createTermSession);
            startActivity(new Intent(this, TermuxActivity.class).addFlags(268435456));
            return 2;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        setupNotificationChannel();
        startForeground(NOTIFICATION_ID, buildNotification());
    }

    void updateNotification() {
        if (this.mWakeLock == null && this.mTerminalSessions.isEmpty() && this.mBackgroundTasks.isEmpty()) {
            stopSelf();
        } else {
            ((NotificationManager) getSystemService("notification")).notify(NOTIFICATION_ID, buildNotification());
        }
    }

    private Notification buildNotification() {
        Intent intent = new Intent(this, TermuxActivity.class);
        intent.addFlags(268435456);
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
        int size = this.mTerminalSessions.size();
        int size2 = this.mBackgroundTasks.size();
        StringBuilder sb = new StringBuilder(String.valueOf(size));
        sb.append(" session");
        sb.append(size == 1 ? "" : "s");
        String sb2 = sb.toString();
        if (size2 > 0) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2));
            sb3.append(", ");
            sb3.append(size2);
            sb3.append(" task");
            sb3.append(size2 != 1 ? "s" : "");
            sb2 = sb3.toString();
        }
        boolean z = this.mWakeLock != null;
        if (z) {
            sb2 = String.valueOf(sb2) + " (wake lock held)";
        }
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(getText(R.string.admsoloraya_res_0x7f1200ca));
        builder.setContentText(sb2);
        builder.setSmallIcon(R.drawable.admsoloraya_res_0x7f0801da);
        builder.setContentIntent(activity);
        builder.setOngoing(true);
        builder.setPriority(z ? 1 : -1);
        builder.setShowWhen(false);
        builder.setColor(-10453621);
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        }
        Resources resources = getResources();
        builder.addAction(17301533, resources.getString(R.string.admsoloraya_res_0x7f1200df), PendingIntent.getService(this, 0, new Intent(this, TermuxService.class).setAction(ACTION_STOP_SERVICE), 0));
        builder.addAction(z ? 17301535 : 17301551, resources.getString(z ? R.string.admsoloraya_res_0x7f1200e1 : R.string.admsoloraya_res_0x7f1200e0), PendingIntent.getService(this, 0, new Intent(this, TermuxService.class).setAction(z ? ACTION_UNLOCK_WAKE : ACTION_LOCK_WAKE), 0));
        return builder.build();
    }

    @Override // android.app.Service
    public void onDestroy() {
        File file = new File("/data/data/com.fufufu.katrina.backup/tmp");
        if (file.exists()) {
            try {
                TermuxInstaller.deleteFolder(file.getCanonicalFile());
            } catch (Exception e) {
                Log.e(EmulatorDebug.LOG_TAG, "Error while removing file at " + file.getAbsolutePath(), e);
            }
            file.mkdirs();
        }
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock != null) {
            wakeLock.release();
        }
        WifiManager.WifiLock wifiLock = this.mWifiLock;
        if (wifiLock != null) {
            wifiLock.release();
        }
        stopForeground(true);
        for (int i = 0; i < this.mTerminalSessions.size(); i++) {
            this.mTerminalSessions.get(i).finishIfRunning();
        }
    }

    public List<TerminalSession> getSessions() {
        return this.mTerminalSessions;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TerminalSession createTermSession(String str, String[] strArr, String str2, boolean z) {
        boolean z2;
        new File(HOME_PATH).mkdirs();
        new File("/data/data/com.fufufu.katrina.backup/files/home/.shortcuts").mkdirs();
        new File(SH_PATH).mkdirs();
        String str3 = str2 == null ? HOME_PATH : str2;
        String[] buildEnvironment = BackgroundJob.buildEnvironment(z, str3);
        if (str == null) {
            if (!z) {
                String[] strArr2 = {"login", "bash", "zsh"};
                int i = 0;
                while (true) {
                    if (i >= 3) {
                        break;
                    }
                    String str4 = strArr2[i];
                    File file = new File("/system/bin/" + str4);
                    if (file.canExecute()) {
                        str = file.getAbsolutePath();
                        break;
                    }
                    i++;
                }
            }
            if (str == null) {
                str = "/system/bin/sh";
            }
            z2 = true;
        } else {
            z2 = false;
        }
        String[] strArr3 = BackgroundJob.setupProcessArgs(str, strArr);
        String str5 = strArr3[0];
        int lastIndexOf = str5.lastIndexOf(47);
        StringBuilder sb = new StringBuilder(z2 ? "-" : "");
        sb.append(lastIndexOf == -1 ? str5 : str5.substring(lastIndexOf + 1));
        String sb2 = sb.toString();
        String[] strArr4 = new String[strArr3.length];
        strArr4[0] = sb2;
        if (strArr3.length > 1) {
            System.arraycopy(strArr3, 1, strArr4, 1, strArr3.length - 1);
        }
        TerminalSession terminalSession = new TerminalSession(str5, str3, strArr4, buildEnvironment, this);
        this.mTerminalSessions.add(terminalSession);
        updateNotification();
        Intent intent = new Intent("com.termfu.app.reload_style");
        intent.putExtra("com.termfu.app.reload_style", "styling");
        sendBroadcast(intent);
        return terminalSession;
    }

    public int removeTermSession(TerminalSession terminalSession) {
        int indexOf = this.mTerminalSessions.indexOf(terminalSession);
        this.mTerminalSessions.remove(indexOf);
        if (this.mTerminalSessions.isEmpty() && this.mWakeLock == null) {
            stopSelf();
        } else {
            updateNotification();
        }
        return indexOf;
    }

    @Override // com.termux.terminal.TerminalSession.SessionChangedCallback
    public void onTitleChanged(TerminalSession terminalSession) {
        TerminalSession.SessionChangedCallback sessionChangedCallback = this.mSessionChangeCallback;
        if (sessionChangedCallback != null) {
            sessionChangedCallback.onTitleChanged(terminalSession);
        }
    }

    @Override // com.termux.terminal.TerminalSession.SessionChangedCallback
    public void onSessionFinished(TerminalSession terminalSession) {
        TerminalSession.SessionChangedCallback sessionChangedCallback = this.mSessionChangeCallback;
        if (sessionChangedCallback != null) {
            sessionChangedCallback.onSessionFinished(terminalSession);
        }
    }

    @Override // com.termux.terminal.TerminalSession.SessionChangedCallback
    public void onTextChanged(TerminalSession terminalSession) {
        TerminalSession.SessionChangedCallback sessionChangedCallback = this.mSessionChangeCallback;
        if (sessionChangedCallback != null) {
            sessionChangedCallback.onTextChanged(terminalSession);
        }
    }

    @Override // com.termux.terminal.TerminalSession.SessionChangedCallback
    public void onClipboardText(TerminalSession terminalSession, String str) {
        TerminalSession.SessionChangedCallback sessionChangedCallback = this.mSessionChangeCallback;
        if (sessionChangedCallback != null) {
            sessionChangedCallback.onClipboardText(terminalSession, str);
        }
    }

    @Override // com.termux.terminal.TerminalSession.SessionChangedCallback
    public void onBell(TerminalSession terminalSession) {
        TerminalSession.SessionChangedCallback sessionChangedCallback = this.mSessionChangeCallback;
        if (sessionChangedCallback != null) {
            sessionChangedCallback.onBell(terminalSession);
        }
    }

    @Override // com.termux.terminal.TerminalSession.SessionChangedCallback
    public void onColorsChanged(TerminalSession terminalSession) {
        TerminalSession.SessionChangedCallback sessionChangedCallback = this.mSessionChangeCallback;
        if (sessionChangedCallback != null) {
            sessionChangedCallback.onColorsChanged(terminalSession);
        }
    }

    public void onBackgroundJobExited(final BackgroundJob backgroundJob) {
        this.mHandler.post(new Runnable() { // from class: com.termfu.app.TermuxService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                TermuxService.this.m122lambda$0$comtermfuappTermuxService(backgroundJob);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$0$com-termfu-app-TermuxService  reason: not valid java name */
    public /* synthetic */ void m122lambda$0$comtermfuappTermuxService(BackgroundJob backgroundJob) {
        this.mBackgroundTasks.remove(backgroundJob);
        updateNotification();
    }

    private void setupNotificationChannel() {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Termux", 2);
        notificationChannel.setDescription("Notifications from Termux");
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(notificationChannel);
    }
}
