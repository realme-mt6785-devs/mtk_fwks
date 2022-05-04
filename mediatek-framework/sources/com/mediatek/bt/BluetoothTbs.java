package com.mediatek.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothManager;
import android.bluetooth.IBluetoothProfileServiceConnection;
import android.bluetooth.IBluetoothStateChangeCallback;
import android.bluetooth.IBluetoothTbs;
import android.bluetooth.IBluetoothTbsCallback;
import android.content.ComponentName;
import android.content.Context;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.util.Log;
import com.mediatek.bt.BluetoothTbs;
import com.mediatek.internal.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class BluetoothTbs implements BluetoothProfile {
    public static final String ACTION_AUDIO_STATE_CHANGED = "android.bluetooth.tbs.profile.action.AUDIO_STATE_CHANGED";
    public static final int CAPABILITY_HOLD_CALL = 1;
    public static final int CAPABILITY_JOIN_CALLS = 2;
    private static final boolean DBG = true;
    private static final int MESSAGE_TBS_SERVICE_CONNECTED = 102;
    private static final int MESSAGE_TBS_SERVICE_DISCONNECTED = 103;
    private static final int REG_TIMEOUT = 1000;
    public static final int RESULT_ERROR_APPLICATION = 3;
    public static final int RESULT_ERROR_INVALID_URI = 2;
    public static final int RESULT_ERROR_UNKNOWN_CALL_ID = 1;
    public static final int RESULT_SUCCESS = 0;
    public static final int STATE_AUDIO_CONNECTED = 12;
    public static final int STATE_AUDIO_CONNECTING = 11;
    public static final int STATE_AUDIO_DISCONNECTED = 10;
    private static final String TAG = "BluetoothTbs";
    public static final int TERMINATION_REASON_CLIENT_HANGUP = 6;
    public static final int TERMINATION_REASON_FAIL = 1;
    public static final int TERMINATION_REASON_INVALID_URI = 0;
    public static final int TERMINATION_REASON_LINE_BUSY = 4;
    public static final int TERMINATION_REASON_NETWORK_CONGESTION = 5;
    public static final int TERMINATION_REASON_NO_ANSWER = 8;
    public static final int TERMINATION_REASON_NO_SERVICE = 7;
    public static final int TERMINATION_REASON_REMOTE_HANGUP = 2;
    public static final int TERMINATION_REASON_SERVER_HANGUP = 3;
    private static final boolean VDBG = true;
    private final IBluetoothStateChangeCallback mBluetoothStateChangeCallback;
    private Context mContext;
    private volatile IBluetoothTbs mService;
    private BluetoothProfile.ServiceListener mServiceListener;
    private String mToken;
    private int mCcid = 0;
    private Callback mCallback = null;
    private Object mServerIfLock = new Object();
    private final IBluetoothProfileServiceConnection mConnection = new IBluetoothProfileServiceConnection.Stub() { // from class: com.mediatek.bt.BluetoothTbs.2
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.d(BluetoothTbs.TAG, "Proxy object connected");
            BluetoothTbs.this.mService = IBluetoothTbs.Stub.asInterface(Binder.allowBlocking(service));
            BluetoothTbs.this.mHandler.sendMessage(BluetoothTbs.this.mHandler.obtainMessage(102));
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.d(BluetoothTbs.TAG, "Proxy object disconnected");
            BluetoothTbs.this.doUnbind();
            BluetoothTbs.this.mHandler.sendMessage(BluetoothTbs.this.mHandler.obtainMessage(103));
        }
    };
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.mediatek.bt.BluetoothTbs.3
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            Log.d(BluetoothTbs.TAG, "handleMessage: msg.what=" + msg.what);
            switch (msg.what) {
                case 102:
                    if (BluetoothTbs.this.mServiceListener != null) {
                        BluetoothTbs.this.mServiceListener.onServiceConnected(26, BluetoothTbs.this);
                        return;
                    }
                    return;
                case 103:
                    if (BluetoothTbs.this.mServiceListener != null) {
                        BluetoothTbs.this.mServiceListener.onServiceDisconnected(26);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Result {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface TerminationReason {
    }

    /* loaded from: classes.dex */
    public static abstract class Callback {
        private static final String TAG = "BluetoothTbs.Callback";

        public abstract void onAcceptCall(int i, UUID uuid);

        public abstract void onPlaceCall(int i, UUID uuid, String str);

        public abstract void onTerminateCall(int i, UUID uuid);

        public void onHoldCall(int requestId, UUID callId) {
            Log.e(TAG, "onHoldCall: unimplemented, however CAPABILITY_HOLD_CALL is set!");
        }

        public void onUnholdCall(int requestId, UUID callId) {
            Log.e(TAG, "onUnholdCall: unimplemented, however CAPABILITY_HOLD_CALL is set!");
        }

        public void onJoinCalls(int requestId, List<UUID> callIds) {
            Log.e(TAG, "onUnholdCall: unimplemented, however CAPABILITY_JOIN_CALLS is set!");
        }
    }

    /* loaded from: classes.dex */
    private class CallbackWrapper extends IBluetoothTbsCallback.Stub {
        private final Callback mCallback;
        private final Executor mExecutor;

        CallbackWrapper(Executor executor, Callback callback) {
            this.mExecutor = executor;
            this.mCallback = callback;
        }

        public void onBearerRegistered(int ccid) {
            Log.d(BluetoothTbs.TAG, "onBearerRegistered(): ccid=" + ccid);
            synchronized (BluetoothTbs.this.mServerIfLock) {
                if (this.mCallback != null) {
                    BluetoothTbs.this.mCcid = ccid;
                    Log.d(BluetoothTbs.TAG, "onBearerRegistered(): notifyAll");
                    BluetoothTbs.this.mServerIfLock.notifyAll();
                } else {
                    Log.e(BluetoothTbs.TAG, "onBearerRegistered: mCallback is null");
                }
            }
        }

        public void onAcceptCall(final int requestId, final ParcelUuid uuid) {
            Log.d(BluetoothTbs.TAG, "onAcceptCall(): requestId=" + requestId);
            clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: com.mediatek.bt.BluetoothTbs$CallbackWrapper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothTbs.CallbackWrapper.this.lambda$onAcceptCall$0$BluetoothTbs$CallbackWrapper(requestId, uuid);
                }
            });
        }

        public /* synthetic */ void lambda$onAcceptCall$0$BluetoothTbs$CallbackWrapper(int requestId, ParcelUuid uuid) {
            this.mCallback.onAcceptCall(requestId, uuid.getUuid());
        }

        public void onTerminateCall(final int requestId, final ParcelUuid uuid) {
            Log.d(BluetoothTbs.TAG, "onTerminateCall(): requestId=" + requestId);
            clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: com.mediatek.bt.BluetoothTbs$CallbackWrapper$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothTbs.CallbackWrapper.this.lambda$onTerminateCall$1$BluetoothTbs$CallbackWrapper(requestId, uuid);
                }
            });
        }

        public /* synthetic */ void lambda$onTerminateCall$1$BluetoothTbs$CallbackWrapper(int requestId, ParcelUuid uuid) {
            this.mCallback.onTerminateCall(requestId, uuid.getUuid());
        }

        public void onHoldCall(final int requestId, final ParcelUuid uuid) {
            Log.d(BluetoothTbs.TAG, "onHoldCall(): requestId=" + requestId);
            clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: com.mediatek.bt.BluetoothTbs$CallbackWrapper$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothTbs.CallbackWrapper.this.lambda$onHoldCall$2$BluetoothTbs$CallbackWrapper(requestId, uuid);
                }
            });
        }

        public /* synthetic */ void lambda$onHoldCall$2$BluetoothTbs$CallbackWrapper(int requestId, ParcelUuid uuid) {
            this.mCallback.onHoldCall(requestId, uuid.getUuid());
        }

        public void onUnholdCall(final int requestId, final ParcelUuid uuid) {
            Log.d(BluetoothTbs.TAG, "onUnholdCall(): requestId=" + requestId);
            clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: com.mediatek.bt.BluetoothTbs$CallbackWrapper$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothTbs.CallbackWrapper.this.lambda$onUnholdCall$3$BluetoothTbs$CallbackWrapper(requestId, uuid);
                }
            });
        }

        public /* synthetic */ void lambda$onUnholdCall$3$BluetoothTbs$CallbackWrapper(int requestId, ParcelUuid uuid) {
            this.mCallback.onUnholdCall(requestId, uuid.getUuid());
        }

        public void onPlaceCall(final int requestId, final ParcelUuid uuid, final String uri) {
            Log.d(BluetoothTbs.TAG, "onPlaceCall(): requestId=" + requestId);
            clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: com.mediatek.bt.BluetoothTbs$CallbackWrapper$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothTbs.CallbackWrapper.this.lambda$onPlaceCall$4$BluetoothTbs$CallbackWrapper(requestId, uuid, uri);
                }
            });
        }

        public /* synthetic */ void lambda$onPlaceCall$4$BluetoothTbs$CallbackWrapper(int requestId, ParcelUuid uuid, String uri) {
            this.mCallback.onPlaceCall(requestId, uuid.getUuid(), uri);
        }

        public void onJoinCalls(final int requestId, List<ParcelUuid> parcelUuids) {
            Log.d(BluetoothTbs.TAG, "onJoinCalls(): requestId=" + requestId);
            final List<UUID> uuids = new ArrayList<>();
            for (ParcelUuid parcelUuid : parcelUuids) {
                uuids.add(parcelUuid.getUuid());
            }
            clearCallingIdentity();
            this.mExecutor.execute(new Runnable() { // from class: com.mediatek.bt.BluetoothTbs$CallbackWrapper$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    BluetoothTbs.CallbackWrapper.this.lambda$onJoinCalls$5$BluetoothTbs$CallbackWrapper(requestId, uuids);
                }
            });
        }

        public /* synthetic */ void lambda$onJoinCalls$5$BluetoothTbs$CallbackWrapper(int requestId, List uuids) {
            this.mCallback.onJoinCalls(requestId, uuids);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothTbs(Context context, BluetoothProfile.ServiceListener listener) {
        IBluetoothStateChangeCallback.Stub stub = new IBluetoothStateChangeCallback.Stub() { // from class: com.mediatek.bt.BluetoothTbs.1
            public void onBluetoothStateChange(boolean up) {
                Log.d(BluetoothTbs.TAG, "onBluetoothStateChange: up=" + up);
                if (!up) {
                    BluetoothTbs.this.doUnbind();
                } else {
                    BluetoothTbs.this.doBind();
                }
            }
        };
        this.mBluetoothStateChangeCallback = stub;
        this.mContext = context;
        this.mServiceListener = listener;
        Log.d(TAG, "BluetoothTbs()");
        IBluetoothManager mgr = this.mAdapter.getBluetoothManager();
        if (mgr != null) {
            try {
                mgr.registerStateChangeCallback(stub);
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
        }
        doBind();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean doBind() {
        Log.d(TAG, "doBind()");
        synchronized (this.mConnection) {
            if (this.mService == null) {
                Log.d(TAG, "Binding service...");
                try {
                    return this.mAdapter.getBluetoothManager().bindBluetoothProfileService(26, this.mConnection);
                } catch (RemoteException e) {
                    Log.e(TAG, "Unable to bind TelephoneBearerService", e);
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doUnbind() {
        Log.d(TAG, "doUnbind()");
        synchronized (this.mConnection) {
            if (this.mService != null) {
                Log.d(TAG, "Unbinding service...");
                try {
                    this.mAdapter.getBluetoothManager().unbindBluetoothProfileService(26, this.mConnection);
                } catch (RemoteException e) {
                    Log.e(TAG, "Unable to unbind TelephoneBearerService", e);
                }
                this.mService = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void close() {
        log("close()");
        unregisterBearer();
        IBluetoothManager mgr = this.mAdapter.getBluetoothManager();
        if (mgr != null) {
            try {
                mgr.unregisterStateChangeCallback(this.mBluetoothStateChangeCallback);
            } catch (RemoteException re) {
                Log.e(TAG, "", re);
            }
        }
        this.mServiceListener = null;
        doUnbind();
    }

    private IBluetoothTbs getService() {
        return this.mService;
    }

    @Override // android.bluetooth.BluetoothProfile
    public int getConnectionState(BluetoothDevice device) {
        throw new UnsupportedOperationException("not supported");
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getConnectedDevices() {
        throw new UnsupportedOperationException("not supported");
    }

    @Override // android.bluetooth.BluetoothProfile
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        throw new UnsupportedOperationException("not supported");
    }

    public boolean registerBearer(String uci, List<String> uriSchemes, int capabilities, String provider, int technology, Executor executor, Callback callback) {
        Log.d(TAG, "registerBearer in: mCcid=" + this.mCcid);
        if (callback == null) {
            throw new IllegalArgumentException("null parameter: " + callback);
        } else if (this.mCcid != 0) {
            return false;
        } else {
            this.mToken = uci;
            IBluetoothTbs service = getService();
            if (service != null) {
                synchronized (this.mServerIfLock) {
                    if (this.mCallback != null) {
                        Log.e(TAG, "Bearer can be opened only once");
                        return false;
                    }
                    this.mCallback = callback;
                    try {
                        CallbackWrapper callbackWrapper = new CallbackWrapper(executor, callback);
                        Log.d(TAG, "registerBearer: callbackWrapper=" + callbackWrapper);
                        service.registerBearer(this.mToken, callbackWrapper, uci, uriSchemes, capabilities, provider, technology);
                        Log.d(TAG, "registerBearer out: mCcid=" + this.mCcid);
                        if (this.mCcid == 0) {
                            try {
                                Log.d(TAG, "registerBearer(): wait");
                                this.mServerIfLock.wait(1000L);
                            } catch (InterruptedException e) {
                                Log.e(TAG, "" + e);
                                this.mCallback = null;
                            }
                            if (this.mCcid == 0) {
                                this.mCallback = null;
                                return false;
                            }
                        }
                        return true;
                    } catch (RemoteException e2) {
                        Log.e(TAG, "", e2);
                        this.mCallback = null;
                        return false;
                    }
                }
            } else {
                if (service == null) {
                    Log.w(TAG, "Proxy not attached to service");
                }
                return false;
            }
        }
    }

    public void unregisterBearer() {
        Log.d(TAG, "unregisterBearer mCcid=" + this.mCcid);
        if (this.mCcid != 0) {
            int i = this.mCcid;
            this.mCcid = 0;
            this.mCallback = null;
            IBluetoothTbs service = getService();
            if (service != null) {
                try {
                    service.unregisterBearer(this.mToken);
                } catch (RemoteException e) {
                    Log.e(TAG, "", e);
                }
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        }
    }

    public int getContentControlId() {
        return this.mCcid;
    }

    public void onCallAdded(BluetoothTbsCall call) {
        Log.d(TAG, "onCallAdded: call=" + call + " mCcid=" + this.mCcid);
        if (this.mCcid != 0) {
            IBluetoothTbs service = getService();
            if (service != null) {
                try {
                    service.callAdded(this.mCcid, call);
                } catch (RemoteException e) {
                    Log.e(TAG, "", e);
                }
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        }
    }

    public void onCallRemoved(UUID callId, int reason) {
        Log.d(TAG, "callRemoved: callId=" + callId + " mCcid=" + this.mCcid);
        if (this.mCcid != 0) {
            IBluetoothTbs service = getService();
            if (service != null) {
                try {
                    service.callRemoved(this.mCcid, new ParcelUuid(callId), reason);
                } catch (RemoteException e) {
                    Log.e(TAG, "", e);
                }
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        }
    }

    public void onCallStateChanged(UUID callId, int state) {
        Log.d(TAG, "callStateChanged: callId=" + callId + " state=" + state + " mCcid=" + this.mCcid);
        if (this.mCcid != 0) {
            IBluetoothTbs service = getService();
            if (service != null) {
                try {
                    service.callStateChanged(this.mCcid, new ParcelUuid(callId), state);
                } catch (RemoteException e) {
                    Log.e(TAG, "", e);
                }
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        }
    }

    public void currentCallsList(List<BluetoothTbsCall> calls) {
        Log.d(TAG, "currentCallsList() mCcid=" + this.mCcid);
        IBluetoothTbs service = getService();
        if (service != null) {
            try {
                service.currentCallsList(this.mCcid, calls);
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
        }
    }

    public void networkStateChanged(String provider, int technology) {
        Log.d(TAG, "networkStateChanged: provider=" + provider + ", technology=" + technology + ", mCcid=" + this.mCcid);
        if (this.mCcid != 0) {
            IBluetoothTbs service = getService();
            if (service != null) {
                try {
                    service.networkStateChanged(this.mCcid, provider, technology);
                } catch (RemoteException e) {
                    Log.e(TAG, "", e);
                }
            }
            if (service == null) {
                Log.w(TAG, "Proxy not attached to service");
            }
        }
    }

    public void phoneStateChanged(int numActive, int numHeld, int callState, String number, int type, String name) {
        Log.d(TAG, "phoneStateChanged: numActive=" + numActive + ", numHeld=" + numHeld + ", callState=" + callState + ", number=" + number + ", type=" + type + ", name=" + name);
        IBluetoothTbs service = getService();
        if (service != null) {
            try {
                service.phoneStateChanged(numActive, numHeld, callState, number, type, name);
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
        }
        if (service == null) {
            Log.w(TAG, "Proxy not attached to service");
        }
    }

    public boolean isInbandRingingEnabled() {
        Log.d(TAG, "isInbandRingingEnabled()");
        IBluetoothTbs service = this.mService;
        if (service != null && isEnabled()) {
            try {
                return service.isInbandRingingEnabled();
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

    public static boolean isInbandRingingSupported(Context context) {
        return context.getResources().getBoolean(R.bool.config_bluetooth_tbs_inband_ringing_support);
    }

    public void requestResult(int requestId, int result) {
        IBluetoothTbs service;
        Log.d(TAG, "requestResult: requestId=" + requestId + " result=" + result + " mCcid=" + this.mCcid);
        if (this.mCcid != 0 && (service = getService()) != null) {
            try {
                service.requestResult(this.mCcid, requestId, result);
            } catch (RemoteException e) {
                Log.e(TAG, "", e);
            }
        }
    }

    public boolean startVoiceRecognition(BluetoothDevice device) {
        log("startVoiceRecognition()");
        IBluetoothTbs service = this.mService;
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return service.startVoiceRecognition(device);
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
        IBluetoothTbs service = this.mService;
        if (service != null && isEnabled() && isValidDevice(device)) {
            try {
                return service.stopVoiceRecognition(device);
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

    public boolean isAudioOn() {
        log("isAudioOn()");
        IBluetoothTbs service = this.mService;
        if (service != null && isEnabled()) {
            try {
                return service.isAudioOn();
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

    public boolean connectAudio() {
        log("connectAudio()");
        IBluetoothTbs service = this.mService;
        if (service == null || !isEnabled()) {
            Log.w(TAG, "Proxy not attached to service");
            Log.d(TAG, Log.getStackTraceString(new Throwable()));
            return false;
        }
        try {
            return service.connectAudio();
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }

    public boolean disconnectAudio() {
        log("disconnectAudio()");
        IBluetoothTbs service = this.mService;
        if (service == null || !isEnabled()) {
            Log.w(TAG, "Proxy not attached to service");
            Log.d(TAG, Log.getStackTraceString(new Throwable()));
            return false;
        }
        try {
            return service.disconnectAudio();
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }

    public boolean startIsoUsingVirtualVoiceCall() {
        log("startIsoUsingVirtualVoiceCall()");
        IBluetoothTbs service = this.mService;
        if (service == null || !isEnabled()) {
            Log.w(TAG, "Proxy not attached to service");
            Log.d(TAG, Log.getStackTraceString(new Throwable()));
            return false;
        }
        try {
            return service.startIsoUsingVirtualVoiceCall();
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }

    public boolean stopIsoUsingVirtualVoiceCall() {
        log("stopIsoUsingVirtualVoiceCall()");
        IBluetoothTbs service = this.mService;
        if (service == null || !isEnabled()) {
            Log.w(TAG, "Proxy not attached to service");
            Log.d(TAG, Log.getStackTraceString(new Throwable()));
            return false;
        }
        try {
            return service.stopIsoUsingVirtualVoiceCall();
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }

    public int getAudioState(BluetoothDevice device) {
        log("getAudioState");
        IBluetoothTbs service = this.mService;
        if (service == null || isDisabled()) {
            Log.w(TAG, "Proxy not attached to service");
            Log.d(TAG, Log.getStackTraceString(new Throwable()));
            return 10;
        }
        try {
            return service.getAudioState(device);
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return 10;
        }
    }

    private boolean isEnabled() {
        return this.mAdapter.getState() == 12;
    }

    private boolean isDisabled() {
        return this.mAdapter.getState() == 10;
    }

    private static boolean isValidDevice(BluetoothDevice device) {
        return device != null && BluetoothAdapter.checkBluetoothAddress(device.getAddress());
    }

    private static void log(String msg) {
        Log.d(TAG, msg);
    }
}
