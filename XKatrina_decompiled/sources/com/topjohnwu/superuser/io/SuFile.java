package com.topjohnwu.superuser.io;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.location.LocationRequestCompat;
import com.topjohnwu.superuser.Shell;
import com.topjohnwu.superuser.ShellUtils;
import com.topjohnwu.superuser.internal.IOFactory;
import com.topjohnwu.superuser.internal.Utils;
import com.topjohnwu.superuser.nio.ExtendedFile;
import com.topjohnwu.superuser.nio.FileSystemManager;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
/* loaded from: classes2.dex */
public class SuFile extends ExtendedFile {
    private final String escapedPath;
    private Shell mShell;

    @Override // com.topjohnwu.superuser.nio.ExtendedFile, java.io.File
    @NonNull
    public SuFile getAbsoluteFile() {
        return this;
    }

    public static ExtendedFile open(String str) {
        return Utils.isMainShellRoot() ? new SuFile(str) : FileSystemManager.getLocal().getFile(str);
    }

    public static ExtendedFile open(String str, String str2) {
        return Utils.isMainShellRoot() ? new SuFile(str, str2) : FileSystemManager.getLocal().getFile(str, str2);
    }

    public static ExtendedFile open(File file, String str) {
        return Utils.isMainShellRoot() ? new SuFile(file, str) : FileSystemManager.getLocal().getFile(file.getPath(), str);
    }

