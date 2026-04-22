package com.lody.virtual.client.hook.proxies.service;

import android.os.IInterface;

import com.lody.virtual.client.core.ServiceLocalManager;
import com.lody.virtual.client.hook.base.BinderInvocationStub;
import com.lody.virtual.client.hook.base.MethodInvocationProxy;
import com.lody.virtual.client.hook.base.MethodInvocationStub;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import com.lody.virtual.helper.utils.VLog;

import java.lang.reflect.Method;

import mirror.android.os.ServiceManager;

public class ServiceManagerStub extends MethodInvocationProxy<MethodInvocationStub<IInterface>> {

    private static final String[] GUARDED_SERVICES = new String[]{
            "phone",
            "phone_msim",
            "telephony.registry",
            "telephony_subscription_service",
            "location",
            "window",
            "alarm"
    };

    public ServiceManagerStub() {
        super(new MethodInvocationStub<>(ServiceManager.getIServiceManager.call()));
    }

    @Override
    public void inject() {
        ServiceManager.sServiceManager.set(getInvocationStub().getProxyInterface());
    }

    @Override
    protected void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new StaticMethodProxy("getService") {
            @Override
            public Object call(Object who, Method method, Object... args) throws Throwable {
                String name = (String) args[0];
                BinderInvocationStub proxy = getLocalProxy(name);
                if (proxy != null) {
                    VLog.d("kk", "ServiceLocalManager.getService:%s->%s", name, proxy);
                    return proxy;
                }
                if (isGuardedService(name)) {
                    VLog.w("ServiceManagerStub", "Blocked host binder leak for service: %s", name);
                    return null;
                }
                VLog.d("kk", "ServiceLocalManager.getService:%s no find", name);
                return super.call(who, method, args);
            }
        });
        addMethodProxy(new StaticMethodProxy("checkService"){
            @Override
            public Object call(Object who, Method method, Object... args) throws Throwable {
                String name = (String) args[0];
                BinderInvocationStub proxy = getLocalProxy(name);
                if (proxy != null) {
                    VLog.d("kk", "ServiceLocalManager.checkService:%s->%s", name, proxy);
                    return proxy;
                }
                if (isGuardedService(name)) {
                    VLog.w("ServiceManagerStub", "Blocked host binder leak on checkService: %s", name);
                    return null;
                }
                VLog.d("kk", "ServiceLocalManager.checkService:%s no find", name);
                return super.call(who, method, args);
            }
        });
    }

    private BinderInvocationStub getLocalProxy(String name) {
        return ServiceLocalManager.getService(name);
    }

    private boolean isGuardedService(String name) {
        if (name == null) {
            return false;
        }
        for (String guarded : GUARDED_SERVICES) {
            if (guarded.equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEnvBad() {
        return ServiceManager.sServiceManager.get() != getInvocationStub().getProxyInterface();
    }
}
