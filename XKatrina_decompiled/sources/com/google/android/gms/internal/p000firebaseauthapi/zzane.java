package com.google.android.gms.internal.p000firebaseauthapi;

import sun.misc.Unsafe;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzane  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzane {
    final Unsafe zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzane(Unsafe unsafe) {
        this.zza = unsafe;
    }

    public abstract double zza(Object obj, long j);

    public abstract float zzb(Object obj, long j);

    public abstract void zzc(Object obj, long j, boolean z);

    public abstract void zzd(Object obj, long j, byte b);

    public abstract void zze(Object obj, long j, double d);

    public abstract void zzf(Object obj, long j, float f);

    public abstract boolean zzg(Object obj, long j);
}
