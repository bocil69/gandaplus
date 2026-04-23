package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrf  reason: invalid package */
/* loaded from: classes.dex */
public final class zzrf implements zzpx {
    private final zzqe zza;

    public zzrf(zzqe zzqeVar) throws GeneralSecurityException {
        if (!zzij.zza(2)) {
            throw new GeneralSecurityException("Can not use HMAC in FIPS-mode, as BoringCrypto module is not available.");
        }
        this.zza = zzqeVar;
    }
}
