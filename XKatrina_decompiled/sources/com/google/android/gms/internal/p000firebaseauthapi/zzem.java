package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzem  reason: invalid package */
/* loaded from: classes.dex */
public final class zzem {
    public static final /* synthetic */ int zza = 0;
    private static final zzzo zzb;
    private static final zzob zzc;
    private static final zznx zzd;
    private static final zzne zze;
    private static final zzna zzf;

    static {
        zzzo zzb2 = zzpd.zzb("type.googleapis.com/google.crypto.tink.AesEaxKey");
        zzb = zzb2;
        zzc = zzob.zzb(new zznz() { // from class: com.google.android.gms.internal.firebase-auth-api.zzei
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznz
            public final zzot zza(zzce zzceVar) {
                zzxo zzxoVar;
                zzeh zzehVar = (zzeh) zzceVar;
                int i = zzem.zza;
                zzwm zza2 = zzwn.zza();
                zza2.zzb("type.googleapis.com/google.crypto.tink.AesEaxKey");
                zzsz zzb3 = zzta.zzb();
                zztc zzb4 = zztd.zzb();
                zzb4.zza(zzehVar.zzb());
                zzb3.zzb((zztd) zzb4.zzi());
                zzb3.zza(zzehVar.zzc());
                zza2.zzc(((zzta) zzb3.zzi()).zzo());
                zzef zzd2 = zzehVar.zzd();
                if (zzef.zza.equals(zzd2)) {
                    zzxoVar = zzxo.TINK;
                } else if (zzef.zzb.equals(zzd2)) {
                    zzxoVar = zzxo.CRUNCHY;
                } else if (zzef.zzc.equals(zzd2)) {
                    zzxoVar = zzxo.RAW;
                } else {
                    throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(String.valueOf(zzd2))));
                }
                zza2.zza(zzxoVar);
                return zzop.zzb((zzwn) zza2.zzi());
            }
        }, zzeh.class, zzop.class);
        zzd = zznx.zzb(new zznv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzej
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznv
            public final zzce zza(zzot zzotVar) {
                return zzem.zzb((zzop) zzotVar);
            }
        }, zzb2, zzop.class);
        zze = zzne.zza(new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zzek
        }, zzdz.class, zzoo.class);
        zzf = zzna.zzb(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzel
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zzmy
            public final zzbn zza(zzot zzotVar, zzcr zzcrVar) {
                return zzem.zza((zzoo) zzotVar, zzcrVar);
            }
        }, zzb2, zzoo.class);
    }

    public static /* synthetic */ zzdz zza(zzoo zzooVar, zzcr zzcrVar) {
        if (!zzooVar.zzg().equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesEaxProtoSerialization.parseKey");
        }
        try {
            zzsx zzd2 = zzsx.zzd(zzooVar.zze(), zzajx.zza());
            if (zzd2.zza() == 0) {
                zzee zzeeVar = new zzee(null);
                zzeeVar.zzb(zzd2.zzf().zzd());
                zzeeVar.zza(zzd2.zze().zza());
                zzeeVar.zzc(16);
                zzeeVar.zzd(zzd(zzooVar.zzc()));
                zzeh zze2 = zzeeVar.zze();
                zzdx zzdxVar = new zzdx(null);
                zzdxVar.zzc(zze2);
                zzdxVar.zzb(zzzq.zzb(zzd2.zzf().zzq(), zzcrVar));
                zzdxVar.zza(zzooVar.zzf());
                return zzdxVar.zzd();
            }
            throw new GeneralSecurityException("Only version 0 keys are accepted");
        } catch (zzaks unused) {
            throw new GeneralSecurityException("Parsing AesEaxcKey failed");
        }
    }

    public static /* synthetic */ zzeh zzb(zzop zzopVar) {
        if (!zzopVar.zzc().zzg().equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesEaxProtoSerialization.parseParameters: ".concat(String.valueOf(zzopVar.zzc().zzg())));
        }
        try {
            zzta zzd2 = zzta.zzd(zzopVar.zzc().zzf(), zzajx.zza());
            zzee zzeeVar = new zzee(null);
            zzeeVar.zzb(zzd2.zza());
            zzeeVar.zza(zzd2.zze().zza());
            zzeeVar.zzc(16);
            zzeeVar.zzd(zzd(zzopVar.zzc().zze()));
            return zzeeVar.zze();
        } catch (zzaks e) {
            throw new GeneralSecurityException("Parsing AesEaxParameters failed: ", e);
        }
    }

    public static void zzc(zznt zzntVar) throws GeneralSecurityException {
        zzntVar.zzh(zzc);
        zzntVar.zzg(zzd);
        zzntVar.zzf(zze);
        zzntVar.zze(zzf);
    }

    private static zzef zzd(zzxo zzxoVar) throws GeneralSecurityException {
        zzxo zzxoVar2 = zzxo.UNKNOWN_PREFIX;
        int ordinal = zzxoVar.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal == 3) {
                    return zzef.zzc;
                }
                if (ordinal != 4) {
                    int zza2 = zzxoVar.zza();
                    throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
                }
            }
            return zzef.zzb;
        }
        return zzef.zza;
    }
}
