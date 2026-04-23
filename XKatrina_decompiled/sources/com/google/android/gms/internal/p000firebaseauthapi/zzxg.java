package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzxg  reason: invalid package */
/* loaded from: classes.dex */
public final class zzxg extends zzakk implements zzalq {
    private static final zzxg zzb;
    private String zzd = "";

    static {
        zzxg zzxgVar = new zzxg();
        zzb = zzxgVar;
        zzakk.zzH(zzxg.class, zzxgVar);
    }

    private zzxg() {
    }

    public static zzxf zza() {
        return (zzxf) zzb.zzt();
    }

    public static zzxg zzc() {
        return zzb;
    }

    public static zzxg zzd(zzajf zzajfVar, zzajx zzajxVar) throws zzaks {
        return (zzxg) zzakk.zzx(zzb, zzajfVar, zzajxVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzf(zzxg zzxgVar, String str) {
        str.getClass();
        zzxgVar.zzd = str;
    }

    public final String zze() {
        return this.zzd;
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
                    return new zzxf(null);
                }
                return new zzxg();
            }
            return zzE(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"zzd"});
        }
        return (byte) 1;
    }
}
