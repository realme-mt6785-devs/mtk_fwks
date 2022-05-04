package android.view;

import android.view.InsetsController;
import android.view.animation.Interpolator;
/* loaded from: classes3.dex */
public final /* synthetic */ class InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda2 implements Interpolator {
    public static final /* synthetic */ InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda2 INSTANCE = new InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda2();

    private /* synthetic */ InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda2() {
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        return InsetsController.InternalAnimationControlListener.lambda$getAlphaInterpolator$2(f);
    }
}
