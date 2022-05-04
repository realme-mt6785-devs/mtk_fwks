package android.widget;

import android.content.Context;
import android.view.animation.Interpolator;
/* loaded from: classes3.dex */
public interface IOplusOverScrollerExt {
    default boolean setFriction(float friction) {
        return false;
    }

    default boolean isFinished(boolean finished) {
        return false;
    }

    default int getCurrX(int x) {
        return -1;
    }

    default int getCurrY(int y) {
        return -1;
    }

    default int getFinalX(int x) {
        return -1;
    }

    default int getFinalY(int y) {
        return -1;
    }

    default boolean getOptHelperEnable() {
        return false;
    }

    default boolean interpolatorValid() {
        return false;
    }

    default Interpolator getInterpolator() {
        return null;
    }

    default boolean setSpringOverScrollerInterpolator(Interpolator interpolator) {
        return false;
    }

    default void forceFinished(boolean finished) {
    }

    default float getCurrVelocity(float velocity) {
        return velocity;
    }

    default int getStartX(int start) {
        return start;
    }

    default int getStartY(int start) {
        return start;
    }

    default boolean computeScrollOffset() {
        return false;
    }

    default boolean startScroll(int startX, int startY, int dx, int dy) {
        return false;
    }

    default boolean startScroll(int startX, int startY, int dx, int dy, int duration) {
        return false;
    }

    default boolean springBack(int startX, int startY, int minX, int maxX, int minY, int maxY) {
        return false;
    }

    default boolean fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY) {
        return false;
    }

    default boolean fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY, int overX, int overY) {
        return false;
    }

    default boolean notifyHorizontalEdgeReached(int startX, int finalX, int overX) {
        return false;
    }

    default boolean notifyVerticalEdgeReached(int startY, int finalY, int overY) {
        return false;
    }

    default boolean isOverScrolled(boolean isOverScroll) {
        return false;
    }

    default boolean abortAnimation() {
        return false;
    }

    default boolean isScrollingInDirection(float xvel, float yvel) {
        return false;
    }

    default void setSpringOverScroller(Object scroller) {
    }

    default void setForceUsingSpring(boolean forceUsingSpring) {
    }

    default boolean hookOverScroller(Context context, Interpolator interpolator) {
        return false;
    }

    default boolean getForceUsingSpring() {
        return false;
    }

    default boolean hookCheckFlingFlag() {
        return false;
    }

    default boolean hookAbortAnimation(ISplineOverScrollerExt mSplineOverScrollerExtX, ISplineOverScrollerExt mSplineOverScrollerExtY) {
        return false;
    }

    default void prepareScrollOpt(ISplineOverScrollerExt splineOverScrollerExtY) {
    }
}
