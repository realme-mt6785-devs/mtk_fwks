package android.telephony;

import android.app.SystemServiceRegistry;
import android.content.Context;
/* loaded from: classes3.dex */
public final /* synthetic */ class TelephonyFrameworkInitializer$$ExternalSyntheticLambda0 implements SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder {
    public static final /* synthetic */ TelephonyFrameworkInitializer$$ExternalSyntheticLambda0 INSTANCE = new TelephonyFrameworkInitializer$$ExternalSyntheticLambda0();

    private /* synthetic */ TelephonyFrameworkInitializer$$ExternalSyntheticLambda0() {
    }

    @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
    public final Object createService(Context context) {
        return TelephonyFrameworkInitializer.lambda$registerServiceWrappers$0(context);
    }
}
