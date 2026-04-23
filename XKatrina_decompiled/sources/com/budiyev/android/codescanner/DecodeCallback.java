package com.budiyev.android.codescanner;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.zxing.Result;
/* loaded from: classes2.dex */
public interface DecodeCallback {
    @WorkerThread
    void onDecoded(@NonNull Result result);
}
