package com.google.android.recaptcha.internal;

import java.util.concurrent.TimeUnit;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzn {
    public static final zzm zza = new zzm(null);
    private final long zzb = System.currentTimeMillis();
    private final zzdk zzc = zzdk.zzb();

    public final long zza(TimeUnit timeUnit) {
        return this.zzc.zza(timeUnit);
    }

    public final long zzb() {
        return this.zzb;
    }
}
