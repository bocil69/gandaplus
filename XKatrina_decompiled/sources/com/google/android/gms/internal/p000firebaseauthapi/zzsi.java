package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsi */
/* loaded from: classes.dex */
public final class zzsi extends zzakk implements zzalq {
    private static final zzsi zzb;
    private int zzd;
    private int zze;
    private zzso zzf;
    private zzvf zzg;

    static {
        zzsi zzsiVar = new zzsi();
        zzb = zzsiVar;
        zzakk.zzH(zzsi.class, zzsiVar);
    }

    private zzsi() {
    }

    public static zzsh zzb() {
        return (zzsh) zzb.zzt();
    }

    public static zzsi zzd(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzsi) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    public static /* synthetic */ void zzh(zzsi zzsiVar, zzso zzsoVar) {
        zzsoVar.getClass();
        zzsiVar.zzf = zzsoVar;
        zzsiVar.zzd |= 1;
    }

    public static /* synthetic */ void zzi(zzsi zzsiVar, zzvf zzvfVar) {
        zzvfVar.getClass();
        zzsiVar.zzg = zzvfVar;
        zzsiVar.zzd |= 2;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzso zze() {
        zzso zzsoVar = this.zzf;
        return zzsoVar == null ? zzso.zzd() : zzsoVar;
    }

    public final zzvf zzf() {
        zzvf zzvfVar = this.zzg;
        return zzvfVar == null ? zzvf.zzd() : zzvfVar;
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
                    return new zzsh(null);
                }
                return new zzsi();
            }
            return zzE(zzb, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003ဉ\u0001", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }
}
