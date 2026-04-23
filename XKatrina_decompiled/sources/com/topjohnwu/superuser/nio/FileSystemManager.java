package com.topjohnwu.superuser.nio;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.topjohnwu.superuser.internal.NIOFactory;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.FileChannel;
/* loaded from: classes2.dex */
public abstract class FileSystemManager {
    private static final FileSystemManager LOCAL = NIOFactory.createLocal();
    public static final int MODE_APPEND = 33554432;
    public static final int MODE_CREATE = 134217728;
    public static final int MODE_READ_ONLY = 268435456;
    public static final int MODE_READ_WRITE = 805306368;
    public static final int MODE_TRUNCATE = 67108864;
    public static final int MODE_WRITE_ONLY = 536870912;
    private static Binder fsService;

    @NonNull
    public abstract ExtendedFile getFile(@NonNull String str);

    @NonNull
    public abstract ExtendedFile getFile(@Nullable String str, @NonNull String str2);

    @NonNull
    public abstract FileChannel openChannel(@NonNull File file, int i) throws IOException;

    @NonNull
    public static synchronized Binder getService() {
        Binder binder;
        synchronized (FileSystemManager.class) {
            if (fsService == null) {
                fsService = NIOFactory.createFsService();
            }
            binder = fsService;
        }
        return binder;
    }

    @NonNull
    public static FileSystemManager getLocal() {
        return LOCAL;
    }

    @NonNull
    public static FileSystemManager getRemote(@NonNull IBinder iBinder) throws RemoteException {
        return NIOFactory.createRemote(iBinder);
    }

    @NonNull
    public final ExtendedFile getFile(@Nullable File file, @NonNull String str) {
        return getFile(file == null ? null : file.getPath(), str);
    }

    @NonNull
    public final ExtendedFile getFile(@NonNull URI uri) {
        return getFile(new File(uri).getPath());
    }

    @NonNull
    public final FileChannel openChannel(@NonNull String str, int i) throws IOException {
        return openChannel(new File(str), i);
    }
}
