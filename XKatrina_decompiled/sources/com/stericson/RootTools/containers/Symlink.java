package com.stericson.RootTools.containers;

import java.io.File;
/* loaded from: classes6.dex */
public class Symlink {
    protected final File file;
    protected final File symlinkPath;

    public Symlink(File file, File file2) {
        this.file = file;
        this.symlinkPath = file2;
    }

    public File getFile() {
        return this.file;
    }

    public File getSymlinkPath() {
        return this.symlinkPath;
    }
}
