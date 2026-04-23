package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztj */
/* loaded from: classes.dex */
public final class zztj extends zzakk implements zzalq {
    private static final zztj zzb;
    private int zzd;
    private int zze;

    static {
        zztj zztjVar = new zztj();
        zzb = zztjVar;
        zzakk.zzH(zztj.class, zztjVar);
    }

    private zztj() {
    }

    public static zzti zzc() {
        return (zzti) zzb.zzt();
    }

    public static zztj zze(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zztj) zzakk.zzx(zzb, zzajfVar, zzajxVar);
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
                    return new zzti(null);
                }
                return new zztj();
            }
            return zzE(zzb, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\u000b\u0003\u000b", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
