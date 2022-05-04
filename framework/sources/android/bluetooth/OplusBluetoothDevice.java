package android.bluetooth;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
/* loaded from: classes.dex */
public final class OplusBluetoothDevice {
    public static final String TAG = "OplusBluetoothDevice";
    private BluetoothDevice mBluetoothDevice;

    public OplusBluetoothDevice(BluetoothDevice device) {
        this.mBluetoothDevice = null;
        this.mBluetoothDevice = device;
    }

    public BluetoothGatt connectGatt(Context context, boolean autoConnect, BluetoothGattCallback callback, boolean fastConnect) {
        return connectGatt(context, autoConnect, callback, 0, fastConnect);
    }

    public BluetoothGatt connectGatt(Context context, boolean autoConnect, BluetoothGattCallback callback, int transport, boolean fastConnect) {
        return connectGatt(context, autoConnect, callback, transport, 1, fastConnect);
    }

    public BluetoothGatt connectGatt(Context context, boolean autoConnect, BluetoothGattCallback callback, int transport, int phy, boolean fastConnect) {
        return connectGatt(context, autoConnect, callback, transport, phy, null, fastConnect);
    }

    public BluetoothGatt connectGatt(Context context, boolean autoConnect, BluetoothGattCallback callback, int transport, int phy, Handler handler, boolean fastConnect) {
        return connectGatt(context, autoConnect, callback, transport, false, phy, handler, fastConnect);
    }

    public BluetoothGatt connectGatt(Context context, boolean autoConnect, BluetoothGattCallback callback, int transport, boolean opportunistic, int phy, Handler handler, boolean fastConnect) {
        RemoteException e;
        BluetoothDevice bluetoothDevice;
        if (callback != null) {
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            IBluetoothManager managerService = adapter.getBluetoothManager();
            try {
                IBluetoothGatt iGatt = managerService.getBluetoothGatt();
                if (iGatt == null || (bluetoothDevice = this.mBluetoothDevice) == null) {
                    return null;
                }
                BluetoothGatt gatt = new BluetoothGatt(iGatt, bluetoothDevice, transport, opportunistic, phy, context.getAttributionSource());
                if (!autoConnect && fastConnect) {
                    boolean result = OplusBluetoothGatt.oplusClientSetFastConnectMode(iGatt, this.mBluetoothDevice.getAddress());
                    Log.d(TAG, "setConnectMode " + result);
                }
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
            }
        } else {
            throw new NullPointerException("callback is null");
        }
    }
}
