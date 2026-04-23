package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcw  reason: invalid package */
/* loaded from: classes.dex */
public final class zzcw {
    public static final zzwn zza = zzc(16);
    public static final zzwn zzb = zzc(32);
    public static final zzwn zzc = zzb(16, 16);
    public static final zzwn zzd = zzb(32, 16);
    public static final zzwn zze = zza(16, 16, 32, 16, zzvc.SHA256);
    public static final zzwn zzf = zza(32, 16, 32, 32, zzvc.SHA256);
    public static final zzwn zzg;
    public static final zzwn zzh;

    static {
        zzwm zza2 = zzwn.zza();
        new zzfy();
        zza2.zzb("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
        zza2.zza(zzxo.TINK);
        zzg = (zzwn) zza2.zzi();
        zzwm zza3 = zzwn.zza();
        new zzhp();
        zza3.zzb("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        zza3.zza(zzxo.TINK);
        zzh = (zzwn) zza3.zzi();
    }

    public static zzwn zza(int i, int i2, int i3, int i4, zzvc zzvcVar) {
        zzsq zzb2 = zzsr.zzb();
        zzst zzb3 = zzsu.zzb();
        zzb3.zza(16);
        zzb2.zzb((zzsu) zzb3.zzi());
        zzb2.zza(i);
        zzvh zzc2 = zzvi.zzc();
        zzvk zzc3 = zzvl.zzc();
        zzc3.zza(zzvcVar);
        zzc3.zzb(i4);
        zzc2.zzb((zzvl) zzc3.zzi());
        zzc2.zza(32);
        zzsk zza2 = zzsl.zza();
        zza2.zza((zzsr) zzb2.zzi());
        zza2.zzb((zzvi) zzc2.zzi());
        zzwm zza3 = zzwn.zza();
        zza3.zzc(((zzsl) zza2.zzi()).zzo());
        new zzdh();
        zza3.zzb("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        zza3.zza(zzxo.TINK);
        return (zzwn) zza3.zzi();
    }

    public static zzwn zzb(int i, int i2) {
        zzsz zzb2 = zzta.zzb();
        zzb2.zza(i);
        zztc zzb3 = zztd.zzb();
        zzb3.zza(16);
        zzb2.zzb((zztd) zzb3.zzi());
        zzwm zza2 = zzwn.zza();
        zza2.zzc(((zzta) zzb2.zzi()).zzo());
        new zzec();
        zza2.zzb("type.googleapis.com/google.crypto.tink.AesEaxKey");
        zza2.zza(zzxo.TINK);
        return (zzwn) zza2.zzi();
    }

    public static zzwn zzc(int i) {
        zzti zzc2 = zztj.zzc();
        zzc2.zza(i);
        zzwm zza2 = zzwn.zza();
        zza2.zzc(((zztj) zzc2.zzi()).zzo());
        new zzet();
        zza2.zzb("type.googleapis.com/google.crypto.tink.AesGcmKey");
        zza2.zza(zzxo.TINK);
        return (zzwn) zza2.zzi();
    }
}
