package android.window;

import android.util.Log;
import android.view.SurfaceControl;
/* loaded from: classes3.dex */
public final /* synthetic */ class SplashScreenView$$ExternalSyntheticLambda0 implements SurfaceControl.OnReparentListener {
    public static final /* synthetic */ SplashScreenView$$ExternalSyntheticLambda0 INSTANCE = new SplashScreenView$$ExternalSyntheticLambda0();

    private /* synthetic */ SplashScreenView$$ExternalSyntheticLambda0() {
    }

    @Override // android.view.SurfaceControl.OnReparentListener
    public final void onReparent(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
        Log.e(SplashScreenView.TAG, String.format("SurfacePackage'surface reparented to %s", surfaceControl));
    }
}
