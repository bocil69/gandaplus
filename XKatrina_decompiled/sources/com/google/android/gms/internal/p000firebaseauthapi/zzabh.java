package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabh  reason: invalid package */
/* loaded from: classes.dex */
public final class zzabh implements zzafe {
    final /* synthetic */ zzagg zza;
    final /* synthetic */ zzadx zzb;
    final /* synthetic */ zzabz zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzabh(zzabz zzabzVar, zzagg zzaggVar, zzadx zzadxVar) {
        this.zzc = zzabzVar;
        this.zza = zzaggVar;
        this.zzb = zzadxVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzaff zzaffVar;
        this.zza.zzb(((zzahb) obj).zze());
        zzaffVar = this.zzc.zza;
        zzaffVar.zzd(this.zza, new zzabg(this));
    }
}
