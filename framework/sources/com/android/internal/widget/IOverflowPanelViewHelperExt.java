package com.android.internal.widget;

import android.view.View;
import com.android.internal.R;
/* loaded from: classes4.dex */
public interface IOverflowPanelViewHelperExt {
    public static final IOverflowPanelViewHelperExt DEFAULT = new IOverflowPanelViewHelperExt() { // from class: com.android.internal.widget.IOverflowPanelViewHelperExt.1
    };

    default void setOverflowMenuCount(int count) {
    }

    default void setConvertViewPosition(int position) {
    }

    default void setOverflowDirection(boolean upward) {
    }

    default void hookGetView(View convertView, int sidePadding, int minimumWidth) {
    }

    default int hookGetSidePaddingRes() {
        return R.dimen.floating_toolbar_overflow_side_padding;
    }
}
