package android.telephony;

import java.util.function.IntFunction;
/* loaded from: classes3.dex */
public final /* synthetic */ class TelephonyManager$$ExternalSyntheticLambda18 implements IntFunction {
    public static final /* synthetic */ TelephonyManager$$ExternalSyntheticLambda18 INSTANCE = new TelephonyManager$$ExternalSyntheticLambda18();

    private /* synthetic */ TelephonyManager$$ExternalSyntheticLambda18() {
    }

    @Override // java.util.function.IntFunction
    public final Object apply(int i) {
        String networkTypeName;
        networkTypeName = TelephonyManager.getNetworkTypeName(i);
        return networkTypeName;
    }
}
