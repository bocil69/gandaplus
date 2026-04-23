package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaba  reason: invalid package */
/* loaded from: classes.dex */
final class zzaba implements zzafe {
    final /* synthetic */ zzafe zza;
    final /* synthetic */ zzahb zzb;
    final /* synthetic */ zzabb zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaba(zzabb zzabbVar, zzafe zzafeVar, zzahb zzahbVar) {
        this.zzc = zzabbVar;
        this.zza = zzafeVar;
        this.zzb = zzahbVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zzc.zzb.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        List zzb = ((zzagr) obj).zzb();
        if (zzb == null || zzb.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        zzags zzagsVar = (zzags) zzb.get(0);
        zzahn zzahnVar = new zzahn();
        zzahnVar.zze(this.zzb.zze());
        zzahnVar.zzb(this.zzc.zza);
        zzabb zzabbVar = this.zzc;
        zzabz.zzf(zzabbVar.zzc, zzabbVar.zzb, this.zzb, zzagsVar, zzahnVar, this.zza);
    }
}
