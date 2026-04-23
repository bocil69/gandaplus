package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzop  reason: invalid package */
/* loaded from: classes.dex */
public final class zzop implements zzot {
    private final zzzo zza;
    private final zzwn zzb;

    private zzop(zzwn zzwnVar, zzzo zzzoVar) {
        this.zzb = zzwnVar;
        this.zza = zzzoVar;
    }

    public static zzop zza(zzwn zzwnVar) throws GeneralSecurityException {
        String zzg = zzwnVar.zzg();
        Charset charset = zzpd.zza;
        byte[] bArr = new byte[zzg.length()];
        for (int i = 0; i < zzg.length(); i++) {
            char charAt = zzg.charAt(i);
            if (charAt < '!' || charAt > '~') {
                throw new GeneralSecurityException("Not a printable ASCII character: " + charAt);
            }
            bArr[i] = (byte) charAt;
        }
        return new zzop(zzwnVar, zzzo.zzb(bArr));
    }

    public static zzop zzb(zzwn zzwnVar) {
        return new zzop(zzwnVar, zzpd.zzb(zzwnVar.zzg()));
    }

    public final zzwn zzc() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzot
    public final zzzo zzd() {
        return this.zza;
    }
}
