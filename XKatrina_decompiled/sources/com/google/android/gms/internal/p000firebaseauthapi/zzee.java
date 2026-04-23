package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzee  reason: invalid package */
/* loaded from: classes.dex */
public final class zzee {
    @Nullable
    private Integer zza;
    @Nullable
    private Integer zzb;
    @Nullable
    private Integer zzc;
    private zzef zzd;

    private zzee() {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzee(zzed zzedVar) {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        this.zzd = zzef.zzc;
    }

    public final zzee zzc(int i) throws GeneralSecurityException {
        this.zzc = 16;
        return this;
    }

    public final zzee zzd(zzef zzefVar) {
        this.zzd = zzefVar;
        return this;
    }

    public final zzeh zze() throws GeneralSecurityException {
        Integer num = this.zza;
        if (num != null) {
            if (this.zzb != null) {
                if (this.zzd != null) {
                    if (this.zzc == null) {
                        throw new GeneralSecurityException("Tag size is not set");
                    }
                    int intValue = num.intValue();
                    int intValue2 = this.zzb.intValue();
                    this.zzc.intValue();
                    return new zzeh(intValue, intValue2, 16, this.zzd, null);
                }
                throw new GeneralSecurityException("Variant is not set");
            }
            throw new GeneralSecurityException("IV size is not set");
        }
        throw new GeneralSecurityException("Key size is not set");
    }

    public final zzee zza(int i) throws GeneralSecurityException {
        if (i == 12 || i == 16) {
            this.zzb = Integer.valueOf(i);
            return this;
        }
        throw new GeneralSecurityException(String.format("Invalid IV size in bytes %d; acceptable values have 12 or 16 bytes", Integer.valueOf(i)));
    }

    public final zzee zzb(int i) throws GeneralSecurityException {
        if (i == 16 || i == 24 || i == 32) {
            this.zza = Integer.valueOf(i);
            return this;
        }
        throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte, 24-byte and 32-byte AES keys are supported", Integer.valueOf(i)));
    }
}
