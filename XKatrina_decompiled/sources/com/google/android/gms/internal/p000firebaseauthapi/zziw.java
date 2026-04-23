package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zziw  reason: invalid package */
/* loaded from: classes.dex */
public final class zziw extends zzje {
    private final int zza;
    private final zziu zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zziw(int i, zziu zziuVar, zziv zzivVar) {
        this.zza = i;
        this.zzb = zziuVar;
    }

    public static zzit zzc() {
        return new zzit(null);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zziw) {
            zziw zziwVar = (zziw) obj;
            return zziwVar.zza == this.zza && zziwVar.zzb == this.zzb;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zziw.class, Integer.valueOf(this.zza), this.zzb});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzb);
        return "AesSiv Parameters (variant: " + valueOf + ", " + this.zza + "-byte key)";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzce
    public final boolean zza() {
        return this.zzb != zziu.zzc;
    }

    public final int zzb() {
        return this.zza;
    }

    public final zziu zzd() {
        return this.zzb;
    }
}
