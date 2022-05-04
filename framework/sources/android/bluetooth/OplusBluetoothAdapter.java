package android.bluetooth;

import android.app.ActivityThread;
import android.bluetooth.IBluetoothManagerCallback;
import android.bluetooth.le.ScanResult;
import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes.dex */
public final class OplusBluetoothAdapter {
    public static final String DESCRIPTOR = "android.bluetooth.IBluetooth";
    public static final int OPLUS_CALL_TRANSACTION_INDEX = 10000;
    public static final int OPLUS_FIRST_CALL_TRANSACTION = 10001;
    public static final String TAG = "OplusBluetoothAdapter";
    public static final int TRANSACTION_ADD_CARKIT_DEVICE = 10012;
    public static final int TRANSACTION_DISABLE_AUTO_CONNECT_POLICY = 10007;
    public static final int TRANSACTION_ENABLE_AUTO_CONNECT_POLICY = 10006;
    public static final int TRANSACTION_GET_BINAURAL_CONNECTION_STATE = 10021;
    public static final int TRANSACTION_GET_BLUETOOTH_CONNECTED_APP = 10005;
    public static final int TRANSACTION_GET_BLUETOOTH_CONNECTION_COUNT = 10004;
    public static final int TRANSACTION_GET_RANDOM_ADDRESS = 10022;
    public static final int TRANSACTION_IS_CARKIT_DEVICE = 10011;
    public static final int TRANSACTION_REGISTER_BLUETOOTH_CALLBACK = 10015;
    public static final int TRANSACTION_REMOVE_CARKIT_DEVICE = 10013;
    public static final int TRANSACTION_SCO_AVAILABLE_OFFCALL = 10003;
    public static final int TRANSACTION_SETBLBLACKORWHITELIST = 10002;
    public static final int TRANSACTION_SET_MODE = 10017;
    public static final int TRANSACTION_SET_SPEAKERPHONE_ON = 10018;
    public static final int TRANSACTION_START_BLUETOOTH_SCO = 10019;
    public static final int TRANSACTION_STOP_BLUETOOTH_SCO = 10020;
    public static final int TRANSACTION_TRIGGER_FIRMWARE_CRASH = 10014;
    public static final int TRANSACTION_UNREGISTER_BLUETOOTH_CALLBACK = 10016;
    private static OplusBluetoothAdapter sAdapter;
    private final BluetoothAdapter mBluetoothAdapter;
    private IBluetooth mService;
    private final ReentrantReadWriteLock mServiceLock = new ReentrantReadWriteLock();
    private IBluetoothManagerCallback mStateChangeCallback = new IBluetoothManagerCallback.Stub() { // from class: android.bluetooth.OplusBluetoothAdapter.1
        /* JADX WARN: Finally extract failed */
        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBluetoothServiceUp(IBluetooth bluetoothService) throws RemoteException {
            try {
                OplusBluetoothAdapter.this.mServiceLock.writeLock().lock();
                if (OplusBluetoothAdapter.this.mService != null) {
                    Log.w(OplusBluetoothAdapter.TAG, "mService is not NULL");
                }
                OplusBluetoothAdapter.this.mService = bluetoothService;
                OplusBluetoothAdapter.this.mServiceLock.writeLock().unlock();
                synchronized (OplusBluetoothAdapter.this.mServiceLifecycleCallbacks) {
                    for (OplusServiceLifecycleCallback cb : OplusBluetoothAdapter.this.mServiceLifecycleCallbacks) {
                        if (cb != null) {
                            cb.onBluetoothServiceUp();
                        }
                    }
                }
            } catch (Throwable th) {
                OplusBluetoothAdapter.this.mServiceLock.writeLock().unlock();
                throw th;
            }
        }

        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBluetoothServiceDown() throws RemoteException {
            synchronized (OplusBluetoothAdapter.this.mServiceLifecycleCallbacks) {
                for (OplusServiceLifecycleCallback cb : OplusBluetoothAdapter.this.mServiceLifecycleCallbacks) {
                    if (cb != null) {
                        cb.onBluetoothServiceDown();
                    }
                }
            }
            try {
                OplusBluetoothAdapter.this.mServiceLock.writeLock().lock();
                OplusBluetoothAdapter.this.mService = null;
            } finally {
                OplusBluetoothAdapter.this.mServiceLock.writeLock().unlock();
            }
        }

        @Override // android.bluetooth.IBluetoothManagerCallback
        public void onBrEdrDown() throws RemoteException {
            Log.d(OplusBluetoothAdapter.TAG, "onBrEdrDown: reached BLE ON state");
        }
    };
    private final List<OplusServiceLifecycleCallback> mServiceLifecycleCallbacks = new ArrayList();

