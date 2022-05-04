package android.service.rotationresolver;

import android.os.ICancellationSignal;
import com.android.internal.util.function.QuadConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class RotationResolverService$1$$ExternalSyntheticLambda0 implements QuadConsumer {
    public static final /* synthetic */ RotationResolverService$1$$ExternalSyntheticLambda0 INSTANCE = new RotationResolverService$1$$ExternalSyntheticLambda0();

    private /* synthetic */ RotationResolverService$1$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((RotationResolverService) obj).resolveRotation((IRotationResolverCallback) obj2, (RotationResolutionRequest) obj3, (ICancellationSignal) obj4);
    }
}
