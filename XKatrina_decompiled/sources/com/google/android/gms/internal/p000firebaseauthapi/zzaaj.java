package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaaj  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaaj implements zzafe {
    final /* synthetic */ zzadx zza;
    final /* synthetic */ zzabz zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaaj(zzabz zzabzVar, zzadx zzadxVar) {
        this.zzb = zzabzVar;
        this.zza = zzadxVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzagf zzagfVar = (zzagf) obj;
        if (zzagfVar.zzg()) {
            this.zza.zzf(new zzaaf(zzagfVar.zzd(), zzagfVar.zzf(), null));
            return;
        }
        this.zzb.zzR(new zzahb(zzagfVar.zze(), zzagfVar.zzc(), Long.valueOf(zzagfVar.zzb()), "Bearer"), null, null, Boolean.valueOf(zzagfVar.zzh()), null, this.zza, this);
    }
}
