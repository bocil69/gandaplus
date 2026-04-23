package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzev  reason: invalid package */
/* loaded from: classes.dex */
public final class zzev {
    @Nullable
    private Integer zza;
    @Nullable
    private Integer zzb;
    @Nullable
    private Integer zzc;
    private zzew zzd;

    private zzev() {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzev(zzeu zzeuVar) {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        this.zzd = zzew.zzc;
    }

    public final zzev zza(int i) throws GeneralSecurityException {
        this.zzb = 12;
        return this;
    }

    public final zzev zzc(int i) throws GeneralSecurityException {
        this.zzc = 16;
        return this;
    }

    public final zzev zzd(zzew zzewVar) {
        this.zzd = zzewVar;
        return this;
    }

    public final zzey zze() throws GeneralSecurityException {
        Integer num = this.zza;
        if (num != null) {
            if (this.zzd != null) {
                if (this.zzb != null) {
                    if (this.zzc == null) {
                        throw new GeneralSecurityException("Tag size is not set");
                    }
                    int intValue = num.intValue();
                    this.zzb.intValue();
                    this.zzc.intValue();
                    return new zzey(intValue, 12, 16, this.zzd, null);
                }
                throw new GeneralSecurityException("IV size is not set");
            }
            throw new GeneralSecurityException("Variant is not set");
        }
        throw new GeneralSecurityException("Key size is not set");
    }

    public final zzev zzb(int i) throws GeneralSecurityException {
        if (i == 16 || i == 24 || i == 32) {
            this.zza = Integer.valueOf(i);
            return this;
        }
        throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte, 24-byte and 32-byte AES keys are supported", Integer.valueOf(i)));
    }
}
