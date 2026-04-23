package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzob  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzob {
    private final Class zza;
    private final Class zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzob(Class cls, Class cls2, zzoa zzoaVar) {
        this.zza = cls;
        this.zzb = cls2;
    }

    public static zzob zzb(zznz zznzVar, Class cls, Class cls2) {
        return new zzny(cls, cls2, zznzVar);
    }

    public abstract zzot zza(zzce zzceVar) throws GeneralSecurityException;

    public final Class zzc() {
        return this.zza;
    }

    public final Class zzd() {
        return this.zzb;
    }
}
