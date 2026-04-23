package com.topjohnwu.superuser.internal;

import android.os.RemoteException;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import com.topjohnwu.superuser.nio.FileSystemManager;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
/* loaded from: classes2.dex */
class RemoteFileChannel extends FileChannel {
    private static final int PIPE_CAPACITY = 65536;
    private final Object fdLock = new Object();
    private final IFileSystemService fs;
    private final int handle;
    private final int mode;
    private final FileDescriptor read;
    private final FileDescriptor write;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RemoteFileChannel(IFileSystemService iFileSystemService, File file, int i) throws IOException {
        this.fs = iFileSystemService;
        this.mode = i;
        File file2 = null;
        try {
            try {
                File createTempFIFO = FileUtils.createTempFIFO();
                this.handle = ((Integer) iFileSystemService.openChannel(file.getAbsolutePath(), FileUtils.modeToPosix(i), createTempFIFO.getPath()).tryAndGet()).intValue();
                this.read = Os.open(createTempFIFO.getPath(), OsConstants.O_RDONLY | OsConstants.O_NONBLOCK, 0);
                this.write = Os.open(createTempFIFO.getPath(), OsConstants.O_WRONLY | OsConstants.O_NONBLOCK, 0);
                if (createTempFIFO != null) {
                    createTempFIFO.delete();
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    file2.delete();
                }
                throw th;
            }
        } catch (RemoteException | ErrnoException e) {
            throw new IOException(e);
        }
    }

