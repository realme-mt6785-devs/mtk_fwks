package android.service.autofill;

import android.os.RemoteCallback;
import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class InlineSuggestionRenderService$2$$ExternalSyntheticLambda2 implements BiConsumer {
    public static final /* synthetic */ InlineSuggestionRenderService$2$$ExternalSyntheticLambda2 INSTANCE = new InlineSuggestionRenderService$2$$ExternalSyntheticLambda2();

    private /* synthetic */ InlineSuggestionRenderService$2$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((InlineSuggestionRenderService) obj).handleGetInlineSuggestionsRendererInfo((RemoteCallback) obj2);
    }
}
