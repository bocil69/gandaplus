package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaka  reason: invalid package */
/* loaded from: classes.dex */
final class zzaka {
    private static final zzajy zza = new zzajz();
    private static final zzajy zzb;

    static {
        zzajy zzajyVar;
        try {
            zzajyVar = (zzajy) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzajyVar = null;
        }
        zzb = zzajyVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzajy zza() {
        zzajy zzajyVar = zzb;
        if (zzajyVar != null) {
            return zzajyVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzajy zzb() {
        return zza;
    }
}
