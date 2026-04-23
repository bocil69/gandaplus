package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzr;
import com.google.firebase.auth.internal.zzx;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadh  reason: invalid package */
/* loaded from: classes.dex */
public final class zzadh extends zzaez {
    private final zzaab zza;

    public zzadh(PhoneAuthCredential phoneAuthCredential, String str) {
        super(2);
        Preconditions.checkNotNull(phoneAuthCredential);
        this.zza = new zzaab(phoneAuthCredential, str);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final String zza() {
        return "signInWithPhoneNumber";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaez
    public final void zzb() {
        zzx zzS = zzadv.zzS(this.zzg, this.zzo);
        ((zzg) this.zzi).zza(this.zzn, zzS);
        zzm(new zzr(zzS));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final void zzc(TaskCompletionSource taskCompletionSource, zzady zzadyVar) {
        this.zzk = new zzaey(this, taskCompletionSource);
        zzadyVar.zzA(this.zza, this.zzf);
    }
}
