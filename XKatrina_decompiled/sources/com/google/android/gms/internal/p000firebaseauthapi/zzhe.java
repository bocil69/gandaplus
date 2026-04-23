package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhe  reason: invalid package */
/* loaded from: classes.dex */
public final class zzhe {
    public static final /* synthetic */ int zza = 0;
    private static final zzzo zzb;
    private static final zzob zzc;
    private static final zznx zzd;
    private static final zzne zze;
    private static final zzna zzf;

    static {
        zzzo zzb2 = zzpd.zzb("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey");
        zzb = zzb2;
        zzc = zzob.zzb(new zznz() { // from class: com.google.android.gms.internal.firebase-auth-api.zzha
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznz
            public final zzot zza(zzce zzceVar) {
                zzgz zzgzVar = (zzgz) zzceVar;
                int i = zzhe.zza;
                zzwm zza2 = zzwn.zza();
                zza2.zzb("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey");
                try {
                    zzwn zzd2 = zzwn.zzd(zzcs.zzb(zzgzVar.zzb()), zzajx.zza());
                    zzxl zzb3 = zzxm.zzb();
                    zzb3.zzb(zzgzVar.zzc());
                    zzb3.zza(zzd2);
                    zza2.zzc(((zzxm) zzb3.zzi()).zzo());
                    zza2.zza(zzxo.RAW);
                    return zzop.zzb((zzwn) zza2.zzi());
                } catch (zzaks e) {
                    throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKeyFormat failed: ", e);
                }
            }
        }, zzgz.class, zzop.class);
        zzd = zznx.zzb(new zznv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhb
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznv
            public final zzce zza(zzot zzotVar) {
                return zzhe.zzb((zzop) zzotVar);
            }
        }, zzb2, zzop.class);
        zze = zzne.zza(new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhc
        }, zzgu.class, zzoo.class);
        zzf = zzna.zzb(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhd
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zzmy
            public final zzbn zza(zzot zzotVar, zzcr zzcrVar) {
                return zzhe.zza((zzoo) zzotVar, zzcrVar);
            }
        }, zzb2, zzoo.class);
    }

    public static /* synthetic */ zzgu zza(zzoo zzooVar, zzcr zzcrVar) {
        if (!zzooVar.zzg().equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsEnvelopeAeadProtoSerialization.parseKey");
        }
        try {
            zzxj zzd2 = zzxj.zzd(zzooVar.zze(), zzajx.zza());
            if (zzooVar.zzc() != zzxo.RAW) {
                String valueOf = String.valueOf(zzd2);
                throw new GeneralSecurityException("KmsEnvelopeAeadKeys are only accepted with OutputPrefixType RAW, got " + valueOf);
            } else if (zzd2.zza() != 0) {
                String valueOf2 = String.valueOf(zzd2);
                throw new GeneralSecurityException("KmsEnvelopeAeadKeys are only accepted with version 0, got " + valueOf2);
            } else {
                return zzgu.zza(zzd(zzd2.zze()));
            }
        } catch (zzaks e) {
            throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKey failed: ", e);
        }
    }

    public static /* synthetic */ zzgz zzb(zzop zzopVar) {
        if (!zzopVar.zzc().zzg().equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsEnvelopeAeadProtoSerialization.parseParameters: ".concat(String.valueOf(zzopVar.zzc().zzg())));
        }
        try {
            return zzd(zzxm.zze(zzopVar.zzc().zzf(), zzajx.zza()));
        } catch (zzaks e) {
            throw new GeneralSecurityException("Parsing KmsEnvelopeAeadKeyFormat failed: ", e);
        }
    }

    public static void zzc(zznt zzntVar) throws GeneralSecurityException {
        zzntVar.zzh(zzc);
        zzntVar.zzg(zzd);
        zzntVar.zzf(zze);
        zzntVar.zze(zzf);
    }

    private static zzgz zzd(zzxm zzxmVar) throws GeneralSecurityException {
        zzgx zzgxVar;
        zzwm zza2 = zzwn.zza();
        zza2.zzb(zzxmVar.zza().zzg());
        zza2.zzc(zzxmVar.zza().zzf());
        zza2.zza(zzxo.RAW);
        zzce zza3 = zzcs.zza(((zzwn) zza2.zzi()).zzq());
        if (zza3 instanceof zzey) {
            zzgxVar = zzgx.zza;
        } else if (zza3 instanceof zzga) {
            zzgxVar = zzgx.zzc;
        } else if (zza3 instanceof zzhr) {
            zzgxVar = zzgx.zzb;
        } else if (zza3 instanceof zzdn) {
            zzgxVar = zzgx.zzd;
        } else if (zza3 instanceof zzeh) {
            zzgxVar = zzgx.zze;
        } else if (zza3 instanceof zzfp) {
            zzgxVar = zzgx.zzf;
        } else {
            throw new GeneralSecurityException("Unsupported DEK parameters when parsing ".concat(zza3.toString()));
        }
        zzgw zzgwVar = new zzgw(null);
        zzgwVar.zzc(zzxmVar.zzf());
        zzgwVar.zza((zzcx) zza3);
        zzgwVar.zzb(zzgxVar);
        return zzgwVar.zzd();
    }
}
