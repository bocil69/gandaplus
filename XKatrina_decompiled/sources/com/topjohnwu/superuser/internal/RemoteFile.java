package com.topjohnwu.superuser.internal;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.system.OsConstants;
import androidx.annotation.NonNull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes2.dex */
class RemoteFile extends FileImpl<RemoteFile> {
    private final IFileSystemService fs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RemoteFile(IFileSystemService iFileSystemService, String str) {
        super(str);
        this.fs = iFileSystemService;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RemoteFile(IFileSystemService iFileSystemService, String str, String str2) {
        super(str, str2);
        this.fs = iFileSystemService;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.topjohnwu.superuser.internal.FileImpl
    public RemoteFile create(String str) {
        return new RemoteFile(this.fs, str);
    }

    @Override // com.topjohnwu.superuser.internal.FileImpl, com.topjohnwu.superuser.nio.ExtendedFile
    @NonNull
    public RemoteFile getChildFile(String str) {
        return new RemoteFile(this.fs, getPath(), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.topjohnwu.superuser.internal.FileImpl
    public RemoteFile[] createArray(int i) {
        return new RemoteFile[i];
    }

    @Override // java.io.File
    @NonNull
    public String getCanonicalPath() throws IOException {
        try {
            return (String) this.fs.getCanonicalPath(getPath()).tryAndGet();
        } catch (RemoteException e) {
            throw new IOException(e);
        }
    }

    private boolean checkAccess(int i) {
        try {
            return this.fs.checkAccess(getPath(), i);
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // java.io.File
    public boolean canRead() {
        return checkAccess(OsConstants.R_OK);
    }

    @Override // java.io.File
    public boolean canWrite() {
        return checkAccess(OsConstants.W_OK);
    }

    @Override // java.io.File
    public boolean canExecute() {
        return checkAccess(OsConstants.X_OK);
    }

    @Override // java.io.File
    public boolean exists() {
        return checkAccess(OsConstants.F_OK);
    }

    @Override // java.io.File
    public boolean isDirectory() {
        try {
            return this.fs.isDirectory(getPath());
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // java.io.File
    public boolean isFile() {
        try {
            return this.fs.isFile(getPath());
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isBlock() {
        try {
            return OsConstants.S_ISBLK(this.fs.getMode(getPath()));
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isCharacter() {
        try {
            return OsConstants.S_ISCHR(this.fs.getMode(getPath()));
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isSymlink() {
        try {
            return OsConstants.S_ISLNK(this.fs.getMode(getPath()));
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isNamedPipe() {
        try {
            return OsConstants.S_ISFIFO(this.fs.getMode(getPath()));
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isSocket() {
        try {
            return OsConstants.S_ISSOCK(this.fs.getMode(getPath()));
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // java.io.File
    public boolean isHidden() {
        try {
            return this.fs.isHidden(getPath());
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // java.io.File
    public long lastModified() {
        try {
            return this.fs.lastModified(getPath());
        } catch (RemoteException unused) {
            return Long.MIN_VALUE;
        }
    }

    @Override // java.io.File
    public long length() {
        try {
            return this.fs.length(getPath());
        } catch (RemoteException unused) {
            return 0L;
        }
    }

    @Override // java.io.File
    public boolean createNewFile() throws IOException {
        try {
            return ((Boolean) this.fs.createNewFile(getPath()).tryAndGet()).booleanValue();
        } catch (RemoteException e) {
            throw new IOException(e);
        }
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean createNewLink(String str) throws IOException {
        try {
            return ((Boolean) this.fs.createLink(getPath(), str, false).tryAndGet()).booleanValue();
        } catch (RemoteException e) {
            throw new IOException(e);
        }
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean createNewSymlink(String str) throws IOException {
        try {
            return ((Boolean) this.fs.createLink(getPath(), str, true).tryAndGet()).booleanValue();
        } catch (RemoteException e) {
            throw new IOException(e);
        }
    }

    @Override // java.io.File
    public boolean delete() {
        try {
            return this.fs.delete(getPath());
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // java.io.File
    public void deleteOnExit() {
        throw new UnsupportedOperationException("deleteOnExit() is not supported in RemoteFile");
    }

    @Override // java.io.File
    public String[] list() {
        try {
            return this.fs.list(getPath());
        } catch (RemoteException unused) {
            return null;
        }
    }

    @Override // java.io.File
    public boolean mkdir() {
        try {
            return this.fs.mkdir(getPath());
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // java.io.File
    public boolean mkdirs() {
        try {
            return this.fs.mkdirs(getPath());
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // java.io.File
    public boolean renameTo(@NonNull File file) {
        try {
            return this.fs.renameTo(getPath(), file.getAbsolutePath());
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // java.io.File
    public boolean setLastModified(long j) {
        try {
            return this.fs.setLastModified(getPath(), j);
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // java.io.File
    public boolean setReadOnly() {
        try {
            return this.fs.setReadOnly(getPath());
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // java.io.File
    public boolean setWritable(boolean z, boolean z2) {
        try {
            return this.fs.setWritable(getPath(), z, z2);
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // java.io.File
    public boolean setReadable(boolean z, boolean z2) {
        try {
            return this.fs.setReadable(getPath(), z, z2);
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // java.io.File
    public boolean setExecutable(boolean z, boolean z2) {
        try {
            return this.fs.setExecutable(getPath(), z, z2);
        } catch (RemoteException unused) {
            return false;
        }
    }

    @Override // java.io.File
    public long getTotalSpace() {
        try {
            return this.fs.getTotalSpace(getPath());
        } catch (RemoteException unused) {
            return 0L;
        }
    }

    @Override // java.io.File
    public long getFreeSpace() {
        try {
            return this.fs.getFreeSpace(getPath());
        } catch (RemoteException unused) {
            return 0L;
        }
    }

    @Override // java.io.File
    public long getUsableSpace() {
        try {
            return this.fs.getUsableSpace(getPath());
        } catch (RemoteException unused) {
            return 0L;
        }
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    @NonNull
    public InputStream newInputStream() throws IOException {
        ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
        try {
            try {
                this.fs.openReadStream(getPath(), createPipe[1]).checkException();
                createPipe[1].close();
                return new ParcelFileDescriptor.AutoCloseInputStream(createPipe[0]);
            } catch (RemoteException e) {
                createPipe[0].close();
                throw new IOException(e);
            }
        } catch (Throwable th) {
            createPipe[1].close();
            throw th;
        }
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    @NonNull
    public OutputStream newOutputStream(boolean z) throws IOException {
        ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
        try {
            try {
                this.fs.openWriteStream(getPath(), createPipe[0], z).checkException();
                createPipe[0].close();
                return new ParcelFileDescriptor.AutoCloseOutputStream(createPipe[1]);
            } catch (RemoteException e) {
                createPipe[1].close();
                throw new IOException(e);
            }
        } catch (Throwable th) {
            createPipe[0].close();
            throw th;
        }
    }
}
