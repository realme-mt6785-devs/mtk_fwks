package android.view;

import android.view.SyncRtSurfaceTransactionApplier;
import android.view.WindowInsetsAnimation;
/* loaded from: classes3.dex */
public interface InsetsAnimationControlCallbacks {
    void applySurfaceParams(SyncRtSurfaceTransactionApplier.SurfaceParams... surfaceParamsArr);

    void notifyFinished(InsetsAnimationControlRunner insetsAnimationControlRunner, boolean z);

    void releaseSurfaceControlFromRt(SurfaceControl surfaceControl);

    void reportPerceptible(int i, boolean z);

    void scheduleApplyChangeInsets(InsetsAnimationControlRunner insetsAnimationControlRunner);

    void startAnimation(InsetsAnimationControlImpl insetsAnimationControlImpl, WindowInsetsAnimationControlListener windowInsetsAnimationControlListener, int i, WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds);
}
