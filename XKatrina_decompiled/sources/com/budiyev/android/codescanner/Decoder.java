package com.budiyev.android.codescanner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import java.lang.Thread;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
final class Decoder {
    private volatile DecodeCallback mCallback;
    private final DecoderThread mDecoderThread;
    private final Map<DecodeHintType, Object> mHints;
    private final MultiFormatReader mReader;
    private volatile State mState;
    private final StateListener mStateListener;
    private volatile DecodeTask mTask;
    private final Object mTaskLock = new Object();

    /* loaded from: classes2.dex */
    public enum State {
        INITIALIZED,
        IDLE,
        DECODING,
        DECODED,
        STOPPED
    }

    /* loaded from: classes2.dex */
    public interface StateListener {
        boolean onStateChanged(@NonNull State state);
    }

    public Decoder(@NonNull StateListener stateListener, @NonNull Thread.UncaughtExceptionHandler uncaughtExceptionHandler, @NonNull List<BarcodeFormat> list, @Nullable DecodeCallback decodeCallback) {
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        this.mReader = multiFormatReader;
        DecoderThread decoderThread = new DecoderThread();
        this.mDecoderThread = decoderThread;
        decoderThread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        this.mHints = enumMap;
        enumMap.put((EnumMap) DecodeHintType.POSSIBLE_FORMATS, (DecodeHintType) list);
        multiFormatReader.setHints(enumMap);
        this.mCallback = decodeCallback;
        this.mStateListener = stateListener;
        this.mState = State.INITIALIZED;
    }

    public void setFormats(@NonNull List<BarcodeFormat> list) {
        this.mHints.put(DecodeHintType.POSSIBLE_FORMATS, list);
        this.mReader.setHints(this.mHints);
    }

    public void setCallback(@Nullable DecodeCallback decodeCallback) {
        this.mCallback = decodeCallback;
    }

    public void decode(@NonNull DecodeTask decodeTask) {
        synchronized (this.mTaskLock) {
            if (this.mState != State.STOPPED) {
                this.mTask = decodeTask;
                this.mTaskLock.notify();
            }
        }
    }

    public void start() {
        if (this.mState != State.INITIALIZED) {
            throw new IllegalStateException("Illegal decoder state");
        }
        this.mDecoderThread.start();
    }

    public void shutdown() {
        this.mDecoderThread.interrupt();
        this.mTask = null;
    }

