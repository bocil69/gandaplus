package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfx  reason: invalid package */
/* loaded from: classes.dex */
final class zzfx extends zznf {
    final /* synthetic */ zzfy zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzfx(zzfy zzfyVar, Class cls) {
        super(cls);
        this.zza = zzfyVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ zzalp zza(zzalp zzalpVar) throws GeneralSecurityException {
        zzub zzubVar = (zzub) zzalpVar;
        zztx zzb = zzty.zzb();
        zzb.zzb(0);
        byte[] zzb2 = zzor.zzb(32);
        zzb.zza(zzajf.zzn(zzb2, 0, zzb2.length));
        return (zzty) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ zzalp zzb(zzajf zzajfVar) throws zzaks {
        return zzub.zzc(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("CHACHA20_POLY1305", zzga.zzc(zzfz.zza));
        hashMap.put("CHACHA20_POLY1305_RAW", zzga.zzc(zzfz.zzc));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ void zzd(zzalp zzalpVar) throws GeneralSecurityException {
        zzub zzubVar = (zzub) zzalpVar;
    }
}
