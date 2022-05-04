package android.os;

import android.os.Build;
import android.os.IOplusService;
import android.provider.SettingsStringUtil;
import android.text.TextUtils;
import android.util.Log;
import com.oplus.os.OplusBuild;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes2.dex */
public final class OplusManager {
    public static final int ANDROID_MSG_INPUTMETHOD_FAILD = 1004;
    public static final int ANDROID_MSG_INSTALL_FAILD = 1003;
    public static final int ANDROID_MSG_LAUNCHACTIVITY = 1002;
    public static final int ANDROID_MSG_SKIPFRAMES = 1001;
    public static final String ANDROID_PANIC_TAG = "SYSTEM_SERVER";
    public static final String ANDROID_PANIC_TAG_BEGIN = "<android-panic-begin>\n";
    public static final String ANDROID_PANIC_TAG_END = "<android-panic-end>\n";
    public static final String ANDROID_TAG = "ANDROID";
    public static final String CAMERA_TAG = "CAMERA";
    public static final String CONNECT_TAG = "CONNECTIVITY";
    private static final int DATA_SIZE = 16;
    private static final boolean DEBUG = true;
    public static final String DO_GR_CHECK_INTERNET = "DO_GR_CHECK_INTERNET";
    public static final String DO_GR_INSTALL_TALKBACK = "DO_GR_INSTALL_TALKBACK";
    public static final String DO_GR_TALKBACK_SUCC = "DO_GR_TALKBACK_SUCC";
    public static final String ENGINEERINGMODE_TEST_BEGIN = "<engineeringmode-test-begin>\n";
    public static final String ENGINEERINGMODE_TEST_END = "<engineeringmode-test-end>\n";
    public static final String ENGINEERINGMODE_TEST_TAG = "ENGINEERINGMODE_TEST";
    public static final String GMAP_PNAME = "com.google.android.apps.maps";
    private static final int INIT_TRY_TIMES = 3;
    public static final String ISSUE_ANDROID_ADSP_CRASH = "adsp_crash";
    public static final String ISSUE_ANDROID_AVERAGE_CURRENT_EVENT = "average_current_event";
    public static final String ISSUE_ANDROID_CHARGER_PLUGIN_625 = "charger_plugin";
    public static final String ISSUE_ANDROID_CHARGER_PLUGOUT_626 = "charger_plugout";
    public static final String ISSUE_ANDROID_CRASH = "crash";
    public static final String ISSUE_ANDROID_FP_DIE = "fp_die";
    public static final String ISSUE_ANDROID_FP_HW_ERROR = "fp_hw_error";
    public static final String ISSUE_ANDROID_FP_RESET_BYHM = "fp_reset_byhm";
    public static final String ISSUE_ANDROID_INPUTMETHOD_FAIL = "inputmethod_fail";
    public static final String ISSUE_ANDROID_INSTALL_FAIL = "install_fail";
    public static final String ISSUE_ANDROID_LAUNCH_ACTIVITY = "launch_activity";
    public static final String ISSUE_ANDROID_MODEM_CRASH = "modem_crash";
    public static final String ISSUE_ANDROID_OTA_UPGRADE = "ota_upgrade";
    public static final String ISSUE_ANDROID_PM_50 = "scan_event";
    public static final String ISSUE_ANDROID_PM_51 = "wifi_discounnect_event";
    public static final String ISSUE_ANDROID_PM_52 = "key_exchange_event";
    public static final String ISSUE_ANDROID_PM_53 = "dhcp_relet_event";
    public static final String ISSUE_ANDROID_PM_54 = "data_call_count";
    public static final String ISSUE_ANDROID_PM_55 = "no_service_time";
    public static final String ISSUE_ANDROID_PM_56 = "reselect_per_min";
    public static final String ISSUE_ANDROID_PM_57 = "sms_send_count";
    public static final String ISSUE_ANDROID_PM_58 = "background_music";
    public static final String ISSUE_ANDROID_PM_59 = "background_download";
    public static final String ISSUE_ANDROID_PM_60 = "wifi_wakeup";
    public static final String ISSUE_ANDROID_PM_61 = "modem_wakeup";
    public static final String ISSUE_ANDROID_PM_62 = "alarm_wakeup";
    public static final String ISSUE_ANDROID_PM_63 = "base_subsystem";
    public static final String ISSUE_ANDROID_PM_64 = "power_other";
    public static final String ISSUE_ANDROID_REBOOT_FROM_BLOCKED = "reboot_from_blocked";
    public static final String ISSUE_ANDROID_SKIP_FRAMES = "skip_frames";
    public static final String ISSUE_ANDROID_SYSTEM_REBOOT_FROM_BLOCKED = "system_server_reboot_from_blocked";
    public static final String ISSUE_ANDROID_VENUS_CRASH = "venus_crash";
    public static final String ISSUE_ANDROID_WCN_CRASH = "wcn_crash";
    public static final String ISSUE_KERNEL_HANG = "HANG";
    public static final String ISSUE_KERNEL_HARDWARE_REBOOT = "Hardware Reboot";
    public static final String ISSUE_KERNEL_HWT = "HWT";
    public static final String ISSUE_KERNEL_PANIC = "panic";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_AS_FAILED = "as_failed";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_AUTHENTICATION_REJECT = "authentication_reject";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_CARD_DROP_RX_BREAK = "card_drop_rx_break";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_CARD_DROP_TIME_OUT = "card_drop_time_out";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_DATA_NOT_ALLOWED = "data_no_allowed";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_DATA_NO_AVAILABLE_APN = "data_no_acailable_apn";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_DATA_SET_UP_DATA_ERROR = "data_set_up_data_error";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_GSM_T3126_EXPIRED = "gsm_t3126_expired";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_LTE_AS_FAILED = "lte_as_failed";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_LTE_REG_REJECT = "ltc_reg_reject";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_LTE_REG_WITHOUT_LTE = "lte_reg_without_lte";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_MCFG_ICCID_FAILED = "mcfg_iccid_failed";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_MO_DROP = "mo_drop";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_MT_CSFB = "mt_csfb";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_MT_PCH = "mt_pch";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_MT_RACH = "mt_rach";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_MT_REJECT = "mt_reject";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_MT_RLF = "mt_rlf";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_MT_RRC = "mt_rrc";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_REG_REJECT = "reg_rejetc";
    public static final String ISSUE_SYS_OEM_NW_DIAG_CAUSE_RF_MIPI_HW_FAILED = "rf_mipi_hw_failed";
    public static final String ISSUE_WIFI_CONNECTING_FAILURE = "wifi_connecting_failure";
    public static final String ISSUE_WIFI_LOAD_DRIVER_FAILURE = "wifi_load_driver_failure";
    public static final String ISSUE_WIFI_TURN_ON_OFF_FAILURE = "wifi_turn_on_off_failure";
    public static final String KERNEL_PANIC_TAG = "SYSTEM_LAST_KMSG";
    public static final String KERNEL_PANIC_TAG_BEGIN = "<kernel-panic-begin>\n";
    public static final String KERNEL_PANIC_TAG_END = "<kernel-panic-end>\n";
    public static final String KERNEL_TAG = "KERNEL";
    private static final Locale LOG_STRING_LOCALE = Locale.US;
    public static final String MULTIMEDIA_TAG = "MULTIMEDIA";
    public static final String NETWORK_TAG = "NETWORK";
    public static final String SERVICE_NAME = "additionald";
    public static final String SHUTDOWN_TAG = "SYSTEM_SHUTDOWN";
    public static final String SHUTDOWN_TAG_BEGIN = "<shutdown-begin>\n";
    public static final String SHUTDOWN_TAG_END = "<shutdown-end>\n";
    public static final String SPMI_BEGIN = "<spmi-begin>\n";
    public static final String SPMI_END = "<spmi-end>\n";
    public static final String SPMI_TAG = "SPMI";
    public static final String TAG = "OplusManager";
    public static final int TYEP_Android_VER = 2;
    public static final int TYEP_BUILD_VER = 3;
    public static final int TYEP_DEVICE = 4;
    public static final int TYEP_PHONE_IMEI = 1;
    public static final int TYPE_ANDROID_ADSP_CRASH = 44;
    public static final int TYPE_ANDROID_AVERAGE_CURRENT_EVENT = 37;
    public static final int TYPE_ANDROID_BACK_KEY = 33;
    public static final int TYPE_ANDROID_CAMERA = 28;
    public static final int TYPE_ANDROID_CHARGER_PLUGIN_625 = 625;
    public static final int TYPE_ANDROID_CHARGER_PLUGOUT_626 = 626;
    public static final int TYPE_ANDROID_CRASH = 22;
    public static final int TYPE_ANDROID_FP_DIE = 47;
    public static final int TYPE_ANDROID_FP_HW_ERROR = 49;
    public static final int TYPE_ANDROID_FP_RESET_BYHM = 48;
    public static final int TYPE_ANDROID_HOME_KEY = 31;
    public static final int TYPE_ANDROID_INPUTMETHOD_FAIL = 43;
    public static final int TYPE_ANDROID_INSTALL_FAILD = 40;
    public static final int TYPE_ANDROID_LAUNCH_ACTIVITY = 39;
    public static final int TYPE_ANDROID_MENU_KEY = 32;
    public static final int TYPE_ANDROID_OTA_FAILD = 41;
    public static final int TYPE_ANDROID_OTA_UPGRADE = 29;
    public static final int TYPE_ANDROID_PM_EVENT_50 = 50;
    public static final int TYPE_ANDROID_PM_EVENT_51 = 51;
    public static final int TYPE_ANDROID_PM_EVENT_52 = 52;
    public static final int TYPE_ANDROID_PM_EVENT_53 = 53;
    public static final int TYPE_ANDROID_PM_EVENT_54 = 54;
    public static final int TYPE_ANDROID_PM_EVENT_55 = 55;
    public static final int TYPE_ANDROID_PM_EVENT_56 = 56;
    public static final int TYPE_ANDROID_PM_EVENT_57 = 57;
    public static final int TYPE_ANDROID_PM_EVENT_58 = 58;
    public static final int TYPE_ANDROID_PM_EVENT_59 = 59;
    public static final int TYPE_ANDROID_PM_EVENT_60 = 60;
    public static final int TYPE_ANDROID_PM_EVENT_61 = 61;
    public static final int TYPE_ANDROID_PM_EVENT_62 = 62;
    public static final int TYPE_ANDROID_PM_EVENT_63 = 63;
    public static final int TYPE_ANDROID_PM_EVENT_64 = 64;
    public static final int TYPE_ANDROID_POWER_KEY = 36;
    public static final int TYPE_ANDROID_REBOOT_HANG = 122;
    public static final int TYPE_ANDROID_REBOOT_HARDWARE_REBOOT = 121;
    public static final int TYPE_ANDROID_REBOOT_HWT = 120;
    public static final int TYPE_ANDROID_SKIPFRAMES = 38;
    public static final int TYPE_ANDROID_SPMI = 24;
    public static final int TYPE_ANDROID_SYSTEM_REBOOT_FROM_BLOCKED = 26;
    public static final int TYPE_ANDROID_UNKNOWN_REBOOT = 42;
    public static final int TYPE_ANDROID_USB = 30;
    public static final int TYPE_ANDROID_VENUS_CRASH = 45;
    public static final int TYPE_ANDROID_VOLDOWN_KEY = 35;
    public static final int TYPE_ANDROID_VOLUP_KEY = 34;
    public static final int TYPE_ANDROID_WCN_CRASH = 46;
    public static final int TYPE_BATTERY_CHARGE_HISTORY = 8;
    public static final int TYPE_CRITICAL_DATA_SIZE = 512;
    private static final int TYPE_DATA_DEFAULT_SIZE = 256;
    private static final int TYPE_DATA_SIZE_OFFSET = 10;
    public static final int TYPE_HW_SHUTDOWN = 5;
    private static final int TYPE_INDEX = 19;
    public static final int TYPE_LOGSIZE = 1022;
    public static final int TYPE_LOGVER = 0;
    public static final int TYPE_MODERN = 23;
    private static final int TYPE_OFFSET = 1024;
    public static final int TYPE_OTA_FLAG = 6;
    public static final int TYPE_PANIC = 600;
    public static final int TYPE_REBOOT = 21;
    public static final int TYPE_REBOOT_FROM_BLOCKED = 27;
    public static final int TYPE_RESMON = 25;
    public static final int TYPE_ROOT_FLAG = 7;
    public static final int TYPE_SHUTDOWN = 20;
    public static final int TYPE_SYMBOL_VERSION_DISAGREE = 803;
    public static final int TYPE_WDI_EXCEPTION = 804;
    public static final int TYPE_WIFI_CONNECT_FAILED = 800;
    private static IOplusService sService;

