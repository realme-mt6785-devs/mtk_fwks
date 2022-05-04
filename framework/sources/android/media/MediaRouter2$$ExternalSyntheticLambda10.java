package android.media;

import android.media.MediaRouter2;
import java.util.function.Function;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRouter2$$ExternalSyntheticLambda10 implements Function {
    public static final /* synthetic */ MediaRouter2$$ExternalSyntheticLambda10 INSTANCE = new MediaRouter2$$ExternalSyntheticLambda10();

    private /* synthetic */ MediaRouter2$$ExternalSyntheticLambda10() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        RouteDiscoveryPreference routeDiscoveryPreference;
        routeDiscoveryPreference = ((MediaRouter2.RouteCallbackRecord) obj).mPreference;
        return routeDiscoveryPreference;
    }
}
