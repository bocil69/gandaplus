package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.firebase.auth.PhoneAuthProvider;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaev  reason: invalid package */
/* loaded from: classes.dex */
final class zzaev implements Runnable {
    final /* synthetic */ zzaex zza;
    final /* synthetic */ zzaew zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaev(zzaew zzaewVar, zzaex zzaexVar) {
        this.zzb = zzaewVar;
        this.zza = zzaexVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zza.zzl) {
            if (!this.zzb.zza.zzl.isEmpty()) {
                this.zza.zza((PhoneAuthProvider.OnVerificationStateChangedCallbacks) this.zzb.zza.zzl.get(0), new Object[0]);
            }
        }
    }
}
