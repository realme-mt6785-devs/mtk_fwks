package com.mediatek.server.audio;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hidl.base.V1_0.DebugInfo;
import android.media.AudioDeviceAttributes;
import android.media.IPlaybackConfigDispatcher;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.Log;
import com.android.server.audio.AudioService;
import com.android.server.audio.AudioSystemAdapter;
import com.android.server.audio.PlaybackActivityMonitor;
import com.android.server.audio.SystemServerAdapter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class AudioServiceExtImpl extends AudioServiceExt {
    static final int CONNECTION_STATE_CONNECTED = 1;
    static final int CONNECTION_STATE_DISCONNECTED = 0;
    protected static final boolean DEBUG_AP;
    protected static final boolean DEBUG_DEVICES;
    protected static final boolean DEBUG_MODE;
    protected static final boolean DEBUG_VOL;
    protected static final boolean LOGD;
    private static final String TAG = "AS.AudioServiceExt";
    private static Field sAudioHandlerField;
    private static AudioService sAudioService;
    private static Method sSendMsgMethod;
    private boolean mBtLeCgOnByApp;
    private Context mContext;
    private boolean mIsBleCgFeatureSupported;
    private AtomicInteger mMode;
    private PlaybackActivityMonitor mPlaybackMonitor;
    private Object mPlaybackMonitorObj;
    private IPlaybackConfigDispatcher mVoiceActivityMonitor;
    private Object mVoiceActivityMonitorObj;
    private volatile boolean mLeVcSupportsAbsoluteVolume = false;
    private AudioDeviceBrokerExt mDeviceBrokerExt = null;
    private Handler mAudioHandler = null;
    private Field mStreamStatesField = null;
    private Field mPlaybackMonitorField = null;
    private Field mVoiceActivityMonitorField = null;
    private Object mStreamStatesObj = null;
    private Method registerPlaybackCallbackMethod = null;
    private Method unregisterPlaybackCallbackMethod = null;
    private AudioServiceExtImpl mAudioServiceExtImpl = null;
    private AtomicBoolean mVoicePlaybackActive = null;
    private Field mleCallVcSupportsAbsoluteVolumeField = null;
    private Field mleVcSupportsAbsoluteVolumeField = null;
    int mBleCgVolume = 0;
    private boolean mIsSystemReadyStatus = false;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface BtProfileConnectionState {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface ConnectionState {
    }

    static {
        boolean z = "eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE);
        LOGD = z;
        DEBUG_MODE = z;
        DEBUG_AP = z;
        DEBUG_VOL = z;
        DEBUG_DEVICES = z;
        sAudioService = null;
        sSendMsgMethod = null;
        sAudioHandlerField = null;
    }

    public AudioServiceExtImpl() {
        this.mIsBleCgFeatureSupported = false;
        boolean z = LOGD;
        if (z) {
            Log.d(TAG, "AudioServiceExtImpl");
        }
        if (SystemProperties.get("persist.vendor.bluetooth.leaudio_mode", "").equalsIgnoreCase("ums-cg")) {
            this.mIsBleCgFeatureSupported = true;
        }
        if (z) {
            Log.d(TAG, "isBleCgFeatureSupported() status=" + this.mIsBleCgFeatureSupported);
        }
    }

    public boolean isBleAudioFeatureSupported() {
        if (LOGD && 1 == 0) {
            Log.d(TAG, "isBleAudioEnable() status=true");
        }
        return true;
    }

    public boolean isBleCgFeatureSupported() {
        return this.mIsBleCgFeatureSupported;
    }

    public void init(Context context, AudioService audioService, AudioSystemAdapter audioSystem, SystemServerAdapter systemServer, Object deviceBroker) {
        boolean z = LOGD;
        if (z) {
            Log.d(TAG, "init()--context=" + context + ",audioService=" + audioService + ", audioSystem=" + audioSystem + ",systemServer" + systemServer + ",deviceBroker" + deviceBroker);
        }
        sAudioService = audioService;
        this.mContext = context;
        this.mDeviceBrokerExt = new AudioDeviceBrokerExt(context, audioService, this, audioSystem, deviceBroker, systemServer);
        sSendMsgMethod = ReflectionHelper.getMethod(audioService, "sendMsg", Handler.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Object.class, Integer.TYPE);
        this.mAudioHandler = (Handler) ReflectionHelper.getFieldObject(audioService, "mAudioHandler", new Class[0]);
        this.mMode = (AtomicInteger) ReflectionHelper.getFieldObject(audioService, "mMode", new Class[0]);
        this.mVoicePlaybackActive = (AtomicBoolean) ReflectionHelper.getFieldObject(audioService, "mVoicePlaybackActive", new Class[0]);
        this.mleCallVcSupportsAbsoluteVolumeField = ReflectionHelper.getField(audioService, "mleCallVcSupportsAbsoluteVolume", new Class[0]);
        this.mleVcSupportsAbsoluteVolumeField = ReflectionHelper.getField(audioService, "mleVcSupportsAbsoluteVolume", new Class[0]);
        this.mIsSystemReadyStatus = true;
        if (z) {
            Log.d(TAG, "init()--Done");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBleCallVcSupportsAbsoluteVolume(boolean support) {
        if (LOGD) {
            Log.d(TAG, "setBleCallVcSupportsAbsoluteVolume() support=" + support);
        }
        ReflectionHelper.callSetBoolean(this.mleCallVcSupportsAbsoluteVolumeField, sAudioService, Boolean.valueOf(support));
    }

    public void onReceiveExt(Context context, Intent intent) {
        if (intent == null || context == null) {
            Log.e(TAG, "onReceiveExt returned Intent or context is null");
        }
        String action = intent.getAction();
        if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
            if (LOGD) {
                Log.d(TAG, "onReceiveExt action=" + action);
            }
            int state = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
            if (state == 10 || state == 13) {
                this.mDeviceBrokerExt.disconnectAllBluetoothProfiles();
            }
        } else if (!action.equals("android.bluetooth.action.LEAUDIO_ACTIVE_DEVICE_CHANGED") && !action.equals("android.bluetooth.tbs.profile.action.AUDIO_STATE_CHANGED") && !action.equals("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED")) {
        } else {
            if (isBleCgFeatureSupported()) {
                if (LOGD) {
                    Log.d(TAG, "onReceiveExt action=" + action);
                }
                this.mDeviceBrokerExt.receiveBtEvent(intent);
            } else if (LOGD) {
                Log.d(TAG, "onReceiveExt skipped CG intents action=" + action);
            }
        }
    }

    public void getBleIntentFilters(IntentFilter intentFilter) {
        if (isBleCgFeatureSupported()) {
            int filtersCountBefore = intentFilter.countActions();
            intentFilter.addAction("android.bluetooth.action.LEAUDIO_ACTIVE_DEVICE_CHANGED");
            intentFilter.addAction("android.bluetooth.tbs.profile.action.AUDIO_STATE_CHANGED");
            if (LOGD) {
                Log.d(TAG, "getBleIntentFilters() Before=" + filtersCountBefore + ",after=" + intentFilter.countActions());
            }
        }
    }

    public void onSystemReadyExt() {
        if (LOGD) {
            Log.d(TAG, "onSystemReadyExt()");
        }
        this.mDeviceBrokerExt.onSystemReady();
    }

    public boolean isBluetoothLeOn() {
        AudioDeviceBrokerExt audioDeviceBrokerExt = this.mDeviceBrokerExt;
        if (audioDeviceBrokerExt == null) {
            return false;
        }
        return audioDeviceBrokerExt.isBluetoothLeOn();
    }

    public boolean isBluetoothLeCgOn() {
        AudioDeviceBrokerExt audioDeviceBrokerExt;
        if (!isBleCgFeatureSupported() || (audioDeviceBrokerExt = this.mDeviceBrokerExt) == null) {
            return false;
        }
        boolean status = audioDeviceBrokerExt.isBluetoothLeCgOn();
        if (LOGD) {
            Log.d(TAG, "isBluetoothLeCgOn()=" + status);
        }
        return status;
    }

    public boolean isBluetoothLeTbsDeviceActive() {
        AudioDeviceBrokerExt audioDeviceBrokerExt;
        if (!isBleCgFeatureSupported() || (audioDeviceBrokerExt = this.mDeviceBrokerExt) == null) {
            return false;
        }
        boolean status = audioDeviceBrokerExt.isBluetoothLeTbsDeviceActive();
        if (LOGD) {
            Log.d(TAG, "isBluetoothLeTbsDeviceActive()" + status);
        }
        return status;
    }

    public void startBluetoothLeCg(IBinder cb, int targetSdkVersion) {
        int uid = Binder.getCallingUid();
        int pid = Binder.getCallingPid();
        int leCgAudioMode = targetSdkVersion < 18 ? 0 : -1;
        String eventSource = "startBluetoothLeCg()) from u/pid:" + uid + "/" + pid;
        Log.d(TAG, eventSource);
        startBluetoothLeCgInt(cb, pid, leCgAudioMode, eventSource);
    }

    public boolean stopBluetoothLeCg(IBinder cb) {
        if (!isBleCgFeatureSupported() || this.mDeviceBrokerExt == null) {
            return false;
        }
        int uid = Binder.getCallingUid();
        int pid = Binder.getCallingPid();
        String eventSource = "stopBluetoothLeCg()) from u/pid:" + uid + "/" + pid;
        Log.d(TAG, eventSource);
        long ident = Binder.clearCallingIdentity();
        boolean status = this.mDeviceBrokerExt.stopBluetoothLeCgForClient(cb, pid, eventSource);
        Binder.restoreCallingIdentity(ident);
        return status;
    }

    public void startBluetoothLeCgVirtualCall(IBinder cb) {
        int uid = Binder.getCallingUid();
        int pid = Binder.getCallingPid();
        String eventSource = "startBluetoothLeCgVirtualCall()) from u/pid:" + uid + "/" + pid;
        Log.d(TAG, eventSource);
        startBluetoothLeCgInt(cb, pid, 0, eventSource);
    }

    void startBluetoothLeCgInt(IBinder cb, int pid, int LeAudioMode, String eventSource) {
        long ident = Binder.clearCallingIdentity();
        this.mDeviceBrokerExt.startBluetoothLeCgForClient(cb, pid, LeAudioMode, eventSource);
        Binder.restoreCallingIdentity(ident);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLeAudioStreamType() {
        return getLeAudioStreamType(this.mMode.intValue());
    }

    private int getLeAudioStreamType(int mode) {
        switch (mode) {
            case DebugInfo.Architecture.IS_32BIT /* 2 */:
            case 3:
                return 0;
            default:
                if (this.mVoicePlaybackActive.get()) {
                    return 0;
                }
                return 3;
        }
    }

    public void setBluetoothLeAudioDeviceConnectionState(BluetoothDevice device, int state, boolean suppressNoisyIntent, int musicDevice) {
        if (this.mDeviceBrokerExt != null) {
            if (device == null) {
                throw new IllegalArgumentException("Illegal null device");
            } else if (state == 2 || state == 0) {
                if (DEBUG_DEVICES) {
                    String eventSource = "setBluetoothLeAudioDeviceConnectionState()) from u/pid:" + Binder.getCallingUid() + "/" + Binder.getCallingPid() + ", device=" + device + ", state=" + state + ", suppressNoisyIntent=" + suppressNoisyIntent + ", musicDevice=" + musicDevice;
                    Log.d(TAG, eventSource);
                }
                this.mDeviceBrokerExt.postBluetoothLeAudioDeviceConnectionState(device, state, suppressNoisyIntent, musicDevice, "AudioService");
            } else {
                throw new IllegalArgumentException("Illegal BluetoothProfile state for device  (dis)connection, got " + state);
            }
        }
    }

    public void leVcSupportsAbsoluteVolume(String address, boolean support) {
        if (LOGD) {
            Log.d(TAG, "leVcSupportsAbsoluteVolume addr=" + address + " support=" + support);
        }
        if (support && this.mLeVcSupportsAbsoluteVolume) {
            Log.d(TAG, "Bail out when already mLeVcSupportsAbsoluteVolume set");
            return;
        }
        ReflectionHelper.callSetBoolean(this.mleVcSupportsAbsoluteVolumeField, sAudioService, Boolean.valueOf(support));
        this.mLeVcSupportsAbsoluteVolume = support;
        this.mDeviceBrokerExt.setLeVcAbsoluteVolumeSupported(support);
    }

    private static void sendMsg(Handler handler, int msg, int existingMsgPolicy, int arg1, int arg2, Object obj, int delay) {
        ReflectionHelper.callMethod(sSendMsgMethod, sAudioService, sAudioHandlerField, Integer.valueOf(msg), Integer.valueOf(existingMsgPolicy), Integer.valueOf(arg1), Integer.valueOf(arg2), obj, Integer.valueOf(delay));
    }

    public void postSetLeVcAbsoluteVolumeIndex(int index) {
        if (LOGD) {
            Log.d(TAG, "postSetLeVcAbsoluteVolumeIndex index=" + index);
        }
        this.mDeviceBrokerExt.postSetLeVcAbsoluteVolumeIndex(index);
    }

    public int getBleCgVolume() {
        if (LOGD) {
            Log.d(TAG, "getBleCgVolume index=" + this.mBleCgVolume);
        }
        return this.mBleCgVolume;
    }

    public void postSetLeCgVcIndex(int index) {
        if (this.mDeviceBrokerExt != null) {
            if (LOGD) {
                Log.d(TAG, "postSetLeCgVcIndex index=" + index);
            }
            this.mBleCgVolume = index;
            this.mDeviceBrokerExt.postSetLeCgVcIndex(index);
        }
    }

    public void handleMessageExt(Message msg) {
        if (this.mDeviceBrokerExt != null) {
            if (LOGD) {
                Log.d(TAG, "handleMessageExt messageID=" + msg.what);
            }
            this.mDeviceBrokerExt.postHandleMessageExt(msg);
        }
    }

    public AudioDeviceAttributes preferredCommunicationDevice() {
        if (this.mDeviceBrokerExt == null || !isBleCgFeatureSupported()) {
            return null;
        }
        AudioDeviceAttributes device = this.mDeviceBrokerExt.preferredCommunicationDevice();
        if (LOGD) {
            Log.d(TAG, "preferredCommunicationDevice device=" + device);
        }
        return device;
    }

    public boolean isBluetoothLeCgActive() {
        if (this.mDeviceBrokerExt == null || !isBleCgFeatureSupported()) {
            return false;
        }
        boolean status = this.mDeviceBrokerExt.isBluetoothLeCgActive();
        if (LOGD) {
            Log.d(TAG, "isBluetoothLeCgActive status=" + status);
        }
        return status;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resetBluetoothLeCgOfApp() {
        this.mBtLeCgOnByApp = false;
    }

    public void setBluetoothLeCgOn(boolean on) {
        if (this.mDeviceBrokerExt == null && !isBleCgFeatureSupported()) {
            this.mBtLeCgOnByApp = false;
        } else if (UserHandle.getCallingAppId() < 10000) {
            int uid = Binder.getCallingUid();
            int pid = Binder.getCallingPid();
            String eventSource = "setBluetoothLeCgOn(" + on + ") from u/pid:" + uid + "/" + pid;
            if (LOGD) {
                String callingApp = this.mContext.getPackageManager().getNameForUid(Binder.getCallingUid());
                Log.d(TAG, eventSource + ", callingApp=" + callingApp);
            }
            this.mDeviceBrokerExt.setBluetoothLeCgOn(on, eventSource);
        }
    }

    public boolean isSystemReady() {
        if (LOGD && !this.mIsSystemReadyStatus) {
            Log.d(TAG, "isSystemReady()" + this.mIsSystemReadyStatus);
        }
        return this.mIsSystemReadyStatus;
    }
}
