package com.google.firebase.auth.internal;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p000firebaseauthapi.zzaha;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.recaptcha.Recaptcha;
import com.google.android.recaptcha.RecaptchaTasksClient;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzbp implements Continuation {
    final /* synthetic */ String zza;
    final /* synthetic */ zzbr zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbp(zzbr zzbrVar, String str) {
        this.zzb = zzbrVar;
        this.zza = str;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* bridge */ /* synthetic */ Object then(Task task) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException(new zzbo((String) Preconditions.checkNotNull(((Exception) Preconditions.checkNotNull(task.getException())).getMessage())));
        }
        zzaha zzahaVar = (zzaha) task.getResult();
        String zzb = zzahaVar.zzb();
        if (!com.google.android.gms.internal.p000firebaseauthapi.zzac.zzd(zzb)) {
            List zzd = com.google.android.gms.internal.p000firebaseauthapi.zzab.zzb(com.google.android.gms.internal.p000firebaseauthapi.zzj.zzb('/')).zzd(zzb);
            String str = zzd.size() != 4 ? null : (String) zzd.get(3);
            if (TextUtils.isEmpty(str)) {
                return Tasks.forException(new Exception("Invalid siteKey format ".concat(String.valueOf(zzb))));
            }
            if (Log.isLoggable("RecaptchaHandler", 4)) {
                Log.i("RecaptchaHandler", "Successfully obtained site key for tenant ".concat(String.valueOf(this.zza)));
            }
            this.zzb.zze = zzahaVar;
            Task<RecaptchaTasksClient> tasksClient = Recaptcha.getTasksClient((Application) this.zzb.zzb.getApplicationContext(), str);
            this.zzb.zza.put(this.zza, tasksClient);
            return tasksClient;
        }
        return Tasks.forException(new zzbo("No Recaptcha Enterprise siteKey configured for tenant/project ".concat(String.valueOf(this.zza))));
    }
}
