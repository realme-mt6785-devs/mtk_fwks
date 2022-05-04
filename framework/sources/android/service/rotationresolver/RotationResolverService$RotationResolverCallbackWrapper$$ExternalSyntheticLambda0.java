package android.service.rotationresolver;

import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class RotationResolverService$RotationResolverCallbackWrapper$$ExternalSyntheticLambda0 implements TriConsumer {
    public static final /* synthetic */ RotationResolverService$RotationResolverCallbackWrapper$$ExternalSyntheticLambda0 INSTANCE = new RotationResolverService$RotationResolverCallbackWrapper$$ExternalSyntheticLambda0();

    private /* synthetic */ RotationResolverService$RotationResolverCallbackWrapper$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((RotationResolverService) obj).sendFailureResult((IRotationResolverCallback) obj2, ((Integer) obj3).intValue());
    }
}
