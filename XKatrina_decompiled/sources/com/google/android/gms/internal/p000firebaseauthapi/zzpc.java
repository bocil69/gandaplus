package com.google.android.gms.internal.p000firebaseauthapi;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzpc  reason: invalid package */
/* loaded from: classes.dex */
public final class zzpc extends RuntimeException {
    public zzpc(String str) {
        super(str);
    }

    public static Object zza(zzpb zzpbVar) {
        try {
            return zzpbVar.zza();
        } catch (Exception e) {
            throw new zzpc(e);
        }
    }

    public zzpc(String str, Throwable th) {
        super("Creating a protokey serialization failed", th);
    }

    public zzpc(Throwable th) {
        super(th);
    }
}
