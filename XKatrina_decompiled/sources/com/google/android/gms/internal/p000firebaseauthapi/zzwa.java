package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwa  reason: invalid package */
/* loaded from: classes.dex */
public final class zzwa extends zzakk implements zzalq {
    private static final zzwa zzb;
    private int zzd;
    private int zze;
    private zzwd zzf;
    private zzajf zzg = zzajf.zzb;

    static {
        zzwa zzwaVar = new zzwa();
        zzb = zzwaVar;
        zzakk.zzH(zzwa.class, zzwaVar);
    }

    private zzwa() {
    }

    public static zzvz zzb() {
        return (zzvz) zzb.zzt();
    }

    public static zzwa zzd(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzwa) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh(zzwa zzwaVar, zzwd zzwdVar) {
        zzwdVar.getClass();
        zzwaVar.zzf = zzwdVar;
        zzwaVar.zzd |= 1;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzwd zze() {
        zzwd zzwdVar = this.zzf;
        return zzwdVar == null ? zzwd.zze() : zzwdVar;
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
                    return new zzvz(null);
                }
                return new zzwa();
            }
            return zzE(zzb, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final boolean zzk() {
        return (this.zzd & 1) != 0;
    }
}
