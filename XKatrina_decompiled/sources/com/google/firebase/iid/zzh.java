package com.google.firebase.iid;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
final class zzh {
    private static final Executor zzaf = zzj.zzam;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor zzd() {
        return zzaf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor zze() {
        return new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), zzk.zzan);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ Thread zza(Runnable runnable) {
        return new Thread(runnable, "firebase-iid-executor");
    }
}