    /* loaded from: classes2.dex */
    public interface IStampCallBack {
        void onComplete(Object obj);
    }

    /* loaded from: classes2.dex */
    public static class QualityEventId {
        public static String EV_STABILITY_REBOOT = "100100001";
        public static String EV_STABILITY_KERNEL_PANIC = "100100002";
        public static String EV_STABILITY_SYSTEM_CRASH = "100100003";
        public static String EV_STABILITY_DEATH_HEALER = "100100004";
        public static String EV_STABILITY_IOHUNG = "100100005";
        public static String EV_STABILITY_OCP = "100100006";
        public static String EV_STABILITY_PMIC_WT = "100100007";
        public static String EV_STABILITY_MTK_HWT = "100100008";
        public static String EV_STABILITY_MTK_HARDWARE_REBOOT = "100100009";
        public static String EV_STABILITY_MTK_HANG = "100100010";
        public static String EV_STABILITY_REBOOT_FROM_BLOCKED = "100100011";
        public static String EV_STABILITY_ANDROID_REBOOT_FROM_BLOCKED = "100100012";
        public static String EV_STABILITY_UNKNOWN_REBOOT = "100100013";
        public static String EV_STABILITY_HANG_BOOT_LOGO = "100200001";
        public static String EV_STABILITY_HANG_BOOTANIM = "100200002";
        public static String EV_STABILITY_SHUTDOWN_ERROR = "100200003";
        public static String EV_STABILITY_HECATE_BLANK_SCREEN = "100300001";
        public static String EV_STABILITY_HECATE_SF_HANGO = "100300002";
        public static String EV_STABILITY_HECATE_HWC_HANG = "100300003";
    }

