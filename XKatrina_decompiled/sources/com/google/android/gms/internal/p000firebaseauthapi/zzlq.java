package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlq  reason: invalid package */
/* loaded from: classes.dex */
final class zzlq implements zzbk {
    private static final byte[] zza = new byte[0];
    private final zzlu zzb;
    private final zzlt zzc;
    private final zzls zzd;
    private final zzlo zze;
    private final int zzf;

    private zzlq(zzlu zzluVar, zzlt zzltVar, zzls zzlsVar, zzlo zzloVar, int i) {
        this.zzb = zzluVar;
        this.zzc = zzltVar;
        this.zzd = zzlsVar;
        this.zze = zzloVar;
        this.zzf = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzlq zzb(zzwa zzwaVar) throws GeneralSecurityException {
        int i;
        zzlu zzc;
        if (!zzwaVar.zzk()) {
            throw new IllegalArgumentException("HpkePrivateKey is missing public_key field.");
        }
        if (!zzwaVar.zze().zzl()) {
            throw new IllegalArgumentException("HpkePrivateKey.public_key is missing params field.");
        }
        if (zzwaVar.zzf().zzp()) {
            throw new IllegalArgumentException("HpkePrivateKey.private_key is empty.");
        }
        zzvx zzb = zzwaVar.zze().zzb();
        zzlt zzc2 = zzlv.zzc(zzb);
        zzls zzb2 = zzlv.zzb(zzb);
        zzlo zza2 = zzlv.zza(zzb);
        zzvr zzc3 = zzb.zzc();
        zzvr zzvrVar = zzvr.KEM_UNKNOWN;
        int ordinal = zzc3.ordinal();
        if (ordinal == 1) {
            i = 32;
        } else if (ordinal == 2) {
            i = 65;
        } else if (ordinal == 3) {
            i = 97;
        } else if (ordinal != 4) {
            throw new IllegalArgumentException("Unable to determine KEM-encoding length for ".concat(String.valueOf(zzc3.name())));
        } else {
            i = 133;
        }
        int ordinal2 = zzwaVar.zze().zzb().zzc().ordinal();
        if (ordinal2 == 1) {
            zzc = zzmf.zzc(zzwaVar.zzf().zzq());
        } else if (ordinal2 == 2 || ordinal2 == 3 || ordinal2 == 4) {
            zzc = zzmd.zzc(zzwaVar.zzf().zzq(), zzwaVar.zze().zzg().zzq(), zzmb.zzh(zzwaVar.zze().zzb().zzc()));
        } else {
            throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
        }
        return new zzlq(zzc, zzc2, zzb2, zza2, i);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbk
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.zzf;
        if (length < i) {
            throw new GeneralSecurityException("Ciphertext is too short.");
        }
        byte[] copyOf = Arrays.copyOf(bArr, i);
        byte[] copyOfRange = Arrays.copyOfRange(bArr, this.zzf, length);
        zzlu zzluVar = this.zzb;
        zzlt zzltVar = this.zzc;
        zzls zzlsVar = this.zzd;
        zzlo zzloVar = this.zze;
        byte[] zza2 = zzltVar.zza(copyOf, zzluVar);
        return zzlp.zza(zzmb.zza, copyOf, zza2, zzltVar, zzlsVar, zzloVar, new byte[0]).zzb(copyOfRange, zza);
    }
}