    private void ensureOpen() throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
    }

    private boolean writable() {
        int i = this.mode & FileSystemManager.MODE_READ_WRITE;
        return i == 536870912 || i == 805306368;
    }

    private boolean readable() {
        int i = this.mode & FileSystemManager.MODE_READ_WRITE;
        return i == 268435456 || i == 805306368;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x004b, code lost:
        if (r10 < 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004d, code lost:
        r10 = r10 + r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0072, code lost:
        r2 = r2 - r1;
        r9.limit(r0);
        end(true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x007a, code lost:
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int read0(java.nio.ByteBuffer r9, long r10) throws java.io.IOException {
        /*
            r8 = this;
            r8.begin()
            int r0 = r9.limit()
            int r1 = r9.position()
            r2 = r1
        Lc:
            if (r0 <= r2) goto L72
            r3 = 0
            java.lang.Object r4 = r8.fdLock     // Catch: java.lang.Throwable -> L60 android.os.RemoteException -> L62 android.system.ErrnoException -> L64
            monitor-enter(r4)     // Catch: java.lang.Throwable -> L60 android.os.RemoteException -> L62 android.system.ErrnoException -> L64
            boolean r5 = r8.isOpen()     // Catch: java.lang.Throwable -> L5d
            if (r5 == 0) goto L54
            boolean r5 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L5d
            if (r5 == 0) goto L1f
            goto L54
        L1f:
            com.topjohnwu.superuser.internal.IFileSystemService r5 = r8.fs     // Catch: java.lang.Throwable -> L5d
            int r6 = r8.handle     // Catch: java.lang.Throwable -> L5d
            int r7 = r0 - r2
            com.topjohnwu.superuser.internal.IOResult r5 = r5.pread(r6, r7, r10)     // Catch: java.lang.Throwable -> L5d
            java.lang.Object r5 = r5.tryAndGet()     // Catch: java.lang.Throwable -> L5d
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch: java.lang.Throwable -> L5d
            int r5 = r5.intValue()     // Catch: java.lang.Throwable -> L5d
            if (r5 != 0) goto L37
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L5d
            goto L72
        L37:
            int r2 = r2 + r5
            r9.limit(r2)     // Catch: java.lang.Throwable -> L5d
            r2 = 0
        L3c:
            if (r2 >= r5) goto L46
            java.io.FileDescriptor r6 = r8.read     // Catch: java.lang.Throwable -> L5d
            int r6 = android.system.Os.read(r6, r9)     // Catch: java.lang.Throwable -> L5d
            int r2 = r2 + r6
            goto L3c
        L46:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L5d
            r6 = 0
            int r2 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r2 < 0) goto L4f
            long r4 = (long) r5
            long r10 = r10 + r4
        L4f:
            int r2 = r9.position()     // Catch: java.lang.Throwable -> L60 android.os.RemoteException -> L62 android.system.ErrnoException -> L64
            goto Lc
        L54:
            r10 = -1
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L5d
            r9.limit(r0)
            r8.end(r3)
            return r10
        L5d:
            r10 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L5d
            throw r10     // Catch: java.lang.Throwable -> L60 android.os.RemoteException -> L62 android.system.ErrnoException -> L64
        L60:
            r10 = move-exception
            goto L6b
        L62:
            r10 = move-exception
            goto L65
        L64:
            r10 = move-exception
        L65:
            java.io.IOException r11 = new java.io.IOException     // Catch: java.lang.Throwable -> L60
            r11.<init>(r10)     // Catch: java.lang.Throwable -> L60
            throw r11     // Catch: java.lang.Throwable -> L60
        L6b:
            r9.limit(r0)
            r8.end(r3)
            throw r10
        L72:
            r10 = 1
            int r2 = r2 - r1
            r9.limit(r0)
            r8.end(r10)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.topjohnwu.superuser.internal.RemoteFileChannel.read0(java.nio.ByteBuffer, long):int");
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        ensureOpen();
        if (!readable()) {
            throw new NonReadableChannelException();
        }
        return read0(byteBuffer, -1L);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.ScatteringByteChannel
    public long read(ByteBuffer[] byteBufferArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i > byteBufferArr.length - i2) {
            throw new IndexOutOfBoundsException();
        }
        ensureOpen();
        if (readable()) {
            int i3 = 0;
            for (int i4 = i; i4 < i + i2; i4++) {
                i3 += read0(byteBufferArr[i4], -1L);
            }
            return i3;
        }
        throw new NonReadableChannelException();
    }

    private int write0(ByteBuffer byteBuffer, long j) throws IOException {
        int write;
        begin();
        int remaining = byteBuffer.remaining();
        while (true) {
            try {
                try {
                    if (byteBuffer.hasRemaining()) {
                        synchronized (this.fdLock) {
                            if (!isOpen() || Thread.interrupted()) {
                                break;
                            }
                            write = Os.write(this.write, byteBuffer);
                            this.fs.pwrite(this.handle, write, j).checkException();
                        }
                        if (j >= 0) {
                            j += write;
                        }
                    } else {
                        end(true);
                        return remaining;
                    }
                } finally {
                    end(false);
                }
            } catch (RemoteException | ErrnoException e) {
                throw new IOException(e);
            }
        }
        return -1;
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        ensureOpen();
        if (!writable()) {
            throw new NonWritableChannelException();
        }
        return write0(byteBuffer, -1L);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.GatheringByteChannel
    public long write(ByteBuffer[] byteBufferArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i > byteBufferArr.length - i2) {
            throw new IndexOutOfBoundsException();
        }
        ensureOpen();
        if (writable()) {
            int i3 = 0;
            for (int i4 = i; i4 < i + i2; i4++) {
                i3 += write(byteBufferArr[i4]);
            }
            return i3;
        }
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public long position() throws IOException {
        ensureOpen();
        try {
            return ((Long) this.fs.lseek(this.handle, 0L, OsConstants.SEEK_CUR).tryAndGet()).longValue();
        } catch (RemoteException e) {
            throw new IOException(e);
        }
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public RemoteFileChannel position(long j) throws IOException {
        ensureOpen();
        if (j < 0) {
            throw new IllegalArgumentException();
        }
        try {
            this.fs.lseek(this.handle, j, OsConstants.SEEK_SET).checkException();
            return this;
        } catch (RemoteException e) {
            throw new IOException(e);
        }
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public long size() throws IOException {
        ensureOpen();
        try {
            return ((Long) this.fs.size(this.handle).tryAndGet()).longValue();
        } catch (RemoteException e) {
            throw new IOException(e);
        }
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public RemoteFileChannel truncate(long j) throws IOException {
        ensureOpen();
        if (j < 0) {
            throw new IllegalArgumentException("Negative size");
        }
        if (!writable()) {
            throw new NonWritableChannelException();
        }
        try {
            this.fs.ftruncate(this.handle, j).checkException();
            return this;
        } catch (RemoteException e) {
            throw new IOException(e);
        }
    }

    @Override // java.nio.channels.FileChannel
    public void force(boolean z) throws IOException {
        ensureOpen();
        try {
            this.fs.sync(this.handle, z).checkException();
        } catch (RemoteException e) {
            throw new IOException(e);
        }
    }

    @Override // java.nio.channels.FileChannel
    public long transferTo(long j, long j2, WritableByteChannel writableByteChannel) throws IOException {
        ensureOpen();
        if (!writableByteChannel.isOpen()) {
            throw new ClosedChannelException();
        }
        if (readable()) {
            long j3 = 0;
            if (j < 0 || j2 < 0) {
                throw new IllegalArgumentException();
            }
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(65536);
            while (j2 > j3) {
                allocateDirect.limit((int) Math.min(allocateDirect.capacity(), j2 - j3));
                if (read0(allocateDirect, j) <= 0) {
                    break;
                }
                allocateDirect.flip();
                long write = writableByteChannel.write(allocateDirect);
                j += write;
                j3 += write;
                allocateDirect.clear();
            }
            return j3;
        }
        throw new NonReadableChannelException();
    }

    @Override // java.nio.channels.FileChannel
    public long transferFrom(ReadableByteChannel readableByteChannel, long j, long j2) throws IOException {
        ensureOpen();
        if (!readableByteChannel.isOpen()) {
            throw new ClosedChannelException();
        }
        if (writable()) {
            long j3 = 0;
            if (j < 0 || j2 < 0) {
                throw new IllegalArgumentException();
            }
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(65536);
            while (j2 > j3) {
                allocateDirect.limit((int) Math.min(allocateDirect.capacity(), j2 - j3));
                if (readableByteChannel.read(allocateDirect) <= 0) {
                    break;
                }
                allocateDirect.flip();
                long write0 = write0(allocateDirect, j);
                j += write0;
                j3 += write0;
                allocateDirect.clear();
            }
            return j3;
        }
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.FileChannel
    public int read(ByteBuffer byteBuffer, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("Negative position");
        }
        ensureOpen();
        return read0(byteBuffer, j);
    }

    @Override // java.nio.channels.FileChannel
    public int write(ByteBuffer byteBuffer, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("Negative position");
        }
        ensureOpen();
        return write0(byteBuffer, j);
    }

    @Override // java.nio.channels.spi.AbstractInterruptibleChannel
    protected void implCloseChannel() {
        try {
            this.fs.close(this.handle);
        } catch (RemoteException unused) {
        }
        synchronized (this.fdLock) {
            try {
                Os.close(this.read);
            } catch (ErrnoException unused2) {
            }
            try {
                Os.close(this.write);
            } catch (ErrnoException unused3) {
            }
        }
    }

    @Override // java.nio.channels.FileChannel
    public MappedByteBuffer map(FileChannel.MapMode mapMode, long j, long j2) {
        throw new UnsupportedOperationException("Memory mapping a remote file is not supported!");
    }

    @Override // java.nio.channels.FileChannel
    public FileLock lock(long j, long j2, boolean z) {
        throw new UnsupportedOperationException("Locking a remote file is not supported!");
    }

    @Override // java.nio.channels.FileChannel
    public FileLock tryLock(long j, long j2, boolean z) {
        throw new UnsupportedOperationException("Locking a remote file is not supported!");
    }
}
