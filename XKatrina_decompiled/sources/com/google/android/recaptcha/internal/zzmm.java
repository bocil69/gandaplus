package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzmm extends zzgo implements zzhz {
    private static final zzmm zzb;
    private int zzd = 0;
    private Object zze;
    private int zzf;

    static {
        zzmm zzmmVar = new zzmm();
        zzb = zzmmVar;
        zzgo.zzC(zzmm.class, zzmmVar);
    }

    private zzmm() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzG(zzmm zzmmVar, String str) {
        str.getClass();
        zzmmVar.zzd = 11;
        zzmmVar.zze = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzH(zzmm zzmmVar, boolean z) {
        zzmmVar.zzd = 1;
        zzmmVar.zze = Boolean.valueOf(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzI(zzmm zzmmVar, zzez zzezVar) {
        zzmmVar.zzd = 2;
        zzmmVar.zze = zzezVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzJ(zzmm zzmmVar, String str) {
        str.getClass();
        zzmmVar.zzd = 3;
        zzmmVar.zze = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzK(zzmm zzmmVar, int i) {
        zzmmVar.zzd = 4;
        zzmmVar.zze = Integer.valueOf(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzL(zzmm zzmmVar, int i) {
        zzmmVar.zzd = 5;
        zzmmVar.zze = Integer.valueOf(i);
    }

    public static zzml zzf() {
        return (zzml) zzb.zzp();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzmm zzmmVar, long j) {
        zzmmVar.zzd = 7;
        zzmmVar.zze = Long.valueOf(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzj(zzmm zzmmVar, float f) {
        zzmmVar.zzd = 9;
        zzmmVar.zze = Float.valueOf(f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzk(zzmm zzmmVar, double d) {
        zzmmVar.zzd = 10;
        zzmmVar.zze = Double.valueOf(d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.recaptcha.internal.zzgo
    public final Object zzh(int i, Object obj, Object obj2) {
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
                    return new zzml(null);
                }
                return new zzmm();
            }
            return zzz(zzb, "\u0000\f\u0001\u0000\u0001\f\f\u0000\u0000\u0000\u0001:\u0000\u0002=\u0000\u0003Ȼ\u0000\u0004B\u0000\u0005B\u0000\u0006>\u0000\u0007C\u0000\b6\u0000\t4\u0000\n3\u0000\u000bȻ\u0000\f\u000b", new Object[]{"zze", "zzd", "zzf"});
        }
        return (byte) 1;
    }
}
