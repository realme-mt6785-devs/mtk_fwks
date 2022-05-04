package android.service.contentcapture;

import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ContentCaptureService$1$$ExternalSyntheticLambda6 implements BiConsumer {
    public static final /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda6 INSTANCE = new ContentCaptureService$1$$ExternalSyntheticLambda6();

    private /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda6() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((ContentCaptureService) obj).handleFinishSession(((Integer) obj2).intValue());
    }
}
