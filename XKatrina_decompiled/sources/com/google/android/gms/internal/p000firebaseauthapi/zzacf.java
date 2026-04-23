package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzan;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacf  reason: invalid package */
/* loaded from: classes.dex */
public final class zzacf extends zzaez {
    public zzacf() {
        super(5);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final String zza() {
        return "delete";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaez
    public final void zzb() {
        ((zzan) this.zzi).zza();
        zzm(null);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final void zzc(TaskCompletionSource taskCompletionSource, zzady zzadyVar) {
        this.zzk = new zzaey(this, taskCompletionSource);
        zzadyVar.zzg(this.zzh.zzf(), this.zzf);
    }
}
