package android.service.contentcapture;

import android.view.contentcapture.DataShareRequest;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ContentCaptureService$1$$ExternalSyntheticLambda1 implements TriConsumer {
    public static final /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda1 INSTANCE = new ContentCaptureService$1$$ExternalSyntheticLambda1();

    private /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((ContentCaptureService) obj).handleOnDataShared((DataShareRequest) obj2, (IDataShareCallback) obj3);
    }
}
