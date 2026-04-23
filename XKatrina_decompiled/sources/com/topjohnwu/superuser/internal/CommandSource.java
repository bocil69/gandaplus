package com.topjohnwu.superuser.internal;

import com.topjohnwu.superuser.internal.ShellInputSource;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ShellInputSource.java */
/* loaded from: classes2.dex */
public class CommandSource implements ShellInputSource {
    private final String[] cmd;

    @Override // com.topjohnwu.superuser.internal.ShellInputSource, java.io.Closeable, java.lang.AutoCloseable
    public /* synthetic */ void close() {
        ShellInputSource.CC.$default$close(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CommandSource(String[] strArr) {
        this.cmd = strArr;
    }

    @Override // com.topjohnwu.superuser.internal.ShellInputSource
    public void serve(OutputStream outputStream) throws IOException {
        String[] strArr;
        for (String str : this.cmd) {
            outputStream.write(str.getBytes(StandardCharsets.UTF_8));
            outputStream.write(10);
            Utils.log(ShellInputSource.TAG, str);
        }
    }
}
