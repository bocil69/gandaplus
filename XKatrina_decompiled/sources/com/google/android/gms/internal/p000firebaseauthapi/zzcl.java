package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcl  reason: invalid package */
/* loaded from: classes.dex */
public final class zzcl {
    private final ConcurrentMap zza;
    private final List zzb;
    private final zzch zzc;
    private final Class zzd;
    private final zzro zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcl(ConcurrentMap concurrentMap, List list, zzch zzchVar, zzro zzroVar, Class cls, zzck zzckVar) {
        this.zza = concurrentMap;
        this.zzb = list;
        this.zzc = zzchVar;
        this.zzd = cls;
        this.zze = zzroVar;
    }

    @Nullable
    public final zzch zza() {
        return this.zzc;
    }

    public final zzro zzb() {
        return this.zze;
    }

    public final Class zzc() {
        return this.zzd;
    }

    public final Collection zzd() {
        return this.zza.values();
    }

    public final List zze(byte[] bArr) {
        List list = (List) this.zza.get(new zzcj(bArr, null));
        return list != null ? list : Collections.emptyList();
    }

    public final boolean zzf() {
        return !this.zze.zza().isEmpty();
    }
}
