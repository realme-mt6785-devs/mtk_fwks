package android.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.internal.R;
/* loaded from: classes3.dex */
public class IconDrawableFactory {
    protected final Context mContext;
    protected final boolean mEmbedShadow;
    public IconDrwableFactoryExt mIconDrwableFactoryExt = IconDrwableFactoryExtPlugin.constructor.newInstance(this);
    protected final LauncherIcons mLauncherIcons;
    protected final PackageManager mPm;
    protected final UserManager mUm;

    private IconDrawableFactory(Context context, boolean embedShadow) {
        this.mContext = context;
        this.mPm = context.getPackageManager();
        this.mUm = (UserManager) context.getSystemService(UserManager.class);
        this.mLauncherIcons = new LauncherIcons(context);
        this.mEmbedShadow = embedShadow;
    }

    protected boolean needsBadging(ApplicationInfo appInfo, int userId) {
        return appInfo.isInstantApp() || this.mUm.hasBadge(userId);
    }

    public Drawable getBadgedIcon(ApplicationInfo appInfo) {
        return getBadgedIcon(appInfo, UserHandle.getUserId(appInfo.uid));
    }

    public Drawable getBadgedIcon(ApplicationInfo appInfo, int userId) {
        return getBadgedIcon(appInfo, appInfo, userId);
    }

    public Drawable getBadgedIcon(PackageItemInfo itemInfo, ApplicationInfo appInfo, int userId) {
        Drawable icon = this.mPm.loadUnbadgedItemIcon(itemInfo, appInfo);
        if (!this.mEmbedShadow && !needsBadging(appInfo, userId)) {
            return icon;
        }
        if (!this.mIconDrwableFactoryExt.isOemIcons()) {
            icon = getShadowedIcon(icon);
        }
        if (appInfo.isInstantApp()) {
            int badgeColor = Resources.getSystem().getColor(R.color.instant_app_badge, null);
            icon = this.mLauncherIcons.getBadgedDrawable(icon, R.drawable.ic_instant_icon_badge_bolt, badgeColor);
        }
        if (this.mIconDrwableFactoryExt.isMultiAppUserId(userId)) {
            return this.mIconDrwableFactoryExt.hookgetBadgedIcon(icon);
        }
        if (this.mUm.hasBadge(userId)) {
            return this.mLauncherIcons.getBadgedDrawable(icon, this.mUm.getUserIconBadgeResId(userId), this.mUm.getUserBadgeColor(userId));
        }
        return icon;
    }

    public Drawable getShadowedIcon(Drawable icon) {
        return this.mLauncherIcons.wrapIconDrawableWithShadow(icon);
    }

    public static IconDrawableFactory newInstance(Context context) {
        return new IconDrawableFactory(context, true);
    }

    public static IconDrawableFactory newInstance(Context context, boolean embedShadow) {
        return new IconDrawableFactory(context, embedShadow);
    }
}
