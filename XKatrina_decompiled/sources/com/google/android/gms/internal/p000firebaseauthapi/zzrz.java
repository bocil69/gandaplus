package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrz */
/* loaded from: classes.dex */
public final class zzrz extends zzakk implements zzalq {
    private static final zzrz zzb;
    private int zzd;
    private int zze;
    private zzajf zzf = zzajf.zzb;
    private zzsf zzg;

    static {
        zzrz zzrzVar = new zzrz();
        zzb = zzrzVar;
        zzakk.zzH(zzrz.class, zzrzVar);
    }

    private zzrz() {
    }

    public static zzry zzb() {
        return (zzry) zzb.zzt();
    }

    public static zzrz zzd(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzrz) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    public static /* synthetic */ void zzi(zzrz zzrzVar, zzsf zzsfVar) {
        zzsfVar.getClass();
        zzrzVar.zzg = zzsfVar;
        zzrzVar.zzd |= 1;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzsf zze() {
        zzsf zzsfVar = this.zzg;
        return zzsfVar == null ? zzsf.zzd() : zzsfVar;
    }

    public final zzajf zzf() {
        return this.zzf;
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
                    return new zzry(null);
                }
                return new zzrz();
            }
            return zzE(zzb, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n\u0003ဉ\u0000", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }
}
