package com.oplus.os;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.OplusPropertyList;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
/* loaded from: classes4.dex */
public class OplusBuild {
    public static final int OplusOS_11_0 = 19;
    public static final int OplusOS_11_1 = 20;
    public static final int OplusOS_11_2 = 21;
    public static final int OplusOS_11_3 = 22;
    public static final int OplusOS_12_0 = 23;
    public static final int OplusOS_12_1 = 24;
    public static final int OplusOS_12_2 = 25;
    public static final int OplusOS_1_0 = 1;
    public static final int OplusOS_1_2 = 2;
    public static final int OplusOS_1_4 = 3;
    public static final int OplusOS_2_0 = 4;
    public static final int OplusOS_2_1 = 5;
    public static final int OplusOS_3_0 = 6;
    public static final int OplusOS_3_1 = 7;
    public static final int OplusOS_3_2 = 8;
    public static final int OplusOS_5_0 = 9;
    public static final int OplusOS_5_1 = 10;
    public static final int OplusOS_5_2 = 11;
    public static final int OplusOS_6_0 = 12;
    public static final int OplusOS_6_1 = 13;
    public static final int OplusOS_6_2 = 14;
    public static final int OplusOS_6_7 = 15;
    public static final int OplusOS_7_0 = 16;
    public static final int OplusOS_7_1 = 17;
    public static final int OplusOS_7_2 = 18;
    private static final String SECURE_SETTINGS_BLUETOOTH_NAME = "bluetooth_name";
    public static final int UNKNOWN = 0;
    public static final String MARKET = getString(OplusPropertyList.PROPERTY_OPLUS_VENDOR_MARKET_NAME);
    private static final String[] VERSIONS = {"V1.0", "V1.2", "V1.4", "V2.0", "V2.1", "V3.0", "V3.1", "V3.2", "V5.0", "V5.1", "V5.2", "V6.0", "V6.1", "V6.2", "V6.7", "V7", "V7.1", "V7.2", "V11", "V11.1", "V11.2", "V11.3", "V12", "V12.1", "V12.2", null};

    /* loaded from: classes4.dex */
    public static class VERSION {
        public static final String RELEASE = OplusBuild.getVersionProp("ro.build.version.oplusrom");
    }

    public static int getOplusOSVERSION() {
        for (int i = VERSIONS.length - 2; i >= 0; i--) {
            if (!TextUtils.isEmpty(VERSION.RELEASE) && VERSION.RELEASE.startsWith(VERSIONS[i])) {
                return i + 1;
            }
        }
        return 23;
    }

    public static boolean setDeviceName(String name) {
        return true;
    }

    public static String getDeviceName() {
        return null;
    }

    public static String getDeviceName(Context context) {
        String name = Settings.Global.getString(context.getContentResolver(), Settings.Global.DEVICE_NAME);
        if (name != null && name.length() != 0 && !name.trim().isEmpty()) {
            return name;
        }
        String name2 = SystemProperties.get(OplusPropertyList.PROPERTY_OPLUS_VENDOR_MARKET_NAME, "");
        if (TextUtils.isEmpty(name2)) {
            return Build.MODEL;
        }
        putDeviceName(context, name2);
        return name2;
    }

    public static void putDeviceName(Context context, String deviceName) {
        if (deviceName != null) {
            Settings.Global.putString(context.getContentResolver(), Settings.Global.DEVICE_NAME, deviceName);
        }
    }

    public static void setDeviceName(Context context, String deviceName) {
        WifiP2pManager wifiP2pManager;
        WifiP2pManager.Channel channel;
        if (deviceName != null && !deviceName.trim().isEmpty()) {
            putDeviceName(context, deviceName);
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (!(!wifiManager.isWifiEnabled() || (wifiP2pManager = (WifiP2pManager) context.getSystemService(Context.WIFI_P2P_SERVICE)) == null || (channel = wifiP2pManager.initialize(context, context.getMainLooper(), null)) == null)) {
                wifiP2pManager.setDeviceName(channel, deviceName, null);
            }
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            if (adapter != null && adapter.isEnabled()) {
                adapter.setName(deviceName);
            }
        }
    }

    private static String getString(String property) {
        return SystemProperties.get(property, "unknown");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getVersionProp(String property) {
        String confidential = SystemProperties.get("ro.build.version.oplusrom.confidential", "unknown");
        if (!OplusPropertyList.VERSION_CONFIDENTIAL.equals("true") || confidential.equals("unknown")) {
            return SystemProperties.get(property, "unknown");
        }
        return confidential;
    }
}
