package com.google.android.recaptcha.internal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzbe extends zzbd implements zzbi {
    private final Function2 zza;
    private final String zzb;

    public zzbe(Function2 function2, String str, Object obj) {
        super(obj);
        this.zza = function2;
        this.zzb = str;
    }

    @Override // com.google.android.recaptcha.internal.zzbd
    public final boolean zza(Object obj, Method method, Object[] objArr) {
        Collection emptyList;
        if (Intrinsics.areEqual(method.getName(), this.zzb)) {
            zzmk zzf = zzmn.zzf();
            if (objArr != null) {
                emptyList = new ArrayList(objArr.length);
                for (Object obj2 : objArr) {
                    zzml zzf2 = zzmm.zzf();
                    zzf2.zzv(obj2.toString());
                    emptyList.add((zzmm) zzf2.zzj());
                }
            } else {
                emptyList = CollectionsKt.emptyList();
            }
            zzf.zzd(emptyList);
            Function2 function2 = this.zza;
            byte[] zzd = ((zzmn) zzf.zzj()).zzd();
            function2.invoke(objArr, zzeb.zzh().zzi(zzd, 0, zzd.length));
            return true;
        }
        return false;
    }
}
