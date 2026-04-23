package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzmd extends zzgo implements zzhz {
    private static final zzmd zzb;
    private int zzd;

    static {
        zzmd zzmdVar = new zzmd();
        zzb = zzmdVar;
        zzgo.zzC(zzmd.class, zzmdVar);
    }

    private zzmd() {
    }

    public static zzmd zzg(byte[] bArr) throws zzgy {
        return (zzmd) zzgo.zzu(zzb, bArr);
    }

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
                    return new zzmc(null);
                }
                return new zzmd();
            }
            return zzz(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"zzd"});
        }
        return (byte) 1;
    }

    public final zzmf zzi() {
        zzmf zzb2 = zzmf.zzb(this.zzd);
        return zzb2 == null ? zzmf.UNRECOGNIZED : zzb2;
    }
}
