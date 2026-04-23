package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhp  reason: invalid package */
/* loaded from: classes.dex */
public final class zzhp extends zzng {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhp() {
        super(zzxu.class, new zzhn(zzbd.class));
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zznf zza() {
        return new zzho(this, zzxx.class);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final zzwh zzb() {
        return zzwh.SYMMETRIC;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* synthetic */ zzalp zzc(zzajf zzajfVar) throws zzaks {
        return zzxu.zzd(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final String zzd() {
        return "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzng
    public final /* bridge */ /* synthetic */ void zze(zzalp zzalpVar) throws GeneralSecurityException {
        zzxu zzxuVar = (zzxu) zzalpVar;
        zzzl.zzc(zzxuVar.zza(), 0);
        if (zzxuVar.zze().zzd() != 32) {
            throw new GeneralSecurityException("invalid XChaCha20Poly1305Key: incorrect key length");
        }
    }
}
