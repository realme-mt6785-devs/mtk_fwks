package android.app.job;

import android.app.SystemServiceRegistry;
import android.os.IBinder;
/* loaded from: classes.dex */
public final /* synthetic */ class JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda3 implements SystemServiceRegistry.StaticServiceProducerWithBinder {
    public static final /* synthetic */ JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda3 INSTANCE = new JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda3();

    private /* synthetic */ JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda3() {
    }

    @Override // android.app.SystemServiceRegistry.StaticServiceProducerWithBinder
    public final Object createService(IBinder iBinder) {
        return JobSchedulerFrameworkInitializer.lambda$registerServiceWrappers$0(iBinder);
    }
}
