package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdx  reason: invalid package */
/* loaded from: classes.dex */
public final class zzdx {
    @Nullable
    private zzeh zza = null;
    @Nullable
    private zzzq zzb = null;
    @Nullable
    private Integer zzc = null;

    private zzdx() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdx(zzdw zzdwVar) {
    }

    public final zzdx zza(@Nullable Integer num) {
        this.zzc = num;
        return this;
    }

    public final zzdx zzb(zzzq zzzqVar) {
        this.zzb = zzzqVar;
        return this;
    }

    public final zzdx zzc(zzeh zzehVar) {
        this.zza = zzehVar;
        return this;
    }

    public final zzdz zzd() throws GeneralSecurityException {
        zzzq zzzqVar;
        zzzo zzb;
        zzeh zzehVar = this.zza;
        if (zzehVar == null || (zzzqVar = this.zzb) == null) {
            throw new GeneralSecurityException("Cannot build without parameters and/or key material");
        }
        if (zzehVar.zzc() == zzzqVar.zza()) {
            if (!zzehVar.zza() || this.zzc != null) {
                if (this.zza.zza() || this.zzc == null) {
                    if (this.zza.zzd() == zzef.zzc) {
                        zzb = zzzo.zzb(new byte[0]);
                    } else if (this.zza.zzd() == zzef.zzb) {
                        zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 0).putInt(this.zzc.intValue()).array());
                    } else if (this.zza.zzd() != zzef.zza) {
                        throw new IllegalStateException("Unknown AesEaxParameters.Variant: ".concat(String.valueOf(String.valueOf(this.zza.zzd()))));
                    } else {
                        zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 1).putInt(this.zzc.intValue()).array());
                    }
                    return new zzdz(this.zza, this.zzb, zzb, this.zzc, null);
                }
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
        }
        throw new GeneralSecurityException("Key size mismatch");
    }
}
