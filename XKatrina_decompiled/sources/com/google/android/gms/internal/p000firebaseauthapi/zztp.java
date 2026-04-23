package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztp */
/* loaded from: classes.dex */
public final class zztp extends zzakk implements zzalq {
    private static final zztp zzb;
    private int zzd;
    private int zze;

    static {
        zztp zztpVar = new zztp();
        zzb = zztpVar;
        zzakk.zzH(zztp.class, zztpVar);
    }

    private zztp() {
    }

    public static zzto zzc() {
        return (zzto) zzb.zzt();
    }

    public static zztp zze(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zztp) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    public final int zza() {
        return this.zzd;
    }

    public final int zzb() {
        return this.zze;
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
                    return new zzto(null);
                }
                return new zztp();
            }
            return zzE(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\u000b", new Object[]{"zze", "zzd"});
        }
        return (byte) 1;
    }
}
