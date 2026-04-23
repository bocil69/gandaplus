package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.internal.zzg;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzach  reason: invalid package */
/* loaded from: classes.dex */
public final class zzach extends zzaez {
    private final MultiFactorAssertion zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;

    public zzach(MultiFactorAssertion multiFactorAssertion, String str, String str2, String str3) {
        super(2);
        this.zza = (MultiFactorAssertion) Preconditions.checkNotNull(multiFactorAssertion);
        this.zzb = Preconditions.checkNotEmpty(str);
        this.zzc = str2;
        this.zzd = str3;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final String zza() {
        return "finalizeMfaEnrollment";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaez
    public final void zzb() {
        ((zzg) this.zzi).zza(this.zzn, zzadv.zzS(this.zzg, this.zzo));
        zzm(null);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final void zzc(TaskCompletionSource taskCompletionSource, zzady zzadyVar) {
        this.zzk = new zzaey(this, taskCompletionSource);
        zzadyVar.zzh(this.zza, this.zzb, this.zzc, this.zzd, this.zzf);
    }
}
