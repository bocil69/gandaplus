package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaai  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaai implements zzafe {
    final /* synthetic */ EmailAuthCredential zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzadx zzc;
    final /* synthetic */ zzabz zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaai(zzabz zzabzVar, EmailAuthCredential emailAuthCredential, String str, zzadx zzadxVar) {
        this.zzd = zzabzVar;
        this.zza = emailAuthCredential;
        this.zzb = str;
        this.zzc = zzadxVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zzc.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        this.zzd.zzQ(new zzage(this.zza, ((zzahb) obj).zze(), this.zzb), this.zzc);
    }
}
