package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzr;
import com.google.firebase.auth.internal.zzx;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadg  reason: invalid package */
/* loaded from: classes.dex */
public final class zzadg extends zzaez {
    private final zzaaa zza;

    public zzadg(EmailAuthCredential emailAuthCredential, String str) {
        super(2);
        Preconditions.checkNotNull(emailAuthCredential, "credential cannot be null");
        this.zza = new zzaaa(emailAuthCredential, str);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final String zza() {
        return "sendSignInLinkToEmail";
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
        zzadyVar.zzz(this.zza, this.zzf);
    }
}
