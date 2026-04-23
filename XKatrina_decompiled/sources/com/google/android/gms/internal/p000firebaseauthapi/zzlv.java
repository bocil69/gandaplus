package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlv  reason: invalid package */
/* loaded from: classes.dex */
public final class zzlv {
    public static zzlo zza(zzvx zzvxVar) throws GeneralSecurityException {
        if (zzvxVar.zza() == zzvn.AES_128_GCM) {
            return new zzll(16);
        }
        if (zzvxVar.zza() == zzvn.AES_256_GCM) {
            return new zzll(32);
        }
        if (zzvxVar.zza() == zzvn.CHACHA20_POLY1305) {
            return new zzlm();
        }
        throw new IllegalArgumentException("Unrecognized HPKE AEAD identifier");
    }

    public static zzls zzb(zzvx zzvxVar) {
        if (zzvxVar.zzb() == zzvp.HKDF_SHA256) {
            return new zzln("HmacSha256");
        }
        if (zzvxVar.zzb() == zzvp.HKDF_SHA384) {
            return new zzln("HmacSha384");
        }
        if (zzvxVar.zzb() == zzvp.HKDF_SHA512) {
            return new zzln("HmacSha512");
        }
        throw new IllegalArgumentException("Unrecognized HPKE KDF identifier");
    }

    public static zzlt zzc(zzvx zzvxVar) throws GeneralSecurityException {
        if (zzvxVar.zzc() == zzvr.DHKEM_X25519_HKDF_SHA256) {
            return new zzme(new zzln("HmacSha256"));
        }
        if (zzvxVar.zzc() == zzvr.DHKEM_P256_HKDF_SHA256) {
            return zzmc.zzc(1);
        }
        if (zzvxVar.zzc() == zzvr.DHKEM_P384_HKDF_SHA384) {
            return zzmc.zzc(2);
        }
        if (zzvxVar.zzc() == zzvr.DHKEM_P521_HKDF_SHA512) {
            return zzmc.zzc(3);
        }
        throw new IllegalArgumentException("Unrecognized HPKE KEM identifier");
    }
}
