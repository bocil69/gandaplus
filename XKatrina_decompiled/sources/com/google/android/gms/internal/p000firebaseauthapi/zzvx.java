package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvx  reason: invalid package */
/* loaded from: classes.dex */
public final class zzvx extends zzakk implements zzalq {
    private static final zzvx zzb;
    private int zzd;
    private int zze;
    private int zzf;

    static {
        zzvx zzvxVar = new zzvx();
        zzb = zzvxVar;
        zzakk.zzH(zzvx.class, zzvxVar);
    }

    private zzvx() {
    }

    public static zzvw zzd() {
        return (zzvw) zzb.zzt();
    }

    public static zzvx zzf() {
        return zzb;
    }

    public final zzvn zza() {
        int i = this.zzf;
        zzvn zzvnVar = zzvn.AEAD_UNKNOWN;
        zzvn zzvnVar2 = i != 0 ? i != 1 ? i != 2 ? i != 3 ? null : zzvn.CHACHA20_POLY1305 : zzvn.AES_256_GCM : zzvn.AES_128_GCM : zzvn.AEAD_UNKNOWN;
        return zzvnVar2 == null ? zzvn.UNRECOGNIZED : zzvnVar2;
    }

    public final zzvp zzb() {
        int i = this.zze;
        zzvp zzvpVar = zzvp.KDF_UNKNOWN;
        zzvp zzvpVar2 = i != 0 ? i != 1 ? i != 2 ? i != 3 ? null : zzvp.HKDF_SHA512 : zzvp.HKDF_SHA384 : zzvp.HKDF_SHA256 : zzvp.KDF_UNKNOWN;
        return zzvpVar2 == null ? zzvp.UNRECOGNIZED : zzvpVar2;
    }

    public final zzvr zzc() {
        int i = this.zzd;
        zzvr zzvrVar = zzvr.KEM_UNKNOWN;
        zzvr zzvrVar2 = i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? null : zzvr.DHKEM_P521_HKDF_SHA512 : zzvr.DHKEM_P384_HKDF_SHA384 : zzvr.DHKEM_P256_HKDF_SHA256 : zzvr.DHKEM_X25519_HKDF_SHA256 : zzvr.KEM_UNKNOWN;
        return zzvrVar2 == null ? zzvr.UNRECOGNIZED : zzvrVar2;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzakk
    protected final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzvw(null);
                }
                return new zzvx();
            }
            return zzE(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u0003\f", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
