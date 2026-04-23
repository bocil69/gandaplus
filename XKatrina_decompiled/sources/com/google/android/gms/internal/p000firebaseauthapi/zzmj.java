package com.google.android.gms.internal.p000firebaseauthapi;

import android.os.Build;
import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmj  reason: invalid package */
/* loaded from: classes.dex */
public final class zzmj {
    private static final Object zza = new Object();
    private static final String zzb = "zzmj";
    private final zzca zzc;
    private final zzbd zzd;
    private final zzbz zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzmj(zzmh zzmhVar, zzmi zzmiVar) {
        this.zzc = new zzmm(zzmh.zza(zzmhVar), zzmh.zzh(zzmhVar), zzmh.zzi(zzmhVar));
        this.zzd = zzmh.zzb(zzmhVar);
        this.zze = zzmh.zzc(zzmhVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean zzd() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public final synchronized zzby zza() throws GeneralSecurityException {
        return this.zze.zzb();
    }
}
