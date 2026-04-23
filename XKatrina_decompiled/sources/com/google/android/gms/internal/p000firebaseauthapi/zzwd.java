package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwd  reason: invalid package */
/* loaded from: classes.dex */
public final class zzwd extends zzakk implements zzalq {
    private static final zzwd zzb;
    private int zzd;
    private int zze;
    private zzvx zzf;
    private zzajf zzg = zzajf.zzb;

    static {
        zzwd zzwdVar = new zzwd();
        zzb = zzwdVar;
        zzakk.zzH(zzwd.class, zzwdVar);
    }

    private zzwd() {
    }

    public static zzwc zzc() {
        return (zzwc) zzb.zzt();
    }

    public static zzwd zze() {
        return zzb;
    }

    public static zzwd zzf(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzwd) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzwd zzwdVar, zzvx zzvxVar) {
        zzvxVar.getClass();
        zzwdVar.zzf = zzvxVar;
        zzwdVar.zzd |= 1;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzvx zzb() {
        zzvx zzvxVar = this.zzf;
        return zzvxVar == null ? zzvx.zzf() : zzvxVar;
    }

    public final zzajf zzg() {
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
                    return new zzwc(null);
                }
                return new zzwd();
            }
            return zzE(zzb, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final boolean zzl() {
        return (this.zzd & 1) != 0;
    }
}
