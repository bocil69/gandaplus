package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznx  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zznx {
    private final zzzo zza;
    private final Class zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zznx(zzzo zzzoVar, Class cls, zznw zznwVar) {
        this.zza = zzzoVar;
        this.zzb = cls;
    }

    public static zznx zzb(zznv zznvVar, zzzo zzzoVar, Class cls) {
        return new zznu(zzzoVar, cls, zznvVar);
    }

    public abstract zzce zza(zzot zzotVar) throws GeneralSecurityException;

    public final zzzo zzc() {
        return this.zza;
    }

    public final Class zzd() {
        return this.zzb;
    }
}
