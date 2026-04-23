package com.google.firebase.auth.internal;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaTasksClient;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
final class zzbq implements Continuation {
    final /* synthetic */ RecaptchaAction zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbq(zzbr zzbrVar, RecaptchaAction recaptchaAction) {
        this.zza = recaptchaAction;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* bridge */ /* synthetic */ Object then(Task task) throws Exception {
        if (task.isSuccessful()) {
            return ((RecaptchaTasksClient) task.getResult()).executeTask(this.zza);
        }
        Exception exc = (Exception) Preconditions.checkNotNull(task.getException());
        if (exc instanceof zzbo) {
            if (Log.isLoggable("RecaptchaHandler", 4)) {
                Log.i("RecaptchaHandler", "Ignoring error related to fetching recaptcha config - ".concat(String.valueOf(exc.getMessage())));
            }
            return Tasks.forResult("");
        }
        return Tasks.forException(exc);
    }
}
