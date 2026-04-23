package com.topjohnwu.superuser.internal;

import androidx.annotation.NonNull;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ByteOutputStream extends ByteArrayOutputStream {
    public byte[] getBuf() {
        return this.buf;
    }

    public void writeTo(@NonNull DataOutput dataOutput) throws IOException {
        dataOutput.write(this.buf, 0, this.count);
    }
}
