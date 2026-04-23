package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Arrays;
import kotlin.collections.CollectionsKt;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzcb implements zzby {
    public static final zzcb zza = new zzcb();

    private zzcb() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        int length = objArr.length;
        if (length == 0) {
            throw new zzt(4, 3, null);
        }
        Object obj = objArr[0];
        if (true != (obj instanceof Class)) {
            obj = null;
        }
        Class cls = (Class) obj;
        if (cls == null) {
            throw new zzt(4, 5, null);
        }
        ArrayList<Object> arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            Object obj2 = objArr[i2];
            int i4 = i3 + 1;
            if (i3 > 0) {
                arrayList.add(obj2);
            }
            i2++;
            i3 = i4;
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (Object obj3 : arrayList) {
            arrayList2.add(zzbk.zza(obj3));
        }
        Class[] clsArr = (Class[]) arrayList2.toArray(new Class[0]);
        try {
            zzblVar.zzc().zzf(i, cls.getConstructor((Class[]) Arrays.copyOf(clsArr, clsArr.length)));
        } catch (Exception e) {
            throw new zzt(6, 9, e);
        }
    }
}
