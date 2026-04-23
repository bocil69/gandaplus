package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.InvalidKeyException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzia  reason: invalid package */
/* loaded from: classes.dex */
public final class zzia extends zzib {
    public zzia(byte[] bArr, int i) throws InvalidKeyException {
        super(bArr, i);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzib
    public final int zza() {
        return 12;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzib
    public final int[] zzb(int[] iArr, int i) {
        int length = iArr.length;
        if (length != 3) {
            throw new IllegalArgumentException(String.format("ChaCha20 uses 96-bit nonces, but got a %d-bit nonce", Integer.valueOf(length * 32)));
        }
        int[] iArr2 = new int[16];
        zzhx.zzb(iArr2, this.zza);
        iArr2[12] = i;
        System.arraycopy(iArr, 0, iArr2, 13, 3);
        return iArr2;
    }
}
