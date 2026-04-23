package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzts  reason: invalid package */
/* loaded from: classes.dex */
public final class zzts extends zzakk implements zzalq {
    private static final zzts zzb;
    private int zzd;
    private zzajf zze = zzajf.zzb;

    static {
        zzts zztsVar = new zzts();
        zzb = zztsVar;
        zzakk.zzH(zzts.class, zztsVar);
    }

    private zzts() {
    }

    public static zztr zzb() {
        return (zztr) zzb.zzt();
    }

    public static zzts zzd(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzts) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzajf zze() {
        return this.zze;
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
                    return new zztr(null);
                }
                return new zzts();
            }
            return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
