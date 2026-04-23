package com.topjohnwu.superuser.nio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
/* loaded from: classes2.dex */
public abstract class ExtendedFile extends File {
    public abstract boolean createNewLink(String str) throws IOException;

    public abstract boolean createNewSymlink(String str) throws IOException;

    @Override // java.io.File
    @NonNull
    public abstract ExtendedFile getAbsoluteFile();

    @Override // java.io.File
    @NonNull
    public abstract ExtendedFile getCanonicalFile() throws IOException;

    @NonNull
    public abstract ExtendedFile getChildFile(String str);

    @Override // java.io.File
    @Nullable
    public abstract ExtendedFile getParentFile();

    public abstract boolean isBlock();

    public abstract boolean isCharacter();

    public abstract boolean isNamedPipe();

    public abstract boolean isSocket();

    public abstract boolean isSymlink();

    @Override // java.io.File
    @Nullable
    public abstract ExtendedFile[] listFiles();

    @Override // java.io.File
    @Nullable
    public abstract ExtendedFile[] listFiles(@Nullable FileFilter fileFilter);

    @Override // java.io.File
    @Nullable
    public abstract ExtendedFile[] listFiles(@Nullable FilenameFilter filenameFilter);

    @NonNull
    public abstract InputStream newInputStream() throws IOException;

    @NonNull
    public abstract OutputStream newOutputStream(boolean z) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public ExtendedFile(@NonNull String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ExtendedFile(@Nullable String str, @NonNull String str2) {
        super(str, str2);
    }

    protected ExtendedFile(@Nullable File file, @NonNull String str) {
        super(file, str);
    }

    protected ExtendedFile(@NonNull URI uri) {
        super(uri);
    }

    @NonNull
    public final OutputStream newOutputStream() throws IOException {
        return newOutputStream(false);
    }
}
