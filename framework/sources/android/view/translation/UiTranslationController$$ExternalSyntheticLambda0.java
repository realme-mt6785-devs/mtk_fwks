package android.view.translation;

import com.android.internal.util.function.QuadConsumer;
import java.util.List;
/* loaded from: classes3.dex */
public final /* synthetic */ class UiTranslationController$$ExternalSyntheticLambda0 implements QuadConsumer {
    public static final /* synthetic */ UiTranslationController$$ExternalSyntheticLambda0 INSTANCE = new UiTranslationController$$ExternalSyntheticLambda0();

    private /* synthetic */ UiTranslationController$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((UiTranslationController) obj).createTranslatorAndStart((TranslationSpec) obj2, (TranslationSpec) obj3, (List) obj4);
    }
}
