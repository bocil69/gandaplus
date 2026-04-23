package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmf  reason: invalid package */
/* loaded from: classes.dex */
final class zzmf implements zzlu {
    private final zzzo zza;
    private final zzzo zzb;

    private zzmf(byte[] bArr, byte[] bArr2) {
        this.zza = zzzo.zzb(bArr);
        this.zzb = zzzo.zzb(bArr2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzmf zzc(byte[] bArr) throws GeneralSecurityException {
        return new zzmf(bArr, zzzm.zzb(bArr));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzlu
    public final zzzo zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzlu
    public final zzzo zzb() {
        return this.zzb;
    }
}
