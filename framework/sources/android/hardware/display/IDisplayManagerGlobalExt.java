package android.hardware.display;

import android.view.IChoreographerExt;
/* loaded from: classes.dex */
public interface IDisplayManagerGlobalExt {
    default void onUpdateRefreshRate(int event, int displayId) {
    }

    default void registerChoreographerForRefreshRateCallback(IChoreographerExt choreographerExt) {
    }

    default void unregisterChoreographerForRefreshRateCallback() {
    }
}
