package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
import java.util.Objects;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzo  reason: invalid package */
/* loaded from: classes.dex */
public final class zzzo {
    private final byte[] zza;

    private zzzo(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.zza = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, i2);
    }

    public static zzzo zzb(byte[] bArr) {
        Objects.requireNonNull(bArr, "data must be non-null");
        return new zzzo(bArr, 0, bArr.length);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzzo) {
            return Arrays.equals(((zzzo) obj).zza, this.zza);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final String toString() {
        String zza = zzze.zza(this.zza);
        return "Bytes(" + zza + ")";
    }

    public final int zza() {
        return this.zza.length;
    }

    public final byte[] zzc() {
        byte[] bArr = this.zza;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }
}
