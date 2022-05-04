package android.graphics;

import java.util.function.DoubleUnaryOperator;
/* loaded from: classes.dex */
public final /* synthetic */ class ColorSpace$$ExternalSyntheticLambda1 implements DoubleUnaryOperator {
    public static final /* synthetic */ ColorSpace$$ExternalSyntheticLambda1 INSTANCE = new ColorSpace$$ExternalSyntheticLambda1();

    private /* synthetic */ ColorSpace$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.DoubleUnaryOperator
    public final double applyAsDouble(double d) {
        double absResponse;
        absResponse = ColorSpace.absResponse(d, 0.9478672985781991d, 0.05213270142180095d, 0.07739938080495357d, 0.04045d, 2.4d);
        return absResponse;
    }
}
