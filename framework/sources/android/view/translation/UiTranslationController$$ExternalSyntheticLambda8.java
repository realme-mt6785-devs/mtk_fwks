package android.view.translation;

import android.view.View;
import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class UiTranslationController$$ExternalSyntheticLambda8 implements BiConsumer {
    public static final /* synthetic */ UiTranslationController$$ExternalSyntheticLambda8 INSTANCE = new UiTranslationController$$ExternalSyntheticLambda8();

    private /* synthetic */ UiTranslationController$$ExternalSyntheticLambda8() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((ViewTranslationCallback) obj2).onShowTranslation((View) obj);
    }
}
