package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.InvalidKeyException;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzm  reason: invalid package */
/* loaded from: classes.dex */
public final class zzzm {
    public static byte[] zza(byte[] bArr, byte[] bArr2) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        long[] jArr = new long[11];
        byte[] copyOf = Arrays.copyOf(bArr, 32);
        copyOf[0] = (byte) (copyOf[0] & 248);
        int i = copyOf[31] & Byte.MAX_VALUE;
        copyOf[31] = (byte) i;
        copyOf[31] = (byte) (i | 64);
        zzmo.zza(jArr, copyOf, bArr2);
        return zzmv.zzj(jArr);
    }

    public static byte[] zzb(byte[] bArr) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        byte[] bArr2 = new byte[32];
        bArr2[0] = 9;
        return zza(bArr, bArr2);
    }
}
