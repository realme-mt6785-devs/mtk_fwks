package com.android.internal.app;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.view.View;
import com.android.internal.R;
/* loaded from: classes4.dex */
public interface IMultiProfilePagerAdapterExt extends IOplusCommonFeature {
    public static final IMultiProfilePagerAdapterExt DEFAULT = new IMultiProfilePagerAdapterExt() { // from class: com.android.internal.app.IMultiProfilePagerAdapterExt.1
    };

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IMultiProfilePagerAdapterExt;
    }

    @Override // android.common.IOplusCommonFeature
    default IMultiProfilePagerAdapterExt getDefault() {
        return DEFAULT;
    }

    default boolean isOriginUi() {
        return true;
    }

    default void showActiveEmptyViewState() {
    }

    default void showWorkProfileOffEmptyState(AbstractMultiProfilePagerAdapter profilePagerAdapter, ResolverListAdapter activeListAdapter, View.OnClickListener listener) {
        profilePagerAdapter.showWorkProfileOffEmptyState(activeListAdapter, listener);
    }

    default void showNoPersonalToWorkIntentsEmptyState(AbstractMultiProfilePagerAdapter profilePagerAdapter, ResolverListAdapter activeListAdapter) {
        profilePagerAdapter.showNoPersonalToWorkIntentsEmptyState(activeListAdapter);
    }

    default void showNoPersonalAppsAvailableEmptyState(AbstractMultiProfilePagerAdapter profilePagerAdapter, ResolverListAdapter activeListAdapter) {
        profilePagerAdapter.showNoPersonalAppsAvailableEmptyState(activeListAdapter);
    }

    default void showNoWorkAppsAvailableEmptyState(AbstractMultiProfilePagerAdapter profilePagerAdapter, ResolverListAdapter activeListAdapter) {
        profilePagerAdapter.showNoWorkAppsAvailableEmptyState(activeListAdapter);
    }

    default void showNoWorkToPersonalIntentsEmptyState(AbstractMultiProfilePagerAdapter profilePagerAdapter, ResolverListAdapter activeListAdapter) {
        profilePagerAdapter.showNoWorkToPersonalIntentsEmptyState(activeListAdapter);
    }

    default void hookResetViewVisibilitiesForConsumerUserEmptyState(View emptyStateView) {
        emptyStateView.findViewById(R.id.resolver_empty_state_icon).setVisibility(8);
    }
}
