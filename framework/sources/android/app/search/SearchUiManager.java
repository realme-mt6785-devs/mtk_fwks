package android.app.search;

import android.annotation.SystemApi;
import android.content.Context;
import java.util.Objects;
@SystemApi
/* loaded from: classes.dex */
public final class SearchUiManager {
    private final Context mContext;

    public SearchUiManager(Context context) {
        Objects.requireNonNull(context);
        this.mContext = context;
    }

    public SearchSession createSearchSession(SearchContext searchContext) {
        return new SearchSession(this.mContext, searchContext);
    }
}
