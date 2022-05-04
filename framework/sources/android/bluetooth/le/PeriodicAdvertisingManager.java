package android.bluetooth.le;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.IBluetoothGatt;
import android.bluetooth.IBluetoothManager;
import android.bluetooth.le.IPeriodicAdvertisingCallback;
import android.content.Attributable;
import android.content.AttributionSource;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes.dex */
public final class PeriodicAdvertisingManager {
    private static final int SKIP_MAX = 499;
    private static final int SKIP_MIN = 0;
    private static final int SYNC_STARTING = -1;
    private static final String TAG = "PeriodicAdvertisingManager";
    private static final int TIMEOUT_MAX = 16384;
    private static final int TIMEOUT_MIN = 10;
    private final AttributionSource mAttributionSource;
    private final BluetoothAdapter mBluetoothAdapter;
    private final IBluetoothManager mBluetoothManager;
    Map<PeriodicAdvertisingCallback, IPeriodicAdvertisingCallback> mCallbackWrappers = new IdentityHashMap();

    public PeriodicAdvertisingManager(BluetoothAdapter bluetoothAdapter) {
        Objects.requireNonNull(bluetoothAdapter);
        BluetoothAdapter bluetoothAdapter2 = bluetoothAdapter;
        this.mBluetoothAdapter = bluetoothAdapter2;
        this.mBluetoothManager = bluetoothAdapter2.getBluetoothManager();
        this.mAttributionSource = bluetoothAdapter2.getAttributionSource();
    }

    public void registerSync(ScanResult scanResult, int skip, int timeout, PeriodicAdvertisingCallback callback) {
        registerSync(scanResult, skip, timeout, callback, null);
    }

    public void registerSync(ScanResult scanResult, int skip, int timeout, PeriodicAdvertisingCallback callback, Handler handler) {
        if (callback == null) {
            throw new IllegalArgumentException("callback can't be null");
        } else if (scanResult == null) {
            throw new IllegalArgumentException("scanResult can't be null");
        } else if (scanResult.getAdvertisingSid() == 255) {
            throw new IllegalArgumentException("scanResult must contain a valid sid");
        } else if (skip < 0 || skip > SKIP_MAX) {
            throw new IllegalArgumentException("timeout must be between 10 and 16384");
        } else if (timeout < 10 || timeout > 16384) {
            throw new IllegalArgumentException("timeout must be between 10 and 16384");
        } else {
            try {
                IBluetoothGatt gatt = this.mBluetoothManager.getBluetoothGatt();
                if (handler == null) {
                    handler = new Handler(Looper.getMainLooper());
                }
                IPeriodicAdvertisingCallback wrapped = wrap(callback, handler);
                this.mCallbackWrappers.put(callback, wrapped);
                try {
                    gatt.registerSync(scanResult, skip, timeout, wrapped, this.mAttributionSource);
                } catch (RemoteException e) {
                    Log.e(TAG, "Failed to register sync - ", e);
                }
            } catch (RemoteException e2) {
                Log.e(TAG, "Failed to get Bluetooth gatt - ", e2);
                callback.onSyncEstablished(0, scanResult.getDevice(), scanResult.getAdvertisingSid(), skip, timeout, 2);
            }
        }
    }

    public void unregisterSync(PeriodicAdvertisingCallback callback) {
        if (callback != null) {
            try {
                IBluetoothGatt gatt = this.mBluetoothManager.getBluetoothGatt();
                IPeriodicAdvertisingCallback wrapper = this.mCallbackWrappers.remove(callback);
                if (wrapper != null) {
                    try {
                        gatt.unregisterSync(wrapper, this.mAttributionSource);
                    } catch (RemoteException e) {
                        Log.e(TAG, "Failed to cancel sync creation - ", e);
                    }
                } else {
                    throw new IllegalArgumentException("callback was not properly registered");
                }
            } catch (RemoteException e2) {
                Log.e(TAG, "Failed to get Bluetooth gatt - ", e2);
            }
        } else {
            throw new IllegalArgumentException("callback can't be null");
        }
    }

    private IPeriodicAdvertisingCallback wrap(final PeriodicAdvertisingCallback callback, final Handler handler) {
        return new IPeriodicAdvertisingCallback.Stub() { // from class: android.bluetooth.le.PeriodicAdvertisingManager.1
            @Override // android.bluetooth.le.IPeriodicAdvertisingCallback
            public void onSyncEstablished(final int syncHandle, final BluetoothDevice device, final int advertisingSid, final int skip, final int timeout, final int status) {
                Attributable.setAttributionSource(device, PeriodicAdvertisingManager.this.mAttributionSource);
                handler.post(new Runnable() { // from class: android.bluetooth.le.PeriodicAdvertisingManager.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        callback.onSyncEstablished(syncHandle, device, advertisingSid, skip, timeout, status);
                        if (status != 0) {
                            PeriodicAdvertisingManager.this.mCallbackWrappers.remove(callback);
                        }
                    }
                });
            }

            @Override // android.bluetooth.le.IPeriodicAdvertisingCallback
            public void onPeriodicAdvertisingReport(final PeriodicAdvertisingReport report) {
                handler.post(new Runnable() { // from class: android.bluetooth.le.PeriodicAdvertisingManager.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        callback.onPeriodicAdvertisingReport(report);
                    }
                });
            }

            @Override // android.bluetooth.le.IPeriodicAdvertisingCallback
            public void onSyncLost(final int syncHandle) {
                handler.post(new Runnable() { // from class: android.bluetooth.le.PeriodicAdvertisingManager.1.3
                    @Override // java.lang.Runnable
                    public void run() {
                        callback.onSyncLost(syncHandle);
                        PeriodicAdvertisingManager.this.mCallbackWrappers.remove(callback);
                    }
                });
            }
        };
    }
}
