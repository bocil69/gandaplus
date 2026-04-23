package com.lody.virtual.client.ipc;

import android.os.Bundle;
import android.os.IBinder;

/**
 * AIDL interface for GMS Host Bridge IPC
 * Flow: Clone App (VA) -> Host App -> Real GMS -> Back to Clone App
 */
interface IGmsHostBridge {
    
    /**
     * Call Google Play Services from Host App
     * @param packageName - Package name of the calling app (clone app)
     * @param hostPackageName - Package name of the host app
     * @param serviceId - GMS Service ID
     * @param requestBundle - Request data from clone app
     * @return Bundle containing response from real GMS
     */
    Bundle callGooglePlayServices(
        String packageName,
        String hostPackageName,
        int serviceId,
        in Bundle requestBundle
    );
    
    /**
     * Get Integrity Token from Host App (using real hardware)
     * @param packageName - Package name of the calling app
     * @param nonce - Nonce for integrity check
     * @return Bundle containing integrity token
     */
    Bundle getIntegrityToken(
        String packageName,
        String nonce
    );
    
    /**
     * Get Google Sign-in Intent from Host
     * @param packageName - Package name
     * @param requestData - Sign-in request data
     * @return Bundle with sign-in result
     */
    Bundle getGoogleSignIn(
        String packageName,
        in Bundle requestData
    );
    
    /**
     * Check if Host App Bridge is available
     */
    boolean isBridgeAvailable();
    
    /**
     * Get Binder for specific GMS service
     */
    IBinder getGmsServiceBinder(
        String packageName,
        String hostPackageName,
        String serviceName
    );
}
