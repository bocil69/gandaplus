package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzit  reason: invalid package */
/* loaded from: classes.dex */
public final class zzit {
    @Nullable
    private Integer zza;
    private zziu zzb;

    private zzit() {
        this.zza = null;
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzit(zzis zzisVar) {
        this.zza = null;
        this.zzb = zziu.zzc;
    }

    public final zzit zzb(zziu zziuVar) {
        this.zzb = zziuVar;
        return this;
    }

    public final zziw zzc() throws GeneralSecurityException {
        Integer num = this.zza;
        if (num != null) {
            if (this.zzb != null) {
                return new zziw(num.intValue(), this.zzb, null);
            }
            throw new GeneralSecurityException("Variant is not set");
        }
        throw new GeneralSecurityException("Key size is not set");
    }

    public final zzit zza(int i) throws GeneralSecurityException {
        if (i == 32 || i == 48 || i == 64) {
            this.zza = Integer.valueOf(i);
            return this;
        }
        throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 32-byte, 48-byte and 64-byte AES-SIV keys are supported", Integer.valueOf(i)));
    }
}
