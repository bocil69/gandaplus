package com.google.firebase.auth.internal;

import android.util.Base64;
import android.util.Log;
import com.google.android.gms.internal.p000firebaseauthapi.zzagx;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.integrity.IntegrityManager;
import com.google.android.play.core.integrity.IntegrityTokenRequest;
import java.security.MessageDigest;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzb implements Continuation {
    final /* synthetic */ String zza;
    final /* synthetic */ IntegrityManager zzb;
    final /* synthetic */ zzf zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(zzf zzfVar, String str, IntegrityManager integrityManager) {
        this.zzc = zzfVar;
        this.zza = str;
        this.zzb = integrityManager;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* bridge */ /* synthetic */ Object then(Task task) throws Exception {
        String str;
        if (task.isSuccessful()) {
            this.zzc.zzc = ((zzagx) task.getResult()).zzb();
            return this.zzb.requestIntegrityToken(IntegrityTokenRequest.builder().setCloudProjectNumber(Long.parseLong(((zzagx) task.getResult()).zzb())).setNonce(new String(Base64.encode(MessageDigest.getInstance("SHA-256").digest(this.zza.getBytes("UTF-8")), 11))).build());
        }
        str = zzf.zza;
        Log.e(str, "Problem retrieving Play Integrity producer project:  ".concat(String.valueOf(task.getException().getMessage())));
        return Tasks.forException(task.getException());
    }
}
