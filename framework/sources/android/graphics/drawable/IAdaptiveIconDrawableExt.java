package android.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
/* loaded from: classes.dex */
public interface IAdaptiveIconDrawableExt {
    default void init() {
    }

    default boolean hookOnBoundsChange(Rect bounds) {
        return false;
    }

    default boolean hookDraw(Canvas canvas) {
        return false;
    }

    default Path hookGetIconMask() {
        return null;
    }

    default boolean hookGetIntrinsicHeight() {
        return false;
    }

    default boolean hookGetIntrinsicWidth() {
        return false;
    }

    default AdaptiveIconDrawable getAdaptiveIconDrawable() {
        return null;
    }

    default float getForegroundScalePercent() {
        return 1.0f;
    }

    default void buildAdaptiveIconDrawable(Resources res, int customIconSize, int customIconFgSize, Path customMask, boolean isPlatformDrawable, boolean isAdaptiveIconDrawable) {
    }

    default boolean shouldHookGetConstantState() {
        return false;
    }

    default Drawable.ConstantState getConstantState() {
        return null;
    }
}
