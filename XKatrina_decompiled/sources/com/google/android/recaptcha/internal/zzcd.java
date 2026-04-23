package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzcd implements zzby {
    public static final zzcd zza = new zzcd();

    private zzcd() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        if (objArr.length < 2) {
            throw new zzt(4, 3, null);
        }
        Class<?> cls = objArr[0];
        if (true != (cls instanceof Object)) {
            cls = null;
        }
        if (cls == null) {
            throw new zzt(4, 5, null);
        }
        Class<?> cls2 = cls instanceof Class ? cls : cls.getClass();
        Object obj = objArr[1];
        if (true != (obj instanceof String)) {
            obj = null;
        }
        String str = (String) obj;
        if (str == null) {
            throw new zzt(4, 5, null);
        }
        String zza2 = zzbx.zza(this, str, zzblVar.zza());
        if (Intrinsics.areEqual(zza2, "forName")) {
            throw new zzt(6, 48, null);
        }
        List<Object> drop = ArraysKt.drop(objArr, 2);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(drop, 10));
        for (Object obj2 : drop) {
            arrayList.add(zzbk.zza(obj2));
        }
        Class[] clsArr = (Class[]) arrayList.toArray(new Class[0]);
        try {
            zzblVar.zzc().zzf(i, cls2.getMethod(zza2, (Class[]) Arrays.copyOf(clsArr, clsArr.length)));
        } catch (Exception e) {
            throw new zzt(6, 13, e);
        }
    }
}
