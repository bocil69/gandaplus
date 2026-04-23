package com.google.android.gms.internal.p000firebaseauthapi;

import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzro  reason: invalid package */
/* loaded from: classes.dex */
public final class zzro {
    public static final zzro zza = new zzrm().zza();
    private final Map zzb;

    public final boolean equals(Object obj) {
        if (obj instanceof zzro) {
            return this.zzb.equals(((zzro) obj).zzb);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzb.hashCode();
    }

    public final String toString() {
        return this.zzb.toString();
    }

    public final Map zza() {
        return this.zzb;
    }
}
