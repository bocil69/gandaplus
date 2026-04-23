package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class TotpMultiFactorGenerator {
    public static final String FACTOR_ID = "totp";

    private TotpMultiFactorGenerator() {
    }

    public static Task<TotpSecret> generateSecret(MultiFactorSession multiFactorSession) {
        Preconditions.checkNotNull(multiFactorSession);
        com.google.firebase.auth.internal.zzag zzagVar = (com.google.firebase.auth.internal.zzag) multiFactorSession;
        return FirebaseAuth.getInstance(zzagVar.zza().zza()).zzn(zzagVar);
    }

    public static TotpMultiFactorAssertion getAssertionForEnrollment(TotpSecret totpSecret, String str) {
        return new TotpMultiFactorAssertion((String) Preconditions.checkNotNull(str), (TotpSecret) Preconditions.checkNotNull(totpSecret), null);
    }

    public static TotpMultiFactorAssertion getAssertionForSignIn(String str, String str2) {
        return new TotpMultiFactorAssertion((String) Preconditions.checkNotNull(str2), null, (String) Preconditions.checkNotNull(str));
    }
}
