package android.bluetooth;

import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothHeadsetClient;
import android.content.Attributable;
import android.content.AttributionSource;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class BluetoothHeadsetClient implements BluetoothProfile {
    public static final String ACTION_AG_EVENT = "android.bluetooth.headsetclient.profile.action.AG_EVENT";
    public static final String ACTION_AUDIO_STATE_CHANGED = "android.bluetooth.headsetclient.profile.action.AUDIO_STATE_CHANGED";
    public static final String ACTION_CALL_CHANGED = "android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED";
    public static final String ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED";
    public static final String ACTION_LAST_VTAG = "android.bluetooth.headsetclient.profile.action.LAST_VTAG";
    public static final String ACTION_RESULT = "android.bluetooth.headsetclient.profile.action.RESULT";
    public static final int ACTION_RESULT_ERROR = 1;
    public static final int ACTION_RESULT_ERROR_BLACKLISTED = 6;
    public static final int ACTION_RESULT_ERROR_BUSY = 3;
    public static final int ACTION_RESULT_ERROR_CME = 7;
    public static final int ACTION_RESULT_ERROR_DELAYED = 5;
    public static final int ACTION_RESULT_ERROR_NO_ANSWER = 4;
    public static final int ACTION_RESULT_ERROR_NO_CARRIER = 2;
    public static final int ACTION_RESULT_OK = 0;
    public static final String ACTION_VENDOR_SPECIFIC_HEADSETCLIENT_EVENT = "android.bluetooth.headsetclient.profile.action.VENDOR_SPECIFIC_EVENT";
    public static final int CALL_ACCEPT_HOLD = 1;
    public static final int CALL_ACCEPT_NONE = 0;
    public static final int CALL_ACCEPT_TERMINATE = 2;
    public static final int CME_CORPORATE_PERSONALIZATION_PIN_REQUIRED = 46;
    public static final int CME_CORPORATE_PERSONALIZATION_PUK_REQUIRED = 47;
    public static final int CME_DIAL_STRING_TOO_LONG = 26;
    public static final int CME_EAP_NOT_SUPPORTED = 49;
    public static final int CME_EMERGENCY_SERVICE_ONLY = 32;
    public static final int CME_HIDDEN_KEY_REQUIRED = 48;
    public static final int CME_INCORRECT_PARAMETERS = 50;
    public static final int CME_INCORRECT_PASSWORD = 16;
    public static final int CME_INVALID_CHARACTER_IN_DIAL_STRING = 27;
    public static final int CME_INVALID_CHARACTER_IN_TEXT_STRING = 25;
    public static final int CME_INVALID_INDEX = 21;
    public static final int CME_MEMORY_FAILURE = 23;
    public static final int CME_MEMORY_FULL = 20;
    public static final int CME_NETWORK_PERSONALIZATION_PIN_REQUIRED = 40;
    public static final int CME_NETWORK_PERSONALIZATION_PUK_REQUIRED = 41;
    public static final int CME_NETWORK_SUBSET_PERSONALIZATION_PIN_REQUIRED = 42;
    public static final int CME_NETWORK_SUBSET_PERSONALIZATION_PUK_REQUIRED = 43;
    public static final int CME_NETWORK_TIMEOUT = 31;
    public static final int CME_NOT_FOUND = 22;
    public static final int CME_NOT_SUPPORTED_FOR_VOIP = 34;
    public static final int CME_NO_CONNECTION_TO_PHONE = 1;
    public static final int CME_NO_NETWORK_SERVICE = 30;
    public static final int CME_NO_SIMULTANOUS_VOIP_CS_CALLS = 33;
    public static final int CME_OPERATION_NOT_ALLOWED = 3;
    public static final int CME_OPERATION_NOT_SUPPORTED = 4;
    public static final int CME_PHFSIM_PIN_REQUIRED = 6;
    public static final int CME_PHFSIM_PUK_REQUIRED = 7;
    public static final int CME_PHONE_FAILURE = 0;
    public static final int CME_PHSIM_PIN_REQUIRED = 5;
    public static final int CME_SERVICE_PROVIDER_PERSONALIZATION_PIN_REQUIRED = 44;
    public static final int CME_SERVICE_PROVIDER_PERSONALIZATION_PUK_REQUIRED = 45;
    public static final int CME_SIM_BUSY = 14;
    public static final int CME_SIM_FAILURE = 13;
    public static final int CME_SIM_NOT_INSERTED = 10;
    public static final int CME_SIM_PIN2_REQUIRED = 17;
    public static final int CME_SIM_PIN_REQUIRED = 11;
    public static final int CME_SIM_PUK2_REQUIRED = 18;
    public static final int CME_SIM_PUK_REQUIRED = 12;
    public static final int CME_SIM_WRONG = 15;
    public static final int CME_SIP_RESPONSE_CODE = 35;
    public static final int CME_TEXT_STRING_TOO_LONG = 24;
    private static final boolean DBG = true;
    public static final String EXTRA_AG_FEATURE_3WAY_CALLING = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_3WAY_CALLING";
    public static final String EXTRA_AG_FEATURE_ACCEPT_HELD_OR_WAITING_CALL = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_ACCEPT_HELD_OR_WAITING_CALL";
    public static final String EXTRA_AG_FEATURE_ATTACH_NUMBER_TO_VT = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_ATTACH_NUMBER_TO_VT";
    public static final String EXTRA_AG_FEATURE_ECC = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_ECC";
    public static final String EXTRA_AG_FEATURE_MERGE = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_MERGE";
    public static final String EXTRA_AG_FEATURE_MERGE_AND_DETACH = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_MERGE_AND_DETACH";
    public static final String EXTRA_AG_FEATURE_REJECT_CALL = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_REJECT_CALL";
    public static final String EXTRA_AG_FEATURE_RELEASE_AND_ACCEPT = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_RELEASE_AND_ACCEPT";
    public static final String EXTRA_AG_FEATURE_RELEASE_HELD_OR_WAITING_CALL = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_RELEASE_HELD_OR_WAITING_CALL";
    public static final String EXTRA_AG_FEATURE_RESPONSE_AND_HOLD = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_RESPONSE_AND_HOLD";
    public static final String EXTRA_AG_FEATURE_VOICE_RECOGNITION = "android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_VOICE_RECOGNITION";
    public static final String EXTRA_AUDIO_WBS = "android.bluetooth.headsetclient.extra.AUDIO_WBS";
    public static final String EXTRA_BATTERY_LEVEL = "android.bluetooth.headsetclient.extra.BATTERY_LEVEL";
    public static final String EXTRA_CALL = "android.bluetooth.headsetclient.extra.CALL";
    public static final String EXTRA_CME_CODE = "android.bluetooth.headsetclient.extra.CME_CODE";
    public static final String EXTRA_IN_BAND_RING = "android.bluetooth.headsetclient.extra.IN_BAND_RING";
    public static final String EXTRA_NETWORK_ROAMING = "android.bluetooth.headsetclient.extra.NETWORK_ROAMING";
    public static final String EXTRA_NETWORK_SIGNAL_STRENGTH = "android.bluetooth.headsetclient.extra.NETWORK_SIGNAL_STRENGTH";
    public static final String EXTRA_NETWORK_STATUS = "android.bluetooth.headsetclient.extra.NETWORK_STATUS";
    public static final String EXTRA_NUMBER = "android.bluetooth.headsetclient.extra.NUMBER";
    public static final String EXTRA_OPERATOR_NAME = "android.bluetooth.headsetclient.extra.OPERATOR_NAME";
    public static final String EXTRA_RESULT_CODE = "android.bluetooth.headsetclient.extra.RESULT_CODE";
    public static final String EXTRA_SUBSCRIBER_INFO = "android.bluetooth.headsetclient.extra.SUBSCRIBER_INFO";
    public static final String EXTRA_VENDOR_EVENT_CODE = "android.bluetooth.headsetclient.extra.VENDOR_EVENT_CODE";
    public static final String EXTRA_VENDOR_EVENT_FULL_ARGS = "android.bluetooth.headsetclient.extra.VENDOR_EVENT_FULL_ARGS";
    public static final String EXTRA_VENDOR_ID = "android.bluetooth.headsetclient.extra.VENDOR_ID";
    public static final String EXTRA_VOICE_RECOGNITION = "android.bluetooth.headsetclient.extra.VOICE_RECOGNITION";
    public static final int STATE_AUDIO_CONNECTED = 2;
    public static final int STATE_AUDIO_CONNECTING = 1;
    public static final int STATE_AUDIO_DISCONNECTED = 0;
    private static final String TAG = "BluetoothHeadsetClient";
    private static final boolean VDBG = false;
    private final BluetoothAdapter mAdapter;
    private final AttributionSource mAttributionSource;
    private final BluetoothProfileConnector<IBluetoothHeadsetClient> mProfileConnector;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothHeadsetClient(Context context, BluetoothProfile.ServiceListener listener, BluetoothAdapter adapter) {
        BluetoothProfileConnector<IBluetoothHeadsetClient> bluetoothProfileConnector = new BluetoothProfileConnector(this, 16, TAG, IBluetoothHeadsetClient.class.getName()) { // from class: android.bluetooth.BluetoothHeadsetClient.1
            @Override // android.bluetooth.BluetoothProfileConnector
            public IBluetoothHeadsetClient getServiceInterface(IBinder service) {
                return IBluetoothHeadsetClient.Stub.asInterface(Binder.allowBlocking(service));
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

    private IBluetoothHeadsetClient getService() {
        return this.mProfileConnector.getService();
    }

    public boolean connect(BluetoothDevice device) {
        log("connect(" + device + ")");
        IBluetoothHeadsetClient service = getService();
        if (service == null || !isEnabled() || !isValidDevice(device)) {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        }
        try {
            return service.connect(device, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    public boolean disconnect(BluetoothDevice device) {
        log("disconnect(" + device + ")");
        IBluetoothHeadsetClient service = getService();
        if (service == null || !isEnabled() || !isValidDevice(device)) {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        }
        try {
            return service.disconnect(device, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, Log.getStackTraceString(new Throwable()));
            return false;
        }
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getConnectedDevices() {
        IBluetoothHeadsetClient service = getService();
        if (service == null || !isEnabled()) {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return new ArrayList();
        }
        try {
            return Attributable.setAttributionSource(service.getConnectedDevices(this.mAttributionSource), this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, Log.getStackTraceString(new Throwable()));
            return new ArrayList();
        }
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        IBluetoothHeadsetClient service = getService();
        if (service == null || !isEnabled()) {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return new ArrayList();
        }
        try {
            return Attributable.setAttributionSource(service.getDevicesMatchingConnectionStates(states, this.mAttributionSource), this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, Log.getStackTraceString(new Throwable()));
            return new ArrayList();
        }
    }

    @Override // android.bluetooth.BluetoothProfile
    public int getConnectionState(BluetoothDevice device) {
        IBluetoothHeadsetClient service = getService();
        if (service == null || !isEnabled() || !isValidDevice(device)) {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return 0;
        }
        try {
            return service.getConnectionState(device, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, Log.getStackTraceString(new Throwable()));
            return 0;
        }
    }

    public boolean setPriority(BluetoothDevice device, int priority) {
        log("setPriority(" + device + ", " + priority + ")");
        return setConnectionPolicy(device, BluetoothAdapter.priorityToConnectionPolicy(priority));
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        log("setConnectionPolicy(" + device + ", " + connectionPolicy + ")");
        IBluetoothHeadsetClient service = getService();
        if (service == null || !isEnabled() || !isValidDevice(device)) {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return false;
        } else if (connectionPolicy != 0 && connectionPolicy != 100) {
            return false;
        } else {
            try {
                return service.setConnectionPolicy(device, connectionPolicy, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
                return false;
            }
        }
    }

    public int getPriority(BluetoothDevice device) {
        return BluetoothAdapter.connectionPolicyToPriority(getConnectionPolicy(device));
    }

    public int getConnectionPolicy(BluetoothDevice device) {
        IBluetoothHeadsetClient service = getService();
        if (service == null || !isEnabled() || !isValidDevice(device)) {
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
            return 0;
        }
        try {
            return service.getConnectionPolicy(device, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, Log.getStackTraceString(new Throwable()));
            return 0;
        }
    }

    public boolean startVoiceRecognition(BluetoothDevice device) {
        log("startVoiceRecognition()");
        IBluetoothHeadsetClient service = getService();
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return service.startVoiceRecognition(device, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return false;
        }
        Log.w(TAG, "Proxy not attached to service");
        return false;
    }

    public boolean sendVendorAtCommand(BluetoothDevice device, int vendorId, String atCommand) {
        log("sendVendorSpecificCommand()");
        IBluetoothHeadsetClient service = getService();
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return service.sendVendorAtCommand(device, vendorId, atCommand, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return false;
        }
        Log.w(TAG, "Proxy not attached to service");
        return false;
    }

    public boolean stopVoiceRecognition(BluetoothDevice device) {
        log("stopVoiceRecognition()");
        IBluetoothHeadsetClient service = getService();
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return service.stopVoiceRecognition(device, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return false;
        }
        Log.w(TAG, "Proxy not attached to service");
        return false;
    }

    public List<BluetoothHeadsetClientCall> getCurrentCalls(BluetoothDevice device) {
        log("getCurrentCalls()");
        IBluetoothHeadsetClient service = getService();
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return Attributable.setAttributionSource(service.getCurrentCalls(device, this.mAttributionSource), this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return null;
        }
        Log.w(TAG, "Proxy not attached to service");
        return null;
    }

    public Bundle getCurrentAgEvents(BluetoothDevice device) {
        log("getCurrentCalls()");
        IBluetoothHeadsetClient service = getService();
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return service.getCurrentAgEvents(device, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return null;
        }
        Log.w(TAG, "Proxy not attached to service");
        return null;
    }

    public boolean acceptCall(BluetoothDevice device, int flag) {
        log("acceptCall()");
        IBluetoothHeadsetClient service = getService();
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return service.acceptCall(device, flag, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return false;
        }
        Log.w(TAG, "Proxy not attached to service");
        return false;
    }

    public boolean holdCall(BluetoothDevice device) {
        log("holdCall()");
        IBluetoothHeadsetClient service = getService();
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return service.holdCall(device, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return false;
        }
        Log.w(TAG, "Proxy not attached to service");
        return false;
    }

    public boolean rejectCall(BluetoothDevice device) {
        log("rejectCall()");
        IBluetoothHeadsetClient service = getService();
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return service.rejectCall(device, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return false;
        }
        Log.w(TAG, "Proxy not attached to service");
        return false;
    }

    public boolean terminateCall(BluetoothDevice device, BluetoothHeadsetClientCall call) {
        log("terminateCall()");
        IBluetoothHeadsetClient service = getService();
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return service.terminateCall(device, call, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return false;
        }
        Log.w(TAG, "Proxy not attached to service");
        return false;
    }

    public boolean enterPrivateMode(BluetoothDevice device, int index) {
        log("enterPrivateMode()");
        IBluetoothHeadsetClient service = getService();
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return service.enterPrivateMode(device, index, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return false;
        }
        Log.w(TAG, "Proxy not attached to service");
        return false;
    }

    public boolean explicitCallTransfer(BluetoothDevice device) {
        log("explicitCallTransfer()");
        IBluetoothHeadsetClient service = getService();
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return service.explicitCallTransfer(device, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return false;
        }
        Log.w(TAG, "Proxy not attached to service");
        return false;
    }

    public BluetoothHeadsetClientCall dial(BluetoothDevice device, String number) {
        log("dial()");
        IBluetoothHeadsetClient service = getService();
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return (BluetoothHeadsetClientCall) Attributable.setAttributionSource(service.dial(device, number, this.mAttributionSource), this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return null;
        }
        Log.w(TAG, "Proxy not attached to service");
        return null;
    }

    public boolean sendDTMF(BluetoothDevice device, byte code) {
        log("sendDTMF()");
        IBluetoothHeadsetClient service = getService();
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return service.sendDTMF(device, code, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return false;
        }
        Log.w(TAG, "Proxy not attached to service");
        return false;
    }

    public boolean getLastVoiceTagNumber(BluetoothDevice device) {
        log("getLastVoiceTagNumber()");
        IBluetoothHeadsetClient service = getService();
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return service.getLastVoiceTagNumber(device, this.mAttributionSource);
            } catch (RemoteException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
        if (service != null) {
            return false;
        }
        Log.w(TAG, "Proxy not attached to service");
        return false;
    }

    public int getAudioState(BluetoothDevice device) {
        IBluetoothHeadsetClient service = getService();
        if (service == null || !isEnabled()) {
            Log.w(TAG, "Proxy not attached to service");
            Log.d(TAG, Log.getStackTraceString(new Throwable()));
            return 0;
        }
        try {
            return service.getAudioState(device, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return 0;
        }
    }

    public void setAudioRouteAllowed(BluetoothDevice device, boolean allowed) {
        IBluetoothHeadsetClient service = getService();
        if (service == null || !isEnabled()) {
            Log.w(TAG, "Proxy not attached to service");
            Log.d(TAG, Log.getStackTraceString(new Throwable()));
            return;
        }
        try {
            service.setAudioRouteAllowed(device, allowed, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
        }
    }

    public boolean getAudioRouteAllowed(BluetoothDevice device) {
        IBluetoothHeadsetClient service = getService();
        if (service == null || !isEnabled()) {
            Log.w(TAG, "Proxy not attached to service");
            Log.d(TAG, Log.getStackTraceString(new Throwable()));
            return false;
        }
        try {
            return service.getAudioRouteAllowed(device, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }

    public boolean connectAudio(BluetoothDevice device) {
        IBluetoothHeadsetClient service = getService();
        if (service == null || !isEnabled()) {
            Log.w(TAG, "Proxy not attached to service");
            Log.d(TAG, Log.getStackTraceString(new Throwable()));
            return false;
        }
        try {
            return service.connectAudio(device, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }

    public boolean disconnectAudio(BluetoothDevice device) {
        IBluetoothHeadsetClient service = getService();
        if (service == null || !isEnabled()) {
            Log.w(TAG, "Proxy not attached to service");
            Log.d(TAG, Log.getStackTraceString(new Throwable()));
            return false;
        }
        try {
            return service.disconnectAudio(device, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }

    public Bundle getCurrentAgFeatures(BluetoothDevice device) {
        IBluetoothHeadsetClient service = getService();
        if (service == null || !isEnabled()) {
            Log.w(TAG, "Proxy not attached to service");
            Log.d(TAG, Log.getStackTraceString(new Throwable()));
            return null;
        }
        try {
            return service.getCurrentAgFeatures(device, this.mAttributionSource);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return null;
        }
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
