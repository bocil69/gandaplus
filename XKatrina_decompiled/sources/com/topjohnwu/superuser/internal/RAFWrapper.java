package com.topjohnwu.superuser.internal;

import androidx.annotation.NonNull;
import com.topjohnwu.superuser.io.SuRandomAccessFile;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class RAFWrapper extends SuRandomAccessFile {
    private final RandomAccessFile raf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RAFWrapper(File file, String str) throws FileNotFoundException {
        this.raf = new RandomAccessFile(file, str);
    }

    @Override // com.topjohnwu.superuser.io.SuRandomAccessFile
    public int read() throws IOException {
        return this.raf.read();
    }

    @Override // com.topjohnwu.superuser.io.SuRandomAccessFile
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.raf.read(bArr, i, i2);
    }

    @Override // com.topjohnwu.superuser.io.SuRandomAccessFile
    public int read(byte[] bArr) throws IOException {
        return this.raf.read(bArr);
    }

    @Override // java.io.DataInput
    public int skipBytes(int i) throws IOException {
        return this.raf.skipBytes(i);
    }

    @Override // java.io.DataOutput
    public void write(int i) throws IOException {
        this.raf.write(i);
    }

    @Override // java.io.DataOutput
    public void write(byte[] bArr) throws IOException {
        this.raf.write(bArr);
    }

    @Override // java.io.DataOutput
    public void write(@NonNull byte[] bArr, int i, int i2) throws IOException {
        this.raf.write(bArr, i, i2);
    }

    @Override // com.topjohnwu.superuser.io.SuRandomAccessFile
    public void seek(long j) throws IOException {
        this.raf.seek(j);
    }

    @Override // com.topjohnwu.superuser.io.SuRandomAccessFile
    public void setLength(long j) throws IOException {
        this.raf.setLength(j);
    }

    @Override // com.topjohnwu.superuser.io.SuRandomAccessFile
    public long length() throws IOException {
        return this.raf.length();
    }

    @Override // com.topjohnwu.superuser.io.SuRandomAccessFile
    public long getFilePointer() throws IOException {
        return this.raf.getFilePointer();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.raf.close();
    }

    @Override // java.io.DataOutput
    public void writeBoolean(boolean z) throws IOException {
        this.raf.writeBoolean(z);
    }

    @Override // java.io.DataOutput
    public void writeByte(int i) throws IOException {
        this.raf.writeByte(i);
    }

    @Override // java.io.DataOutput
    public void writeShort(int i) throws IOException {
        this.raf.writeShort(i);
    }

    @Override // java.io.DataOutput
    public void writeChar(int i) throws IOException {
        this.raf.writeChar(i);
    }

    @Override // java.io.DataOutput
    public void writeInt(int i) throws IOException {
        this.raf.writeInt(i);
    }

    @Override // java.io.DataOutput
    public void writeLong(long j) throws IOException {
        this.raf.writeLong(j);
    }

    @Override // java.io.DataOutput
    public void writeFloat(float f) throws IOException {
        this.raf.writeFloat(f);
    }

    @Override // java.io.DataOutput
    public void writeDouble(double d) throws IOException {
        this.raf.writeDouble(d);
    }

    @Override // java.io.DataOutput
    public void writeBytes(@NonNull String str) throws IOException {
        this.raf.writeBytes(str);
    }

    @Override // java.io.DataOutput
    public void writeChars(@NonNull String str) throws IOException {
        this.raf.writeChars(str);
    }

    @Override // java.io.DataOutput
    public void writeUTF(@NonNull String str) throws IOException {
        this.raf.writeUTF(str);
    }

    @Override // java.io.DataInput
    public void readFully(@NonNull byte[] bArr) throws IOException {
        this.raf.readFully(bArr);
    }

    @Override // java.io.DataInput
    public void readFully(@NonNull byte[] bArr, int i, int i2) throws IOException {
        this.raf.readFully(bArr, i, i2);
    }

    @Override // java.io.DataInput
    public boolean readBoolean() throws IOException {
        return this.raf.readBoolean();
    }

    @Override // java.io.DataInput
    public byte readByte() throws IOException {
        return this.raf.readByte();
    }

    @Override // java.io.DataInput
    public int readUnsignedByte() throws IOException {
        return this.raf.readUnsignedByte();
    }

    @Override // java.io.DataInput
    public short readShort() throws IOException {
        return this.raf.readShort();
    }

    @Override // java.io.DataInput
    public int readUnsignedShort() throws IOException {
        return this.raf.readUnsignedShort();
    }

    @Override // java.io.DataInput
    public char readChar() throws IOException {
        return this.raf.readChar();
    }

    @Override // java.io.DataInput
    public int readInt() throws IOException {
        return this.raf.readInt();
    }

    @Override // java.io.DataInput
    public long readLong() throws IOException {
        return this.raf.readLong();
    }

    @Override // java.io.DataInput
    public float readFloat() throws IOException {
        return this.raf.readFloat();
    }

    @Override // java.io.DataInput
    public double readDouble() throws IOException {
        return this.raf.readDouble();
    }

    @Override // java.io.DataInput
    public String readLine() throws IOException {
        return this.raf.readLine();
    }

    @Override // java.io.DataInput
    public String readUTF() throws IOException {
        return this.raf.readUTF();
    }

    public FileDescriptor getFD() throws IOException {
        return this.raf.getFD();
    }

    public FileChannel getChannel() {
        return this.raf.getChannel();
    }
}
