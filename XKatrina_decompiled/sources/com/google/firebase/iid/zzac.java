package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes2.dex */
public final class zzac {
    @GuardedBy("MessengerIpcClient.class")
    private static zzac zzby;
    private final Context zzag;
    private final ScheduledExecutorService zzbz;
    @GuardedBy("this")
    private zzae zzca = new zzae(this);
    @GuardedBy("this")
    private int zzcb = 1;

    public static synchronized zzac zzc(Context context) {
        zzac zzacVar;
        synchronized (zzac.class) {
            if (zzby == null) {
                zzby = new zzac(context, com.google.android.gms.internal.firebase_messaging.zza.zza().zza(1, new NamedThreadFactory("MessengerIpcClient"), com.google.android.gms.internal.firebase_messaging.zzf.zze));
            }
            zzacVar = zzby;
        }
        return zzacVar;
    }

    @VisibleForTesting
    private zzac(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzbz = scheduledExecutorService;
        this.zzag = context.getApplicationContext();
    }

    public final Task<Void> zza(int i, Bundle bundle) {
        return zza(new zzak(zzx(), 2, bundle));
    }

    public final Task<Bundle> zzb(int i, Bundle bundle) {
        return zza(new zzal(zzx(), 1, bundle));
    }

    private final synchronized <T> Task<T> zza(zzaj<T> zzajVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(zzajVar);
            Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(valueOf).length() + 9).append("Queueing ").append(valueOf).toString());
        }
        if (!this.zzca.zzb(zzajVar)) {
            this.zzca = new zzae(this);
            this.zzca.zzb(zzajVar);
        }
        return zzajVar.zzcl.getTask();
    }

    private final synchronized int zzx() {
        int i;
        i = this.zzcb;
        this.zzcb = i + 1;
        return i;
    }
}
