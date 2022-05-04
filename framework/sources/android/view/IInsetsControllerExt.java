package android.view;

import android.view.animation.Interpolator;
/* loaded from: classes3.dex */
public interface IInsetsControllerExt {
    default Interpolator replaceIMEInterpolator(Interpolator ip) {
        return ip;
    }

    default long replaceIMEDurationMs(boolean show, int time) {
        return time;
    }

    default boolean setInsetAnimationTid(int type) {
        return false;
    }
}
