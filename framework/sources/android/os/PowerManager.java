package android.os;

import android.annotation.SystemApi;
import android.app.PropertyInvalidatedCache;
import android.content.Context;
import android.media.AudioSystem;
import android.os.IThermalStatusListener;
import android.os.PowerManager;
import android.service.dreams.Sandman;
import android.sysprop.InitProperties;
import android.util.ArrayMap;
import android.util.Log;
import android.util.proto.ProtoOutputStream;
import com.android.internal.R;
import com.android.internal.location.GpsNetInitiatedHandler;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes2.dex */
public final class PowerManager extends OplusBasePowerManager {
    public static final int ACQUIRE_CAUSES_WAKEUP = 268435456;
    public static final String ACTION_DEVICE_IDLE_MODE_CHANGED = "android.os.action.DEVICE_IDLE_MODE_CHANGED";
    public static final String ACTION_ENHANCED_DISCHARGE_PREDICTION_CHANGED = "android.os.action.ENHANCED_DISCHARGE_PREDICTION_CHANGED";
    public static final String ACTION_LIGHT_DEVICE_IDLE_MODE_CHANGED = "android.os.action.LIGHT_DEVICE_IDLE_MODE_CHANGED";
    public static final String ACTION_POWER_SAVE_MODE_CHANGED = "android.os.action.POWER_SAVE_MODE_CHANGED";
    public static final String ACTION_POWER_SAVE_MODE_CHANGED_INTERNAL = "android.os.action.POWER_SAVE_MODE_CHANGED_INTERNAL";
    public static final String ACTION_POWER_SAVE_TEMP_WHITELIST_CHANGED = "android.os.action.POWER_SAVE_TEMP_WHITELIST_CHANGED";
    public static final String ACTION_POWER_SAVE_WHITELIST_CHANGED = "android.os.action.POWER_SAVE_WHITELIST_CHANGED";
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_DEFAULT = 2;
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_DEFAULT_VR = 7;
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_DIM = 3;
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_DOZE = 4;
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_MAXIMUM = 1;
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_MAXIMUM_VR = 6;
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_MINIMUM = 0;
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_MINIMUM_VR = 5;
    public static final int BRIGHTNESS_DEFAULT = -1;
    public static final int BRIGHTNESS_INVALID = -1;
    public static final float BRIGHTNESS_INVALID_FLOAT = Float.NaN;
    public static final float BRIGHTNESS_MAX = 1.0f;
    public static final float BRIGHTNESS_MIN = 0.0f;
    public static final int BRIGHTNESS_OFF = 0;
    public static final float BRIGHTNESS_OFF_FLOAT = -1.0f;
    public static final int BRIGHTNESS_ON = 255;
    private static final String CACHE_KEY_IS_INTERACTIVE_PROPERTY = "cache_key.is_interactive";
    private static final String CACHE_KEY_IS_POWER_SAVE_MODE_PROPERTY = "cache_key.is_power_save_mode";
    public static final int DOZE_WAKE_LOCK = 64;
    public static final int DRAW_WAKE_LOCK = 128;
    @Deprecated
    public static final int FULL_WAKE_LOCK = 26;
    public static final int GO_TO_SLEEP_FLAG_NO_DOZE = 1;
    public static final int GO_TO_SLEEP_REASON_ACCESSIBILITY = 7;
    public static final int GO_TO_SLEEP_REASON_APPLICATION = 0;
    public static final int GO_TO_SLEEP_REASON_DEVICE_ADMIN = 1;
    public static final int GO_TO_SLEEP_REASON_DISPLAY_GROUPS_TURNED_OFF = 12;
    public static final int GO_TO_SLEEP_REASON_DISPLAY_GROUP_REMOVED = 11;
    public static final int GO_TO_SLEEP_REASON_FORCE_SUSPEND = 8;
    public static final int GO_TO_SLEEP_REASON_HDMI = 5;
    public static final int GO_TO_SLEEP_REASON_INATTENTIVE = 9;
    public static final int GO_TO_SLEEP_REASON_LID_SWITCH = 3;
    public static final int GO_TO_SLEEP_REASON_MAX = 15;
    public static final int GO_TO_SLEEP_REASON_MIN = 0;
    public static final int GO_TO_SLEEP_REASON_POWER_BUTTON = 4;
    public static final int GO_TO_SLEEP_REASON_QUIESCENT = 10;
    public static final int GO_TO_SLEEP_REASON_SLEEP_BUTTON = 6;
    public static final int GO_TO_SLEEP_REASON_TIMEOUT = 2;
    public static final int LOCATION_MODE_ALL_DISABLED_WHEN_SCREEN_OFF = 2;
    public static final int LOCATION_MODE_FOREGROUND_ONLY = 3;
    public static final int LOCATION_MODE_GPS_DISABLED_WHEN_SCREEN_OFF = 1;
    public static final int LOCATION_MODE_NO_CHANGE = 0;
    public static final int LOCATION_MODE_THROTTLE_REQUESTS_WHEN_SCREEN_OFF = 4;
    private static final int MAX_CACHE_ENTRIES = 1;
    public static final int MAX_LOCATION_MODE = 4;
    public static final int MAX_SOUND_TRIGGER_MODE = 2;
    private static final int MINIMUM_HEADROOM_TIME_MILLIS = 500;
    public static final int MIN_LOCATION_MODE = 0;
    public static final int MIN_SOUND_TRIGGER_MODE = 0;
    public static final int ON_AFTER_RELEASE = 536870912;
    public static final int PARTIAL_WAKE_LOCK = 1;
    @SystemApi
    public static final int POWER_SAVE_MODE_TRIGGER_DYNAMIC = 1;
    @SystemApi
    public static final int POWER_SAVE_MODE_TRIGGER_PERCENTAGE = 0;
    public static final int PRE_IDLE_TIMEOUT_MODE_LONG = 1;
    public static final int PRE_IDLE_TIMEOUT_MODE_NORMAL = 0;
    public static final int PRE_IDLE_TIMEOUT_MODE_SHORT = 2;
    public static final int PROXIMITY_SCREEN_OFF_WAKE_LOCK = 32;
    public static final String REBOOT_QUIESCENT = "quiescent";
    public static final String REBOOT_RECOVERY = "recovery";
    public static final String REBOOT_RECOVERY_UPDATE = "recovery-update";
    public static final String REBOOT_REQUESTED_BY_DEVICE_OWNER = "deviceowner";
    public static final String REBOOT_SAFE_MODE = "safemode";
    @SystemApi
    public static final String REBOOT_USERSPACE = "userspace";
    public static final int RELEASE_FLAG_TIMEOUT = 65536;
    public static final int RELEASE_FLAG_WAIT_FOR_NO_PROXIMITY = 1;
    @Deprecated
    public static final int SCREEN_BRIGHT_WAKE_LOCK = 10;
    @Deprecated
    public static final int SCREEN_DIM_WAKE_LOCK = 6;
    public static final String SHUTDOWN_BATTERY_THERMAL_STATE = "thermal,battery";
    public static final String SHUTDOWN_LOW_BATTERY = "battery";
    public static final int SHUTDOWN_REASON_BATTERY_THERMAL = 6;
    public static final int SHUTDOWN_REASON_LOW_BATTERY = 5;
    public static final int SHUTDOWN_REASON_REBOOT = 2;
    public static final int SHUTDOWN_REASON_SHUTDOWN = 1;
    public static final int SHUTDOWN_REASON_THERMAL_SHUTDOWN = 4;
    public static final int SHUTDOWN_REASON_UNKNOWN = 0;
    public static final int SHUTDOWN_REASON_USER_REQUESTED = 3;
    public static final String SHUTDOWN_THERMAL_STATE = "thermal";
    public static final String SHUTDOWN_USER_REQUESTED = "userrequested";
    @SystemApi
    public static final int SOUND_TRIGGER_MODE_ALL_DISABLED = 2;
    @SystemApi
    public static final int SOUND_TRIGGER_MODE_ALL_ENABLED = 0;
    @SystemApi
    public static final int SOUND_TRIGGER_MODE_CRITICAL_ONLY = 1;
    private static final String TAG = "PowerManager";
    public static final int THERMAL_STATUS_CRITICAL = 4;
    public static final int THERMAL_STATUS_EMERGENCY = 5;
    public static final int THERMAL_STATUS_LIGHT = 1;
    public static final int THERMAL_STATUS_MODERATE = 2;
    public static final int THERMAL_STATUS_NONE = 0;
    public static final int THERMAL_STATUS_SEVERE = 3;
    public static final int THERMAL_STATUS_SHUTDOWN = 6;
    public static final int UNIMPORTANT_FOR_LOGGING = 1073741824;
    @SystemApi
    public static final int USER_ACTIVITY_EVENT_ACCESSIBILITY = 3;
    public static final int USER_ACTIVITY_EVENT_ATTENTION = 4;
    @SystemApi
    public static final int USER_ACTIVITY_EVENT_BUTTON = 1;
    public static final int USER_ACTIVITY_EVENT_FACE_DOWN = 5;
    @SystemApi
    public static final int USER_ACTIVITY_EVENT_OTHER = 0;
    @SystemApi
    public static final int USER_ACTIVITY_EVENT_TOUCH = 2;
    @SystemApi
    public static final int USER_ACTIVITY_FLAG_INDIRECT = 2;
    @SystemApi
    public static final int USER_ACTIVITY_FLAG_NO_CHANGE_LIGHTS = 1;
    public static final int WAKE_LOCK_LEVEL_MASK = 65535;
    public static final int WAKE_REASON_APPLICATION = 2;
    public static final int WAKE_REASON_CAMERA_LAUNCH = 5;
    public static final int WAKE_REASON_DISPLAY_GROUP_ADDED = 10;
    public static final int WAKE_REASON_DISPLAY_GROUP_TURNED_ON = 11;
    public static final int WAKE_REASON_GESTURE = 4;
    public static final int WAKE_REASON_HDMI = 8;
    public static final int WAKE_REASON_LID = 9;
    public static final int WAKE_REASON_PLUGGED_IN = 3;
    public static final int WAKE_REASON_POWER_BUTTON = 1;
    public static final int WAKE_REASON_UNKNOWN = 0;
    public static final int WAKE_REASON_WAKE_KEY = 6;
    public static final int WAKE_REASON_WAKE_MOTION = 7;
    final Context mContext;
    final Handler mHandler;
    private PowerWhitelistManager mPowerWhitelistManager;
    final IPowerManager mService;
    final IThermalService mThermalService;
    private PropertyInvalidatedCache<Void, Boolean> mPowerSaveModeCache = new PropertyInvalidatedCache<Void, Boolean>(1, CACHE_KEY_IS_POWER_SAVE_MODE_PROPERTY) { // from class: android.os.PowerManager.1
        /* JADX INFO: Access modifiers changed from: protected */
        public Boolean recompute(Void query) {
            try {
                return Boolean.valueOf(PowerManager.this.mService.isPowerSaveMode());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    };
    private PropertyInvalidatedCache<Void, Boolean> mInteractiveCache = new PropertyInvalidatedCache<Void, Boolean>(1, CACHE_KEY_IS_INTERACTIVE_PROPERTY) { // from class: android.os.PowerManager.2
        /* JADX INFO: Access modifiers changed from: protected */
        public Boolean recompute(Void query) {
            try {
                return Boolean.valueOf(PowerManager.this.mService.isInteractive());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    };
    private final ArrayMap<OnThermalStatusChangedListener, IThermalStatusListener> mListenerMap = new ArrayMap<>();
    private final AtomicLong mLastHeadroomUpdate = new AtomicLong(0);

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface AutoPowerSaveModeTriggers {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface BrightnessConstraint {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface GoToSleepReason {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface LocationPowerSaveMode {
    }

    /* loaded from: classes2.dex */
    public interface OnThermalStatusChangedListener {
        void onThermalStatusChanged(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ServiceType {
        public static final int ANIMATION = 3;
        public static final int AOD = 14;
        public static final int BATTERY_STATS = 9;
        public static final int DATA_SAVER = 10;
        public static final int FORCE_ALL_APPS_STANDBY = 11;
        public static final int FORCE_BACKGROUND_CHECK = 12;
        public static final int FULL_BACKUP = 4;
        public static final int KEYVALUE_BACKUP = 5;
        public static final int LOCATION = 1;
        public static final int NETWORK_FIREWALL = 6;
        public static final int NIGHT_MODE = 16;
        public static final int NULL = 0;
        public static final int OPTIONAL_SENSORS = 13;
        public static final int QUICK_DOZE = 15;
        public static final int SCREEN_BRIGHTNESS = 7;
        public static final int SOUND = 8;
        public static final int VIBRATION = 2;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ShutdownReason {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface SoundTriggerPowerSaveMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ThermalStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface WakeReason {
    }

    public static String sleepReasonToString(int sleepReason) {
        switch (sleepReason) {
            case 0:
                return "application";
            case 1:
                return "device_admin";
            case 2:
                return GpsNetInitiatedHandler.NI_INTENT_KEY_TIMEOUT;
            case 3:
                return "lid_switch";
            case 4:
                return "power_button";
            case 5:
                return AudioSystem.DEVICE_OUT_HDMI_NAME;
            case 6:
                return "sleep_button";
            case 7:
                return Context.ACCESSIBILITY_SERVICE;
            case 8:
                return "force_suspend";
            case 9:
                return "inattentive";
            case 10:
            default:
                return Integer.toString(sleepReason);
            case 11:
                return "display_group_removed";
            case 12:
                return "display_groups_turned_off";
            case 13:
                return "sleep_proximity";
            case 14:
                return "sleep_fingerprint";
            case 15:
                return "system_ui_cleanup";
        }
    }

    public static String wakeReasonToString(int wakeReason) {
        String reason = wakeReasonToStringExtend(wakeReason);
        if (reason != null) {
            return reason;
        }
        switch (wakeReason) {
            case 0:
                return "WAKE_REASON_UNKNOWN";
            case 1:
                return "WAKE_REASON_POWER_BUTTON";
            case 2:
                return "WAKE_REASON_APPLICATION";
            case 3:
                return "WAKE_REASON_PLUGGED_IN";
            case 4:
                return "WAKE_REASON_GESTURE";
            case 5:
                return "WAKE_REASON_CAMERA_LAUNCH";
            case 6:
                return "WAKE_REASON_WAKE_KEY";
            case 7:
                return "WAKE_REASON_WAKE_MOTION";
            case 8:
                return "WAKE_REASON_HDMI";
            case 9:
                return "WAKE_REASON_LID";
            case 10:
                return "WAKE_REASON_DISPLAY_GROUP_ADDED";
            case 11:
                return "WAKE_REASON_DISPLAY_GROUP_TURNED_ON";
            default:
                return Integer.toString(wakeReason);
        }
    }

    /* loaded from: classes2.dex */
    public static class WakeData {
        public long sleepDuration;
        public int wakeReason;
        public long wakeTime;

        public WakeData(long wakeTime, int wakeReason, long sleepDuration) {
            this.wakeTime = wakeTime;
            this.wakeReason = wakeReason;
            this.sleepDuration = sleepDuration;
        }
    }

    public static String locationPowerSaveModeToString(int mode) {
        switch (mode) {
            case 0:
                return "NO_CHANGE";
            case 1:
                return "GPS_DISABLED_WHEN_SCREEN_OFF";
            case 2:
                return "ALL_DISABLED_WHEN_SCREEN_OFF";
            case 3:
                return "FOREGROUND_ONLY";
            case 4:
                return "THROTTLE_REQUESTS_WHEN_SCREEN_OFF";
            default:
                return Integer.toString(mode);
        }
    }

    public PowerManager(Context context, IPowerManager service, IThermalService thermalService, Handler handler) {
        this.mContext = context;
        this.mService = service;
        this.mThermalService = thermalService;
        this.mHandler = handler;
    }

    private PowerWhitelistManager getPowerWhitelistManager() {
        if (this.mPowerWhitelistManager == null) {
            this.mPowerWhitelistManager = (PowerWhitelistManager) this.mContext.getSystemService(PowerWhitelistManager.class);
        }
        return this.mPowerWhitelistManager;
    }

    public int getMinimumScreenBrightnessSetting() {
        return this.mContext.getResources().getInteger(R.integer.config_screenBrightnessSettingMinimum);
    }

    public int getMaximumScreenBrightnessSetting() {
        return this.mContext.getResources().getInteger(R.integer.config_screenBrightnessSettingMaximum);
    }

    public int getMinBrightness() {
        try {
            return this.mService.getMinimumScreenBrightnessSetting();
        } catch (RemoteException e) {
            return -1;
        }
    }

    public int getMaxBrightness() {
        try {
            BRIGHTNESS_MULTIBITS_ON = this.mService.getMaximumScreenBrightnessSetting();
            return BRIGHTNESS_MULTIBITS_ON;
        } catch (RemoteException e) {
            return -1;
        }
    }

    public int getDefaultBrightness() {
        try {
            return this.mService.getDefaultScreenBrightnessSetting();
        } catch (RemoteException e) {
            return -1;
        }
    }

    public void setFlashing(int type, int color, int onMS, int offMS, int mode) {
        try {
            this.mService.setFlashing(type, color, onMS, offMS, mode);
        } catch (RemoteException e) {
        }
    }

    public int getDefaultScreenBrightnessSetting() {
        return this.mContext.getResources().getInteger(R.integer.config_screenBrightnessSettingDefault);
    }

    public int getMinimumScreenBrightnessForVrSetting() {
        return this.mContext.getResources().getInteger(R.integer.config_screenBrightnessForVrSettingMinimum);
    }

    public int getMaximumScreenBrightnessForVrSetting() {
        return this.mContext.getResources().getInteger(R.integer.config_screenBrightnessForVrSettingMaximum);
    }

    public int getDefaultScreenBrightnessForVrSetting() {
        return this.mContext.getResources().getInteger(R.integer.config_screenBrightnessForVrSettingDefault);
    }

    public float getBrightnessConstraint(int constraint) {
        try {
            return this.mService.getBrightnessConstraint(constraint);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public WakeLock newWakeLock(int levelAndFlags, String tag) {
        validateWakeLockParameters(levelAndFlags, tag);
        return new WakeLock(levelAndFlags, tag, this.mContext.getOpPackageName(), -1);
    }

    public WakeLock newWakeLock(int levelAndFlags, String tag, int displayId) {
        validateWakeLockParameters(levelAndFlags, tag);
        return new WakeLock(levelAndFlags, tag, this.mContext.getOpPackageName(), displayId);
    }

    public static void validateWakeLockParameters(int levelAndFlags, String tag) {
        switch (65535 & levelAndFlags) {
            case 1:
            case 6:
            case 10:
            case 26:
            case 32:
            case 64:
            case 128:
                if (tag == null) {
                    throw new IllegalArgumentException("The tag must not be null.");
                }
                return;
            default:
                throw new IllegalArgumentException("Must specify a valid wake lock level.");
        }
    }

    @Deprecated
    public void userActivity(long when, boolean noChangeLights) {
        userActivity(when, 0, noChangeLights ? 1 : 0);
    }

    @SystemApi
    public void userActivity(long when, int event, int flags) {
        try {
            this.mService.userActivity(this.mContext.getDisplayId(), when, event, flags);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void goToSleep(long time) {
        goToSleep(time, 0, 0);
    }

    public void goToSleep(long time, int reason, int flags) {
        printStackTraceInfo("goToSleepBy");
        try {
            this.mService.goToSleep(time, reason, flags);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public void wakeUp(long time) {
        printStackTraceInfo("wakeUpBy");
        wakeUp(time, 0, "wakeUp");
    }

    @Deprecated
    public void wakeUp(long time, String details) {
        wakeUp(time, 0, details);
    }

    public void wakeUp(long time, int reason, String details) {
        try {
            this.mService.wakeUp(time, reason, details, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void nap(long time) {
        printStackTraceInfo("napBy");
        try {
            this.mService.nap(time);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void dream(long time) {
        Sandman.startDreamByUserRequest(this.mContext);
    }

    public void boostScreenBrightness(long time) {
        try {
            this.mService.boostScreenBrightness(time);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isWakeLockLevelSupported(int level) {
        try {
            return this.mService.isWakeLockLevelSupported(level);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public boolean isScreenOn() {
        return isInteractive();
    }

    public boolean isInteractive() {
        return this.mInteractiveCache.query(null).booleanValue();
    }

    public static boolean isRebootingUserspaceSupportedImpl() {
        return InitProperties.is_userspace_reboot_supported().orElse(false).booleanValue();
    }

    public boolean isRebootingUserspaceSupported() {
        return isRebootingUserspaceSupportedImpl();
    }

    public void reboot(String reason) {
        if (!REBOOT_USERSPACE.equals(reason) || isRebootingUserspaceSupported()) {
            printStackTraceInfo("rebootBy");
            try {
                this.mService.reboot(false, reason, true);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            throw new UnsupportedOperationException("Attempted userspace reboot on a device that doesn't support it");
        }
    }

    public void rebootSafeMode() {
        printStackTraceInfo("rebootSafeMode");
        try {
            this.mService.rebootSafeMode(false, true);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isPowerSaveMode() {
        return this.mPowerSaveModeCache.query(null).booleanValue();
    }

    @SystemApi
    public boolean setPowerSaveModeEnabled(boolean mode) {
        printStackTraceInfo("setPowerSaveModeBy");
        try {
            return this.mService.setPowerSaveModeEnabled(mode);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public BatterySaverPolicyConfig getFullPowerSavePolicy() {
        try {
            return this.mService.getFullPowerSavePolicy();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean setFullPowerSavePolicy(BatterySaverPolicyConfig config) {
        try {
            return this.mService.setFullPowerSavePolicy(config);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean setDynamicPowerSaveHint(boolean powerSaveHint, int disableThreshold) {
        try {
            return this.mService.setDynamicPowerSaveHint(powerSaveHint, disableThreshold);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean setAdaptivePowerSavePolicy(BatterySaverPolicyConfig config) {
        try {
            return this.mService.setAdaptivePowerSavePolicy(config);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean setAdaptivePowerSaveEnabled(boolean enabled) {
        try {
            return this.mService.setAdaptivePowerSaveEnabled(enabled);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public int getPowerSaveModeTrigger() {
        try {
            return this.mService.getPowerSaveModeTrigger();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setBatteryDischargePrediction(Duration timeRemaining, boolean isPersonalized) {
        if (timeRemaining != null) {
            try {
                this.mService.setBatteryDischargePrediction(new ParcelDuration(timeRemaining), isPersonalized);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            throw new IllegalArgumentException("time remaining must not be null");
        }
    }

    public Duration getBatteryDischargePrediction() {
        try {
            ParcelDuration parcelDuration = this.mService.getBatteryDischargePrediction();
            if (parcelDuration == null) {
                return null;
            }
            return parcelDuration.getDuration();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isBatteryDischargePredictionPersonalized() {
        try {
            return this.mService.isBatteryDischargePredictionPersonalized();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public PowerSaveState getPowerSaveState(int serviceType) {
        try {
            return this.mService.getPowerSaveState(serviceType);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getLocationPowerSaveMode() {
        PowerSaveState powerSaveState = getPowerSaveState(1);
        if (!powerSaveState.batterySaverEnabled) {
            return 0;
        }
        return powerSaveState.locationMode;
    }

    public int getSoundTriggerPowerSaveMode() {
        PowerSaveState powerSaveState = getPowerSaveState(8);
        if (!powerSaveState.batterySaverEnabled) {
            return 0;
        }
        return powerSaveState.soundTriggerMode;
    }

    public boolean isDeviceIdleMode() {
        try {
            return this.mService.isDeviceIdleMode();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isLightDeviceIdleMode() {
        try {
            return this.mService.isLightDeviceIdleMode();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isIgnoringBatteryOptimizations(String packageName) {
        return getPowerWhitelistManager().isWhitelisted(packageName, true);
    }

    public void shutdown(boolean confirm, String reason, boolean wait) {
        printStackTraceInfo("shutdownBy");
        try {
            this.mService.shutdown(confirm, reason, wait);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isSustainedPerformanceModeSupported() {
        return this.mContext.getResources().getBoolean(R.bool.config_sustainedPerformanceModeSupported);
    }

    public int getCurrentThermalStatus() {
        try {
            return this.mThermalService.getCurrentThermalStatus();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addThermalStatusListener(OnThermalStatusChangedListener listener) {
        Preconditions.checkNotNull(listener, "listener cannot be null");
        addThermalStatusListener(this.mContext.getMainExecutor(), listener);
    }

    public void addThermalStatusListener(Executor executor, OnThermalStatusChangedListener listener) {
        Preconditions.checkNotNull(listener, "listener cannot be null");
        Preconditions.checkNotNull(executor, "executor cannot be null");
        Preconditions.checkArgument(!this.mListenerMap.containsKey(listener), "Listener already registered: %s", listener);
        IThermalStatusListener internalListener = new AnonymousClass3(executor, listener);
        try {
            if (this.mThermalService.registerThermalStatusListener(internalListener)) {
                this.mListenerMap.put(listener, internalListener);
                return;
            }
            throw new RuntimeException("Listener failed to set");
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.os.PowerManager$3  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass3 extends IThermalStatusListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ OnThermalStatusChangedListener val$listener;

        AnonymousClass3(Executor executor, OnThermalStatusChangedListener onThermalStatusChangedListener) {
            this.val$executor = executor;
            this.val$listener = onThermalStatusChangedListener;
        }

        @Override // android.os.IThermalStatusListener
        public void onStatusChange(final int status) {
            long token = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final OnThermalStatusChangedListener onThermalStatusChangedListener = this.val$listener;
                executor.execute(new Runnable() { // from class: android.os.PowerManager$3$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        PowerManager.OnThermalStatusChangedListener.this.onThermalStatusChanged(status);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(token);
            }
        }
    }

    public void removeThermalStatusListener(OnThermalStatusChangedListener listener) {
        Preconditions.checkNotNull(listener, "listener cannot be null");
        IThermalStatusListener internalListener = this.mListenerMap.get(listener);
        Preconditions.checkArgument(internalListener != null, "Listener was not added");
        try {
            if (this.mThermalService.unregisterThermalStatusListener(internalListener)) {
                this.mListenerMap.remove(listener);
                return;
            }
            throw new RuntimeException("Listener failed to remove");
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public float getThermalHeadroom(int forecastSeconds) {
        long now = SystemClock.elapsedRealtime();
        long timeSinceLastUpdate = now - this.mLastHeadroomUpdate.get();
        if (timeSinceLastUpdate < 500) {
            return Float.NaN;
        }
        try {
            float forecast = this.mThermalService.getThermalHeadroom(forecastSeconds);
            this.mLastHeadroomUpdate.set(SystemClock.elapsedRealtime());
            return forecast;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setDozeAfterScreenOff(boolean dozeAfterScreenOf) {
        try {
            this.mService.setDozeAfterScreenOff(dozeAfterScreenOf);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isAmbientDisplayAvailable() {
        try {
            return this.mService.isAmbientDisplayAvailable();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void suppressAmbientDisplay(String token, boolean suppress) {
        try {
            this.mService.suppressAmbientDisplay(token, suppress);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isAmbientDisplaySuppressedForToken(String token) {
        try {
            return this.mService.isAmbientDisplaySuppressedForToken(token);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isAmbientDisplaySuppressed() {
        try {
            return this.mService.isAmbientDisplaySuppressed();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isAmbientDisplaySuppressedForTokenByApp(String token, int appUid) {
        try {
            return this.mService.isAmbientDisplaySuppressedForTokenByApp(token, appUid);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getLastShutdownReason() {
        try {
            return this.mService.getLastShutdownReason();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getLastSleepReason() {
        try {
            return this.mService.getLastSleepReason();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean forceSuspend() {
        try {
            return this.mService.forceSuspend();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* loaded from: classes2.dex */
    public final class WakeLock {
        private final int mDisplayId;
        private int mExternalCount;
        private int mFlags;
        private boolean mHeld;
        private String mHistoryTag;
        private int mInternalCount;
        private final String mPackageName;
        private String mTag;
        private final String mTraceName;
        private WorkSource mWorkSource;
        private boolean mRefCounted = true;
        private final Runnable mReleaser = new Runnable() { // from class: android.os.PowerManager$WakeLock$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PowerManager.WakeLock.this.lambda$new$0$PowerManager$WakeLock();
            }
        };
        private final IBinder mToken = new Binder();

        public /* synthetic */ void lambda$new$0$PowerManager$WakeLock() {
            release(65536);
        }

        WakeLock(int flags, String tag, String packageName, int displayId) {
            this.mFlags = flags;
            this.mTag = tag;
            this.mPackageName = packageName;
            this.mTraceName = "WakeLock (" + this.mTag + ")";
            this.mDisplayId = displayId;
        }

        protected void finalize() throws Throwable {
            synchronized (this.mToken) {
                if (this.mHeld) {
                    Log.wtf(PowerManager.TAG, "WakeLock finalized while still held: " + this.mTag);
                    Trace.asyncTraceEnd(131072L, this.mTraceName, 0);
                    try {
                        PowerManager.this.mService.releaseWakeLock(this.mToken, 0);
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
            }
        }

        public void setReferenceCounted(boolean value) {
            synchronized (this.mToken) {
                this.mRefCounted = value;
            }
        }

        public void acquire() {
            synchronized (this.mToken) {
                acquireLocked();
            }
        }

        public void acquire(long timeout) {
            synchronized (this.mToken) {
                acquireLocked();
                PowerManager.this.mHandler.postDelayed(this.mReleaser, timeout);
            }
        }

        private void acquireLocked() {
            int i = this.mInternalCount + 1;
            this.mInternalCount = i;
            this.mExternalCount++;
            if (!this.mRefCounted || i == 1) {
                PowerManager.this.mHandler.removeCallbacks(this.mReleaser);
                Trace.asyncTraceBegin(131072L, this.mTraceName, 0);
                try {
                    PowerManager.this.mService.acquireWakeLock(this.mToken, this.mFlags, this.mTag, this.mPackageName, this.mWorkSource, this.mHistoryTag, this.mDisplayId);
                    this.mHeld = true;
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        }

        public void release() {
            release(0);
        }

        public void release(int flags) {
            synchronized (this.mToken) {
                int i = this.mInternalCount;
                if (i > 0) {
                    this.mInternalCount = i - 1;
                }
                if ((65536 & flags) == 0) {
                    this.mExternalCount--;
                }
                if (!this.mRefCounted || this.mInternalCount == 0) {
                    PowerManager.this.mHandler.removeCallbacks(this.mReleaser);
                    if (this.mHeld) {
                        Trace.asyncTraceEnd(131072L, this.mTraceName, 0);
                        try {
                            PowerManager.this.mService.releaseWakeLock(this.mToken, flags);
                            this.mHeld = false;
                        } catch (RemoteException e) {
                            throw e.rethrowFromSystemServer();
                        }
                    }
                }
                if (this.mRefCounted && this.mExternalCount < 0) {
                    throw new RuntimeException("WakeLock under-locked " + this.mTag);
                }
            }
        }

        public boolean isHeld() {
            boolean z;
            synchronized (this.mToken) {
                z = this.mHeld;
            }
            return z;
        }

        public void setWorkSource(WorkSource ws) {
            synchronized (this.mToken) {
                if (ws != null) {
                    try {
                        if (ws.isEmpty()) {
                            ws = null;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                boolean changed = true;
                if (ws == null) {
                    if (this.mWorkSource == null) {
                        changed = false;
                    }
                    this.mWorkSource = null;
                } else {
                    WorkSource workSource = this.mWorkSource;
                    if (workSource == null) {
                        changed = true;
                        this.mWorkSource = new WorkSource(ws);
                    } else {
                        changed = true ^ workSource.equals(ws);
                        if (changed) {
                            this.mWorkSource.set(ws);
                        }
                    }
                }
                if (changed && this.mHeld) {
                    try {
                        PowerManager.this.mService.updateWakeLockWorkSource(this.mToken, this.mWorkSource, this.mHistoryTag);
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
            }
        }

        public void setTag(String tag) {
            this.mTag = tag;
        }

        public String getTag() {
            return this.mTag;
        }

        public void setHistoryTag(String tag) {
            this.mHistoryTag = tag;
        }

        public void setUnimportantForLogging(boolean state) {
            if (state) {
                this.mFlags |= 1073741824;
            } else {
                this.mFlags &= -1073741825;
            }
        }

        public String toString() {
            String str;
            synchronized (this.mToken) {
                str = "WakeLock{" + Integer.toHexString(System.identityHashCode(this)) + " held=" + this.mHeld + ", refCount=" + this.mInternalCount + "}";
            }
            return str;
        }

        public void dumpDebug(ProtoOutputStream proto, long fieldId) {
            synchronized (this.mToken) {
                long token = proto.start(fieldId);
                proto.write(1138166333441L, this.mTag);
                proto.write(1138166333442L, this.mPackageName);
                proto.write(1133871366147L, this.mHeld);
                proto.write(1120986464260L, this.mInternalCount);
                WorkSource workSource = this.mWorkSource;
                if (workSource != null) {
                    workSource.dumpDebug(proto, 1146756268037L);
                }
                proto.end(token);
            }
        }

        public Runnable wrap(final Runnable r) {
            acquire();
            return new Runnable() { // from class: android.os.PowerManager$WakeLock$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    PowerManager.WakeLock.this.lambda$wrap$1$PowerManager$WakeLock(r);
                }
            };
        }

        public /* synthetic */ void lambda$wrap$1$PowerManager$WakeLock(Runnable r) {
            try {
                r.run();
            } finally {
                release();
            }
        }
    }

    public static void invalidatePowerSaveModeCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_IS_POWER_SAVE_MODE_PROPERTY);
    }

    public static void invalidateIsInteractiveCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_IS_INTERACTIVE_PROPERTY);
    }
}
