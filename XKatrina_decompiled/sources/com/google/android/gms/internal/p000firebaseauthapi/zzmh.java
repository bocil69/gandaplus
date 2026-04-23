package com.google.android.gms.internal.p000firebaseauthapi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import java.io.CharConversionException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.ProviderException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmh */
/* loaded from: classes.dex */
public final class zzmh {
    private Context zza = null;
    private String zzb = null;
    private String zzc = null;
    private String zzd = null;
    private zzbd zze = null;
    private zzbv zzf = null;
    private zzwn zzg = null;
    private zzbz zzh;

    private final zzbd zzj() throws GeneralSecurityException {
        String str;
        String str2;
        String str3;
        if (!zzmj.zzd()) {
            str3 = zzmj.zzb;
            Log.w(str3, "Android Keystore requires at least Android M");
            return null;
        }
        zzml zzmlVar = new zzml();
        try {
            boolean zzc = zzml.zzc(this.zzd);
            try {
                return zzmlVar.zza(this.zzd);
            } catch (GeneralSecurityException | ProviderException e) {
                if (!zzc) {
                    throw new KeyStoreException(String.format("the master key %s exists but is unusable", this.zzd), e);
                }
                str2 = zzmj.zzb;
                Log.w(str2, "cannot use Android Keystore, it'll be disabled", e);
                return null;
            }
        } catch (GeneralSecurityException | ProviderException e2) {
            str = zzmj.zzb;
            Log.w(str, "cannot use Android Keystore, it'll be disabled", e2);
            return null;
        }
    }

    private final zzbz zzk(byte[] bArr) throws GeneralSecurityException, IOException {
        String str;
        try {
            this.zze = new zzml().zza(this.zzd);
            try {
                return zzbz.zzf(zzby.zzh(zzbe.zzc(bArr), this.zze));
            } catch (IOException | GeneralSecurityException e) {
                try {
                    return zzl(bArr);
                } catch (IOException unused) {
                    throw e;
                }
            }
        } catch (GeneralSecurityException | ProviderException e2) {
            try {
                zzbz zzl = zzl(bArr);
                str = zzmj.zzb;
                Log.w(str, "cannot use Android Keystore, it'll be disabled", e2);
                return zzl;
            } catch (IOException unused2) {
                throw e2;
            }
        }
    }

    private static final zzbz zzl(byte[] bArr) throws GeneralSecurityException, IOException {
        return zzbz.zzf(zzbg.zzb(zzbe.zzc(bArr)));
    }

    public final zzmh zzd(zzwn zzwnVar) {
        this.zzg = zzwnVar;
        return this;
    }

    public final zzmh zze(String str) {
        if (str.startsWith("android-keystore://")) {
            this.zzd = str;
            return this;
        }
        throw new IllegalArgumentException("key URI must start with android-keystore://");
    }

    public final zzmh zzf(Context context, String str, String str2) throws IOException {
        if (context != null) {
            this.zza = context;
            this.zzb = "GenericIdpKeyset";
            this.zzc = str2;
            return this;
        }
        throw new IllegalArgumentException("need an Android context");
    }

    public final synchronized zzmj zzg() throws GeneralSecurityException, IOException {
        Object obj;
        SharedPreferences sharedPreferences;
        byte[] bArr;
        zzmj zzmjVar;
        if (this.zzb == null) {
            throw new IllegalArgumentException("keysetName cannot be null");
        }
        zzwn zzwnVar = this.zzg;
        if (zzwnVar != null && this.zzf == null) {
            this.zzf = zzbv.zza(zzcs.zza(zzwnVar.zzq()));
        }
        obj = zzmj.zza;
        synchronized (obj) {
            Context context = this.zza;
            String str = this.zzb;
            String str2 = this.zzc;
            if (str == null) {
                throw new IllegalArgumentException("keysetName cannot be null");
            }
            Context applicationContext = context.getApplicationContext();
            if (str2 == null) {
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
            } else {
                sharedPreferences = applicationContext.getSharedPreferences(str2, 0);
            }
            try {
                String string = sharedPreferences.getString(str, null);
                if (string == null) {
                    bArr = null;
                } else if (string.length() % 2 == 0) {
                    int length = string.length() / 2;
                    bArr = new byte[length];
                    for (int i = 0; i < length; i++) {
                        int i2 = i + i;
                        int digit = Character.digit(string.charAt(i2), 16);
                        int digit2 = Character.digit(string.charAt(i2 + 1), 16);
                        if (digit != -1 && digit2 != -1) {
                            bArr[i] = (byte) ((digit * 16) + digit2);
                        } else {
                            throw new IllegalArgumentException("input is not hexadecimal");
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Expected a string of even length");
                }
                if (bArr == null) {
                    if (this.zzd != null) {
                        this.zze = zzj();
                    }
                    if (this.zzf == null) {
                        throw new GeneralSecurityException("cannot read or generate keyset");
                    }
                    zzbz zze = zzbz.zze();
                    zze.zzc(this.zzf);
                    zze.zzd(zze.zzb().zzd().zzb(0).zza());
                    zzmm zzmmVar = new zzmm(this.zza, this.zzb, this.zzc);
                    zzby zzb = zze.zzb();
                    zzbd zzbdVar = this.zze;
                    try {
                        if (zzbdVar != null) {
                            zzb.zzf(zzmmVar, zzbdVar);
                        } else {
                            zzbg.zza(zzb, zzmmVar);
                        }
                        this.zzh = zze;
                    } catch (IOException e) {
                        throw new GeneralSecurityException(e);
                    }
                } else {
                    if (this.zzd != null && zzmj.zzd()) {
                        this.zzh = zzk(bArr);
                    }
                    this.zzh = zzl(bArr);
                }
                zzmjVar = new zzmj(this, null);
            } catch (ClassCastException | IllegalArgumentException unused) {
                throw new CharConversionException(String.format("can't read keyset; the pref value %s is not a valid hex string", str));
            }
        }
        return zzmjVar;
    }
}
