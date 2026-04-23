package com.google.android.recaptcha.internal;

import sun.misc.Unsafe;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
final class zzjn extends zzjo {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjn(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.recaptcha.internal.zzjo
    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j));
    }

    @Override // com.google.android.recaptcha.internal.zzjo
    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j));
    }

    @Override // com.google.android.recaptcha.internal.zzjo
    public final void zzc(Object obj, long j, boolean z) {
        if (zzjp.zzb) {
            zzjp.zzD(obj, j, r3 ? (byte) 1 : (byte) 0);
        } else {
            zzjp.zzE(obj, j, r3 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.recaptcha.internal.zzjo
    public final void zzd(Object obj, long j, byte b) {
        if (zzjp.zzb) {
            zzjp.zzD(obj, j, b);
        } else {
            zzjp.zzE(obj, j, b);
        }
    }

    @Override // com.google.android.recaptcha.internal.zzjo
    public final void zze(Object obj, long j, double d) {
        this.zza.putLong(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.recaptcha.internal.zzjo
    public final void zzf(Object obj, long j, float f) {
        this.zza.putInt(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.recaptcha.internal.zzjo
    public final boolean zzg(Object obj, long j) {
        if (zzjp.zzb) {
            return zzjp.zzt(obj, j);
        }
        return zzjp.zzu(obj, j);
    }
}
