package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmc  reason: invalid package */
/* loaded from: classes.dex */
final class zzmc implements zzlt {
    private final zzln zza;
    private final int zzb;

    private zzmc(zzln zzlnVar, int i) {
        this.zza = zzlnVar;
        this.zzb = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzmc zzc(int i) throws GeneralSecurityException {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 1) {
                return new zzmc(new zzln("HmacSha512"), 3);
            }
            return new zzmc(new zzln("HmacSha384"), 2);
        }
        return new zzmc(new zzln("HmacSha256"), 1);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzlt
    public final byte[] zza(byte[] bArr, zzlu zzluVar) throws GeneralSecurityException {
        byte[] zzf = zzym.zzf(zzym.zzg(this.zzb, zzluVar.zza().zzc()), zzym.zzh(zzym.zzi(this.zzb), 1, bArr));
        byte[] zzb = zzyf.zzb(bArr, zzluVar.zzb().zzc());
        byte[] zze = zzmb.zze(zzb());
        zzln zzlnVar = this.zza;
        return zzlnVar.zzb(null, zzf, "eae_prk", zzb, "shared_secret", zze, zzlnVar.zza());
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzlt
    public final byte[] zzb() throws GeneralSecurityException {
        int i = this.zzb - 1;
        if (i != 0) {
            return i != 1 ? zzmb.zzf : zzmb.zze;
        }
        return zzmb.zzd;
    }
}
