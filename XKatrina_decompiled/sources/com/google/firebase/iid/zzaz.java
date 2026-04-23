package com.google.firebase.iid;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzaz {
    @GuardedBy("itself")
    private final zzav zzar;
    @GuardedBy("this")
    private int zzdp = 0;
    @GuardedBy("this")
    private final Map<Integer, TaskCompletionSource<Void>> zzdq = new ArrayMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaz(zzav zzavVar) {
        this.zzar = zzavVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized Task<Void> zza(String str) {
        String zzai;
        TaskCompletionSource<Void> taskCompletionSource;
        synchronized (this.zzar) {
            zzai = this.zzar.zzai();
            this.zzar.zzf(new StringBuilder(String.valueOf(zzai).length() + 1 + String.valueOf(str).length()).append(zzai).append(",").append(str).toString());
        }
        taskCompletionSource = new TaskCompletionSource<>();
        this.zzdq.put(Integer.valueOf((TextUtils.isEmpty(zzai) ? 0 : zzai.split(",").length - 1) + this.zzdp), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized boolean zzao() {
        return zzap() != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0016, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000b, code lost:
        if (com.google.firebase.iid.FirebaseInstanceId.zzm() == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000d, code lost:
        android.util.Log.d("FirebaseInstanceId", "topic sync succeeded");
     */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean zzc(com.google.firebase.iid.FirebaseInstanceId r4) {
        /*
            r3 = this;
        L0:
            monitor-enter(r3)
            java.lang.String r1 = r3.zzap()     // Catch: java.lang.Throwable -> L20
            if (r1 != 0) goto L17
            boolean r0 = com.google.firebase.iid.FirebaseInstanceId.zzm()     // Catch: java.lang.Throwable -> L20
            if (r0 == 0) goto L14
            java.lang.String r0 = "FirebaseInstanceId"
            java.lang.String r1 = "topic sync succeeded"
            android.util.Log.d(r0, r1)     // Catch: java.lang.Throwable -> L20
        L14:
            r0 = 1
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L20
        L16:
            return r0
        L17:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L20
            boolean r0 = zza(r4, r1)
            if (r0 != 0) goto L23
            r0 = 0
            goto L16
        L20:
            r0 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L20
            throw r0
        L23:
            monitor-enter(r3)
            java.util.Map<java.lang.Integer, com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>> r0 = r3.zzdq     // Catch: java.lang.Throwable -> L43
            int r2 = r3.zzdp     // Catch: java.lang.Throwable -> L43
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L43
            java.lang.Object r0 = r0.remove(r2)     // Catch: java.lang.Throwable -> L43
            com.google.android.gms.tasks.TaskCompletionSource r0 = (com.google.android.gms.tasks.TaskCompletionSource) r0     // Catch: java.lang.Throwable -> L43
            r3.zzk(r1)     // Catch: java.lang.Throwable -> L43
            int r1 = r3.zzdp     // Catch: java.lang.Throwable -> L43
            int r1 = r1 + 1
            r3.zzdp = r1     // Catch: java.lang.Throwable -> L43
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L43
            if (r0 == 0) goto L0
            r1 = 0
            r0.setResult(r1)
            goto L0
        L43:
            r0 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L43
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzaz.zzc(com.google.firebase.iid.FirebaseInstanceId):boolean");
    }

    @Nullable
    @GuardedBy("this")
    private final String zzap() {
        String zzai;
        synchronized (this.zzar) {
            zzai = this.zzar.zzai();
        }
        if (!TextUtils.isEmpty(zzai)) {
            String[] split = zzai.split(",");
            if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                return split[1];
            }
        }
        return null;
    }

    private final synchronized boolean zzk(String str) {
        boolean z;
        synchronized (this.zzar) {
            String zzai = this.zzar.zzai();
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (zzai.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                String valueOf3 = String.valueOf(",");
                String valueOf4 = String.valueOf(str);
                this.zzar.zzf(zzai.substring((valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3)).length()));
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    @WorkerThread
    private static boolean zza(FirebaseInstanceId firebaseInstanceId, String str) {
        String[] split = str.split("!");
        if (split.length == 2) {
            String str2 = split[0];
            String str3 = split[1];
            char c = 65535;
            try {
                switch (str2.hashCode()) {
                    case 83:
                        if (str2.equals(ExifInterface.LATITUDE_SOUTH)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 85:
                        if (str2.equals("U")) {
                            c = 1;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        firebaseInstanceId.zzb(str3);
                        if (FirebaseInstanceId.zzm()) {
                            Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                            return true;
                        }
                        return true;
                    case 1:
                        firebaseInstanceId.zzc(str3);
                        if (FirebaseInstanceId.zzm()) {
                            Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                            return true;
                        }
                        return true;
                    default:
                        return true;
                }
            } catch (IOException e) {
                String valueOf = String.valueOf(e.getMessage());
                Log.e("FirebaseInstanceId", valueOf.length() != 0 ? "Topic sync failed: ".concat(valueOf) : new String("Topic sync failed: "));
                return false;
            }
        }
        return true;
    }
}
