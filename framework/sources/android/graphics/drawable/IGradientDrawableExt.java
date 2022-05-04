package android.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
/* loaded from: classes.dex */
public interface IGradientDrawableExt {
    default void drawRoundRect(boolean smoothRound, Canvas canvas, RectF rect, float rad, boolean haveStroke, Paint fillPaint, Paint strokePaint) {
    }

    default boolean checkElementsName(String name) {
        return false;
    }

    default void setSmoothRoundStyle(boolean smooth) {
    }

    default boolean getSmoothRoundStyle() {
        return false;
    }

    default boolean getGradientStateSmoothRoundStyle() {
        return false;
    }
}
