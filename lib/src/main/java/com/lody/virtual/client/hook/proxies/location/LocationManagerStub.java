package com.lody.virtual.client.hook.proxies.location;

import android.content.Context;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;

import android.util.Log;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.annotations.Inject;
import com.lody.virtual.client.hook.base.BinderInvocationStub;
import com.lody.virtual.client.hook.base.MethodInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.ReflectException;

import com.xdja.zs.VAppPermissionManager;

import java.lang.reflect.Method;

import mirror.android.location.ILocationManager;
import mirror.android.os.ServiceManager;

/**
 * @author Lody
 * @see android.location.LocationManager
 */
@Inject(MethodProxies.class)
public class LocationManagerStub extends MethodInvocationProxy<BinderInvocationStub> {
    public LocationManagerStub() {
        super(new BinderInvocationStub(getInterface()));
    }

    private static IInterface getInterface() {
        IBinder base = ServiceManager.getService.call(Context.LOCATION_SERVICE);
        if (base instanceof Binder) {
            try {
                return Reflect.on(base).get("mILocationManager");
            } catch (ReflectException e) {
                e.printStackTrace();
            }
        }
        return ILocationManager.Stub.asInterface.call(base);
    }

    @Override
    public void inject() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        Object base = mirror.android.location.LocationManager.mService.get(locationManager);
        if (base instanceof Binder) {
            Reflect.on(base).set("mILocationManager", getInvocationStub().getProxyInterface());
        }
        mirror.android.location.LocationManager.mService.set(locationManager, getInvocationStub().getProxyInterface());
        getInvocationStub().replaceService(Context.LOCATION_SERVICE);
    }

    @Override
    public boolean isEnvBad() {
        return false;
    }


    @Override
    protected void onBindMethods() {
        super.onBindMethods();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            addMethodProxy(new ReplaceLastPkgMethodProxy("addTestProvider"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("removeTestProvider"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("setTestProviderLocation"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("clearTestProviderLocation"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("setTestProviderEnabled"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("clearTestProviderEnabled"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("setTestProviderStatus"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("clearTestProviderStatus"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("addGpsMeasurementListener", true));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("addGpsNavigationMessageListener", true));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("removeGpsMeasurementListener", 0));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("removeGpsNavigationMessageListener", 0));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("requestGeofence", 0));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("removeGeofence", 0));
        }

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            addMethodProxy(new MethodProxies.GetLastKnownLocation());
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("addProximityAlert", 0));
        }

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            addMethodProxy(new MethodProxies.RequestLocationUpdatesPI());
            addMethodProxy(new MethodProxies.RemoveUpdatesPI());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            addMethodProxy(new MethodProxies.RequestLocationUpdates());
            addMethodProxy(new MethodProxies.RemoveUpdates());
        }
        
        // Android 11+ (SDK 30+)
        if (Build.VERSION.SDK_INT >= 30) {
            addMethodProxy(new MethodProxies.GetCurrentLocation());
        }
        // Android 12+ (SDK 31+)
        if (Build.VERSION.SDK_INT >= 31) {
            addMethodProxy(new MethodProxies.RegisterLocationListener());
            addMethodProxy(new MethodProxies.UnregisterLocationListener());
        }

        addMethodProxy(new MethodProxies.IsProviderEnabled());
        addMethodProxy(new MethodProxies.GetBestProvider());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            addMethodProxy(new MethodProxies.GetLastLocation());
            addMethodProxy(new MethodProxies.AddGpsStatusListener());
            addMethodProxy(new MethodProxies.RemoveGpsStatusListener());
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("addNmeaListener", 0));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("removeNmeaListener", 0));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            addMethodProxy(new MethodProxies.RegisterGnssStatusCallback());
            addMethodProxy(new MethodProxies.UnregisterGnssStatusCallback());
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("registerGnssMeasurementsCallback", true));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("unregisterGnssMeasurementsCallback", 0));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("addGnssMeasurementsListener", true));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("removeGnssMeasurementsListener", 0));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("registerGnssNavigationMessageCallback", true));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("unregisterGnssNavigationMessageCallback", 0));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("addGnssNavigationMessageListener", true));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("removeGnssNavigationMessageListener", 0));
        }


        addMethodProxy(new MethodProxies.getAllProviders());
        addMethodProxy(new MethodProxies.getProviders());
        addMethodProxy(new MethodProxies.HasProvider());
        addMethodProxy(new MethodProxies.getProviderProperties());
        addMethodProxy(new MethodProxies.sendExtraCommand());
        addMethodProxy(new MethodProxies.locationCallbackFinished());
    }

    private static class FakeReplaceLastPkgMethodProxy extends ReplaceLastPkgMethodProxy {
        private Object mDefValue;

        private FakeReplaceLastPkgMethodProxy(String name, Object def) {
            super(name);
            mDefValue = def;
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            // Fix for Ganda Plus: Prioritize fake location over permission blocking for cloned apps
            if (isFakeLocationEnable()) {
                Log.d("geyao_LocationManStub", "Fake location enabled, bypassing permission check for: " + getAppPkg());
                return mDefValue;
            }
            
            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_LocationManStub", "Location permission blocked for: " + getAppPkg());
                return 0;
            }
            return super.call(who, method, args);
        }
    }
}
