package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzir  reason: invalid package */
/* loaded from: classes.dex */
public final class zzir extends zzng {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzir() {
        super(zzts.class, new zzip(zzbj.class));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zznf zza() {
        return new zziq(this, zztv.class);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zzwh zzb() {
        return zzwh.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* synthetic */ zzalp zzc(zzajf zzajfVar) throws zzaks {
        return zzts.zzd(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.AesSivKey";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* bridge */ /* synthetic */ void zze(zzalp zzalpVar) throws GeneralSecurityException {
        zzts zztsVar = (zzts) zzalpVar;
        zzzl.zzc(zztsVar.zza(), 0);
        if (zztsVar.zze().zzd() == 64) {
            return;
        }
        int zzd = zztsVar.zze().zzd();
        throw new InvalidKeyException("invalid key size: " + zzd + ". Valid keys must have 64 bytes.");
    }
}