    /* loaded from: classes2.dex */
    public static class StampEvent {
        public String dayno;
        public String eventId;
        public String hour;
        public Map<String, String> logMap;
        public String otaVersion;
        public String timestamp;
    }

    /* loaded from: classes2.dex */
    public static class StampId {
        public static String AD_DEVICE = "000000";
        public static String AD_KE = "010101";
        public static String AD_JE = "010102";
        public static String AD_OCP = "010103";
        public static String AD_FILECHECK = "010104";
        public static String AD_EMMCCHECK = "010105";
        public static String AD_PMICREASON = "010107";
        public static String AD_BATTERYOFF = "010201";
    }

    private static native int nativeCleanItem(int i);

    private static native String nativeReadCriticalData(int i, int i2);

    private static native String nativeReadRawPartition(int i, int i2);

    private static native int nativeSyncCahceToEmmc();

    private static native String nativeTestFunc(int i, int i2);

    private static native int nativeUpdateConfig();

    private static native int nativeWriteCriticalData(int i, String str);

    private static native int nativeWriteRawPartition(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2);

    public static final boolean init() {
        if (sService != null) {
            return true;
        }
        int times = 3;
        do {
            Log.w(TAG, "Try to get additionald service instance! times = " + times);
            IOplusService asInterface = IOplusService.Stub.asInterface(ServiceManager.getService(SERVICE_NAME));
            sService = asInterface;
            if (asInterface != null) {
                return true;
            }
            times--;
        } while (times > 0);
        return false;
    }

