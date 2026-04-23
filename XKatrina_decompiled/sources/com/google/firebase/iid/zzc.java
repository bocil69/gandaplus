package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.concurrent.ExecutorService;
/* loaded from: classes2.dex */
public abstract class zzc extends Service {
    private final Object lock;
    @VisibleForTesting
    final ExecutorService zzt;
    private Binder zzu;
    private int zzv;
    private int zzw;

    public zzc() {
        com.google.android.gms.internal.firebase_messaging.zzb zza = com.google.android.gms.internal.firebase_messaging.zza.zza();
        String valueOf = String.valueOf(getClass().getSimpleName());
        this.zzt = zza.zza(new NamedThreadFactory(valueOf.length() != 0 ? "Firebase-".concat(valueOf) : new String("Firebase-")), com.google.android.gms.internal.firebase_messaging.zzf.zze);
        this.lock = new Object();
        this.zzw = 0;
    }

    public abstract void zzd(Intent intent);

    @Override // android.app.Service
    public final synchronized IBinder onBind(Intent intent) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "Service received bind request");
        }
        if (this.zzu == null) {
            this.zzu = new zzg(this);
        }
        return this.zzu;
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        synchronized (this.lock) {
            this.zzv = i2;
            this.zzw++;
        }
        Intent zzb = zzb(intent);
        if (zzb == null) {
            zza(intent);
            return 2;
        } else if (zzc(zzb)) {
            zza(intent);
            return 2;
        } else {
            this.zzt.execute(new zzb(this, zzb, intent));
            return 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Intent intent) {
        if (intent != null) {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
        synchronized (this.lock) {
            this.zzw--;
            if (this.zzw == 0) {
                stopSelfResult(this.zzv);
            }
        }
    }

    protected Intent zzb(Intent intent) {
        return intent;
    }

    public boolean zzc(Intent intent) {
        return false;
    }
}
