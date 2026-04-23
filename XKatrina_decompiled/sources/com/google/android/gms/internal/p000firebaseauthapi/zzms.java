package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzms  reason: invalid package */
/* loaded from: classes.dex */
public final class zzms {
    final Map zza = new HashMap();
    final Map zzb = new HashMap();

    private zzms() {
    }

    public final zzms zza(Enum r2, Object obj) {
        this.zza.put(r2, obj);
        this.zzb.put(obj, r2);
        return this;
    }

    public final zzmu zzb() {
        return new zzmu(Collections.unmodifiableMap(this.zza), Collections.unmodifiableMap(this.zzb), null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzms(zzmr zzmrVar) {
    }
}
