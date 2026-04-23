package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzck implements zzby {
    public static final zzck zza = new zzck();

    private zzck() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        if (objArr.length == 1) {
            Object obj = objArr[0];
            if (true != (obj instanceof String)) {
                obj = null;
            }
            String str = (String) obj;
            if (str == null) {
                throw new zzt(4, 5, null);
            }
            zzblVar.zzf(str);
            return;
        }
        throw new zzt(4, 3, null);
    }
}
