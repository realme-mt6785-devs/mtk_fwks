package android.service.contentsuggestions;

import android.app.contentsuggestions.ContentSuggestionsManager;
import android.app.contentsuggestions.SelectionsRequest;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ContentSuggestionsService$1$$ExternalSyntheticLambda2 implements TriConsumer {
    public static final /* synthetic */ ContentSuggestionsService$1$$ExternalSyntheticLambda2 INSTANCE = new ContentSuggestionsService$1$$ExternalSyntheticLambda2();

    private /* synthetic */ ContentSuggestionsService$1$$ExternalSyntheticLambda2() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((ContentSuggestionsService) obj).onSuggestContentSelections((SelectionsRequest) obj2, (ContentSuggestionsManager.SelectionsCallback) obj3);
    }
}
