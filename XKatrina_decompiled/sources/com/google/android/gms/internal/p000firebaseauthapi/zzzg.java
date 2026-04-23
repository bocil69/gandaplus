package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzg  reason: invalid package */
/* loaded from: classes.dex */
public final class zzzg implements zzrw {
    private final SecretKey zza;
    private final byte[] zzb;
    private final byte[] zzc;

    public zzzg(byte[] bArr) throws GeneralSecurityException {
        zzzl.zzb(bArr.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        this.zza = secretKeySpec;
        Cipher zzb = zzb();
        zzb.init(1, secretKeySpec);
        byte[] zzb2 = zzrd.zzb(zzb.doFinal(new byte[16]));
        this.zzb = zzb2;
        this.zzc = zzrd.zzb(zzb2);
    }

    private static Cipher zzb() throws GeneralSecurityException {
        if (!zzij.zza(1)) {
            throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
        }
        return (Cipher) zzyv.zza.zza("AES/ECB/NoPadding");
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzrw
    public final byte[] zza(byte[] bArr, int i) throws GeneralSecurityException {
        byte[] zzc;
        if (i <= 16) {
            SecretKey secretKey = this.zza;
            Cipher zzb = zzb();
            zzb.init(1, secretKey);
            int length = bArr.length;
            int max = Math.max(1, (int) Math.ceil(length / 16.0d));
            int i2 = max - 1;
            int i3 = i2 * 16;
            if (max * 16 == length) {
                zzc = zzyf.zzd(bArr, i3, this.zzb, 0, 16);
            } else {
                zzc = zzyf.zzc(zzrd.zza(Arrays.copyOfRange(bArr, i3, length)), this.zzc);
            }
            byte[] bArr2 = new byte[16];
            for (int i4 = 0; i4 < i2; i4++) {
                bArr2 = zzb.doFinal(zzyf.zzd(bArr2, 0, bArr, i4 * 16, 16));
            }
            return Arrays.copyOf(zzb.doFinal(zzyf.zzc(zzc, bArr2)), i);
        }
        throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
    }
}
