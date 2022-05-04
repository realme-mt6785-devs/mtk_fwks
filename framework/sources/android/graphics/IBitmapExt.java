package android.graphics;
/* loaded from: classes.dex */
public interface IBitmapExt {
    default boolean hasCalculatedColor() {
        return false;
    }

    default void setHasCalculatedColor(boolean isCalculatedColor) {
    }

    default boolean isAssetSource() {
        return false;
    }

    default void setIsAssetSource(boolean isAssetSource) {
    }

    default int getColorState() {
        return 0;
    }

    default void setColorState(int colorState) {
    }

    default boolean isCanvasBaseBitmap() {
        return false;
    }

    default void setIsCanvasBaseBitmap(boolean isCanvasBaseBitmap) {
    }

    default boolean isViewSrc() {
        return false;
    }

    default void setIsViewSrc(boolean isViewSrc) {
    }

    default boolean checkLM(byte[] hash, boolean getHash, int width, int height) {
        return false;
    }
}
