package com.google.android.gms.internal.p000firebaseauthapi;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzgj  reason: invalid package */
/* loaded from: classes.dex */
public final class zzgj implements zzbd {
    private static final byte[] zza = new byte[0];
    private static final Set zzb;
    private final zzwn zzc;
    private final zzbd zzd;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("type.googleapis.com/google.crypto.tink.AesGcmKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
        hashSet.add("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesGcmSivKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesEaxKey");
        zzb = Collections.unmodifiableSet(hashSet);
    }

    public zzgj(zzwn zzwnVar, zzbd zzbdVar) {
        if (zzc(zzwnVar.zzg())) {
            this.zzc = zzwnVar;
            this.zzd = zzbdVar;
            return;
        }
        String zzg = zzwnVar.zzg();
        throw new IllegalArgumentException("Unsupported DEK key type: " + zzg + ". Only Tink AEAD key types are supported.");
    }

    public static boolean zzc(String str) {
        return zzb.contains(str);
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbd
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            int i = wrap.getInt();
            if (i <= 0 || i > bArr.length - 4) {
                throw new GeneralSecurityException("invalid ciphertext");
            }
            byte[] bArr3 = new byte[i];
            wrap.get(bArr3, 0, i);
            byte[] bArr4 = new byte[wrap.remaining()];
            wrap.get(bArr4, 0, wrap.remaining());
            byte[] zza2 = this.zzd.zza(bArr3, zza);
            String zzg = this.zzc.zzg();
            int i2 = zzcq.zza;
            zzajf zzajfVar = zzajf.zzb;
            return ((zzbd) zzcq.zze(zzg, zzajf.zzn(zza2, 0, zza2.length), zzbd.class)).zza(bArr4, bArr2);
        } catch (IndexOutOfBoundsException | NegativeArraySizeException | BufferUnderflowException e) {
            throw new GeneralSecurityException("invalid ciphertext", e);
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzbd
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        throw null;
    }
}
