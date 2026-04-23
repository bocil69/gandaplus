package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzly  reason: invalid package */
/* loaded from: classes.dex */
public final class zzly extends zzon {
    public zzly() {
        super(zzwa.class, zzwd.class, new zzlw(zzbk.class));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zznf zza() {
        return new zzlx(this, zzvu.class);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zzwh zzb() {
        return zzwh.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* synthetic */ zzalp zzc(zzajf zzajfVar) throws zzaks {
        return zzwa.zzd(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.HpkePrivateKey";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* bridge */ /* synthetic */ void zze(zzalp zzalpVar) throws GeneralSecurityException {
        zzwa zzwaVar = (zzwa) zzalpVar;
        if (zzwaVar.zzf().zzp()) {
            throw new GeneralSecurityException("Private key is empty.");
        }
        if (!zzwaVar.zzk()) {
            throw new GeneralSecurityException("Missing public key.");
        }
        zzzl.zzc(zzwaVar.zza(), 0);
        zzmb.zzb(zzwaVar.zze().zzb());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzon
    public final /* synthetic */ zzalp zzg(zzalp zzalpVar) throws GeneralSecurityException {
        return ((zzwa) zzalpVar).zze();
    }
}
