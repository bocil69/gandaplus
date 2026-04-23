package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeh  reason: invalid package */
/* loaded from: classes.dex */
public final class zzeh extends zzcx {
    private final int zza;
    private final int zzb;
    private final int zzc = 16;
    private final zzef zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzeh(int i, int i2, int i3, zzef zzefVar, zzeg zzegVar) {
        this.zza = i;
        this.zzb = i2;
        this.zzd = zzefVar;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzeh) {
            zzeh zzehVar = (zzeh) obj;
            if (zzehVar.zza == this.zza && zzehVar.zzb == this.zzb) {
                int i = zzehVar.zzc;
                if (zzehVar.zzd == this.zzd) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zzeh.class, Integer.valueOf(this.zza), Integer.valueOf(this.zzb), 16, this.zzd});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzd);
        return "AesEax Parameters (variant: " + valueOf + ", " + this.zzb + "-byte IV, 16-byte tag, and " + this.zza + "-byte key)";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzce
    public final boolean zza() {
        return this.zzd != zzef.zzc;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final int zzc() {
        return this.zza;
    }

    public final zzef zzd() {
        return this.zzd;
    }
}
