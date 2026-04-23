package com.google.android.gms.internal.p000firebaseauthapi;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import com.google.android.gms.stats.CodePackage;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.KeyGenerator;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzml  reason: invalid package */
/* loaded from: classes.dex */
public final class zzml implements zzcb {
    private static final Object zza = new Object();
    private static final String zzb = "zzml";
    private KeyStore zzc;

    public zzml() throws GeneralSecurityException {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                keyStore.load(null);
                this.zzc = keyStore;
                return;
            } catch (IOException | GeneralSecurityException e) {
                throw new IllegalStateException(e);
            }
        }
        throw new IllegalStateException("need Android Keystore on Android M or newer");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzc(String str) throws GeneralSecurityException {
        zzml zzmlVar = new zzml();
        synchronized (zza) {
            if (zzmlVar.zzd(str)) {
                return false;
            }
            String zza2 = zzzl.zza("android-keystore://", str);
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
            keyGenerator.init(new KeyGenParameterSpec.Builder(zza2, 3).setKeySize(256).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").build());
            keyGenerator.generateKey();
            return true;
        }
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzcb
    public final synchronized zzbd zza(String str) throws GeneralSecurityException {
        zzmk zzmkVar;
        zzmkVar = new zzmk(zzzl.zza("android-keystore://", str), this.zzc);
        byte[] zzb2 = zzor.zzb(10);
        byte[] bArr = new byte[0];
        if (!Arrays.equals(zzb2, zzmkVar.zza(zzmkVar.zzb(zzb2, bArr), bArr))) {
            throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
        }
        return zzmkVar;
    }

    @Override // com.google.android.gms.internal.p000firebaseauthapi.zzcb
    public final synchronized boolean zzb(String str) {
        return str.toLowerCase(Locale.US).startsWith("android-keystore://");
    }

    final synchronized boolean zzd(String str) throws GeneralSecurityException {
        String str2;
        try {
        } catch (NullPointerException unused) {
            Log.w(zzb, "Keystore is temporarily unavailable, wait, reinitialize Keystore and try again.");
            try {
                try {
                    Thread.sleep((int) (Math.random() * 40.0d));
                } catch (InterruptedException unused2) {
                }
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                this.zzc = keyStore;
                keyStore.load(null);
                return this.zzc.containsAlias(str2);
            } catch (IOException e) {
                throw new GeneralSecurityException(e);
            }
        }
        return this.zzc.containsAlias(zzzl.zza("android-keystore://", str));
    }
}
