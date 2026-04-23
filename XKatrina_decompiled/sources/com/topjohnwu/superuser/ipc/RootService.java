package com.topjohnwu.superuser.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.topjohnwu.superuser.Shell;
import com.topjohnwu.superuser.internal.RootServiceManager;
import com.topjohnwu.superuser.internal.RootServiceServer;
import com.topjohnwu.superuser.internal.UiThreadHandler;
import com.topjohnwu.superuser.internal.Utils;
import java.io.IOException;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public abstract class RootService extends ContextWrapper {
    public static final String CATEGORY_DAEMON_MODE = "com.topjohnwu.superuser.DAEMON_MODE";

    @NonNull
    protected Context onAttach(@NonNull Context context) {
        return context;
    }

    public abstract IBinder onBind(@NonNull Intent intent);

    public void onCreate() {
    }

    public void onDestroy() {
    }

    public void onRebind(@NonNull Intent intent) {
    }

    public boolean onUnbind(@NonNull Intent intent) {
        return false;
    }

    @MainThread
    public static void bind(@NonNull Intent intent, @NonNull Executor executor, @NonNull ServiceConnection serviceConnection) {
        Shell.Task bindOrTask;
        if (Utils.isRootImpossible() || (bindOrTask = bindOrTask(intent, executor, serviceConnection)) == null) {
            return;
        }
        Shell.EXECUTOR.execute(asRunnable(bindOrTask));
    }

    @MainThread
    public static void bind(@NonNull Intent intent, @NonNull ServiceConnection serviceConnection) {
        bind(intent, UiThreadHandler.executor, serviceConnection);
    }

    @Nullable
    @MainThread
    public static Shell.Task bindOrTask(@NonNull Intent intent, @NonNull Executor executor, @NonNull ServiceConnection serviceConnection) {
        return RootServiceManager.getInstance().createBindTask(intent, executor, serviceConnection);
    }

    @MainThread
    public static void unbind(@NonNull ServiceConnection serviceConnection) {
        RootServiceManager.getInstance().unbind(serviceConnection);
    }

    @MainThread
    public static void stop(@NonNull Intent intent) {
        Shell.Task stopOrTask;
        if (Utils.isRootImpossible() || (stopOrTask = stopOrTask(intent)) == null) {
            return;
        }
        Shell.EXECUTOR.execute(asRunnable(stopOrTask));
    }

    @Nullable
    @MainThread
    public static Shell.Task stopOrTask(@NonNull Intent intent) {
        return RootServiceManager.getInstance().createStopTask(intent);
    }

    private static Runnable asRunnable(final Shell.Task task) {
        return new Runnable() { // from class: com.topjohnwu.superuser.ipc.RootService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RootService.lambda$asRunnable$0(Shell.Task.this);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$asRunnable$0(Shell.Task task) {
        try {
            Shell shell = Shell.getShell();
            if (shell.isRoot()) {
                shell.execTask(task);
            }
        } catch (IOException e) {
            Utils.err(e);
        }
    }

    public RootService() {
        super(null);
    }

    @Override // android.content.ContextWrapper
    protected final void attachBaseContext(Context context) {
        super.attachBaseContext(onAttach(Utils.getContextImpl(context)));
        RootServiceServer.getInstance(context).register(this);
        onCreate();
    }

    @NonNull
    public ComponentName getComponentName() {
        return new ComponentName(this, getClass());
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Context getApplicationContext() {
        return Utils.context;
    }

    public final void stopSelf() {
        RootServiceServer.getInstance(this).selfStop(getComponentName());
    }

    @Nullable
    @MainThread
    @Deprecated
    public static Runnable createBindTask(@NonNull Intent intent, @NonNull Executor executor, @NonNull ServiceConnection serviceConnection) {
        Shell.Task bindOrTask = bindOrTask(intent, executor, serviceConnection);
        if (bindOrTask == null) {
            return null;
        }
        return asRunnable(bindOrTask);
    }
}
