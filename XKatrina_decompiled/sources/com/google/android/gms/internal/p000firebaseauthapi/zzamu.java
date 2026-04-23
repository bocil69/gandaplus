package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzamu  reason: invalid package */
/* loaded from: classes.dex */
public final class zzamu extends RuntimeException {
    public zzamu(zzalp zzalpVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzaks zza() {
        return new zzaks(getMessage());
    }
}
