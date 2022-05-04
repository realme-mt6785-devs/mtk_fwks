package android.sysprop;

import java.util.function.Function;
/* loaded from: classes3.dex */
public final /* synthetic */ class TelephonyProperties$$ExternalSyntheticLambda13 implements Function {
    public static final /* synthetic */ TelephonyProperties$$ExternalSyntheticLambda13 INSTANCE = new TelephonyProperties$$ExternalSyntheticLambda13();

    private /* synthetic */ TelephonyProperties$$ExternalSyntheticLambda13() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        Boolean tryParseBoolean;
        tryParseBoolean = TelephonyProperties.tryParseBoolean((String) obj);
        return tryParseBoolean;
    }
}
