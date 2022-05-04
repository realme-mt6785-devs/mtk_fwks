package android.app.blob;

import android.app.SystemServiceRegistry;
import android.content.Context;
import android.os.IBinder;
/* loaded from: classes.dex */
public final /* synthetic */ class BlobStoreManagerFrameworkInitializer$$ExternalSyntheticLambda0 implements SystemServiceRegistry.ContextAwareServiceProducerWithBinder {
    public static final /* synthetic */ BlobStoreManagerFrameworkInitializer$$ExternalSyntheticLambda0 INSTANCE = new BlobStoreManagerFrameworkInitializer$$ExternalSyntheticLambda0();

    private /* synthetic */ BlobStoreManagerFrameworkInitializer$$ExternalSyntheticLambda0() {
    }

    @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithBinder
    public final Object createService(Context context, IBinder iBinder) {
        return BlobStoreManagerFrameworkInitializer.lambda$initialize$0(context, iBinder);
    }
}
