package android.service.wallpaper;

import android.os.SystemClock;
import java.util.function.Supplier;
/* loaded from: classes3.dex */
public final /* synthetic */ class WallpaperService$Engine$$ExternalSyntheticLambda7 implements Supplier {
    public static final /* synthetic */ WallpaperService$Engine$$ExternalSyntheticLambda7 INSTANCE = new WallpaperService$Engine$$ExternalSyntheticLambda7();

    private /* synthetic */ WallpaperService$Engine$$ExternalSyntheticLambda7() {
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return Long.valueOf(SystemClock.elapsedRealtime());
    }
}
