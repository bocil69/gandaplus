package com.google.android.recaptcha.internal;

import java.util.List;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzlp extends zzgo implements zzhz {
    private static final zzlp zzb;
    private zzgv zzd = zzgo.zzw();

    static {
        zzlp zzlpVar = new zzlp();
        zzb = zzlpVar;
        zzgo.zzC(zzlp.class, zzlpVar);
    }

    private zzlp() {
    }

    public static zzlp zzg() {
        return zzb;
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
                    return new zzlo(null);
                }
                return new zzlp();
            }
            return zzz(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001Ț", new Object[]{"zzd"});
        }
        return (byte) 1;
    }

    public final List zzi() {
        return this.zzd;
    }
}