    @NonNull
    public State getState() {
        return this.mState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setState(@NonNull State state) {
        this.mState = state;
        return this.mStateListener.onStateChanged(state);
    }

    /* loaded from: classes2.dex */
    private final class DecoderThread extends Thread {
        public DecoderThread() {
            super("cs-decoder");
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x0035 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0005 A[SYNTHETIC] */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r4 = this;
                r0 = 10
                android.os.Process.setThreadPriority(r0)
            L5:
                com.budiyev.android.codescanner.Decoder r0 = com.budiyev.android.codescanner.Decoder.this
                com.budiyev.android.codescanner.Decoder$State r1 = com.budiyev.android.codescanner.Decoder.State.IDLE
                com.budiyev.android.codescanner.Decoder.access$000(r0, r1)
            Lc:
                com.budiyev.android.codescanner.Decoder r0 = com.budiyev.android.codescanner.Decoder.this     // Catch: com.google.zxing.ReaderException -> L5 java.lang.Throwable -> L67
                java.lang.Object r0 = com.budiyev.android.codescanner.Decoder.access$100(r0)     // Catch: com.google.zxing.ReaderException -> L5 java.lang.Throwable -> L67
                monitor-enter(r0)     // Catch: com.google.zxing.ReaderException -> L5 java.lang.Throwable -> L67
                com.budiyev.android.codescanner.Decoder r1 = com.budiyev.android.codescanner.Decoder.this     // Catch: java.lang.Throwable -> L64
                com.budiyev.android.codescanner.DecodeTask r1 = com.budiyev.android.codescanner.Decoder.access$200(r1)     // Catch: java.lang.Throwable -> L64
                if (r1 == 0) goto L50
                com.budiyev.android.codescanner.Decoder r2 = com.budiyev.android.codescanner.Decoder.this     // Catch: java.lang.Throwable -> L64
                r3 = 0
                com.budiyev.android.codescanner.Decoder.access$202(r2, r3)     // Catch: java.lang.Throwable -> L64
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L64
                com.budiyev.android.codescanner.Decoder r0 = com.budiyev.android.codescanner.Decoder.this     // Catch: com.google.zxing.ReaderException -> L5 java.lang.Throwable -> L67
                com.budiyev.android.codescanner.Decoder$State r2 = com.budiyev.android.codescanner.Decoder.State.DECODING     // Catch: com.google.zxing.ReaderException -> L5 java.lang.Throwable -> L67
                com.budiyev.android.codescanner.Decoder.access$000(r0, r2)     // Catch: com.google.zxing.ReaderException -> L5 java.lang.Throwable -> L67
                com.budiyev.android.codescanner.Decoder r0 = com.budiyev.android.codescanner.Decoder.this     // Catch: com.google.zxing.ReaderException -> L5 java.lang.Throwable -> L67
                com.google.zxing.MultiFormatReader r0 = com.budiyev.android.codescanner.Decoder.access$300(r0)     // Catch: com.google.zxing.ReaderException -> L5 java.lang.Throwable -> L67
                com.google.zxing.Result r0 = r1.decode(r0)     // Catch: com.google.zxing.ReaderException -> L5 java.lang.Throwable -> L67
                if (r0 == 0) goto L5
                com.budiyev.android.codescanner.Decoder r1 = com.budiyev.android.codescanner.Decoder.this
                com.budiyev.android.codescanner.Decoder.access$202(r1, r3)
                com.budiyev.android.codescanner.Decoder r1 = com.budiyev.android.codescanner.Decoder.this
                com.budiyev.android.codescanner.Decoder$State r2 = com.budiyev.android.codescanner.Decoder.State.DECODED
                boolean r1 = com.budiyev.android.codescanner.Decoder.access$000(r1, r2)
                if (r1 == 0) goto L5
                com.budiyev.android.codescanner.Decoder r1 = com.budiyev.android.codescanner.Decoder.this
                com.budiyev.android.codescanner.DecodeCallback r1 = com.budiyev.android.codescanner.Decoder.access$400(r1)
                if (r1 == 0) goto L5
                r1.onDecoded(r0)
                goto L5
            L50:
                com.budiyev.android.codescanner.Decoder r1 = com.budiyev.android.codescanner.Decoder.this     // Catch: java.lang.InterruptedException -> L5b java.lang.Throwable -> L64
                java.lang.Object r1 = com.budiyev.android.codescanner.Decoder.access$100(r1)     // Catch: java.lang.InterruptedException -> L5b java.lang.Throwable -> L64
                r1.wait()     // Catch: java.lang.InterruptedException -> L5b java.lang.Throwable -> L64
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L64
                goto Lc
            L5b:
                com.budiyev.android.codescanner.Decoder r1 = com.budiyev.android.codescanner.Decoder.this     // Catch: java.lang.Throwable -> L64
                com.budiyev.android.codescanner.Decoder$State r2 = com.budiyev.android.codescanner.Decoder.State.STOPPED     // Catch: java.lang.Throwable -> L64
                com.budiyev.android.codescanner.Decoder.access$000(r1, r2)     // Catch: java.lang.Throwable -> L64
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L64
                return
            L64:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L64
                throw r1     // Catch: com.google.zxing.ReaderException -> L5 java.lang.Throwable -> L67
            L67:
                r0 = move-exception
                goto L6a
            L69:
                throw r0
            L6a:
                goto L69
            */
            throw new UnsupportedOperationException("Method not decompiled: com.budiyev.android.codescanner.Decoder.DecoderThread.run():void");
        }
    }
}
