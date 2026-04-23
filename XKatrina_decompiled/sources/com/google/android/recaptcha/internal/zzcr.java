package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzcr {
    public static final zzcr zza = new zzcr();
    private static List zzb = CollectionsKt.emptyList();

    private zzcr() {
    }

    @JvmStatic
    public static final void acx(int[] iArr) {
        zzb(iArr);
    }

    @JvmStatic
    public static final int zza(int[] iArr) {
        Iterator it = CollectionsKt.plus(zzb, ArraysKt.toList(iArr)).iterator();
        if (!it.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        Object next = it.next();
        while (it.hasNext()) {
            next = Integer.valueOf(((Number) next).intValue() ^ ((Number) it.next()).intValue());
        }
        return ((Number) next).intValue();
    }

    public static final void zzb(int[] iArr) {
        zzb = ArraysKt.toList(iArr);
    }
}
