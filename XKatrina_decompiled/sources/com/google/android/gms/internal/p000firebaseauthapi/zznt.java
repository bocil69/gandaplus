package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznt  reason: invalid package */
/* loaded from: classes.dex */
public final class zznt {
    private static final zznt zza = (zznt) zzpc.zza(new zzpb() { // from class: com.google.android.gms.internal.firebase-auth-api.zznr
        @Override // com.google.android.gms.internal.p000firebaseauthapi.zzpb
        public final Object zza() {
            zznt zzntVar = new zznt();
            zzntVar.zzf(new zznb(zzni.class, zzoo.class, new zznc() { // from class: com.google.android.gms.internal.firebase-auth-api.zzns
            }));
            return zzntVar;
        }
    });
    private final AtomicReference zzb = new AtomicReference(new zzpa(new zzou(), null));

    public static zznt zzc() {
        return zza;
    }

    public final zzbn zza(zzoo zzooVar, @Nullable zzcr zzcrVar) throws GeneralSecurityException {
        if (((zzpa) this.zzb.get()).zzh(zzooVar)) {
            return ((zzpa) this.zzb.get()).zza(zzooVar, zzcrVar);
        }
        return new zzni(zzooVar, zzcrVar);
    }

    public final zzce zzb(zzot zzotVar) throws GeneralSecurityException {
        return ((zzpa) this.zzb.get()).zzb(zzotVar);
    }

    public final zzot zzd(zzce zzceVar, Class cls) throws GeneralSecurityException {
        return ((zzpa) this.zzb.get()).zzc(zzceVar, cls);
    }

    public final synchronized void zze(zzna zznaVar) throws GeneralSecurityException {
        zzou zzouVar = new zzou((zzpa) this.zzb.get());
        zzouVar.zza(zznaVar);
        this.zzb.set(new zzpa(zzouVar, null));
    }

    public final synchronized void zzf(zzne zzneVar) throws GeneralSecurityException {
        zzou zzouVar = new zzou((zzpa) this.zzb.get());
        zzouVar.zzb(zzneVar);
        this.zzb.set(new zzpa(zzouVar, null));
    }

    public final synchronized void zzg(zznx zznxVar) throws GeneralSecurityException {
        zzou zzouVar = new zzou((zzpa) this.zzb.get());
        zzouVar.zzc(zznxVar);
        this.zzb.set(new zzpa(zzouVar, null));
    }

    public final synchronized void zzh(zzob zzobVar) throws GeneralSecurityException {
        zzou zzouVar = new zzou((zzpa) this.zzb.get());
        zzouVar.zzd(zzobVar);
        this.zzb.set(new zzpa(zzouVar, null));
    }

    public final boolean zzi(zzot zzotVar) {
        return ((zzpa) this.zzb.get()).zzi(zzotVar);
    }
}
