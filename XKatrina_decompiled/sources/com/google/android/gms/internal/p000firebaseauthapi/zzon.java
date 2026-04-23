package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzon  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzon extends zzng {
    private final Class zza;

    /* JADX INFO: Access modifiers changed from: protected */
    @SafeVarargs
    public zzon(Class cls, Class cls2, zzog... zzogVarArr) {
        super(cls, zzogVarArr);
        this.zza = cls2;
    }

    public abstract zzalp zzg(zzalp zzalpVar) throws GeneralSecurityException;
}
