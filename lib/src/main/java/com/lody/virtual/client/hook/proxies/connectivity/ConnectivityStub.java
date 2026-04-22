package com.lody.virtual.client.hook.proxies.connectivity;

import android.content.Context;

import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ResultStaticMethodProxy;

import mirror.android.net.IConnectivityManager;

/**
 * @author legency
 * @see android.net.ConnectivityManager
 */
public class ConnectivityStub extends BinderInvocationProxy {

    public ConnectivityStub() {
        super(IConnectivityManager.Stub.asInterface, Context.CONNECTIVITY_SERVICE);
    }

    @Override
    protected void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new ResultStaticMethodProxy("isTetheringSupported", true));
        addMethodProxy(new MethodProxies.PrepareVpn());
        addMethodProxy(new MethodProxies.ReplacePackageAndUidProxy("requestNetwork"));
        addMethodProxy(new MethodProxies.ReplacePackageAndUidProxy("listenForNetwork"));
        addMethodProxy(new MethodProxies.ReplacePackageAndUidProxy("pendingRequestForNetwork"));
        addMethodProxy(new MethodProxies.ReplacePackageAndUidProxy("registerNetworkCallback"));
        addMethodProxy(new MethodProxies.ReplacePackageAndUidProxy("registerDefaultNetworkCallback"));
        addMethodProxy(new MethodProxies.ReplacePackageAndUidProxy("registerDefaultNetworkCallbackForUid"));
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getNetworkCapabilities"));
    }
}
