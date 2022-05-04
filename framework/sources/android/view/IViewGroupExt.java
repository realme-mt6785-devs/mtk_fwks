package android.view;

import android.graphics.Canvas;
/* loaded from: classes3.dex */
public interface IViewGroupExt {
    default void d(String tag, String msg) {
    }

    default void v(String tag, String msg) {
    }

    default boolean isLevelDebug() {
        return false;
    }

    default boolean isLevelVerbose() {
        return false;
    }

    default void hookdispatchTouchEvent(MotionEvent ev, IViewExt viewExt) {
    }

    default void markDispatchDraw(ViewGroup viewGroup, Canvas canvas) {
    }

    default void markDrawChild(ViewGroup viewGroup, View view, Canvas canvas) {
    }

    default boolean hookdrawChild(Canvas canvas, View child, long drawingTime) {
        return true;
    }
}
