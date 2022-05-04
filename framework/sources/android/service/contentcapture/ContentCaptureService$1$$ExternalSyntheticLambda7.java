package android.service.contentcapture;

import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ContentCaptureService$1$$ExternalSyntheticLambda7 implements Consumer {
    public static final /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda7 INSTANCE = new ContentCaptureService$1$$ExternalSyntheticLambda7();

    private /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda7() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((ContentCaptureService) obj).handleOnDisconnected();
    }
}
