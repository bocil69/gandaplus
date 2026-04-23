package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakf  reason: invalid package */
/* loaded from: classes.dex */
final class zzakf implements zzaln {
    private static final zzakf zza = new zzakf();

    private zzakf() {
    }

    public static zzakf zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaln
    public final zzalm zzb(Class cls) {
        if (!zzakk.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
        }
        try {
            return (zzalm) zzakk.zzv(cls.asSubclass(zzakk.class)).zzj(3, null, null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzaln
    public final boolean zzc(Class cls) {
        return zzakk.class.isAssignableFrom(cls);
    }
}
