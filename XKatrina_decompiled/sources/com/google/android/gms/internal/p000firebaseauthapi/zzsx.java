package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsx  reason: invalid package */
/* loaded from: classes.dex */
public final class zzsx extends zzakk implements zzalq {
    private static final zzsx zzb;
    private int zzd;
    private int zze;
    private zztd zzf;
    private zzajf zzg = zzajf.zzb;

    static {
        zzsx zzsxVar = new zzsx();
        zzb = zzsxVar;
        zzakk.zzH(zzsx.class, zzsxVar);
    }

    private zzsx() {
    }

    public static zzsw zzb() {
        return (zzsw) zzb.zzt();
    }

    public static zzsx zzd(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzsx) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh(zzsx zzsxVar, zztd zztdVar) {
        zztdVar.getClass();
        zzsxVar.zzf = zztdVar;
        zzsxVar.zzd |= 1;
    }

    public final int zza() {
        return this.zze;
    }

    public final zztd zze() {
        zztd zztdVar = this.zzf;
        return zztdVar == null ? zztd.zzd() : zztdVar;
    }

    public final zzajf zzf() {
        return this.zzg;
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
                    return new zzsw(null);
                }
                return new zzsx();
            }
            return zzE(zzb, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }
}
