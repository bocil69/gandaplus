package com.google.android.recaptcha.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzbz implements zzby {
    public static final zzbz zza = new zzbz();

    private zzbz() {
    }

    public static final List zzc(Object obj) {
        if (obj instanceof byte[]) {
            return ArraysKt.toList((byte[]) obj);
        }
        if (obj instanceof short[]) {
            return ArraysKt.toList((short[]) obj);
        }
        if (obj instanceof int[]) {
            return ArraysKt.toList((int[]) obj);
        }
        if (obj instanceof long[]) {
            return ArraysKt.toList((long[]) obj);
        }
        if (obj instanceof float[]) {
            return ArraysKt.toList((float[]) obj);
        }
        if (obj instanceof double[]) {
            return ArraysKt.toList((double[]) obj);
        }
        return null;
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        if (objArr.length == 2) {
            Object obj = objArr[0];
            if (true != (obj instanceof Object)) {
                obj = null;
            }
            if (obj == null) {
                throw new zzt(4, 5, null);
            }
            Object obj2 = objArr[1];
            if (true != (obj2 instanceof Object)) {
                obj2 = null;
            }
            if (obj2 == null) {
                throw new zzt(4, 5, null);
            }
            zzblVar.zzc().zzf(i, zzb(obj, obj2));
            return;
        }
        throw new zzt(4, 3, null);
    }

    public final Object zzb(Object obj, Object obj2) throws zzt {
        List<Number> zzc = zzc(obj);
        List<Number> zzc2 = zzc(obj2);
        if (obj instanceof Number) {
            if (obj2 instanceof Number) {
                return Double.valueOf(Math.pow(((Number) obj).doubleValue(), ((Number) obj2).doubleValue()));
            }
            if (zzc2 != null) {
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(zzc2, 10));
                for (Number number : zzc2) {
                    arrayList.add(Double.valueOf(Math.pow(number.doubleValue(), ((Number) obj).doubleValue())));
                }
                return (Serializable) arrayList.toArray(new Double[0]);
            }
        }
        if (zzc != null && (obj2 instanceof Number)) {
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(zzc, 10));
            for (Number number2 : zzc) {
                arrayList2.add(Double.valueOf(Math.pow(number2.doubleValue(), ((Number) obj2).doubleValue())));
            }
            return (Serializable) arrayList2.toArray(new Double[0]);
        } else if (zzc == null || zzc2 == null) {
            throw new zzt(4, 5, null);
        } else {
            zzbx.zzb(this, zzc.size(), zzc2.size());
            int size = zzc.size();
            Double[] dArr = new Double[size];
            for (int i = 0; i < size; i++) {
                dArr[i] = Double.valueOf(Math.pow(((Number) zzc.get(i)).doubleValue(), ((Number) zzc2.get(i)).doubleValue()));
            }
            return (Serializable) dArr;
        }
    }
}
