package com.google.android.gms.internal.p000firebaseauthapi;

import java.math.BigInteger;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmp  reason: invalid package */
/* loaded from: classes.dex */
final class zzmp {
    static final zzmp zza = new zzmp(BigInteger.ONE, BigInteger.ONE, BigInteger.ZERO);
    final BigInteger zzb;
    final BigInteger zzc;
    final BigInteger zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzmp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.zzb = bigInteger;
        this.zzc = bigInteger2;
        this.zzd = bigInteger3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza() {
        return this.zzd.equals(BigInteger.ZERO);
    }
}
