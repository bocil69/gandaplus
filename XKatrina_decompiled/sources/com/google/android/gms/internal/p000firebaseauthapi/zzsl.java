package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsl */
/* loaded from: classes.dex */
public final class zzsl extends zzakk implements zzalq {
    private static final zzsl zzb;
    private int zzd;
    private zzsr zze;
    private zzvi zzf;

    static {
        zzsl zzslVar = new zzsl();
        zzb = zzslVar;
        zzakk.zzH(zzsl.class, zzslVar);
    }

    private zzsl() {
    }

    public static zzsk zza() {
        return (zzsk) zzb.zzt();
    }

    public static zzsl zzc(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzsl) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    public static /* synthetic */ void zzf(zzsl zzslVar, zzsr zzsrVar) {
        zzsrVar.getClass();
        zzslVar.zze = zzsrVar;
        zzslVar.zzd |= 1;
    }

    public static /* synthetic */ void zzg(zzsl zzslVar, zzvi zzviVar) {
        zzviVar.getClass();
        zzslVar.zzf = zzviVar;
        zzslVar.zzd |= 2;
    }

    public final zzsr zzd() {
        zzsr zzsrVar = this.zze;
        return zzsrVar == null ? zzsr.zzd() : zzsrVar;
    }

    public final zzvi zze() {
        zzvi zzviVar = this.zzf;
        return zzviVar == null ? zzvi.zze() : zzviVar;
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
                    return new zzsk(null);
                }
                return new zzsl();
            }
            return zzE(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
