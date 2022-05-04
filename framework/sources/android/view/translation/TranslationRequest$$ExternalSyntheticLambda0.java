package android.view.translation;

import java.util.function.IntFunction;
/* loaded from: classes3.dex */
public final /* synthetic */ class TranslationRequest$$ExternalSyntheticLambda0 implements IntFunction {
    public static final /* synthetic */ TranslationRequest$$ExternalSyntheticLambda0 INSTANCE = new TranslationRequest$$ExternalSyntheticLambda0();

    private /* synthetic */ TranslationRequest$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.IntFunction
    public final Object apply(int i) {
        return TranslationRequest.singleRequestFlagsToString(i);
    }
}
