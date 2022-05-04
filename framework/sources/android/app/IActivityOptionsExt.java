package android.app;

import android.os.Bundle;
/* loaded from: classes.dex */
public interface IActivityOptionsExt {
    default void setData(Bundle b) {
    }

    default void getData(Bundle b) {
    }

    default boolean isRPLaunch() {
        return false;
    }

    default void setRPLaunch(boolean rpLaunch) {
    }

    default int getZoomLaunchFlags() {
        return 0;
    }

    default void setZoomLaunchFlags(int zoomLaunchFlags) {
    }
}
