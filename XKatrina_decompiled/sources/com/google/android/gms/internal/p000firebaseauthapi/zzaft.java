package com.google.android.gms.internal.p000firebaseauthapi;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Base64;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.auth.PhoneAuthCredential;
import com.topjohnwu.superuser.nio.FileSystemManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaft  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaft {
    private static final Logger zza = new Logger("FirebaseAuth", "SmsRetrieverHelper");
    private final Context zzb;
    private final ScheduledExecutorService zzc;
    private final HashMap zzd = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaft(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzb = context;
        this.zzc = scheduledExecutorService;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzd(zzaft zzaftVar, String str) {
        zzafs zzafsVar = (zzafs) zzaftVar.zzd.get(str);
        if (zzafsVar == null || zzac.zzd(zzafsVar.zzd) || zzac.zzd(zzafsVar.zze) || zzafsVar.zzb.isEmpty()) {
            return;
        }
        for (zzadx zzadxVar : zzafsVar.zzb) {
            zzadxVar.zzr(PhoneAuthCredential.zzc(zzafsVar.zzd, zzafsVar.zze));
        }
        zzafsVar.zzh = true;
    }

    private static String zzl(String str, String str2) {
        String str3 = str + " " + str2;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str3.getBytes(zzk.zzc));
            String substring = Base64.encodeToString(Arrays.copyOf(messageDigest.digest(), 9), 3).substring(0, 11);
            zza.d("Package: " + str + " -- Hash: " + substring, new Object[0]);
            return substring;
        } catch (NoSuchAlgorithmException e) {
            zza.e("NoSuchAlgorithm: ".concat(String.valueOf(e.getMessage())), new Object[0]);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzm(String str) {
        zzafs zzafsVar = (zzafs) this.zzd.get(str);
        if (zzafsVar == null || zzafsVar.zzh || zzac.zzd(zzafsVar.zzd)) {
            return;
        }
        zza.w("Timed out waiting for SMS.", new Object[0]);
        for (zzadx zzadxVar : zzafsVar.zzb) {
            zzadxVar.zza(zzafsVar.zzd);
        }
        zzafsVar.zzi = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: zzn */
    public final void zzg(String str) {
        zzafs zzafsVar = (zzafs) this.zzd.get(str);
        if (zzafsVar == null) {
            return;
        }
        if (!zzafsVar.zzi) {
            zzm(str);
        }
        zzi(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzb() {
        Signature[] apkContentsSigners;
        try {
            String packageName = this.zzb.getPackageName();
            if (Build.VERSION.SDK_INT < 28) {
                apkContentsSigners = Wrappers.packageManager(this.zzb).getPackageInfo(packageName, 64).signatures;
            } else {
                apkContentsSigners = Wrappers.packageManager(this.zzb).getPackageInfo(packageName, FileSystemManager.MODE_CREATE).signingInfo.getApkContentsSigners();
            }
            String zzl = zzl(packageName, apkContentsSigners[0].toCharsString());
            if (zzl != null) {
                return zzl;
            }
            zza.e("Hash generation failed.", new Object[0]);
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            zza.e("Unable to find package to obtain hash.", new Object[0]);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzh(zzadx zzadxVar, String str) {
        zzafs zzafsVar = (zzafs) this.zzd.get(str);
        if (zzafsVar == null) {
            return;
        }
        zzafsVar.zzb.add(zzadxVar);
        if (zzafsVar.zzg) {
            zzadxVar.zzb(zzafsVar.zzd);
        }
        if (zzafsVar.zzh) {
            zzadxVar.zzr(PhoneAuthCredential.zzc(zzafsVar.zzd, zzafsVar.zze));
        }
        if (zzafsVar.zzi) {
            zzadxVar.zza(zzafsVar.zzd);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzi(String str) {
        zzafs zzafsVar = (zzafs) this.zzd.get(str);
        if (zzafsVar == null) {
            return;
        }
        ScheduledFuture scheduledFuture = zzafsVar.zzf;
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            zzafsVar.zzf.cancel(false);
        }
        zzafsVar.zzb.clear();
        this.zzd.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzj(final String str, zzadx zzadxVar, long j, boolean z) {
        this.zzd.put(str, new zzafs(j, z));
        zzh(zzadxVar, str);
        zzafs zzafsVar = (zzafs) this.zzd.get(str);
        long j2 = zzafsVar.zza;
        if (j2 <= 0) {
            zza.w("Timeout of 0 specified; SmsRetriever will not start.", new Object[0]);
            return;
        }
        zzafsVar.zzf = this.zzc.schedule(new Runnable() { // from class: com.google.android.gms.internal.firebase-auth-api.zzafo
            @Override // java.lang.Runnable
            public final void run() {
                zzaft.this.zzg(str);
            }
        }, j2, TimeUnit.SECONDS);
        if (!zzafsVar.zzc) {
            zza.w("SMS auto-retrieval unavailable; SmsRetriever will not start.", new Object[0]);
            return;
        }
        zzafr zzafrVar = new zzafr(this, str);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.gms.auth.api.phone.SMS_RETRIEVED");
        zzb.zza(this.zzb.getApplicationContext(), zzafrVar, intentFilter);
        SmsRetriever.getClient(this.zzb).startSmsRetriever().addOnFailureListener(new zzafp(this));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzk(String str) {
        return this.zzd.get(str) != null;
    }
}
