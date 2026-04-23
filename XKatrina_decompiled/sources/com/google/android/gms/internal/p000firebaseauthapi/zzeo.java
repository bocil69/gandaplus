package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeo  reason: invalid package */
/* loaded from: classes.dex */
public final class zzeo {
    @Nullable
    private zzey zza = null;
    @Nullable
    private zzzq zzb = null;
    @Nullable
    private Integer zzc = null;

    private zzeo() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzeo(zzen zzenVar) {
    }

    public final zzeo zza(@Nullable Integer num) {
        this.zzc = num;
        return this;
    }

    public final zzeo zzb(zzzq zzzqVar) {
        this.zzb = zzzqVar;
        return this;
    }

    public final zzeo zzc(zzey zzeyVar) {
        this.zza = zzeyVar;
        return this;
    }

    public final zzeq zzd() throws GeneralSecurityException {
        zzzq zzzqVar;
        zzzo zzb;
        zzey zzeyVar = this.zza;
        if (zzeyVar == null || (zzzqVar = this.zzb) == null) {
            throw new GeneralSecurityException("Cannot build without parameters and/or key material");
        }
        if (zzeyVar.zzb() == zzzqVar.zza()) {
            if (!zzeyVar.zza() || this.zzc != null) {
                if (this.zza.zza() || this.zzc == null) {
                    if (this.zza.zzd() == zzew.zzc) {
                        zzb = zzzo.zzb(new byte[0]);
                    } else if (this.zza.zzd() == zzew.zzb) {
                        zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 0).putInt(this.zzc.intValue()).array());
                    } else if (this.zza.zzd() != zzew.zza) {
                        throw new IllegalStateException("Unknown AesGcmParameters.Variant: ".concat(String.valueOf(String.valueOf(this.zza.zzd()))));
                    } else {
                        zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 1).putInt(this.zzc.intValue()).array());
                    }
                    return new zzeq(this.zza, this.zzb, zzb, this.zzc, null);
                }
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
        }
        throw new GeneralSecurityException("Key size mismatch");
    }
}
