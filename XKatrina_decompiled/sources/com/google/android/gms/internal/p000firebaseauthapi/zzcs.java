package com.google.android.gms.internal.p000firebaseauthapi;

import java.io.IOException;
import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcs  reason: invalid package */
/* loaded from: classes.dex */
public final class zzcs {
    public static zzce zza(byte[] bArr) throws GeneralSecurityException {
        try {
            zzwn zzd = zzwn.zzd(bArr, zzajx.zza());
            zznt zzc = zznt.zzc();
            zzop zza = zzop.zza(zzd);
            return !zzc.zzi(zza) ? new zznj(zza) : zzc.zzb(zza);
        } catch (IOException e) {
            throw new GeneralSecurityException("Failed to parse proto", e);
        }
    }

    public static byte[] zzb(zzce zzceVar) throws GeneralSecurityException {
        return zzceVar instanceof zznj ? ((zznj) zzceVar).zzb().zzc().zzq() : ((zzop) zznt.zzc().zzd(zzceVar, zzop.class)).zzc().zzq();
    }
}
