package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Set;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjr  reason: invalid package */
/* loaded from: classes.dex */
public final class zzjr {
    private zzjs zza;
    private zzjt zzb;
    private zzju zzc;
    private zzce zzd;
    private zzjv zze;
    @Nullable
    private zzzo zzf;

    private zzjr() {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzjr(zzjq zzjqVar) {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
        this.zze = zzjv.zzc;
        this.zzf = null;
    }

    public final zzjr zza(zzjs zzjsVar) {
        this.zza = zzjsVar;
        return this;
    }

    public final zzjr zzb(zzce zzceVar) throws GeneralSecurityException {
        Set set;
        set = zzjx.zza;
        if (set.contains(zzceVar)) {
            this.zzd = zzceVar;
            return this;
        }
        String obj = zzceVar.toString();
        throw new GeneralSecurityException("Invalid DEM parameters " + obj + "; only AES128_GCM_RAW, AES256_GCM_RAW, AES128_CTR_HMAC_SHA256_RAW, AES256_CTR_HMAC_SHA256_RAW XCHACHA20_POLY1305_RAW and AES256_SIV_RAW are currently supported.");
    }

    public final zzjr zzc(zzjt zzjtVar) {
        this.zzb = zzjtVar;
        return this;
    }

    public final zzjr zzd(zzju zzjuVar) {
        this.zzc = zzjuVar;
        return this;
    }

    public final zzjr zze(zzzo zzzoVar) {
        if (zzzoVar.zza() == 0) {
            this.zzf = null;
            return this;
        }
        this.zzf = zzzoVar;
        return this;
    }

    public final zzjr zzf(zzjv zzjvVar) {
        this.zze = zzjvVar;
        return this;
    }

    public final zzjx zzg() throws GeneralSecurityException {
        zzjs zzjsVar = this.zza;
        if (zzjsVar != null) {
            if (this.zzb != null) {
                if (this.zzd != null) {
                    if (this.zze != null) {
                        zzjs zzjsVar2 = zzjs.zzd;
                        if (zzjsVar == zzjsVar2 || this.zzc != null) {
                            if (zzjsVar != zzjsVar2 || this.zzc == null) {
                                return new zzjx(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, null);
                            }
                            throw new GeneralSecurityException("For Curve25519 point format must not be set");
                        }
                        throw new GeneralSecurityException("Point format is not set");
                    }
                    throw new GeneralSecurityException("Variant is not set");
                }
                throw new GeneralSecurityException("DEM parameters are not set");
            }
            throw new GeneralSecurityException("Hash type is not set");
        }
        throw new GeneralSecurityException("Elliptic curve type is not set");
    }
}
