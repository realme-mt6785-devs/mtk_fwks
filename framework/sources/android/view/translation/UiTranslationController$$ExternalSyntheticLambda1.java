package android.view.translation;

import com.android.internal.util.function.TriConsumer;
import java.util.ArrayList;
/* loaded from: classes3.dex */
public final /* synthetic */ class UiTranslationController$$ExternalSyntheticLambda1 implements TriConsumer {
    public static final /* synthetic */ UiTranslationController$$ExternalSyntheticLambda1 INSTANCE = new UiTranslationController$$ExternalSyntheticLambda1();

    private /* synthetic */ UiTranslationController$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((UiTranslationController) obj).sendTranslationRequest((Translator) obj2, (ArrayList) obj3);
    }
}
