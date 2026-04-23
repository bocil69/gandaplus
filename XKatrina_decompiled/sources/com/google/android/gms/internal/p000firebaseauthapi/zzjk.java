package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjk  reason: invalid package */
/* loaded from: classes.dex */
final class zzjk extends zznf {
    final /* synthetic */ zzjl zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzjk(zzjl zzjlVar, Class cls) {
        super(cls);
        this.zza = zzjlVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ zzalp zza(zzalp zzalpVar) throws GeneralSecurityException {
        zzuj zzujVar = (zzuj) zzalpVar;
        KeyPair zzc = zzym.zzc(zzym.zzi(zzlj.zzc(zzujVar.zzd().zzf().zzd())));
        ECPoint w = ((ECPublicKey) zzc.getPublic()).getW();
        zzur zzc2 = zzus.zzc();
        zzc2.zzb(0);
        zzc2.zza(zzujVar.zzd());
        byte[] byteArray = w.getAffineX().toByteArray();
        zzajf zzajfVar = zzajf.zzb;
        zzc2.zzc(zzajf.zzn(byteArray, 0, byteArray.length));
        byte[] byteArray2 = w.getAffineY().toByteArray();
        zzc2.zzd(zzajf.zzn(byteArray2, 0, byteArray2.length));
        zzuo zzb = zzup.zzb();
        zzb.zzc(0);
        zzb.zzb((zzus) zzc2.zzi());
        byte[] byteArray3 = ((ECPrivateKey) zzc.getPrivate()).getS().toByteArray();
        zzb.zza(zzajf.zzn(byteArray3, 0, byteArray3.length));
        return (zzup) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ zzalp zzb(zzajf zzajfVar) throws zzaks {
        return zzuj.zzc(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        zzjr zzjrVar = new zzjr(null);
        zzjrVar.zza(zzjs.zza);
        zzjrVar.zzc(zzjt.zzc);
        zzjrVar.zzd(zzju.zzb);
        zzjrVar.zzf(zzjv.zza);
        zzev zzc = zzey.zzc();
        zzc.zza(12);
        zzc.zzb(16);
        zzc.zzc(16);
        zzc.zzd(zzew.zzc);
        zzjrVar.zzb(zzc.zze());
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM", zzjrVar.zzg());
        zzjr zzjrVar2 = new zzjr(null);
        zzjrVar2.zza(zzjs.zza);
        zzjrVar2.zzc(zzjt.zzc);
        zzjrVar2.zzd(zzju.zzb);
        zzjrVar2.zzf(zzjv.zzc);
        zzev zzc2 = zzey.zzc();
        zzc2.zza(12);
        zzc2.zzb(16);
        zzc2.zzc(16);
        zzc2.zzd(zzew.zzc);
        zzjrVar2.zzb(zzc2.zze());
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_RAW", zzjrVar2.zzg());
        zzjr zzjrVar3 = new zzjr(null);
        zzjrVar3.zza(zzjs.zza);
        zzjrVar3.zzc(zzjt.zzc);
        zzjrVar3.zzd(zzju.zza);
        zzjrVar3.zzf(zzjv.zza);
        zzev zzc3 = zzey.zzc();
        zzc3.zza(12);
        zzc3.zzb(16);
        zzc3.zzc(16);
        zzc3.zzd(zzew.zzc);
        zzjrVar3.zzb(zzc3.zze());
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM", zzjrVar3.zzg());
        zzjr zzjrVar4 = new zzjr(null);
        zzjrVar4.zza(zzjs.zza);
        zzjrVar4.zzc(zzjt.zzc);
        zzjrVar4.zzd(zzju.zza);
        zzjrVar4.zzf(zzjv.zzc);
        zzev zzc4 = zzey.zzc();
        zzc4.zza(12);
        zzc4.zzb(16);
        zzc4.zzc(16);
        zzc4.zzd(zzew.zzc);
        zzjrVar4.zzb(zzc4.zze());
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM_RAW", zzjrVar4.zzg());
        zzjr zzjrVar5 = new zzjr(null);
        zzjrVar5.zza(zzjs.zza);
        zzjrVar5.zzc(zzjt.zzc);
        zzjrVar5.zzd(zzju.zza);
        zzjrVar5.zzf(zzjv.zzc);
        zzev zzc5 = zzey.zzc();
        zzc5.zza(12);
        zzc5.zzb(16);
        zzc5.zzc(16);
        zzc5.zzd(zzew.zzc);
        zzjrVar5.zzb(zzc5.zze());
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX", zzjrVar5.zzg());
        zzjr zzjrVar6 = new zzjr(null);
        zzjrVar6.zza(zzjs.zza);
        zzjrVar6.zzc(zzjt.zzc);
        zzjrVar6.zzd(zzju.zzb);
        zzjrVar6.zzf(zzjv.zza);
        zzdj zzf = zzdn.zzf();
        zzf.zza(16);
        zzf.zzc(32);
        zzf.zze(16);
        zzf.zzd(16);
        zzf.zzb(zzdk.zzc);
        zzf.zzf(zzdl.zzc);
        zzjrVar6.zzb(zzf.zzg());
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zzjrVar6.zzg());
        zzjr zzjrVar7 = new zzjr(null);
        zzjrVar7.zza(zzjs.zza);
        zzjrVar7.zzc(zzjt.zzc);
        zzjrVar7.zzd(zzju.zzb);
        zzjrVar7.zzf(zzjv.zzc);
        zzdj zzf2 = zzdn.zzf();
        zzf2.zza(16);
        zzf2.zzc(32);
        zzf2.zze(16);
        zzf2.zzd(16);
        zzf2.zzb(zzdk.zzc);
        zzf2.zzf(zzdl.zzc);
        zzjrVar7.zzb(zzf2.zzg());
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zzjrVar7.zzg());
        zzjr zzjrVar8 = new zzjr(null);
        zzjrVar8.zza(zzjs.zza);
        zzjrVar8.zzc(zzjt.zzc);
        zzjrVar8.zzd(zzju.zza);
        zzjrVar8.zzf(zzjv.zza);
        zzdj zzf3 = zzdn.zzf();
        zzf3.zza(16);
        zzf3.zzc(32);
        zzf3.zze(16);
        zzf3.zzd(16);
        zzf3.zzb(zzdk.zzc);
        zzf3.zzf(zzdl.zzc);
        zzjrVar8.zzb(zzf3.zzg());
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zzjrVar8.zzg());
        zzjr zzjrVar9 = new zzjr(null);
        zzjrVar9.zza(zzjs.zza);
        zzjrVar9.zzc(zzjt.zzc);
        zzjrVar9.zzd(zzju.zza);
        zzjrVar9.zzf(zzjv.zzc);
        zzdj zzf4 = zzdn.zzf();
        zzf4.zza(16);
        zzf4.zzc(32);
        zzf4.zze(16);
        zzf4.zzd(16);
        zzf4.zzb(zzdk.zzc);
        zzf4.zzf(zzdl.zzc);
        zzjrVar9.zzb(zzf4.zzg());
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zzjrVar9.zzg());
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ void zzd(zzalp zzalpVar) throws GeneralSecurityException {
        zzlj.zzb(((zzuj) zzalpVar).zzd());
    }
}
