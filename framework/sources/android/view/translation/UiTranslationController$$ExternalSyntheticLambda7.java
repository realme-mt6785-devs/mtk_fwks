package android.view.translation;

import android.view.View;
import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class UiTranslationController$$ExternalSyntheticLambda7 implements BiConsumer {
    public static final /* synthetic */ UiTranslationController$$ExternalSyntheticLambda7 INSTANCE = new UiTranslationController$$ExternalSyntheticLambda7();

    private /* synthetic */ UiTranslationController$$ExternalSyntheticLambda7() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((ViewTranslationCallback) obj2).onHideTranslation((View) obj);
    }
}
