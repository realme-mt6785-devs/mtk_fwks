package android.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.MergedConfiguration;
import android.util.TypedValue;
import android.view.SurfaceControl;
import android.view.ViewRootImpl;
import android.view.WindowManager;
import java.util.List;
/* loaded from: classes3.dex */
public interface IViewRootImplExt {
    default void init(ViewRootImpl viewRootImpl, Context context) {
    }

    default void hookNewInstance(Context context) {
    }

    default void logConfigurationNightError(Context context, boolean useAutoDark) {
    }

    default void logForceDarkAllowedStatus(Context context, boolean forceDarkAllowedDefault) {
    }

    default void forceDarkWithoutTheme(Context context, View view, boolean useAutoDark) {
    }

    default int changeSystemUiVisibility(int mSystemUiVisibility) {
        return 0;
    }

    default void setDarkModeProgress(View view, Configuration globalConfig) {
    }

    default void refreshForceDark(View view, Parcelable mOplusDarkModeData) {
    }

    default void updateLogLevel() {
    }

    default boolean isLevelDebug() {
        return false;
    }

    default void debugInputStageDeliverd(String mTag, int flag, InputEvent event, String stage, String detail) {
    }

    default void v(String tag, String msg) {
    }

    default void debugEventHandled(String tag, InputEvent event, String detail) {
    }

    default void debugInputEventEnqueue(String tag, InputEvent event, boolean immediately, boolean scheduled) {
    }

    default void debugInputEventFinished(String tag, int flag, InputEvent event) {
    }

    default void debugInputEventStart(String tag, InputEvent event) {
    }

    default void setRefreshRateIfNeed(boolean ifNeed, Context context, View view, ViewRootImpl.W window) {
    }

    default void dispatchDetachedFromWindow(View view) {
    }

    default void hookSetView(View view, Context context) {
    }

    default ViewRootImpl.W createWindowClient() {
        return null;
    }

    default void hookViewRootImplHooks(Context context) {
    }

    default void setConnected(boolean isConnected) {
    }

    default boolean isConnected() {
        return false;
    }

    default void initSystemUINavigationGesture(ViewRootImpl viewRootImpl, Context context) {
    }

    default void checkKeyguardAndConfig(String tag) {
    }

    default void handleGestureMotionDown(View view) {
    }

    default void handleGestureConfigCheck() {
    }

    default void setSystemGestureExclusionRegion(List<Rect> rects) {
    }

    default boolean processGestureEvent(MotionEvent event) {
        return false;
    }

    default boolean showSurfaceViewBackground(int subLayer) {
        return false;
    }

    default void setLastReportedMergedConfiguration(View mView, Configuration newConfig, Context context) {
    }

    default void onWindowFocusChangedByRoot(boolean hasWindowFocus, View view, MergedConfiguration configuration) {
    }

    default boolean dispatchTouchEventForZoomWinow(InputEventReceiver receiver, InputEvent ev, View view, MergedConfiguration configuration) {
        return false;
    }

    default void updateInputEventToInputMethod(InputEvent event) {
    }

    default void initViewRoomImpl() {
    }

    default void checkIsWebchatLauncherUI() {
    }

    default boolean isWebchatLauncherUI() {
        return false;
    }

    default void showSoftInput(String pkg) {
    }

    default void hideSoftInputFromWindow(String pkg) {
    }

    default boolean checkTraversalsImmediatelyProssible(boolean isFirst) {
        return false;
    }

    default boolean checkTraversalsImmediatelyProssibleInTraversals(boolean isFirst, boolean mIsInTraversal) {
        return false;
    }

    default boolean disableRelayout() {
        return false;
    }

    default boolean isSplitWindow(Configuration configuration) {
        return false;
    }

    default int getColorMode(int colorMode) {
        return colorMode;
    }

    default boolean setTransparentRegionForZoom(MergedConfiguration configuration, SurfaceControl sc, SurfaceControl.Transaction transaction) {
        return false;
    }

    default int getBaseSize(WindowManager.LayoutParams lp, TypedValue value, Resources res) {
        return (int) value.getDimension(res.getDisplayMetrics());
    }

    default void debugCancelDraw(String tag, boolean cancelDraw, boolean isViewVisible) {
    }

    default void initScrollOpt(Context context) {
    }

    default void updateBufferPendingState(Surface surface) {
    }

    default void updateScrollerState(MotionEvent me) {
    }

    default void resetOptState(Surface surface) {
    }

    default boolean nativeIsBufferAccumulated(long nativeObject) {
        return true;
    }

    default void nativeSetPresentTimeMode(long nativeObject, int mode) {
    }

    default void setScrollToTopRootView(View view, WindowManager.LayoutParams params) {
    }

    default void setScrollToTopWinFrame(Rect winFrame) {
    }

    default void handleWindowFocusChanged(Context context, boolean hasFocus) {
    }

    default void postShowGuidePopupRunnable(View decorView) {
    }

    default void processPointerEvent(MotionEvent e, Context context) {
    }

    default void onWindowDying() {
    }
}
