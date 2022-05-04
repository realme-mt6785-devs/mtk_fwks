package android.app.job;

import android.app.SystemServiceRegistry;
import android.content.Context;
import android.os.PowerExemptionManager;
/* loaded from: classes.dex */
public final /* synthetic */ class JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda1 implements SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder {
    public static final /* synthetic */ JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda1 INSTANCE = new JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda1();

    private /* synthetic */ JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda1() {
    }

    @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
    public final Object createService(Context context) {
        return new PowerExemptionManager(context);
    }
}
