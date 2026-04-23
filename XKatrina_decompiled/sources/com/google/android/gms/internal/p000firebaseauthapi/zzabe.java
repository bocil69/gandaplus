package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.firebase.auth.internal.zzai;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabe  reason: invalid package */
/* loaded from: classes.dex */
public final class zzabe implements zzafe {
    final /* synthetic */ zzahr zza;
    final /* synthetic */ zzadx zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzabe(zzabz zzabzVar, zzahr zzahrVar, zzadx zzadxVar) {
        this.zza = zzahrVar;
        this.zzb = zzadxVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafd
    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafe
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzahr zzahrVar = this.zza;
        zzahs zzahsVar = (zzahs) obj;
        if (zzahrVar instanceof zzahv) {
            this.zzb.zzb(zzahsVar.zzc());
        } else if (zzahrVar instanceof zzahx) {
            this.zzb.zzp(zzahsVar);
        } else {
            String name = zzahrVar.getClass().getName();
            throw new IllegalArgumentException("startMfaEnrollmentRequest must be an instance of either StartPhoneMfaEnrollmentRequest or StartTotpMfaEnrollmentRequest but was " + name + ".");
        }
    }
}
