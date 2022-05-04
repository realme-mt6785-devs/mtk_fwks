package android.telephony;

import android.app.SystemServiceRegistry;
import android.content.Context;
/* loaded from: classes3.dex */
public final /* synthetic */ class TelephonyFrameworkInitializer$$ExternalSyntheticLambda4 implements SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder {
    public static final /* synthetic */ TelephonyFrameworkInitializer$$ExternalSyntheticLambda4 INSTANCE = new TelephonyFrameworkInitializer$$ExternalSyntheticLambda4();

    private /* synthetic */ TelephonyFrameworkInitializer$$ExternalSyntheticLambda4() {
    }

    @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
    public final Object createService(Context context) {
        return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$4(context);
    }
}
