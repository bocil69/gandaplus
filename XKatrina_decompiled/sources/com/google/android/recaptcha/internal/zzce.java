package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.collections.ArraysKt;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzce implements zzby {
    public static final zzce zza = new zzce();

    private zzce() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        int length = objArr.length;
        if (length >= 2) {
            Object obj = objArr[0];
            if (true != (obj instanceof Method)) {
                obj = null;
            }
            Method method = (Method) obj;
            if (method == null) {
                throw new zzt(4, 5, null);
            }
            Object obj2 = objArr[1];
            Object[] array = ArraysKt.toList(objArr).subList(2, length).toArray(new Object[0]);
            try {
                zzblVar.zzc().zzf(i, method.invoke(obj2, Arrays.copyOf(array, array.length)));
                return;
            } catch (Exception e) {
                throw new zzt(6, 15, e);
            }
        }
        throw new zzt(4, 3, null);
    }
}
