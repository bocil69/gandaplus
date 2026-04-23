package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwz */
/* loaded from: classes.dex */
public final class zzwz extends zzakk implements zzalq {
    private static final zzwz zzb;
    private String zzd = "";
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzwz zzwzVar = new zzwz();
        zzb = zzwzVar;
        zzakk.zzH(zzwz.class, zzwzVar);
    }

    private zzwz() {
    }

    public static zzwy zzb() {
        return (zzwy) zzb.zzt();
    }

    public static /* synthetic */ void zzd(zzwz zzwzVar, String str) {
        str.getClass();
        zzwzVar.zzd = str;
    }

    public final int zza() {
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
                    return new zzwy(null);
                }
                return new zzwz();
            }
            return zzE(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }
}
