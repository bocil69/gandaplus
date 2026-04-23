package com.topjohnwu.superuser.io;

import androidx.annotation.NonNull;
import com.topjohnwu.superuser.internal.IOFactory;
import com.topjohnwu.superuser.internal.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public final class SuFileInputStream {
    @NonNull
    public static InputStream open(@NonNull String str) throws FileNotFoundException {
        return open(new File(str));
    }

    @NonNull
    public static InputStream open(@NonNull File file) throws FileNotFoundException {
        if (file instanceof SuFile) {
            return IOFactory.fifoIn((SuFile) file);
        }
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            if (!Utils.isMainShellRoot()) {
                throw e;
            }
            return IOFactory.fifoIn(new SuFile(file));
        }
    }

    private SuFileInputStream() {
    }
}
