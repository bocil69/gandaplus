package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztv */
/* loaded from: classes.dex */
public final class zztv extends zzakk implements zzalq {
    private static final zztv zzb;
    private int zzd;
    private int zze;

    static {
        zztv zztvVar = new zztv();
        zzb = zztvVar;
        zzakk.zzH(zztv.class, zztvVar);
    }

    private zztv() {
    }

    public static zztu zzc() {
        return (zztu) zzb.zzt();
    }

    public static zztv zze(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zztv) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    public final int zza() {
        return this.zzd;
    }

    public final int zzb() {
        return this.zze;
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
                    return new zztu(null);
                }
                return new zztv();
            }
            return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\u000b", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
