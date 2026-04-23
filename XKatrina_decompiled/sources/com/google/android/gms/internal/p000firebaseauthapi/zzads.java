package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.ActionCodeSettings;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzads  reason: invalid package */
/* loaded from: classes.dex */
public final class zzads extends zzaez {
    private final zzaad zza;

    public zzads(String str, String str2, ActionCodeSettings actionCodeSettings) {
        super(6);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(actionCodeSettings);
        this.zza = new zzaad(str, str2, actionCodeSettings);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final String zza() {
        return "verifyBeforeUpdateEmail";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaez
    public final void zzb() {
        zzm(null);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzafb
    public final void zzc(TaskCompletionSource taskCompletionSource, zzady zzadyVar) {
        this.zzk = new zzaey(this, taskCompletionSource);
        zzadyVar.zzI(this.zza, this.zzf);
    }
}
