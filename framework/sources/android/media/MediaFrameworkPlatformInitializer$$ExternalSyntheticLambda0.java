package android.media;

import android.app.SystemServiceRegistry;
import android.content.Context;
/* loaded from: classes2.dex */
public final /* synthetic */ class MediaFrameworkPlatformInitializer$$ExternalSyntheticLambda0 implements SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder {
    public static final /* synthetic */ MediaFrameworkPlatformInitializer$$ExternalSyntheticLambda0 INSTANCE = new MediaFrameworkPlatformInitializer$$ExternalSyntheticLambda0();

    private /* synthetic */ MediaFrameworkPlatformInitializer$$ExternalSyntheticLambda0() {
    }

    @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
    public final Object createService(Context context) {
        return MediaFrameworkPlatformInitializer.lambda$registerServiceWrappers$0(context);
    }
}
