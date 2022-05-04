package android.view;

import android.view.SurfaceControl;
/* loaded from: classes3.dex */
public interface AttachedSurfaceControl {
    boolean applyTransactionOnDraw(SurfaceControl.Transaction transaction);

    SurfaceControl.Transaction buildReparentTransaction(SurfaceControl surfaceControl);
}
