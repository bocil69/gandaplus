package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaaz  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaaz implements zzafe {
    final /* synthetic */ zzadx zza;
    final /* synthetic */ zzabz zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaaz(zzabz zzabzVar, zzadx zzadxVar) {
        this.zzb = zzabzVar;
        this.zza = zzadxVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzahb zzahbVar = (zzahb) obj;
        zzahn zzahnVar = new zzahn();
        zzahnVar.zze(zzahbVar.zze());
        zzahnVar.zzd(null);
        zzahnVar.zzg(null);
        zzabz.zze(this.zzb, this.zza, zzahbVar, zzahnVar, this);
    }
}
