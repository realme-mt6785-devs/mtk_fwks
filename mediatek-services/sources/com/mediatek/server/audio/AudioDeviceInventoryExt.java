package com.mediatek.server.audio;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.media.AudioSystem;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import com.android.server.audio.AudioDeviceInventory;
import com.android.server.audio.AudioService;
import com.android.server.audio.AudioSystemAdapter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.function.Consumer;
/* loaded from: classes.dex */
public class AudioDeviceInventoryExt {
    private static final String TAG = "AS.AudioDeviceInventoryExt";
    private static AudioDeviceInventory mDeviceInventory;
    AudioSystemAdapter mAudioSystem;
    private static Object mDeviceBroker = null;
    private static Field mDevicesLockField = null;
    private static Object mDevicesLock = null;
    private static Method mCheckSendBecomingNoisyIntentIntMethod = null;
    private static Method mSetCurrentAudioRouteNameIfPossibleMethod = null;
    private static Method mPostAccessoryPlugMediaUnmuteMethod = null;
    private static Method mSetBluetoothA2dpOnIntMethod = null;
    private static Method mPostApplyVolumeOnDeviceMethod = null;
    private static Method mPostSetVolumeIndexOnDeviceMethod = null;
    private static Method mGetA2dpCodecMethod = null;
    private static Method mPostBluetoothA2dpDeviceConfigChangeMethod = null;
    private static AudioDeviceBrokerExt mDeviceBrokerExt = null;
    private static LinkedHashMap<String, AudioDeviceInventory.DeviceInfo> mConnectedDevices = null;
    private static ArrayMap<Integer, String> mApmConnectedDevices = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioDeviceInventoryExt(AudioService service, AudioSystemAdapter audioSystem, Object deviceBroker, AudioDeviceBrokerExt deviceBrokerExt, AudioDeviceInventory deviceInventory) {
        this.mAudioSystem = null;
        mDeviceBroker = deviceBroker;
        this.mAudioSystem = audioSystem;
        mDeviceInventory = deviceInventory;
        mDeviceBrokerExt = deviceBrokerExt;
        mCheckSendBecomingNoisyIntentIntMethod = ReflectionHelper.getMethod(deviceInventory, "checkSendBecomingNoisyIntentInt", Integer.TYPE, Integer.TYPE, Integer.TYPE);
        mSetCurrentAudioRouteNameIfPossibleMethod = ReflectionHelper.getMethod(mDeviceInventory, "setCurrentAudioRouteNameIfPossible", String.class, Boolean.TYPE);
        mPostAccessoryPlugMediaUnmuteMethod = ReflectionHelper.getMethod(deviceBroker, "postAccessoryPlugMediaUnmute", Integer.TYPE);
        mPostSetVolumeIndexOnDeviceMethod = ReflectionHelper.getMethod(deviceBroker, "postSetVolumeIndexOnDevice", Integer.TYPE, Integer.TYPE, Integer.TYPE, String.class);
        mPostApplyVolumeOnDeviceMethod = ReflectionHelper.getMethod(deviceBroker, "postApplyVolumeOnDevice", Integer.TYPE, Integer.TYPE, String.class);
        mDevicesLock = ReflectionHelper.getFieldObject(deviceInventory, "mDevicesLock", new Class[0]);
        mConnectedDevices = (LinkedHashMap) ReflectionHelper.getFieldObject(deviceInventory, "mConnectedDevices", new Class[0]);
    }

    private void setCurrentAudioRouteNameIfPossible(String name, boolean fromA2dp) {
        ReflectionHelper.callMethod(mSetCurrentAudioRouteNameIfPossibleMethod, mDeviceInventory, name, Boolean.valueOf(fromA2dp));
    }

    private int checkSendBecomingNoisyIntentInt(int device, int state, int musicDevice) {
        int delay = ((Integer) ReflectionHelper.callMethod(mCheckSendBecomingNoisyIntentIntMethod, mDeviceInventory, Integer.valueOf(device), Integer.valueOf(state), Integer.valueOf(musicDevice))).intValue();
        return delay;
    }

