package android.telephony;

import android.app.SystemServiceRegistry;
import android.content.Context;
import android.os.TelephonyServiceManager;
import android.telephony.euicc.EuiccCardManager;
import android.telephony.euicc.EuiccManager;
import android.telephony.ims.ImsManager;
import com.android.internal.util.Preconditions;
/* loaded from: classes3.dex */
public class TelephonyFrameworkInitializer {
    private static volatile TelephonyServiceManager sTelephonyServiceManager;

    private TelephonyFrameworkInitializer() {
    }

    public static void setTelephonyServiceManager(TelephonyServiceManager telephonyServiceManager) {
        Preconditions.checkState(sTelephonyServiceManager == null, "setTelephonyServiceManager called twice!");
        sTelephonyServiceManager = (TelephonyServiceManager) Preconditions.checkNotNull(telephonyServiceManager);
    }

    public static void registerServiceWrappers() {
        SystemServiceRegistry.registerContextAwareService("phone", TelephonyManager.class, TelephonyFrameworkInitializer$$ExternalSyntheticLambda0.INSTANCE);
        SystemServiceRegistry.registerContextAwareService(Context.TELEPHONY_SUBSCRIPTION_SERVICE, SubscriptionManager.class, TelephonyFrameworkInitializer$$ExternalSyntheticLambda1.INSTANCE);
        SystemServiceRegistry.registerContextAwareService("carrier_config", CarrierConfigManager.class, TelephonyFrameworkInitializer$$ExternalSyntheticLambda2.INSTANCE);
        SystemServiceRegistry.registerContextAwareService(Context.EUICC_SERVICE, EuiccManager.class, TelephonyFrameworkInitializer$$ExternalSyntheticLambda3.INSTANCE);
        SystemServiceRegistry.registerContextAwareService(Context.EUICC_CARD_SERVICE, EuiccCardManager.class, TelephonyFrameworkInitializer$$ExternalSyntheticLambda4.INSTANCE);
        SystemServiceRegistry.registerContextAwareService(Context.TELEPHONY_IMS_SERVICE, ImsManager.class, TelephonyFrameworkInitializer$$ExternalSyntheticLambda5.INSTANCE);
        SystemServiceRegistry.registerContextAwareService(Context.SMS_SERVICE, SmsManager.class, TelephonyFrameworkInitializer$$ExternalSyntheticLambda6.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ TelephonyManager lambda$registerServiceWrappers$0(Context context) {
        return new TelephonyManager(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SubscriptionManager lambda$registerServiceWrappers$1(Context context) {
        return new SubscriptionManager(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ CarrierConfigManager lambda$registerServiceWrappers$2(Context context) {
        return new CarrierConfigManager(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ EuiccManager lambda$registerServiceWrappers$3(Context context) {
        return new EuiccManager(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ EuiccCardManager lambda$registerServiceWrappers$4(Context context) {
        return new EuiccCardManager(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ImsManager lambda$registerServiceWrappers$5(Context context) {
        return new ImsManager(context);
    }

    public static TelephonyServiceManager getTelephonyServiceManager() {
        return sTelephonyServiceManager;
    }
}
