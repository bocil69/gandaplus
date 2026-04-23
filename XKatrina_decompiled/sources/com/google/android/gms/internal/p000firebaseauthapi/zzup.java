package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzup */
/* loaded from: classes.dex */
public final class zzup extends zzakk implements zzalq {
    private static final zzup zzb;
    private int zzd;
    private int zze;
    private zzus zzf;
    private zzajf zzg = zzajf.zzb;

    static {
        zzup zzupVar = new zzup();
        zzb = zzupVar;
        zzakk.zzH(zzup.class, zzupVar);
    }

    private zzup() {
    }

    public static zzuo zzb() {
        return (zzuo) zzb.zzt();
    }

    public static zzup zzd(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzup) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    public static /* synthetic */ void zzh(zzup zzupVar, zzus zzusVar) {
        zzusVar.getClass();
        zzupVar.zzf = zzusVar;
        zzupVar.zzd |= 1;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzus zze() {
        zzus zzusVar = this.zzf;
        return zzusVar == null ? zzus.zze() : zzusVar;
    }

    public final zzajf zzf() {
        return this.zzg;
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
                    return new zzuo(null);
                }
                return new zzup();
            }
            return zzE(zzb, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }
}
