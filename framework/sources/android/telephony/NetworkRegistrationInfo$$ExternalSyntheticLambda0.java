package android.telephony;

import java.util.function.Function;
/* loaded from: classes3.dex */
public final /* synthetic */ class NetworkRegistrationInfo$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ NetworkRegistrationInfo$$ExternalSyntheticLambda0 INSTANCE = new NetworkRegistrationInfo$$ExternalSyntheticLambda0();

    private /* synthetic */ NetworkRegistrationInfo$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        String serviceTypeToString;
        serviceTypeToString = NetworkRegistrationInfo.serviceTypeToString(((Integer) obj).intValue());
        return serviceTypeToString;
    }
}
