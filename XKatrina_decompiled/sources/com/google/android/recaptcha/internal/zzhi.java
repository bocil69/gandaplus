package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
final class zzhi extends zzhm {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzhi() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhi(zzhh zzhhVar) {
        super(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static List zzf(Object obj, long j, int i) {
        zzhf zzhfVar;
        List arrayList;
        List list = (List) zzjp.zzf(obj, j);
        if (list.isEmpty()) {
            if (list instanceof zzhg) {
                arrayList = new zzhf(i);
            } else if (!(list instanceof zzig) || !(list instanceof zzgv)) {
                arrayList = new ArrayList(i);
            } else {
                arrayList = ((zzgv) list).zzd(i);
            }
            zzjp.zzs(obj, j, arrayList);
            return arrayList;
        }
        if (zza.isAssignableFrom(list.getClass())) {
            ArrayList arrayList2 = new ArrayList(list.size() + i);
            arrayList2.addAll(list);
            zzjp.zzs(obj, j, arrayList2);
            zzhfVar = arrayList2;
        } else if (list instanceof zzjk) {
            zzhf zzhfVar2 = new zzhf(list.size() + i);
            zzhfVar2.addAll(zzhfVar2.size(), (zzjk) list);
            zzjp.zzs(obj, j, zzhfVar2);
            zzhfVar = zzhfVar2;
        } else if ((list instanceof zzig) && (list instanceof zzgv)) {
            zzgv zzgvVar = (zzgv) list;
            if (zzgvVar.zzc()) {
                return list;
            }
            zzgv zzd = zzgvVar.zzd(list.size() + i);
            zzjp.zzs(obj, j, zzd);
            return zzd;
        } else {
            return list;
        }
        return zzhfVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzhm
    public final List zza(Object obj, long j) {
        return zzf(obj, j, 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzhm
    public final void zzb(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzjp.zzf(obj, j);
        if (list instanceof zzhg) {
            unmodifiableList = ((zzhg) list).zze();
        } else if (zza.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if (!(list instanceof zzig) || !(list instanceof zzgv)) {
                unmodifiableList = Collections.unmodifiableList(list);
            } else {
                zzgv zzgvVar = (zzgv) list;
                if (zzgvVar.zzc()) {
                    zzgvVar.zzb();
                    return;
                }
                return;
            }
        }
        zzjp.zzs(obj, j, unmodifiableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.recaptcha.internal.zzhm
    public final void zzc(Object obj, Object obj2, long j) {
        List list = (List) zzjp.zzf(obj2, j);
        List zzf = zzf(obj, j, list.size());
        int size = zzf.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            zzf.addAll(list);
        }
        if (size > 0) {
            list = zzf;
        }
        zzjp.zzs(obj, j, list);
    }
}
