package android.content.rollback;

import android.app.SystemServiceRegistry;
import android.content.Context;
import android.content.rollback.IRollbackManager;
import android.os.IBinder;
/* loaded from: classes.dex */
public class RollbackManagerFrameworkInitializer {
    private RollbackManagerFrameworkInitializer() {
    }

    public static void initialize() {
        SystemServiceRegistry.registerContextAwareService("rollback", RollbackManager.class, RollbackManagerFrameworkInitializer$$ExternalSyntheticLambda0.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ RollbackManager lambda$initialize$0(Context context, IBinder b) {
        return new RollbackManager(context, IRollbackManager.Stub.asInterface(b));
    }
}
