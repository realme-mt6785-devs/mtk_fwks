package com.mediatek.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothUuid;
import android.bluetooth.IBluetoothManager;
import android.content.Context;
import android.os.ParcelUuid;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class BluetoothLeAudioFactory {
    private static Object lock = new Object();
    private static BluetoothLeAudioFactory sInstance;

    public static BluetoothLeAudioFactory getInstance() {
        if (sInstance == null) {
            synchronized (lock) {
                if (sInstance == null) {
                    try {
                        PathClassLoader classLoader = new PathClassLoader("/system/framework/mediatek-framework.jar", BluetoothLeAudioFactory.class.getClassLoader());
                        Class<?> clazz = Class.forName("com.mediatek.bt.BluetoothLeAudioFactoryImpl", false, classLoader);
                        Constructor constructor = clazz.getConstructor(new Class[0]);
                        BluetoothLeAudioFactory bluetoothLeAudioFactory = (BluetoothLeAudioFactory) constructor.newInstance(new Object[0]);
                        sInstance = bluetoothLeAudioFactory;
                        return bluetoothLeAudioFactory;
                    } catch (Exception e) {
                        sInstance = null;
                    }
                }
            }
        }
        return new BluetoothLeAudioFactory();
    }

    public boolean isLeAudioProfileSupported(Context context) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return false;
        }
        return bluetoothLeAudioFactory.isLeAudioProfileSupported(context);
    }

    public boolean getProfileProxy(Context context, BluetoothProfile.ServiceListener listener, int profile) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return false;
        }
        return bluetoothLeAudioFactory.getProfileProxy(context, listener, profile);
    }

    public void closeProfileProxy(int profile, BluetoothProfile proxy) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory != null) {
            bluetoothLeAudioFactory.closeProfileProxy(profile, proxy);
        }
    }

    public int getProfileCount() {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return 22;
        }
        return bluetoothLeAudioFactory.getProfileCount();
    }

    public void getSupportedProfiles(IBluetoothManager mManagerService, List<Integer> supportedProfiles) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory != null) {
            bluetoothLeAudioFactory.getSupportedProfiles(mManagerService, supportedProfiles);
        }
    }

    public void onLeAudioSystemReady(BluetoothAdapter adapter, Context context, BluetoothProfile.ServiceListener listener) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory != null) {
            bluetoothLeAudioFactory.onLeAudioSystemReady(adapter, context, listener);
        }
    }

    public boolean isLeAudioConnected(int profile) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return false;
        }
        return bluetoothLeAudioFactory.isLeAudioConnected(profile);
    }

    public void setVcAbsoluteVolume(int index, BluetoothProfile profile) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory != null) {
            bluetoothLeAudioFactory.setVcAbsoluteVolume(index, profile);
        }
    }

    public int getLeAudioProfileId() {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return -1;
        }
        return bluetoothLeAudioFactory.getLeAudioProfileId();
    }

    public int getVcProfileId() {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return -1;
        }
        return bluetoothLeAudioFactory.getVcProfileId();
    }

    public int getTbsProfileId() {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return -1;
        }
        return bluetoothLeAudioFactory.getTbsProfileId();
    }

    public String getLeAudioConnectionStateChangedAction() {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        return bluetoothLeAudioFactory == null ? "null" : bluetoothLeAudioFactory.getLeAudioConnectionStateChangedAction();
    }

    public String getVcConnectionStateChangedAction() {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        return bluetoothLeAudioFactory == null ? "null" : bluetoothLeAudioFactory.getVcConnectionStateChangedAction();
    }

    public ParcelUuid getLeAudioUuid() {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        return bluetoothLeAudioFactory == null ? BluetoothUuid.BASE_UUID : bluetoothLeAudioFactory.getLeAudioUuid();
    }

    public ParcelUuid getVcUuid() {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        return bluetoothLeAudioFactory == null ? BluetoothUuid.BASE_UUID : bluetoothLeAudioFactory.getVcUuid();
    }

    public String getLeAudioActiveDeviceChangedAction() {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        return bluetoothLeAudioFactory == null ? "null" : bluetoothLeAudioFactory.getLeAudioActiveDeviceChangedAction();
    }

    public String getLeAudioSetMemberAvailableAction() {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        return bluetoothLeAudioFactory == null ? "null" : bluetoothLeAudioFactory.getLeAudioSetMemberAvailableAction();
    }

    public String getLeAudioConfChangedAction() {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        return bluetoothLeAudioFactory == null ? "null" : bluetoothLeAudioFactory.getLeAudioConfChangedAction();
    }

    public String getLeAudioConnectionRejectedAction() {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        return bluetoothLeAudioFactory == null ? "null" : bluetoothLeAudioFactory.getLeAudioConnectionRejectedAction();
    }

    public String getLeAudioGroupIdExtra() {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        return bluetoothLeAudioFactory == null ? "null" : bluetoothLeAudioFactory.getLeAudioGroupIdExtra();
    }

    public boolean connectLeAudio(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return false;
        }
        return bluetoothLeAudioFactory.connectLeAudio(profile, device);
    }

    public boolean connectVc(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return false;
        }
        return bluetoothLeAudioFactory.connectVc(profile, device);
    }

    public boolean disconnectLeAudio(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return false;
        }
        return bluetoothLeAudioFactory.disconnectLeAudio(profile, device);
    }

    public boolean disconnectVc(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return false;
        }
        return bluetoothLeAudioFactory.disconnectVc(profile, device);
    }

    public int getLeAudioPriority(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return -1;
        }
        return bluetoothLeAudioFactory.getLeAudioPriority(profile, device);
    }

    public int getVcPriority(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return -1;
        }
        return bluetoothLeAudioFactory.getVcPriority(profile, device);
    }

    public void setLeAudioPriority(BluetoothProfile profile, BluetoothDevice device, int priority) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory != null) {
            bluetoothLeAudioFactory.setLeAudioPriority(profile, device, priority);
        }
    }

    public void setVcPriority(BluetoothProfile profile, BluetoothDevice device, int priority) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory != null) {
            bluetoothLeAudioFactory.setVcPriority(profile, device, priority);
        }
    }

    public List<BluetoothDevice> getLeAudioActiveDevices(BluetoothProfile profile) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        return bluetoothLeAudioFactory == null ? new ArrayList() : bluetoothLeAudioFactory.getLeAudioActiveDevices(profile);
    }

    public boolean setLeAudioActiveDevice(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return false;
        }
        return bluetoothLeAudioFactory.setLeAudioActiveDevice(profile, device);
    }

    public int getLeAudioConnectionPolicy(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return 0;
        }
        return bluetoothLeAudioFactory.getLeAudioConnectionPolicy(profile, device);
    }

    public int getVcConnectionPolicy(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return 0;
        }
        return bluetoothLeAudioFactory.getVcConnectionPolicy(profile, device);
    }

    public boolean setLeAudioConnectionPolicy(BluetoothProfile profile, BluetoothDevice device, int policy) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return false;
        }
        return bluetoothLeAudioFactory.setLeAudioConnectionPolicy(profile, device, policy);
    }

    public boolean setVcConnectionPolicy(BluetoothProfile profile, BluetoothDevice device, int policy) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory == null) {
            return false;
        }
        return bluetoothLeAudioFactory.setVcConnectionPolicy(profile, device, policy);
    }

    public void setVolume(BluetoothProfile profile, int volume) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory != null) {
            bluetoothLeAudioFactory.setVolume(profile, volume);
        }
    }

    public void volumeUp(BluetoothProfile profile, boolean unmute) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory != null) {
            bluetoothLeAudioFactory.volumeUp(profile, unmute);
        }
    }

    public void volumeDown(BluetoothProfile profile, boolean unmute) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory != null) {
            bluetoothLeAudioFactory.volumeDown(profile, unmute);
        }
    }

    public void mute(BluetoothProfile profile) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory != null) {
            bluetoothLeAudioFactory.mute(profile);
        }
    }

    public void unmute(BluetoothProfile profile) {
        BluetoothLeAudioFactory bluetoothLeAudioFactory = sInstance;
        if (bluetoothLeAudioFactory != null) {
            bluetoothLeAudioFactory.unmute(profile);
        }
    }
}
