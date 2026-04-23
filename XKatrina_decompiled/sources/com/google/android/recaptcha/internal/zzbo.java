package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.ArrayList;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzbo implements zzby {
    public static final zzbo zza = new zzbo();

    private zzbo() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        Object obj;
        if (objArr.length == 2) {
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
                if (obj2 instanceof Integer) {
                    obj = Integer.valueOf(((Number) obj2).intValue() + intValue);
                } else if (obj2 instanceof int[]) {
                    int[] iArr = (int[]) obj2;
                    ArrayList arrayList = new ArrayList(iArr.length);
                    for (int i2 : iArr) {
                        arrayList.add(Integer.valueOf(i2 + intValue));
                    }
                    obj = (Serializable) arrayList.toArray(new Integer[0]);
                } else {
                    throw new zzt(4, 5, null);
                }
                zzblVar.zzc().zzf(i, obj);
                return;
            }
            throw new zzt(4, 5, null);
        }
        throw new zzt(4, 3, null);
    }
}