    public static String readRawPartition(int offset, int size) {
        try {
            String res = nativeReadRawPartition(offset, size);
            return res;
        } catch (Exception e) {
            Log.e(TAG, "read Raw Partition exception!");
            e.printStackTrace();
            return null;
        }
    }

    public static int writeRawPartition(int type, String tagbegin, String logType, String time, String buildTime, String romVersion, String logstring, String tagend, int isAddToDropbox) {
        try {
            int res = nativeWriteRawPartition(type, tagbegin, logType, time, buildTime, romVersion, logstring, tagend, isAddToDropbox);
            return res;
        } catch (Exception e) {
            Log.e(TAG, "write Raw Partition exception!");
            e.printStackTrace();
            return -1;
        }
    }

    public static int readCriticalData(int type) {
        String dataString;
        String dataString2 = readCriticalData(type, 16);
        if (dataString2 == null || (dataString = dataString2.trim()) == null || dataString.length() == 0) {
            return 0;
        }
        try {
            int res = 0 + Integer.parseInt(dataString);
            return res;
        } catch (Exception e) {
            Log.e(TAG, "read critical data failed!! e = " + e.toString());
            e.printStackTrace();
            return 0;
        }
    }

    public static String readCriticalData(int id, int size) {
        try {
            String res = nativeReadCriticalData(id, size);
            return res;
        } catch (Exception e) {
            Log.e(TAG, "read Critical Data exception!\n");
            e.printStackTrace();
            return null;
        }
    }

