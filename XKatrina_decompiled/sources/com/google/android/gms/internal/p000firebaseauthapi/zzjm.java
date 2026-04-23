package com.google.android.gms.internal.p000firebaseauthapi;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjm  reason: invalid package */
/* loaded from: classes.dex */
final class zzjm extends zzog {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjm(Class cls) {
        super(cls);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzog
    public final /* bridge */ /* synthetic */ Object zza(zzalp zzalpVar) throws GeneralSecurityException {
        zzus zzusVar = (zzus) zzalpVar;
        zzum zzb = zzusVar.zzb();
        zzuv zzf = zzb.zzf();
        int zzc = zzlj.zzc(zzf.zzd());
        byte[] zzq = zzusVar.zzg().zzq();
        byte[] zzq2 = zzusVar.zzh().zzq();
        ECParameterSpec zzi = zzym.zzi(zzc);
        ECPoint eCPoint = new ECPoint(new BigInteger(1, zzq), new BigInteger(1, zzq2));
        zzmq.zzf(eCPoint, zzi.getCurve());
        return new zzyj((ECPublicKey) ((KeyFactory) zzyv.zzg.zza("EC")).generatePublic(new ECPublicKeySpec(eCPoint, zzi)), zzf.zzf().zzq(), zzlj.zza(zzf.zze()), zzlj.zzd(zzb.zza()), new zzlk(zzb.zzb().zzd()));
    }
}
