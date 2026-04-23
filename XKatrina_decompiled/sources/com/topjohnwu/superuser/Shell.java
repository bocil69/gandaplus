package com.topjohnwu.superuser;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.location.LocationRequestCompat;
import com.topjohnwu.superuser.internal.BuilderImpl;
import com.topjohnwu.superuser.internal.MainShell;
import com.topjohnwu.superuser.internal.UiThreadHandler;
import com.topjohnwu.superuser.internal.Utils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public abstract class Shell implements Closeable {
    public static final int FLAG_MOUNT_MASTER = 2;
    public static final int FLAG_NON_ROOT_SHELL = 1;
    public static final int FLAG_REDIRECT_STDERR = 8;
    public static final int NON_ROOT_SHELL = 0;
    @Deprecated
    public static final int ROOT_MOUNT_MASTER = 2;
    public static final int ROOT_SHELL = 1;
    public static final int UNKNOWN = -1;
    @NonNull
    public static ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    public static boolean enableVerboseLogging = false;

    /* loaded from: classes2.dex */
    public interface GetShellCallback {
        void onShell(@NonNull Shell shell);
    }

    /* loaded from: classes2.dex */
    public static class Initializer {
        public boolean onInit(@NonNull Context context, @NonNull Shell shell) {
            return true;
        }
    }

    /* loaded from: classes2.dex */
    public interface ResultCallback {
        void onResult(@NonNull Result result);
    }

    /* loaded from: classes2.dex */
    public interface Task {
        void run(@NonNull OutputStream outputStream, @NonNull InputStream inputStream, @NonNull InputStream inputStream2) throws IOException;
    }

    public abstract void execTask(@NonNull Task task) throws IOException;

    public abstract int getStatus();

    public abstract boolean isAlive();

    @NonNull
    public abstract Job newJob();

    public abstract boolean waitAndClose(long j, @NonNull TimeUnit timeUnit) throws IOException, InterruptedException;

    public static void setDefaultBuilder(Builder builder) {
        MainShell.setBuilder(builder);
    }

    @NonNull
    public static Shell getShell() {
        return MainShell.get();
    }

    public static void getShell(@NonNull GetShellCallback getShellCallback) {
        MainShell.get(UiThreadHandler.executor, getShellCallback);
    }

    public static void getShell(@Nullable Executor executor, @NonNull GetShellCallback getShellCallback) {
        MainShell.get(executor, getShellCallback);
    }

    @Nullable
    public static Shell getCachedShell() {
        return MainShell.getCached();
    }

    @Nullable
    public static Boolean isAppGrantedRoot() {
        return Utils.isAppGrantedRoot();
    }

    @NonNull
    public static Job cmd(@NonNull String... strArr) {
        return MainShell.newJob(false, strArr);
    }

    @NonNull
    public static Job cmd(@NonNull InputStream inputStream) {
        return MainShell.newJob(false, inputStream);
    }

    public boolean isRoot() {
        return getStatus() >= 1;
    }

    public void waitAndClose() throws IOException {
        while (!waitAndClose(LocationRequestCompat.PASSIVE_INTERVAL, TimeUnit.NANOSECONDS)) {
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Builder {
        @NonNull
        public abstract Shell build();

        @NonNull
        public abstract Shell build(Process process);

        @NonNull
        public abstract Shell build(String... strArr);

        @NonNull
        public abstract Builder setFlags(int i);

        @NonNull
        public abstract Builder setTimeout(long j);

        @NonNull
        public static Builder create() {
            return new BuilderImpl();
        }

        @NonNull
        @SafeVarargs
        public final Builder setInitializers(@NonNull Class<? extends Initializer>... clsArr) {
            ((BuilderImpl) this).setInitializersImpl(clsArr);
            return this;
        }

        @NonNull
        public final Builder setContext(@NonNull Context context) {
            Utils.setContext(context);
            return this;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Result {
        public static final int JOB_NOT_EXECUTED = -1;

        public abstract int getCode();

        @NonNull
        public abstract List<String> getErr();

        @NonNull
        public abstract List<String> getOut();

        public boolean isSuccess() {
            return getCode() == 0;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Job {
        @NonNull
        public abstract Job add(@NonNull InputStream inputStream);

        @NonNull
        public abstract Job add(@NonNull String... strArr);

        @NonNull
        public abstract Future<Result> enqueue();

        @NonNull
        public abstract Result exec();

        public abstract void submit(@Nullable Executor executor, @Nullable ResultCallback resultCallback);

        @NonNull
        public abstract Job to(@Nullable List<String> list);

        @NonNull
        public abstract Job to(@Nullable List<String> list, @Nullable List<String> list2);

        public final void submit() {
            submit(null);
        }

        public final void submit(@Nullable ResultCallback resultCallback) {
            submit(UiThreadHandler.executor, resultCallback);
        }
    }

    @NonNull
    @Deprecated
    public static Job su(@NonNull String... strArr) {
        return MainShell.newJob(true, strArr);
    }

    @NonNull
    @Deprecated
    public static Job sh(@NonNull String... strArr) {
        return MainShell.newJob(false, strArr);
    }

    @NonNull
    @Deprecated
    public static Job su(@NonNull InputStream inputStream) {
        return MainShell.newJob(true, inputStream);
    }

    @NonNull
    @Deprecated
    public static Job sh(@NonNull InputStream inputStream) {
        return MainShell.newJob(false, inputStream);
    }

    @Deprecated
    public static boolean rootAccess() {
        return Shell$$ExternalSyntheticBackport0.m(isAppGrantedRoot(), Boolean.TRUE);
    }
}
