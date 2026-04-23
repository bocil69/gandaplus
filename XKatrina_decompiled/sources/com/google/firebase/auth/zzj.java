package com.google.firebase.auth;

import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzj implements OnCompleteListener {
    final /* synthetic */ PhoneAuthOptions zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ FirebaseAuth zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj(FirebaseAuth firebaseAuth, PhoneAuthOptions phoneAuthOptions, String str) {
        this.zzc = firebaseAuth;
        this.zza = phoneAuthOptions;
        this.zzb = str;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task) {
        String zza;
        String str = null;
        if (!task.isSuccessful()) {
            Exception exception = task.getException();
            if (exception instanceof FirebaseAuthMissingActivityForRecaptchaException) {
                FirebaseAuth.zzX((FirebaseAuthMissingActivityForRecaptchaException) exception, this.zza, this.zzb);
                return;
            }
            Log.e("FirebaseAuth", "Error while validating application identity: ".concat(String.valueOf(task.getException() != null ? task.getException().getMessage() : "")));
            Log.e("FirebaseAuth", "Proceeding without any application identifier.");
            zza = null;
        } else {
            str = ((com.google.firebase.auth.internal.zze) task.getResult()).zzb();
            zza = ((com.google.firebase.auth.internal.zze) task.getResult()).zza();
        }
        this.zzc.zzV(this.zza, str, zza);
    }
}
