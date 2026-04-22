package com.lody.virtual.client.hook.proxies.phonesubinfo;

import android.text.TextUtils;

import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.helper.utils.marks.FakeDeviceMark;
import com.lody.virtual.remote.VDeviceConfig;

import java.lang.reflect.Method;

/**
 * @author Lody
 */
@SuppressWarnings("ALL")
class MethodProxies {

    @FakeDeviceMark("fake device id")
    static class GetDeviceId extends ReplaceLastPkgMethodProxy {
        public GetDeviceId() {
            super("getDeviceId");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            VDeviceConfig config = getDeviceConfig();
            if (config.enable) {
                String deviceId = config.deviceId;
                if (!TextUtils.isEmpty(deviceId)) {
                    return deviceId;
                }
            }
            return super.call(who, method, args);
        }
    }

    //
    @FakeDeviceMark("fake device id")
    static class GetDeviceIdForPhone extends GetDeviceId {
        @Override
        public String getMethodName() {
            return "getDeviceIdForPhone";
        }
    }

    @FakeDeviceMark("fake device id.")
    static class GetDeviceIdForSubscriber extends GetDeviceId {

        @Override
        public String getMethodName() {
            return "getDeviceIdForSubscriber";
        }

    }

    @FakeDeviceMark("fake device id.")
    static class GetImeiForSubscriber extends GetDeviceId {

        @Override
        public String getMethodName() {
            return "getImeiForSubscriber";
        }

    }

    @FakeDeviceMark("fake iccid")
    static class GetIccSerialNumber extends ReplaceLastPkgMethodProxy {
        public GetIccSerialNumber() {
            super("getIccSerialNumber");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            VDeviceConfig config = getDeviceConfig();
            if (config.enable) {
                String iccId = !TextUtils.isEmpty(config.simSerial) ? config.simSerial : config.iccId;
                if (!TextUtils.isEmpty(iccId)) {
                    return iccId;
                }
            }
            return super.call(who, method, args);
        }
    }

    @FakeDeviceMark("fake subscriber id")
    static class GetSubscriberId extends ReplaceLastPkgMethodProxy {
        public GetSubscriberId() {
            super("getSubscriberId");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            VDeviceConfig config = getDeviceConfig();
            if (config.enable) {
                String subscriberId = !TextUtils.isEmpty(config.subscriberId) ? config.subscriberId : config.iccId;
                if (!TextUtils.isEmpty(subscriberId)) {
                    return subscriberId;
                }
            }
            return super.call(who, method, args);
        }
    }

    @FakeDeviceMark("fake subscriber id")
    static class GetSubscriberIdForSubscriber extends GetSubscriberId {
        @Override
        public String getMethodName() {
            return "getSubscriberIdForSubscriber";
        }
    }

    @FakeDeviceMark("fake sim serial")
    static class GetSimSerialNumber extends ReplaceLastPkgMethodProxy {
        public GetSimSerialNumber() {
            super("getSimSerialNumber");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            VDeviceConfig config = getDeviceConfig();
            if (config.enable) {
                String simSerial = !TextUtils.isEmpty(config.simSerial) ? config.simSerial : config.iccId;
                if (!TextUtils.isEmpty(simSerial)) {
                    return simSerial;
                }
            }
            return super.call(who, method, args);
        }
    }

    @FakeDeviceMark("fake sim serial")
    static class GetSimSerialNumberForSubscriber extends GetSimSerialNumber {
        @Override
        public String getMethodName() {
            return "getSimSerialNumberForSubscriber";
        }
    }

    static class GetIccSerialNumberForSubscriber extends GetIccSerialNumber {
        @Override
        public String getMethodName() {
            return "getIccSerialNumberForSubscriber";
        }
    }
}
