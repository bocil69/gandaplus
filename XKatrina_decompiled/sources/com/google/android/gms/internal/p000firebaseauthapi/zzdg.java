package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdg  reason: invalid package */
/* loaded from: classes.dex */
final class zzdg extends zznf {
    final /* synthetic */ zzdh zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdg(zzdh zzdhVar, Class cls) {
        super(cls);
        this.zza = zzdhVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ zzalp zza(zzalp zzalpVar) throws GeneralSecurityException {
        zzsl zzslVar = (zzsl) zzalpVar;
        new zzdv();
        zzso zzf = zzdu.zzf(zzslVar.zzd());
        zzalp zza = new zzqj().zza().zza(zzslVar.zze());
        zzsh zzb = zzsi.zzb();
        zzb.zza(zzf);
        zzb.zzb((zzvf) zza);
        zzb.zzc(0);
        return (zzsi) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ zzalp zzb(zzajf zzajfVar) throws zzaks {
        return zzsl.zzc(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_CTR_HMAC_SHA256", zzhl.zze);
        zzdj zzdjVar = new zzdj(null);
        zzdjVar.zza(16);
        zzdjVar.zzc(32);
        zzdjVar.zze(16);
        zzdjVar.zzd(16);
        zzdjVar.zzb(zzdk.zzc);
        zzdjVar.zzf(zzdl.zzc);
        hashMap.put("AES128_CTR_HMAC_SHA256_RAW", zzdjVar.zzg());
        hashMap.put("AES256_CTR_HMAC_SHA256", zzhl.zzf);
        zzdj zzdjVar2 = new zzdj(null);
        zzdjVar2.zza(32);
        zzdjVar2.zzc(32);
        zzdjVar2.zze(32);
        zzdjVar2.zzd(16);
        zzdjVar2.zzb(zzdk.zzc);
        zzdjVar2.zzf(zzdl.zzc);
        hashMap.put("AES256_CTR_HMAC_SHA256_RAW", zzdjVar2.zzg());
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ void zzd(zzalp zzalpVar) throws GeneralSecurityException {
        zzsl zzslVar = (zzsl) zzalpVar;
        ((zzdu) new zzdv().zza()).zzd(zzslVar.zzd());
        new zzqj().zza().zzd(zzslVar.zze());
        zzzl.zzb(zzslVar.zzd().zza());
    }
}
