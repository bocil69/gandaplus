package com.topjohnwu.superuser.internal;

import android.annotation.SuppressLint;
import android.os.Binder;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.LruCache;
import com.topjohnwu.superuser.internal.IFileSystemService;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class FileSystemService extends IFileSystemService.Stub {
    static final int PIPE_CAPACITY = 65536;
    private final LruCache<String, File> mCache = new LruCache<String, File>(100) { // from class: com.topjohnwu.superuser.internal.FileSystemService.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        public File create(String str) {
            return new File(str);
        }
    };
    private final FileContainer openFiles = new FileContainer();
    private final ExecutorService streamPool = Executors.newCachedThreadPool();

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public IOResult getCanonicalPath(String str) {
        try {
            return new IOResult(this.mCache.get(str).getCanonicalPath());
        } catch (IOException e) {
            return new IOResult(e);
        }
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public boolean isDirectory(String str) {
        return this.mCache.get(str).isDirectory();
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public boolean isFile(String str) {
        return this.mCache.get(str).isFile();
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public boolean isHidden(String str) {
        return this.mCache.get(str).isHidden();
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public long lastModified(String str) {
        return this.mCache.get(str).lastModified();
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public long length(String str) {
        return this.mCache.get(str).length();
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public IOResult createNewFile(String str) {
        try {
            return new IOResult(Boolean.valueOf(this.mCache.get(str).createNewFile()));
        } catch (IOException e) {
            return new IOResult(e);
        }
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public boolean delete(String str) {
        return this.mCache.get(str).delete();
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public String[] list(String str) {
        return this.mCache.get(str).list();
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public boolean mkdir(String str) {
        return this.mCache.get(str).mkdir();
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public boolean mkdirs(String str) {
        return this.mCache.get(str).mkdirs();
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public boolean renameTo(String str, String str2) {
        return this.mCache.get(str).renameTo(this.mCache.get(str2));
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public boolean setLastModified(String str, long j) {
        return this.mCache.get(str).setLastModified(j);
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public boolean setReadOnly(String str) {
        return this.mCache.get(str).setReadOnly();
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public boolean setWritable(String str, boolean z, boolean z2) {
        return this.mCache.get(str).setWritable(z, z2);
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public boolean setReadable(String str, boolean z, boolean z2) {
        return this.mCache.get(str).setReadable(z, z2);
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public boolean setExecutable(String str, boolean z, boolean z2) {
        return this.mCache.get(str).setExecutable(z, z2);
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public boolean checkAccess(String str, int i) {
        try {
            return Os.access(str, i);
        } catch (ErrnoException unused) {
            return false;
        }
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public long getTotalSpace(String str) {
        return this.mCache.get(str).getTotalSpace();
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public long getFreeSpace(String str) {
        return this.mCache.get(str).getFreeSpace();
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    @SuppressLint({"UsableSpace"})
    public long getUsableSpace(String str) {
        return this.mCache.get(str).getUsableSpace();
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public int getMode(String str) {
        try {
            return Os.lstat(str).st_mode;
        } catch (ErrnoException unused) {
            return 0;
        }
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public IOResult createLink(String str, String str2, boolean z) {
        try {
            if (z) {
                Os.symlink(str2, str);
            } else {
                Os.link(str2, str);
            }
            return new IOResult((Object) true);
        } catch (ErrnoException e) {
            if (e.errno == OsConstants.EEXIST) {
                return new IOResult((Object) false);
            }
            return new IOResult(e);
        }
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public void register(IBinder iBinder) {
        final int callingPid = Binder.getCallingPid();
        try {
            iBinder.linkToDeath(new IBinder.DeathRecipient() { // from class: com.topjohnwu.superuser.internal.FileSystemService$$ExternalSyntheticLambda3
                @Override // android.os.IBinder.DeathRecipient
                public final void binderDied() {
                    FileSystemService.this.m132xcbca6e54(callingPid);
                }
            }, 0);
        } catch (RemoteException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$register$0$com-topjohnwu-superuser-internal-FileSystemService  reason: not valid java name */
    public /* synthetic */ void m132xcbca6e54(int i) {
        this.openFiles.pidDied(i);
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public IOResult openChannel(String str, int i, String str2) {
        OpenFile openFile = new OpenFile();
        try {
            openFile.fd = Os.open(str, i | OsConstants.O_NONBLOCK, 438);
            openFile.read = Os.open(str2, OsConstants.O_RDONLY | OsConstants.O_NONBLOCK, 0);
            openFile.write = Os.open(str2, OsConstants.O_WRONLY | OsConstants.O_NONBLOCK, 0);
            return new IOResult(Integer.valueOf(this.openFiles.put(openFile)));
        } catch (ErrnoException e) {
            openFile.close();
            return new IOResult(e);
        }
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public IOResult openReadStream(String str, final ParcelFileDescriptor parcelFileDescriptor) {
        final OpenFile openFile = new OpenFile();
        try {
            openFile.fd = Os.open(str, OsConstants.O_RDONLY, 0);
            this.streamPool.execute(new Runnable() { // from class: com.topjohnwu.superuser.internal.FileSystemService$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    FileSystemService.lambda$openReadStream$1(OpenFile.this, parcelFileDescriptor);
                }
            });
            return new IOResult();
        } catch (ErrnoException e) {
            openFile.close();
            return new IOResult(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$openReadStream$1(OpenFile openFile, ParcelFileDescriptor parcelFileDescriptor) {
        try {
            openFile.write = FileUtils.createFileDescriptor(parcelFileDescriptor.detachFd());
            do {
            } while (openFile.pread(65536, -1L) > 0);
            if (openFile != null) {
                openFile.close();
            }
        } catch (ErrnoException | IOException unused) {
        }
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public IOResult openWriteStream(String str, final ParcelFileDescriptor parcelFileDescriptor, boolean z) {
        final OpenFile openFile = new OpenFile();
        try {
            openFile.fd = Os.open(str, (z ? OsConstants.O_APPEND : OsConstants.O_TRUNC) | OsConstants.O_CREAT | OsConstants.O_WRONLY, 438);
            this.streamPool.execute(new Runnable() { // from class: com.topjohnwu.superuser.internal.FileSystemService$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    FileSystemService.lambda$openWriteStream$2(OpenFile.this, parcelFileDescriptor);
                }
            });
            return new IOResult();
        } catch (ErrnoException e) {
            openFile.close();
            return new IOResult(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$openWriteStream$2(OpenFile openFile, ParcelFileDescriptor parcelFileDescriptor) {
        try {
            openFile.read = FileUtils.createFileDescriptor(parcelFileDescriptor.detachFd());
            do {
            } while (openFile.pwrite(65536, -1L, false) > 0);
            if (openFile != null) {
                openFile.close();
            }
        } catch (ErrnoException | IOException unused) {
        }
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public void close(int i) {
        this.openFiles.remove(i);
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public IOResult pread(int i, int i2, long j) {
        try {
            return new IOResult(Integer.valueOf(this.openFiles.get(i).pread(i2, j)));
        } catch (ErrnoException | IOException e) {
            return new IOResult(e);
        }
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public IOResult pwrite(int i, int i2, long j) {
        try {
            this.openFiles.get(i).pwrite(i2, j, true);
            return new IOResult();
        } catch (ErrnoException | IOException e) {
            return new IOResult(e);
        }
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public IOResult lseek(int i, long j, int i2) {
        try {
            return new IOResult(Long.valueOf(this.openFiles.get(i).lseek(j, i2)));
        } catch (ErrnoException | IOException e) {
            return new IOResult(e);
        }
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public IOResult size(int i) {
        try {
            return new IOResult(Long.valueOf(this.openFiles.get(i).size()));
        } catch (ErrnoException | IOException e) {
            return new IOResult(e);
        }
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public IOResult ftruncate(int i, long j) {
        try {
            this.openFiles.get(i).ftruncate(j);
            return new IOResult();
        } catch (ErrnoException | IOException e) {
            return new IOResult(e);
        }
    }

    @Override // com.topjohnwu.superuser.internal.IFileSystemService
    public IOResult sync(int i, boolean z) {
        try {
            this.openFiles.get(i).sync(z);
            return new IOResult();
        } catch (ErrnoException | IOException e) {
            return new IOResult(e);
        }
    }
}
