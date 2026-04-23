package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzic  reason: invalid package */
/* loaded from: classes.dex */
public final class zzic extends zzid {
    public zzic(byte[] bArr) throws GeneralSecurityException {
        super(bArr);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzid
    final zzib zza(byte[] bArr, int i) throws InvalidKeyException {
        return new zzia(bArr, i);
    }
}
