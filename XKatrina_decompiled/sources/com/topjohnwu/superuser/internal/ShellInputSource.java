package com.topjohnwu.superuser.internal;

import com.android.tools.r8.annotations.SynthesizedClassV2;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes2.dex */
interface ShellInputSource extends Closeable {
    public static final String TAG = "SHELL_IN";

    @SynthesizedClassV2(kind = 7, versionHash = "79350b666c61fb98f585652cf8eb3be7850d2ab8c16c1e890d0171be2ca2d761")
    /* renamed from: com.topjohnwu.superuser.internal.ShellInputSource$-CC  reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC {
        public static void $default$close(ShellInputSource _this) {
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void serve(OutputStream outputStream) throws IOException;
}
