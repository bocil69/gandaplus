package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqv  reason: invalid package */
/* loaded from: classes.dex */
final class zzqv implements zzcd {
    private final zzcl zza;
    private final zzrp zzb;
    private final zzrp zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzqv(zzcl zzclVar, zzqu zzquVar) {
        zzrp zzrpVar;
        this.zza = zzclVar;
        if (zzclVar.zzf()) {
            zzrq zzb = zznp.zza().zzb();
            zzrv zza = zznm.zza(zzclVar);
            this.zzb = zzb.zza(zza, "mac", "compute");
            zzrpVar = zzb.zza(zza, "mac", "verify");
        } else {
            zzrpVar = zznm.zza;
            this.zzb = zzrpVar;
        }
        this.zzc = zzrpVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzcd
    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length <= 5) {
            throw new GeneralSecurityException("tag too short");
        }
        for (zzch zzchVar : this.zza.zze(Arrays.copyOf(bArr, 5))) {
            try {
                ((zzcd) zzchVar.zzd()).zza(bArr, bArr2);
                zzchVar.zza();
                return;
            } catch (GeneralSecurityException unused) {
            }
        }
        for (zzch zzchVar2 : this.zza.zze(zzbi.zza)) {
            try {
                ((zzcd) zzchVar2.zzd()).zza(bArr, bArr2);
                zzchVar2.zza();
                return;
            } catch (GeneralSecurityException unused2) {
            }
        }
        throw new GeneralSecurityException("invalid MAC");
    }
}
