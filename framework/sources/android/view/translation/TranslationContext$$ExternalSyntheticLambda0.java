package android.view.translation;

import java.util.function.IntFunction;
/* loaded from: classes3.dex */
public final /* synthetic */ class TranslationContext$$ExternalSyntheticLambda0 implements IntFunction {
    public static final /* synthetic */ TranslationContext$$ExternalSyntheticLambda0 INSTANCE = new TranslationContext$$ExternalSyntheticLambda0();

    private /* synthetic */ TranslationContext$$ExternalSyntheticLambda0() {
    }

    @Override // java.util.function.IntFunction
    public final Object apply(int i) {
        return TranslationContext.singleTranslationFlagToString(i);
    }
}
