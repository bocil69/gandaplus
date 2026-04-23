package com.topjohnwu.superuser.internal;

import androidx.annotation.NonNull;
import com.android.tools.r8.annotations.SynthesizedClassV2;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public interface DataInputImpl extends DataInput {
    int read() throws IOException;

    int read(byte[] bArr) throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;

    @Override // java.io.DataInput
    boolean readBoolean() throws IOException;

    @Override // java.io.DataInput
    byte readByte() throws IOException;

    @Override // java.io.DataInput
    char readChar() throws IOException;

    @Override // java.io.DataInput
    double readDouble() throws IOException;

    @Override // java.io.DataInput
    float readFloat() throws IOException;

    @Override // java.io.DataInput
    void readFully(@NonNull byte[] bArr) throws IOException;

    @Override // java.io.DataInput
    void readFully(byte[] bArr, int i, int i2) throws IOException;

    @Override // java.io.DataInput
    int readInt() throws IOException;

    @Override // java.io.DataInput
    String readLine() throws IOException;

    @Override // java.io.DataInput
    long readLong() throws IOException;

    @Override // java.io.DataInput
    short readShort() throws IOException;

    @Override // java.io.DataInput
    @NonNull
    String readUTF() throws IOException;

    @Override // java.io.DataInput
    int readUnsignedByte() throws IOException;

    @Override // java.io.DataInput
    int readUnsignedShort() throws IOException;

    @SynthesizedClassV2(kind = 7, versionHash = "79350b666c61fb98f585652cf8eb3be7850d2ab8c16c1e890d0171be2ca2d761")
    /* renamed from: com.topjohnwu.superuser.internal.DataInputImpl$-CC  reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC {
        public static int $default$read(DataInputImpl _this) throws IOException {
            byte[] bArr = new byte[1];
            if (_this.read(bArr) != 1) {
                return -1;
            }
            return bArr[0] & 255;
        }

        public static void $default$readFully(DataInputImpl _this, byte[] bArr, int i, int i2) throws IOException {
            if (_this.read(bArr, i, i2) != i2) {
                throw new EOFException();
            }
        }

        public static boolean $default$readBoolean(DataInputImpl _this) throws IOException {
            return _this.readByte() != 0;
        }

        public static byte $default$readByte(DataInputImpl _this) throws IOException {
            byte[] bArr = new byte[1];
            _this.readFully(bArr);
            return bArr[0];
        }

        public static int $default$readUnsignedByte(DataInputImpl _this) throws IOException {
            return _this.readByte() & 255;
        }

        public static short $default$readShort(DataInputImpl _this) throws IOException {
            byte[] bArr = new byte[2];
            _this.readFully(bArr);
            return (short) ((bArr[0] << 8) + (bArr[1] << 0));
        }

        public static int $default$readUnsignedShort(DataInputImpl _this) throws IOException {
            byte[] bArr = new byte[2];
            _this.readFully(bArr);
            return (bArr[0] << 8) + (bArr[1] << 0);
        }

        public static char $default$readChar(DataInputImpl _this) throws IOException {
            return (char) _this.readUnsignedShort();
        }

        public static int $default$readInt(DataInputImpl _this) throws IOException {
            byte[] bArr = new byte[4];
            _this.readFully(bArr);
            return (bArr[0] << 24) + (bArr[1] << 16) + (bArr[2] << 8) + (bArr[3] << 0);
        }

        public static long $default$readLong(DataInputImpl _this) throws IOException {
            byte[] bArr = new byte[8];
            _this.readFully(bArr);
            return (bArr[0] << 56) + ((bArr[1] & 255) << 48) + ((bArr[2] & 255) << 40) + ((bArr[3] & 255) << 32) + ((bArr[4] & 255) << 24) + ((bArr[5] & 255) << 16) + ((bArr[6] & 255) << 8) + ((bArr[7] & 255) << 0);
        }

        public static String $default$readLine(DataInputImpl _this) throws IOException {
            int readUnsignedByte;
            ByteOutputStream byteOutputStream = new ByteOutputStream();
            do {
                try {
                    readUnsignedByte = _this.readUnsignedByte();
                    byteOutputStream.write(readUnsignedByte);
                } catch (EOFException unused) {
                }
            } while (readUnsignedByte != 10);
            int size = byteOutputStream.size();
            if (size == 0) {
                return null;
            }
            byte[] buf = byteOutputStream.getBuf();
            if (buf[size - 1] == 10 && size - 1 > 0 && buf[size - 1] == 13) {
                size--;
            }
            return new String(buf, 0, size, StandardCharsets.UTF_8);
        }

        @NonNull
        public static String $default$readUTF(DataInputImpl _this) throws IOException {
            int readUnsignedShort = _this.readUnsignedShort();
            byte[] bArr = new byte[readUnsignedShort + 2];
            bArr[0] = (byte) (readUnsignedShort >>> 8);
            bArr[1] = (byte) (readUnsignedShort >>> 0);
            _this.readFully(bArr, 2, readUnsignedShort);
            return new DataInputStream(new ByteArrayInputStream(bArr)).readUTF();
        }
    }
}
