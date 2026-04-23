package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmb  reason: invalid package */
/* loaded from: classes.dex */
public final class zzmb {
    public static final byte[] zza = zzd(1, 0);
    public static final byte[] zzb = zzd(1, 2);
    public static final byte[] zzc = zzd(2, 32);
    public static final byte[] zzd = zzd(2, 16);
    public static final byte[] zze = zzd(2, 17);
    public static final byte[] zzf = zzd(2, 18);
    public static final byte[] zzg = zzd(2, 1);
    public static final byte[] zzh = zzd(2, 2);
    public static final byte[] zzi = zzd(2, 3);
    public static final byte[] zzj = zzd(2, 1);
    public static final byte[] zzk = zzd(2, 2);
    public static final byte[] zzl = zzd(2, 3);
    public static final byte[] zzm = new byte[0];
    private static final byte[] zzn = "KEM".getBytes(zzpd.zza);
    private static final byte[] zzo = "HPKE".getBytes(zzpd.zza);
    private static final byte[] zzp = "HPKE-v1".getBytes(zzpd.zza);

    public static int zza(zzvr zzvrVar) throws GeneralSecurityException {
        zzvr zzvrVar2 = zzvr.KEM_UNKNOWN;
        int ordinal = zzvrVar.ordinal();
        if (ordinal == 1 || ordinal == 2) {
            return 32;
        }
        if (ordinal != 3) {
            if (ordinal == 4) {
                return 66;
            }
            throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
        }
        return 48;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(zzvx zzvxVar) throws GeneralSecurityException {
        if (zzvxVar.zzc() == zzvr.KEM_UNKNOWN || zzvxVar.zzc() == zzvr.UNRECOGNIZED) {
            throw new GeneralSecurityException("Invalid KEM param: ".concat(String.valueOf(zzvxVar.zzc().name())));
        }
        if (zzvxVar.zzb() == zzvp.KDF_UNKNOWN || zzvxVar.zzb() == zzvp.UNRECOGNIZED) {
            throw new GeneralSecurityException("Invalid KDF param: ".concat(String.valueOf(zzvxVar.zzb().name())));
        }
        if (zzvxVar.zza() == zzvn.AEAD_UNKNOWN || zzvxVar.zza() == zzvn.UNRECOGNIZED) {
            throw new GeneralSecurityException("Invalid AEAD param: ".concat(String.valueOf(zzvxVar.zza().name())));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zzc(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        return zzyf.zzb(zzo, bArr, bArr2, bArr3);
    }

    public static byte[] zzd(int i, int i2) {
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) ((i2 >> (((i - i3) - 1) * 8)) & 255);
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zze(byte[] bArr) throws GeneralSecurityException {
        return zzyf.zzb(zzn, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zzf(String str, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return zzyf.zzb(zzp, bArr2, str.getBytes(zzpd.zza), bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] zzg(String str, byte[] bArr, byte[] bArr2, int i) throws GeneralSecurityException {
        return zzyf.zzb(zzd(2, i), zzp, bArr2, str.getBytes(zzpd.zza), bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(zzvr zzvrVar) throws GeneralSecurityException {
        zzvr zzvrVar2 = zzvr.KEM_UNKNOWN;
        int ordinal = zzvrVar.ordinal();
        if (ordinal != 2) {
            if (ordinal != 3) {
                if (ordinal == 4) {
                    return 3;
                }
                throw new GeneralSecurityException("Unrecognized NIST HPKE KEM identifier");
            }
            return 2;
        }
        return 1;
    }
}
