package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlj  reason: invalid package */
/* loaded from: classes.dex */
final class zzlj {
    public static String zza(zzvc zzvcVar) throws NoSuchAlgorithmException {
        zzud zzudVar = zzud.UNKNOWN_FORMAT;
        zzux zzuxVar = zzux.UNKNOWN_CURVE;
        zzvc zzvcVar2 = zzvc.UNKNOWN_HASH;
        int ordinal = zzvcVar.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal != 3) {
                    if (ordinal != 4) {
                        if (ordinal == 5) {
                            return "HmacSha224";
                        }
                        throw new NoSuchAlgorithmException("hash unsupported for HMAC: ".concat(String.valueOf(String.valueOf(zzvcVar))));
                    }
                    return "HmacSha512";
                }
                return "HmacSha256";
            }
            return "HmacSha384";
        }
        return "HmacSha1";
    }

    public static void zzb(zzum zzumVar) throws GeneralSecurityException {
        zzym.zzi(zzc(zzumVar.zzf().zzd()));
        zza(zzumVar.zzf().zze());
        if (zzumVar.zza() == zzud.UNKNOWN_FORMAT) {
            throw new GeneralSecurityException("unknown EC point format");
        }
        zzcq.zzb(zzumVar.zzb().zzd());
    }

    public static int zzc(zzux zzuxVar) throws GeneralSecurityException {
        zzud zzudVar = zzud.UNKNOWN_FORMAT;
        zzux zzuxVar2 = zzux.UNKNOWN_CURVE;
        zzvc zzvcVar = zzvc.UNKNOWN_HASH;
        int ordinal = zzuxVar.ordinal();
        int i = 1;
        if (ordinal != 1) {
            i = 2;
            if (ordinal != 2) {
                if (ordinal == 3) {
                    return 3;
                }
                throw new GeneralSecurityException("unknown curve type: ".concat(String.valueOf(String.valueOf(zzuxVar))));
            }
        }
        return i;
    }

    public static int zzd(zzud zzudVar) throws GeneralSecurityException {
        zzud zzudVar2 = zzud.UNKNOWN_FORMAT;
        zzux zzuxVar = zzux.UNKNOWN_CURVE;
        zzvc zzvcVar = zzvc.UNKNOWN_HASH;
        int ordinal = zzudVar.ordinal();
        int i = 1;
        if (ordinal != 1) {
            i = 2;
            if (ordinal != 2) {
                if (ordinal == 3) {
                    return 3;
                }
                throw new GeneralSecurityException("unknown point format: ".concat(String.valueOf(String.valueOf(zzudVar))));
            }
        }
        return i;
    }
}
