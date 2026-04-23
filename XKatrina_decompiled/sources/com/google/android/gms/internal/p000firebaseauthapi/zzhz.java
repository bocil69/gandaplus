package com.google.android.gms.internal.p000firebaseauthapi;

import android.os.Build;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhz  reason: invalid package */
/* loaded from: classes.dex */
public final class zzhz {
    private static final ThreadLocal zza = new zzhy();
    private final SecretKey zzb;
    private final boolean zzc;

    public zzhz(byte[] bArr, boolean z) throws GeneralSecurityException {
        if (!zzij.zza(2)) {
            throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
        }
        zzzl.zzb(bArr.length);
        this.zzb = new SecretKeySpec(bArr, "AES");
        this.zzc = z;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        AlgorithmParameterSpec gCMParameterSpec;
        if (bArr.length == 12) {
            boolean z = this.zzc;
            int i = true != z ? 16 : 28;
            int length = bArr2.length;
            if (length < i) {
                throw new GeneralSecurityException("ciphertext too short");
            }
            if (!z || ByteBuffer.wrap(bArr).equals(ByteBuffer.wrap(bArr2, 0, 12))) {
                String property = System.getProperty("java.vendor");
                Integer num = null;
                if (property == "The Android Project" || (property != null && property.equals("The Android Project"))) {
                    num = Integer.valueOf(Build.VERSION.SDK_INT);
                }
                if (num == null || num.intValue() > 19) {
                    gCMParameterSpec = new GCMParameterSpec(128, bArr, 0, 12);
                } else {
                    gCMParameterSpec = new IvParameterSpec(bArr, 0, 12);
                }
                ThreadLocal threadLocal = zza;
                ((Cipher) threadLocal.get()).init(2, this.zzb, gCMParameterSpec);
                boolean z2 = this.zzc;
                int i2 = true != z2 ? 0 : 12;
                if (z2) {
                    length -= 12;
                }
                return ((Cipher) threadLocal.get()).doFinal(bArr2, i2, length);
            }
            throw new GeneralSecurityException("iv does not match prepended iv");
        }
        throw new GeneralSecurityException("iv is wrong size");
    }
}
