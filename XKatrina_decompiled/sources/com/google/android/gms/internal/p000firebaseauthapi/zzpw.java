package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpw  reason: invalid package */
/* loaded from: classes.dex */
final class zzpw {
    public static final /* synthetic */ int zza = 0;
    private static final zzzo zzb;
    private static final zzob zzc;
    private static final zznx zzd;
    private static final zzne zze;
    private static final zzna zzf;

    static {
        zzzo zzb2 = zzpd.zzb("type.googleapis.com/google.crypto.tink.AesCmacKey");
        zzb = zzb2;
        zzc = zzob.zzb(new zznz() { // from class: com.google.android.gms.internal.firebase-auth-api.zzps
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznz
            public final zzot zza(zzce zzceVar) {
                zzxo zzxoVar;
                zzpr zzprVar = (zzpr) zzceVar;
                int i = zzpw.zza;
                zzwm zza2 = zzwn.zza();
                zza2.zzb("type.googleapis.com/google.crypto.tink.AesCmacKey");
                zzsb zzb3 = zzsc.zzb();
                zzse zzb4 = zzsf.zzb();
                zzb4.zza(zzprVar.zzb());
                zzb3.zzb((zzsf) zzb4.zzi());
                zzb3.zza(zzprVar.zzc());
                zza2.zzc(((zzsc) zzb3.zzi()).zzo());
                zzpp zze2 = zzprVar.zze();
                if (zzpp.zza.equals(zze2)) {
                    zzxoVar = zzxo.TINK;
                } else if (zzpp.zzb.equals(zze2)) {
                    zzxoVar = zzxo.CRUNCHY;
                } else if (zzpp.zzd.equals(zze2)) {
                    zzxoVar = zzxo.RAW;
                } else if (zzpp.zzc.equals(zze2)) {
                    zzxoVar = zzxo.LEGACY;
                } else {
                    throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(String.valueOf(zze2))));
                }
                zza2.zza(zzxoVar);
                return zzop.zzb((zzwn) zza2.zzi());
            }
        }, zzpr.class, zzop.class);
        zzd = zznx.zzb(new zznv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzpt
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznv
            public final zzce zza(zzot zzotVar) {
                return zzpw.zzb((zzop) zzotVar);
            }
        }, zzb2, zzop.class);
        zze = zzne.zza(new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zzpu
        }, zzph.class, zzoo.class);
        zzf = zzna.zzb(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzpv
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zzmy
            public final zzbn zza(zzot zzotVar, zzcr zzcrVar) {
                return zzpw.zza((zzoo) zzotVar, zzcrVar);
            }
        }, zzb2, zzoo.class);
    }

    public static /* synthetic */ zzph zza(zzoo zzooVar, zzcr zzcrVar) {
        if (!zzooVar.zzg().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesCmacProtoSerialization.parseKey");
        }
        try {
            zzrz zzd2 = zzrz.zzd(zzooVar.zze(), zzajx.zza());
            if (zzd2.zza() == 0) {
                zzpo zzpoVar = new zzpo(null);
                zzpoVar.zza(zzd2.zzf().zzd());
                zzpoVar.zzb(zzd2.zze().zza());
                zzpoVar.zzc(zzd(zzooVar.zzc()));
                zzpr zzd3 = zzpoVar.zzd();
                zzpf zzpfVar = new zzpf(null);
                zzpfVar.zzc(zzd3);
                zzpfVar.zza(zzzq.zzb(zzd2.zzf().zzq(), zzcrVar));
                zzpfVar.zzb(zzooVar.zzf());
                return zzpfVar.zzd();
            }
            throw new GeneralSecurityException("Only version 0 keys are accepted");
        } catch (zzaks | IllegalArgumentException unused) {
            throw new GeneralSecurityException("Parsing AesCmacKey failed");
        }
    }

    public static /* synthetic */ zzpr zzb(zzop zzopVar) {
        if (!zzopVar.zzc().zzg().equals("type.googleapis.com/google.crypto.tink.AesCmacKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesCmacProtoSerialization.parseParameters: ".concat(String.valueOf(zzopVar.zzc().zzg())));
        }
        try {
            zzsc zzd2 = zzsc.zzd(zzopVar.zzc().zzf(), zzajx.zza());
            zzpo zzpoVar = new zzpo(null);
            zzpoVar.zza(zzd2.zza());
            zzpoVar.zzb(zzd2.zze().zza());
            zzpoVar.zzc(zzd(zzopVar.zzc().zze()));
            return zzpoVar.zzd();
        } catch (zzaks e) {
            throw new GeneralSecurityException("Parsing AesCmacParameters failed: ", e);
        }
    }

    public static void zzc(zznt zzntVar) throws GeneralSecurityException {
        zzntVar.zzh(zzc);
        zzntVar.zzg(zzd);
        zzntVar.zzf(zze);
        zzntVar.zze(zzf);
    }

    private static zzpp zzd(zzxo zzxoVar) throws GeneralSecurityException {
        zzxo zzxoVar2 = zzxo.UNKNOWN_PREFIX;
        int ordinal = zzxoVar.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal != 3) {
                    if (ordinal == 4) {
                        return zzpp.zzb;
                    }
                    int zza2 = zzxoVar.zza();
                    throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
                }
                return zzpp.zzd;
            }
            return zzpp.zzc;
        }
        return zzpp.zza;
    }
}
