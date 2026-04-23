package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwi  reason: invalid package */
/* loaded from: classes.dex */
public final class zzwi extends zzakk implements zzalq {
    private static final zzwi zzb;
    private String zzd = "";
    private zzajf zze = zzajf.zzb;
    private int zzf;

    static {
        zzwi zzwiVar = new zzwi();
        zzb = zzwiVar;
        zzakk.zzH(zzwi.class, zzwiVar);
    }

    private zzwi() {
    }

    public static zzwf zza() {
        return (zzwf) zzb.zzt();
    }

    public static zzwi zzd() {
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzg(zzwi zzwiVar, String str) {
        str.getClass();
        zzwiVar.zzd = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh(zzwi zzwiVar, zzajf zzajfVar) {
        zzajfVar.getClass();
        zzwiVar.zze = zzajfVar;
    }

    public final zzwh zzb() {
        int i = this.zzf;
        zzwh zzwhVar = zzwh.UNKNOWN_KEYMATERIAL;
        zzwh zzwhVar2 = i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? null : zzwh.REMOTE : zzwh.ASYMMETRIC_PUBLIC : zzwh.ASYMMETRIC_PRIVATE : zzwh.SYMMETRIC : zzwh.UNKNOWN_KEYMATERIAL;
        return zzwhVar2 == null ? zzwh.UNRECOGNIZED : zzwhVar2;
    }

    public final zzajf zze() {
        return this.zze;
    }

    public final String zzf() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzakk
    protected final Object zzj(int i, Object obj, Object obj2) {
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
                    return new zzwf(null);
                }
                return new zzwi();
            }
            return zzE(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
