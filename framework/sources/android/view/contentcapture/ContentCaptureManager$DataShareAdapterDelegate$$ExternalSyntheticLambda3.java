package android.view.contentcapture;

import java.util.function.Consumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ContentCaptureManager$DataShareAdapterDelegate$$ExternalSyntheticLambda3 implements Consumer {
    public static final /* synthetic */ ContentCaptureManager$DataShareAdapterDelegate$$ExternalSyntheticLambda3 INSTANCE = new ContentCaptureManager$DataShareAdapterDelegate$$ExternalSyntheticLambda3();

    private /* synthetic */ ContentCaptureManager$DataShareAdapterDelegate$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ((DataShareWriteAdapter) obj).onRejected();
    }
}
