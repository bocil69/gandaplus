package com.lody.virtual.client.ipc;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IInterface;
import android.util.Log;

import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.proxies.location.MockLocationHelper;
import com.lody.virtual.client.hook.utils.MethodParameterUtils;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.remote.vloc.VLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see LocationManager
 * <p>
 * 实现代码多，资源回收不及时：拦截gps状态，定位请求，并且交给虚拟定位服务，虚拟服务根据一样的条件，再次向系统定位服务请求
 * LocationManager.addgpslistener
 * LocationManager.request
 * <p>
 * 实现代码少：GpsStatusListenerTransport、ListenerTransport这2个对象，hook里面的方法，修改参数，都是binder
 */
public class VLocationManager {
    public static final String TAG = "VLoc";
    private Handler mWorkHandler;
    private HandlerThread mHandlerThread;
    private final List<Object> mGpsListeners = new ArrayList<>();
    private static VLocationManager sVLocationManager = new VLocationManager();

    private VLocationManager() {
        LocationManager locationManager = (LocationManager) VirtualCore.get().getContext().getSystemService(Context.LOCATION_SERVICE);
        MockLocationHelper.fakeGpsStatus(locationManager);
    }

    public static VLocationManager get() {
        return sVLocationManager;
    }


    private void checkWork() {
        if (mHandlerThread == null) {
            synchronized (this) {
                if (mHandlerThread == null) {
                    mHandlerThread = new HandlerThread("loc_thread");
                    mHandlerThread.start();
                }
            }
        }
        if (mWorkHandler == null) {
            synchronized (this) {
                if (mWorkHandler == null) {
                    mWorkHandler = new Handler(mHandlerThread.getLooper());
                }
            }
        }
    }

    private void stopGpsTask() {
        if (mWorkHandler != null) {
            mWorkHandler.removeCallbacks(mUpdateGpsStatusTask);
        }
    }

    private void startGpsTask() {
        checkWork();
        stopGpsTask();
        mWorkHandler.postDelayed(mUpdateGpsStatusTask, 5000);
    }

    private Runnable mUpdateGpsStatusTask = new Runnable() {
        @Override
        public void run() {
            synchronized (mGpsListeners) {
                for (Object listener : mGpsListeners) {
                    notifyGpsStatus(listener);
                }
            }
            mWorkHandler.postDelayed(mUpdateGpsStatusTask, 8000);
        }
    };


