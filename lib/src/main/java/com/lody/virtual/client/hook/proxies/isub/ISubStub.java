package com.lody.virtual.client.hook.proxies.isub;

import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.lody.virtual.client.VClient;
import com.lody.virtual.client.hook.base.BinderInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import com.lody.virtual.client.ipc.VLocationManager;
import com.lody.virtual.remote.VDeviceConfig;

import java.lang.reflect.Method;
import java.util.List;

import mirror.com.android.internal.telephony.ISub;

/**
 * @author Lody
 */
public class ISubStub extends BinderInvocationProxy {

    public ISubStub() {
        super(ISub.Stub.asInterface, "isub");
    }

    @Override
    protected void onBindMethods() {
        super.onBindMethods();
        addMethodProxy(new GetAllSubInfoList());
        addMethodProxy(new ReplaceCallingPkgMethodProxy("getAllSubInfoCount"));
        addMethodProxy(new GetActiveSubscriptionInfo("getActiveSubscriptionInfo"));
        addMethodProxy(new GetActiveSubscriptionInfo("getActiveSubscriptionInfoForIccId"));
        addMethodProxy(new GetActiveSubscriptionInfo("getActiveSubscriptionInfoForSimSlotIndex"));
        addMethodProxy(new GetActiveSubscriptionInfoList());
        addMethodProxy(new ReplaceLastPkgMethodProxy("getActiveSubInfoCount"));
        addMethodProxy(new GetSubscriptionProperty());
        addMethodProxy(new StaticMethodProxy(Build.VERSION.SDK_INT >= 24 ?
                "getSimStateForSlotIdx" : "getSimStateForSubscriber"));
    }

    private static class GetAllSubInfoList extends ReplaceCallingPkgMethodProxy {
        GetAllSubInfoList() {
            super("getAllSubInfoList");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            return patchSubscriptionInfoResult(super.call(who, method, args));
        }
    }

    private static class GetActiveSubscriptionInfo extends ReplaceLastPkgMethodProxy {
        GetActiveSubscriptionInfo(String name) {
            super(name);
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            return patchSubscriptionInfoResult(super.call(who, method, args));
        }
    }

    private static class GetActiveSubscriptionInfoList extends StaticMethodProxy {
        GetActiveSubscriptionInfoList() {
            super("getActiveSubscriptionInfoList");
        }

        @Override
        public Object call(Object who, Method method, Object... args) {
            try {
                return patchSubscriptionInfoResult(super.call(who, method, args));
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static class GetSubscriptionProperty extends ReplaceLastPkgMethodProxy {
        GetSubscriptionProperty() {
            super("getSubscriptionProperty");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            VDeviceConfig config = getDeviceConfig();
            if (config.enable && args != null && args.length > 1 && args[1] instanceof String) {
                String key = (String) args[1];
                String spoofed = getSpoofedSubscriptionProperty(config, key);
                if (spoofed != null) {
                    return spoofed;
                }
            }
            return super.call(who, method, args);
        }
    }

    private static Object patchSubscriptionInfoResult(Object result) {
        VDeviceConfig config = VClient.get().getDeviceConfig();
        if (result == null || config == null || !config.enable) {
            return result;
        }
        if (result instanceof SubscriptionInfo) {
            patchSubscriptionInfo((SubscriptionInfo) result, config);
        } else if (result instanceof List) {
            List<?> list = (List<?>) result;
            for (Object item : list) {
                if (item instanceof SubscriptionInfo) {
                    patchSubscriptionInfo((SubscriptionInfo) item, config);
                }
            }
        }
        return result;
    }

    private static void patchSubscriptionInfo(SubscriptionInfo info, VDeviceConfig config) {
        if (mirror.android.telephony.SubscriptionInfo.mCarrierName != null) {
            mirror.android.telephony.SubscriptionInfo.mCarrierName.set(info, getOperatorName(config));
        }
        if (mirror.android.telephony.SubscriptionInfo.mCountryIso != null) {
            mirror.android.telephony.SubscriptionInfo.mCountryIso.set(info, getCountryIso(config));
        }
        if (mirror.android.telephony.SubscriptionInfo.mMcc != null) {
            mirror.android.telephony.SubscriptionInfo.mMcc.set(info, getMcc(config));
        }
        if (mirror.android.telephony.SubscriptionInfo.mMnc != null) {
            mirror.android.telephony.SubscriptionInfo.mMnc.set(info, getMnc(config));
        }
    }

    private static String getSpoofedSubscriptionProperty(VDeviceConfig config, String key) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        String normalized = key.toLowerCase();
        if ("carrier_name".equals(normalized) || "display_name".equals(normalized)) {
            return getOperatorName(config);
        }
        if ("country_iso".equals(normalized)) {
            return getCountryIso(config);
        }
        if ("mcc_string".equals(normalized)) {
            return String.format(java.util.Locale.US, "%03d", getMcc(config));
        }
        if ("mnc_string".equals(normalized)) {
            String operatorCode = getOperatorCode(config);
            if (!TextUtils.isEmpty(operatorCode) && operatorCode.length() > 3) {
                return operatorCode.substring(3);
            }
            return null;
        }
        if ("operator_alpha".equals(normalized) || "icc_operator_alpha".equals(normalized)) {
            return getOperatorName(config);
        }
        if ("operator_numeric".equals(normalized) || "icc_operator_numeric".equals(normalized)) {
            return getOperatorCode(config);
        }
        return null;
    }

    private static String getOperatorCode(VDeviceConfig config) {
        if (!TextUtils.isEmpty(config.networkOperator)) {
            return config.networkOperator;
        }
        if (!TextUtils.isEmpty(config.subscriberId) && config.subscriberId.length() >= 5) {
            return config.subscriberId.substring(0, Math.min(6, config.subscriberId.length()));
        }
        return null;
    }

    private static String getOperatorName(VDeviceConfig config) {
        if (!TextUtils.isEmpty(config.operatorName)) {
            return config.operatorName;
        }
        String manufacturer = config.getProp("MANUFACTURER");
        return TextUtils.isEmpty(manufacturer) ? "Unknown" : manufacturer;
    }

    private static String getCountryIso(VDeviceConfig config) {
        if (!TextUtils.isEmpty(config.countryIso)) {
            return config.countryIso.toLowerCase();
        }
        String operatorCode = getOperatorCode(config);
        if (TextUtils.isEmpty(operatorCode) || operatorCode.length() < 3) {
            return "us";
        }
        String mcc = operatorCode.substring(0, 3);
        if ("510".equals(mcc)) return "id";
        if ("525".equals(mcc)) return "sg";
        if ("460".equals(mcc)) return "cn";
        if ("404".equals(mcc) || "405".equals(mcc)) return "in";
        if ("440".equals(mcc)) return "jp";
        return "us";
    }

    private static int getMcc(VDeviceConfig config) {
        String operatorCode = getOperatorCode(config);
        if (TextUtils.isEmpty(operatorCode) || operatorCode.length() < 3) {
            return 0;
        }
        return safeParseInt(operatorCode.substring(0, 3));
    }

    private static int getMnc(VDeviceConfig config) {
        String operatorCode = getOperatorCode(config);
        if (TextUtils.isEmpty(operatorCode) || operatorCode.length() <= 3) {
            return 0;
        }
        return safeParseInt(operatorCode.substring(3));
    }

    private static int safeParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
