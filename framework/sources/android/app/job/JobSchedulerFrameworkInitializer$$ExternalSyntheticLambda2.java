package android.app.job;

import android.app.SystemServiceRegistry;
import android.content.Context;
import android.os.PowerWhitelistManager;
/* loaded from: classes.dex */
public final /* synthetic */ class JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda2 implements SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder {
    public static final /* synthetic */ JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda2 INSTANCE = new JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda2();

    private /* synthetic */ JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda2() {
    }

    @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
    public final Object createService(Context context) {
        return new PowerWhitelistManager(context);
    }
}
