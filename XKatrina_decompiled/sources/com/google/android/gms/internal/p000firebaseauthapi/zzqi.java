package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqi  reason: invalid package */
/* loaded from: classes.dex */
final class zzqi extends zznf {
    final /* synthetic */ zzqj zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzqi(zzqj zzqjVar, Class cls) {
        super(cls);
        this.zza = zzqjVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ zzalp zza(zzalp zzalpVar) throws GeneralSecurityException {
        zzvi zzviVar = (zzvi) zzalpVar;
        zzve zzb = zzvf.zzb();
        zzb.zzc(0);
        zzb.zzb(zzviVar.zzg());
        byte[] zzb2 = zzor.zzb(zzviVar.zza());
        zzb.zza(zzajf.zzn(zzb2, 0, zzb2.length));
        return (zzvf) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ zzalp zzb(zzajf zzajfVar) throws zzaks {
        return zzvi.zzf(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("HMAC_SHA256_128BITTAG", zzrc.zza);
        zzql zzqlVar = new zzql(null);
        zzqlVar.zzb(32);
        zzqlVar.zzc(16);
        zzqlVar.zzd(zzqn.zzd);
        zzqlVar.zza(zzqm.zzc);
        hashMap.put("HMAC_SHA256_128BITTAG_RAW", zzqlVar.zze());
        zzql zzqlVar2 = new zzql(null);
        zzqlVar2.zzb(32);
        zzqlVar2.zzc(32);
        zzqlVar2.zzd(zzqn.zza);
        zzqlVar2.zza(zzqm.zzc);
        hashMap.put("HMAC_SHA256_256BITTAG", zzqlVar2.zze());
        zzql zzqlVar3 = new zzql(null);
        zzqlVar3.zzb(32);
        zzqlVar3.zzc(32);
        zzqlVar3.zzd(zzqn.zzd);
        zzqlVar3.zza(zzqm.zzc);
        hashMap.put("HMAC_SHA256_256BITTAG_RAW", zzqlVar3.zze());
        zzql zzqlVar4 = new zzql(null);
        zzqlVar4.zzb(64);
        zzqlVar4.zzc(16);
        zzqlVar4.zzd(zzqn.zza);
        zzqlVar4.zza(zzqm.zze);
        hashMap.put("HMAC_SHA512_128BITTAG", zzqlVar4.zze());
        zzql zzqlVar5 = new zzql(null);
        zzqlVar5.zzb(64);
        zzqlVar5.zzc(16);
        zzqlVar5.zzd(zzqn.zzd);
        zzqlVar5.zza(zzqm.zze);
        hashMap.put("HMAC_SHA512_128BITTAG_RAW", zzqlVar5.zze());
        zzql zzqlVar6 = new zzql(null);
        zzqlVar6.zzb(64);
        zzqlVar6.zzc(32);
        zzqlVar6.zzd(zzqn.zza);
        zzqlVar6.zza(zzqm.zze);
        hashMap.put("HMAC_SHA512_256BITTAG", zzqlVar6.zze());
        zzql zzqlVar7 = new zzql(null);
        zzqlVar7.zzb(64);
        zzqlVar7.zzc(32);
        zzqlVar7.zzd(zzqn.zzd);
        zzqlVar7.zza(zzqm.zze);
        hashMap.put("HMAC_SHA512_256BITTAG_RAW", zzqlVar7.zze());
        hashMap.put("HMAC_SHA512_512BITTAG", zzrc.zzd);
        zzql zzqlVar8 = new zzql(null);
        zzqlVar8.zzb(64);
        zzqlVar8.zzc(64);
        zzqlVar8.zzd(zzqn.zzd);
        zzqlVar8.zza(zzqm.zze);
        hashMap.put("HMAC_SHA512_512BITTAG_RAW", zzqlVar8.zze());
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ void zzd(zzalp zzalpVar) throws GeneralSecurityException {
        zzvi zzviVar = (zzvi) zzalpVar;
        if (zzviVar.zza() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        zzqj.zzn(zzviVar.zzg());
    }
}
