package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabd  reason: invalid package */
/* loaded from: classes.dex */
public final class zzabd implements zzafe {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzadx zzc;
    final /* synthetic */ zzabz zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzabd(zzabz zzabzVar, String str, String str2, zzadx zzadxVar) {
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
        zzaff zzaffVar;
        zzail zzailVar = new zzail(((zzahb) obj).zze(), this.zza, this.zzb);
        zzaffVar = this.zzd.zza;
        zzaffVar.zzw(zzailVar, new zzabc(this));
    }
}
