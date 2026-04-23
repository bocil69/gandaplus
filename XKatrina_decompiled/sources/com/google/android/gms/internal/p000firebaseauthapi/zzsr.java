package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsr */
/* loaded from: classes.dex */
public final class zzsr extends zzakk implements zzalq {
    private static final zzsr zzb;
    private int zzd;
    private zzsu zze;
    private int zzf;

    static {
        zzsr zzsrVar = new zzsr();
        zzb = zzsrVar;
        zzakk.zzH(zzsr.class, zzsrVar);
    }

    private zzsr() {
    }

    public static zzsq zzb() {
        return (zzsq) zzb.zzt();
    }

    public static zzsr zzd() {
        return zzb;
    }

    public static zzsr zze(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzsr) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    public static /* synthetic */ void zzg(zzsr zzsrVar, zzsu zzsuVar) {
        zzsuVar.getClass();
        zzsrVar.zze = zzsuVar;
        zzsrVar.zzd |= 1;
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzsu zzf() {
        zzsu zzsuVar = this.zze;
        return zzsuVar == null ? zzsu.zzd() : zzsuVar;
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
                    return new zzsq(null);
                }
                return new zzsr();
            }
            return zzE(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u000b", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
