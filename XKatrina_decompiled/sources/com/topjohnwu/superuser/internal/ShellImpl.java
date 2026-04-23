package com.topjohnwu.superuser.internal;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.topjohnwu.superuser.Shell;
import com.topjohnwu.superuser.ShellUtils;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ShellImpl extends Shell {
    private final NoCloseInputStream STDERR;
    private final NoCloseOutputStream STDIN;
    private final NoCloseInputStream STDOUT;
    final ExecutorService executor;
    private final Process proc;
    final boolean redirect;
    private int status;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class NoCloseInputStream extends FilterInputStream {
        @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        NoCloseInputStream(InputStream inputStream) {
            super(inputStream);
        }

        void close0() throws IOException {
            this.in.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class NoCloseOutputStream extends FilterOutputStream {
        NoCloseOutputStream(@NonNull OutputStream outputStream) {
            super(outputStream instanceof BufferedOutputStream ? outputStream : new BufferedOutputStream(outputStream));
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(@NonNull byte[] bArr, int i, int i2) throws IOException {
            this.out.write(bArr, i, i2);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.out.flush();
        }

        void close0() throws IOException {
            super.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ShellImpl(BuilderImpl builderImpl, Process process) throws IOException {
        this.status = -1;
        this.redirect = builderImpl.hasFlags(8);
        this.proc = process;
        this.STDIN = new NoCloseOutputStream(process.getOutputStream());
        this.STDOUT = new NoCloseInputStream(process.getInputStream());
        this.STDERR = new NoCloseInputStream(process.getErrorStream());
        SerialExecutorService serialExecutorService = new SerialExecutorService();
        this.executor = serialExecutorService;
        try {
            try {
                try {
                    this.status = ((Integer) serialExecutorService.submit(new Callable() { // from class: com.topjohnwu.superuser.internal.ShellImpl$$ExternalSyntheticLambda1
                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            Integer shellCheck;
                            shellCheck = ShellImpl.this.shellCheck();
                            return shellCheck;
                        }
                    }).get(builderImpl.timeout, TimeUnit.SECONDS)).intValue();
                } catch (TimeoutException e) {
                    throw new IOException("Shell check timeout", e);
                }
            } catch (InterruptedException e2) {
                throw new IOException("Shell check interrupted", e2);
            } catch (ExecutionException e3) {
                Throwable cause = e3.getCause();
                if (cause instanceof IOException) {
                    throw ((IOException) cause);
                }
                throw new IOException("Unknown ExecutionException", cause);
            }
        } catch (IOException e4) {
            this.executor.shutdownNow();
            release();
            throw e4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer shellCheck() throws IOException {
        try {
            this.proc.exitValue();
            throw new IOException("Created process has terminated");
        } catch (IllegalThreadStateException unused) {
            ShellUtils.cleanInputStream(this.STDOUT);
            ShellUtils.cleanInputStream(this.STDERR);
            int i = 0;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.STDOUT));
            try {
                this.STDIN.write("echo SHELL_TEST\n".getBytes(StandardCharsets.UTF_8));
                this.STDIN.flush();
                String readLine = bufferedReader.readLine();
                if (TextUtils.isEmpty(readLine) || !readLine.contains("SHELL_TEST")) {
                    throw new IOException("Created process is not a shell");
                }
                this.STDIN.write("id\n".getBytes(StandardCharsets.UTF_8));
                this.STDIN.flush();
                String readLine2 = bufferedReader.readLine();
                if (!TextUtils.isEmpty(readLine2) && readLine2.contains("uid=0")) {
                    Utils.setConfirmedRootState(true);
                    String escapedString = ShellUtils.escapedString(System.getProperty("user.dir"));
                    NoCloseOutputStream noCloseOutputStream = this.STDIN;
                    noCloseOutputStream.write(("cd " + escapedString + "\n").getBytes(StandardCharsets.UTF_8));
                    this.STDIN.flush();
                    i = 1;
                }
                bufferedReader.close();
                return Integer.valueOf(i);
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
                throw th;
            }
        }
    }

    private void release() {
        this.status = -1;
        try {
            this.STDIN.close0();
        } catch (IOException unused) {
        }
        try {
            this.STDERR.close0();
        } catch (IOException unused2) {
        }
        try {
            this.STDOUT.close0();
        } catch (IOException unused3) {
        }
        this.proc.destroy();
    }

    @Override // com.topjohnwu.superuser.Shell
    public boolean waitAndClose(long j, @NonNull TimeUnit timeUnit) throws InterruptedException {
        if (this.status < 0) {
            return true;
        }
        this.executor.shutdown();
        if (this.executor.awaitTermination(j, timeUnit)) {
            release();
            return true;
        }
        this.status = -1;
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.status < 0) {
            return;
        }
        this.executor.shutdownNow();
        release();
    }

    @Override // com.topjohnwu.superuser.Shell
    public int getStatus() {
        return this.status;
    }

    @Override // com.topjohnwu.superuser.Shell
    public boolean isAlive() {
        if (this.status < 0) {
            return false;
        }
        try {
            this.proc.exitValue();
            return false;
        } catch (IllegalThreadStateException unused) {
            return true;
        }
    }

    @Override // com.topjohnwu.superuser.Shell
    public synchronized void execTask(@NonNull Shell.Task task) throws IOException {
        if (this.status < 0) {
            throw new ShellTerminatedException();
        }
        ShellUtils.cleanInputStream(this.STDOUT);
        ShellUtils.cleanInputStream(this.STDERR);
        try {
            this.STDIN.write(10);
            this.STDIN.flush();
            task.run(this.STDIN, this.STDOUT, this.STDERR);
        } catch (IOException unused) {
            release();
            throw new ShellTerminatedException();
        }
    }

    @Override // com.topjohnwu.superuser.Shell
    @NonNull
    public Shell.Job newJob() {
        return new JobImpl(this);
    }
}
