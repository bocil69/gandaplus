package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoo  reason: invalid package */
/* loaded from: classes.dex */
public final class zzoo implements zzot {
    private final String zza;
    private final zzzo zzb;
    private final zzajf zzc;
    private final zzwh zzd;
    private final zzxo zze;
    @Nullable
    private final Integer zzf;

    private zzoo(String str, zzajf zzajfVar, zzwh zzwhVar, zzxo zzxoVar, @Nullable Integer num) {
        this.zza = str;
        this.zzb = zzpd.zzb(str);
        this.zzc = zzajfVar;
        this.zzd = zzwhVar;
        this.zze = zzxoVar;
        this.zzf = num;
    }

    public static zzoo zza(String str, zzajf zzajfVar, zzwh zzwhVar, zzxo zzxoVar, @Nullable Integer num) throws GeneralSecurityException {
        if (zzxoVar == zzxo.RAW) {
            if (num != null) {
                throw new GeneralSecurityException("Keys with output prefix type raw should not have an id requirement.");
            }
        } else if (num == null) {
            throw new GeneralSecurityException("Keys with output prefix type different from raw should have an id requirement.");
        }
        return new zzoo(str, zzajfVar, zzwhVar, zzxoVar, num);
    }

    public final zzwh zzb() {
        return this.zzd;
    }

    public final zzxo zzc() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzot
    public final zzzo zzd() {
        return this.zzb;
    }

    public final zzajf zze() {
        return this.zzc;
    }

    @Nullable
    public final Integer zzf() {
        return this.zzf;
    }

    public final String zzg() {
        return this.zza;
    }
}
