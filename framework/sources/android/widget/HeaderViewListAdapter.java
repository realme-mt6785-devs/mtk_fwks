package android.widget;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes3.dex */
public class HeaderViewListAdapter implements WrapperListAdapter, Filterable {
    static final ArrayList<ListView.FixedViewInfo> EMPTY_INFO_LIST = new ArrayList<>();
    private final ListAdapter mAdapter;
    boolean mAreAllFixedViewsSelectable;
    ArrayList<ListView.FixedViewInfo> mFooterViewInfos;
    ArrayList<ListView.FixedViewInfo> mHeaderViewInfos;
    private final boolean mIsFilterable;

    public HeaderViewListAdapter(ArrayList<ListView.FixedViewInfo> headerViewInfos, ArrayList<ListView.FixedViewInfo> footerViewInfos, ListAdapter adapter) {
        this.mAdapter = adapter;
        this.mIsFilterable = adapter instanceof Filterable;
        if (headerViewInfos == null) {
            this.mHeaderViewInfos = EMPTY_INFO_LIST;
        } else {
            this.mHeaderViewInfos = headerViewInfos;
        }
        if (footerViewInfos == null) {
            this.mFooterViewInfos = EMPTY_INFO_LIST;
        } else {
            this.mFooterViewInfos = footerViewInfos;
        }
        this.mAreAllFixedViewsSelectable = areAllListInfosSelectable(this.mHeaderViewInfos) && areAllListInfosSelectable(this.mFooterViewInfos);
    }

    public int getHeadersCount() {
        return this.mHeaderViewInfos.size();
    }

    public int getFootersCount() {
        return this.mFooterViewInfos.size();
    }

    @Override // android.widget.Adapter
    public boolean isEmpty() {
        ListAdapter listAdapter = this.mAdapter;
        return listAdapter == null || listAdapter.isEmpty();
    }

    private boolean areAllListInfosSelectable(ArrayList<ListView.FixedViewInfo> infos) {
        if (infos == null) {
            return true;
        }
        Iterator<ListView.FixedViewInfo> it = infos.iterator();
        while (it.hasNext()) {
            ListView.FixedViewInfo info = it.next();
            if (!info.isSelectable) {
                return false;
            }
        }
        return true;
    }

    public boolean removeHeader(View v) {
        int i = 0;
        while (true) {
            boolean z = false;
            if (i >= this.mHeaderViewInfos.size()) {
                return false;
            }
            ListView.FixedViewInfo info = this.mHeaderViewInfos.get(i);
            if (info.view == v) {
                this.mHeaderViewInfos.remove(i);
                if (areAllListInfosSelectable(this.mHeaderViewInfos) && areAllListInfosSelectable(this.mFooterViewInfos)) {
                    z = true;
                }
                this.mAreAllFixedViewsSelectable = z;
                return true;
            }
            i++;
        }
    }

    public boolean removeFooter(View v) {
        int i = 0;
        while (true) {
            boolean z = false;
            if (i >= this.mFooterViewInfos.size()) {
                return false;
            }
            ListView.FixedViewInfo info = this.mFooterViewInfos.get(i);
            if (info.view == v) {
                this.mFooterViewInfos.remove(i);
                if (areAllListInfosSelectable(this.mHeaderViewInfos) && areAllListInfosSelectable(this.mFooterViewInfos)) {
                    z = true;
                }
                this.mAreAllFixedViewsSelectable = z;
                return true;
            }
            i++;
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.mAdapter != null) {
            return getFootersCount() + getHeadersCount() + this.mAdapter.getCount();
        }
        return getFootersCount() + getHeadersCount();
    }

    @Override // android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter != null) {
            return this.mAreAllFixedViewsSelectable && listAdapter.areAllItemsEnabled();
        }
        return true;
    }

    @Override // android.widget.ListAdapter
    public boolean isEnabled(int position) {
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return this.mHeaderViewInfos.get(position).isSelectable;
        }
        int adjPosition = position - numHeaders;
        int adapterCount = 0;
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter == null || adjPosition >= (adapterCount = listAdapter.getCount())) {
            return this.mFooterViewInfos.get(adjPosition - adapterCount).isSelectable;
        }
        return this.mAdapter.isEnabled(adjPosition);
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return this.mHeaderViewInfos.get(position).data;
        }
        int adjPosition = position - numHeaders;
        int adapterCount = 0;
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter == null || adjPosition >= (adapterCount = listAdapter.getCount())) {
            return this.mFooterViewInfos.get(adjPosition - adapterCount).data;
        }
        return this.mAdapter.getItem(adjPosition);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        int numHeaders = getHeadersCount();
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter == null || position < numHeaders) {
            return -1L;
        }
        int adjPosition = position - numHeaders;
        int adapterCount = listAdapter.getCount();
        if (adjPosition < adapterCount) {
            return this.mAdapter.getItemId(adjPosition);
        }
        return -1L;
    }

    @Override // android.widget.Adapter
    public boolean hasStableIds() {
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter != null) {
            return listAdapter.hasStableIds();
        }
        return false;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return this.mHeaderViewInfos.get(position).view;
        }
        int adjPosition = position - numHeaders;
        int adapterCount = 0;
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter == null || adjPosition >= (adapterCount = listAdapter.getCount())) {
            return this.mFooterViewInfos.get(adjPosition - adapterCount).view;
        }
        return this.mAdapter.getView(adjPosition, convertView, parent);
    }

    @Override // android.widget.Adapter
    public int getItemViewType(int position) {
        int numHeaders = getHeadersCount();
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter == null || position < numHeaders) {
            return -2;
        }
        int adjPosition = position - numHeaders;
        int adapterCount = listAdapter.getCount();
        if (adjPosition < adapterCount) {
            return this.mAdapter.getItemViewType(adjPosition);
        }
        return -2;
    }

    @Override // android.widget.Adapter
    public int getViewTypeCount() {
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter != null) {
            return listAdapter.getViewTypeCount();
        }
        return 1;
    }

    @Override // android.widget.Adapter
    public void registerDataSetObserver(DataSetObserver observer) {
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(observer);
        }
    }

    @Override // android.widget.Adapter
    public void unregisterDataSetObserver(DataSetObserver observer) {
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter != null) {
            listAdapter.unregisterDataSetObserver(observer);
        }
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.mIsFilterable) {
            return ((Filterable) this.mAdapter).getFilter();
        }
        return null;
    }

    @Override // android.widget.WrapperListAdapter
    public ListAdapter getWrappedAdapter() {
        return this.mAdapter;
    }
}
