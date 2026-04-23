package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjl  reason: invalid package */
/* loaded from: classes.dex */
public final class zzjl extends zzon {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjl() {
        super(zzup.class, zzus.class, new zzjj(zzbk.class));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zznf zza() {
        return new zzjk(this, zzuj.class);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zzwh zzb() {
        return zzwh.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* synthetic */ zzalp zzc(zzajf zzajfVar) throws zzaks {
        return zzup.zzd(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* bridge */ /* synthetic */ void zze(zzalp zzalpVar) throws GeneralSecurityException {
        zzup zzupVar = (zzup) zzalpVar;
        if (zzupVar.zzf().zzp()) {
            throw new GeneralSecurityException("invalid ECIES private key");
        }
        zzzl.zzc(zzupVar.zza(), 0);
        zzlj.zzb(zzupVar.zze().zzb());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzon
    public final /* synthetic */ zzalp zzg(zzalp zzalpVar) throws GeneralSecurityException {
        return ((zzup) zzalpVar).zze();
    }
}
