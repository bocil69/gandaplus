package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzkg  reason: invalid package */
/* loaded from: classes.dex */
public final class zzkg extends zzli {
    private final zzjx zza;
    @Nullable
    private final ECPoint zzb;
    @Nullable
    private final zzzo zzc;
    private final zzzo zzd;
    @Nullable
    private final Integer zze;

    private zzkg(zzjx zzjxVar, @Nullable ECPoint eCPoint, @Nullable zzzo zzzoVar, zzzo zzzoVar2, @Nullable Integer num) {
        this.zza = zzjxVar;
        this.zzb = eCPoint;
        this.zzc = zzzoVar;
        this.zzd = zzzoVar2;
        this.zze = num;
    }

    public static zzkg zzb(zzjx zzjxVar, zzzo zzzoVar, @Nullable Integer num) throws GeneralSecurityException {
        if (!zzjxVar.zzc().equals(zzjs.zzd)) {
            throw new GeneralSecurityException("createForCurveX25519 may only be called with parameters for curve X25519");
        }
        zzg(zzjxVar.zzf(), num);
        if (zzzoVar.zza() != 32) {
            throw new GeneralSecurityException("Encoded public point byte length for X25519 curve must be 32");
        }
        return new zzkg(zzjxVar, null, zzzoVar, zzf(zzjxVar.zzf(), num), num);
    }

    public static zzkg zzc(zzjx zzjxVar, ECPoint eCPoint, @Nullable Integer num) throws GeneralSecurityException {
        EllipticCurve curve;
        if (zzjxVar.zzc().equals(zzjs.zzd)) {
            throw new GeneralSecurityException("createForNistCurve may only be called with parameters for NIST curve");
        }
        zzg(zzjxVar.zzf(), num);
        zzjs zzc = zzjxVar.zzc();
        if (zzc == zzjs.zza) {
            curve = zzmq.zza.getCurve();
        } else if (zzc == zzjs.zzb) {
            curve = zzmq.zzb.getCurve();
        } else if (zzc != zzjs.zzc) {
            throw new IllegalArgumentException("Unable to determine NIST curve type for ".concat(String.valueOf(String.valueOf(zzc))));
        } else {
            curve = zzmq.zzc.getCurve();
        }
        zzmq.zzf(eCPoint, curve);
        return new zzkg(zzjxVar, eCPoint, null, zzf(zzjxVar.zzf(), num), num);
    }

    private static zzzo zzf(zzjv zzjvVar, @Nullable Integer num) {
        if (zzjvVar == zzjv.zzc) {
            return zzzo.zzb(new byte[0]);
        }
        if (num == null) {
            throw new IllegalStateException("idRequirement must be non-null for EciesParameters.Variant: ".concat(String.valueOf(String.valueOf(zzjvVar))));
        }
        if (zzjvVar == zzjv.zzb) {
            return zzzo.zzb(ByteBuffer.allocate(5).put((byte) 0).putInt(num.intValue()).array());
        }
        if (zzjvVar != zzjv.zza) {
            throw new IllegalStateException("Unknown EciesParameters.Variant: ".concat(String.valueOf(String.valueOf(zzjvVar))));
        }
        return zzzo.zzb(ByteBuffer.allocate(5).put((byte) 1).putInt(num.intValue()).array());
    }

    private static void zzg(zzjv zzjvVar, @Nullable Integer num) throws GeneralSecurityException {
        if (zzjvVar.equals(zzjv.zzc) || num != null) {
            if (zzjvVar.equals(zzjv.zzc) && num != null) {
                throw new GeneralSecurityException("'idRequirement' must be null for NO_PREFIX variant.");
            }
            return;
        }
        String valueOf = String.valueOf(zzjvVar);
        throw new GeneralSecurityException("'idRequirement' must be non-null for " + valueOf + " variant.");
    }

    public final zzjx zza() {
        return this.zza;
    }

    @Nullable
    public final zzzo zzd() {
        return this.zzc;
    }

    @Nullable
    public final ECPoint zze() {
        return this.zzb;
    }
}
