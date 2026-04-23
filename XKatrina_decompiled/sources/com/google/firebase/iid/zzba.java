package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.Nullable;
@VisibleForTesting
/* loaded from: classes2.dex */
final class zzba extends BroadcastReceiver {
    @Nullable
    private zzax zzdr;

    public zzba(zzax zzaxVar) {
        this.zzdr = zzaxVar;
    }

    public final void zzaq() {
        if (FirebaseInstanceId.zzm()) {
            Log.d("FirebaseInstanceId", "Connectivity change received registered");
        }
        this.zzdr.getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (this.zzdr != null && this.zzdr.zzan()) {
            if (FirebaseInstanceId.zzm()) {
                Log.d("FirebaseInstanceId", "Connectivity changed. Starting background sync.");
            }
            FirebaseInstanceId.zza(this.zzdr, 0L);
            this.zzdr.getContext().unregisterReceiver(this);
            this.zzdr = null;
        }
    }
}
