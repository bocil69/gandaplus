package com.google.android.recaptcha.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.os.EnvironmentCompat;
import java.util.Locale;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzah {
    public static final zzag zza = new zzag(null);
    private static zzkj zzb;
    private final String zzc;
    private final zzs zzd;
    private final zzku zze;
    private final long zzf;

    public zzah(zzaf zzafVar, String str, zzs zzsVar) {
        this.zzc = str;
        this.zzd = zzsVar;
        zzku zzi = zzkx.zzi();
        this.zze = zzi;
        this.zzf = System.currentTimeMillis();
        zzi.zzq(zzafVar.zza());
        zzi.zze(zzafVar.zzb());
        zzi.zzs(zzafVar.zzc());
        zzi.zzv(zzafVar.zzd());
        zzi.zzu(zzka.zzc(zzka.zzb(System.currentTimeMillis())));
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0047 -> B:15:0x0047). Please submit an issue!!! */
    private static final zzkj zzd(Context context) {
        String str;
        String str2 = EnvironmentCompat.MEDIA_UNKNOWN;
        if (Build.VERSION.SDK_INT >= 33) {
            int i = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.ApplicationInfoFlags.of(128L)).metaData.getInt("com.google.android.gms.version", -1);
            str = i == -1 ? EnvironmentCompat.MEDIA_UNKNOWN : String.valueOf(i);
        } else {
            int i2 = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getInt("com.google.android.gms.version", -1);
            if (i2 != -1) {
                str = String.valueOf(i2);
            }
        }
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                str2 = String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L)).getLongVersionCode());
            } else if (Build.VERSION.SDK_INT >= 28) {
                str2 = String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).getLongVersionCode());
            } else {
                str2 = String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        zzki zzf = zzkj.zzf();
        zzf.zzd(Build.VERSION.SDK_INT);
        zzf.zzq(str);
        zzf.zzs("18.1.2");
        zzf.zzp(Build.MODEL);
        zzf.zzr(Build.MANUFACTURER);
        zzf.zze(str2);
        return (zzkj) zzf.zzj();
    }

    public final long zza() {
        return this.zzf;
    }

    public final zzku zzb() {
        return this.zze;
    }

    public final zzkx zzc(int i, zzkm zzkmVar, Context context) {
        zzku zzkuVar = this.zze;
        zzkuVar.zzp(System.currentTimeMillis() - this.zzf);
        zzkuVar.zzw(i);
        if (zzkmVar != null) {
            this.zze.zzr(zzkmVar);
        }
        if (zzb == null) {
            zzb = zzd(context);
        }
        zzku zzkuVar2 = this.zze;
        zzli zzf = zzlj.zzf();
        zzf.zzq(this.zzc);
        zzkj zzkjVar = zzb;
        if (zzkjVar == null) {
            zzkjVar = zzd(context);
        }
        zzf.zzd(zzkjVar);
        zzf.zzp(Locale.getDefault().getISO3Language());
        zzf.zze(Locale.getDefault().getISO3Country());
        zzkuVar2.zzt((zzlj) zzf.zzj());
        return (zzkx) this.zze.zzj();
    }
}
