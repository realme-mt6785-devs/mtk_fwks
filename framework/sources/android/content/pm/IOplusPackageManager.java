package android.content.pm;

import android.content.ComponentName;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.RemoteException;
import com.oplus.app.OplusAppDynamicFeatureData;
import com.oplus.content.OplusRemovableAppInfo;
import com.oplus.content.OplusRuleInfo;
import com.oplus.ota.OplusSystemUpdateInfo;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public interface IOplusPackageManager extends IOplusBasePackageManager {
    public static final int ACTION_CHECK_MDM_PERM = 10026;
    public static final int APP_DYNAMIC_DETECT = 10034;
    public static final int DELETE_PACKAGE_DELEGATED = 10041;
    public static final int FIXUP_APP_DATA = 10043;
    public static final int GET_ACTIVITY_ICONS_CACHE_TRANSACTION = 10006;
    public static final int GET_APP_ICONS_CACHE_TRANSACTION = 10005;
    public static final int GET_APP_ICON_BITMAP_TRANSACTION = 10004;
    public static final int GET_APP_LIST_FROM_PARTITION = 10040;
    public static final int GET_CPTLIST_BY_TYPE = 10028;
    public static final int GET_CUSTOMIZE_DEFAULT_APP = 10047;
    public static final int GET_DETECT_APP_LIST = 10036;
    public static final int GET_ICON_PACK_LIST = 10032;
    public static final int GET_INTERCEPT_RULE_INFOS = 10019;
    public static final int GET_MIG_MAPPING_PKG_NAME = 10044;
    public static final int GET_NOT_INSTALLED_SYSTEM_APPS = 10038;
    public static final int GET_OPLUS_FREEZED_PACKAGE_LIST = 10012;
    public static final int GET_OPLUS_FREEZE_PACKAGE_STATE = 10010;
    public static final int GET_OPLUS_PACKAGE_FREEZE_FLAG = 10013;
    public static final int GET_OPLUS_SYSTEM_AVAILABLE_FEATURES_TRANSACTION = 10015;
    public static final int GET_REMOVABLE_APP_INFO = 10024;
    public static final int GET_REMOVABLE_APP_INFOS = 10023;
    public static final int GET_REMOVABLE_APP_LIST = 10021;
    public static final int GET_REMOVED_APP_INFOS = 10022;
    public static final int GET_SYSTEM_UPDATE_INFO = 10042;
    public static final int GET_UNINSTALLABLE_APP_CONFIG = 10048;
    public static final int GET_VALID_APP_LIST = 10039;
    public static final int IN_CPT_WHITE_LIST = 10030;
    public static final int IN_OPLUS_FREEZE_PACKAGE_LIST = 10011;
    public static final int IN_OPLUS_STANDARD_WHITE_LIST = 10033;
    public static final int IN_PMS_WHITE_LIST = 10020;
    public static final int IN_UNINSTALLABLE_APP_CONFIG = 10049;
    public static final int IS_CLOSE_SUPER_FIREWALL_TRANSACTION = 10002;
    public static final int IS_CROSS_VERSION_UPDATE = 10037;
    public static final int IS_DETECT_APP = 10035;
    public static final int IS_SECURE_PAY_APP = 10016;
    public static final int IS_SUPPORT_SESSION_WRITE = 10027;
    public static final int IS_SYSTEM_DATA_APP = 10017;
    public static final int LOAD_REGION_FEATURE_TRANSACTION = 10014;
    public static final int OPLUS_FREEZE_PACKAGE = 10008;
    public static final int OPLUS_UNFREEZE_PACKAGE = 10009;
    public static final int PROHIBIT_CHILD_INSTALLATION = 10007;
    public static final int REMOVE_CUSTOMIZE_DEFAULT_APP = 10046;
    public static final int RESTORE_REMOVABLE_APP = 10025;
    public static final int SEND_CPT_UPLOAD = 10029;
    public static final int SEND_MAP_COMMONDCS_UPLOAD = 10031;
    public static final int SET_CLOSE_SUPER_FIREWALL_TRANSACTION = 10003;
    public static final int SET_CUSTOMIZE_DEFAULT_APP = 10045;
    public static final int SET_INTERCEPT_RULE_INFOS = 10018;

    void checkEMMApkRuntimePermission(ComponentName componentName) throws SecurityException;

    void deletePackageDelegated(String str, int i, int i2, int i3, int i4, IPackageDeleteObserver iPackageDeleteObserver) throws RemoteException;

    void dynamicDetectApp(OplusAppDynamicFeatureData oplusAppDynamicFeatureData) throws RemoteException;

    Drawable getActivityIconCache(ComponentName componentName) throws PackageManager.NameNotFoundException;

    Map getActivityIconsCache(IPackageDeleteObserver iPackageDeleteObserver) throws RemoteException;

    Bitmap getAppIconBitmap(String str) throws RemoteException;

    Map getAppIconsCache(boolean z) throws RemoteException;

    List<String> getAppListFromPartition(String str) throws RemoteException;

    Drawable getApplicationIconCache(ApplicationInfo applicationInfo);

    Drawable getApplicationIconCache(String str) throws PackageManager.NameNotFoundException;

    Drawable getApplicationIconCacheAll(ApplicationInfo applicationInfo);

    Drawable getApplicationIconCacheOrignal(ApplicationInfo applicationInfo);

    Drawable getApplicationIconCacheOrignal(String str) throws PackageManager.NameNotFoundException;

    List<String> getCptListByType(int i) throws RemoteException;

    String getCustomizeDefaultApp(String str) throws RemoteException;

    List<String> getDetectAppList() throws RemoteException;

    List<ApplicationInfo> getIconPackList() throws RemoteException;

    List<OplusRuleInfo> getInterceptRuleInfos() throws RemoteException;

    List<String> getNotInstalledSystemApps() throws RemoteException;

    int getOplusFreezePackageState(String str, int i) throws RemoteException;

    List<String> getOplusFreezedPackageList(int i) throws RemoteException;

    int getOplusPackageFreezeFlag(String str, int i) throws RemoteException;

    FeatureInfo[] getOplusSystemAvailableFeatures() throws RemoteException;

    OplusRemovableAppInfo getRemovableAppInfo(String str) throws RemoteException;

    List<OplusRemovableAppInfo> getRemovableAppInfos() throws RemoteException;

    List<String> getRemovableAppList() throws RemoteException;

    List<OplusRemovableAppInfo> getRemovedAppInfos() throws RemoteException;

    OplusSystemUpdateInfo getSystemUpdateInfo() throws RemoteException;

    List<String> getValidAppList() throws RemoteException;

    boolean inCptWhiteList(int i, String str) throws RemoteException;

    boolean inOplusFreezePackageList(String str, int i) throws RemoteException;

    boolean inOplusStandardWhiteList(String str, int i, String str2) throws RemoteException;

    boolean inPmsWhiteList(int i, String str, List<String> list) throws RemoteException;

    boolean isClosedSuperFirewall() throws RemoteException;

    boolean isCrossVersionUpdate() throws RemoteException;

    boolean isDetectApp(String str) throws RemoteException;

    boolean isFullFunctionMode() throws RemoteException;

    boolean isSecurePayApp(String str) throws RemoteException;

    boolean isSupportSessionWrite() throws RemoteException;

    boolean isSystemDataApp(String str) throws RemoteException;

    boolean loadRegionFeature(String str) throws RemoteException;

    int oplusFreezePackage(String str, int i, int i2, int i3, String str2) throws RemoteException;

    int oplusUnFreezePackage(String str, int i, int i2, int i3, String str2) throws RemoteException;

    boolean prohibitChildInstallation(int i, boolean z) throws RemoteException;

    void removeCustomizeDefaultApp(String str) throws RemoteException;

    boolean restoreRemovableApp(String str, IntentSender intentSender, Bundle bundle) throws RemoteException;

    void sendCptUpload(String str, String str2) throws RemoteException;

    void sendMapCommonDcsUpload(String str, String str2, Map map) throws RemoteException;

    boolean setCustomizeDefaultApp(String str, String str2) throws RemoteException;

    boolean setInterceptRuleInfos(List<OplusRuleInfo> list) throws RemoteException;
}
