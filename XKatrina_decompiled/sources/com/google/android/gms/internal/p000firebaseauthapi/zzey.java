package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzey  reason: invalid package */
/* loaded from: classes.dex */
public final class zzey extends zzcx {
    private final int zza;
    private final int zzb = 12;
    private final int zzc = 16;
    private final zzew zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzey(int i, int i2, int i3, zzew zzewVar, zzex zzexVar) {
        this.zza = i;
        this.zzd = zzewVar;
    }

    public static zzev zzc() {
        return new zzev(null);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzey) {
            zzey zzeyVar = (zzey) obj;
            if (zzeyVar.zza == this.zza) {
                int i = zzeyVar.zzb;
                int i2 = zzeyVar.zzc;
                if (zzeyVar.zzd == this.zzd) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zzey.class, Integer.valueOf(this.zza), 12, 16, this.zzd});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzd);
        return "AesGcm Parameters (variant: " + valueOf + ", 12-byte IV, 16-byte tag, and " + this.zza + "-byte key)";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzce
    public final boolean zza() {
        return this.zzd != zzew.zzc;
    }

    public final int zzb() {
        return this.zza;
    }

    public final zzew zzd() {
        return this.zzd;
    }
}
