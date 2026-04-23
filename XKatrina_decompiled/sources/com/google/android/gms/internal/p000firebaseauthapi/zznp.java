package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.concurrent.atomic.AtomicReference;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznp  reason: invalid package */
/* loaded from: classes.dex */
public final class zznp {
    private static final zznp zza = new zznp();
    private static final zzno zzb = new zzno(null);
    private final AtomicReference zzc = new AtomicReference();

    public static zznp zza() {
        return zza;
    }

    public final zzrq zzb() {
        zzrq zzrqVar = (zzrq) this.zzc.get();
        return zzrqVar == null ? zzb : zzrqVar;
    }
}
