package com.topjohnwu.superuser.internal;

import androidx.annotation.NonNull;
import com.android.tools.r8.annotations.SynthesizedClassV2;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public interface DataOutputImpl extends DataOutput {
    @Override // java.io.DataOutput
    void write(int i) throws IOException;

    @Override // java.io.DataOutput
    void write(@NonNull byte[] bArr) throws IOException;

    @Override // java.io.DataOutput
    void writeBoolean(boolean z) throws IOException;

    @Override // java.io.DataOutput
    void writeByte(int i) throws IOException;

    @Override // java.io.DataOutput
    void writeBytes(@NonNull String str) throws IOException;

    @Override // java.io.DataOutput
    void writeChar(int i) throws IOException;

    @Override // java.io.DataOutput
    void writeChars(@NonNull String str) throws IOException;

    @Override // java.io.DataOutput
    void writeDouble(double d) throws IOException;

    @Override // java.io.DataOutput
    void writeFloat(float f) throws IOException;

    @Override // java.io.DataOutput
    void writeInt(int i) throws IOException;

    @Override // java.io.DataOutput
    void writeLong(long j) throws IOException;

    @Override // java.io.DataOutput
    void writeShort(int i) throws IOException;

    @Override // java.io.DataOutput
    void writeUTF(@NonNull String str) throws IOException;

    @SynthesizedClassV2(kind = 7, versionHash = "79350b666c61fb98f585652cf8eb3be7850d2ab8c16c1e890d0171be2ca2d761")
    /* renamed from: com.topjohnwu.superuser.internal.DataOutputImpl$-CC  reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC {
        public static void $default$writeBytes(DataOutputImpl _this, @NonNull String str) throws IOException {
            ByteOutputStream byteOutputStream = new ByteOutputStream();
            new DataOutputStream(byteOutputStream).writeBytes(str);
            byteOutputStream.writeTo(_this);
        }

        public static void $default$writeChars(DataOutputImpl _this, @NonNull String str) throws IOException {
            ByteOutputStream byteOutputStream = new ByteOutputStream();
            new DataOutputStream(byteOutputStream).writeChars(str);
            byteOutputStream.writeTo(_this);
        }

        public static void $default$writeUTF(DataOutputImpl _this, @NonNull String str) throws IOException {
            ByteOutputStream byteOutputStream = new ByteOutputStream();
            new DataOutputStream(byteOutputStream).writeUTF(str);
            byteOutputStream.writeTo(_this);
        }
    }
}
