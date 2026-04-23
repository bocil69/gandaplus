package com.topjohnwu.superuser.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IFileSystemService extends IInterface {
    public static final String DESCRIPTOR = "com.topjohnwu.superuser.internal.IFileSystemService";

    /* loaded from: classes2.dex */
    public static class Default implements IFileSystemService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public boolean checkAccess(String str, int i) throws RemoteException {
            return false;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public void close(int i) throws RemoteException {
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public IOResult createLink(String str, String str2, boolean z) throws RemoteException {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public IOResult createNewFile(String str) throws RemoteException {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public boolean delete(String str) throws RemoteException {
            return false;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public IOResult ftruncate(int i, long j) throws RemoteException {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public IOResult getCanonicalPath(String str) throws RemoteException {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public long getFreeSpace(String str) throws RemoteException {
            return 0L;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public int getMode(String str) throws RemoteException {
            return 0;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public long getTotalSpace(String str) throws RemoteException {
            return 0L;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public long getUsableSpace(String str) throws RemoteException {
            return 0L;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public boolean isDirectory(String str) throws RemoteException {
            return false;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public boolean isFile(String str) throws RemoteException {
            return false;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public boolean isHidden(String str) throws RemoteException {
            return false;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public long lastModified(String str) throws RemoteException {
            return 0L;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public long length(String str) throws RemoteException {
            return 0L;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public String[] list(String str) throws RemoteException {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public IOResult lseek(int i, long j, int i2) throws RemoteException {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public boolean mkdir(String str) throws RemoteException {
            return false;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public boolean mkdirs(String str) throws RemoteException {
            return false;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public IOResult openChannel(String str, int i, String str2) throws RemoteException {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public IOResult openReadStream(String str, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public IOResult openWriteStream(String str, ParcelFileDescriptor parcelFileDescriptor, boolean z) throws RemoteException {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public IOResult pread(int i, int i2, long j) throws RemoteException {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public IOResult pwrite(int i, int i2, long j) throws RemoteException {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public void register(IBinder iBinder) throws RemoteException {
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public boolean renameTo(String str, String str2) throws RemoteException {
            return false;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public boolean setExecutable(String str, boolean z, boolean z2) throws RemoteException {
            return false;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public boolean setLastModified(String str, long j) throws RemoteException {
            return false;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public boolean setReadOnly(String str) throws RemoteException {
            return false;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public boolean setReadable(String str, boolean z, boolean z2) throws RemoteException {
            return false;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public boolean setWritable(String str, boolean z, boolean z2) throws RemoteException {
            return false;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public IOResult size(int i) throws RemoteException {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IFileSystemService
        public IOResult sync(int i, boolean z) throws RemoteException {
            return null;
        }
    }

    boolean checkAccess(String str, int i) throws RemoteException;

    void close(int i) throws RemoteException;

    IOResult createLink(String str, String str2, boolean z) throws RemoteException;

    IOResult createNewFile(String str) throws RemoteException;

    boolean delete(String str) throws RemoteException;

    IOResult ftruncate(int i, long j) throws RemoteException;

    IOResult getCanonicalPath(String str) throws RemoteException;

    long getFreeSpace(String str) throws RemoteException;

    int getMode(String str) throws RemoteException;

    long getTotalSpace(String str) throws RemoteException;

    long getUsableSpace(String str) throws RemoteException;

    boolean isDirectory(String str) throws RemoteException;

    boolean isFile(String str) throws RemoteException;

    boolean isHidden(String str) throws RemoteException;

    long lastModified(String str) throws RemoteException;

    long length(String str) throws RemoteException;

    String[] list(String str) throws RemoteException;

    IOResult lseek(int i, long j, int i2) throws RemoteException;

    boolean mkdir(String str) throws RemoteException;

    boolean mkdirs(String str) throws RemoteException;

    IOResult openChannel(String str, int i, String str2) throws RemoteException;

    IOResult openReadStream(String str, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    IOResult openWriteStream(String str, ParcelFileDescriptor parcelFileDescriptor, boolean z) throws RemoteException;

    IOResult pread(int i, int i2, long j) throws RemoteException;

    IOResult pwrite(int i, int i2, long j) throws RemoteException;

    void register(IBinder iBinder) throws RemoteException;

    boolean renameTo(String str, String str2) throws RemoteException;

    boolean setExecutable(String str, boolean z, boolean z2) throws RemoteException;

    boolean setLastModified(String str, long j) throws RemoteException;

    boolean setReadOnly(String str) throws RemoteException;

    boolean setReadable(String str, boolean z, boolean z2) throws RemoteException;

    boolean setWritable(String str, boolean z, boolean z2) throws RemoteException;

    IOResult size(int i) throws RemoteException;

    IOResult sync(int i, boolean z) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IFileSystemService {
        static final int TRANSACTION_checkAccess = 18;
        static final int TRANSACTION_close = 28;
        static final int TRANSACTION_createLink = 23;
        static final int TRANSACTION_createNewFile = 7;
        static final int TRANSACTION_delete = 8;
        static final int TRANSACTION_ftruncate = 33;
        static final int TRANSACTION_getCanonicalPath = 1;
        static final int TRANSACTION_getFreeSpace = 20;
        static final int TRANSACTION_getMode = 22;
        static final int TRANSACTION_getTotalSpace = 19;
        static final int TRANSACTION_getUsableSpace = 21;
        static final int TRANSACTION_isDirectory = 2;
        static final int TRANSACTION_isFile = 3;
        static final int TRANSACTION_isHidden = 4;
        static final int TRANSACTION_lastModified = 5;
        static final int TRANSACTION_length = 6;
        static final int TRANSACTION_list = 9;
        static final int TRANSACTION_lseek = 31;
        static final int TRANSACTION_mkdir = 10;
        static final int TRANSACTION_mkdirs = 11;
        static final int TRANSACTION_openChannel = 25;
        static final int TRANSACTION_openReadStream = 26;
        static final int TRANSACTION_openWriteStream = 27;
        static final int TRANSACTION_pread = 29;
        static final int TRANSACTION_pwrite = 30;
        static final int TRANSACTION_register = 24;
        static final int TRANSACTION_renameTo = 12;
        static final int TRANSACTION_setExecutable = 17;
        static final int TRANSACTION_setLastModified = 13;
        static final int TRANSACTION_setReadOnly = 14;
        static final int TRANSACTION_setReadable = 16;
        static final int TRANSACTION_setWritable = 15;
        static final int TRANSACTION_size = 32;
        static final int TRANSACTION_sync = 34;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IFileSystemService.DESCRIPTOR);
        }

        public static IFileSystemService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IFileSystemService.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IFileSystemService)) {
                return (IFileSystemService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IFileSystemService.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IFileSystemService.DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    IOResult canonicalPath = getCanonicalPath(parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, canonicalPath, 1);
                    break;
                case 2:
                    boolean isDirectory = isDirectory(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(isDirectory ? 1 : 0);
                    break;
                case 3:
                    boolean isFile = isFile(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(isFile ? 1 : 0);
                    break;
                case 4:
                    boolean isHidden = isHidden(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(isHidden ? 1 : 0);
                    break;
                case 5:
                    long lastModified = lastModified(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(lastModified);
                    break;
                case 6:
                    long length = length(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(length);
                    break;
                case 7:
                    IOResult createNewFile = createNewFile(parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, createNewFile, 1);
                    break;
                case 8:
                    boolean delete = delete(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(delete ? 1 : 0);
                    break;
                case 9:
                    String[] list = list(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStringArray(list);
                    break;
                case 10:
                    boolean mkdir = mkdir(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(mkdir ? 1 : 0);
                    break;
                case 11:
                    boolean mkdirs = mkdirs(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(mkdirs ? 1 : 0);
                    break;
                case 12:
                    boolean renameTo = renameTo(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(renameTo ? 1 : 0);
                    break;
                case 13:
                    boolean lastModified2 = setLastModified(parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(lastModified2 ? 1 : 0);
                    break;
                case 14:
                    boolean readOnly = setReadOnly(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(readOnly ? 1 : 0);
                    break;
                case 15:
                    boolean writable = setWritable(parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(writable ? 1 : 0);
                    break;
                case 16:
                    boolean readable = setReadable(parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(readable ? 1 : 0);
                    break;
                case 17:
                    boolean executable = setExecutable(parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(executable ? 1 : 0);
                    break;
                case 18:
                    boolean checkAccess = checkAccess(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(checkAccess ? 1 : 0);
                    break;
                case 19:
                    long totalSpace = getTotalSpace(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(totalSpace);
                    break;
                case 20:
                    long freeSpace = getFreeSpace(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(freeSpace);
                    break;
                case 21:
                    long usableSpace = getUsableSpace(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(usableSpace);
                    break;
                case 22:
                    int mode = getMode(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(mode);
                    break;
                case 23:
                    IOResult createLink = createLink(parcel.readString(), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, createLink, 1);
                    break;
                case 24:
                    register(parcel.readStrongBinder());
                    break;
                case 25:
                    IOResult openChannel = openChannel(parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, openChannel, 1);
                    break;
                case 26:
                    IOResult openReadStream = openReadStream(parcel.readString(), (ParcelFileDescriptor) _Parcel.readTypedObject(parcel, ParcelFileDescriptor.CREATOR));
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, openReadStream, 1);
                    break;
                case 27:
                    IOResult openWriteStream = openWriteStream(parcel.readString(), (ParcelFileDescriptor) _Parcel.readTypedObject(parcel, ParcelFileDescriptor.CREATOR), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, openWriteStream, 1);
                    break;
                case 28:
                    close(parcel.readInt());
                    break;
                case 29:
                    IOResult pread = pread(parcel.readInt(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, pread, 1);
                    break;
                case 30:
                    IOResult pwrite = pwrite(parcel.readInt(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, pwrite, 1);
                    break;
                case 31:
                    IOResult lseek = lseek(parcel.readInt(), parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, lseek, 1);
                    break;
                case 32:
                    IOResult size = size(parcel.readInt());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, size, 1);
                    break;
                case 33:
                    IOResult ftruncate = ftruncate(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, ftruncate, 1);
                    break;
                case 34:
                    IOResult sync = sync(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    _Parcel.writeTypedObject(parcel2, sync, 1);
                    break;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements IFileSystemService {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IFileSystemService.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public IOResult getCanonicalPath(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return (IOResult) _Parcel.readTypedObject(obtain2, IOResult.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public boolean isDirectory(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public boolean isFile(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public boolean isHidden(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public long lastModified(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public long length(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public IOResult createNewFile(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return (IOResult) _Parcel.readTypedObject(obtain2, IOResult.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public boolean delete(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public String[] list(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public boolean mkdir(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public boolean mkdirs(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public boolean renameTo(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public boolean setLastModified(String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public boolean setReadOnly(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public boolean setWritable(String str, boolean z, boolean z2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public boolean setReadable(String str, boolean z, boolean z2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public boolean setExecutable(String str, boolean z, boolean z2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public boolean checkAccess(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public long getTotalSpace(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public long getFreeSpace(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public long getUsableSpace(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public int getMode(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public IOResult createLink(String str, String str2, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return (IOResult) _Parcel.readTypedObject(obtain2, IOResult.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public void register(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(24, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public IOResult openChannel(String str, int i, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return (IOResult) _Parcel.readTypedObject(obtain2, IOResult.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public IOResult openReadStream(String str, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    _Parcel.writeTypedObject(obtain, parcelFileDescriptor, 0);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return (IOResult) _Parcel.readTypedObject(obtain2, IOResult.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public IOResult openWriteStream(String str, ParcelFileDescriptor parcelFileDescriptor, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeString(str);
                    _Parcel.writeTypedObject(obtain, parcelFileDescriptor, 0);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return (IOResult) _Parcel.readTypedObject(obtain2, IOResult.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public void close(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(28, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public IOResult pread(int i, int i2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    return (IOResult) _Parcel.readTypedObject(obtain2, IOResult.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public IOResult pwrite(int i, int i2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return (IOResult) _Parcel.readTypedObject(obtain2, IOResult.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public IOResult lseek(int i, long j, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeInt(i2);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    return (IOResult) _Parcel.readTypedObject(obtain2, IOResult.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public IOResult size(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    return (IOResult) _Parcel.readTypedObject(obtain2, IOResult.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public IOResult ftruncate(int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                    return (IOResult) _Parcel.readTypedObject(obtain2, IOResult.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IFileSystemService
            public IOResult sync(int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IFileSystemService.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return (IOResult) _Parcel.readTypedObject(obtain2, IOResult.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class _Parcel {
        /* JADX INFO: Access modifiers changed from: private */
        public static <T> T readTypedObject(Parcel parcel, Parcelable.Creator<T> creator) {
            if (parcel.readInt() != 0) {
                return creator.createFromParcel(parcel);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static <T extends Parcelable> void writeTypedObject(Parcel parcel, T t, int i) {
            if (t != null) {
                parcel.writeInt(1);
                t.writeToParcel(parcel, i);
                return;
            }
            parcel.writeInt(0);
        }
    }
}
