package android.telephony;

import android.app.SystemServiceRegistry;
import android.content.Context;
/* loaded from: classes3.dex */
public final /* synthetic */ class TelephonyFrameworkInitializer$$ExternalSyntheticLambda5 implements SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder {
    public static final /* synthetic */ TelephonyFrameworkInitializer$$ExternalSyntheticLambda5 INSTANCE = new TelephonyFrameworkInitializer$$ExternalSyntheticLambda5();

    private /* synthetic */ TelephonyFrameworkInitializer$$ExternalSyntheticLambda5() {
    }

    @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
    public final Object createService(Context context) {
        return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$5(context);
    }
}
