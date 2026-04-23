package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.logging.Logger;
import java.util.HashMap;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafq  reason: invalid package */
/* loaded from: classes.dex */
final class zzafq extends zzadx {
    final /* synthetic */ zzaft zza;
    private final String zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzafq(zzaft zzaftVar, zzadx zzadxVar, String str) {
        super(zzadxVar);
        this.zza = zzaftVar;
        this.zzb = str;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadx
    public final void zzb(String str) {
        Logger logger;
        HashMap hashMap;
        logger = zzaft.zza;
        logger.d("onCodeSent", new Object[0]);
        hashMap = this.zza.zzd;
        zzafs zzafsVar = (zzafs) hashMap.get(this.zzb);
        if (zzafsVar == null) {
            return;
        }
        for (zzadx zzadxVar : zzafsVar.zzb) {
            zzadxVar.zzb(str);
        }
        zzafsVar.zzg = true;
        zzafsVar.zzd = str;
        if (zzafsVar.zza <= 0) {
            this.zza.zzg(this.zzb);
        } else if (!zzafsVar.zzc) {
            this.zza.zzm(this.zzb);
        } else if (!zzac.zzd(zzafsVar.zze)) {
            zzaft.zzd(this.zza, this.zzb);
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzadx
    public final void zzh(Status status) {
        Logger logger;
        HashMap hashMap;
        logger = zzaft.zza;
        String statusCodeString = CommonStatusCodes.getStatusCodeString(status.getStatusCode());
        String statusMessage = status.getStatusMessage();
        logger.e("SMS verification code request failed: " + statusCodeString + " " + statusMessage, new Object[0]);
        hashMap = this.zza.zzd;
        zzafs zzafsVar = (zzafs) hashMap.get(this.zzb);
        if (zzafsVar == null) {
            return;
        }
        for (zzadx zzadxVar : zzafsVar.zzb) {
            zzadxVar.zzh(status);
        }
        this.zza.zzi(this.zzb);
    }
}
