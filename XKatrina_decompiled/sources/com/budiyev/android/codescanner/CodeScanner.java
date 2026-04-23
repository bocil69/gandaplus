package com.budiyev.android.codescanner;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Process;
import android.view.SurfaceHolder;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.Decoder;
import com.google.zxing.BarcodeFormat;
import java.lang.Thread;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
public final class CodeScanner {
    public static final List<BarcodeFormat> ALL_FORMATS;
    public static final int CAMERA_BACK = -1;
    public static final int CAMERA_FRONT = -2;
    private static final boolean DEFAULT_AUTO_FOCUS_ENABLED = true;
    private static final AutoFocusMode DEFAULT_AUTO_FOCUS_MODE;
    private static final boolean DEFAULT_FLASH_ENABLED = false;
    private static final List<BarcodeFormat> DEFAULT_FORMATS;
    private static final long DEFAULT_SAFE_AUTO_FOCUS_INTERVAL = 2000;
    private static final ScanMode DEFAULT_SCAN_MODE;
    private static final boolean DEFAULT_TOUCH_FOCUS_ENABLED = true;
    public static final List<BarcodeFormat> ONE_DIMENSIONAL_FORMATS;
    private static final int SAFE_AUTO_FOCUS_ATTEMPTS_THRESHOLD = 2;
    public static final List<BarcodeFormat> TWO_DIMENSIONAL_FORMATS;
    private volatile boolean mAutoFocusEnabled;
    private volatile AutoFocusMode mAutoFocusMode;
    private volatile int mCameraId;
    private final Context mContext;
    private volatile DecodeCallback mDecodeCallback;
    private final DecoderStateListener mDecoderStateListener;
    private volatile DecoderWrapper mDecoderWrapper;
    private volatile ErrorCallback mErrorCallback;
    private final ExceptionHandler mExceptionHandler;
    private volatile boolean mFlashEnabled;
    private volatile List<BarcodeFormat> mFormats;
    private volatile boolean mInitialization;
    private boolean mInitializationRequested;
    private final Object mInitializeLock;
    private volatile boolean mInitialized;
    private final Handler mMainThreadHandler;
    private boolean mPreviewActive;
    private final Camera.PreviewCallback mPreviewCallback;
    private int mSafeAutoFocusAttemptsCount;
    private final Camera.AutoFocusCallback mSafeAutoFocusCallback;
    private volatile long mSafeAutoFocusInterval;
    private final Runnable mSafeAutoFocusTask;
    private boolean mSafeAutoFocusTaskScheduled;
    private boolean mSafeAutoFocusing;
    private volatile ScanMode mScanMode;
    private final CodeScannerView mScannerView;
    private final Runnable mStopPreviewTask;
    private volatile boolean mStoppingPreview;
    private final SurfaceHolder.Callback mSurfaceCallback;
    private final SurfaceHolder mSurfaceHolder;
    private final Camera.AutoFocusCallback mTouchFocusCallback;
    private boolean mTouchFocusEnabled;
    private boolean mTouchFocusing;
    private int mViewHeight;
    private int mViewWidth;
    private volatile int mZoom;

    static {
        List<BarcodeFormat> unmodifiableList = Collections.unmodifiableList(Arrays.asList(BarcodeFormat.values()));
        ALL_FORMATS = unmodifiableList;
        ONE_DIMENSIONAL_FORMATS = Collections.unmodifiableList(Arrays.asList(BarcodeFormat.CODABAR, BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.EAN_8, BarcodeFormat.EAN_13, BarcodeFormat.ITF, BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED, BarcodeFormat.UPC_A, BarcodeFormat.UPC_E, BarcodeFormat.UPC_EAN_EXTENSION));
        TWO_DIMENSIONAL_FORMATS = Collections.unmodifiableList(Arrays.asList(BarcodeFormat.AZTEC, BarcodeFormat.DATA_MATRIX, BarcodeFormat.MAXICODE, BarcodeFormat.PDF_417, BarcodeFormat.QR_CODE));
        DEFAULT_FORMATS = unmodifiableList;
        DEFAULT_SCAN_MODE = ScanMode.SINGLE;
        DEFAULT_AUTO_FOCUS_MODE = AutoFocusMode.SAFE;
    }

