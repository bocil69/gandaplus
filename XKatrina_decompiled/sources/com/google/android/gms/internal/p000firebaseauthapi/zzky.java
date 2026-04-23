package com.google.android.gms.internal.p000firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzky  reason: invalid package */
/* loaded from: classes.dex */
public final class zzky {
    public static final /* synthetic */ int zza = 0;
    private static final zzzo zzb;
    private static final zzzo zzc;
    private static final zzob zzd;
    private static final zznx zze;
    private static final zzne zzf;
    private static final zzna zzg;
    private static final zzne zzh;
    private static final zzna zzi;
    private static final zzmu zzj;
    private static final zzmu zzk;
    private static final zzmu zzl;
    private static final zzmu zzm;

    static {
        zzzo zzb2 = zzpd.zzb("type.googleapis.com/google.crypto.tink.HpkePrivateKey");
        zzb = zzb2;
        zzzo zzb3 = zzpd.zzb("type.googleapis.com/google.crypto.tink.HpkePublicKey");
        zzc = zzb3;
        zzd = zzob.zzb(new zznz() { // from class: com.google.android.gms.internal.firebase-auth-api.zzks
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznz
            public final zzot zza(zzce zzceVar) {
                return zzky.zzd((zzkq) zzceVar);
            }
        }, zzkq.class, zzop.class);
        zze = zznx.zzb(new zznv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzkt
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznv
            public final zzce zza(zzot zzotVar) {
                return zzky.zza((zzop) zzotVar);
            }
        }, zzb2, zzop.class);
        zzf = zzne.zza(new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zzku
        }, zzkz.class, zzoo.class);
        zzg = zzna.zzb(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzkv
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zzmy
            public final zzbn zza(zzot zzotVar, zzcr zzcrVar) {
                return zzky.zzc((zzoo) zzotVar, zzcrVar);
            }
        }, zzb3, zzoo.class);
        zzh = zzne.zza(new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zzkw
        }, zzkr.class, zzoo.class);
        zzi = zzna.zzb(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzkx
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zzmy
            public final zzbn zza(zzot zzotVar, zzcr zzcrVar) {
                return zzky.zzb((zzoo) zzotVar, zzcrVar);
            }
        }, zzb2, zzoo.class);
        zzms zza2 = zzmu.zza();
        zza2.zza(zzxo.RAW, zzko.zzc);
        zza2.zza(zzxo.TINK, zzko.zza);
        zza2.zza(zzxo.LEGACY, zzko.zzb);
        zza2.zza(zzxo.CRUNCHY, zzko.zzb);
        zzj = zza2.zzb();
        zzms zza3 = zzmu.zza();
        zza3.zza(zzvr.DHKEM_P256_HKDF_SHA256, zzkn.zza);
        zza3.zza(zzvr.DHKEM_P384_HKDF_SHA384, zzkn.zzb);
        zza3.zza(zzvr.DHKEM_P521_HKDF_SHA512, zzkn.zzc);
        zza3.zza(zzvr.DHKEM_X25519_HKDF_SHA256, zzkn.zzf);
        zzk = zza3.zzb();
        zzms zza4 = zzmu.zza();
        zza4.zza(zzvp.HKDF_SHA256, zzkm.zza);
        zza4.zza(zzvp.HKDF_SHA384, zzkm.zzb);
        zza4.zza(zzvp.HKDF_SHA512, zzkm.zzc);
        zzl = zza4.zzb();
        zzms zza5 = zzmu.zza();
        zza5.zza(zzvn.AES_128_GCM, zzkh.zza);
        zza5.zza(zzvn.AES_256_GCM, zzkh.zzb);
        zza5.zza(zzvn.CHACHA20_POLY1305, zzkh.zzc);
        zzm = zza5.zzb();
    }

    public static /* synthetic */ zzkq zza(zzop zzopVar) {
        if (!zzopVar.zzc().zzg().equals("type.googleapis.com/google.crypto.tink.HpkePrivateKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to HpkeProtoSerialization.parseParameters: ".concat(String.valueOf(zzopVar.zzc().zzg())));
        }
        try {
            return zzf(zzopVar.zzc().zze(), zzvu.zzc(zzopVar.zzc().zzf(), zzajx.zza()).zzd());
        } catch (zzaks e) {
            throw new GeneralSecurityException("Parsing HpkeParameters failed: ", e);
        }
    }

    public static /* synthetic */ zzkr zzb(zzoo zzooVar, zzcr zzcrVar) {
        if (!zzooVar.zzg().equals("type.googleapis.com/google.crypto.tink.HpkePrivateKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to HpkeProtoSerialization.parsePrivateKey: ".concat(String.valueOf(zzooVar.zzg())));
        }
        try {
            zzwa zzd2 = zzwa.zzd(zzooVar.zze(), zzajx.zza());
            if (zzd2.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            zzwd zze2 = zzd2.zze();
            return zzkr.zza(zzkz.zzb(zzf(zzooVar.zzc(), zze2.zzb()), zzg(zze2.zzb().zzc(), zze2.zzg().zzq()), zzooVar.zzf()), zzzq.zzb(zzmn.zzc(zzmn.zza(zzd2.zzf().zzq()), zzmb.zza(zze2.zzb().zzc())), zzcrVar));
        } catch (zzaks unused) {
            throw new GeneralSecurityException("Parsing HpkePrivateKey failed");
        }
    }

    public static /* synthetic */ zzkz zzc(zzoo zzooVar, zzcr zzcrVar) {
        if (!zzooVar.zzg().equals("type.googleapis.com/google.crypto.tink.HpkePublicKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to HpkeProtoSerialization.parsePublicKey: ".concat(String.valueOf(zzooVar.zzg())));
        }
        try {
            zzwd zzf2 = zzwd.zzf(zzooVar.zze(), zzajx.zza());
            if (zzf2.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return zzkz.zzb(zzf(zzooVar.zzc(), zzf2.zzb()), zzg(zzf2.zzb().zzc(), zzf2.zzg().zzq()), zzooVar.zzf());
        } catch (zzaks unused) {
            throw new GeneralSecurityException("Parsing HpkePublicKey failed");
        }
    }

    public static /* synthetic */ zzop zzd(zzkq zzkqVar) {
        zzwm zza2 = zzwn.zza();
        zza2.zzb("type.googleapis.com/google.crypto.tink.HpkePrivateKey");
        zzvt zza3 = zzvu.zza();
        zzvw zzd2 = zzvx.zzd();
        zzd2.zzc((zzvr) zzk.zzb(zzkqVar.zze()));
        zzd2.zzb((zzvp) zzl.zzb(zzkqVar.zzd()));
        zzd2.zza((zzvn) zzm.zzb(zzkqVar.zzb()));
        zza3.zza((zzvx) zzd2.zzi());
        zza2.zzc(((zzvu) zza3.zzi()).zzo());
        zza2.zza((zzxo) zzj.zzb(zzkqVar.zzf()));
        return zzop.zzb((zzwn) zza2.zzi());
    }

    public static void zze(zznt zzntVar) throws GeneralSecurityException {
        zzntVar.zzh(zzd);
        zzntVar.zzg(zze);
        zzntVar.zzf(zzf);
        zzntVar.zze(zzg);
        zzntVar.zzf(zzh);
        zzntVar.zze(zzi);
    }

    private static zzkq zzf(zzxo zzxoVar, zzvx zzvxVar) throws GeneralSecurityException {
        zzkl zzklVar = new zzkl(null);
        zzklVar.zzd((zzko) zzj.zzc(zzxoVar));
        zzklVar.zzc((zzkn) zzk.zzc(zzvxVar.zzc()));
        zzklVar.zzb((zzkm) zzl.zzc(zzvxVar.zzb()));
        zzklVar.zza((zzkh) zzm.zzc(zzvxVar.zza()));
        return zzklVar.zze();
    }

    private static zzzo zzg(zzvr zzvrVar, byte[] bArr) throws GeneralSecurityException {
        int i;
        BigInteger zza2 = zzmn.zza(bArr);
        byte[] bArr2 = zzmb.zza;
        zzvr zzvrVar2 = zzvr.KEM_UNKNOWN;
        int ordinal = zzvrVar.ordinal();
        if (ordinal == 1) {
            i = 32;
        } else if (ordinal == 2) {
            i = 65;
        } else if (ordinal == 3) {
            i = 97;
        } else if (ordinal != 4) {
            throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
        } else {
            i = 133;
        }
        return zzzo.zzb(zzmn.zzc(zza2, i));
    }
}
