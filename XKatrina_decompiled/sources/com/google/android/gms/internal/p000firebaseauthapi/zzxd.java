package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxd  reason: invalid package */
/* loaded from: classes.dex */
public final class zzxd extends zzakk implements zzalq {
    private static final zzxd zzb;
    private int zzd;
    private int zze;
    private zzxg zzf;

    static {
        zzxd zzxdVar = new zzxd();
        zzb = zzxdVar;
        zzakk.zzH(zzxd.class, zzxdVar);
    }

    private zzxd() {
    }

    public static zzxc zzb() {
        return (zzxc) zzb.zzt();
    }

    public static zzxd zzd(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzxd) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzg(zzxd zzxdVar, zzxg zzxgVar) {
        zzxgVar.getClass();
        zzxdVar.zzf = zzxgVar;
        zzxdVar.zzd |= 1;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzxg zze() {
        zzxg zzxgVar = this.zzf;
        return zzxgVar == null ? zzxg.zzc() : zzxgVar;
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
                    return new zzxc(null);
                }
                return new zzxd();
            }
            return zzE(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
