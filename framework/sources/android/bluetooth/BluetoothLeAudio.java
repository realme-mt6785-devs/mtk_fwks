package android.bluetooth;

import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothLeAudio;
import android.content.Attributable;
import android.content.AttributionSource;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.ims.ImsConferenceState;
import android.util.CloseGuard;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class BluetoothLeAudio implements BluetoothProfile, AutoCloseable {
    public static final String ACTION_LE_AUDIO_ACTIVE_DEVICE_CHANGED = "android.bluetooth.action.LE_AUDIO_ACTIVE_DEVICE_CHANGED";
    public static final String ACTION_LE_AUDIO_CONNECTION_STATE_CHANGED = "android.bluetooth.action.LE_AUDIO_CONNECTION_STATE_CHANGED";
    private static final boolean DBG = false;
    public static final int GROUP_ID_INVALID = -1;
    private static final String TAG = "BluetoothLeAudio";
    private static final boolean VDBG = false;
    private final BluetoothAdapter mAdapter;
    private final AttributionSource mAttributionSource;
    private CloseGuard mCloseGuard;
    private final BluetoothProfileConnector<IBluetoothLeAudio> mProfileConnector;

    BluetoothLeAudio(Context context, BluetoothProfile.ServiceListener listener, BluetoothAdapter adapter) {
        BluetoothProfileConnector<IBluetoothLeAudio> bluetoothProfileConnector = new BluetoothProfileConnector(this, 22, TAG, IBluetoothLeAudio.class.getName()) { // from class: android.bluetooth.BluetoothLeAudio.1
            @Override // android.bluetooth.BluetoothProfileConnector
            public IBluetoothLeAudio getServiceInterface(IBinder service) {
                return IBluetoothLeAudio.Stub.asInterface(Binder.allowBlocking(service));
            }
        };
        this.mProfileConnector = bluetoothProfileConnector;
        this.mAdapter = adapter;
        this.mAttributionSource = adapter.getAttributionSource();
        bluetoothProfileConnector.connect(context, listener);
        CloseGuard closeGuard = new CloseGuard();
        this.mCloseGuard = closeGuard;
        closeGuard.open("close");
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.mProfileConnector.disconnect();
    }

    private IBluetoothLeAudio getService() {
        return this.mProfileConnector.getService();
    }

    protected void finalize() {
        CloseGuard closeGuard = this.mCloseGuard;
        if (closeGuard != null) {
            closeGuard.warnIfOpen();
        }
        close();
    }

    public boolean connect(BluetoothDevice device) {
        try {
            IBluetoothLeAudio service = getService();
            if (service != null && this.mAdapter.isEnabled() && isValidDevice(device)) {
                return service.connect(device, this.mAttributionSource);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public boolean disconnect(BluetoothDevice device) {
        try {
            IBluetoothLeAudio service = getService();
            if (service != null && this.mAdapter.isEnabled() && isValidDevice(device)) {
                return service.disconnect(device, this.mAttributionSource);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getConnectedDevices() {
        try {
            IBluetoothLeAudio service = getService();
            if (service != null && this.mAdapter.isEnabled()) {
                return Attributable.setAttributionSource(service.getConnectedDevices(this.mAttributionSource), this.mAttributionSource);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return new ArrayList();
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return new ArrayList();
        }
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        try {
            IBluetoothLeAudio service = getService();
            if (service != null && this.mAdapter.isEnabled()) {
                return Attributable.setAttributionSource(service.getDevicesMatchingConnectionStates(states, this.mAttributionSource), this.mAttributionSource);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return new ArrayList();
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return new ArrayList();
        }
    }

    @Override // android.bluetooth.BluetoothProfile
    public int getConnectionState(BluetoothDevice device) {
        try {
            IBluetoothLeAudio service = getService();
            if (service != null && this.mAdapter.isEnabled() && isValidDevice(device)) {
                return service.getConnectionState(device, this.mAttributionSource);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return 0;
        }
    }

    public boolean setActiveDevice(BluetoothDevice device) {
        try {
            IBluetoothLeAudio service = getService();
            if (service == null || !this.mAdapter.isEnabled() || (device != null && !isValidDevice(device))) {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return false;
            }
            service.setActiveDevice(device, this.mAttributionSource);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public List<BluetoothDevice> getActiveDevices() {
        try {
            IBluetoothLeAudio service = getService();
            if (service != null && this.mAdapter.isEnabled()) {
                return Attributable.setAttributionSource(service.getActiveDevices(this.mAttributionSource), this.mAttributionSource);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return new ArrayList();
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return new ArrayList();
        }
    }

    public int getGroupId(BluetoothDevice device) {
        try {
            IBluetoothLeAudio service = getService();
            if (service != null && this.mAdapter.isEnabled()) {
                return service.getGroupId(device, this.mAttributionSource);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return -1;
        }
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        try {
            IBluetoothLeAudio service = getService();
            if (service == null || !this.mAdapter.isEnabled() || !isValidDevice(device)) {
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

    public int getConnectionPolicy(BluetoothDevice device) {
        try {
            IBluetoothLeAudio service = getService();
            if (service != null && this.mAdapter.isEnabled() && isValidDevice(device)) {
                return service.getConnectionPolicy(device, this.mAttributionSource);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return 0;
        }
    }

    public static String stateToString(int state) {
        switch (state) {
            case 0:
                return "disconnected";
            case 1:
                return "connecting";
            case 2:
                return "connected";
            case 3:
                return ImsConferenceState.STATUS_DISCONNECTING;
            default:
                return "<unknown state " + state + ">";
        }
    }

    private boolean isValidDevice(BluetoothDevice device) {
        return device != null && BluetoothAdapter.checkBluetoothAddress(device.getAddress());
    }

    private static void log(String msg) {
        Log.d(TAG, msg);
    }
}
