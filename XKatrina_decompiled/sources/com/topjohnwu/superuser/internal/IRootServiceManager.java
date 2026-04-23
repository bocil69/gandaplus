package com.topjohnwu.superuser.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
/* loaded from: classes2.dex */
public interface IRootServiceManager extends IInterface {
    public static final String DESCRIPTOR = "com.topjohnwu.superuser.internal.IRootServiceManager";

    /* loaded from: classes2.dex */
    public static class Default implements IRootServiceManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IRootServiceManager
        public IBinder bind(Intent intent) throws RemoteException {
            return null;
        }

        @Override // com.topjohnwu.superuser.internal.IRootServiceManager
        public void broadcast(int i) throws RemoteException {
        }

        @Override // com.topjohnwu.superuser.internal.IRootServiceManager
        public void connect(IBinder iBinder) throws RemoteException {
        }

        @Override // com.topjohnwu.superuser.internal.IRootServiceManager
        public void stop(ComponentName componentName, int i) throws RemoteException {
        }

        @Override // com.topjohnwu.superuser.internal.IRootServiceManager
        public void unbind(ComponentName componentName) throws RemoteException {
        }
    }

    IBinder bind(Intent intent) throws RemoteException;

    void broadcast(int i) throws RemoteException;

    void connect(IBinder iBinder) throws RemoteException;

    void stop(ComponentName componentName, int i) throws RemoteException;

    void unbind(ComponentName componentName) throws RemoteException;

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IRootServiceManager {
        static final int TRANSACTION_bind = 4;
        static final int TRANSACTION_broadcast = 1;
        static final int TRANSACTION_connect = 3;
        static final int TRANSACTION_stop = 2;
        static final int TRANSACTION_unbind = 5;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IRootServiceManager.DESCRIPTOR);
        }

        public static IRootServiceManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(IRootServiceManager.DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRootServiceManager)) {
                return (IRootServiceManager) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IRootServiceManager.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IRootServiceManager.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                broadcast(parcel.readInt());
            } else if (i == 2) {
                stop((ComponentName) _Parcel.readTypedObject(parcel, ComponentName.CREATOR), parcel.readInt());
            } else if (i == 3) {
                connect(parcel.readStrongBinder());
                parcel2.writeNoException();
            } else if (i == 4) {
                IBinder bind = bind((Intent) _Parcel.readTypedObject(parcel, Intent.CREATOR));
                parcel2.writeNoException();
                parcel2.writeStrongBinder(bind);
            } else if (i == 5) {
                unbind((ComponentName) _Parcel.readTypedObject(parcel, ComponentName.CREATOR));
            } else {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            return true;
        }

        /* loaded from: classes2.dex */
        private static class Proxy implements IRootServiceManager {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return IRootServiceManager.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.topjohnwu.superuser.internal.IRootServiceManager
            public void broadcast(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRootServiceManager.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IRootServiceManager
            public void stop(ComponentName componentName, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRootServiceManager.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, componentName, 0);
                    obtain.writeInt(i);
                    this.mRemote.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IRootServiceManager
            public void connect(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRootServiceManager.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IRootServiceManager
            public IBinder bind(Intent intent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRootServiceManager.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, intent, 0);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.topjohnwu.superuser.internal.IRootServiceManager
            public void unbind(ComponentName componentName) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(IRootServiceManager.DESCRIPTOR);
                    _Parcel.writeTypedObject(obtain, componentName, 0);
                    this.mRemote.transact(5, obtain, null, 1);
                } finally {
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
