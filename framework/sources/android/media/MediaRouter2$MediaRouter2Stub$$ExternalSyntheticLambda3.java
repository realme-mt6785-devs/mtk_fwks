package android.media;

import java.util.function.BiConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda3 implements BiConsumer {
    public static final /* synthetic */ MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda3 INSTANCE = new MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda3();

    private /* synthetic */ MediaRouter2$MediaRouter2Stub$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((MediaRouter2) obj).releaseControllerOnHandler((RoutingSessionInfo) obj2);
    }
}
