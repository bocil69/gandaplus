package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsu */
/* loaded from: classes.dex */
public final class zzsu extends zzakk implements zzalq {
    private static final zzsu zzb;
    private int zzd;

    static {
        zzsu zzsuVar = new zzsu();
        zzb = zzsuVar;
        zzakk.zzH(zzsu.class, zzsuVar);
    }

    private zzsu() {
    }

    public static zzst zzb() {
        return (zzst) zzb.zzt();
    }

    public static zzsu zzd() {
        return zzb;
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
                    return new zzst(null);
                }
                return new zzsu();
            }
            return zzE(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zzd"});
        }
        return (byte) 1;
    }
}
