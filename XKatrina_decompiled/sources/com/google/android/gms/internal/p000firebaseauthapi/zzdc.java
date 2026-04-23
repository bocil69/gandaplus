package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdc  reason: invalid package */
/* loaded from: classes.dex */
public final class zzdc {
    @Nullable
    private zzdn zza = null;
    @Nullable
    private zzzq zzb = null;
    @Nullable
    private zzzq zzc = null;
    @Nullable
    private Integer zzd = null;

    private zzdc() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzdc(zzdb zzdbVar) {
    }

    public final zzdc zza(zzzq zzzqVar) {
        this.zzb = zzzqVar;
        return this;
    }

    public final zzdc zzb(zzzq zzzqVar) {
        this.zzc = zzzqVar;
        return this;
    }

    public final zzdc zzc(@Nullable Integer num) {
        this.zzd = num;
        return this;
    }

    public final zzdc zzd(zzdn zzdnVar) {
        this.zza = zzdnVar;
        return this;
    }

    public final zzde zze() throws GeneralSecurityException {
        zzzo zzb;
        zzdn zzdnVar = this.zza;
        if (zzdnVar != null) {
            zzzq zzzqVar = this.zzb;
            if (zzzqVar == null || this.zzc == null) {
                throw new GeneralSecurityException("Cannot build without key material");
            }
            if (zzdnVar.zzb() != zzzqVar.zza()) {
                throw new GeneralSecurityException("AES key size mismatch");
            }
            if (zzdnVar.zzc() != this.zzc.zza()) {
                throw new GeneralSecurityException("HMAC key size mismatch");
            }
            if (!this.zza.zza() || this.zzd != null) {
                if (this.zza.zza() || this.zzd == null) {
                    if (this.zza.zzh() == zzdl.zzc) {
                        zzb = zzzo.zzb(new byte[0]);
                    } else if (this.zza.zzh() == zzdl.zzb) {
                        zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 0).putInt(this.zzd.intValue()).array());
                    } else if (this.zza.zzh() != zzdl.zza) {
                        throw new IllegalStateException("Unknown AesCtrHmacAeadParameters.Variant: ".concat(String.valueOf(String.valueOf(this.zza.zzh()))));
                    } else {
                        zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 1).putInt(this.zzd.intValue()).array());
                    }
                    return new zzde(this.zza, this.zzb, this.zzc, zzb, this.zzd, null);
                }
                throw new GeneralSecurityException("Cannot create key with ID requirement with parameters without ID requirement");
            }
            throw new GeneralSecurityException("Cannot create key without ID requirement with parameters with ID requirement");
        }
        throw new GeneralSecurityException("Cannot build without parameters");
    }
}
