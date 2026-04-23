package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlx  reason: invalid package */
/* loaded from: classes.dex */
final class zzlx extends zznf {
    final /* synthetic */ zzly zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzlx(zzly zzlyVar, Class cls) {
        super(cls);
        this.zza = zzlyVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ zzalp zza(zzalp zzalpVar) throws GeneralSecurityException {
        byte[] zzb;
        byte[] zzb2;
        zzvu zzvuVar = (zzvu) zzalpVar;
        zzvr zzc = zzvuVar.zzd().zzc();
        zzvr zzvrVar = zzvr.KEM_UNKNOWN;
        int ordinal = zzc.ordinal();
        if (ordinal == 1) {
            zzb = zzor.zzb(32);
            zzb[0] = (byte) (zzb[0] | 7);
            int i = zzb[31] & 63;
            zzb[31] = (byte) i;
            zzb[31] = (byte) (i | 128);
            zzb2 = zzzm.zzb(zzb);
        } else if (ordinal == 2 || ordinal == 3 || ordinal == 4) {
            int zzh = zzmb.zzh(zzvuVar.zzd().zzc());
            KeyPair zzc2 = zzym.zzc(zzym.zzi(zzh));
            ECPoint w = ((ECPublicKey) zzc2.getPublic()).getW();
            EllipticCurve curve = zzym.zzi(zzh).getCurve();
            zzmq.zzf(w, curve);
            int zza = zzym.zza(curve);
            int i2 = zza + zza + 1;
            zzb2 = new byte[i2];
            byte[] zzb3 = zzmn.zzb(w.getAffineX());
            byte[] zzb4 = zzmn.zzb(w.getAffineY());
            int length = zzb4.length;
            System.arraycopy(zzb4, 0, zzb2, i2 - length, length);
            int length2 = zzb3.length;
            System.arraycopy(zzb3, 0, zzb2, (zza + 1) - length2, length2);
            zzb2[0] = 4;
            zzb = zzmn.zzc(((ECPrivateKey) zzc2.getPrivate()).getS(), zzmb.zza(zzc));
        } else {
            throw new GeneralSecurityException("Invalid KEM");
        }
        zzwc zzc3 = zzwd.zzc();
        zzc3.zzc(0);
        zzc3.zza(zzvuVar.zzd());
        zzc3.zzb(zzajf.zzn(zzb2, 0, zzb2.length));
        zzvz zzb5 = zzwa.zzb();
        zzb5.zzc(0);
        zzb5.zzb((zzwd) zzc3.zzi());
        zzb5.zza(zzajf.zzn(zzb, 0, zzb.length));
        return (zzwa) zzb5.zzi();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ zzalp zzb(zzajf zzajfVar) throws zzaks {
        return zzvu.zzc(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        zzkl zzc = zzkq.zzc();
        zzc.zzd(zzko.zza);
        zzc.zzc(zzkn.zzf);
        zzc.zzb(zzkm.zza);
        zzc.zza(zzkh.zza);
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", zzc.zze());
        zzkl zzc2 = zzkq.zzc();
        zzc2.zzd(zzko.zzc);
        zzc2.zzc(zzkn.zzf);
        zzc2.zzb(zzkm.zza);
        zzc2.zza(zzkh.zza);
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", zzc2.zze());
        zzkl zzc3 = zzkq.zzc();
        zzc3.zzd(zzko.zza);
        zzc3.zzc(zzkn.zzf);
        zzc3.zzb(zzkm.zza);
        zzc3.zza(zzkh.zzb);
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", zzc3.zze());
        zzkl zzc4 = zzkq.zzc();
        zzc4.zzd(zzko.zzc);
        zzc4.zzc(zzkn.zzf);
        zzc4.zzb(zzkm.zza);
        zzc4.zza(zzkh.zzb);
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", zzc4.zze());
        zzkl zzc5 = zzkq.zzc();
        zzc5.zzd(zzko.zza);
        zzc5.zzc(zzkn.zzf);
        zzc5.zzb(zzkm.zza);
        zzc5.zza(zzkh.zzc);
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305", zzc5.zze());
        zzkl zzc6 = zzkq.zzc();
        zzc6.zzd(zzko.zzc);
        zzc6.zzc(zzkn.zzf);
        zzc6.zzb(zzkm.zza);
        zzc6.zza(zzkh.zzc);
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305_RAW", zzc6.zze());
        zzkl zzc7 = zzkq.zzc();
        zzc7.zzd(zzko.zza);
        zzc7.zzc(zzkn.zza);
        zzc7.zzb(zzkm.zza);
        zzc7.zza(zzkh.zza);
        hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", zzc7.zze());
        zzkl zzc8 = zzkq.zzc();
        zzc8.zzd(zzko.zzc);
        zzc8.zzc(zzkn.zza);
        zzc8.zzb(zzkm.zza);
        zzc8.zza(zzkh.zza);
        hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", zzc8.zze());
        zzkl zzc9 = zzkq.zzc();
        zzc9.zzd(zzko.zza);
        zzc9.zzc(zzkn.zza);
        zzc9.zzb(zzkm.zza);
        zzc9.zza(zzkh.zzb);
        hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", zzc9.zze());
        zzkl zzc10 = zzkq.zzc();
        zzc10.zzd(zzko.zzc);
        zzc10.zzc(zzkn.zza);
        zzc10.zzb(zzkm.zza);
        zzc10.zza(zzkh.zzb);
        hashMap.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", zzc10.zze());
        zzkl zzc11 = zzkq.zzc();
        zzc11.zzd(zzko.zza);
        zzc11.zzc(zzkn.zzb);
        zzc11.zzb(zzkm.zzb);
        zzc11.zza(zzkh.zza);
        hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM", zzc11.zze());
        zzkl zzc12 = zzkq.zzc();
        zzc12.zzd(zzko.zzc);
        zzc12.zzc(zzkn.zzb);
        zzc12.zzb(zzkm.zzb);
        zzc12.zza(zzkh.zza);
        hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM_RAW", zzc12.zze());
        zzkl zzc13 = zzkq.zzc();
        zzc13.zzd(zzko.zza);
        zzc13.zzc(zzkn.zzb);
        zzc13.zzb(zzkm.zzb);
        zzc13.zza(zzkh.zzb);
        hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM", zzc13.zze());
        zzkl zzc14 = zzkq.zzc();
        zzc14.zzd(zzko.zzc);
        zzc14.zzc(zzkn.zzb);
        zzc14.zzb(zzkm.zzb);
        zzc14.zza(zzkh.zzb);
        hashMap.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM_RAW", zzc14.zze());
        zzkl zzc15 = zzkq.zzc();
        zzc15.zzd(zzko.zza);
        zzc15.zzc(zzkn.zzc);
        zzc15.zzb(zzkm.zzc);
        zzc15.zza(zzkh.zza);
        hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM", zzc15.zze());
        zzkl zzc16 = zzkq.zzc();
        zzc16.zzd(zzko.zzc);
        zzc16.zzc(zzkn.zzc);
        zzc16.zzb(zzkm.zzc);
        zzc16.zza(zzkh.zza);
        hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM_RAW", zzc16.zze());
        zzkl zzc17 = zzkq.zzc();
        zzc17.zzd(zzko.zza);
        zzc17.zzc(zzkn.zzc);
        zzc17.zzb(zzkm.zzc);
        zzc17.zza(zzkh.zzb);
        hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM", zzc17.zze());
        zzkl zzc18 = zzkq.zzc();
        zzc18.zzd(zzko.zzc);
        zzc18.zzc(zzkn.zzc);
        zzc18.zzb(zzkm.zzc);
        zzc18.zza(zzkh.zzb);
        hashMap.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM_RAW", zzc18.zze());
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ void zzd(zzalp zzalpVar) throws GeneralSecurityException {
        zzmb.zzb(((zzvu) zzalpVar).zzd());
    }
}
