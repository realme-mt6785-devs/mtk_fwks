package android.bluetooth;

import android.bluetooth.IBluetoothCallback;
import android.bluetooth.IBluetoothConnectionCallback;
import android.bluetooth.IBluetoothMetadataListener;
import android.bluetooth.IBluetoothOobDataCallback;
import android.bluetooth.IBluetoothSocketManager;
import android.content.AttributionSource;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.ResultReceiver;
import java.util.List;
/* loaded from: classes.dex */
public interface IBluetooth extends IInterface {
    boolean canBondWithoutDialog(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean cancelBondProcess(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean cancelDiscovery(AttributionSource attributionSource) throws RemoteException;

    boolean connectAllEnabledProfiles(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean createBond(BluetoothDevice bluetoothDevice, int i, OobData oobData, OobData oobData2, AttributionSource attributionSource) throws RemoteException;

    boolean disable(AttributionSource attributionSource) throws RemoteException;

    boolean disconnectAllEnabledProfiles(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean enable(boolean z, AttributionSource attributionSource) throws RemoteException;

    boolean factoryReset(AttributionSource attributionSource) throws RemoteException;

    boolean fetchRemoteUuids(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean fetchRemoteUuidsWithAttribution(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    String findBrAddress(String str, AttributionSource attributionSource) throws RemoteException;

    String findLeAddress(String str, AttributionSource attributionSource) throws RemoteException;

    void generateLocalOobData(int i, IBluetoothOobDataCallback iBluetoothOobDataCallback, AttributionSource attributionSource) throws RemoteException;

    int getAdapterConnectionState() throws RemoteException;

    String getAddress() throws RemoteException;

    String getAddressWithAttribution(AttributionSource attributionSource) throws RemoteException;

    int getBatteryLevel(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    BluetoothClass getBluetoothClass(AttributionSource attributionSource) throws RemoteException;

    int getBondState(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    BluetoothDevice[] getBondedDevices(AttributionSource attributionSource) throws RemoteException;

    int getConnectionState(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getConnectionStateWithAttribution(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    int getDiscoverableTimeout(AttributionSource attributionSource) throws RemoteException;

    long getDiscoveryEndMillis(AttributionSource attributionSource) throws RemoteException;

    int getIoCapability(AttributionSource attributionSource) throws RemoteException;

    int getLeAudioStatus(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    int getLeIoCapability(AttributionSource attributionSource) throws RemoteException;

    int getLeMaximumAdvertisingDataLength() throws RemoteException;

    int getMaxConnectedAudioDevices(AttributionSource attributionSource) throws RemoteException;

    int getMessageAccessPermission(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    byte[] getMetadata(BluetoothDevice bluetoothDevice, int i, AttributionSource attributionSource) throws RemoteException;

    List<BluetoothDevice> getMostRecentlyConnectedDevices(AttributionSource attributionSource) throws RemoteException;

    String getName(AttributionSource attributionSource) throws RemoteException;

    int getNameLengthForAdvertise(AttributionSource attributionSource) throws RemoteException;

    int getPhonebookAccessPermission(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    int getProfileConnectionState(int i) throws RemoteException;

    String getRemoteAlias(BluetoothDevice bluetoothDevice) throws RemoteException;

    String getRemoteAliasWithAttribution(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    int getRemoteClass(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    String getRemoteName(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    int getRemoteType(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    ParcelUuid[] getRemoteUuids(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    int getScanMode(AttributionSource attributionSource) throws RemoteException;

    boolean getSilenceMode(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    int getSimAccessPermission(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    IBluetoothSocketManager getSocketManager() throws RemoteException;

    int getState() throws RemoteException;

    long getSupportedProfiles() throws RemoteException;

    ParcelUuid[] getUuids(AttributionSource attributionSource) throws RemoteException;

    boolean isActivityAndEnergyReportingSupported() throws RemoteException;

    boolean isBondingInitiatedLocally(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean isConnectableDevice(String str, AttributionSource attributionSource) throws RemoteException;

    boolean isDiscovering(AttributionSource attributionSource) throws RemoteException;

    boolean isDualModeDevice(String str, AttributionSource attributionSource) throws RemoteException;

    boolean isLe2MPhySupported() throws RemoteException;

    boolean isLeCodedPhySupported() throws RemoteException;

    boolean isLeDevice(String str, AttributionSource attributionSource) throws RemoteException;

    boolean isLeExtendedAdvertisingSupported() throws RemoteException;

    boolean isLePeriodicAdvertisingSupported() throws RemoteException;

    boolean isMultiAdvertisementSupported() throws RemoteException;

    boolean isOffloadedFilteringSupported() throws RemoteException;

    boolean isOffloadedScanBatchingSupported() throws RemoteException;

    void onBrEdrDown(AttributionSource attributionSource) throws RemoteException;

    void onLeServiceUp(AttributionSource attributionSource) throws RemoteException;

    boolean registerBluetoothConnectionCallback(IBluetoothConnectionCallback iBluetoothConnectionCallback, AttributionSource attributionSource) throws RemoteException;

    void registerCallback(IBluetoothCallback iBluetoothCallback, AttributionSource attributionSource) throws RemoteException;

    boolean registerMetadataListener(IBluetoothMetadataListener iBluetoothMetadataListener, BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    boolean removeActiveDevice(int i, AttributionSource attributionSource) throws RemoteException;

    boolean removeBond(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    BluetoothActivityEnergyInfo reportActivityInfo(AttributionSource attributionSource) throws RemoteException;

    void requestActivityInfo(ResultReceiver resultReceiver, AttributionSource attributionSource) throws RemoteException;

    boolean sdpSearch(BluetoothDevice bluetoothDevice, ParcelUuid parcelUuid, AttributionSource attributionSource) throws RemoteException;

    boolean setActiveDevice(BluetoothDevice bluetoothDevice, int i, AttributionSource attributionSource) throws RemoteException;

    boolean setBluetoothClass(BluetoothClass bluetoothClass, AttributionSource attributionSource) throws RemoteException;

    boolean setDiscoverableTimeout(int i, AttributionSource attributionSource) throws RemoteException;

    boolean setIoCapability(int i, AttributionSource attributionSource) throws RemoteException;

    boolean setLeAudioStatus(BluetoothDevice bluetoothDevice, int i, AttributionSource attributionSource) throws RemoteException;

    boolean setLeIoCapability(int i, AttributionSource attributionSource) throws RemoteException;

    boolean setMessageAccessPermission(BluetoothDevice bluetoothDevice, int i, AttributionSource attributionSource) throws RemoteException;

    boolean setMetadata(BluetoothDevice bluetoothDevice, int i, byte[] bArr, AttributionSource attributionSource) throws RemoteException;

    boolean setName(String str, AttributionSource attributionSource) throws RemoteException;

    boolean setPairingConfirmation(BluetoothDevice bluetoothDevice, boolean z, AttributionSource attributionSource) throws RemoteException;

    boolean setPasskey(BluetoothDevice bluetoothDevice, boolean z, int i, byte[] bArr, AttributionSource attributionSource) throws RemoteException;

    boolean setPhonebookAccessPermission(BluetoothDevice bluetoothDevice, int i, AttributionSource attributionSource) throws RemoteException;

    boolean setPin(BluetoothDevice bluetoothDevice, boolean z, int i, byte[] bArr, AttributionSource attributionSource) throws RemoteException;

    int setRemoteAlias(BluetoothDevice bluetoothDevice, String str, AttributionSource attributionSource) throws RemoteException;

    boolean setScanMode(int i, int i2, AttributionSource attributionSource) throws RemoteException;

    boolean setSilenceMode(BluetoothDevice bluetoothDevice, boolean z, AttributionSource attributionSource) throws RemoteException;

    boolean setSimAccessPermission(BluetoothDevice bluetoothDevice, int i, AttributionSource attributionSource) throws RemoteException;

    boolean startDiscovery(AttributionSource attributionSource) throws RemoteException;

    boolean unregisterBluetoothConnectionCallback(IBluetoothConnectionCallback iBluetoothConnectionCallback, AttributionSource attributionSource) throws RemoteException;

    void unregisterCallback(IBluetoothCallback iBluetoothCallback, AttributionSource attributionSource) throws RemoteException;

    boolean unregisterMetadataListener(BluetoothDevice bluetoothDevice, AttributionSource attributionSource) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetooth {
        @Override // android.bluetooth.IBluetooth
        public int getState() throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean enable(boolean quietMode, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean disable(AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public String getAddress() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public String getAddressWithAttribution(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public ParcelUuid[] getUuids(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setName(String name, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public String getName(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public int getNameLengthForAdvertise(AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public BluetoothClass getBluetoothClass(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setBluetoothClass(BluetoothClass bluetoothClass, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public int getIoCapability(AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setIoCapability(int capability, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public int getLeIoCapability(AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setLeIoCapability(int capability, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public int getScanMode(AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setScanMode(int mode, int duration, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public int getDiscoverableTimeout(AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setDiscoverableTimeout(int timeout, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean startDiscovery(AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean cancelDiscovery(AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isDiscovering(AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public long getDiscoveryEndMillis(AttributionSource attributionSource) throws RemoteException {
            return 0L;
        }

        @Override // android.bluetooth.IBluetooth
        public int getAdapterConnectionState() throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public int getProfileConnectionState(int profile) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public BluetoothDevice[] getBondedDevices(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean createBond(BluetoothDevice device, int transport, OobData p192Data, OobData p256Data, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean cancelBondProcess(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean removeBond(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public int getBondState(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isBondingInitiatedLocally(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public long getSupportedProfiles() throws RemoteException {
            return 0L;
        }

        @Override // android.bluetooth.IBluetooth
        public int getConnectionState(BluetoothDevice device) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public int getConnectionStateWithAttribution(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public String getRemoteName(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public int getRemoteType(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public String getRemoteAlias(BluetoothDevice device) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public String getRemoteAliasWithAttribution(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public int setRemoteAlias(BluetoothDevice device, String name, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public int getRemoteClass(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public ParcelUuid[] getRemoteUuids(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean fetchRemoteUuids(BluetoothDevice device) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean fetchRemoteUuidsWithAttribution(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean sdpSearch(BluetoothDevice device, ParcelUuid uuid, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public int getBatteryLevel(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public int getMaxConnectedAudioDevices(AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setPin(BluetoothDevice device, boolean accept, int len, byte[] pinCode, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setPasskey(BluetoothDevice device, boolean accept, int len, byte[] passkey, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setPairingConfirmation(BluetoothDevice device, boolean accept, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public int getPhonebookAccessPermission(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setSilenceMode(BluetoothDevice device, boolean silence, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean getSilenceMode(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setPhonebookAccessPermission(BluetoothDevice device, int value, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public int getMessageAccessPermission(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setMessageAccessPermission(BluetoothDevice device, int value, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public int getSimAccessPermission(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setSimAccessPermission(BluetoothDevice device, int value, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public void registerCallback(IBluetoothCallback callback, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetooth
        public void unregisterCallback(IBluetoothCallback callback, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetooth
        public IBluetoothSocketManager getSocketManager() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean factoryReset(AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isMultiAdvertisementSupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isOffloadedFilteringSupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isOffloadedScanBatchingSupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isActivityAndEnergyReportingSupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isLe2MPhySupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isLeCodedPhySupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isLeExtendedAdvertisingSupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isLePeriodicAdvertisingSupported() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public int getLeMaximumAdvertisingDataLength() throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public BluetoothActivityEnergyInfo reportActivityInfo(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean registerMetadataListener(IBluetoothMetadataListener listener, BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean unregisterMetadataListener(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setMetadata(BluetoothDevice device, int key, byte[] value, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public byte[] getMetadata(BluetoothDevice device, int key, AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public void requestActivityInfo(ResultReceiver result, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetooth
        public void onLeServiceUp(AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetooth
        public void onBrEdrDown(AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetooth
        public boolean connectAllEnabledProfiles(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean disconnectAllEnabledProfiles(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setActiveDevice(BluetoothDevice device, int profiles, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public List<BluetoothDevice> getMostRecentlyConnectedDevices(AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean removeActiveDevice(int profiles, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean registerBluetoothConnectionCallback(IBluetoothConnectionCallback callback, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean unregisterBluetoothConnectionCallback(IBluetoothConnectionCallback callback, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean canBondWithoutDialog(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public void generateLocalOobData(int transport, IBluetoothOobDataCallback callback, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetooth
        public boolean setLeAudioStatus(BluetoothDevice device, int value, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public int getLeAudioStatus(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isDualModeDevice(String address, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public String findBrAddress(String address, AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public String findLeAddress(String address, AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isLeDevice(String address, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetooth
        public boolean isConnectableDevice(String address, AttributionSource attributionSource) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetooth {
        public static final String DESCRIPTOR = "android.bluetooth.IBluetooth";
        static final int TRANSACTION_canBondWithoutDialog = 86;
        static final int TRANSACTION_cancelBondProcess = 28;
        static final int TRANSACTION_cancelDiscovery = 21;
        static final int TRANSACTION_connectAllEnabledProfiles = 79;
        static final int TRANSACTION_createBond = 27;
        static final int TRANSACTION_disable = 3;
        static final int TRANSACTION_disconnectAllEnabledProfiles = 80;
        static final int TRANSACTION_enable = 2;
        static final int TRANSACTION_factoryReset = 61;
        static final int TRANSACTION_fetchRemoteUuids = 42;
        static final int TRANSACTION_fetchRemoteUuidsWithAttribution = 43;
        static final int TRANSACTION_findBrAddress = 91;
        static final int TRANSACTION_findLeAddress = 92;
        static final int TRANSACTION_generateLocalOobData = 87;
        static final int TRANSACTION_getAdapterConnectionState = 24;
        static final int TRANSACTION_getAddress = 4;
        static final int TRANSACTION_getAddressWithAttribution = 5;
        static final int TRANSACTION_getBatteryLevel = 45;
        static final int TRANSACTION_getBluetoothClass = 10;
        static final int TRANSACTION_getBondState = 30;
        static final int TRANSACTION_getBondedDevices = 26;
        static final int TRANSACTION_getConnectionState = 33;
        static final int TRANSACTION_getConnectionStateWithAttribution = 34;
        static final int TRANSACTION_getDiscoverableTimeout = 18;
        static final int TRANSACTION_getDiscoveryEndMillis = 23;
        static final int TRANSACTION_getIoCapability = 12;
        static final int TRANSACTION_getLeAudioStatus = 89;
        static final int TRANSACTION_getLeIoCapability = 14;
        static final int TRANSACTION_getLeMaximumAdvertisingDataLength = 70;
        static final int TRANSACTION_getMaxConnectedAudioDevices = 46;
        static final int TRANSACTION_getMessageAccessPermission = 54;
        static final int TRANSACTION_getMetadata = 75;
        static final int TRANSACTION_getMostRecentlyConnectedDevices = 82;
        static final int TRANSACTION_getName = 8;
        static final int TRANSACTION_getNameLengthForAdvertise = 9;
        static final int TRANSACTION_getPhonebookAccessPermission = 50;
        static final int TRANSACTION_getProfileConnectionState = 25;
        static final int TRANSACTION_getRemoteAlias = 37;
        static final int TRANSACTION_getRemoteAliasWithAttribution = 38;
        static final int TRANSACTION_getRemoteClass = 40;
        static final int TRANSACTION_getRemoteName = 35;
        static final int TRANSACTION_getRemoteType = 36;
        static final int TRANSACTION_getRemoteUuids = 41;
        static final int TRANSACTION_getScanMode = 16;
        static final int TRANSACTION_getSilenceMode = 52;
        static final int TRANSACTION_getSimAccessPermission = 56;
        static final int TRANSACTION_getSocketManager = 60;
        static final int TRANSACTION_getState = 1;
        static final int TRANSACTION_getSupportedProfiles = 32;
        static final int TRANSACTION_getUuids = 6;
        static final int TRANSACTION_isActivityAndEnergyReportingSupported = 65;
        static final int TRANSACTION_isBondingInitiatedLocally = 31;
        static final int TRANSACTION_isConnectableDevice = 94;
        static final int TRANSACTION_isDiscovering = 22;
        static final int TRANSACTION_isDualModeDevice = 90;
        static final int TRANSACTION_isLe2MPhySupported = 66;
        static final int TRANSACTION_isLeCodedPhySupported = 67;
        static final int TRANSACTION_isLeDevice = 93;
        static final int TRANSACTION_isLeExtendedAdvertisingSupported = 68;
        static final int TRANSACTION_isLePeriodicAdvertisingSupported = 69;
        static final int TRANSACTION_isMultiAdvertisementSupported = 62;
        static final int TRANSACTION_isOffloadedFilteringSupported = 63;
        static final int TRANSACTION_isOffloadedScanBatchingSupported = 64;
        static final int TRANSACTION_onBrEdrDown = 78;
        static final int TRANSACTION_onLeServiceUp = 77;
        static final int TRANSACTION_registerBluetoothConnectionCallback = 84;
        static final int TRANSACTION_registerCallback = 58;
        static final int TRANSACTION_registerMetadataListener = 72;
        static final int TRANSACTION_removeActiveDevice = 83;
        static final int TRANSACTION_removeBond = 29;
        static final int TRANSACTION_reportActivityInfo = 71;
        static final int TRANSACTION_requestActivityInfo = 76;
        static final int TRANSACTION_sdpSearch = 44;
        static final int TRANSACTION_setActiveDevice = 81;
        static final int TRANSACTION_setBluetoothClass = 11;
        static final int TRANSACTION_setDiscoverableTimeout = 19;
        static final int TRANSACTION_setIoCapability = 13;
        static final int TRANSACTION_setLeAudioStatus = 88;
        static final int TRANSACTION_setLeIoCapability = 15;
        static final int TRANSACTION_setMessageAccessPermission = 55;
        static final int TRANSACTION_setMetadata = 74;
        static final int TRANSACTION_setName = 7;
        static final int TRANSACTION_setPairingConfirmation = 49;
        static final int TRANSACTION_setPasskey = 48;
        static final int TRANSACTION_setPhonebookAccessPermission = 53;
        static final int TRANSACTION_setPin = 47;
        static final int TRANSACTION_setRemoteAlias = 39;
        static final int TRANSACTION_setScanMode = 17;
        static final int TRANSACTION_setSilenceMode = 51;
        static final int TRANSACTION_setSimAccessPermission = 57;
        static final int TRANSACTION_startDiscovery = 20;
        static final int TRANSACTION_unregisterBluetoothConnectionCallback = 85;
        static final int TRANSACTION_unregisterCallback = 59;
        static final int TRANSACTION_unregisterMetadataListener = 73;

        public Stub() {
            attachInterface(this, "android.bluetooth.IBluetooth");
        }

        public static IBluetooth asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("android.bluetooth.IBluetooth");
            if (iin == null || !(iin instanceof IBluetooth)) {
                return new Proxy(obj);
            }
            return (IBluetooth) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getState";
                case 2:
                    return "enable";
                case 3:
                    return "disable";
                case 4:
                    return "getAddress";
                case 5:
                    return "getAddressWithAttribution";
                case 6:
                    return "getUuids";
                case 7:
                    return "setName";
                case 8:
                    return "getName";
                case 9:
                    return "getNameLengthForAdvertise";
                case 10:
                    return "getBluetoothClass";
                case 11:
                    return "setBluetoothClass";
                case 12:
                    return "getIoCapability";
                case 13:
                    return "setIoCapability";
                case 14:
                    return "getLeIoCapability";
                case 15:
                    return "setLeIoCapability";
                case 16:
                    return "getScanMode";
                case 17:
                    return "setScanMode";
                case 18:
                    return "getDiscoverableTimeout";
                case 19:
                    return "setDiscoverableTimeout";
                case 20:
                    return "startDiscovery";
                case 21:
                    return "cancelDiscovery";
                case 22:
                    return "isDiscovering";
                case 23:
                    return "getDiscoveryEndMillis";
                case 24:
                    return "getAdapterConnectionState";
                case 25:
                    return "getProfileConnectionState";
                case 26:
                    return "getBondedDevices";
                case 27:
                    return "createBond";
                case 28:
                    return "cancelBondProcess";
                case 29:
                    return "removeBond";
                case 30:
                    return "getBondState";
                case 31:
                    return "isBondingInitiatedLocally";
                case 32:
                    return "getSupportedProfiles";
                case 33:
                    return "getConnectionState";
                case 34:
                    return "getConnectionStateWithAttribution";
                case 35:
                    return "getRemoteName";
                case 36:
                    return "getRemoteType";
                case 37:
                    return "getRemoteAlias";
                case 38:
                    return "getRemoteAliasWithAttribution";
                case 39:
                    return "setRemoteAlias";
                case 40:
                    return "getRemoteClass";
                case 41:
                    return "getRemoteUuids";
                case 42:
                    return "fetchRemoteUuids";
                case 43:
                    return "fetchRemoteUuidsWithAttribution";
                case 44:
                    return "sdpSearch";
                case 45:
                    return "getBatteryLevel";
                case 46:
                    return "getMaxConnectedAudioDevices";
                case 47:
                    return "setPin";
                case 48:
                    return "setPasskey";
                case 49:
                    return "setPairingConfirmation";
                case 50:
                    return "getPhonebookAccessPermission";
                case 51:
                    return "setSilenceMode";
                case 52:
                    return "getSilenceMode";
                case 53:
                    return "setPhonebookAccessPermission";
                case 54:
                    return "getMessageAccessPermission";
                case 55:
                    return "setMessageAccessPermission";
                case 56:
                    return "getSimAccessPermission";
                case 57:
                    return "setSimAccessPermission";
                case 58:
                    return "registerCallback";
                case 59:
                    return "unregisterCallback";
                case 60:
                    return "getSocketManager";
                case 61:
                    return "factoryReset";
                case 62:
                    return "isMultiAdvertisementSupported";
                case 63:
                    return "isOffloadedFilteringSupported";
                case 64:
                    return "isOffloadedScanBatchingSupported";
                case 65:
                    return "isActivityAndEnergyReportingSupported";
                case 66:
                    return "isLe2MPhySupported";
                case 67:
                    return "isLeCodedPhySupported";
                case 68:
                    return "isLeExtendedAdvertisingSupported";
                case 69:
                    return "isLePeriodicAdvertisingSupported";
                case 70:
                    return "getLeMaximumAdvertisingDataLength";
                case 71:
                    return "reportActivityInfo";
                case 72:
                    return "registerMetadataListener";
                case 73:
                    return "unregisterMetadataListener";
                case 74:
                    return "setMetadata";
                case 75:
                    return "getMetadata";
                case 76:
                    return "requestActivityInfo";
                case 77:
                    return "onLeServiceUp";
                case 78:
                    return "onBrEdrDown";
                case 79:
                    return "connectAllEnabledProfiles";
                case 80:
                    return "disconnectAllEnabledProfiles";
                case 81:
                    return "setActiveDevice";
                case 82:
                    return "getMostRecentlyConnectedDevices";
                case 83:
                    return "removeActiveDevice";
                case 84:
                    return "registerBluetoothConnectionCallback";
                case 85:
                    return "unregisterBluetoothConnectionCallback";
                case 86:
                    return "canBondWithoutDialog";
                case 87:
                    return "generateLocalOobData";
                case 88:
                    return "setLeAudioStatus";
                case 89:
                    return "getLeAudioStatus";
                case 90:
                    return "isDualModeDevice";
                case 91:
                    return "findBrAddress";
                case 92:
                    return "findLeAddress";
                case 93:
                    return "isLeDevice";
                case 94:
                    return "isConnectableDevice";
                default:
                    return null;
            }
        }

        @Override // android.os.Binder
        public String getTransactionName(int transactionCode) {
            return getDefaultTransactionName(transactionCode);
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            AttributionSource _arg1;
            AttributionSource _arg0;
            AttributionSource _arg02;
            AttributionSource _arg03;
            AttributionSource _arg12;
            AttributionSource _arg04;
            AttributionSource _arg05;
            AttributionSource _arg06;
            BluetoothClass _arg07;
            AttributionSource _arg13;
            AttributionSource _arg08;
            AttributionSource _arg14;
            AttributionSource _arg09;
            AttributionSource _arg15;
            AttributionSource _arg010;
            AttributionSource _arg2;
            AttributionSource _arg011;
            AttributionSource _arg16;
            AttributionSource _arg012;
            AttributionSource _arg013;
            AttributionSource _arg014;
            AttributionSource _arg015;
            AttributionSource _arg016;
            BluetoothDevice _arg017;
            OobData _arg22;
            OobData _arg3;
            AttributionSource _arg4;
            BluetoothDevice _arg018;
            AttributionSource _arg17;
            BluetoothDevice _arg019;
            AttributionSource _arg18;
            BluetoothDevice _arg020;
            AttributionSource _arg19;
            BluetoothDevice _arg021;
            AttributionSource _arg110;
            BluetoothDevice _arg022;
            BluetoothDevice _arg023;
            AttributionSource _arg111;
            BluetoothDevice _arg024;
            AttributionSource _arg112;
            BluetoothDevice _arg025;
            AttributionSource _arg113;
            BluetoothDevice _arg026;
            BluetoothDevice _arg027;
            AttributionSource _arg114;
            BluetoothDevice _arg028;
            AttributionSource _arg23;
            BluetoothDevice _arg029;
            AttributionSource _arg115;
            BluetoothDevice _arg030;
            AttributionSource _arg116;
            BluetoothDevice _arg031;
            BluetoothDevice _arg032;
            AttributionSource _arg117;
            BluetoothDevice _arg033;
            ParcelUuid _arg118;
            AttributionSource _arg24;
            BluetoothDevice _arg034;
            AttributionSource _arg119;
            AttributionSource _arg035;
            BluetoothDevice _arg036;
            boolean _arg120;
            AttributionSource _arg42;
            BluetoothDevice _arg037;
            boolean _arg121;
            AttributionSource _arg43;
            BluetoothDevice _arg038;
            AttributionSource _arg25;
            BluetoothDevice _arg039;
            AttributionSource _arg122;
            BluetoothDevice _arg040;
            AttributionSource _arg26;
            BluetoothDevice _arg041;
            AttributionSource _arg123;
            BluetoothDevice _arg042;
            AttributionSource _arg27;
            BluetoothDevice _arg043;
            AttributionSource _arg124;
            BluetoothDevice _arg044;
            AttributionSource _arg28;
            BluetoothDevice _arg045;
            AttributionSource _arg125;
            BluetoothDevice _arg046;
            AttributionSource _arg29;
            AttributionSource _arg126;
            AttributionSource _arg127;
            AttributionSource _arg047;
            AttributionSource _arg048;
            BluetoothDevice _arg128;
            AttributionSource _arg210;
            BluetoothDevice _arg049;
            AttributionSource _arg129;
            BluetoothDevice _arg050;
            AttributionSource _arg32;
            BluetoothDevice _arg051;
            AttributionSource _arg211;
            ResultReceiver _arg052;
            AttributionSource _arg130;
            AttributionSource _arg053;
            AttributionSource _arg054;
            BluetoothDevice _arg055;
            AttributionSource _arg131;
            BluetoothDevice _arg056;
            AttributionSource _arg132;
            BluetoothDevice _arg057;
            AttributionSource _arg212;
            AttributionSource _arg058;
            AttributionSource _arg133;
            AttributionSource _arg134;
            AttributionSource _arg135;
            BluetoothDevice _arg059;
            AttributionSource _arg136;
            AttributionSource _arg213;
            BluetoothDevice _arg060;
            AttributionSource _arg214;
            BluetoothDevice _arg061;
            AttributionSource _arg137;
            AttributionSource _arg138;
            AttributionSource _arg139;
            AttributionSource _arg140;
            AttributionSource _arg141;
            AttributionSource _arg142;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString("android.bluetooth.IBluetooth");
                    return true;
                default:
                    boolean _arg143 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            int _result = getState();
                            reply.writeNoException();
                            reply.writeInt(_result);
                            return true;
                        case 2:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg143 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg1 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            boolean enable = enable(_arg143, _arg1);
                            reply.writeNoException();
                            reply.writeInt(enable ? 1 : 0);
                            return true;
                        case 3:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg0 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            boolean disable = disable(_arg0);
                            reply.writeNoException();
                            reply.writeInt(disable ? 1 : 0);
                            return true;
                        case 4:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            String _result2 = getAddress();
                            reply.writeNoException();
                            reply.writeString(_result2);
                            return true;
                        case 5:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg02 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            String _result3 = getAddressWithAttribution(_arg02);
                            reply.writeNoException();
                            reply.writeString(_result3);
                            return true;
                        case 6:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg03 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            ParcelUuid[] _result4 = getUuids(_arg03);
                            reply.writeNoException();
                            reply.writeTypedArray(_result4, 1);
                            return true;
                        case 7:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            String _arg062 = data.readString();
                            if (data.readInt() != 0) {
                                _arg12 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            boolean name = setName(_arg062, _arg12);
                            reply.writeNoException();
                            reply.writeInt(name ? 1 : 0);
                            return true;
                        case 8:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg04 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            String _result5 = getName(_arg04);
                            reply.writeNoException();
                            reply.writeString(_result5);
                            return true;
                        case 9:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg05 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            int _result6 = getNameLengthForAdvertise(_arg05);
                            reply.writeNoException();
                            reply.writeInt(_result6);
                            return true;
                        case 10:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg06 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            BluetoothClass _result7 = getBluetoothClass(_arg06);
                            reply.writeNoException();
                            if (_result7 != null) {
                                reply.writeInt(1);
                                _result7.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 11:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg07 = BluetoothClass.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg13 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            boolean bluetoothClass = setBluetoothClass(_arg07, _arg13);
                            reply.writeNoException();
                            reply.writeInt(bluetoothClass ? 1 : 0);
                            return true;
                        case 12:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg08 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            int _result8 = getIoCapability(_arg08);
                            reply.writeNoException();
                            reply.writeInt(_result8);
                            return true;
                        case 13:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            int _arg063 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg14 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            boolean ioCapability = setIoCapability(_arg063, _arg14);
                            reply.writeNoException();
                            reply.writeInt(ioCapability ? 1 : 0);
                            return true;
                        case 14:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg09 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg09 = null;
                            }
                            int _result9 = getLeIoCapability(_arg09);
                            reply.writeNoException();
                            reply.writeInt(_result9);
                            return true;
                        case 15:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            int _arg064 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg15 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            boolean leIoCapability = setLeIoCapability(_arg064, _arg15);
                            reply.writeNoException();
                            reply.writeInt(leIoCapability ? 1 : 0);
                            return true;
                        case 16:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg010 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg010 = null;
                            }
                            int _result10 = getScanMode(_arg010);
                            reply.writeNoException();
                            reply.writeInt(_result10);
                            return true;
                        case 17:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            int _arg065 = data.readInt();
                            int _arg144 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg2 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            boolean scanMode = setScanMode(_arg065, _arg144, _arg2);
                            reply.writeNoException();
                            reply.writeInt(scanMode ? 1 : 0);
                            return true;
                        case 18:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg011 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg011 = null;
                            }
                            int _result11 = getDiscoverableTimeout(_arg011);
                            reply.writeNoException();
                            reply.writeInt(_result11);
                            return true;
                        case 19:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            int _arg066 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg16 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            boolean discoverableTimeout = setDiscoverableTimeout(_arg066, _arg16);
                            reply.writeNoException();
                            reply.writeInt(discoverableTimeout ? 1 : 0);
                            return true;
                        case 20:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg012 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg012 = null;
                            }
                            boolean startDiscovery = startDiscovery(_arg012);
                            reply.writeNoException();
                            reply.writeInt(startDiscovery ? 1 : 0);
                            return true;
                        case 21:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg013 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg013 = null;
                            }
                            boolean cancelDiscovery = cancelDiscovery(_arg013);
                            reply.writeNoException();
                            reply.writeInt(cancelDiscovery ? 1 : 0);
                            return true;
                        case 22:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg014 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg014 = null;
                            }
                            boolean isDiscovering = isDiscovering(_arg014);
                            reply.writeNoException();
                            reply.writeInt(isDiscovering ? 1 : 0);
                            return true;
                        case 23:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg015 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg015 = null;
                            }
                            long _result12 = getDiscoveryEndMillis(_arg015);
                            reply.writeNoException();
                            reply.writeLong(_result12);
                            return true;
                        case 24:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            int _result13 = getAdapterConnectionState();
                            reply.writeNoException();
                            reply.writeInt(_result13);
                            return true;
                        case 25:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            int _arg067 = data.readInt();
                            int _result14 = getProfileConnectionState(_arg067);
                            reply.writeNoException();
                            reply.writeInt(_result14);
                            return true;
                        case 26:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg016 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg016 = null;
                            }
                            BluetoothDevice[] _result15 = getBondedDevices(_arg016);
                            reply.writeNoException();
                            reply.writeTypedArray(_result15, 1);
                            return true;
                        case 27:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg017 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg017 = null;
                            }
                            int _arg145 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg22 = OobData.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg3 = OobData.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg4 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            boolean createBond = createBond(_arg017, _arg145, _arg22, _arg3, _arg4);
                            reply.writeNoException();
                            reply.writeInt(createBond ? 1 : 0);
                            return true;
                        case 28:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg018 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg018 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg17 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            boolean cancelBondProcess = cancelBondProcess(_arg018, _arg17);
                            reply.writeNoException();
                            reply.writeInt(cancelBondProcess ? 1 : 0);
                            return true;
                        case 29:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg019 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg019 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg18 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            boolean removeBond = removeBond(_arg019, _arg18);
                            reply.writeNoException();
                            reply.writeInt(removeBond ? 1 : 0);
                            return true;
                        case 30:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg020 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg020 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg19 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg19 = null;
                            }
                            int _result16 = getBondState(_arg020, _arg19);
                            reply.writeNoException();
                            reply.writeInt(_result16);
                            return true;
                        case 31:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg021 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg021 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg110 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg110 = null;
                            }
                            boolean isBondingInitiatedLocally = isBondingInitiatedLocally(_arg021, _arg110);
                            reply.writeNoException();
                            reply.writeInt(isBondingInitiatedLocally ? 1 : 0);
                            return true;
                        case 32:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            long _result17 = getSupportedProfiles();
                            reply.writeNoException();
                            reply.writeLong(_result17);
                            return true;
                        case 33:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg022 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg022 = null;
                            }
                            int _result18 = getConnectionState(_arg022);
                            reply.writeNoException();
                            reply.writeInt(_result18);
                            return true;
                        case 34:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg023 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg023 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg111 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg111 = null;
                            }
                            int _result19 = getConnectionStateWithAttribution(_arg023, _arg111);
                            reply.writeNoException();
                            reply.writeInt(_result19);
                            return true;
                        case 35:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg024 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg024 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg112 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg112 = null;
                            }
                            String _result20 = getRemoteName(_arg024, _arg112);
                            reply.writeNoException();
                            reply.writeString(_result20);
                            return true;
                        case 36:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg025 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg025 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg113 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg113 = null;
                            }
                            int _result21 = getRemoteType(_arg025, _arg113);
                            reply.writeNoException();
                            reply.writeInt(_result21);
                            return true;
                        case 37:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg026 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg026 = null;
                            }
                            String _result22 = getRemoteAlias(_arg026);
                            reply.writeNoException();
                            reply.writeString(_result22);
                            return true;
                        case 38:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg027 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg027 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg114 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg114 = null;
                            }
                            String _result23 = getRemoteAliasWithAttribution(_arg027, _arg114);
                            reply.writeNoException();
                            reply.writeString(_result23);
                            return true;
                        case 39:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg028 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg028 = null;
                            }
                            String _arg146 = data.readString();
                            if (data.readInt() != 0) {
                                _arg23 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            int _result24 = setRemoteAlias(_arg028, _arg146, _arg23);
                            reply.writeNoException();
                            reply.writeInt(_result24);
                            return true;
                        case 40:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg029 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg029 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg115 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg115 = null;
                            }
                            int _result25 = getRemoteClass(_arg029, _arg115);
                            reply.writeNoException();
                            reply.writeInt(_result25);
                            return true;
                        case 41:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg030 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg030 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg116 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg116 = null;
                            }
                            ParcelUuid[] _result26 = getRemoteUuids(_arg030, _arg116);
                            reply.writeNoException();
                            reply.writeTypedArray(_result26, 1);
                            return true;
                        case 42:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg031 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg031 = null;
                            }
                            boolean fetchRemoteUuids = fetchRemoteUuids(_arg031);
                            reply.writeNoException();
                            reply.writeInt(fetchRemoteUuids ? 1 : 0);
                            return true;
                        case 43:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg032 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg032 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg117 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg117 = null;
                            }
                            boolean fetchRemoteUuidsWithAttribution = fetchRemoteUuidsWithAttribution(_arg032, _arg117);
                            reply.writeNoException();
                            reply.writeInt(fetchRemoteUuidsWithAttribution ? 1 : 0);
                            return true;
                        case 44:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg033 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg033 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg118 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg118 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg24 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg24 = null;
                            }
                            boolean sdpSearch = sdpSearch(_arg033, _arg118, _arg24);
                            reply.writeNoException();
                            reply.writeInt(sdpSearch ? 1 : 0);
                            return true;
                        case 45:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg034 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg034 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg119 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg119 = null;
                            }
                            int _result27 = getBatteryLevel(_arg034, _arg119);
                            reply.writeNoException();
                            reply.writeInt(_result27);
                            return true;
                        case 46:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg035 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg035 = null;
                            }
                            int _result28 = getMaxConnectedAudioDevices(_arg035);
                            reply.writeNoException();
                            reply.writeInt(_result28);
                            return true;
                        case 47:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg036 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg036 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg120 = true;
                            } else {
                                _arg120 = false;
                            }
                            int _arg215 = data.readInt();
                            byte[] _arg33 = data.createByteArray();
                            if (data.readInt() != 0) {
                                _arg42 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg42 = null;
                            }
                            boolean pin = setPin(_arg036, _arg120, _arg215, _arg33, _arg42);
                            reply.writeNoException();
                            reply.writeInt(pin ? 1 : 0);
                            return true;
                        case 48:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg037 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg037 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg121 = true;
                            } else {
                                _arg121 = false;
                            }
                            int _arg216 = data.readInt();
                            byte[] _arg34 = data.createByteArray();
                            if (data.readInt() != 0) {
                                _arg43 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg43 = null;
                            }
                            boolean passkey = setPasskey(_arg037, _arg121, _arg216, _arg34, _arg43);
                            reply.writeNoException();
                            reply.writeInt(passkey ? 1 : 0);
                            return true;
                        case 49:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg038 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg038 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg143 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg25 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg25 = null;
                            }
                            boolean pairingConfirmation = setPairingConfirmation(_arg038, _arg143, _arg25);
                            reply.writeNoException();
                            reply.writeInt(pairingConfirmation ? 1 : 0);
                            return true;
                        case 50:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg039 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg039 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg122 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg122 = null;
                            }
                            int _result29 = getPhonebookAccessPermission(_arg039, _arg122);
                            reply.writeNoException();
                            reply.writeInt(_result29);
                            return true;
                        case 51:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg040 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg040 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg143 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg26 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg26 = null;
                            }
                            boolean silenceMode = setSilenceMode(_arg040, _arg143, _arg26);
                            reply.writeNoException();
                            reply.writeInt(silenceMode ? 1 : 0);
                            return true;
                        case 52:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg041 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg041 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg123 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg123 = null;
                            }
                            boolean silenceMode2 = getSilenceMode(_arg041, _arg123);
                            reply.writeNoException();
                            reply.writeInt(silenceMode2 ? 1 : 0);
                            return true;
                        case 53:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg042 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg042 = null;
                            }
                            int _arg147 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg27 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg27 = null;
                            }
                            boolean phonebookAccessPermission = setPhonebookAccessPermission(_arg042, _arg147, _arg27);
                            reply.writeNoException();
                            reply.writeInt(phonebookAccessPermission ? 1 : 0);
                            return true;
                        case 54:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg043 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg043 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg124 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg124 = null;
                            }
                            int _result30 = getMessageAccessPermission(_arg043, _arg124);
                            reply.writeNoException();
                            reply.writeInt(_result30);
                            return true;
                        case 55:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg044 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg044 = null;
                            }
                            int _arg148 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg28 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg28 = null;
                            }
                            boolean messageAccessPermission = setMessageAccessPermission(_arg044, _arg148, _arg28);
                            reply.writeNoException();
                            reply.writeInt(messageAccessPermission ? 1 : 0);
                            return true;
                        case 56:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg045 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg045 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg125 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg125 = null;
                            }
                            int _result31 = getSimAccessPermission(_arg045, _arg125);
                            reply.writeNoException();
                            reply.writeInt(_result31);
                            return true;
                        case 57:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg046 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg046 = null;
                            }
                            int _arg149 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg29 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg29 = null;
                            }
                            boolean simAccessPermission = setSimAccessPermission(_arg046, _arg149, _arg29);
                            reply.writeNoException();
                            reply.writeInt(simAccessPermission ? 1 : 0);
                            return true;
                        case 58:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            IBluetoothCallback _arg068 = IBluetoothCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg126 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg126 = null;
                            }
                            registerCallback(_arg068, _arg126);
                            reply.writeNoException();
                            return true;
                        case 59:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            IBluetoothCallback _arg069 = IBluetoothCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg127 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg127 = null;
                            }
                            unregisterCallback(_arg069, _arg127);
                            reply.writeNoException();
                            return true;
                        case 60:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            IBluetoothSocketManager _result32 = getSocketManager();
                            reply.writeNoException();
                            reply.writeStrongBinder(_result32 != null ? _result32.asBinder() : null);
                            return true;
                        case 61:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg047 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg047 = null;
                            }
                            boolean factoryReset = factoryReset(_arg047);
                            reply.writeNoException();
                            reply.writeInt(factoryReset ? 1 : 0);
                            return true;
                        case 62:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            boolean isMultiAdvertisementSupported = isMultiAdvertisementSupported();
                            reply.writeNoException();
                            reply.writeInt(isMultiAdvertisementSupported ? 1 : 0);
                            return true;
                        case 63:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            boolean isOffloadedFilteringSupported = isOffloadedFilteringSupported();
                            reply.writeNoException();
                            reply.writeInt(isOffloadedFilteringSupported ? 1 : 0);
                            return true;
                        case 64:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            boolean isOffloadedScanBatchingSupported = isOffloadedScanBatchingSupported();
                            reply.writeNoException();
                            reply.writeInt(isOffloadedScanBatchingSupported ? 1 : 0);
                            return true;
                        case 65:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            boolean isActivityAndEnergyReportingSupported = isActivityAndEnergyReportingSupported();
                            reply.writeNoException();
                            reply.writeInt(isActivityAndEnergyReportingSupported ? 1 : 0);
                            return true;
                        case 66:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            boolean isLe2MPhySupported = isLe2MPhySupported();
                            reply.writeNoException();
                            reply.writeInt(isLe2MPhySupported ? 1 : 0);
                            return true;
                        case 67:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            boolean isLeCodedPhySupported = isLeCodedPhySupported();
                            reply.writeNoException();
                            reply.writeInt(isLeCodedPhySupported ? 1 : 0);
                            return true;
                        case 68:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            boolean isLeExtendedAdvertisingSupported = isLeExtendedAdvertisingSupported();
                            reply.writeNoException();
                            reply.writeInt(isLeExtendedAdvertisingSupported ? 1 : 0);
                            return true;
                        case 69:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            boolean isLePeriodicAdvertisingSupported = isLePeriodicAdvertisingSupported();
                            reply.writeNoException();
                            reply.writeInt(isLePeriodicAdvertisingSupported ? 1 : 0);
                            return true;
                        case 70:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            int _result33 = getLeMaximumAdvertisingDataLength();
                            reply.writeNoException();
                            reply.writeInt(_result33);
                            return true;
                        case 71:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg048 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg048 = null;
                            }
                            BluetoothActivityEnergyInfo _result34 = reportActivityInfo(_arg048);
                            reply.writeNoException();
                            if (_result34 != null) {
                                reply.writeInt(1);
                                _result34.writeToParcel(reply, 1);
                            } else {
                                reply.writeInt(0);
                            }
                            return true;
                        case 72:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            IBluetoothMetadataListener _arg070 = IBluetoothMetadataListener.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg128 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg128 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg210 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg210 = null;
                            }
                            boolean registerMetadataListener = registerMetadataListener(_arg070, _arg128, _arg210);
                            reply.writeNoException();
                            reply.writeInt(registerMetadataListener ? 1 : 0);
                            return true;
                        case 73:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg049 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg049 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg129 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg129 = null;
                            }
                            boolean unregisterMetadataListener = unregisterMetadataListener(_arg049, _arg129);
                            reply.writeNoException();
                            reply.writeInt(unregisterMetadataListener ? 1 : 0);
                            return true;
                        case 74:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg050 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg050 = null;
                            }
                            int _arg150 = data.readInt();
                            byte[] _arg217 = data.createByteArray();
                            if (data.readInt() != 0) {
                                _arg32 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            boolean metadata = setMetadata(_arg050, _arg150, _arg217, _arg32);
                            reply.writeNoException();
                            reply.writeInt(metadata ? 1 : 0);
                            return true;
                        case 75:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg051 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg051 = null;
                            }
                            int _arg151 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg211 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg211 = null;
                            }
                            byte[] _result35 = getMetadata(_arg051, _arg151, _arg211);
                            reply.writeNoException();
                            reply.writeByteArray(_result35);
                            return true;
                        case 76:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg052 = ResultReceiver.CREATOR.createFromParcel(data);
                            } else {
                                _arg052 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg130 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg130 = null;
                            }
                            requestActivityInfo(_arg052, _arg130);
                            return true;
                        case 77:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg053 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg053 = null;
                            }
                            onLeServiceUp(_arg053);
                            reply.writeNoException();
                            return true;
                        case 78:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg054 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg054 = null;
                            }
                            onBrEdrDown(_arg054);
                            reply.writeNoException();
                            return true;
                        case 79:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg055 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg055 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg131 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg131 = null;
                            }
                            boolean connectAllEnabledProfiles = connectAllEnabledProfiles(_arg055, _arg131);
                            reply.writeNoException();
                            reply.writeInt(connectAllEnabledProfiles ? 1 : 0);
                            return true;
                        case 80:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg056 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg056 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg132 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg132 = null;
                            }
                            boolean disconnectAllEnabledProfiles = disconnectAllEnabledProfiles(_arg056, _arg132);
                            reply.writeNoException();
                            reply.writeInt(disconnectAllEnabledProfiles ? 1 : 0);
                            return true;
                        case 81:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg057 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg057 = null;
                            }
                            int _arg152 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg212 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg212 = null;
                            }
                            boolean activeDevice = setActiveDevice(_arg057, _arg152, _arg212);
                            reply.writeNoException();
                            reply.writeInt(activeDevice ? 1 : 0);
                            return true;
                        case 82:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg058 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg058 = null;
                            }
                            List<BluetoothDevice> _result36 = getMostRecentlyConnectedDevices(_arg058);
                            reply.writeNoException();
                            reply.writeTypedList(_result36);
                            return true;
                        case 83:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            int _arg071 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg133 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg133 = null;
                            }
                            boolean removeActiveDevice = removeActiveDevice(_arg071, _arg133);
                            reply.writeNoException();
                            reply.writeInt(removeActiveDevice ? 1 : 0);
                            return true;
                        case 84:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            IBluetoothConnectionCallback _arg072 = IBluetoothConnectionCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg134 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg134 = null;
                            }
                            boolean registerBluetoothConnectionCallback = registerBluetoothConnectionCallback(_arg072, _arg134);
                            reply.writeNoException();
                            reply.writeInt(registerBluetoothConnectionCallback ? 1 : 0);
                            return true;
                        case 85:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            IBluetoothConnectionCallback _arg073 = IBluetoothConnectionCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg135 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg135 = null;
                            }
                            boolean unregisterBluetoothConnectionCallback = unregisterBluetoothConnectionCallback(_arg073, _arg135);
                            reply.writeNoException();
                            reply.writeInt(unregisterBluetoothConnectionCallback ? 1 : 0);
                            return true;
                        case 86:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg059 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg059 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg136 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg136 = null;
                            }
                            boolean canBondWithoutDialog = canBondWithoutDialog(_arg059, _arg136);
                            reply.writeNoException();
                            reply.writeInt(canBondWithoutDialog ? 1 : 0);
                            return true;
                        case 87:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            int _arg074 = data.readInt();
                            IBluetoothOobDataCallback _arg153 = IBluetoothOobDataCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg213 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg213 = null;
                            }
                            generateLocalOobData(_arg074, _arg153, _arg213);
                            reply.writeNoException();
                            return true;
                        case 88:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg060 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg060 = null;
                            }
                            int _arg154 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg214 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg214 = null;
                            }
                            boolean leAudioStatus = setLeAudioStatus(_arg060, _arg154, _arg214);
                            reply.writeNoException();
                            reply.writeInt(leAudioStatus ? 1 : 0);
                            return true;
                        case 89:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            if (data.readInt() != 0) {
                                _arg061 = BluetoothDevice.CREATOR.createFromParcel(data);
                            } else {
                                _arg061 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg137 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg137 = null;
                            }
                            int _result37 = getLeAudioStatus(_arg061, _arg137);
                            reply.writeNoException();
                            reply.writeInt(_result37);
                            return true;
                        case 90:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            String _arg075 = data.readString();
                            if (data.readInt() != 0) {
                                _arg138 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg138 = null;
                            }
                            boolean isDualModeDevice = isDualModeDevice(_arg075, _arg138);
                            reply.writeNoException();
                            reply.writeInt(isDualModeDevice ? 1 : 0);
                            return true;
                        case 91:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            String _arg076 = data.readString();
                            if (data.readInt() != 0) {
                                _arg139 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg139 = null;
                            }
                            String _result38 = findBrAddress(_arg076, _arg139);
                            reply.writeNoException();
                            reply.writeString(_result38);
                            return true;
                        case 92:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            String _arg077 = data.readString();
                            if (data.readInt() != 0) {
                                _arg140 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg140 = null;
                            }
                            String _result39 = findLeAddress(_arg077, _arg140);
                            reply.writeNoException();
                            reply.writeString(_result39);
                            return true;
                        case 93:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            String _arg078 = data.readString();
                            if (data.readInt() != 0) {
                                _arg141 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg141 = null;
                            }
                            boolean isLeDevice = isLeDevice(_arg078, _arg141);
                            reply.writeNoException();
                            reply.writeInt(isLeDevice ? 1 : 0);
                            return true;
                        case 94:
                            data.enforceInterface("android.bluetooth.IBluetooth");
                            String _arg079 = data.readString();
                            if (data.readInt() != 0) {
                                _arg142 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg142 = null;
                            }
                            boolean isConnectableDevice = isConnectableDevice(_arg079, _arg142);
                            reply.writeNoException();
                            reply.writeInt(isConnectableDevice ? 1 : 0);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetooth {
            public static IBluetooth sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "android.bluetooth.IBluetooth";
            }

            @Override // android.bluetooth.IBluetooth
            public int getState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean enable(boolean quietMode, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    _data.writeInt(quietMode ? 1 : 0);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enable(quietMode, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean disable(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disable(attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public String getAddress() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAddress();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public String getAddressWithAttribution(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAddressWithAttribution(attributionSource);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public ParcelUuid[] getUuids(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUuids(attributionSource);
                    }
                    _reply.readException();
                    ParcelUuid[] _result = (ParcelUuid[]) _reply.createTypedArray(ParcelUuid.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setName(String name, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeString(name);
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setName(name, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public String getName(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getName(attributionSource);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getNameLengthForAdvertise(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNameLengthForAdvertise(attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public BluetoothClass getBluetoothClass(AttributionSource attributionSource) throws RemoteException {
                BluetoothClass _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBluetoothClass(attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = BluetoothClass.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setBluetoothClass(BluetoothClass bluetoothClass, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (bluetoothClass != null) {
                        _data.writeInt(1);
                        bluetoothClass.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setBluetoothClass(bluetoothClass, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getIoCapability(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIoCapability(attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setIoCapability(int capability, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeInt(capability);
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setIoCapability(capability, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getLeIoCapability(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLeIoCapability(attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setLeIoCapability(int capability, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeInt(capability);
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLeIoCapability(capability, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getScanMode(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getScanMode(attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setScanMode(int mode, int duration, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeInt(mode);
                    _data.writeInt(duration);
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setScanMode(mode, duration, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getDiscoverableTimeout(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDiscoverableTimeout(attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setDiscoverableTimeout(int timeout, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeInt(timeout);
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDiscoverableTimeout(timeout, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean startDiscovery(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startDiscovery(attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean cancelDiscovery(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelDiscovery(attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isDiscovering(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDiscovering(attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public long getDiscoveryEndMillis(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDiscoveryEndMillis(attributionSource);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getAdapterConnectionState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAdapterConnectionState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getProfileConnectionState(int profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeInt(profile);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProfileConnectionState(profile);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public BluetoothDevice[] getBondedDevices(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBondedDevices(attributionSource);
                    }
                    _reply.readException();
                    BluetoothDevice[] _result = (BluetoothDevice[]) _reply.createTypedArray(BluetoothDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean createBond(BluetoothDevice device, int transport, OobData p192Data, OobData p256Data, AttributionSource attributionSource) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    try {
                        _data.writeInt(transport);
                        if (p192Data != null) {
                            _data.writeInt(1);
                            p192Data.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (p256Data != null) {
                            _data.writeInt(1);
                            p256Data.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (attributionSource != null) {
                            _data.writeInt(1);
                            attributionSource.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                if (_reply.readInt() == 0) {
                                    _result = false;
                                }
                                _reply.recycle();
                                _data.recycle();
                                return _result;
                            }
                            boolean createBond = Stub.getDefaultImpl().createBond(device, transport, p192Data, p256Data, attributionSource);
                            _reply.recycle();
                            _data.recycle();
                            return createBond;
                        } catch (Throwable th2) {
                            th = th2;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean cancelBondProcess(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelBondProcess(device, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean removeBond(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeBond(device, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getBondState(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBondState(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isBondingInitiatedLocally(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isBondingInitiatedLocally(device, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public long getSupportedProfiles() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedProfiles();
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getConnectionState(BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectionState(device);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getConnectionStateWithAttribution(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectionStateWithAttribution(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public String getRemoteName(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemoteName(device, attributionSource);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getRemoteType(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemoteType(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public String getRemoteAlias(BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemoteAlias(device);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public String getRemoteAliasWithAttribution(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemoteAliasWithAttribution(device, attributionSource);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int setRemoteAlias(BluetoothDevice device, String name, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(name);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRemoteAlias(device, name, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getRemoteClass(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemoteClass(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public ParcelUuid[] getRemoteUuids(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemoteUuids(device, attributionSource);
                    }
                    _reply.readException();
                    ParcelUuid[] _result = (ParcelUuid[]) _reply.createTypedArray(ParcelUuid.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean fetchRemoteUuids(BluetoothDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().fetchRemoteUuids(device);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean fetchRemoteUuidsWithAttribution(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().fetchRemoteUuidsWithAttribution(device, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean sdpSearch(BluetoothDevice device, ParcelUuid uuid, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (uuid != null) {
                        _data.writeInt(1);
                        uuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sdpSearch(device, uuid, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getBatteryLevel(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBatteryLevel(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getMaxConnectedAudioDevices(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMaxConnectedAudioDevices(attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setPin(BluetoothDevice device, boolean accept, int len, byte[] pinCode, AttributionSource attributionSource) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(accept ? 1 : 0);
                    try {
                        _data.writeInt(len);
                        try {
                            _data.writeByteArray(pinCode);
                            if (attributionSource != null) {
                                _data.writeInt(1);
                                attributionSource.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            try {
                                boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    if (_reply.readInt() == 0) {
                                        _result = false;
                                    }
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                boolean pin = Stub.getDefaultImpl().setPin(device, accept, len, pinCode, attributionSource);
                                _reply.recycle();
                                _data.recycle();
                                return pin;
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setPasskey(BluetoothDevice device, boolean accept, int len, byte[] passkey, AttributionSource attributionSource) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(accept ? 1 : 0);
                    try {
                        _data.writeInt(len);
                        try {
                            _data.writeByteArray(passkey);
                            if (attributionSource != null) {
                                _data.writeInt(1);
                                attributionSource.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            try {
                                boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                                if (_status || Stub.getDefaultImpl() == null) {
                                    _reply.readException();
                                    if (_reply.readInt() == 0) {
                                        _result = false;
                                    }
                                    _reply.recycle();
                                    _data.recycle();
                                    return _result;
                                }
                                boolean passkey2 = Stub.getDefaultImpl().setPasskey(device, accept, len, passkey, attributionSource);
                                _reply.recycle();
                                _data.recycle();
                                return passkey2;
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setPairingConfirmation(BluetoothDevice device, boolean accept, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(accept ? 1 : 0);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPairingConfirmation(device, accept, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getPhonebookAccessPermission(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPhonebookAccessPermission(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setSilenceMode(BluetoothDevice device, boolean silence, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(silence ? 1 : 0);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSilenceMode(device, silence, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean getSilenceMode(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(52, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSilenceMode(device, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setPhonebookAccessPermission(BluetoothDevice device, int value, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(value);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPhonebookAccessPermission(device, value, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getMessageAccessPermission(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(54, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMessageAccessPermission(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setMessageAccessPermission(BluetoothDevice device, int value, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(value);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(55, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMessageAccessPermission(device, value, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getSimAccessPermission(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(56, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSimAccessPermission(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setSimAccessPermission(BluetoothDevice device, int value, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(value);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(57, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSimAccessPermission(device, value, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public void registerCallback(IBluetoothCallback callback, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(58, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerCallback(callback, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public void unregisterCallback(IBluetoothCallback callback, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(59, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterCallback(callback, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public IBluetoothSocketManager getSocketManager() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _status = this.mRemote.transact(60, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSocketManager();
                    }
                    _reply.readException();
                    IBluetoothSocketManager _result = IBluetoothSocketManager.Stub.asInterface(_reply.readStrongBinder());
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean factoryReset(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(61, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().factoryReset(attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isMultiAdvertisementSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(62, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isMultiAdvertisementSupported();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isOffloadedFilteringSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(63, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isOffloadedFilteringSupported();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isOffloadedScanBatchingSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(64, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isOffloadedScanBatchingSupported();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isActivityAndEnergyReportingSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(65, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isActivityAndEnergyReportingSupported();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isLe2MPhySupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(66, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLe2MPhySupported();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isLeCodedPhySupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(67, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLeCodedPhySupported();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isLeExtendedAdvertisingSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(68, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLeExtendedAdvertisingSupported();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isLePeriodicAdvertisingSupported() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(69, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLePeriodicAdvertisingSupported();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getLeMaximumAdvertisingDataLength() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _status = this.mRemote.transact(70, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLeMaximumAdvertisingDataLength();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public BluetoothActivityEnergyInfo reportActivityInfo(AttributionSource attributionSource) throws RemoteException {
                BluetoothActivityEnergyInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(71, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reportActivityInfo(attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = BluetoothActivityEnergyInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean registerMetadataListener(IBluetoothMetadataListener listener, BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(72, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerMetadataListener(listener, device, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean unregisterMetadataListener(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(73, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterMetadataListener(device, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setMetadata(BluetoothDevice device, int key, byte[] value, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(key);
                    _data.writeByteArray(value);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(74, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMetadata(device, key, value, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public byte[] getMetadata(BluetoothDevice device, int key, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(key);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(75, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMetadata(device, key, attributionSource);
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public void requestActivityInfo(ResultReceiver result, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (result != null) {
                        _data.writeInt(1);
                        result.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(76, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestActivityInfo(result, attributionSource);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public void onLeServiceUp(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(77, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onLeServiceUp(attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public void onBrEdrDown(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(78, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().onBrEdrDown(attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean connectAllEnabledProfiles(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(79, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connectAllEnabledProfiles(device, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean disconnectAllEnabledProfiles(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(80, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnectAllEnabledProfiles(device, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setActiveDevice(BluetoothDevice device, int profiles, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(profiles);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(81, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setActiveDevice(device, profiles, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public List<BluetoothDevice> getMostRecentlyConnectedDevices(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(82, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMostRecentlyConnectedDevices(attributionSource);
                    }
                    _reply.readException();
                    List<BluetoothDevice> _result = _reply.createTypedArrayList(BluetoothDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean removeActiveDevice(int profiles, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeInt(profiles);
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(83, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeActiveDevice(profiles, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean registerBluetoothConnectionCallback(IBluetoothConnectionCallback callback, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(84, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerBluetoothConnectionCallback(callback, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean unregisterBluetoothConnectionCallback(IBluetoothConnectionCallback callback, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(85, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unregisterBluetoothConnectionCallback(callback, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean canBondWithoutDialog(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(86, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canBondWithoutDialog(device, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public void generateLocalOobData(int transport, IBluetoothOobDataCallback callback, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeInt(transport);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(87, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().generateLocalOobData(transport, callback, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean setLeAudioStatus(BluetoothDevice device, int value, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    boolean _result = true;
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(value);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(88, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLeAudioStatus(device, value, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public int getLeAudioStatus(BluetoothDevice device, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(89, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLeAudioStatus(device, attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isDualModeDevice(String address, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeString(address);
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(90, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDualModeDevice(address, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public String findBrAddress(String address, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeString(address);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(91, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().findBrAddress(address, attributionSource);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public String findLeAddress(String address, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeString(address);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(92, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().findLeAddress(address, attributionSource);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isLeDevice(String address, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeString(address);
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(93, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLeDevice(address, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetooth
            public boolean isConnectableDevice(String address, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    _data.writeString(address);
                    boolean _result = true;
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(94, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isConnectableDevice(address, attributionSource);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetooth impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetooth getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
