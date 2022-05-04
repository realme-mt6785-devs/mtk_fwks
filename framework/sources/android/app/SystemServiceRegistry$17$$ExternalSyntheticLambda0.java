package android.app;

import android.os.IBinder;
import android.os.ServiceManager;
import java.util.function.Supplier;
/* loaded from: classes.dex */
public final /* synthetic */ class SystemServiceRegistry$17$$ExternalSyntheticLambda0 implements Supplier {
    public static final /* synthetic */ SystemServiceRegistry$17$$ExternalSyntheticLambda0 INSTANCE = new SystemServiceRegistry$17$$ExternalSyntheticLambda0();

    private /* synthetic */ SystemServiceRegistry$17$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        IBinder service;
        service = ServiceManager.getService("tethering");
        return service;
    }
}
