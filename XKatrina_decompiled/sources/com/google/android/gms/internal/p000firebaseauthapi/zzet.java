package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzet  reason: invalid package */
/* loaded from: classes.dex */
public final class zzet extends zzng {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzet() {
        super(zztg.class, new zzer(zzbd.class));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zznf zza() {
        return new zzes(this, zztj.class);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zzwh zzb() {
        return zzwh.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* synthetic */ zzalp zzc(zzajf zzajfVar) throws zzaks {
        return zztg.zzd(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* bridge */ /* synthetic */ void zze(zzalp zzalpVar) throws GeneralSecurityException {
        zztg zztgVar = (zztg) zzalpVar;
        zzzl.zzc(zztgVar.zza(), 0);
        zzzl.zzb(zztgVar.zze().zzd());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final int zzf() {
        return 2;
    }
}
