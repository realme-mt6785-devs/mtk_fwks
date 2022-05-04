package android.telephony;

import android.app.SystemServiceRegistry;
import android.content.Context;
/* loaded from: classes3.dex */
public final /* synthetic */ class TelephonyFrameworkInitializer$$ExternalSyntheticLambda6 implements SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder {
    public static final /* synthetic */ TelephonyFrameworkInitializer$$ExternalSyntheticLambda6 INSTANCE = new TelephonyFrameworkInitializer$$ExternalSyntheticLambda6();

    private /* synthetic */ TelephonyFrameworkInitializer$$ExternalSyntheticLambda6() {
    }

    @Override // android.app.SystemServiceRegistry.ContextAwareServiceProducerWithoutBinder
    public final Object createService(Context context) {
        SmsManager smsManagerForContextAndSubscriptionId;
        smsManagerForContextAndSubscriptionId = SmsManager.getSmsManagerForContextAndSubscriptionId(context, Integer.MAX_VALUE);
        return smsManagerForContextAndSubscriptionId;
    }
}
