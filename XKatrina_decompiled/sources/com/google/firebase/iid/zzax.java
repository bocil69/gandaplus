package com.google.firebase.iid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.util.Log;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzax implements Runnable {
    private final zzaz zzay;
    private final long zzdk;
    private final PowerManager.WakeLock zzdl = ((PowerManager) getContext().getSystemService("power")).newWakeLock(1, "fiid-sync");
    private final FirebaseInstanceId zzdm;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public zzax(FirebaseInstanceId firebaseInstanceId, zzan zzanVar, zzaz zzazVar, long j) {
        this.zzdm = firebaseInstanceId;
        this.zzay = zzazVar;
        this.zzdk = j;
        this.zzdl.setReferenceCounted(false);
    }

    @Override // java.lang.Runnable
    @SuppressLint({"Wakelock"})
    public final void run() {
        try {
            if (zzaw.zzak().zzd(getContext())) {
                this.zzdl.acquire();
            }
            this.zzdm.zza(true);
            if (!this.zzdm.zzo()) {
                this.zzdm.zza(false);
            } else if (zzaw.zzak().zze(getContext()) && !zzan()) {
                new zzba(this).zzaq();
                if (zzaw.zzak().zzd(getContext())) {
                    this.zzdl.release();
                }
            } else {
                if (zzam() && this.zzay.zzc(this.zzdm)) {
                    this.zzdm.zza(false);
                } else {
                    this.zzdm.zza(this.zzdk);
                }
                if (zzaw.zzak().zzd(getContext())) {
                    this.zzdl.release();
                }
            }
        } finally {
            if (zzaw.zzak().zzd(getContext())) {
                this.zzdl.release();
            }
        }
    }

    @VisibleForTesting
    private final boolean zzam() {
        zzay zzk = this.zzdm.zzk();
        if (this.zzdm.zzr() || this.zzdm.zza(zzk)) {
            try {
                String zzl = this.zzdm.zzl();
                if (zzl == null) {
                    Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                    return false;
                }
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    Log.d("FirebaseInstanceId", "Token successfully retrieved");
                }
                if (zzk == null || !(zzk == null || zzl.equals(zzk.zzbv))) {
                    Intent intent = new Intent("com.google.firebase.messaging.NEW_TOKEN");
                    intent.putExtra("token", zzl);
                    Context context = getContext();
                    Intent intent2 = new Intent(context, FirebaseInstanceIdReceiver.class);
                    intent2.setAction("com.google.firebase.MESSAGING_EVENT");
                    intent2.putExtra("wrapped_intent", intent);
                    context.sendBroadcast(intent2);
                    return true;
                }
                return true;
            } catch (IOException | SecurityException e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("FirebaseInstanceId", valueOf.length() != 0 ? "Token retrieval failed: ".concat(valueOf) : new String("Token retrieval failed: "));
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Context getContext() {
        return this.zzdm.zzi().getApplicationContext();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzan() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
