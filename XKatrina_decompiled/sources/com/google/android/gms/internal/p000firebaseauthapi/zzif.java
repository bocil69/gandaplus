package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzif  reason: invalid package */
/* loaded from: classes.dex */
public final class zzif extends zzid {
    public zzif(byte[] bArr) throws GeneralSecurityException {
        super(bArr);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzid
    final zzib zza(byte[] bArr, int i) throws InvalidKeyException {
        return new zzie(bArr, i);
    }
}
