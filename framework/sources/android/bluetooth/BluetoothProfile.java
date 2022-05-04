package android.bluetooth;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
/* loaded from: classes.dex */
public interface BluetoothProfile {
    public static final int A2DP = 2;
    @SystemApi
    public static final int A2DP_SINK = 11;
    public static final int AVRCP = 13;
    @SystemApi
    public static final int AVRCP_CONTROLLER = 12;
    @SystemApi
    public static final int CONNECTION_POLICY_ALLOWED = 100;
    @SystemApi
    public static final int CONNECTION_POLICY_FORBIDDEN = 0;
    @SystemApi
    public static final int CONNECTION_POLICY_UNKNOWN = -1;
    public static final String EXTRA_PREVIOUS_STATE = "android.bluetooth.profile.extra.PREVIOUS_STATE";
    public static final String EXTRA_STATE = "android.bluetooth.profile.extra.STATE";
    public static final int GATT = 7;
    public static final int GATT_SERVER = 8;
    public static final int HEADSET = 1;
    @SystemApi
    public static final int HEADSET_CLIENT = 16;
    @Deprecated
    public static final int HEALTH = 3;
    public static final int HEARING_AID = 21;
    public static final int HID_DEVICE = 19;
    public static final int HID_HOST = 4;
    public static final int LE_AUDIO = 22;
    public static final int MAP = 9;
    @SystemApi
    public static final int MAP_CLIENT = 18;
    public static final int MAX_PROFILE_ID = 22;
    public static final int OPP = 20;
    @SystemApi
    public static final int PAN = 5;
    public static final int PBAP = 6;
    @SystemApi
    public static final int PBAP_CLIENT = 17;
    public static final int PRIORITY_AUTO_CONNECT = 1000;
    @SystemApi
    @Deprecated
    public static final int PRIORITY_OFF = 0;
    @SystemApi
    @Deprecated
    public static final int PRIORITY_ON = 100;
    public static final int PRIORITY_UNDEFINED = -1;
    public static final int SAP = 10;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 3;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface BtProfileState {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ConnectionPolicy {
    }

    /* loaded from: classes.dex */
    public interface ServiceListener {
        void onServiceConnected(int i, BluetoothProfile bluetoothProfile);

        void onServiceDisconnected(int i);
    }

    List<BluetoothDevice> getConnectedDevices();

    int getConnectionState(BluetoothDevice bluetoothDevice);

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr);

    static String getConnectionStateName(int connectionState) {
        switch (connectionState) {
            case 0:
                return "STATE_DISCONNECTED";
            case 1:
                return "STATE_CONNECTING";
            case 2:
                return "STATE_CONNECTED";
            case 3:
                return "STATE_DISCONNECTING";
            default:
                return "STATE_UNKNOWN";
        }
    }

    static String getProfileName(int profile) {
        switch (profile) {
            case 1:
                return "HEADSET";
            case 2:
                return "A2DP";
            case 3:
            case 14:
            case 15:
            default:
                return "UNKNOWN_PROFILE";
            case 4:
                return "HID_HOST";
            case 5:
                return "PAN";
            case 6:
                return "PBAP";
            case 7:
                return "GATT";
            case 8:
                return "GATT_SERVER";
            case 9:
                return "MAP";
            case 10:
                return "SAP";
            case 11:
                return "A2DP_SINK";
            case 12:
                return "AVRCP_CONTROLLER";
            case 13:
                return "AVRCP";
            case 16:
                return "HEADSET_CLIENT";
            case 17:
                return "PBAP_CLIENT";
            case 18:
                return "MAP_CLIENT";
            case 19:
                return "HID_DEVICE";
            case 20:
                return "OPP";
            case 21:
                return "HEARING_AID";
        }
    }
}
