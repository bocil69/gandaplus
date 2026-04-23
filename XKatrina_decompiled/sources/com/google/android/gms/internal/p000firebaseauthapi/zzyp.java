package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.security.ProviderInstaller;
import java.security.GeneralSecurityException;
import java.security.Provider;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzyp  reason: invalid package */
/* loaded from: classes.dex */
final class zzyp implements zzyu {
    private final zzzd zza;

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzyu
    public final Object zza(String str) throws GeneralSecurityException {
        for (Provider provider : zzyv.zzb(ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL")) {
            try {
                return this.zza.zza(str, provider);
            } catch (Exception unused) {
            }
        }
        return this.zza.zza(str, null);
    }
}
