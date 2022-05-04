package android.hardware.camera2.params;

import android.hardware.camera2.utils.HashCodeHelpers;
import com.android.internal.util.Preconditions;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes.dex */
public final class LensShadingMap {
    public static final float MINIMUM_GAIN_FACTOR = 1.0f;
    private final int mColumns;
    private final float[] mElements;
    private final int mRows;

    public LensShadingMap(float[] elements, int rows, int columns) {
        this.mRows = Preconditions.checkArgumentPositive(rows, "rows must be positive");
        this.mColumns = Preconditions.checkArgumentPositive(columns, "columns must be positive");
        Objects.requireNonNull(elements, "elements must not be null");
        this.mElements = elements;
        if (elements.length == getGainFactorCount()) {
            Preconditions.checkArrayElementsInRange(elements, 1.0f, Float.MAX_VALUE, "elements");
            return;
        }
        throw new IllegalArgumentException("elements must be " + getGainFactorCount() + " length, received " + elements.length);
    }

    public int getRowCount() {
        return this.mRows;
    }

    public int getColumnCount() {
        return this.mColumns;
    }

    public int getGainFactorCount() {
        return this.mRows * this.mColumns * 4;
    }

    public float getGainFactor(int colorChannel, int column, int row) {
        int i;
        if (colorChannel < 0 || colorChannel > 4) {
            throw new IllegalArgumentException("colorChannel out of range");
        } else if (column < 0 || column >= (i = this.mColumns)) {
            throw new IllegalArgumentException("column out of range");
        } else if (row >= 0 && row < this.mRows) {
            return this.mElements[(((i * row) + column) * 4) + colorChannel];
        } else {
            throw new IllegalArgumentException("row out of range");
        }
    }

    public RggbChannelVector getGainFactorVector(int column, int row) {
        int i;
        if (column < 0 || column >= (i = this.mColumns)) {
            throw new IllegalArgumentException("column out of range");
        } else if (row < 0 || row >= this.mRows) {
            throw new IllegalArgumentException("row out of range");
        } else {
            int offset = ((i * row) + column) * 4;
            float[] fArr = this.mElements;
            float red = fArr[offset + 0];
            float greenEven = fArr[offset + 1];
            float greenOdd = fArr[offset + 2];
            float blue = fArr[offset + 3];
            return new RggbChannelVector(red, greenEven, greenOdd, blue);
        }
    }

    public void copyGainFactors(float[] destination, int offset) {
        Preconditions.checkArgumentNonnegative(offset, "offset must not be negative");
        Objects.requireNonNull(destination, "destination must not be null");
        if (destination.length + offset >= getGainFactorCount()) {
            System.arraycopy(this.mElements, 0, destination, offset, getGainFactorCount());
            return;
        }
        throw new ArrayIndexOutOfBoundsException("destination too small to fit elements");
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LensShadingMap)) {
            return false;
        }
        LensShadingMap other = (LensShadingMap) obj;
        if (this.mRows == other.mRows && this.mColumns == other.mColumns && Arrays.equals(this.mElements, other.mElements)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int elemsHash = HashCodeHelpers.hashCode(this.mElements);
        return HashCodeHelpers.hashCode(this.mRows, this.mColumns, elemsHash);
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("LensShadingMap{");
        String[] channelPrefix = {"R:(", "G_even:(", "G_odd:(", "B:("};
        for (int ch = 0; ch < 4; ch++) {
            str.append(channelPrefix[ch]);
            for (int r = 0; r < this.mRows; r++) {
                str.append("[");
                for (int c = 0; c < this.mColumns; c++) {
                    float gain = getGainFactor(ch, c, r);
                    str.append(gain);
                    if (c < this.mColumns - 1) {
                        str.append(", ");
                    }
                }
                str.append("]");
                if (r < this.mRows - 1) {
                    str.append(", ");
                }
            }
            str.append(")");
            if (ch < 3) {
                str.append(", ");
            }
        }
        str.append("}");
        return str.toString();
    }
}
