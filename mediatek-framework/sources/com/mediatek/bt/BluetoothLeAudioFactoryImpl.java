package com.mediatek.bt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothManager;
import android.content.Context;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.FeatureFlagUtils;
import android.util.Log;
import com.mediatek.internal.R;
import java.util.List;
/* loaded from: classes.dex */
public class BluetoothLeAudioFactoryImpl extends BluetoothLeAudioFactory {
    private static final boolean DBG = true;
    public static final String LE_AUDIO_SETTINGS = "settings_bluetooth_le_audio";
    private static final String SUPPORT_LEAUDIO_CG_MODE = "ums-cg";
    private static final String SUPPORT_LEAUDIO_UMS_MODE = "ums";
    private static final String TAG = "BluetoothLeAudioFactoryImpl";

    public BluetoothLeAudioFactoryImpl() {
        FeatureFlagUtils.getAllFeatureFlags().put(LE_AUDIO_SETTINGS, "false");
    }

    public static boolean isLeAudioProfileEnabled(String mode) {
        if (SystemProperties.get("persist.vendor.bluetooth.leaudio_mode", "").equalsIgnoreCase(mode)) {
            return true;
        }
        return false;
    }

    private boolean isLeAudioProfileSupported(IBluetoothManager mManagerService) {
        if (mManagerService == null) {
            return false;
        }
        try {
            return mManagerService.isLeAudioProfileSupported();
        } catch (RemoteException e) {
            Log.e(TAG, "remote expection when calling isLeAudioProfileSupported", e);
            return false;
        }
    }

    public boolean isLeAudioProfileSupported(Context context) {
        boolean mIsLeAudioProfileSupported = context.getResources().getBoolean(R.bool.config_le_audio_profile_supported);
        Log.d(TAG, "isLeAudioProfileSupported: " + mIsLeAudioProfileSupported);
        String value = SystemProperties.get("persist.sys.fflag.override.settings_bluetooth_le_audio");
        if (TextUtils.isEmpty(value)) {
            return mIsLeAudioProfileSupported;
        }
        boolean isLeAudioEnabled = Boolean.parseBoolean(value);
        Log.d(TAG, "set feature flag LE_AUDIO_SETTINGS to " + isLeAudioEnabled);
        FeatureFlagUtils.setEnabled(context, LE_AUDIO_SETTINGS, isLeAudioEnabled);
        if (!isLeAudioEnabled || mIsLeAudioProfileSupported) {
            return mIsLeAudioProfileSupported;
        }
        return true;
    }

    public boolean getProfileProxy(Context context, BluetoothProfile.ServiceListener listener, int profile) {
        boolean succeed = false;
        if (profile != 22 || (!isLeAudioProfileEnabled(SUPPORT_LEAUDIO_UMS_MODE) && !isLeAudioProfileEnabled(SUPPORT_LEAUDIO_CG_MODE))) {
            if (profile == 23) {
                new BluetoothVolumeControl(context, listener);
                succeed = true;
            } else if (profile != 26 || !isLeAudioProfileEnabled(SUPPORT_LEAUDIO_CG_MODE)) {
                Log.w(TAG, "getProfileProxy: profile=" + profile + " not found!!");
            } else {
                new BluetoothTbs(context, listener);
                succeed = true;
            }
        } else if (isLeAudioProfileSupported(context)) {
            new BluetoothLeAudio(context, listener);
            succeed = true;
        }
        Log.d(TAG, "getProfileProxy: profile=" + profile + ";succeed=" + succeed);
        return succeed;
    }

    public void closeProfileProxy(int profile, BluetoothProfile proxy) {
        if (profile == 22 && (isLeAudioProfileEnabled(SUPPORT_LEAUDIO_UMS_MODE) || isLeAudioProfileEnabled(SUPPORT_LEAUDIO_CG_MODE))) {
            BluetoothLeAudio le_audio = (BluetoothLeAudio) proxy;
            le_audio.close();
        } else if (profile == 23) {
            BluetoothVolumeControl vcs = (BluetoothVolumeControl) proxy;
            vcs.close();
        } else if (profile != 26 || !isLeAudioProfileEnabled(SUPPORT_LEAUDIO_CG_MODE)) {
            Log.w(TAG, "closeProfileProxy: profile=" + profile + " not found!!");
        } else {
            BluetoothTbs tbs = (BluetoothTbs) proxy;
            tbs.close();
        }
    }

    public int getProfileCount() {
        return 26;
    }

    public void getSupportedProfiles(IBluetoothManager mManagerService, List<Integer> supportedProfiles) {
        if (isLeAudioProfileSupported(mManagerService)) {
            supportedProfiles.add(22);
        }
    }

    public void onLeAudioSystemReady(BluetoothAdapter adapter, Context context, BluetoothProfile.ServiceListener listener) {
        adapter.getProfileProxy(context, listener, 22);
        Log.v(TAG, "onLeAudioSystemReady: profile=22");
    }

    public boolean isLeAudioConnected(int profile) {
        return profile == 22;
    }

