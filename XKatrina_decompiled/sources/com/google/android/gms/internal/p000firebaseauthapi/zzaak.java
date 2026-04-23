package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaak  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaak implements zzafe {
    final /* synthetic */ zzafd zza;
    final /* synthetic */ zzadx zzb;
    final /* synthetic */ zzahb zzc;
    final /* synthetic */ zzahn zzd;
    final /* synthetic */ zzabz zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaak(zzabz zzabzVar, zzafd zzafdVar, zzadx zzadxVar, zzahb zzahbVar, zzahn zzahnVar) {
        this.zze = zzabzVar;
        this.zza = zzafdVar;
        this.zzb = zzadxVar;
        this.zzc = zzahbVar;
        this.zzd = zzahnVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zza.zza(str);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List zzb = ((zzagr) obj).zzb();
        if (zzb == null || zzb.isEmpty()) {
            this.zza.zza("No users");
        } else {
            zzabz.zzf(this.zze, this.zzb, this.zzc, (zzags) zzb.get(0), this.zzd, this.zza);
        }
    }
}
