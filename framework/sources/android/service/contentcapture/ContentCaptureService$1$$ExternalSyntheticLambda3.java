package android.service.contentcapture;

import android.os.IBinder;
import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ContentCaptureService$1$$ExternalSyntheticLambda3 implements BiConsumer {
    public static final /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda3 INSTANCE = new ContentCaptureService$1$$ExternalSyntheticLambda3();

    private /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((ContentCaptureService) obj).handleOnConnected((IBinder) obj2);
    }
}
