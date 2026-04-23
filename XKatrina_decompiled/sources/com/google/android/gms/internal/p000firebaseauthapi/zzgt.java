package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgt  reason: invalid package */
/* loaded from: classes.dex */
final class zzgt {
    public static final /* synthetic */ int zza = 0;
    private static final zzzo zzb;
    private static final zzob zzc;
    private static final zznx zzd;
    private static final zzne zze;
    private static final zzna zzf;

    static {
        zzzo zzb2 = zzpd.zzb("type.googleapis.com/google.crypto.tink.KmsAeadKey");
        zzb = zzb2;
        zzc = zzob.zzb(new zznz() { // from class: com.google.android.gms.internal.firebase-auth-api.zzgp
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznz
            public final zzot zza(zzce zzceVar) {
                int i = zzgt.zza;
                zzwm zza2 = zzwn.zza();
                zza2.zzb("type.googleapis.com/google.crypto.tink.KmsAeadKey");
                zzxf zza3 = zzxg.zza();
                zza3.zza(((zzgo) zzceVar).zzc());
                zza2.zzc(((zzxg) zza3.zzi()).zzo());
                zza2.zza(zzxo.RAW);
                return zzop.zzb((zzwn) zza2.zzi());
            }
        }, zzgo.class, zzop.class);
        zzd = zznx.zzb(new zznv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzgq
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznv
            public final zzce zza(zzot zzotVar) {
                int i = zzgt.zza;
                zzop zzopVar = (zzop) zzotVar;
                if (!zzopVar.zzc().zzg().equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
                    throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsAeadProtoSerialization.parseParameters: ".concat(String.valueOf(zzopVar.zzc().zzg())));
                }
                try {
                    zzxg zzd2 = zzxg.zzd(((zzop) zzotVar).zzc().zzf(), zzajx.zza());
                    if (zzopVar.zzc().zze() != zzxo.RAW) {
                        String valueOf = String.valueOf(zzopVar.zzc().zze());
                        String valueOf2 = String.valueOf(zzd2);
                        throw new GeneralSecurityException("Only key templates with RAW are accepted, but got " + valueOf + " with format " + valueOf2);
                    }
                    return zzgo.zzb(zzd2.zze());
                } catch (zzaks e) {
                    throw new GeneralSecurityException("Parsing KmsAeadKeyFormat failed: ", e);
                }
            }
        }, zzb2, zzop.class);
        zze = zzne.zza(new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zzgr
        }, zzgn.class, zzoo.class);
        zzf = zzna.zzb(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzgs
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zzmy
            public final zzbn zza(zzot zzotVar, zzcr zzcrVar) {
                int i = zzgt.zza;
                zzoo zzooVar = (zzoo) zzotVar;
                if (!zzooVar.zzg().equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
                    throw new IllegalArgumentException("Wrong type URL in call to LegacyKmsAeadProtoSerialization.parseKey");
                }
                if (zzooVar.zzc() != zzxo.RAW) {
                    throw new GeneralSecurityException("KmsAeadKey are only accepted with RAW, got ".concat(String.valueOf(String.valueOf(zzooVar.zzc()))));
                }
                try {
                    zzxd zzd2 = zzxd.zzd(((zzoo) zzotVar).zze(), zzajx.zza());
                    if (zzd2.zza() != 0) {
                        String valueOf = String.valueOf(zzd2);
                        throw new GeneralSecurityException("KmsAeadKey are only accepted with version 0, got " + valueOf);
                    }
                    return zzgn.zza(zzgo.zzb(zzd2.zze().zze()));
                } catch (zzaks e) {
                    throw new GeneralSecurityException("Parsing KmsAeadKey failed: ", e);
                }
            }
        }, zzb2, zzoo.class);
    }

    public static void zza(zznt zzntVar) throws GeneralSecurityException {
        zzntVar.zzh(zzc);
        zzntVar.zzg(zzd);
        zzntVar.zzf(zze);
        zzntVar.zze(zzf);
    }
}