    public static int writeCriticalData(int id, String content) {
        if (content != null) {
            try {
                if (content.length() > 502) {
                    content = content.substring(0, 502);
                }
            } catch (Exception e) {
                Log.e(TAG, "write Critical Data exception!\n");
                e.printStackTrace();
                return -1;
            }
        }
        int res = nativeWriteCriticalData(id, content);
        return res;
    }

    public static String getTime() {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        Date d1 = new Date(time);
        String strTime = format.format(d1);
        return strTime + "\n";
    }

    public static String getVersionFOrAndroid() {
        if (TextUtils.isEmpty(Build.VERSION.RELEASE)) {
            return "null";
        }
        return Build.VERSION.RELEASE;
    }

    public static String getOplusRomVersion() {
        return OplusBuild.VERSION.RELEASE;
    }

    public static String getBuildVersion() {
        String ver = SystemProperties.get("ro.build.version.ota");
        if (ver == null || ver.isEmpty()) {
            return "null";
        }
        return ver;
    }

    public static void recordEventForLog(int event, String log) {
        switch (event) {
            case 1001:
            case 1002:
            case 1003:
            default:
                return;
            case 1004:
                writeLogToPartition(43, log, ANDROID_TAG, ISSUE_ANDROID_INPUTMETHOD_FAIL, "type_issue_inputmethod_fail");
                return;
        }
    }

    public static int writeLogToPartition(String logstring, String tagString, String issue) {
        Log.v(TAG, "this is the old api");
        return -1;
    }

