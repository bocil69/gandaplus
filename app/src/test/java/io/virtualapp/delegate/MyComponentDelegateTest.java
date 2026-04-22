package io.virtualapp.delegate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyComponentDelegateTest {

    @Before
    @After
    public void resetRootHideState() {
        MyComponentDelegate.resetRootHideInjectedForTests();
    }

    @Test
    public void rootHideInjectionRunsOnlyOnceUntilReset() {
        assertTrue(MyComponentDelegate.markRootHideInjectedIfNeeded());
        assertFalse(MyComponentDelegate.markRootHideInjectedIfNeeded());

        MyComponentDelegate.resetRootHideInjectedForTests();

        assertTrue(MyComponentDelegate.markRootHideInjectedIfNeeded());
    }

    @Test
    public void beforeStartApplicationKeepsNetworkHookFlowWhileRootHideRunsOnce() {
        RecordingDelegate delegate = new RecordingDelegate();
        Context startupContext = new ContextWrapper(new Application());

        delegate.beforeStartApplication("com.example.app", "com.example.app", startupContext);
        delegate.beforeStartApplication("com.example.app", "com.example.app", startupContext);

        assertEquals(2, delegate.networkHookCalls);
        assertEquals(1, delegate.rootHideCalls);
        assertEquals(startupContext, delegate.lastContext);
    }

    private static final class RecordingDelegate extends MyComponentDelegate {
        private int networkHookCalls;
        private int rootHideCalls;
        private Context lastContext;

        @Override
        void injectNetworkHooks(String packageName, String processName, Context context) {
            networkHookCalls++;
            lastContext = context;
        }

        @Override
        void injectRootHide() {
            rootHideCalls++;
        }
    }
}
