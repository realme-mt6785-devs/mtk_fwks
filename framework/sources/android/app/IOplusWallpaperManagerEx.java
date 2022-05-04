package android.app;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.content.ComponentName;
import android.content.Context;
import android.service.wallpaper.WallpaperService;
import java.io.InputStream;
/* loaded from: classes.dex */
public interface IOplusWallpaperManagerEx extends IOplusCommonFeature {
    public static final float DARK_MODE_WINDOW_ALPHA = 0.76f;
    public static final IOplusWallpaperManagerEx DEFAULT = new IOplusWallpaperManagerEx() { // from class: android.app.IOplusWallpaperManagerEx.1
    };
    public static final int FLAG_FAST_SET = 65536;
    public static final float NORMAL_MODE_WINDOW_ALPHA = 0.98f;

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IOplusWallpaperManagerEx;
    }

    @Override // android.common.IOplusCommonFeature
    default IOplusWallpaperManagerEx getDefault() {
        return DEFAULT;
    }

    default InputStream openDefaultWallpaper(Context context, int which) {
        return null;
    }

    default ComponentName getDefaultWallpaperComponent(Context context) {
        return null;
    }

    default float getDarkModeWallpaperWindowAlpha(Context context, float alpha, WallpaperService.Engine engine) {
        return alpha;
    }
}
