package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Collections;
import java.util.HashMap;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrm  reason: invalid package */
/* loaded from: classes.dex */
public final class zzrm {
    private HashMap zza = new HashMap();

    public final zzro zza() {
        if (this.zza == null) {
            throw new IllegalStateException("cannot call build() twice");
        }
        zzro zzroVar = new zzro(Collections.unmodifiableMap(this.zza), null);
        this.zza = null;
        return zzroVar;
    }
}
