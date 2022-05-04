package com.mediatek.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfileConnector;
import android.bluetooth.IBluetoothVolumeControl;
import android.content.AttributionSource;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class BluetoothVolumeControl implements BluetoothProfile {
    public static final String ACTION_VC_CONNECTION_STATE_CHANGED = "android.bluetooth.action.VC_CONNECTION_STATE_CHANGED";
    public static final String ACTION_VC_DEVICE_AVAILABLE = "android.bluetooth.action.VC_DEVICE_AVAILABLE";
    public static final String ACTION_VC_EXT_AUDIO_IN_DESCRIPTION_CHANGED = "android.bluetooth.action.VC_EXT_AUDIO_IN_DESCRIPTION_CHANGED";
    public static final String ACTION_VC_EXT_AUDIO_IN_GAIN_PROPS_CHANGED = "android.bluetooth.action.VC_EXT_AUDIO_IN_GAIN_PROPS_CHANGED";
    public static final String ACTION_VC_EXT_AUDIO_IN_STATE_CHANGED = "android.bluetooth.action.VC_EXT_AUDIO_IN_STATE_CHANGED";
    public static final String ACTION_VC_EXT_AUDIO_IN_STATUS_CHANGED = "android.bluetooth.action.VC_EXT_AUDIO_IN_STATUS_CHANGED";
    public static final String ACTION_VC_EXT_AUDIO_IN_TYPE_CHANGED = "android.bluetooth.action.VC_EXT_AUDIO_IN_TYPE_CHANGED";
    public static final String ACTION_VC_EXT_AUDIO_OUT_DESCRIPTION_CHANGED = "android.bluetooth.action.VC_EXT_AUDIO_OUT_DESCRIPTION_CHANGED";
    public static final String ACTION_VC_EXT_AUDIO_OUT_LOCATION_CHANGED = "android.bluetooth.action.VC_EXT_AUDIO_OUT_LOCATION_CHANGED";
    public static final String ACTION_VC_EXT_AUDIO_OUT_VOLUME_OFFSET_CHANGED = "android.bluetooth.action.VC_EXT_AUDIO_OUT_VOLUME_OFFSET_CHANGED";
    public static final String ACTION_VC_VOLUME_STATE_CHANGED = "android.bluetooth.action.VC_VOLUME_STATE_CHANGED";
    private static final boolean DBG = true;
    public static final String EXTRA_VC_DESCRIPTION = "android.bluetooth.extra.VC_DESCRIPTION";
    public static final String EXTRA_VC_EXT_INPUT_ID = "android.bluetooth.extra.VC_EXT_INPUT_ID";
    public static final String EXTRA_VC_EXT_OUTPUT_ID = "android.bluetooth.extra.VC_EXT_OUTPUT_ID";
    public static final String EXTRA_VC_GAIN_MODE = "android.bluetooth.extra.VC_GAIN_MODE";
    public static final String EXTRA_VC_GAIN_VALUE = "android.bluetooth.extra.VC_GAIN_VALUE";
    public static final String EXTRA_VC_LOCATION = "android.bluetooth.extra.VC_LOCATION";
    public static final String EXTRA_VC_MAX = "android.bluetooth.extra.VC_MAX";
    public static final String EXTRA_VC_MIN = "android.bluetooth.extra.VC_MIN";
    public static final String EXTRA_VC_MUTED = "android.bluetooth.extra.VC_MUTED";
    public static final String EXTRA_VC_NUM_INPUTS = "android.bluetooth.extra.VC_NUM_INPUTS";
    public static final String EXTRA_VC_NUM_OFFSETS = "android.bluetooth.extra.VC_NUM_OFFSETS";
    public static final String EXTRA_VC_OFFSET = "android.bluetooth.extra.VC_OFFSET";
    public static final String EXTRA_VC_STATUS = "android.bluetooth.extra.VC_STATUS";
    public static final String EXTRA_VC_TYPE = "android.bluetooth.extra.VC_TYPE";
    public static final String EXTRA_VC_UNIT = "android.bluetooth.extra.VC_UNIT";
    public static final String EXTRA_VC_VOLUME = "android.bluetooth.extra.VC_VOLUME";
    private static final String TAG = "BluetoothVolumeControl";
    private static final boolean VDBG = true;
    private final BluetoothAdapter mAdapter;
    private final AttributionSource mAttributionSource;
    private final BluetoothProfileConnector<IBluetoothVolumeControl> mProfileConnector;

    public BluetoothVolumeControl(Context context, BluetoothProfile.ServiceListener listener) {
        BluetoothProfileConnector<IBluetoothVolumeControl> bluetoothProfileConnector = new BluetoothProfileConnector(this, 23, TAG, IBluetoothVolumeControl.class.getName()) { // from class: com.mediatek.bt.BluetoothVolumeControl.1
            public IBluetoothVolumeControl getServiceInterface(IBinder service) {
                return IBluetoothVolumeControl.Stub.asInterface(Binder.allowBlocking(service));
            }
        };
        this.mProfileConnector = bluetoothProfileConnector;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mAdapter = defaultAdapter;
        this.mAttributionSource = defaultAdapter.getAttributionSource();
        bluetoothProfileConnector.connect(context, listener);
    }

    public void close() {
        this.mProfileConnector.disconnect();
    }

    private IBluetoothVolumeControl getService() {
        return (IBluetoothVolumeControl) this.mProfileConnector.getService();
    }

    public boolean connect(BluetoothDevice device) {
        log("connect(" + device + ")");
        try {
            IBluetoothVolumeControl service = getService();
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
        log("disconnect(" + device + ")");
        try {
            IBluetoothVolumeControl service = getService();
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
        log("getConnectedDevices()");
        try {
            IBluetoothVolumeControl service = getService();
            if (service != null && this.mAdapter.isEnabled()) {
                return service.getConnectedDevices(this.mAttributionSource);
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
        log("getDevicesMatchingConnectionStates()");
        try {
            IBluetoothVolumeControl service = getService();
            if (service != null && this.mAdapter.isEnabled()) {
                return service.getDevicesMatchingConnectionStates(states, this.mAttributionSource);
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
        log("getConnectionState(" + device + ")");
        try {
            IBluetoothVolumeControl service = getService();
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

    public void setVolume(int volume) {
        log("setVolume(" + volume + ")");
        try {
            IBluetoothVolumeControl service = getService();
            if (service != null && this.mAdapter.isEnabled()) {
                service.setVolume(volume, this.mAttributionSource);
            } else if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
        }
    }

    public void volumeUp(boolean unmute) {
        log("volumeUp()");
        try {
            IBluetoothVolumeControl service = getService();
            if (service != null && this.mAdapter.isEnabled()) {
                service.volumeUp(unmute, this.mAttributionSource);
            } else if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
        }
    }

    public void volumeDown(boolean unmute) {
        log("volumeDown()");
        IBluetoothVolumeControl service = getService();
        if (service != null) {
            try {
                if (this.mAdapter.isEnabled()) {
                    service.volumeDown(unmute, this.mAttributionSource);
                    return;
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
                return;
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
    }

    public void mute() {
        log("mute()");
        IBluetoothVolumeControl service = getService();
        if (service != null) {
            try {
                if (this.mAdapter.isEnabled()) {
                    service.mute(this.mAttributionSource);
                    return;
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
                return;
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
    }

    public void unmute() {
        log("unmute()");
        IBluetoothVolumeControl service = getService();
        if (service != null) {
            try {
                if (this.mAdapter.isEnabled()) {
                    service.unmute(this.mAttributionSource);
                    return;
                }
            } catch (RemoteException e) {
                Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
                return;
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        log("setConnectionPolicy(" + device + ", " + connectionPolicy + ")");
        try {
            IBluetoothVolumeControl service = getService();
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

    public boolean setPriority(BluetoothDevice device, int priority) {
        log("setPriority(" + device + ", " + priority + ")");
        return setConnectionPolicy(device, BluetoothAdapter.priorityToConnectionPolicy(priority));
    }

    public int getConnectionPolicy(BluetoothDevice device) {
        log("getConnectionPolicy(" + device + ")");
        try {
            IBluetoothVolumeControl service = getService();
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

    public int getPriority(BluetoothDevice device) {
        log("getPriority(" + device + ")");
        return BluetoothAdapter.connectionPolicyToPriority(getConnectionPolicy(device));
    }

    private boolean isEnabled() {
        return this.mAdapter.getState() == 12;
    }

    private static boolean isValidDevice(BluetoothDevice device) {
        return device != null && BluetoothAdapter.checkBluetoothAddress(device.getAddress());
    }

    private static void log(String msg) {
        Log.d(TAG, msg);
    }
}
