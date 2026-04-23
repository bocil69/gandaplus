package com.topjohnwu.superuser.internal;

import androidx.annotation.NonNull;
import androidx.core.location.LocationRequestCompat;
import com.topjohnwu.superuser.ShellUtils;
import com.topjohnwu.superuser.io.SuFile;
import java.io.FileNotFoundException;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ShellBlockIO extends ShellIO {
    private final long blockSize;

    @Override // com.topjohnwu.superuser.internal.ShellIO
    protected String getConv() {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ShellBlockIO(SuFile suFile, String str) throws FileNotFoundException {
        super(suFile, str);
        long j;
        try {
            j = Long.parseLong(ShellUtils.fastCmd("blockdev --getsize64 " + suFile.getEscapedPath()));
        } catch (NumberFormatException unused) {
            j = LocationRequestCompat.PASSIVE_INTERVAL;
        }
        this.blockSize = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.topjohnwu.superuser.internal.ShellIO
    public int alignedRead(byte[] bArr, int i, int i2, int i3, int i4) throws IOException {
        if (i3 * i4 >= this.blockSize) {
            this.eof = true;
            return -1;
        }
        return super.alignedRead(bArr, i, i2, i3, i4);
    }

    @Override // com.topjohnwu.superuser.internal.ShellIO, java.io.DataOutput
    public void write(@NonNull byte[] bArr, int i, int i2) throws IOException {
        if (this.fileOff + i2 > this.blockSize) {
            throw new IOException("Cannot write pass block size");
        }
        super.write(bArr, i, i2);
    }

    @Override // com.topjohnwu.superuser.internal.ShellIO, com.topjohnwu.superuser.io.SuRandomAccessFile
    public long length() {
        return this.blockSize;
    }

    @Override // com.topjohnwu.superuser.internal.ShellIO, com.topjohnwu.superuser.io.SuRandomAccessFile
    public void setLength(long j) {
        throw new UnsupportedOperationException("Block devices have fixed sizes");
    }

    @Override // com.topjohnwu.superuser.internal.ShellIO, com.topjohnwu.superuser.io.SuRandomAccessFile
    public void seek(long j) throws IOException {
        if (j > this.blockSize) {
            throw new IOException("Cannot seek pass block size");
        }
        this.fileOff = j;
    }
}
