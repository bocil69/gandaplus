package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabi  reason: invalid package */
/* loaded from: classes.dex */
public final class zzabi implements zzafe {
    final /* synthetic */ zzadx zza;
    final /* synthetic */ zzabz zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzabi(zzabz zzabzVar, zzadx zzadxVar) {
        this.zzb = zzabzVar;
        this.zza = zzadxVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zza.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzagj zzagjVar = (zzagj) obj;
        this.zzb.zzR(new zzahb(zzagjVar.zzc(), zzagjVar.zzb(), Long.valueOf(zzahd.zza(zzagjVar.zzb())), "Bearer"), null, null, false, null, this.zza, this);
    }
}
