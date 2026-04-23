package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpa */
/* loaded from: classes.dex */
public final class zzpa {
    private final Map zza;
    private final Map zzb;
    private final Map zzc;
    private final Map zzd;

    public /* synthetic */ zzpa(zzou zzouVar, zzoz zzozVar) {
        Map map;
        Map map2;
        Map map3;
        Map map4;
        map = zzouVar.zza;
        this.zza = new HashMap(map);
        map2 = zzouVar.zzb;
        this.zzb = new HashMap(map2);
        map3 = zzouVar.zzc;
        this.zzc = new HashMap(map3);
        map4 = zzouVar.zzd;
        this.zzd = new HashMap(map4);
    }

    public final zzbn zza(zzot zzotVar, @Nullable zzcr zzcrVar) throws GeneralSecurityException {
        zzow zzowVar = new zzow(zzotVar.getClass(), zzotVar.zzd(), null);
        if (!this.zzb.containsKey(zzowVar)) {
            String obj = zzowVar.toString();
            throw new GeneralSecurityException("No Key Parser for requested key type " + obj + " available");
        }
        return ((zzna) this.zzb.get(zzowVar)).zza(zzotVar, zzcrVar);
    }

    public final zzce zzb(zzot zzotVar) throws GeneralSecurityException {
        zzow zzowVar = new zzow(zzotVar.getClass(), zzotVar.zzd(), null);
        if (!this.zzd.containsKey(zzowVar)) {
            String obj = zzowVar.toString();
            throw new GeneralSecurityException("No Parameters Parser for requested key type " + obj + " available");
        }
        return ((zznx) this.zzd.get(zzowVar)).zza(zzotVar);
    }

    public final zzot zzc(zzce zzceVar, Class cls) throws GeneralSecurityException {
        zzoy zzoyVar = new zzoy(zzceVar.getClass(), cls, null);
        if (!this.zzc.containsKey(zzoyVar)) {
            String obj = zzoyVar.toString();
            throw new GeneralSecurityException("No Key Format serializer for " + obj + " available");
        }
        return ((zzob) this.zzc.get(zzoyVar)).zza(zzceVar);
    }

    public final boolean zzh(zzot zzotVar) {
        return this.zzb.containsKey(new zzow(zzotVar.getClass(), zzotVar.zzd(), null));
    }

    public final boolean zzi(zzot zzotVar) {
        return this.zzd.containsKey(new zzow(zzotVar.getClass(), zzotVar.zzd(), null));
    }
}
