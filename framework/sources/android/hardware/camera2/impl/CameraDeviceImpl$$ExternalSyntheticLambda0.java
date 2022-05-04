package android.hardware.camera2.impl;

import java.util.function.BiConsumer;
/* loaded from: classes.dex */
public final /* synthetic */ class CameraDeviceImpl$$ExternalSyntheticLambda0 implements BiConsumer {
    public static final /* synthetic */ CameraDeviceImpl$$ExternalSyntheticLambda0 INSTANCE = new CameraDeviceImpl$$ExternalSyntheticLambda0();

    private /* synthetic */ CameraDeviceImpl$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((CameraDeviceImpl) obj).notifyError(((Integer) obj2).intValue());
    }
}
