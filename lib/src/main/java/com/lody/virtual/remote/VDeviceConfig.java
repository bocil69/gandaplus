package com.lody.virtual.remote;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.lody.virtual.os.VEnvironment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author Lody
 */
public class VDeviceConfig implements Parcelable {

    private static final UsedDeviceInfoPool mPool = new UsedDeviceInfoPool();

    private static final class UsedDeviceInfoPool {
        final List<String> deviceIds = new ArrayList<>();
        final List<String> androidIds = new ArrayList<>();
        final List<String> wifiMacs = new ArrayList<>();
        final List<String> bluetoothMacs = new ArrayList<>();
        final List<String> iccIds = new ArrayList<>();
        final List<String> subscriberIds = new ArrayList<>();
        final List<String> simSerials = new ArrayList<>();
    }

    public static final int VERSION = 5;

    public boolean enable;

    public String deviceId;
    public String androidId;
    public String wifiMac;
    public String bluetoothMac;
    public String iccId;
    public String subscriberId;
    public String simSerial;
    public String networkOperator;
    public String operatorName;
    public String countryIso;
    public String serial;
    public String gmsAdId;
    public String alias;

    public final Map<String, String> buildProp = new HashMap<>();

    public VDeviceConfig() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.enable ? (byte) 1 : (byte) 0);
        dest.writeString(this.deviceId);
        dest.writeString(this.androidId);
        dest.writeString(this.wifiMac);
        dest.writeString(this.bluetoothMac);
        dest.writeString(this.iccId);
        dest.writeString(this.subscriberId);
        dest.writeString(this.simSerial);
        dest.writeString(this.networkOperator);
        dest.writeString(this.operatorName);
        dest.writeString(this.countryIso);
        dest.writeString(this.serial);
        dest.writeString(this.gmsAdId);
        dest.writeString(this.alias);
        dest.writeInt(this.buildProp.size());
        for (Map.Entry<String, String> entry : this.buildProp.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    public VDeviceConfig(Parcel in) {
        this(in, VERSION);
    }

    public VDeviceConfig(Parcel in, int version) {
        this.enable = in.readByte() != 0;
        this.deviceId = in.readString();
        this.androidId = in.readString();
        this.wifiMac = in.readString();
        this.bluetoothMac = in.readString();
        this.iccId = in.readString();
        if (version >= 4) {
            this.subscriberId = in.readString();
            this.simSerial = in.readString();
            this.networkOperator = in.readString();
            if (version >= 5) {
                this.operatorName = in.readString();
                this.countryIso = in.readString();
            }
        }
        this.serial = in.readString();
        this.gmsAdId = in.readString();
        this.alias = in.readString();
        int buildPropSize = in.readInt();
        for (int i = 0; i < buildPropSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.buildProp.put(key, value);
        }
    }

    public static final Creator<VDeviceConfig> CREATOR = new Creator<VDeviceConfig>() {
        @Override
        public VDeviceConfig createFromParcel(Parcel source) {
            return new VDeviceConfig(source);
        }

        @Override
        public VDeviceConfig[] newArray(int size) {
            return new VDeviceConfig[size];
        }
    };

    public String getProp(String key) {
        return buildProp.get(key);
    }

    public void setProp(String key, String value) {
        buildProp.put(key, value);
    }


    public void clear() {
        deviceId = null;
        androidId = null;
        wifiMac = null;
        bluetoothMac = null;
        iccId = null;
        subscriberId = null;
        simSerial = null;
        networkOperator = null;
        operatorName = null;
        countryIso = null;
        serial = null;
        gmsAdId = null;
    }

    public static VDeviceConfig random() {
        VDeviceConfig info = new VDeviceConfig();
        String value;
        do {
            value = generateDeviceId();
            info.deviceId = value;
        } while (mPool.deviceIds.contains(value));
        do {
            // Kat: generateRandomStringLow(16) = Long.toString(abs(rnd.nextLong()), 16)
            value = Long.toString(Math.abs(new Random().nextLong()), 16).toLowerCase();
            // ensure exactly 16 chars
            if (value.length() > 16) value = value.substring(0, 16);
            info.androidId = value;
        } while (mPool.androidIds.contains(value));
        do {
            value = generateMac();
            info.wifiMac = value;
        } while (mPool.wifiMacs.contains(value));
        do {
            value = generateMac();
            info.bluetoothMac = value;
        } while (mPool.bluetoothMacs.contains(value));

        do {
            value = generateNumeric(20);
            info.iccId = value;
        } while (mPool.iccIds.contains(value));

        OperatorProfile operatorProfile = loadRandomOperatorProfile();
        String operatorCode = normalizeOperatorCode(operatorProfile.networkOperator);
        do {
            value = generateImsi(operatorCode);
            info.subscriberId = value;
        } while (mPool.subscriberIds.contains(value));

        info.simSerial = info.iccId;
        info.networkOperator = operatorCode;
        info.operatorName = operatorProfile.operatorName;
        info.countryIso = operatorProfile.countryIso;

        // Kat: generateRandomStringLow(16) = base-36/hex long
        info.serial = generateSerial();
        info.buildProp.put("SERIAL", info.serial);
        // Kat: generateUUID()
        info.gmsAdId = UUID.randomUUID().toString();

        // Default values in case assets fail to load
        String bBrand = "samsung";
        String bModel = "SM-G975F";
        String bDevice = "beyond2";
        String bProduct = "beyond2ltexx";
        String bManuf = "Samsung";
        String bRelease = "11";
        String bBuildId = "RP1A.200720.012";
        String bInc = "G975FXXUAFUE3";
        
        try {
            android.content.Context context = com.lody.virtual.client.core.VirtualCore.get().getContext();
            if (context != null) {
                java.io.InputStream is = context.getAssets().open("prop.json");
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                is.close();
                String jsonStr = new String(buffer, "UTF-8");
                org.json.JSONArray rootArray = new org.json.JSONArray(jsonStr);
                if (rootArray.length() > 0) {
                    int brandIndex = new Random().nextInt(rootArray.length());
                    org.json.JSONObject brandObj = rootArray.getJSONObject(brandIndex);
                    org.json.JSONArray dataArray = brandObj.getJSONArray("DATA");
                    if (dataArray.length() > 0) {
                        int deviceIndex = new Random().nextInt(dataArray.length());
                        org.json.JSONObject deviceObj = dataArray.getJSONObject(deviceIndex);

                        // JSON uses "MEREK" instead of "BRAND"
                        bBrand = deviceObj.optString("BRAND", deviceObj.optString("MEREK", bBrand));
                        bModel = deviceObj.optString("MODEL", bModel);
                        bDevice = deviceObj.optString("DEVICE", bDevice);
                        bProduct = deviceObj.optString("PRODUCT", bProduct);
                        bManuf = deviceObj.optString("MANUFACTURER", bManuf);
                        bRelease = deviceObj.optString("RELEASE", bRelease);
                        bBuildId = deviceObj.optString("BUILDID", bBuildId);
                        bInc = deviceObj.optString("INCREMENTAL", bInc);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Use default values set above
        }
        
        // Always set build properties (either from JSON or defaults)
        info.buildProp.put("MANUFACTURER", bManuf);
        info.buildProp.put("MODEL", bModel);
        info.buildProp.put("BRAND", bBrand);
        info.buildProp.put("PRODUCT", bProduct);
        info.buildProp.put("DEVICE", bDevice);
        info.buildProp.put("ID", bBuildId);
        info.buildProp.put("DISPLAY", bBuildId);
        info.buildProp.put("BOARD", "kona");
        info.buildProp.put("HARDWARE", "qcom");
        info.buildProp.put("BOOTLOADER", bInc);
        info.buildProp.put("SERIAL", info.serial);
        info.buildProp.put("ro.build.version.release", bRelease);
        info.buildProp.put("ro.build.id", bBuildId);
        info.buildProp.put("ro.build.display.id", bBuildId);
        info.buildProp.put("ro.product.model", bModel);
        info.buildProp.put("ro.product.manufacturer", bManuf);
        info.buildProp.put("ro.product.brand", bBrand);
        info.buildProp.put("ro.product.device", bDevice);
        
        String fingerprint = bBrand + "/" + bProduct + "/" + bDevice + ":" + bRelease + "/" + bBuildId + "/" + bInc + ":user/release-keys";
        info.buildProp.put("FINGERPRINT", fingerprint);
        info.buildProp.put("ro.build.fingerprint", fingerprint);

        addToPool(info);
        return info;
    }


    public static String generateDeviceId() {
        // Kat: generateRandomNumber() = "86" + 13 SecureRandom digits
        SecureRandom sr = new SecureRandom();
        StringBuilder sb = new StringBuilder("86");
        for (int i = 0; i < 13; i++) {
            sb.append(sr.nextInt(10));
        }
        return sb.toString();
    }


    public static String generate10(long seed, int length) {
        Random random = new Random(seed);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static String generateHex(long seed, int length) {
        // Use SecureRandom for true randomness (ignore seed)
        SecureRandom random = new SecureRandom();
        String hex = "0123456789abcdef";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(hex.charAt(random.nextInt(16)));
        }
        return sb.toString();
    }

    private static String generateImsi(String operatorCode) {
        SecureRandom sr = new SecureRandom();
        String normalized = normalizeOperatorCode(operatorCode);
        StringBuilder sb = new StringBuilder(normalized);
        int digitsToAdd = Math.max(0, 15 - normalized.length());
        for (int i = 0; i < digitsToAdd; i++) {
            sb.append(sr.nextInt(10));
        }
        return sb.toString();
    }

    private static String normalizeOperatorCode(String networkOperator) {
        String fallback = "51010";
        if (TextUtils.isEmpty(networkOperator)) {
            return fallback;
        }
        String digits = networkOperator.replaceAll("\\D+", "");
        if (digits.length() < 5) {
            return fallback;
        }
        return digits.length() > 6 ? digits.substring(0, 6) : digits;
    }

    private static OperatorProfile loadRandomOperatorProfile() {
        OperatorProfile fallback = new OperatorProfile("Telkomsel", "51010", "id");
        InputStream is = null;
        try {
            android.content.Context context = com.lody.virtual.client.core.VirtualCore.get().getContext();
            if (context == null) {
                return fallback;
            }
            is = context.getAssets().open("operator.json");
            byte[] buffer = new byte[is.available()];
            int read = is.read(buffer);
            if (read <= 0) {
                return fallback;
            }
            org.json.JSONArray rootArray = new org.json.JSONArray(new String(buffer, 0, read, "UTF-8"));
            if (rootArray.length() == 0) {
                return fallback;
            }
            int countryIndex = new Random().nextInt(rootArray.length());
            org.json.JSONObject countryObj = rootArray.getJSONObject(countryIndex);
            String iso = countryObj.optString("ISO", "id").toLowerCase();
            org.json.JSONArray dataArray = countryObj.optJSONArray("DATA");
            if (dataArray == null || dataArray.length() == 0) {
                return new OperatorProfile(fallback.operatorName, fallback.networkOperator, iso);
            }
            int operatorIndex = new Random().nextInt(dataArray.length());
            org.json.JSONObject operatorObj = dataArray.getJSONObject(operatorIndex);
            String operatorName = operatorObj.optString("operator", fallback.operatorName);
            String mcc = operatorObj.optString("mcc", "510");
            String mnc = operatorObj.optString("mnc", "10");
            return new OperatorProfile(operatorName, mcc + mnc, iso);
        } catch (Exception ignored) {
            return fallback;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    private static final class OperatorProfile {
        final String operatorName;
        final String networkOperator;
        final String countryIso;

        OperatorProfile(String operatorName, String networkOperator, String countryIso) {
            this.operatorName = operatorName;
            this.networkOperator = networkOperator;
            this.countryIso = TextUtils.isEmpty(countryIso) ? "id" : countryIso.toLowerCase();
        }
    }

    private static String generateNumeric(int len) {
        SecureRandom sr = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(sr.nextInt(10));
        }
        return sb.toString();
    }

    private static String generateMac() {
        Random random = new Random();
        String HEX_CHARACTERS = "0123456789abcdef";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(HEX_CHARACTERS.charAt(random.nextInt(16)));
            sb.append(HEX_CHARACTERS.charAt(random.nextInt(16)));
            if (i < 5) {
                sb.append(":");
            }
        }
        return sb.toString();
    }

    @SuppressLint("HardwareIds")
    private static String generateSerial() {
        // Kat: generateRandomStringLow(16) = base-hex of random long
        String s = Long.toString(Math.abs(new Random().nextLong()), 16).toLowerCase();
        return s.length() >= 16 ? s.substring(0, 16) : s;
    }


    public File getWifiFile(int userId, boolean is64Bit) {
        if (TextUtils.isEmpty(wifiMac)) {
            return null;
        }
        File wifiMacFie = VEnvironment.getWifiMacFile(userId, is64Bit);
        if (!wifiMacFie.exists()) {
            try {
                RandomAccessFile file = new RandomAccessFile(wifiMacFie, "rws");
                file.write((wifiMac + "\n").getBytes());
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wifiMacFie;
    }

    public static void addToPool(VDeviceConfig info) {
        mPool.deviceIds.add(info.deviceId);
        mPool.androidIds.add(info.androidId);
        mPool.wifiMacs.add(info.wifiMac);
        mPool.bluetoothMacs.add(info.bluetoothMac);
        mPool.iccIds.add(info.iccId);
        if (!TextUtils.isEmpty(info.subscriberId)) {
            mPool.subscriberIds.add(info.subscriberId);
        }
        if (!TextUtils.isEmpty(info.simSerial)) {
            mPool.simSerials.add(info.simSerial);
        }
    }
}
