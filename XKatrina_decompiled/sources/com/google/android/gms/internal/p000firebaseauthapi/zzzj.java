package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzj  reason: invalid package */
/* loaded from: classes.dex */
public final class zzzj implements zzcd {
    private static final byte[] zza = {0};
    private final zzrw zzb;
    private final int zzc;
    private final byte[] zzd;
    private final byte[] zze;

    private zzzj(zzph zzphVar) throws GeneralSecurityException {
        this.zzb = new zzzg(zzphVar.zzd().zzc(zzbm.zza()));
        this.zzc = zzphVar.zza().zzb();
        this.zzd = zzphVar.zzc().zzc();
        if (zzphVar.zza().zze().equals(zzpp.zzc)) {
            this.zze = Arrays.copyOf(zza, 1);
        } else {
            this.zze = new byte[0];
        }
    }

    public static zzcd zzb(zzph zzphVar) throws GeneralSecurityException {
        return new zzzj(zzphVar);
    }

    public static zzcd zzc(zzqe zzqeVar) throws GeneralSecurityException {
        return new zzzj(zzqeVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzcd
    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zze;
        if (!MessageDigest.isEqual(bArr3.length > 0 ? zzyf.zzb(this.zzd, this.zzb.zza(zzyf.zzb(bArr2, bArr3), this.zzc)) : zzyf.zzb(this.zzd, this.zzb.zza(bArr2, this.zzc)), bArr)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    private zzzj(zzqe zzqeVar) throws GeneralSecurityException {
        String valueOf = String.valueOf(String.valueOf(zzqeVar.zzd().zzf()));
        this.zzb = new zzzi("HMAC".concat(valueOf), new SecretKeySpec(zzqeVar.zze().zzc(zzbm.zza()), "HMAC"));
        this.zzc = zzqeVar.zzd().zzb();
        this.zzd = zzqeVar.zzc().zzc();
        if (zzqeVar.zzd().zzg().equals(zzqn.zzc)) {
            this.zze = Arrays.copyOf(zza, 1);
        } else {
            this.zze = new byte[0];
        }
    }

    public zzzj(zzrw zzrwVar, int i) throws GeneralSecurityException {
        this.zzb = zzrwVar;
        this.zzc = i;
        this.zzd = new byte[0];
        this.zze = new byte[0];
        if (i < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
        }
        zzrwVar.zza(new byte[0], i);
    }
}
