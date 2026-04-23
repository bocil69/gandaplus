package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaau  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaau implements zzafe {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzadx zzc;
    final /* synthetic */ zzabz zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaau(zzabz zzabzVar, String str, String str2, zzadx zzadxVar) {
        this.zzd = zzabzVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzadxVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zzc.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzahb zzahbVar = (zzahb) obj;
        zzahn zzahnVar = new zzahn();
        zzahnVar.zze(zzahbVar.zze());
        zzahnVar.zzd(this.zza);
        zzahnVar.zzg(this.zzb);
        zzabz.zze(this.zzd, this.zzc, zzahbVar, zzahnVar, this);
    }
}
