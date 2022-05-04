package android.service.contentcapture;

import android.view.contentcapture.ContentCaptureContext;
import com.android.internal.os.IResultReceiver;
import com.android.internal.util.function.HexConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ContentCaptureService$1$$ExternalSyntheticLambda0 implements HexConsumer {
    public static final /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda0 INSTANCE = new ContentCaptureService$1$$ExternalSyntheticLambda0();

    private /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.HexConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        ((ContentCaptureService) obj).handleOnCreateSession((ContentCaptureContext) obj2, ((Integer) obj3).intValue(), ((Integer) obj4).intValue(), (IResultReceiver) obj5, ((Integer) obj6).intValue());
    }
}
