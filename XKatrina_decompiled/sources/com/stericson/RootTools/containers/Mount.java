package com.stericson.RootTools.containers;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
/* loaded from: classes6.dex */
public class Mount {
    final File mDevice;
    final Set<String> mFlags;
    final File mMountPoint;
    final String mType;

    public Mount(File file, File file2, String str, String str2) {
        this.mDevice = file;
        this.mMountPoint = file2;
        this.mType = str;
        this.mFlags = new LinkedHashSet(Arrays.asList(str2.split(",")));
    }

    public File getDevice() {
        return this.mDevice;
    }

    public File getMountPoint() {
        return this.mMountPoint;
    }

    public String getType() {
        return this.mType;
    }

    public Set<String> getFlags() {
        return this.mFlags;
    }

    public String toString() {
        return String.format("%s on %s type %s %s", this.mDevice, this.mMountPoint, this.mType, this.mFlags);
    }
}
