package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdn  reason: invalid package */
/* loaded from: classes.dex */
public final class zzdn extends zzcx {
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private final zzdl zze;
    private final zzdk zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdn(int i, int i2, int i3, int i4, zzdl zzdlVar, zzdk zzdkVar, zzdm zzdmVar) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = i4;
        this.zze = zzdlVar;
        this.zzf = zzdkVar;
    }

    public static zzdj zzf() {
        return new zzdj(null);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzdn) {
            zzdn zzdnVar = (zzdn) obj;
            return zzdnVar.zza == this.zza && zzdnVar.zzb == this.zzb && zzdnVar.zzc == this.zzc && zzdnVar.zzd == this.zzd && zzdnVar.zze == this.zze && zzdnVar.zzf == this.zzf;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zzdn.class, Integer.valueOf(this.zza), Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), Integer.valueOf(this.zzd), this.zze, this.zzf});
    }

    public final String toString() {
        zzdk zzdkVar = this.zzf;
        String valueOf = String.valueOf(this.zze);
        String valueOf2 = String.valueOf(zzdkVar);
        return "AesCtrHmacAead Parameters (variant: " + valueOf + ", hashType: " + valueOf2 + ", " + this.zzc + "-byte IV, and " + this.zzd + "-byte tags, and " + this.zza + "-byte AES key, and " + this.zzb + "-byte HMAC key)";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzce
    public final boolean zza() {
        return this.zze != zzdl.zzc;
    }

    public final int zzb() {
        return this.zza;
    }

    public final int zzc() {
        return this.zzb;
    }

    public final int zzd() {
        return this.zzc;
    }

    public final int zze() {
        return this.zzd;
    }

    public final zzdk zzg() {
        return this.zzf;
    }

    public final zzdl zzh() {
        return this.zze;
    }
}
