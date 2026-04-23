package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaaw  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaaw implements zzafe {
    final /* synthetic */ zzaij zza;
    final /* synthetic */ zzadx zzb;
    final /* synthetic */ zzabz zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaaw(zzabz zzabzVar, zzaij zzaijVar, zzadx zzadxVar) {
        this.zzc = zzabzVar;
        this.zza = zzaijVar;
        this.zzb = zzadxVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzaff zzaffVar;
        this.zza.zzd(((zzahb) obj).zze());
        zzaffVar = this.zzc.zza;
        zzaffVar.zzv(this.zza, new zzaav(this, this));
    }
}
