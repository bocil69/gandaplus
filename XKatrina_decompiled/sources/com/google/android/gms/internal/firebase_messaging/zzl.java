package com.google.android.gms.internal.firebase_messaging;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
final class zzl extends FilterInputStream {
    private long zzh;
    private long zzi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(InputStream inputStream, long j) {
        super(inputStream);
        this.zzi = -1L;
        zzg.checkNotNull(inputStream);
        if (!(1048576 >= 0)) {
            throw new IllegalArgumentException(String.valueOf("limit must be non-negative"));
        }
        this.zzh = 1048576L;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int available() throws IOException {
        return (int) Math.min(this.in.available(), this.zzh);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized void mark(int i) {
        this.in.mark(i);
        this.zzi = this.zzh;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        if (this.zzh == 0) {
            return -1;
        }
        int read = this.in.read();
        if (read != -1) {
            this.zzh--;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.zzh == 0) {
            return -1;
        }
        int read = this.in.read(bArr, i, (int) Math.min(i2, this.zzh));
        if (read != -1) {
            this.zzh -= read;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized void reset() throws IOException {
        if (!this.in.markSupported()) {
            throw new IOException("Mark not supported");
        }
        if (this.zzi == -1) {
            throw new IOException("Mark not set");
        }
        this.in.reset();
        this.zzh = this.zzi;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j) throws IOException {
        long skip = this.in.skip(Math.min(j, this.zzh));
        this.zzh -= skip;
        return skip;
    }
}
