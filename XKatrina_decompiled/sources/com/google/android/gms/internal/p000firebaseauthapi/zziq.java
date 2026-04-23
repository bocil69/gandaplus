package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zziq  reason: invalid package */
/* loaded from: classes.dex */
final class zziq extends zznf {
    final /* synthetic */ zzir zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zziq(zzir zzirVar, Class cls) {
        super(cls);
        this.zza = zzirVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ zzalp zza(zzalp zzalpVar) throws GeneralSecurityException {
        zztr zzb = zzts.zzb();
        byte[] zzb2 = zzor.zzb(((zztv) zzalpVar).zza());
        zzb.zza(zzajf.zzn(zzb2, 0, zzb2.length));
        zzb.zzb(0);
        return (zzts) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ zzalp zzb(zzajf zzajfVar) throws zzaks {
        return zztv.zze(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES256_SIV", zzji.zza);
        zzit zzitVar = new zzit(null);
        zzitVar.zza(64);
        zzitVar.zzb(zziu.zzc);
        hashMap.put("AES256_SIV_RAW", zzitVar.zzc());
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ void zzd(zzalp zzalpVar) throws GeneralSecurityException {
        zztv zztvVar = (zztv) zzalpVar;
        if (zztvVar.zza() == 64) {
            return;
        }
        int zza = zztvVar.zza();
        throw new InvalidAlgorithmParameterException("invalid key size: " + zza + ". Valid keys must have 64 bytes.");
    }
}
