package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqh  reason: invalid package */
/* loaded from: classes.dex */
final class zzqh extends zzog {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzqh(Class cls) {
        super(cls);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzog
    public final /* bridge */ /* synthetic */ Object zza(zzalp zzalpVar) throws GeneralSecurityException {
        zzvf zzvfVar = (zzvf) zzalpVar;
        zzvc zzb = zzvfVar.zzf().zzb();
        SecretKeySpec secretKeySpec = new SecretKeySpec(zzvfVar.zzg().zzq(), "HMAC");
        int zza = zzvfVar.zzf().zza();
        zzvc zzvcVar = zzvc.UNKNOWN_HASH;
        int ordinal = zzb.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal != 3) {
                    if (ordinal != 4) {
                        if (ordinal == 5) {
                            return new zzzj(new zzzi("HMACSHA224", secretKeySpec), zza);
                        }
                        throw new GeneralSecurityException("unknown hash");
                    }
                    return new zzzj(new zzzi("HMACSHA512", secretKeySpec), zza);
                }
                return new zzzj(new zzzi("HMACSHA256", secretKeySpec), zza);
            }
            return new zzzj(new zzzi("HMACSHA384", secretKeySpec), zza);
        }
        return new zzzj(new zzzi("HMACSHA1", secretKeySpec), zza);
    }
}
