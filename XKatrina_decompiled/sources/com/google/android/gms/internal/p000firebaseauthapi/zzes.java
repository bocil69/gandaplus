package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzes  reason: invalid package */
/* loaded from: classes.dex */
final class zzes extends zznf {
    final /* synthetic */ zzet zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzes(zzet zzetVar, Class cls) {
        super(cls);
        this.zza = zzetVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ zzalp zza(zzalp zzalpVar) throws GeneralSecurityException {
        zztf zzb = zztg.zzb();
        byte[] zzb2 = zzor.zzb(((zztj) zzalpVar).zza());
        zzb.zza(zzajf.zzn(zzb2, 0, zzb2.length));
        zzb.zzb(0);
        return (zztg) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ zzalp zzb(zzajf zzajfVar) throws zzaks {
        return zztj.zze(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_GCM", zzhl.zza);
        zzev zzevVar = new zzev(null);
        zzevVar.zza(12);
        zzevVar.zzb(16);
        zzevVar.zzc(16);
        zzevVar.zzd(zzew.zzc);
        hashMap.put("AES128_GCM_RAW", zzevVar.zze());
        hashMap.put("AES256_GCM", zzhl.zzb);
        zzev zzevVar2 = new zzev(null);
        zzevVar2.zza(12);
        zzevVar2.zzb(32);
        zzevVar2.zzc(16);
        zzevVar2.zzd(zzew.zzc);
        hashMap.put("AES256_GCM_RAW", zzevVar2.zze());
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ void zzd(zzalp zzalpVar) throws GeneralSecurityException {
        zzzl.zzb(((zztj) zzalpVar).zza());
    }
}
