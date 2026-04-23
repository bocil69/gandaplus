package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamt  reason: invalid package */
/* loaded from: classes.dex */
public final class zzamt extends zzakk implements zzalq {
    private static final zzamt zzb;
    private long zzd;
    private int zze;

    static {
        zzamt zzamtVar = new zzamt();
        zzb = zzamtVar;
        zzakk.zzH(zzamt.class, zzamtVar);
    }

    private zzamt() {
    }

    public static zzams zzc() {
        return (zzams) zzb.zzt();
    }

    public final int zza() {
        return this.zze;
    }

    public final long zzb() {
        return this.zzd;
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
                    return new zzams(null);
                }
                return new zzamt();
            }
            return new zzalz(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
