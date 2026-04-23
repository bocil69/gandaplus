package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzco  reason: invalid package */
/* loaded from: classes.dex */
final class zzco extends zzbp implements zzcn {
    private final zzon zza;
    private final zzng zzb;

    public zzco(zzon zzonVar, zzng zzngVar, Class cls) {
        super(zzonVar, cls);
        this.zza = zzonVar;
        this.zzb = zzngVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzcn
    public final zzwi zzd(zzajf zzajfVar) throws GeneralSecurityException {
        try {
            zzalp zzc = this.zza.zzc(zzajfVar);
            this.zza.zze(zzc);
            zzalp zzg = this.zza.zzg(zzc);
            this.zzb.zze(zzg);
            zzwf zza = zzwi.zza();
            zza.zzb(this.zzb.zzd());
            zza.zzc(zzg.zzo());
            zza.zza(this.zzb.zzb());
            return (zzwi) zza.zzi();
        } catch (zzaks e) {
            throw new GeneralSecurityException("expected serialized proto of type ", e);
        }
    }
}
