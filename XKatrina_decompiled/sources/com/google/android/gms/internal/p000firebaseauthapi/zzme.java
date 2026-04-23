package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzme  reason: invalid package */
/* loaded from: classes.dex */
final class zzme implements zzlt {
    private final zzln zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzme(zzln zzlnVar) {
        this.zza = zzlnVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzlt
    public final byte[] zza(byte[] bArr, zzlu zzluVar) throws GeneralSecurityException {
        byte[] zza = zzzm.zza(zzluVar.zza().zzc(), bArr);
        byte[] zzb = zzyf.zzb(bArr, zzluVar.zzb().zzc());
        byte[] zze = zzmb.zze(zzmb.zzc);
        zzln zzlnVar = this.zza;
        return zzlnVar.zzb(null, zza, "eae_prk", zzb, "shared_secret", zze, zzlnVar.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzlt
    public final byte[] zzb() throws GeneralSecurityException {
        if (Arrays.equals(this.zza.zzc(), zzmb.zzg)) {
            return zzmb.zzc;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }
}
