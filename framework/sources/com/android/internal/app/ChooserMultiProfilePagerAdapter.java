package com.android.internal.app;

import android.content.Context;
import android.os.UserHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.R;
import com.android.internal.app.AbstractMultiProfilePagerAdapter;
import com.android.internal.app.ChooserActivity;
import com.android.internal.widget.GridLayoutManager;
import com.android.internal.widget.RecyclerView;
/* loaded from: classes4.dex */
public class ChooserMultiProfilePagerAdapter extends AbstractMultiProfilePagerAdapter {
    private static final int SINGLE_CELL_SPAN_SIZE = 1;
    private int mBottomOffset;
    private IChooserMultiProfilePagerAdapterExt mCMPPExt = ChooserMultiProfilePagerAdapterExtPlugin.constructor.newInstance();
    private final boolean mIsSendAction;
    private final ChooserProfileDescriptor[] mItems;
    private int mMaxTargetsPerRow;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChooserMultiProfilePagerAdapter(Context context, ChooserActivity.ChooserGridAdapter adapter, UserHandle personalProfileUserHandle, UserHandle workProfileUserHandle, boolean isSendAction, int maxTargetsPerRow) {
        super(context, 0, personalProfileUserHandle, workProfileUserHandle);
        this.mItems = new ChooserProfileDescriptor[]{createProfileDescriptor(adapter)};
        this.mIsSendAction = isSendAction;
        this.mMaxTargetsPerRow = maxTargetsPerRow;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChooserMultiProfilePagerAdapter(Context context, ChooserActivity.ChooserGridAdapter personalAdapter, ChooserActivity.ChooserGridAdapter workAdapter, int defaultProfile, UserHandle personalProfileUserHandle, UserHandle workProfileUserHandle, boolean isSendAction, int maxTargetsPerRow) {
        super(context, defaultProfile, personalProfileUserHandle, workProfileUserHandle);
        this.mItems = new ChooserProfileDescriptor[]{createProfileDescriptor(personalAdapter), createProfileDescriptor(workAdapter)};
        this.mIsSendAction = isSendAction;
        this.mMaxTargetsPerRow = maxTargetsPerRow;
    }

    private ChooserProfileDescriptor createProfileDescriptor(ChooserActivity.ChooserGridAdapter adapter) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ViewGroup rootView = this.mCMPPExt.getChooserProfileDescriptor(inflater);
        return new ChooserProfileDescriptor(rootView, adapter);
    }

    RecyclerView getListViewForIndex(int index) {
        return getItem(index).recyclerView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ChooserProfileDescriptor getItem(int pageIndex) {
        return this.mItems[pageIndex];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public int getItemCount() {
        return this.mItems.length;
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ChooserActivity.ChooserGridAdapter getAdapterForIndex(int pageIndex) {
        return this.mItems[pageIndex].chooserGridAdapter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ChooserListAdapter getListAdapterForUserHandle(UserHandle userHandle) {
        if (getActiveListAdapter().getUserHandle().equals(userHandle)) {
            return getActiveListAdapter();
        }
        if (getInactiveListAdapter() == null || !getInactiveListAdapter().getUserHandle().equals(userHandle)) {
            return null;
        }
        return getInactiveListAdapter();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public void setupListAdapter(int pageIndex) {
        RecyclerView recyclerView = getItem(pageIndex).recyclerView;
        final ChooserActivity.ChooserGridAdapter chooserGridAdapter = getItem(pageIndex).chooserGridAdapter;
        final GridLayoutManager glm = (GridLayoutManager) recyclerView.getLayoutManager();
        glm.setSpanCount(this.mMaxTargetsPerRow);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.android.internal.app.ChooserMultiProfilePagerAdapter.1
            @Override // com.android.internal.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                if (chooserGridAdapter.shouldCellSpan(position)) {
                    return 1;
                }
                return glm.getSpanCount();
            }
        });
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ChooserListAdapter getActiveListAdapter() {
        return getAdapterForIndex(getCurrentPage()).getListAdapter();
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ChooserListAdapter getInactiveListAdapter() {
        if (getCount() == 1) {
            return null;
        }
        return getAdapterForIndex(1 - getCurrentPage()).getListAdapter();
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getPersonalListAdapter() {
        return getAdapterForIndex(0).getListAdapter();
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ResolverListAdapter getWorkListAdapter() {
        return getAdapterForIndex(1).getListAdapter();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public ChooserActivity.ChooserGridAdapter getCurrentRootAdapter() {
        return getAdapterForIndex(getCurrentPage());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public RecyclerView getActiveAdapterView() {
        return getListViewForIndex(getCurrentPage());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public RecyclerView getInactiveAdapterView() {
        if (getCount() == 1) {
            return null;
        }
        return getListViewForIndex(1 - getCurrentPage());
    }

    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    String getMetricsCategory() {
        return "intent_chooser";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public void showWorkProfileOffEmptyState(ResolverListAdapter activeListAdapter, View.OnClickListener listener) {
        showEmptyState(activeListAdapter, R.drawable.ic_work_apps_off, R.string.resolver_turn_on_work_apps, 0, listener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public void showNoPersonalToWorkIntentsEmptyState(ResolverListAdapter activeListAdapter) {
        if (this.mIsSendAction) {
            showEmptyState(activeListAdapter, R.drawable.ic_sharing_disabled, R.string.resolver_cross_profile_blocked, R.string.resolver_cant_share_with_work_apps_explanation);
        } else {
            showEmptyState(activeListAdapter, R.drawable.ic_sharing_disabled, R.string.resolver_cross_profile_blocked, R.string.resolver_cant_access_work_apps_explanation);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public void showNoWorkToPersonalIntentsEmptyState(ResolverListAdapter activeListAdapter) {
        if (this.mIsSendAction) {
            showEmptyState(activeListAdapter, R.drawable.ic_sharing_disabled, R.string.resolver_cross_profile_blocked, R.string.resolver_cant_share_with_personal_apps_explanation);
        } else {
            showEmptyState(activeListAdapter, R.drawable.ic_sharing_disabled, R.string.resolver_cross_profile_blocked, R.string.resolver_cant_access_personal_apps_explanation);
        }
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
    public void setEmptyStateBottomOffset(int bottomOffset) {
        this.mBottomOffset = bottomOffset;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter
    public void setupContainerPadding(View container) {
        int initialBottomPadding = getContext().getResources().getDimensionPixelSize(R.dimen.resolver_empty_state_container_padding_bottom);
        container.setPadding(container.getPaddingLeft(), container.getPaddingTop(), container.getPaddingRight(), this.mBottomOffset + initialBottomPadding);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class ChooserProfileDescriptor extends AbstractMultiProfilePagerAdapter.ProfileDescriptor {
        private ChooserActivity.ChooserGridAdapter chooserGridAdapter;
        private RecyclerView recyclerView;

        ChooserProfileDescriptor(ViewGroup rootView, ChooserActivity.ChooserGridAdapter adapter) {
            super(rootView);
            this.chooserGridAdapter = adapter;
            this.recyclerView = (RecyclerView) rootView.findViewById(R.id.resolver_list);
        }
    }
}
