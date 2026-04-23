package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlr  reason: invalid package */
/* loaded from: classes.dex */
final class zzlr implements zzbl {
    private final zzwd zza;
    private final zzlt zzb;
    private final zzls zzc;
    private final zzlo zzd;

    private zzlr(zzwd zzwdVar, zzlt zzltVar, zzls zzlsVar, zzlo zzloVar) {
        this.zza = zzwdVar;
        this.zzb = zzltVar;
        this.zzc = zzlsVar;
        this.zzd = zzloVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzlr zza(zzwd zzwdVar) throws GeneralSecurityException {
        if (zzwdVar.zzg().zzp()) {
            throw new IllegalArgumentException("HpkePublicKey.public_key is empty.");
        }
        zzvx zzb = zzwdVar.zzb();
        return new zzlr(zzwdVar, zzlv.zzc(zzb), zzlv.zzb(zzb), zzlv.zza(zzb));
    }
}
