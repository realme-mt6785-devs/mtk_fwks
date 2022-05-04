package android.graphics;
/* loaded from: classes.dex */
public class DashPathEffect extends PathEffect {
    private static native long nativeCreate(float[] fArr, float f);

    public DashPathEffect(float[] intervals, float phase) {
        if (intervals.length >= 2) {
            this.native_instance = nativeCreate(intervals, phase);
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
