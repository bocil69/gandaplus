package com.google.firebase.iid;

import android.util.Log;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzf implements Runnable {
    private final /* synthetic */ zze zzac;
    private final /* synthetic */ zzg zzad;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(zzg zzgVar, zze zzeVar) {
        this.zzad = zzgVar;
        this.zzac = zzeVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzc zzcVar;
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
        }
        zzcVar = this.zzad.zzae;
        zzcVar.zzd(this.zzac.intent);
        this.zzac.finish();
    }
}
