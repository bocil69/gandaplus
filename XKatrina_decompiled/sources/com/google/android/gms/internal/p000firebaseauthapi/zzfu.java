package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfu  reason: invalid package */
/* loaded from: classes.dex */
final class zzfu {
    public static final /* synthetic */ int zza = 0;
    private static final zzzo zzb;
    private static final zzob zzc;
    private static final zznx zzd;
    private static final zzne zze;
    private static final zzna zzf;

    static {
        zzzo zzb2 = zzpd.zzb("type.googleapis.com/google.crypto.tink.AesGcmSivKey");
        zzb = zzb2;
        zzc = zzob.zzb(new zznz() { // from class: com.google.android.gms.internal.firebase-auth-api.zzfq
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznz
            public final zzot zza(zzce zzceVar) {
                zzxo zzxoVar;
                zzfp zzfpVar = (zzfp) zzceVar;
                int i = zzfu.zza;
                zzwm zza2 = zzwn.zza();
                zza2.zzb("type.googleapis.com/google.crypto.tink.AesGcmSivKey");
                zzto zzc2 = zztp.zzc();
                zzc2.zza(zzfpVar.zzb());
                zza2.zzc(((zztp) zzc2.zzi()).zzo());
                zzfn zzc3 = zzfpVar.zzc();
                if (zzfn.zza.equals(zzc3)) {
                    zzxoVar = zzxo.TINK;
                } else if (zzfn.zzb.equals(zzc3)) {
                    zzxoVar = zzxo.CRUNCHY;
                } else if (zzfn.zzc.equals(zzc3)) {
                    zzxoVar = zzxo.RAW;
                } else {
                    throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(String.valueOf(zzc3))));
                }
                zza2.zza(zzxoVar);
                return zzop.zzb((zzwn) zza2.zzi());
            }
        }, zzfp.class, zzop.class);
        zzd = zznx.zzb(new zznv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzfr
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznv
            public final zzce zza(zzot zzotVar) {
                return zzfu.zzb((zzop) zzotVar);
            }
        }, zzb2, zzop.class);
        zze = zzne.zza(new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zzfs
        }, zzfh.class, zzoo.class);
        zzf = zzna.zzb(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzft
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zzmy
            public final zzbn zza(zzot zzotVar, zzcr zzcrVar) {
                return zzfu.zza((zzoo) zzotVar, zzcrVar);
            }
        }, zzb2, zzoo.class);
    }

    public static /* synthetic */ zzfh zza(zzoo zzooVar, zzcr zzcrVar) {
        if (!zzooVar.zzg().equals("type.googleapis.com/google.crypto.tink.AesGcmSivKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivProtoSerialization.parseKey");
        }
        try {
            zztm zzd2 = zztm.zzd(zzooVar.zze(), zzajx.zza());
            if (zzd2.zza() == 0) {
                zzfm zzfmVar = new zzfm(null);
                zzfmVar.zza(zzd2.zze().zzd());
                zzfmVar.zzb(zzd(zzooVar.zzc()));
                zzfp zzc2 = zzfmVar.zzc();
                zzff zzffVar = new zzff(null);
                zzffVar.zzc(zzc2);
                zzffVar.zzb(zzzq.zzb(zzd2.zze().zzq(), zzcrVar));
                zzffVar.zza(zzooVar.zzf());
                return zzffVar.zzd();
            }
            throw new GeneralSecurityException("Only version 0 keys are accepted");
        } catch (zzaks unused) {
            throw new GeneralSecurityException("Parsing AesGcmSivKey failed");
        }
    }

    public static /* synthetic */ zzfp zzb(zzop zzopVar) {
        if (!zzopVar.zzc().zzg().equals("type.googleapis.com/google.crypto.tink.AesGcmSivKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmSivProtoSerialization.parseParameters: ".concat(String.valueOf(zzopVar.zzc().zzg())));
        }
        try {
            zztp zze2 = zztp.zze(zzopVar.zzc().zzf(), zzajx.zza());
            if (zze2.zzb() == 0) {
                zzfm zzfmVar = new zzfm(null);
                zzfmVar.zza(zze2.zza());
                zzfmVar.zzb(zzd(zzopVar.zzc().zze()));
                return zzfmVar.zzc();
            }
            throw new GeneralSecurityException("Only version 0 parameters are accepted");
        } catch (zzaks e) {
            throw new GeneralSecurityException("Parsing AesGcmSivParameters failed: ", e);
        }
    }

    public static void zzc(zznt zzntVar) throws GeneralSecurityException {
        zzntVar.zzh(zzc);
        zzntVar.zzg(zzd);
        zzntVar.zzf(zze);
        zzntVar.zze(zzf);
    }

    private static zzfn zzd(zzxo zzxoVar) throws GeneralSecurityException {
        zzxo zzxoVar2 = zzxo.UNKNOWN_PREFIX;
        int ordinal = zzxoVar.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal == 3) {
                    return zzfn.zzc;
                }
                if (ordinal != 4) {
                    int zza2 = zzxoVar.zza();
                    throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
                }
            }
            return zzfn.zzb;
        }
        return zzfn.zza;
    }
}
