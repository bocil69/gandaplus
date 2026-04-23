package com.topjohnwu.superuser.internal;

import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import androidx.annotation.NonNull;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes2.dex */
class LocalFile extends FileImpl<LocalFile> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalFile(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalFile(String str, String str2) {
        super(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.topjohnwu.superuser.internal.FileImpl
    public LocalFile create(String str) {
        return new LocalFile(str);
    }

    @Override // com.topjohnwu.superuser.internal.FileImpl, com.topjohnwu.superuser.nio.ExtendedFile
    @NonNull
    public LocalFile getChildFile(String str) {
        return new LocalFile(getPath(), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.topjohnwu.superuser.internal.FileImpl
    public LocalFile[] createArray(int i) {
        return new LocalFile[i];
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isBlock() {
        try {
            return OsConstants.S_ISBLK(Os.lstat(getPath()).st_mode);
        } catch (ErrnoException unused) {
            return false;
        }
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isCharacter() {
        try {
            return OsConstants.S_ISCHR(Os.lstat(getPath()).st_mode);
        } catch (ErrnoException unused) {
            return false;
        }
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isSymlink() {
        try {
            return OsConstants.S_ISLNK(Os.lstat(getPath()).st_mode);
        } catch (ErrnoException unused) {
            return false;
        }
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isNamedPipe() {
        try {
            return OsConstants.S_ISFIFO(Os.lstat(getPath()).st_mode);
        } catch (ErrnoException unused) {
            return false;
        }
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isSocket() {
        try {
            return OsConstants.S_ISSOCK(Os.lstat(getPath()).st_mode);
        } catch (ErrnoException unused) {
            return false;
        }
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    @NonNull
    public InputStream newInputStream() throws IOException {
        return new FileInputStream(this);
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    @NonNull
    public OutputStream newOutputStream(boolean z) throws IOException {
        return new FileOutputStream(this, z);
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean createNewLink(String str) throws IOException {
        return createLink(str, false);
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean createNewSymlink(String str) throws IOException {
        return createLink(str, true);
    }

    private boolean createLink(String str, boolean z) throws IOException {
        try {
            if (z) {
                Os.symlink(str, getPath());
                return true;
            }
            Os.link(str, getPath());
            return true;
        } catch (ErrnoException e) {
            if (e.errno == OsConstants.EEXIST) {
                return false;
            }
            throw new IOException(e);
        }
    }
}
