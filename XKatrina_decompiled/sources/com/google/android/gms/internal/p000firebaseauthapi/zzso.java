package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzso  reason: invalid package */
/* loaded from: classes.dex */
public final class zzso extends zzakk implements zzalq {
    private static final zzso zzb;
    private int zzd;
    private int zze;
    private zzsu zzf;
    private zzajf zzg = zzajf.zzb;

    static {
        zzso zzsoVar = new zzso();
        zzb = zzsoVar;
        zzakk.zzH(zzso.class, zzsoVar);
    }

    private zzso() {
    }

    public static zzsn zzb() {
        return (zzsn) zzb.zzt();
    }

    public static zzso zzd() {
        return zzb;
    }

    public static zzso zze(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzso) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzso zzsoVar, zzsu zzsuVar) {
        zzsuVar.getClass();
        zzsoVar.zzf = zzsuVar;
        zzsoVar.zzd |= 1;
    }

    public final int zza() {
        return this.zze;
    }

    public final zzsu zzf() {
        zzsu zzsuVar = this.zzf;
        return zzsuVar == null ? zzsu.zzd() : zzsuVar;
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
                    return new zzsn(null);
                }
                return new zzso();
            }
            return zzE(zzb, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002ဉ\u0000\u0003\n", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }
}
