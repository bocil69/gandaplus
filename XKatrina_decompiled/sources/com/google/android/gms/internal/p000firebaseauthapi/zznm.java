package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznm  reason: invalid package */
/* loaded from: classes.dex */
public final class zznm {
    public static final zzrp zza = new zznl(null);

    public static zzrv zza(zzcl zzclVar) {
        zzbu zzbuVar;
        zzrr zzrrVar = new zzrr();
        zzrrVar.zzb(zzclVar.zzb());
        for (List<zzch> list : zzclVar.zzd()) {
            for (zzch zzchVar : list) {
                int zzh = zzchVar.zzh() - 2;
                if (zzh == 1) {
                    zzbuVar = zzbu.zza;
                } else if (zzh == 2) {
                    zzbuVar = zzbu.zzb;
                } else if (zzh == 3) {
                    zzbuVar = zzbu.zzc;
                } else {
                    throw new IllegalStateException("Unknown key status");
                }
                int zza2 = zzchVar.zza();
                String zzf = zzchVar.zzf();
                if (zzf.startsWith("type.googleapis.com/google.crypto.")) {
                    zzf = zzf.substring(34);
                }
                zzrrVar.zza(zzbuVar, zza2, zzf, zzchVar.zzc().name());
            }
        }
        if (zzclVar.zza() != null) {
            zzrrVar.zzc(zzclVar.zza().zza());
        }
        try {
            return zzrrVar.zzd();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
