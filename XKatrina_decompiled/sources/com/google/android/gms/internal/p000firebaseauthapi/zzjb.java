package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjb  reason: invalid package */
/* loaded from: classes.dex */
final class zzjb {
    public static final /* synthetic */ int zza = 0;
    private static final zzzo zzb;
    private static final zzob zzc;
    private static final zznx zzd;
    private static final zzne zze;
    private static final zzna zzf;
    private static final Map zzg;
    private static final Map zzh;

    static {
        zzzo zzb2 = zzpd.zzb("type.googleapis.com/google.crypto.tink.AesSivKey");
        zzb = zzb2;
        zzc = zzob.zzb(new zznz() { // from class: com.google.android.gms.internal.firebase-auth-api.zzix
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznz
            public final zzot zza(zzce zzceVar) {
                return zzjb.zzc((zziw) zzceVar);
            }
        }, zziw.class, zzop.class);
        zzd = zznx.zzb(new zznv() { // from class: com.google.android.gms.internal.firebase-auth-api.zziy
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zznv
            public final zzce zza(zzot zzotVar) {
                return zzjb.zzb((zzop) zzotVar);
            }
        }, zzb2, zzop.class);
        zze = zzne.zza(new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zziz
        }, zzio.class, zzoo.class);
        zzf = zzna.zzb(new zzmy() { // from class: com.google.android.gms.internal.firebase-auth-api.zzja
            @Override // com.google.android.gms.internal.p000firebaseauthapi.zzmy
            public final zzbn zza(zzot zzotVar, zzcr zzcrVar) {
                return zzjb.zza((zzoo) zzotVar, zzcrVar);
            }
        }, zzb2, zzoo.class);
        HashMap hashMap = new HashMap();
        hashMap.put(zziu.zzc, zzxo.RAW);
        hashMap.put(zziu.zza, zzxo.TINK);
        hashMap.put(zziu.zzb, zzxo.CRUNCHY);
        zzg = Collections.unmodifiableMap(hashMap);
        EnumMap enumMap = new EnumMap(zzxo.class);
        enumMap.put((EnumMap) zzxo.RAW, (zzxo) zziu.zzc);
        enumMap.put((EnumMap) zzxo.TINK, (zzxo) zziu.zza);
        enumMap.put((EnumMap) zzxo.CRUNCHY, (zzxo) zziu.zzb);
        enumMap.put((EnumMap) zzxo.LEGACY, (zzxo) zziu.zzb);
        zzh = Collections.unmodifiableMap(enumMap);
    }

    public static /* synthetic */ zzio zza(zzoo zzooVar, zzcr zzcrVar) {
        if (!zzooVar.zzg().equals("type.googleapis.com/google.crypto.tink.AesSivKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesSivParameters.parseParameters");
        }
        try {
            zzts zzd2 = zzts.zzd(zzooVar.zze(), zzajx.zza());
            if (zzd2.zza() == 0) {
                zzit zzitVar = new zzit(null);
                zzitVar.zza(zzd2.zze().zzd());
                zzitVar.zzb(zze(zzooVar.zzc()));
                zziw zzc2 = zzitVar.zzc();
                zzim zzimVar = new zzim(null);
                zzimVar.zzc(zzc2);
                zzimVar.zzb(zzzq.zzb(zzd2.zze().zzq(), zzcrVar));
                zzimVar.zza(zzooVar.zzf());
                return zzimVar.zzd();
            }
            throw new GeneralSecurityException("Only version 0 keys are accepted");
        } catch (zzaks unused) {
            throw new GeneralSecurityException("Parsing AesSivKey failed");
        }
    }

    public static /* synthetic */ zziw zzb(zzop zzopVar) {
        if (!zzopVar.zzc().zzg().equals("type.googleapis.com/google.crypto.tink.AesSivKey")) {
            throw new IllegalArgumentException("Wrong type URL in call to AesSivParameters.parseParameters: ".concat(String.valueOf(zzopVar.zzc().zzg())));
        }
        try {
            zztv zze2 = zztv.zze(zzopVar.zzc().zzf(), zzajx.zza());
            if (zze2.zzb() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            zzit zzitVar = new zzit(null);
            zzitVar.zza(zze2.zza());
            zzitVar.zzb(zze(zzopVar.zzc().zze()));
            return zzitVar.zzc();
        } catch (zzaks e) {
            throw new GeneralSecurityException("Parsing AesSivParameters failed: ", e);
        }
    }

    public static /* synthetic */ zzop zzc(zziw zziwVar) {
        zzwm zza2 = zzwn.zza();
        zza2.zzb("type.googleapis.com/google.crypto.tink.AesSivKey");
        zztu zzc2 = zztv.zzc();
        zzc2.zza(zziwVar.zzb());
        zza2.zzc(((zztv) zzc2.zzi()).zzo());
        zziu zzd2 = zziwVar.zzd();
        Map map = zzg;
        if (map.containsKey(zzd2)) {
            zza2.zza((zzxo) map.get(zzd2));
            return zzop.zzb((zzwn) zza2.zzi());
        }
        throw new GeneralSecurityException("Unable to serialize variant: ".concat(String.valueOf(String.valueOf(zzd2))));
    }

    public static void zzd(zznt zzntVar) throws GeneralSecurityException {
        zzntVar.zzh(zzc);
        zzntVar.zzg(zzd);
        zzntVar.zzf(zze);
        zzntVar.zze(zzf);
    }

    private static zziu zze(zzxo zzxoVar) throws GeneralSecurityException {
        Map map = zzh;
        if (map.containsKey(zzxoVar)) {
            return (zziu) map.get(zzxoVar);
        }
        int zza2 = zzxoVar.zza();
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + zza2);
    }
}
