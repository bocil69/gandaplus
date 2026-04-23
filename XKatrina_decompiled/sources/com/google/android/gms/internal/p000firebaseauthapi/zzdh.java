package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdh  reason: invalid package */
/* loaded from: classes.dex */
public final class zzdh extends zzng {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdh() {
        super(zzsi.class, new zzdf(zzbd.class));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zznf zza() {
        return new zzdg(this, zzsl.class);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zzwh zzb() {
        return zzwh.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* synthetic */ zzalp zzc(zzajf zzajfVar) throws zzaks {
        return zzsi.zzd(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* bridge */ /* synthetic */ void zze(zzalp zzalpVar) throws GeneralSecurityException {
        zzsi zzsiVar = (zzsi) zzalpVar;
        zzzl.zzc(zzsiVar.zza(), 0);
        new zzdv();
        zzdv.zzh(zzsiVar.zze());
        new zzqj();
        zzqj.zzm(zzsiVar.zzf());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final int zzf() {
        return 2;
    }
}
