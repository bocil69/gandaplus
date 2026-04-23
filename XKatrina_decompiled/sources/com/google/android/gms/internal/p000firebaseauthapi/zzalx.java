package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzalx  reason: invalid package */
/* loaded from: classes.dex */
public final class zzalx {
    private static final zzalx zza = new zzalx();
    private final ConcurrentMap zzc = new ConcurrentHashMap();
    private final zzamc zzb = new zzalh();

    private zzalx() {
    }

    public static zzalx zza() {
        return zza;
    }

    public final zzamb zzb(Class cls) {
        zzakq.zzc(cls, "messageType");
        zzamb zzambVar = (zzamb) this.zzc.get(cls);
        if (zzambVar == null) {
            zzambVar = this.zzb.zza(cls);
            zzakq.zzc(cls, "messageType");
            zzamb zzambVar2 = (zzamb) this.zzc.putIfAbsent(cls, zzambVar);
            if (zzambVar2 != null) {
                return zzambVar2;
            }
        }
        return zzambVar;
    }
}