    @MainThread
    public CodeScanner(@NonNull Context context, @NonNull CodeScannerView codeScannerView) {
        this.mInitializeLock = new Object();
        this.mFormats = DEFAULT_FORMATS;
        this.mScanMode = DEFAULT_SCAN_MODE;
        this.mAutoFocusMode = DEFAULT_AUTO_FOCUS_MODE;
        this.mDecodeCallback = null;
        this.mErrorCallback = null;
        this.mDecoderWrapper = null;
        this.mInitialization = false;
        this.mInitialized = false;
        this.mStoppingPreview = false;
        this.mAutoFocusEnabled = true;
        this.mFlashEnabled = false;
        this.mSafeAutoFocusInterval = DEFAULT_SAFE_AUTO_FOCUS_INTERVAL;
        this.mCameraId = -1;
        this.mZoom = 0;
        this.mTouchFocusEnabled = true;
        this.mTouchFocusing = false;
        this.mPreviewActive = false;
        this.mSafeAutoFocusing = false;
        this.mSafeAutoFocusTaskScheduled = false;
        this.mInitializationRequested = false;
        this.mSafeAutoFocusAttemptsCount = 0;
        this.mViewWidth = 0;
        this.mViewHeight = 0;
        this.mContext = context;
        this.mScannerView = codeScannerView;
        this.mSurfaceHolder = codeScannerView.getPreviewView().getHolder();
        this.mMainThreadHandler = new Handler();
        this.mSurfaceCallback = new SurfaceCallback();
        this.mPreviewCallback = new PreviewCallback();
        this.mTouchFocusCallback = new TouchFocusCallback();
        this.mSafeAutoFocusCallback = new SafeAutoFocusCallback();
        this.mSafeAutoFocusTask = new SafeAutoFocusTask();
        this.mStopPreviewTask = new StopPreviewTask();
        this.mDecoderStateListener = new DecoderStateListener();
        this.mExceptionHandler = new ExceptionHandler();
        codeScannerView.setCodeScanner(this);
        codeScannerView.setSizeListener(new ScannerSizeListener());
    }

    @MainThread
    public CodeScanner(@NonNull Context context, @NonNull CodeScannerView codeScannerView, int i) {
        this(context, codeScannerView);
        this.mCameraId = i;
    }

    public int getCamera() {
        return this.mCameraId;
    }

    @MainThread
    public void setCamera(int i) {
        synchronized (this.mInitializeLock) {
            if (this.mCameraId != i) {
                this.mCameraId = i;
                if (this.mInitialized) {
                    boolean z = this.mPreviewActive;
                    releaseResources();
                    if (z) {
                        initialize();
                    }
                }
            }
        }
    }

    @NonNull
    public List<BarcodeFormat> getFormats() {
        return this.mFormats;
    }

    @MainThread
    public void setFormats(@NonNull List<BarcodeFormat> list) {
        DecoderWrapper decoderWrapper;
        synchronized (this.mInitializeLock) {
            list.getClass();
            List<BarcodeFormat> list2 = list;
            this.mFormats = list;
            if (this.mInitialized && (decoderWrapper = this.mDecoderWrapper) != null) {
                decoderWrapper.getDecoder().setFormats(list);
            }
        }
    }

    @Nullable
    public DecodeCallback getDecodeCallback() {
        return this.mDecodeCallback;
    }

    public void setDecodeCallback(@Nullable DecodeCallback decodeCallback) {
        DecoderWrapper decoderWrapper;
        synchronized (this.mInitializeLock) {
            this.mDecodeCallback = decodeCallback;
            if (this.mInitialized && (decoderWrapper = this.mDecoderWrapper) != null) {
                decoderWrapper.getDecoder().setCallback(decodeCallback);
            }
        }
    }

    @Nullable
    public ErrorCallback getErrorCallback() {
        return this.mErrorCallback;
    }

    public void setErrorCallback(@Nullable ErrorCallback errorCallback) {
        this.mErrorCallback = errorCallback;
    }

    @NonNull
    public ScanMode getScanMode() {
        return this.mScanMode;
    }

    public void setScanMode(@NonNull ScanMode scanMode) {
        scanMode.getClass();
        this.mScanMode = scanMode;
    }

    public int getZoom() {
        return this.mZoom;
    }

