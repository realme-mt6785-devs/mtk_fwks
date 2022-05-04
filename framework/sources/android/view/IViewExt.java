package android.view;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.Layout;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Animation;
/* loaded from: classes3.dex */
public interface IViewExt {
    public static final int DRAG_FLAG_CUSTOM_CANCEL_ANIMATION = 1073741824;
    public static final int DRAG_FLAG_CUSTOM_RETURN_ANIMATION = Integer.MIN_VALUE;
    public static final int LOG_LEVEL_DEBUG = 1;
    public static final int LOG_LEVEL_VERBOSE = 2;
    public static final int LOG_LEVEL_VERBOSE_ACTION = 21;
    public static final int TYPE_FORCE_DARK_ALGORITHM_GOOGLE = 2;
    public static final int TYPE_FORCE_DARK_ALGORITHM_OPLUS = 1;

    default void hookPerformClick() {
    }

    default void hookAssignParent(ViewParent parent) {
    }

    default void hookStartDraw(View view, Canvas canvas) {
    }

    default void hookAfterDispatchDraw(View view, Canvas canvas) {
    }

    default void hookAfterDrawCanvas(View view, Canvas canvas) {
    }

    default void hookDrawBackground(View view, Canvas canvas) {
    }

    default void getDrawableRenderNode(View view, Canvas canvas) {
    }

    default void hookSizeChange(View view) {
    }

    default void hookRequestLayout() {
    }

    default void hookOverScrollBy(int scrollX, int scrollY, int scrollRangeX, int scrollRangeY) {
    }

    default boolean hookDispatchNestedScroll() {
        return true;
    }

    default boolean isOplusOSStyle() {
        return false;
    }

    default boolean isOplusStyle() {
        return false;
    }

    default void updateColorNavigationGuardColor(int color) {
    }

    default Bitmap getColorCustomDrawingCache(Rect clip, int viewTop) {
        return null;
    }

    default int getOriginWebSettingForceDark() {
        return -1;
    }

    default void setOriginWebSettingForceDark(int forceDark) {
    }

    default void setUsageForceDarkAlgorithmType(int type) {
    }

    default void setCrudeState(int state) {
    }

    default int getCrudeState() {
        return 0;
    }

    default int getOplusViewType() {
        return 0;
    }

    default void setOplusViewTypeLocked(int viewType) {
    }

    default void hookTextView(Layout mlayout) {
    }

    default float getTextViewDefaultLineMulti(Object textview, float pxSize, float oriValue) {
        return oriValue;
    }

    default float getTextViewParaSpacing(Object textview) {
        return 0.0f;
    }

    default void setLayout(Layout layout) {
    }

    default void setScrollXForColor(int x) {
    }

    default void setScrollYForColor(int y) {
    }

    default boolean debugWebViewDraw() {
        return false;
    }

    default void onTouchEvent(MotionEvent event) {
    }

    default IViewRootImplExt getViewRootImpl() {
        return null;
    }

    default boolean initialAwakenScrollBars() {
        return false;
    }

    default boolean hookIsTouchPressed() {
        return false;
    }

    default void setContentDescriptionForField(AccessibilityNodeInfo info) {
    }

    default void logEvent(int level, String tag, InputEvent event, String info) {
    }

    default View getView() {
        return null;
    }

    default void initViewHooks(Resources resources) {
    }

    default void initView() {
    }

    default void checkBoostAnimation(Animation animation) {
    }

    default void checkBoostBuildDrawingCache() {
    }

    default void checkBoostTouchEvent(int action) {
    }

    default void checkBoostOnPerformClick(String liStr) {
    }

    default void checkNeedBoostedPropertyAnimator(ViewPropertyAnimator animator) {
    }

    default void ignoreSpecailViewDescendantInvalidated(ViewParent p) {
    }

    default boolean isIgnoreSpecailViewDescendantInvalidated() {
        return false;
    }

    default void adjustImageViewLayerType(int width, int height) {
    }

    default boolean checkMutiTouchView() {
        return false;
    }

    default void adjustPendingLayertype(int layerType) {
    }
}
