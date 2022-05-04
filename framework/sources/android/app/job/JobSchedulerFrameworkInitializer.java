package android.app.job;

import android.annotation.SystemApi;
import android.app.JobSchedulerImpl;
import android.app.SystemServiceRegistry;
import android.app.job.IJobScheduler;
import android.content.Context;
import android.os.DeviceIdleManager;
import android.os.IBinder;
import android.os.IDeviceIdleController;
import android.os.PowerExemptionManager;
import android.os.PowerWhitelistManager;
@SystemApi
/* loaded from: classes.dex */
public class JobSchedulerFrameworkInitializer {
    private JobSchedulerFrameworkInitializer() {
    }

    public static void registerServiceWrappers() {
        SystemServiceRegistry.registerStaticService("jobscheduler", JobScheduler.class, JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda3.INSTANCE);
        SystemServiceRegistry.registerContextAwareService(Context.DEVICE_IDLE_CONTROLLER, DeviceIdleManager.class, JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda0.INSTANCE);
        SystemServiceRegistry.registerContextAwareService(Context.POWER_WHITELIST_MANAGER, PowerWhitelistManager.class, JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda2.INSTANCE);
        SystemServiceRegistry.registerContextAwareService(Context.POWER_EXEMPTION_SERVICE, PowerExemptionManager.class, JobSchedulerFrameworkInitializer$$ExternalSyntheticLambda1.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ JobScheduler lambda$registerServiceWrappers$0(IBinder b) {
        return new JobSchedulerImpl(IJobScheduler.Stub.asInterface(b));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ DeviceIdleManager lambda$registerServiceWrappers$1(Context context, IBinder b) {
        return new DeviceIdleManager(context, IDeviceIdleController.Stub.asInterface(b));
    }
}
