package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.internal.InternalTokenResult;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* loaded from: classes.dex */
public final class zzv implements Runnable {
    final /* synthetic */ FirebaseAuth zza;
    final /* synthetic */ InternalTokenResult zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzv(FirebaseAuth firebaseAuth, InternalTokenResult internalTokenResult) {
        this.zza = firebaseAuth;
        this.zzb = internalTokenResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        List<IdTokenListener> list;
        List<FirebaseAuth.IdTokenListener> list2;
        list = this.zza.zzd;
        for (IdTokenListener idTokenListener : list) {
            idTokenListener.onIdTokenChanged(this.zzb);
        }
        list2 = this.zza.zzc;
        for (FirebaseAuth.IdTokenListener idTokenListener2 : list2) {
            idTokenListener2.onIdTokenChanged(this.zza);
        }
    }
}
