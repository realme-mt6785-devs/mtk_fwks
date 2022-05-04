package android.media;

import com.android.internal.util.function.TriConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda6 implements TriConsumer {
    public static final /* synthetic */ MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda6 INSTANCE = new MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda6();

    private /* synthetic */ MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda6() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((MediaRoute2ProviderService) obj).onReleaseSession(((Long) obj2).longValue(), (String) obj3);
    }
}
