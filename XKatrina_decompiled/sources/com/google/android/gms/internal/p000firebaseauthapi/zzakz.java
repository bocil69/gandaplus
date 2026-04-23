package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakz  reason: invalid package */
/* loaded from: classes.dex */
final class zzakz extends zzald {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzakz() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzakz(zzaky zzakyVar) {
        super(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static List zzf(Object obj, long j, int i) {
        zzakw zzakwVar;
        List arrayList;
        List list = (List) zzanf.zzf(obj, j);
        if (list.isEmpty()) {
            if (list instanceof zzakx) {
                arrayList = new zzakw(i);
            } else if (!(list instanceof zzalw) || !(list instanceof zzakp)) {
                arrayList = new ArrayList(i);
            } else {
                arrayList = ((zzakp) list).zzd(i);
            }
            zzanf.zzs(obj, j, arrayList);
            return arrayList;
        }
        if (zza.isAssignableFrom(list.getClass())) {
            ArrayList arrayList2 = new ArrayList(list.size() + i);
            arrayList2.addAll(list);
            zzanf.zzs(obj, j, arrayList2);
            zzakwVar = arrayList2;
        } else if (!(list instanceof zzana)) {
            if ((list instanceof zzalw) && (list instanceof zzakp)) {
                zzakp zzakpVar = (zzakp) list;
                if (zzakpVar.zzc()) {
                    return list;
                }
                zzakp zzd = zzakpVar.zzd(list.size() + i);
                zzanf.zzs(obj, j, zzd);
                return zzd;
            }
            return list;
        } else {
            zzakw zzakwVar2 = new zzakw(list.size() + i);
            zzakwVar2.addAll(zzakwVar2.size(), (zzana) list);
            zzanf.zzs(obj, j, zzakwVar2);
            zzakwVar = zzakwVar2;
        }
        return zzakwVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzald
    public final List zza(Object obj, long j) {
        return zzf(obj, j, 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzald
    public final void zzb(Object obj, long j) {
        Object unmodifiableList;
        List list = (List) zzanf.zzf(obj, j);
        if (list instanceof zzakx) {
            unmodifiableList = ((zzakx) list).zze();
        } else if (zza.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if (!(list instanceof zzalw) || !(list instanceof zzakp)) {
                unmodifiableList = Collections.unmodifiableList(list);
            } else {
                zzakp zzakpVar = (zzakp) list;
                if (zzakpVar.zzc()) {
                    zzakpVar.zzb();
                    return;
                }
                return;
            }
        }
        zzanf.zzs(obj, j, unmodifiableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzald
    public final void zzc(Object obj, Object obj2, long j) {
        List list = (List) zzanf.zzf(obj2, j);
        List zzf = zzf(obj, j, list.size());
        int size = zzf.size();
        int size2 = list.size();
        if (size > 0 && size2 > 0) {
            zzf.addAll(list);
        }
        if (size > 0) {
            list = zzf;
        }
        zzanf.zzs(obj, j, list);
    }
}
