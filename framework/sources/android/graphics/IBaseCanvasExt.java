package android.graphics;

import java.util.List;
/* loaded from: classes.dex */
public interface IBaseCanvasExt {
    public static final int TRANSFORM_BACKGROUND = 1;
    public static final int TRANSFORM_FOREGROUND = 2;
    public static final int TRANSFORM_UNKNOWN = 0;
    public static final int TYPE_MAYBE_FULL_HORIZONTAL_LINE = 3;
    public static final int TYPE_MAYBE_FULL_HORIZONTAL_RECT = 2;
    public static final int TYPE_MAYBE_SMALL_VIEW = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final RectF mRectF = new RectF();

    /* loaded from: classes.dex */
    public static class Entity {
        public boolean isDarkMode;
        public Paint newPaint;
        public RealPaintState realPaintState;
    }

    /* loaded from: classes.dex */
    public static class RealPaintState {
        public int color;
        public ColorFilter colorFilter;
        public int colorFilterColor;
        public List<long[]> composeShaderColors;
        public long[] shaderColors;
    }

    default void setViewArea(int width, int height) {
    }

    default int getViewAreaWidth() {
        return 0;
    }

    default int getViewAreaHeight() {
        return 0;
    }

    default int getOplusViewType() {
        return 0;
    }

    default void setOplusViewTypeLocked(int viewType) {
    }

    default int getTransformType() {
        return 2;
    }

    default void setTransformType(int mTransformType) {
    }

    default RectF getRectF(float width, float height) {
        return mRectF;
    }

    default RectF getRectF(float left, float top, float right, float bottom) {
        return mRectF;
    }

    default RectF getRectF(Rect rect) {
        return new RectF(rect);
    }

    default void setIsCanvasBaseBitmap(Bitmap bitmap, boolean value) {
    }

    default boolean isHardwareAccelerated() {
        return false;
    }

    default boolean isDarkMode() {
        return false;
    }

    default int getWidth() {
        return 0;
    }

    default int getHeight() {
        return 0;
    }

    default int[] changeColors(int[] colors) {
        return colors;
    }

    default Entity changeBitmap(Paint paint, Bitmap bitmap, RectF rectF) {
        return null;
    }

    default int changeColor(int color) {
        return color;
    }

    default Entity changePatch(NinePatch patch, Paint paint, RectF rectF) {
        return null;
    }

    default Entity changeArea(Paint paint, RectF rectF) {
        return null;
    }

    default Entity changeArea(Paint paint, RectF rectF, Path path) {
        return null;
    }

    default Entity changeText(Paint paint) {
        return null;
    }

    default void resetEntity(Entity entity, Paint paint) {
    }

    default void setClipChildRect(Rect rect) {
    }

    default Rect getClipChildRect() {
        return null;
    }
}
