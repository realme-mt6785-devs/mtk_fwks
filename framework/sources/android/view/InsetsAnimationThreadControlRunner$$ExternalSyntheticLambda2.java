package android.view;

import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class InsetsAnimationThreadControlRunner$$ExternalSyntheticLambda2 implements Consumer {
    public static final /* synthetic */ InsetsAnimationThreadControlRunner$$ExternalSyntheticLambda2 INSTANCE = new InsetsAnimationThreadControlRunner$$ExternalSyntheticLambda2();

    private /* synthetic */ InsetsAnimationThreadControlRunner$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((SurfaceControl) obj).release();
    }
}
