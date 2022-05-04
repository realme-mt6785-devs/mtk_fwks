package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.service.wallpaper.WallpaperService;
import java.io.InputStream;
/* loaded from: classes.dex */
public interface IWallpaperManagerExt {
    public static final float DARK_MODE_WINDOW_ALPHA = 0.76f;
    public static final int FLAG_FAST_SET = 65536;
    public static final float NORMAL_MODE_WINDOW_ALPHA = 0.98f;

    default InputStream openDefaultWallpaper(Context context, int which) {
        return null;
    }

    default ComponentName getDefaultWallpaperComponent(Context context) {
        return null;
    }

    default String getModuleWallpaperFileName(Context context, int which) {
        return "";
    }

    default boolean checkCustomizeWallpaperDir() {
        return false;
    }

    default float getDarkModeWallpaperWindowAlpha(Context context, float alpha, WallpaperService.Engine engine) {
        return alpha;
    }
}
