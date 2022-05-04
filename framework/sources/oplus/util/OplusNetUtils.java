package oplus.util;

import android.net.MacAddress;
import android.net.wifi.WifiConfiguration;
import android.util.Log;
import com.android.internal.content.NativeLibraryHelper;
/* loaded from: classes4.dex */
public class OplusNetUtils {
    private static final String TAG = "OplusNetUtils";

    public static String ipStrMask(String ipAddr) {
        if (ipAddr == null || "".equals(ipAddr)) {
            return "";
        }
        try {
            String tmpStr = ipAddr.replaceAll("((\\d{1,3}\\.){3})\\d{1,3}", "$1***");
            return tmpStr;
        } catch (Exception e) {
            Log.e(TAG, "invalid ip address exception e" + e);
            return "invalid ip address";
        }
    }

    public static String macStrMask(String macAddr) {
        if (macAddr == null) {
            return "null";
        }
        if ("".equals(macAddr) || "any".equals(macAddr)) {
            return macAddr;
        }
        try {
            String tmpStr = macAddr.replaceAll("([A-Fa-f0-9]{2})((:[A-Fa-f0-9]{2}){3})((:[A-Fa-f0-9]{2}){2})", "$1:**:**:**$4");
            return tmpStr;
        } catch (Exception e) {
            Log.e(TAG, "invalid mac address exception e" + e);
            return "invalid mac address";
        }
    }

    public static String macStrMask(MacAddress mac) {
        if (mac == null) {
            return "null";
        }
        return macStrMask(mac.toString());
    }

    public static String normalStrMask(String targetStr) {
        if (targetStr == null || targetStr.isEmpty()) {
            return "EMPTY";
        }
        char[] targetBit = targetStr.toCharArray();
        if (targetBit.length > 4) {
            return "" + targetBit[0] + targetBit[1] + "***" + targetBit[targetBit.length - 2] + targetBit[targetBit.length - 1];
        } else if (targetBit.length < 3) {
            return "" + targetBit[0] + "*";
        } else {
            return "" + targetBit[0] + "**" + targetBit[targetBit.length - 1];
        }
    }

    public static String urlStrMask(String targetStr) {
        if (targetStr == null || targetStr.isEmpty()) {
            return "EMPTY";
        }
        StringBuilder sbuf = new StringBuilder();
        char[] targetBit = targetStr.toCharArray();
        for (int i = 0; i < targetStr.length(); i += 2) {
            sbuf.append(targetBit[i] + "*");
        }
        return sbuf.toString();
    }

    public static String getAuthType(WifiConfiguration config) {
        if (config == null) {
            return "null";
        }
        if (isConfigForWepNetwork(config)) {
            return "WEP";
        }
        try {
            return WifiConfiguration.KeyMgmt.strings[config.getAuthType()];
        } catch (Exception e) {
            return "multi";
        }
    }

    public static String getMaskedConfigKey(WifiConfiguration config) {
        if (config == null) {
            return "null";
        }
        if (config.isPasspoint()) {
            return config.getKey();
        }
        return normalStrMask(config.getPrintableSsid()) + NativeLibraryHelper.CLEAR_ABI_OVERRIDE + getAuthType(config);
    }

    public static String getMaskedConfigKey(String configKey) {
        if (configKey == null || configKey.isEmpty()) {
            return "EMPTY";
        }
        int index = configKey.lastIndexOf(34);
        if (index > 0) {
            String ssid = configKey.substring(0, index);
            String keyMgmt = configKey.substring(index);
            return normalStrMask(ssid) + NativeLibraryHelper.CLEAR_ABI_OVERRIDE + keyMgmt;
        }
        String ssid2 = normalStrMask(configKey);
        return ssid2;
    }

    private static boolean isConfigForWepNetwork(WifiConfiguration config) {
        return config != null && config.allowedKeyManagement != null && config.allowedKeyManagement.get(0) && hasAnyValidWepKey(config.wepKeys);
    }

    private static boolean hasAnyValidWepKey(String[] wepKeys) {
        for (String str : wepKeys) {
            if (str != null) {
                return true;
            }
        }
        return false;
    }
}
