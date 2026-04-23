package com.google.firebase.iid;

import android.util.Log;
import android.util.Pair;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzaq {
    private final Executor executor;
    @GuardedBy("this")
    private final Map<Pair<String, String>, Task<InstanceIdResult>> zzcs = new ArrayMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaq(Executor executor) {
        this.executor = executor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public final synchronized Task<InstanceIdResult> zza(String str, String str2, zzar zzarVar) {
        Task task;
        final Pair pair = new Pair(str, str2);
        Task task2 = this.zzcs.get(pair);
        if (task2 != null) {
            task = task2;
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(pair);
                Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 29).append("Joining ongoing request for: ").append(valueOf).toString());
                task = task2;
            }
        } else {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf2 = String.valueOf(pair);
                Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf2).length() + 24).append("Making new request for: ").append(valueOf2).toString());
            }
            Task continueWithTask = zzarVar.zzs().continueWithTask(this.executor, new Continuation(this, pair) { // from class: com.google.firebase.iid.zzas
                private final zzaq zzcu;
                private final Pair zzcv;

                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    this.zzcu = this;
                    this.zzcv = pair;
                }

                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task3) {
                    return this.zzcu.zza(this.zzcv, task3);
                }
            });
            this.zzcs.put(pair, continueWithTask);
            task = continueWithTask;
        }
        return task;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Task zza(Pair pair, Task task) throws Exception {
        synchronized (this) {
            this.zzcs.remove(pair);
        }
        return task;
    }
}
