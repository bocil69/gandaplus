package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmd  reason: invalid package */
/* loaded from: classes.dex */
final class zzmd implements zzlu {
    private final zzzo zza;
    private final zzzo zzb;

    private zzmd(byte[] bArr, byte[] bArr2) {
        this.zza = zzzo.zzb(bArr);
        this.zzb = zzzo.zzb(bArr2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzmd zzc(byte[] bArr, byte[] bArr2, int i) throws GeneralSecurityException {
        zzym.zzd(zzym.zzh(zzym.zzi(i), 1, bArr2), zzym.zzg(i, bArr));
        return new zzmd(bArr, bArr2);
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
