package android.app;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.SparseArray;
import com.oplus.app.IOplusAppStartController;
import com.oplus.app.IOplusGameSpaceController;
import com.oplus.app.IOplusHansListener;
import com.oplus.app.IOplusPermissionRecordController;
import com.oplus.app.IOplusProtectConnection;
import com.oplus.eap.IOplusEapDataCallback;
import com.oplus.eventhub.sdk.aidl.IEventCallback;
import com.oplus.multiapp.OplusMultiAppConfig;
import com.oplus.util.OplusAccidentallyTouchData;
import com.oplus.util.OplusDisplayCompatData;
import com.oplus.util.OplusDisplayOptimizationData;
import com.oplus.util.OplusPackageFreezeData;
import com.oplus.util.OplusProcDependData;
import com.oplus.util.OplusReflectData;
import com.oplus.util.OplusResolveData;
import com.oplus.util.OplusSecureKeyboardData;
import com.oplus.util.OplusUXIconData;
import com.oplus.verifycode.IOplusVerifyCodeListener;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public interface IOplusActivityManager extends IOplusBaseActivityManager {
    public static final int ACCIDENTALLY_TOUCH_TRANSACTION = 10015;
    public static final int ADD_BACKGROUND_RESTRICTED_INFO = 10049;
    public static final int ADD_FAST_APP_THIRD_LOGIN = 10046;
    public static final int ADD_FAST_APP_WC_PAY = 10038;
    public static final int ADD_MINI_PROGRAM_SHARE = 10031;
    public static final int ADD_OR_REMOVE_VERIFY_CODE_LISTENER = 10134;
    public static final int ADD_PREVENT_INDULGE_LIST = 10051;
    public static final int ADD_STAGE_PROTECT_INFO = 10021;
    public static final int ANR_VIA_THEIA_EVENT = 10114;
    public static final int COMPACT_PROCESS = 10127;
    public static final int DARKMODE_GET_APP_DATA = 10077;
    public static final int DARKMODE_IS_NIGHTMODE = 10068;
    public static final int DIRECT_REFLECT_TRANSACTION = 10037;
    public static final int DISPLAY_COMPAT_TRANSACTION = 10027;
    public static final int DISPLAY_OPTIMIZATION_TRANSACTION = 10023;
    public static final int EAP_UPDATE_ANR_DUMPSTATE = 10126;
    public static final int ENTER_FASTFREEZER = 10105;
    public static final int EXECUTE_RES_PRELOAD = 10104;
    public static final int EXIT_FASTFREEZER = 10106;
    public static final int FAVORITE_QUERY_RULE = 10048;
    public static final int FINISH_NOT_ORDER_RECEIVER_TRANSACTION = 10081;
    public static final int FONT_VARIATION_TRANSACTION = 10085;
    public static final int FORCE_TRIM_APP_MEMORY = 10067;
    public static final int GET_ALL_VISIBILE_RECENT_TASKS = 10111;
    public static final int GET_ASSOCIATED_PROCESS_FOR_PID = 10017;
    public static final int GET_ASSOCIATED_PROCESS_FOR_PKG = 10018;
    public static final int GET_ASSOCIATE_ACTIVITY_APP_LIST = 10045;
    public static final int GET_CONFIG_INFO = 10063;
    public static final int GET_CPU_LIMIT_LATEST_INFOS = 10140;
    public static final int GET_CPU_TOTAL_LOAD = 10138;
    public static final int GET_CPU_WORKING_STATS = 10061;
    public static final int GET_DOWNLOADING_LIST = 10129;
    public static final int GET_GLOBAL_PKG_WHITE_LIST = 10019;
    public static final int GET_GLOBAL_PROCESS_WHITE_LIST = 10020;
    public static final int GET_IS_SUPPORT_MULTIAPP = 10094;
    public static final int GET_MULTI_APP_ACCESS_MODE = 10096;
    public static final int GET_MULTI_APP_ALIAS = 10090;
    public static final int GET_MULTI_APP_ALL_ACCESS_MODE = 10098;
    public static final int GET_MULTI_APP_CONFIG = 10088;
    public static final int GET_MULTI_APP_LIST = 10091;
    public static final int GET_MULTI_APP_MAX_CREATE_NUM = 10093;
    public static final int GET_PACKAGE_FREEZE_DATA_INFOS = 10132;
    public static final int GET_PKG_PRELOAD_FILES = 10119;
    public static final int GET_PRELOAD_IO_SIZE = 10112;
    public static final int GET_PRELOAD_PKG_LIST = 10117;
    public static final int GET_PRELOAD_STATUS = 10118;
    public static final int GET_PROC_CMDLINE = 10079;
    public static final int GET_PROC_COMMON_INFO_LIST = 10070;
    public static final int GET_PROC_DEPENDENCE_PACKAGE = 10072;
    public static final int GET_PROC_DEPENDENCE_PID = 10071;
    public static final int GET_RES_PRELOAD_INFO = 10110;
    public static final int GET_RUNNING_APP_PROCESS_INFOS = 10131;
    public static final int GET_RUNNING_PROCESSES = 10086;
    public static final int GET_STAGE_PROTECT_LIST = 10124;
    public static final int GET_STAGE_PROTECT_LIST_AS_USER = 10125;
    public static final int GET_STAGE_PROTECT_LIST_FROM_PKG = 10025;
    public static final int GET_STAGE_PROTECT_LIST_FROM_PKG_AS_USER = 10123;
    public static final int GET_TASK_PACKAGE_LIST = 10073;
    public static final int GET_TOP_PIDS_LOAD_INFOS = 10139;
    public static final int GET_TRAFFIC_BYTES_LIST = 10115;
    public static final int GET_TRAFFIC_PACKET_LIST = 10116;
    public static final int GET_UID_CPU_WORKING_STATS = 10076;
    public static final int GRANT_OPLUS_PERMISSION_GROUP = 10012;
    public static final int HANDLE_APP_FOR_NOTIFICATION = 10014;
    public static final int HANDLE_APP_FROM_CONTROL_CENTER = 10026;
    public static final int HANS_NATIVE_UNFREEZE1_TRANSACTION = 10041;
    public static final int HANS_NATIVE_UNFREEZE_TRANSACTION = 10040;
    public static final int IN_DOWNLOADING = 10128;
    public static final int IS_FROZEN_BY_HANS = 10113;
    public static final int IS_MULTI_APP = 10099;
    public static final int IS_PERMISSION_INTERCEPT_ENABLED = 10005;
    public static final int KILL_PID_FORCE = 10008;
    public static final int MULTI_APP_INIT_CODE = 85;
    public static final int MULTI_APP_SCAN_FILE = 10095;
    public static final int NOTIFY_APP_KILL_REASON = 10084;
    public static final int NOTIFY_ATHENA_ONEKEY_CLEAR_RUNNING_STATE = 10103;
    public static final int OPLUS_ANIMATION_TRANSACTION = 10058;
    public static final int PUT_CONFIG_INFO = 10062;
    public static final int QUERY_PROCESS_NAME = 10083;
    public static final int QUERY_USAGE_STATS = 10130;
    public static final int REGISTER_HANS_LISTENER = 10101;
    public static final int REMOVE_FAST_APP_THIRD_LOGIN = 10047;
    public static final int REMOVE_FAST_APP_WC_PAY = 10039;
    public static final int REMOVE_MINI_PROGRAM_SHARE = 10032;
    public static final int REMOVE_STAGE_PROTECT_INFO = 10022;
    public static final int REPORT_SKIPPED_FRAMES = 10082;
    public static final int REPORT_SKIPPED_FRAMES_WITH_FLAG = 10109;
    public static final int REPORT_SKIPPED_FRAMES_WITH_PCKNAME = 10137;
    public static final int RESOLVE_TRANSACTION = 10034;
    public static final int REVOKE_OPLUS_PERMISSION_GROUP = 10013;
    public static final int SECURE_KEYBOARD_TRANSACTION = 10024;
    public static final int SET_APP_FREEZE = 10078;
    public static final int SET_APP_FREEZE_CONTROLLER = 10044;
    public static final int SET_EAP_CRASH_CALLBACK = 10107;
    public static final int SET_ERROR_INFO_CALLBACK = 10135;
    public static final int SET_GAME_CONTROLLER_TRANSACTION = 10016;
    public static final int SET_MULTI_APP_ACCESS_MODE = 10097;
    public static final int SET_MULTI_APP_ALIAS = 10089;
    public static final int SET_MULTI_APP_CONFIG = 10087;
    public static final int SET_MULTI_APP_STATUS = 10092;
    public static final int SET_PERMISSION_INTERCEPT_ENABLE = 10004;
    public static final int SET_PERMISSION_RECORD_CONTROLLER = 10066;
    public static final int SET_PREVENT_INDULGE_CONTROLLER = 10050;
    public static final int SET_PROPERTIES_TRANSACTION = 10006;
    public static final int SET_START_MONITOR_CONTROLLER = 10043;
    public static final int SYNC_PERMISSION_RECORD = 10074;
    public static final int SYSTEM_DUMP_PROC_PERF_DATA = 10069;
    public static final int TRIGGER_PROC_ACTIVE_GC = 10080;
    public static final int UI_SWITCH_NOTIFICATION = 10133;
    public static final int UNREGISTER_HANS_LISTENER = 10102;
    public static final int UNSET_EAP_CRASH_CALLBACK = 10108;
    public static final int UNSET_ERROR_INFO_CALLBACK = 10136;
    public static final int UPDATE_CPU_TRACKER = 10060;
    public static final int UPDATE_PERMISSION_CHOICE = 10003;
    public static final int UPDATE_UID_CPU_TRACKER = 10075;
    public static final int UXICON_TRANSACTION = 10120;

    void activeGc(int[] iArr) throws RemoteException;

    void addBackgroundRestrictedInfo(String str, List<String> list) throws RemoteException;

    void addFastAppThirdLogin(String str, String str2) throws RemoteException;

    void addFastAppWCPay(String str, String str2) throws RemoteException;

    void addMiniProgramShare(String str, String str2, String str3) throws RemoteException;

    boolean addOrRemoveOplusVerifyCodeListener(boolean z, IOplusVerifyCodeListener iOplusVerifyCodeListener) throws RemoteException;

    void addPreventIndulgeList(List<String> list) throws RemoteException;

    void addStageProtectInfo(String str, String str2, List<String> list, String str3, long j, IOplusProtectConnection iOplusProtectConnection) throws RemoteException;

    void anrViaTheiaEvent(int i, String str) throws RemoteException;

    void compactProcess(List<Integer> list, int i, int i2) throws RemoteException;

    boolean dumpProcPerfData(Bundle bundle) throws RemoteException;

    void enterFastFreezer(String str, int[] iArr, long j, String str2) throws RemoteException;

    void exitFastFreezer(String str, String str2) throws RemoteException;

    void finishNotOrderReceiver(IBinder iBinder, int i, int i2, String str, Bundle bundle, boolean z) throws RemoteException;

    void forceTrimAppMemory(int i) throws RemoteException;

    OplusAccidentallyTouchData getAccidentallyTouchData() throws RemoteException;

    List<ActivityManager.RecentTaskInfo> getAllVisibleTasksInfo(int i) throws RemoteException;

    Bundle getConfigInfo(String str, int i, int i2) throws RemoteException;

    OplusDisplayCompatData getDisplayCompatData() throws RemoteException;

    OplusDisplayOptimizationData getDisplayOptimizationData() throws RemoteException;

    List<OplusPackageFreezeData> getDownloadingList(int i, boolean z) throws RemoteException;

    ArrayList<String> getGlobalPkgWhiteList(int i) throws RemoteException;

    ArrayList<String> getGlobalProcessWhiteList() throws RemoteException;

    boolean getIsSupportMultiApp() throws RemoteException;

    String getMultiAppAlias(String str) throws RemoteException;

    OplusMultiAppConfig getMultiAppConfig() throws RemoteException;

    List<String> getMultiAppList(int i) throws RemoteException;

    int getMultiAppMaxCreateNum() throws RemoteException;

    List<OplusPackageFreezeData> getPackageFreezeDataInfos(List<Integer> list) throws RemoteException;

    List<String> getProcCmdline(int[] iArr) throws RemoteException;

    List<String> getProcCommonInfoList(int i) throws RemoteException;

    List<OplusProcDependData> getProcDependency(int i) throws RemoteException;

    List<OplusProcDependData> getProcDependency(String str, int i) throws RemoteException;

    OplusReflectData getReflectData() throws RemoteException;

    OplusResolveData getResolveData() throws RemoteException;

    List<ActivityManager.RunningAppProcessInfo> getRunningAppProcessInfos(List<Integer> list) throws RemoteException;

    List<OplusPackageFreezeData> getRunningProcesses() throws RemoteException;

    OplusSecureKeyboardData getSecureKeyboardData() throws RemoteException;

    List<String> getStageProtectList(int i) throws RemoteException;

    List<String> getStageProtectListAsUser(int i, int i2) throws RemoteException;

    ArrayList<String> getStageProtectListFromPkg(String str, int i) throws RemoteException;

    List<String> getStageProtectListFromPkgAsUser(String str, int i, int i2) throws RemoteException;

    List<String> getTaskPkgList(int i) throws RemoteException;

    SparseArray<Long> getTrafficBytesList(ArrayList<Integer> arrayList) throws RemoteException;

    SparseArray<Long> getTrafficPacketList(ArrayList<Integer> arrayList) throws RemoteException;

    OplusUXIconData getUXIconData() throws RemoteException;

    void grantOplusPermissionByGroup(String str, String str2) throws RemoteException;

    void handleAppForNotification(String str, int i, int i2) throws RemoteException;

    void handleAppFromControlCenter(String str, int i) throws RemoteException;

    boolean inDownloading(int i, int i2, boolean z) throws RemoteException;

    boolean isFrozenByHans(String str, int i) throws RemoteException;

    boolean isPermissionInterceptEnabled() throws RemoteException;

    void killPidForce(int i) throws RemoteException;

    void notifyAppKillReason(int i, int i2, int i3, int i4, String str) throws RemoteException;

    void notifyAthenaOnekeyClearRunning(int i) throws RemoteException;

    void notifyUiSwitched(String str, int i) throws RemoteException;

    boolean putConfigInfo(String str, Bundle bundle, int i, int i2) throws RemoteException;

    String queryProcessNameFromPid(int i) throws RemoteException;

    List<UsageStats> queryUsageStats(int i, long j, long j2) throws RemoteException;

    void registerEapDataCallback(IOplusEapDataCallback iOplusEapDataCallback) throws RemoteException;

    boolean registerHansListener(String str, IOplusHansListener iOplusHansListener) throws RemoteException;

    void removeFastAppThirdLogin(String str, String str2) throws RemoteException;

    void removeFastAppWCPay(String str, String str2) throws RemoteException;

    void removeMiniProgramShare(String str, String str2, String str3) throws RemoteException;

    void removeStageProtectInfo(String str, String str2) throws RemoteException;

    void reportSkippedFrames(long j, long j2) throws RemoteException;

    void reportSkippedFrames(long j, boolean z, boolean z2, long j2) throws RemoteException;

    void reportSkippedFrames(long j, boolean z, boolean z2, long j2, String str) throws RemoteException;

    void revokeOplusPermissionByGroup(String str, String str2) throws RemoteException;

    boolean setAppFreeze(String str, Bundle bundle) throws RemoteException;

    void setAppStartMonitorController(IOplusAppStartController iOplusAppStartController) throws RemoteException;

    void setGameSpaceController(IOplusGameSpaceController iOplusGameSpaceController) throws RemoteException;

    int setMultiAppAlias(String str, String str2) throws RemoteException;

    int setMultiAppConfig(OplusMultiAppConfig oplusMultiAppConfig) throws RemoteException;

    int setMultiAppStatus(String str, int i) throws RemoteException;

    void setPermissionInterceptEnable(boolean z) throws RemoteException;

    void setPermissionRecordController(IOplusPermissionRecordController iOplusPermissionRecordController) throws RemoteException;

    void setPreventIndulgeController(IOplusAppStartController iOplusAppStartController) throws RemoteException;

    void setSystemProperties(String str, String str2) throws RemoteException;

    void syncPermissionRecord() throws RemoteException;

    void unregisterEapDataCallback(IOplusEapDataCallback iOplusEapDataCallback) throws RemoteException;

    boolean unregisterHansListener(String str, IOplusHansListener iOplusHansListener) throws RemoteException;

    void updatePermissionChoice(String str, String str2, int i) throws RemoteException;

    default void registerErrorInfoCallback(IEventCallback callback) throws RemoteException {
    }

    default void unregisterErrorInfoCallback(IEventCallback callback) throws RemoteException {
    }
}
