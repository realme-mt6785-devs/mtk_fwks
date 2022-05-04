package android.service.contentcapture;

import android.view.contentcapture.DataRemovalRequest;
import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ContentCaptureService$1$$ExternalSyntheticLambda5 implements BiConsumer {
    public static final /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda5 INSTANCE = new ContentCaptureService$1$$ExternalSyntheticLambda5();

    private /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda5() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((ContentCaptureService) obj).handleOnDataRemovalRequest((DataRemovalRequest) obj2);
    }
}
