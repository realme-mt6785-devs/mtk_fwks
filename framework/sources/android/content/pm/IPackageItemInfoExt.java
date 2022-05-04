package android.content.pm;

import android.graphics.drawable.Drawable;
/* loaded from: classes.dex */
public interface IPackageItemInfoExt {
    default Drawable loadIcon(PackageItemInfo pkgItemInfo, PackageManager pm, ApplicationInfo appInfo) {
        return null;
    }
}
