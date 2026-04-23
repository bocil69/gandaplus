package com.topjohnwu.superuser.internal;

import android.os.Build;
import android.system.ErrnoException;
import android.system.Int64Ref;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import android.util.MutableLong;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class OpenFile implements Closeable {
    private static final boolean FORCE_NO_SPLICE = false;
    private ByteBuffer buf;
    FileDescriptor fd;
    FileDescriptor read;
    private StructStat st;
    FileDescriptor write;

    private ByteBuffer getBuf() {
        if (this.buf == null) {
            this.buf = ByteBuffer.allocateDirect(65536);
        }
        this.buf.clear();
        return this.buf;
    }

    private StructStat getStat() throws ErrnoException {
        if (this.st == null) {
            this.st = Os.fstat(this.fd);
        }
        return this.st;
    }

    private void ensureOpen() throws ClosedChannelException {
        if (this.fd == null) {
            throw new ClosedChannelException();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        FileDescriptor fileDescriptor = this.fd;
        if (fileDescriptor != null) {
            try {
                Os.close(fileDescriptor);
            } catch (ErrnoException unused) {
            }
            this.fd = null;
        }
        FileDescriptor fileDescriptor2 = this.read;
        if (fileDescriptor2 != null) {
            try {
                Os.close(fileDescriptor2);
            } catch (ErrnoException unused2) {
            }
            this.read = null;
        }
        FileDescriptor fileDescriptor3 = this.write;
        if (fileDescriptor3 != null) {
            try {
                Os.close(fileDescriptor3);
            } catch (ErrnoException unused3) {
            }
            this.write = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long lseek(long j, int i) throws ErrnoException, IOException {
        ensureOpen();
        return Os.lseek(this.fd, j, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized long size() throws ErrnoException, IOException {
        long lseek;
        ensureOpen();
        long lseek2 = Os.lseek(this.fd, 0L, OsConstants.SEEK_CUR);
        Os.lseek(this.fd, 0L, OsConstants.SEEK_END);
        lseek = Os.lseek(this.fd, 0L, OsConstants.SEEK_CUR);
        Os.lseek(this.fd, lseek2, OsConstants.SEEK_SET);
        return lseek;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void ftruncate(long j) throws ErrnoException, IOException {
        ensureOpen();
        Os.ftruncate(this.fd, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void sync(boolean z) throws ErrnoException, IOException {
        ensureOpen();
        if (z) {
            Os.fsync(this.fd);
        } else {
            Os.fdatasync(this.fd);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int pread(int i, long j) throws ErrnoException, IOException {
        long sendfile;
        if (this.fd == null || this.write == null) {
            throw new ClosedChannelException();
        }
        MutableLong mutableLong = null;
        if (Build.VERSION.SDK_INT >= 28) {
            sendfile = FileUtils.splice(this.fd, j >= 0 ? new Int64Ref(j) : null, this.write, null, i, 0);
        } else {
            StructStat stat = getStat();
            if (!OsConstants.S_ISREG(stat.st_mode) && !OsConstants.S_ISBLK(stat.st_mode)) {
                ByteBuffer buf = getBuf();
                buf.limit(Math.min(i, buf.capacity()));
                if (j < 0) {
                    Os.read(this.fd, buf);
                } else {
                    Os.pread(this.fd, buf, j);
                }
                buf.flip();
                sendfile = buf.remaining();
                int i2 = (int) sendfile;
                while (i2 > 0) {
                    i2 -= Os.write(this.write, buf);
                }
            }
            if (j >= 0) {
                mutableLong = new MutableLong(j);
            }
            sendfile = FileUtils.sendfile(this.write, this.fd, mutableLong, i);
        }
        return (int) sendfile;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int pwrite(int i, long j, boolean z) throws ErrnoException, IOException {
        if (this.fd == null || this.read == null) {
            throw new ClosedChannelException();
        }
        if (Build.VERSION.SDK_INT >= 28) {
            Int64Ref int64Ref = j < 0 ? null : new Int64Ref(j);
            if (!z) {
                return (int) FileUtils.splice(this.read, null, this.fd, int64Ref, i, 0);
            }
            int i2 = i;
            while (i2 > 0) {
                long j2 = i2;
                i2 = (int) (j2 - FileUtils.splice(this.read, null, this.fd, int64Ref, j2, 0));
            }
            return i;
        }
        ByteBuffer buf = getBuf();
        int i3 = 0;
        buf.limit(i);
        if (z) {
            while (i > i3) {
                i3 += Os.read(this.read, buf);
            }
        } else {
            i3 = Os.read(this.read, buf);
        }
        buf.flip();
        int i4 = i3;
        while (i4 > 0) {
            if (j < 0) {
                i4 -= Os.write(this.fd, buf);
            } else {
                int pwrite = Os.pwrite(this.fd, buf, j);
                i4 -= pwrite;
                j += pwrite;
            }
        }
        return i3;
    }
}
