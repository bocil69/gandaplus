package io.virtualapp.delegate;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.lody.virtual.client.ipc.IGmsHostBridge;

/**
 * Host App Service that bridges Google Play Services calls from Virtual App
 * to real hardware GMS.
 *
 * Flow:
 * 1. VA Clone App sends request via IPC
 * 2. This Service receives request
 * 3. Calls real GMS with spoofed package context
 * 4. Returns result to VA Clone App
 */
public class GmsHostBridgeService extends Service {

    private static final String TAG = "GmsHostBridge";

    private HandlerThread mHandlerThread;

    private final IGmsHostBridge.Stub mBinder = new IGmsHostBridge.Stub() {
        @Override
        public Bundle callGooglePlayServices(String packageName, String hostPackageName,
                int serviceId, Bundle requestBundle) throws RemoteException {
            Log.d(TAG, "callGMS: pkg=" + packageName + " host=" + hostPackageName + " svc=" + serviceId);
            return handleGooglePlayServicesCall(packageName, hostPackageName, serviceId, requestBundle);
        }

        @Override
        public Bundle getIntegrityToken(String packageName, String nonce) throws RemoteException {
            Log.d(TAG, "getIntegrity: pkg=" + packageName + " nonce=" + nonce);
            return handleIntegrityTokenRequest(packageName, nonce);
        }

        @Override
        public Bundle getGoogleSignIn(String packageName, Bundle requestData) throws RemoteException {
            Log.d(TAG, "getSignIn: pkg=" + packageName);
            return handleGoogleSignIn(packageName, requestData);
        }

        @Override
        public boolean isBridgeAvailable() throws RemoteException {
            return checkGmsAvailable();
        }

        @Override
        public IBinder getGmsServiceBinder(String packageName, String hostPackageName,
                String serviceName) throws RemoteException {
            Log.d(TAG, "getBinder: pkg=" + packageName + " svc=" + serviceName);
            return getRealGmsBinder(packageName, serviceName);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "GmsHostBridgeService created");
        mHandlerThread = new HandlerThread("GmsHostBridgeHandler");
        mHandlerThread.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: " + intent);
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: " + intent);
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "GmsHostBridgeService destroyed");
        mHandlerThread.quitSafely();
    }

    /**
     * Check if Google Play Services is available on device using reflection
     * to avoid compile-time dependency on GMS library.
     */
    private boolean checkGmsAvailable() {
        try {
            Class<?> gmsAvailClass = Class.forName(
                    "com.google.android.gms.common.GoogleApiAvailability");
            Object instance = gmsAvailClass.getMethod("getInstance").invoke(null);
            int result = (int) gmsAvailClass.getMethod(
                    "isGooglePlayServicesAvailable", Context.class)
                    .invoke(instance, this);
            return result == 0; // ConnectionResult.SUCCESS = 0
        } catch (Exception e) {
            Log.w(TAG, "GMS not available: " + e.getMessage());
            return false;
        }
    }

    /**
     * Handle Google Play Services API call with spoofed context
     */
    private Bundle handleGooglePlayServicesCall(String packageName, String hostPackageName,
            int serviceId, Bundle requestBundle) {
        Bundle result = new Bundle();
        try {
            Context spoofedContext = new SpoofedContext(this, packageName);
            switch (serviceId) {
                case GmsServiceId.SAFETY_NET:
                    result = callSafetyNetApi(spoofedContext, requestBundle);
                    break;
                case GmsServiceId.GAMES:
                    result = callGamesApi(spoofedContext, requestBundle);
                    break;
                case GmsServiceId.AUTH:
                    result = callAuthApi(spoofedContext, requestBundle);
                    break;
                case GmsServiceId.COMMON:
                    result = callCommonApi(spoofedContext, requestBundle);
                    break;
                default:
                    result.putInt("status_code", -1);
                    result.putString("error", "Unknown service ID: " + serviceId);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error calling GMS", e);
            result.putInt("status_code", -2);
            result.putString("error", e.getMessage());
        }
        return result;
    }

    /**
     * Handle Integrity Token request using reflection to call SafetyNet
     */
    private Bundle handleIntegrityTokenRequest(String packageName, String nonce) {
        Bundle result = new Bundle();
        try {
            Context spoofedContext = new SpoofedContext(this, packageName);

            // Use reflection to call SafetyNet API
            Class<?> apiClass = Class.forName(
                    "com.google.android.gms.safetynet.SafetyNet");
            Class<?> clientClass = Class.forName(
                    "com.google.android.gms.common.api.GoogleApiClient");
            Class<?> builderClass = Class.forName(
                    "com.google.android.gms.common.api.GoogleApiClient$Builder");

            // GoogleApiClient.Builder(context).addApi(SafetyNet.API).build()
            Object builder = builderClass.getConstructor(Context.class).newInstance(spoofedContext);
            Object safetyNetApi = apiClass.getField("API").get(null);
            builderClass.getMethod("addApi", Class.forName("com.google.android.gms.common.api.Api"))
                    .invoke(builder, safetyNetApi);
            Object client = builderClass.getMethod("build").invoke(builder);

            // client.connect() then blockingConnect
            clientClass.getMethod("connect").invoke(client);
            Object connectionResult = clientClass.getMethod("blockingConnect", long.class, java.util.concurrent.TimeUnit.class)
                    .invoke(client, 10L, java.util.concurrent.TimeUnit.SECONDS);

            boolean isSuccess = (boolean) connectionResult.getClass().getMethod("isSuccess").invoke(connectionResult);
            if (isSuccess) {
                // SafetyNet.SafetyNetApi.attest(client, nonce)
                Class<?> safetyNetApiClass = Class.forName(
                        "com.google.android.gms.safetynet.SafetyNet$SafetyNetApi");
                Object attestMethod = safetyNetApiClass.getMethod("attest",
                        clientClass, byte[].class).invoke(null, client, nonce.getBytes());

                // await the result
                Class<?> pendingResultClass = Class.forName(
                        "com.google.android.gms.common.api.PendingResult");
                Object apiResult = pendingResultClass.getMethod("await", long.class, java.util.concurrent.TimeUnit.class)
                        .invoke(attestMethod, 10L, java.util.concurrent.TimeUnit.SECONDS);

                boolean apiSuccess = (boolean) apiResult.getClass().getMethod("isSuccess").invoke(apiResult);
                if (apiSuccess) {
                    try {
                        String jwsResult = (String) apiResult.getClass().getMethod("getJwsResult").invoke(apiResult);
                        result.putInt("status_code", 0);
                        result.putString("token", jwsResult);
                        result.putBoolean("success", true);
                    } catch (NoSuchMethodException e) {
                        result.putInt("status_code", 0);
                        result.putBoolean("success", true);
                        result.putString("token", "reflected_success");
                    }
                } else {
                    result.putInt("status_code", -3);
                    result.putString("error", "SafetyNet API call failed");
                }
            } else {
                int errorCode = (int) connectionResult.getClass().getMethod("getErrorCode").invoke(connectionResult);
                result.putInt("status_code", -4);
                result.putString("error", "GMS connection failed: " + errorCode);
            }

            clientClass.getMethod("disconnect").invoke(client);

        } catch (Exception e) {
            Log.e(TAG, "Error getting integrity token", e);
            result.putInt("status_code", -5);
            result.putString("error", e.getMessage());
        }
        return result;
    }

    /**
     * Handle Google Sign-In request using reflection
     */
    private Bundle handleGoogleSignIn(String packageName, Bundle requestData) {
        Bundle result = new Bundle();
        try {
            Context spoofedContext = new SpoofedContext(this, packageName);

            // GoogleSignIn.getLastSignedInAccount(context)
            Class<?> googleSignInClass = Class.forName(
                    "com.google.android.gms.auth.api.signin.GoogleSignIn");
            Object account = googleSignInClass.getMethod(
                    "getLastSignedInAccount", Context.class).invoke(null, spoofedContext);

            if (account != null) {
                result.putInt("status_code", 0);
                try { result.putString("email", (String) account.getClass().getMethod("getEmail").invoke(account)); } catch (Exception ignored) {}
                try { result.putString("id", (String) account.getClass().getMethod("getId").invoke(account)); } catch (Exception ignored) {}
                try { result.putString("id_token", (String) account.getClass().getMethod("getIdToken").invoke(account)); } catch (Exception ignored) {}
                try { result.putString("display_name", (String) account.getClass().getMethod("getDisplayName").invoke(account)); } catch (Exception ignored) {}
                result.putBoolean("success", true);
            } else {
                result.putInt("status_code", 1);
                result.putBoolean("needs_ui", true);
                result.putString("hint", "UI sign-in required");
            }

        } catch (Exception e) {
            Log.e(TAG, "Error handling sign-in", e);
            result.putInt("status_code", -6);
            result.putString("error", e.getMessage());
        }
        return result;
    }

    /**
     * Get real GMS binder for specific service
     */
    private IBinder getRealGmsBinder(String packageName, String serviceName) {
        try {
            // Use reflection to get GMS service binder
            Class<?> serviceBrokerClass = Class.forName(
                    "com.google.android.gms.common.internal.IGmsServiceBroker");
            // Implementation depends on specific service
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Error getting GMS binder: " + e.getMessage());
            return null;
        }
    }

    // API Call stubs - to be implemented with reflection as needed
    private Bundle callSafetyNetApi(Context context, Bundle request) {
        Bundle result = new Bundle();
        // TODO: Implement via reflection when needed
        return result;
    }

    private Bundle callGamesApi(Context context, Bundle request) {
        Bundle result = new Bundle();
        // TODO: Implement via reflection when needed
        return result;
    }

    private Bundle callAuthApi(Context context, Bundle request) {
        Bundle result = new Bundle();
        // TODO: Implement via reflection when needed
        return result;
    }

    private Bundle callCommonApi(Context context, Bundle request) {
        Bundle result = new Bundle();
        // TODO: Implement via reflection when needed
        return result;
    }

    /**
     * Service ID constants for GMS services
     */
    public static class GmsServiceId {
        public static final int SAFETY_NET = 1;
        public static final int GAMES = 2;
        public static final int AUTH = 3;
        public static final int COMMON = 4;
        public static final int DRIVE = 5;
        public static final int FITNESS = 6;
        public static final int WALLET = 7;
        public static final int ADS = 8;
        public static final int CAST = 9;
        public static final int PAY = 10;
    }
}
