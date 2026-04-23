package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlf  reason: invalid package */
/* loaded from: classes.dex */
public final class zzlf {
    public static final zzwn zza;
    public static final zzwn zzb;
    public static final zzwn zzc;
    private static final byte[] zzd;

    static {
        byte[] bArr = new byte[0];
        zzd = bArr;
        zza = zza(zzux.NIST_P256, zzvc.SHA256, zzud.UNCOMPRESSED, zzcw.zza, zzxo.TINK, bArr);
        zzb = zza(zzux.NIST_P256, zzvc.SHA256, zzud.COMPRESSED, zzcw.zza, zzxo.RAW, bArr);
        zzc = zza(zzux.NIST_P256, zzvc.SHA256, zzud.UNCOMPRESSED, zzcw.zze, zzxo.TINK, bArr);
    }

    @Deprecated
    static zzwn zza(zzux zzuxVar, zzvc zzvcVar, zzud zzudVar, zzwn zzwnVar, zzxo zzxoVar, byte[] bArr) {
        zzui zza2 = zzuj.zza();
        zzuu zza3 = zzuv.zza();
        zza3.zza(zzuxVar);
        zza3.zzb(zzvcVar);
        zza3.zzc(zzajf.zzn(bArr, 0, 0));
        zzuf zza4 = zzug.zza();
        zza4.zza(zzwnVar);
        zzul zzc2 = zzum.zzc();
        zzc2.zzc((zzuv) zza3.zzi());
        zzc2.zza((zzug) zza4.zzi());
        zzc2.zzb(zzudVar);
        zza2.zza((zzum) zzc2.zzi());
        zzwm zza5 = zzwn.zza();
        new zzjl();
        zza5.zzb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey");
        zza5.zza(zzxoVar);
        zza5.zzc(((zzuj) zza2.zzi()).zzo());
        return (zzwn) zza5.zzi();
    }
}
