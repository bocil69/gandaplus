package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzbv  reason: invalid package */
/* loaded from: classes.dex */
public final class zzbv {
    @Nullable
    private final zzce zza;

    private zzbv(zzce zzceVar) {
        this.zza = zzceVar;
    }

    public static zzbv zza(zzce zzceVar) throws GeneralSecurityException {
        return new zzbv(zzceVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzwn zzb() throws GeneralSecurityException {
        zzce zzceVar = this.zza;
        return zzceVar instanceof zznj ? ((zznj) zzceVar).zzb().zzc() : ((zzop) zznt.zzc().zzd(zzceVar, zzop.class)).zzc();
    }
}
