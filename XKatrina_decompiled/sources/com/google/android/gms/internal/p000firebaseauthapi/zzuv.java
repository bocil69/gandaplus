package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuv  reason: invalid package */
/* loaded from: classes.dex */
public final class zzuv extends zzakk implements zzalq {
    private static final zzuv zzb;
    private int zzd;
    private int zze;
    private zzajf zzf = zzajf.zzb;

    static {
        zzuv zzuvVar = new zzuv();
        zzb = zzuvVar;
        zzakk.zzH(zzuv.class, zzuvVar);
    }

    private zzuv() {
    }

    public static zzuu zza() {
        return (zzuu) zzb.zzt();
    }

    public static zzuv zzc() {
        return zzb;
    }

    public final zzux zzd() {
        int i = this.zzd;
        zzux zzuxVar = zzux.UNKNOWN_CURVE;
        zzux zzuxVar2 = i != 0 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? null : zzux.CURVE25519 : zzux.NIST_P521 : zzux.NIST_P384 : zzux.NIST_P256 : zzux.UNKNOWN_CURVE;
        return zzuxVar2 == null ? zzux.UNRECOGNIZED : zzuxVar2;
    }

    public final zzvc zze() {
        zzvc zzb2 = zzvc.zzb(this.zze);
        return zzb2 == null ? zzvc.UNRECOGNIZED : zzb2;
    }

    public final zzajf zzf() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzakk
    public final Object zzj(int i, Object obj, Object obj2) {
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
                    return new zzuu(null);
                }
                return new zzuv();
            }
            return zzE(zzb, "\u0000\u0003\u0000\u0000\u0001\u000b\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
