package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvl */
/* loaded from: classes.dex */
public final class zzvl extends zzakk implements zzalq {
    private static final zzvl zzb;
    private int zzd;
    private int zze;

    static {
        zzvl zzvlVar = new zzvl();
        zzb = zzvlVar;
        zzakk.zzH(zzvl.class, zzvlVar);
    }

    private zzvl() {
    }

    public static zzvk zzc() {
        return (zzvk) zzb.zzt();
    }

    public static zzvl zze() {
        return zzb;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzvc zzb() {
        zzvc zzb2 = zzvc.zzb(this.zzd);
        return zzb2 == null ? zzvc.UNRECOGNIZED : zzb2;
    }

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
                    return new zzvk(null);
                }
                return new zzvl();
            }
            return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
