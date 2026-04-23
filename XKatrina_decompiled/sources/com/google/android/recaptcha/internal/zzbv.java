package com.google.android.recaptcha.internal;

import java.lang.reflect.Array;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzbv implements zzby {
    public static final zzbv zza = new zzbv();

    private zzbv() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        if (objArr.length != 2) {
            throw new zzt(4, 3, null);
        }
        Object obj = objArr[0];
        if (true != (obj instanceof Object)) {
            obj = null;
        }
        if (obj == null) {
            throw new zzt(4, 5, null);
        }
        Object obj2 = objArr[1];
        if (true != (obj2 instanceof Integer)) {
            obj2 = null;
        }
        Integer num = (Integer) obj2;
        if (num != null) {
            int intValue = num.intValue();
            try {
                if (obj instanceof String) {
                    obj = zzbx.zza(this, (String) obj, zzblVar.zza());
                }
                zzblVar.zzc().zzf(i, Array.newInstance(zzbk.zza(obj), intValue));
                return;
            } catch (Exception e) {
                throw new zzt(6, 21, e);
            }
        }
        throw new zzt(4, 5, null);
    }
}
