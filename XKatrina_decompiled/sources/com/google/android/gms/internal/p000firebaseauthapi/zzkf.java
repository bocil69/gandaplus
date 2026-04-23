package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkf  reason: invalid package */
/* loaded from: classes.dex */
final class zzkf {
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
        zzzo zzb2 = zzpd.zzb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey");
        zzb = zzb2;
        zzzo zzb3 = zzpd.zzb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey");
        zzc = zzb3;
        zzd = zzob.zzb(new zznz() { // from class: com.google.android.gms.internal.firebase-auth-api.zzjz
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznz
            public final zzot zza(zzce zzceVar) {
                return zzkf.zzd((zzjx) zzceVar);
            }
        }, zzjx.class, zzop.class);
        zze = zznx.zzb(new zznv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzka
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznv
            public final zzce zza(zzot zzotVar) {
                return zzkf.zza((zzop) zzotVar);
            }
        }, zzb2, zzop.class);
        zzf = zzne.zza(new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zzkb
        }, zzkg.class, zzoo.class);
        zzg = zzna.zzb(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzkc
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zzmy
            public final zzbn zza(zzot zzotVar, zzcr zzcrVar) {
                return zzkf.zzc((zzoo) zzotVar, zzcrVar);
            }
        }, zzb3, zzoo.class);
        zzh = zzne.zza(new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zzkd
        }, zzjy.class, zzoo.class);
        zzi = zzna.zzb(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzke
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zzmy
            public final zzbn zza(zzot zzotVar, zzcr zzcrVar) {
                return zzkf.zzb((zzoo) zzotVar, zzcrVar);
            }
        }, zzb2, zzoo.class);
        zzms zza2 = zzmu.zza();
        zza2.zza(zzxo.RAW, zzjv.zzc);
        zza2.zza(zzxo.TINK, zzjv.zza);
        zza2.zza(zzxo.LEGACY, zzjv.zzb);
        zza2.zza(zzxo.CRUNCHY, zzjv.zzb);
        zzj = zza2.zzb();
        zzms zza3 = zzmu.zza();
        zza3.zza(zzvc.SHA1, zzjt.zza);
        zza3.zza(zzvc.SHA224, zzjt.zzb);
        zza3.zza(zzvc.SHA256, zzjt.zzc);
        zza3.zza(zzvc.SHA384, zzjt.zzd);
        zza3.zza(zzvc.SHA512, zzjt.zze);
        zzk = zza3.zzb();
        zzms zza4 = zzmu.zza();
        zza4.zza(zzux.NIST_P256, zzjs.zza);
        zza4.zza(zzux.NIST_P384, zzjs.zzb);
        zza4.zza(zzux.NIST_P521, zzjs.zzc);
        zza4.zza(zzux.CURVE25519, zzjs.zzd);
        zzl = zza4.zzb();
        zzms zza5 = zzmu.zza();
        zza5.zza(zzud.UNCOMPRESSED, zzju.zzb);
        zza5.zza(zzud.COMPRESSED, zzju.zza);
        zza5.zza(zzud.DO_NOT_USE_CRUNCHY_UNCOMPRESSED, zzju.zzc);
        zzm = zza5.zzb();
    }

    public static /* synthetic */ zzjx zza(zzop zzopVar) {
        if (!zzopVar.zzc().zzg().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to EciesProtoSerialization.parseParameters: ".concat(String.valueOf(zzopVar.zzc().zzg())));
        }
        try {
            return zzf(zzopVar.zzc().zze(), zzuj.zzc(zzopVar.zzc().zzf(), zzajx.zza()).zzd());
        } catch (zzaks e) {
            throw new GeneralSecurityException("Parsing EciesParameters failed: ", e);
        }
    }

    public static /* synthetic */ zzjy zzb(zzoo zzooVar, zzcr zzcrVar) {
        if (!zzooVar.zzg().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to EciesProtoSerialization.parsePrivateKey: ".concat(String.valueOf(zzooVar.zzg())));
        }
        try {
            zzup zzd2 = zzup.zzd(zzooVar.zze(), zzajx.zza());
            if (zzd2.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            zzus zze2 = zzd2.zze();
            zzjx zzf2 = zzf(zzooVar.zzc(), zze2.zzb());
            if (zzf2.zzc().equals(zzjs.zzd)) {
                return zzjy.zza(zzkg.zzb(zzf2, zzzo.zzb(zze2.zzg().zzq()), zzooVar.zzf()), zzzq.zzb(zzd2.zzf().zzq(), zzcrVar));
            }
            return zzjy.zzb(zzkg.zzc(zzf2, new ECPoint(zzmn.zza(zze2.zzg().zzq()), zzmn.zza(zze2.zzh().zzq())), zzooVar.zzf()), zzzp.zza(zzmn.zza(zzd2.zzf().zzq()), zzcrVar));
        } catch (zzaks | IllegalArgumentException unused) {
            throw new GeneralSecurityException("Parsing EcdsaPrivateKey failed");
        }
    }

    public static /* synthetic */ zzkg zzc(zzoo zzooVar, zzcr zzcrVar) {
        if (!zzooVar.zzg().equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to EciesProtoSerialization.parsePublicKey: ".concat(String.valueOf(zzooVar.zzg())));
        }
        try {
            zzus zzf2 = zzus.zzf(zzooVar.zze(), zzajx.zza());
            if (zzf2.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            zzjx zzf3 = zzf(zzooVar.zzc(), zzf2.zzb());
            if (zzf3.zzc().equals(zzjs.zzd)) {
                if (!zzf2.zzh().zzp()) {
                    throw new GeneralSecurityException("Y must be empty for X25519 points");
                }
                return zzkg.zzb(zzf3, zzzo.zzb(zzf2.zzg().zzq()), zzooVar.zzf());
            }
            return zzkg.zzc(zzf3, new ECPoint(zzmn.zza(zzf2.zzg().zzq()), zzmn.zza(zzf2.zzh().zzq())), zzooVar.zzf());
        } catch (zzaks | IllegalArgumentException unused) {
            throw new GeneralSecurityException("Parsing EcdsaPublicKey failed");
        }
    }

    public static /* synthetic */ zzop zzd(zzjx zzjxVar) {
        zzwm zza2 = zzwn.zza();
        zza2.zzb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey");
        zzui zza3 = zzuj.zza();
        zzuu zza4 = zzuv.zza();
        zza4.zza((zzux) zzl.zzb(zzjxVar.zzc()));
        zza4.zzb((zzvc) zzk.zzb(zzjxVar.zzd()));
        if (zzjxVar.zzg() != null && zzjxVar.zzg().zza() > 0) {
            byte[] zzc2 = zzjxVar.zzg().zzc();
            zza4.zzc(zzajf.zzn(zzc2, 0, zzc2.length));
        }
        zzuv zzuvVar = (zzuv) zza4.zzi();
        try {
            zzwn zzd2 = zzwn.zzd(zzcs.zzb(zzjxVar.zzb()), zzajx.zza());
            zzuf zza5 = zzug.zza();
            zzwm zza6 = zzwn.zza();
            zza6.zzb(zzd2.zzg());
            zza6.zza(zzxo.TINK);
            zza6.zzc(zzd2.zzf());
            zza5.zza((zzwn) zza6.zzi());
            zzug zzugVar = (zzug) zza5.zzi();
            zzju zze2 = zzjxVar.zze();
            if (zze2 == null) {
                zze2 = zzju.zza;
            }
            zzul zzc3 = zzum.zzc();
            zzc3.zzc(zzuvVar);
            zzc3.zza(zzugVar);
            zzc3.zzb((zzud) zzm.zzb(zze2));
            zza3.zza((zzum) zzc3.zzi());
            zza2.zzc(((zzuj) zza3.zzi()).zzo());
            zza2.zza((zzxo) zzj.zzb(zzjxVar.zzf()));
            return zzop.zzb((zzwn) zza2.zzi());
        } catch (zzaks e) {
            throw new GeneralSecurityException("Parsing EciesParameters failed: ", e);
        }
    }

    public static void zze(zznt zzntVar) throws GeneralSecurityException {
        zzntVar.zzh(zzd);
        zzntVar.zzg(zze);
        zzntVar.zzf(zzf);
        zzntVar.zze(zzg);
        zzntVar.zzf(zzh);
        zzntVar.zze(zzi);
    }

    private static zzjx zzf(zzxo zzxoVar, zzum zzumVar) throws GeneralSecurityException {
        zzwm zza2 = zzwn.zza();
        zza2.zzb(zzumVar.zzb().zzd().zzg());
        zza2.zza(zzxo.RAW);
        zza2.zzc(zzumVar.zzb().zzd().zzf());
        zzjr zzjrVar = new zzjr(null);
        zzjrVar.zzf((zzjv) zzj.zzc(zzxoVar));
        zzjrVar.zza((zzjs) zzl.zzc(zzumVar.zzf().zzd()));
        zzjrVar.zzc((zzjt) zzk.zzc(zzumVar.zzf().zze()));
        zzjrVar.zzb(zzcs.zza(((zzwn) zza2.zzi()).zzq()));
        zzjrVar.zze(zzzo.zzb(zzumVar.zzf().zzf().zzq()));
        if (!zzumVar.zzf().zzd().equals(zzux.CURVE25519)) {
            zzjrVar.zzd((zzju) zzm.zzc(zzumVar.zza()));
        } else if (!zzumVar.zza().equals(zzud.COMPRESSED)) {
            throw new GeneralSecurityException("For CURVE25519 EcPointFormat must be compressed");
        }
        return zzjrVar.zzg();
    }
}
