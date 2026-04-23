package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzag;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadj  reason: invalid package */
/* loaded from: classes.dex */
public final class zzadj extends zzaez {
    private final zzahx zza;

    public zzadj(zzag zzagVar, String str) {
        super(12);
        Preconditions.checkNotNull(zzagVar);
        this.zza = zzahx.zzb(Preconditions.checkNotEmpty(zzagVar.zzd()), str);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final String zza() {
        return "startMfaEnrollment";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaez
    public final void zzb() {
        zzm(this.zzz);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final void zzc(TaskCompletionSource taskCompletionSource, zzady zzadyVar) {
        this.zzk = new zzaey(this, taskCompletionSource);
        zzadyVar.zzD(this.zza, this.zzf);
    }
}
