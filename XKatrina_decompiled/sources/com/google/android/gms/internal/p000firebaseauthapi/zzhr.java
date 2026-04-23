package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhr  reason: invalid package */
/* loaded from: classes.dex */
public final class zzhr extends zzcx {
    private final zzhq zza;

    private zzhr(zzhq zzhqVar) {
        this.zza = zzhqVar;
    }

    public static zzhr zzc() {
        return new zzhr(zzhq.zzc);
    }

    public static zzhr zzd(zzhq zzhqVar) {
        return new zzhr(zzhqVar);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof zzhr) && ((zzhr) obj).zza == this.zza;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zzhr.class, this.zza});
    }

    public final String toString() {
        String obj = this.zza.toString();
        return "XChaCha20Poly1305 Parameters (variant: " + obj + ")";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzce
    public final boolean zza() {
        return this.zza != zzhq.zzc;
    }

    public final zzhq zzb() {
        return this.zza;
    }
}
