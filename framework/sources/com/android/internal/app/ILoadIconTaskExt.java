package com.android.internal.app;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import com.android.internal.app.ResolverListAdapter;
import com.android.internal.app.chooser.DisplayResolveInfo;
import java.util.List;
/* loaded from: classes4.dex */
public interface ILoadIconTaskExt extends IOplusCommonFeature {
    public static final ILoadIconTaskExt DEFAULT = new ILoadIconTaskExt() { // from class: com.android.internal.app.ILoadIconTaskExt.1
    };

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.ILoadIconTaskExt;
    }

    @Override // android.common.IOplusCommonFeature
    default ILoadIconTaskExt getDefault() {
        return DEFAULT;
    }

    default List<ResolverListAdapter.ViewHolder> getHolderList() {
        return null;
    }

    default void hookonPostExecute(IResolverListAdapterExt resolverListAdapterExt, DisplayResolveInfo displayResolveInfo) {
    }

    default boolean hooksetViewHolder(IResolverListAdapterExt resolverListAdapterExt, DisplayResolveInfo displayResolveInfo, ResolverListAdapter.ViewHolder holder) {
        return false;
    }
}
