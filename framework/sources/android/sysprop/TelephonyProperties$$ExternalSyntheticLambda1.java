package android.sysprop;

import java.util.function.Function;
/* loaded from: classes3.dex */
public final /* synthetic */ class TelephonyProperties$$ExternalSyntheticLambda1 implements Function {
    public static final /* synthetic */ TelephonyProperties$$ExternalSyntheticLambda1 INSTANCE = new TelephonyProperties$$ExternalSyntheticLambda1();

    private /* synthetic */ TelephonyProperties$$ExternalSyntheticLambda1() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        Integer tryParseInteger;
        tryParseInteger = TelephonyProperties.tryParseInteger((String) obj);
        return tryParseInteger;
    }
}
