package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzoi  reason: invalid package */
/* loaded from: classes.dex */
public final class zzoi {
    private final Map zza;
    private final Map zzb;

    private zzoi() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
    }

    public final zzoi zza(zzof zzofVar) throws GeneralSecurityException {
        zzok zzokVar = new zzok(zzofVar.zzc(), zzofVar.zzd(), null);
        if (this.zza.containsKey(zzokVar)) {
            zzof zzofVar2 = (zzof) this.zza.get(zzokVar);
            if (!zzofVar2.equals(zzofVar) || !zzofVar.equals(zzofVar2)) {
                throw new GeneralSecurityException("Attempt to register non-equal PrimitiveConstructor object for already existing object of type: ".concat(zzokVar.toString()));
            }
        } else {
            this.zza.put(zzokVar, zzofVar);
        }
        return this;
    }

    public final zzoi zzb(zzcm zzcmVar) throws GeneralSecurityException {
        Map map = this.zzb;
        Class zzb = zzcmVar.zzb();
        if (map.containsKey(zzb)) {
            zzcm zzcmVar2 = (zzcm) this.zzb.get(zzb);
            if (!zzcmVar2.equals(zzcmVar) || !zzcmVar.equals(zzcmVar2)) {
                throw new GeneralSecurityException("Attempt to register non-equal PrimitiveWrapper object or input class object for already existing object of type".concat(zzb.toString()));
            }
        } else {
            this.zzb.put(zzb, zzcmVar);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzoi(zzoh zzohVar) {
        this.zza = new HashMap();
        this.zzb = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzoi(zzom zzomVar, zzoh zzohVar) {
        this.zza = new HashMap(zzom.zzd(zzomVar));
        this.zzb = new HashMap(zzom.zze(zzomVar));
    }
}
