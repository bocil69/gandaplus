package com.termux.terminal;
/* loaded from: classes6.dex */
final class ByteQueue {
    private final byte[] mBuffer;
    private int mHead;
    private boolean mOpen = true;
    private int mStoredBytes;

    public ByteQueue(int i) {
        this.mBuffer = new byte[i];
    }

    public synchronized void close() {
        this.mOpen = false;
        notify();
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0015, code lost:
        if (r8.mOpen != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0019, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x001a, code lost:
        r10 = r8.mBuffer.length;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x001d, code lost:
        if (r10 != r0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x001f, code lost:
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0021, code lost:
        r0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0022, code lost:
        r2 = r9.length;
        r3 = 0;
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0025, code lost:
        if (r2 <= 0) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0027, code lost:
        r5 = r8.mStoredBytes;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0029, code lost:
        if (r5 > 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x002c, code lost:
        r5 = java.lang.Math.min(r2, java.lang.Math.min(r10 - r8.mHead, r5));
        java.lang.System.arraycopy(r8.mBuffer, r8.mHead, r9, r4, r5);
        r6 = r8.mHead + r5;
        r8.mHead = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0044, code lost:
        if (r6 < r10) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0046, code lost:
        r8.mHead = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0048, code lost:
        r8.mStoredBytes -= r5;
        r2 = r2 - r5;
        r4 = r4 + r5;
        r3 = r3 + r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0051, code lost:
        if (r0 == false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0053, code lost:
        notify();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0057, code lost:
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized int read(byte[] r9, boolean r10) {
        /*
            r8 = this;
            monitor-enter(r8)
        L1:
            int r0 = r8.mStoredBytes     // Catch: java.lang.Throwable -> L58
            r1 = 0
            if (r0 != 0) goto L13
            boolean r2 = r8.mOpen     // Catch: java.lang.Throwable -> L58
            if (r2 != 0) goto Lb
            goto L13
        Lb:
            if (r10 == 0) goto L11
            r8.wait()     // Catch: java.lang.InterruptedException -> L1 java.lang.Throwable -> L58
            goto L1
        L11:
            monitor-exit(r8)
            return r1
        L13:
            boolean r10 = r8.mOpen     // Catch: java.lang.Throwable -> L58
            if (r10 != 0) goto L1a
            r9 = -1
            monitor-exit(r8)
            return r9
        L1a:
            byte[] r10 = r8.mBuffer     // Catch: java.lang.Throwable -> L58
            int r10 = r10.length     // Catch: java.lang.Throwable -> L58
            if (r10 != r0) goto L21
            r0 = 1
            goto L22
        L21:
            r0 = 0
        L22:
            int r2 = r9.length     // Catch: java.lang.Throwable -> L58
            r3 = 0
            r4 = 0
        L25:
            if (r2 <= 0) goto L51
            int r5 = r8.mStoredBytes     // Catch: java.lang.Throwable -> L58
            if (r5 > 0) goto L2c
            goto L51
        L2c:
            int r6 = r8.mHead     // Catch: java.lang.Throwable -> L58
            int r6 = r10 - r6
            int r5 = java.lang.Math.min(r6, r5)     // Catch: java.lang.Throwable -> L58
            int r5 = java.lang.Math.min(r2, r5)     // Catch: java.lang.Throwable -> L58
            byte[] r6 = r8.mBuffer     // Catch: java.lang.Throwable -> L58
            int r7 = r8.mHead     // Catch: java.lang.Throwable -> L58
            java.lang.System.arraycopy(r6, r7, r9, r4, r5)     // Catch: java.lang.Throwable -> L58
            int r6 = r8.mHead     // Catch: java.lang.Throwable -> L58
            int r6 = r6 + r5
            r8.mHead = r6     // Catch: java.lang.Throwable -> L58
            if (r6 < r10) goto L48
            r8.mHead = r1     // Catch: java.lang.Throwable -> L58
        L48:
            int r6 = r8.mStoredBytes     // Catch: java.lang.Throwable -> L58
            int r6 = r6 - r5
            r8.mStoredBytes = r6     // Catch: java.lang.Throwable -> L58
            int r2 = r2 - r5
            int r4 = r4 + r5
            int r3 = r3 + r5
            goto L25
        L51:
            if (r0 == 0) goto L56
            r8.notify()     // Catch: java.lang.Throwable -> L58
        L56:
            monitor-exit(r8)
            return r3
        L58:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.termux.terminal.ByteQueue.read(byte[], boolean):int");
    }

    public boolean write(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        if (i2 + i > bArr.length) {
            throw new IllegalArgumentException("length + offset > buffer.length");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("length <= 0");
        }
        int length = this.mBuffer.length;
        synchronized (this) {
            while (true) {
                boolean z = true;
                if (i2 <= 0) {
                    return true;
                }
                while (true) {
                    i3 = this.mStoredBytes;
                    if (length != i3 || !this.mOpen) {
                        break;
                    }
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                    }
                }
                if (!this.mOpen) {
                    return false;
                }
                if (i3 != 0) {
                    z = false;
                }
                int min = Math.min(i2, length - i3);
                i2 -= min;
                while (min > 0) {
                    int i5 = this.mHead;
                    int i6 = this.mStoredBytes + i5;
                    if (i6 >= length) {
                        i6 -= length;
                        i4 = i5 - i6;
                    } else {
                        i4 = length - i6;
                    }
                    int min2 = Math.min(i4, min);
                    System.arraycopy(bArr, i, this.mBuffer, i6, min2);
                    i += min2;
                    min -= min2;
                    this.mStoredBytes += min2;
                }
                if (z) {
                    notify();
                }
            }
        }
    }
}