    public static int writeLogToPartition(int type, String logstring, String tagString, String issue, int isOnlyAddToDropbox) {
        String tagbegin;
        String tagend;
        if (logstring == null) {
            return -1;
        }
        Locale locale = LOG_STRING_LOCALE;
        String time = String.format(locale, "log-time: %s", getTime());
        String buildTime = "log-buildTime: " + SystemProperties.get("ro.build.version.ota", "UNKNOWN") + "\n";
        String romVersion = "log-romVersion: " + getOplusRomVersion() + "\n";
        String logType = String.format(locale, "LOGTYPE: %d\n", Integer.valueOf(type));
        Log.v(TAG, "writeLogToPartition: logType = " + logType + ", time = " + time);
        String issue2 = (issue == null || issue.isEmpty()) ? tagString : issue;
        if (tagString.equals(ANDROID_TAG)) {
            tagbegin = "<android-" + issue2 + "-begin>\n";
            tagend = "\n<android-" + issue2 + "-end>\n";
        } else if (tagString.equals(MULTIMEDIA_TAG)) {
            tagbegin = "<multimedia-" + issue2 + "-begin>\n";
            tagend = "\n<multimedia-" + issue2 + "-end>\n";
        } else if (tagString.equals("NETWORK")) {
            tagbegin = "<network-" + issue2 + "-begin>\n";
            tagend = "\n<network-" + issue2 + "-end>\n";
        } else if (tagString.equals(KERNEL_TAG)) {
            tagbegin = "<kernel-" + issue2 + "-begin>\n";
            tagend = "\n<kernel-" + issue2 + "-end>\n";
        } else if (tagString.equals(CONNECT_TAG)) {
            tagbegin = "<connectivity-" + issue2 + "-begin>\n";
            tagend = "\n<connectivity-" + issue2 + "-end>\n";
        } else if (tagString.equals(CAMERA_TAG)) {
            tagbegin = "<camera-" + issue2 + "-begin>\n";
            tagend = "\n<camera-" + issue2 + "-end>\n";
        } else {
            Log.v(TAG, "the invalid tag");
            return -1;
        }
        String str = tagbegin + logType + time + buildTime + romVersion + logstring + tagend;
        return writeRawPartition(type, tagbegin, logType, time, buildTime, romVersion, logstring, tagend, isOnlyAddToDropbox);
    }

    public static int incrementCriticalData(int type, String desc) {
        return writeLogToPartition(type, (String) null, (String) null, (String) null, desc);
    }

    public static int writeLogToPartition(int type, String logstring, String tagString, String issue, String desc) {
        int res;
        if (logstring == null) {
            res = 0;
        } else if (!logstring.isEmpty()) {
            res = writeLogToPartition(type, logstring, tagString, issue, -1);
        } else {
            Log.v(TAG, "log is empty");
            res = 0;
        }
        int upRes = updateLogReference(type, desc, false);
        if (type > 19) {
            upRes = updateLogReference(type, desc, true);
        }
        if (upRes == -1 && res == -1) {
            return -3;
        }
        if (upRes != -1 || res == -1) {
            return (upRes == -1 || res != -1) ? 1 : -1;
        }
        return -2;
    }

    private static int updateLogReference(int type, String desc, boolean isBackup) {
        String ref;
        String ref2;
        int res;
        String str = "catch e = ";
        try {
            if (isBackup) {
                ref = readCriticalData(type + 1024, 256);
                Log.v(TAG, "updateLogReference read backup type=" + (type + 1024) + " ref=" + ref);
            } else {
                ref = readCriticalData(type, 256);
                Log.d(TAG, "type = " + type);
                Log.d(TAG, "ref = " + ref);
            }
            if (ref == null || ref.isEmpty()) {
                ref2 = String.format(LOG_STRING_LOCALE, "%d:%s:%d", Integer.valueOf(type), desc, 1);
            } else {
                String[] refSplit = ref.split(SettingsStringUtil.DELIMITER);
                if (refSplit == null || refSplit.length < 2) {
                    Log.v(TAG, "update can not get any keyword");
                    ref2 = String.format(LOG_STRING_LOCALE, "%d:%s:%d", Integer.valueOf(type), desc, 1);
                } else {
                    try {
                        int count = Integer.parseInt(refSplit[2]);
                        if (desc.equals(refSplit[1])) {
                            str = String.format(LOG_STRING_LOCALE, "%d:%s:%d", Integer.valueOf(type), desc, Integer.valueOf(count + 1));
                            ref2 = str;
                        } else {
                            str = String.format(LOG_STRING_LOCALE, "%d:%s:%d", Integer.valueOf(type), desc, 1);
                            ref2 = str;
                        }
                    } catch (Exception e) {
                        Log.v(TAG, str + e.toString());
                        ref2 = String.format(LOG_STRING_LOCALE, "%d:%s:%d", Integer.valueOf(type), desc, 1);
                    }
                }
            }
            if (isBackup) {
                res = writeCriticalData(type + 1024, ref2);
            } else {
                res = writeCriticalData(type, ref2);
            }
            Log.v(TAG, "updateLogReference res=" + res);
            return res;
        } catch (Exception e2) {
            Log.v(TAG, str + e2.toString());
            return -1;
        }
    }

