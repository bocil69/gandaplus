package com.topjohnwu.superuser.internal;

import androidx.annotation.RestrictTo;
import com.topjohnwu.superuser.Shell;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class SerialExecutorService extends AbstractExecutorService implements Callable<Void> {
    private boolean isShutdown = false;
    private ArrayDeque<Runnable> mTasks = new ArrayDeque<>();
    private FutureTask<Void> scheduleTask = null;

    @Override // java.util.concurrent.Callable
    public Void call() {
        Runnable poll;
        while (true) {
            synchronized (this) {
                poll = this.mTasks.poll();
                if (poll == null) {
                    this.scheduleTask = null;
                    return null;
                }
            }
            poll.run();
        }
    }

    @Override // java.util.concurrent.Executor
    public synchronized void execute(Runnable runnable) {
        if (this.isShutdown) {
            throw new RejectedExecutionException("Task " + runnable.toString() + " rejected from " + toString());
        }
        this.mTasks.offer(runnable);
        if (this.scheduleTask == null) {
            this.scheduleTask = new FutureTask<>(this);
            Shell.EXECUTOR.execute(this.scheduleTask);
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public synchronized void shutdown() {
        this.isShutdown = true;
        this.mTasks.clear();
    }

    @Override // java.util.concurrent.ExecutorService
    public synchronized List<Runnable> shutdownNow() {
        ArrayList arrayList;
        this.isShutdown = true;
        FutureTask<Void> futureTask = this.scheduleTask;
        if (futureTask != null) {
            futureTask.cancel(true);
        }
        arrayList = new ArrayList(this.mTasks);
        this.mTasks.clear();
        return arrayList;
    }

    @Override // java.util.concurrent.ExecutorService
    public synchronized boolean isShutdown() {
        return this.isShutdown;
    }

    @Override // java.util.concurrent.ExecutorService
    public synchronized boolean isTerminated() {
        boolean z;
        if (this.isShutdown) {
            z = this.scheduleTask == null;
        }
        return z;
    }

    @Override // java.util.concurrent.ExecutorService
    public synchronized boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        FutureTask<Void> futureTask = this.scheduleTask;
        if (futureTask == null) {
            return true;
        }
        try {
            futureTask.get(j, timeUnit);
        } catch (ExecutionException unused) {
        } catch (TimeoutException unused2) {
            return false;
        }
        return true;
    }
}
