package com.topjohnwu.superuser.internal;

import android.os.Binder;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import java.io.IOException;
/* loaded from: classes2.dex */
class FileContainer {
    private static final String ERROR_MSG = "Requested file was not opened!";
    private int nextHandle = 0;
    private final SparseArray<SparseArray<OpenFile>> files = new SparseArray<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public synchronized OpenFile get(int i) throws IOException {
        OpenFile openFile;
        SparseArray<OpenFile> sparseArray = this.files.get(Binder.getCallingPid());
        if (sparseArray == null) {
            throw new IOException(ERROR_MSG);
        }
        openFile = sparseArray.get(i);
        if (openFile == null) {
            throw new IOException(ERROR_MSG);
        }
        return openFile;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int put(OpenFile openFile) {
        int i;
        int callingPid = Binder.getCallingPid();
        SparseArray<OpenFile> sparseArray = this.files.get(callingPid);
        if (sparseArray == null) {
            sparseArray = new SparseArray<>();
            this.files.put(callingPid, sparseArray);
        }
        i = this.nextHandle;
        this.nextHandle = i + 1;
        sparseArray.append(i, openFile);
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void remove(int i) {
        SparseArray<OpenFile> sparseArray = this.files.get(Binder.getCallingPid());
        if (sparseArray == null) {
            return;
        }
        OpenFile openFile = sparseArray.get(i);
        if (openFile == null) {
            return;
        }
        sparseArray.remove(i);
        synchronized (openFile) {
            openFile.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void pidDied(int i) {
        SparseArray<OpenFile> sparseArray = this.files.get(i);
        if (sparseArray == null) {
            return;
        }
        this.files.remove(i);
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            sparseArray.valueAt(i2).close();
        }
    }
}
