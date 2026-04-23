package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzub */
/* loaded from: classes.dex */
public final class zzub extends zzakk implements zzalq {
    private static final zzub zzb;

    static {
        zzub zzubVar = new zzub();
        zzb = zzubVar;
        zzakk.zzH(zzub.class, zzubVar);
    }

    private zzub() {
    }

    public static zzub zzb() {
        return zzb;
    }

    public static zzub zzc(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzub) zzakk.zzx(zzb, zzajfVar, zzajxVar);
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
                    return new zzua(null);
                }
                return new zzub();
            }
            return zzE(zzb, "\u0000\u0000", null);
        }
        return (byte) 1;
    }
}
