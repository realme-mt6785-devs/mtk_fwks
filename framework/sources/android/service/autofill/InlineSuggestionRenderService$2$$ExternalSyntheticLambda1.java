package android.service.autofill;

import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class InlineSuggestionRenderService$2$$ExternalSyntheticLambda1 implements TriConsumer {
    public static final /* synthetic */ InlineSuggestionRenderService$2$$ExternalSyntheticLambda1 INSTANCE = new InlineSuggestionRenderService$2$$ExternalSyntheticLambda1();

    private /* synthetic */ InlineSuggestionRenderService$2$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((InlineSuggestionRenderService) obj).handleDestroySuggestionViews(((Integer) obj2).intValue(), ((Integer) obj3).intValue());
    }
}
