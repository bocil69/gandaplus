package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzvu */
/* loaded from: classes.dex */
public final class zzvu extends zzakk implements zzalq {
    private static final zzvu zzb;
    private int zzd;
    private zzvx zze;

    static {
        zzvu zzvuVar = new zzvu();
        zzb = zzvuVar;
        zzakk.zzH(zzvu.class, zzvuVar);
    }

    private zzvu() {
    }

    public static zzvt zza() {
        return (zzvt) zzb.zzt();
    }

    public static zzvu zzc(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzvu) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    public static /* synthetic */ void zze(zzvu zzvuVar, zzvx zzvxVar) {
        zzvxVar.getClass();
        zzvuVar.zze = zzvxVar;
        zzvuVar.zzd |= 1;
    }

    public final zzvx zzd() {
        zzvx zzvxVar = this.zze;
        return zzvxVar == null ? zzvx.zzf() : zzvxVar;
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
                    return new zzvt(null);
                }
                return new zzvu();
            }
            return zzE(zzb, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
