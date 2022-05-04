package android.sysprop;

import java.util.function.Function;
/* loaded from: classes3.dex */
public final /* synthetic */ class HdmiProperties$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ HdmiProperties$$ExternalSyntheticLambda0 INSTANCE = new HdmiProperties$$ExternalSyntheticLambda0();

    private /* synthetic */ HdmiProperties$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        Integer tryParseInteger;
        tryParseInteger = HdmiProperties.tryParseInteger((String) obj);
        return tryParseInteger;
    }
}
