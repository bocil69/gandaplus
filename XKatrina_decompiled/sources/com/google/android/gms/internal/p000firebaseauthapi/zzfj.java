package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfj  reason: invalid package */
/* loaded from: classes.dex */
final class zzfj extends zznf {
    final /* synthetic */ zzfk zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfj(zzfk zzfkVar, Class cls) {
        super(cls);
        this.zza = zzfkVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ zzalp zza(zzalp zzalpVar) throws GeneralSecurityException {
        zztl zzb = zztm.zzb();
        byte[] zzb2 = zzor.zzb(((zztp) zzalpVar).zza());
        zzb.zza(zzajf.zzn(zzb2, 0, zzb2.length));
        zzb.zzb(0);
        return (zztm) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ zzalp zzb(zzajf zzajfVar) throws zzaks {
        return zztp.zze(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        zzfm zzfmVar = new zzfm(null);
        zzfmVar.zza(16);
        zzfmVar.zzb(zzfn.zza);
        hashMap.put("AES128_GCM_SIV", zzfmVar.zzc());
        zzfm zzfmVar2 = new zzfm(null);
        zzfmVar2.zza(16);
        zzfmVar2.zzb(zzfn.zzc);
        hashMap.put("AES128_GCM_SIV_RAW", zzfmVar2.zzc());
        zzfm zzfmVar3 = new zzfm(null);
        zzfmVar3.zza(32);
        zzfmVar3.zzb(zzfn.zza);
        hashMap.put("AES256_GCM_SIV", zzfmVar3.zzc());
        zzfm zzfmVar4 = new zzfm(null);
        zzfmVar4.zza(32);
        zzfmVar4.zzb(zzfn.zzc);
        hashMap.put("AES256_GCM_SIV_RAW", zzfmVar4.zzc());
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ void zzd(zzalp zzalpVar) throws GeneralSecurityException {
        zzzl.zzb(((zztp) zzalpVar).zza());
    }
}
