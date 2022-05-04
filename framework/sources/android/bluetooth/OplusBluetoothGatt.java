package android.bluetooth;

import android.bluetooth.OplusBluetoothAdapter;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class OplusBluetoothGatt {
    public static final String DESCRIPTOR = "android.bluetooth.BluetoothGatt";
    public static final int OPLUS_CALL_TRANSACTION_INDEX = 10000;
    public static final int OPLUS_GATT_FAST_MODE = 1;
    public static final int OPLUS_GATT_NORMAL_MODE = 2;
    private static final String TAG = "OplusBluetoothGatt";
    public static final int TRANSACTION_CLIENT_SET_CONNECT_MODE = 10001;
    public static final int TRANSACTION_REG_RSSI_DETECT_CALLBACK = 10002;
    public static final int TRANSACTION_UNREG_RSSI_DETECT_CALLBACK = 10003;
    private static final Map<OplusBluetoothRssiDetectCallback, OplusBluetoothAdapter.BleRssiDetectCallback> mRssiDetectCallbackList = new HashMap();

    static boolean oplusClientSetConnectMode(IBluetoothGatt bluetoothGatt, String address, int mode) throws RemoteException {
        boolean result = false;
        if (bluetoothGatt == null) {
            Log.w(TAG, "oplusClientSetConnectMode bluetoothGatt is null!");
            return false;
        } else if (address == null) {
            Log.w(TAG, "oplusClientSetConnectMode address is null!");
            return false;
        } else {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                data.writeString(address);
                data.writeInt(mode);
                bluetoothGatt.asBinder().transact(10001, data, reply, 0);
                reply.readException();
                if (reply.readInt() != 0) {
                    result = true;
                }
                return result;
            } finally {
                reply.recycle();
                data.recycle();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean oplusClientSetFastConnectMode(IBluetoothGatt bluetoothGatt, String address) throws RemoteException {
        return oplusClientSetConnectMode(bluetoothGatt, address, 1);
    }

    static boolean setOplusBluetoothRssiDetectCallback(IBluetoothGatt bluetoothGatt, OplusBluetoothAdapter.BleRssiDetectCallback iCallback, boolean reg, String packageName) throws RemoteException {
        boolean result = false;
        if (bluetoothGatt == null) {
            Log.w(TAG, "setOplusBluetoothRssiDetectCallback bluetoothGatt is null!");
            return false;
        } else if (iCallback == null) {
            Log.w(TAG, "iCallback is null!");
            return false;
        } else if (packageName == null) {
            Log.w(TAG, "packageName is null!");
            return false;
        } else {
            Log.d(TAG, "setOplusBluetoothRssiDetectCallback(): reg = " + reg);
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                data.writeStrongBinder(iCallback);
                data.writeString(packageName);
                bluetoothGatt.asBinder().transact(reg ? 10002 : 10003, data, reply, 0);
                reply.readException();
                if (reply.readInt() != 0) {
                    result = true;
                }
                return result;
            } finally {
                reply.recycle();
                data.recycle();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean registerOplusBluetoothRssiDetectCallback(IBluetoothGatt bluetoothGatt, OplusBluetoothAdapter.BleRssiDetectCallback callback, String packageName) throws RemoteException {
        if (callback == null) {
            return false;
        }
        Map<OplusBluetoothRssiDetectCallback, OplusBluetoothAdapter.BleRssiDetectCallback> map = mRssiDetectCallbackList;
        if (map.containsKey(callback.getCallback())) {
            return true;
        }
        map.put(callback.getCallback(), callback);
        return setOplusBluetoothRssiDetectCallback(bluetoothGatt, callback, true, packageName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean unregisterOplusBluetoothRssiDetectCallback(IBluetoothGatt bluetoothGatt, OplusBluetoothAdapter.BleRssiDetectCallback callback, String packageName) throws RemoteException {
        if (callback == null) {
            return false;
        }
        mRssiDetectCallbackList.remove(callback.getCallback());
        return setOplusBluetoothRssiDetectCallback(bluetoothGatt, callback, false, packageName);
    }
}
