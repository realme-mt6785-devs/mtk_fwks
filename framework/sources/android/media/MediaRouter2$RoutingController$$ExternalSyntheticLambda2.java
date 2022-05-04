package android.media;

import java.util.Map;
import java.util.function.Function;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRouter2$RoutingController$$ExternalSyntheticLambda2 implements Function {
    public final /* synthetic */ Map f$0;

    public /* synthetic */ MediaRouter2$RoutingController$$ExternalSyntheticLambda2(Map map) {
        this.f$0 = map;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return (MediaRoute2Info) this.f$0.get((String) obj);
    }
}
