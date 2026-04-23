package com.google.android.recaptcha.internal;

import java.util.Collection;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.Charsets;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzbt implements zzby {
    public static final zzbt zza = new zzbt();

    private zzbt() {
    }

    @Override // com.google.android.recaptcha.internal.zzby
    public final void zza(int i, zzbl zzblVar, Object... objArr) throws zzt {
        String joinToString$default;
        String str;
        if (objArr.length == 1) {
            Object obj = objArr[0];
            if (true != (obj instanceof Object)) {
                obj = null;
            }
            if (obj == null) {
                throw new zzt(4, 5, null);
            }
            if (obj instanceof int[]) {
                joinToString$default = ArraysKt.joinToString$default((int[]) obj, ",", "[", "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
            } else {
                if (obj instanceof byte[]) {
                    str = new String((byte[]) obj, Charsets.UTF_8);
                } else if (obj instanceof long[]) {
                    joinToString$default = ArraysKt.joinToString$default((long[]) obj, ",", "[", "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                } else if (obj instanceof short[]) {
                    joinToString$default = ArraysKt.joinToString$default((short[]) obj, ",", "[", "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                } else if (obj instanceof float[]) {
                    joinToString$default = ArraysKt.joinToString$default((float[]) obj, ",", "[", "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                } else if (obj instanceof double[]) {
                    joinToString$default = ArraysKt.joinToString$default((double[]) obj, ",", "[", "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                } else if (obj instanceof char[]) {
                    str = new String((char[]) obj);
                } else if (obj instanceof Object[]) {
                    joinToString$default = ArraysKt.joinToString$default((Object[]) obj, ",", "[", "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                } else if (!(obj instanceof Collection)) {
                    throw new zzt(4, 5, null);
                } else {
                    joinToString$default = CollectionsKt.joinToString$default((Iterable) obj, ",", "[", "]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                }
                joinToString$default = str;
            }
            zzblVar.zzc().zzf(i, joinToString$default);
            return;
        }
        throw new zzt(4, 3, null);
    }
}
