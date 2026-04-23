package com.google.android.recaptcha.internal;

import java.lang.reflect.Array;
import java.util.List;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzbp implements zzby {
    public static final zzbp zza = new zzbp();

    private zzbp() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        Object obj;
        if (objArr.length != 2) {
            throw new zzt(4, 3, null);
        }
        Object obj2 = objArr[0];
        if (true != (obj2 instanceof Object)) {
            obj2 = null;
        }
        if (obj2 == null) {
            throw new zzt(4, 5, null);
        }
        Object obj3 = objArr[1];
        if (true != (obj3 instanceof Integer)) {
            obj3 = null;
        }
        Integer num = (Integer) obj3;
        if (num != null) {
            int intValue = num.intValue();
            try {
                if (obj2 instanceof String) {
                    obj = String.valueOf(((String) obj2).charAt(intValue));
                } else {
                    obj = obj2 instanceof List ? ((List) obj2).get(intValue) : Array.get(obj2, intValue);
                }
                zzblVar.zzc().zzf(i, obj);
                return;
            } catch (Exception e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    throw new zzt(4, 22, e);
                }
                throw new zzt(4, 23, e);
            }
        }
        throw new zzt(4, 5, null);
    }
}
