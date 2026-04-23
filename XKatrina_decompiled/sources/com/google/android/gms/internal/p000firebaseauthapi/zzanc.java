package com.google.android.gms.internal.p000firebaseauthapi;

import sun.misc.Unsafe;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzanc  reason: invalid package */
/* loaded from: classes.dex */
final class zzanc extends zzane {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzanc(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzane
    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzane
    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzane
    public final void zzc(Object obj, long j, boolean z) {
        if (zzanf.zzb) {
            zzanf.zzD(obj, j, r3 ? (byte) 1 : (byte) 0);
        } else {
            zzanf.zzE(obj, j, r3 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzane
    public final void zzd(Object obj, long j, byte b) {
        if (zzanf.zzb) {
            zzanf.zzD(obj, j, b);
        } else {
            zzanf.zzE(obj, j, b);
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzane
    public final void zze(Object obj, long j, double d) {
        this.zza.putLong(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzane
    public final void zzf(Object obj, long j, float f) {
        this.zza.putInt(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzane
    public final boolean zzg(Object obj, long j) {
        if (zzanf.zzb) {
            return zzanf.zzt(obj, j);
        }
        return zzanf.zzu(obj, j);
    }
}
