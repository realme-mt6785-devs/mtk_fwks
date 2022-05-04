package android.service.contentsuggestions;

import android.app.contentsuggestions.ClassificationsRequest;
import android.app.contentsuggestions.ContentSuggestionsManager;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ContentSuggestionsService$1$$ExternalSyntheticLambda1 implements TriConsumer {
    public static final /* synthetic */ ContentSuggestionsService$1$$ExternalSyntheticLambda1 INSTANCE = new ContentSuggestionsService$1$$ExternalSyntheticLambda1();

    private /* synthetic */ ContentSuggestionsService$1$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((ContentSuggestionsService) obj).onClassifyContentSelections((ClassificationsRequest) obj2, (ContentSuggestionsManager.ClassificationsCallback) obj3);
    }
}
