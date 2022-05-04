package android.view;

import android.view.animation.Interpolator;
/* loaded from: classes3.dex */
public final /* synthetic */ class InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda3 implements Interpolator {
    public static final /* synthetic */ InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda3 INSTANCE = new InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda3();

    private /* synthetic */ InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda3() {
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        float min;
        min = Math.min(1.0f, 2.0f * f);
        return min;
    }
}
