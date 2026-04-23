package com.google.firebase.auth.internal;

import android.os.Build;
import com.google.android.gms.internal.p000firebaseauthapi.zzaha;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzbr {
    final Map zza;
    final FirebaseApp zzb;
    final FirebaseAuth zzc;
    final zzbm zzd;
    private zzaha zze;

    public zzbr(FirebaseApp firebaseApp, FirebaseAuth firebaseAuth) {
        zzbn zzbnVar = new zzbn();
        this.zza = new HashMap();
        this.zzb = firebaseApp;
        this.zzc = firebaseAuth;
        this.zzd = zzbnVar;
    }

    public static void zzd() throws zzbs {
        if (Build.VERSION.SDK_INT >= 19) {
            return;
        }
        int i = Build.VERSION.SDK_INT;
        throw new zzbs("SDK version too low to use Recaptcha Enterprise. Got " + i + ", Want >= 19");
    }

    private final Task zzf(String str) {
        return (Task) this.zza.get(str);
    }

    private static String zzg(String str) {
        return com.google.android.gms.internal.p000firebaseauthapi.zzac.zzd(str) ? "*" : str;
    }

    public final Task zza(String str, Boolean bool, RecaptchaAction recaptchaAction) {
        try {
            zzd();
            String zzg = zzg(str);
            Task zzf = zzf(zzg);
            if (bool.booleanValue() || zzf == null) {
                zzf = zzb(zzg, bool);
            }
            return zzf.continueWithTask(new zzbq(this, recaptchaAction));
        } catch (zzbs e) {
            return Tasks.forException(e);
        }
    }

    public final Task zzb(String str, Boolean bool) {
        Task zzf;
        try {
            zzd();
            String zzg = zzg(str);
            return (bool.booleanValue() || (zzf = zzf(zzg)) == null) ? this.zzc.zze("RECAPTCHA_ENTERPRISE").continueWithTask(new zzbp(this, zzg)) : zzf;
        } catch (zzbs e) {
            return Tasks.forException(e);
        }
    }

    public final boolean zze() {
        zzaha zzahaVar = this.zze;
        return zzahaVar != null && zzahaVar.zzc();
    }
}
