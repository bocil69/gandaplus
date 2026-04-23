package com.topjohnwu.superuser.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.topjohnwu.superuser.NoShellException;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayList;
import java.util.concurrent.Executor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class PendingJob extends JobImpl {
    private final boolean isSU;
    private boolean retry = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PendingJob(boolean z) {
        this.isSU = z;
        to(NOPList.getInstance());
    }

    @Override // com.topjohnwu.superuser.internal.JobImpl, com.topjohnwu.superuser.Shell.Job
    @NonNull
    public Shell.Result exec() {
        try {
            this.shell = MainShell.get();
            if (this.isSU && !this.shell.isRoot()) {
                close();
                return ResultImpl.INSTANCE;
            }
            if (this.out instanceof NOPList) {
                this.out = new ArrayList();
            }
            Shell.Result exec = super.exec();
            if (this.retry && exec == ResultImpl.SHELL_ERR) {
                this.retry = false;
                return exec();
            }
            return exec;
        } catch (NoShellException unused) {
            close();
            return ResultImpl.INSTANCE;
        }
    }

    @Override // com.topjohnwu.superuser.internal.JobImpl, com.topjohnwu.superuser.Shell.Job
    public void submit(@Nullable final Executor executor, @Nullable final Shell.ResultCallback resultCallback) {
        MainShell.get(null, new Shell.GetShellCallback() { // from class: com.topjohnwu.superuser.internal.PendingJob$$ExternalSyntheticLambda1
            @Override // com.topjohnwu.superuser.Shell.GetShellCallback
            public final void onShell(Shell shell) {
                PendingJob.this.m135lambda$submit$1$comtopjohnwusuperuserinternalPendingJob(executor, resultCallback, shell);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$submit$1$com-topjohnwu-superuser-internal-PendingJob  reason: not valid java name */
    public /* synthetic */ void m135lambda$submit$1$comtopjohnwusuperuserinternalPendingJob(final Executor executor, final Shell.ResultCallback resultCallback, Shell shell) {
        if (this.isSU && !shell.isRoot()) {
            close();
            ResultImpl.INSTANCE.callback(executor, resultCallback);
            return;
        }
        if (this.out instanceof NOPList) {
            this.out = resultCallback == null ? null : new ArrayList();
        }
        this.shell = (ShellImpl) shell;
        super.submit(executor, new Shell.ResultCallback() { // from class: com.topjohnwu.superuser.internal.PendingJob$$ExternalSyntheticLambda0
            @Override // com.topjohnwu.superuser.Shell.ResultCallback
            public final void onResult(Shell.Result result) {
                PendingJob.this.m134lambda$submit$0$comtopjohnwusuperuserinternalPendingJob(executor, resultCallback, result);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$submit$0$com-topjohnwu-superuser-internal-PendingJob  reason: not valid java name */
    public /* synthetic */ void m134lambda$submit$0$comtopjohnwusuperuserinternalPendingJob(Executor executor, Shell.ResultCallback resultCallback, Shell.Result result) {
        if (this.retry && result == ResultImpl.SHELL_ERR) {
            this.retry = false;
            submit(executor, resultCallback);
        } else if (resultCallback != null) {
            resultCallback.onResult(result);
        }
    }
}
