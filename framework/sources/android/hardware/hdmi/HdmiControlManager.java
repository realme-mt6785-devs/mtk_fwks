package android.hardware.hdmi;

import android.annotation.SystemApi;
import android.hardware.hdmi.HdmiControlManager;
import android.hardware.hdmi.IHdmiCecSettingChangeListener;
import android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener;
import android.hardware.hdmi.IHdmiControlStatusChangeListener;
import android.hardware.hdmi.IHdmiHotplugEventListener;
import android.os.Binder;
import android.os.RemoteException;
import android.sysprop.HdmiProperties;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.util.ConcurrentUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
@SystemApi
/* loaded from: classes2.dex */
public final class HdmiControlManager {
    public static final String ACTION_OSD_MESSAGE = "android.hardware.hdmi.action.OSD_MESSAGE";
    public static final int AVR_VOLUME_MUTED = 101;
    @SystemApi
    public static final String CEC_SETTING_NAME_HDMI_CEC_ENABLED = "hdmi_cec_enabled";
    @SystemApi
    public static final String CEC_SETTING_NAME_HDMI_CEC_VERSION = "hdmi_cec_version";
    @SystemApi
    public static final String CEC_SETTING_NAME_POWER_CONTROL_MODE = "power_control_mode";
    @SystemApi
    public static final String CEC_SETTING_NAME_POWER_STATE_CHANGE_ON_ACTIVE_SOURCE_LOST = "power_state_change_on_active_source_lost";
    public static final String CEC_SETTING_NAME_RC_PROFILE_SOURCE_HANDLES_CONTENTS_MENU = "rc_profile_source_handles_contents_menu";
    public static final String CEC_SETTING_NAME_RC_PROFILE_SOURCE_HANDLES_MEDIA_CONTEXT_SENSITIVE_MENU = "rc_profile_source_handles_media_context_sensitive_menu";
    public static final String CEC_SETTING_NAME_RC_PROFILE_SOURCE_HANDLES_ROOT_MENU = "rc_profile_source_handles_root_menu";
    public static final String CEC_SETTING_NAME_RC_PROFILE_SOURCE_HANDLES_SETUP_MENU = "rc_profile_source_handles_setup_menu";
    public static final String CEC_SETTING_NAME_RC_PROFILE_SOURCE_HANDLES_TOP_MENU = "rc_profile_source_handles_top_menu";
    public static final String CEC_SETTING_NAME_RC_PROFILE_TV = "rc_profile_tv";
    @SystemApi
    public static final String CEC_SETTING_NAME_SYSTEM_AUDIO_MODE_MUTING = "system_audio_mode_muting";
    @SystemApi
    public static final String CEC_SETTING_NAME_TV_SEND_STANDBY_ON_SLEEP = "tv_send_standby_on_sleep";
    @SystemApi
    public static final String CEC_SETTING_NAME_TV_WAKE_ON_ONE_TOUCH_PLAY = "tv_wake_on_one_touch_play";
    @SystemApi
    public static final String CEC_SETTING_NAME_VOLUME_CONTROL_MODE = "volume_control_enabled";
    public static final int CLEAR_TIMER_STATUS_CEC_DISABLE = 162;
    public static final int CLEAR_TIMER_STATUS_CHECK_RECORDER_CONNECTION = 160;
    public static final int CLEAR_TIMER_STATUS_FAIL_TO_CLEAR_SELECTED_SOURCE = 161;
    public static final int CLEAR_TIMER_STATUS_TIMER_CLEARED = 128;
    public static final int CLEAR_TIMER_STATUS_TIMER_NOT_CLEARED_NO_INFO_AVAILABLE = 2;
    public static final int CLEAR_TIMER_STATUS_TIMER_NOT_CLEARED_NO_MATCHING = 1;
    public static final int CLEAR_TIMER_STATUS_TIMER_NOT_CLEARED_RECORDING = 0;
    public static final int CONTROL_STATE_CHANGED_REASON_SETTING = 1;
    public static final int CONTROL_STATE_CHANGED_REASON_STANDBY = 3;
    public static final int CONTROL_STATE_CHANGED_REASON_START = 0;
    public static final int CONTROL_STATE_CHANGED_REASON_WAKEUP = 2;
    public static final int DEVICE_EVENT_ADD_DEVICE = 1;
    public static final int DEVICE_EVENT_REMOVE_DEVICE = 2;
    public static final int DEVICE_EVENT_UPDATE_DEVICE = 3;
    public static final String EXTRA_MESSAGE_EXTRA_PARAM1 = "android.hardware.hdmi.extra.MESSAGE_EXTRA_PARAM1";
    public static final String EXTRA_MESSAGE_ID = "android.hardware.hdmi.extra.MESSAGE_ID";
    @SystemApi
    public static final int HDMI_CEC_CONTROL_DISABLED = 0;
    @SystemApi
    public static final int HDMI_CEC_CONTROL_ENABLED = 1;
    @SystemApi
    public static final int HDMI_CEC_VERSION_1_4_B = 5;
    @SystemApi
    public static final int HDMI_CEC_VERSION_2_0 = 6;
    private static final int INVALID_PHYSICAL_ADDRESS = 65535;
    public static final int ONE_TOUCH_RECORD_ALREADY_RECORDING = 18;
    public static final int ONE_TOUCH_RECORD_CEC_DISABLED = 51;
    public static final int ONE_TOUCH_RECORD_CHECK_RECORDER_CONNECTION = 49;
    public static final int ONE_TOUCH_RECORD_DISALLOW_TO_COPY = 13;
    public static final int ONE_TOUCH_RECORD_DISALLOW_TO_FUTHER_COPIES = 14;
    public static final int ONE_TOUCH_RECORD_FAIL_TO_RECORD_DISPLAYED_SCREEN = 50;
    public static final int ONE_TOUCH_RECORD_INVALID_EXTERNAL_PHYSICAL_ADDRESS = 10;
    public static final int ONE_TOUCH_RECORD_INVALID_EXTERNAL_PLUG_NUMBER = 9;
    public static final int ONE_TOUCH_RECORD_MEDIA_PROBLEM = 21;
    public static final int ONE_TOUCH_RECORD_MEDIA_PROTECTED = 19;
    public static final int ONE_TOUCH_RECORD_NOT_ENOUGH_SPACE = 22;
    public static final int ONE_TOUCH_RECORD_NO_MEDIA = 16;
    public static final int ONE_TOUCH_RECORD_NO_OR_INSUFFICIENT_CA_ENTITLEMENTS = 12;
    public static final int ONE_TOUCH_RECORD_NO_SOURCE_SIGNAL = 20;
    public static final int ONE_TOUCH_RECORD_OTHER_REASON = 31;
    public static final int ONE_TOUCH_RECORD_PARENT_LOCK_ON = 23;
    public static final int ONE_TOUCH_RECORD_PLAYING = 17;
    public static final int ONE_TOUCH_RECORD_PREVIOUS_RECORDING_IN_PROGRESS = 48;
    public static final int ONE_TOUCH_RECORD_RECORDING_ALREADY_TERMINATED = 27;
    public static final int ONE_TOUCH_RECORD_RECORDING_ANALOGUE_SERVICE = 3;
    public static final int ONE_TOUCH_RECORD_RECORDING_CURRENTLY_SELECTED_SOURCE = 1;
    public static final int ONE_TOUCH_RECORD_RECORDING_DIGITAL_SERVICE = 2;
    public static final int ONE_TOUCH_RECORD_RECORDING_EXTERNAL_INPUT = 4;
    public static final int ONE_TOUCH_RECORD_RECORDING_TERMINATED_NORMALLY = 26;
    public static final int ONE_TOUCH_RECORD_UNABLE_ANALOGUE_SERVICE = 6;
    public static final int ONE_TOUCH_RECORD_UNABLE_DIGITAL_SERVICE = 5;
    public static final int ONE_TOUCH_RECORD_UNABLE_SELECTED_SERVICE = 7;
    public static final int ONE_TOUCH_RECORD_UNSUPPORTED_CA = 11;
    public static final int OSD_MESSAGE_ARC_CONNECTED_INVALID_PORT = 1;
    public static final int OSD_MESSAGE_AVR_VOLUME_CHANGED = 2;
    @SystemApi
    public static final String POWER_CONTROL_MODE_BROADCAST = "broadcast";
    @SystemApi
    public static final String POWER_CONTROL_MODE_NONE = "none";
    @SystemApi
    public static final String POWER_CONTROL_MODE_TV = "to_tv";
    @SystemApi
    public static final String POWER_STATE_CHANGE_ON_ACTIVE_SOURCE_LOST_NONE = "none";
    @SystemApi
    public static final String POWER_STATE_CHANGE_ON_ACTIVE_SOURCE_LOST_STANDBY_NOW = "standby_now";
    public static final int POWER_STATUS_ON = 0;
    public static final int POWER_STATUS_STANDBY = 1;
    public static final int POWER_STATUS_TRANSIENT_TO_ON = 2;
    public static final int POWER_STATUS_TRANSIENT_TO_STANDBY = 3;
    public static final int POWER_STATUS_UNKNOWN = -1;
    public static final int RC_PROFILE_SOURCE_CONTENTS_MENU_HANDLED = 1;
    public static final int RC_PROFILE_SOURCE_CONTENTS_MENU_NOT_HANDLED = 0;
    public static final int RC_PROFILE_SOURCE_MEDIA_CONTEXT_SENSITIVE_MENU_HANDLED = 1;
    public static final int RC_PROFILE_SOURCE_MEDIA_CONTEXT_SENSITIVE_MENU_NOT_HANDLED = 0;
    public static final int RC_PROFILE_SOURCE_ROOT_MENU_HANDLED = 1;
    public static final int RC_PROFILE_SOURCE_ROOT_MENU_NOT_HANDLED = 0;
    public static final int RC_PROFILE_SOURCE_SETUP_MENU_HANDLED = 1;
    public static final int RC_PROFILE_SOURCE_SETUP_MENU_NOT_HANDLED = 0;
    public static final int RC_PROFILE_SOURCE_TOP_MENU_HANDLED = 1;
    public static final int RC_PROFILE_SOURCE_TOP_MENU_NOT_HANDLED = 0;
    public static final int RC_PROFILE_TV_FOUR = 14;
    public static final int RC_PROFILE_TV_NONE = 0;
    public static final int RC_PROFILE_TV_ONE = 2;
    public static final int RC_PROFILE_TV_THREE = 10;
    public static final int RC_PROFILE_TV_TWO = 6;
    @Deprecated
    public static final int RESULT_ALREADY_IN_PROGRESS = 4;
    public static final int RESULT_COMMUNICATION_FAILED = 7;
    public static final int RESULT_EXCEPTION = 5;
    public static final int RESULT_INCORRECT_MODE = 6;
    public static final int RESULT_SOURCE_NOT_AVAILABLE = 2;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_TARGET_NOT_AVAILABLE = 3;
    public static final int RESULT_TIMEOUT = 1;
    @SystemApi
    public static final int SYSTEM_AUDIO_MODE_MUTING_DISABLED = 0;
    @SystemApi
    public static final int SYSTEM_AUDIO_MODE_MUTING_ENABLED = 1;
    private static final String TAG = "HdmiControlManager";
    public static final int TIMER_RECORDING_RESULT_EXTRA_CEC_DISABLED = 3;
    public static final int TIMER_RECORDING_RESULT_EXTRA_CHECK_RECORDER_CONNECTION = 1;
    public static final int TIMER_RECORDING_RESULT_EXTRA_FAIL_TO_RECORD_SELECTED_SOURCE = 2;
    public static final int TIMER_RECORDING_RESULT_EXTRA_NO_ERROR = 0;
    public static final int TIMER_RECORDING_TYPE_ANALOGUE = 2;
    public static final int TIMER_RECORDING_TYPE_DIGITAL = 1;
    public static final int TIMER_RECORDING_TYPE_EXTERNAL = 3;
    public static final int TIMER_STATUS_MEDIA_INFO_NOT_PRESENT = 2;
    public static final int TIMER_STATUS_MEDIA_INFO_PRESENT_NOT_PROTECTED = 0;
    public static final int TIMER_STATUS_MEDIA_INFO_PRESENT_PROTECTED = 1;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_CA_NOT_SUPPORTED = 6;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_CLOCK_FAILURE = 10;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_DATE_OUT_OF_RANGE = 2;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_DUPLICATED = 14;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_INVALID_EXTERNAL_PHYSICAL_NUMBER = 5;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_INVALID_EXTERNAL_PLUG_NUMBER = 4;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_INVALID_SEQUENCE = 3;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_NO_CA_ENTITLEMENTS = 7;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_NO_FREE_TIME = 1;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_PARENTAL_LOCK_ON = 9;
    public static final int TIMER_STATUS_NOT_PROGRAMMED_UNSUPPORTED_RESOLUTION = 8;
    public static final int TIMER_STATUS_PROGRAMMED_INFO_ENOUGH_SPACE = 8;
    public static final int TIMER_STATUS_PROGRAMMED_INFO_MIGHT_NOT_ENOUGH_SPACE = 11;
    public static final int TIMER_STATUS_PROGRAMMED_INFO_NOT_ENOUGH_SPACE = 9;
    public static final int TIMER_STATUS_PROGRAMMED_INFO_NO_MEDIA_INFO = 10;
    @SystemApi
    public static final int TV_SEND_STANDBY_ON_SLEEP_DISABLED = 0;
    @SystemApi
    public static final int TV_SEND_STANDBY_ON_SLEEP_ENABLED = 1;
    @SystemApi
    public static final int TV_WAKE_ON_ONE_TOUCH_PLAY_DISABLED = 0;
    @SystemApi
    public static final int TV_WAKE_ON_ONE_TOUCH_PLAY_ENABLED = 1;
    @SystemApi
    public static final int VOLUME_CONTROL_DISABLED = 0;
    @SystemApi
    public static final int VOLUME_CONTROL_ENABLED = 1;
    private final boolean mHasAudioSystemDevice;
    private final boolean mHasPlaybackDevice;
    private final boolean mHasSwitchDevice;
    private final boolean mHasTvDevice;
    private final boolean mIsSwitchDevice;
    private final IHdmiControlService mService;
    private int mLocalPhysicalAddress = 65535;
    private final Object mLock = new Object();
    private final ArrayMap<HotplugEventListener, IHdmiHotplugEventListener> mHotplugEventListeners = new ArrayMap<>();
    private final ArrayMap<HdmiControlStatusChangeListener, IHdmiControlStatusChangeListener> mHdmiControlStatusChangeListeners = new ArrayMap<>();
    private final ArrayMap<HdmiCecVolumeControlFeatureListener, IHdmiCecVolumeControlFeatureListener> mHdmiCecVolumeControlFeatureListeners = new ArrayMap<>();
    private final ArrayMap<String, ArrayMap<CecSettingChangeListener, IHdmiCecSettingChangeListener>> mCecSettingChangeListeners = new ArrayMap<>();

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ActiveSourceLostBehavior {
    }

