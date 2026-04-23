package com.google.android.gms.internal.p000firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlp  reason: invalid package */
/* loaded from: classes.dex */
public final class zzlp {
    private static final byte[] zza = new byte[0];
    private final zzlo zzb;
    private final BigInteger zzc;
    private final byte[] zzd;
    private final byte[] zze;
    private final byte[] zzf;
    private BigInteger zzg = BigInteger.ZERO;

    private zzlp(byte[] bArr, byte[] bArr2, byte[] bArr3, BigInteger bigInteger, zzlo zzloVar) {
        this.zzf = bArr;
        this.zzd = bArr2;
        this.zze = bArr3;
        this.zzc = bigInteger;
        this.zzb = zzloVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzlp zza(byte[] bArr, byte[] bArr2, byte[] bArr3, zzlt zzltVar, zzls zzlsVar, zzlo zzloVar, byte[] bArr4) throws GeneralSecurityException {
        byte[] zzc = zzmb.zzc(zzltVar.zzb(), zzlsVar.zzc(), zzloVar.zzb());
        byte[] bArr5 = zzmb.zzm;
        byte[] bArr6 = zza;
        byte[] zzb = zzyf.zzb(bArr, zzlsVar.zze(bArr5, bArr6, "psk_id_hash", zzc), zzlsVar.zze(zzmb.zzm, bArr4, "info_hash", zzc));
        byte[] zze = zzlsVar.zze(bArr3, bArr6, "secret", zzc);
        return new zzlp(bArr2, zzlsVar.zzd(zze, zzb, "key", zzc, zzloVar.zza()), zzlsVar.zzd(zze, zzb, "base_nonce", zzc, 12), BigInteger.ONE.shiftLeft(96).subtract(BigInteger.ONE), zzloVar);
    }

    private final synchronized byte[] zzc() throws GeneralSecurityException {
        byte[] zzc;
        zzc = zzyf.zzc(this.zze, zzmn.zzc(this.zzg, 12));
        if (this.zzg.compareTo(this.zzc) >= 0) {
            throw new GeneralSecurityException("message limit reached");
        }
        this.zzg = this.zzg.add(BigInteger.ONE);
        return zzc;
    }

    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return this.zzb.zzc(this.zzd, zzc(), bArr, bArr2);
    }
}
