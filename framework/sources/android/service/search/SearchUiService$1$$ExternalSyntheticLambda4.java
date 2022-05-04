package android.service.search;

import android.app.search.SearchSessionId;
import java.util.function.BiConsumer;
/* loaded from: classes3.dex */
public final /* synthetic */ class SearchUiService$1$$ExternalSyntheticLambda4 implements BiConsumer {
    public static final /* synthetic */ SearchUiService$1$$ExternalSyntheticLambda4 INSTANCE = new SearchUiService$1$$ExternalSyntheticLambda4();

    private /* synthetic */ SearchUiService$1$$ExternalSyntheticLambda4() {
    }

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        ((SearchUiService) obj).doDestroy((SearchSessionId) obj2);
    }
}
