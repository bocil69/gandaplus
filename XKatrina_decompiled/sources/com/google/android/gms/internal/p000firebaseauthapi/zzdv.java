package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdv  reason: invalid package */
/* loaded from: classes.dex */
public final class zzdv extends zzng {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdv() {
        super(zzso.class, new zzdt(zzzf.class));
    }

    public static final void zzh(zzso zzsoVar) throws GeneralSecurityException {
        zzzl.zzc(zzsoVar.zza(), 0);
        zzzl.zzb(zzsoVar.zzg().zzd());
        zzm(zzsoVar.zzf());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void zzm(zzsu zzsuVar) throws GeneralSecurityException {
        if (zzsuVar.zza() < 12 || zzsuVar.zza() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zznf zza() {
        return new zzdu(this, zzsr.class);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zzwh zzb() {
        return zzwh.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* synthetic */ zzalp zzc(zzajf zzajfVar) throws zzaks {
        return zzso.zze(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesCtrKey";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* bridge */ /* synthetic */ void zze(zzalp zzalpVar) throws GeneralSecurityException {
        zzh((zzso) zzalpVar);
    }
}
