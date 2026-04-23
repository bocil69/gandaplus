package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzhm  reason: invalid package */
/* loaded from: classes.dex */
public final class zzhm extends zzcv {
    private final zzhr zza;
    private final zzzq zzb;
    private final zzzo zzc;
    @Nullable
    private final Integer zzd;

    private zzhm(zzhr zzhrVar, zzzq zzzqVar, zzzo zzzoVar, @Nullable Integer num) {
        this.zza = zzhrVar;
        this.zzb = zzzqVar;
        this.zzc = zzzoVar;
        this.zzd = num;
    }

    public static zzhm zza(zzhq zzhqVar, zzzq zzzqVar, @Nullable Integer num) throws GeneralSecurityException {
        zzzo zzb;
        zzhq zzhqVar2 = zzhq.zzc;
        if (zzhqVar != zzhqVar2 && num == null) {
            String obj = zzhqVar.toString();
            throw new GeneralSecurityException("For given Variant " + obj + " the value of idRequirement must be non-null");
        } else if (zzhqVar != zzhqVar2 || num == null) {
            if (zzzqVar.zza() == 32) {
                zzhr zzd = zzhr.zzd(zzhqVar);
                if (zzd.zzb() == zzhqVar2) {
                    zzb = zzzo.zzb(new byte[0]);
                } else if (zzd.zzb() == zzhq.zzb) {
                    zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 0).putInt(num.intValue()).array());
                } else if (zzd.zzb() == zzhq.zza) {
                    zzb = zzzo.zzb(ByteBuffer.allocate(5).put((byte) 1).putInt(num.intValue()).array());
                } else {
                    throw new IllegalStateException("Unknown Variant: ".concat(zzd.zzb().toString()));
                }
                return new zzhm(zzd, zzzqVar, zzb, num);
            }
            int zza = zzzqVar.zza();
            throw new GeneralSecurityException("XChaCha20Poly1305 key must be constructed with key of length 32 bytes, not " + zza);
        } else {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        }
    }
}
