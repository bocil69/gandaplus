package io.virtualapp.hook.network;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class LegacyNetworkHookPolicyTest {

    @Test
    public void defaultsOffWhenStoredToggleIsMissing() {
        assertFalse(LegacyNetworkHookPolicy.evaluate(null, "debug"));
    }

    @Test
    public void releaseBuildForcesOffEvenWhenStoredToggleIsEnabled() {
        assertFalse(LegacyNetworkHookPolicy.evaluate(Boolean.TRUE, "release"));
    }
}
