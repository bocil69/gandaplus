package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfv  reason: invalid package */
/* loaded from: classes.dex */
public final class zzfv extends zzcv {
    private final zzga zza;
    private final zzzq zzb;
    private final zzzo zzc;
    @Nullable
    private final Integer zzd;

    private zzfv(zzga zzgaVar, zzzq zzzqVar, zzzo zzzoVar, @Nullable Integer num) {
        this.zza = zzgaVar;
        this.zzb = zzzqVar;
        this.zzc = zzzoVar;
        this.zzd = num;
    }

    public static zzfv zza(zzfz zzfzVar, zzzq zzzqVar, @Nullable Integer num) throws GeneralSecurityException {
        zzzo zzb;
        zzfz zzfzVar2 = zzfz.zzc;
        if (zzfzVar != zzfzVar2 && num == null) {
            String obj = zzfzVar.toString();
            throw new GeneralSecurityException("For given Variant " + obj + " the value of idRequirement must be non-null");
        } else if (zzfzVar != zzfzVar2 || num == null) {
            if (zzzqVar.zza() == 32) {
                zzga zzc = zzga.zzc(zzfzVar);
                if (zzc.zzb() == zzfzVar2) {
                    zzb = zzzo.zzb(new byte[0]);
                } else if (zzc.zzb() == zzfz.zzb) {
                    zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 0).putInt(num.intValue()).array());
                } else if (zzc.zzb() == zzfz.zza) {
                    zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 1).putInt(num.intValue()).array());
                } else {
                    throw new IllegalStateException("Unknown Variant: ".concat(zzc.zzb().toString()));
                }
                return new zzfv(zzc, zzzqVar, zzb, num);
            }
            int zza = zzzqVar.zza();
            throw new GeneralSecurityException("ChaCha20Poly1305 key must be constructed with key of length 32 bytes, not " + zza);
        } else {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        }
    }
}
