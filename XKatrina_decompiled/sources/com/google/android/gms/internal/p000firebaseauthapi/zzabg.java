package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabg  reason: invalid package */
/* loaded from: classes.dex */
final class zzabg implements zzafe {
    final /* synthetic */ zzabh zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzabg(zzabh zzabhVar) {
        this.zza = zzabhVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zza.zzb.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzagh zzaghVar = (zzagh) obj;
        this.zza.zzc.zzR(new zzahb(zzaghVar.zzc(), zzaghVar.zzb(), Long.valueOf(zzahd.zza(zzaghVar.zzb())), "Bearer"), null, null, false, null, this.zza.zzb, this);
    }
}
