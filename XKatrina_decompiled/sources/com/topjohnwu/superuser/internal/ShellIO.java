package com.topjohnwu.superuser.internal;

import androidx.annotation.NonNull;
import com.topjohnwu.superuser.Shell;
import com.topjohnwu.superuser.ShellUtils;
import com.topjohnwu.superuser.internal.DataInputImpl;
import com.topjohnwu.superuser.internal.DataOutputImpl;
import com.topjohnwu.superuser.io.SuFile;
import com.topjohnwu.superuser.io.SuRandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ShellIO extends SuRandomAccessFile implements DataInputImpl, DataOutputImpl {
    private static final String TAG = "SHELLIO";
    boolean eof;
    private final SuFile file;
    long fileOff;
    private boolean readOnly;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    protected String getConv() {
        return "conv=notrunc";
    }

    @Override // java.io.DataInput, com.topjohnwu.superuser.internal.DataInputImpl
    public /* synthetic */ boolean readBoolean() {
        return DataInputImpl.CC.$default$readBoolean(this);
    }

    @Override // java.io.DataInput, com.topjohnwu.superuser.internal.DataInputImpl
    public /* synthetic */ byte readByte() {
        return DataInputImpl.CC.$default$readByte(this);
    }

    @Override // java.io.DataInput, com.topjohnwu.superuser.internal.DataInputImpl
    public /* synthetic */ char readChar() {
        return DataInputImpl.CC.$default$readChar(this);
    }

    @Override // java.io.DataInput, com.topjohnwu.superuser.internal.DataInputImpl
    public /* synthetic */ double readDouble() {
        double longBitsToDouble;
        longBitsToDouble = Double.longBitsToDouble(readLong());
        return longBitsToDouble;
    }

    @Override // java.io.DataInput, com.topjohnwu.superuser.internal.DataInputImpl
    public /* synthetic */ float readFloat() {
        float intBitsToFloat;
        intBitsToFloat = Float.intBitsToFloat(readInt());
        return intBitsToFloat;
    }

    @Override // java.io.DataInput, com.topjohnwu.superuser.internal.DataInputImpl
    public /* synthetic */ void readFully(byte[] bArr) {
        readFully(bArr, 0, bArr.length);
    }

    @Override // java.io.DataInput, com.topjohnwu.superuser.internal.DataInputImpl
    public /* synthetic */ void readFully(byte[] bArr, int i, int i2) {
        DataInputImpl.CC.$default$readFully(this, bArr, i, i2);
    }

    @Override // java.io.DataInput, com.topjohnwu.superuser.internal.DataInputImpl
    public /* synthetic */ int readInt() {
        return DataInputImpl.CC.$default$readInt(this);
    }

    @Override // java.io.DataInput, com.topjohnwu.superuser.internal.DataInputImpl
    public /* synthetic */ long readLong() {
        return DataInputImpl.CC.$default$readLong(this);
    }

    @Override // java.io.DataInput, com.topjohnwu.superuser.internal.DataInputImpl
    public /* synthetic */ short readShort() {
        return DataInputImpl.CC.$default$readShort(this);
    }

    @Override // java.io.DataInput, com.topjohnwu.superuser.internal.DataInputImpl
    public /* synthetic */ String readUTF() {
        return DataInputImpl.CC.$default$readUTF(this);
    }

    @Override // java.io.DataInput, com.topjohnwu.superuser.internal.DataInputImpl
    public /* synthetic */ int readUnsignedByte() {
        return DataInputImpl.CC.$default$readUnsignedByte(this);
    }

    @Override // java.io.DataInput, com.topjohnwu.superuser.internal.DataInputImpl
    public /* synthetic */ int readUnsignedShort() {
        return DataInputImpl.CC.$default$readUnsignedShort(this);
    }

    @Override // java.io.DataOutput, com.topjohnwu.superuser.internal.DataOutputImpl
    public /* synthetic */ void write(int i) {
        write(new byte[]{(byte) (i & 255)});
    }

    @Override // java.io.DataOutput, com.topjohnwu.superuser.internal.DataOutputImpl
    public /* synthetic */ void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.DataOutput, com.topjohnwu.superuser.internal.DataOutputImpl
    public /* synthetic */ void writeBoolean(boolean z) {
        write(r1 ? 1 : 0);
    }

    @Override // java.io.DataOutput, com.topjohnwu.superuser.internal.DataOutputImpl
    public /* synthetic */ void writeByte(int i) {
        write(i);
    }

    @Override // java.io.DataOutput, com.topjohnwu.superuser.internal.DataOutputImpl
    public /* synthetic */ void writeBytes(String str) {
        DataOutputImpl.CC.$default$writeBytes(this, str);
    }

    @Override // java.io.DataOutput, com.topjohnwu.superuser.internal.DataOutputImpl
    public /* synthetic */ void writeChar(int i) {
        writeShort(i);
    }

    @Override // java.io.DataOutput, com.topjohnwu.superuser.internal.DataOutputImpl
    public /* synthetic */ void writeChars(String str) {
        DataOutputImpl.CC.$default$writeChars(this, str);
    }

    @Override // java.io.DataOutput, com.topjohnwu.superuser.internal.DataOutputImpl
    public /* synthetic */ void writeDouble(double d) {
        writeLong(Double.doubleToLongBits(d));
    }

    @Override // java.io.DataOutput, com.topjohnwu.superuser.internal.DataOutputImpl
    public /* synthetic */ void writeFloat(float f) {
        writeInt(Float.floatToIntBits(f));
    }

    @Override // java.io.DataOutput, com.topjohnwu.superuser.internal.DataOutputImpl
    public /* synthetic */ void writeInt(int i) {
        write(new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) (i >>> 0)});
    }

    @Override // java.io.DataOutput, com.topjohnwu.superuser.internal.DataOutputImpl
    public /* synthetic */ void writeLong(long j) {
        write(new byte[]{(byte) (j >>> 56), (byte) (j >>> 48), (byte) (j >>> 40), (byte) (j >>> 32), (byte) (j >>> 24), (byte) (j >>> 16), (byte) (j >>> 8), (byte) (j >>> 0)});
    }

    @Override // java.io.DataOutput, com.topjohnwu.superuser.internal.DataOutputImpl
    public /* synthetic */ void writeShort(int i) {
        write(new byte[]{(byte) (i >>> 8), (byte) (i >>> 0)});
    }

    @Override // java.io.DataOutput, com.topjohnwu.superuser.internal.DataOutputImpl
    public /* synthetic */ void writeUTF(String str) {
        DataOutputImpl.CC.$default$writeUTF(this, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ShellIO get(SuFile suFile, String str) throws FileNotFoundException {
        if (suFile.isBlock()) {
            return new ShellBlockIO(suFile, str);
        }
        return new ShellIO(suFile, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ShellIO(SuFile suFile, String str) throws FileNotFoundException {
        FileNotFoundException fileNotFoundException = new FileNotFoundException("No such file or directory");
        this.file = suFile;
        if (suFile.isDirectory()) {
            throw fileNotFoundException;
        }
        this.fileOff = 0L;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 114:
                if (str.equals("r")) {
                    c = 0;
                    break;
                }
                break;
            case 119:
                if (str.equals("w")) {
                    c = 1;
                    break;
                }
                break;
            case 3653:
                if (str.equals("rw")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (!suFile.exists()) {
                    throw fileNotFoundException;
                }
                this.readOnly = true;
                return;
            case 1:
                if (!suFile.clear()) {
                    throw fileNotFoundException;
                }
                return;
            case 2:
                if (!suFile.exists() && !suFile.createNewFile()) {
                    throw fileNotFoundException;
                }
                return;
            default:
                return;
        }
    }

    public void write(@NonNull byte[] bArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (this.readOnly) {
            throw new IOException("File is opened as read-only");
        }
        long j = this.fileOff;
        if (j > 0 && j < 512 && i2 > 512) {
            int i3 = 512 - ((int) j);
            write0(bArr, i, i3);
            i2 -= i3;
            i += i3;
        }
        write0(bArr, i, i2);
    }

    private void write0(@NonNull final byte[] bArr, final int i, final int i2) throws IOException {
        this.file.getShell().execTask(new Shell.Task() { // from class: com.topjohnwu.superuser.internal.ShellIO$$ExternalSyntheticLambda1
            @Override // com.topjohnwu.superuser.Shell.Task
            public final void run(OutputStream outputStream, InputStream inputStream, InputStream inputStream2) {
                ShellIO.this.m148lambda$write0$0$comtopjohnwusuperuserinternalShellIO(i2, bArr, i, outputStream, inputStream, inputStream2);
            }
        });
        this.fileOff += i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$write0$0$com-topjohnwu-superuser-internal-ShellIO  reason: not valid java name */
    public /* synthetic */ void m148lambda$write0$0$comtopjohnwusuperuserinternalShellIO(int i, byte[] bArr, int i2, OutputStream outputStream, InputStream inputStream, InputStream inputStream2) throws IOException {
        String format;
        if (this.fileOff == 0) {
            format = String.format(Locale.ROOT, "dd of=%s bs=%d count=1 %s 2>/dev/null; echo\n", this.file.getEscapedPath(), Integer.valueOf(i), getConv());
        } else {
            format = String.format(Locale.ROOT, "dd of=%s ibs=%d count=1 obs=%d seek=1 %s 2>/dev/null; echo\n", this.file.getEscapedPath(), Integer.valueOf(i), Long.valueOf(this.fileOff), getConv());
        }
        Utils.log(TAG, format);
        outputStream.write(format.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.write(bArr, i2, i);
        outputStream.flush();
        inputStream.read(IOFactory.JUNK);
    }

    @Override // com.topjohnwu.superuser.io.SuRandomAccessFile
    public int read() throws IOException {
        return DataInputImpl.CC.$default$read(this);
    }

    @Override // com.topjohnwu.superuser.io.SuRandomAccessFile
    public int read(byte[] bArr) throws IOException {
        int read;
        read = read(bArr, 0, bArr.length);
        return read;
    }

    @Override // com.topjohnwu.superuser.io.SuRandomAccessFile
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int alignedRead;
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        if (this.eof) {
            return -1;
        }
        long j = i2;
        int gcd = (int) ShellUtils.gcd(this.fileOff, j);
        if (gcd >= 512 || i2 < 512) {
            alignedRead = alignedRead(bArr, i, i2 / gcd, (int) (this.fileOff / gcd), gcd);
        } else {
            long j2 = this.fileOff;
            long j3 = 4096;
            long j4 = j2 / j3;
            int i3 = (int) (((((j2 + j) + j3) - 1) / j3) - j4);
            byte[] bArr2 = new byte[i3 * 4096];
            long j5 = j4 * j3;
            int alignedRead2 = alignedRead(bArr2, 0, i3, (int) j4, 4096);
            if (alignedRead2 > 0) {
                int i4 = (int) ((alignedRead2 + j5) - this.fileOff);
                if (i4 < i2) {
                    this.eof = true;
                }
                alignedRead = Math.min(i4, i2);
                System.arraycopy(bArr2, (int) (this.fileOff - j5), bArr, i, alignedRead);
            } else {
                alignedRead = i2;
            }
        }
        this.fileOff += alignedRead;
        if (alignedRead == 0) {
            return -1;
        }
        return alignedRead;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int alignedRead(final byte[] bArr, final int i, final int i2, final int i3, final int i4) throws IOException {
        if (this.eof) {
            return 0;
        }
        final int[] iArr = new int[1];
        final int i5 = i2 * i4;
        this.file.getShell().execTask(new Shell.Task() { // from class: com.topjohnwu.superuser.internal.ShellIO$$ExternalSyntheticLambda2
            @Override // com.topjohnwu.superuser.Shell.Task
            public final void run(OutputStream outputStream, InputStream inputStream, InputStream inputStream2) {
                ShellIO.this.m146lambda$alignedRead$1$comtopjohnwusuperuserinternalShellIO(i, i4, i3, i2, i5, iArr, bArr, outputStream, inputStream, inputStream2);
            }
        });
        int i6 = iArr[0];
        if (i6 == 0 || i6 != i5) {
            this.eof = true;
        }
        return i6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$alignedRead$1$com-topjohnwu-superuser-internal-ShellIO  reason: not valid java name */
    public /* synthetic */ void m146lambda$alignedRead$1$comtopjohnwusuperuserinternalShellIO(int i, int i2, int i3, int i4, int i5, int[] iArr, byte[] bArr, OutputStream outputStream, InputStream inputStream, InputStream inputStream2) throws IOException {
        String format = String.format(Locale.ROOT, "dd if=%s ibs=%d skip=%d count=%d obs=%d 2>/dev/null; echo >&2\n", this.file.getEscapedPath(), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
        Utils.log(TAG, format);
        outputStream.write(format.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        while (true) {
            if ((iArr[0] != i5 && inputStream2.available() == 0) || inputStream.available() != 0) {
                int read = inputStream.read(bArr, i, inputStream.available());
                i += read;
                iArr[0] = iArr[0] + read;
            } else {
                inputStream2.read(IOFactory.JUNK);
                return;
            }
        }
    }

    @Override // java.io.DataInput, com.topjohnwu.superuser.internal.DataInputImpl
    public String readLine() throws IOException {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        boolean z = false;
        do {
            long j = this.fileOff / 512;
            byte[] bArr = new byte[512];
            int alignedRead = alignedRead(bArr, 0, 1, (int) j, 512);
            if (alignedRead != 0) {
                int i = (int) (this.fileOff - (j * 512));
                while (true) {
                    if (i >= alignedRead) {
                        break;
                    }
                    byte b = bArr[i];
                    byteOutputStream.write(b);
                    if (b == 10) {
                        i++;
                        z = true;
                        break;
                    }
                    i++;
                }
                if (this.eof && i != alignedRead) {
                    this.eof = false;
                }
                if (this.eof) {
                    break;
                }
            } else {
                break;
            }
        } while (!z);
        int size = byteOutputStream.size();
        if (size == 0) {
            return null;
        }
        this.fileOff += size;
        byte[] buf = byteOutputStream.getBuf();
        if (buf[size - 1] == 10 && size - 1 > 0 && buf[size - 1] == 13) {
            size--;
        }
        return new String(buf, 0, size, StandardCharsets.UTF_8);
    }

    @Override // com.topjohnwu.superuser.io.SuRandomAccessFile
    public void seek(long j) throws IOException {
        this.fileOff = j;
        this.eof = false;
    }

    @Override // com.topjohnwu.superuser.io.SuRandomAccessFile
    public void setLength(final long j) throws IOException {
        if (j == 0) {
            if (!this.file.clear()) {
                throw new IOException("Cannot clear file");
            }
            return;
        }
        this.file.getShell().execTask(new Shell.Task() { // from class: com.topjohnwu.superuser.internal.ShellIO$$ExternalSyntheticLambda0
            @Override // com.topjohnwu.superuser.Shell.Task
            public final void run(OutputStream outputStream, InputStream inputStream, InputStream inputStream2) {
                ShellIO.this.m147lambda$setLength$2$comtopjohnwusuperuserinternalShellIO(j, outputStream, inputStream, inputStream2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setLength$2$com-topjohnwu-superuser-internal-ShellIO  reason: not valid java name */
    public /* synthetic */ void m147lambda$setLength$2$comtopjohnwusuperuserinternalShellIO(long j, OutputStream outputStream, InputStream inputStream, InputStream inputStream2) throws IOException {
        String format = String.format(Locale.ROOT, "dd of=%s bs=%d seek=1 count=0 2>/dev/null; echo\n", this.file.getEscapedPath(), Long.valueOf(j));
        Utils.log(TAG, format);
        outputStream.write(format.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        inputStream.read(IOFactory.JUNK);
    }

    @Override // com.topjohnwu.superuser.io.SuRandomAccessFile
    public long length() {
        return this.file.length();
    }

    @Override // com.topjohnwu.superuser.io.SuRandomAccessFile
    public long getFilePointer() {
        return this.fileOff;
    }

    @Override // java.io.DataInput
    public int skipBytes(int i) {
        if (i <= 0) {
            return 0;
        }
        long j = this.fileOff;
        long min = Math.min(length(), this.fileOff + i);
        this.fileOff = min;
        return (int) (min - j);
    }
}
