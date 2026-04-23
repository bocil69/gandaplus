package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfp  reason: invalid package */
/* loaded from: classes.dex */
public final class zzfp extends zzcx {
    private final int zza;
    private final zzfn zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfp(int i, zzfn zzfnVar, zzfo zzfoVar) {
        this.zza = i;
        this.zzb = zzfnVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzfp) {
            zzfp zzfpVar = (zzfp) obj;
            return zzfpVar.zza == this.zza && zzfpVar.zzb == this.zzb;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zzfp.class, Integer.valueOf(this.zza), this.zzb});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzb);
        return "AesGcmSiv Parameters (variant: " + valueOf + ", " + this.zza + "-byte key)";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzce
    public final boolean zza() {
        return this.zzb != zzfn.zzc;
    }

    public final int zzb() {
        return this.zza;
    }

    public final zzfn zzc() {
        return this.zzb;
    }
}
