package android.service.search;

import android.app.search.Query;
import android.app.search.SearchSessionId;
import android.app.search.SearchTargetEvent;
import com.android.internal.util.function.QuadConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class SearchUiService$1$$ExternalSyntheticLambda0 implements QuadConsumer {
    public static final /* synthetic */ SearchUiService$1$$ExternalSyntheticLambda0 INSTANCE = new SearchUiService$1$$ExternalSyntheticLambda0();

    private /* synthetic */ SearchUiService$1$$ExternalSyntheticLambda0() {
    }

    @Override // com.android.internal.util.function.QuadConsumer
    public final void accept(Object obj, Object obj2, Object obj3, Object obj4) {
        ((SearchUiService) obj).onNotifyEvent((SearchSessionId) obj2, (Query) obj3, (SearchTargetEvent) obj4);
    }
}
