package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzff  reason: invalid package */
/* loaded from: classes.dex */
public final class zzff {
    @Nullable
    private zzfp zza = null;
    @Nullable
    private zzzq zzb = null;
    @Nullable
    private Integer zzc = null;

    private zzff() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzff(zzfe zzfeVar) {
    }

    public final zzff zza(@Nullable Integer num) {
        this.zzc = num;
        return this;
    }

    public final zzff zzb(zzzq zzzqVar) {
        this.zzb = zzzqVar;
        return this;
    }

    public final zzff zzc(zzfp zzfpVar) {
        this.zza = zzfpVar;
        return this;
    }

    public final zzfh zzd() throws GeneralSecurityException {
        zzzq zzzqVar;
        zzzo zzb;
        zzfp zzfpVar = this.zza;
        if (zzfpVar == null || (zzzqVar = this.zzb) == null) {
            throw new GeneralSecurityException("Cannot build without parameters and/or key material");
        }
        if (zzfpVar.zzb() == zzzqVar.zza()) {
            if (!zzfpVar.zza() || this.zzc != null) {
                if (this.zza.zza() || this.zzc == null) {
                    if (this.zza.zzc() == zzfn.zzc) {
                        zzb = zzzo.zzb(new byte[0]);
                    } else if (this.zza.zzc() == zzfn.zzb) {
                        zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 0).putInt(this.zzc.intValue()).array());
                    } else if (this.zza.zzc() != zzfn.zza) {
                        throw new IllegalStateException("Unknown AesGcmSivParameters.Variant: ".concat(String.valueOf(String.valueOf(this.zza.zzc()))));
                    } else {
                        zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 1).putInt(this.zzc.intValue()).array());
                    }
                    return new zzfh(this.zza, this.zzb, zzb, this.zzc, null);
                }
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
        }
        throw new GeneralSecurityException("Key size mismatch");
    }
}
