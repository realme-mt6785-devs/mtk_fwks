package android.hardware.camera2.impl;

import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class CameraInjectionSessionImpl$$ExternalSyntheticLambda0 implements BiConsumer {
    public static final /* synthetic */ CameraInjectionSessionImpl$$ExternalSyntheticLambda0 INSTANCE = new CameraInjectionSessionImpl$$ExternalSyntheticLambda0();

    private /* synthetic */ CameraInjectionSessionImpl$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((CameraInjectionSessionImpl) obj).notifyError(((Integer) obj2).intValue());
    }
}
