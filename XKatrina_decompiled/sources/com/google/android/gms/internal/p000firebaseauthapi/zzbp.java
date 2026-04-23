package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbp  reason: invalid package */
/* loaded from: classes.dex */
class zzbp implements zzbo {
    private final zzng zza;
    private final Class zzb;

    public zzbp(zzng zzngVar, Class cls) {
        if (!zzngVar.zzl().contains(cls) && !Void.class.equals(cls)) {
            throw new IllegalArgumentException(String.format("Given internalKeyMananger %s does not support primitive class %s", zzngVar.toString(), cls.getName()));
        }
        this.zza = zzngVar;
        this.zzb = cls;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbo
    public final zzwi zza(zzajf zzajfVar) throws GeneralSecurityException {
        try {
            zznf zza = this.zza.zza();
            zzalp zzb = zza.zzb(zzajfVar);
            zza.zzd(zzb);
            zzalp zza2 = zza.zza(zzb);
            zzwf zza3 = zzwi.zza();
            zza3.zzb(this.zza.zzd());
            zza3.zzc(zza2.zzo());
            zza3.zza(this.zza.zzb());
            return (zzwi) zza3.zzi();
        } catch (zzaks e) {
            throw new GeneralSecurityException("Unexpected proto", e);
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbo
    public final Object zzb(zzajf zzajfVar) throws GeneralSecurityException {
        try {
            zzalp zzc = this.zza.zzc(zzajfVar);
            if (Void.class.equals(this.zzb)) {
                throw new GeneralSecurityException("Cannot create a primitive for Void");
            }
            this.zza.zze(zzc);
            return this.zza.zzk(zzc, this.zzb);
        } catch (zzaks e) {
            throw new GeneralSecurityException("Failures parsing proto of type ".concat(String.valueOf(this.zza.zzj().getName())), e);
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbo
    public final String zzc() {
        return this.zza.zzd();
    }
}
