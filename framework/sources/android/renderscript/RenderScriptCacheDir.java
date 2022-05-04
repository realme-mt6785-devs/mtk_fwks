package android.renderscript;

import java.io.File;
@Deprecated
/* loaded from: classes2.dex */
public class RenderScriptCacheDir {
    static File mCacheDir;

    public static void setupDiskCache(File cacheDir) {
        mCacheDir = cacheDir;
    }
}
