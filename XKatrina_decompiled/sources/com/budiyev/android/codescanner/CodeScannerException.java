package com.budiyev.android.codescanner;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
/* loaded from: classes2.dex */
public final class CodeScannerException extends RuntimeException {
    public CodeScannerException() {
    }

    public CodeScannerException(@Nullable String str) {
        super(str);
    }

    public CodeScannerException(@Nullable String str, @Nullable Throwable th) {
        super(str, th);
    }

    public CodeScannerException(@Nullable Throwable th) {
        super(th);
    }

    @RequiresApi(24)
    public CodeScannerException(@Nullable String str, @Nullable Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
    }
}
