package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzds  reason: invalid package */
/* loaded from: classes.dex */
public final class zzds {
    public static final /* synthetic */ int zza = 0;
    private static final zzzo zzb;
    private static final zzob zzc;
    private static final zznx zzd;
    private static final zzne zze;
    private static final zzna zzf;

    static {
        zzzo zzb2 = zzpd.zzb("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        zzb = zzb2;
        zzc = zzob.zzb(new zznz() { // from class: com.google.android.gms.internal.firebase-auth-api.zzdo
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznz
            public final zzot zza(zzce zzceVar) {
                zzvc zzvcVar;
                zzxo zzxoVar;
                zzdn zzdnVar = (zzdn) zzceVar;
                int i = zzds.zza;
                zzwm zza2 = zzwn.zza();
                zza2.zzb("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
                zzsk zza3 = zzsl.zza();
                zzsq zzb3 = zzsr.zzb();
                zzst zzb4 = zzsu.zzb();
                zzb4.zza(zzdnVar.zzd());
                zzb3.zzb((zzsu) zzb4.zzi());
                zzb3.zza(zzdnVar.zzb());
                zza3.zza((zzsr) zzb3.zzi());
                zzvh zzc2 = zzvi.zzc();
                zzvk zzc3 = zzvl.zzc();
                zzc3.zzb(zzdnVar.zze());
                zzdk zzg = zzdnVar.zzg();
                if (zzdk.zza.equals(zzg)) {
                    zzvcVar = zzvc.SHA1;
                } else if (zzdk.zzb.equals(zzg)) {
                    zzvcVar = zzvc.SHA224;
                } else if (zzdk.zzc.equals(zzg)) {
                    zzvcVar = zzvc.SHA256;
                } else if (zzdk.zzd.equals(zzg)) {
                    zzvcVar = zzvc.SHA384;
                } else if (zzdk.zze.equals(zzg)) {
                    zzvcVar = zzvc.SHA512;
                } else {
                    throw new GeneralSecurityException("Unable to serialize HashType ".concat(String.valueOf(String.valueOf(zzg))));
                }
                zzc3.zza(zzvcVar);
                zzc2.zzb((zzvl) zzc3.zzi());
                zzc2.zza(zzdnVar.zzc());
                zza3.zzb((zzvi) zzc2.zzi());
                zza2.zzc(((zzsl) zza3.zzi()).zzo());
                zzdl zzh = zzdnVar.zzh();
                if (zzdl.zza.equals(zzh)) {
                    zzxoVar = zzxo.TINK;
                } else if (zzdl.zzb.equals(zzh)) {
                    zzxoVar = zzxo.CRUNCHY;
                } else if (zzdl.zzc.equals(zzh)) {
                    zzxoVar = zzxo.RAW;
                } else {
                    throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(String.valueOf(zzh))));
                }
                zza2.zza(zzxoVar);
                return zzop.zzb((zzwn) zza2.zzi());
            }
        }, zzdn.class, zzop.class);
        zzd = zznx.zzb(new zznv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzdp
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznv
            public final zzce zza(zzot zzotVar) {
                return zzds.zzb((zzop) zzotVar);
            }
        }, zzb2, zzop.class);
        zze = zzne.zza(new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zzdq
        }, zzde.class, zzoo.class);
        zzf = zzna.zzb(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzdr
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zzmy
            public final zzbn zza(zzot zzotVar, zzcr zzcrVar) {
                return zzds.zza((zzoo) zzotVar, zzcrVar);
            }
        }, zzb2, zzoo.class);
    }

    public static /* synthetic */ zzde zza(zzoo zzooVar, zzcr zzcrVar) {
        if (!zzooVar.zzg().equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesCtrHmacAeadProtoSerialization.parseKey");
        }
        try {
            zzsi zzd2 = zzsi.zzd(zzooVar.zze(), zzajx.zza());
            if (zzd2.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            if (zzd2.zze().zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys inner AES CTR keys are accepted");
            }
            if (zzd2.zzf().zza() == 0) {
                zzdj zzdjVar = new zzdj(null);
                zzdjVar.zza(zzd2.zze().zzg().zzd());
                zzdjVar.zzc(zzd2.zzf().zzg().zzd());
                zzdjVar.zzd(zzd2.zze().zzf().zza());
                zzdjVar.zze(zzd2.zzf().zzf().zza());
                zzdjVar.zzb(zzd(zzd2.zzf().zzf().zzb()));
                zzdjVar.zzf(zze(zzooVar.zzc()));
                zzdn zzg = zzdjVar.zzg();
                zzdc zzdcVar = new zzdc(null);
                zzdcVar.zzd(zzg);
                zzdcVar.zza(zzzq.zzb(zzd2.zze().zzg().zzq(), zzcrVar));
                zzdcVar.zzb(zzzq.zzb(zzd2.zzf().zzg().zzq(), zzcrVar));
                zzdcVar.zzc(zzooVar.zzf());
                return zzdcVar.zze();
            }
            throw new GeneralSecurityException("Only version 0 keys inner HMAC keys are accepted");
        } catch (zzaks unused) {
            throw new GeneralSecurityException("Parsing AesCtrHmacAeadKey failed");
        }
    }

    public static /* synthetic */ zzdn zzb(zzop zzopVar) {
        if (!zzopVar.zzc().zzg().equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesCtrHmacAeadProtoSerialization.parseParameters: ".concat(String.valueOf(zzopVar.zzc().zzg())));
        }
        try {
            zzsl zzc2 = zzsl.zzc(zzopVar.zzc().zzf(), zzajx.zza());
            if (zzc2.zze().zzb() == 0) {
                zzdj zzdjVar = new zzdj(null);
                zzdjVar.zza(zzc2.zzd().zza());
                zzdjVar.zzc(zzc2.zze().zza());
                zzdjVar.zzd(zzc2.zzd().zzf().zza());
                zzdjVar.zze(zzc2.zze().zzg().zza());
                zzdjVar.zzb(zzd(zzc2.zze().zzg().zzb()));
                zzdjVar.zzf(zze(zzopVar.zzc().zze()));
                return zzdjVar.zzg();
            }
            throw new GeneralSecurityException("Only version 0 keys are accepted");
        } catch (zzaks e) {
            throw new GeneralSecurityException("Parsing AesCtrHmacAeadParameters failed: ", e);
        }
    }

    public static void zzc(zznt zzntVar) throws GeneralSecurityException {
        zzntVar.zzh(zzc);
        zzntVar.zzg(zzd);
        zzntVar.zzf(zze);
        zzntVar.zze(zzf);
    }

    private static zzdk zzd(zzvc zzvcVar) throws GeneralSecurityException {
        zzvc zzvcVar2 = zzvc.UNKNOWN_HASH;
        zzxo zzxoVar = zzxo.UNKNOWN_PREFIX;
        int ordinal = zzvcVar.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal != 3) {
                    if (ordinal != 4) {
                        if (ordinal == 5) {
                            return zzdk.zzb;
                        }
                        int zza2 = zzvcVar.zza();
                        throw new GeneralSecurityException("Unable to parse HashType: " + zza2);
                    }
                    return zzdk.zze;
                }
                return zzdk.zzc;
            }
            return zzdk.zzd;
        }
        return zzdk.zza;
    }

    private static zzdl zze(zzxo zzxoVar) throws GeneralSecurityException {
        zzvc zzvcVar = zzvc.UNKNOWN_HASH;
        zzxo zzxoVar2 = zzxo.UNKNOWN_PREFIX;
        int ordinal = zzxoVar.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal == 3) {
                    return zzdl.zzc;
                }
                if (ordinal != 4) {
                    int zza2 = zzxoVar.zza();
                    throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
                }
            }
            return zzdl.zzb;
        }
        return zzdl.zza;
    }
}
