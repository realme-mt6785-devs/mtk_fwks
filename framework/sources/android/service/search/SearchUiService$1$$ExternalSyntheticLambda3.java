package android.service.search;

import android.app.search.SearchContext;
import android.app.search.SearchSessionId;
import com.android.internal.util.function.TriConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class SearchUiService$1$$ExternalSyntheticLambda3 implements TriConsumer {
    public static final /* synthetic */ SearchUiService$1$$ExternalSyntheticLambda3 INSTANCE = new SearchUiService$1$$ExternalSyntheticLambda3();

    private /* synthetic */ SearchUiService$1$$ExternalSyntheticLambda3() {
    }

    @Override // com.android.internal.util.function.TriConsumer
    public final void accept(Object obj, Object obj2, Object obj3) {
        ((SearchUiService) obj).onSearchSessionCreated((SearchContext) obj2, (SearchSessionId) obj3);
    }
}
