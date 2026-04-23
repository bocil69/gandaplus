package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzbg extends zzbd {
    private final zzbf zza;
    private final String zzb;

    public zzbg(zzbf zzbfVar, String str, Object obj) {
        super(obj);
        this.zza = zzbfVar;
        this.zzb = str;
    }

    @Override // com.google.android.recaptcha.internal.zzbd
    public final boolean zza(Object obj, Method method, Object[] objArr) {
        List emptyList;
        if (Intrinsics.areEqual(method.getName(), this.zzb)) {
            zzbf zzbfVar = this.zza;
            if (objArr == null || (emptyList = ArraysKt.asList(objArr)) == null) {
                emptyList = CollectionsKt.emptyList();
            }
            zzbfVar.zzb(emptyList);
            return true;
        }
        return false;
    }
}
