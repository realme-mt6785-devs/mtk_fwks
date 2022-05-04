package android.bluetooth;

import android.Manifest;
import android.annotation.SystemApi;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothPbap;
import android.bluetooth.IBluetoothStateChangeCallback;
import android.content.Attributable;
import android.content.AttributionSource;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@SystemApi
/* loaded from: classes.dex */
public class BluetoothPbap implements BluetoothProfile {
    @SystemApi
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.pbap.profile.action.CONNECTION_STATE_CHANGED";
    private static final boolean DBG = false;
    public static final int RESULT_CANCELED = 2;
    public static final int RESULT_FAILURE = 0;
    public static final int RESULT_SUCCESS = 1;
    private static final String TAG = "BluetoothPbap";
    private final BluetoothAdapter mAdapter;
    private final AttributionSource mAttributionSource;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback;
    private final ServiceConnection mConnection = new ServiceConnection() { // from class: android.bluetooth.BluetoothPbap.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName className, IBinder service) {
            BluetoothPbap.log("Proxy object connected");
            BluetoothPbap.this.mService = IBluetoothPbap.Stub.asInterface(service);
            if (BluetoothPbap.this.mServiceListener != null) {
                BluetoothPbap.this.mServiceListener.onServiceConnected(6, BluetoothPbap.this);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName className) {
            BluetoothPbap.log("Proxy object disconnected");
            BluetoothPbap.this.doUnbind();
            if (BluetoothPbap.this.mServiceListener != null) {
                BluetoothPbap.this.mServiceListener.onServiceDisconnected(6);
            }
        }
    };
    private final Context mContext;
    private volatile IBluetoothPbap mService;
    private BluetoothProfile.ServiceListener mServiceListener;

    public BluetoothPbap(Context context, BluetoothProfile.ServiceListener l, BluetoothAdapter adapter) {
        IBluetoothStateChangeCallback.Stub stub = new IBluetoothStateChangeCallback.Stub() { // from class: android.bluetooth.BluetoothPbap.1
            @Override // android.bluetooth.IBluetoothStateChangeCallback
            public void onBluetoothStateChange(boolean up) {
                BluetoothPbap.log("onBluetoothStateChange: up=" + up);
                if (!up) {
                    BluetoothPbap.this.doUnbind();
                } else {
                    BluetoothPbap.this.doBind();
                }
            }
        };
        this.mBluetoothStateChangeCallback = stub;
        this.mContext = context;
        this.mServiceListener = l;
        this.mAdapter = adapter;
        this.mAttributionSource = adapter.getAttributionSource();
        if (context.getApplicationInfo().targetSdkVersion > 30 || context.checkSelfPermission(Manifest.permission.BLUETOOTH) == 0) {
            IBluetoothManager mgr = adapter.getBluetoothManager();
            if (mgr != null) {
                try {
                    mgr.registerStateChangeCallback(stub);
                } catch (RemoteException re) {
                    Log.e(TAG, "", re);
                }
            }
            doBind();
            return;
        }
        throw new SecurityException("Need BLUETOOTH permission");
    }

    boolean doBind() {
        synchronized (this.mConnection) {
            try {
                try {
                    if (this.mService == null) {
                        log("Binding service...");
                        Intent intent = new Intent(IBluetoothPbap.class.getName());
                        ComponentName comp = intent.resolveSystemService(this.mContext.getPackageManager(), 0);
                        intent.setComponent(comp);
                        if (comp == null || !this.mContext.bindServiceAsUser(intent, this.mConnection, 0, UserHandle.CURRENT_OR_SELF)) {
                            Log.e(TAG, "Could not bind to Bluetooth Pbap Service with " + intent);
                            return false;
                        }
                    }
                    return true;
                } catch (SecurityException se) {
                    Log.e(TAG, "", se);
                    return false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doUnbind() {
        synchronized (this.mConnection) {
            if (this.mService != null) {
                log("Unbinding service...");
                try {
                    this.mContext.unbindService(this.mConnection);
                } catch (IllegalArgumentException ie) {
                    Log.e(TAG, "", ie);
                }
                this.mService = null;
            }
        }
    }

    protected void finalize() throws Throwable {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    public synchronized void close() {
        IBluetoothManager mgr = this.mAdapter.getBluetoothManager();
        if (mgr != null) {
            try {
                mgr.unregisterStateChangeCallback(this.mBluetoothStateChangeCallback);
            } catch (RemoteException re) {
                Log.e(TAG, "", re);
            }
        }
        doUnbind();
        this.mServiceListener = null;
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getConnectedDevices() {
        log("getConnectedDevices()");
        IBluetoothPbap service = this.mService;
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
            return new ArrayList();
        }
        try {
            return Attributable.setAttributionSource(service.getConnectedDevices(this.mAttributionSource), this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return new ArrayList();
        }
    }

    @Override // android.bluetooth.BluetoothProfile
    @SystemApi
    public int getConnectionState(BluetoothDevice device) {
        log("getConnectionState: device=" + device);
        try {
            IBluetoothPbap service = this.mService;
            if (service != null && isEnabled() && isValidDevice(device)) {
                return service.getConnectionState(device, this.mAttributionSource);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return 0;
        }
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        log("getDevicesMatchingConnectionStates: states=" + Arrays.toString(states));
        IBluetoothPbap service = this.mService;
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
            return new ArrayList();
        }
        try {
            return Attributable.setAttributionSource(service.getDevicesMatchingConnectionStates(states, this.mAttributionSource), this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return new ArrayList();
        }
    }

    @SystemApi
    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        try {
            IBluetoothPbap service = this.mService;
            if (service == null || !isEnabled() || !isValidDevice(device)) {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return false;
            } else if (connectionPolicy == 0 || connectionPolicy == 100) {
                return service.setConnectionPolicy(device, connectionPolicy, this.mAttributionSource);
            } else {
                return false;
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public boolean disconnect(BluetoothDevice device) {
        log("disconnect()");
        IBluetoothPbap service = this.mService;
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
            return false;
        }
        try {
            service.disconnect(device, this.mAttributionSource);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }

    private boolean isEnabled() {
        return this.mAdapter.getState() == 12;
    }

    private boolean isValidDevice(BluetoothDevice device) {
        return device != null && BluetoothAdapter.checkBluetoothAddress(device.getAddress());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String msg) {
    }
}
