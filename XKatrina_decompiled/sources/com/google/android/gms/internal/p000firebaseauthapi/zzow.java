package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzow  reason: invalid package */
/* loaded from: classes.dex */
final class zzow {
    private final Class zza;
    private final zzzo zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzow(Class cls, zzzo zzzoVar, zzov zzovVar) {
        this.zza = cls;
        this.zzb = zzzoVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzow) {
            zzow zzowVar = (zzow) obj;
            return zzowVar.zza.equals(this.zza) && zzowVar.zzb.equals(this.zzb);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        zzzo zzzoVar = this.zzb;
        String simpleName = this.zza.getSimpleName();
        String valueOf = String.valueOf(zzzoVar);
        return simpleName + ", object identifier: " + valueOf;
    }
}
