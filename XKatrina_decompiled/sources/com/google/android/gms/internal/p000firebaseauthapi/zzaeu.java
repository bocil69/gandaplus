package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthProvider;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaeu  reason: invalid package */
/* loaded from: classes.dex */
final class zzaeu implements zzaex {
    final /* synthetic */ Status zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaeu(zzaew zzaewVar, Status status) {
        this.zza = status;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaex
    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onVerificationFailed(zzadz.zza(this.zza));
    }
}
