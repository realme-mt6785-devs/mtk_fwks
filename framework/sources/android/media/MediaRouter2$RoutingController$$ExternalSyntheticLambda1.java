package android.media;

import android.media.MediaRouter2;
import java.util.function.BiConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRouter2$RoutingController$$ExternalSyntheticLambda1 implements BiConsumer {
    public static final /* synthetic */ MediaRouter2$RoutingController$$ExternalSyntheticLambda1 INSTANCE = new MediaRouter2$RoutingController$$ExternalSyntheticLambda1();

    private /* synthetic */ MediaRouter2$RoutingController$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((MediaRouter2) obj).notifyStop((MediaRouter2.RoutingController) obj2);
    }
}
