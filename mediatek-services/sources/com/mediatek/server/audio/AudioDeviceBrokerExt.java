package com.mediatek.server.audio;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioDeviceAttributes;
import android.media.AudioDeviceInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import com.android.server.audio.AudioDeviceInventory;
import com.android.server.audio.AudioService;
import com.android.server.audio.AudioSystemAdapter;
import com.android.server.audio.SystemServerAdapter;
import com.mediatek.bt.BluetoothTbs;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class AudioDeviceBrokerExt {
    static final int BT_LE_TBS_CNCT_TIMEOUT_MS = 3000;
    private static final int DEFAULT_ABS_VOL_IDX_DELAY_MS = 200;
    private static final int MSG_BT_LE_TBS_CNCT_FAILED = 108;
    private static final int MSG_DISCONNECT_BLE_TBS = 106;
    private static final int MSG_DISCONNECT_BT_LE = 103;
    private static final int MSG_IIL_SET_FORCE_BT_A2DP_USE = 5;
    private static final int MSG_IL_SET_LE_AUDIO_CONNECTION_STATE = 100;
    private static final int MSG_I_BROADCAST_BT_LE_CG_CONNECTION_STATE = 109;
    private static final int MSG_I_SET_LE_CG_VC_ABSOLUTE_VOLUME = 107;
    private static final int MSG_I_SET_LE_VC_ABSOLUTE_VOLUME = 104;
    private static final int MSG_L_BT_SERVICE_CONNECTED_PROFILE_LE = 102;
    private static final int MSG_L_BT_SERVICE_CONNECTED_PROFILE_LE_TBS = 105;
    private static final int MSG_L_LE_AUDIO_DEVICE_CONNECTION_CHANGE_EXT = 101;
    private static final int MSG_L_UPDATE_COMMUNICATION_ROUTE = 39;
    private static final int SENDMSG_NOOP = 1;
    private static final int SENDMSG_QUEUE = 2;
    private static final int SENDMSG_REPLACE = 0;
    private static final String TAG = "AS.AudioDeviceBrokerExt";
    private Method addCommunicationRouteClientMethod;
    private Method getCommunicationDeviceMethod;
    private Method getCommunicationRouteClientForPidMethod;
    private Method getContentResolverMethod;
    private Method handleDeviceConnectionMethod;
    private Method isDeviceActiveForCommunicationMethod;
    private Method isDeviceRequestedForCommunicationMethod;
    private final AudioService mAudioService;
    private AudioServiceExtImpl mAudioServiceExtImpl;
    private boolean mBluetoothLeCgOn;
    private boolean mBluetoothLeDeviceStatus;
    private Handler mBrokerHandler;
    private final BtHelperExt mBtHelperExt;
    private final Context mContext;
    private final Object mDeviceBroker;
    private final AudioDeviceInventory mDeviceInventory;
    private final AudioDeviceInventoryExt mDeviceInventoryExt;
    private Object mDeviceStateLock;
    private AudioDeviceAttributes mPreferredCommunicationDevice;
    private Object mSetModeLock;
    private final SystemServerAdapter mSystemServer;
    private Method onSetForceUseMethod;
    private Method removeCommunicationRouteClientMethod;
    private Method sendIILMsgMethod;
    private static final Object sLastBLeCgDeviceConnectTimeLock = new Object();
    private static long sLastBLeCgDeviceConnectTime = 0;
    private Method removeMessagesMethod = null;
    private Field mDeviceStateLockField = null;
    private Field mSetModeLockField = null;
    private Field mDeviceInventoryField = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioDeviceBrokerExt(Context context, AudioService service, AudioServiceExtImpl serviceExtImpl, AudioSystemAdapter audioSystem, Object deviceBroker, SystemServerAdapter systemServer) {
        this.mAudioServiceExtImpl = null;
        this.sendIILMsgMethod = null;
        this.onSetForceUseMethod = null;
        this.handleDeviceConnectionMethod = null;
        this.isDeviceRequestedForCommunicationMethod = null;
        this.isDeviceActiveForCommunicationMethod = null;
        this.getCommunicationRouteClientForPidMethod = null;
        this.getCommunicationDeviceMethod = null;
        this.addCommunicationRouteClientMethod = null;
        this.removeCommunicationRouteClientMethod = null;
        this.getContentResolverMethod = null;
        this.mDeviceStateLock = null;
        this.mSetModeLock = null;
        this.mBrokerHandler = null;
        this.mContext = context;
        this.mAudioService = service;
        this.mAudioServiceExtImpl = serviceExtImpl;
        this.mBtHelperExt = new BtHelperExt(deviceBroker, this);
        this.mDeviceBroker = deviceBroker;
        this.mSystemServer = systemServer;
        this.sendIILMsgMethod = ReflectionHelper.getMethod(deviceBroker, "sendIILMsg", Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Object.class, Integer.TYPE);
        this.mBrokerHandler = (Handler) ReflectionHelper.getFieldObject(deviceBroker, "mBrokerHandler", new Class[0]);
        this.getContentResolverMethod = ReflectionHelper.getMethod(deviceBroker, "getContentResolver", new Class[0]);
        this.onSetForceUseMethod = ReflectionHelper.getMethod(deviceBroker, "onSetForceUse", Integer.TYPE, Integer.TYPE, Boolean.TYPE, String.class);
        this.handleDeviceConnectionMethod = ReflectionHelper.getMethod(deviceBroker, "handleDeviceConnection", Boolean.TYPE, Integer.TYPE, String.class, String.class);
        this.isDeviceRequestedForCommunicationMethod = ReflectionHelper.getMethod(deviceBroker, "isDeviceRequestedForCommunication", Integer.TYPE);
        this.isDeviceActiveForCommunicationMethod = ReflectionHelper.getMethod(deviceBroker, "isDeviceActiveForCommunication", Integer.TYPE);
        this.getCommunicationDeviceMethod = ReflectionHelper.getMethod(deviceBroker, "getCommunicationDevice", new Class[0]);
        this.getCommunicationRouteClientForPidMethod = ReflectionHelper.getMethod(deviceBroker, "getCommunicationRouteClientForPid", Integer.TYPE);
        this.addCommunicationRouteClientMethod = ReflectionHelper.getMethod(deviceBroker, "addCommunicationRouteClient", IBinder.class, Integer.TYPE, AudioDeviceAttributes.class);
        this.removeCommunicationRouteClientMethod = ReflectionHelper.getMethod(deviceBroker, "removeCommunicationRouteClient", IBinder.class, Boolean.TYPE);
        this.mDeviceStateLock = ReflectionHelper.getFieldObject(deviceBroker, "mDeviceStateLock", new Class[0]);
        this.mSetModeLock = ReflectionHelper.getFieldObject(deviceBroker, "mSetModeLock", new Class[0]);
        AudioDeviceInventory audioDeviceInventory = (AudioDeviceInventory) ReflectionHelper.getFieldObject(deviceBroker, "mDeviceInventory", new Class[0]);
        this.mDeviceInventory = audioDeviceInventory;
        this.mDeviceInventoryExt = new AudioDeviceInventoryExt(service, audioSystem, deviceBroker, this, audioDeviceInventory);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Context getContext() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onSystemReady() {
        synchronized (this.mSetModeLock) {
            synchronized (this.mDeviceStateLock) {
                this.mBtHelperExt.onSystemReady();
            }
        }
    }

    private Object addCommunicationRouteClient(IBinder cb, int pid, AudioDeviceAttributes device) {
        return ReflectionHelper.callMethod(this.addCommunicationRouteClientMethod, this.mDeviceBroker, cb, Integer.valueOf(pid), device);
    }

    private Object removeCommunicationRouteClient(IBinder cb, boolean unregister) {
        return ReflectionHelper.callMethod(this.removeCommunicationRouteClientMethod, this.mDeviceBroker, cb, Boolean.valueOf(unregister));
    }

    private Object getCommunicationRouteClientForPid(int pid) {
        return ReflectionHelper.callMethod(this.getCommunicationRouteClientForPidMethod, this.mDeviceBroker, Integer.valueOf(pid));
    }

    private boolean isDeviceActiveForCommunication(int deviceType) {
        return ((Boolean) ReflectionHelper.callMethod(this.isDeviceActiveForCommunicationMethod, this.mDeviceBroker, Integer.valueOf(deviceType))).booleanValue();
    }

    private boolean isDeviceRequestedForCommunication(int deviceType) {
        return ((Boolean) ReflectionHelper.callMethod(this.isDeviceRequestedForCommunicationMethod, this.mDeviceBroker, Integer.valueOf(deviceType))).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBluetoothLeCgOn(boolean on, String eventSource) {
        if (AudioServiceExtImpl.DEBUG_DEVICES) {
            Log.v(TAG, "setBluetoothLeCgOn: " + on + " " + eventSource);
        }
        if (!on) {
            this.mAudioServiceExtImpl.resetBluetoothLeCgOfApp();
        }
        synchronized (this.mDeviceStateLock) {
            this.mBluetoothLeCgOn = on;
            sendLMsgNoDelay(MSG_L_UPDATE_COMMUNICATION_ROUTE, 2, eventSource);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isBluetoothLeCgOn() {
        boolean z;
        synchronized (this.mDeviceStateLock) {
            z = this.mBluetoothLeCgOn;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isBluetoothLeTbsDeviceActive() {
        boolean isBluetoothLeTbsDeviceActive;
        synchronized (this.mDeviceStateLock) {
            isBluetoothLeTbsDeviceActive = this.mBtHelperExt.isBluetoothLeTbsDeviceActive();
        }
        return isBluetoothLeTbsDeviceActive;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startBluetoothLeCgForClient(IBinder cb, int pid, int LeAudioMode, String eventSource) {
        if (AudioServiceExtImpl.DEBUG_DEVICES) {
            Log.v(TAG, "startBluetoothLeCsForClient_Sync, pid: " + pid);
        }
        synchronized (this.mSetModeLock) {
            synchronized (this.mDeviceStateLock) {
                AudioDeviceAttributes device = new AudioDeviceAttributes(16777216, "");
                setCommunicationRouteForStartCsClient(cb, pid, device, LeAudioMode, eventSource);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean stopBluetoothLeCgForClient(IBinder cb, int pid, String eventSource) {
        boolean leCGStatus;
        if (AudioServiceExtImpl.DEBUG_DEVICES) {
            Log.v(TAG, "stopBluetoothLeCgForClient, pid: " + pid);
        }
        synchronized (this.mSetModeLock) {
            synchronized (this.mDeviceStateLock) {
                Object client = getCommunicationRouteClientForPid(pid);
                boolean leCGStatus2 = false;
                if (client != null) {
                    Method getDeviceMethod = ReflectionHelper.getMethod(client, "getDevice", new Class[0]);
                    AudioDeviceAttributes mDevice = (AudioDeviceAttributes) ReflectionHelper.callMethod(getDeviceMethod, client, new Object[0]);
                    if (mDevice != null && mDevice.getType() == 21) {
                        leCGStatus2 = true;
                    }
                    if (mDevice == null || mDevice.getType() != 2 || !this.mBtHelperExt.isBluetoothLeCgOn()) {
                        leCGStatus = leCGStatus2;
                    } else {
                        Log.w(TAG, "stopBluetoothLeCgForClient, preferred device is speaker,turn off CG, pid: " + pid);
                        this.mBtHelperExt.stopBluetoothLeCg(eventSource);
                        sendLMsgNoDelay(MSG_L_UPDATE_COMMUNICATION_ROUTE, 2, eventSource);
                        return true;
                    }
                } else {
                    leCGStatus = false;
                }
                if (client == null || !leCGStatus) {
                    Log.w(TAG, "stopBluetoothLeCgForClient CG is OFF,failed, pid: " + pid);
                    return false;
                }
                setCommunicationRouteForStopCsClient(cb, pid, null, -1, eventSource);
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isBluetoothLeCgRequested() {
        return isDeviceRequestedForCommunication(21);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isBluetoothLeCgActive() {
        if (!this.mBtHelperExt.isBluetoothLeCgOn()) {
            return false;
        }
        boolean activeDeviceStatus = false;
        boolean preferredDeviceStatus = false;
        AudioDeviceInfo mActiveCommunicationDevice = (AudioDeviceInfo) ReflectionHelper.getFieldObject(this.mDeviceBroker, "mActiveCommunicationDevice", new Class[0]);
        if (mActiveCommunicationDevice == null) {
            mActiveCommunicationDevice = getCommunicationDevice();
        }
        if (mActiveCommunicationDevice != null) {
            int deviceType = mActiveCommunicationDevice.getType();
            activeDeviceStatus = deviceType == 7 || deviceType == 21;
            Log.d(TAG, "ActiveDevice=" + deviceType);
        }
        AudioDeviceAttributes mPrefComDev = (AudioDeviceAttributes) ReflectionHelper.getFieldObject(this.mDeviceBroker, "mPreferredCommunicationDevice", new Class[0]);
        if (mPrefComDev != null) {
            int prefdeviceType = mPrefComDev.getType();
            preferredDeviceStatus = prefdeviceType == 21;
            Log.d(TAG, "mPreferredCommunicationDevice=" + prefdeviceType);
        }
        Log.d(TAG, "activeDeviceStatus=" + activeDeviceStatus + ",preferredDeviceStatus=" + preferredDeviceStatus);
        return activeDeviceStatus && preferredDeviceStatus;
    }

    AudioDeviceInfo getCommunicationDevice() {
        return (AudioDeviceInfo) ReflectionHelper.callMethod(this.getCommunicationDeviceMethod, this.mDeviceBroker, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBleCallVcSupportsAbsoluteVolume(boolean support) {
        this.mAudioServiceExtImpl.setBleCallVcSupportsAbsoluteVolume(support);
    }

    void setCommunicationRouteForStartCsClient(IBinder cb, int pid, AudioDeviceAttributes device, int LeCgAudioMode, String eventSource) {
        if (AudioServiceExtImpl.DEBUG_DEVICES) {
            Log.v(TAG, "setCommunicationRouteForStartCsClient: device: " + device);
        }
        AudioDeviceAttributes prevClientDevice = null;
        Object client = getCommunicationRouteClientForPid(pid);
        if (client != null) {
            Method getDeviceMethod = ReflectionHelper.getMethod(client, "getDevice", new Class[0]);
            prevClientDevice = (AudioDeviceAttributes) ReflectionHelper.callMethod(getDeviceMethod, client, new Object[0]);
        }
        if (device != null && (client = addCommunicationRouteClient(cb, pid, device)) == null) {
            Log.w(TAG, "setCommunicationRouteForStartCsClient: could not add client for pid: " + pid + " and device: " + device);
        }
        if (client != null) {
            if (!this.mBtHelperExt.isBluetoothLeCgOn() && !this.mBtHelperExt.startBluetoothLeCg(LeCgAudioMode, eventSource)) {
                Log.w(TAG, "setCommunicationRouteForStartCsClient: failure to start BLE-CG for pid: " + pid);
                if (prevClientDevice != null) {
                    addCommunicationRouteClient(cb, pid, prevClientDevice);
                } else {
                    removeCommunicationRouteClient(cb, true);
                }
                postBroadcastLeCgConnectionState(0);
            }
            sendLMsgNoDelay(MSG_L_UPDATE_COMMUNICATION_ROUTE, 2, eventSource);
        }
    }

    void setCommunicationRouteForStopCsClient(IBinder cb, int pid, AudioDeviceAttributes device, int LeCgAudioMode, String eventSource) {
        if (AudioServiceExtImpl.DEBUG_DEVICES) {
            Log.v(TAG, "setCommunicationRouteForStopCsClient: device: " + device);
        }
        isBluetoothLeCgRequested();
        if (device == null) {
            Object client = removeCommunicationRouteClient(cb, true);
            if (client == null) {
                return;
            }
        }
        if (this.mBtHelperExt.isBluetoothLeCgOn()) {
            this.mBtHelperExt.stopBluetoothLeCg(eventSource);
        }
        sendLMsgNoDelay(MSG_L_UPDATE_COMMUNICATION_ROUTE, 2, eventSource);
    }

    void setCommunicationRouteForClient(IBinder cb, int pid, AudioDeviceAttributes device, int LeCgAudioMode, String eventSource) {
        Object client;
        if (AudioServiceExtImpl.DEBUG_DEVICES) {
            Log.v(TAG, "setCommunicationRouteForClient: device: " + device);
        }
        boolean wasBLeCgRequested = isBluetoothLeCgRequested();
        AudioDeviceAttributes prevClientDevice = null;
        Object client2 = getCommunicationRouteClientForPid(pid);
        if (client2 != null) {
            Method getDeviceMethod = ReflectionHelper.getMethod(client2, "getDevice", new Class[0]);
            prevClientDevice = (AudioDeviceAttributes) ReflectionHelper.callMethod(getDeviceMethod, client2, new Object[0]);
        }
        if (device != null) {
            client = addCommunicationRouteClient(cb, pid, device);
            if (client == null) {
                Log.w(TAG, "setCommunicationRouteForClient: could not add client for pid: " + pid + " and device: " + device);
            }
        } else {
            client = removeCommunicationRouteClient(cb, true);
        }
        if (client != null) {
            boolean isBtLeCgRequested = isBluetoothLeCgRequested();
            if (!isBtLeCgRequested || wasBLeCgRequested) {
                if (!isBtLeCgRequested && wasBLeCgRequested) {
                    this.mBtHelperExt.stopBluetoothLeCg(eventSource);
                }
            } else if (!this.mBtHelperExt.startBluetoothLeCg(LeCgAudioMode, eventSource)) {
                Log.w(TAG, "setCommunicationRouteForClient: failure to start BT SCO for pid: " + pid);
                if (prevClientDevice != null) {
                    addCommunicationRouteClient(cb, pid, prevClientDevice);
                } else {
                    removeCommunicationRouteClient(cb, true);
                }
                postBroadcastLeCgConnectionState(0);
            }
            sendLMsgNoDelay(MSG_L_UPDATE_COMMUNICATION_ROUTE, 2, eventSource);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean handleDeviceConnection(boolean connect, int device, String address, String deviceName) {
        return ((Boolean) ReflectionHelper.callMethod(this.handleDeviceConnectionMethod, this.mDeviceBroker, Boolean.valueOf(connect), Integer.valueOf(device), address, deviceName)).booleanValue();
    }

    private void sendLMsgNoDelay(int msg, int existingMsgPolicy, Object obj) {
        sendIILMsg(msg, existingMsgPolicy, 0, 0, obj, 0);
    }

    private void sendMsg(int msg, int existingMsgPolicy, int delay) {
        sendIILMsg(msg, existingMsgPolicy, 0, 0, null, delay);
    }

    private void sendIMsg(int msg, int existingMsgPolicy, int arg, int delay) {
        sendIILMsg(msg, existingMsgPolicy, arg, 0, null, delay);
    }

    private void sendMsgNoDelay(int msg, int existingMsgPolicy) {
        sendIILMsg(msg, existingMsgPolicy, 0, 0, null, 0);
    }

    private void sendIMsgNoDelay(int msg, int existingMsgPolicy, int arg) {
        sendIILMsg(msg, existingMsgPolicy, arg, 0, null, 0);
    }

    private void sendIILMsgNoDelay(int msg, int existingMsgPolicy, int arg1, int arg2, Object obj) {
        sendIILMsg(msg, existingMsgPolicy, arg1, arg2, obj, 0);
    }

    private void sendILMsg(int msg, int existingMsgPolicy, int arg, Object obj, int delay) {
        sendIILMsg(msg, existingMsgPolicy, arg, 0, obj, delay);
    }

    private void sendIILMsg(int msg, int existingMsgPolicy, int arg1, int arg2, Object obj, int delay) {
        ReflectionHelper.callMethod(this.sendIILMsgMethod, this.mDeviceBroker, Integer.valueOf(msg), Integer.valueOf(existingMsgPolicy), Integer.valueOf(arg1), Integer.valueOf(arg2), obj, Integer.valueOf(delay));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void disconnectAllBluetoothProfiles() {
        synchronized (this.mDeviceStateLock) {
            this.mBtHelperExt.disconnectAllBluetoothProfiles();
        }
    }

    /* loaded from: classes.dex */
    private static final class BtDeviceConnectionInfo {
        final BluetoothDevice mDevice;
        final int mProfile;
        final int mState;
        final boolean mSupprNoisy;
        final int mVolume;

        BtDeviceConnectionInfo(BluetoothDevice device, int state, int profile, boolean suppressNoisyIntent, int vol) {
            this.mDevice = device;
            this.mState = state;
            this.mProfile = profile;
            this.mSupprNoisy = suppressNoisyIntent;
            this.mVolume = vol;
        }

        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (this == o) {
                return true;
            }
            if (o instanceof BtDeviceConnectionInfo) {
                return this.mDevice.equals(((BtDeviceConnectionInfo) o).mDevice);
            }
            return false;
        }

        public String toString() {
            return "BtDeviceConnectionInfo dev=" + this.mDevice.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class LeAudioDeviceConnectionInfo {
        final BluetoothDevice mDevice;
        final String mEventSource;
        final int mMusicDevice;
        final int mState;
        final boolean mSupprNoisy;

        LeAudioDeviceConnectionInfo(BluetoothDevice device, int state, boolean suppressNoisyIntent, int musicDevice, String eventSource) {
            this.mDevice = device;
            this.mState = state;
            this.mSupprNoisy = suppressNoisyIntent;
            this.mMusicDevice = musicDevice;
            this.mEventSource = eventSource;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postBluetoothLeAudioDeviceConnectionState(BluetoothDevice device, int state, boolean suppressNoisyIntent, int musicDevice, String eventSource) {
        LeAudioDeviceConnectionInfo info = new LeAudioDeviceConnectionInfo(device, state, suppressNoisyIntent, musicDevice, eventSource);
        sendLMsgNoDelay(MSG_L_LE_AUDIO_DEVICE_CONNECTION_CHANGE_EXT, 2, info);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postSetLeVcAbsoluteVolumeIndex(int index) {
        sendIMsgNoDelay(MSG_I_SET_LE_VC_ABSOLUTE_VOLUME, 0, index);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postSetLeCgVcIndex(int index) {
        synchronized (sLastBLeCgDeviceConnectTimeLock) {
            long diffTime = SystemClock.uptimeMillis() - sLastBLeCgDeviceConnectTime;
            if (diffTime >= ((long) DEFAULT_ABS_VOL_IDX_DELAY_MS)) {
                sendIMsgNoDelay(MSG_I_SET_LE_CG_VC_ABSOLUTE_VOLUME, 0, index);
            } else {
                sendIMsg(MSG_I_SET_LE_CG_VC_ABSOLUTE_VOLUME, 0, index, DEFAULT_ABS_VOL_IDX_DELAY_MS);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postSetLeAudioConnectionState(int state, BluetoothDevice device, int delay) {
        sendILMsg(MSG_IL_SET_LE_AUDIO_CONNECTION_STATE, 2, state, device, delay);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postDisconnectLE() {
        sendMsgNoDelay(MSG_DISCONNECT_BT_LE, 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postBtLEProfileConnected(BluetoothProfile LEProfile) {
        sendLMsgNoDelay(MSG_L_BT_SERVICE_CONNECTED_PROFILE_LE, 2, LEProfile);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postBtTbsProfileConnected(BluetoothTbs leTbsProfile) {
        sendLMsgNoDelay(MSG_L_BT_SERVICE_CONNECTED_PROFILE_LE_TBS, 2, leTbsProfile);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postBtTbsProfileDisconnected() {
        sendMsgNoDelay(MSG_DISCONNECT_BLE_TBS, 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postBroadcastLeCgConnectionState(int state) {
        sendIMsgNoDelay(MSG_I_BROADCAST_BT_LE_CG_CONNECTION_STATE, 2, state);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleFailureToConnectToBluetoothTbsService(int delay) {
        sendMsg(MSG_BT_LE_TBS_CNCT_FAILED, 0, delay);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleCancelFailureToConnectToBluetoothTbsService() {
        this.mBrokerHandler.removeMessages(MSG_BT_LE_TBS_CNCT_FAILED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isBluetoothLeOn() {
        return this.mBluetoothLeDeviceStatus;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBluetoothLeOnInt(boolean on, boolean fromLe, String source) {
        String eventSource = "setBluetoothLeOnInt(" + on + ") from u/pid:" + Binder.getCallingUid() + "/" + Binder.getCallingPid() + " src:" + source;
        synchronized (this.mDeviceStateLock) {
            this.mBluetoothLeDeviceStatus = on;
            this.mBrokerHandler.removeMessages(MSG_IIL_SET_FORCE_BT_A2DP_USE);
            if (on) {
                ReflectionHelper.callMethod(this.onSetForceUseMethod, this.mDeviceBroker, 1, 0, Boolean.valueOf(fromLe), eventSource);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLeVcAbsoluteVolumeSupported(boolean supported) {
        this.mBtHelperExt.setLeVcAbsoluteVolumeSupported(supported);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void postHandleMessageExt(Message msg) {
        if (AudioServiceExtImpl.LOGD) {
            Log.d(TAG, "postHandleMessageExt msgId=" + msg.what);
        }
        switch (msg.what) {
            case MSG_IL_SET_LE_AUDIO_CONNECTION_STATE /* 100 */:
                synchronized (this.mDeviceStateLock) {
                    this.mDeviceInventoryExt.onSetLeAudioConnectionState((BluetoothDevice) msg.obj, msg.arg1, this.mAudioServiceExtImpl.getLeAudioStreamType());
                }
                return;
            case MSG_L_LE_AUDIO_DEVICE_CONNECTION_CHANGE_EXT /* 101 */:
                LeAudioDeviceConnectionInfo info = (LeAudioDeviceConnectionInfo) msg.obj;
                if (AudioServiceExtImpl.LOGD) {
                    Log.d(TAG, "setLeAudioDeviceConnectionState state=" + info.mState + " addr=" + info.mDevice.getAddress() + " supprNoisy=" + info.mSupprNoisy + " src=" + info.mEventSource);
                }
                synchronized (this.mDeviceStateLock) {
                    this.mDeviceInventoryExt.setBluetoothLeAudioDeviceConnectionState(info.mDevice, info.mState, info.mSupprNoisy, info.mMusicDevice);
                }
                return;
            case MSG_L_BT_SERVICE_CONNECTED_PROFILE_LE /* 102 */:
                synchronized (this.mSetModeLock) {
                    synchronized (this.mDeviceStateLock) {
                        this.mBtHelperExt.onLeProfileConnected((BluetoothProfile) msg.obj);
                    }
                }
                return;
            case MSG_DISCONNECT_BT_LE /* 103 */:
                synchronized (this.mSetModeLock) {
                    synchronized (this.mDeviceStateLock) {
                        this.mDeviceInventoryExt.disconnectLE();
                    }
                }
                return;
            case MSG_I_SET_LE_VC_ABSOLUTE_VOLUME /* 104 */:
                synchronized (this.mDeviceStateLock) {
                    this.mBtHelperExt.setLeVcAbsoluteVolumeIndex(msg.arg1);
                }
                return;
            case MSG_L_BT_SERVICE_CONNECTED_PROFILE_LE_TBS /* 105 */:
                synchronized (this.mSetModeLock) {
                    synchronized (this.mDeviceStateLock) {
                        this.mBtHelperExt.onLeTbsProfileConnected((BluetoothTbs) msg.obj);
                    }
                }
                return;
            case MSG_DISCONNECT_BLE_TBS /* 106 */:
                synchronized (this.mSetModeLock) {
                    synchronized (this.mDeviceStateLock) {
                        this.mBtHelperExt.disconnectBleTbs();
                    }
                }
                return;
            case MSG_I_SET_LE_CG_VC_ABSOLUTE_VOLUME /* 107 */:
                synchronized (this.mDeviceStateLock) {
                    this.mBtHelperExt.setLeCgVcIndex(msg.arg1);
                }
                return;
            case MSG_BT_LE_TBS_CNCT_FAILED /* 108 */:
                synchronized (this.mSetModeLock) {
                    synchronized (this.mDeviceStateLock) {
                        this.mBtHelperExt.resetBluetoothLeCg();
                    }
                }
                return;
            case MSG_I_BROADCAST_BT_LE_CG_CONNECTION_STATE /* 109 */:
                synchronized (this.mDeviceStateLock) {
                    this.mBtHelperExt.onBroadcastLeCgConnectionState(msg.arg1);
                }
                return;
            default:
                Log.wtf(TAG, "Invalid message " + msg.what);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void receiveBtEvent(Intent intent) {
        synchronized (sLastBLeCgDeviceConnectTimeLock) {
            sLastBLeCgDeviceConnectTime = SystemClock.uptimeMillis();
        }
        synchronized (this.mSetModeLock) {
            synchronized (this.mDeviceStateLock) {
                this.mBtHelperExt.receiveBtEvent(intent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioDeviceAttributes preferredCommunicationDevice() {
        AudioDeviceAttributes device;
        boolean btCgOn = this.mBluetoothLeCgOn && this.mBtHelperExt.isBluetoothLeCgOn();
        if (!btCgOn || (device = this.mBtHelperExt.getLeTbsAudioDevice()) == null) {
            return null;
        }
        return device;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ContentResolver getContentResolver() {
        return (ContentResolver) ReflectionHelper.callMethod(this.getContentResolverMethod, this.mDeviceBroker, new Object[0]);
    }
}