    public void setZoom(int i) {
        DecoderWrapper decoderWrapper;
        if (i < 0) {
            throw new IllegalArgumentException("Zoom value must be greater than or equal to zero");
        }
        synchronized (this.mInitializeLock) {
            if (i != this.mZoom) {
                this.mZoom = i;
                if (this.mInitialized && (decoderWrapper = this.mDecoderWrapper) != null) {
                    Camera camera = decoderWrapper.getCamera();
                    Camera.Parameters parameters = camera.getParameters();
                    Utils.setZoom(parameters, i);
                    camera.setParameters(parameters);
                }
            }
        }
        this.mZoom = i;
    }

    public boolean isTouchFocusEnabled() {
        return this.mTouchFocusEnabled;
    }

    public void setTouchFocusEnabled(boolean z) {
        this.mTouchFocusEnabled = z;
    }

    public boolean isAutoFocusEnabled() {
        return this.mAutoFocusEnabled;
    }

    @MainThread
    public void setAutoFocusEnabled(boolean z) {
        synchronized (this.mInitializeLock) {
            boolean z2 = this.mAutoFocusEnabled != z;
            this.mAutoFocusEnabled = z;
            this.mScannerView.setAutoFocusEnabled(z);
            DecoderWrapper decoderWrapper = this.mDecoderWrapper;
            if (this.mInitialized && this.mPreviewActive && z2 && decoderWrapper != null && decoderWrapper.isAutoFocusSupported()) {
                setAutoFocusEnabledInternal(z);
            }
        }
    }

    @NonNull
    public AutoFocusMode getAutoFocusMode() {
        return this.mAutoFocusMode;
    }

    @MainThread
    public void setAutoFocusMode(@NonNull AutoFocusMode autoFocusMode) {
        synchronized (this.mInitializeLock) {
            autoFocusMode.getClass();
            AutoFocusMode autoFocusMode2 = autoFocusMode;
            this.mAutoFocusMode = autoFocusMode;
            if (this.mInitialized && this.mAutoFocusEnabled) {
                setAutoFocusEnabledInternal(true);
            }
        }
    }

    public void setAutoFocusInterval(long j) {
        this.mSafeAutoFocusInterval = j;
    }

    public boolean isFlashEnabled() {
        return this.mFlashEnabled;
    }

    @MainThread
    public void setFlashEnabled(boolean z) {
        synchronized (this.mInitializeLock) {
            boolean z2 = this.mFlashEnabled != z;
            this.mFlashEnabled = z;
            this.mScannerView.setFlashEnabled(z);
            DecoderWrapper decoderWrapper = this.mDecoderWrapper;
            if (this.mInitialized && this.mPreviewActive && z2 && decoderWrapper != null && decoderWrapper.isFlashSupported()) {
                setFlashEnabledInternal(z);
            }
        }
    }

    public boolean isPreviewActive() {
        return this.mPreviewActive;
    }

    @MainThread
    public void startPreview() {
        synchronized (this.mInitializeLock) {
            if (!this.mInitialized && !this.mInitialization) {
                initialize();
            } else if (this.mPreviewActive) {
            } else {
                this.mSurfaceHolder.addCallback(this.mSurfaceCallback);
                startPreviewInternal(false);
            }
        }
    }

    @MainThread
    public void stopPreview() {
        if (this.mInitialized && this.mPreviewActive) {
            this.mSurfaceHolder.removeCallback(this.mSurfaceCallback);
            stopPreviewInternal(false);
        }
    }

