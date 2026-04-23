package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzajx  reason: invalid package */
/* loaded from: classes.dex */
public final class zzajx {
    static final zzajx zza = new zzajx(true);
    public static final /* synthetic */ int zzb = 0;
    private static volatile boolean zzc;
    private final Map zzd;

    zzajx() {
        this.zzd = new HashMap();
    }

    public static zzajx zza() {
        return zza;
    }

    public final zzaki zzb(zzalp zzalpVar, int i) {
        return (zzaki) this.zzd.get(new zzajw(zzalpVar, i));
    }

    zzajx(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
