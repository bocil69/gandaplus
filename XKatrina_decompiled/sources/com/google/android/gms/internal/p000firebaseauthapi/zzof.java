package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzof  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzof {
    private final Class zza;
    private final Class zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzof(Class cls, Class cls2, zzoe zzoeVar) {
        this.zza = cls;
        this.zzb = cls2;
    }

    public static zzof zzb(zzod zzodVar, Class cls, Class cls2) {
        return new zzoc(cls, cls2, zzodVar);
    }

    public abstract Object zza(zzbn zzbnVar) throws GeneralSecurityException;

    public final Class zzc() {
        return this.zza;
    }

    public final Class zzd() {
        return this.zzb;
    }
}
