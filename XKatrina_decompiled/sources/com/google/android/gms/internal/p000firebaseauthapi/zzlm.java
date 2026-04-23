package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlm  reason: invalid package */
/* loaded from: classes.dex */
final class zzlm implements zzlo {
    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzlo
    public final int zza() {
        return 32;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzlo
    public final byte[] zzb() {
        return zzmb.zzl;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzlo
    public final byte[] zzc(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws GeneralSecurityException {
        if (bArr.length != 32) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: 32");
        }
        return new zzic(bArr).zzc(bArr2, bArr3, bArr4);
    }
}
