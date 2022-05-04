package android.net.wifi;

import android.app.ActivityThread;
import android.content.Context;
import android.net.MacAddress;
import android.net.wifi.SoftApConfiguration;
import android.os.Environment;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import com.android.internal.util.FastXmlSerializer;
import com.android.internal.util.XmlUtils;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
/* loaded from: classes2.dex */
public final class SoftApConfToXmlMigrationUtil {
    private static final int CONFIG_STORE_DATA_VERSION = 3;
    private static final String FILE_HOSTAPD_DENY = "/data/misc/wifi/hostapd.deny";
    private static final String LEGACY_AP_CONFIG_FILE = "softap.conf";
    private static final String LEGACY_WIFI_STORE_DIRECTORY_NAME = "wifi";
    private static final String MAC_PATTERN_STR = "([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2}";
    private static final int MAX_CLIENT_DEFAULT = 10;
    private static final int MIN_CLIENT_DEFAULT = 1;
    private static final String NAME_TAG = "#name-";
    private static final String TAG = "SoftApConfToXmlMigrationUtil";
    private static final int WIFICONFIG_AP_BAND_2GHZ = 0;
    private static final int WIFICONFIG_AP_BAND_5GHZ = 1;
    private static final int WIFICONFIG_AP_BAND_ANY = -1;
    private static final String WIFI_HOTSPOT_MAX_CLIENT_NUM = "oppo_wifi_ap_max_devices_connect";
    private static final String XML_TAG_ALLOWED_CLIENT_LIST = "AllowedClientList";
    private static final String XML_TAG_AP_BAND = "ApBand";
    private static final String XML_TAG_AUTO_SHUTDOWN_ENABLED = "AutoShutdownEnabled";
    private static final String XML_TAG_BLOCKED_CLIENT_LIST = "BlockedClientList";
    private static final String XML_TAG_BSSID = "Bssid";
    private static final String XML_TAG_CHANNEL = "Channel";
    private static final String XML_TAG_CLIENT_CONTROL_BY_USER = "ClientControlByUser";
    public static final String XML_TAG_CLIENT_MACADDRESS = "ClientMacAddress";
    private static final String XML_TAG_DOCUMENT_HEADER = "WifiConfigStoreData";
    private static final String XML_TAG_HIDDEN_SSID = "HiddenSSID";
    private static final String XML_TAG_MAX_NUMBER_OF_CLIENTS = "MaxNumberOfClients";
    private static final String XML_TAG_PASSPHRASE = "Passphrase";
    private static final String XML_TAG_SECTION_HEADER_SOFTAP = "SoftAp";
    private static final String XML_TAG_SECURITY_TYPE = "SecurityType";
    private static final String XML_TAG_SHUTDOWN_TIMEOUT_MILLIS = "ShutdownTimeoutMillis";
    private static final String XML_TAG_SSID = "SSID";
    private static final String XML_TAG_VERSION = "Version";

    private static File getLegacyWifiSharedDirectory() {
        return new File(Environment.getDataMiscDirectory(), "wifi");
    }

    public static int convertWifiConfigBandToSoftApConfigBand(int wifiConfigBand) {
        switch (wifiConfigBand) {
            case -1:
                return 3;
            case 0:
                return 1;
            case 1:
                return 2;
            default:
                return 1;
        }
    }