    public static ExtendedFile open(URI uri) {
        return Utils.isMainShellRoot() ? new SuFile(uri) : FileSystemManager.getLocal().getFile(new File(uri).getPath());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SuFile(@NonNull File file) {
        super(file.getAbsolutePath());
        this.escapedPath = ShellUtils.escapedString(getPath());
    }

    public SuFile(String str) {
        this(new File(str));
    }

    public SuFile(String str, String str2) {
        this(new File(str, str2));
    }

    public SuFile(File file, String str) {
        this(file.getAbsolutePath(), str);
    }

    public SuFile(URI uri) {
        this(new File(uri));
    }

    private SuFile create(String str) {
        SuFile suFile = new SuFile(str);
        suFile.mShell = this.mShell;
        return suFile;
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    @NonNull
    public SuFile getChildFile(String str) {
        SuFile suFile = new SuFile(this, str);
        suFile.mShell = this.mShell;
        return suFile;
    }

    private String cmd(String str) {
        return ShellUtils.fastCmd(getShell(), str.replace("@@", this.escapedPath));
    }

    private boolean cmdBool(String str) {
        return ShellUtils.fastCmdResult(getShell(), str.replace("@@", this.escapedPath));
    }

    public void setShell(Shell shell) {
        this.mShell = shell;
    }

    public Shell getShell() {
        Shell shell = this.mShell;
        return shell == null ? Shell.getShell() : shell;
    }

    @NonNull
    public String getEscapedPath() {
        return this.escapedPath;
    }

    @Override // java.io.File
    public boolean canExecute() {
        return cmdBool("[ -x @@ ]");
    }

    @Override // java.io.File
    public boolean canRead() {
        return cmdBool("[ -r @@ ]");
    }

    @Override // java.io.File
    public boolean canWrite() {
        return cmdBool("[ -w @@ ]");
    }

    @Override // java.io.File
    public boolean createNewFile() {
        return cmdBool("[ ! -e @@ ] && echo -n > @@");
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean createNewLink(String str) {
        String escapedString = ShellUtils.escapedString(str);
        return cmdBool("[ ! -d " + escapedString + " ] && ln @@ " + escapedString);
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean createNewSymlink(String str) {
        String escapedString = ShellUtils.escapedString(str);
        return cmdBool("[ ! -d " + escapedString + " ] && ln -s @@ " + escapedString);
    }

    @Override // java.io.File
    public boolean delete() {
        return cmdBool("rm -f @@ || rmdir -f @@");
    }

    public boolean deleteRecursive() {
        return cmdBool("rm -rf @@");
    }

    public boolean clear() {
        return cmdBool("echo -n > @@");
    }

    @Override // java.io.File
    public void deleteOnExit() {
        throw new UnsupportedOperationException("Unsupported SuFile operation");
    }

    @Override // java.io.File
    public boolean exists() {
        return cmdBool("[ -e @@ ]");
    }

    @Override // java.io.File
    @NonNull
    public String getAbsolutePath() {
        return getPath();
    }

    @Override // java.io.File
    @NonNull
    public String getCanonicalPath() {
        String cmd = cmd("readlink -f @@");
        return cmd.isEmpty() ? getPath() : cmd;
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile, java.io.File
    @NonNull
    public SuFile getCanonicalFile() {
        return create(getCanonicalPath());
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile, java.io.File
    public SuFile getParentFile() {
        String parent = getParent();
        if (parent == null) {
            return null;
        }
        return create(parent);
    }

    private long statFS(String str) {
        String[] split = cmd("stat -fc '%S " + str + "' @@").split(" ");
        if (split.length != 2) {
            return LocationRequestCompat.PASSIVE_INTERVAL;
        }
        try {
            return Long.parseLong(split[0]) * Long.parseLong(split[1]);
        } catch (NumberFormatException unused) {
            return LocationRequestCompat.PASSIVE_INTERVAL;
        }
    }

    @Override // java.io.File
    public long getFreeSpace() {
        return statFS("%f");
    }

    @Override // java.io.File
    public long getTotalSpace() {
        return statFS("%b");
    }

    @Override // java.io.File
    public long getUsableSpace() {
        return statFS("%a");
    }

    @Override // java.io.File
    public boolean isDirectory() {
        return cmdBool("[ -d @@ ]");
    }

    @Override // java.io.File
    public boolean isFile() {
        return cmdBool("[ -f @@ ]");
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isBlock() {
        return cmdBool("[ -b @@ ]");
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isCharacter() {
        return cmdBool("[ -c @@ ]");
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isSymlink() {
        return cmdBool("[ -L @@ ]");
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isNamedPipe() {
        return cmdBool("[ -p @@ ]");
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    public boolean isSocket() {
        return cmdBool("[ -S @@ ]");
    }

    @Override // java.io.File
    public long lastModified() {
        try {
            return Long.parseLong(cmd("stat -c '%Y' @@")) * 1000;
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    @Override // java.io.File
    public long length() {
        try {
            return Long.parseLong(cmd("stat -c '%s' @@"));
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    @Override // java.io.File
    public boolean mkdir() {
        return cmdBool("mkdir @@");
    }

    @Override // java.io.File
    public boolean mkdirs() {
        return cmdBool("mkdir -p @@");
    }

    @Override // java.io.File
    public boolean renameTo(File file) {
        return ShellUtils.fastCmdResult(getShell(), "mv -f " + this.escapedPath + " " + ShellUtils.escapedString(file.getAbsolutePath()));
    }

    private boolean setPerms(boolean z, boolean z2, int i) {
        char[] charArray = cmd("stat -c '%a' @@").toCharArray();
        int i2 = 0;
        if (charArray.length != 3) {
            return false;
        }
        while (i2 < 3) {
            int i3 = charArray[i2] - '0';
            charArray[i2] = (char) (((!z || (z2 && i2 != 0)) ? i3 & (i ^ (-1)) : i3 | i) + 48);
            i2++;
        }
        return cmdBool("chmod " + new String(charArray) + " @@");
    }

    @Override // java.io.File
    public boolean setExecutable(boolean z, boolean z2) {
        return setPerms(z, z2, 1);
    }

    @Override // java.io.File
    public boolean setReadable(boolean z, boolean z2) {
        return setPerms(z, z2, 4);
    }

    @Override // java.io.File
    public boolean setWritable(boolean z, boolean z2) {
        return setPerms(z, z2, 2);
    }

    @Override // java.io.File
    public boolean setReadOnly() {
        return setWritable(false, false) && setExecutable(false, false);
    }

    @Override // java.io.File
    public boolean setLastModified(long j) {
        String format = new SimpleDateFormat("yyyyMMddHHmm", Locale.US).format(new Date(j));
        return cmdBool("[ -e @@ ] && touch -t " + format + " @@");
    }

    @Override // java.io.File
    public String[] list() {
        return list(null);
    }

    @Override // java.io.File
    public String[] list(FilenameFilter filenameFilter) {
        if (isDirectory()) {
            List<String> out = getShell().newJob().add("ls -a " + this.escapedPath).to(new LinkedList(), null).exec().getOut();
            ListIterator<String> listIterator = out.listIterator();
            while (listIterator.hasNext()) {
                String next = listIterator.next();
                if (next.equals(".") || next.equals("..") || (filenameFilter != null && !filenameFilter.accept(this, next))) {
                    listIterator.remove();
                }
            }
            return (String[]) out.toArray(new String[0]);
        }
        return null;
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile, java.io.File
    @Nullable
    public SuFile[] listFiles() {
        String[] list;
        if (isDirectory() && (list = list()) != null) {
            int length = list.length;
            SuFile[] suFileArr = new SuFile[length];
            for (int i = 0; i < length; i++) {
                suFileArr[i] = getChildFile(list[i]);
            }
            return suFileArr;
        }
        return null;
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile, java.io.File
    @Nullable
    public SuFile[] listFiles(FilenameFilter filenameFilter) {
        String[] list;
        if (isDirectory() && (list = list(filenameFilter)) != null) {
            int length = list.length;
            SuFile[] suFileArr = new SuFile[length];
            for (int i = 0; i < length; i++) {
                suFileArr[i] = getChildFile(list[i]);
            }
            return suFileArr;
        }
        return null;
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile, java.io.File
    @Nullable
    public SuFile[] listFiles(FileFilter fileFilter) {
        String[] list = list();
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            SuFile childFile = getChildFile(str);
            if (fileFilter == null || fileFilter.accept(childFile)) {
                arrayList.add(childFile);
            }
        }
        return (SuFile[]) arrayList.toArray(new SuFile[0]);
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    @NonNull
    public InputStream newInputStream() throws IOException {
        return IOFactory.fifoIn(this);
    }

    @Override // com.topjohnwu.superuser.nio.ExtendedFile
    @NonNull
    public OutputStream newOutputStream(boolean z) throws IOException {
        return IOFactory.fifoOut(this, z);
    }
}
