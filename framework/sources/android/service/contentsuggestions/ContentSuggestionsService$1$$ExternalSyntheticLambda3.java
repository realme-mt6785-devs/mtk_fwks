package android.service.contentsuggestions;

import android.os.Bundle;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class ContentSuggestionsService$1$$ExternalSyntheticLambda3 implements TriConsumer {
    public static final /* synthetic */ ContentSuggestionsService$1$$ExternalSyntheticLambda3 INSTANCE = new ContentSuggestionsService$1$$ExternalSyntheticLambda3();

    private /* synthetic */ ContentSuggestionsService$1$$ExternalSyntheticLambda3() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((ContentSuggestionsService) obj).onNotifyInteraction((String) obj2, (Bundle) obj3);
    }
}
