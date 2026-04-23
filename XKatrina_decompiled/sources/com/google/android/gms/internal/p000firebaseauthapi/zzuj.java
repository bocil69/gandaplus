package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuj */
/* loaded from: classes.dex */
public final class zzuj extends zzakk implements zzalq {
    private static final zzuj zzb;
    private int zzd;
    private zzum zze;

    static {
        zzuj zzujVar = new zzuj();
        zzb = zzujVar;
        zzakk.zzH(zzuj.class, zzujVar);
    }

    private zzuj() {
    }

    public static zzui zza() {
        return (zzui) zzb.zzt();
    }

    public static zzuj zzc(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzuj) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    public static /* synthetic */ void zze(zzuj zzujVar, zzum zzumVar) {
        zzumVar.getClass();
        zzujVar.zze = zzumVar;
        zzujVar.zzd |= 1;
    }

    public final zzum zzd() {
        zzum zzumVar = this.zze;
        return zzumVar == null ? zzum.zze() : zzumVar;
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
                    return new zzui(null);
                }
                return new zzuj();
            }
            return zzE(zzb, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
