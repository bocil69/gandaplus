package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkl  reason: invalid package */
/* loaded from: classes.dex */
public final class zzkl {
    private zzkn zza;
    private zzkm zzb;
    private zzkh zzc;
    private zzko zzd;

    private zzkl() {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzkl(zzkk zzkkVar) {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        this.zzd = zzko.zzc;
    }

    public final zzkl zza(zzkh zzkhVar) {
        this.zzc = zzkhVar;
        return this;
    }

    public final zzkl zzb(zzkm zzkmVar) {
        this.zzb = zzkmVar;
        return this;
    }

    public final zzkl zzc(zzkn zzknVar) {
        this.zza = zzknVar;
        return this;
    }

    public final zzkl zzd(zzko zzkoVar) {
        this.zzd = zzkoVar;
        return this;
    }

    public final zzkq zze() throws GeneralSecurityException {
        zzkn zzknVar = this.zza;
        if (zzknVar != null) {
            zzkm zzkmVar = this.zzb;
            if (zzkmVar != null) {
                zzkh zzkhVar = this.zzc;
                if (zzkhVar != null) {
                    zzko zzkoVar = this.zzd;
                    if (zzkoVar != null) {
                        return new zzkq(zzknVar, zzkmVar, zzkhVar, zzkoVar, null);
                    }
                    throw new GeneralSecurityException("HPKE variant is not set");
                }
                throw new GeneralSecurityException("HPKE AEAD parameter is not set");
            }
            throw new GeneralSecurityException("HPKE KDF parameter is not set");
        }
        throw new GeneralSecurityException("HPKE KEM parameter is not set");
    }
}
