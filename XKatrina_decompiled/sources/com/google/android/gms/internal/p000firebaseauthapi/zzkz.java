package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.EllipticCurve;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkz  reason: invalid package */
/* loaded from: classes.dex */
public final class zzkz extends zzli {
    private final zzkq zza;
    private final zzzo zzb;
    private final zzzo zzc;
    @Nullable
    private final Integer zzd;

    private zzkz(zzkq zzkqVar, zzzo zzzoVar, zzzo zzzoVar2, @Nullable Integer num) {
        this.zza = zzkqVar;
        this.zzb = zzzoVar;
        this.zzc = zzzoVar2;
        this.zzd = num;
    }

    public static zzkz zzb(zzkq zzkqVar, zzzo zzzoVar, @Nullable Integer num) throws GeneralSecurityException {
        EllipticCurve curve;
        zzzo zzb;
        zzko zzf = zzkqVar.zzf();
        if (zzf.equals(zzko.zzc) || num != null) {
            if (!zzf.equals(zzko.zzc) || num == null) {
                zzkn zze = zzkqVar.zze();
                int zza = zzzoVar.zza();
                String str = "Encoded public key byte length for " + zze.toString() + " must be %d, not " + zza;
                zzkn zzknVar = zzkn.zza;
                if (zze == zzknVar) {
                    if (zza != 65) {
                        throw new GeneralSecurityException(String.format(str, 65));
                    }
                } else if (zze == zzkn.zzb) {
                    if (zza != 97) {
                        throw new GeneralSecurityException(String.format(str, 97));
                    }
                } else if (zze == zzkn.zzc) {
                    if (zza != 133) {
                        throw new GeneralSecurityException(String.format(str, 133));
                    }
                } else if (zze != zzkn.zzf) {
                    throw new GeneralSecurityException("Unable to validate public key length for ".concat(zze.toString()));
                } else {
                    if (zza != 32) {
                        throw new GeneralSecurityException(String.format(str, 32));
                    }
                }
                if (zze == zzknVar || zze == zzkn.zzb || zze == zzkn.zzc) {
                    if (zze == zzknVar) {
                        curve = zzmq.zza.getCurve();
                    } else if (zze == zzkn.zzb) {
                        curve = zzmq.zzb.getCurve();
                    } else if (zze == zzkn.zzc) {
                        curve = zzmq.zzc.getCurve();
                    } else {
                        throw new IllegalArgumentException("Unable to determine NIST curve type for ".concat(zze.toString()));
                    }
                    zzmq.zzf(zzym.zzj(curve, 1, zzzoVar.zzc()), curve);
                }
                zzko zzf2 = zzkqVar.zzf();
                if (zzf2 == zzko.zzc) {
                    zzb = zzzo.zzb(new byte[0]);
                } else if (num != null) {
                    if (zzf2 == zzko.zzb) {
                        zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 0).putInt(num.intValue()).array());
                    } else if (zzf2 == zzko.zza) {
                        zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 1).putInt(num.intValue()).array());
                    } else {
                        throw new IllegalStateException("Unknown HpkeParameters.Variant: ".concat(zzf2.toString()));
                    }
                } else {
                    throw new IllegalStateException("idRequirement must be non-null for HpkeParameters.Variant ".concat(zzf2.toString()));
                }
                return new zzkz(zzkqVar, zzzoVar, zzb, num);
            }
            throw new GeneralSecurityException("'idRequirement' must be null for NO_PREFIX variant.");
        }
        throw new GeneralSecurityException("'idRequirement' must be non-null for " + zzf.toString() + " variant.");
    }

    public final zzkq zza() {
        return this.zza;
    }

    public final zzzo zzc() {
        return this.zzb;
    }
}
