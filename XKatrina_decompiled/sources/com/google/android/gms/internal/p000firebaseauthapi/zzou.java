package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzou  reason: invalid package */
/* loaded from: classes.dex */
public final class zzou {
    private final Map zza;
    private final Map zzb;
    private final Map zzc;
    private final Map zzd;

    public zzou() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
        this.zzc = new HashMap();
        this.zzd = new HashMap();
    }

    public final zzou zza(zzna zznaVar) throws GeneralSecurityException {
        zzow zzowVar = new zzow(zznaVar.zzd(), zznaVar.zzc(), null);
        if (this.zzb.containsKey(zzowVar)) {
            zzna zznaVar2 = (zzna) this.zzb.get(zzowVar);
            if (!zznaVar2.equals(zznaVar) || !zznaVar.equals(zznaVar2)) {
                throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: ".concat(zzowVar.toString()));
            }
        } else {
            this.zzb.put(zzowVar, zznaVar);
        }
        return this;
    }

    public final zzou zzb(zzne zzneVar) throws GeneralSecurityException {
        zzoy zzoyVar = new zzoy(zzneVar.zzb(), zzneVar.zzc(), null);
        if (this.zza.containsKey(zzoyVar)) {
            zzne zzneVar2 = (zzne) this.zza.get(zzoyVar);
            if (!zzneVar2.equals(zzneVar) || !zzneVar.equals(zzneVar2)) {
                throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: ".concat(zzoyVar.toString()));
            }
        } else {
            this.zza.put(zzoyVar, zzneVar);
        }
        return this;
    }

    public final zzou zzc(zznx zznxVar) throws GeneralSecurityException {
        zzow zzowVar = new zzow(zznxVar.zzd(), zznxVar.zzc(), null);
        if (this.zzd.containsKey(zzowVar)) {
            zznx zznxVar2 = (zznx) this.zzd.get(zzowVar);
            if (!zznxVar2.equals(zznxVar) || !zznxVar.equals(zznxVar2)) {
                throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: ".concat(zzowVar.toString()));
            }
        } else {
            this.zzd.put(zzowVar, zznxVar);
        }
        return this;
    }

    public final zzou zzd(zzob zzobVar) throws GeneralSecurityException {
        zzoy zzoyVar = new zzoy(zzobVar.zzc(), zzobVar.zzd(), null);
        if (this.zzc.containsKey(zzoyVar)) {
            zzob zzobVar2 = (zzob) this.zzc.get(zzoyVar);
            if (!zzobVar2.equals(zzobVar) || !zzobVar.equals(zzobVar2)) {
                throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: ".concat(zzoyVar.toString()));
            }
        } else {
            this.zzc.put(zzoyVar, zzobVar);
        }
        return this;
    }

    public zzou(zzpa zzpaVar) {
        this.zza = new HashMap(zzpa.zze(zzpaVar));
        this.zzb = new HashMap(zzpa.zzd(zzpaVar));
        this.zzc = new HashMap(zzpa.zzg(zzpaVar));
        this.zzd = new HashMap(zzpa.zzf(zzpaVar));
    }
}
