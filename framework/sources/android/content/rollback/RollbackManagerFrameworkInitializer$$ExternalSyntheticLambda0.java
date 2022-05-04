package android.content.rollback;

import android.app.SystemServiceRegistry;
import android.content.Context;
import android.os.IBinder;
/* loaded from: classes.dex */
public final /* synthetic */ class RollbackManagerFrameworkInitializer$$ExternalSyntheticLambda0 implements SystemServiceRegistry.ContextAwareServiceProducerWithBinder {
    public static final /* synthetic */ RollbackManagerFrameworkInitializer$$ExternalSyntheticLambda0 INSTANCE = new RollbackManagerFrameworkInitializer$$ExternalSyntheticLambda0();

    private /* synthetic */ RollbackManagerFrameworkInitializer$$ExternalSyntheticLambda0() {
    }

    @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithBinder
    public final Object createService(Context context, IBinder iBinder) {
        return RollbackManagerFrameworkInitializer.lambda$initialize$0(context, iBinder);
    }
}
