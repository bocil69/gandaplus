package com.google.firebase.auth.internal;

import com.google.android.gms.common.api.internal.BackgroundDetector;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzbu implements BackgroundDetector.BackgroundStateChangeListener {
    final /* synthetic */ zzbv zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbu(zzbv zzbvVar) {
        this.zza = zzbvVar;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean z) {
        boolean zzg;
        zzam zzamVar;
        if (z) {
            this.zza.zzc = true;
            this.zza.zzc();
            return;
        }
        this.zza.zzc = false;
        zzg = this.zza.zzg();
        if (zzg) {
            zzamVar = this.zza.zzb;
            zzamVar.zzc();
        }
    }
}