    public boolean hasVirtualLocation(String packageName, int userId) {
        try {
            return VirtualLocationManager.get().getMode(userId, packageName) != VirtualLocationManager.MODE_CLOSE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isProviderEnabled(String provider) {
        return LocationManager.GPS_PROVIDER.equals(provider);
    }

    public boolean hasProvider(String provider) {
        return LocationManager.GPS_PROVIDER.equals(provider)
                || LocationManager.NETWORK_PROVIDER.equals(provider);
    }

    public List<String> getAllProviders() {
        return Arrays.asList(LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER);
    }

    public VLocation getLocation(String packageName, int userId) {
        return getVirtualLocation(packageName, null, userId);
    }

    public VLocation getCurAppLocation() {
        return getVirtualLocation(VClient.get().getCurrentPackage(), null, VUserHandle.myUserId());
    }

    public VLocation getVirtualLocation(String packageName, Location loc, int userId) {
        try {
            if (VirtualLocationManager.get().getMode(userId, packageName) == VirtualLocationManager.MODE_USE_GLOBAL) {
                return VirtualLocationManager.get().getGlobalLocation();
            } else {
                return VirtualLocationManager.get().getLocation(userId, packageName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPackageName() {
        return VClient.get().getCurrentPackage();
    }

    public void removeGpsStatusListener(final Object[] args) {
        if (args[0] instanceof PendingIntent) {
            return;
        }
        boolean needStop;
        synchronized (mGpsListeners) {
            mGpsListeners.remove(args[0]);
            needStop = mGpsListeners.size() == 0;
        }
        if (needStop) {
            stopGpsTask();
        }
    }


    public void addGpsStatusListener(final Object[] args) {
        final Object GpsStatusListenerTransport = args[0];
        MockLocationHelper.invokeSvStatusChanged(GpsStatusListenerTransport);
        if (GpsStatusListenerTransport != null) {
            synchronized (mGpsListeners) {
                mGpsListeners.add(GpsStatusListenerTransport);
            }
        }
        checkWork();
        notifyGpsStatus(GpsStatusListenerTransport);
        startGpsTask();
    }

    private void notifyGpsStatus(final Object transport) {
        if (transport == null) {
            return;
        }
//        checkWork();
        mWorkHandler.post(new Runnable() {
            @Override
            public void run() {
//                GpsStatusGenerate.fakeGpsStatus(transport);
                MockLocationHelper.invokeSvStatusChanged(transport);
                MockLocationHelper.invokeNmeaReceived(transport);
            }
        });
    }

    public void removeUpdates(final Object[] args) {
        Object listenerTransport = findLocationListener(args);
        if (listenerTransport != null) {
            UpdateLocationTask task = getTask(listenerTransport);
            if (task != null) {
                task.stop();
                synchronized (mLocationTaskMap) {
                    mLocationTaskMap.remove(listenerTransport);
                }
            }
        }
    }

    public void requestLocationUpdates(Object[] args) {
        final Object listenerTransport = findLocationListener(args);
        if (listenerTransport == null) {
            Log.e(TAG, "ListenerTransport:null");
        } else {
            //mInterval
            long mInterval;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                try {
                    mInterval = Reflect.on(args[0]).get("mInterval");
                } catch (Throwable e) {
                    mInterval = 60 * 1000;
                }
            } else {
                mInterval = MethodParameterUtils.getFirstParam(args, Long.class);
            }
            VLocation location = getCurAppLocation();
            checkWork();
            if (location != null) {
                notifyLocation(listenerTransport, location.toSysLocation(), true);
            }
            UpdateLocationTask task = getTask(listenerTransport);
            if (task == null) {
                synchronized (mLocationTaskMap) {
                    task = new UpdateLocationTask(listenerTransport, mInterval);
                    mLocationTaskMap.put(listenerTransport, task);
                }
            }
            task.start();
        }
    }

    public boolean dispatchLocation(Object listenerTransport, VLocation location) {
        if (location == null) {
            return false;
        }
        return notifyLocation(listenerTransport, location.toSysLocation(), false);
    }

    private Object findLocationListener(Object[] args) {
        if (args == null) {
            return null;
        }
        for (Object arg : args) {
            if (arg == null) {
                continue;
            }
            if (arg instanceof PendingIntent || arg instanceof String || arg instanceof Number || arg instanceof Boolean) {
                continue;
            }
            if (arg instanceof LocationRequest || arg instanceof Location) {
                continue;
            }
            String className = arg.getClass().getName();
            if (className.contains("ListenerTransport") || className.contains("ILocationListener")) {
                return arg;
            }
            if (arg instanceof IInterface && className.toLowerCase().contains("location")) {
                return arg;
            }
        }
        return null;
    }

    private boolean notifyLocation(final Object ListenerTransport, final Location location, boolean post) {
        if (ListenerTransport == null) {
            return false;
        }
        if (!post) {
            try {
                callLocationChanged(ListenerTransport, location);
                return true;
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return false;
        }
        mWorkHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    callLocationChanged(ListenerTransport, location);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
        return true;
    }

    private void callLocationChanged(Object listenerTransport, Location location) {
        String className = listenerTransport.getClass().getName();
        if (className.contains("ILocationListener") &&
                mirror.android.location.ILocationListener.onLocationChanged != null) {
            mirror.android.location.ILocationListener.onLocationChanged.call(listenerTransport, location);
            return;
        }
        if (mirror.android.location.LocationManager.ListenerTransport.onLocationChanged != null) {
            mirror.android.location.LocationManager.ListenerTransport.onLocationChanged.call(listenerTransport, location);
            return;
        }
        LocationListener listener = mirror.android.location.LocationManager.ListenerTransport.mListener == null
                ? null
                : mirror.android.location.LocationManager.ListenerTransport.mListener.get(listenerTransport);
        if (listener != null) {
            VLog.w(TAG, "callLocationChanged fallback to LocationListener for %s", className);
            listener.onLocationChanged(location);
            return;
        }
        // On Android 13+ the LocationManager internals changed and the reflection hooks may not
        // resolve. Log a warning instead of crashing the loc_thread — location is non-critical.
        VLog.w(TAG, "callLocationChanged: no hook available for %s, skipping update", className);
    }

    private final Map<Object, UpdateLocationTask> mLocationTaskMap = new HashMap<>();

    private UpdateLocationTask getTask(Object locationListener) {
        UpdateLocationTask task;
        synchronized (mLocationTaskMap) {
            task = mLocationTaskMap.get(locationListener);
        }
        return task;
    }

    private class UpdateLocationTask implements Runnable {
        private Object mListenerTransport;
        private long mTime;
        private volatile boolean mRunning;

        private UpdateLocationTask(Object ListenerTransport, long time) {
            mListenerTransport = ListenerTransport;
            mTime = time;
        }

        @Override
        public void run() {
            if (mRunning) {
                VLocation location = getCurAppLocation();
                if (location != null) {
                    if (notifyLocation(mListenerTransport, location.toSysLocation(), false)) {
                        start();
                    }
                }
            }
        }

        public void start() {
            mRunning = true;
            mWorkHandler.removeCallbacks(this);
            if (mTime > 0) {
                mWorkHandler.postDelayed(this, mTime);
            } else {
                mWorkHandler.post(this);
            }
        }

        public void stop() {
            mRunning = false;
            mWorkHandler.removeCallbacks(this);
        }
    }
}
