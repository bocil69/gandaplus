package com.budiyev.android.codescanner;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.Map;
/* loaded from: classes2.dex */
public final class BarcodeUtils {
    public static final int ROTATION_0 = 0;
    public static final int ROTATION_180 = 180;
    public static final int ROTATION_270 = 270;
    public static final int ROTATION_90 = 90;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Rotation {
    }

    private BarcodeUtils() {
    }

    @Nullable
    public static Result decodeBitmap(@NonNull Bitmap bitmap) {
        return decodeBitmap(bitmap, null);
    }

    @Nullable
    public static Result decodeBitmap(@NonNull Bitmap bitmap, @Nullable Map<DecodeHintType, ?> map) {
        bitmap.getClass();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        return decodeRgb(iArr, width, height, map);
    }

    @Nullable
    public static Result decodeRgb(@NonNull int[] iArr, int i, int i2) {
        return decodeRgb(iArr, i, i2, null);
    }

    @Nullable
    public static Result decodeRgb(@NonNull int[] iArr, int i, int i2, @Nullable Map<DecodeHintType, ?> map) {
        iArr.getClass();
        try {
            return Utils.decodeLuminanceSource(createReader(map), new RGBLuminanceSource(i, i2, iArr));
        } catch (ReaderException unused) {
            return null;
        }
    }

    @Nullable
    public static Result decodeYuv(@NonNull byte[] bArr, int i, int i2) {
        return decodeYuv(bArr, i, i2, 0, false, null);
    }

    @Nullable
    public static Result decodeYuv(@NonNull byte[] bArr, int i, int i2, int i3, boolean z, @Nullable Map<DecodeHintType, ?> map) {
        int i4;
        int i5;
        bArr.getClass();
        byte[] rotateYuv = Utils.rotateYuv(bArr, i, i2, i3);
        if (i3 == 90 || i3 == 270) {
            i4 = i;
            i5 = i2;
        } else {
            i5 = i;
            i4 = i2;
        }
        try {
            return Utils.decodeLuminanceSource(createReader(map), new PlanarYUVLuminanceSource(rotateYuv, i5, i4, 0, 0, i5, i4, z));
        } catch (ReaderException unused) {
            return null;
        }
    }

    @Nullable
    public static BitMatrix encodeBitMatrix(@NonNull String str, @NonNull BarcodeFormat barcodeFormat, int i, int i2) {
        return encodeBitMatrix(str, barcodeFormat, i, i2, null);
    }

    @Nullable
    public static BitMatrix encodeBitMatrix(@NonNull String str, @NonNull BarcodeFormat barcodeFormat, int i, int i2, @Nullable Map<EncodeHintType, ?> map) {
        str.getClass();
        barcodeFormat.getClass();
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            if (map != null) {
                return multiFormatWriter.encode(str, barcodeFormat, i, i2, map);
            }
            return multiFormatWriter.encode(str, barcodeFormat, i, i2);
        } catch (WriterException unused) {
            return null;
        }
    }

    @Nullable
    public static Bitmap encodeBitmap(@NonNull String str, @NonNull BarcodeFormat barcodeFormat, int i, int i2) {
        return encodeBitmap(str, barcodeFormat, i, i2, null);
    }

    @Nullable
    public static Bitmap encodeBitmap(@NonNull String str, @NonNull BarcodeFormat barcodeFormat, int i, int i2, @Nullable Map<EncodeHintType, ?> map) {
        BitMatrix encodeBitMatrix = encodeBitMatrix(str, barcodeFormat, i, i2, map);
        if (encodeBitMatrix != null) {
            return createBitmap(encodeBitMatrix);
        }
        return null;
    }

    @NonNull
    public static Bitmap createBitmap(@NonNull BitMatrix bitMatrix) {
        bitMatrix.getClass();
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = bitMatrix.get(i2 % width, i2 / height) ? ViewCompat.MEASURED_STATE_MASK : -1;
        }
        return Bitmap.createBitmap(iArr, width, height, Bitmap.Config.ARGB_8888);
    }

    @NonNull
    private static MultiFormatReader createReader(@Nullable Map<DecodeHintType, ?> map) {
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        if (map != null) {
            multiFormatReader.setHints(map);
        } else {
            multiFormatReader.setHints(Collections.singletonMap(DecodeHintType.POSSIBLE_FORMATS, CodeScanner.ALL_FORMATS));
        }
        return multiFormatReader;
    }
}
