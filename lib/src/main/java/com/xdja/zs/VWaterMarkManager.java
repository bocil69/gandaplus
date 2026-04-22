package com.xdja.zs;

import android.os.RemoteException;
import android.os.SystemClock;

import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.client.ipc.LocalProxyUtils;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.helper.utils.IInterfaceUtils;

public class VWaterMarkManager {
    private static final long CACHE_TTL_MS = 1000L;
    private static final VWaterMarkManager sInstance = new VWaterMarkManager();
    IWaterMark mService;
    private WaterMarkInfo mCachedWaterMark;
    private long mCacheUpdatedAt;

    public static VWaterMarkManager get() {
        return sInstance;
    }

    private Object getRemoteInterface() {
        return IWaterMark.Stub
                .asInterface(ServiceManagerNative.getService(ServiceManagerNative.WATERMARK));
    }

    public IWaterMark getService() {

        if (mService == null || !IInterfaceUtils.isAlive(mService)) {
            synchronized (this) {
                Object binder = getRemoteInterface();
                mService = LocalProxyUtils.genProxy(IWaterMark.class, binder);
            }
        }
        return mService;
    }

    /**
     * 设置水印信息
     *
     * @param waterMark 水印信息
     */
    public void setWaterMark(WaterMarkInfo waterMark) {
        try {
            getService().setWaterMark(waterMark);
            updateCache(waterMark);
        } catch (RemoteException e) {
            e.printStackTrace();
            VirtualRuntime.crash(e);
        }
    }

    /**
     * 获取水印信息
     *
     * @return 水印信息
     */
    public WaterMarkInfo getWaterMark() {
        WaterMarkInfo cached = readCache();
        if (cached != null) {
            return cached;
        }
        try {
            WaterMarkInfo waterMarkInfo = getService().getWaterMark();
            updateCache(waterMarkInfo);
            return waterMarkInfo;
        } catch (RemoteException e) {
            e.printStackTrace();
            return VirtualRuntime.crash(e);
        }
    }

    private synchronized WaterMarkInfo readCache() {
        if (SystemClock.elapsedRealtime() - mCacheUpdatedAt > CACHE_TTL_MS) {
            mCachedWaterMark = null;
            return null;
        }
        return mCachedWaterMark;
    }

    private synchronized void updateCache(WaterMarkInfo waterMarkInfo) {
        mCachedWaterMark = waterMarkInfo;
        mCacheUpdatedAt = SystemClock.elapsedRealtime();
    }
}
