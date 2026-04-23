package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzlb  reason: invalid package */
/* loaded from: classes.dex */
final class zzlb implements zzbk {
    private final zzcl zza;
    private final zzrp zzb;

    public zzlb(zzcl zzclVar) {
        this.zza = zzclVar;
        this.zzb = zzclVar.zzf() ? zznp.zza().zzb().zza(zznm.zza(zzclVar), "hybrid_decrypt", "decrypt") : zznm.zza;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbk
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        if (length > 5) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 5);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 5, length);
            for (zzch zzchVar : this.zza.zze(copyOfRange)) {
                try {
                    byte[] zza = ((zzbk) zzchVar.zze()).zza(copyOfRange2, null);
                    zzchVar.zza();
                    int length2 = copyOfRange2.length;
                    return zza;
                } catch (GeneralSecurityException unused) {
                }
            }
        }
        for (zzch zzchVar2 : this.zza.zze(zzbi.zza)) {
            try {
                byte[] zza2 = ((zzbk) zzchVar2.zze()).zza(bArr, null);
                zzchVar2.zza();
                int length3 = bArr.length;
                return zza2;
            } catch (GeneralSecurityException unused2) {
            }
        }
        throw new GeneralSecurityException("decryption failed");
    }
}
