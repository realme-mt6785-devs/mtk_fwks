package android.media;

import com.android.internal.util.function.QuadConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda4 implements QuadConsumer {
    public static final /* synthetic */ MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda4 INSTANCE = new MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda4();

    private /* synthetic */ MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda4() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((MediaRoute2ProviderService) obj).onTransferToRoute(((Long) obj2).longValue(), (String) obj3, (String) obj4);
    }
}
