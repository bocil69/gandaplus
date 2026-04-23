package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p000firebaseauthapi.zzahs;
import com.google.android.gms.internal.p000firebaseauthapi.zzahy;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.internal.zzbw;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzt implements Continuation {
    final /* synthetic */ FirebaseAuth zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzt(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* bridge */ /* synthetic */ Object then(Task task) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException((Exception) Preconditions.checkNotNull(task.getException()));
        }
        zzahs zzahsVar = (zzahs) task.getResult();
        if (!(zzahsVar instanceof zzahy)) {
            String name = zzahsVar.getClass().getName();
            throw new IllegalArgumentException("Response should be an instance of StartTotpMfaEnrollmentResponse but was " + name + ".");
        }
        zzahy zzahyVar = (zzahy) zzahsVar;
        return Tasks.forResult(new zzbw(Preconditions.checkNotEmpty(zzahyVar.zzi()), Preconditions.checkNotEmpty(zzahyVar.zzh()), zzahyVar.zze(), zzahyVar.zzd(), zzahyVar.zzf(), Preconditions.checkNotEmpty(zzahyVar.zzc()), this.zza));
    }
}
