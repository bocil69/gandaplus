package com.lody.virtual.client.hook.proxies.location;

import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Build;
import android.util.Log;

import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.client.hook.annotations.SkipInject;
import com.lody.virtual.client.ipc.VLocationManager;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.remote.vloc.VLocation;

import java.lang.reflect.Method;
import java.util.Arrays;

import mirror.android.location.LocationRequestL;
import com.xdja.zs.VAppPermissionManager;

/**
 * @author Lody
 */
@SuppressWarnings("ALL")
class MethodProxies {

    private static void fixLocationRequest(LocationRequest request) {
        if (request != null) {
            if (LocationRequestL.mHideFromAppOps != null) {
                LocationRequestL.mHideFromAppOps.set(request, false);
            }
            if (LocationRequestL.mWorkSource != null) {
                LocationRequestL.mWorkSource.set(request, null);
            }
        }
    }

    @SkipInject
    static class AddGpsStatusListener extends ReplaceLastPkgMethodProxy {
        public AddGpsStatusListener() {
            super("addGpsStatusListener");
        }

        public AddGpsStatusListener(String name) {
            super(name);
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            if (isFakeLocationEnable()) {
                return true;
            }
            
            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_LocationManStub", method.getName() + " return");
                return true;
            }
            return super.call(who, method, args);
        }
    }

    @SkipInject
    static class RequestLocationUpdates extends ReplaceLastPkgMethodProxy {

        public RequestLocationUpdates() {
            super("requestLocationUpdates");
        }

        public RequestLocationUpdates(String name) {
            super(name);
        }

        @Override
        public Object call(final Object who, Method method, Object... args) throws Throwable {
            // Prioritize fake location over permission blocking
            if (isFakeLocationEnable()) {
                VLocationManager.get().requestLocationUpdates(args);
                return 0;
            }
            
            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_LocationManStub", "requestLocationUpdates   return");
                return 0;
            }

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                LocationRequest request = (LocationRequest) args[0];
                fixLocationRequest(request);
            }
            return super.call(who, method, args);
        }
    }

    @SkipInject
    static class RemoveUpdates extends ReplaceLastPkgMethodProxy {

        public RemoveUpdates() {
            super("removeUpdates");
        }

        public RemoveUpdates(String name) {
            super(name);
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            if (isFakeLocationEnable()) {
                VLocationManager.get().removeUpdates(args);
                return 0;
            }
            return super.call(who, method, args);
        }
    }

    @SkipInject
    static class GetLastLocation extends ReplaceLastPkgMethodProxy {

        public GetLastLocation() {
            super("getLastLocation");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            // Prioritize fake location over permission blocking
            if (isFakeLocationEnable()) {
                VLocation loc = VLocationManager.get().getLocation(getAppPkg(), getAppUserId());
                if (loc != null) {
                    return loc.toSysLocation();
                }
                return null;
            }
            
            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_LocationManStub", "getLastLocation return");
                return null;
            }
            
            if (!(args[0] instanceof String)) {
                LocationRequest request = (LocationRequest) args[0];
                fixLocationRequest(request);
            }
            return super.call(who, method, args);
        }
    }

    @SkipInject
    static class GetLastKnownLocation extends GetLastLocation {
        @Override
        public String getMethodName() {
            return "getLastKnownLocation";
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            // Prioritize fake location over permission blocking
            if (isFakeLocationEnable()) {
                VLocation loc = VLocationManager.get().getLocation(getAppPkg(), getAppUserId());
                if (loc != null) {
                    return loc.toSysLocation();
                }
                return null;
            }
            
            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_LocationManStub", "getLastKnownLocation return");
                return null;
            }
            return super.call(who, method, args);
        }
    }

    @SkipInject
    static class IsProviderEnabled extends MethodProxy {
        @Override
        public String getMethodName() {
            return "isProviderEnabled";
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            // Prioritize fake location over permission blocking
            if (isFakeLocationEnable()) {
                if (args[0] instanceof String) {
                    return VLocationManager.get().isProviderEnabled((String) args[0]);
                }
                return false;
            }
            
            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_LocationManStub", "isProviderEnabled return");
                return false;
            }
            return super.call(who, method, args);
        }
    }

    static class getAllProviders extends MethodProxy {

        @Override
        public String getMethodName() {
            return "getAllProviders";
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            // Prioritize fake location over permission blocking
            if (isFakeLocationEnable()) {
                return VLocationManager.get().getAllProviders();
            }
            
            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_LocationManStub", "getAllProviders return");
                return null;
            }
            return super.call(who, method, args);
        }
    }

    static class getProviders extends MethodProxy {

        @Override
        public String getMethodName() {
            return "getProviders";
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            if (isFakeLocationEnable()) {
                return VLocationManager.get().getAllProviders();
            }

            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_LocationManStub", "getProviders return");
                return null;
            }
            return super.call(who, method, args);
        }
    }

    static class HasProvider extends MethodProxy {

        @Override
        public String getMethodName() {
            return "hasProvider";
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            if (isFakeLocationEnable()) {
                if (args.length > 0 && args[0] instanceof String) {
                    return VLocationManager.get().hasProvider((String) args[0]);
                }
                return false;
            }

            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_LocationManStub", "hasProvider return");
                return false;
            }
            return super.call(who, method, args);
        }
    }

    @SkipInject
    static class GetBestProvider extends MethodProxy {
        @Override
        public String getMethodName() {
            return "getBestProvider";
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            // Prioritize fake location over permission blocking
            if (isFakeLocationEnable()) {
                return LocationManager.GPS_PROVIDER;
            }
            
            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_LocationManStub", "getBestProvider return");
                return null;
            }
            return super.call(who, method, args);
        }
    }

    @SkipInject
    static class RemoveGpsStatusListener extends ReplaceLastPkgMethodProxy {
        public RemoveGpsStatusListener() {
            super("removeGpsStatusListener");
        }

        public RemoveGpsStatusListener(String name) {
            super(name);
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            if (isFakeLocationEnable()) {
                VLocationManager.get().removeGpsStatusListener(args);
                return 0;
            }
            return super.call(who, method, args);
        }
    }

    @SkipInject
    static class RequestLocationUpdatesPI extends RequestLocationUpdates {
        public RequestLocationUpdatesPI() {
            super("requestLocationUpdatesPI");
        }
    }

    @SkipInject
    static class RemoveUpdatesPI extends RemoveUpdates {
        public RemoveUpdatesPI() {
            super("removeUpdatesPI");
        }
    }

    @SkipInject
    static class UnregisterGnssStatusCallback extends RemoveGpsStatusListener {
        public UnregisterGnssStatusCallback() {
            super("unregisterGnssStatusCallback");
        }
    }

    @SkipInject
    static class RegisterGnssStatusCallback extends AddGpsStatusListener {
        public RegisterGnssStatusCallback() {
            super("registerGnssStatusCallback");
        }
    }


    @SkipInject
    static class GetCurrentLocation extends ReplaceLastPkgMethodProxy {
        public GetCurrentLocation() {
            super("getCurrentLocation");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            if (isFakeLocationEnable()) {
                // In API 30+, getCurrentLocation signature usually has ILocationListener at args[2].
                // void getCurrentLocation(LocationRequest, ICancellationSignal, ILocationListener, String, String, String)
                try {
                    Object listener = null;
                    if (args.length >= 3 && args[2] != null && args[2].getClass().getName().contains("ILocationListener")) {
                        listener = args[2];
                    } else {
                        // fallback search for listener
                        for (Object arg : args) {
                            if (arg != null && arg.getClass().getName().contains("ILocationListener")) {
                                listener = arg;
                                break;
                            }
                        }
                    }
                    if (listener != null) {
                        VLocation loc = VLocationManager.get().getLocation(getAppPkg(), getAppUserId());
                        VLocationManager.get().dispatchLocation(listener, loc);
                    }
                } catch (Throwable e) {
                    Log.e("LocationStub", "Error in getCurrentLocation mock", e);
                }
                return null;
            }
            return super.call(who, method, args);
        }
    }

    @SkipInject
    static class RegisterLocationListener extends ReplaceLastPkgMethodProxy {
        public RegisterLocationListener() {
            super("registerLocationListener");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            if (isFakeLocationEnable()) {
                // Try to use existing requestLocationUpdates logic if possible,
                // but to prevent leak and crash, we catch exceptions.
                try {
                    VLocationManager.get().requestLocationUpdates(args);
                } catch (Throwable e) {
                    Log.e("LocationStub", "Error in registerLocationListener mock", e);
                }
                return null; // Return null to avoid calling super and leaking real location
            }
            return super.call(who, method, args);
        }
    }

    @SkipInject
    static class UnregisterLocationListener extends ReplaceLastPkgMethodProxy {
        public UnregisterLocationListener() {
            super("unregisterLocationListener");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            if (isFakeLocationEnable()) {
                try {
                    VLocationManager.get().removeUpdates(args);
                } catch (Throwable e) {
                    Log.e("LocationStub", "Error in unregisterLocationListener mock", e);
                }
                return null;
            }
            return super.call(who, method, args);
        }
    }

    static class sendExtraCommand extends MethodProxy {

        @Override
        public String getMethodName() {
            return "sendExtraCommand";
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_LocationManStub", "sendExtraCommand return");
                return true;
            }
            if (isFakeLocationEnable()) {
                return true;
            }
            return super.call(who, method, args);
        }
    }

    static class getProviderProperties extends MethodProxy {

        @Override
        public String getMethodName() {
            return "getProviderProperties";
        }

        @Override
        public Object afterCall(Object who, Method method, Object[] args, Object result) throws Throwable {
            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_LocationManStub", "getProviderProperties return");
                return null;
            }
            if (isFakeLocationEnable()) {
                try {
                    Reflect.on(result).set("mRequiresNetwork", false);
                    Reflect.on(result).set("mRequiresCell", false);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                return result;
            }
            return super.afterCall(who, method, args, result);
        }
    }

    static class locationCallbackFinished extends MethodProxy {

        @Override
        public String getMethodName() {
            return "locationCallbackFinished";
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            if (isFakeLocationEnable()) {
                return true;
            }

            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_TelephonyRegStub", "locationCallbackFinished return");
                return true;
            }

            return super.call(who, method, args);
        }
    }
}
