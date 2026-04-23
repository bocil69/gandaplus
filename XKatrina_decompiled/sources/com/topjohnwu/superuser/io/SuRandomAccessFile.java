package com.topjohnwu.superuser.io;

import androidx.annotation.NonNull;
import com.topjohnwu.superuser.internal.IOFactory;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
/* loaded from: classes2.dex */
public abstract class SuRandomAccessFile implements DataInput, DataOutput, Closeable {
    public abstract long getFilePointer() throws IOException;

    public abstract long length() throws IOException;

    public abstract int read() throws IOException;

    public abstract int read(byte[] bArr) throws IOException;

    public abstract int read(byte[] bArr, int i, int i2) throws IOException;

    public abstract void seek(long j) throws IOException;

    public abstract void setLength(long j) throws IOException;

    @NonNull
    public static SuRandomAccessFile open(@NonNull File file, String str) throws FileNotFoundException {
        if (file instanceof SuFile) {
            return IOFactory.shellIO((SuFile) file, str);
        }
        try {
            return IOFactory.raf(file, str);
        } catch (FileNotFoundException unused) {
            return IOFactory.shellIO(new SuFile(file), str);
        }
    }

    @NonNull
    public static SuRandomAccessFile open(@NonNull String str, String str2) throws FileNotFoundException {
        return open(new File(str), str2);
    }
}
