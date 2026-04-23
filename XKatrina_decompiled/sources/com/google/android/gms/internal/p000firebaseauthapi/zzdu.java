package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzdu  reason: invalid package */
/* loaded from: classes.dex */
final class zzdu extends zznf {
    final /* synthetic */ zzdv zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdu(zzdv zzdvVar, Class cls) {
        super(cls);
        this.zza = zzdvVar;
    }

    public static final zzso zzf(zzsr zzsrVar) throws GeneralSecurityException {
        zzsn zzb = zzso.zzb();
        zzb.zzb(zzsrVar.zzf());
        byte[] zzb2 = zzor.zzb(zzsrVar.zza());
        zzb.zza(zzajf.zzn(zzb2, 0, zzb2.length));
        zzb.zzc(0);
        return (zzso) zzb.zzi();
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* bridge */ /* synthetic */ zzalp zza(zzalp zzalpVar) throws GeneralSecurityException {
        return zzf((zzsr) zzalpVar);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    public final /* synthetic */ zzalp zzb(zzajf zzajfVar) throws zzaks {
        return zzsr.zze(zzajfVar, zzajx.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zznf
    /* renamed from: zze */
    public final void zzd(zzsr zzsrVar) throws GeneralSecurityException {
        zzzl.zzb(zzsrVar.zza());
        zzdv zzdvVar = this.zza;
        zzdv.zzm(zzsrVar.zzf());
    }
}
