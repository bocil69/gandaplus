package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Comparator;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaix  reason: invalid package */
/* loaded from: classes.dex */
final class zzaix implements Comparator {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzajf zzajfVar = (zzajf) obj;
        zzajf zzajfVar2 = (zzajf) obj2;
        zzaiw zzaiwVar = new zzaiw(zzajfVar);
        zzaiw zzaiwVar2 = new zzaiw(zzajfVar2);
        while (zzaiwVar.hasNext() && zzaiwVar2.hasNext()) {
            int compareTo = Integer.valueOf(zzaiwVar.zza() & 255).compareTo(Integer.valueOf(zzaiwVar2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzajfVar.zzd()).compareTo(Integer.valueOf(zzajfVar2.zzd()));
    }
}