    private static SoftApConfiguration loadFromLegacyFile(InputStream fis) {
        SoftApConfiguration.Builder configBuilder;
        DataInputStream in;
        int version;
        boolean isSoftApTimeOutEnabled;
        SoftApConfiguration config = null;
        DataInputStream in2 = null;
        try {
        } catch (IOException e) {
            Log.e(TAG, "Error closing hotspot configuration during read", e);
        }
        try {
            try {
                try {
                    configBuilder = new SoftApConfiguration.Builder();
                    in = new DataInputStream(new BufferedInputStream(fis));
                    version = in.readInt();
                    isSoftApTimeOutEnabled = true;
                } catch (IOException e2) {
                    Log.e(TAG, "Error reading hotspot configuration ", e2);
                    config = null;
                    if (0 != 0) {
                        in2.close();
                    }
                }
            } catch (IllegalArgumentException ie) {
                Log.e(TAG, "Invalid hotspot configuration ", ie);
                config = null;
                if (0 != 0) {
                    in2.close();
                }
            }
            if (version >= 1 && version <= 3) {
                configBuilder.setSsid(in.readUTF());
                if (version >= 2) {
                    int band = in.readInt();
                    int channel = in.readInt();
                    if (channel == 0) {
                        configBuilder.setBand(convertWifiConfigBandToSoftApConfigBand(band));
                    } else {
                        configBuilder.setChannel(channel, convertWifiConfigBandToSoftApConfigBand(band));
                    }
                }
                int authType = in.readInt();
                if (authType == 1 || authType == 4) {
                    configBuilder.setPassphrase(in.readUTF(), 1);
                }
                try {
                    configBuilder.setHiddenSsid(in.readBoolean());
                } catch (IOException e3) {
                    Log.e(TAG, "hiddenSSID error " + e3);
                    configBuilder.setHiddenSsid(false);
                }
                Context context = ActivityThread.currentActivityThread().getSystemContext();
                int maxNumSta = Settings.System.getInt(context.getContentResolver(), WIFI_HOTSPOT_MAX_CLIENT_NUM, 10);
                Log.d(TAG, "in addOEMToConfig, maxNumSta = " + maxNumSta);
                if (maxNumSta >= 1 && maxNumSta <= 10) {
                    configBuilder.setMaxNumberOfClients(maxNumSta);
                }
                if (Settings.Global.getInt(context.getContentResolver(), Settings.Global.SOFT_AP_TIMEOUT_ENABLED, 1) == 0) {
                    isSoftApTimeOutEnabled = false;
                }
                Log.d(TAG, "in addOEMToConfig, isSoftApTimeOutEnabled = " + isSoftApTimeOutEnabled);
                configBuilder.setAutoShutdownEnabled(isSoftApTimeOutEnabled);
                try {
                    List<String> deniedClients = convertHostApdDenyData(FILE_HOSTAPD_DENY);
                    ArrayList<MacAddress> blockedList = new ArrayList<>();
                    if (deniedClients.size() == 0) {
                        Log.d(TAG, "restoreHostApdDenyData deniedClients.size is zero.");
                    }
                    for (String client : deniedClients) {
                        blockedList.add(MacAddress.fromString(client));
                    }
                    configBuilder.setBlockedClientList(blockedList);
                } catch (RemoteException e4) {
                    Log.e(TAG, "convertHostApdDenyData failed.");
                }
                config = configBuilder.build();
                in.close();
                return config;
            }
            Log.e(TAG, "Bad version on hotspot configuration file");
            try {
                in.close();
            } catch (IOException e5) {
                Log.e(TAG, "Error closing hotspot configuration during read", e5);
            }
            return null;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    in2.close();
                } catch (IOException e6) {
                    Log.e(TAG, "Error closing hotspot configuration during read", e6);
                }
            }
            throw th;
        }
    }

    private static byte[] convertConfToXml(SoftApConfiguration softApConf) {
        try {
            XmlSerializer out = new FastXmlSerializer();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            out.setOutput(outputStream, StandardCharsets.UTF_8.name());
            out.startDocument(null, true);
            out.startTag(null, XML_TAG_DOCUMENT_HEADER);
            XmlUtils.writeValueXml((Object) 3, XML_TAG_VERSION, out);
            out.startTag(null, XML_TAG_SECTION_HEADER_SOFTAP);
            XmlUtils.writeValueXml(softApConf.getSsid(), XML_TAG_SSID, out);
            if (softApConf.getBssid() != null) {
                XmlUtils.writeValueXml(softApConf.getBssid().toString(), XML_TAG_BSSID, out);
            }
            XmlUtils.writeValueXml(Integer.valueOf(softApConf.getBand()), XML_TAG_AP_BAND, out);
            XmlUtils.writeValueXml(Integer.valueOf(softApConf.getChannel()), XML_TAG_CHANNEL, out);
            XmlUtils.writeValueXml(Boolean.valueOf(softApConf.isHiddenSsid()), XML_TAG_HIDDEN_SSID, out);
            XmlUtils.writeValueXml(Integer.valueOf(softApConf.getSecurityType()), XML_TAG_SECURITY_TYPE, out);
            if (softApConf.getSecurityType() != 0) {
                XmlUtils.writeValueXml(softApConf.getPassphrase(), XML_TAG_PASSPHRASE, out);
            }
            XmlUtils.writeValueXml(Integer.valueOf(softApConf.getMaxNumberOfClients()), XML_TAG_MAX_NUMBER_OF_CLIENTS, out);
            XmlUtils.writeValueXml(Boolean.valueOf(softApConf.isClientControlByUserEnabled()), XML_TAG_CLIENT_CONTROL_BY_USER, out);
            XmlUtils.writeValueXml(Boolean.valueOf(softApConf.isAutoShutdownEnabled()), XML_TAG_AUTO_SHUTDOWN_ENABLED, out);
            XmlUtils.writeValueXml(Long.valueOf(softApConf.getShutdownTimeoutMillis()), XML_TAG_SHUTDOWN_TIMEOUT_MILLIS, out);
            out.startTag(null, XML_TAG_BLOCKED_CLIENT_LIST);
            for (MacAddress mac : softApConf.getBlockedClientList()) {
                XmlUtils.writeValueXml(mac.toString(), XML_TAG_CLIENT_MACADDRESS, out);
            }
            out.endTag(null, XML_TAG_BLOCKED_CLIENT_LIST);
            out.startTag(null, XML_TAG_ALLOWED_CLIENT_LIST);
            for (MacAddress mac2 : softApConf.getAllowedClientList()) {
                XmlUtils.writeValueXml(mac2.toString(), XML_TAG_CLIENT_MACADDRESS, out);
            }
            out.endTag(null, XML_TAG_ALLOWED_CLIENT_LIST);
            out.endTag(null, XML_TAG_SECTION_HEADER_SOFTAP);
            out.endTag(null, XML_TAG_DOCUMENT_HEADER);
            out.endDocument();
            return outputStream.toByteArray();
        } catch (IOException | XmlPullParserException e) {
            Log.e(TAG, "Failed to convert softap conf to XML", e);
            return null;
        }
    }

    private SoftApConfToXmlMigrationUtil() {
    }

    public static InputStream convert(InputStream fis) {
        byte[] xmlBytes;
        SoftApConfiguration softApConf = loadFromLegacyFile(fis);
        if (softApConf == null || (xmlBytes = convertConfToXml(softApConf)) == null) {
            return null;
        }
        return new ByteArrayInputStream(xmlBytes);
    }

    public static InputStream convert() {
        File file = new File(getLegacyWifiSharedDirectory(), LEGACY_AP_CONFIG_FILE);
        try {
            FileInputStream fis = new FileInputStream(file);
            return convert(fis);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static void remove() {
        File file = new File(getLegacyWifiSharedDirectory(), LEGACY_AP_CONFIG_FILE);
        file.delete();
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0033, code lost:
        android.util.Log.d(android.net.wifi.SoftApConfToXmlMigrationUtil.TAG, "Reach end of hostapd.deny!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.List<java.lang.String> convertHostApdDenyData(java.lang.String r8) throws android.os.RemoteException {
        /*
            java.lang.String r0 = "#name-"
            java.lang.String r1 = "SoftApConfToXmlMigrationUtil"
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r3 = 0
            r4 = 0
            java.lang.String r5 = ""
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch: all -> 0x00b2, IOException -> 0x00b4
            java.io.FileReader r7 = new java.io.FileReader     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r7.<init>(r8)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r6.<init>(r7)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r3 = r6
        L_0x0018:
            java.lang.String r6 = r3.readLine()     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r4 = r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r6.<init>()     // Catch: all -> 0x00b2, IOException -> 0x00b4
            java.lang.String r7 = "Reach hostapd.deny! line = "
            r6.append(r7)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r6.append(r4)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            java.lang.String r6 = r6.toString()     // Catch: all -> 0x00b2, IOException -> 0x00b4
            android.util.Log.d(r1, r6)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            if (r4 != 0) goto L_0x003a
            java.lang.String r0 = "Reach end of hostapd.deny!"
            android.util.Log.d(r1, r0)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            goto L_0x00b8
        L_0x003a:
            java.lang.String r6 = "#"
            boolean r6 = r4.startsWith(r6)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            if (r6 == 0) goto L_0x007b
            boolean r6 = r4.startsWith(r0)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            if (r6 == 0) goto L_0x0066
            int r6 = r0.length()     // Catch: all -> 0x00b2, IOException -> 0x00b4
            java.lang.String r6 = r4.substring(r6)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r5 = r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r6.<init>()     // Catch: all -> 0x00b2, IOException -> 0x00b4
            java.lang.String r7 = "Device name: "
            r6.append(r7)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r6.append(r5)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            java.lang.String r6 = r6.toString()     // Catch: all -> 0x00b2, IOException -> 0x00b4
            android.util.Log.d(r1, r6)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            goto L_0x00af
        L_0x0066:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r6.<init>()     // Catch: all -> 0x00b2, IOException -> 0x00b4
            java.lang.String r7 = "Skip comment: "
            r6.append(r7)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r6.append(r4)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            java.lang.String r6 = r6.toString()     // Catch: all -> 0x00b2, IOException -> 0x00b4
            android.util.Log.d(r1, r6)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            goto L_0x00af
        L_0x007b:
            java.lang.String r6 = "([A-Fa-f0-9]{2}:){5}[A-Fa-f0-9]{2}"
            boolean r6 = r4.matches(r6)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            if (r6 != 0) goto L_0x0098
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r6.<init>()     // Catch: all -> 0x00b2, IOException -> 0x00b4
            java.lang.String r7 = "Invalid dev: "
            r6.append(r7)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r6.append(r4)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            java.lang.String r6 = r6.toString()     // Catch: all -> 0x00b2, IOException -> 0x00b4
            android.util.Log.d(r1, r6)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            goto L_0x00af
        L_0x0098:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r6.<init>()     // Catch: all -> 0x00b2, IOException -> 0x00b4
            java.lang.String r7 = "convertHostApdDenyData deviceName = "
            r6.append(r7)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r6.append(r5)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            java.lang.String r6 = r6.toString()     // Catch: all -> 0x00b2, IOException -> 0x00b4
            android.util.Log.d(r1, r6)     // Catch: all -> 0x00b2, IOException -> 0x00b4
            r2.add(r4)     // Catch: all -> 0x00b2, IOException -> 0x00b4
        L_0x00af:
            if (r4 != 0) goto L_0x0018
            goto L_0x00b8
        L_0x00b2:
            r0 = move-exception
            goto L_0x00bd
        L_0x00b4:
            r0 = move-exception
            r0.printStackTrace()     // Catch: all -> 0x00b2
        L_0x00b8:
            libcore.io.IoUtils.closeQuietly(r3)
            return r2
        L_0x00bd:
            libcore.io.IoUtils.closeQuietly(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.wifi.SoftApConfToXmlMigrationUtil.convertHostApdDenyData(java.lang.String):java.util.List");
    }
}
