package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznf  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zznf {
    private final Class zza;

    public zznf(Class cls) {
        this.zza = cls;
    }

    public abstract zzalp zza(zzalp zzalpVar) throws GeneralSecurityException;

    public abstract zzalp zzb(zzajf zzajfVar) throws zzaks;

    public Map zzc() throws GeneralSecurityException {
        return Collections.emptyMap();
    }

    public abstract void zzd(zzalp zzalpVar) throws GeneralSecurityException;
}
