package com.topjohnwu.superuser.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.topjohnwu.superuser.nio.ExtendedFile;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: classes2.dex */
abstract class FileImpl<T extends ExtendedFile> extends ExtendedFile {
    protected abstract T create(String str);

    protected abstract T[] createArray(int i);

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    @NonNull
    public abstract T getChildFile(String str);

    /* JADX INFO: Access modifiers changed from: protected */
    public FileImpl(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FileImpl(String str, String str2) {
        super(str, str2);
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile, java.io.File
    @NonNull
    public T getAbsoluteFile() {
        return create(getAbsolutePath());
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile, java.io.File
    @NonNull
    public T getCanonicalFile() throws IOException {
        return create(getCanonicalPath());
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile, java.io.File
    @Nullable
    public T getParentFile() {
        return create(getParent());
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile, java.io.File
    @Nullable
    public T[] listFiles() {
        String[] list = list();
        if (list == null) {
            return null;
        }
        int length = list.length;
        T[] createArray = createArray(length);
        for (int i = 0; i < length; i++) {
            createArray[i] = getChildFile(list[i]);
        }
        return createArray;
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile, java.io.File
    @Nullable
    public T[] listFiles(@Nullable FilenameFilter filenameFilter) {
        String[] list = list();
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (filenameFilter == null || filenameFilter.accept(this, str)) {
                arrayList.add(getChildFile(str));
            }
        }
        return (T[]) ((ExtendedFile[]) arrayList.toArray(createArray(0)));
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile, java.io.File
    @Nullable
    public T[] listFiles(@Nullable FileFilter fileFilter) {
        String[] list = list();
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            T childFile = getChildFile(str);
            if (fileFilter == null || fileFilter.accept(childFile)) {
                arrayList.add(childFile);
            }
        }
        return (T[]) ((ExtendedFile[]) arrayList.toArray(createArray(0)));
    }
}
