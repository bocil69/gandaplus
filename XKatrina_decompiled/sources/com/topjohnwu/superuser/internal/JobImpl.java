package com.topjohnwu.superuser.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.topjohnwu.superuser.Shell;
import com.topjohnwu.superuser.internal.StreamGobbler;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class JobImpl extends Shell.Job implements Shell.Task, Closeable {
    static final byte[] END_CMD;
    static final String END_UUID;
    private static final List<String> UNSET_ERR = new ArrayList(0);
    static final int UUID_LEN = 36;
    protected List<String> out;
    protected ShellImpl shell;
    private final List<ShellInputSource> sources = new ArrayList();
    private final ResultImpl result = new ResultImpl();
    protected List<String> err = UNSET_ERR;

    static {
        String uuid = UUID.randomUUID().toString();
        END_UUID = uuid;
        END_CMD = String.format("__RET=$?;echo %1$s;echo %1$s >&2;echo $__RET;unset __RET\n", uuid).getBytes(StandardCharsets.UTF_8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JobImpl() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JobImpl(ShellImpl shellImpl) {
        this.shell = shellImpl;
    }

    @Override // com.topjohnwu.superuser.Shell.Task
    public void run(@NonNull OutputStream outputStream, @NonNull InputStream inputStream, @NonNull InputStream inputStream2) throws IOException {
        Future submit = Shell.EXECUTOR.submit(new StreamGobbler.OUT(inputStream, this.result.out));
        Future submit2 = Shell.EXECUTOR.submit(new StreamGobbler.ERR(inputStream2, this.result.err));
        for (ShellInputSource shellInputSource : this.sources) {
            shellInputSource.serve(outputStream);
        }
        outputStream.write(END_CMD);
        outputStream.flush();
        try {
            this.result.code = ((Integer) submit.get()).intValue();
            submit2.get();
        } catch (InterruptedException | ExecutionException e) {
            throw ((InterruptedIOException) new InterruptedIOException().initCause(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ResultImpl exec0() {
        boolean z = this.err == UNSET_ERR;
        this.result.out = this.out;
        this.result.err = z ? null : this.err;
        if (z && this.shell.redirect) {
            this.result.err = this.out;
        }
        if (this.result.out != null && this.result.out == this.result.err && !Utils.isSynchronized(this.result.out)) {
            List<String> synchronizedList = Collections.synchronizedList(this.result.out);
            this.result.out = synchronizedList;
            this.result.err = synchronizedList;
        }
        try {
            try {
                this.shell.execTask(this);
                close();
                this.result.out = this.out;
                this.result.err = z ? null : this.err;
                return this.result;
            } catch (IOException e) {
                if (e instanceof ShellTerminatedException) {
                    ResultImpl resultImpl = ResultImpl.SHELL_ERR;
                    close();
                    this.result.out = this.out;
                    this.result.err = z ? null : this.err;
                    return resultImpl;
                }
                Utils.err(e);
                ResultImpl resultImpl2 = ResultImpl.INSTANCE;
                close();
                this.result.out = this.out;
                this.result.err = z ? null : this.err;
                return resultImpl2;
            }
        } catch (Throwable th) {
            close();
            this.result.out = this.out;
            this.result.err = z ? null : this.err;
            throw th;
        }
    }

    @Override // com.topjohnwu.superuser.Shell.Job
    @NonNull
    public Shell.Result exec() {
        return exec0();
    }

    @Override // com.topjohnwu.superuser.Shell.Job
    @NonNull
    public Future<Shell.Result> enqueue() {
        FutureTask futureTask = new FutureTask(new Callable() { // from class: com.topjohnwu.superuser.internal.JobImpl$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                ResultImpl exec0;
                exec0 = JobImpl.this.exec0();
                return exec0;
            }
        });
        this.shell.executor.execute(futureTask);
        return futureTask;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$submit$0$com-topjohnwu-superuser-internal-JobImpl  reason: not valid java name */
    public /* synthetic */ void m133lambda$submit$0$comtopjohnwusuperuserinternalJobImpl(Executor executor, Shell.ResultCallback resultCallback) {
        exec0().callback(executor, resultCallback);
    }

    @Override // com.topjohnwu.superuser.Shell.Job
    public void submit(@Nullable final Executor executor, @Nullable final Shell.ResultCallback resultCallback) {
        this.shell.executor.execute(new Runnable() { // from class: com.topjohnwu.superuser.internal.JobImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                JobImpl.this.m133lambda$submit$0$comtopjohnwusuperuserinternalJobImpl(executor, resultCallback);
            }
        });
    }

    @Override // com.topjohnwu.superuser.Shell.Job
    @NonNull
    public Shell.Job to(List<String> list) {
        this.out = list;
        return this;
    }

    @Override // com.topjohnwu.superuser.Shell.Job
    @NonNull
    public Shell.Job to(List<String> list, List<String> list2) {
        this.out = list;
        this.err = list2;
        return this;
    }

    @Override // com.topjohnwu.superuser.Shell.Job
    @NonNull
    public Shell.Job add(@NonNull InputStream inputStream) {
        if (inputStream != null) {
            this.sources.add(new InputStreamSource(inputStream));
        }
        return this;
    }

    @Override // com.topjohnwu.superuser.Shell.Job
    @NonNull
    public Shell.Job add(@NonNull String... strArr) {
        if (strArr != null && strArr.length > 0) {
            this.sources.add(new CommandSource(strArr));
        }
        return this;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        for (ShellInputSource shellInputSource : this.sources) {
            shellInputSource.close();
        }
    }
}
