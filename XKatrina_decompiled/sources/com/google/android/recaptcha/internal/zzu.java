package com.google.android.recaptcha.internal;

import android.content.Context;
import android.os.Build;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
/* compiled from: com.google.android.recaptcha:recaptcha@@18.1.2 */
/* loaded from: classes.dex */
public final class zzu {
    public static final zzu zza = new zzu();
    private static final String zzb = String.valueOf(Build.VERSION.SDK_INT);
    private static final GoogleApiAvailabilityLight zzc = GoogleApiAvailabilityLight.getInstance();

    private zzu() {
    }

    public static final String zza(Context context) {
        int isGooglePlayServicesAvailable = zzc.isGooglePlayServicesAvailable(context);
        return (isGooglePlayServicesAvailable == 1 || isGooglePlayServicesAvailable == 3 || isGooglePlayServicesAvailable == 9) ? "ANDROID_OFFPLAY" : "ANDROID_ONPLAY";
    }

    public static final String zzb() {
        return zzb;
    }
}
