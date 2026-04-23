package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzsc */
/* loaded from: classes.dex */
public final class zzsc extends zzakk implements zzalq {
    private static final zzsc zzb;
    private int zzd;
    private int zze;
    private zzsf zzf;

    static {
        zzsc zzscVar = new zzsc();
        zzb = zzscVar;
        zzakk.zzH(zzsc.class, zzscVar);
    }

    private zzsc() {
    }

    public static zzsb zzb() {
        return (zzsb) zzb.zzt();
    }

    public static zzsc zzd(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzsc) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    public static /* synthetic */ void zzg(zzsc zzscVar, zzsf zzsfVar) {
        zzsfVar.getClass();
        zzscVar.zzf = zzsfVar;
        zzscVar.zzd |= 1;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzsf zze() {
        zzsf zzsfVar = this.zzf;
        return zzsfVar == null ? zzsf.zzd() : zzsfVar;
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
                    return new zzsb(null);
                }
                return new zzsc();
            }
            return zzE(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
