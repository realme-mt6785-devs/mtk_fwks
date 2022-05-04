package android.graphics;
/* loaded from: classes.dex */
public class ColorMatrixColorFilter extends ColorFilter {
    private final ColorMatrix mMatrix;

    private static native long nativeColorMatrixFilter(float[] fArr);

    public ColorMatrixColorFilter(ColorMatrix matrix) {
        ColorMatrix colorMatrix = new ColorMatrix();
        this.mMatrix = colorMatrix;
        colorMatrix.set(matrix);
    }

    public ColorMatrixColorFilter(float[] array) {
        ColorMatrix colorMatrix = new ColorMatrix();
        this.mMatrix = colorMatrix;
        if (array.length >= 20) {
            colorMatrix.set(array);
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void getColorMatrix(ColorMatrix colorMatrix) {
        colorMatrix.set(this.mMatrix);
    }

    public void setColorMatrix(ColorMatrix matrix) {
        discardNativeInstance();
        if (matrix == null) {
            this.mMatrix.reset();
        } else {
            this.mMatrix.set(matrix);
        }
    }

    public void setColorMatrixArray(float[] array) {
        discardNativeInstance();
        if (array == null) {
            this.mMatrix.reset();
        } else if (array.length >= 20) {
            this.mMatrix.set(array);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override // android.graphics.ColorFilter
    long createNativeInstance() {
        return nativeColorMatrixFilter(this.mMatrix.getArray());
    }
}
