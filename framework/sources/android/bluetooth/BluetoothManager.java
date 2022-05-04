package android.bluetooth;

import android.app.ActivityThread;
import android.app.AppGlobals;
import android.content.Attributable;
import android.content.AttributionSource;
import android.content.Context;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class BluetoothManager {
    private static final boolean DBG = false;
    private static final String TAG = "BluetoothManager";
    private final BluetoothAdapter mAdapter;
    private final AttributionSource mAttributionSource;

    public BluetoothManager(Context context) {
        AttributionSource resolveAttributionSource = resolveAttributionSource(context);
        this.mAttributionSource = resolveAttributionSource;
        this.mAdapter = BluetoothAdapter.createAdapter(resolveAttributionSource);
    }

    public static AttributionSource resolveAttributionSource(Context context) {
        AttributionSource res = null;
        if (context != null) {
            res = context.getAttributionSource();
        }
        if (res == null) {
            res = ActivityThread.currentAttributionSource();
        }
        if (res == null) {
            int uid = Process.myUid();
            if (uid == 0) {
                uid = 1000;
            }
            try {
                res = new AttributionSource(uid, AppGlobals.getPackageManager().getPackagesForUid(uid)[0], null);
            } catch (RemoteException e) {
            }
        }
        if (res != null) {
            return res;
        }
        throw new IllegalStateException("Failed to resolve AttributionSource");
    }

    public BluetoothAdapter getAdapter() {
        return this.mAdapter;
    }

    public int getConnectionState(BluetoothDevice device, int profile) {
        List<BluetoothDevice> connectedDevices = getConnectedDevices(profile);
        for (BluetoothDevice connectedDevice : connectedDevices) {
            if (device.equals(connectedDevice)) {
                return 2;
            }
        }
        return 0;
    }

    public List<BluetoothDevice> getConnectedDevices(int profile) {
        return getDevicesMatchingConnectionStates(profile, new int[]{2});
    }

    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int profile, int[] states) {
        if (profile == 7 || profile == 8) {
            List<BluetoothDevice> devices = new ArrayList<>();
            try {
                IBluetoothManager managerService = this.mAdapter.getBluetoothManager();
                IBluetoothGatt iGatt = managerService.getBluetoothGatt();
                return iGatt == null ? devices : Attributable.setAttributionSource(iGatt.getDevicesMatchingConnectionStates(states, this.mAttributionSource), this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
                return devices;
            }
        } else {
            throw new IllegalArgumentException("Profile not supported: " + profile);
        }
    }

    public BluetoothGattServer openGattServer(Context context, BluetoothGattServerCallback callback) {
        return openGattServer(context, callback, 0);
    }

    public BluetoothGattServer openGattServer(Context context, BluetoothGattServerCallback callback, boolean eatt_support) {
        return openGattServer(context, callback, 0, eatt_support);
    }

    public BluetoothGattServer openGattServer(Context context, BluetoothGattServerCallback callback, int transport) {
        return openGattServer(context, callback, transport, false);
    }

    public BluetoothGattServer openGattServer(Context context, BluetoothGattServerCallback callback, int transport, boolean eatt_support) {
        if (context == null || callback == null) {
            throw new IllegalArgumentException("null parameter: " + context + " " + callback);
        }
        try {
            IBluetoothManager managerService = this.mAdapter.getBluetoothManager();
            IBluetoothGatt iGatt = managerService.getBluetoothGatt();
            if (iGatt == null) {
                Log.e(TAG, "Fail to get GATT Server connection");
                return null;
            }
            BluetoothGattServer mGattServer = new BluetoothGattServer(iGatt, transport, this.mAdapter);
            Boolean regStatus = Boolean.valueOf(mGattServer.registerCallback(callback, eatt_support));
            if (regStatus.booleanValue()) {
                return mGattServer;
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "", e);
            return null;
        }
    }
}
