package android.sysprop;

import java.util.function.Function;
/* loaded from: classes3.dex */
public final /* synthetic */ class TelephonyProperties$$ExternalSyntheticLambda9 implements Function {
    public static final /* synthetic */ TelephonyProperties$$ExternalSyntheticLambda9 INSTANCE = new TelephonyProperties$$ExternalSyntheticLambda9();

    private /* synthetic */ TelephonyProperties$$ExternalSyntheticLambda9() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        String tryParseString;
        tryParseString = TelephonyProperties.tryParseString((String) obj);
        return tryParseString;
    }
}
