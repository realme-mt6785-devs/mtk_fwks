package android.telephony;

import java.util.function.Function;
/* loaded from: classes3.dex */
public final /* synthetic */ class ServiceState$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ ServiceState$$ExternalSyntheticLambda0 INSTANCE = new ServiceState$$ExternalSyntheticLambda0();

    private /* synthetic */ ServiceState$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((NetworkRegistrationInfo) obj).sanitizeLocationInfo();
    }
}
