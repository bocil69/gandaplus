package com.google.android.gms.internal.p000firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECParameterSpec;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkr  reason: invalid package */
/* loaded from: classes.dex */
public final class zzkr extends zzlh {
    private final zzkz zza;
    private final zzzq zzb;

    private zzkr(zzkz zzkzVar, zzzq zzzqVar) {
        this.zza = zzkzVar;
        this.zzb = zzzqVar;
    }

    public static zzkr zza(zzkz zzkzVar, zzzq zzzqVar) throws GeneralSecurityException {
        ECParameterSpec eCParameterSpec;
        zzkn zze = zzkzVar.zza().zze();
        int zza = zzzqVar.zza();
        String str = "Encoded private key byte length for " + zze.toString() + " must be %d, not " + zza;
        if (zze == zzkn.zza) {
            if (zza != 32) {
                throw new GeneralSecurityException(String.format(str, 32));
            }
        } else if (zze == zzkn.zzb) {
            if (zza != 48) {
                throw new GeneralSecurityException(String.format(str, 48));
            }
        } else if (zze == zzkn.zzc) {
            if (zza != 66) {
                throw new GeneralSecurityException(String.format(str, 66));
            }
        } else if (zze != zzkn.zzf) {
            throw new GeneralSecurityException("Unable to validate private key length for ".concat(zze.toString()));
        } else {
            if (zza != 32) {
                throw new GeneralSecurityException(String.format(str, 32));
            }
        }
        zzkn zze2 = zzkzVar.zza().zze();
        byte[] zzc = zzkzVar.zzc().zzc();
        byte[] zzc2 = zzzqVar.zzc(zzbm.zza());
        zzkn zzknVar = zzkn.zza;
        if (zze2 != zzknVar && zze2 != zzkn.zzb && zze2 != zzkn.zzc) {
            if (zze2 != zzkn.zzf) {
                throw new IllegalArgumentException("Unable to validate key pair for ".concat(zze2.toString()));
            }
            if (!Arrays.equals(zzzm.zzb(zzc2), zzc)) {
                throw new GeneralSecurityException("Invalid private key for public key.");
            }
        } else {
            if (zze2 == zzknVar) {
                eCParameterSpec = zzmq.zza;
            } else if (zze2 == zzkn.zzb) {
                eCParameterSpec = zzmq.zzb;
            } else if (zze2 != zzkn.zzc) {
                throw new IllegalArgumentException("Unable to determine NIST curve params for ".concat(zze2.toString()));
            } else {
                eCParameterSpec = zzmq.zzc;
            }
            BigInteger order = eCParameterSpec.getOrder();
            BigInteger zza2 = zzmn.zza(zzc2);
            if (zza2.signum() <= 0 || zza2.compareTo(order) >= 0) {
                throw new GeneralSecurityException("Invalid private key.");
            }
            if (!zzmq.zze(zza2, eCParameterSpec).equals(zzym.zzj(eCParameterSpec.getCurve(), 1, zzc))) {
                throw new GeneralSecurityException("Invalid private key for public key.");
            }
        }
        return new zzkr(zzkzVar, zzzqVar);
    }
}
