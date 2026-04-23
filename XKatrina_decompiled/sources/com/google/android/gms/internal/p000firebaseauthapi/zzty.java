package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzty  reason: invalid package */
/* loaded from: classes.dex */
public final class zzty extends zzakk implements zzalq {
    private static final zzty zzb;
    private int zzd;
    private zzajf zze = zzajf.zzb;

    static {
        zzty zztyVar = new zzty();
        zzb = zztyVar;
        zzakk.zzH(zzty.class, zztyVar);
    }

    private zzty() {
    }

    public static zztx zzb() {
        return (zztx) zzb.zzt();
    }

    public static zzty zzd(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzty) zzakk.zzx(zzb, zzajfVar, zzajxVar);
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
                    return new zztx(null);
                }
                return new zzty();
            }
            return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
