package com.google.android.gms.internal.p000firebaseauthapi;

import android.text.TextUtils;
import com.google.firebase.auth.internal.zzai;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabc  reason: invalid package */
/* loaded from: classes.dex */
final class zzabc implements zzafe {
    final /* synthetic */ zzabd zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzabc(zzabd zzabdVar) {
        this.zza = zzabdVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zza.zzc.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzaim zzaimVar = (zzaim) obj;
        if (TextUtils.isEmpty(zzaimVar.zzb()) || TextUtils.isEmpty(zzaimVar.zzc())) {
            this.zza.zzc.zzh(zzai.zza("INTERNAL_SUCCESS_SIGN_OUT"));
            return;
        }
        zzahb zzahbVar = new zzahb(zzaimVar.zzc(), zzaimVar.zzb(), Long.valueOf(zzahd.zza(zzaimVar.zzb())), "Bearer");
        zzabd zzabdVar = this.zza;
        zzabdVar.zzd.zzR(zzahbVar, null, null, false, null, zzabdVar.zzc, this);
    }
}
