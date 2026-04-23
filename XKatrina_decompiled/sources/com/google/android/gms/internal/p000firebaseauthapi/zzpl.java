package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpl  reason: invalid package */
/* loaded from: classes.dex */
final class zzpl extends zznf {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzpl(zzpm zzpmVar, Class cls) {
        super(cls);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ zzalp zza(zzalp zzalpVar) throws GeneralSecurityException {
        zzsc zzscVar = (zzsc) zzalpVar;
        zzry zzb = zzrz.zzb();
        zzb.zzc(0);
        byte[] zzb2 = zzor.zzb(zzscVar.zza());
        zzb.zza(zzajf.zzn(zzb2, 0, zzb2.length));
        zzb.zzb(zzscVar.zze());
        return (zzrz) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ zzalp zzb(zzajf zzajfVar) throws zzaks {
        return zzsc.zzd(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES_CMAC", zzrc.zze);
        hashMap.put("AES256_CMAC", zzrc.zze);
        zzpo zzpoVar = new zzpo(null);
        zzpoVar.zza(32);
        zzpoVar.zzb(16);
        zzpoVar.zzc(zzpp.zzd);
        hashMap.put("AES256_CMAC_RAW", zzpoVar.zzd());
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ void zzd(zzalp zzalpVar) throws GeneralSecurityException {
        zzsc zzscVar = (zzsc) zzalpVar;
        zzpm.zzn(zzscVar.zze());
        zzpm.zzo(zzscVar.zza());
    }
}
