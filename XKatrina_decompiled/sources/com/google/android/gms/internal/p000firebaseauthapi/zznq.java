package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznq  reason: invalid package */
/* loaded from: classes.dex */
public final class zznq {
    private static final zznq zza = new zznq();
    private final AtomicReference zzb = new AtomicReference(new zzom(new zzoi(null), null));

    zznq() {
    }

    public static zznq zza() {
        return zza;
    }

    public final Class zzb(Class cls) throws GeneralSecurityException {
        return ((zzom) this.zzb.get()).zza(cls);
    }

    public final Object zzc(zzbn zzbnVar, Class cls) throws GeneralSecurityException {
        return ((zzom) this.zzb.get()).zzb(zzbnVar, cls);
    }

    public final Object zzd(zzcl zzclVar, Class cls) throws GeneralSecurityException {
        return ((zzom) this.zzb.get()).zzc(zzclVar, cls);
    }

    public final synchronized void zze(zzof zzofVar) throws GeneralSecurityException {
        zzoi zzoiVar = new zzoi((zzom) this.zzb.get(), null);
        zzoiVar.zza(zzofVar);
        this.zzb.set(new zzom(zzoiVar, null));
    }

    public final synchronized void zzf(zzcm zzcmVar) throws GeneralSecurityException {
        zzoi zzoiVar = new zzoi((zzom) this.zzb.get(), null);
        zzoiVar.zzb(zzcmVar);
        this.zzb.set(new zzom(zzoiVar, null));
    }
}
