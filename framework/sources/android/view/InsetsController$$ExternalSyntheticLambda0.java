package android.view;

import android.animation.TypeEvaluator;
import android.graphics.Insets;
/* loaded from: classes3.dex */
public final /* synthetic */ class InsetsController$$ExternalSyntheticLambda0 implements TypeEvaluator {
    public static final /* synthetic */ InsetsController$$ExternalSyntheticLambda0 INSTANCE = new InsetsController$$ExternalSyntheticLambda0();

    private /* synthetic */ InsetsController$$ExternalSyntheticLambda0() {
    }

    @Override // android.animation.TypeEvaluator
    public final Object evaluate(float f, Object obj, Object obj2) {
        Insets of;
        Insets insets = (Insets) obj;
        Insets insets2 = (Insets) obj2;
        of = Insets.of((int) (insets.left + ((insets2.left - insets.left) * f)), (int) (insets.top + ((insets2.top - insets.top) * f)), (int) (insets.right + ((insets2.right - insets.right) * f)), (int) (insets.bottom + ((insets2.bottom - insets.bottom) * f)));
        return of;
    }
}
