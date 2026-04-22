package com.lody.virtual.client.hook.proxies.telephony;

import android.os.Bundle;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.NeighboringCellInfo;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;

import com.lody.virtual.client.hook.base.ReplaceCallingPkgMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.client.hook.annotations.SkipInject;
import com.lody.virtual.client.ipc.VirtualLocationManager;
import com.lody.virtual.helper.utils.marks.FakeDeviceMark;
import com.lody.virtual.helper.utils.marks.FakeLocMark;
import com.lody.virtual.remote.VDeviceConfig;
import com.lody.virtual.remote.vloc.VCell;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.xdja.zs.VAppPermissionManager;
/**
 * @author Lody
 */
@SuppressWarnings("ALL")
class MethodProxies {

    @FakeDeviceMark("fake device id.")
    static class GetDeviceId extends ReplaceLastPkgMethodProxy {

        public GetDeviceId() {
            super("getDeviceId");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            VDeviceConfig config = getDeviceConfig();
            if (config.enable) {
                String imei = config.deviceId;
                if (!TextUtils.isEmpty(imei)) {
                    return imei;
                }
            }
            return super.call(who, method, args);
        }
    }

    @FakeDeviceMark("fake device id.")
    static class GetImeiForSlot extends GetDeviceId {
        @Override
        public String getMethodName() {
            return "getImeiForSlot";
        }
    }

    @FakeDeviceMark("fake device id.")
    static class GetMeidForSlot extends GetDeviceId {
        @Override
        public String getMethodName() {
            return "getMeidForSlot";
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

    @FakeDeviceMark("fake network operator")
    static class GetNetworkOperator extends ReplaceLastPkgMethodProxy {

        public GetNetworkOperator() {
            super("getNetworkOperator");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            VDeviceConfig config = getDeviceConfig();
            if (config.enable) {
                String networkOperator = getOperatorCode(config);
                if (!TextUtils.isEmpty(networkOperator)) {
                    return networkOperator;
                }
            }
            return super.call(who, method, args);
        }
    }

    @FakeDeviceMark("fake network operator name")
    static class GetNetworkOperatorName extends ReplaceLastPkgMethodProxy {

        public GetNetworkOperatorName() {
            super("getNetworkOperatorName");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            VDeviceConfig config = getDeviceConfig();
            if (config.enable) {
                return getOperatorName(config);
            }
            return super.call(who, method, args);
        }
    }

    @FakeDeviceMark("fake sim operator")
    static class GetSimOperator extends GetNetworkOperator {
        @Override
        public String getMethodName() {
            return "getSimOperator";
        }
    }

    @FakeDeviceMark("fake sim operator name")
    static class GetSimOperatorName extends GetNetworkOperatorName {
        @Override
        public String getMethodName() {
            return "getSimOperatorName";
        }
    }

    @FakeDeviceMark("fake network country iso")
    static class GetNetworkCountryIso extends ReplaceLastPkgMethodProxy {

        public GetNetworkCountryIso() {
            super("getNetworkCountryIso");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            VDeviceConfig config = getDeviceConfig();
            if (config.enable) {
                return getCountryIso(config);
            }
            return super.call(who, method, args);
        }
    }

    @FakeDeviceMark("fake sim country iso")
    static class GetSimCountryIso extends GetNetworkCountryIso {
        @Override
        public String getMethodName() {
            return "getSimCountryIso";
        }
    }

    static String getOperatorCode(VDeviceConfig config) {
        if (!TextUtils.isEmpty(config.networkOperator)) {
            return config.networkOperator;
        }
        if (!TextUtils.isEmpty(config.subscriberId) && config.subscriberId.length() >= 5) {
            return config.subscriberId.substring(0, Math.min(6, config.subscriberId.length()));
        }
        return null;
    }

    static String getOperatorName(VDeviceConfig config) {
        if (!TextUtils.isEmpty(config.operatorName)) {
            return config.operatorName;
        }
        String operatorName = config.getProp("MANUFACTURER");
        return TextUtils.isEmpty(operatorName) ? "Unknown" : operatorName;
    }

    static String getCountryIso(VDeviceConfig config) {
        if (!TextUtils.isEmpty(config.countryIso)) {
            return config.countryIso.toLowerCase();
        }
        return mapMccToCountryIso(getOperatorCode(config));
    }

    static int getMcc(VDeviceConfig config) {
        String operatorCode = getOperatorCode(config);
        if (TextUtils.isEmpty(operatorCode) || operatorCode.length() < 3) {
            return 0;
        }
        return safeParseInt(operatorCode.substring(0, 3));
    }

    static int getMnc(VDeviceConfig config) {
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

    private static String mapMccToCountryIso(String networkOperator) {
        if (TextUtils.isEmpty(networkOperator) || networkOperator.length() < 3) {
            return "us";
        }
        String mcc = networkOperator.substring(0, 3);
        if ("460".equals(mcc)) return "cn";
        if ("404".equals(mcc) || "405".equals(mcc)) return "in";
        if ("510".equals(mcc)) return "id";
        if ("525".equals(mcc)) return "sg";
        if ("440".equals(mcc)) return "jp";
        return "us";
    }


    @SkipInject
    @FakeLocMark("cell location")
    static class GetCellLocation extends ReplaceCallingPkgMethodProxy {

        public GetCellLocation() {
            super("getCellLocation");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_TelephonyStub", "getCellLocation return");
                return null;
            }
            if (isFakeLocationEnable()) {
                VCell cell = VirtualLocationManager.get().getCell(getAppUserId(), getAppPkg());
                if (cell != null) {
                    return getCellLocationInternal(cell);
                }
                return null;
            }
            return super.call(who, method, args);
        }
    }

    static class GetAllCellInfoUsingSubId extends ReplaceCallingPkgMethodProxy {

        public GetAllCellInfoUsingSubId() {
            super("getAllCellInfoUsingSubId");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_TelephonyStub", "getAllCellInfoUsingSubId return");
                return null;
            }
            if (isFakeLocationEnable()) {
                return null;
            }
            return super.call(who, method, args);
        }
    }

