package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacl  reason: invalid package */
/* loaded from: classes.dex */
public final class zzacl extends zzaez {
    private final zzzu zza;

    public zzacl(String str, String str2) {
        super(10);
        Preconditions.checkNotEmpty("RECAPTCHA_ENTERPRISE");
        this.zza = new zzzu(str, "RECAPTCHA_ENTERPRISE");
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final String zza() {
        return "getRecaptchaConfig";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaez
    public final void zzb() {
        zzm(this.zzx);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final void zzc(TaskCompletionSource taskCompletionSource, zzady zzadyVar) {
        this.zzk = new zzaey(this, taskCompletionSource);
        zzadyVar.zzm(this.zza, this.zzf);
    }
}
