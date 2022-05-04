package android.graphics;

import android.graphics.Path;
/* loaded from: classes.dex */
public interface IPathExt {
    default boolean isAddArea() {
        return false;
    }

    default void hookPath(IPathExt pathExt) {
    }

    default void hookAddRect() {
    }

    default void hookAddRoundRect(float left, float top, float right, float bottom, float rx, float ry, Path.Direction dir) {
    }

    default void hookAddRoundRect(float left, float top, float right, float bottom, float[] radii, Path.Direction dir) {
    }
}
