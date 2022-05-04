package android.service.contentcapture;

import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ContentCaptureService$1$$ExternalSyntheticLambda4 implements BiConsumer {
    public static final /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda4 INSTANCE = new ContentCaptureService$1$$ExternalSyntheticLambda4();

    private /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((ContentCaptureService) obj).handleOnActivityEvent((ActivityEvent) obj2);
    }
}
