package android.widget;

import android.content.Context;
import android.util.Pair;
/* loaded from: classes3.dex */
public interface ISplineOverScrollerExt {
    default void hookSaveCurrVeloAccuCount() {
    }

    default void hookSetAbortTime(long time) {
    }

    default void hookResetVeloAccuCount() {
    }

    default void hookStartFling(long now, float currVelo, int velo, boolean finished) {
    }

    default Pair<Integer, Double> hookEndFling(Context context, int velocity, float flingFriction) {
        return new Pair<>(0, Double.valueOf(0.0d));
    }

    default void prepareScrollOpt() {
    }

    default void updateScrollerStateToIdle(Object splineOverScroller) {
    }

    default void updateScrollerStateToFling(Object splineOverScroller, long currentTime) {
    }

    default void updateScrollerStateToEnd(Object splineOverScroller, int displacement) {
    }
}
