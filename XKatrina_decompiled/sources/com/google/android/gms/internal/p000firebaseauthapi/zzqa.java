package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.List;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzqa  reason: invalid package */
/* loaded from: classes.dex */
public final class zzqa implements zzcm {
    private static final zzqa zza = new zzqa();

    private zzqa() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzd() throws GeneralSecurityException {
        zzcq.zzh(zza);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzcm
    public final Class zza() {
        return zzpx.class;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzcm
    public final Class zzb() {
        return zzpx.class;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzcm
    public final /* bridge */ /* synthetic */ Object zzc(zzcl zzclVar) throws GeneralSecurityException {
        if (zzclVar.zza() == null) {
            throw new GeneralSecurityException("no primary in primitive set");
        }
        for (List<zzch> list : zzclVar.zzd()) {
            for (zzch zzchVar : list) {
                zzpx zzpxVar = (zzpx) zzchVar.zzd();
            }
        }
        return new zzpz(zzclVar, null);
    }
}
