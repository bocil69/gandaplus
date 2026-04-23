package com.google.firebase.auth.internal;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p000firebaseauthapi.zzadz;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public abstract class zzbl {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Task zzc(RecaptchaAction recaptchaAction, FirebaseAuth firebaseAuth, String str, Continuation continuation, Task task) throws Exception {
        if (task.isSuccessful()) {
            return Tasks.forResult(task.getResult());
        }
        Exception exc = (Exception) Preconditions.checkNotNull(task.getException());
        int i = zzadz.zzb;
        if (!(exc instanceof FirebaseAuthException) || !((FirebaseAuthException) exc).getErrorCode().endsWith("MISSING_RECAPTCHA_TOKEN")) {
            String valueOf = String.valueOf(recaptchaAction);
            String message = exc.getMessage();
            Log.e("RecaptchaCallWrapper", "Initial task failed for action " + valueOf + "with exception - " + message);
            return Tasks.forException(exc);
        }
        if (Log.isLoggable("RecaptchaCallWrapper", 4)) {
            Log.i("RecaptchaCallWrapper", "Falling back to recaptcha enterprise flow for action ".concat(String.valueOf(String.valueOf(recaptchaAction))));
        }
        if (firebaseAuth.zzB() == null) {
            firebaseAuth.zzP(new zzbr(firebaseAuth.getApp(), firebaseAuth));
        }
        return zzd(firebaseAuth.zzB(), recaptchaAction, str, continuation);
    }

    private static Task zzd(zzbr zzbrVar, RecaptchaAction recaptchaAction, String str, Continuation continuation) {
        Task zza = zzbrVar.zza(str, false, recaptchaAction);
        return zza.continueWithTask(continuation).continueWithTask(new zzbk(str, zzbrVar, recaptchaAction, continuation));
    }

    public abstract Task zza(String str);

    public final Task zzb(final FirebaseAuth firebaseAuth, final String str, final RecaptchaAction recaptchaAction) {
        final Continuation continuation = new Continuation() { // from class: com.google.firebase.auth.internal.zzbj
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                zzbl zzblVar = zzbl.this;
                if (!task.isSuccessful()) {
                    String message = ((Exception) Preconditions.checkNotNull(task.getException())).getMessage();
                    Log.e("RecaptchaCallWrapper", "Failed to get Recaptcha token, error - " + message + "\n\n Failing open with a fake token.");
                    return zzblVar.zza("NO_RECAPTCHA");
                }
                return zzblVar.zza((String) task.getResult());
            }
        };
        zzbr zzB = firebaseAuth.zzB();
        if (zzB == null || !zzB.zze()) {
            return zza(null).continueWithTask(new Continuation() { // from class: com.google.firebase.auth.internal.zzbi
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return zzbl.zzc(RecaptchaAction.this, firebaseAuth, str, continuation, task);
                }
            });
        }
        return zzd(zzB, recaptchaAction, str, continuation);
    }
}
