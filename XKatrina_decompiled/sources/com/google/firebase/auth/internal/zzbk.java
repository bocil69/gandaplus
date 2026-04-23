package com.google.firebase.auth.internal;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p000firebaseauthapi.zzadz;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.firebase.auth.FirebaseAuthException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
final class zzbk implements Continuation {
    final /* synthetic */ String zza;
    final /* synthetic */ zzbr zzb;
    final /* synthetic */ RecaptchaAction zzc;
    final /* synthetic */ Continuation zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbk(String str, zzbr zzbrVar, RecaptchaAction recaptchaAction, Continuation continuation) {
        this.zza = str;
        this.zzb = zzbrVar;
        this.zzc = recaptchaAction;
        this.zzd = continuation;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* bridge */ /* synthetic */ Object then(Task task) throws Exception {
        if (task.isSuccessful()) {
            return task;
        }
        Exception exc = (Exception) Preconditions.checkNotNull(task.getException());
        int i = zzadz.zzb;
        if ((exc instanceof FirebaseAuthException) && ((FirebaseAuthException) exc).getErrorCode().endsWith("INVALID_RECAPTCHA_TOKEN")) {
            if (Log.isLoggable("RecaptchaCallWrapper", 4)) {
                Log.i("RecaptchaCallWrapper", "Invalid token - Refreshing Recaptcha Enterprise config and fetching new token for tenant ".concat(String.valueOf(this.zza)));
            }
            return this.zzb.zza(this.zza, true, this.zzc).continueWithTask(this.zzd);
        }
        return task;
    }
}
