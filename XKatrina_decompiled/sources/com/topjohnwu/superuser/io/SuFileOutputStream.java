package com.topjohnwu.superuser.io;

import androidx.annotation.NonNull;
import com.topjohnwu.superuser.internal.IOFactory;
import com.topjohnwu.superuser.internal.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
/* loaded from: classes2.dex */
public final class SuFileOutputStream {
    @NonNull
    public static OutputStream open(@NonNull String str) throws FileNotFoundException {
        return open(new File(str), false);
    }

    @NonNull
    public static OutputStream open(@NonNull String str, boolean z) throws FileNotFoundException {
        return open(new File(str), z);
    }

    @NonNull
    public static OutputStream open(@NonNull File file) throws FileNotFoundException {
        return open(file, false);
    }

    @NonNull
    public static OutputStream open(@NonNull File file, boolean z) throws FileNotFoundException {
        if (file instanceof SuFile) {
            return IOFactory.fifoOut((SuFile) file, z);
        }
        try {
            return new FileOutputStream(file, z);
        } catch (FileNotFoundException e) {
            if (!Utils.isMainShellRoot()) {
                throw e;
            }
            return IOFactory.fifoOut(new SuFile(file), z);
        }
    }

    private SuFileOutputStream() {
    }
}
