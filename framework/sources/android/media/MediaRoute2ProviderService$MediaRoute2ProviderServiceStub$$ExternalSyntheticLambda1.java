package android.media;

import com.android.internal.util.function.QuadConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda1 implements QuadConsumer {
    public static final /* synthetic */ MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda1 INSTANCE = new MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda1();

    private /* synthetic */ MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((MediaRoute2ProviderService) obj).onSetSessionVolume(((Long) obj2).longValue(), (String) obj3, ((Integer) obj4).intValue());
    }
}
