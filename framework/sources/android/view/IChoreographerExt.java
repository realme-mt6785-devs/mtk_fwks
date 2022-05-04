package android.view;

import android.os.Handler;
/* loaded from: classes3.dex */
public interface IChoreographerExt {
    default void recordSkippedFrames(long skippedFrames, boolean animation, boolean traversal, long startNanos, long jitterNanos) {
    }

    default void traceBeginForSkippedFrames() {
    }

    default void traceEndForSkippedFrames() {
    }

    default void dumpAnimationDropInfo(long startNanos) {
    }

    default void setIsSFChoregrapher(boolean isSFChoregrapher) {
    }

    default void registerRefreshRateListener(long frameStartNanos, Handler handler) {
    }

    default void doFrameStartHook() {
    }

    default void doFrameHook() {
    }

    default long adjustFrameTimeNanos(long frameTimeNanos, long lastFrameTimeNanos) {
        return frameTimeNanos;
    }

    default void setIsPreDraw(boolean isPreDraw) {
    }

    default boolean checkScrollOptCase() {
        return false;
    }

    default boolean checkScrollOptEnable(long startNanos, long jitterNanos, long intendedFrameTimeNanos, long frameTimeNanos, long id, long frameDeadline, long frameInterval) {
        return false;
    }

    default void traceBeginForOptimizeSlidingEffect(long startNanos, long frameTimeNanos) {
    }

    default boolean checkScrollOptSceneEnable(long frameTimeNanos, long frameIntervalNanos) {
        return false;
    }

    default void traceEndForOptimizeSlidingEffect() {
    }

    default void checkPreAnimOpt(long frameTimeNanos, long frameIntervalNanos, long lastFrameTimeNanos) {
    }

    default void resetOptState(boolean isActivityEnable) {
    }

    default void onUpdateRefreshRate(float newRefreshRate) {
    }

    default void setSlideAnimOptHelper(Choreographer choreographer) {
    }

    default void doInitiativeFrame(long obj, int arg1) {
    }

    default void doAnimCallback(long obj, int arg1) {
    }

    default long getVsyncEventDataId() {
        return 0L;
    }

    default boolean checkJankTrackerEnable() {
        return false;
    }

    default long getInputEventFlag(InputEvent event, int maximumVelocity, int minimumVelocity) {
        return 0L;
    }

    default long populateAndResetFrameInfo(long[] frameInfoList) {
        return 0L;
    }

    default void populateMeasureCost(long cost) {
    }

    default void populateLayoutCost(long cost) {
    }

    default long getDrawingCacheFlag() {
        return 0L;
    }

    default void markRelayout() {
    }

    default void resetFrameCount() {
    }

    default void markDrawStart() {
    }

    default void syncViewCount(int viewCount) {
    }
}