    private void makeLeAudioDeviceAvailable(String address, String name, int streamType, String eventSource) {
        mDeviceBrokerExt.setBluetoothLeOnInt(true, true, eventSource);
        this.mAudioSystem.setDeviceConnectionState(536870912, 1, address, name, 0);
        this.mAudioSystem.setParameters("Leaudiosuspendonly=false");
        mConnectedDevices.put(AudioDeviceInventory.DeviceInfo.makeDeviceListKey(536870912, address), new AudioDeviceInventory.DeviceInfo(536870912, name, address, 0));
        ReflectionHelper.callMethod(mPostAccessoryPlugMediaUnmuteMethod, mDeviceBroker, 536870912);
        ReflectionHelper.callMethod(mPostApplyVolumeOnDeviceMethod, mDeviceBroker, Integer.valueOf(streamType), 536870912, "makeLeAudioDeviceAvailable");
        setCurrentAudioRouteNameIfPossible(name, false);
        this.mAudioSystem.setDeviceConnectionState(-1610612736, 1, address, name, 0);
        mConnectedDevices.put(AudioDeviceInventory.DeviceInfo.makeDeviceListKey(-1610612736, address), new AudioDeviceInventory.DeviceInfo(-1610612736, name, address, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: makeLeAudioDeviceUnavailable */
    public void lambda$disconnectLE$1$AudioDeviceInventoryExt(String address) {
        mDeviceBrokerExt.setBluetoothLeOnInt(false, true, "makeLeAudioDeviceUnavailable");
        this.mAudioSystem.setDeviceConnectionState(536870912, 0, address, "", 0);
        mConnectedDevices.remove(AudioDeviceInventory.DeviceInfo.makeDeviceListKey(536870912, address));
        setCurrentAudioRouteNameIfPossible(null, false);
        Log.d(TAG, "makeLeAudioDeviceUnavailable addr=" + address + "device=" + AudioSystem.getDeviceName(536870912));
        this.mAudioSystem.setDeviceConnectionState(-1610612736, 0, address, "", 0);
        mConnectedDevices.remove(AudioDeviceInventory.DeviceInfo.makeDeviceListKey(-1610612736, address));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int setBluetoothLeAudioDeviceConnectionState(BluetoothDevice device, int state, boolean suppressNoisyIntent, int musicDevice) {
        int delay;
        synchronized (mDevicesLock) {
            if (!suppressNoisyIntent) {
                int intState = state == 2 ? 1 : 0;
                delay = checkSendBecomingNoisyIntentInt(536870912, intState, musicDevice);
                if (delay > 0) {
                    delay = 250;
                }
            } else {
                delay = 0;
            }
            mDeviceBrokerExt.postSetLeAudioConnectionState(state, device, delay);
        }
        return delay;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void disconnectLE() {
        synchronized (mConnectedDevices) {
            final ArraySet<String> toRemove = new ArraySet<>();
            mConnectedDevices.values().forEach(new Consumer() { // from class: com.mediatek.server.audio.AudioDeviceInventoryExt$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    AudioDeviceInventoryExt.lambda$disconnectLE$0(toRemove, (AudioDeviceInventory.DeviceInfo) obj);
                }
            });
            if (toRemove.size() > 0) {
                checkSendBecomingNoisyIntentInt(536870912, 0, 0);
                this.mAudioSystem.setParameters("Leaudiosuspendonly=true");
                toRemove.stream().forEach(new Consumer() { // from class: com.mediatek.server.audio.AudioDeviceInventoryExt$$ExternalSyntheticLambda1
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        AudioDeviceInventoryExt.this.lambda$disconnectLE$1$AudioDeviceInventoryExt((String) obj);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$disconnectLE$0(ArraySet toRemove, AudioDeviceInventory.DeviceInfo deviceInfo) {
        if (deviceInfo.mDeviceType == 536870912) {
            toRemove.add(deviceInfo.mDeviceAddress);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onSetLeAudioConnectionState(BluetoothDevice btDevice, int state, int streamType) {
        String address = btDevice.getAddress();
        if (!BluetoothAdapter.checkBluetoothAddress(address)) {
            address = "";
        }
        Log.d(TAG, "onSetLeAudioConnectionState addr=" + getAnonymizedAddress(address));
        synchronized (mConnectedDevices) {
            String key = AudioDeviceInventory.DeviceInfo.makeDeviceListKey(536870912, btDevice.getAddress());
            AudioDeviceInventory.DeviceInfo dio = mConnectedDevices.get(key);
            String key2 = AudioDeviceInventory.DeviceInfo.makeDeviceListKey(-1610612736, btDevice.getAddress());
            AudioDeviceInventory.DeviceInfo dii = mConnectedDevices.get(key2);
            boolean isConnected = (dio == null || dii == null) ? false : true;
            if (isConnected && state != 2) {
                lambda$disconnectLE$1$AudioDeviceInventoryExt(address);
            } else if (!isConnected && state == 2) {
                makeLeAudioDeviceAvailable(address, BtHelperExt.getName(btDevice), streamType, "onSetLeAudioConnectionState");
            }
        }
    }

    private String getAnonymizedAddress(String address) {
        if (address == null || address.length() <= 8) {
            return address;
        }
        return "XX:XX:XX" + address.substring(8);
    }
}
