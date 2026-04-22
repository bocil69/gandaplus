package com.xdja.zs;

import android.os.RemoteException;

import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.client.ipc.LocalProxyUtils;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.helper.utils.IInterfaceUtils;

/**
 * Created by wxudong on 18-1-23.
 */

public class VSafekeyManager {
    private static final VSafekeyManager sInstance = new VSafekeyManager();
    IVSafekey mService;

    private IVSafekey requireService() {
        return getService();
    }

    private Object getRemoteInterface() {
        return IVSafekey.Stub
                .asInterface(ServiceManagerNative.getService(ServiceManagerNative.SAFEKEY));
    }

    public IVSafekey getService() {

        if (mService == null || !IInterfaceUtils.isAlive(mService)) {
            synchronized (this) {
                Object binder = getRemoteInterface();
                mService = LocalProxyUtils.genProxy(IVSafekey.class, binder);
            }
        }
        return mService;
    }

    public static VSafekeyManager get() {
        return sInstance;
    }

    public boolean checkCardState() {
        IVSafekey service = requireService();
        if (service == null) {
            return false;
        }
        try {
            return service.checkCardState();
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public String getCardId() {
        IVSafekey service = requireService();
        if (service == null) {
            return null;
        }
        try {
            return service.getCardId();
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public int getPinTryCount() {
        IVSafekey service = requireService();
        if (service == null) {
            return -1;
        }
        try {
            return service.getPinTryCount();
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public void registerCallback(IVSCallback vsCallback) {
        IVSafekey service = requireService();
        if (service == null) {
            return;
        }
        try {
            service.registerCallback(vsCallback);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void unregisterCallback() {
        IVSafekey service = requireService();
        if (service == null) {
            return;
        }
        try {
            service.unregisterCallback();
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public int initSafekeyCard() {
        IVSafekey service = requireService();
        if (service == null) {
            return -1;
        }
        try {
            return service.initSafekeyCard();
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
            return -1;
        }
    }


    public static byte[] encryptKey(byte[] key, int keylen) {
        IVSafekey service = get().requireService();
        if (service == null) {
            return null;
        }
        try {
            return service.encryptKey(key, keylen);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }


    public static byte[] decryptKey(byte[] seckey, int seckeylen) {
        IVSafekey service = get().requireService();
        if (service == null) {
            return null;
        }
        try {
            return service.decryptKey(seckey, seckeylen);
        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }

    public static byte[] getRandom(int len) {
        IVSafekey service = get().requireService();
        if (service == null) {
            return null;
        }
        try {
            return service.getRandom(len);

        } catch (RemoteException e) {
            return VirtualRuntime.crash(e);
        }
    }
}
