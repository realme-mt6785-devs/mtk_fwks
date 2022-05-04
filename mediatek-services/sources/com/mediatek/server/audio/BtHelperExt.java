package com.mediatek.server.audio;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.ContentResolver;
import android.content.Intent;
import android.hidl.base.V1_0.DebugInfo;
import android.media.AudioDeviceAttributes;
import android.media.AudioSystem;
import android.os.Binder;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import com.mediatek.bt.BluetoothLeAudio;
import com.mediatek.bt.BluetoothLeAudioFactory;
import com.mediatek.bt.BluetoothTbs;
import com.mediatek.net.connectivity.MtkPacketMessage;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public class BtHelperExt {
    private static final int LE_CG_MODE_MAX = 2;
    private static final int LE_CG_MODE_RAW = 1;
    static final int LE_CG_MODE_UNDEFINED = -1;
    static final int LE_CG_MODE_VIRTUAL_CALL = 0;
    private static final int LE_CG_MODE_VR = 2;
    private static final int LE_CG_STATE_ACTIVATE_REQ = 1;
    private static final int LE_CG_STATE_ACTIVE_EXTERNAL = 2;
    private static final int LE_CG_STATE_ACTIVE_INTERNAL = 3;
    private static final int LE_CG_STATE_DEACTIVATE_REQ = 4;
    private static final int LE_CG_STATE_DEACTIVATING = 5;
    private static final int LE_CG_STATE_INACTIVE = 0;
    private static final String TAG = "AS.BtHelperExt";
    private BluetoothTbs mBluetoothLeTbs;
    private BluetoothDevice mBluetoothLeTbsDevice;
    Object mDeviceBroker;
    private AudioDeviceBrokerExt mDeviceBrokerExt;
    private BluetoothProfile mLeAudio;
    private BluetoothLeAudio mLeAudioProfile;
    private int mLeCgAudioMode;
    private int mLeCgConnectionState;
    private boolean mLeVcAbsVolSupported = false;
    private boolean mLeCallVcAbsVolSupported = false;
    private BluetoothProfile.ServiceListener mBluetoothProfileServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.mediatek.server.audio.BtHelperExt.1
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (BluetoothLeAudioFactory.getInstance().isLeAudioConnected(profile)) {
                BtHelperExt.this.mLeAudioProfile = (BluetoothLeAudio) proxy;
                BtHelperExt.this.mDeviceBrokerExt.postBtLEProfileConnected(proxy);
                Log.i(BtHelperExt.TAG, "LE_AUDIO Profile Connected");
            }
            if (profile == 26) {
                Log.i(BtHelperExt.TAG, "BLE CS AUDIO Profile Connected, proxy=" + proxy);
                BtHelperExt.this.mDeviceBrokerExt.postBtTbsProfileConnected((BluetoothTbs) proxy);
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int profile) {
            if (BluetoothLeAudioFactory.getInstance().isLeAudioConnected(profile)) {
                Log.i(BtHelperExt.TAG, "LE_AUDIO Profile Disconnected");
                BtHelperExt.this.mLeAudioProfile = null;
                BtHelperExt.this.mDeviceBrokerExt.postDisconnectLE();
            }
            if (profile == 26) {
                Log.i(BtHelperExt.TAG, "BLE CS AUDIO Profile Disconnected");
                BtHelperExt.this.mDeviceBrokerExt.postBtTbsProfileDisconnected();
            }
        }
    };

    /* loaded from: classes.dex */
    static class BluetoothA2dpDeviceInfo {
        private final BluetoothDevice mBtDevice;
        private final int mCodec;
        private final int mVolume;

        BluetoothA2dpDeviceInfo(BluetoothDevice btDevice) {
            this(btDevice, BtHelperExt.LE_CG_MODE_UNDEFINED, 0);
        }

        BluetoothA2dpDeviceInfo(BluetoothDevice btDevice, int volume, int codec) {
            this.mBtDevice = btDevice;
            this.mVolume = volume;
            this.mCodec = codec;
        }

        public BluetoothDevice getBtDevice() {
            return this.mBtDevice;
        }

        public int getVolume() {
            return this.mVolume;
        }

        public int getCodec() {
            return this.mCodec;
        }

        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            if (this == o) {
                return true;
            }
            if (o instanceof BluetoothA2dpDeviceInfo) {
                return this.mBtDevice.equals(((BluetoothA2dpDeviceInfo) o).getBtDevice());
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getName(BluetoothDevice device) {
        String deviceName = device.getName();
        if (deviceName == null) {
            return "";
        }
        return deviceName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BtHelperExt(Object broker, AudioDeviceBrokerExt deviceBrokerExt) {
        this.mDeviceBrokerExt = null;
        this.mDeviceBroker = broker;
        this.mDeviceBrokerExt = deviceBrokerExt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void onSystemReady() {
        this.mLeCgConnectionState = LE_CG_MODE_UNDEFINED;
        resetBluetoothLeCg();
        Intent newIntent = new Intent("android.media.SCO_AUDIO_STATE_CHANGED");
        newIntent.putExtra("android.media.extra.SCO_AUDIO_STATE", 0);
        sendStickyBroadcastToAll(newIntent);
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter != null) {
            BluetoothLeAudioFactory.getInstance().onLeAudioSystemReady(adapter, this.mDeviceBrokerExt.getContext(), this.mBluetoothProfileServiceListener);
            adapter.getProfileProxy(this.mDeviceBrokerExt.getContext(), this.mBluetoothProfileServiceListener, 26);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void onLeProfileConnected(BluetoothProfile LeAudio) {
        this.mLeAudio = LeAudio;
        List<BluetoothDevice> deviceList = LeAudio.getConnectedDevices();
        if (!deviceList.isEmpty()) {
            BluetoothDevice btDevice = deviceList.get(0);
            int state = LeAudio.getConnectionState(btDevice);
            this.mDeviceBrokerExt.postBluetoothLeAudioDeviceConnectionState(btDevice, state, false, 0, "mBluetoothProfileServiceListener");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void receiveBtEvent(Intent intent) {
        BluetoothTbs bluetoothTbs;
        BluetoothDevice bluetoothDevice;
        String action = intent.getAction();
        Log.i(TAG, "receiveBtEvent action: " + action + " mLeCgAudioMode: " + this.mLeCgAudioMode);
        if (action.equals("android.bluetooth.action.LEAUDIO_ACTIVE_DEVICE_CHANGED")) {
            BluetoothDevice btDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (AudioServiceExtImpl.DEBUG_DEVICES) {
                Log.d(TAG, "receiveBtEvent() BTactiveDeviceChanged, btDevice=" + btDevice);
            }
            setBLeCgActiveDevice(btDevice);
        } else if (action.equals("android.bluetooth.tbs.profile.action.AUDIO_STATE_CHANGED") || action.equals("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED")) {
            boolean broadcast = false;
            int LeCgAudioMode = LE_CG_MODE_UNDEFINED;
            int btState = intent.getIntExtra("android.bluetooth.profile.extra.STATE", LE_CG_MODE_UNDEFINED);
            BluetoothDevice btDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            String address = null;
            if (btDevice2 != null) {
                address = btDevice2.getAnonymizedAddress();
            }
            if (AudioServiceExtImpl.DEBUG_DEVICES) {
                String btStateInfo = btState == 12 ? "AudioConnected" : "AudioDisconnected";
                Log.d(TAG, "receiveBtEvent() ACTION_AUDIO_STATE_CHANGED, btState=" + btState + "{" + btStateInfo + "}address=" + address);
            }
            switch (btState) {
                case 10:
                    this.mLeCallVcAbsVolSupported = false;
                    this.mDeviceBrokerExt.setBleCallVcSupportsAbsoluteVolume(false);
                    this.mDeviceBrokerExt.setBluetoothLeCgOn(false, "BtHelper.receiveBtEvent");
                    LeCgAudioMode = 0;
                    int i = this.mLeCgAudioMode;
                    if ((i != 1 && (i != 2 || !this.mDeviceBrokerExt.isBluetoothLeCgRequested())) || (bluetoothTbs = this.mBluetoothLeTbs) == null || (bluetoothDevice = this.mBluetoothLeTbsDevice) == null || !connectBluetoothLeCgAudioHelper(bluetoothTbs, bluetoothDevice, this.mLeCgAudioMode)) {
                        if (this.mLeCgAudioMode != 2) {
                            broadcast = true;
                        }
                        this.mLeCgAudioMode = 0;
                        break;
                    } else {
                        this.mLeCgAudioMode = LE_CG_STATE_ACTIVE_INTERNAL;
                        LeCgAudioMode = 2;
                        broadcast = true;
                        break;
                    }
                    break;
                case 11:
                    int i2 = this.mLeCgAudioMode;
                    if (!(i2 == LE_CG_STATE_ACTIVE_INTERNAL || i2 == LE_CG_STATE_DEACTIVATE_REQ)) {
                        this.mLeCgAudioMode = 2;
                        break;
                    }
                    break;
                case 12:
                    LeCgAudioMode = 1;
                    int i3 = this.mLeCgAudioMode;
                    if (i3 != LE_CG_STATE_ACTIVE_INTERNAL && i3 != LE_CG_STATE_DEACTIVATE_REQ) {
                        this.mLeCgAudioMode = 2;
                    } else if (this.mDeviceBrokerExt.isBluetoothLeCgRequested()) {
                        broadcast = true;
                    }
                    this.mLeCallVcAbsVolSupported = true;
                    this.mDeviceBrokerExt.setBleCallVcSupportsAbsoluteVolume(true);
                    this.mDeviceBrokerExt.setBluetoothLeCgOn(true, "BtHelper.receiveBtEvent");
                    break;
            }
            if (broadcast) {
                broadcastScoConnectionState(LeCgAudioMode);
                if (AudioServiceExtImpl.DEBUG_DEVICES) {
                    Log.d(TAG, "receiveBtEvent(): BR SCOAudioStateChanged, LeCgAudioMode=" + LeCgAudioMode);
                }
                Intent newIntent = new Intent("android.media.SCO_AUDIO_STATE_CHANGED");
                newIntent.putExtra("android.media.extra.SCO_AUDIO_STATE", LeCgAudioMode);
                sendStickyBroadcastToAll(newIntent);
            }
        }
    }

    private void sendStickyBroadcastToAll(Intent intent) {
        intent.addFlags(268435456);
        long ident = Binder.clearCallingIdentity();
        try {
            this.mDeviceBrokerExt.getContext().sendStickyBroadcastAsUser(intent, UserHandle.ALL);
        } finally {
            Binder.restoreCallingIdentity(ident);
        }
    }

    private static boolean disconnectBluetoothLeCgAudioHelper(BluetoothTbs bluetoothTbs, BluetoothDevice device, int leCgAudioMode) {
        if (AudioServiceExtImpl.DEBUG_DEVICES) {
            Log.d(TAG, "stopIsoUsingVirtualVoiceCall, leCgAudioMode=" + leCgAudioMode);
        }
        return bluetoothTbs.stopIsoUsingVirtualVoiceCall();
    }

    private static boolean connectBluetoothLeCgAudioHelper(BluetoothTbs bluetoothTbs, BluetoothDevice device, int leCgAudioMode) {
        if (AudioServiceExtImpl.DEBUG_DEVICES) {
            Log.d(TAG, "startIsoUsingVirtualVoiceCall, leCgAudioMode=" + leCgAudioMode);
        }
        return bluetoothTbs.startIsoUsingVirtualVoiceCall();
    }

    private void checkCgAudioState() {
        BluetoothDevice bluetoothDevice;
        BluetoothTbs bluetoothTbs = this.mBluetoothLeTbs;
        if (!(bluetoothTbs == null || (bluetoothDevice = this.mBluetoothLeTbsDevice) == null || this.mLeCgAudioMode != 0 || bluetoothTbs.getAudioState(bluetoothDevice) == 10)) {
            this.mLeCgAudioMode = 2;
        }
        if (AudioServiceExtImpl.DEBUG_DEVICES) {
            Log.d(TAG, "checkCgAudioState() mLeCgAudioMode=" + this.mLeCgAudioMode + ", mBluetoothLeTbsDevice =" + this.mBluetoothLeTbsDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void onLeTbsProfileConnected(BluetoothTbs bluetoothLeTbs) {
        this.mDeviceBrokerExt.handleCancelFailureToConnectToBluetoothTbsService();
        this.mBluetoothLeTbs = bluetoothLeTbs;
        if (AudioServiceExtImpl.DEBUG_DEVICES) {
            Log.d(TAG, "onLeTbsProfileConnected bluetoothLeTbs = " + bluetoothLeTbs);
        }
        checkCgAudioState();
        int i = this.mLeCgAudioMode;
        if (i == 1 || i == LE_CG_STATE_DEACTIVATE_REQ) {
            boolean status = false;
            BluetoothDevice bluetoothDevice = this.mBluetoothLeTbsDevice;
            if (bluetoothDevice != null) {
                switch (i) {
                    case 1:
                        status = connectBluetoothLeCgAudioHelper(this.mBluetoothLeTbs, bluetoothDevice, i);
                        if (status) {
                            this.mLeCgAudioMode = LE_CG_STATE_ACTIVE_INTERNAL;
                            break;
                        }
                        break;
                    case LE_CG_STATE_DEACTIVATE_REQ /* 4 */:
                        status = disconnectBluetoothLeCgAudioHelper(this.mBluetoothLeTbs, bluetoothDevice, i);
                        if (status) {
                            this.mLeCgAudioMode = LE_CG_STATE_DEACTIVATING;
                            break;
                        }
                        break;
                }
            }
            if (!status) {
                this.mLeCgAudioMode = 0;
                broadcastScoConnectionState(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void disconnectBleTbs() {
        if (AudioServiceExtImpl.DEBUG_DEVICES) {
            Log.d(TAG, "disconnectBleTbs()");
        }
        setBLeCgActiveDevice(null);
        this.mBluetoothLeTbs = null;
    }

    private void setBLeCgActiveDevice(BluetoothDevice btDevice) {
        Log.i(TAG, "setBLeCgActiveDevice: " + getAnonymizedAddress(this.mBluetoothLeTbsDevice) + " -> " + getAnonymizedAddress(btDevice));
        BluetoothDevice previousActiveDevice = this.mBluetoothLeTbsDevice;
        if (!Objects.equals(btDevice, previousActiveDevice)) {
            if (!handleBtLeCsActiveDeviceChange(previousActiveDevice, false)) {
                Log.w(TAG, "setBLeCgActiveDevice() failed to remove previous device " + getAnonymizedAddress(previousActiveDevice));
            }
            if (!handleBtLeCsActiveDeviceChange(btDevice, true)) {
                Log.e(TAG, "setBLeCgActiveDevice() failed to add new device " + getAnonymizedAddress(btDevice));
                btDevice = null;
            }
            this.mBluetoothLeTbsDevice = btDevice;
            if (btDevice == null) {
                resetBluetoothLeCg();
                this.mLeCallVcAbsVolSupported = false;
                this.mDeviceBrokerExt.setBleCallVcSupportsAbsoluteVolume(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void resetBluetoothLeCg() {
        this.mLeCgAudioMode = 0;
        broadcastScoConnectionState(0);
        AudioSystem.setParameters("BTAudiosuspend=false");
        this.mDeviceBrokerExt.setBluetoothLeCgOn(false, "resetBluetoothLeCg");
    }

    private AudioDeviceAttributes btLeTbsDeviceToAudioDevice(BluetoothDevice btDevice) {
        String address = btDevice.getAddress();
        if (!BluetoothAdapter.checkBluetoothAddress(address)) {
            address = "";
        }
        BluetoothClass btClass = btDevice.getBluetoothClass();
        int nativeType = 16777216;
        if (btClass != null) {
            switch (btClass.getDeviceClass()) {
                case 1028:
                case 1032:
                    nativeType = 16777216;
                    break;
                case 1056:
                    nativeType = 16777216;
                    break;
            }
        }
        if (AudioServiceExtImpl.DEBUG_DEVICES) {
            StringBuilder sb = new StringBuilder();
            sb.append("btHeadsetDeviceToAudioDevice btDevice: ");
            sb.append(btDevice);
            sb.append(" btClass: ");
            sb.append(btClass == null ? "Unknown" : btClass);
            sb.append(" nativeType: ");
            sb.append(nativeType);
            sb.append(" address: ");
            sb.append(address);
            Log.i(TAG, sb.toString());
        }
        return new AudioDeviceAttributes(nativeType, address);
    }

    private boolean handleBtLeCsActiveDeviceChange(BluetoothDevice btDevice, boolean isActive) {
        if (btDevice == null) {
            return true;
        }
        AudioDeviceAttributes audioDevice = btLeTbsDeviceToAudioDevice(btDevice);
        String btDeviceName = getName(btDevice);
        boolean result = false;
        if (isActive) {
            result = false | this.mDeviceBrokerExt.handleDeviceConnection(isActive, audioDevice.getInternalType(), audioDevice.getAddress(), btDeviceName);
        } else {
            int[] outDeviceTypes = {16777216};
            for (int outDeviceType : outDeviceTypes) {
                result |= this.mDeviceBrokerExt.handleDeviceConnection(isActive, outDeviceType, audioDevice.getAddress(), btDeviceName);
            }
        }
        if (!this.mDeviceBrokerExt.handleDeviceConnection(isActive, -2146435072, audioDevice.getAddress(), btDeviceName) || !result) {
            return false;
        }
        return true;
    }

    private String getAnonymizedAddress(BluetoothDevice btDevice) {
        return btDevice == null ? "(null)" : btDevice.getAnonymizedAddress();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void disconnectAllBluetoothProfiles() {
        Log.i(TAG, "disconnectAllBluetoothProfiles");
        this.mDeviceBrokerExt.postDisconnectLE();
        this.mDeviceBrokerExt.postBtTbsProfileDisconnected();
    }

    synchronized boolean isLeVcAbsoluteVolumeSupported() {
        boolean z;
        if (this.mLeAudio != null) {
            if (this.mLeVcAbsVolSupported) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setLeVcAbsoluteVolumeSupported(boolean supported) {
        this.mLeVcAbsVolSupported = supported;
        Log.i(TAG, "setLeVcAbsoluteVolumeSupported supported=" + this.mLeVcAbsVolSupported);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setLeVcAbsoluteVolumeIndex(int index) {
        if (this.mLeAudio == null && AudioServiceExtImpl.DEBUG_VOL) {
            Log.d(TAG, "setLEAvrcpAbsoluteVolumeIndex: bailing due to null mLeAudio");
        } else if (!this.mLeVcAbsVolSupported) {
            Log.d(TAG, "setLeVcAbsoluteVolumeIndex: abs vol not supported");
        } else {
            if (AudioServiceExtImpl.DEBUG_VOL) {
                Log.i(TAG, " setLeVcAbsoluteVolumeIndex()mLeAudio.setVcAbsoluteVolume for index=" + index);
            }
            BluetoothLeAudioFactory.getInstance().setVcAbsoluteVolume(index, this.mLeAudio);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setLeCgVcIndex(int index) {
        if (this.mLeAudio == null && AudioServiceExtImpl.DEBUG_VOL) {
            Log.d(TAG, "setLeCgVcIndex: bailing due to null mLeAudio");
        } else if (!this.mLeCallVcAbsVolSupported) {
            Log.d(TAG, "setLeCgVcIndex: abs vol not supported");
        } else {
            if (AudioServiceExtImpl.DEBUG_VOL) {
                Log.i(TAG, "setLeCgVcIndex()mLeAudio.setVcAbsoluteVolume for index=" + index);
            }
            BluetoothLeAudioFactory.getInstance().setVcAbsoluteVolume(index, this.mLeAudio);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean isBluetoothLeTbsDeviceActive() {
        return this.mBluetoothLeTbsDevice != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean startBluetoothLeCg(int LeCgAudioMode, String eventSource) {
        if (AudioServiceExtImpl.DEBUG_VOL) {
            Log.i(TAG, "startBluetoothLeCg() LeCgAudioMode=" + LeCgAudioMode + ",eventSource = " + eventSource);
        }
        return requestLeCgState(12, LeCgAudioMode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean stopBluetoothLeCg(String eventSource) {
        if (AudioServiceExtImpl.DEBUG_VOL) {
            Log.i(TAG, "stopBluetoothLeCg() eventSource = " + eventSource);
        }
        return requestLeCgState(10, 0);
    }

    private boolean getBluetoothTbs() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter != null) {
            adapter.getProfileProxy(this.mDeviceBrokerExt.getContext(), this.mBluetoothProfileServiceListener, 26);
        }
        this.mDeviceBrokerExt.handleFailureToConnectToBluetoothTbsService(0 != 0 ? 3000 : 0);
        return false;
    }

    private boolean requestLeCgState(int state, int cGAudioMode) {
        checkCgAudioState();
        if (state != 12) {
            if (state == 10) {
                int i = this.mLeCgAudioMode;
                switch (i) {
                    case 1:
                        this.mLeCgAudioMode = 0;
                        broadcastScoConnectionState(0);
                        break;
                    case DebugInfo.Architecture.IS_32BIT /* 2 */:
                    default:
                        Log.w(TAG, "requestLeCgState: failed to disconnect in state " + this.mLeCgAudioMode + ", cGAudioMode=" + cGAudioMode);
                        broadcastScoConnectionState(0);
                        return false;
                    case LE_CG_STATE_ACTIVE_INTERNAL /* 3 */:
                        BluetoothTbs bluetoothTbs = this.mBluetoothLeTbs;
                        if (bluetoothTbs != null) {
                            BluetoothDevice bluetoothDevice = this.mBluetoothLeTbsDevice;
                            if (bluetoothDevice != null) {
                                if (!disconnectBluetoothLeCgAudioHelper(bluetoothTbs, bluetoothDevice, i)) {
                                    this.mLeCgAudioMode = 0;
                                    broadcastScoConnectionState(0);
                                    break;
                                } else {
                                    this.mLeCgAudioMode = LE_CG_STATE_DEACTIVATING;
                                    break;
                                }
                            } else {
                                this.mLeCgAudioMode = 0;
                                broadcastScoConnectionState(0);
                                break;
                            }
                        } else if (getBluetoothTbs()) {
                            this.mLeCgAudioMode = LE_CG_STATE_DEACTIVATE_REQ;
                            break;
                        } else {
                            Log.w(TAG, "requestScoState: getBluetoothHeadset failed during disconnection, mLeCgAudioMode=" + this.mLeCgAudioMode);
                            this.mLeCgAudioMode = 0;
                            broadcastScoConnectionState(0);
                            return false;
                        }
                }
            }
        } else {
            broadcastScoConnectionState(2);
            switch (this.mLeCgAudioMode) {
                case MtkPacketMessage.NF_DROP /* 0 */:
                    this.mLeCgAudioMode = cGAudioMode;
                    if (cGAudioMode == LE_CG_MODE_UNDEFINED) {
                        this.mLeCgAudioMode = 0;
                        if (this.mBluetoothLeTbsDevice != null) {
                            ContentResolver contentResolver = this.mDeviceBrokerExt.getContentResolver();
                            int i2 = Settings.Global.getInt(contentResolver, "bluetooth_sco_channel_" + this.mBluetoothLeTbsDevice.getAddress(), 0);
                            this.mLeCgAudioMode = i2;
                            if (i2 > 2 || i2 < 0) {
                                this.mLeCgAudioMode = 0;
                            }
                        }
                    }
                    BluetoothTbs bluetoothTbs2 = this.mBluetoothLeTbs;
                    if (bluetoothTbs2 != null) {
                        BluetoothDevice bluetoothDevice2 = this.mBluetoothLeTbsDevice;
                        if (bluetoothDevice2 == null) {
                            Log.w(TAG, "requestScoState: no active device while connecting, mLeCgAudioMode=" + this.mLeCgAudioMode);
                            broadcastScoConnectionState(0);
                            return false;
                        } else if (connectBluetoothLeCgAudioHelper(bluetoothTbs2, bluetoothDevice2, this.mLeCgAudioMode)) {
                            this.mLeCgAudioMode = LE_CG_STATE_ACTIVE_INTERNAL;
                            break;
                        } else {
                            Log.w(TAG, "requestScoState: connect to " + getAnonymizedAddress(this.mBluetoothLeTbsDevice) + " failed, mLeCgAudioMode=" + this.mLeCgAudioMode);
                            broadcastScoConnectionState(0);
                            return false;
                        }
                    } else if (getBluetoothTbs()) {
                        this.mLeCgAudioMode = 1;
                        break;
                    } else {
                        Log.w(TAG, "requestScoState: getBluetoothTbs() failed during connection, mLeCgAudioMode=" + this.mLeCgAudioMode);
                        broadcastScoConnectionState(0);
                        return false;
                    }
                case 1:
                case DebugInfo.Architecture.IS_32BIT /* 2 */:
                default:
                    Log.w(TAG, "requestLeCgState: failed to connect in state " + this.mLeCgAudioMode + ", cGAudioMode=" + cGAudioMode);
                    broadcastScoConnectionState(0);
                    return false;
                case LE_CG_STATE_ACTIVE_INTERNAL /* 3 */:
                    Log.w(TAG, "requestLeCgState: already in ACTIVE mode, simply return");
                    break;
                case LE_CG_STATE_DEACTIVATE_REQ /* 4 */:
                    this.mLeCgAudioMode = LE_CG_STATE_ACTIVE_INTERNAL;
                    broadcastScoConnectionState(1);
                    break;
                case LE_CG_STATE_DEACTIVATING /* 5 */:
                    this.mLeCgAudioMode = 1;
                    break;
            }
        }
        return true;
    }

    private void broadcastScoConnectionState(int state) {
        this.mDeviceBrokerExt.postBroadcastLeCgConnectionState(state);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void onBroadcastLeCgConnectionState(int state) {
        if (state != this.mLeCgConnectionState) {
            Intent newIntent = new Intent("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
            newIntent.putExtra("android.media.extra.SCO_AUDIO_STATE", state);
            newIntent.putExtra("android.media.extra.SCO_AUDIO_PREVIOUS_STATE", this.mLeCgConnectionState);
            sendStickyBroadcastToAll(newIntent);
            this.mLeCgConnectionState = state;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioDeviceAttributes getLeTbsAudioDevice() {
        BluetoothDevice bluetoothDevice = this.mBluetoothLeTbsDevice;
        if (bluetoothDevice == null) {
            return null;
        }
        return btLeTbsDeviceToAudioDevice(bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean isBluetoothLeCgOn() {
        BluetoothTbs bluetoothTbs = this.mBluetoothLeTbs;
        boolean z = false;
        if (bluetoothTbs == null) {
            return false;
        }
        if (bluetoothTbs.getAudioState(this.mBluetoothLeTbsDevice) == 12) {
            z = true;
        }
        return z;
    }
}
