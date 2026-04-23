package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzrl  reason: invalid package */
/* loaded from: classes.dex */
public final class zzrl implements zzcd {
    private static final byte[] zza = {0};
    private final zzcd zzb;
    private final zzxo zzc;
    private final byte[] zzd;

    private zzrl(zzcd zzcdVar, zzxo zzxoVar, byte[] bArr) {
        this.zzb = zzcdVar;
        this.zzc = zzxoVar;
        this.zzd = bArr;
    }

    public static zzcd zzb(zzni zzniVar) throws GeneralSecurityException {
        byte[] array;
        zzoo zza2 = zzniVar.zza(zzbm.zza());
        zzwf zza3 = zzwi.zza();
        zza3.zzb(zza2.zzg());
        zza3.zzc(zza2.zze());
        zza3.zza(zza2.zzb());
        zzcd zzcdVar = (zzcd) zzcq.zzd((zzwi) zza3.zzi(), zzcd.class);
        zzxo zzc = zza2.zzc();
        zzxo zzxoVar = zzxo.UNKNOWN_PREFIX;
        int ordinal = zzc.ordinal();
        if (ordinal == 1) {
            array = ByteBuffer.allocate(5).put((byte) 1).putInt(zzniVar.zzb().intValue()).array();
        } else {
            if (ordinal != 2) {
                if (ordinal == 3) {
                    array = new byte[0];
                } else if (ordinal != 4) {
                    throw new GeneralSecurityException("unknown output prefix type");
                }
            }
            array = ByteBuffer.allocate(5).put((byte) 0).putInt(zzniVar.zzb().intValue()).array();
        }
        return new zzrl(zzcdVar, zzc, array);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzcd
    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        if (length < 10) {
            throw new GeneralSecurityException("tag too short");
        }
        if (this.zzc.equals(zzxo.LEGACY)) {
            bArr2 = zzyf.zzb(bArr2, zza);
        }
        byte[] bArr3 = new byte[0];
        if (!this.zzc.equals(zzxo.RAW)) {
            byte[] copyOf = Arrays.copyOf(bArr, 5);
            bArr = Arrays.copyOfRange(bArr, 5, length);
            bArr3 = copyOf;
        }
        if (!Arrays.equals(this.zzd, bArr3)) {
            throw new GeneralSecurityException("wrong prefix");
        }
        this.zzb.zza(bArr, bArr2);
    }
}