    @SkipInject
    @FakeLocMark("cell location")
    static class GetAllCellInfo extends ReplaceCallingPkgMethodProxy {

        public GetAllCellInfo() {
            super("getAllCellInfo");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_TelephonyRegStub", "getAllCellInfo return");
                return null;
            }
            if (isFakeLocationEnable()) {
                List<VCell> cells = VirtualLocationManager.get().getAllCell(getAppUserId(), getAppPkg());
                if (cells != null) {
                    List<CellInfo> result = new ArrayList<CellInfo>();
                    for (VCell cell : cells) {
                        result.add(createCellInfo(cell));
                    }
                    return result;
                }
                return null;
            }
            return super.call(who, method, args);
        }
    }

    @SkipInject
    @FakeLocMark("neb cell location")
    static class GetNeighboringCellInfo extends ReplaceCallingPkgMethodProxy {

        public GetNeighboringCellInfo() {
            super("getNeighboringCellInfo");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            boolean appPermissionEnable = VAppPermissionManager.get().getLocationEnable(getAppPkg());
            if (appPermissionEnable) {
                Log.e("geyao_TelephonyStub", "getNeighboringCellInfo return");
                return null;
            }
            if (isFakeLocationEnable()) {
                List<VCell> cells = VirtualLocationManager.get().getNeighboringCell(getAppUserId(), getAppPkg());
                if (cells != null) {
                    List<NeighboringCellInfo> infos = new ArrayList<>();
                    for (VCell cell : cells) {
                        NeighboringCellInfo info = new NeighboringCellInfo();
                        mirror.android.telephony.NeighboringCellInfo.mLac.set(info, cell.lac);
                        mirror.android.telephony.NeighboringCellInfo.mCid.set(info, cell.cid);
                        mirror.android.telephony.NeighboringCellInfo.mRssi.set(info, 6);
                        infos.add(info);
                    }
                    return infos;
                }
                return null;
            }
            return super.call(who, method, args);
        }
    }

