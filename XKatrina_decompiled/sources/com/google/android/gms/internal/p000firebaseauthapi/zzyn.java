package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyn  reason: invalid package */
/* loaded from: classes.dex */
public final class zzyn implements zzbd {
    private final zzzf zza;
    private final zzcd zzb;
    private final int zzc;

    public zzyn(zzzf zzzfVar, zzcd zzcdVar, int i) {
        this.zza = zzzfVar;
        this.zzb = zzcdVar;
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbd
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.zzc;
        if (length < i) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, length - i);
        this.zzb.zza(Arrays.copyOfRange(bArr, length - this.zzc, length), zzyf.zzb(bArr2, copyOfRange, Arrays.copyOf(ByteBuffer.allocate(8).putLong(0L).array(), 8)));
        return this.zza.zza(copyOfRange);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbd
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        throw null;
    }
}
