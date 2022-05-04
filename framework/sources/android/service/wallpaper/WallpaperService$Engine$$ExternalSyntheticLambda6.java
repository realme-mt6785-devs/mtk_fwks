package android.service.wallpaper;

import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class WallpaperService$Engine$$ExternalSyntheticLambda6 implements Consumer {
    public static final /* synthetic */ WallpaperService$Engine$$ExternalSyntheticLambda6 INSTANCE = new WallpaperService$Engine$$ExternalSyntheticLambda6();

    private /* synthetic */ WallpaperService$Engine$$ExternalSyntheticLambda6() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((EngineWindowPage) obj).setLastUpdateTime(0L);
    }
}
