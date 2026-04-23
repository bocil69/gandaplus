package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcu  reason: invalid package */
/* loaded from: classes.dex */
public final class zzcu {
    public static final String zza;
    public static final String zzb;
    @Deprecated
    static final zzxr zzc;
    @Deprecated
    static final zzxr zzd;
    @Deprecated
    static final zzxr zze;

    static {
        new zzdh();
        zza = "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
        new zzet();
        zzb = "type.googleapis.com/google.crypto.tink.AesGcmKey";
        new zzfk();
        new zzec();
        new zzgi();
        new zzgm();
        new zzfy();
        new zzhp();
        zzxr zzb2 = zzxr.zzb();
        zzc = zzb2;
        zzd = zzb2;
        zze = zzb2;
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzda.zzd();
        zzqq.zza();
        zzcq.zzg(new zzdh(), true);
        int i = zzds.zza;
        zzds.zzc(zznt.zzc());
        zzcq.zzg(new zzet(), true);
        int i2 = zzfd.zza;
        zzfd.zzc(zznt.zzc());
        if (zzik.zzb()) {
            return;
        }
        zzcq.zzg(new zzec(), true);
        int i3 = zzem.zza;
        zzem.zzc(zznt.zzc());
        zzfk.zzg(true);
        zzcq.zzg(new zzfy(), true);
        int i4 = zzgf.zza;
        zzgf.zzc(zznt.zzc());
        zzcq.zzg(new zzgi(), true);
        int i5 = zzgt.zza;
        zzgt.zza(zznt.zzc());
        zzcq.zzg(new zzgm(), true);
        int i6 = zzhe.zza;
        zzhe.zzc(zznt.zzc());
        zzcq.zzg(new zzhp(), true);
        int i7 = zzhw.zza;
        zzhw.zzc(zznt.zzc());
    }
}
