package android.telephony;

import android.app.SystemServiceRegistry;
import android.content.Context;
/* loaded from: classes3.dex */
public final /* synthetic */ class TelephonyFrameworkInitializer$$ExternalSyntheticLambda1 implements SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder {
    public static final /* synthetic */ TelephonyFrameworkInitializer$$ExternalSyntheticLambda1 INSTANCE = new TelephonyFrameworkInitializer$$ExternalSyntheticLambda1();

    private /* synthetic */ TelephonyFrameworkInitializer$$ExternalSyntheticLambda1() {
    }

    @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
    public final Object createService(Context context) {
        return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$1(context);
    }
}
