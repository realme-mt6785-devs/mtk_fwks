package android.service.rotationresolver;

import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class RotationResolverService$RotationResolverCallbackWrapper$$ExternalSyntheticLambda1 implements TriConsumer {
    public static final /* synthetic */ RotationResolverService$RotationResolverCallbackWrapper$$ExternalSyntheticLambda1 INSTANCE = new RotationResolverService$RotationResolverCallbackWrapper$$ExternalSyntheticLambda1();

    private /* synthetic */ RotationResolverService$RotationResolverCallbackWrapper$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((RotationResolverService) obj).sendRotationResult((IRotationResolverCallback) obj2, ((Integer) obj3).intValue());
    }
}
