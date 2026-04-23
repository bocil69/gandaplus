package com.lody.virtual.client.hook.secondary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;

import com.lody.virtual.GmsSupport;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.IGmsHostBridge;
import com.lody.virtual.helper.utils.VLog;

/**
 * IPC Bridge that forwards Google Play Services calls from Virtual App
 * to Host App, which then calls real GMS on actual hardware.
 *
 * Architecture Flow:
 * 1. Clone App (VA) makes GMS call
 * 2. This bridge intercepts and forwards via IPC to Host App
 * 3. Host App receives call and spoofs package context
 * 4. Host App calls real Google Play Services
 * 5. Real GMS generates token/account on hardware
 * 6. Result flows back: Host App -> Bridge -> Clone App
 */
public class GmsHostIpcBridge {

    private static final String TAG = "GmsHostIpcBridge";
    private static final String HOST_SERVICE_ACTION = "io.virtualapp.GMS_HOST_BRIDGE";

    private static GmsHostIpcBridge sInstance;
    private final Context mContext;
    private final String mHostPackageName;
    private IGmsHostBridge mHostBridge;
    private final Object mConnectionLock = new Object();
    private volatile boolean mIsConnected = false;
    private HandlerThread mHandlerThread;

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            VLog.i(TAG, "Host service connected: %s", name);
            mHostBridge = IGmsHostBridge.Stub.asInterface(service);
            mIsConnected = true;
            synchronized (mConnectionLock) {
                mConnectionLock.notifyAll();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            VLog.w(TAG, "Host service disconnected: %s", name);
            mHostBridge = null;
            mIsConnected = false;
        }
    };

    private GmsHostIpcBridge(Context context) {
        mContext = context.getApplicationContext();
        mHostPackageName = VirtualCore.get().getHostPkg();
        mHandlerThread = new HandlerThread("GmsIpcHandler");
        mHandlerThread.start();
        connectToHostService();
    }

    public static synchronized GmsHostIpcBridge getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new GmsHostIpcBridge(context);
        }
        return sInstance;
    }

    private void connectToHostService() {
        try {
            Intent intent = new Intent(HOST_SERVICE_ACTION);
            intent.setPackage(mHostPackageName);

            VLog.i(TAG, "Connecting to host service: %s", mHostPackageName);

            boolean bound = mContext.bindService(intent, mServiceConnection,
                    Context.BIND_AUTO_CREATE | Context.BIND_IMPORTANT);

            if (!bound) {
                VLog.w(TAG, "Failed to bind to host service, trying explicit intent");
                intent.setComponent(new ComponentName(mHostPackageName,
                        "io.virtualapp.delegate.GmsHostBridgeService"));
                bound = mContext.bindService(intent, mServiceConnection,
                        Context.BIND_AUTO_CREATE | Context.BIND_IMPORTANT);
            }

            if (!bound) {
                VLog.e(TAG, "Could not bind to host GMS bridge service");
            }

        } catch (Exception e) {
            VLog.e(TAG, "Error connecting to host service: %s", e.getMessage());
        }
    }

    private boolean ensureConnected() {
        if (mIsConnected && mHostBridge != null) {
            return true;
        }
        synchronized (mConnectionLock) {
            if (!mIsConnected) {
                try {
                    mConnectionLock.wait(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return mIsConnected && mHostBridge != null;
    }

    /**
     * Call Google Play Services via Host App
     */
    public Bundle callGooglePlayServices(String appPackageName, int serviceId,
            Bundle requestData) {
        if (!ensureConnected()) {
            VLog.e(TAG, "Host bridge not connected");
            Bundle error = new Bundle();
            error.putInt("status_code", -1);
            error.putString("error", "Host bridge not connected");
            return error;
        }
        try {
            VLog.i(TAG, "Calling GMS via host: pkg=%s svc=%d", appPackageName, serviceId);
            return mHostBridge.callGooglePlayServices(
                    appPackageName, mHostPackageName, serviceId, requestData);
        } catch (RemoteException e) {
            VLog.e(TAG, "Remote error calling GMS: %s", e.getMessage());
            Bundle error = new Bundle();
            error.putInt("status_code", -2);
            error.putString("error", "Remote error: " + e.getMessage());
            return error;
        }
    }

    /**
     * Get Integrity Token via Host App
     */
    public Bundle getIntegrityToken(String appPackageName, String nonce) {
        if (!ensureConnected()) {
            Bundle error = new Bundle();
            error.putInt("status_code", -1);
            error.putString("error", "Host bridge not connected");
            return error;
        }
        try {
            VLog.i(TAG, "Getting integrity token via host: pkg=%s", appPackageName);
            return mHostBridge.getIntegrityToken(appPackageName, nonce);
        } catch (RemoteException e) {
            VLog.e(TAG, "Remote error getting integrity: %s", e.getMessage());
            Bundle error = new Bundle();
            error.putInt("status_code", -2);
            error.putString("error", "Remote error: " + e.getMessage());
            return error;
        }
    }

    /**
     * Get Google Sign-In via Host App
     */
    public Bundle getGoogleSignIn(String appPackageName, Bundle signInData) {
        if (!ensureConnected()) {
            Bundle error = new Bundle();
            error.putInt("status_code", -1);
            error.putString("error", "Host bridge not connected");
            return error;
        }
        try {
            VLog.i(TAG, "Getting sign-in via host: pkg=%s", appPackageName);
            return mHostBridge.getGoogleSignIn(appPackageName, signInData);
        } catch (RemoteException e) {
            VLog.e(TAG, "Remote error in sign-in: %s", e.getMessage());
            Bundle error = new Bundle();
            error.putInt("status_code", -2);
            error.putString("error", "Remote error: " + e.getMessage());
            return error;
        }
    }

    /**
     * Check if host bridge is available
     */
    public boolean isBridgeAvailable() {
        if (!ensureConnected()) {
            return false;
        }
        try {
            return mHostBridge.isBridgeAvailable();
        } catch (RemoteException e) {
            return false;
        }
    }

    /**
     * Get real GMS service binder from host
     */
    public IBinder getGmsServiceBinder(String appPackageName, String serviceName) {
        if (!ensureConnected()) {
            return null;
        }
        try {
            return mHostBridge.getGmsServiceBinder(appPackageName, mHostPackageName, serviceName);
        } catch (RemoteException e) {
            VLog.e(TAG, "Error getting GMS binder: %s", e.getMessage());
            return null;
        }
    }

    /**
     * Check if component is GMS
     */
    public static boolean isGmsComponent(ComponentName componentName) {
        return componentName != null && GmsSupport.isGmsPackage(componentName.getPackageName());
    }
}
