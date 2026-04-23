package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgl  reason: invalid package */
/* loaded from: classes.dex */
final class zzgl extends zznf {
    final /* synthetic */ zzgm zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzgl(zzgm zzgmVar, Class cls) {
        super(cls);
        this.zza = zzgmVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ zzalp zza(zzalp zzalpVar) throws GeneralSecurityException {
        zzxi zzb = zzxj.zzb();
        zzb.zza((zzxm) zzalpVar);
        zzb.zzb(0);
        return (zzxj) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ zzalp zzb(zzajf zzajfVar) throws zzaks {
        return zzxm.zze(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ void zzd(zzalp zzalpVar) throws GeneralSecurityException {
        zzxm zzxmVar = (zzxm) zzalpVar;
        if (!zzgj.zzc(zzxmVar.zza().zzg())) {
            String zzg = zzxmVar.zza().zzg();
            throw new GeneralSecurityException("Unsupported DEK key type: " + zzg + ". Only Tink AEAD key types are supported.");
        } else if (zzxmVar.zzf().isEmpty() || !zzxmVar.zzi()) {
            throw new GeneralSecurityException("invalid key format: missing KEK URI or DEK template");
        }
    }
}
