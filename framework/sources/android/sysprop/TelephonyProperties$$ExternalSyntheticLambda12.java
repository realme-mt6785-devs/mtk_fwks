package android.sysprop;

import java.util.function.Function;
/* loaded from: classes3.dex */
public final /* synthetic */ class TelephonyProperties$$ExternalSyntheticLambda12 implements Function {
    public static final /* synthetic */ TelephonyProperties$$ExternalSyntheticLambda12 INSTANCE = new TelephonyProperties$$ExternalSyntheticLambda12();

    private /* synthetic */ TelephonyProperties$$ExternalSyntheticLambda12() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        String tryParseString;
        tryParseString = TelephonyProperties.tryParseString((String) obj);
        return tryParseString;
    }
}
