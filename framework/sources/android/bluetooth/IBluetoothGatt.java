package android.bluetooth;

import android.app.PendingIntent;
import android.bluetooth.IBluetoothGattCallback;
import android.bluetooth.IBluetoothGattServerCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertisingSetParameters;
import android.bluetooth.le.IAdvertisingSetCallback;
import android.bluetooth.le.IPeriodicAdvertisingCallback;
import android.bluetooth.le.IScannerCallback;
import android.bluetooth.le.PeriodicAdvertisingParameters;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.AttributionSource;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.WorkSource;
import java.util.List;
/* loaded from: classes.dex */
public interface IBluetoothGatt extends IInterface {
    void addService(int i, BluetoothGattService bluetoothGattService, AttributionSource attributionSource) throws RemoteException;

    void beginReliableWrite(int i, String str, AttributionSource attributionSource) throws RemoteException;

    void clearServices(int i, AttributionSource attributionSource) throws RemoteException;

    void clientConnect(int i, String str, boolean z, int i2, boolean z2, int i3, AttributionSource attributionSource) throws RemoteException;

    void clientDisconnect(int i, String str, AttributionSource attributionSource) throws RemoteException;

    void clientReadPhy(int i, String str, AttributionSource attributionSource) throws RemoteException;

    void clientSetPreferredPhy(int i, String str, int i2, int i3, int i4, AttributionSource attributionSource) throws RemoteException;

    void configureMTU(int i, String str, int i2, AttributionSource attributionSource) throws RemoteException;

    void connectionParameterUpdate(int i, String str, int i2, AttributionSource attributionSource) throws RemoteException;

    void disconnectAll(AttributionSource attributionSource) throws RemoteException;

    void discoverServiceByUuid(int i, String str, ParcelUuid parcelUuid, AttributionSource attributionSource) throws RemoteException;

    void discoverServices(int i, String str, AttributionSource attributionSource) throws RemoteException;

    void enableAdvertisingSet(int i, boolean z, int i2, int i3, AttributionSource attributionSource) throws RemoteException;

    void endReliableWrite(int i, String str, boolean z, AttributionSource attributionSource) throws RemoteException;

