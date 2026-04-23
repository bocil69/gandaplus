package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhw  reason: invalid package */
/* loaded from: classes.dex */
public final class zzhw {
    public static final /* synthetic */ int zza = 0;
    private static final zzzo zzb;
    private static final zzob zzc;
    private static final zznx zzd;
    private static final zzne zze;
    private static final zzna zzf;

    static {
        zzzo zzb2 = zzpd.zzb("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        zzb = zzb2;
        zzc = zzob.zzb(new zznz() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhs
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznz
            public final zzot zza(zzce zzceVar) {
                zzxo zzxoVar;
                int i = zzhw.zza;
                zzwm zza2 = zzwn.zza();
                zza2.zzb("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
                zza2.zzc(zzxx.zzc().zzo());
                zzhq zzb3 = ((zzhr) zzceVar).zzb();
                if (zzhq.zza.equals(zzb3)) {
                    zzxoVar = zzxo.TINK;
                } else if (zzhq.zzb.equals(zzb3)) {
                    zzxoVar = zzxo.CRUNCHY;
                } else if (zzhq.zzc.equals(zzb3)) {
                    zzxoVar = zzxo.RAW;
                } else {
                    throw new GeneralSecurityException("Unable to serialize variant: ".concat(zzb3.toString()));
                }
                zza2.zza(zzxoVar);
                return zzop.zzb((zzwn) zza2.zzi());
            }
        }, zzhr.class, zzop.class);
        zzd = zznx.zzb(new zznv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzht
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznv
            public final zzce zza(zzot zzotVar) {
                return zzhw.zzb((zzop) zzotVar);
            }
        }, zzb2, zzop.class);
        zze = zzne.zza(new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhu
        }, zzhm.class, zzoo.class);
        zzf = zzna.zzb(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzhv
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zzmy
            public final zzbn zza(zzot zzotVar, zzcr zzcrVar) {
                return zzhw.zza((zzoo) zzotVar, zzcrVar);
            }
        }, zzb2, zzoo.class);
    }

    public static /* synthetic */ zzhm zza(zzoo zzooVar, zzcr zzcrVar) {
        if (!zzooVar.zzg().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305ProtoSerialization.parseKey");
        }
        try {
            zzxu zzd2 = zzxu.zzd(zzooVar.zze(), zzajx.zza());
            if (zzd2.zza() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return zzhm.zza(zzd(zzooVar.zzc()), zzzq.zzb(zzd2.zze().zzq(), zzcrVar), zzooVar.zzf());
        } catch (zzaks unused) {
            throw new GeneralSecurityException("Parsing XChaCha20Poly1305Key failed");
        }
    }

    public static /* synthetic */ zzhr zzb(zzop zzopVar) {
        if (!zzopVar.zzc().zzg().equals("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key")) {
            throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305ProtoSerialization.parseParameters: ".concat(String.valueOf(zzopVar.zzc().zzg())));
        }
        try {
            if (zzxx.zzd(zzopVar.zzc().zzf(), zzajx.zza()).zza() != 0) {
                throw new GeneralSecurityException("Only version 0 parameters are accepted");
            }
            return zzhr.zzd(zzd(zzopVar.zzc().zze()));
        } catch (zzaks e) {
            throw new GeneralSecurityException("Parsing XChaCha20Poly1305Parameters failed: ", e);
        }
    }

    public static void zzc(zznt zzntVar) throws GeneralSecurityException {
        zzntVar.zzh(zzc);
        zzntVar.zzg(zzd);
        zzntVar.zzf(zze);
        zzntVar.zze(zzf);
    }

    private static zzhq zzd(zzxo zzxoVar) throws GeneralSecurityException {
        zzxo zzxoVar2 = zzxo.UNKNOWN_PREFIX;
        int ordinal = zzxoVar.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal == 3) {
                    return zzhq.zzc;
                }
                if (ordinal != 4) {
                    int zza2 = zzxoVar.zza();
                    throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
                }
            }
            return zzhq.zzb;
        }
        return zzhq.zza;
    }
}
