package com.google.android.gms.internal.p000firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmn  reason: invalid package */
/* loaded from: classes.dex */
public final class zzmn {
    public static BigInteger zza(byte[] bArr) {
        return new BigInteger(1, bArr);
    }

    public static byte[] zzb(BigInteger bigInteger) {
        if (bigInteger.signum() == -1) {
            throw new IllegalArgumentException("n must not be negative");
        }
        return bigInteger.toByteArray();
    }

    public static byte[] zzc(BigInteger bigInteger, int i) throws GeneralSecurityException {
        if (bigInteger.signum() == -1) {
            throw new IllegalArgumentException("integer must be nonnegative");
        }
        byte[] byteArray = bigInteger.toByteArray();
        int length = byteArray.length;
        if (length == i) {
            return byteArray;
        }
        int i2 = i + 1;
        if (length <= i2) {
            if (length == i2) {
                if (byteArray[0] == 0) {
                    return Arrays.copyOfRange(byteArray, 1, length);
                }
                throw new GeneralSecurityException("integer too large");
            }
            byte[] bArr = new byte[i];
            System.arraycopy(byteArray, 0, bArr, i - length, length);
            return bArr;
        }
        throw new GeneralSecurityException("integer too large");
    }
}
