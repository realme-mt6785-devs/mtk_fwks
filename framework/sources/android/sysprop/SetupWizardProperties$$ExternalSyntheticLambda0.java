package android.sysprop;

import java.util.function.Function;
/* loaded from: classes3.dex */
public final /* synthetic */ class SetupWizardProperties$$ExternalSyntheticLambda0 implements Function {
    public static final /* synthetic */ SetupWizardProperties$$ExternalSyntheticLambda0 INSTANCE = new SetupWizardProperties$$ExternalSyntheticLambda0();

    private /* synthetic */ SetupWizardProperties$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        String tryParseString;
        tryParseString = SetupWizardProperties.tryParseString((String) obj);
        return tryParseString;
    }
}
