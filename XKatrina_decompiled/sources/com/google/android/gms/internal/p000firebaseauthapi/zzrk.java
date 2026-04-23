package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrk  reason: invalid package */
/* loaded from: classes.dex */
public final class zzrk {
    public static final /* synthetic */ int zza = 0;
    private static final zzzo zzb;
    private static final zzmu zzc;
    private static final zzmu zzd;
    private static final zzob zze;
    private static final zznx zzf;
    private static final zzne zzg;
    private static final zzna zzh;

    static {
        zzzo zzb2 = zzpd.zzb("type.googleapis.com/google.crypto.tink.HmacKey");
        zzb = zzb2;
        zzms zza2 = zzmu.zza();
        zza2.zza(zzxo.RAW, zzqn.zzd);
        zza2.zza(zzxo.TINK, zzqn.zza);
        zza2.zza(zzxo.LEGACY, zzqn.zzc);
        zza2.zza(zzxo.CRUNCHY, zzqn.zzb);
        zzc = zza2.zzb();
        zzms zza3 = zzmu.zza();
        zza3.zza(zzvc.SHA1, zzqm.zza);
        zza3.zza(zzvc.SHA224, zzqm.zzb);
        zza3.zza(zzvc.SHA256, zzqm.zzc);
        zza3.zza(zzvc.SHA384, zzqm.zzd);
        zza3.zza(zzvc.SHA512, zzqm.zze);
        zzd = zza3.zzb();
        zze = zzob.zzb(new zznz() { // from class: com.google.android.gms.internal.firebase-auth-api.zzrg
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznz
            public final zzot zza(zzce zzceVar) {
                return zzrk.zza((zzqp) zzceVar);
            }
        }, zzqp.class, zzop.class);
        zzf = zznx.zzb(new zznv() { // from class: com.google.android.gms.internal.firebase-auth-api.zzrh
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznv
            public final zzce zza(zzot zzotVar) {
                return zzrk.zzc((zzop) zzotVar);
            }
        }, zzb2, zzop.class);
        zzg = zzne.zza(new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zzri
        }, zzqe.class, zzoo.class);
        zzh = zzna.zzb(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzrj
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zzmy
            public final zzbn zza(zzot zzotVar, zzcr zzcrVar) {
                return zzrk.zzb((zzoo) zzotVar, zzcrVar);
            }
        }, zzb2, zzoo.class);
    }

    public static /* synthetic */ zzop zza(zzqp zzqpVar) {
        zzwm zza2 = zzwn.zza();
        zza2.zzb("type.googleapis.com/google.crypto.tink.HmacKey");
        zzvh zzc2 = zzvi.zzc();
        zzvk zzc3 = zzvl.zzc();
        zzc3.zzb(zzqpVar.zzb());
        zzc3.zza((zzvc) zzd.zzb(zzqpVar.zzf()));
        zzc2.zzb((zzvl) zzc3.zzi());
        zzc2.zza(zzqpVar.zzc());
        zza2.zzc(((zzvi) zzc2.zzi()).zzo());
        zza2.zza((zzxo) zzc.zzb(zzqpVar.zzg()));
        return zzop.zzb((zzwn) zza2.zzi());
    }

    public static /* synthetic */ zzqe zzb(zzoo zzooVar, zzcr zzcrVar) {
        if (!zzooVar.zzg().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseKey");
        }
        try {
            zzvf zze2 = zzvf.zze(zzooVar.zze(), zzajx.zza());
            if (zze2.zza() == 0) {
                zzql zze3 = zzqp.zze();
                zze3.zzb(zze2.zzg().zzd());
                zze3.zzc(zze2.zzf().zza());
                zze3.zza((zzqm) zzd.zzc(zze2.zzf().zzb()));
                zze3.zzd((zzqn) zzc.zzc(zzooVar.zzc()));
                zzqp zze4 = zze3.zze();
                zzqc zza2 = zzqe.zza();
                zza2.zzc(zze4);
                zza2.zzb(zzzq.zzb(zze2.zzg().zzq(), zzcrVar));
                zza2.zza(zzooVar.zzf());
                return zza2.zzd();
            }
            throw new GeneralSecurityException("Only version 0 keys are accepted");
        } catch (zzaks | IllegalArgumentException unused) {
            throw new GeneralSecurityException("Parsing HmacKey failed");
        }
    }

    public static /* synthetic */ zzqp zzc(zzop zzopVar) {
        if (!zzopVar.zzc().zzg().equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to HmacProtoSerialization.parseParameters: ".concat(String.valueOf(zzopVar.zzc().zzg())));
        }
        try {
            zzvi zzf2 = zzvi.zzf(zzopVar.zzc().zzf(), zzajx.zza());
            if (zzf2.zzb() == 0) {
                zzql zze2 = zzqp.zze();
                zze2.zzb(zzf2.zza());
                zze2.zzc(zzf2.zzg().zza());
                zze2.zza((zzqm) zzd.zzc(zzf2.zzg().zzb()));
                zze2.zzd((zzqn) zzc.zzc(zzopVar.zzc().zze()));
                return zze2.zze();
            }
            int zzb2 = zzf2.zzb();
            throw new GeneralSecurityException("Parsing HmacParameters failed: unknown Version " + zzb2);
        } catch (zzaks e) {
            throw new GeneralSecurityException("Parsing HmacParameters failed: ", e);
        }
    }

    public static void zzd(zznt zzntVar) throws GeneralSecurityException {
        zzntVar.zzh(zze);
        zzntVar.zzg(zzf);
        zzntVar.zzf(zzg);
        zzntVar.zze(zzh);
    }
}
