package android.service.contentcapture;

import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ContentCaptureService$1$$ExternalSyntheticLambda2 implements TriConsumer {
    public static final /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda2 INSTANCE = new ContentCaptureService$1$$ExternalSyntheticLambda2();

    private /* synthetic */ ContentCaptureService$1$$ExternalSyntheticLambda2() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((ContentCaptureService) obj).handleOnActivitySnapshot(((Integer) obj2).intValue(), (SnapshotData) obj3);
    }
}
