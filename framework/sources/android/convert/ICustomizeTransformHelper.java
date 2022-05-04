package android.convert;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import java.util.HashMap;
/* loaded from: classes.dex */
public interface ICustomizeTransformHelper extends IOplusCommonFeature {
    public static final String ACTION_POWER_BUTTON_ENDS_ALARMCLOCK = "oplus.intent.action.POWER_BUTTON_ENDS_ALARMCLOCK";
    public static final String ALARMCLOCK_PKG = "com.oplus.alarmclock";
    public static final String CAMERA_PKG = "com.oplus.camera";
    public static final String CHILDREN_SPACE_PKG = "com.oplus.childrenspace";
    public static final String CUSTOMIZE_FLIPED_FONT_FILE_NAME = "customize_fliped_font_file_name";
    public static final String CUSTOMIZE_LAUNCHER_PKG_NAME = "customize_launcher_pkg_name";
    public static final String CUSTOMIZE_THEME_ICON_PKG_NAME = "customize_theme_icon_pkg_name";
    public static final String CUSTOMIZE_VERSION_ROOT_PATH = "customize_version_root_path";
    public static final String LEGACY_PREFIX = "legacy_prefix";
    public static final String NAME = "ICustomizeTransformHelper";
    public static final String OEM_DDS_SWITCH_NO_TRIGGER_PKGS = "oem_dds_switch_no_trigger_pkgs";
    public static final String OEM_PACKAGE_BLACKLISTAPP = "oem_package_blacklistapp";
    public static final String OEM_PACKAGE_CTAUTOREGIST = "oem_package_ctautoregist";
    public static final String OEM_PACKAGE_FINDMYPHONE = "oem_findmyphone";
    public static final String OEM_PACKAGE_HEALTHCHECK = "oem_package_healthcheck";
    public static final String OEM_PACKAGE_MO_SMS_NOT_SHOW_IN_UI = "config_oem_package_mo_sms_not_show_in_ui";
    public static final String OEM_PACKAGE_REGSERVICE = "oem_package_regservice";
    public static final String OLD_ACTION_KEY_LOCK = "com.oplus.intent.action.KEY_LOCK_MODE";
    public static final String OPLUS_BACKUPRESTORE = "oem_package_backuprestore";
    public static final String OPLUS_BROWSER = "oem_package_browser";
    public static final String OPLUS_INCALL_ACTION = "oplus.intent.action.incallui.HIDE_INCOMING_FULL_UI";
    public static final String OPLUS_INCALL_SCREEN = "com.android.incallui/com.android.incallui.OplusInCallActivity";
    public static final String OPLUS_OSHARE = "oem_package_oshare";
    public static final String OPLUS_PACKAGE_FLOATASSISTANT = "oplus_package_floatassistant";
    public static final String OPLUS_TRANSMISSION_PKG = "config_oplus_transmission_pkg";
    public static final String OPLUS_WIFISECUREDETECT = "oem_package_wifisecuredetect";
    public static final String OPLUS_WINDOW_UTILS_DIRECT_APPS = "oplus_window_utils_direct_apps";
    public static final String QUICK_LAUNCH_CAMERA_KEY = "com.oplus.camera quick launch";
    public static final String SHOW_DEVICE_LOCKED = "oplus_show_device_locked";
    public static final String SILENCE_ACTION_FOR_OPLUS_SPEECH = "SILENCE_ACTION_FOR_OPLUS_SPEECH";
    public static final String SOS_ACTIVITY1 = "com.oplus.sos.ui.EmergencyCallDetailActivity";
    public static final String SOS_ACTIVITY2 = "com.oplus.sos.ui.EmergencyAutoCallActivity";
    public static final String SOS_ACTIVITY3 = "com.oplus.sos.ui.MessageSendConfirmActivity";
    public static final String STR_POWER_BUTTON_ENDS_ALARMCLOCK = "oplus_power_button_ends_alarm_clock";
    public static final String TITLE_EDGE_FLOATBAR = "OplusOSEdgeFloatBar";
    public static final String TITLE_EDGE_PANEL = "OplusOSEdgePanel";
    public static final String WAKEUP_NUM = "oplus.dt.wakeupnum";
    public static final ICustomizeTransformHelper DEFAULT = new ICustomizeTransformHelper() { // from class: android.convert.ICustomizeTransformHelper.1
    };
    public static final HashMap<String, Object> mTransfromCache = new HashMap<>();

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.ICustomizeTransformHelper;
    }

    @Override // android.common.IOplusCommonFeature
    default IOplusCommonFeature getDefault() {
        return DEFAULT;
    }

    default Object getTransfromCacheFeature(String transformKey) {
        return null;
    }
}
