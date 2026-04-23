package com.budiyev.android.codescanner;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.os.Build;
import android.view.WindowManager;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class Utils {
    private static final float DISTORTION_STEP = 0.1f;
    private static final float MAX_DISTORTION = 3.0f;
    private static final int MAX_FPS = 30000;
    private static final float MIN_DISTORTION = 0.3f;
    private static final int MIN_FPS = 10000;
    private static final int MIN_PREVIEW_PIXELS = 589824;

    /* loaded from: classes2.dex */
    public static final class SuppressErrorCallback implements ErrorCallback {
        @Override // com.budiyev.android.codescanner.ErrorCallback
        public void onError(@NonNull Throwable th) {
        }
    }

    public static boolean isPortrait(int i) {
        return i == 90 || i == 270;
    }

    private Utils() {
    }

    @NonNull
    public static Point findSuitableImageSize(@NonNull Camera.Parameters parameters, int i, int i2) {
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes != null && !supportedPreviewSizes.isEmpty()) {
            Collections.sort(supportedPreviewSizes, new CameraSizeComparator());
            float f = i / i2;
            for (float f2 = MIN_DISTORTION; f2 <= MAX_DISTORTION; f2 += 0.1f) {
                for (Camera.Size size : supportedPreviewSizes) {
                    int i3 = size.width;
                    int i4 = size.height;
                    if (i3 * i4 >= MIN_PREVIEW_PIXELS && Math.abs(f - (i3 / i4)) <= f2) {
                        return new Point(i3, i4);
                    }
                }
            }
        }
        Camera.Size previewSize = parameters.getPreviewSize();
        if (previewSize == null) {
            throw new CodeScannerException("Unable to configure camera preview size");
        }
        return new Point(previewSize.width, previewSize.height);
    }

    public static void configureFpsRange(@NonNull Camera.Parameters parameters) {
        int i;
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        if (supportedPreviewFpsRange == null || supportedPreviewFpsRange.isEmpty()) {
            return;
        }
        Collections.sort(supportedPreviewFpsRange, new FpsRangeComparator());
        for (int[] iArr : supportedPreviewFpsRange) {
            int i2 = iArr[0];
            if (i2 >= MIN_FPS && (i = iArr[1]) <= MAX_FPS) {
                parameters.setPreviewFpsRange(i2, i);
                return;
            }
        }
    }

    public static void configureSceneMode(@NonNull Camera.Parameters parameters) {
        List<String> supportedSceneModes;
        if ("barcode".equals(parameters.getSceneMode()) || (supportedSceneModes = parameters.getSupportedSceneModes()) == null || !supportedSceneModes.contains("barcode")) {
            return;
        }
        parameters.setSceneMode("barcode");
    }

    public static void configureVideoStabilization(@NonNull Camera.Parameters parameters) {
        if (!parameters.isVideoStabilizationSupported() || parameters.getVideoStabilization()) {
            return;
        }
        parameters.setVideoStabilization(true);
    }

    public static void configureFocusArea(@NonNull Camera.Parameters parameters, @NonNull Rect rect, int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList(1);
        Rect bound = rect.rotate(-i3, i / 2.0f, i2 / 2.0f).bound(0, 0, i, i2);
        arrayList.add(new Camera.Area(new android.graphics.Rect(mapCoordinate(bound.getLeft(), i), mapCoordinate(bound.getTop(), i2), mapCoordinate(bound.getRight(), i), mapCoordinate(bound.getBottom(), i2)), 1000));
        if (parameters.getMaxNumFocusAreas() > 0) {
            parameters.setFocusAreas(arrayList);
        }
        if (parameters.getMaxNumMeteringAreas() > 0) {
            parameters.setMeteringAreas(arrayList);
        }
    }

    public static void configureDefaultFocusArea(@NonNull Camera.Parameters parameters, @NonNull Rect rect, @NonNull Point point, @NonNull Point point2, int i, int i2, int i3) {
        boolean isPortrait = isPortrait(i3);
        int i4 = isPortrait ? i2 : i;
        if (!isPortrait) {
            i = i2;
        }
        configureFocusArea(parameters, getImageFrameRect(i4, i, rect, point, point2), i4, i, i3);
    }

    public static void configureDefaultFocusArea(@NonNull Camera.Parameters parameters, @NonNull DecoderWrapper decoderWrapper, @NonNull Rect rect) {
        Point imageSize = decoderWrapper.getImageSize();
        configureDefaultFocusArea(parameters, rect, decoderWrapper.getPreviewSize(), decoderWrapper.getViewSize(), imageSize.getX(), imageSize.getY(), decoderWrapper.getDisplayOrientation());
    }

    public static void configureFocusModeForTouch(@NonNull Camera.Parameters parameters) {
        List<String> supportedFocusModes;
        if ("auto".equals(parameters.getFocusMode()) || (supportedFocusModes = parameters.getSupportedFocusModes()) == null || !supportedFocusModes.contains("auto")) {
            return;
        }
        parameters.setFocusMode("auto");
    }

    public static void disableAutoFocus(@NonNull Camera.Parameters parameters) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes == null || supportedFocusModes.isEmpty()) {
            return;
        }
        String focusMode = parameters.getFocusMode();
        if (supportedFocusModes.contains("fixed")) {
            if ("fixed".equals(focusMode)) {
                return;
            }
            parameters.setFocusMode("fixed");
        } else if (!supportedFocusModes.contains("auto") || "auto".equals(focusMode)) {
        } else {
            parameters.setFocusMode("auto");
        }
    }

    public static void setAutoFocusMode(@NonNull Camera.Parameters parameters, AutoFocusMode autoFocusMode) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes == null || supportedFocusModes.isEmpty()) {
            return;
        }
        if (autoFocusMode == AutoFocusMode.CONTINUOUS) {
            if ("continuous-picture".equals(parameters.getFocusMode())) {
                return;
            }
            if (supportedFocusModes.contains("continuous-picture")) {
                parameters.setFocusMode("continuous-picture");
                return;
            }
        }
        if (!"auto".equals(parameters.getFocusMode()) && supportedFocusModes.contains("auto")) {
            parameters.setFocusMode("auto");
        }
    }

    public static void setFlashMode(@NonNull Camera.Parameters parameters, @NonNull String str) {
        List<String> supportedFlashModes;
        if (str.equals(parameters.getFlashMode()) || (supportedFlashModes = parameters.getSupportedFlashModes()) == null || !supportedFlashModes.contains(str)) {
            return;
        }
        parameters.setFlashMode(str);
    }

    public static void setZoom(@NonNull Camera.Parameters parameters, int i) {
        if (!parameters.isZoomSupported() || parameters.getZoom() == i) {
            return;
        }
        parameters.setZoom(Math.min(i, parameters.getMaxZoom()));
    }

    public static int getDisplayOrientation(@NonNull Context context, @NonNull Camera.CameraInfo cameraInfo) {
        int i;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            throw new CodeScannerException("Unable to access window manager");
        }
        int rotation = windowManager.getDefaultDisplay().getRotation();
        int i2 = BarcodeUtils.ROTATION_180;
        if (rotation == 0) {
            i = 0;
        } else if (rotation == 1) {
            i = 90;
        } else if (rotation == 2) {
            i = BarcodeUtils.ROTATION_180;
        } else if (rotation == 3) {
            i = BarcodeUtils.ROTATION_270;
        } else if (rotation % 90 == 0) {
            i = (rotation + 360) % 360;
        } else {
            throw new CodeScannerException("Invalid display rotation");
        }
        if (cameraInfo.facing != 1) {
            i2 = 360;
        }
        return ((i2 + cameraInfo.orientation) - i) % 360;
    }

    @NonNull
    public static Point getPreviewSize(int i, int i2, int i3, int i4) {
        if (i == i3 && i2 == i4) {
            return new Point(i3, i4);
        }
        int i5 = (i * i4) / i2;
        if (i5 < i3) {
            return new Point(i3, (i2 * i3) / i);
        }
        return new Point(i5, i4);
    }

    @NonNull
    public static Rect getImageFrameRect(int i, int i2, @NonNull Rect rect, @NonNull Point point, @NonNull Point point2) {
        int x = point.getX();
        int y = point.getY();
        int x2 = (x - point2.getX()) / 2;
        int y2 = (y - point2.getY()) / 2;
        float f = i / x;
        float f2 = i2 / y;
        return new Rect(Math.max(Math.round((rect.getLeft() + x2) * f), 0), Math.max(Math.round((rect.getTop() + y2) * f2), 0), Math.min(Math.round((rect.getRight() + x2) * f), i), Math.min(Math.round((rect.getBottom() + y2) * f2), i2));
    }

    @NonNull
    public static byte[] rotateYuv(@NonNull byte[] bArr, int i, int i2, int i3) {
        if (i3 == 0 || i3 == 360) {
            return bArr;
        }
        if (i3 % 90 != 0 || i3 < 0 || i3 > 270) {
            throw new IllegalArgumentException("Invalid rotation (valid: 0, 90, 180, 270)");
        }
        byte[] bArr2 = new byte[bArr.length];
        int i4 = i * i2;
        boolean z = i3 % BarcodeUtils.ROTATION_180 != 0;
        boolean z2 = i3 % BarcodeUtils.ROTATION_270 != 0;
        boolean z3 = i3 >= 180;
        for (int i5 = 0; i5 < i2; i5++) {
            for (int i6 = 0; i6 < i; i6++) {
                int i7 = (i5 * i) + i6;
                int i8 = ((i5 >> 1) * i) + i4 + (i6 & (-2));
                int i9 = i8 + 1;
                int i10 = z ? i2 : i;
                int i11 = z ? i : i2;
                int i12 = z ? i5 : i6;
                int i13 = z ? i6 : i5;
                if (z2) {
                    i12 = (i10 - i12) - 1;
                }
                if (z3) {
                    i13 = (i11 - i13) - 1;
                }
                int i14 = i4 + ((i13 >> 1) * i10) + (i12 & (-2));
                bArr2[(i13 * i10) + i12] = (byte) (bArr[i7] & 255);
                bArr2[i14] = (byte) (bArr[i8] & 255);
                bArr2[i14 + 1] = (byte) (bArr[i9] & 255);
            }
        }
        return bArr2;
    }

    @Nullable
    public static Result decodeLuminanceSource(@NonNull MultiFormatReader multiFormatReader, @NonNull LuminanceSource luminanceSource) throws ReaderException {
        try {
            return multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(luminanceSource)));
        } catch (NotFoundException unused) {
            return multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(luminanceSource.invert())));
        } finally {
            multiFormatReader.reset();
        }
    }

    @NonNull
    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getDrawable(i);
        }
        return context.getResources().getDrawable(i);
    }

    private static int mapCoordinate(int i, int i2) {
        return ((i * 2000) / i2) + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
    }

    /* loaded from: classes2.dex */
    private static final class CameraSizeComparator implements Comparator<Camera.Size> {
        private CameraSizeComparator() {
        }

        @Override // java.util.Comparator
        public int compare(@NonNull Camera.Size size, @NonNull Camera.Size size2) {
            return Utils$CameraSizeComparator$$ExternalSyntheticBackport0.m(size2.height * size2.width, size.height * size.width);
        }
    }

    /* loaded from: classes2.dex */
    private static final class FpsRangeComparator implements Comparator<int[]> {
        private FpsRangeComparator() {
        }

        @Override // java.util.Comparator
        public int compare(int[] iArr, int[] iArr2) {
            int m = Utils$FpsRangeComparator$$ExternalSyntheticBackport0.m(iArr2[1], iArr[1]);
            return m == 0 ? Utils$FpsRangeComparator$$ExternalSyntheticBackport0.m(iArr2[0], iArr[0]) : m;
        }
    }
}
