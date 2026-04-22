package io.virtualapp.hook.network;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import com.lody.virtual.remote.VDeviceConfig;

import org.junit.Test;

public class NetworkSpoofManagerTest {

    private static final String GRAB_PASSENGER_PACKAGE = "com.grabtaxi.passenger";
    private static final String GRAB_DRIVER_PACKAGE = "com.grab.driver";
    private static final String OVO_PACKAGE = "ovo.id";
    private static final String ESKIMO_PACKAGE = "travel.eskimo.esim";
    private static final String UNKNOWN_PACKAGE = "io.virtualapp.unknown";

    @Test
    public void dispatchesGrabPassengerPackageToGrabHook() {
        RecordingDispatcher dispatcher = dispatchForPackage(GRAB_PASSENGER_PACKAGE, true);

        dispatcher.assertOnlyGrabCalled();
    }

    @Test
    public void dispatchesGrabDriverPackageToGrabHook() {
        RecordingDispatcher dispatcher = dispatchForPackage(GRAB_DRIVER_PACKAGE, true);

        dispatcher.assertOnlyGrabCalled();
    }

    @Test
    public void dispatchesOvoPackageToOvoHook() {
        RecordingDispatcher dispatcher = dispatchForPackage(OVO_PACKAGE, true);

        dispatcher.assertOnlyOvoCalled();
    }

    @Test
    public void dispatchesEskimoPackageToEskimoHook() {
        RecordingDispatcher dispatcher = dispatchForPackage(ESKIMO_PACKAGE, true);

        dispatcher.assertOnlyEskimoCalled();
    }

    @Test
    public void doesNotDispatchUnknownPackageWhenPolicyIsOn() {
        RecordingDispatcher dispatcher = new RecordingDispatcher();

        boolean injected = NetworkSpoofManager.injectLegacyHooksIfAllowed(
                UNKNOWN_PACKAGE,
                true,
                enabledConfig(),
                getClass().getClassLoader(),
                dispatcher);

        assertFalse(injected);
        dispatcher.assertNoHooksCalled();
    }

    @Test
    public void doesNotDispatchKnownPackagesWhenPolicyIsOff() {
        assertPolicyOffPreventsDispatch(GRAB_PASSENGER_PACKAGE);
        assertPolicyOffPreventsDispatch(GRAB_DRIVER_PACKAGE);
        assertPolicyOffPreventsDispatch(OVO_PACKAGE);
        assertPolicyOffPreventsDispatch(ESKIMO_PACKAGE);
    }

    @Test
    public void resolvePolicyContextPrefersHostContextWhenAvailable() {
        Context startupContext = new ContextWrapper(new Application());
        Context hostContext = new Application();

        Context resolvedContext = NetworkSpoofManager.resolvePolicyContext(startupContext, hostContext);

        assertSame(hostContext, resolvedContext);
    }

    private static VDeviceConfig enabledConfig() {
        VDeviceConfig config = new VDeviceConfig();
        config.enable = true;
        return config;
    }

    private RecordingDispatcher dispatchForPackage(String packageName, boolean policyEnabled) {
        RecordingDispatcher dispatcher = new RecordingDispatcher();

        boolean injected = NetworkSpoofManager.injectLegacyHooksIfAllowed(
                packageName,
                policyEnabled,
                enabledConfig(),
                getClass().getClassLoader(),
                dispatcher);

        assertTrue(injected);
        return dispatcher;
    }

    private void assertPolicyOffPreventsDispatch(String packageName) {
        RecordingDispatcher dispatcher = new RecordingDispatcher();

        boolean injected = NetworkSpoofManager.injectLegacyHooksIfAllowed(
                packageName,
                false,
                enabledConfig(),
                getClass().getClassLoader(),
                dispatcher);

        assertFalse(injected);
        dispatcher.assertNoHooksCalled();
    }

    private static final class RecordingDispatcher implements NetworkSpoofManager.LegacyHookDispatcher {
        private int grabCalls;
        private int ovoCalls;
        private int eskimoCalls;

        @Override
        public void hookGrab(ClassLoader classLoader, VDeviceConfig config) {
            grabCalls++;
        }

        @Override
        public void hookOvo(ClassLoader classLoader, VDeviceConfig config) {
            ovoCalls++;
        }

        @Override
        public void hookEskimo(ClassLoader classLoader, VDeviceConfig config) {
            eskimoCalls++;
        }

        void assertNoHooksCalled() {
            assertFalse(grabCalls > 0 || ovoCalls > 0 || eskimoCalls > 0);
        }

        void assertOnlyOvoCalled() {
            assertFalse(grabCalls > 0);
            assertTrue(ovoCalls > 0);
            assertFalse(eskimoCalls > 0);
        }

        void assertOnlyGrabCalled() {
            assertTrue(grabCalls > 0);
            assertFalse(ovoCalls > 0);
            assertFalse(eskimoCalls > 0);
        }

        void assertOnlyEskimoCalled() {
            assertFalse(grabCalls > 0);
            assertFalse(ovoCalls > 0);
            assertTrue(eskimoCalls > 0);
        }
    }
}