    public void setVcAbsoluteVolume(int index, BluetoothProfile profile) {
        BluetoothLeAudio mLeAudio = (BluetoothLeAudio) profile;
        mLeAudio.setVcAbsoluteVolume(index);
    }

    public int getLeAudioProfileId() {
        return 22;
    }

    public int getVcProfileId() {
        return 23;
    }

    public int getTbsProfileId() {
        return 26;
    }

    public String getLeAudioConnectionStateChangedAction() {
        return BluetoothLeAudio.ACTION_LEAUDIO_CONNECTION_STATE_CHANGED;
    }

    public String getVcConnectionStateChangedAction() {
        return BluetoothVolumeControl.ACTION_VC_CONNECTION_STATE_CHANGED;
    }

    public ParcelUuid getLeAudioUuid() {
        return BluetoothUuidEx.LE_AUDIO;
    }

    public ParcelUuid getVcUuid() {
        return BluetoothUuidEx.VOLUME_CONTROL;
    }

    public String getLeAudioActiveDeviceChangedAction() {
        return BluetoothLeAudio.ACTION_LEAUDIO_ACTIVE_DEVICE_CHANGED;
    }

    public String getLeAudioSetMemberAvailableAction() {
        return BluetoothLeAudio.ACTION_LEAUDIO_SET_MEMBER_AVAILABLE;
    }

    public String getLeAudioConfChangedAction() {
        return BluetoothLeAudio.ACTION_LEAUDIO_CONF_CHANGED;
    }

    public String getLeAudioConnectionRejectedAction() {
        return BluetoothLeAudio.ACTION_LEAUDIO_CONNECTION_REJECTED;
    }

    public String getLeAudioGroupIdExtra() {
        return BluetoothLeAudio.EXTRA_LEAUDIO_GROUP_ID;
    }

    public boolean connectLeAudio(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothLeAudio mService = (BluetoothLeAudio) profile;
        return mService.connect(device);
    }

    public boolean connectVc(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothVolumeControl mService = (BluetoothVolumeControl) profile;
        return mService.connect(device);
    }

    public boolean disconnectLeAudio(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothLeAudio mService = (BluetoothLeAudio) profile;
        return mService.disconnect(device);
    }

    public boolean disconnectVc(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothVolumeControl mService = (BluetoothVolumeControl) profile;
        return mService.disconnect(device);
    }

    public int getLeAudioPriority(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothLeAudio mService = (BluetoothLeAudio) profile;
        return mService.getPriority(device);
    }

    public int getVcPriority(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothVolumeControl mService = (BluetoothVolumeControl) profile;
        return mService.getPriority(device);
    }

    public void setLeAudioPriority(BluetoothProfile profile, BluetoothDevice device, int priority) {
        BluetoothLeAudio mService = (BluetoothLeAudio) profile;
        mService.setPriority(device, priority);
    }

    public void setVcPriority(BluetoothProfile profile, BluetoothDevice device, int priority) {
        BluetoothVolumeControl mService = (BluetoothVolumeControl) profile;
        mService.setPriority(device, priority);
    }

    public List<BluetoothDevice> getLeAudioActiveDevices(BluetoothProfile profile) {
        BluetoothLeAudio mService = (BluetoothLeAudio) profile;
        return mService.getActiveDevices();
    }

    public boolean setLeAudioActiveDevice(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothLeAudio mService = (BluetoothLeAudio) profile;
        return mService.setActiveDevice(device);
    }

    public int getLeAudioConnectionPolicy(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothLeAudio mService = (BluetoothLeAudio) profile;
        return mService.getConnectionPolicy(device);
    }

    public int getVcConnectionPolicy(BluetoothProfile profile, BluetoothDevice device) {
        BluetoothVolumeControl mService = (BluetoothVolumeControl) profile;
        return mService.getConnectionPolicy(device);
    }

    public boolean setLeAudioConnectionPolicy(BluetoothProfile profile, BluetoothDevice device, int policy) {
        BluetoothLeAudio mService = (BluetoothLeAudio) profile;
        return mService.setConnectionPolicy(device, policy);
    }

    public boolean setVcConnectionPolicy(BluetoothProfile profile, BluetoothDevice device, int policy) {
        BluetoothVolumeControl mService = (BluetoothVolumeControl) profile;
        return mService.setConnectionPolicy(device, policy);
    }

    public void setVolume(BluetoothProfile profile, int volume) {
        BluetoothVolumeControl mService = (BluetoothVolumeControl) profile;
        mService.setVolume(volume);
    }

    public void volumeUp(BluetoothProfile profile, boolean unmute) {
        BluetoothVolumeControl mService = (BluetoothVolumeControl) profile;
        mService.volumeUp(unmute);
    }

    public void volumeDown(BluetoothProfile profile, boolean unmute) {
        BluetoothVolumeControl mService = (BluetoothVolumeControl) profile;
        mService.volumeDown(unmute);
    }

    public void mute(BluetoothProfile profile) {
        BluetoothVolumeControl mService = (BluetoothVolumeControl) profile;
        mService.mute();
    }

    public void unmute(BluetoothProfile profile) {
        BluetoothVolumeControl mService = (BluetoothVolumeControl) profile;
        mService.unmute();
    }
}
