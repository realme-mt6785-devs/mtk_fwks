package android.inputmethodservice;

import android.view.autofill.AutofillId;
import android.view.inputmethod.InlineSuggestionsResponse;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes2.dex */
public final /* synthetic */ class InlineSuggestionSession$InlineSuggestionsResponseCallbackImpl$$ExternalSyntheticLambda0 implements TriConsumer {
    public static final /* synthetic */ InlineSuggestionSession$InlineSuggestionsResponseCallbackImpl$$ExternalSyntheticLambda0 INSTANCE = new InlineSuggestionSession$InlineSuggestionsResponseCallbackImpl$$ExternalSyntheticLambda0();

    private /* synthetic */ InlineSuggestionSession$InlineSuggestionsResponseCallbackImpl$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((InlineSuggestionSession) obj).handleOnInlineSuggestionsResponse((AutofillId) obj2, (InlineSuggestionsResponse) obj3);
    }
}
