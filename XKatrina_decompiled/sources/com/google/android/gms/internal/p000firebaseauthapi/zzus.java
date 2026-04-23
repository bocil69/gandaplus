package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzus  reason: invalid package */
/* loaded from: classes.dex */
public final class zzus extends zzakk implements zzalq {
    private static final zzus zzb;
    private int zzd;
    private int zze;
    private zzum zzf;
    private zzajf zzg = zzajf.zzb;
    private zzajf zzh = zzajf.zzb;

    static {
        zzus zzusVar = new zzus();
        zzb = zzusVar;
        zzakk.zzH(zzus.class, zzusVar);
    }

    private zzus() {
    }

    public static zzur zzc() {
        return (zzur) zzb.zzt();
    }

    public static zzus zze() {
        return zzb;
    }

    public static zzus zzf(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzus) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzk(zzus zzusVar, zzum zzumVar) {
        zzumVar.getClass();
        zzusVar.zzf = zzumVar;
        zzusVar.zzd |= 1;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzum zzb() {
        zzum zzumVar = this.zzf;
        return zzumVar == null ? zzum.zze() : zzumVar;
    }

    public final zzajf zzg() {
        return this.zzg;
    }

    public final zzajf zzh() {
        return this.zzh;
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
                    return new zzur(null);
                }
                return new zzus();
            }
            return zzE(zzb, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n\u0004\n", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        }
        return (byte) 1;
    }
}
