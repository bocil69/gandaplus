package com.google.android.recaptcha.internal;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzbs implements zzby {
    public static final zzbs zza = new zzbs();

    private zzbs() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        if (objArr.length != 2) {
            throw new zzt(4, 3, null);
        }
        Object obj = objArr[0];
        if (true != (obj instanceof String)) {
            obj = null;
        }
        String str = (String) obj;
        if (str == null) {
            throw new zzt(4, 5, null);
        }
        Object obj2 = objArr[1];
        if (true != (obj2 instanceof String)) {
            obj2 = null;
        }
        String str2 = (String) obj2;
        if (str2 == null) {
            throw new zzt(4, 5, null);
        }
        zzblVar.zzc().zzf(i, str.concat(str2));
    }
}
