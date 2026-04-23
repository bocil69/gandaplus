package com.budiyev.android.codescanner;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.budiyev.android.codescanner.Utils;
/* loaded from: classes2.dex */
public interface ErrorCallback {
    @NonNull
    public static final ErrorCallback SUPPRESS = new Utils.SuppressErrorCallback();

    @WorkerThread
    void onError(@NonNull Throwable th);
}
