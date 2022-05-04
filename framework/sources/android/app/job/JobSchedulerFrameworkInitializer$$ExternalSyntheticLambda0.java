package android.app.job;

import android.app.SystemServiceRegistry;
import android.content.Context;
import android.os.IBinder;
/* loaded from: classes.dex */
public final /* synthetic */ class JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda0 implements SystemServiceRegistry.ContextAwareServiceProducerWithBinder {
    public static final /* synthetic */ JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda0 INSTANCE = new JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda0();

    private /* synthetic */ JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda0() {
    }

    @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithBinder
    public final Object createService(Context context, IBinder iBinder) {
        return JobSchedulerFrameworkInitializer.lambda$registerServiceWrappers$1(context, iBinder);
    }
}
