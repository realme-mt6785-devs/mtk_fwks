package android.sysprop;

import java.util.function.Function;
/* loaded from: classes3.dex */
public final /* synthetic */ class TelephonyProperties$$ExternalSyntheticLambda10 implements Function {
    public static final /* synthetic */ TelephonyProperties$$ExternalSyntheticLambda10 INSTANCE = new TelephonyProperties$$ExternalSyntheticLambda10();

    private /* synthetic */ TelephonyProperties$$ExternalSyntheticLambda10() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        String tryParseString;
        tryParseString = TelephonyProperties.tryParseString((String) obj);
        return tryParseString;
    }
}
