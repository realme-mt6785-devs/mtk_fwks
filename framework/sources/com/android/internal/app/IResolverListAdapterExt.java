package com.android.internal.app;

import android.common.IOplusCommonFeature;
import android.common.OplusFeatureList;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.app.ResolverActivity;
import com.android.internal.app.chooser.DisplayResolveInfo;
import java.util.List;
/* loaded from: classes4.dex */
public interface IResolverListAdapterExt extends IOplusCommonFeature {
    public static final IResolverListAdapterExt DEFAULT = new IResolverListAdapterExt() { // from class: com.android.internal.app.IResolverListAdapterExt.1
    };

    @Override // android.common.IOplusCommonFeature
    default OplusFeatureList.OplusIndex index() {
        return OplusFeatureList.OplusIndex.IResolverListAdapterExt;
    }

    @Override // android.common.IOplusCommonFeature
    default IResolverListAdapterExt getDefault() {
        return DEFAULT;
    }

    default void setPlaceholderResolveList(List<ResolverActivity.ResolvedComponentInfo> infos) {
    }

    default boolean sortComponentsNull(List sortedComponents, boolean originShow) {
        return originShow;
    }

    default boolean isOriginUi() {
        return true;
    }

    default boolean isOpShareUi() {
        return false;
    }

    default void checkIsCtsTest(Context context, Intent intent) {
    }

    default ResolveInfo getResolveInfo(Intent ii, PackageManager mPm) {
        ActivityInfo ai = ii.resolveActivityInfo(mPm, 0);
        if (ai == null) {
            Log.w("IResolverListAdapter", "No activity found for " + ii);
            return null;
        }
        ResolveInfo ri = new ResolveInfo();
        ri.activityInfo = ai;
        return ri;
    }

    default void addMultiAppResolveInfoIfNeed(List<ResolverActivity.ResolvedComponentInfo> sortedComponents, List<Intent> intents, ResolverListController resolverListController, PackageManager packageManager, List<DisplayResolveInfo> displayList) {
    }

    default boolean hookAddResolveInfo(Context context, ResolverListAdapter resolverListAdapter, Intent ii, ResolveInfo ri, List<DisplayResolveInfo> mDisplayList) {
        return false;
    }

    default View hookonCreateView(LayoutInflater mInflater, int resId, ViewGroup parent) {
        return mInflater.inflate(resId, parent, false);
    }

    default List<ResolverActivity.ResolvedComponentInfo> getPlaceholderResolveList() {
        return null;
    }

    default boolean hasCustomFlag(int flag) {
        return false;
    }
}