    @SystemApi
    /* loaded from: classes2.dex */
    public interface CecSettingChangeListener {
        void onChange(String str);
    }

    /* loaded from: classes2.dex */
    public @interface CecSettingName {
    }

    @SystemApi
    /* loaded from: classes2.dex */
    public @interface ControlCallbackResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface HdmiCecControl {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface HdmiCecVersion {
    }

    /* loaded from: classes2.dex */
    public interface HdmiCecVolumeControlFeatureListener {
        void onHdmiCecVolumeControlFeature(int i);
    }

    /* loaded from: classes2.dex */
    public interface HdmiControlStatusChangeListener {
        void onStatusChange(int i, boolean z);
    }

    @SystemApi
    /* loaded from: classes2.dex */
    public interface HotplugEventListener {
        void onReceived(HdmiHotplugEvent hdmiHotplugEvent);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface PowerControlMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface RcProfileSourceHandlesContentsMenu {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface RcProfileSourceHandlesMediaContextSensitiveMenu {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface RcProfileSourceHandlesRootMenu {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface RcProfileSourceHandlesSetupMenu {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface RcProfileSourceHandlesTopMenu {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface RcProfileTv {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface SystemAudioModeMuting {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface TvSendStandbyOnSleep {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface TvWakeOnOneTouchPlay {
    }

    @SystemApi
    /* loaded from: classes2.dex */
    public interface VendorCommandListener {
        void onControlStateChanged(boolean z, int i);

        void onReceived(int i, int i2, byte[] bArr, boolean z);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface VolumeControl {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocalPhysicalAddress(int physicalAddress) {
        synchronized (this.mLock) {
            this.mLocalPhysicalAddress = physicalAddress;
        }
    }

    private int getLocalPhysicalAddress() {
        int i;
        synchronized (this.mLock) {
            i = this.mLocalPhysicalAddress;
        }
        return i;
    }

    public HdmiControlManager(IHdmiControlService service) {
        this.mService = service;
        int[] types = null;
        if (service != null) {
            try {
                types = service.getSupportedTypes();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        this.mHasTvDevice = hasDeviceType(types, 0);
        this.mHasPlaybackDevice = hasDeviceType(types, 4);
        this.mHasAudioSystemDevice = hasDeviceType(types, 5);
        this.mHasSwitchDevice = hasDeviceType(types, 6);
        this.mIsSwitchDevice = HdmiProperties.is_switch().orElse(false).booleanValue();
        addHotplugEventListener(new ClientHotplugEventListener(this, null));
    }

    /* loaded from: classes2.dex */
    private final class ClientHotplugEventListener implements HotplugEventListener {
        private ClientHotplugEventListener() {
        }

        /* synthetic */ ClientHotplugEventListener(HdmiControlManager x0, AnonymousClass1 x1) {
            this();
        }

        @Override // android.hardware.hdmi.HdmiControlManager.HotplugEventListener
        public void onReceived(HdmiHotplugEvent event) {
            int i;
            new ArrayList();
            try {
                List<HdmiPortInfo> ports = HdmiControlManager.this.mService.getPortInfo();
                if (ports.isEmpty()) {
                    Log.e(HdmiControlManager.TAG, "Can't find port info, not updating connected status. Hotplug event:" + event);
                    return;
                }
                for (HdmiPortInfo port : ports) {
                    if (port.getId() == event.getPort()) {
                        if (port.getType() == 1) {
                            HdmiControlManager hdmiControlManager = HdmiControlManager.this;
                            if (event.isConnected()) {
                                i = port.getAddress();
                            } else {
                                i = 65535;
                            }
                            hdmiControlManager.setLocalPhysicalAddress(i);
                            return;
                        }
                        return;
                    }
                }
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    private static boolean hasDeviceType(int[] types, int type) {
        if (types == null) {
            return false;
        }
        for (int t : types) {
            if (t == type) {
                return true;
            }
        }
        return false;
    }

    @SystemApi
    public HdmiClient getClient(int type) {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService == null) {
            return null;
        }
        switch (type) {
            case 0:
                if (this.mHasTvDevice) {
                    return new HdmiTvClient(iHdmiControlService);
                }
                return null;
            case 1:
            case 2:
            case 3:
            default:
                return null;
            case 4:
                if (this.mHasPlaybackDevice) {
                    return new HdmiPlaybackClient(iHdmiControlService);
                }
                return null;
            case 5:
                if (this.mHasAudioSystemDevice) {
                    return new HdmiAudioSystemClient(iHdmiControlService);
                }
                return null;
            case 6:
                if (this.mHasSwitchDevice || this.mIsSwitchDevice) {
                    return new HdmiSwitchClient(iHdmiControlService);
                }
                return null;
        }
    }

    @SystemApi
    public HdmiPlaybackClient getPlaybackClient() {
        return (HdmiPlaybackClient) getClient(4);
    }

    @SystemApi
    public HdmiTvClient getTvClient() {
        return (HdmiTvClient) getClient(0);
    }

    public HdmiAudioSystemClient getAudioSystemClient() {
        return (HdmiAudioSystemClient) getClient(5);
    }

    public HdmiSwitchClient getSwitchClient() {
        return (HdmiSwitchClient) getClient(6);
    }

    @SystemApi
    public List<HdmiDeviceInfo> getConnectedDevices() {
        try {
            return this.mService.getDeviceList();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public List<HdmiDeviceInfo> getConnectedDevicesList() {
        try {
            return this.mService.getDeviceList();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void powerOffDevice(HdmiDeviceInfo deviceInfo) {
        Objects.requireNonNull(deviceInfo);
        try {
            this.mService.powerOffRemoteDevice(deviceInfo.getLogicalAddress(), deviceInfo.getDevicePowerStatus());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public void powerOffRemoteDevice(HdmiDeviceInfo deviceInfo) {
        Objects.requireNonNull(deviceInfo);
        try {
            this.mService.powerOffRemoteDevice(deviceInfo.getLogicalAddress(), deviceInfo.getDevicePowerStatus());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void powerOnDevice(HdmiDeviceInfo deviceInfo) {
        Objects.requireNonNull(deviceInfo);
        try {
            this.mService.powerOnRemoteDevice(deviceInfo.getLogicalAddress(), deviceInfo.getDevicePowerStatus());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public void powerOnRemoteDevice(HdmiDeviceInfo deviceInfo) {
        Objects.requireNonNull(deviceInfo);
        try {
            this.mService.powerOnRemoteDevice(deviceInfo.getLogicalAddress(), deviceInfo.getDevicePowerStatus());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setActiveSource(HdmiDeviceInfo deviceInfo) {
        Objects.requireNonNull(deviceInfo);
        try {
            this.mService.askRemoteDeviceToBecomeActiveSource(deviceInfo.getPhysicalAddress());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public void requestRemoteDeviceToBecomeActiveSource(HdmiDeviceInfo deviceInfo) {
        Objects.requireNonNull(deviceInfo);
        try {
            this.mService.askRemoteDeviceToBecomeActiveSource(deviceInfo.getPhysicalAddress());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setStandbyMode(boolean isStandbyModeOn) {
        try {
            this.mService.setStandbyMode(isStandbyModeOn);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void toggleAndFollowTvPower() {
        try {
            this.mService.toggleAndFollowTvPower();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean shouldHandleTvPowerKey() {
        try {
            return this.mService.shouldHandleTvPowerKey();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setHdmiCecVolumeControlEnabled(int hdmiCecVolumeControlEnabled) {
        try {
            this.mService.setCecSettingIntValue(CEC_SETTING_NAME_VOLUME_CONTROL_MODE, hdmiCecVolumeControlEnabled);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public int getHdmiCecVolumeControlEnabled() {
        try {
            return this.mService.getCecSettingIntValue(CEC_SETTING_NAME_VOLUME_CONTROL_MODE);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean getSystemAudioMode() {
        try {
            return this.mService.getSystemAudioMode();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public int getPhysicalAddress() {
        return getLocalPhysicalAddress();
    }

    @SystemApi
    public boolean isDeviceConnected(HdmiDeviceInfo targetDevice) {
        int targetPhysicalAddress;
        Objects.requireNonNull(targetDevice);
        int physicalAddress = getLocalPhysicalAddress();
        return (physicalAddress == 65535 || (targetPhysicalAddress = targetDevice.getPhysicalAddress()) == 65535 || HdmiUtils.getLocalPortFromPhysicalAddress(targetPhysicalAddress, physicalAddress) == -1) ? false : true;
    }

    @SystemApi
    @Deprecated
    public boolean isRemoteDeviceConnected(HdmiDeviceInfo targetDevice) {
        int targetPhysicalAddress;
        Objects.requireNonNull(targetDevice);
        int physicalAddress = getLocalPhysicalAddress();
        return (physicalAddress == 65535 || (targetPhysicalAddress = targetDevice.getPhysicalAddress()) == 65535 || HdmiUtils.getLocalPortFromPhysicalAddress(targetPhysicalAddress, physicalAddress) == -1) ? false : true;
    }

    @SystemApi
    public void addHotplugEventListener(HotplugEventListener listener) {
        addHotplugEventListener(ConcurrentUtils.DIRECT_EXECUTOR, listener);
    }

    @SystemApi
    public void addHotplugEventListener(Executor executor, HotplugEventListener listener) {
        if (this.mService == null) {
            Log.e(TAG, "HdmiControlService is not available");
        } else if (this.mHotplugEventListeners.containsKey(listener)) {
            Log.e(TAG, "listener is already registered");
        } else {
            IHdmiHotplugEventListener wrappedListener = getHotplugEventListenerWrapper(executor, listener);
            this.mHotplugEventListeners.put(listener, wrappedListener);
            try {
                this.mService.addHotplugEventListener(wrappedListener);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    @SystemApi
    public void removeHotplugEventListener(HotplugEventListener listener) {
        if (this.mService == null) {
            Log.e(TAG, "HdmiControlService is not available");
            return;
        }
        IHdmiHotplugEventListener wrappedListener = this.mHotplugEventListeners.remove(listener);
        if (wrappedListener == null) {
            Log.e(TAG, "tried to remove not-registered listener");
            return;
        }
        try {
            this.mService.removeHotplugEventListener(wrappedListener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.hardware.hdmi.HdmiControlManager$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 extends IHdmiHotplugEventListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ HotplugEventListener val$listener;

        AnonymousClass1(Executor executor, HotplugEventListener hotplugEventListener) {
            this.val$executor = executor;
            this.val$listener = hotplugEventListener;
        }

        @Override // android.hardware.hdmi.IHdmiHotplugEventListener
        public void onReceived(final HdmiHotplugEvent event) {
            long token = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final HotplugEventListener hotplugEventListener = this.val$listener;
                executor.execute(new Runnable() { // from class: android.hardware.hdmi.HdmiControlManager$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        HdmiControlManager.HotplugEventListener.this.onReceived(event);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(token);
            }
        }
    }

    private IHdmiHotplugEventListener getHotplugEventListenerWrapper(Executor executor, HotplugEventListener listener) {
        return new AnonymousClass1(executor, listener);
    }

    public void addHdmiControlStatusChangeListener(HdmiControlStatusChangeListener listener) {
        addHdmiControlStatusChangeListener(ConcurrentUtils.DIRECT_EXECUTOR, listener);
    }

    public void addHdmiControlStatusChangeListener(Executor executor, HdmiControlStatusChangeListener listener) {
        if (this.mService == null) {
            Log.e(TAG, "HdmiControlService is not available");
        } else if (this.mHdmiControlStatusChangeListeners.containsKey(listener)) {
            Log.e(TAG, "listener is already registered");
        } else {
            IHdmiControlStatusChangeListener wrappedListener = getHdmiControlStatusChangeListenerWrapper(executor, listener);
            this.mHdmiControlStatusChangeListeners.put(listener, wrappedListener);
            try {
                this.mService.addHdmiControlStatusChangeListener(wrappedListener);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void removeHdmiControlStatusChangeListener(HdmiControlStatusChangeListener listener) {
        if (this.mService == null) {
            Log.e(TAG, "HdmiControlService is not available");
            return;
        }
        IHdmiControlStatusChangeListener wrappedListener = this.mHdmiControlStatusChangeListeners.remove(listener);
        if (wrappedListener == null) {
            Log.e(TAG, "tried to remove not-registered listener");
            return;
        }
        try {
            this.mService.removeHdmiControlStatusChangeListener(wrappedListener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.hardware.hdmi.HdmiControlManager$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass2 extends IHdmiControlStatusChangeListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ HdmiControlStatusChangeListener val$listener;

        AnonymousClass2(Executor executor, HdmiControlStatusChangeListener hdmiControlStatusChangeListener) {
            this.val$executor = executor;
            this.val$listener = hdmiControlStatusChangeListener;
        }

        @Override // android.hardware.hdmi.IHdmiControlStatusChangeListener
        public void onStatusChange(final int isCecEnabled, final boolean isCecAvailable) {
            long token = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final HdmiControlStatusChangeListener hdmiControlStatusChangeListener = this.val$listener;
                executor.execute(new Runnable() { // from class: android.hardware.hdmi.HdmiControlManager$2$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        HdmiControlManager.HdmiControlStatusChangeListener.this.onStatusChange(isCecEnabled, isCecAvailable);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(token);
            }
        }
    }

    private IHdmiControlStatusChangeListener getHdmiControlStatusChangeListenerWrapper(Executor executor, HdmiControlStatusChangeListener listener) {
        return new AnonymousClass2(executor, listener);
    }

    public void addHdmiCecVolumeControlFeatureListener(Executor executor, HdmiCecVolumeControlFeatureListener listener) {
        if (this.mService == null) {
            Log.e(TAG, "HdmiControlService is not available");
        } else if (this.mHdmiCecVolumeControlFeatureListeners.containsKey(listener)) {
            Log.e(TAG, "listener is already registered");
        } else {
            IHdmiCecVolumeControlFeatureListener wrappedListener = createHdmiCecVolumeControlFeatureListenerWrapper(executor, listener);
            this.mHdmiCecVolumeControlFeatureListeners.put(listener, wrappedListener);
            try {
                this.mService.addHdmiCecVolumeControlFeatureListener(wrappedListener);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void removeHdmiCecVolumeControlFeatureListener(HdmiCecVolumeControlFeatureListener listener) {
        if (this.mService == null) {
            Log.e(TAG, "HdmiControlService is not available");
            return;
        }
        IHdmiCecVolumeControlFeatureListener wrappedListener = this.mHdmiCecVolumeControlFeatureListeners.remove(listener);
        if (wrappedListener == null) {
            Log.e(TAG, "tried to remove not-registered listener");
            return;
        }
        try {
            this.mService.removeHdmiCecVolumeControlFeatureListener(wrappedListener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.hardware.hdmi.HdmiControlManager$3  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass3 extends IHdmiCecVolumeControlFeatureListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ HdmiCecVolumeControlFeatureListener val$listener;

        AnonymousClass3(Executor executor, HdmiCecVolumeControlFeatureListener hdmiCecVolumeControlFeatureListener) {
            this.val$executor = executor;
            this.val$listener = hdmiCecVolumeControlFeatureListener;
        }

        @Override // android.hardware.hdmi.IHdmiCecVolumeControlFeatureListener
        public void onHdmiCecVolumeControlFeature(final int enabled) {
            long token = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final HdmiCecVolumeControlFeatureListener hdmiCecVolumeControlFeatureListener = this.val$listener;
                executor.execute(new Runnable() { // from class: android.hardware.hdmi.HdmiControlManager$3$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        HdmiControlManager.HdmiCecVolumeControlFeatureListener.this.onHdmiCecVolumeControlFeature(enabled);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(token);
            }
        }
    }

    private IHdmiCecVolumeControlFeatureListener createHdmiCecVolumeControlFeatureListenerWrapper(Executor executor, HdmiCecVolumeControlFeatureListener listener) {
        return new AnonymousClass3(executor, listener);
    }

    private void addCecSettingChangeListener(String setting, Executor executor, CecSettingChangeListener listener) {
        if (this.mService == null) {
            Log.e(TAG, "HdmiControlService is not available");
        } else if (!this.mCecSettingChangeListeners.containsKey(setting) || !this.mCecSettingChangeListeners.get(setting).containsKey(listener)) {
            IHdmiCecSettingChangeListener wrappedListener = getCecSettingChangeListenerWrapper(executor, listener);
            if (!this.mCecSettingChangeListeners.containsKey(setting)) {
                this.mCecSettingChangeListeners.put(setting, new ArrayMap<>());
            }
            this.mCecSettingChangeListeners.get(setting).put(listener, wrappedListener);
            try {
                this.mService.addCecSettingChangeListener(setting, wrappedListener);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "listener is already registered");
        }
    }

    private void removeCecSettingChangeListener(String setting, CecSettingChangeListener listener) {
        if (this.mService == null) {
            Log.e(TAG, "HdmiControlService is not available");
            return;
        }
        IHdmiCecSettingChangeListener wrappedListener = !this.mCecSettingChangeListeners.containsKey(setting) ? null : this.mCecSettingChangeListeners.get(setting).remove(listener);
        if (wrappedListener == null) {
            Log.e(TAG, "tried to remove not-registered listener");
            return;
        }
        try {
            this.mService.removeCecSettingChangeListener(setting, wrappedListener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.hardware.hdmi.HdmiControlManager$4  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass4 extends IHdmiCecSettingChangeListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ CecSettingChangeListener val$listener;

        AnonymousClass4(Executor executor, CecSettingChangeListener cecSettingChangeListener) {
            this.val$executor = executor;
            this.val$listener = cecSettingChangeListener;
        }

        @Override // android.hardware.hdmi.IHdmiCecSettingChangeListener
        public void onChange(final String setting) {
            long token = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CecSettingChangeListener cecSettingChangeListener = this.val$listener;
                executor.execute(new Runnable() { // from class: android.hardware.hdmi.HdmiControlManager$4$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        HdmiControlManager.CecSettingChangeListener.this.onChange(setting);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(token);
            }
        }
    }

    private IHdmiCecSettingChangeListener getCecSettingChangeListenerWrapper(Executor executor, CecSettingChangeListener listener) {
        return new AnonymousClass4(executor, listener);
    }

    @SystemApi
    public List<String> getUserCecSettings() {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                return iHdmiControlService.getUserCecSettings();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public List<String> getAllowedCecSettingStringValues(String name) {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                return iHdmiControlService.getAllowedCecSettingStringValues(name);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public List<Integer> getAllowedCecSettingIntValues(String name) {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                int[] allowedValues = iHdmiControlService.getAllowedCecSettingIntValues(name);
                return (List) Arrays.stream(allowedValues).boxed().collect(Collectors.toList());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public void setHdmiCecEnabled(int value) {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                iHdmiControlService.setCecSettingIntValue(CEC_SETTING_NAME_HDMI_CEC_ENABLED, value);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public int getHdmiCecEnabled() {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                return iHdmiControlService.getCecSettingIntValue(CEC_SETTING_NAME_HDMI_CEC_ENABLED);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public void addHdmiCecEnabledChangeListener(CecSettingChangeListener listener) {
        addHdmiCecEnabledChangeListener(ConcurrentUtils.DIRECT_EXECUTOR, listener);
    }

    @SystemApi
    public void addHdmiCecEnabledChangeListener(Executor executor, CecSettingChangeListener listener) {
        addCecSettingChangeListener(CEC_SETTING_NAME_HDMI_CEC_ENABLED, executor, listener);
    }

    @SystemApi
    public void removeHdmiCecEnabledChangeListener(CecSettingChangeListener listener) {
        removeCecSettingChangeListener(CEC_SETTING_NAME_HDMI_CEC_ENABLED, listener);
    }

    @SystemApi
    public void setHdmiCecVersion(int value) {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                iHdmiControlService.setCecSettingIntValue(CEC_SETTING_NAME_HDMI_CEC_VERSION, value);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public int getHdmiCecVersion() {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                return iHdmiControlService.getCecSettingIntValue(CEC_SETTING_NAME_HDMI_CEC_VERSION);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public void setPowerControlMode(String value) {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                iHdmiControlService.setCecSettingStringValue(CEC_SETTING_NAME_POWER_CONTROL_MODE, value);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public String getPowerControlMode() {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                return iHdmiControlService.getCecSettingStringValue(CEC_SETTING_NAME_POWER_CONTROL_MODE);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public void setPowerStateChangeOnActiveSourceLost(String value) {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                iHdmiControlService.setCecSettingStringValue(CEC_SETTING_NAME_POWER_STATE_CHANGE_ON_ACTIVE_SOURCE_LOST, value);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public String getPowerStateChangeOnActiveSourceLost() {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                return iHdmiControlService.getCecSettingStringValue(CEC_SETTING_NAME_POWER_STATE_CHANGE_ON_ACTIVE_SOURCE_LOST);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public void setSystemAudioModeMuting(int value) {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                iHdmiControlService.setCecSettingIntValue(CEC_SETTING_NAME_SYSTEM_AUDIO_MODE_MUTING, value);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public int getSystemAudioModeMuting() {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                return iHdmiControlService.getCecSettingIntValue(CEC_SETTING_NAME_SYSTEM_AUDIO_MODE_MUTING);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public void setTvWakeOnOneTouchPlay(int value) {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                iHdmiControlService.setCecSettingIntValue(CEC_SETTING_NAME_TV_WAKE_ON_ONE_TOUCH_PLAY, value);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public int getTvWakeOnOneTouchPlay() {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                return iHdmiControlService.getCecSettingIntValue(CEC_SETTING_NAME_TV_WAKE_ON_ONE_TOUCH_PLAY);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public void setTvSendStandbyOnSleep(int value) {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                iHdmiControlService.setCecSettingIntValue(CEC_SETTING_NAME_TV_SEND_STANDBY_ON_SLEEP, value);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }

    @SystemApi
    public int getTvSendStandbyOnSleep() {
        IHdmiControlService iHdmiControlService = this.mService;
        if (iHdmiControlService != null) {
            try {
                return iHdmiControlService.getCecSettingIntValue(CEC_SETTING_NAME_TV_SEND_STANDBY_ON_SLEEP);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            Log.e(TAG, "HdmiControlService is not available");
            throw new RuntimeException("HdmiControlService is not available");
        }
    }
}
