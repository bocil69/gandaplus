package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.charset.Charset;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzct  reason: invalid package */
/* loaded from: classes.dex */
final class zzct {
    public static final Charset zza = Charset.forName("UTF-8");

    public static zzxa zza(zzwv zzwvVar) {
        zzwx zza2 = zzxa.zza();
        zza2.zzb(zzwvVar.zzb());
        for (zzwu zzwuVar : zzwvVar.zzh()) {
            zzwy zzb = zzwz.zzb();
            zzb.zzc(zzwuVar.zzb().zzf());
            zzb.zzd(zzwuVar.zzk());
            zzb.zzb(zzwuVar.zze());
            zzb.zza(zzwuVar.zza());
            zza2.zza((zzwz) zzb.zzi());
        }
        return (zzxa) zza2.zzi();
    }
}
