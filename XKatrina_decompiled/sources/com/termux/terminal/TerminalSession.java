package com.termux.terminal;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
/* loaded from: classes6.dex */
public final class TerminalSession extends TerminalOutput {
    private static final int MSG_NEW_INPUT = 1;
    private static final int MSG_PROCESS_EXITED = 4;
    private final String[] mArgs;
    final SessionChangedCallback mChangeCallback;
    private final String mCwd;
    TerminalEmulator mEmulator;
    private final String[] mEnv;
    public String mSessionName;
    int mShellExitStatus;
    private final String mShellPath;
    int mShellPid;
    private int mTerminalFileDescriptor;
    public final String mHandle = UUID.randomUUID().toString();
    final ByteQueue mProcessToTerminalIOQueue = new ByteQueue(4096);
    final ByteQueue mTerminalToProcessIOQueue = new ByteQueue(4096);
    private final byte[] mUtf8InputBuffer = new byte[5];
    @SuppressLint({"HandlerLeak"})
    final Handler mMainThreadHandler = new Handler() { // from class: com.termux.terminal.TerminalSession.1
        final byte[] mReceiveBuffer = new byte[4096];

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int read = TerminalSession.this.mProcessToTerminalIOQueue.read(this.mReceiveBuffer, false);
            if (read > 0) {
                TerminalSession.this.mEmulator.append(this.mReceiveBuffer, read);
                TerminalSession.this.notifyScreenUpdate();
            }
            if (message.what == 4) {
                int intValue = ((Integer) message.obj).intValue();
                TerminalSession.this.cleanupResources(intValue);
                TerminalSession.this.mChangeCallback.onSessionFinished(TerminalSession.this);
                String str = "\r\n[Process completed";
                if (intValue > 0) {
                    str = "\r\n[Process completed (code " + intValue + ")";
                } else if (intValue < 0) {
                    str = "\r\n[Process completed (signal " + (-intValue) + ")";
                }
                byte[] bytes = (String.valueOf(str) + " - press Enter]").getBytes(StandardCharsets.UTF_8);
                TerminalSession.this.mEmulator.append(bytes, bytes.length);
                TerminalSession.this.notifyScreenUpdate();
            }
        }
    };

    /* loaded from: classes6.dex */
    public interface SessionChangedCallback {
        void onBell(TerminalSession terminalSession);

        void onClipboardText(TerminalSession terminalSession, String str);

        void onColorsChanged(TerminalSession terminalSession);

        void onSessionFinished(TerminalSession terminalSession);

        void onTextChanged(TerminalSession terminalSession);

        void onTitleChanged(TerminalSession terminalSession);
    }

    private static FileDescriptor wrapFileDescriptor(int i) {
        Field declaredField;
        FileDescriptor fileDescriptor = new FileDescriptor();
        try {
            try {
                try {
                    declaredField = FileDescriptor.class.getDeclaredField("descriptor");
                } catch (NoSuchFieldException e) {
                    e = e;
                    Log.wtf(EmulatorDebug.LOG_TAG, "Error accessing FileDescriptor#descriptor private field", e);
                    System.exit(1);
                    return fileDescriptor;
                }
            } catch (NoSuchFieldException unused) {
                declaredField = FileDescriptor.class.getDeclaredField("fd");
            }
            declaredField.setAccessible(true);
            declaredField.set(fileDescriptor, Integer.valueOf(i));
        } catch (IllegalAccessException e2) {
            e = e2;
            Log.wtf(EmulatorDebug.LOG_TAG, "Error accessing FileDescriptor#descriptor private field", e);
            System.exit(1);
            return fileDescriptor;
        } catch (IllegalArgumentException e3) {
            e = e3;
            Log.wtf(EmulatorDebug.LOG_TAG, "Error accessing FileDescriptor#descriptor private field", e);
            System.exit(1);
            return fileDescriptor;
        }
        return fileDescriptor;
    }

    public TerminalSession(String str, String str2, String[] strArr, String[] strArr2, SessionChangedCallback sessionChangedCallback) {
        this.mChangeCallback = sessionChangedCallback;
        this.mShellPath = str;
        this.mCwd = str2;
        this.mArgs = strArr;
        this.mEnv = strArr2;
    }

    public void updateSize(int i, int i2) {
        if (this.mEmulator == null) {
            initializeEmulator(i, i2);
            return;
        }
        JNI.setPtyWindowSize(this.mTerminalFileDescriptor, i2, i);
        this.mEmulator.resize(i, i2);
    }

    public String getTitle() {
        TerminalEmulator terminalEmulator = this.mEmulator;
        if (terminalEmulator == null) {
            return null;
        }
        return terminalEmulator.getTitle();
    }

    /* JADX WARN: Type inference failed for: r10v3, types: [com.termux.terminal.TerminalSession$2] */
    /* JADX WARN: Type inference failed for: r10v4, types: [com.termux.terminal.TerminalSession$3] */
    /* JADX WARN: Type inference failed for: r9v3, types: [com.termux.terminal.TerminalSession$4] */
    public void initializeEmulator(int i, int i2) {
        this.mEmulator = new TerminalEmulator(this, i, i2, 2000);
        int[] iArr = new int[1];
        int createSubprocess = JNI.createSubprocess(this.mShellPath, this.mCwd, this.mArgs, this.mEnv, iArr, i2, i);
        this.mTerminalFileDescriptor = createSubprocess;
        this.mShellPid = iArr[0];
        final FileDescriptor wrapFileDescriptor = wrapFileDescriptor(createSubprocess);
        new Thread("TermSessionInputReader[pid=" + this.mShellPid + "]") { // from class: com.termux.terminal.TerminalSession.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Throwable th = null;
                try {
                    FileInputStream fileInputStream = new FileInputStream(wrapFileDescriptor);
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read != -1) {
                            if (TerminalSession.this.mProcessToTerminalIOQueue.write(bArr, 0, read)) {
                                TerminalSession.this.mMainThreadHandler.sendEmptyMessage(1);
                            } else {
                                fileInputStream.close();
                                return;
                            }
                        } else {
                            fileInputStream.close();
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    if (0 == 0) {
                        throw th2;
                    } else if (null == th2) {
                    } else {
                        try {
                            th.addSuppressed(th2);
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        }.start();
        new Thread("TermSessionOutputWriter[pid=" + this.mShellPid + "]") { // from class: com.termux.terminal.TerminalSession.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                byte[] bArr = new byte[4096];
                Throwable th = null;
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(wrapFileDescriptor);
                    while (true) {
                        int read = TerminalSession.this.mTerminalToProcessIOQueue.read(bArr, true);
                        if (read != -1) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileOutputStream.close();
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    if (0 == 0) {
                        throw th2;
                    }
                    if (null != th2) {
                        try {
                            th.addSuppressed(th2);
                        } catch (IOException unused) {
                            return;
                        }
                    }
                    throw null;
                }
            }
        }.start();
        new Thread("TermSessionWaiter[pid=" + this.mShellPid + "]") { // from class: com.termux.terminal.TerminalSession.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                TerminalSession.this.mMainThreadHandler.sendMessage(TerminalSession.this.mMainThreadHandler.obtainMessage(4, Integer.valueOf(JNI.waitFor(TerminalSession.this.mShellPid))));
            }
        }.start();
    }

    @Override // com.termux.terminal.TerminalOutput
    public void write(byte[] bArr, int i, int i2) {
        if (this.mShellPid > 0) {
            this.mTerminalToProcessIOQueue.write(bArr, i, i2);
        }
    }

    public void writeCodePoint(boolean z, int i) {
        int i2;
        int i3;
        int i4;
        if (i > 1114111 || (i >= 55296 && i <= 57343)) {
            throw new IllegalArgumentException("Invalid code point: " + i);
        }
        if (z) {
            this.mUtf8InputBuffer[0] = 27;
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (i <= 127) {
            i4 = i2 + 1;
            this.mUtf8InputBuffer[i2] = (byte) i;
        } else {
            if (i <= 2047) {
                byte[] bArr = this.mUtf8InputBuffer;
                int i5 = i2 + 1;
                bArr[i2] = (byte) ((i >> 6) | 192);
                i3 = i5 + 1;
                bArr[i5] = (byte) ((i & 63) | 128);
            } else if (i <= 65535) {
                byte[] bArr2 = this.mUtf8InputBuffer;
                int i6 = i2 + 1;
                bArr2[i2] = (byte) ((i >> 12) | 224);
                int i7 = i6 + 1;
                bArr2[i6] = (byte) (((i >> 6) & 63) | 128);
                i4 = i7 + 1;
                bArr2[i7] = (byte) ((i & 63) | 128);
            } else {
                byte[] bArr3 = this.mUtf8InputBuffer;
                int i8 = i2 + 1;
                bArr3[i2] = (byte) ((i >> 18) | 240);
                int i9 = i8 + 1;
                bArr3[i8] = (byte) (((i >> 12) & 63) | 128);
                int i10 = i9 + 1;
                bArr3[i9] = (byte) (((i >> 6) & 63) | 128);
                i3 = i10 + 1;
                bArr3[i10] = (byte) ((i & 63) | 128);
            }
            i4 = i3;
        }
        write(this.mUtf8InputBuffer, 0, i4);
    }

    public TerminalEmulator getEmulator() {
        return this.mEmulator;
    }

    protected void notifyScreenUpdate() {
        this.mChangeCallback.onTextChanged(this);
    }

    public void reset() {
        this.mEmulator.reset();
        notifyScreenUpdate();
    }

    public void finishIfRunning() {
        if (isRunning()) {
            try {
                Os.kill(this.mShellPid, OsConstants.SIGKILL);
            } catch (ErrnoException e) {
                Log.w(EmulatorDebug.LOG_TAG, "Failed sending SIGKILL: " + e.getMessage());
            }
        }
    }

    void cleanupResources(int i) {
        synchronized (this) {
            this.mShellPid = -1;
            this.mShellExitStatus = i;
        }
        this.mTerminalToProcessIOQueue.close();
        this.mProcessToTerminalIOQueue.close();
        JNI.close(this.mTerminalFileDescriptor);
    }

    @Override // com.termux.terminal.TerminalOutput
    public void titleChanged(String str, String str2) {
        this.mChangeCallback.onTitleChanged(this);
    }

    public synchronized boolean isRunning() {
        return this.mShellPid != -1;
    }

    public synchronized int getExitStatus() {
        return this.mShellExitStatus;
    }

    @Override // com.termux.terminal.TerminalOutput
    public void clipboardText(String str) {
        this.mChangeCallback.onClipboardText(this, str);
    }

    @Override // com.termux.terminal.TerminalOutput
    public void onBell() {
        this.mChangeCallback.onBell(this);
    }

    @Override // com.termux.terminal.TerminalOutput
    public void onColorsChanged() {
        this.mChangeCallback.onColorsChanged(this);
    }

    public int getPid() {
        return this.mShellPid;
    }

    public String getCwd() {
        String format;
        String canonicalPath;
        String str;
        int i = this.mShellPid;
        if (i < 1) {
            return null;
        }
        try {
            format = String.format("/proc/%s/cwd/", Integer.valueOf(i));
            canonicalPath = new File(format).getCanonicalPath();
            if (canonicalPath.endsWith("/")) {
                str = canonicalPath;
            } else {
                str = String.valueOf(canonicalPath) + '/';
            }
        } catch (IOException | SecurityException e) {
            Log.e(EmulatorDebug.LOG_TAG, "Error getting current directory", e);
        }
        if (format.equals(str)) {
            return null;
        }
        return canonicalPath;
    }
}
