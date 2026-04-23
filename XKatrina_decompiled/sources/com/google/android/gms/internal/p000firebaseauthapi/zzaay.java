package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaay  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaay implements zzafe {
    final /* synthetic */ zzaic zza;
    final /* synthetic */ zzadx zzb;
    final /* synthetic */ zzabz zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaay(zzabz zzabzVar, zzaic zzaicVar, zzadx zzadxVar) {
        this.zzc = zzabzVar;
        this.zza = zzaicVar;
        this.zzb = zzadxVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzaff zzaffVar;
        this.zza.zzd(true);
        this.zza.zzc(((zzahb) obj).zze());
        zzaffVar = this.zzc.zza;
        zzaffVar.zzs(this.zza, new zzaax(this, this));
    }
}