    /* loaded from: classes.dex */
    public static abstract class OplusServiceLifecycleCallback {
        public abstract void onBluetoothServiceDown();

        public abstract void onBluetoothServiceUp();
    }

    public static synchronized OplusBluetoothAdapter getOplusBluetoothAdapter() {
        OplusBluetoothAdapter oplusBluetoothAdapter;
        synchronized (OplusBluetoothAdapter.class) {
            if (sAdapter == null) {
                sAdapter = createOplusAdapter();
            }
            oplusBluetoothAdapter = sAdapter;
        }
        return oplusBluetoothAdapter;
    }

    OplusBluetoothAdapter(BluetoothAdapter adapter) {
        Objects.requireNonNull(adapter);
        this.mBluetoothAdapter = adapter;
        getService();
    }

    private static OplusBluetoothAdapter createOplusAdapter() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter != null) {
            return new OplusBluetoothAdapter(adapter);
        }
        Log.e(TAG, "Bluetooth Adapter is null");
        return null;
    }

    /* JADX WARN: Finally extract failed */
    private IBluetooth getService() {
        try {
            this.mServiceLock.writeLock().lock();
            IBluetooth tService = this.mBluetoothAdapter.getBluetoothService(this.mStateChangeCallback);
            if (this.mService == null) {
                this.mService = tService;
            }
            this.mServiceLock.writeLock().unlock();
            return this.mService;
        } catch (Throwable th) {
            this.mServiceLock.writeLock().unlock();
            throw th;
        }
    }

    public boolean oplusRegisterServiceLifecycleCallback(OplusServiceLifecycleCallback callback) {
        if (callback == null) {
            return false;
        }
        synchronized (this.mServiceLifecycleCallbacks) {
            this.mServiceLifecycleCallbacks.add(callback);
        }
        return true;
    }

    public void oplusUnregisterServiceLifecycleCallback(OplusServiceLifecycleCallback callback) {
        if (callback != null) {
            synchronized (this.mServiceLifecycleCallbacks) {
                this.mServiceLifecycleCallbacks.remove(callback);
            }
        }
    }

    public void setBLBlackOrWhiteList(List<String> addressList, int btCustomizeMode, boolean enable) throws RemoteException {
        if (this.mService == null) {
            Log.w(TAG, "OplusBluetoothAdapter setBLBlackOrWhiteList null!");
            return;
        }
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            this.mServiceLock.readLock().lock();
            if (this.mService != null) {
                data.writeInterfaceToken("android.bluetooth.IBluetooth");
                data.writeStringList(addressList);
                data.writeInt(btCustomizeMode);
                data.writeInt(enable ? 1 : 0);
                this.mService.asBinder().transact(10002, data, reply, 0);
                reply.readException();
            }
        } finally {
            reply.recycle();
            data.recycle();
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean isBluetoothScoAvailableOffCall() throws RemoteException {
        boolean result = true;
        result = true;
        if (this.mService == null) {
            Log.w(TAG, "OplusBluetoothAdapter isBluetoothScoAvailableOffCall null!");
            return true;
        }
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            this.mServiceLock.readLock().lock();
            if (this.mService != null) {
                data.writeInterfaceToken("android.bluetooth.IBluetooth");
                boolean status = this.mService.asBinder().transact(10003, data, reply, 0);
                reply.readException();
                if (status) {
                    if (reply.readInt() == 0) {
                        result = false;
                    }
                }
            }
            return result;
        } finally {
            data.recycle();
            reply.recycle();
            this.mServiceLock.readLock().unlock();
        }
    }

    public int getBluetoothConnectionCount() throws RemoteException {
        if (this.mService == null) {
            Log.w(TAG, "OplusBluetoothAdapter getBluetoothConnectionCount null!");
            return 0;
        }
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        int result = 0;
        try {
            this.mServiceLock.readLock().lock();
            if (this.mService != null) {
                data.writeInterfaceToken("android.bluetooth.IBluetooth");
                this.mService.asBinder().transact(10004, data, reply, 0);
                reply.readException();
                result = reply.readInt();
            }
            return result;
        } finally {
            data.recycle();
            reply.recycle();
            this.mServiceLock.readLock().unlock();
        }
    }

    public int[] getBluetoothConnectedAppPID() throws RemoteException {
        int[] result = null;
        if (this.mService == null) {
            Log.w(TAG, "OplusBluetoothAdapter getBluetoothConnectedAppPID null!");
            return null;
        }
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            this.mServiceLock.readLock().lock();
            if (this.mService != null) {
                data.writeInterfaceToken("android.bluetooth.IBluetooth");
                this.mService.asBinder().transact(10005, data, reply, 0);
                reply.readException();
                int length = reply.readInt();
                result = new int[length];
                reply.readIntArray(result);
            }
            return result;
        } finally {
            data.recycle();
            reply.recycle();
            this.mServiceLock.readLock().unlock();
        }
    }

    public void oplusEnableAutoConnectPolicy(BluetoothDevice device) throws RemoteException {
        if (this.mService != null && device != null) {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    data.writeString(device.getAddress());
                    this.mService.asBinder().transact(10006, data, reply, 0);
                    reply.readException();
                }
            } finally {
                data.recycle();
                reply.recycle();
                this.mServiceLock.readLock().unlock();
            }
        }
    }

    public void oplusDisableAutoConnectPolicy(BluetoothDevice device) throws RemoteException {
        if (this.mService != null && device != null) {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    data.writeString(device.getAddress());
                    this.mService.asBinder().transact(10007, data, reply, 0);
                    reply.readException();
                }
            } finally {
                data.recycle();
                reply.recycle();
                this.mServiceLock.readLock().unlock();
            }
        }
    }

    public void oplusTriggerFirmwareCrash() throws RemoteException {
        Log.d(TAG, "oplusTriggerFirmwareCrash() call by:" + ActivityThread.currentPackageName());
        if (this.mService == null) {
            Log.w(TAG, "oplusBluetoothAdapter oplusTriggerFirmwareCrash null!");
            return;
        }
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            this.mServiceLock.readLock().lock();
            if (this.mService != null) {
                data.writeInterfaceToken("android.bluetooth.IBluetooth");
                data.writeString(ActivityThread.currentPackageName());
                this.mService.asBinder().transact(10014, data, reply, 0);
                reply.readException();
            }
        } finally {
            data.recycle();
            reply.recycle();
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean oplusIsCarkit(BluetoothDevice device) throws RemoteException {
        boolean result = false;
        if (this.mService == null || device == null) {
            Log.w(TAG, "oplusBluetoothAdapter oplusIsCarkit null!");
            return false;
        }
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        result = false;
        try {
            this.mServiceLock.readLock().lock();
            if (this.mService != null) {
                data.writeInterfaceToken("android.bluetooth.IBluetooth");
                data.writeString(device.getAddress());
                boolean status = this.mService.asBinder().transact(10011, data, reply, 0);
                reply.readException();
                if (status) {
                    if (reply.readInt() != 0) {
                        result = true;
                    }
                }
            }
            return result;
        } finally {
            data.recycle();
            reply.recycle();
            this.mServiceLock.readLock().unlock();
        }
    }

    public void oplusAddCarkit(BluetoothDevice device) throws RemoteException {
        if (this.mService == null || device == null) {
            Log.w(TAG, "oplusBluetoothAdapter oplusAddCarkit null!");
            return;
        }
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            this.mServiceLock.readLock().lock();
            if (this.mService != null) {
                data.writeInterfaceToken("android.bluetooth.IBluetooth");
                data.writeString(device.getAddress());
                this.mService.asBinder().transact(10012, data, reply, 0);
                reply.readException();
            }
        } finally {
            data.recycle();
            reply.recycle();
            this.mServiceLock.readLock().unlock();
        }
    }

    public void oplusRemoveCarkit(BluetoothDevice device) throws RemoteException {
        if (this.mService == null || device == null) {
            Log.w(TAG, "oplusBluetoothAdapter oplusAddCarkit null!");
            return;
        }
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            this.mServiceLock.readLock().lock();
            if (this.mService != null) {
                data.writeInterfaceToken("android.bluetooth.IBluetooth");
                data.writeString(device.getAddress());
                this.mService.asBinder().transact(10013, data, reply, 0);
                reply.readException();
            }
        } finally {
            data.recycle();
            reply.recycle();
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean registerOplusBluetoothCallback(OplusBluetoothExtendCallback iCallback, int code) {
        boolean ret = false;
        if (this.mService == null || iCallback == null) {
            Log.w(TAG, "OplusBluetoothExtendCallback register null!");
            return false;
        }
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    data.writeStrongBinder(iCallback);
                    data.writeInt(code);
                    this.mService.asBinder().transact(10015, data, reply, 0);
                    reply.readException();
                    ret = true;
                }
            } catch (RemoteException e) {
                ret = false;
                Log.e(TAG, "", e);
            }
            return ret;
        } finally {
            data.recycle();
            reply.recycle();
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean unRegisterOplusBluetoothCallback(OplusBluetoothExtendCallback iCallback, int code) {
        boolean ret = false;
        if (this.mService == null || iCallback == null) {
            Log.w(TAG, "OplusBluetoothExtendCallback unregister null!");
            return false;
        }
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            try {
                this.mServiceLock.readLock().lock();
                if (this.mService != null) {
                    data.writeInterfaceToken("android.bluetooth.IBluetooth");
                    data.writeStrongBinder(iCallback);
                    data.writeInt(code);
                    this.mService.asBinder().transact(10016, data, reply, 0);
                    reply.readException();
                    ret = true;
                }
            } catch (RemoteException e) {
                ret = false;
                Log.e(TAG, "", e);
            }
            return ret;
        } finally {
            data.recycle();
            reply.recycle();
            this.mServiceLock.readLock().unlock();
        }
    }

    public boolean registerOplusBluetoothRssiDetectCallback(OplusBluetoothRssiDetectCallback callback) {
        if (callback == null) {
            Log.w(TAG, "callback null!");
            return false;
        }
        String packageName = ActivityThread.currentPackageName();
        BleRssiDetectCallback bleRssiDetectCallback = new BleRssiDetectCallback(callback);
        IBluetoothManager managerService = this.mBluetoothAdapter.getBluetoothManager();
        try {
            IBluetoothGatt iGatt = managerService.getBluetoothGatt();
            if (iGatt == null) {
                return false;
            }
            return OplusBluetoothGatt.registerOplusBluetoothRssiDetectCallback(iGatt, bleRssiDetectCallback, packageName);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    public boolean unregisterOplusBluetoothRssiDetectCallback(OplusBluetoothRssiDetectCallback callback) {
        if (callback == null) {
            Log.w(TAG, "callback null!");
            return false;
        }
        String packageName = ActivityThread.currentPackageName();
        BleRssiDetectCallback bleRssiDetectCallback = new BleRssiDetectCallback(callback);
        IBluetoothManager managerService = this.mBluetoothAdapter.getBluetoothManager();
        try {
            IBluetoothGatt iGatt = managerService.getBluetoothGatt();
            if (iGatt == null) {
                return false;
            }
            return OplusBluetoothGatt.unregisterOplusBluetoothRssiDetectCallback(iGatt, bleRssiDetectCallback, packageName);
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return false;
        }
    }

    /* loaded from: classes.dex */
    public class BleRssiDetectCallback extends OplusBluetoothExtendCallback {
        private OplusBluetoothRssiDetectCallback mCallback;

        public BleRssiDetectCallback(OplusBluetoothRssiDetectCallback callback) {
            this.mCallback = callback;
        }

        @Override // android.bluetooth.OplusBluetoothExtendCallback
        public void oplusBluetoothExtendCb(int code, Parcel data) {
            ScanResult scanResult;
            if (code == 200001) {
                float modifyRssi = 0.0f;
                try {
                    scanResult = (ScanResult) data.readParcelable(ScanResult.class.getClassLoader());
                    modifyRssi = data.readFloat();
                } catch (BadParcelableException e) {
                    scanResult = null;
                }
                if (scanResult != null) {
                    this.mCallback.onRssiDetectResultCallback(scanResult, modifyRssi);
                }
            }
        }

        public OplusBluetoothRssiDetectCallback getCallback() {
            return this.mCallback;
        }
    }

    public void oplusSetMode(int mode, int callingPid, String callingPackage) {
        if (this.mService != null) {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                try {
                    this.mServiceLock.readLock().lock();
                    if (this.mService != null) {
                        data.writeInterfaceToken("android.bluetooth.IBluetooth");
                        data.writeInt(mode);
                        data.writeInt(callingPid);
                        data.writeString(callingPackage);
                        this.mService.asBinder().transact(10017, data, reply, 0);
                        reply.readException();
                    }
                } catch (RemoteException e) {
                    Log.w(TAG, "oplusSetMode fail");
                }
            } finally {
                data.recycle();
                reply.recycle();
                this.mServiceLock.readLock().unlock();
            }
        }
    }

    public boolean isBluetoothRecordConnected() throws RemoteException {
        boolean result = false;
        if (this.mService == null) {
            Log.w(TAG, "oplusBluetoothAdapter isBluetoothRecordConnected null!");
            return false;
        }
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        result = false;
        try {
            this.mServiceLock.readLock().lock();
            if (this.mService != null) {
                data.writeInterfaceToken("android.bluetooth.IBluetooth");
                this.mService.asBinder().transact(10021, data, reply, 0);
                reply.readException();
                if (reply.readInt() != 0) {
                    result = true;
                }
            }
            return result;
        } finally {
            data.recycle();
            reply.recycle();
            this.mServiceLock.readLock().unlock();
        }
    }

    public void oplusSetSpeakerphoneOn(boolean on, int callingPid, String callingPackage) {
        if (this.mService != null) {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                try {
                    this.mServiceLock.readLock().lock();
                    if (this.mService != null) {
                        data.writeInterfaceToken("android.bluetooth.IBluetooth");
                        data.writeBoolean(on);
                        data.writeInt(callingPid);
                        data.writeString(callingPackage);
                        this.mService.asBinder().transact(10018, data, reply, 0);
                        reply.readException();
                    }
                } catch (RemoteException e) {
                    Log.w(TAG, "oplusSetSpeakerphoneOn fail");
                }
            } finally {
                data.recycle();
                reply.recycle();
                this.mServiceLock.readLock().unlock();
            }
        }
    }

    public void oplusStartBluetoothSco(int callingPid, String callingPackage) {
        if (this.mService != null) {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                try {
                    this.mServiceLock.readLock().lock();
                    if (this.mService != null) {
                        data.writeInterfaceToken("android.bluetooth.IBluetooth");
                        data.writeInt(callingPid);
                        data.writeString(callingPackage);
                        this.mService.asBinder().transact(10019, data, reply, 0);
                        reply.readException();
                    }
                } catch (RemoteException e) {
                    Log.w(TAG, "oplusStartBluetoothSco fail");
                }
            } finally {
                data.recycle();
                reply.recycle();
                this.mServiceLock.readLock().unlock();
            }
        }
    }

    public void oplusStopBluetoothSco(int callingPid, String callingPackage) {
        if (this.mService != null) {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                try {
                    this.mServiceLock.readLock().lock();
                    if (this.mService != null) {
                        data.writeInterfaceToken("android.bluetooth.IBluetooth");
                        data.writeInt(callingPid);
                        data.writeString(callingPackage);
                        this.mService.asBinder().transact(10020, data, reply, 0);
                        reply.readException();
                    }
                } catch (RemoteException e) {
                    Log.w(TAG, "oplusStopBluetoothSco fail");
                }
            } finally {
                data.recycle();
                reply.recycle();
                this.mServiceLock.readLock().unlock();
            }
        }
    }

    public String oplusGetRandomAddress() throws RemoteException {
        if (this.mService == null) {
            Log.w(TAG, "oplusBluetoothAdapter is null!");
            return null;
        }
        Parcel data = Parcel.obtain();
        Parcel reply = Parcel.obtain();
        try {
            data.writeInterfaceToken("android.bluetooth.IBluetooth");
            this.mService.asBinder().transact(10022, data, reply, 0);
            reply.readException();
            String address = reply.readString();
            return address;
        } finally {
            data.recycle();
            reply.recycle();
        }
    }
}
