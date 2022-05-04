package com.android.internal.app;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.internal.R;
/* loaded from: classes4.dex */
public interface IChooserMultiProfilePagerAdapterExt {
    default ViewGroup getChooserProfileDescriptor(LayoutInflater inflater) {
        return (ViewGroup) inflater.inflate(R.layout.chooser_list_per_profile, (ViewGroup) null, false);
    }
}
