package android.widget;

import android.content.Context;
/* loaded from: classes3.dex */
public interface IAbsListviewExt {
    default void init(Context context, AbsListView absListView) {
    }

    default int getFlywheelTimeout() {
        return 0;
    }

    default void setFlywheelTimeout(int flywheelTimeout) {
    }

    default boolean enableEndFlingProtectIfNeeded() {
        return false;
    }

    default void execEndFlingProtectIfNeeded() {
    }

    default OverScroller getOverScroller(Context context) {
        return new OverScroller(context);
    }

    default void setFlingFriction(float f) {
    }

    default void setFlingMode(int mode) {
    }

    default int getTouchMode() {
        return -1;
    }

    default void setTouchMode(int mode) {
    }

    default void startSpringback() {
    }

    default boolean isSystemStyle() {
        return false;
    }

    default boolean isSupportedStyle() {
        return false;
    }

    default int getScaledOverscrollDistance(int dist) {
        return dist;
    }

    default int getScaledOverflingDistance(int dist) {
        return dist;
    }

    default int calcRealOverScrollDist(int dist, int scrollY) {
        return dist;
    }

    default int calcRealOverScrollDist(int dist, int scrollY, int range) {
        return dist;
    }

    default void initViewConfigHelper(Context context, boolean optHelperEnable, boolean forceUsingSpring) {
    }

    default int getOverScrollDistance(int dist) {
        return dist;
    }

    default int getOverFlingDistance(int dist) {
        return dist;
    }

    default void initListHook(boolean optHelperEnable, boolean forceUsingSpring) {
    }

    default boolean getOptHelperEnable() {
        return false;
    }

    default FastScroller getFastScroller(AbsListView absListView, int style) {
        return new FastScroller(absListView, style);
    }

    default void obtainViewHook() {
    }
}
