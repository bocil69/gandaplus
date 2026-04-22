package com.xdja.zs;

import com.xdja.zs.IWaterMark;


public class VWaterMarkService extends IWaterMark.Stub {
    private static VWaterMarkService sInstance;
    private static WaterMarkInfo waterMarkInfo;

    public static void systemReady() {
        sInstance = new VWaterMarkService();
    }

    public static VWaterMarkService get() {
        return sInstance;
    }

    public void setWaterMark(WaterMarkInfo waterMark) {
        waterMarkInfo = waterMark;
    }

    public WaterMarkInfo getWaterMark() {
        return waterMarkInfo;
    }
}
