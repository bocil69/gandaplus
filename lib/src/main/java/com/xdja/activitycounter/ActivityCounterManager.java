package com.xdja.activitycounter;

import android.os.RemoteException;
import android.util.Log;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.client.ipc.LocalProxyUtils;
import com.lody.virtual.client.ipc.ServiceManagerNative;

/**
 * @Date 18-11-28 10
 * @Author lxf@xdja.com
 * @Descrip:
 */
public class ActivityCounterManager {
    private static final String TAG = "ActivityCounterManager";
    private static final ActivityCounterManager sInstance = new ActivityCounterManager();
    public static ActivityCounterManager get() { return sInstance; }

    private  IActivityCounterService mRemote;
    public  IActivityCounterService getRemote() {
        if (mRemote == null ||
                (!mRemote.asBinder().isBinderAlive() && !VirtualCore.get().isVAppProcess())) {
            synchronized (ActivityCounterManager.class) {
                Object remote = getStubInterface();
                if (remote == null) {
                    Log.w(TAG, "getStubInterface returned null - XDJIA service not available");
                    return null;
                }
                mRemote = LocalProxyUtils.genProxy( IActivityCounterService.class, remote);
            }
        }
        return mRemote;
    }
    private Object getStubInterface() {
        return  IActivityCounterService.Stub
                .asInterface(ServiceManagerNative.getService(ServiceManagerNative.FLOATICONBALL));
    }
    public void activityCountAdd(String pkg, String name, int pid ){
        try {
            IActivityCounterService remote = getRemote();
            if (remote != null) {
                remote.activityCountAdd(pkg,name,pid);
            } else {
                Log.w(TAG, "activityCountAdd skipped - service is null");
            }
        } catch (RemoteException e) {
            Log.w(TAG, "activityCountAdd RemoteException: " + e.getMessage());
        }
    }
    public void activityCountReduce(String pkg,String name,int pid){
        try {
            IActivityCounterService remote = getRemote();
            if (remote != null) {
                remote.activityCountReduce(pkg,name,pid);
            } else {
                Log.w(TAG, "activityCountReduce skipped - service is null");
            }
        } catch (RemoteException e) {
            Log.w(TAG, "activityCountReduce RemoteException: " + e.getMessage());
        }
    }
    public void cleanProcess(int pid){
        try{
            IActivityCounterService remote = getRemote();
            if (remote != null) {
                remote.cleanProcess(pid);
            } else {
                Log.w(TAG, "cleanProcess skipped - service is null");
            }
        } catch (RemoteException e) {
            Log.w(TAG, "cleanProcess RemoteException: " + e.getMessage());
        }
    }
    public void cleanPackage(String pkg){
        try{
            IActivityCounterService remote = getRemote();
            if (remote != null) {
                remote.cleanPackage(pkg);
            } else {
                Log.w(TAG, "cleanPackage skipped - service is null (pkg: " + pkg + ")");
            }
        } catch (RemoteException e) {
            Log.w(TAG, "cleanPackage RemoteException: " + e.getMessage());
        }
    }
    public boolean isForeGroundApp(String pkg){
        try {
            IActivityCounterService remote = getRemote();
            if (remote != null) {
                return remote.isForeGroundApp(pkg);
            } else {
                Log.w(TAG, "isForeGroundApp skipped - service is null");
            }
        } catch (RemoteException e) {
            Log.w(TAG, "isForeGroundApp RemoteException: " + e.getMessage());
        }
        return false;
    }
    public boolean isForeGround(){
        try {
            IActivityCounterService remote = getRemote();
            if (remote != null) {
                return remote.isForeGround();
            } else {
                Log.w(TAG, "isForeGround skipped - service is null");
            }
        } catch (RemoteException e) {
            Log.w(TAG, "isForeGround RemoteException: " + e.getMessage());
        }
        return false;
    }
    public void registerCallback(IForegroundInterface fibCallback) {
        try {
            IActivityCounterService remote = getRemote();
            if (remote != null) {
                remote.registerCallback(fibCallback);
            } else {
                Log.w(TAG, "registerCallback skipped - service is null");
            }
        } catch (RemoteException e) {
            Log.w(TAG, "registerCallback RemoteException: " + e.getMessage());
        }
    }

    public void unregisterCallback() {
        try {
            IActivityCounterService remote = getRemote();
            if (remote != null) {
                remote.unregisterCallback();
            } else {
                Log.w(TAG, "unregisterCallback skipped - service is null");
            }
        } catch (RemoteException e) {
            Log.w(TAG, "unregisterCallback RemoteException: " + e.getMessage());
        }
    }
}
