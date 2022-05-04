package android.content.res;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.content.ComponentName;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
/* loaded from: classes.dex */
public interface IUxIconPackageManagerExt extends IOplusCommonFeature {
    public static final IUxIconPackageManagerExt DEFAULT = new IUxIconPackageManagerExt() { // from class: android.content.res.IUxIconPackageManagerExt.1
    };

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IUxIconPackageManagerExt;
    }

    @Override // android.common.IOplusCommonFeature
    default IUxIconPackageManagerExt getDefault() {
        return DEFAULT;
    }

    default PackageManager getPackageManager() {
        return null;
    }

    default Drawable loadItemIcon(PackageItemInfo itemInfo, ApplicationInfo appInfo, boolean isConvertEnable) {
        if (getPackageManager() != null) {
            return getPackageManager().loadItemIcon(itemInfo, appInfo);
        }
        return null;
    }

    default Drawable loadResolveIcon(ResolveInfo info, PackageManager pm, String packageName, int resid, ApplicationInfo appInfo, boolean convert) {
        return pm.getDrawable(packageName, resid, appInfo);
    }

    default void clearCachedIconForActivity(ComponentName activityName) throws PackageManager.NameNotFoundException {
    }

    default Drawable getUxIconDrawable(Drawable src, boolean isForegroundDrawable) {
        return src;
    }

    default Drawable getUxIconDrawable(String packageName, Drawable src, boolean isForegroundDrawable) {
        return src;
    }

    default Drawable getCachedIconForThemeHelper(String packageName, int id) {
        return null;
    }

    default IResourcesExt getOplusBaseResourcesForThemeHelper(ApplicationInfo app) throws PackageManager.NameNotFoundException {
        return IResourcesExt.DEFAULT;
    }

    default void putCachedIconForThemeHelper(String packageName, int id, Drawable dr) {
    }
}
