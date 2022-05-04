package android.media;

import com.android.internal.util.function.QuadConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda3 implements QuadConsumer {
    public static final /* synthetic */ MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda3 INSTANCE = new MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda3();

    private /* synthetic */ MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda3() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((MediaRoute2ProviderService) obj).onSelectRoute(((Long) obj2).longValue(), (String) obj3, (String) obj4);
    }
}
