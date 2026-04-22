package io.virtualapp.hook.network;

import android.content.Context;
import android.util.Log;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VDeviceManager;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.remote.VDeviceConfig;

public class NetworkSpoofManager {
    private static final String TAG = "NetworkSpoofManager";

    interface LegacyHookDispatcher {
        void hookGrab(ClassLoader classLoader, VDeviceConfig config);

        void hookOvo(ClassLoader classLoader, VDeviceConfig config);

        void hookEskimo(ClassLoader classLoader, VDeviceConfig config);
    }

    private static final LegacyHookDispatcher DEFAULT_DISPATCHER = new LegacyHookDispatcher() {
        @Override
        public void hookGrab(ClassLoader classLoader, VDeviceConfig config) {
            GrabRequestHook.hook(classLoader, config);
        }

        @Override
        public void hookOvo(ClassLoader classLoader, VDeviceConfig config) {
            OvoSslPinningBypass.hook(classLoader, config);
        }

        @Override
        public void hookEskimo(ClassLoader classLoader, VDeviceConfig config) {
            EskimoRequestHook.hook(classLoader, config);
        }
    };

    static boolean injectLegacyHooksIfAllowed(String packageName, boolean policyEnabled,
                                              VDeviceConfig config, ClassLoader classLoader,
                                              LegacyHookDispatcher dispatcher) {
        if (!policyEnabled || config == null || !config.enable || classLoader == null || dispatcher == null) {
            return false;
        }

        if ("com.grabtaxi.passenger".equals(packageName) || "com.grab.driver".equals(packageName)) {
            dispatcher.hookGrab(classLoader, config);
            return true;
        } else if ("ovo.id".equals(packageName)) {
            dispatcher.hookOvo(classLoader, config);
            return true;
        } else if ("travel.eskimo.esim".equals(packageName)) {
            dispatcher.hookEskimo(classLoader, config);
            return true;
        }

        return false;
    }

    static Context resolvePolicyContext(Context startupContext, Context hostContext) {
        if (hostContext != null) {
            return hostContext;
        }

        Context applicationContext = startupContext.getApplicationContext();
        if (applicationContext != null) {
            return applicationContext;
        }

        return startupContext;
    }

    public static void inject(String packageName, String processName, Context context) {
        if (context == null) return;

        try {
            Context policyContext = resolvePolicyContext(context, VirtualCore.get().getContext());
            if (!LegacyNetworkHookPolicy.get(policyContext).isEnabled()) {
                Log.i(TAG, "Skipping legacy network hooks for " + packageName + " because policy is disabled");
                return;
            }

            int userId = VUserHandle.myUserId();
            VDeviceConfig config = VDeviceManager.get().getDeviceConfig(userId);
            if (config == null || !config.enable) {
                return;
            }

            Log.i(TAG, "Injecting NetworkSpoofManager for " + packageName + " [user:" + userId + "]");

            ClassLoader classLoader = context.getClassLoader();

            injectLegacyHooksIfAllowed(packageName, true, config, classLoader, DEFAULT_DISPATCHER);
            
        } catch (Throwable t) {
            Log.e(TAG, "Failed to inject NetworkSpoofManager", t);
        }
    }
}
