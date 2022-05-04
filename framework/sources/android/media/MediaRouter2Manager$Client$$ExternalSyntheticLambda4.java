package android.media;

import java.util.function.BiConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRouter2Manager$Client$$ExternalSyntheticLambda4 implements BiConsumer {
    public static final /* synthetic */ MediaRouter2Manager$Client$$ExternalSyntheticLambda4 INSTANCE = new MediaRouter2Manager$Client$$ExternalSyntheticLambda4();

    private /* synthetic */ MediaRouter2Manager$Client$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((MediaRouter2Manager) obj).notifySessionReleased((RoutingSessionInfo) obj2);
    }
}
