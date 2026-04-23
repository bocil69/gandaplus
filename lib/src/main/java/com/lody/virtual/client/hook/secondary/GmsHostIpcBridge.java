package com.lody.virtual.client.hook.secondary;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

import com.lody.virtual.GmsSupport;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.IGmsHostBridge;
import com.lody.virtual.helper.utils.VLog;

import java.lang.reflect.Field;
import java.util.IdentityHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

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
    private static final int MSG_CALL_GMS = 1;
    private static final int MSG_GET_INTEGRITY = 2;
    private static final int MSG_GET_SIGNIN = 3;
    private static final int MSG_RESPONSE = 4;
    private static final long IPC_TIMEOUT_MS = 30000; // 30 seconds
    
    private static GmsHostIpcBridge sInstance;
    private final Context mContext;
    private final String mHostPackageName;
    private IGmsHostBridge mHostBridge;
    private Messenger mHostMessenger;
    private final Object mConnectionLock = new Object();
    private volatile boolean mIsConnected = false;
    
    // Handler for processing responses
    private HandlerThread mHandlerThread;
    private Handler mHandler;
    private Messenger mMessenger;
    
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
        initHandler();
        connectToHostService();
    }
    
    public static synchronized GmsHostIpcBridge getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new GmsHostIpcBridge(context);
        }
        return sInstance;
    }
    
    private void initHandler() {
        mHandlerThread = new HandlerThread("GmsIpcHandler");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MSG_RESPONSE) {
                    // Handle response from host
                    Bundle response = msg.getData();
                    // Response is handled via callback/latch mechanism
                }
            }
        };
        mMessenger = new Messenger(mHandler);
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
                // Try explicit component
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
    
    /**
     * Ensure connection to host service is established
     */
    private boolean ensureConnected() {
        if (mIsConnected && mHostBridge != null) {
            return true;
        }
        
        synchronized (mConnectionLock) {
            if (!mIsConnected) {
                try {
                    mConnectionLock.wait(5000); // Wait up to 5 seconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        
        return mIsConnected && mHostBridge != null;
    }
    
    /**
     * Call Google Play Services via Host App
     * This is the main bridge method
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
            
            // Forward call to host via IPC
            return mHostBridge.callGooglePlayServices(
                    appPackageName, 
                    mHostPackageName,
                    serviceId, 
                    requestData
            );
            
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
            VLog.e(TAG, "Host bridge not connected");
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
            VLog.e(TAG, "Host bridge not connected");
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
     * Sanitize and prepare request data for host
     * Replaces app package with host package where needed
     */
    private Bundle sanitizeRequest(Bundle request, String appPkg, String hostPkg) {
        if (request == null) {
            return new Bundle();
        }
        
        Bundle sanitized = new Bundle(request);
        
        // Replace package names in bundle
        for (String key : sanitized.keySet()) {
            Object value = sanitized.get(key);
            if (value instanceof String) {
                String str = (String) value;
                if (str.equals(appPkg)) {
                    sanitized.putString(key, hostPkg);
                }
            } else if (value instanceof Bundle) {
                sanitized.putBundle(key, sanitizeRequest((Bundle) value, appPkg, hostPkg));
            }
        }
        
        return sanitized;
    }
    
    /**
     * Wrap GMS binder to intercept calls and forward to host
     */
    public static IBinder maybeWrapBinder(ComponentName componentName, IBinder service) {
        if (service == null || !isGmsComponent(componentName)) {
            return service;
        }
        
        Application app = VClient.get().getCurrentApplication();
        if (app == null) {
            return service;
        }
        
        // Return wrapped binder that forwards to host
        return new HostForwardingBinder(app, service);
    }
    
    private static boolean isGmsComponent(ComponentName componentName) {
        return componentName != null && GmsSupport.isGmsPackage(componentName.getPackageName());
    }
    
    /**
     * Binder wrapper that forwards GMS calls to Host App
     */
    private static class HostForwardingBinder extends IBinder.Stub {
        private final Context mContext;
        private final IBinder mBaseBinder;
        private final GmsHostIpcBridge mBridge;
        
        HostForwardingBinder(Context context, IBinder baseBinder) {
            mContext = context;
            mBaseBinder = baseBinder;
            mBridge = getInstance(context);
        }
        
        @Override
        public String getInterfaceDescriptor() throws RemoteException {
            return mBaseBinder.getInterfaceDescriptor();
        }
        
        @Override
        public boolean pingBinder() {
            return mBaseBinder.pingBinder();
        }
        
        @Override
        public boolean isBinderAlive() {
            return mBaseBinder.isBinderAlive();
        }
        
        @Override
        public IInterface queryLocalInterface(String descriptor) {
            // Return wrapper interface
            return null;
        }
        
        @Override
        public void dump(java.io.FileDescriptor fd, String[] args) throws RemoteException {
            mBaseBinder.dump(fd, args);
        }
        
        @Override
        public void dumpAsync(java.io.FileDescriptor fd, String[] args) throws RemoteException {
            mBaseBinder.dumpAsync(fd, args);
        }
        
        @Override
        public void shellCommand(java.io.FileDescriptor in, java.io.FileDescriptor out,
                java.io.FileDescriptor err, String[] args, ShellCallback callback,
                ResultReceiver resultReceiver) throws RemoteException {
            mBaseBinder.shellCommand(in, out, err, args, callback, resultReceiver);
        }
        
        @Override
        public boolean transact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String appPkg = VClient.get().getCurrentPackage();
            
            // Check if this is a call we should forward to host
            if (shouldForwardToHost(code, data)) {
                VLog.d(TAG, "Forwarding transact to host: code=%d pkg=%s", code, appPkg);
                
                // Extract request data
                Bundle requestData = extractRequestData(data);
                
                // Forward to host
                Bundle response = mBridge.callGooglePlayServices(appPkg, code, requestData);
                
                // Write response to reply parcel
                if (response != null && reply != null) {
                    reply.writeBundle(response);
                    return true;
                }
            }
            
            // Otherwise, pass through to base binder
            return mBaseBinder.transact(code, data, reply, flags);
        }
        
        @Override
        public void linkToDeath(DeathRecipient recipient, int flags) throws RemoteException {
            mBaseBinder.linkToDeath(recipient, flags);
        }
        
        @Override
        public boolean unlinkToDeath(DeathRecipient recipient, int flags) {
            return mBaseBinder.unlinkToDeath(recipient, flags);
        }
        
        private boolean shouldForwardToHost(int code, Parcel data) {
            // Determine if this transact should be forwarded to host
            // For now, forward all GMS broker transactions
            return true;
        }
        
        private Bundle extractRequestData(Parcel data) {
            Bundle bundle = new Bundle();
            // Extract data from parcel
            // Implementation depends on specific GMS service
            return bundle;
        }
    }
}
