package com.topjohnwu.superuser.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ShellInputSource.java */
/* loaded from: classes2.dex */
public class InputStreamSource implements ShellInputSource {
    private final InputStream in;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InputStreamSource(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override // com.topjohnwu.superuser.internal.ShellInputSource
    public void serve(OutputStream outputStream) throws IOException {
        Utils.pump(this.in, outputStream);
        this.in.close();
        outputStream.write(10);
        Utils.log(ShellInputSource.TAG, "<InputStream>");
    }

    @Override // com.topjohnwu.superuser.internal.ShellInputSource, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.in.close();
        } catch (IOException unused) {
        }
    }
}
