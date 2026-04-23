package com.google.android.gms.internal.p000firebaseauthapi;

import android.app.Activity;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.util.DefaultClock;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.Map;
import java.util.concurrent.Executor;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafn  reason: invalid package */
/* loaded from: classes.dex */
public final class zzafn {
    private static final Map zza = new ArrayMap();

    public static PhoneAuthProvider.OnVerificationStateChangedCallbacks zza(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, zzaez zzaezVar) {
        zze(str, zzaezVar);
        return new zzafl(onVerificationStateChangedCallbacks, str);
    }

    public static void zzc() {
        zza.clear();
    }

    public static boolean zzd(String str, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        Map map = zza;
        if (map.containsKey(str)) {
            zzafm zzafmVar = (zzafm) map.get(str);
            if (DefaultClock.getInstance().currentTimeMillis() - zzafmVar.zzb < 120000) {
                zzaez zzaezVar = zzafmVar.zza;
                if (zzaezVar != null) {
                    zzaezVar.zzh(onVerificationStateChangedCallbacks, activity, executor, str);
                    return true;
                }
                return true;
            }
            zze(str, null);
            return false;
        }
        zze(str, null);
        return false;
    }

    private static void zze(String str, zzaez zzaezVar) {
        zza.put(str, new zzafm(zzaezVar, DefaultClock.getInstance().currentTimeMillis()));
    }
}
