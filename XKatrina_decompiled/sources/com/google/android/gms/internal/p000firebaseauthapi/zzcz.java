package com.google.android.gms.internal.p000firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcz  reason: invalid package */
/* loaded from: classes.dex */
final class zzcz implements zzbd {
    private final zzcl zza;
    private final zzrp zzb;
    private final zzrp zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcz(zzcl zzclVar, zzcy zzcyVar) {
        zzrp zzrpVar;
        this.zza = zzclVar;
        if (zzclVar.zzf()) {
            zzrq zzb = zznp.zza().zzb();
            zzrv zza = zznm.zza(zzclVar);
            this.zzb = zzb.zza(zza, "aead", "encrypt");
            zzrpVar = zzb.zza(zza, "aead", "decrypt");
        } else {
            zzrpVar = zznm.zza;
            this.zzb = zzrpVar;
        }
        this.zzc = zzrpVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbd
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        if (length > 5) {
            byte[] copyOf = Arrays.copyOf(bArr, 5);
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, length);
            for (zzch zzchVar : this.zza.zze(copyOf)) {
                try {
                    byte[] zza = ((zzbd) zzchVar.zze()).zza(copyOfRange, bArr2);
                    zzchVar.zza();
                    int length2 = copyOfRange.length;
                    return zza;
                } catch (GeneralSecurityException unused) {
                }
            }
        }
        for (zzch zzchVar2 : this.zza.zze(zzbi.zza)) {
            try {
                byte[] zza2 = ((zzbd) zzchVar2.zze()).zza(bArr, bArr2);
                zzchVar2.zza();
                int length3 = bArr.length;
                return zza2;
            } catch (GeneralSecurityException unused2) {
            }
        }
        throw new GeneralSecurityException("decryption failed");
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbd
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        throw null;
    }
}
