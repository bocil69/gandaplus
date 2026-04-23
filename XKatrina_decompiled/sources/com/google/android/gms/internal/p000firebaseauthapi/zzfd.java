package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfd  reason: invalid package */
/* loaded from: classes.dex */
public final class zzfd {
    public static final /* synthetic */ int zza = 0;
    private static final zzzo zzb;
    private static final zzob zzc;
    private static final zznx zzd;
    private static final zzne zze;
    private static final zzna zzf;

    static {
        zzzo zzb2 = zzpd.zzb("type.googleapis.com/google.crypto.tink.AesGcmKey");
        zzb = zzb2;
        zzc = zzob.zzb(new zznz() { // from class: com.google.android.gms.internal.firebase-auth-api.zzez
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznz
            public final zzot zza(zzce zzceVar) {
                zzxo zzxoVar;
                zzey zzeyVar = (zzey) zzceVar;
                int i = zzfd.zza;
                zzwm zza2 = zzwn.zza();
                zza2.zzb("type.googleapis.com/google.crypto.tink.AesGcmKey");
                zzti zzc2 = zztj.zzc();
                zzc2.zza(zzeyVar.zzb());
                zza2.zzc(((zztj) zzc2.zzi()).zzo());
                zzew zzd2 = zzeyVar.zzd();
                if (zzew.zza.equals(zzd2)) {
                    zzxoVar = zzxo.TINK;
                } else if (zzew.zzb.equals(zzd2)) {
                    zzxoVar = zzxo.CRUNCHY;
                } else if (zzew.zzc.equals(zzd2)) {
                    zzxoVar = zzxo.RAW;
                } else {
                    throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(String.valueOf(zzd2))));
                }
                zza2.zza(zzxoVar);
                return zzop.zzb((zzwn) zza2.zzi());
            }
        }, zzey.class, zzop.class);
        zzd = zznx.zzb(new zznv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzfa
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznv
            public final zzce zza(zzot zzotVar) {
                return zzfd.zzb((zzop) zzotVar);
            }
        }, zzb2, zzop.class);
        zze = zzne.zza(new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zzfb
        }, zzeq.class, zzoo.class);
        zzf = zzna.zzb(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzfc
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zzmy
            public final zzbn zza(zzot zzotVar, zzcr zzcrVar) {
                return zzfd.zza((zzoo) zzotVar, zzcrVar);
            }
        }, zzb2, zzoo.class);
    }

    public static /* synthetic */ zzeq zza(zzoo zzooVar, zzcr zzcrVar) {
        if (!zzooVar.zzg().equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmProtoSerialization.parseKey");
        }
        try {
            zztg zzd2 = zztg.zzd(zzooVar.zze(), zzajx.zza());
            if (zzd2.zza() == 0) {
                zzev zzevVar = new zzev(null);
                zzevVar.zzb(zzd2.zze().zzd());
                zzevVar.zza(12);
                zzevVar.zzc(16);
                zzevVar.zzd(zzd(zzooVar.zzc()));
                zzey zze2 = zzevVar.zze();
                zzeo zzeoVar = new zzeo(null);
                zzeoVar.zzc(zze2);
                zzeoVar.zzb(zzzq.zzb(zzd2.zze().zzq(), zzcrVar));
                zzeoVar.zza(zzooVar.zzf());
                return zzeoVar.zzd();
            }
            throw new GeneralSecurityException("Only version 0 keys are accepted");
        } catch (zzaks unused) {
            throw new GeneralSecurityException("Parsing AesGcmKey failed");
        }
    }

    public static /* synthetic */ zzey zzb(zzop zzopVar) {
        if (!zzopVar.zzc().zzg().equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesGcmProtoSerialization.parseParameters: ".concat(String.valueOf(zzopVar.zzc().zzg())));
        }
        try {
            zztj zze2 = zztj.zze(zzopVar.zzc().zzf(), zzajx.zza());
            if (zze2.zzb() == 0) {
                zzev zzevVar = new zzev(null);
                zzevVar.zzb(zze2.zza());
                zzevVar.zza(12);
                zzevVar.zzc(16);
                zzevVar.zzd(zzd(zzopVar.zzc().zze()));
                return zzevVar.zze();
            }
            throw new GeneralSecurityException("Only version 0 parameters are accepted");
        } catch (zzaks e) {
            throw new GeneralSecurityException("Parsing AesGcmParameters failed: ", e);
        }
    }

    public static void zzc(zznt zzntVar) throws GeneralSecurityException {
        zzntVar.zzh(zzc);
        zzntVar.zzg(zzd);
        zzntVar.zzf(zze);
        zzntVar.zze(zzf);
    }

    private static zzew zzd(zzxo zzxoVar) throws GeneralSecurityException {
        zzxo zzxoVar2 = zzxo.UNKNOWN_PREFIX;
        int ordinal = zzxoVar.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal == 3) {
                    return zzew.zzc;
                }
                if (ordinal != 4) {
                    int zza2 = zzxoVar.zza();
                    throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
                }
            }
            return zzew.zzb;
        }
        return zzew.zza;
    }
}
