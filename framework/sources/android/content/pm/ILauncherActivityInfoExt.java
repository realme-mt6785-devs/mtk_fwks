package android.content.pm;

import android.graphics.drawable.Drawable;
import android.os.UserHandle;
/* loaded from: classes.dex */
public interface ILauncherActivityInfoExt {
    default boolean isOplusIcons() {
        return false;
    }

    default Drawable getBadgedIcon(LauncherActivityInfo obj, int density, PackageManager pm, UserHandle user, ActivityInfo info) {
        return null;
    }
}
