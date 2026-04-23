package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzjt  reason: invalid package */
/* loaded from: classes.dex */
public final class zzjt {
    public static final zzjt zza = new zzjt("SHA1");
    public static final zzjt zzb = new zzjt("SHA224");
    public static final zzjt zzc = new zzjt("SHA256");
    public static final zzjt zzd = new zzjt("SHA384");
    public static final zzjt zze = new zzjt("SHA512");
    private final String zzf;

    private zzjt(String str) {
        this.zzf = str;
    }

    public final String toString() {
        return this.zzf;
    }
}
