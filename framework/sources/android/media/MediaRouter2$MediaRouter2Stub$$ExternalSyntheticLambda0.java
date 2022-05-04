package android.media;

import com.android.internal.util.function.QuadConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda0 implements QuadConsumer {
    public static final /* synthetic */ MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda0 INSTANCE = new MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda0();

    private /* synthetic */ MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((MediaRouter2) obj).onRequestCreateControllerByManagerOnHandler((RoutingSessionInfo) obj2, (MediaRoute2Info) obj3, ((Long) obj4).longValue());
    }
}
