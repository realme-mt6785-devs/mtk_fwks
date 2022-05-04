package android.view;

import android.view.InsetsController;
import android.view.animation.Interpolator;
/* loaded from: classes3.dex */
public final /* synthetic */ class InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda4 implements Interpolator {
    public static final /* synthetic */ InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda4 INSTANCE = new InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda4();

    private /* synthetic */ InsetsController$InternalAnimationControlListener$$ExternalSyntheticLambda4() {
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        return InsetsController.InternalAnimationControlListener.lambda$getAlphaInterpolator$4(f);
    }
}
