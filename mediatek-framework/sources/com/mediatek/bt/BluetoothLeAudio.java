package com.mediatek.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothProfileConnector;
import android.bluetooth.IBluetoothLeAudio;
import android.content.AttributionSource;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class BluetoothLeAudio implements BluetoothProfile {
    public static final String ACTION_LEAUDIO_ACTIVE_DEVICE_CHANGED = "android.bluetooth.action.LEAUDIO_ACTIVE_DEVICE_CHANGED";
    public static final String ACTION_LEAUDIO_BROADCAST_ADDRESS_CHANGED = "android.bluetooth.action.LEAUDIO_BROADCAST_ADDRESS_CHANGED";
    public static final String ACTION_LEAUDIO_BROADCAST_CREATED = "android.bluetooth.action.LEAUDIO_BROADCAST_CREATED";
    public static final String ACTION_LEAUDIO_BROADCAST_STATE_CHANGED = "android.bluetooth.action.LEAUDIO_BROADCAST_STATE_CHANGED";
    public static final String ACTION_LEAUDIO_CONF_CHANGED = "android.bluetooth.action.LEAUDIO_CONF_CHANGED";
    public static final String ACTION_LEAUDIO_CONNECTION_REJECTED = "android.bluetooth.action.LEAUDIO_CONNECTION_REJECTED";
    public static final String ACTION_LEAUDIO_CONNECTION_STATE_CHANGED = "android.bluetooth.action.LEAUDIO_CONNECTION_STATE_CHANGED";
    public static final String ACTION_LEAUDIO_GROUP_LOCK_CHANGED = "android.bluetooth.action.LEAUDIO_GROUP_LOCK_CHANGED";
    public static final String ACTION_LEAUDIO_GROUP_NODE_STATUS_CHANGED = "android.bluetooth.action.LEAUDIO_GROUP_NODE_STATUS_CHANGED";
    public static final String ACTION_LEAUDIO_GROUP_STATUS_CHANGED = "android.bluetooth.action.LEAUDIO_GROUP_STATUS_CHANGED";
    public static final String ACTION_LEAUDIO_SET_MEMBER_AVAILABLE = "android.bluetooth.action.LEAUDIO_SET_MEMBER_AVAILABLE";
    public static final int CONTENT_TYPE_ATTENTION_SEEKING = 16;
    public static final int CONTENT_TYPE_CONVERSATIONAL = 2;
    public static final int CONTENT_TYPE_EMERGENCY_ALERT = 128;
    public static final int CONTENT_TYPE_IMMEDIATE_ALERT = 32;
    public static final int CONTENT_TYPE_INSTRUCTIONAL = 8;
    public static final int CONTENT_TYPE_MAN_MACHINE = 64;
    public static final int CONTENT_TYPE_MEDIA = 4;
    public static final int CONTENT_TYPE_RINGTONE = 256;
    public static final int CONTENT_TYPE_TV = 512;
    public static final int CONTENT_TYPE_UNSPECIFIED = 1;
    private static final boolean DBG = true;
    public static final String EXTRA_LEAUDIO_DIRECTION = "android.bluetooth.extra.LEAUDIO_DIRECTION";
    public static final String EXTRA_LEAUDIO_GROUP_FLAGS = "android.bluetooth.extra.LEAUDIO_GROUP_FLAGS";
    public static final String EXTRA_LEAUDIO_GROUP_ID = "android.bluetooth.extra.LEAUDIO_GROUP_ID";
    public static final String EXTRA_LEAUDIO_GROUP_LOCK_OP_STATUS = "android.bluetooth.extra.LEAUDIO_GROUP_LOCK_OP_STATUS";
    public static final String EXTRA_LEAUDIO_GROUP_LOCK_STATE = "android.bluetooth.extra.LEAUDIO_GROUP_LOCK_STATE";
    public static final String EXTRA_LEAUDIO_GROUP_NODE_STATUS = "android.bluetooth.extra.LEAUDIO_GROUP_NODE_STATUS";
    public static final String EXTRA_LEAUDIO_GROUP_STATUS = "android.bluetooth.extra.LEAUDIO_GROUP_STATUS";
    public static final String EXTRA_LEAUDIO_SINK_LOCATION = "android.bluetooth.extra.LEAUDIO_SINK_LOCATION";
    public static final String EXTRA_LEAUDIO_SOURCE_LOCATION = "android.bluetooth.extra.LEAUDIO_SOURCE_LOCATION";
    public static final int GROUP_ID_INVALID = -1;
    public static final int GROUP_LOCK_FAILED_GROUP_EMPTY = 2;
    public static final int GROUP_LOCK_FAILED_GROUP_NOT_CONNECTED = 3;
    public static final int GROUP_LOCK_FAILED_INVALID_GROUP = 1;
    public static final int GROUP_LOCK_FAILED_LOCKED_BY_OTHER = 4;
    public static final int GROUP_LOCK_FAILED_OTHER_REASON = 5;
    public static final int GROUP_LOCK_SUCCESS = 0;
    public static final int GROUP_NODE_ADDED = 1;
    public static final int GROUP_NODE_REMOVED = 2;
    public static final int GROUP_STATUS_DESTROYED = 4;
    public static final int GROUP_STATUS_IDLE = 0;
    public static final int GROUP_STATUS_RECONFIGURED = 3;
    public static final int GROUP_STATUS_RELEASING = 6;
    public static final int GROUP_STATUS_STREAMING = 1;
    public static final int GROUP_STATUS_SUSPENDED = 2;
    private static final String TAG = "BluetoothLeAudio";
    private static final boolean VDBG = false;
    private final BluetoothAdapter mAdapter;
    private final AttributionSource mAttributionSource;
    private final BluetoothProfileConnector<IBluetoothLeAudio> mProfileConnector;

    public BluetoothLeAudio(Context context, BluetoothProfile.ServiceListener listener) {
        BluetoothProfileConnector<IBluetoothLeAudio> bluetoothProfileConnector = new BluetoothProfileConnector(this, 22, TAG, IBluetoothLeAudio.class.getName()) { // from class: com.mediatek.bt.BluetoothLeAudio.1
            public IBluetoothLeAudio getServiceInterface(IBinder service) {
                return IBluetoothLeAudio.Stub.asInterface(Binder.allowBlocking(service));
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

    private IBluetoothLeAudio getService() {
        return (IBluetoothLeAudio) this.mProfileConnector.getService();
    }

    public boolean connect(BluetoothDevice device) {
        log("connect(" + device + ")");
        try {
            IBluetoothLeAudio service = getService();
            if (service != null && this.mAdapter.isEnabled() && isValidDevice(device)) {
                return service.connect(device, this.mAttributionSource);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return VDBG;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return VDBG;
        }
    }

    public boolean disconnect(BluetoothDevice device) {
        log("disconnect(" + device + ")");
        try {
            IBluetoothLeAudio service = getService();
            if (service != null && this.mAdapter.isEnabled() && isValidDevice(device)) {
                return service.disconnect(device, this.mAttributionSource);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return VDBG;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return VDBG;
        }
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getConnectedDevices() {
        try {
            IBluetoothLeAudio service = getService();
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
        try {
            IBluetoothLeAudio service = getService();
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
        log("setActiveDevice(" + device + ")");
        try {
            IBluetoothLeAudio service = getService();
            if (service == null || !this.mAdapter.isEnabled() || (device != null && !isValidDevice(device))) {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return VDBG;
            }
            service.setActiveDevice(device, this.mAttributionSource);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return VDBG;
        }
    }

    public List<BluetoothDevice> getActiveDevices() {
        try {
            IBluetoothLeAudio service = getService();
            if (service != null && this.mAdapter.isEnabled()) {
                return service.getActiveDevices(this.mAttributionSource);
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

    public boolean groupAddNode(int group_id, BluetoothDevice device) {
        try {
            IBluetoothLeAudio service = getService();
            if (service != null && this.mAdapter.isEnabled()) {
                return service.groupAddNode(group_id, device, this.mAttributionSource);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return VDBG;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return VDBG;
        }
    }

    public boolean groupRemoveNode(int group_id, BluetoothDevice device) {
        try {
            IBluetoothLeAudio service = getService();
            if (service != null && this.mAdapter.isEnabled()) {
                return service.groupRemoveNode(group_id, device, this.mAttributionSource);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return VDBG;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return VDBG;
        }
    }

    public boolean groupStream(int group_id) {
        try {
            IBluetoothLeAudio service = getService();
            if (service == null || !this.mAdapter.isEnabled()) {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return VDBG;
            }
            service.groupStream(group_id, this.mAttributionSource);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return VDBG;
        }
    }

    public boolean groupSuspend(int group_id) {
        try {
            IBluetoothLeAudio service = getService();
            if (service == null || !this.mAdapter.isEnabled()) {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return VDBG;
            }
            service.groupSuspend(group_id, this.mAttributionSource);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return VDBG;
        }
    }

    public boolean groupStop(int group_id) {
        try {
            IBluetoothLeAudio service = getService();
            if (service == null || !this.mAdapter.isEnabled()) {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return VDBG;
            }
            service.groupStop(group_id, this.mAttributionSource);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return VDBG;
        }
    }

    public boolean groupDestroy(int group_id) {
        try {
            IBluetoothLeAudio service = getService();
            if (service == null || !this.mAdapter.isEnabled()) {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return VDBG;
            }
            service.groupDestroy(group_id, this.mAttributionSource);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return VDBG;
        }
    }

    public boolean groupLockSet(int group_id, boolean lock) {
        try {
            IBluetoothLeAudio service = getService();
            if (service == null || !this.mAdapter.isEnabled()) {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return VDBG;
            }
            service.groupLockSet(group_id, lock, this.mAttributionSource);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return VDBG;
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
        log("setConnectionPolicy(" + device + ", " + connectionPolicy + ")");
        try {
            IBluetoothLeAudio service = getService();
            if (service == null || !this.mAdapter.isEnabled() || !isValidDevice(device)) {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return VDBG;
            } else if (connectionPolicy == 0 || connectionPolicy == 100) {
                return service.setConnectionPolicy(device, connectionPolicy, this.mAttributionSource);
            } else {
                return VDBG;
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()));
            return VDBG;
        }
    }

    public boolean setPriority(BluetoothDevice device, int priority) {
        log("setPriority(" + device + ", " + priority + ")");
        return setConnectionPolicy(device, BluetoothAdapter.priorityToConnectionPolicy(priority));
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

    public int getPriority(BluetoothDevice device) {
        return BluetoothAdapter.connectionPolicyToPriority(getConnectionPolicy(device));
    }

    public void setVcAbsoluteVolume(int volume) {
        Log.d(TAG, "setVcAbsoluteVolume");
        try {
            IBluetoothLeAudio service = getService();
            if (service != null && this.mAdapter.isEnabled()) {
                service.setVcAbsoluteVolume(volume, this.mAttributionSource);
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Stack:" + Log.getStackTraceString(new Throwable()) + "Error talking to BT service in setVcAbsoluteVolume()");
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
                return "disconnecting";
            default:
                return "<unknown state " + state + ">";
        }
    }

    private boolean isValidDevice(BluetoothDevice device) {
        if (device != null && BluetoothAdapter.checkBluetoothAddress(device.getAddress())) {
            return true;
        }
        return VDBG;
    }

    private static void log(String msg) {
        Log.d(TAG, msg);
    }
}
