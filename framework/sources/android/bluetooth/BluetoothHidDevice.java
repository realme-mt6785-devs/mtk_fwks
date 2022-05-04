package android.bluetooth;

import android.annotation.SystemApi;
import android.bluetooth.BluetoothHidDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothHidDevice;
import android.bluetooth.IBluetoothHidDeviceCallback;
import android.content.Attributable;
import android.content.AttributionSource;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class BluetoothHidDevice implements BluetoothProfile {
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.hiddevice.profile.action.CONNECTION_STATE_CHANGED";
    private static final boolean DBG = false;
    public static final byte ERROR_RSP_INVALID_PARAM = 4;
    public static final byte ERROR_RSP_INVALID_RPT_ID = 2;
    public static final byte ERROR_RSP_NOT_READY = 1;
    public static final byte ERROR_RSP_SUCCESS = 0;
    public static final byte ERROR_RSP_UNKNOWN = 14;
    public static final byte ERROR_RSP_UNSUPPORTED_REQ = 3;
    public static final byte PROTOCOL_BOOT_MODE = 0;
    public static final byte PROTOCOL_REPORT_MODE = 1;
    public static final byte REPORT_TYPE_FEATURE = 3;
    public static final byte REPORT_TYPE_INPUT = 1;
    public static final byte REPORT_TYPE_OUTPUT = 2;
    public static final byte SUBCLASS1_COMBO = -64;
    public static final byte SUBCLASS1_KEYBOARD = 64;
    public static final byte SUBCLASS1_MOUSE = Byte.MIN_VALUE;
    public static final byte SUBCLASS1_NONE = 0;
    public static final byte SUBCLASS2_CARD_READER = 6;
    public static final byte SUBCLASS2_DIGITIZER_TABLET = 5;
    public static final byte SUBCLASS2_GAMEPAD = 2;
    public static final byte SUBCLASS2_JOYSTICK = 1;
    public static final byte SUBCLASS2_REMOTE_CONTROL = 3;
    public static final byte SUBCLASS2_SENSING_DEVICE = 4;
    public static final byte SUBCLASS2_UNCATEGORIZED = 0;
    private static final String TAG = BluetoothHidDevice.class.getSimpleName();
    private final BluetoothAdapter mAdapter;
    private final AttributionSource mAttributionSource;
    private final BluetoothProfileConnector<IBluetoothHidDevice> mProfileConnector;

    /* loaded from: classes.dex */
    public static abstract class Callback {
        private static final String TAG = "BluetoothHidDevCallback";

        public void onAppStatusChanged(BluetoothDevice pluggedDevice, boolean registered) {
            Log.d(TAG, "onAppStatusChanged: pluggedDevice=" + pluggedDevice + " registered=" + registered);
        }

        public void onConnectionStateChanged(BluetoothDevice device, int state) {
            Log.d(TAG, "onConnectionStateChanged: device=" + device + " state=" + state);
        }

        public void onGetReport(BluetoothDevice device, byte type, byte id, int bufferSize) {
            Log.d(TAG, "onGetReport: device=" + device + " type=" + ((int) type) + " id=" + ((int) id) + " bufferSize=" + bufferSize);
        }

        public void onSetReport(BluetoothDevice device, byte type, byte id, byte[] data) {
            Log.d(TAG, "onSetReport: device=" + device + " type=" + ((int) type) + " id=" + ((int) id));
        }

        public void onSetProtocol(BluetoothDevice device, byte protocol) {
            Log.d(TAG, "onSetProtocol: device=" + device + " protocol=" + ((int) protocol));
        }

        public void onInterruptData(BluetoothDevice device, byte reportId, byte[] data) {
            Log.d(TAG, "onInterruptData: device=" + device + " reportId=" + ((int) reportId));
        }

        public void onVirtualCableUnplug(BluetoothDevice device) {
            Log.d(TAG, "onVirtualCableUnplug: device=" + device);
        }
    }

    /* loaded from: classes.dex */
    private static class CallbackWrapper extends IBluetoothHidDeviceCallback.Stub {
        private final AttributionSource mAttributionSource;
        private final Callback mCallback;
        private final Executor mExecutor;

        CallbackWrapper(Executor executor, Callback callback, AttributionSource attributionSource) {
            this.mExecutor = executor;
            this.mCallback = callback;
            this.mAttributionSource = attributionSource;
        }

        @Override // android.bluetooth.IBluetoothHidDeviceCallback
        public void onAppStatusChanged(final BluetoothDevice pluggedDevice, final boolean registered) {
            Attributable.setAttributionSource(pluggedDevice, this.mAttributionSource);
            long token = clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.bluetooth.BluetoothHidDevice$CallbackWrapper$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothHidDevice.CallbackWrapper.this.lambda$onAppStatusChanged$0$BluetoothHidDevice$CallbackWrapper(pluggedDevice, registered);
                    }
                });
            } finally {
                restoreCallingIdentity(token);
            }
        }

        public /* synthetic */ void lambda$onAppStatusChanged$0$BluetoothHidDevice$CallbackWrapper(BluetoothDevice pluggedDevice, boolean registered) {
            this.mCallback.onAppStatusChanged(pluggedDevice, registered);
        }

        @Override // android.bluetooth.IBluetoothHidDeviceCallback
        public void onConnectionStateChanged(final BluetoothDevice device, final int state) {
            Attributable.setAttributionSource(device, this.mAttributionSource);
            long token = clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.bluetooth.BluetoothHidDevice$CallbackWrapper$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothHidDevice.CallbackWrapper.this.lambda$onConnectionStateChanged$1$BluetoothHidDevice$CallbackWrapper(device, state);
                    }
                });
            } finally {
                restoreCallingIdentity(token);
            }
        }

        public /* synthetic */ void lambda$onConnectionStateChanged$1$BluetoothHidDevice$CallbackWrapper(BluetoothDevice device, int state) {
            this.mCallback.onConnectionStateChanged(device, state);
        }

        @Override // android.bluetooth.IBluetoothHidDeviceCallback
        public void onGetReport(final BluetoothDevice device, final byte type, final byte id, final int bufferSize) {
            Attributable.setAttributionSource(device, this.mAttributionSource);
            long token = clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.bluetooth.BluetoothHidDevice$CallbackWrapper$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothHidDevice.CallbackWrapper.this.lambda$onGetReport$2$BluetoothHidDevice$CallbackWrapper(device, type, id, bufferSize);
                    }
                });
            } finally {
                restoreCallingIdentity(token);
            }
        }

        public /* synthetic */ void lambda$onGetReport$2$BluetoothHidDevice$CallbackWrapper(BluetoothDevice device, byte type, byte id, int bufferSize) {
            this.mCallback.onGetReport(device, type, id, bufferSize);
        }

        @Override // android.bluetooth.IBluetoothHidDeviceCallback
        public void onSetReport(final BluetoothDevice device, final byte type, final byte id, final byte[] data) {
            Attributable.setAttributionSource(device, this.mAttributionSource);
            long token = clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.bluetooth.BluetoothHidDevice$CallbackWrapper$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothHidDevice.CallbackWrapper.this.lambda$onSetReport$3$BluetoothHidDevice$CallbackWrapper(device, type, id, data);
                    }
                });
            } finally {
                restoreCallingIdentity(token);
            }
        }

        public /* synthetic */ void lambda$onSetReport$3$BluetoothHidDevice$CallbackWrapper(BluetoothDevice device, byte type, byte id, byte[] data) {
            this.mCallback.onSetReport(device, type, id, data);
        }

        @Override // android.bluetooth.IBluetoothHidDeviceCallback
        public void onSetProtocol(final BluetoothDevice device, final byte protocol) {
            Attributable.setAttributionSource(device, this.mAttributionSource);
            long token = clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.bluetooth.BluetoothHidDevice$CallbackWrapper$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothHidDevice.CallbackWrapper.this.lambda$onSetProtocol$4$BluetoothHidDevice$CallbackWrapper(device, protocol);
                    }
                });
            } finally {
                restoreCallingIdentity(token);
            }
        }

        public /* synthetic */ void lambda$onSetProtocol$4$BluetoothHidDevice$CallbackWrapper(BluetoothDevice device, byte protocol) {
            this.mCallback.onSetProtocol(device, protocol);
        }

        @Override // android.bluetooth.IBluetoothHidDeviceCallback
        public void onInterruptData(final BluetoothDevice device, final byte reportId, final byte[] data) {
            Attributable.setAttributionSource(device, this.mAttributionSource);
            long token = clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.bluetooth.BluetoothHidDevice$CallbackWrapper$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothHidDevice.CallbackWrapper.this.lambda$onInterruptData$5$BluetoothHidDevice$CallbackWrapper(device, reportId, data);
                    }
                });
            } finally {
                restoreCallingIdentity(token);
            }
        }

        public /* synthetic */ void lambda$onInterruptData$5$BluetoothHidDevice$CallbackWrapper(BluetoothDevice device, byte reportId, byte[] data) {
            this.mCallback.onInterruptData(device, reportId, data);
        }

        @Override // android.bluetooth.IBluetoothHidDeviceCallback
        public void onVirtualCableUnplug(final BluetoothDevice device) {
            Attributable.setAttributionSource(device, this.mAttributionSource);
            long token = clearCallingIdentity();
            try {
                this.mExecutor.execute(new Runnable() { // from class: android.bluetooth.BluetoothHidDevice$CallbackWrapper$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        BluetoothHidDevice.CallbackWrapper.this.lambda$onVirtualCableUnplug$6$BluetoothHidDevice$CallbackWrapper(device);
                    }
                });
            } finally {
                restoreCallingIdentity(token);
            }
        }

        public /* synthetic */ void lambda$onVirtualCableUnplug$6$BluetoothHidDevice$CallbackWrapper(BluetoothDevice device) {
            this.mCallback.onVirtualCableUnplug(device);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothHidDevice(Context context, BluetoothProfile.ServiceListener listener, BluetoothAdapter adapter) {
        BluetoothProfileConnector<IBluetoothHidDevice> bluetoothProfileConnector = new BluetoothProfileConnector(this, 19, "BluetoothHidDevice", IBluetoothHidDevice.class.getName()) { // from class: android.bluetooth.BluetoothHidDevice.1
            @Override // android.bluetooth.BluetoothProfileConnector
            public IBluetoothHidDevice getServiceInterface(IBinder service) {
                return IBluetoothHidDevice.Stub.asInterface(Binder.allowBlocking(service));
            }
        };
        this.mProfileConnector = bluetoothProfileConnector;
        this.mAdapter = adapter;
        this.mAttributionSource = adapter.getAttributionSource();
        bluetoothProfileConnector.connect(context, listener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void close() {
        this.mProfileConnector.disconnect();
    }

    private IBluetoothHidDevice getService() {
        return this.mProfileConnector.getService();
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getConnectedDevices() {
        IBluetoothHidDevice service = getService();
        if (service != null) {
            try {
                return Attributable.setAttributionSource(service.getConnectedDevices(this.mAttributionSource), this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
            }
        } else {
            Log.w(TAG, "Proxy not attached to service");
        }
        return new ArrayList();
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        IBluetoothHidDevice service = getService();
        if (service != null) {
            try {
                return Attributable.setAttributionSource(service.getDevicesMatchingConnectionStates(states, this.mAttributionSource), this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
            }
        } else {
            Log.w(TAG, "Proxy not attached to service");
        }
        return new ArrayList();
    }

    @Override // android.bluetooth.BluetoothProfile
    public int getConnectionState(BluetoothDevice device) {
        IBluetoothHidDevice service = getService();
        if (service != null) {
            try {
                return service.getConnectionState(device, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
                return 0;
            }
        } else {
            Log.w(TAG, "Proxy not attached to service");
            return 0;
        }
    }

    public boolean registerApp(BluetoothHidDeviceAppSdpSettings sdp, BluetoothHidDeviceAppQosSettings inQos, BluetoothHidDeviceAppQosSettings outQos, Executor executor, Callback callback) {
        if (sdp == null) {
            throw new IllegalArgumentException("sdp parameter cannot be null");
        } else if (executor == null) {
            throw new IllegalArgumentException("executor parameter cannot be null");
        } else if (callback != null) {
            IBluetoothHidDevice service = getService();
            if (service != null) {
                try {
                    CallbackWrapper cbw = new CallbackWrapper(executor, callback, this.mAttributionSource);
                    boolean result = service.registerApp(sdp, inQos, outQos, cbw, this.mAttributionSource);
                    return result;
                } catch (RemoteException e) {
                    Log.e(TAG, e.toString());
                    return false;
                }
            } else {
                Log.w(TAG, "Proxy not attached to service");
                return false;
            }
        } else {
            throw new IllegalArgumentException("callback parameter cannot be null");
        }
    }

    public boolean unregisterApp() {
        IBluetoothHidDevice service = getService();
        if (service != null) {
            try {
                boolean result = service.unregisterApp(this.mAttributionSource);
                return result;
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
                return false;
            }
        } else {
            Log.w(TAG, "Proxy not attached to service");
            return false;
        }
    }

    public boolean sendReport(BluetoothDevice device, int id, byte[] data) {
        IBluetoothHidDevice service = getService();
        if (service != null) {
            try {
                boolean result = service.sendReport(device, id, data, this.mAttributionSource);
                return result;
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
                return false;
            }
        } else {
            Log.w(TAG, "Proxy not attached to service");
            return false;
        }
    }

    public boolean replyReport(BluetoothDevice device, byte type, byte id, byte[] data) {
        IBluetoothHidDevice service = getService();
        if (service != null) {
            try {
                boolean result = service.replyReport(device, type, id, data, this.mAttributionSource);
                return result;
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
                return false;
            }
        } else {
            Log.w(TAG, "Proxy not attached to service");
            return false;
        }
    }

    public boolean reportError(BluetoothDevice device, byte error) {
        IBluetoothHidDevice service = getService();
        if (service != null) {
            try {
                boolean result = service.reportError(device, error, this.mAttributionSource);
                return result;
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
                return false;
            }
        } else {
            Log.w(TAG, "Proxy not attached to service");
            return false;
        }
    }

    public String getUserAppName() {
        IBluetoothHidDevice service = getService();
        if (service != null) {
            try {
                return service.getUserAppName(this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
                return "";
            }
        } else {
            Log.w(TAG, "Proxy not attached to service");
            return "";
        }
    }

    public boolean connect(BluetoothDevice device) {
        IBluetoothHidDevice service = getService();
        if (service != null) {
            try {
                boolean result = service.connect(device, this.mAttributionSource);
                return result;
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
                return false;
            }
        } else {
            Log.w(TAG, "Proxy not attached to service");
            return false;
        }
    }

    public boolean disconnect(BluetoothDevice device) {
        IBluetoothHidDevice service = getService();
        if (service != null) {
            try {
                boolean result = service.disconnect(device, this.mAttributionSource);
                return result;
            } catch (RemoteException e) {
                Log.e(TAG, e.toString());
                return false;
            }
        } else {
            Log.w(TAG, "Proxy not attached to service");
            return false;
        }
    }

    @SystemApi
    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        log("setConnectionPolicy(" + device + ", " + connectionPolicy + ")");
        try {
            IBluetoothHidDevice service = getService();
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
            String str = TAG;
            Log.e(str, "Stack:" + Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    private boolean isEnabled() {
        return this.mAdapter.getState() == 12;
    }

    private boolean isValidDevice(BluetoothDevice device) {
        return device != null && BluetoothAdapter.checkBluetoothAddress(device.getAddress());
    }

    private static void log(String msg) {
    }
}
