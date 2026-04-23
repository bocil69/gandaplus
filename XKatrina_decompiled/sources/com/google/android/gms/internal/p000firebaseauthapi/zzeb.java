package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzeb  reason: invalid package */
/* loaded from: classes.dex */
final class zzeb extends zznf {
    final /* synthetic */ zzec zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzeb(zzec zzecVar, Class cls) {
        super(cls);
        this.zza = zzecVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ zzalp zza(zzalp zzalpVar) throws GeneralSecurityException {
        zzta zztaVar = (zzta) zzalpVar;
        zzsw zzb = zzsx.zzb();
        byte[] zzb2 = zzor.zzb(zztaVar.zza());
        zzb.zza(zzajf.zzn(zzb2, 0, zzb2.length));
        zzb.zzb(zztaVar.zze());
        zzb.zzc(0);
        return (zzsx) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ zzalp zzb(zzajf zzajfVar) throws zzaks {
        return zzta.zzd(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_EAX", zzhl.zzc);
        zzee zzeeVar = new zzee(null);
        zzeeVar.zza(16);
        zzeeVar.zzb(16);
        zzeeVar.zzc(16);
        zzeeVar.zzd(zzef.zzc);
        hashMap.put("AES128_EAX_RAW", zzeeVar.zze());
        hashMap.put("AES256_EAX", zzhl.zzd);
        zzee zzeeVar2 = new zzee(null);
        zzeeVar2.zza(16);
        zzeeVar2.zzb(32);
        zzeeVar2.zzc(16);
        zzeeVar2.zzd(zzef.zzc);
        hashMap.put("AES256_EAX_RAW", zzeeVar2.zze());
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ void zzd(zzalp zzalpVar) throws GeneralSecurityException {
        zzta zztaVar = (zzta) zzalpVar;
        zzzl.zzb(zztaVar.zza());
        if (zztaVar.zze().zza() != 12 && zztaVar.zze().zza() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }
}
