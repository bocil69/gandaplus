package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzkg extends zzgo implements zzhz {
    private static final zzkg zzb;
    private int zzd;
    private zzfw zze;
    private int zzf;

    static {
        zzkg zzkgVar = new zzkg();
        zzb = zzkgVar;
        zzgo.zzC(zzkg.class, zzkgVar);
    }

    private zzkg() {
    }

    public static zzkf zzf() {
        return (zzkf) zzb.zzp();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzkg zzkgVar, zzfw zzfwVar) {
        zzfwVar.getClass();
        zzkgVar.zze = zzfwVar;
        zzkgVar.zzd |= 1;
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
                    return new zzkf(null);
                }
                return new zzkg();
            }
            return zzz(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u0004", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
