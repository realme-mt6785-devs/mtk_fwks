package android.media;

import java.util.function.UnaryOperator;
/* loaded from: classes2.dex */
public final /* synthetic */ class RoutingSessionInfo$Builder$$ExternalSyntheticLambda0 implements UnaryOperator {
    public static final /* synthetic */ RoutingSessionInfo$Builder$$ExternalSyntheticLambda0 INSTANCE = new RoutingSessionInfo$Builder$$ExternalSyntheticLambda0();

    private /* synthetic */ RoutingSessionInfo$Builder$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return MediaRouter2Utils.getOriginalId((String) obj);
    }
}
