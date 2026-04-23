package com.topjohnwu.superuser.internal;

import androidx.annotation.GuardedBy;
import androidx.annotation.RestrictTo;
import com.topjohnwu.superuser.NoShellException;
import com.topjohnwu.superuser.Shell;
import java.io.InputStream;
import java.util.concurrent.Executor;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public final class MainShell {
    @GuardedBy("class")
    private static boolean isInitMain;
    @GuardedBy("class")
    private static BuilderImpl mainBuilder;
    @GuardedBy("self")
    private static final ShellImpl[] mainShell = new ShellImpl[1];

    private MainShell() {
    }

    public static synchronized ShellImpl get() {
        ShellImpl cached;
        synchronized (MainShell.class) {
            cached = getCached();
            if (cached == null) {
                isInitMain = true;
                if (mainBuilder == null) {
                    mainBuilder = new BuilderImpl();
                }
                cached = mainBuilder.build();
                isInitMain = false;
            }
        }
        return cached;
    }

    private static void returnShell(final Shell shell, Executor executor, final Shell.GetShellCallback getShellCallback) {
        if (executor == null) {
            getShellCallback.onShell(shell);
        } else {
            executor.execute(new Runnable() { // from class: com.topjohnwu.superuser.internal.MainShell$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Shell.GetShellCallback.this.onShell(shell);
                }
            });
        }
    }

    public static void get(final Executor executor, final Shell.GetShellCallback getShellCallback) {
        ShellImpl cached = getCached();
        if (cached != null) {
            returnShell(cached, executor, getShellCallback);
        } else {
            Shell.EXECUTOR.execute(new Runnable() { // from class: com.topjohnwu.superuser.internal.MainShell$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    MainShell.lambda$get$1(executor, getShellCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$get$1(Executor executor, Shell.GetShellCallback getShellCallback) {
        try {
            returnShell(get(), executor, getShellCallback);
        } catch (NoShellException e) {
            Utils.ex(e);
        }
    }

    public static ShellImpl getCached() {
        ShellImpl shellImpl;
        ShellImpl[] shellImplArr = mainShell;
        synchronized (shellImplArr) {
            shellImpl = shellImplArr[0];
            if (shellImpl != null && shellImpl.getStatus() < 0) {
                shellImplArr[0] = null;
            }
        }
        return shellImpl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void setCached(ShellImpl shellImpl) {
        synchronized (MainShell.class) {
            if (isInitMain) {
                ShellImpl[] shellImplArr = mainShell;
                synchronized (shellImplArr) {
                    shellImplArr[0] = shellImpl;
                }
            }
        }
    }

    public static synchronized void setBuilder(Shell.Builder builder) {
        synchronized (MainShell.class) {
            if (isInitMain || getCached() != null) {
                throw new IllegalStateException("The main shell was already created");
            }
            mainBuilder = (BuilderImpl) builder;
        }
    }

    public static Shell.Job newJob(boolean z, InputStream inputStream) {
        return new PendingJob(z).add(inputStream);
    }

    public static Shell.Job newJob(boolean z, String... strArr) {
        return new PendingJob(z).add(strArr);
    }
}
