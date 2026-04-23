package com.google.firebase.auth;

import android.util.Log;
import com.google.android.gms.internal.p000firebaseauthapi.zzadz;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthProvider;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzm extends PhoneAuthProvider.OnVerificationStateChangedCallbacks {
    final /* synthetic */ PhoneAuthOptions zza;
    final /* synthetic */ PhoneAuthProvider.OnVerificationStateChangedCallbacks zzb;
    final /* synthetic */ FirebaseAuth zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzm(FirebaseAuth firebaseAuth, PhoneAuthOptions phoneAuthOptions, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
        this.zzc = firebaseAuth;
        this.zza = phoneAuthOptions;
        this.zzb = onVerificationStateChangedCallbacks;
    }

    @Override // com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
    public final void onCodeAutoRetrievalTimeOut(String str) {
        this.zzb.onCodeAutoRetrievalTimeOut(str);
    }

    @Override // com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
    public final void onCodeSent(String str, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
        this.zzb.onCodeSent(str, forceResendingToken);
    }

    @Override // com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
    public final void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
        this.zzb.onVerificationCompleted(phoneAuthCredential);
    }

    @Override // com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
    public final void onVerificationFailed(FirebaseException firebaseException) {
        int i = zzadz.zzb;
        if (!(firebaseException instanceof FirebaseAuthException) || !((FirebaseAuthException) firebaseException).getErrorCode().endsWith("ALTERNATE_CLIENT_IDENTIFIER_REQUIRED")) {
            String zzh = this.zza.zzh();
            String message = firebaseException.getMessage();
            Log.d("FirebaseAuth", "Invoking original failure callbacks after phone verification failure for " + zzh + ", error - " + message);
            this.zzb.onVerificationFailed(firebaseException);
            return;
        }
        this.zza.zzj(true);
        Log.d("FirebaseAuth", "Re-triggering phone verification with Recaptcha flow forced for phone number ".concat(String.valueOf(this.zza.zzh())));
        this.zzc.zzU(this.zza);
    }
}
