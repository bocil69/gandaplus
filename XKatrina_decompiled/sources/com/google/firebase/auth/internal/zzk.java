package com.google.firebase.auth.internal;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.internal.p000firebaseauthapi.zzca;
import com.google.android.gms.internal.p000firebaseauthapi.zzla;
import com.google.android.gms.internal.p000firebaseauthapi.zzlf;
import com.google.android.gms.internal.p000firebaseauthapi.zzmh;
import com.google.android.gms.internal.p000firebaseauthapi.zzmj;
import com.google.android.gms.internal.p000firebaseauthapi.zzos;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzk {
    private static zzk zza;
    private final String zzb;
    private final zzmj zzc;

    private zzk(Context context, String str, boolean z) {
        zzmj zzmjVar;
        this.zzb = str;
        try {
            zzla.zza();
            zzmh zzmhVar = new zzmh();
            zzmhVar.zzf(context, "GenericIdpKeyset", String.format("com.google.firebase.auth.api.crypto.%s", str));
            zzmhVar.zzd(zzlf.zza);
            zzmhVar.zze(String.format("android-keystore://firebear_master_key_id.%s", str));
            zzmjVar = zzmhVar.zzg();
        } catch (IOException | GeneralSecurityException e) {
            Log.e("FirebearCryptoHelper", "Exception encountered during crypto setup:\n".concat(String.valueOf(e.getMessage())));
            zzmjVar = null;
        }
        this.zzc = zzmjVar;
    }

    public static zzk zza(Context context, String str) {
        zzk zzkVar = zza;
        if (zzkVar == null || !com.google.android.gms.internal.p000firebaseauthapi.zzq.zza(zzkVar.zzb, str)) {
            zza = new zzk(context, str, true);
        }
        return zza;
    }

    public final String zzb(String str) {
        String str2;
        zzmj zzmjVar = this.zzc;
        if (zzmjVar != null) {
            try {
                synchronized (zzmjVar) {
                    str2 = new String(((com.google.android.gms.internal.p000firebaseauthapi.zzbk) this.zzc.zza().zze(zzos.zza(), com.google.android.gms.internal.p000firebaseauthapi.zzbk.class)).zza(Base64.decode(str, 8), null), "UTF-8");
                }
                return str2;
            } catch (UnsupportedEncodingException | GeneralSecurityException e) {
                Log.e("FirebearCryptoHelper", "Exception encountered while decrypting bytes:\n".concat(String.valueOf(e.getMessage())));
                return null;
            }
        }
        Log.e("FirebearCryptoHelper", "KeysetManager failed to initialize - unable to decrypt payload");
        return null;
    }

    public final String zzc() {
        if (this.zzc == null) {
            Log.e("FirebearCryptoHelper", "KeysetManager failed to initialize - unable to get Public key");
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        zzca zza2 = com.google.android.gms.internal.p000firebaseauthapi.zzbf.zza(byteArrayOutputStream);
        try {
            synchronized (this.zzc) {
                this.zzc.zza().zzb().zzg(zza2);
            }
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 8);
        } catch (IOException | GeneralSecurityException e) {
            Log.e("FirebearCryptoHelper", "Exception encountered when attempting to get Public Key:\n".concat(String.valueOf(e.getMessage())));
            return null;
        }
    }
}
