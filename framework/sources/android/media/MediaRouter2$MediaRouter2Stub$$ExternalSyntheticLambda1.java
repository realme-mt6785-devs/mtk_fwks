package android.media;

import com.android.internal.util.function.TriConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda1 implements TriConsumer {
    public static final /* synthetic */ MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda1 INSTANCE = new MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda1();

    private /* synthetic */ MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((MediaRouter2) obj).createControllerOnHandler(((Integer) obj2).intValue(), (RoutingSessionInfo) obj3);
    }
}
