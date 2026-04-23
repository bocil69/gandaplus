package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzna  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzna {
    private final zzzo zza;
    private final Class zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzna(zzzo zzzoVar, Class cls, zzmz zzmzVar) {
        this.zza = zzzoVar;
        this.zzb = cls;
    }

    public static zzna zzb(zzmy zzmyVar, zzzo zzzoVar, Class cls) {
        return new zzmx(zzzoVar, cls, zzmyVar);
    }

    public abstract zzbn zza(zzot zzotVar, @Nullable zzcr zzcrVar) throws GeneralSecurityException;

    public final zzzo zzc() {
        return this.zza;
    }

    public final Class zzd() {
        return this.zzb;
    }
}
