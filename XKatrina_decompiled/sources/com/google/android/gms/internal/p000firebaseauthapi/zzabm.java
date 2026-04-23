package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabm  reason: invalid package */
/* loaded from: classes.dex */
final class zzabm implements zzafe {
    final /* synthetic */ zzafe zza;
    final /* synthetic */ zzahb zzb;
    final /* synthetic */ zzabn zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzabm(zzabn zzabnVar, zzafe zzafeVar, zzahb zzahbVar) {
        this.zzc = zzabnVar;
        this.zza = zzafeVar;
        this.zzb = zzahbVar;
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
            return;
        }
        this.zzc.zza.zzk(this.zzb, (zzags) zzb.get(0));
    }
}