    @MainThread
    public void releaseResources() {
        if (this.mInitialized) {
            if (this.mPreviewActive) {
                stopPreview();
            }
            releaseResourcesInternal();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void performTouchFocus(Rect rect) {
        synchronized (this.mInitializeLock) {
            if (this.mInitialized && this.mPreviewActive && !this.mTouchFocusing) {
                try {
                    setAutoFocusEnabled(false);
                    DecoderWrapper decoderWrapper = this.mDecoderWrapper;
                    if (this.mPreviewActive && decoderWrapper != null && decoderWrapper.isAutoFocusSupported()) {
                        Point imageSize = decoderWrapper.getImageSize();
                        int x = imageSize.getX();
                        int y = imageSize.getY();
                        int displayOrientation = decoderWrapper.getDisplayOrientation();
                        if (displayOrientation == 90 || displayOrientation == 270) {
                            x = y;
                            y = x;
                        }
                        Rect imageFrameRect = Utils.getImageFrameRect(x, y, rect, decoderWrapper.getPreviewSize(), decoderWrapper.getViewSize());
                        Camera camera = decoderWrapper.getCamera();
                        camera.cancelAutoFocus();
                        Camera.Parameters parameters = camera.getParameters();
                        Utils.configureFocusArea(parameters, imageFrameRect, x, y, displayOrientation);
                        Utils.configureFocusModeForTouch(parameters);
                        camera.setParameters(parameters);
                        camera.autoFocus(this.mTouchFocusCallback);
                        this.mTouchFocusing = true;
                    }
                } catch (Exception unused) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAutoFocusSupportedOrUnknown() {
        DecoderWrapper decoderWrapper = this.mDecoderWrapper;
        return decoderWrapper == null || decoderWrapper.isAutoFocusSupported();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFlashSupportedOrUnknown() {
        DecoderWrapper decoderWrapper = this.mDecoderWrapper;
        return decoderWrapper == null || decoderWrapper.isFlashSupported();
    }

    private void initialize() {
        initialize(this.mScannerView.getWidth(), this.mScannerView.getHeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initialize(int i, int i2) {
        this.mViewWidth = i;
        this.mViewHeight = i2;
        if (i > 0 && i2 > 0) {
            this.mInitialization = true;
            this.mInitializationRequested = false;
            InitializationThread initializationThread = new InitializationThread(i, i2);
            initializationThread.setUncaughtExceptionHandler(this.mExceptionHandler);
            initializationThread.start();
            return;
        }
        this.mInitializationRequested = true;
    }

    private void startPreviewInternal(boolean z) {
        try {
            DecoderWrapper decoderWrapper = this.mDecoderWrapper;
            if (decoderWrapper != null) {
                Camera camera = decoderWrapper.getCamera();
                camera.setPreviewCallback(this.mPreviewCallback);
                camera.setPreviewDisplay(this.mSurfaceHolder);
                if (!z && decoderWrapper.isFlashSupported() && this.mFlashEnabled) {
                    setFlashEnabledInternal(true);
                }
                camera.startPreview();
                this.mStoppingPreview = false;
                this.mPreviewActive = true;
                this.mSafeAutoFocusing = false;
                this.mSafeAutoFocusAttemptsCount = 0;
                if (decoderWrapper.isAutoFocusSupported() && this.mAutoFocusEnabled) {
                    Rect frameRect = this.mScannerView.getFrameRect();
                    if (frameRect != null) {
                        Camera.Parameters parameters = camera.getParameters();
                        Utils.configureDefaultFocusArea(parameters, decoderWrapper, frameRect);
                        camera.setParameters(parameters);
                    }
                    if (this.mAutoFocusMode == AutoFocusMode.SAFE) {
                        scheduleSafeAutoFocusTask();
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPreviewInternalSafe() {
        if (!this.mInitialized || this.mPreviewActive) {
            return;
        }
        startPreviewInternal(true);
    }

    private void stopPreviewInternal(boolean z) {
        try {
            DecoderWrapper decoderWrapper = this.mDecoderWrapper;
            if (decoderWrapper != null) {
                Camera camera = decoderWrapper.getCamera();
                camera.cancelAutoFocus();
                Camera.Parameters parameters = camera.getParameters();
                if (!z && decoderWrapper.isFlashSupported() && this.mFlashEnabled) {
                    Utils.setFlashMode(parameters, "off");
                }
                camera.setParameters(parameters);
                camera.setPreviewCallback(null);
                camera.stopPreview();
            }
        } catch (Exception unused) {
        }
        this.mStoppingPreview = false;
        this.mPreviewActive = false;
        this.mSafeAutoFocusing = false;
        this.mSafeAutoFocusAttemptsCount = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopPreviewInternalSafe() {
        if (this.mInitialized && this.mPreviewActive) {
            stopPreviewInternal(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseResourcesInternal() {
        this.mInitialized = false;
        this.mInitialization = false;
        this.mStoppingPreview = false;
        this.mPreviewActive = false;
        this.mSafeAutoFocusing = false;
        DecoderWrapper decoderWrapper = this.mDecoderWrapper;
        if (decoderWrapper != null) {
            this.mDecoderWrapper = null;
            decoderWrapper.release();
        }
    }

    private void setFlashEnabledInternal(boolean z) {
        Camera camera;
        Camera.Parameters parameters;
        try {
            DecoderWrapper decoderWrapper = this.mDecoderWrapper;
            if (decoderWrapper == null || (parameters = (camera = decoderWrapper.getCamera()).getParameters()) == null) {
                return;
            }
            if (z) {
                Utils.setFlashMode(parameters, "torch");
            } else {
                Utils.setFlashMode(parameters, "off");
            }
            camera.setParameters(parameters);
        } catch (Exception unused) {
        }
    }

    private void setAutoFocusEnabledInternal(boolean z) {
        Rect frameRect;
        try {
            DecoderWrapper decoderWrapper = this.mDecoderWrapper;
            if (decoderWrapper != null) {
                Camera camera = decoderWrapper.getCamera();
                camera.cancelAutoFocus();
                this.mTouchFocusing = false;
                Camera.Parameters parameters = camera.getParameters();
                AutoFocusMode autoFocusMode = this.mAutoFocusMode;
                if (z) {
                    Utils.setAutoFocusMode(parameters, autoFocusMode);
                } else {
                    Utils.disableAutoFocus(parameters);
                }
                if (z && (frameRect = this.mScannerView.getFrameRect()) != null) {
                    Utils.configureDefaultFocusArea(parameters, decoderWrapper, frameRect);
                }
                camera.setParameters(parameters);
                if (z) {
                    this.mSafeAutoFocusAttemptsCount = 0;
                    this.mSafeAutoFocusing = false;
                    if (autoFocusMode == AutoFocusMode.SAFE) {
                        scheduleSafeAutoFocusTask();
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safeAutoFocusCamera() {
        DecoderWrapper decoderWrapper;
        int i;
        if (this.mInitialized && this.mPreviewActive && (decoderWrapper = this.mDecoderWrapper) != null && decoderWrapper.isAutoFocusSupported() && this.mAutoFocusEnabled) {
            if (this.mSafeAutoFocusing && (i = this.mSafeAutoFocusAttemptsCount) < 2) {
                this.mSafeAutoFocusAttemptsCount = i + 1;
            } else {
                try {
                    Camera camera = decoderWrapper.getCamera();
                    camera.cancelAutoFocus();
                    camera.autoFocus(this.mSafeAutoFocusCallback);
                    this.mSafeAutoFocusAttemptsCount = 0;
                    this.mSafeAutoFocusing = true;
                } catch (Exception unused) {
                    this.mSafeAutoFocusing = false;
                }
            }
            scheduleSafeAutoFocusTask();
        }
    }

    private void scheduleSafeAutoFocusTask() {
        if (this.mSafeAutoFocusTaskScheduled) {
            return;
        }
        this.mSafeAutoFocusTaskScheduled = true;
        this.mMainThreadHandler.postDelayed(this.mSafeAutoFocusTask, this.mSafeAutoFocusInterval);
    }

    /* loaded from: classes2.dex */
    private final class ScannerSizeListener implements CodeScannerView.SizeListener {
        private ScannerSizeListener() {
        }

        @Override // com.budiyev.android.codescanner.CodeScannerView.SizeListener
        public void onSizeChanged(int i, int i2) {
            synchronized (CodeScanner.this.mInitializeLock) {
                if (i != CodeScanner.this.mViewWidth || i2 != CodeScanner.this.mViewHeight) {
                    boolean z = CodeScanner.this.mPreviewActive;
                    if (CodeScanner.this.mInitialized) {
                        CodeScanner.this.releaseResources();
                    }
                    if (z || CodeScanner.this.mInitializationRequested) {
                        CodeScanner.this.initialize(i, i2);
                    }
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    private final class PreviewCallback implements Camera.PreviewCallback {
        private PreviewCallback() {
        }

        @Override // android.hardware.Camera.PreviewCallback
        public void onPreviewFrame(byte[] bArr, Camera camera) {
            DecoderWrapper decoderWrapper;
            Rect frameRect;
            if (!CodeScanner.this.mInitialized || CodeScanner.this.mStoppingPreview || CodeScanner.this.mScanMode == ScanMode.PREVIEW || bArr == null || (decoderWrapper = CodeScanner.this.mDecoderWrapper) == null) {
                return;
            }
            Decoder decoder = decoderWrapper.getDecoder();
            if (decoder.getState() == Decoder.State.IDLE && (frameRect = CodeScanner.this.mScannerView.getFrameRect()) != null && frameRect.getWidth() >= 1 && frameRect.getHeight() >= 1) {
                decoder.decode(new DecodeTask(bArr, decoderWrapper.getImageSize(), decoderWrapper.getPreviewSize(), decoderWrapper.getViewSize(), frameRect, decoderWrapper.getDisplayOrientation(), decoderWrapper.shouldReverseHorizontal()));
            }
        }
    }

    /* loaded from: classes2.dex */
    private final class SurfaceCallback implements SurfaceHolder.Callback {
        private SurfaceCallback() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            CodeScanner.this.startPreviewInternalSafe();
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (surfaceHolder.getSurface() == null) {
                CodeScanner.this.mPreviewActive = false;
                return;
            }
            CodeScanner.this.stopPreviewInternalSafe();
            CodeScanner.this.startPreviewInternalSafe();
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            CodeScanner.this.stopPreviewInternalSafe();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class DecoderStateListener implements Decoder.StateListener {
        private DecoderStateListener() {
        }

        @Override // com.budiyev.android.codescanner.Decoder.StateListener
        public boolean onStateChanged(@NonNull Decoder.State state) {
            if (state == Decoder.State.DECODED) {
                ScanMode scanMode = CodeScanner.this.mScanMode;
                if (scanMode == ScanMode.PREVIEW) {
                    return false;
                }
                if (scanMode == ScanMode.SINGLE) {
                    CodeScanner.this.mStoppingPreview = true;
                    CodeScanner.this.mMainThreadHandler.post(CodeScanner.this.mStopPreviewTask);
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class InitializationThread extends Thread {
        private final int mHeight;
        private final int mWidth;

        public InitializationThread(int i, int i2) {
            super("cs-init");
            this.mWidth = i;
            this.mHeight = i2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            initialize();
        }

        /* JADX WARN: Removed duplicated region for block: B:62:0x00f4  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x0101  */
        /* JADX WARN: Removed duplicated region for block: B:80:0x011a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void initialize() {
            /*
                Method dump skipped, instructions count: 391
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.budiyev.android.codescanner.CodeScanner.InitializationThread.initialize():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        private ExceptionHandler() {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(@NonNull Thread thread, @NonNull Throwable th) {
            CodeScanner.this.releaseResourcesInternal();
            ErrorCallback errorCallback = CodeScanner.this.mErrorCallback;
            if (errorCallback != null) {
                errorCallback.onError(th);
                return;
            }
            throw new CodeScannerException(th);
        }
    }

    /* loaded from: classes2.dex */
    private final class TouchFocusCallback implements Camera.AutoFocusCallback {
        private TouchFocusCallback() {
        }

        @Override // android.hardware.Camera.AutoFocusCallback
        public void onAutoFocus(boolean z, @NonNull Camera camera) {
            CodeScanner.this.mTouchFocusing = false;
        }
    }

    /* loaded from: classes2.dex */
    private final class SafeAutoFocusCallback implements Camera.AutoFocusCallback {
        private SafeAutoFocusCallback() {
        }

        @Override // android.hardware.Camera.AutoFocusCallback
        public void onAutoFocus(boolean z, @NonNull Camera camera) {
            CodeScanner.this.mSafeAutoFocusing = false;
        }
    }

    /* loaded from: classes2.dex */
    private final class SafeAutoFocusTask implements Runnable {
        private SafeAutoFocusTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CodeScanner.this.mSafeAutoFocusTaskScheduled = false;
            if (CodeScanner.this.mAutoFocusMode == AutoFocusMode.SAFE) {
                CodeScanner.this.safeAutoFocusCamera();
            }
        }
    }

    /* loaded from: classes2.dex */
    private final class StopPreviewTask implements Runnable {
        private StopPreviewTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CodeScanner.this.stopPreview();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public final class FinishInitializationTask implements Runnable {
        private final Point mPreviewSize;

        private FinishInitializationTask(@NonNull Point point) {
            this.mPreviewSize = point;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CodeScanner.this.mInitialized) {
                CodeScanner.this.mScannerView.setPreviewSize(this.mPreviewSize);
                CodeScanner.this.mScannerView.setAutoFocusEnabled(CodeScanner.this.isAutoFocusEnabled());
                CodeScanner.this.mScannerView.setFlashEnabled(CodeScanner.this.isFlashEnabled());
                CodeScanner.this.startPreview();
            }
        }
    }
}
