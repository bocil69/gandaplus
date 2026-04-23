package com.budiyev.android.codescanner;

import android.hardware.Camera;
import androidx.annotation.NonNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class DecoderWrapper {
    private final boolean mAutoFocusSupported;
    private final Camera mCamera;
    private final Camera.CameraInfo mCameraInfo;
    private final Decoder mDecoder;
    private final int mDisplayOrientation;
    private final boolean mFlashSupported;
    private final Point mImageSize;
    private final Point mPreviewSize;
    private final boolean mReverseHorizontal;
    private final Point mViewSize;

    public DecoderWrapper(@NonNull Camera camera, @NonNull Camera.CameraInfo cameraInfo, @NonNull Decoder decoder, @NonNull Point point, @NonNull Point point2, @NonNull Point point3, int i, boolean z, boolean z2) {
        this.mCamera = camera;
        this.mCameraInfo = cameraInfo;
        this.mDecoder = decoder;
        this.mImageSize = point;
        this.mPreviewSize = point2;
        this.mViewSize = point3;
        this.mDisplayOrientation = i;
        this.mReverseHorizontal = cameraInfo.facing == 1;
        this.mAutoFocusSupported = z;
        this.mFlashSupported = z2;
    }

    @NonNull
    public Camera getCamera() {
        return this.mCamera;
    }

    @NonNull
    public Camera.CameraInfo getCameraInfo() {
        return this.mCameraInfo;
    }

    @NonNull
    public Decoder getDecoder() {
        return this.mDecoder;
    }

    @NonNull
    public Point getImageSize() {
        return this.mImageSize;
    }

    @NonNull
    public Point getPreviewSize() {
        return this.mPreviewSize;
    }

    @NonNull
    public Point getViewSize() {
        return this.mViewSize;
    }

    public int getDisplayOrientation() {
        return this.mDisplayOrientation;
    }

    public boolean shouldReverseHorizontal() {
        return this.mReverseHorizontal;
    }

    public boolean isAutoFocusSupported() {
        return this.mAutoFocusSupported;
    }

    public boolean isFlashSupported() {
        return this.mFlashSupported;
    }

    public void release() {
        this.mCamera.release();
        this.mDecoder.shutdown();
    }
}
