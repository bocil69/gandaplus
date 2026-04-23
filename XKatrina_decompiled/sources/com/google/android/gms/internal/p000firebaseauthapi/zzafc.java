package com.google.android.gms.internal.p000firebaseauthapi;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Executor;
/* compiled from: com.google.firebase:firebase-auth@@22.1.2 */
/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafc  reason: invalid package */
/* loaded from: classes.dex */
public class zzafc {
    zzady zza;
    Executor zzb;

    public final Task zzU(final zzafb zzafbVar) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzb.execute(new Runnable() { // from class: com.google.android.gms.internal.firebase-auth-api.zzafa
            @Override // java.lang.Runnable
            public final void run() {
                zzafbVar.zzc(taskCompletionSource, zzafc.this.zza);
            }
        });
        return taskCompletionSource.getTask();
    }
}
