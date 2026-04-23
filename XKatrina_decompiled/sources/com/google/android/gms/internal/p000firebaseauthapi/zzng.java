package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzng  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzng {
    private final Class zza;
    private final Map zzb;
    private final Class zzc;

    /* JADX INFO: Access modifiers changed from: protected */
    @SafeVarargs
    public zzng(Class cls, zzog... zzogVarArr) {
        this.zza = cls;
        HashMap hashMap = new HashMap();
        for (int i = 0; i <= 0; i++) {
            zzog zzogVar = zzogVarArr[i];
            if (hashMap.containsKey(zzogVar.zzb())) {
                throw new IllegalArgumentException("KeyTypeManager constructed with duplicate factories for primitive ".concat(String.valueOf(zzogVar.zzb().getCanonicalName())));
            }
            hashMap.put(zzogVar.zzb(), zzogVar);
        }
        this.zzc = zzogVarArr[0].zzb();
        this.zzb = Collections.unmodifiableMap(hashMap);
    }

    public zznf zza() {
        throw new UnsupportedOperationException("Creating keys is not supported.");
    }

    public abstract zzwh zzb();

    public abstract zzalp zzc(zzajf zzajfVar) throws zzaks;

    public abstract String zzd();

    public abstract void zze(zzalp zzalpVar) throws GeneralSecurityException;

    public int zzf() {
        return 1;
    }

    public final Class zzi() {
        return this.zzc;
    }

    public final Class zzj() {
        return this.zza;
    }

    public final Object zzk(zzalp zzalpVar, Class cls) throws GeneralSecurityException {
        zzog zzogVar = (zzog) this.zzb.get(cls);
        if (zzogVar == null) {
            String canonicalName = cls.getCanonicalName();
            throw new IllegalArgumentException("Requested primitive class " + canonicalName + " not supported.");
        }
        return zzogVar.zza(zzalpVar);
    }

    public final Set zzl() {
        return this.zzb.keySet();
    }
}
