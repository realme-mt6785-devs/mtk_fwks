package android.media;

import android.os.Bundle;
import com.android.internal.util.function.QuintConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda5 implements QuintConsumer {
    public static final /* synthetic */ MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda5 INSTANCE = new MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda5();

    private /* synthetic */ MediaRoute2ProviderService$MediaRoute2ProviderServiceStub$$ExternalSyntheticLambda5() {
    }

    @Override // com.android.internal.util.function.QuintConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        ((MediaRoute2ProviderService) obj).onCreateSession(((Long) obj2).longValue(), (String) obj3, (String) obj4, (Bundle) obj5);
    }
}
