package android.service.contentcapture;

import android.content.ContentCaptureOptions;
import android.content.pm.ParceledListSlice;
import com.android.internal.util.function.QuintConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ContentCaptureService$2$$ExternalSyntheticLambda0 implements QuintConsumer {
    public static final /* synthetic */ ContentCaptureService$2$$ExternalSyntheticLambda0 INSTANCE = new ContentCaptureService$2$$ExternalSyntheticLambda0();

    private /* synthetic */ ContentCaptureService$2$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.QuintConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        ((ContentCaptureService) obj).handleSendEvents(((Integer) obj2).intValue(), (ParceledListSlice) obj3, ((Integer) obj4).intValue(), (ContentCaptureOptions) obj5);
    }
}