    private static Bundle getCellLocationInternal(VCell cell) {
        if (cell != null) {
            Bundle cellData = new Bundle();
            if (cell.type == 2) {
                try {
                    CdmaCellLocation cellLoc = new CdmaCellLocation();
                    cellLoc.setCellLocationData(cell.baseStationId, Integer.MAX_VALUE, Integer.MAX_VALUE, cell.systemId, cell.networkId);
                    cellLoc.fillInNotifierBundle(cellData);
                } catch (Throwable e) {
                    cellData.putInt("baseStationId", cell.baseStationId);
                    cellData.putInt("baseStationLatitude", Integer.MAX_VALUE);
                    cellData.putInt("baseStationLongitude", Integer.MAX_VALUE);
                    cellData.putInt("systemId", cell.systemId);
                    cellData.putInt("networkId", cell.networkId);
                }
            } else {
                try {
                    GsmCellLocation cellLoc = new GsmCellLocation();
                    cellLoc.setLacAndCid(cell.lac, cell.cid);
                    cellLoc.fillInNotifierBundle(cellData);
                } catch (Throwable e) {
                    cellData.putInt("lac", cell.lac);
                    cellData.putInt("cid", cell.cid);
                    cellData.putInt("psc", cell.psc);
                }
            }
            return cellData;
        }
        return null;
    }


    private static CellInfo createCellInfo(VCell cell) {
        if (cell.type == 2) { // CDMA
            CellInfoCdma cdma = mirror.android.telephony.CellInfoCdma.ctor.newInstance();
            CellIdentityCdma identityCdma = mirror.android.telephony.CellInfoCdma.mCellIdentityCdma.get(cdma);
            CellSignalStrengthCdma strengthCdma = mirror.android.telephony.CellInfoCdma.mCellSignalStrengthCdma.get(cdma);
            mirror.android.telephony.CellIdentityCdma.mNetworkId.set(identityCdma, cell.networkId);
            mirror.android.telephony.CellIdentityCdma.mSystemId.set(identityCdma, cell.systemId);
            mirror.android.telephony.CellIdentityCdma.mBasestationId.set(identityCdma, cell.baseStationId);
            mirror.android.telephony.CellSignalStrengthCdma.mCdmaDbm.set(strengthCdma, -74);
            mirror.android.telephony.CellSignalStrengthCdma.mCdmaEcio.set(strengthCdma, -91);
            mirror.android.telephony.CellSignalStrengthCdma.mEvdoDbm.set(strengthCdma, -64);
            mirror.android.telephony.CellSignalStrengthCdma.mEvdoSnr.set(strengthCdma, 7);
            return cdma;
        } else { // GSM
            CellInfoGsm gsm = mirror.android.telephony.CellInfoGsm.ctor.newInstance();
            CellIdentityGsm identityGsm = mirror.android.telephony.CellInfoGsm.mCellIdentityGsm.get(gsm);
            CellSignalStrengthGsm strengthGsm = mirror.android.telephony.CellInfoGsm.mCellSignalStrengthGsm.get(gsm);
            mirror.android.telephony.CellIdentityGsm.mMcc.set(identityGsm, cell.mcc);
            mirror.android.telephony.CellIdentityGsm.mMnc.set(identityGsm, cell.mnc);
            mirror.android.telephony.CellIdentityGsm.mLac.set(identityGsm, cell.lac);
            mirror.android.telephony.CellIdentityGsm.mCid.set(identityGsm, cell.cid);
            mirror.android.telephony.CellSignalStrengthGsm.mSignalStrength.set(strengthGsm, 20);
            mirror.android.telephony.CellSignalStrengthGsm.mBitErrorRate.set(strengthGsm, 0);
            return gsm;
        }
    }


}
