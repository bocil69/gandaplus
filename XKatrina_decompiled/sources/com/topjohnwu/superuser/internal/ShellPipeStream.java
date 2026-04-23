package com.topjohnwu.superuser.internal;

import com.topjohnwu.superuser.Shell;
import com.topjohnwu.superuser.io.SuFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ShellPipeStream {
    private static final byte[] END_CMD = "echo\n".getBytes(StandardCharsets.UTF_8);
    private static final int FIFO_TIMEOUT = 250;
    private static final String TAG = "FIFOIO";

    ShellPipeStream() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InputStream openReadStream(final SuFile suFile) throws FileNotFoundException {
        if (suFile.isDirectory() || !suFile.canRead()) {
            throw new FileNotFoundException("No such file or directory: " + suFile.getPath());
        }
        final File file = null;
        try {
            try {
                file = FileUtils.createTempFIFO();
                suFile.getShell().execTask(new Shell.Task() { // from class: com.topjohnwu.superuser.internal.ShellPipeStream$$ExternalSyntheticLambda0
                    @Override // com.topjohnwu.superuser.Shell.Task
                    public final void run(OutputStream outputStream, InputStream inputStream, InputStream inputStream2) {
                        ShellPipeStream.lambda$openReadStream$0(SuFile.this, file, outputStream, inputStream, inputStream2);
                    }
                });
                return (InputStream) Shell.EXECUTOR.submit(new Callable() { // from class: com.topjohnwu.superuser.internal.ShellPipeStream$$ExternalSyntheticLambda1
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return ShellPipeStream.lambda$openReadStream$1(file);
                    }
                }).get(250L, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                if (e instanceof FileNotFoundException) {
                    throw ((FileNotFoundException) e);
                }
                Throwable cause = e.getCause();
                if (cause instanceof FileNotFoundException) {
                    throw ((FileNotFoundException) cause);
                }
                throw ((FileNotFoundException) new FileNotFoundException("Cannot open fifo").initCause(e));
            }
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$openReadStream$0(SuFile suFile, File file, OutputStream outputStream, InputStream inputStream, InputStream inputStream2) throws IOException {
        String str = "cat " + suFile.getEscapedPath() + " > " + file + " 2>/dev/null &";
        Utils.log(TAG, str);
        outputStream.write(str.getBytes(StandardCharsets.UTF_8));
        outputStream.write(10);
        outputStream.flush();
        outputStream.write(END_CMD);
        outputStream.flush();
        inputStream.read(IOFactory.JUNK);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ InputStream lambda$openReadStream$1(File file) throws Exception {
        return new FileInputStream(file);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OutputStream openWriteStream(final SuFile suFile, boolean z) throws FileNotFoundException {
        if (suFile.isDirectory()) {
            throw new FileNotFoundException(suFile.getPath() + " is not a file but a directory");
        }
        z = (suFile.isBlock() || suFile.isCharacter()) ? false : false;
        if (z && !suFile.canWrite() && !suFile.createNewFile()) {
            throw new FileNotFoundException("Cannot write to file " + suFile.getPath());
        } else if (!suFile.clear()) {
            throw new FileNotFoundException("Failed to clear file " + suFile.getPath());
        } else {
            final String str = z ? " >> " : " > ";
            final File file = null;
            try {
                try {
                    file = FileUtils.createTempFIFO();
                    suFile.getShell().execTask(new Shell.Task() { // from class: com.topjohnwu.superuser.internal.ShellPipeStream$$ExternalSyntheticLambda2
                        @Override // com.topjohnwu.superuser.Shell.Task
                        public final void run(OutputStream outputStream, InputStream inputStream, InputStream inputStream2) {
                            ShellPipeStream.lambda$openWriteStream$2(file, str, suFile, outputStream, inputStream, inputStream2);
                        }
                    });
                    return (OutputStream) Shell.EXECUTOR.submit(new Callable() { // from class: com.topjohnwu.superuser.internal.ShellPipeStream$$ExternalSyntheticLambda3
                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            return ShellPipeStream.lambda$openWriteStream$3(file);
                        }
                    }).get(250L, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    if (e instanceof FileNotFoundException) {
                        throw ((FileNotFoundException) e);
                    }
                    Throwable cause = e.getCause();
                    if (cause instanceof FileNotFoundException) {
                        throw ((FileNotFoundException) cause);
                    }
                    throw ((FileNotFoundException) new FileNotFoundException("Cannot open fifo").initCause(e));
                }
            } finally {
                if (file != null) {
                    file.delete();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$openWriteStream$2(File file, String str, SuFile suFile, OutputStream outputStream, InputStream inputStream, InputStream inputStream2) throws IOException {
        String str2 = "cat " + file + str + suFile.getEscapedPath() + " 2>/dev/null &";
        Utils.log(TAG, str2);
        outputStream.write(str2.getBytes(StandardCharsets.UTF_8));
        outputStream.write(10);
        outputStream.flush();
        outputStream.write(END_CMD);
        outputStream.flush();
        inputStream.read(IOFactory.JUNK);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ OutputStream lambda$openWriteStream$3(File file) throws Exception {
        return new FileOutputStream(file);
    }
}
