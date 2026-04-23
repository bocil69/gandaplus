package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzom */
/* loaded from: classes.dex */
public final class zzom {
    private final Map zza;
    private final Map zzb;

    public /* synthetic */ zzom(zzoi zzoiVar, zzol zzolVar) {
        Map map;
        Map map2;
        map = zzoiVar.zza;
        this.zza = new HashMap(map);
        map2 = zzoiVar.zzb;
        this.zzb = new HashMap(map2);
    }

    public final Class zza(Class cls) throws GeneralSecurityException {
        if (!this.zzb.containsKey(cls)) {
            String obj = cls.toString();
            throw new GeneralSecurityException("No input primitive class for " + obj + " available");
        }
        return ((zzcm) this.zzb.get(cls)).zza();
    }

    public final Object zzb(zzbn zzbnVar, Class cls) throws GeneralSecurityException {
        zzok zzokVar = new zzok(zzbnVar.getClass(), cls, null);
        if (!this.zza.containsKey(zzokVar)) {
            String obj = zzokVar.toString();
            throw new GeneralSecurityException("No PrimitiveConstructor for " + obj + " available");
        }
        return ((zzof) this.zza.get(zzokVar)).zza(zzbnVar);
    }

    public final Object zzc(zzcl zzclVar, Class cls) throws GeneralSecurityException {
        if (!this.zzb.containsKey(cls)) {
            throw new GeneralSecurityException("No wrapper found for ".concat(cls.toString()));
        }
        zzcm zzcmVar = (zzcm) this.zzb.get(cls);
        if (!zzclVar.zzc().equals(zzcmVar.zza()) || !zzcmVar.zza().equals(zzclVar.zzc())) {
            throw new GeneralSecurityException("Input primitive type of the wrapper doesn't match the type of primitives in the provided PrimitiveSet");
        }
        return zzcmVar.zzc(zzclVar);
    }
}
