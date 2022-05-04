package android.service.wallpaper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.service.wallpaper.WallpaperService;
import android.view.IWindowSession;
import com.android.internal.view.BaseIWindow;
/* loaded from: classes3.dex */
public interface IWallpaperServiceExt {
    default void registerSetingsContentObserver(Context context) {
    }

    default void unregisterSettingsContentObserver(Context context) {
    }

    default void addEngine(WallpaperService.Engine engine) {
    }

    default void removeEngine(WallpaperService.Engine engine) {
    }

    default void onBind(Intent intent) {
    }

    default void reportFinishDrawing(Handler handler, IWindowSession windowSession, BaseIWindow window) {
    }

    default void finishDrawingIfNeed() {
    }

    default void onCommand(String action, int x, int y, int z, Bundle extras, boolean resultRequested) {
    }

    default void setWallPaperEngine(WallpaperService.Engine engine) {
    }

    default void setWallpaperService(WallpaperService service) {
    }

    default void adjustMessageQueue(Handler handler, Message msg) {
    }
}
