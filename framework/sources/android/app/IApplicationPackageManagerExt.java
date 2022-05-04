package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageManagerExt;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ParceledListSlice;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes.dex */
public interface IApplicationPackageManagerExt extends IPackageManagerExt {
    default void init(ContextImpl context) {
    }

    default boolean arePermissionsIndividuallyControlledOverrideTrue() {
        return false;
    }

    default Drawable getDefaultActivityIcon(Context context) {
        return null;
    }

    default void modifyResultInGetResourcesForApplication(Resources resources, ApplicationInfo app) {
    }

    default Drawable interceptGetDrawableInLoadUnbadgedItemIcon(PackageManager manager, PackageItemInfo itemInfo, ApplicationInfo appInfo) {
        return manager.getDrawable(itemInfo.packageName, itemInfo.icon, appInfo);
    }

    default List<PackageInfo> getInstalledPackagesAsUserExt(ParceledListSlice<PackageInfo> parceledList) {
        return null;
    }

    default List<ApplicationInfo> getInstalledApplicationsAsUserExt(ParceledListSlice<ApplicationInfo> parceledList) {
        return null;
    }

    default Bitmap getAppIconBitmap(String packageName) {
        return null;
    }

    default Map<String, Bitmap> getAppIconsCache(boolean compress) {
        return new ConcurrentHashMap();
    }

    default Map<String, Bitmap> getActivityIconsCache(IPackageDeleteObserver observer) {
        return new ConcurrentHashMap();
    }

    default int oplusFreezePackage(String pkgName, int userId, int freezeFlag, int flag) {
        return 0;
    }

    default int oplusUnFreezePackage(String pkgName, int userId, int freezeFlag, int flag) {
        return 0;
    }

    default int getOplusFreezePackageState(String pkgName, int userId) {
        return 0;
    }

    default boolean inOplusFreezePackageList(String pkgName, int userId) {
        return false;
    }

    default List<String> getOplusFreezedPackageList(int userId) {
        return new ArrayList();
    }

    default int getOplusPackageFreezeFlag(String pkgName, int userId) {
        return 0;
    }

    default boolean isSecurePayApp(String pkg) {
        return false;
    }

    default boolean isFullFunctionMode() {
        return false;
    }

    default boolean isClosedSuperFirewall() {
        return false;
    }

    default boolean loadRegionFeature(String name) {
        return false;
    }

    default FeatureInfo[] getOplusSystemAvailableFeatures() {
        return new FeatureInfo[0];
    }

    default boolean isSystemDataApp(String packageName) {
        return false;
    }

    default void checkEMMApkRuntimePermission(ComponentName cn) throws SecurityException {
    }

    default void checkAndLogMultiApp(boolean print, Context context, Object name, String tag) {
    }

    default Drawable getMultiAppUserBadgedIcon(UserHandle user) {
        return null;
    }

    default int getMultiAppUserBadgeId(UserHandle user, boolean withbg) {
        return -1;
    }

    default boolean isMultiAppUserId(int userId) {
        return false;
    }

    default Drawable getApplicationIconCacheAll(ApplicationInfo info, PackageManager manager) {
        return info.loadIcon(manager);
    }

    default Drawable getApplicationIconCache(ApplicationInfo info, PackageManager manager) {
        return info.loadIcon(manager);
    }

    default Drawable getApplicationIconCacheOrignal(ApplicationInfo info, PackageManager manager) {
        return info.loadIcon(manager);
    }

    default Drawable getApplicationIconCache(String packageName, PackageManager manager) throws PackageManager.NameNotFoundException {
        return manager.getApplicationIcon(manager.getApplicationInfo(packageName, 1024));
    }

    default Drawable getApplicationIconCacheOrignal(String packageName, PackageManager manager) throws PackageManager.NameNotFoundException {
        return manager.getApplicationIcon(manager.getApplicationInfo(packageName, 1024));
    }

    default Drawable getActivityIconCache(ComponentName componentName, PackageManager manager) throws PackageManager.NameNotFoundException {
        return manager.getActivityInfo(componentName, 1024).loadIcon(manager);
    }
}
