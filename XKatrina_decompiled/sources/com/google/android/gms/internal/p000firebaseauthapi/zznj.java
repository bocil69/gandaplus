package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznj  reason: invalid package */
/* loaded from: classes.dex */
public final class zznj extends zzce {
    private final zzop zza;

    public zznj(zzop zzopVar) {
        this.zza = zzopVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zznj) {
            zzop zzopVar = ((zznj) obj).zza;
            return this.zza.zzc().zze().equals(zzopVar.zzc().zze()) && this.zza.zzc().zzg().equals(zzopVar.zzc().zzg()) && this.zza.zzc().zzf().equals(zzopVar.zzc().zzf());
        }
        return false;
    }

    public final int hashCode() {
        zzop zzopVar = this.zza;
        return Arrays.hashCode(new Object[]{zzopVar.zzc(), zzopVar.zzd()});
    }

    public final String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = this.zza.zzc().zzg();
        zzxo zze = this.zza.zzc().zze();
        zzxo zzxoVar = zzxo.UNKNOWN_PREFIX;
        int ordinal = zze.ordinal();
        objArr[1] = ordinal != 1 ? ordinal != 2 ? ordinal != 3 ? ordinal != 4 ? "UNKNOWN" : "CRUNCHY" : "RAW" : "LEGACY" : "TINK";
        return String.format("(typeUrl=%s, outputPrefixType=%s)", objArr);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzce
    public final boolean zza() {
        throw null;
    }

    public final zzop zzb() {
        return this.zza;
    }
}
