package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgz  reason: invalid package */
/* loaded from: classes.dex */
public final class zzgz extends zzcx {
    private final String zza;
    private final zzgx zzb;
    private final zzcx zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgz(String str, zzgx zzgxVar, zzcx zzcxVar, zzgy zzgyVar) {
        this.zza = str;
        this.zzb = zzgxVar;
        this.zzc = zzcxVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzgz) {
            zzgz zzgzVar = (zzgz) obj;
            return zzgzVar.zzb.equals(this.zzb) && zzgzVar.zzc.equals(this.zzc) && zzgzVar.zza.equals(this.zza);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zzgz.class, this.zza, this.zzb, this.zzc});
    }

    public final String toString() {
        zzcx zzcxVar = this.zzc;
        String valueOf = String.valueOf(this.zzb);
        String valueOf2 = String.valueOf(zzcxVar);
        return "LegacyKmsEnvelopeAead Parameters (kekUri: " + this.zza + ", dekParsingStrategy: " + valueOf + ", dekParametersForNewKeys: " + valueOf2 + ")";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzce
    public final boolean zza() {
        return false;
    }

    public final zzcx zzb() {
        return this.zzc;
    }

    public final String zzc() {
        return this.zza;
    }
}
