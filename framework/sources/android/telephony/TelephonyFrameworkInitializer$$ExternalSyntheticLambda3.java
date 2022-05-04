package android.telephony;

import android.app.SystemServiceRegistry;
import android.content.Context;
/* loaded from: classes3.dex */
public final /* synthetic */ class TelephonyFrameworkInitializer$$ExternalSyntheticLambda3 implements SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder {
    public static final /* synthetic */ TelephonyFrameworkInitializer$$ExternalSyntheticLambda3 INSTANCE = new TelephonyFrameworkInitializer$$ExternalSyntheticLambda3();

    private /* synthetic */ TelephonyFrameworkInitializer$$ExternalSyntheticLambda3() {
    }

    @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
    public final Object createService(Context context) {
        return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$3(context);
    }
}
