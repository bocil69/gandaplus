package com.google.android.gms.internal.p000firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.util.Arrays;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjy  reason: invalid package */
/* loaded from: classes.dex */
public final class zzjy extends zzlh {
    private final zzkg zza;
    @Nullable
    private final zzzp zzb;
    @Nullable
    private final zzzq zzc;

    private zzjy(zzkg zzkgVar, @Nullable zzzp zzzpVar, @Nullable zzzq zzzqVar) {
        this.zza = zzkgVar;
        this.zzb = zzzpVar;
        this.zzc = zzzqVar;
    }

    public static zzjy zza(zzkg zzkgVar, zzzq zzzqVar) throws GeneralSecurityException {
        if (zzkgVar.zzd() == null) {
            throw new GeneralSecurityException("ECIES private key for X25519 curve cannot be constructed with NIST-curve public key");
        }
        byte[] zzc = zzzqVar.zzc(zzbm.zza());
        byte[] zzc2 = zzkgVar.zzd().zzc();
        if (zzc.length != 32) {
            throw new GeneralSecurityException("Private key bytes length for X25519 curve must be 32");
        }
        if (Arrays.equals(zzzm.zzb(zzc), zzc2)) {
            return new zzjy(zzkgVar, null, zzzqVar);
        }
        throw new GeneralSecurityException("Invalid private key for public key.");
    }

    public static zzjy zzb(zzkg zzkgVar, zzzp zzzpVar) throws GeneralSecurityException {
        if (zzkgVar.zze() == null) {
            throw new GeneralSecurityException("ECIES private key for NIST curve cannot be constructed with X25519-curve public key");
        }
        BigInteger zzb = zzzpVar.zzb(zzbm.zza());
        ECPoint zze = zzkgVar.zze();
        zzjs zzc = zzkgVar.zza().zzc();
        BigInteger order = zzc(zzc).getOrder();
        if (zzb.signum() <= 0 || zzb.compareTo(order) >= 0) {
            throw new GeneralSecurityException("Invalid private value");
        }
        if (zzmq.zze(zzb, zzc(zzc)).equals(zze)) {
            return new zzjy(zzkgVar, zzzpVar, null);
        }
        throw new GeneralSecurityException("Invalid private value");
    }

    private static ECParameterSpec zzc(zzjs zzjsVar) {
        if (zzjsVar == zzjs.zza) {
            return zzmq.zza;
        }
        if (zzjsVar == zzjs.zzb) {
            return zzmq.zzb;
        }
        if (zzjsVar != zzjs.zzc) {
            throw new IllegalArgumentException("Unable to determine NIST curve type for ".concat(String.valueOf(String.valueOf(zzjsVar))));
        }
        return zzmq.zzc;
    }
}
