package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpf  reason: invalid package */
/* loaded from: classes.dex */
public final class zzpf {
    @Nullable
    private zzpr zza = null;
    @Nullable
    private zzzq zzb = null;
    @Nullable
    private Integer zzc = null;

    private zzpf() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzpf(zzpe zzpeVar) {
    }

    public final zzpf zza(zzzq zzzqVar) throws GeneralSecurityException {
        this.zzb = zzzqVar;
        return this;
    }

    public final zzpf zzb(@Nullable Integer num) {
        this.zzc = num;
        return this;
    }

    public final zzpf zzc(zzpr zzprVar) {
        this.zza = zzprVar;
        return this;
    }

    public final zzph zzd() throws GeneralSecurityException {
        zzzq zzzqVar;
        zzzo zzb;
        zzpr zzprVar = this.zza;
        if (zzprVar == null || (zzzqVar = this.zzb) == null) {
            throw new GeneralSecurityException("Cannot build without parameters and/or key material");
        }
        if (zzprVar.zzc() == zzzqVar.zza()) {
            if (!zzprVar.zza() || this.zzc != null) {
                if (this.zza.zza() || this.zzc == null) {
                    if (this.zza.zze() == zzpp.zzd) {
                        zzb = zzzo.zzb(new byte[0]);
                    } else if (this.zza.zze() == zzpp.zzc || this.zza.zze() == zzpp.zzb) {
                        zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 0).putInt(this.zzc.intValue()).array());
                    } else if (this.zza.zze() != zzpp.zza) {
                        throw new IllegalStateException("Unknown AesCmacParametersParameters.Variant: ".concat(String.valueOf(String.valueOf(this.zza.zze()))));
                    } else {
                        zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 1).putInt(this.zzc.intValue()).array());
                    }
                    return new zzph(this.zza, this.zzb, zzb, this.zzc, null);
                }
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
        }
        throw new GeneralSecurityException("Key size mismatch");
    }
}
