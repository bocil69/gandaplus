package com.google.android.gms.internal.p000firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.PhoneAuthCredential;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaav  reason: invalid package */
/* loaded from: classes.dex */
final class zzaav implements zzafe {
    final /* synthetic */ zzafe zza;
    final /* synthetic */ zzaaw zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaav(zzaaw zzaawVar, zzafe zzafeVar) {
        this.zzb = zzaawVar;
        this.zza = zzafeVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zza.zza(str);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzaik zzaikVar = (zzaik) obj;
        if (!TextUtils.isEmpty(zzaikVar.zzf())) {
            Status status = new Status(FirebaseError.ERROR_CREDENTIAL_ALREADY_IN_USE);
            zzaaw zzaawVar = this.zzb;
            zzaawVar.zzb.zzg(status, PhoneAuthCredential.zzd(zzaikVar.zzd(), zzaikVar.zzf()));
            return;
        }
        zzahb zzahbVar = new zzahb(zzaikVar.zze(), zzaikVar.zzc(), Long.valueOf(zzaikVar.zzb()), "Bearer");
        zzaaw zzaawVar2 = this.zzb;
        Boolean valueOf = Boolean.valueOf(zzaikVar.zzg());
        zzaaw zzaawVar3 = this.zzb;
        zzafe zzafeVar = this.zza;
        zzaawVar2.zzc.zzR(zzahbVar, null, "phone", valueOf, null, zzaawVar3.zzb, zzafeVar);
    }
}
