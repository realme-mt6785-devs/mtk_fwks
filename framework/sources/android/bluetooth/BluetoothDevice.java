package android.bluetooth;

import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.app.PropertyInvalidatedCache;
import android.bluetooth.IBluetoothManagerCallback;
import android.content.Attributable;
import android.content.AttributionSource;
import android.content.Context;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;
/* loaded from: classes.dex */
public final class BluetoothDevice implements Parcelable, Attributable {
    @SystemApi
    public static final int ACCESS_ALLOWED = 1;
    @SystemApi
    public static final int ACCESS_REJECTED = 2;
    @SystemApi
    public static final int ACCESS_UNKNOWN = 0;
    public static final String ACTION_ACL_CONNECTED = "android.bluetooth.device.action.ACL_CONNECTED";
    public static final String ACTION_ACL_DISCONNECTED = "android.bluetooth.device.action.ACL_DISCONNECTED";
    public static final String ACTION_ACL_DISCONNECT_REQUESTED = "android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED";
    public static final String ACTION_ALIAS_CHANGED = "android.bluetooth.device.action.ALIAS_CHANGED";
    public static final String ACTION_BATTERY_LEVEL_CHANGED = "android.bluetooth.device.action.BATTERY_LEVEL_CHANGED";
    public static final String ACTION_BOND_STATE_CHANGED = "android.bluetooth.device.action.BOND_STATE_CHANGED";
    public static final String ACTION_CLASS_CHANGED = "android.bluetooth.device.action.CLASS_CHANGED";
    public static final String ACTION_CONNECTION_ACCESS_CANCEL = "android.bluetooth.device.action.CONNECTION_ACCESS_CANCEL";
    public static final String ACTION_CONNECTION_ACCESS_REPLY = "android.bluetooth.device.action.CONNECTION_ACCESS_REPLY";
    public static final String ACTION_CONNECTION_ACCESS_REQUEST = "android.bluetooth.device.action.CONNECTION_ACCESS_REQUEST";
    public static final String ACTION_FOUND = "android.bluetooth.device.action.FOUND";
    public static final String ACTION_MAS_INSTANCE = "android.bluetooth.device.action.MAS_INSTANCE";
    public static final String ACTION_NAME_CHANGED = "android.bluetooth.device.action.NAME_CHANGED";
    public static final String ACTION_NAME_FAILED = "android.bluetooth.device.action.NAME_FAILED";
    public static final String ACTION_PAIRING_CANCEL = "android.bluetooth.device.action.PAIRING_CANCEL";
    public static final String ACTION_PAIRING_REQUEST = "android.bluetooth.device.action.PAIRING_REQUEST";
    public static final String ACTION_SDP_RECORD = "android.bluetooth.device.action.SDP_RECORD";
    @SystemApi
    public static final String ACTION_SILENCE_MODE_CHANGED = "android.bluetooth.device.action.SILENCE_MODE_CHANGED";
    public static final String ACTION_UUID = "android.bluetooth.device.action.UUID";
    public static final int ADDRESS_TYPE_PUBLIC = 0;
    public static final int ADDRESS_TYPE_RANDOM = 1;
    public static final int BATTERY_LEVEL_BLUETOOTH_OFF = -100;
    public static final int BATTERY_LEVEL_UNKNOWN = -1;
    private static final String BLUETOOTH_BONDING_CACHE_PROPERTY = "cache_key.bluetooth.get_bond_state";
    public static final int BOND_BONDED = 12;
    public static final int BOND_BONDING = 11;
    public static final int BOND_NONE = 10;
    public static final int BOND_SUCCESS = 0;
    public static final int CONNECTION_ACCESS_NO = 2;
    public static final int CONNECTION_ACCESS_YES = 1;
    private static final int CONNECTION_STATE_CONNECTED = 1;
    private static final int CONNECTION_STATE_DISCONNECTED = 0;
    private static final int CONNECTION_STATE_ENCRYPTED_BREDR = 2;
    private static final int CONNECTION_STATE_ENCRYPTED_LE = 4;
    private static final boolean DBG = false;
    public static final int DEVICE_TYPE_CLASSIC = 1;
    @SystemApi
    public static final String DEVICE_TYPE_DEFAULT = "Default";
    public static final int DEVICE_TYPE_DUAL = 3;
    public static final int DEVICE_TYPE_LE = 2;
    public static final int DEVICE_TYPE_UNKNOWN = 0;
    @SystemApi
    public static final String DEVICE_TYPE_UNTETHERED_HEADSET = "Untethered Headset";
    @SystemApi
    public static final String DEVICE_TYPE_WATCH = "Watch";
    public static final int ERROR = Integer.MIN_VALUE;
    public static final String EXTRA_ACCESS_REQUEST_TYPE = "android.bluetooth.device.extra.ACCESS_REQUEST_TYPE";
    public static final String EXTRA_ALWAYS_ALLOWED = "android.bluetooth.device.extra.ALWAYS_ALLOWED";
    public static final String EXTRA_BATTERY_LEVEL = "android.bluetooth.device.extra.BATTERY_LEVEL";
    public static final String EXTRA_BOND_STATE = "android.bluetooth.device.extra.BOND_STATE";
    public static final String EXTRA_CLASS = "android.bluetooth.device.extra.CLASS";
    public static final String EXTRA_CLASS_NAME = "android.bluetooth.device.extra.CLASS_NAME";
    public static final String EXTRA_CONNECTION_ACCESS_RESULT = "android.bluetooth.device.extra.CONNECTION_ACCESS_RESULT";
    public static final String EXTRA_DEVICE = "android.bluetooth.device.extra.DEVICE";
    public static final String EXTRA_MAS_INSTANCE = "android.bluetooth.device.extra.MAS_INSTANCE";
    public static final String EXTRA_NAME = "android.bluetooth.device.extra.NAME";
    public static final String EXTRA_PACKAGE_NAME = "android.bluetooth.device.extra.PACKAGE_NAME";
    public static final String EXTRA_PAIRING_INITIATOR = "android.bluetooth.device.extra.PAIRING_INITIATOR";
    public static final int EXTRA_PAIRING_INITIATOR_BACKGROUND = 2;
    public static final int EXTRA_PAIRING_INITIATOR_FOREGROUND = 1;
    public static final String EXTRA_PAIRING_KEY = "android.bluetooth.device.extra.PAIRING_KEY";
    public static final String EXTRA_PAIRING_VARIANT = "android.bluetooth.device.extra.PAIRING_VARIANT";
    public static final String EXTRA_PREVIOUS_BOND_STATE = "android.bluetooth.device.extra.PREVIOUS_BOND_STATE";
    public static final String EXTRA_REASON = "android.bluetooth.device.extra.REASON";
    public static final String EXTRA_RSSI = "android.bluetooth.device.extra.RSSI";
    public static final String EXTRA_SDP_RECORD = "android.bluetooth.device.extra.SDP_RECORD";
    public static final String EXTRA_SDP_SEARCH_STATUS = "android.bluetooth.device.extra.SDP_SEARCH_STATUS";
    public static final String EXTRA_UUID = "android.bluetooth.device.extra.UUID";
    public static final int LE_ADUIO_STATE_OFF = 0;
    public static final int LE_ADUIO_STATE_ON = 1;
    @SystemApi
    public static final int METADATA_COMPANION_APP = 4;
    @SystemApi
    public static final int METADATA_DEVICE_TYPE = 17;
    @SystemApi
    public static final int METADATA_ENHANCED_SETTINGS_UI_URI = 16;
    @SystemApi
    public static final int METADATA_HARDWARE_VERSION = 3;
    @SystemApi
    public static final int METADATA_IS_UNTETHERED_HEADSET = 6;
    @SystemApi
    public static final int METADATA_MAIN_BATTERY = 18;
    @SystemApi
    public static final int METADATA_MAIN_CHARGING = 19;
    @SystemApi
    public static final int METADATA_MAIN_ICON = 5;
    @SystemApi
    public static final int METADATA_MAIN_LOW_BATTERY_THRESHOLD = 20;
    @SystemApi
    public static final int METADATA_MANUFACTURER_NAME = 0;
    @SystemApi
    public static final int METADATA_MAX_LENGTH = 2048;
    @SystemApi
    public static final int METADATA_MODEL_NAME = 1;
    @SystemApi
    public static final int METADATA_SOFTWARE_VERSION = 2;
    @SystemApi
    public static final int METADATA_UNTETHERED_CASE_BATTERY = 12;
    @SystemApi
    public static final int METADATA_UNTETHERED_CASE_CHARGING = 15;
    @SystemApi
    public static final int METADATA_UNTETHERED_CASE_ICON = 9;
    @SystemApi
    public static final int METADATA_UNTETHERED_CASE_LOW_BATTERY_THRESHOLD = 23;
    @SystemApi
    public static final int METADATA_UNTETHERED_LEFT_BATTERY = 10;
    @SystemApi
    public static final int METADATA_UNTETHERED_LEFT_CHARGING = 13;
    @SystemApi
    public static final int METADATA_UNTETHERED_LEFT_ICON = 7;
    @SystemApi
    public static final int METADATA_UNTETHERED_LEFT_LOW_BATTERY_THRESHOLD = 21;
    @SystemApi
    public static final int METADATA_UNTETHERED_RIGHT_BATTERY = 11;
    @SystemApi
    public static final int METADATA_UNTETHERED_RIGHT_CHARGING = 14;
    @SystemApi
    public static final int METADATA_UNTETHERED_RIGHT_ICON = 8;
    @SystemApi
    public static final int METADATA_UNTETHERED_RIGHT_LOW_BATTERY_THRESHOLD = 22;
    public static final int PAIRING_VARIANT_CONSENT = 3;
    public static final int PAIRING_VARIANT_DISPLAY_PASSKEY = 4;
    public static final int PAIRING_VARIANT_DISPLAY_PIN = 5;
    public static final int PAIRING_VARIANT_OOB_CONSENT = 6;
    public static final int PAIRING_VARIANT_PASSKEY = 1;
    public static final int PAIRING_VARIANT_PASSKEY_CONFIRMATION = 2;
    public static final int PAIRING_VARIANT_PIN = 0;
    public static final int PAIRING_VARIANT_PIN_16_DIGITS = 7;
    public static final int PHY_LE_1M = 1;
    public static final int PHY_LE_1M_MASK = 1;
    public static final int PHY_LE_2M = 2;
    public static final int PHY_LE_2M_MASK = 2;
    public static final int PHY_LE_CODED = 3;
    public static final int PHY_LE_CODED_MASK = 4;
    public static final int PHY_OPTION_NO_PREFERRED = 0;
    public static final int PHY_OPTION_S2 = 1;
    public static final int PHY_OPTION_S8 = 2;
    public static final int REQUEST_TYPE_MESSAGE_ACCESS = 3;
    public static final int REQUEST_TYPE_PHONEBOOK_ACCESS = 2;
    public static final int REQUEST_TYPE_PROFILE_CONNECTION = 1;
    public static final int REQUEST_TYPE_SIM_ACCESS = 4;
    private static final String TAG = "BluetoothDevice";
    public static final int TRANSPORT_AUTO = 0;
    public static final int TRANSPORT_BREDR = 1;
    public static final int TRANSPORT_LE = 2;
    public static final int UNBOND_REASON_AUTH_CANCELED = 3;
    public static final int UNBOND_REASON_AUTH_FAILED = 1;
    public static final int UNBOND_REASON_AUTH_REJECTED = 2;
    public static final int UNBOND_REASON_AUTH_TIMEOUT = 6;
    public static final int UNBOND_REASON_DISCOVERY_IN_PROGRESS = 5;
    public static final int UNBOND_REASON_REMOTE_AUTH_CANCELED = 8;
    public static final int UNBOND_REASON_REMOTE_DEVICE_DOWN = 4;
    public static final int UNBOND_REASON_REMOVED = 9;
    public static final int UNBOND_REASON_REPEATED_ATTEMPTS = 7;
    private static volatile IBluetooth sService;
    private final String mAddress;
    private final int mAddressType;
    private AttributionSource mAttributionSource;
    private final PropertyInvalidatedCache<BluetoothDevice, Integer> mBluetoothBondCache = new PropertyInvalidatedCache<BluetoothDevice, Integer>(8, BLUETOOTH_BONDING_CACHE_PROPERTY) { // from class: android.bluetooth.BluetoothDevice.3
        /* JADX INFO: Access modifiers changed from: protected */
        public Integer recompute(BluetoothDevice query) {
            try {
                if (BluetoothDevice.sService != null) {
                    return Integer.valueOf(BluetoothDevice.sService.getBondState(query, BluetoothDevice.this.mAttributionSource));
                }
                Log.e(BluetoothDevice.TAG, "BT not enabled. Cannot get bond state. return BOND_NONE");
                return 10;
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            }
        }
    };
    public static final ParcelUuid LE_AUDIO = ParcelUuid.fromString("0000184E-0000-1000-8000-00805F9B34FB");
    static IBluetoothManagerCallback sStateChangeCallback = new IBluetoothManagerCallback.Stub() { // from class: android.bluetooth.BluetoothDevice.1
        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBluetoothServiceUp(IBluetooth bluetoothService) throws RemoteException {
            synchronized (BluetoothDevice.class) {
                if (BluetoothDevice.sService == null) {
                    IBluetooth unused = BluetoothDevice.sService = bluetoothService;
                }
            }
        }

        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBluetoothServiceDown() throws RemoteException {
            synchronized (BluetoothDevice.class) {
                IBluetooth unused = BluetoothDevice.sService = null;
            }
        }

        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBrEdrDown() {
        }

        public void onOobData(int transport, OobData oobData) {
        }
    };
    public static final Parcelable.Creator<BluetoothDevice> CREATOR = new Parcelable.Creator<BluetoothDevice>() { // from class: android.bluetooth.BluetoothDevice.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothDevice createFromParcel(Parcel in) {
            return new BluetoothDevice(in.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothDevice[] newArray(int size) {
            return new BluetoothDevice[size];
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AccessPermission {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AddressType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface MetadataKey {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SetAliasReturnValues {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Transport {
    }

    static IBluetooth getService() {
        synchronized (BluetoothDevice.class) {
            if (sService == null) {
                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                sService = adapter.getBluetoothService(sStateChangeCallback);
            }
        }
        return sService;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothDevice(String address) {
        getService();
        if (BluetoothAdapter.checkBluetoothAddress(address)) {
            this.mAddress = address;
            this.mAddressType = 0;
            this.mAttributionSource = BluetoothManager.resolveAttributionSource(null);
            return;
        }
        throw new IllegalArgumentException(address + " is not a valid Bluetooth address");
    }

    @Override // android.content.Attributable
    public void setAttributionSource(AttributionSource attributionSource) {
        this.mAttributionSource = attributionSource;
    }

    public void prepareToEnterProcess(AttributionSource attributionSource) {
        setAttributionSource(attributionSource);
    }

    public boolean equals(Object o) {
        if (o instanceof BluetoothDevice) {
            return this.mAddress.equals(((BluetoothDevice) o).getAddress());
        }
        return false;
    }

    public int hashCode() {
        return this.mAddress.hashCode();
    }

    public String toString() {
        return this.mAddress;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.mAddress);
    }

    public String getAddress() {
        return this.mAddress;
    }

    public String getAnonymizedAddress() {
        return "XX:XX:XX" + getAddress().substring(8);
    }

    public String getName() {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "BT not enabled. Cannot get Remote Device name");
            return null;
        }
        try {
            String name = service.getRemoteName(this, this.mAttributionSource);
            if (name != null) {
                return name.replace('\t', ' ').replace('\n', ' ').replace('\r', ' ');
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return null;
        }
    }

    public int getType() {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "BT not enabled. Cannot get Remote Device type");
            return 0;
        }
        try {
            return service.getRemoteType(this, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return 0;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return 0;
        }
    }

    public String getAlias() {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "BT not enabled. Cannot get Remote Device Alias");
            return null;
        }
        try {
            String alias = service.getRemoteAliasWithAttribution(this, this.mAttributionSource);
            if (alias == null) {
                return getName();
            }
            return alias.replace('\t', ' ').replace('\n', ' ').replace('\r', ' ');
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return null;
        }
    }

    public int setAlias(String alias) {
        if (alias == null || !alias.isEmpty()) {
            IBluetooth service = sService;
            if (service == null) {
                Log.e(TAG, "BT not enabled. Cannot set Remote Device name");
                return 1;
            }
            try {
                Log.i(TAG, "setAlias(): alias: " + alias + ", called by: " + ActivityThread.currentPackageName());
                return service.setRemoteAlias(this, alias, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
                throw e.rethrowFromSystemServer();
            } catch (NullPointerException npe) {
                Log.e(TAG, "NullPointerException for getName()", npe);
                return 1;
            }
        } else {
            throw new IllegalArgumentException("alias cannot be the empty string");
        }
    }

    public int getBatteryLevel() {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "Bluetooth disabled. Cannot get remote device battery level");
            return -100;
        }
        try {
            return service.getBatteryLevel(this, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return -1;
        }
    }

    public boolean createBond() {
        Log.i(TAG, "createBond(): called by: " + ActivityThread.currentPackageName());
        return createBond(0);
    }

    public boolean createBond(int transport) {
        return createBondInternal(transport, null, null);
    }

    @SystemApi
    public boolean createBondOutOfBand(int transport, OobData remoteP192Data, OobData remoteP256Data) {
        if (remoteP192Data != null || remoteP256Data != null) {
            return createBondInternal(transport, remoteP192Data, remoteP256Data);
        }
        throw new IllegalArgumentException("One or both arguments for the OOB data types are required to not be null.  Please use createBond() instead if you do not have OOB data to pass.");
    }

    private boolean createBondInternal(int transport, OobData remoteP192Data, OobData remoteP256Data) {
        IBluetooth service = sService;
        if (service == null) {
            Log.w(TAG, "BT not enabled, createBondOutOfBand failed");
            return false;
        }
        try {
            return service.createBond(this, transport, remoteP192Data, remoteP256Data, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public boolean isBondingInitiatedLocally() {
        IBluetooth service = sService;
        if (service == null) {
            Log.w(TAG, "BT not enabled, isBondingInitiatedLocally failed");
            return false;
        }
        try {
            return service.isBondingInitiatedLocally(this, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    @SystemApi
    public boolean cancelBondProcess() {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "BT not enabled. Cannot cancel Remote Device bond");
            return false;
        }
        try {
            Log.i(TAG, "cancelBondProcess() for device " + getAddress() + " called by pid: " + Process.myPid() + " tid: " + Process.myTid());
            return service.cancelBondProcess(this, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return false;
        }
    }

    @SystemApi
    public boolean removeBond() {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "BT not enabled. Cannot remove Remote Device bond");
            return false;
        }
        try {
            Log.i(TAG, "removeBond() for device " + getAddress() + " called by pid: " + Process.myPid() + " tid: " + Process.myTid());
            StringBuilder sb = new StringBuilder();
            sb.append("removeBond(): called by: ");
            sb.append(ActivityThread.currentPackageName());
            Log.i(TAG, sb.toString());
            return service.removeBond(this, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return false;
        }
    }

    public void disableBluetoothGetBondStateCache() {
        this.mBluetoothBondCache.disableLocal();
    }

    public static void invalidateBluetoothGetBondStateCache() {
        PropertyInvalidatedCache.invalidateCache(BLUETOOTH_BONDING_CACHE_PROPERTY);
    }

    public int getBondState() {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "BT not enabled. Cannot get bond state");
            return 10;
        }
        try {
            return this.mBluetoothBondCache.query(this).intValue();
        } catch (RuntimeException e) {
            if (e.getCause() instanceof RemoteException) {
                Log.e(TAG, "", e);
                return 10;
            }
            throw e;
        }
    }

    @SystemApi
    public boolean canBondWithoutDialog() {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "BT not enabled. Cannot check if we can skip pairing dialog");
            return false;
        }
        try {
            return service.canBondWithoutDialog(this, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return false;
        }
    }

    @SystemApi
    public boolean isConnected() {
        IBluetooth service = sService;
        if (service == null) {
            return false;
        }
        try {
            return service.getConnectionStateWithAttribution(this, this.mAttributionSource) != 0;
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return false;
        }
    }

    @SystemApi
    public boolean isEncrypted() {
        IBluetooth service = sService;
        if (service == null) {
            return false;
        }
        try {
            return service.getConnectionStateWithAttribution(this, this.mAttributionSource) > 1;
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return false;
        }
    }

    public BluetoothClass getBluetoothClass() {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "BT not enabled. Cannot get Bluetooth Class");
            return null;
        }
        try {
            int classInt = service.getRemoteClass(this, this.mAttributionSource);
            if (classInt == -16777216) {
                return null;
            }
            return new BluetoothClass(classInt);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return null;
        }
    }

    public ParcelUuid[] getUuids() {
        IBluetooth service = sService;
        if (service == null || !isBluetoothEnabled()) {
            Log.e(TAG, "BT not enabled. Cannot get remote device Uuids");
            return null;
        }
        try {
            return service.getRemoteUuids(this, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return null;
        }
    }

    public boolean fetchUuidsWithSdp() {
        IBluetooth service = sService;
        if (service == null || !isBluetoothEnabled()) {
            Log.e(TAG, "BT not enabled. Cannot fetchUuidsWithSdp");
            return false;
        }
        try {
            return service.fetchRemoteUuidsWithAttribution(this, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return false;
        }
    }

    public boolean sdpSearch(ParcelUuid uuid) {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "BT not enabled. Cannot query remote device sdp records");
            return false;
        }
        try {
            return service.sdpSearch(this, uuid, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return false;
        }
    }

    public boolean isTwsPlusDevice() {
        return false;
    }

    public void setBondingInitiatedLocally(boolean localInitiated) {
    }

    public boolean setPin(byte[] pin) {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "BT not enabled. Cannot set Remote Device pin");
            return false;
        }
        try {
            return service.setPin(this, true, pin.length, pin, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return false;
        }
    }

    public boolean setPin(String pin) {
        byte[] pinBytes = convertPinToBytes(pin);
        if (pinBytes == null) {
            return false;
        }
        Log.i(TAG, "setPin(): pin:" + pin + ", called by: " + ActivityThread.currentPackageName());
        return setPin(pinBytes);
    }

    public boolean setPairingConfirmation(boolean confirm) {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "BT not enabled. Cannot set pairing confirmation");
            return false;
        }
        try {
            Log.i(TAG, "setPairingConfirmation(): confirm: " + confirm + ", called by: " + ActivityThread.currentPackageName());
            return service.setPairingConfirmation(this, confirm, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return false;
        }
    }

