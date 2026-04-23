package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzql  reason: invalid package */
/* loaded from: classes.dex */
public final class zzql {
    @Nullable
    private Integer zza;
    @Nullable
    private Integer zzb;
    private zzqm zzc;
    private zzqn zzd;

    private zzql() {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzql(zzqk zzqkVar) {
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        this.zzd = zzqn.zzd;
    }

    public final zzql zza(zzqm zzqmVar) {
        this.zzc = zzqmVar;
        return this;
    }

    public final zzql zzb(int i) throws GeneralSecurityException {
        this.zza = Integer.valueOf(i);
        return this;
    }

    public final zzql zzc(int i) throws GeneralSecurityException {
        this.zzb = Integer.valueOf(i);
        return this;
    }

    public final zzql zzd(zzqn zzqnVar) {
        this.zzd = zzqnVar;
        return this;
    }

    public final zzqp zze() throws GeneralSecurityException {
        Integer num = this.zza;
        if (num != null) {
            if (this.zzb != null) {
                if (this.zzc != null) {
                    if (this.zzd == null) {
                        throw new GeneralSecurityException("variant is not set");
                    }
                    if (num.intValue() < 16) {
                        throw new InvalidAlgorithmParameterException(String.format("Invalid key size in bytes %d; must be at least 16 bytes", this.zza));
                    }
                    int intValue = this.zzb.intValue();
                    zzqm zzqmVar = this.zzc;
                    if (intValue < 10) {
                        throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; must be at least 10 bytes", Integer.valueOf(intValue)));
                    }
                    if (zzqmVar == zzqm.zza) {
                        if (intValue > 20) {
                            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 20 bytes for SHA1", Integer.valueOf(intValue)));
                        }
                    } else if (zzqmVar == zzqm.zzb) {
                        if (intValue > 28) {
                            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 28 bytes for SHA224", Integer.valueOf(intValue)));
                        }
                    } else if (zzqmVar == zzqm.zzc) {
                        if (intValue > 32) {
                            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 32 bytes for SHA256", Integer.valueOf(intValue)));
                        }
                    } else if (zzqmVar == zzqm.zzd) {
                        if (intValue > 48) {
                            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 48 bytes for SHA384", Integer.valueOf(intValue)));
                        }
                    } else if (zzqmVar != zzqm.zze) {
                        throw new GeneralSecurityException("unknown hash type; must be SHA256, SHA384 or SHA512");
                    } else {
                        if (intValue > 64) {
                            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 64 bytes for SHA512", Integer.valueOf(intValue)));
                        }
                    }
                    return new zzqp(this.zza.intValue(), this.zzb.intValue(), this.zzd, this.zzc, null);
                }
                throw new GeneralSecurityException("hash type is not set");
            }
            throw new GeneralSecurityException("tag size is not set");
        }
        throw new GeneralSecurityException("key size is not set");
    }
}
