package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.firebase.auth.PhoneAuthProvider;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaer  reason: invalid package */
/* loaded from: classes.dex */
final class zzaer implements zzaex {
    final /* synthetic */ String zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaer(zzaew zzaewVar, String str) {
        this.zza = str;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaex
    public final void zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onCodeSent(this.zza, PhoneAuthProvider.ForceResendingToken.zza());
    }
}
