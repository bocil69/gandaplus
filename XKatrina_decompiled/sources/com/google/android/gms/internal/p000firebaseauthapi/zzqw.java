package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqw  reason: invalid package */
/* loaded from: classes.dex */
final class zzqw implements zzcm {
    private static final zzqw zza = new zzqw();
    private static final zzof zzb = zzof.zzb(new zzod() { // from class: com.google.android.gms.internal.firebase-auth-api.zzqt
        @Override // com.google.android.gms.internal.p000firebaseauthapi.zzod
        public final Object zza(zzbn zzbnVar) {
            return zzrl.zzb((zzni) zzbnVar);
        }
    }, zzni.class, zzcd.class);

    zzqw() {
    }

    public static void zzd() throws GeneralSecurityException {
        zzcq.zzh(zza);
        zznq.zza().zze(zzb);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzcm
    public final Class zza() {
        return zzcd.class;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzcm
    public final Class zzb() {
        return zzcd.class;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzcm
    public final /* bridge */ /* synthetic */ Object zzc(zzcl zzclVar) throws GeneralSecurityException {
        for (List<zzch> list : zzclVar.zzd()) {
            for (zzch zzchVar : list) {
                if (zzchVar.zzb() instanceof zzqr) {
                    zzqr zzqrVar = (zzqr) zzchVar.zzb();
                    zzzo zzb2 = zzzo.zzb(zzchVar.zzg());
                    if (!zzb2.equals(zzqrVar.zzc())) {
                        String valueOf = String.valueOf(zzqrVar.zzb());
                        String obj = zzqrVar.zzc().toString();
                        String obj2 = zzb2.toString();
                        throw new GeneralSecurityException("Mac Key with parameters " + valueOf + " has wrong output prefix (" + obj + ") instead of (" + obj2 + ")");
                    }
                }
            }
        }
        return new zzqv(zzclVar, null);
    }
}
