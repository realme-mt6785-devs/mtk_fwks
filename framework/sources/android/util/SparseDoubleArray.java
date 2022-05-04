package android.util;
/* loaded from: classes3.dex */
public class SparseDoubleArray implements Cloneable {
    private SparseLongArray mValues;

    public SparseDoubleArray() {
        this(10);
    }

    public SparseDoubleArray(int initialCapacity) {
        this.mValues = new SparseLongArray(initialCapacity);
    }

    public SparseDoubleArray clone() {
        SparseDoubleArray clone = null;
        try {
            clone = (SparseDoubleArray) super.clone();
            clone.mValues = this.mValues.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            return clone;
        }
    }

    public double get(int key) {
        int index = this.mValues.indexOfKey(key);
        if (index < 0) {
            return 0.0d;
        }
        return valueAt(index);
    }

    public void put(int key, double value) {
        this.mValues.put(key, Double.doubleToRawLongBits(value));
    }

    public void add(int key, double summand) {
        double oldValue = get(key);
        put(key, oldValue + summand);
    }

    public int size() {
        return this.mValues.size();
    }

    public int keyAt(int index) {
        return this.mValues.keyAt(index);
    }

    public double valueAt(int index) {
        return Double.longBitsToDouble(this.mValues.valueAt(index));
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder buffer = new StringBuilder(size() * 34);
        buffer.append('{');
        for (int i = 0; i < size(); i++) {
            if (i > 0) {
                buffer.append(", ");
            }
            int key = keyAt(i);
            buffer.append(key);
            buffer.append('=');
            double value = valueAt(i);
            buffer.append(value);
        }
        buffer.append('}');
        return buffer.toString();
    }
}
