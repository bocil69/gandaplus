package com.topjohnwu.superuser.internal;

import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.system.ErrnoException;
import android.system.OsConstants;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.topjohnwu.superuser.internal.FileUtils;
import com.topjohnwu.superuser.internal.IFileSystemService;
import com.topjohnwu.superuser.nio.ExtendedFile;
import com.topjohnwu.superuser.nio.FileSystemManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.attribute.FileAttribute;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public final class NIOFactory {
    private NIOFactory() {
    }

    public static FileSystemManager createLocal() {
        return new FileSystemManager() { // from class: com.topjohnwu.superuser.internal.NIOFactory.1
            @Override // com.topjohnwu.superuser.nio.FileSystemManager
            @NonNull
            public ExtendedFile getFile(@NonNull String str) {
                return new LocalFile(str);
            }

            @Override // com.topjohnwu.superuser.nio.FileSystemManager
            @NonNull
            public ExtendedFile getFile(@Nullable String str, @NonNull String str2) {
                return new LocalFile(str, str2);
            }

            @Override // com.topjohnwu.superuser.nio.FileSystemManager
            @NonNull
            public FileChannel openChannel(@NonNull File file, int i) throws IOException {
                if (Build.VERSION.SDK_INT >= 26) {
                    return FileChannel.open(file.toPath(), FileUtils.modeToOptions(i), new FileAttribute[0]);
                }
                FileUtils.Flag modeToFlag = FileUtils.modeToFlag(i);
                if (modeToFlag.write) {
                    if (!modeToFlag.create && !file.exists()) {
                        ErrnoException errnoException = new ErrnoException("open", OsConstants.ENOENT);
                        throw new FileNotFoundException(file + ": " + errnoException.getMessage());
                    } else if (modeToFlag.append) {
                        return new FileOutputStream(file, true).getChannel();
                    } else {
                        if (!modeToFlag.read && modeToFlag.truncate) {
                            return new FileOutputStream(file, false).getChannel();
                        }
                        FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
                        if (modeToFlag.truncate) {
                            channel.truncate(0L);
                        }
                        return channel;
                    }
                }
                return new FileInputStream(file).getChannel();
            }
        };
    }

    public static FileSystemManager createRemote(IBinder iBinder) throws RemoteException {
        final IFileSystemService asInterface = IFileSystemService.Stub.asInterface(iBinder);
        if (asInterface == null) {
            throw new IllegalArgumentException("The IBinder provided is invalid");
        }
        asInterface.register(new Binder());
        return new FileSystemManager() { // from class: com.topjohnwu.superuser.internal.NIOFactory.2
            @Override // com.topjohnwu.superuser.nio.FileSystemManager
            @NonNull
            public ExtendedFile getFile(@NonNull String str) {
                return new RemoteFile(IFileSystemService.this, str);
            }

            @Override // com.topjohnwu.superuser.nio.FileSystemManager
            @NonNull
            public ExtendedFile getFile(@Nullable String str, @NonNull String str2) {
                return new RemoteFile(IFileSystemService.this, str, str2);
            }

            @Override // com.topjohnwu.superuser.nio.FileSystemManager
            @NonNull
            public FileChannel openChannel(@NonNull File file, int i) throws IOException {
                return new RemoteFileChannel(IFileSystemService.this, file, i);
            }
        };
    }

    public static FileSystemService createFsService() {
        return new FileSystemService();
    }
}