    public boolean cancelPairing() {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "BT not enabled. Cannot cancel pairing");
            return false;
        }
        try {
            return service.cancelBondProcess(this, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return false;
        }
    }

    boolean isBluetoothEnabled() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null || !adapter.isEnabled()) {
            return false;
        }
        return true;
    }

    public int getPhonebookAccessPermission() {
        IBluetooth service = sService;
        if (service == null) {
            return 0;
        }
        try {
            return service.getPhonebookAccessPermission(this, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return 0;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return 0;
        }
    }

    @SystemApi
    public boolean setSilenceMode(boolean silence) {
        IBluetooth service = sService;
        if (service != null) {
            try {
                return service.setSilenceMode(this, silence, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, "setSilenceMode fail", e);
                return false;
            }
        } else {
            throw new IllegalStateException("Bluetooth is not turned ON");
        }
    }

    @SystemApi
    public boolean isInSilenceMode() {
        IBluetooth service = sService;
        if (service != null) {
            try {
                return service.getSilenceMode(this, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, "isInSilenceMode fail", e);
                return false;
            }
        } else {
            throw new IllegalStateException("Bluetooth is not turned ON");
        }
    }

    @SystemApi
    public boolean setPhonebookAccessPermission(int value) {
        IBluetooth service = sService;
        if (service == null) {
            return false;
        }
        try {
            Log.i(TAG, "setPhonebookAccessPermission(): value: " + value + ", called by: " + ActivityThread.currentPackageName());
            return service.setPhonebookAccessPermission(this, value, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return false;
        }
    }

    public int getMessageAccessPermission() {
        IBluetooth service = sService;
        if (service == null) {
            return 0;
        }
        try {
            return service.getMessageAccessPermission(this, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return 0;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return 0;
        }
    }

    @SystemApi
    public boolean setMessageAccessPermission(int value) {
        if (value == 1 || value == 2 || value == 0) {
            IBluetooth service = sService;
            if (service == null) {
                return false;
            }
            try {
                Log.i(TAG, "setMessageAccessPermission(): value: " + value + ", called by: " + ActivityThread.currentPackageName());
                return service.setMessageAccessPermission(this, value, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
                return false;
            } catch (NullPointerException npe) {
                Log.e(TAG, "NullPointerException for getName()", npe);
                return false;
            }
        } else {
            throw new IllegalArgumentException(value + "is not a valid AccessPermission value");
        }
    }

    @SystemApi
    public int getSimAccessPermission() {
        IBluetooth service = sService;
        if (service == null) {
            return 0;
        }
        try {
            return service.getSimAccessPermission(this, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return 0;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return 0;
        }
    }

    @SystemApi
    public boolean setSimAccessPermission(int value) {
        IBluetooth service = sService;
        if (service == null) {
            return false;
        }
        try {
            Log.i(TAG, "setSimAccessPermission(): value: " + value + ", called by: " + ActivityThread.currentPackageName());
            return service.setSimAccessPermission(this, value, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        } catch (NullPointerException npe) {
            Log.e(TAG, "NullPointerException for getName()", npe);
            return false;
        }
    }

    public BluetoothSocket createRfcommSocket(int channel) throws IOException {
        if (isBluetoothEnabled()) {
            return new BluetoothSocket(1, -1, true, true, this, channel, null);
        }
        Log.e(TAG, "Bluetooth is not enabled");
        throw new IOException();
    }

    public BluetoothSocket createL2capSocket(int channel) throws IOException {
        return new BluetoothSocket(3, -1, true, true, this, channel, null);
    }

    public BluetoothSocket createInsecureL2capSocket(int channel) throws IOException {
        return new BluetoothSocket(3, -1, false, false, this, channel, null);
    }

    public BluetoothSocket createRfcommSocketToServiceRecord(UUID uuid) throws IOException {
        if (isBluetoothEnabled()) {
            return new BluetoothSocket(1, -1, true, true, this, -1, new ParcelUuid(uuid));
        }
        Log.e(TAG, "Bluetooth is not enabled");
        throw new IOException();
    }

    public BluetoothSocket createInsecureRfcommSocketToServiceRecord(UUID uuid) throws IOException {
        if (isBluetoothEnabled()) {
            return new BluetoothSocket(1, -1, false, false, this, -1, new ParcelUuid(uuid));
        }
        Log.e(TAG, "Bluetooth is not enabled");
        throw new IOException();
    }

    public BluetoothSocket createInsecureRfcommSocket(int port) throws IOException {
        if (isBluetoothEnabled()) {
            return new BluetoothSocket(1, -1, false, false, this, port, null);
        }
        Log.e(TAG, "Bluetooth is not enabled");
        throw new IOException();
    }

    public BluetoothSocket createScoSocket() throws IOException {
        if (isBluetoothEnabled()) {
            return new BluetoothSocket(2, -1, true, true, this, -1, null);
        }
        Log.e(TAG, "Bluetooth is not enabled");
        throw new IOException();
    }

    public static byte[] convertPinToBytes(String pin) {
        if (pin == null) {
            return null;
        }
        try {
            byte[] pinBytes = pin.getBytes("UTF-8");
            if (pinBytes.length <= 0 || pinBytes.length > 16) {
                return null;
            }
            return pinBytes;
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "UTF-8 not supported?!?");
            return null;
        }
    }

    public BluetoothGatt connectGatt(Context context, boolean autoConnect, BluetoothGattCallback callback) {
        return connectGatt(context, autoConnect, callback, 0);
    }

    public BluetoothGatt connectGatt(Context context, boolean autoConnect, BluetoothGattCallback callback, int transport) {
        return connectGatt(context, autoConnect, callback, transport, 1);
    }

    public BluetoothGatt connectGatt(Context context, boolean autoConnect, BluetoothGattCallback callback, int transport, int phy) {
        return connectGatt(context, autoConnect, callback, transport, phy, null);
    }

    public BluetoothGatt connectGatt(Context context, boolean autoConnect, BluetoothGattCallback callback, int transport, int phy, Handler handler) {
        return connectGatt(context, autoConnect, callback, transport, false, phy, handler);
    }

    public BluetoothGatt connectGatt(Context context, boolean autoConnect, BluetoothGattCallback callback, int transport, boolean opportunistic, int phy, Handler handler) {
        RemoteException e;
        if (callback != null) {
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            IBluetoothManager managerService = adapter.getBluetoothManager();
            try {
                IBluetoothGatt iGatt = managerService.getBluetoothGatt();
                if (iGatt == null) {
                    return null;
                }
                try {
                    BluetoothGatt gatt = new BluetoothGatt(iGatt, this, transport, opportunistic, phy, this.mAttributionSource);
                    try {
                        gatt.connect(Boolean.valueOf(autoConnect), callback, handler);
                        return gatt;
                    } catch (RemoteException e2) {
                        e = e2;
                        Log.e(TAG, "", e);
                        return null;
                    }
                } catch (RemoteException e3) {
                    e = e3;
                    Log.e(TAG, "", e);
                    return null;
                }
            } catch (RemoteException e4) {
                e = e4;
            }
        } else {
            throw new NullPointerException("callback is null");
        }
    }

    public BluetoothSocket createL2capChannel(int psm) throws IOException {
        if (isBluetoothEnabled()) {
            return new BluetoothSocket(4, -1, true, true, this, psm, null);
        }
        Log.e(TAG, "createL2capChannel: Bluetooth is not enabled");
        throw new IOException();
    }

    public BluetoothSocket createInsecureL2capChannel(int psm) throws IOException {
        if (isBluetoothEnabled()) {
            return new BluetoothSocket(4, -1, false, false, this, psm, null);
        }
        Log.e(TAG, "createInsecureL2capChannel: Bluetooth is not enabled");
        throw new IOException();
    }

    @SystemApi
    public boolean setMetadata(int key, byte[] value) {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "Bluetooth is not enabled. Cannot set metadata");
            return false;
        } else if (value.length <= 2048) {
            try {
                return service.setMetadata(this, key, value, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, "setMetadata fail", e);
                return false;
            }
        } else {
            throw new IllegalArgumentException("value length is " + value.length + ", should not over 2048");
        }
    }

    @SystemApi
    public byte[] getMetadata(int key) {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "Bluetooth is not enabled. Cannot get metadata");
            return null;
        }
        try {
            return service.getMetadata(this, key, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "getMetadata fail", e);
            return null;
        }
    }

    public static int getMaxMetadataKey() {
        return 23;
    }

    public boolean setLeAudioStatus(int value) {
        IBluetooth service = sService;
        if (service == null) {
            Log.e(TAG, "BT not enabled. Cannot set le audio status");
            return false;
        }
        try {
            return service.setLeAudioStatus(this, value, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "setLeAudioStatus fail", e);
            return false;
        }
    }

    public int getLeAudioStatus() {
        IBluetooth service = sService;
        if (service == null) {
            return 0;
        }
        try {
            return service.getLeAudioStatus(this, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "getLeAudioStatus fail", e);
            return 0;
        }
    }

    public boolean isDualModeDevice() {
        IBluetooth service = sService;
        if (service == null) {
            return false;
        }
        try {
            return service.isDualModeDevice(this.mAddress, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "isDualModeDevice fail", e);
            return false;
        }
    }

    public String findBrAddress() {
        IBluetooth service = sService;
        if (service == null) {
            return null;
        }
        try {
            return service.findBrAddress(this.mAddress, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "findBrAddress fail", e);
            return null;
        }
    }

    public String findLeAddress() {
        IBluetooth service = sService;
        if (service == null) {
            return null;
        }
        try {
            return service.findLeAddress(this.mAddress, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "findLeAddress fail", e);
            return null;
        }
    }

    public boolean isLeDevice() {
        IBluetooth service = sService;
        if (service == null) {
            return false;
        }
        try {
            String callerPackagename = ActivityThread.currentPackageName();
            Log.d(TAG, "callerPackagename = : " + callerPackagename);
            if (callerPackagename == null) {
                return isLeDeviceInternal();
            }
            return service.isLeDevice(this.mAddress, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "isLeDevice fail", e);
            return false;
        }
    }

    public boolean isConnectableDevice() {
        IBluetooth service = sService;
        if (service == null) {
            return false;
        }
        try {
            return service.isConnectableDevice(this.mAddress, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, "isConnectableDevice fail", e);
            return false;
        }
    }

    boolean isLeDeviceInternal() {
        boolean isLeAudioDevice = false;
        ParcelUuid[] uuids = getUuids();
        if (getType() == 2 && ArrayUtils.contains(uuids, LE_AUDIO)) {
            isLeAudioDevice = true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("isaudioledevice ");
        sb.append(isLeAudioDevice ? "true" : "false");
        Log.d(TAG, sb.toString());
        return isLeAudioDevice;
    }
}
