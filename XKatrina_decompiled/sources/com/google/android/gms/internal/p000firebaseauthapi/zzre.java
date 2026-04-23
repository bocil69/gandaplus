package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzre  reason: invalid package */
/* loaded from: classes.dex */
public final class zzre implements zzpx {
    private final zzph zza;

    public zzre(zzph zzphVar) throws GeneralSecurityException {
        if (!zzij.zza(1)) {
            throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
        }
        this.zza = zzphVar;
    }
}
