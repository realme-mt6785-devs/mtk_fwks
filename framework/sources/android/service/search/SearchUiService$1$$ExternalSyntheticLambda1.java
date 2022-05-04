package android.service.search;

import android.app.search.Query;
import android.app.search.SearchSessionId;
import android.service.search.SearchUiService;
import com.android.internal.util.function.QuadConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class SearchUiService$1$$ExternalSyntheticLambda1 implements QuadConsumer {
    public static final /* synthetic */ SearchUiService$1$$ExternalSyntheticLambda1 INSTANCE = new SearchUiService$1$$ExternalSyntheticLambda1();

    private /* synthetic */ SearchUiService$1$$ExternalSyntheticLambda1() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((SearchUiService) obj).onQuery((SearchSessionId) obj2, (Query) obj3, (SearchUiService.CallbackWrapper) obj4);
    }
}
