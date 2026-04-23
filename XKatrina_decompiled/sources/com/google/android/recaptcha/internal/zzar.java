package com.google.android.recaptcha.internal;

import java.util.concurrent.TimeUnit;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzar {
    public static final zzar zza = new zzar();

    private zzar() {
    }

    public static final zzlg zza(zzn zznVar, zzn zznVar2) {
        zzlf zzf = zzlg.zzf();
        zzf.zzp(zzka.zzb(zznVar.zzb()));
        zzf.zzq(zzjy.zzb(zznVar.zza(TimeUnit.NANOSECONDS)));
        zzf.zzd(zzka.zzb(zznVar2.zzb()));
        zzf.zze(zzjy.zzb(zznVar2.zza(TimeUnit.NANOSECONDS)));
        return (zzlg) zzf.zzj();
    }
}
