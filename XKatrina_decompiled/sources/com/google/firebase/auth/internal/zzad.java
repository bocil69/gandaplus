package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
final class zzad implements Continuation {
    final /* synthetic */ zzae zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzad(zzae zzaeVar) {
        this.zza = zzaeVar;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* bridge */ /* synthetic */ Object then(Task task) throws Exception {
        com.google.firebase.auth.zze zzeVar;
        com.google.firebase.auth.zze zzeVar2;
        com.google.firebase.auth.zze zzeVar3;
        zzeVar = this.zza.zzd;
        if (zzeVar != null) {
            if (task.isSuccessful()) {
                AuthResult authResult = (AuthResult) task.getResult();
                zzeVar3 = this.zza.zzd;
                return Tasks.forResult(new zzr((zzx) authResult.getUser(), (zzp) authResult.getAdditionalUserInfo(), zzeVar3));
            }
            Exception exception = task.getException();
            if (exception instanceof FirebaseAuthUserCollisionException) {
                zzeVar2 = this.zza.zzd;
                ((FirebaseAuthUserCollisionException) exception).zza(zzeVar2);
            }
            return Tasks.forException(exception);
        }
        return task;
    }
}
