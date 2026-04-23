package com.topjohnwu.superuser.internal;

import android.annotation.SuppressLint;
import android.os.Build;
import android.system.ErrnoException;
import android.system.Int64Ref;
import android.system.Os;
import android.system.OsConstants;
import android.util.ArraySet;
import android.util.MutableLong;
import androidx.annotation.RequiresApi;
import com.topjohnwu.superuser.nio.FileSystemManager;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
@SuppressLint({"DiscouragedPrivateApi"})
/* loaded from: classes2.dex */
public class FileUtils {
    private static Object os;
    private static Method sendfile;
    private static AccessibleObject setFd;
    private static Method splice;

    FileUtils() {
    }

    /* loaded from: classes2.dex */
    static class Flag {
        boolean append;
        boolean create;
        boolean read;
        boolean truncate;
        boolean write;

        Flag() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int modeToPosix(int i) {
        int i2;
        if ((i & FileSystemManager.MODE_READ_WRITE) == 805306368) {
            i2 = OsConstants.O_RDWR;
        } else if ((i & 536870912) == 536870912) {
            i2 = OsConstants.O_WRONLY;
        } else if ((i & 268435456) == 268435456) {
            i2 = OsConstants.O_RDONLY;
        } else {
            throw new IllegalArgumentException("Bad mode: " + i);
        }
        if ((i & FileSystemManager.MODE_CREATE) == 134217728) {
            i2 |= OsConstants.O_CREAT;
        }
        if ((i & FileSystemManager.MODE_TRUNCATE) == 67108864) {
            i2 |= OsConstants.O_TRUNC;
        }
        return (i & FileSystemManager.MODE_APPEND) == 33554432 ? i2 | OsConstants.O_APPEND : i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(api = 26)
    public static Set<OpenOption> modeToOptions(int i) {
        ArraySet arraySet = new ArraySet();
        if ((i & FileSystemManager.MODE_READ_WRITE) == 805306368) {
            arraySet.add(StandardOpenOption.READ);
            arraySet.add(StandardOpenOption.WRITE);
        } else if ((i & 536870912) == 536870912) {
            arraySet.add(StandardOpenOption.WRITE);
        } else if ((i & 268435456) == 268435456) {
            arraySet.add(StandardOpenOption.READ);
        } else {
            throw new IllegalArgumentException("Bad mode: " + i);
        }
        if ((i & FileSystemManager.MODE_CREATE) == 134217728) {
            arraySet.add(StandardOpenOption.CREATE);
        }
        if ((i & FileSystemManager.MODE_TRUNCATE) == 67108864) {
            arraySet.add(StandardOpenOption.TRUNCATE_EXISTING);
        }
        if ((i & FileSystemManager.MODE_APPEND) == 33554432) {
            arraySet.add(StandardOpenOption.APPEND);
        }
        return arraySet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Flag modeToFlag(int i) {
        Flag flag = new Flag();
        if ((i & FileSystemManager.MODE_READ_WRITE) == 805306368) {
            flag.read = true;
            flag.write = true;
        } else if ((i & 536870912) == 536870912) {
            flag.write = true;
        } else if ((i & 268435456) == 268435456) {
            flag.read = true;
        } else {
            throw new IllegalArgumentException("Bad mode: " + i);
        }
        if ((i & FileSystemManager.MODE_CREATE) == 134217728) {
            flag.create = true;
        }
        if ((i & FileSystemManager.MODE_TRUNCATE) == 67108864) {
            flag.truncate = true;
        }
        if ((i & FileSystemManager.MODE_APPEND) == 33554432) {
            flag.append = true;
        }
        if (flag.append && flag.read) {
            throw new IllegalArgumentException("READ + APPEND not allowed");
        }
        if (flag.append && flag.truncate) {
            throw new IllegalArgumentException("APPEND + TRUNCATE not allowed");
        }
        return flag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(api = 28)
    public static long splice(FileDescriptor fileDescriptor, Int64Ref int64Ref, FileDescriptor fileDescriptor2, Int64Ref int64Ref2, long j, int i) throws ErrnoException {
        try {
            if (splice == null) {
                splice = Os.class.getMethod("splice", FileDescriptor.class, Int64Ref.class, FileDescriptor.class, Int64Ref.class, Long.TYPE, Integer.TYPE);
            }
            return ((Long) splice.invoke(null, fileDescriptor, int64Ref, fileDescriptor2, int64Ref2, Long.valueOf(j), Integer.valueOf(i))).longValue();
        } catch (InvocationTargetException e) {
            throw ((ErrnoException) e.getTargetException());
        } catch (ReflectiveOperationException unused) {
            throw new ErrnoException("splice", OsConstants.ENOSYS);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long sendfile(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, MutableLong mutableLong, long j) throws ErrnoException {
        if (Build.VERSION.SDK_INT >= 28) {
            Int64Ref int64Ref = mutableLong != null ? new Int64Ref(mutableLong.value) : null;
            long sendfile2 = Os.sendfile(fileDescriptor, fileDescriptor2, int64Ref, j);
            if (int64Ref != null) {
                mutableLong.value = int64Ref.value;
            }
            return sendfile2;
        }
        try {
            if (os == null) {
                os = Class.forName("libcore.io.Libcore").getField("os").get(null);
            }
            if (sendfile == null) {
                sendfile = os.getClass().getMethod("sendfile", FileDescriptor.class, FileDescriptor.class, MutableLong.class, Long.TYPE);
            }
            return ((Long) sendfile.invoke(os, fileDescriptor, fileDescriptor2, mutableLong, Long.valueOf(j))).longValue();
        } catch (InvocationTargetException e) {
            throw ((ErrnoException) e.getTargetException());
        } catch (ReflectiveOperationException unused) {
            throw new ErrnoException("sendfile", OsConstants.ENOSYS);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File createTempFIFO() throws ErrnoException, IOException {
        File createTempFile = File.createTempFile("libsu-fifo-", null);
        createTempFile.delete();
        Os.mkfifo(createTempFile.getPath(), 420);
        return createTempFile;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FileDescriptor createFileDescriptor(int i) {
        if (setFd == null) {
            try {
                try {
                    setFd = FileDescriptor.class.getDeclaredConstructor(Integer.TYPE);
                } catch (NoSuchMethodException unused) {
                }
            } catch (NoSuchMethodException unused2) {
                setFd = FileDescriptor.class.getDeclaredMethod("setInt$", Integer.TYPE);
            }
            setFd.setAccessible(true);
        }
        try {
            AccessibleObject accessibleObject = setFd;
            if (accessibleObject instanceof Constructor) {
                return (FileDescriptor) ((Constructor) accessibleObject).newInstance(Integer.valueOf(i));
            }
            FileDescriptor fileDescriptor = new FileDescriptor();
            ((Method) setFd).invoke(fileDescriptor, Integer.valueOf(i));
            return fileDescriptor;
        } catch (ReflectiveOperationException unused3) {
            return null;
        }
    }
}
