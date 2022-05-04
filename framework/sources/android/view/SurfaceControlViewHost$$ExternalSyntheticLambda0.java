package android.view;

import android.view.SurfaceControl;
import android.view.WindowlessWindowManager;
/* loaded from: classes3.dex */
public final /* synthetic */ class SurfaceControlViewHost$$ExternalSyntheticLambda0 implements WindowlessWindowManager.ResizeCompleteCallback {
    public static final /* synthetic */ SurfaceControlViewHost$$ExternalSyntheticLambda0 INSTANCE = new SurfaceControlViewHost$$ExternalSyntheticLambda0();

    private /* synthetic */ SurfaceControlViewHost$$ExternalSyntheticLambda0() {
    }

    @Override // android.view.WindowlessWindowManager.ResizeCompleteCallback
    public final void finished(SurfaceControl.Transaction transaction) {
        transaction.apply();
    }
}
