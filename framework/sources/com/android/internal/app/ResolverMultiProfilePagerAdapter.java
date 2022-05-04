package com.android.internal.app;

import android.content.Context;
import android.content.res.Resources;
import android.os.UserHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.internal.R;
import com.android.internal.app.AbstractMultiProfilePagerAdapter;
/* loaded from: classes4.dex */
public class ResolverMultiProfilePagerAdapter extends AbstractMultiProfilePagerAdapter {
    private final ResolverProfileDescriptor[] mItems;
    private IResolverMultiProfilePagerAdapterExt mRMPPExt;
    private final boolean mShouldShowNoCrossProfileIntentsEmptyState;
    private boolean mUseLayoutWithDefault;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolverMultiProfilePagerAdapter(Context context, ResolverListAdapter adapter, UserHandle personalProfileUserHandle, UserHandle workProfileUserHandle) {
        super(context, 0, personalProfileUserHandle, workProfileUserHandle);
        this.mRMPPExt = ResolverMultiProfilePagerAdapterExtPlugin.constructor.newInstance();
        this.mItems = new ResolverProfileDescriptor[]{createProfileDescriptor(adapter)};
        this.mShouldShowNoCrossProfileIntentsEmptyState = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolverMultiProfilePagerAdapter(Context context, ResolverListAdapter personalAdapter, ResolverListAdapter workAdapter, int defaultProfile, UserHandle personalProfileUserHandle, UserHandle workProfileUserHandle, boolean shouldShowNoCrossProfileIntentsEmptyState) {
        super(context, defaultProfile, personalProfileUserHandle, workProfileUserHandle);
        this.mRMPPExt = ResolverMultiProfilePagerAdapterExtPlugin.constructor.newInstance();
        this.mItems = new ResolverProfileDescriptor[]{createProfileDescriptor(personalAdapter), createProfileDescriptor(workAdapter)};
        this.mShouldShowNoCrossProfileIntentsEmptyState = shouldShowNoCrossProfileIntentsEmptyState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public void updateAfterConfigChange() {
        ResolverProfileDescriptor[] resolverProfileDescriptorArr;
        super.updateAfterConfigChange();
        for (ResolverProfileDescriptor descriptor : this.mItems) {
            View emptyStateCont = descriptor.rootView.findViewById(R.id.resolver_empty_state_container);
            Resources resources = getContext().getResources();
            emptyStateCont.setPadding(emptyStateCont.getPaddingLeft(), resources.getDimensionPixelSize(R.dimen.resolver_empty_state_container_padding_top), emptyStateCont.getPaddingRight(), resources.getDimensionPixelSize(R.dimen.resolver_empty_state_container_padding_bottom));
        }
    }

    private ResolverProfileDescriptor createProfileDescriptor(ResolverListAdapter adapter) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ViewGroup rootView = this.mRMPPExt.getResolverProfileDescriptor(inflater);
        return new ResolverProfileDescriptor(rootView, adapter);
    }

    ListView getListViewForIndex(int index) {
        return getItem(index).listView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverProfileDescriptor getItem(int pageIndex) {
        return this.mItems[pageIndex];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public int getItemCount() {
        return this.mItems.length;
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    void setupListAdapter(int pageIndex) {
        ListView listView = getItem(pageIndex).listView;
        listView.setAdapter((ListAdapter) getItem(pageIndex).resolverListAdapter);
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getAdapterForIndex(int pageIndex) {
        return this.mItems[pageIndex].resolverListAdapter;
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter, com.android.internal.widget.PagerAdapter
    public ViewGroup instantiateItem(ViewGroup container, int position) {
        setupListAdapter(position);
        return super.instantiateItem(container, position);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getListAdapterForUserHandle(UserHandle userHandle) {
        if (getActiveListAdapter().getUserHandle().equals(userHandle)) {
            return getActiveListAdapter();
        }
        if (getInactiveListAdapter() == null || !getInactiveListAdapter().getUserHandle().equals(userHandle)) {
            return null;
        }
        return getInactiveListAdapter();
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getActiveListAdapter() {
        return getAdapterForIndex(getCurrentPage());
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getInactiveListAdapter() {
        if (getCount() == 1) {
            return null;
        }
        return getAdapterForIndex(1 - getCurrentPage());
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getPersonalListAdapter() {
        return getAdapterForIndex(0);
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getWorkListAdapter() {
        return getAdapterForIndex(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getCurrentRootAdapter() {
        return getActiveListAdapter();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ListView getActiveAdapterView() {
        return getListViewForIndex(getCurrentPage());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ViewGroup getInactiveAdapterView() {
        if (getCount() == 1) {
            return null;
        }
        return getListViewForIndex(1 - getCurrentPage());
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    String getMetricsCategory() {
        return "intent_resolver";
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    boolean allowShowNoCrossProfileIntentsEmptyState() {
        return this.mShouldShowNoCrossProfileIntentsEmptyState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public void showWorkProfileOffEmptyState(ResolverListAdapter activeListAdapter, View.OnClickListener listener) {
        showEmptyState(activeListAdapter, R.drawable.ic_work_apps_off, R.string.resolver_turn_on_work_apps, 0, listener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public void showNoPersonalToWorkIntentsEmptyState(ResolverListAdapter activeListAdapter) {
        showEmptyState(activeListAdapter, R.drawable.ic_sharing_disabled, R.string.resolver_cross_profile_blocked, R.string.resolver_cant_access_work_apps_explanation);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public void showNoWorkToPersonalIntentsEmptyState(ResolverListAdapter activeListAdapter) {
        showEmptyState(activeListAdapter, R.drawable.ic_sharing_disabled, R.string.resolver_cross_profile_blocked, R.string.resolver_cant_access_personal_apps_explanation);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public void showNoPersonalAppsAvailableEmptyState(ResolverListAdapter listAdapter) {
        showEmptyState(listAdapter, R.drawable.ic_no_apps, R.string.resolver_no_personal_apps_available, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public void showNoWorkAppsAvailableEmptyState(ResolverListAdapter listAdapter) {
        showEmptyState(listAdapter, R.drawable.ic_no_apps, R.string.resolver_no_work_apps_available, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUseLayoutWithDefault(boolean useLayoutWithDefault) {
        this.mUseLayoutWithDefault = useLayoutWithDefault;
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    protected void setupContainerPadding(View container) {
        int bottom = this.mUseLayoutWithDefault ? container.getPaddingBottom() : 0;
        container.setPadding(container.getPaddingLeft(), container.getPaddingTop(), container.getPaddingRight(), bottom);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class ResolverProfileDescriptor extends AbstractMultiProfilePagerAdapter.ProfileDescriptor {
        final ListView listView;
        private ResolverListAdapter resolverListAdapter;

        ResolverProfileDescriptor(ViewGroup rootView, ResolverListAdapter adapter) {
            super(rootView);
            this.resolverListAdapter = adapter;
            this.listView = (ListView) rootView.findViewById(R.id.resolver_list);
        }
    }
}
