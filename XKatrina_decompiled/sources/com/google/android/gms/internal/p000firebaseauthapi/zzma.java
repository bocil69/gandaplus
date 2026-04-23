package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzma  reason: invalid package */
/* loaded from: classes.dex */
public final class zzma extends zzng {
    public zzma() {
        super(zzwd.class, new zzlz(zzbl.class));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zzwh zzb() {
        return zzwh.ASYMMETRIC_PUBLIC;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* synthetic */ zzalp zzc(zzajf zzajfVar) throws zzaks {
        return zzwd.zzf(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.HpkePublicKey";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* bridge */ /* synthetic */ void zze(zzalp zzalpVar) throws GeneralSecurityException {
        zzwd zzwdVar = (zzwd) zzalpVar;
        zzzl.zzc(zzwdVar.zza(), 0);
        if (!zzwdVar.zzl()) {
            throw new GeneralSecurityException("Missing HPKE key params.");
        }
        zzmb.zzb(zzwdVar.zzb());
    }
}
