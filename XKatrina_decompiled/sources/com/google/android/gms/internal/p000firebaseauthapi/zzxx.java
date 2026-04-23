package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxx */
/* loaded from: classes.dex */
public final class zzxx extends zzakk implements zzalq {
    private static final zzxx zzb;
    private int zzd;

    static {
        zzxx zzxxVar = new zzxx();
        zzb = zzxxVar;
        zzakk.zzH(zzxx.class, zzxxVar);
    }

    private zzxx() {
    }

    public static zzxx zzc() {
        return zzb;
    }

    public static zzxx zzd(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzxx) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    public final int zza() {
        return this.zzd;
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
                    return new zzxw(null);
                }
                return new zzxx();
            }
            return zzE(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zzd"});
        }
        return (byte) 1;
    }
}
