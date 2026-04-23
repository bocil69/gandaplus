package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzll  reason: invalid package */
/* loaded from: classes.dex */
final class zzll implements zzlo {
    private final int zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzll(int i) throws InvalidAlgorithmParameterException {
        if (i == 16 || i == 32) {
            this.zza = i;
            return;
        }
        throw new InvalidAlgorithmParameterException("Unsupported key length: " + i);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzlo
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzlo
    public final byte[] zzc(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws GeneralSecurityException {
        int length = bArr.length;
        if (length != this.zza) {
            throw new InvalidAlgorithmParameterException("Unexpected key length: " + length);
        }
        return new zzhz(bArr, false).zza(bArr2, bArr3, bArr4);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzlo
    public final byte[] zzb() throws GeneralSecurityException {
        int i = this.zza;
        if (i != 16) {
            if (i == 32) {
                return zzmb.zzk;
            }
            throw new GeneralSecurityException("Could not determine HPKE AEAD ID");
        }
        return zzmb.zzj;
    }
}