    public static boolean isEmmcLimit(int type) {
        try {
            String ref = readCriticalData(type + 1024, 256);
            String[] refSplit = ref.split(SettingsStringUtil.DELIMITER);
            if (refSplit == null || refSplit.length < 2) {
                Log.v(TAG, "the refs is not formative");
                return false;
            }
            try {
                int count = Integer.parseInt(refSplit[2]);
                if (count < 5000) {
                    return false;
                }
            } catch (Exception e) {
                Log.v(TAG, "catch e = " + e.toString());
            }
            Log.v(TAG, "limit to record type = " + type);
            return true;
        } catch (Exception e2) {
            Log.v(TAG, "isEmmcLimit exception e = " + e2.toString());
            return false;
        }
    }

    public static int cleanItem(int id) {
        return nativeCleanItem(id);
    }

    public static int syncCacheToEmmc() {
        nativeSyncCahceToEmmc();
        return 0;
    }

    public static int updateConfig() {
        nativeUpdateConfig();
        return 0;
    }

    public static int testFunc(int id, int size) {
        nativeTestFunc(id, size);
        return 0;
    }

    public static void onStamp(String eventId, Map<String, String> logMap) {
        if (sService != null || init()) {
            try {
                IOplusService iOplusService = sService;
                if (iOplusService != null) {
                    iOplusService.sendOnStampEvent(eventId, logMap);
                    Log.d(TAG, "send on stamp success");
                }
            } catch (Exception e) {
                Log.e(TAG, "error : " + e.toString());
            }
        } else {
            Log.d(TAG, "can not init the service");
        }
    }

    public static void readAllStamp(String eventId, IStampCallBack cb) {
        if (sService == null && !init()) {
            Log.d(TAG, "can not init the service");
        }
    }

    public static void readLastStamp(String eventId, int lastDays, IStampCallBack cb) {
        if (sService == null && !init()) {
            Log.d(TAG, "can not init the service");
        }
    }

    public static void rawQueryEvent(String selectQuery) {
        if (sService == null && !init()) {
            Log.d(TAG, "can not init the service");
        }
    }

    public static void onDeleteStampId(String eventId) {
        if (sService != null || init()) {
            try {
                IOplusService iOplusService = sService;
                if (iOplusService != null) {
                    iOplusService.sendDeleteStampId(eventId);
                    Log.d(TAG, "send on delete stamp success");
                }
            } catch (Exception e) {
                Log.e(TAG, "error : " + e.toString());
            }
        } else {
            Log.d(TAG, "can not init the service");
        }
    }

    public static void sendQualityDCSEvent(String eventId, Map<String, String> extraMap) {
        if (sService != null || init()) {
            try {
                sService.sendQualityDCSEvent(eventId, extraMap);
                Log.d(TAG, "sendOnQualityDCSEvent success");
            } catch (Exception e) {
                Log.e(TAG, "error : " + e.toString());
            }
        } else {
            Log.d(TAG, "sendOnQualityDCSEvent failed. Can not init the service");
        }
    }

    public static long getLongCheck(long val, long def) {
        return val < 0 ? def : val;
    }
}