    void flushPendingBatchResults(int i, AttributionSource attributionSource) throws RemoteException;

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr, AttributionSource attributionSource) throws RemoteException;

    void getOwnAddress(int i, AttributionSource attributionSource) throws RemoteException;

    void leConnectionUpdate(int i, String str, int i2, int i3, int i4, int i5, int i6, int i7, AttributionSource attributionSource) throws RemoteException;

    int numHwTrackFiltersAvailable(AttributionSource attributionSource) throws RemoteException;

    void readCharacteristic(int i, String str, int i2, int i3, AttributionSource attributionSource) throws RemoteException;

    void readDescriptor(int i, String str, int i2, int i3, AttributionSource attributionSource) throws RemoteException;

    void readRemoteRssi(int i, String str, AttributionSource attributionSource) throws RemoteException;

    void readUsingCharacteristicUuid(int i, String str, ParcelUuid parcelUuid, int i2, int i3, int i4, AttributionSource attributionSource) throws RemoteException;

    void refreshDevice(int i, String str, AttributionSource attributionSource) throws RemoteException;

    void registerClient(ParcelUuid parcelUuid, IBluetoothGattCallback iBluetoothGattCallback, boolean z, AttributionSource attributionSource) throws RemoteException;

    void registerForNotification(int i, String str, int i2, boolean z, AttributionSource attributionSource) throws RemoteException;

    void registerScanner(IScannerCallback iScannerCallback, WorkSource workSource, AttributionSource attributionSource) throws RemoteException;

    void registerServer(ParcelUuid parcelUuid, IBluetoothGattServerCallback iBluetoothGattServerCallback, boolean z, AttributionSource attributionSource) throws RemoteException;

    void registerSync(ScanResult scanResult, int i, int i2, IPeriodicAdvertisingCallback iPeriodicAdvertisingCallback, AttributionSource attributionSource) throws RemoteException;

    void removeService(int i, int i2, AttributionSource attributionSource) throws RemoteException;

    void sendNotification(int i, String str, int i2, boolean z, byte[] bArr, AttributionSource attributionSource) throws RemoteException;

    void sendResponse(int i, String str, int i2, int i3, int i4, byte[] bArr, AttributionSource attributionSource) throws RemoteException;

    void serverConnect(int i, String str, boolean z, int i2, AttributionSource attributionSource) throws RemoteException;

    void serverDisconnect(int i, String str, AttributionSource attributionSource) throws RemoteException;

    void serverReadPhy(int i, String str, AttributionSource attributionSource) throws RemoteException;

    void serverSetPreferredPhy(int i, String str, int i2, int i3, int i4, AttributionSource attributionSource) throws RemoteException;

    void setAdvertisingData(int i, AdvertiseData advertiseData, AttributionSource attributionSource) throws RemoteException;

    void setAdvertisingParameters(int i, AdvertisingSetParameters advertisingSetParameters, AttributionSource attributionSource) throws RemoteException;

    void setPeriodicAdvertisingData(int i, AdvertiseData advertiseData, AttributionSource attributionSource) throws RemoteException;

    void setPeriodicAdvertisingEnable(int i, boolean z, AttributionSource attributionSource) throws RemoteException;

    void setPeriodicAdvertisingParameters(int i, PeriodicAdvertisingParameters periodicAdvertisingParameters, AttributionSource attributionSource) throws RemoteException;

    void setScanResponseData(int i, AdvertiseData advertiseData, AttributionSource attributionSource) throws RemoteException;

    void startAdvertisingSet(AdvertisingSetParameters advertisingSetParameters, AdvertiseData advertiseData, AdvertiseData advertiseData2, PeriodicAdvertisingParameters periodicAdvertisingParameters, AdvertiseData advertiseData3, int i, int i2, IAdvertisingSetCallback iAdvertisingSetCallback, AttributionSource attributionSource) throws RemoteException;

    void startScan(int i, ScanSettings scanSettings, List<ScanFilter> list, List list2, AttributionSource attributionSource) throws RemoteException;

    void startScanForIntent(PendingIntent pendingIntent, ScanSettings scanSettings, List<ScanFilter> list, AttributionSource attributionSource) throws RemoteException;

    void stopAdvertisingSet(IAdvertisingSetCallback iAdvertisingSetCallback, AttributionSource attributionSource) throws RemoteException;

    void stopScan(int i, AttributionSource attributionSource) throws RemoteException;

    void stopScanForIntent(PendingIntent pendingIntent, AttributionSource attributionSource) throws RemoteException;

    void unregAll(AttributionSource attributionSource) throws RemoteException;

    void unregisterClient(int i, AttributionSource attributionSource) throws RemoteException;

    void unregisterScanner(int i, AttributionSource attributionSource) throws RemoteException;

    void unregisterServer(int i, AttributionSource attributionSource) throws RemoteException;

    void unregisterSync(IPeriodicAdvertisingCallback iPeriodicAdvertisingCallback, AttributionSource attributionSource) throws RemoteException;

    void writeCharacteristic(int i, String str, int i2, int i3, int i4, byte[] bArr, AttributionSource attributionSource) throws RemoteException;

    void writeDescriptor(int i, String str, int i2, int i3, byte[] bArr, AttributionSource attributionSource) throws RemoteException;

    /* loaded from: classes.dex */
    public static class Default implements IBluetoothGatt {
        @Override // android.bluetooth.IBluetoothGatt
        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states, AttributionSource attributionSource) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void registerScanner(IScannerCallback callback, WorkSource workSource, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void unregisterScanner(int scannerId, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void startScan(int scannerId, ScanSettings settings, List<ScanFilter> filters, List scanStorages, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void startScanForIntent(PendingIntent intent, ScanSettings settings, List<ScanFilter> filters, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void stopScanForIntent(PendingIntent intent, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void stopScan(int scannerId, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void flushPendingBatchResults(int scannerId, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void startAdvertisingSet(AdvertisingSetParameters parameters, AdvertiseData advertiseData, AdvertiseData scanResponse, PeriodicAdvertisingParameters periodicParameters, AdvertiseData periodicData, int duration, int maxExtAdvEvents, IAdvertisingSetCallback callback, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void stopAdvertisingSet(IAdvertisingSetCallback callback, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void getOwnAddress(int advertiserId, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void enableAdvertisingSet(int advertiserId, boolean enable, int duration, int maxExtAdvEvents, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void setAdvertisingData(int advertiserId, AdvertiseData data, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void setScanResponseData(int advertiserId, AdvertiseData data, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void setAdvertisingParameters(int advertiserId, AdvertisingSetParameters parameters, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void setPeriodicAdvertisingParameters(int advertiserId, PeriodicAdvertisingParameters parameters, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void setPeriodicAdvertisingData(int advertiserId, AdvertiseData data, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void setPeriodicAdvertisingEnable(int advertiserId, boolean enable, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void registerSync(ScanResult scanResult, int skip, int timeout, IPeriodicAdvertisingCallback callback, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void unregisterSync(IPeriodicAdvertisingCallback callback, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void registerClient(ParcelUuid appId, IBluetoothGattCallback callback, boolean eatt_support, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void unregisterClient(int clientIf, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void clientConnect(int clientIf, String address, boolean isDirect, int transport, boolean opportunistic, int phy, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void clientDisconnect(int clientIf, String address, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void clientSetPreferredPhy(int clientIf, String address, int txPhy, int rxPhy, int phyOptions, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void clientReadPhy(int clientIf, String addres, AttributionSource attributionSources) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void refreshDevice(int clientIf, String address, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void discoverServices(int clientIf, String address, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void discoverServiceByUuid(int clientIf, String address, ParcelUuid uuid, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void readCharacteristic(int clientIf, String address, int handle, int authReq, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void readUsingCharacteristicUuid(int clientIf, String address, ParcelUuid uuid, int startHandle, int endHandle, int authReq, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void writeCharacteristic(int clientIf, String address, int handle, int writeType, int authReq, byte[] value, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void readDescriptor(int clientIf, String address, int handle, int authReq, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void writeDescriptor(int clientIf, String address, int handle, int authReq, byte[] value, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void registerForNotification(int clientIf, String address, int handle, boolean enable, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void beginReliableWrite(int clientIf, String address, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void endReliableWrite(int clientIf, String address, boolean execute, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void readRemoteRssi(int clientIf, String address, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void configureMTU(int clientIf, String address, int mtu, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void connectionParameterUpdate(int clientIf, String address, int connectionPriority, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void leConnectionUpdate(int clientIf, String address, int minInterval, int maxInterval, int peripheralLatency, int supervisionTimeout, int minConnectionEventLen, int maxConnectionEventLen, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void registerServer(ParcelUuid appId, IBluetoothGattServerCallback callback, boolean eatt_support, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void unregisterServer(int serverIf, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void serverConnect(int serverIf, String address, boolean isDirect, int transport, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void serverDisconnect(int serverIf, String address, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void serverSetPreferredPhy(int clientIf, String address, int txPhy, int rxPhy, int phyOptions, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void serverReadPhy(int clientIf, String address, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void addService(int serverIf, BluetoothGattService service, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void removeService(int serverIf, int handle, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void clearServices(int serverIf, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void sendResponse(int serverIf, String address, int requestId, int status, int offset, byte[] value, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void sendNotification(int serverIf, String address, int handle, boolean confirm, byte[] value, AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void disconnectAll(AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public void unregAll(AttributionSource attributionSource) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGatt
        public int numHwTrackFiltersAvailable(AttributionSource attributionSource) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IBluetoothGatt {
        public static final String DESCRIPTOR = "android.bluetooth.IBluetoothGatt";
        static final int TRANSACTION_addService = 48;
        static final int TRANSACTION_beginReliableWrite = 36;
        static final int TRANSACTION_clearServices = 50;
        static final int TRANSACTION_clientConnect = 23;
        static final int TRANSACTION_clientDisconnect = 24;
        static final int TRANSACTION_clientReadPhy = 26;
        static final int TRANSACTION_clientSetPreferredPhy = 25;
        static final int TRANSACTION_configureMTU = 39;
        static final int TRANSACTION_connectionParameterUpdate = 40;
        static final int TRANSACTION_disconnectAll = 53;
        static final int TRANSACTION_discoverServiceByUuid = 29;
        static final int TRANSACTION_discoverServices = 28;
        static final int TRANSACTION_enableAdvertisingSet = 12;
        static final int TRANSACTION_endReliableWrite = 37;
        static final int TRANSACTION_flushPendingBatchResults = 8;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 1;
        static final int TRANSACTION_getOwnAddress = 11;
        static final int TRANSACTION_leConnectionUpdate = 41;
        static final int TRANSACTION_numHwTrackFiltersAvailable = 55;
        static final int TRANSACTION_readCharacteristic = 30;
        static final int TRANSACTION_readDescriptor = 33;
        static final int TRANSACTION_readRemoteRssi = 38;
        static final int TRANSACTION_readUsingCharacteristicUuid = 31;
        static final int TRANSACTION_refreshDevice = 27;
        static final int TRANSACTION_registerClient = 21;
        static final int TRANSACTION_registerForNotification = 35;
        static final int TRANSACTION_registerScanner = 2;
        static final int TRANSACTION_registerServer = 42;
        static final int TRANSACTION_registerSync = 19;
        static final int TRANSACTION_removeService = 49;
        static final int TRANSACTION_sendNotification = 52;
        static final int TRANSACTION_sendResponse = 51;
        static final int TRANSACTION_serverConnect = 44;
        static final int TRANSACTION_serverDisconnect = 45;
        static final int TRANSACTION_serverReadPhy = 47;
        static final int TRANSACTION_serverSetPreferredPhy = 46;
        static final int TRANSACTION_setAdvertisingData = 13;
        static final int TRANSACTION_setAdvertisingParameters = 15;
        static final int TRANSACTION_setPeriodicAdvertisingData = 17;
        static final int TRANSACTION_setPeriodicAdvertisingEnable = 18;
        static final int TRANSACTION_setPeriodicAdvertisingParameters = 16;
        static final int TRANSACTION_setScanResponseData = 14;
        static final int TRANSACTION_startAdvertisingSet = 9;
        static final int TRANSACTION_startScan = 4;
        static final int TRANSACTION_startScanForIntent = 5;
        static final int TRANSACTION_stopAdvertisingSet = 10;
        static final int TRANSACTION_stopScan = 7;
        static final int TRANSACTION_stopScanForIntent = 6;
        static final int TRANSACTION_unregAll = 54;
        static final int TRANSACTION_unregisterClient = 22;
        static final int TRANSACTION_unregisterScanner = 3;
        static final int TRANSACTION_unregisterServer = 43;
        static final int TRANSACTION_unregisterSync = 20;
        static final int TRANSACTION_writeCharacteristic = 32;
        static final int TRANSACTION_writeDescriptor = 34;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBluetoothGatt asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IBluetoothGatt)) {
                return new Proxy(obj);
            }
            return (IBluetoothGatt) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static String getDefaultTransactionName(int transactionCode) {
            switch (transactionCode) {
                case 1:
                    return "getDevicesMatchingConnectionStates";
                case 2:
                    return "registerScanner";
                case 3:
                    return "unregisterScanner";
                case 4:
                    return "startScan";
                case 5:
                    return "startScanForIntent";
                case 6:
                    return "stopScanForIntent";
                case 7:
                    return "stopScan";
                case 8:
                    return "flushPendingBatchResults";
                case 9:
                    return "startAdvertisingSet";
                case 10:
                    return "stopAdvertisingSet";
                case 11:
                    return "getOwnAddress";
                case 12:
                    return "enableAdvertisingSet";
                case 13:
                    return "setAdvertisingData";
                case 14:
                    return "setScanResponseData";
                case 15:
                    return "setAdvertisingParameters";
                case 16:
                    return "setPeriodicAdvertisingParameters";
                case 17:
                    return "setPeriodicAdvertisingData";
                case 18:
                    return "setPeriodicAdvertisingEnable";
                case 19:
                    return "registerSync";
                case 20:
                    return "unregisterSync";
                case 21:
                    return "registerClient";
                case 22:
                    return "unregisterClient";
                case 23:
                    return "clientConnect";
                case 24:
                    return "clientDisconnect";
                case 25:
                    return "clientSetPreferredPhy";
                case 26:
                    return "clientReadPhy";
                case 27:
                    return "refreshDevice";
                case 28:
                    return "discoverServices";
                case 29:
                    return "discoverServiceByUuid";
                case 30:
                    return "readCharacteristic";
                case 31:
                    return "readUsingCharacteristicUuid";
                case 32:
                    return "writeCharacteristic";
                case 33:
                    return "readDescriptor";
                case 34:
                    return "writeDescriptor";
                case 35:
                    return "registerForNotification";
                case 36:
                    return "beginReliableWrite";
                case 37:
                    return "endReliableWrite";
                case 38:
                    return "readRemoteRssi";
                case 39:
                    return "configureMTU";
                case 40:
                    return "connectionParameterUpdate";
                case 41:
                    return "leConnectionUpdate";
                case 42:
                    return "registerServer";
                case 43:
                    return "unregisterServer";
                case 44:
                    return "serverConnect";
                case 45:
                    return "serverDisconnect";
                case 46:
                    return "serverSetPreferredPhy";
                case 47:
                    return "serverReadPhy";
                case 48:
                    return "addService";
                case 49:
                    return "removeService";
                case 50:
                    return "clearServices";
                case 51:
                    return "sendResponse";
                case 52:
                    return "sendNotification";
                case 53:
                    return "disconnectAll";
                case 54:
                    return "unregAll";
                case 55:
                    return "numHwTrackFiltersAvailable";
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
            WorkSource _arg12;
            AttributionSource _arg2;
            AttributionSource _arg13;
            ScanSettings _arg14;
            AttributionSource _arg4;
            PendingIntent _arg0;
            ScanSettings _arg15;
            AttributionSource _arg3;
            PendingIntent _arg02;
            AttributionSource _arg16;
            AttributionSource _arg17;
            AttributionSource _arg18;
            AdvertisingSetParameters _arg03;
            AdvertiseData _arg19;
            AdvertiseData _arg22;
            PeriodicAdvertisingParameters _arg32;
            AdvertiseData _arg42;
            AttributionSource _arg8;
            AttributionSource _arg110;
            AttributionSource _arg111;
            boolean _arg112;
            AttributionSource _arg43;
            AdvertiseData _arg113;
            AttributionSource _arg23;
            AdvertiseData _arg114;
            AttributionSource _arg24;
            AdvertisingSetParameters _arg115;
            AttributionSource _arg25;
            PeriodicAdvertisingParameters _arg116;
            AttributionSource _arg26;
            AdvertiseData _arg117;
            AttributionSource _arg27;
            AttributionSource _arg28;
            ScanResult _arg04;
            AttributionSource _arg44;
            AttributionSource _arg118;
            ParcelUuid _arg05;
            AttributionSource _arg33;
            AttributionSource _arg119;
            boolean _arg29;
            boolean _arg45;
            AttributionSource _arg6;
            AttributionSource _arg210;
            AttributionSource _arg5;
            AttributionSource _arg211;
            AttributionSource _arg212;
            AttributionSource _arg213;
            ParcelUuid _arg214;
            AttributionSource _arg34;
            AttributionSource _arg46;
            ParcelUuid _arg215;
            AttributionSource _arg62;
            AttributionSource _arg63;
            AttributionSource _arg47;
            AttributionSource _arg52;
            boolean _arg35;
            AttributionSource _arg48;
            AttributionSource _arg216;
            AttributionSource _arg36;
            AttributionSource _arg217;
            AttributionSource _arg37;
            AttributionSource _arg38;
            AttributionSource _arg82;
            ParcelUuid _arg06;
            AttributionSource _arg39;
            AttributionSource _arg120;
            boolean _arg218;
            AttributionSource _arg49;
            AttributionSource _arg219;
            AttributionSource _arg53;
            AttributionSource _arg220;
            BluetoothGattService _arg121;
            AttributionSource _arg221;
            AttributionSource _arg222;
            AttributionSource _arg122;
            AttributionSource _arg64;
            boolean _arg310;
            AttributionSource _arg54;
            AttributionSource _arg07;
            AttributionSource _arg08;
            AttributionSource _arg09;
            switch (code) {
                case IBinder.INTERFACE_TRANSACTION /* 1598968902 */:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    boolean _arg223 = false;
                    switch (code) {
                        case 1:
                            data.enforceInterface(DESCRIPTOR);
                            int[] _arg010 = data.createIntArray();
                            if (data.readInt() != 0) {
                                _arg1 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg1 = null;
                            }
                            List<BluetoothDevice> _result = getDevicesMatchingConnectionStates(_arg010, _arg1);
                            reply.writeNoException();
                            reply.writeTypedList(_result);
                            return true;
                        case 2:
                            data.enforceInterface(DESCRIPTOR);
                            IScannerCallback _arg011 = IScannerCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg12 = WorkSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg12 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg2 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg2 = null;
                            }
                            registerScanner(_arg011, _arg12, _arg2);
                            reply.writeNoException();
                            return true;
                        case 3:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg012 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg13 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg13 = null;
                            }
                            unregisterScanner(_arg012, _arg13);
                            reply.writeNoException();
                            return true;
                        case 4:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg013 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg14 = ScanSettings.CREATOR.createFromParcel(data);
                            } else {
                                _arg14 = null;
                            }
                            List<ScanFilter> _arg224 = data.createTypedArrayList(ScanFilter.CREATOR);
                            ClassLoader cl = getClass().getClassLoader();
                            List _arg311 = data.readArrayList(cl);
                            if (data.readInt() != 0) {
                                _arg4 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg4 = null;
                            }
                            startScan(_arg013, _arg14, _arg224, _arg311, _arg4);
                            reply.writeNoException();
                            return true;
                        case 5:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg0 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg0 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg15 = ScanSettings.CREATOR.createFromParcel(data);
                            } else {
                                _arg15 = null;
                            }
                            List<ScanFilter> _arg225 = data.createTypedArrayList(ScanFilter.CREATOR);
                            if (data.readInt() != 0) {
                                _arg3 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg3 = null;
                            }
                            startScanForIntent(_arg0, _arg15, _arg225, _arg3);
                            reply.writeNoException();
                            return true;
                        case 6:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg02 = PendingIntent.CREATOR.createFromParcel(data);
                            } else {
                                _arg02 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg16 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg16 = null;
                            }
                            stopScanForIntent(_arg02, _arg16);
                            reply.writeNoException();
                            return true;
                        case 7:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg014 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg17 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg17 = null;
                            }
                            stopScan(_arg014, _arg17);
                            reply.writeNoException();
                            return true;
                        case 8:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg015 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg18 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg18 = null;
                            }
                            flushPendingBatchResults(_arg015, _arg18);
                            reply.writeNoException();
                            return true;
                        case 9:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg03 = AdvertisingSetParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg03 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg19 = AdvertiseData.CREATOR.createFromParcel(data);
                            } else {
                                _arg19 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg22 = AdvertiseData.CREATOR.createFromParcel(data);
                            } else {
                                _arg22 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg32 = PeriodicAdvertisingParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg32 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg42 = AdvertiseData.CREATOR.createFromParcel(data);
                            } else {
                                _arg42 = null;
                            }
                            int _arg55 = data.readInt();
                            int _arg65 = data.readInt();
                            IAdvertisingSetCallback _arg7 = IAdvertisingSetCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg8 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg8 = null;
                            }
                            startAdvertisingSet(_arg03, _arg19, _arg22, _arg32, _arg42, _arg55, _arg65, _arg7, _arg8);
                            reply.writeNoException();
                            return true;
                        case 10:
                            data.enforceInterface(DESCRIPTOR);
                            IAdvertisingSetCallback _arg016 = IAdvertisingSetCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg110 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg110 = null;
                            }
                            stopAdvertisingSet(_arg016, _arg110);
                            reply.writeNoException();
                            return true;
                        case 11:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg017 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg111 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg111 = null;
                            }
                            getOwnAddress(_arg017, _arg111);
                            reply.writeNoException();
                            return true;
                        case 12:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg018 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg112 = true;
                            } else {
                                _arg112 = false;
                            }
                            int _arg226 = data.readInt();
                            int _arg312 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg43 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg43 = null;
                            }
                            enableAdvertisingSet(_arg018, _arg112, _arg226, _arg312, _arg43);
                            reply.writeNoException();
                            return true;
                        case 13:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg019 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg113 = AdvertiseData.CREATOR.createFromParcel(data);
                            } else {
                                _arg113 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg23 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg23 = null;
                            }
                            setAdvertisingData(_arg019, _arg113, _arg23);
                            reply.writeNoException();
                            return true;
                        case 14:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg020 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg114 = AdvertiseData.CREATOR.createFromParcel(data);
                            } else {
                                _arg114 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg24 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg24 = null;
                            }
                            setScanResponseData(_arg020, _arg114, _arg24);
                            reply.writeNoException();
                            return true;
                        case 15:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg021 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg115 = AdvertisingSetParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg115 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg25 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg25 = null;
                            }
                            setAdvertisingParameters(_arg021, _arg115, _arg25);
                            reply.writeNoException();
                            return true;
                        case 16:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg022 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg116 = PeriodicAdvertisingParameters.CREATOR.createFromParcel(data);
                            } else {
                                _arg116 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg26 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg26 = null;
                            }
                            setPeriodicAdvertisingParameters(_arg022, _arg116, _arg26);
                            reply.writeNoException();
                            return true;
                        case 17:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg023 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg117 = AdvertiseData.CREATOR.createFromParcel(data);
                            } else {
                                _arg117 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg27 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg27 = null;
                            }
                            setPeriodicAdvertisingData(_arg023, _arg117, _arg27);
                            reply.writeNoException();
                            return true;
                        case 18:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg024 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg223 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg28 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg28 = null;
                            }
                            setPeriodicAdvertisingEnable(_arg024, _arg223, _arg28);
                            reply.writeNoException();
                            return true;
                        case 19:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg04 = ScanResult.CREATOR.createFromParcel(data);
                            } else {
                                _arg04 = null;
                            }
                            int _arg123 = data.readInt();
                            int _arg227 = data.readInt();
                            IPeriodicAdvertisingCallback _arg313 = IPeriodicAdvertisingCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg44 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg44 = null;
                            }
                            registerSync(_arg04, _arg123, _arg227, _arg313, _arg44);
                            reply.writeNoException();
                            return true;
                        case 20:
                            data.enforceInterface(DESCRIPTOR);
                            IPeriodicAdvertisingCallback _arg025 = IPeriodicAdvertisingCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg118 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg118 = null;
                            }
                            unregisterSync(_arg025, _arg118);
                            reply.writeNoException();
                            return true;
                        case 21:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg05 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg05 = null;
                            }
                            IBluetoothGattCallback _arg124 = IBluetoothGattCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg223 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg33 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg33 = null;
                            }
                            registerClient(_arg05, _arg124, _arg223, _arg33);
                            reply.writeNoException();
                            return true;
                        case 22:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg026 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg119 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg119 = null;
                            }
                            unregisterClient(_arg026, _arg119);
                            reply.writeNoException();
                            return true;
                        case 23:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg027 = data.readInt();
                            String _arg125 = data.readString();
                            if (data.readInt() != 0) {
                                _arg29 = true;
                            } else {
                                _arg29 = false;
                            }
                            int _arg314 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg45 = true;
                            } else {
                                _arg45 = false;
                            }
                            int _arg56 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg6 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg6 = null;
                            }
                            clientConnect(_arg027, _arg125, _arg29, _arg314, _arg45, _arg56, _arg6);
                            reply.writeNoException();
                            return true;
                        case 24:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg028 = data.readInt();
                            String _arg126 = data.readString();
                            if (data.readInt() != 0) {
                                _arg210 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg210 = null;
                            }
                            clientDisconnect(_arg028, _arg126, _arg210);
                            reply.writeNoException();
                            return true;
                        case 25:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg029 = data.readInt();
                            String _arg127 = data.readString();
                            int _arg228 = data.readInt();
                            int _arg315 = data.readInt();
                            int _arg410 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg5 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg5 = null;
                            }
                            clientSetPreferredPhy(_arg029, _arg127, _arg228, _arg315, _arg410, _arg5);
                            reply.writeNoException();
                            return true;
                        case 26:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg030 = data.readInt();
                            String _arg128 = data.readString();
                            if (data.readInt() != 0) {
                                _arg211 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg211 = null;
                            }
                            clientReadPhy(_arg030, _arg128, _arg211);
                            reply.writeNoException();
                            return true;
                        case 27:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg031 = data.readInt();
                            String _arg129 = data.readString();
                            if (data.readInt() != 0) {
                                _arg212 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg212 = null;
                            }
                            refreshDevice(_arg031, _arg129, _arg212);
                            reply.writeNoException();
                            return true;
                        case 28:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg032 = data.readInt();
                            String _arg130 = data.readString();
                            if (data.readInt() != 0) {
                                _arg213 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg213 = null;
                            }
                            discoverServices(_arg032, _arg130, _arg213);
                            reply.writeNoException();
                            return true;
                        case 29:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg033 = data.readInt();
                            String _arg131 = data.readString();
                            if (data.readInt() != 0) {
                                _arg214 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg214 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg34 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg34 = null;
                            }
                            discoverServiceByUuid(_arg033, _arg131, _arg214, _arg34);
                            reply.writeNoException();
                            return true;
                        case 30:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg034 = data.readInt();
                            String _arg132 = data.readString();
                            int _arg229 = data.readInt();
                            int _arg316 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg46 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg46 = null;
                            }
                            readCharacteristic(_arg034, _arg132, _arg229, _arg316, _arg46);
                            reply.writeNoException();
                            return true;
                        case 31:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg035 = data.readInt();
                            String _arg133 = data.readString();
                            if (data.readInt() != 0) {
                                _arg215 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg215 = null;
                            }
                            int _arg317 = data.readInt();
                            int _arg411 = data.readInt();
                            int _arg57 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg62 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg62 = null;
                            }
                            readUsingCharacteristicUuid(_arg035, _arg133, _arg215, _arg317, _arg411, _arg57, _arg62);
                            reply.writeNoException();
                            return true;
                        case 32:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg036 = data.readInt();
                            String _arg134 = data.readString();
                            int _arg230 = data.readInt();
                            int _arg318 = data.readInt();
                            int _arg412 = data.readInt();
                            byte[] _arg58 = data.createByteArray();
                            if (data.readInt() != 0) {
                                _arg63 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg63 = null;
                            }
                            writeCharacteristic(_arg036, _arg134, _arg230, _arg318, _arg412, _arg58, _arg63);
                            reply.writeNoException();
                            return true;
                        case 33:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg037 = data.readInt();
                            String _arg135 = data.readString();
                            int _arg231 = data.readInt();
                            int _arg319 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg47 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg47 = null;
                            }
                            readDescriptor(_arg037, _arg135, _arg231, _arg319, _arg47);
                            reply.writeNoException();
                            return true;
                        case 34:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg038 = data.readInt();
                            String _arg136 = data.readString();
                            int _arg232 = data.readInt();
                            int _arg320 = data.readInt();
                            byte[] _arg413 = data.createByteArray();
                            if (data.readInt() != 0) {
                                _arg52 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg52 = null;
                            }
                            writeDescriptor(_arg038, _arg136, _arg232, _arg320, _arg413, _arg52);
                            reply.writeNoException();
                            return true;
                        case 35:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg039 = data.readInt();
                            String _arg137 = data.readString();
                            int _arg233 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg35 = true;
                            } else {
                                _arg35 = false;
                            }
                            if (data.readInt() != 0) {
                                _arg48 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg48 = null;
                            }
                            registerForNotification(_arg039, _arg137, _arg233, _arg35, _arg48);
                            reply.writeNoException();
                            return true;
                        case 36:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg040 = data.readInt();
                            String _arg138 = data.readString();
                            if (data.readInt() != 0) {
                                _arg216 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg216 = null;
                            }
                            beginReliableWrite(_arg040, _arg138, _arg216);
                            reply.writeNoException();
                            return true;
                        case 37:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg041 = data.readInt();
                            String _arg139 = data.readString();
                            if (data.readInt() != 0) {
                                _arg223 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg36 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg36 = null;
                            }
                            endReliableWrite(_arg041, _arg139, _arg223, _arg36);
                            reply.writeNoException();
                            return true;
                        case 38:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg042 = data.readInt();
                            String _arg140 = data.readString();
                            if (data.readInt() != 0) {
                                _arg217 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg217 = null;
                            }
                            readRemoteRssi(_arg042, _arg140, _arg217);
                            reply.writeNoException();
                            return true;
                        case 39:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg043 = data.readInt();
                            String _arg141 = data.readString();
                            int _arg234 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg37 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg37 = null;
                            }
                            configureMTU(_arg043, _arg141, _arg234, _arg37);
                            reply.writeNoException();
                            return true;
                        case 40:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg044 = data.readInt();
                            String _arg142 = data.readString();
                            int _arg235 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg38 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg38 = null;
                            }
                            connectionParameterUpdate(_arg044, _arg142, _arg235, _arg38);
                            reply.writeNoException();
                            return true;
                        case 41:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg045 = data.readInt();
                            String _arg143 = data.readString();
                            int _arg236 = data.readInt();
                            int _arg321 = data.readInt();
                            int _arg414 = data.readInt();
                            int _arg59 = data.readInt();
                            int _arg66 = data.readInt();
                            int _arg72 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg82 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg82 = null;
                            }
                            leConnectionUpdate(_arg045, _arg143, _arg236, _arg321, _arg414, _arg59, _arg66, _arg72, _arg82);
                            reply.writeNoException();
                            return true;
                        case 42:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg06 = ParcelUuid.CREATOR.createFromParcel(data);
                            } else {
                                _arg06 = null;
                            }
                            IBluetoothGattServerCallback _arg144 = IBluetoothGattServerCallback.Stub.asInterface(data.readStrongBinder());
                            if (data.readInt() != 0) {
                                _arg223 = true;
                            }
                            if (data.readInt() != 0) {
                                _arg39 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg39 = null;
                            }
                            registerServer(_arg06, _arg144, _arg223, _arg39);
                            reply.writeNoException();
                            return true;
                        case 43:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg046 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg120 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg120 = null;
                            }
                            unregisterServer(_arg046, _arg120);
                            reply.writeNoException();
                            return true;
                        case 44:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg047 = data.readInt();
                            String _arg145 = data.readString();
                            if (data.readInt() != 0) {
                                _arg218 = true;
                            } else {
                                _arg218 = false;
                            }
                            int _arg322 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg49 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg49 = null;
                            }
                            serverConnect(_arg047, _arg145, _arg218, _arg322, _arg49);
                            reply.writeNoException();
                            return true;
                        case 45:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg048 = data.readInt();
                            String _arg146 = data.readString();
                            if (data.readInt() != 0) {
                                _arg219 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg219 = null;
                            }
                            serverDisconnect(_arg048, _arg146, _arg219);
                            reply.writeNoException();
                            return true;
                        case 46:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg049 = data.readInt();
                            String _arg147 = data.readString();
                            int _arg237 = data.readInt();
                            int _arg323 = data.readInt();
                            int _arg415 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg53 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg53 = null;
                            }
                            serverSetPreferredPhy(_arg049, _arg147, _arg237, _arg323, _arg415, _arg53);
                            reply.writeNoException();
                            return true;
                        case 47:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg050 = data.readInt();
                            String _arg148 = data.readString();
                            if (data.readInt() != 0) {
                                _arg220 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg220 = null;
                            }
                            serverReadPhy(_arg050, _arg148, _arg220);
                            reply.writeNoException();
                            return true;
                        case 48:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg051 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg121 = BluetoothGattService.CREATOR.createFromParcel(data);
                            } else {
                                _arg121 = null;
                            }
                            if (data.readInt() != 0) {
                                _arg221 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg221 = null;
                            }
                            addService(_arg051, _arg121, _arg221);
                            reply.writeNoException();
                            return true;
                        case 49:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg052 = data.readInt();
                            int _arg149 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg222 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg222 = null;
                            }
                            removeService(_arg052, _arg149, _arg222);
                            reply.writeNoException();
                            return true;
                        case 50:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg053 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg122 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg122 = null;
                            }
                            clearServices(_arg053, _arg122);
                            reply.writeNoException();
                            return true;
                        case 51:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg054 = data.readInt();
                            String _arg150 = data.readString();
                            int _arg238 = data.readInt();
                            int _arg324 = data.readInt();
                            int _arg416 = data.readInt();
                            byte[] _arg510 = data.createByteArray();
                            if (data.readInt() != 0) {
                                _arg64 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg64 = null;
                            }
                            sendResponse(_arg054, _arg150, _arg238, _arg324, _arg416, _arg510, _arg64);
                            reply.writeNoException();
                            return true;
                        case 52:
                            data.enforceInterface(DESCRIPTOR);
                            int _arg055 = data.readInt();
                            String _arg151 = data.readString();
                            int _arg239 = data.readInt();
                            if (data.readInt() != 0) {
                                _arg310 = true;
                            } else {
                                _arg310 = false;
                            }
                            byte[] _arg417 = data.createByteArray();
                            if (data.readInt() != 0) {
                                _arg54 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg54 = null;
                            }
                            sendNotification(_arg055, _arg151, _arg239, _arg310, _arg417, _arg54);
                            reply.writeNoException();
                            return true;
                        case 53:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg07 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg07 = null;
                            }
                            disconnectAll(_arg07);
                            reply.writeNoException();
                            return true;
                        case 54:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg08 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg08 = null;
                            }
                            unregAll(_arg08);
                            reply.writeNoException();
                            return true;
                        case 55:
                            data.enforceInterface(DESCRIPTOR);
                            if (data.readInt() != 0) {
                                _arg09 = AttributionSource.CREATOR.createFromParcel(data);
                            } else {
                                _arg09 = null;
                            }
                            int _result2 = numHwTrackFiltersAvailable(_arg09);
                            reply.writeNoException();
                            reply.writeInt(_result2);
                            return true;
                        default:
                            return super.onTransact(code, data, reply, flags);
                    }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IBluetoothGatt {
            public static IBluetoothGatt sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.bluetooth.IBluetoothGatt
            public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(states);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDevicesMatchingConnectionStates(states, attributionSource);
                    }
                    _reply.readException();
                    List<BluetoothDevice> _result = _reply.createTypedArrayList(BluetoothDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void registerScanner(IScannerCallback callback, WorkSource workSource, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (workSource != null) {
                        _data.writeInt(1);
                        workSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerScanner(callback, workSource, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void unregisterScanner(int scannerId, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(scannerId);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterScanner(scannerId, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void startScan(int scannerId, ScanSettings settings, List<ScanFilter> filters, List scanStorages, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(scannerId);
                    if (settings != null) {
                        _data.writeInt(1);
                        settings.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeTypedList(filters);
                    _data.writeList(scanStorages);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startScan(scannerId, settings, filters, scanStorages, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void startScanForIntent(PendingIntent intent, ScanSettings settings, List<ScanFilter> filters, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (settings != null) {
                        _data.writeInt(1);
                        settings.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeTypedList(filters);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().startScanForIntent(intent, settings, filters, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void stopScanForIntent(PendingIntent intent, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopScanForIntent(intent, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void stopScan(int scannerId, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(scannerId);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopScan(scannerId, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void flushPendingBatchResults(int scannerId, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(scannerId);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().flushPendingBatchResults(scannerId, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void startAdvertisingSet(AdvertisingSetParameters parameters, AdvertiseData advertiseData, AdvertiseData scanResponse, PeriodicAdvertisingParameters periodicParameters, AdvertiseData periodicData, int duration, int maxExtAdvEvents, IAdvertisingSetCallback callback, AttributionSource attributionSource) throws RemoteException {
                Parcel _data;
                Parcel _reply;
                Throwable th;
                Parcel _data2;
                Parcel _reply2;
                Parcel _data3 = Parcel.obtain();
                Parcel _reply3 = Parcel.obtain();
                try {
                    _data3.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parameters != null) {
                        try {
                            _data3.writeInt(1);
                            parameters.writeToParcel(_data3, 0);
                        } catch (Throwable th2) {
                            th = th2;
                            _reply = _reply3;
                            _data = _data3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } else {
                        _data3.writeInt(0);
                    }
                    if (advertiseData != null) {
                        _data3.writeInt(1);
                        advertiseData.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    if (scanResponse != null) {
                        _data3.writeInt(1);
                        scanResponse.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    if (periodicParameters != null) {
                        _data3.writeInt(1);
                        periodicParameters.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    if (periodicData != null) {
                        _data3.writeInt(1);
                        periodicData.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    _data3.writeInt(duration);
                    _data3.writeInt(maxExtAdvEvents);
                    _data3.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (attributionSource != null) {
                        _data3.writeInt(1);
                        attributionSource.writeToParcel(_data3, 0);
                    } else {
                        _data3.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(9, _data3, _reply3, 0);
                    try {
                        if (_status) {
                            _reply2 = _reply3;
                            _data2 = _data3;
                        } else if (Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().startAdvertisingSet(parameters, advertiseData, scanResponse, periodicParameters, periodicData, duration, maxExtAdvEvents, callback, attributionSource);
                            _reply3.recycle();
                            _data3.recycle();
                            return;
                        } else {
                            _reply2 = _reply3;
                            _data2 = _data3;
                        }
                        _reply2.readException();
                        _reply2.recycle();
                        _data2.recycle();
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    _reply = _reply3;
                    _data = _data3;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void stopAdvertisingSet(IAdvertisingSetCallback callback, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().stopAdvertisingSet(callback, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void getOwnAddress(int advertiserId, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().getOwnAddress(advertiserId, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void enableAdvertisingSet(int advertiserId, boolean enable, int duration, int maxExtAdvEvents, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    _data.writeInt(enable ? 1 : 0);
                    _data.writeInt(duration);
                    _data.writeInt(maxExtAdvEvents);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().enableAdvertisingSet(advertiserId, enable, duration, maxExtAdvEvents, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void setAdvertisingData(int advertiserId, AdvertiseData data, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    if (data != null) {
                        _data.writeInt(1);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAdvertisingData(advertiserId, data, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void setScanResponseData(int advertiserId, AdvertiseData data, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    if (data != null) {
                        _data.writeInt(1);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setScanResponseData(advertiserId, data, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void setAdvertisingParameters(int advertiserId, AdvertisingSetParameters parameters, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    if (parameters != null) {
                        _data.writeInt(1);
                        parameters.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAdvertisingParameters(advertiserId, parameters, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void setPeriodicAdvertisingParameters(int advertiserId, PeriodicAdvertisingParameters parameters, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    if (parameters != null) {
                        _data.writeInt(1);
                        parameters.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setPeriodicAdvertisingParameters(advertiserId, parameters, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void setPeriodicAdvertisingData(int advertiserId, AdvertiseData data, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    if (data != null) {
                        _data.writeInt(1);
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setPeriodicAdvertisingData(advertiserId, data, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void setPeriodicAdvertisingEnable(int advertiserId, boolean enable, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(advertiserId);
                    _data.writeInt(enable ? 1 : 0);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setPeriodicAdvertisingEnable(advertiserId, enable, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void registerSync(ScanResult scanResult, int skip, int timeout, IPeriodicAdvertisingCallback callback, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (scanResult != null) {
                        _data.writeInt(1);
                        scanResult.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(skip);
                    _data.writeInt(timeout);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerSync(scanResult, skip, timeout, callback, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void unregisterSync(IPeriodicAdvertisingCallback callback, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterSync(callback, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void registerClient(ParcelUuid appId, IBluetoothGattCallback callback, boolean eatt_support, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (appId != null) {
                        _data.writeInt(1);
                        appId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(eatt_support ? 1 : 0);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerClient(appId, callback, eatt_support, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void unregisterClient(int clientIf, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterClient(clientIf, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void clientConnect(int clientIf, String address, boolean isDirect, int transport, boolean opportunistic, int phy, AttributionSource attributionSource) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(clientIf);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeString(address);
                    _data.writeInt(isDirect ? 1 : 0);
                    try {
                        _data.writeInt(transport);
                        _data.writeInt(opportunistic ? 1 : 0);
                        try {
                            _data.writeInt(phy);
                            if (attributionSource != null) {
                                _data.writeInt(1);
                                attributionSource.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                _reply.recycle();
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().clientConnect(clientIf, address, isDirect, transport, opportunistic, phy, attributionSource);
                            _reply.recycle();
                            _data.recycle();
                        } catch (Throwable th4) {
                            th = th4;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void clientDisconnect(int clientIf, String address, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    _data.writeString(address);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clientDisconnect(clientIf, address, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void clientSetPreferredPhy(int clientIf, String address, int txPhy, int rxPhy, int phyOptions, AttributionSource attributionSource) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(clientIf);
                        try {
                            _data.writeString(address);
                            try {
                                _data.writeInt(txPhy);
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
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
                try {
                    _data.writeInt(rxPhy);
                    try {
                        _data.writeInt(phyOptions);
                        if (attributionSource != null) {
                            _data.writeInt(1);
                            attributionSource.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().clientSetPreferredPhy(clientIf, address, txPhy, rxPhy, phyOptions, attributionSource);
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void clientReadPhy(int clientIf, String addres, AttributionSource attributionSources) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    _data.writeString(addres);
                    if (attributionSources != null) {
                        _data.writeInt(1);
                        attributionSources.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clientReadPhy(clientIf, addres, attributionSources);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void refreshDevice(int clientIf, String address, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    _data.writeString(address);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().refreshDevice(clientIf, address, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void discoverServices(int clientIf, String address, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    _data.writeString(address);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().discoverServices(clientIf, address, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void discoverServiceByUuid(int clientIf, String address, ParcelUuid uuid, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    _data.writeString(address);
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
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().discoverServiceByUuid(clientIf, address, uuid, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void readCharacteristic(int clientIf, String address, int handle, int authReq, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    _data.writeString(address);
                    _data.writeInt(handle);
                    _data.writeInt(authReq);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().readCharacteristic(clientIf, address, handle, authReq, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void readUsingCharacteristicUuid(int clientIf, String address, ParcelUuid uuid, int startHandle, int endHandle, int authReq, AttributionSource attributionSource) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(clientIf);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    _data.writeString(address);
                    if (uuid != null) {
                        _data.writeInt(1);
                        uuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    try {
                        _data.writeInt(startHandle);
                        _data.writeInt(endHandle);
                        _data.writeInt(authReq);
                        if (attributionSource != null) {
                            _data.writeInt(1);
                            attributionSource.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().readUsingCharacteristicUuid(clientIf, address, uuid, startHandle, endHandle, authReq, attributionSource);
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void writeCharacteristic(int clientIf, String address, int handle, int writeType, int authReq, byte[] value, AttributionSource attributionSource) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(clientIf);
                        try {
                            _data.writeString(address);
                            try {
                                _data.writeInt(handle);
                                try {
                                    _data.writeInt(writeType);
                                    _data.writeInt(authReq);
                                    _data.writeByteArray(value);
                                    if (attributionSource != null) {
                                        _data.writeInt(1);
                                        attributionSource.writeToParcel(_data, 0);
                                    } else {
                                        _data.writeInt(0);
                                    }
                                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        _reply.recycle();
                                        _data.recycle();
                                        return;
                                    }
                                    Stub.getDefaultImpl().writeCharacteristic(clientIf, address, handle, writeType, authReq, value, attributionSource);
                                    _reply.recycle();
                                    _data.recycle();
                                } catch (Throwable th2) {
                                    th = th2;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void readDescriptor(int clientIf, String address, int handle, int authReq, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    _data.writeString(address);
                    _data.writeInt(handle);
                    _data.writeInt(authReq);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().readDescriptor(clientIf, address, handle, authReq, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void writeDescriptor(int clientIf, String address, int handle, int authReq, byte[] value, AttributionSource attributionSource) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(clientIf);
                        try {
                            _data.writeString(address);
                            try {
                                _data.writeInt(handle);
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
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
                try {
                    _data.writeInt(authReq);
                    try {
                        _data.writeByteArray(value);
                        if (attributionSource != null) {
                            _data.writeInt(1);
                            attributionSource.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().writeDescriptor(clientIf, address, handle, authReq, value, attributionSource);
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void registerForNotification(int clientIf, String address, int handle, boolean enable, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    _data.writeString(address);
                    _data.writeInt(handle);
                    _data.writeInt(enable ? 1 : 0);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerForNotification(clientIf, address, handle, enable, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void beginReliableWrite(int clientIf, String address, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    _data.writeString(address);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().beginReliableWrite(clientIf, address, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void endReliableWrite(int clientIf, String address, boolean execute, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    _data.writeString(address);
                    _data.writeInt(execute ? 1 : 0);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().endReliableWrite(clientIf, address, execute, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void readRemoteRssi(int clientIf, String address, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    _data.writeString(address);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().readRemoteRssi(clientIf, address, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void configureMTU(int clientIf, String address, int mtu, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    _data.writeString(address);
                    _data.writeInt(mtu);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().configureMTU(clientIf, address, mtu, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void connectionParameterUpdate(int clientIf, String address, int connectionPriority, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    _data.writeString(address);
                    _data.writeInt(connectionPriority);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().connectionParameterUpdate(clientIf, address, connectionPriority, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void leConnectionUpdate(int clientIf, String address, int minInterval, int maxInterval, int peripheralLatency, int supervisionTimeout, int minConnectionEventLen, int maxConnectionEventLen, AttributionSource attributionSource) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(clientIf);
                        try {
                            _data.writeString(address);
                            _data.writeInt(minInterval);
                            _data.writeInt(maxInterval);
                            _data.writeInt(peripheralLatency);
                            _data.writeInt(supervisionTimeout);
                            _data.writeInt(minConnectionEventLen);
                            _data.writeInt(maxConnectionEventLen);
                            if (attributionSource != null) {
                                _data.writeInt(1);
                                attributionSource.writeToParcel(_data, 0);
                            } else {
                                _data.writeInt(0);
                            }
                            boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                            if (_status || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                _reply.recycle();
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().leConnectionUpdate(clientIf, address, minInterval, maxInterval, peripheralLatency, supervisionTimeout, minConnectionEventLen, maxConnectionEventLen, attributionSource);
                            _reply.recycle();
                            _data.recycle();
                        } catch (Throwable th2) {
                            th = th2;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void registerServer(ParcelUuid appId, IBluetoothGattServerCallback callback, boolean eatt_support, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (appId != null) {
                        _data.writeInt(1);
                        appId.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    _data.writeInt(eatt_support ? 1 : 0);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().registerServer(appId, callback, eatt_support, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void unregisterServer(int serverIf, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregisterServer(serverIf, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void serverConnect(int serverIf, String address, boolean isDirect, int transport, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    _data.writeString(address);
                    _data.writeInt(isDirect ? 1 : 0);
                    _data.writeInt(transport);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().serverConnect(serverIf, address, isDirect, transport, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void serverDisconnect(int serverIf, String address, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    _data.writeString(address);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().serverDisconnect(serverIf, address, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void serverSetPreferredPhy(int clientIf, String address, int txPhy, int rxPhy, int phyOptions, AttributionSource attributionSource) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(clientIf);
                        try {
                            _data.writeString(address);
                            try {
                                _data.writeInt(txPhy);
                            } catch (Throwable th2) {
                                th = th2;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
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
                try {
                    _data.writeInt(rxPhy);
                    try {
                        _data.writeInt(phyOptions);
                        if (attributionSource != null) {
                            _data.writeInt(1);
                            attributionSource.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                        if (_status || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().serverSetPreferredPhy(clientIf, address, txPhy, rxPhy, phyOptions, attributionSource);
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void serverReadPhy(int clientIf, String address, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clientIf);
                    _data.writeString(address);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().serverReadPhy(clientIf, address, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void addService(int serverIf, BluetoothGattService service, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    if (service != null) {
                        _data.writeInt(1);
                        service.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().addService(serverIf, service, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void removeService(int serverIf, int handle, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    _data.writeInt(handle);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().removeService(serverIf, handle, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void clearServices(int serverIf, AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serverIf);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().clearServices(serverIf, attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void sendResponse(int serverIf, String address, int requestId, int status, int offset, byte[] value, AttributionSource attributionSource) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(serverIf);
                        try {
                            _data.writeString(address);
                            try {
                                _data.writeInt(requestId);
                                try {
                                    _data.writeInt(status);
                                    _data.writeInt(offset);
                                    _data.writeByteArray(value);
                                    if (attributionSource != null) {
                                        _data.writeInt(1);
                                        attributionSource.writeToParcel(_data, 0);
                                    } else {
                                        _data.writeInt(0);
                                    }
                                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        _reply.recycle();
                                        _data.recycle();
                                        return;
                                    }
                                    Stub.getDefaultImpl().sendResponse(serverIf, address, requestId, status, offset, value, attributionSource);
                                    _reply.recycle();
                                    _data.recycle();
                                } catch (Throwable th2) {
                                    th = th2;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                _reply.recycle();
                                _data.recycle();
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void sendNotification(int serverIf, String address, int handle, boolean confirm, byte[] value, AttributionSource attributionSource) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    _data.writeInt(serverIf);
                    try {
                        _data.writeString(address);
                        try {
                            _data.writeInt(handle);
                            _data.writeInt(confirm ? 1 : 0);
                            try {
                                _data.writeByteArray(value);
                                if (attributionSource != null) {
                                    _data.writeInt(1);
                                    attributionSource.writeToParcel(_data, 0);
                                } else {
                                    _data.writeInt(0);
                                }
                                try {
                                    boolean _status = this.mRemote.transact(52, _data, _reply, 0);
                                    if (_status || Stub.getDefaultImpl() == null) {
                                        _reply.readException();
                                        _reply.recycle();
                                        _data.recycle();
                                        return;
                                    }
                                    Stub.getDefaultImpl().sendNotification(serverIf, address, handle, confirm, value, attributionSource);
                                    _reply.recycle();
                                    _data.recycle();
                                } catch (Throwable th3) {
                                    th = th3;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void disconnectAll(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().disconnectAll(attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public void unregAll(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(54, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().unregAll(attributionSource);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGatt
            public int numHwTrackFiltersAvailable(AttributionSource attributionSource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (attributionSource != null) {
                        _data.writeInt(1);
                        attributionSource.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(55, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().numHwTrackFiltersAvailable(attributionSource);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothGatt impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IBluetoothGatt getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
