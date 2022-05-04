package android.sysprop;

import java.util.function.Function;
/* loaded from: classes3.dex */
public final /* synthetic */ class TelephonyProperties$$ExternalSyntheticLambda3 implements Function {
    public static final /* synthetic */ TelephonyProperties$$ExternalSyntheticLambda3 INSTANCE = new TelephonyProperties$$ExternalSyntheticLambda3();

    private /* synthetic */ TelephonyProperties$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        Integer tryParseInteger;
        tryParseInteger = TelephonyProperties.tryParseInteger((String) obj);
        return tryParseInteger;
    }
}
